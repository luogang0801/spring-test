package com.spring.store;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;

import com.spring.util.string.PropertiesFileUtil;

/**
 * HDFS 文件保存适配器
 * 
 * @date 2015年10月28日 上午10:31:44
 * @author maliang
 */
public class HDFSStoreAdpter extends FileStoreAdpter {

	/**
	 * 配置文件读取工具
	 */
	static PropertiesFileUtil pf = null;

	/**
	 * 默认读取字节数
	 */
	private static final int BUFF = 4096;

	/**
	 * 加载hdfs配置文件，配置文件默认在classpath 下面
	 */
	static {
		try {
			pf = PropertiesFileUtil.builder("hdfs.properties");
		} catch (ConfigurationException e) {
			throw new RuntimeException("加载hdfs配置文件出错", e);
		}
	}

	/**
	 * 获取hdfs集群配置信息
	 * <p>
	 * 默认的key值在配置文件
	 * </p>
	 * 
	 * @return Configuration
	 * @date 2015年10月28日 上午10:44:32
	 * @author maliang
	 */
	private Configuration getClusterConfig() {
		Configuration conf = new Configuration();

		URL coreSiteURL = getClass().getResource("/core-site.xml");
		if (coreSiteURL == null) {
			throw new RuntimeException("classpath 下未找到 core-site.xml 配置文件");
		}
		URL hdfsSiteURL = getClass().getResource("/hdfs-site.xml");
		if (hdfsSiteURL == null) {
			throw new RuntimeException("classpath 下未找到 hdfs-site.xml 配置文件");
		}
		conf.addResource(coreSiteURL.toString());
		conf.addResource(hdfsSiteURL.toString());

		// conf.set("fs.defaultFS", pf.getString("fs.defaultFS"));
		// conf.set("dfs.nameservices", pf.getString("dfs.nameservices"));
		// conf.set("dfs.replication", pf.getString("dfs.replication"));
		// conf.set("dfs.ha.namenodes.ns1", pf.getString("dfs.ha.namenodes.ns1"));
		// conf.set("dfs.namenode.rpc-address.ns1.nn1", pf.getString("dfs.namenode.rpc-address.ns1.nn1"));
		// conf.set("dfs.namenode.rpc-address.ns1.nn2", pf.getString("dfs.namenode.rpc-address.ns1.nn2"));
		// conf.set("dfs.client.failover.proxy.provider.ns1", pf.getString("dfs.client.failover.proxy.provider.ns1"));
		// conf.set("dfs.ha.automatic-failover.enabled", pf.getString("dfs.ha.automatic-failover.enabled"));

		return conf;
	}

	/**
	 * 获取hdfs 集群uri
	 * 
	 * @return URI
	 * @throws URISyntaxException
	 * @date 2015年10月28日 上午10:53:59
	 * @author maliang
	 */
	private URI getClusterURI() throws URISyntaxException {
		return new URI(pf.getString("fs.defaultFS"));
	}

	private FileSystem getFileSystem() throws IOException, InterruptedException, URISyntaxException {
		return FileSystem.get(getClusterURI(), getClusterConfig(), pf.getString("dfs.user"));

	}

	@Override
	public String save(String des, String target) throws Exception {
		InputStream is = new FileInputStream(des);
		return save(is, target);
	}

	/**
	 * 保存文件，路径由客户端自定义
	 */
	@Override
	public String save(InputStream is, String target) throws Exception {
		FileSystem fs = getFileSystem();
		if (StringUtils.isBlank(target)) {
			// 目标路径没指定，保存到hdfs默认路径
			target = File.separatorChar + "";
		}

		if (!target.startsWith("/")) {
			target = "/" + target;
		}

		OutputStream out = fs.create(new Path(target));
		IOUtils.copyBytes(is, out, BUFF, true);
		fs.close();
		return target;
	}

	/**
	 * 删除文件，根据文件保存地址
	 */
	@Override
	public boolean delete(String target) throws Exception {
		if (StringUtils.isBlank(target)) {
			return false;
		}
		// 默认在hdfs 根路径下面
		if (!target.startsWith("/")) {
			target = "/" + target;
		}

		FileSystem fs = FileSystem.get(getClusterURI(), getClusterConfig(), pf.getString("dfs.user"));
		return fs.delete(new Path(target), true);
	}

	@Override
	public InputStream get(String target) throws Exception {
		if (StringUtils.isBlank(target)) {
			return null;
		}
		// FileSystem fs = FileSystem.get(getClusterURI(), getClusterConfig());
		// FileSystem fs = FileSystem.newInstance(getClusterURI(), getClusterConfig());
		FileSystem fs = getFileSystem();
		FSDataInputStream fis = fs.open(new Path(target));
		// OutputStream out = new FileOutputStream("D:\\abcd3.txt");
		// IOUtils.copyBytes(in, out, 4096, true);
		// fs.close();
		return fis;
	}

	@Override
	public boolean down(String target, String savePath) throws Exception {
		// return super.down(target, savePath);
		InputStream is = this.get(target);

		// 默认在hdfs 根路径下面
		if (!target.startsWith("/")) {
			target = "/" + target;
		}

		OutputStream os = new FileOutputStream(savePath);
		IOUtils.copyBytes(is, os, BUFF, true);
		return true;
	}

	/**
	 * 创建多级目录
	 */
	@Override
	public boolean mkdirs(String dirPath) throws Exception {
		if (StringUtils.isBlank(dirPath)) {
			return false;
		}
		FileSystem fs = getFileSystem();
		fs.mkdirs(new Path(dirPath));
		fs.close();
		return true;
	}

	/**
	 * 重名命
	 * 
	 * @see com.lyc.store.adpter.FileStoreAdpter#rename(java.lang.String)
	 */
	@Override
	public boolean rename(String filePath, String newFileName) throws Exception {

		if (StringUtils.isBlank(filePath)) {
			return false;
		}
		// 默认在hdfs 根路径下面
		if (!filePath.startsWith("/")) {
			filePath = "/" + filePath;
		}
		FileSystem fs = getFileSystem();
		Path srcPath = new Path(filePath);
		Path targetPath = new Path(srcPath.getParent(), newFileName);

		return fs.rename(srcPath, targetPath);
	}

	/**
	 * 列出指定目录下的文件
	 * 
	 * @see com.lyc.store.adpter.FileStoreAdpter#listFiles(java.lang.String, boolean)
	 */
	@Override
	public List<String> listFiles(String dirPath) throws Exception {
		if (StringUtils.isBlank(dirPath)) {
			return null;
		}
		List<String> paths = new ArrayList<String>(5);
		FileSystem fs = getFileSystem();
		FileStatus[] fileStatus = fs.listStatus(new Path(dirPath));
		if (ArrayUtils.isEmpty(fileStatus)) {
			return null;
		}
		for (FileStatus fStatus : fileStatus) {
			paths.add(fStatus.getPath().toUri().getPath());
		}
		// RemoteIterator<LocatedFileStatus> fileStatusIterator = fs.listFiles(new Path(dirPath), recursive);
		// while (fileStatusIterator.hasNext()) {
		// LocatedFileStatus fileStatus = fileStatusIterator.next();
		// paths.add(fileStatus.getPath().toUri().getPath());
		// }
		return paths;
	}

	@Override
	public void unzipOnServer(String zipFileName) throws Exception {
		FileSystem fs = getFileSystem();

		Path inputPath = new Path(zipFileName);
		CompressionCodecFactory factory = new CompressionCodecFactory(fs.getConf());
		CompressionCodec codec = factory.getCodec(inputPath);
		if (codec == null) {
			// System.out.println("no codec found for " + zipFileName);
			System.exit(1);
		}
		String outputUri = CompressionCodecFactory.removeSuffix(zipFileName, codec.getDefaultExtension());
		InputStream in = null;
		OutputStream out = null;
		try {
			in = codec.createInputStream(fs.open(inputPath));
			out = fs.create(new Path(outputUri));
			IOUtils.copyBytes(in, out, fs.getConf());
		} finally {
			IOUtils.closeStream(out);
			IOUtils.closeStream(in);
		}
	}
}

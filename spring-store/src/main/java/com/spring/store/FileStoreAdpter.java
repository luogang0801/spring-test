package com.spring.store;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.fileupload.FileUploadException;

public abstract class FileStoreAdpter {
	/**
	 * 字符串默认返回值
	 */
	protected static String default_result_str = "";

	/**
	 * boolean类型默认返回值
	 */
	protected static boolean default_result_bool = false;

	/**
	 * inputstream 默认返回值
	 */
	protected static InputStream default_result_is = null;

	/**
	 * 保存文件
	 * 
	 * @param des
	 *            源文件
	 * @param target
	 *            存放目标地址
	 * @return string 文件保存路径
	 * @date 2015年10月28日 上午10:30:36
	 * @author luogang
	 * @throws FileStoreException
	 */
	public String save(String des, String target) throws Exception {
		throw new FileUploadException("文件保存错误");
	}

	/**
	 * 保存文件
	 * 
	 * @param is
	 *            源文件流
	 * @param target
	 *            存放路径
	 * @return string 返回存放路径
	 * @date 2015年10月28日 下午2:40:24
	 * @author luogang
	 * @throws FileStoreException
	 */
	public String save(InputStream is, String target) throws Exception {
		throw new FileUploadException("文件保存错误");
	}

	/**
	 * 删除文件
	 * 
	 * @param target
	 *            文件存放位置
	 * @return
	 * @date 2015年10月28日 下午1:15:02
	 * @author luogang
	 * @throws FileUploadException
	 */
	public boolean delete(String target) throws Exception {
		// return default_result_bool;
		throw new FileUploadException("文件删除错误");
	}

	/**
	 * 获取文件
	 * 
	 * @param path
	 * @return
	 * @date 2015年10月28日 上午10:30:43
	 * @author luogang
	 * @throws FileUploadException
	 */
	public InputStream get(String target) throws Exception {
		throw new FileUploadException("获取文件错误");
	}

	/**
	 * 下载文件
	 * 
	 * @param target
	 *            源文件
	 * @param savePath
	 *            保存地址
	 * @return boolean 如果没有异常抛出始终返回true
	 * @date 2015年10月28日 下午1:25:21
	 * @author luogang
	 * @throws FileUploadException
	 */
	public boolean down(String target, String savePath) throws Exception {
		// return default_result_bool;
		throw new FileUploadException("下载文件错误");
	}

	/**
	 * 重命名文件
	 * 
	 * @param filePath
	 *            HDFS文件路径
	 * @param newFileName
	 *            文件新名
	 * @return boolean 如果没有异常抛出始终返回true
	 * @date 2015年11月2日 上午11:51:17
	 * @author libo
	 * @throws FileUploadException
	 */
	public boolean rename(String filePath, String newFileName) throws Exception {
		throw new FileUploadException("修改文件名称错误");
	}

	/**
	 * 新建目录,支持多级创建
	 * 
	 * @param dirPath
	 *            目录
	 * @return
	 * @date 2015年11月3日 下午4:17:10
	 * @author libo
	 * @throws FileUploadException
	 */
	public boolean mkdirs(String dirPath) throws Exception {
		throw new FileUploadException("创建目录错误");
	}

	/**
	 * 列出目录下所有的文件 返回文件所在路径集合
	 * 
	 * @param dirPath
	 *            目录
	 * @return {@link List<String>}
	 * @date 2016年1月22日 下午7:12:19
	 * @author libo
	 * @throws FileUploadException
	 */
	public List<String> listFiles(String dirPath) throws Exception {
		throw new FileUploadException("列出所有的文件错误");
	}

	public void unzipOnServer(String zipFileName) throws Exception {
	}
}

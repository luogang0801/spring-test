package com.spring.store;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.fileupload.FileUploadException;

public abstract class FileStoreAdpter {
	/**
	 * �ַ���Ĭ�Ϸ���ֵ
	 */
	protected static String default_result_str = "";

	/**
	 * boolean����Ĭ�Ϸ���ֵ
	 */
	protected static boolean default_result_bool = false;

	/**
	 * inputstream Ĭ�Ϸ���ֵ
	 */
	protected static InputStream default_result_is = null;

	/**
	 * �����ļ�
	 * 
	 * @param des
	 *            Դ�ļ�
	 * @param target
	 *            ���Ŀ���ַ
	 * @return string �ļ�����·��
	 * @date 2015��10��28�� ����10:30:36
	 * @author luogang
	 * @throws FileStoreException
	 */
	public String save(String des, String target) throws Exception {
		throw new FileUploadException("�ļ��������");
	}

	/**
	 * �����ļ�
	 * 
	 * @param is
	 *            Դ�ļ���
	 * @param target
	 *            ���·��
	 * @return string ���ش��·��
	 * @date 2015��10��28�� ����2:40:24
	 * @author luogang
	 * @throws FileStoreException
	 */
	public String save(InputStream is, String target) throws Exception {
		throw new FileUploadException("�ļ��������");
	}

	/**
	 * ɾ���ļ�
	 * 
	 * @param target
	 *            �ļ����λ��
	 * @return
	 * @date 2015��10��28�� ����1:15:02
	 * @author luogang
	 * @throws FileUploadException
	 */
	public boolean delete(String target) throws Exception {
		// return default_result_bool;
		throw new FileUploadException("�ļ�ɾ������");
	}

	/**
	 * ��ȡ�ļ�
	 * 
	 * @param path
	 * @return
	 * @date 2015��10��28�� ����10:30:43
	 * @author luogang
	 * @throws FileUploadException
	 */
	public InputStream get(String target) throws Exception {
		throw new FileUploadException("��ȡ�ļ�����");
	}

	/**
	 * �����ļ�
	 * 
	 * @param target
	 *            Դ�ļ�
	 * @param savePath
	 *            �����ַ
	 * @return boolean ���û���쳣�׳�ʼ�շ���true
	 * @date 2015��10��28�� ����1:25:21
	 * @author luogang
	 * @throws FileUploadException
	 */
	public boolean down(String target, String savePath) throws Exception {
		// return default_result_bool;
		throw new FileUploadException("�����ļ�����");
	}

	/**
	 * �������ļ�
	 * 
	 * @param filePath
	 *            HDFS�ļ�·��
	 * @param newFileName
	 *            �ļ�����
	 * @return boolean ���û���쳣�׳�ʼ�շ���true
	 * @date 2015��11��2�� ����11:51:17
	 * @author libo
	 * @throws FileUploadException
	 */
	public boolean rename(String filePath, String newFileName) throws Exception {
		throw new FileUploadException("�޸��ļ����ƴ���");
	}

	/**
	 * �½�Ŀ¼,֧�ֶ༶����
	 * 
	 * @param dirPath
	 *            Ŀ¼
	 * @return
	 * @date 2015��11��3�� ����4:17:10
	 * @author libo
	 * @throws FileUploadException
	 */
	public boolean mkdirs(String dirPath) throws Exception {
		throw new FileUploadException("����Ŀ¼����");
	}

	/**
	 * �г�Ŀ¼�����е��ļ� �����ļ�����·������
	 * 
	 * @param dirPath
	 *            Ŀ¼
	 * @return {@link List<String>}
	 * @date 2016��1��22�� ����7:12:19
	 * @author libo
	 * @throws FileUploadException
	 */
	public List<String> listFiles(String dirPath) throws Exception {
		throw new FileUploadException("�г����е��ļ�����");
	}

	public void unzipOnServer(String zipFileName) throws Exception {
	}
}

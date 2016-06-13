package com.luogang.io.client.sender;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.luogang.io.SocketWrapper;
import static com.luogang.io.Commons.*;
public class MessageSender implements Sendable {
	
	private String message;//��ͨ��message��Ϣ
	
	private byte []messageBytes;//��Ϣ����ʱʹ��
	
	private int length = 0;
	
	public MessageSender(String []tokens) throws UnsupportedEncodingException {
		if(tokens.length >= 2) {
			message = tokens[1];
			this.messageBytes = message.getBytes(DEFAULT_MESSAGE_CHARSET);
			this.length = messageBytes.length;
		}else {
			throw new RuntimeException("����sendMsg�����������ݡ�");
		}
	}

	/**
	 * �������ݴ���
	 * @throws IOException 
	 */
	@Override
	public void sendContent(SocketWrapper socketWrapper) throws IOException {
		println("�Ҵ�ʱ��������˷�����Ϣ��" + message);
		socketWrapper.write(length);
		socketWrapper.write(messageBytes);
		println("������Ϣ��ϡ�");
	}

	@Override
	public byte getSendType() {
		return SEND_MESSAGE;
	}

}
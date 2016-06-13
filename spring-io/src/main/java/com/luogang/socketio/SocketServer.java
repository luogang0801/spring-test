package com.luogang.socketio;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corundumstudio.socketio.AckCallback;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

public class SocketServer {
	private Logger LOG = LoggerFactory.getLogger(getClass());
	private SocketIOServer socketIOServer;
	private Configuration configuration;

	public void init() {

		socketIOServer = this.useDefaultConfig().buider();
		// ��ӷ���˽�����Ϣ����
		this.enventListener();

		// ���Ӽ�����
		this.addConnect();
		// �Ͽ����Ӽ�����
		this.addDisconnect();
		// ����
		this.start();
	}

	public static void main(String[] args) {
		new SocketServer().init();
	}

	/**
	 * ������Ϣ����
	 * <p>
	 * ת���������û�
	 * </p>
	 * 
	 * @param socketIOServer
	 * @date 2015��11��12�� ����4:33:56
	 * @author luogang
	 */
	private void enventListener() {

		socketIOServer.addEventListener("chatevent111", ChatObject.class, new DataListener<ChatObject>() {

			@Override
			public void onData(SocketIOClient client, ChatObject data, AckRequest ackSender) throws Exception {
				
				socketIOServer.getBroadcastOperations().sendEvent("chatevent", data);
			}
		});

	}

	private SocketServer useDefaultConfig() {
		if (configuration == null) {
			configuration = new Configuration();
		}

		// ����������Ϣ
		// ���ö˿���Ϣ
		configuration.setPort(9092);

		// ����Ĭ��IP
		configuration.setHostname("127.0.0.1");

		// ��������ʱ��
		configuration.setPingInterval(25000);

		// ���ó�ʱʱ��
		configuration.setPingTimeout(60000);

		return this;
	}

	private SocketIOServer buider() {
		return new SocketIOServer(configuration);
	}

	private void addDisconnect() {
		socketIOServer.addDisconnectListener(new DisconnectListener() {

			@Override
			public void onDisconnect(SocketIOClient client) {

				Object object = client.get("userId");
				LOG.info("duan:" + object);
				String object2 = client.get("userId");

				Collection<SocketIOClient> allClients = socketIOServer.getAllClients();
				for (SocketIOClient socketIOClient : allClients) {
					socketIOClient.sendEvent("disconnectTest", new AckCallback<String>(String.class, 10000) {
						@Override
						public void onSuccess(String result) {
							System.out.println(result);
						}
					}, object2 + "�û�������");
				}
			}
		});
	}

	private void addConnect() {
		socketIOServer.addConnectListener(new ConnectListener() {

			@Override
			public void onConnect(SocketIOClient client) {
				LOG.info("�½�����...");
				HandshakeData handshakeData = client.getHandshakeData();
				String val = handshakeData.getSingleUrlParam("userId");
				LOG.info(val);
				client.sendEvent("system", new AckCallback<String>(String.class) {
					@Override
					public void onSuccess(String result) {
						System.out.println("sdfsf:" + result);
					}
				}, "test");
				client.set("userId", val);
			}
		});
	}

	/**
	 * ����
	 */
	private void start() {
		if (socketIOServer != null) {
			Configuration config = socketIOServer.getConfiguration();
			LOG.info("netty server [{},{}] start...", config.getHostname(), config.getPort());
			socketIOServer.start();
		}
	}
}
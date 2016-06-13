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
		// 添加服务端接收消息监听
		this.enventListener();

		// 连接监听器
		this.addConnect();
		// 断开连接监听器
		this.addDisconnect();
		// 启动
		this.start();
	}

	public static void main(String[] args) {
		new SocketServer().init();
	}

	/**
	 * 接收消息监听
	 * <p>
	 * 转发给其他用户
	 * </p>
	 * 
	 * @param socketIOServer
	 * @date 2015年11月12日 下午4:33:56
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

		// 设置属性信息
		// 设置端口信息
		configuration.setPort(9092);

		// 设置默认IP
		configuration.setHostname("127.0.0.1");

		// 设置心跳时间
		configuration.setPingInterval(25000);

		// 设置超时时间
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
					}, object2 + "用户已下线");
				}
			}
		});
	}

	private void addConnect() {
		socketIOServer.addConnectListener(new ConnectListener() {

			@Override
			public void onConnect(SocketIOClient client) {
				LOG.info("新建连接...");
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
	 * 启动
	 */
	private void start() {
		if (socketIOServer != null) {
			Configuration config = socketIOServer.getConfiguration();
			LOG.info("netty server [{},{}] start...", config.getHostname(), config.getPort());
			socketIOServer.start();
		}
	}
}
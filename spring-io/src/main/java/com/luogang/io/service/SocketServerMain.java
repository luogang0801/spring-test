package com.luogang.io.service;

import static com.luogang.io.Commons.SERVER_SAVE_BASE_PATH;
import static com.luogang.io.Commons.logInfo;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import com.luogang.io.SocketWrapper;

public class SocketServerMain {
	
	private final static List<Worker> workers = new ArrayList<Worker>();

	public static void main(String []args) throws IOException {
		initPath();
		ServerSocket serverSocket = new ServerSocket(8889);
		logInfo("端口已经打开为8889，开始准备接受数据.....");
		try {
			int index = 1;
			while(true) {
				SocketWrapper socketWrapper = new SocketWrapper(serverSocket.accept());
				workers.add(new Worker(socketWrapper , "socket_thread_" + index++));
			}
		}finally {
			System.out.println("end=====");
			serverSocket.close();
			interruptWorkers();
		}
	}
	
	private static void interruptWorkers() {
		for(Worker worker : workers) {
			worker.interrupt();
		}
	}
	
	private static void initPath() {
		File file = new File(SERVER_SAVE_BASE_PATH);
		if(!file.exists()) {
			boolean success = file.mkdirs();
			if(!success) 
				throw new RuntimeException("无法创建目录：" + SERVER_SAVE_BASE_PATH);
		}
	}
}

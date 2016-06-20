package com.luogang.io.client;

import static com.luogang.io.Commons.print;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.luogang.io.SocketWrapper;
import com.luogang.io.client.exceptions.DirectNotExistsException;
import com.luogang.io.client.exceptions.ExitException;
import com.luogang.io.client.exceptions.NoOptionException;
import com.luogang.io.client.processor.LineProcessor;

public class SocketClientMain {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner scanner = new Scanner(System.in);
		SocketWrapper socketWrapper = new SocketWrapper("localhost", 8889);
		try {
			print("�Ѿ������Ϸ������ˣ����ڿ����������ݿ�ʼͨ����.....\n>");
			String line = scanner.nextLine();
			while (!"bye".equals(line)) {
				if (line != null) {
					try {
						LineProcessor processor = new LineProcessor(line);
						processor.sendContentBySocket(socketWrapper);
						socketWrapper.displayStatus();
					} catch (ExitException e) {
						break;// �˳�ϵͳ
					} catch (NoOptionException e) {
						/* û�����κβ��� */
					} catch (DirectNotExistsException e) {
						System.out.println(e.getMessage());
					} catch (RuntimeException e) {
						System.out.println(e.getMessage());
					} catch (FileNotFoundException e) {
						System.out.println(e.getMessage());
//						sendFile d:\Stdd.java charset=utf-8
					} catch (SocketException e) {
						socketWrapper.displayStatus();
						System.out.println("Socket�쳣�� " + e.getMessage() + "��������������Ͽ�����....");
						break;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("�����Ϸ������Ͽ����ӣ�");
						break;
					}
				}
				print(">");
				line = scanner.nextLine();
			}
		} finally {
			socketWrapper.close();
		}
	}
}

package com.luogang.io.client.sender;

import java.io.IOException;

import com.luogang.io.SocketWrapper;

public interface Sendable {
	
	public byte getSendType();

	public void sendContent(SocketWrapper socketWrapper) throws IOException;
}

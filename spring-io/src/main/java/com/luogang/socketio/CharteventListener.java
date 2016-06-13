package com.luogang.socketio;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

public class CharteventListener implements DataListener<ChatObject> {

    SocketIOServer server;

    public void setServer(SocketIOServer server) {
        this.server = server;
    }

    public void onData(SocketIOClient client, ChatObject data,
            AckRequest ackSender) throws Exception {
        // chateventΪ �¼������ƣ� dataΪ���͵�����
        this.server.getBroadcastOperations().sendEvent("chatevent", data);
    }
}
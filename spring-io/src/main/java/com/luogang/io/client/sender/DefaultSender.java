package com.luogang.io.client.sender;

import java.io.IOException;

import com.luogang.io.SocketWrapper;
import com.luogang.io.client.exceptions.ExitException;

import static com.luogang.io.Commons.*;

public class DefaultSender implements Sendable {

	public DefaultSender(String[] tokens) {
		String firstToken = tokens[0];
		if (HELP_STR.equalsIgnoreCase(firstToken)) {// ����
			println(HELP_SHOW);
		} else if (EXIT_STR.equalsIgnoreCase(firstToken)) {// �˳�
			// System.exit(0);�÷���ֱ�ӹرս��̣�Ҳ����ʹ�ã��Զ����ExitException�ⲿ����socket���մ���
			throw new ExitException();
		} else {
			throw new RuntimeException(ERROR_MESSAGE_FORMAT);
		}
	}

	@Override
	public byte getSendType() {
		return 0;
	}

	@Override
	public void sendContent(SocketWrapper socketWrapper) throws IOException {
		/* �������κ���Ϣ */
	}

}

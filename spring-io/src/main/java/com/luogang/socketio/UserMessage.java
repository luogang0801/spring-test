package com.luogang.socketio;

import java.io.Serializable;

public class UserMessage implements Serializable {
	private static final long serialVersionUID = -2816814971525087857L;

	/**
	 * 消息ID
	 */
	public String id;

	/**
	 * 发送用户ID
	 */
	public String sendUserId;

	public String sendUserName;

	/**
	 * 接收用户ID
	 */
	public String acceptUserId;

	public String acceptUserName;
	/**
	 * 发送信息内容
	 */
	public String context;
	/**
	 * 发送时间
	 */
	public Long sendTime;
	/**
	 * 信息接收时间
	 */
	public Long acceptTime;

	/**
	 * 消息code
	 */
	public String msgCode;

	/**
	 * 是否已读
	 */
	public boolean isRead;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}

	public String getSendUserName() {
		return sendUserName;
	}

	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}

	public String getAcceptUserId() {
		return acceptUserId;
	}

	public void setAcceptUserId(String acceptUserId) {
		this.acceptUserId = acceptUserId;
	}

	public String getAcceptUserName() {
		return acceptUserName;
	}

	public void setAcceptUserName(String acceptUserName) {
		this.acceptUserName = acceptUserName;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Long getSendTime() {
		return sendTime;
	}

	public void setSendTime(Long sendTime) {
		this.sendTime = sendTime;
	}

	public Long getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Long acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

}

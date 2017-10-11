package com.jyzq.entity;

import java.util.Date;

public class MessageEntity {
	private int messageId;
	private String sendOpenId;
	private String recOpenId;
	private String content;
	private Date createdTime;
	
	public MessageEntity() {}
	
	public MessageEntity(String sendOpenId, String recOpenId, String content) {
		this.sendOpenId = sendOpenId;
		this.recOpenId = recOpenId;
		this.content = content;
	}
	
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getSendOpenId() {
		return sendOpenId;
	}
	public void setSendOpenId(String sendOpenId) {
		this.sendOpenId = sendOpenId;
	}
	public String getRecOpenId() {
		return recOpenId;
	}
	public void setRecOpenId(String recOpenId) {
		this.recOpenId = recOpenId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
}

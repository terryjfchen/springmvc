package com.jyzq.service;

public interface MessageService {
	public void sendMessage(String openId, String destOpenId, String content) throws Exception;
}

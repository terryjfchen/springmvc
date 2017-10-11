package com.jyzq.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jyzq.entity.MessageEntity;
import com.jyzq.mapper.MessageMapper;
import com.jyzq.service.MessageService;

@Service("messageService")
@Transactional(readOnly=true)
public class MessageServiceImpl implements MessageService {
	@Resource
	private MessageMapper messageMapper;

	@Override
	@Transactional(rollbackFor=Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public void sendMessage(String openId, String destOpenId, String content) throws Exception {
		MessageEntity message = new MessageEntity(openId, destOpenId, content);
		messageMapper.insert(message);
	}
}

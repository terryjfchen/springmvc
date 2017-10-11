package com.jyzq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.jyzq.entity.MessageEntity;

public interface MessageMapper {

	@Select("SELECT * FROM message")
	@Results({
		@Result(property = "messageId",  column = "message_id"),
		@Result(property = "sendOpenId",  column = "send_open_id"),
		@Result(property = "recOpenId",  column = "rec_open_id"),
		@Result(property = "content",  column = "content"),
		@Result(property = "createdTime",  column = "created_time")
	})
	List<MessageEntity> getAll();

	@Insert("INSERT INTO message(send_open_id,rec_open_id,content,created_time) "
		+ "VALUES(#{sendOpenId}, #{recOpenId}, #{content}, now())")
	void insert(MessageEntity message);
}

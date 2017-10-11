package com.jyzq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jyzq.entity.UserEntity;

public interface UserMapper {
	@Select("SELECT * FROM user")
	@Results({
		@Result(property = "userId",  column = "user_id"),
		@Result(property = "openId",  column = "open_id"),
		@Result(property = "wsSessionId",  column = "ws_session_id"),
		@Result(property = "name",  column = "name")
	})
	List<UserEntity> getAll();
	

	@Select("SELECT * FROM user where user_id=#{userId}")
	@Results({
		@Result(property = "userId",  column = "user_id"),
		@Result(property = "openId",  column = "open_id"),
		@Result(property = "wsSessionId",  column = "ws_session_id"),
		@Result(property = "name",  column = "name")
	})
	UserEntity getByOpenId(String userId);
	
	@Update("UPDATE user SET open_id=#{openId},name=#{name},ws_session_id=#{wsSessionId} WHERE user_id =#{userId}")
	void update(UserEntity user);
}

package com.jyzq.service;

import com.jyzq.entity.UserEntity;

public interface UserService {
	public String getUser(UserEntity user) throws Exception ;
	
	public void updateUser(UserEntity user);
	
	public void syncSessionId(UserEntity user) throws Exception;
}

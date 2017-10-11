package com.jyzq.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JsonConfig;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jyzq.entity.UserEntity;
import com.jyzq.service.UserService;
import com.jyzq.mapper.UserMapper;

@Service("userService")
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userMapper;

	@Override
	@Transactional(rollbackFor=Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public String getUser(UserEntity user) throws Exception {
		// TODO Auto-generated method stub
		userMapper.update(user);
		
		List users = userMapper.getAll();
		JsonConfig jsonConfig = new JsonConfig();

		System.out.println(JSONArray.fromObject(users, jsonConfig).toString());
		
		if(user.getName().equals("187")) {
			throw new Exception("");
		}
		
		return "greeting";
	}

	@Override
	public void updateUser(UserEntity user) {
		// TODO Auto-generated method stub
		userMapper.update(user);;
	}

	@Override
	@Transactional(rollbackFor=Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public void syncSessionId(UserEntity user) throws Exception {
		if(user.getOpenId()==null || user.getOpenId().equals("")) {
			throw new Exception("User openId is null");
		}
		
		if(user.getWsSessionId()==null || user.getWsSessionId().equals("")) {
			throw new Exception("User session is null");
		}
		
		UserEntity userInDB = userMapper.getByOpenId(user.getOpenId());
		
		if(userInDB==null || userInDB.getUserId()==0 || userInDB.getOpenId()==null) {
			throw new Exception("Can not retrieve user from DB");
		}
		
		userInDB.setWsSessionId(user.getWsSessionId());
		userMapper.update(userInDB);
	}
}

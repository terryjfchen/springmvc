package com.jyzq.entity;

public class UserEntity {
	private int userId;
	private String openId;
	private String name;
	private String wsSessionId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWsSessionId() {
		return wsSessionId;
	}
	public void setWsSessionId(String wsSessionId) {
		this.wsSessionId = wsSessionId;
	}
}

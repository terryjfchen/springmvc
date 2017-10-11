package com.jyzq.bean;

public class IncomeMessage {
	private String openId;//OpenId
	private String destOpenId;
	private String content;

    public IncomeMessage() {
    }

    public IncomeMessage(String openId, String destOpenId, String content) {
        this.openId = openId;
        this.destOpenId = destOpenId;
        this.content = content;
    }

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDestOpenId() {
		return destOpenId;
	}

	public void setDestOpenId(String destOpenId) {
		this.destOpenId = destOpenId;
	}
}

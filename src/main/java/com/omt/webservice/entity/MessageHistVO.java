package com.omt.webservice.entity;

import java.util.List;

public class MessageHistVO {

	private String email;

	private List<HistoryPureVO> messageList;


	public List<HistoryPureVO> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<HistoryPureVO> messageList) {
		this.messageList = messageList;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}

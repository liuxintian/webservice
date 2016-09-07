package com.omt.webservice.voting.entity;

public class WebVotes {
	
	private String userName;
	private String holdingCode;
	private String meetingName;
	
	private String votingType; // 0:discard,1:agree,2:disagree
	private String votingTime;
	private String votingInfo;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHoldingCode() {
		return holdingCode;
	}
	public void setHoldingCode(String holdingCode) {
		this.holdingCode = holdingCode;
	}
	public String getMeetingName() {
		return meetingName;
	}
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	public String getVotingType() {
		return votingType;
	}
	public void setVotingType(String votingType) {
		this.votingType = votingType;
	}
	public String getVotingTime() {
		return votingTime;
	}
	public void setVotingTime(String votingTime) {
		this.votingTime = votingTime;
	}
	public String getVotingInfo() {
		return votingInfo;
	}
	public void setVotingInfo(String votingInfo) {
		this.votingInfo = votingInfo;
	}
	
}

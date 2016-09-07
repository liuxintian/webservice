package com.omt.webservice.voting.entity;

public class Votes {
	
	private String userId;
	private String holdingId;
	private String meetingId;
	
	private String votingType; // 0:discard,1:agree,2:disagree
	private String votingTime;
	private String votingInfo;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHoldingId() {
		return holdingId;
	}
	public void setHoldingId(String holdingId) {
		this.holdingId = holdingId;
	}
	public String getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
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

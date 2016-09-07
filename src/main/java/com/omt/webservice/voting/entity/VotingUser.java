package com.omt.webservice.voting.entity;

public class VotingUser {
	private String id;
	private String userName;
	private String password;
	private String emailAddr;

	private String postcode;
	private String holderType; // X-Chess; I-Issuer
	private String hinSrn;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailAddr() {
		return emailAddr;
	}
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getHolderType() {
		return holderType;
	}
	public void setHolderType(String holderType) {
		this.holderType = holderType;
	}
	public String getHinSrn() {
		return hinSrn;
	}
	public void setHinSrn(String hinSrn) {
		this.hinSrn = hinSrn;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}

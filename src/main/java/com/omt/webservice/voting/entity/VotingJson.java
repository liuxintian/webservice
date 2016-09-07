package com.omt.webservice.voting.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"userName", "emailAddr" , "holderType", "hinSrn", "postcode", "holdings", "votes"})
public class VotingJson {

	private String userName;
	private String password;
	private String emailAddr;
	private String phone;

	private List<Votes> votes;
	private List<Holdings> holdings;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<Votes> getVotes() {
		return votes;
	}
	public void setVotes(List<Votes> votes) {
		this.votes = votes;
	}
	public List<Holdings> getHoldings() {
		return holdings;
	}
	public void setHoldings(List<Holdings> holdings) {
		this.holdings = holdings;
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

}

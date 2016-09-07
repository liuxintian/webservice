package com.omt.webservice.voting.entity;

public class VotingResult {
	private boolean result;
	private String reason;
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}

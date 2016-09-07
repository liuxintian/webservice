package com.omt.websocket.entity;

public class CmpChartHistory {
	private String code;
	private String date;
	private String value;
	private ShareData sharedata;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ShareData getSharedata() {
		return sharedata;
	}
	public void setSharedata(ShareData sharedata) {
		this.sharedata = sharedata;
	}
}

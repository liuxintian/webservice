package com.omt.websocket.entity;

public class CompanyList {
	private String code;
	private String market;

	private String uniqstr;
	
	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUniqstr() {
		uniqstr = code+"|"+market;
		return uniqstr;
	}
	
}

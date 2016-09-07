package com.omt.asxdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AsxDataVO {
	private String asxcode;
	private String name;
	private String lastprice;
	private String changepercent;
	private String changevalues;
	private int type; // 1:up, 2:down
	
	public String getAsxcode() {
		return asxcode;
	}
	public void setAsxcode(String asxcode) {
		this.asxcode = asxcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastprice() {
		return lastprice;
	}
	public void setLastprice(String lastprice) {
		this.lastprice = lastprice;
	}
	public String getChangepercent() {
		return changepercent;
	}
	public void setChangepercent(String changepercent) {
		this.changepercent = changepercent;
	}
	public String getChangevalues() {
		return changevalues;
	}
	public void setChangevalues(String changevalues) {
		this.changevalues = changevalues;
	}
	
	@JsonIgnore
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}

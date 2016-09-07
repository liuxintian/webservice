package com.omt.statistics.entity;

import java.util.List;

public class StPageShowVO {
	private String id;
	private String date;
	private String url;
	private String type;
	private String code;
	private String count;
	private List<StatisticsVO> stdetails;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<StatisticsVO> getStdetails() {
		return stdetails;
	}
	public void setStdetails(List<StatisticsVO> stdetails) {
		this.stdetails = stdetails;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

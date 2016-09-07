package com.omt.websocket.entity.light;

import java.util.List;

import com.omt.webservice.morningstar.entity.ChartData;

public class LightCHResponse {
	private String code;
	private String exchange;
	private List<ChartData> value;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<ChartData> getValue() {
		return value;
	}
	public void setValue(List<ChartData> value) {
		this.value = value;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
}

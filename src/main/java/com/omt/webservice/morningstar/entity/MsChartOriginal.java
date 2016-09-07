package com.omt.webservice.morningstar.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "symbol", "exchangeid", "type", "data"})
public class MsChartOriginal {
	
	@JsonProperty("symbol")
	private String symbol;
	@JsonProperty("exchangeid")
	private String exchangeid;
	@JsonProperty("type")
	private String type;
	@JsonProperty("data")
	private List<ChartData> data;

	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getExchangeid() {
		return exchangeid;
	}
	public void setExchangeid(String exchangeid) {
		this.exchangeid = exchangeid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<ChartData> getData() {
		return data;
	}
	public void setData(List<ChartData> data) {
		this.data = data;
	}
}

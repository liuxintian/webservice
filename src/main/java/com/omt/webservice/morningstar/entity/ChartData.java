package com.omt.webservice.morningstar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.omt.webservice.UtilLibs;

@JsonPropertyOrder({ "Low", "Open", "Close", "Date", "Volume", "High"})
public class ChartData {
	
	private String date;
	private String time;
	private Double open;
	private Double low;
	private Double high;
	private Double close;  //Morning star D2 last == Paritech close
	private Integer volume;
	
	@JsonProperty("Date")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@JsonProperty("D953")
	public void setD953(String date) {
		this.date = UtilLibs.convertDateFormat(date);
	}

	@JsonIgnore
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	@JsonProperty("D952")
	public void setD952(String time) {
		this.time = time;
	}

	@JsonProperty("Open")
	public Double getOpen() {
		return open;
	}
	public void setOpen(Double open) {
		this.open = open;
	}
	@JsonProperty("D17")
	public void setD17(Double open) {
		this.open = open;
	}
	
	@JsonProperty("Low")
	public Double getLow() {
		return low;
	}
	public void setLow(Double low) {
		this.low = low;
	}
	@JsonProperty("D19")
	public void setD19(Double low) {
		this.low = low;
	}
	
	@JsonProperty("High")
	public Double getHigh() {
		return high;
	}
	public void setHigh(Double high) {
		this.high = high;
	}
	@JsonProperty("D18")
	public void setD18(Double high) {
		this.high = high;
	}
	
	@JsonProperty("Close")
	public Double getClose() {
		return close;
	}
	public void setClose(Double close) {
		this.close = close;
	}
	@JsonProperty("D2")
	public void setD2(Double close) {
		this.close = close;
	}
	
	@JsonProperty("Volume")
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	@JsonProperty("D16")
	public void setD16(Integer volume) {
		this.volume = volume;
	}
}

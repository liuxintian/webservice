package com.omt.websocket.entity.light;

/**
 * Entity for last close Response to Adam Back-end
 * @author tonyliu
 *
 */
public class LightLastClose {
	private String code;
	private String exchange;
	private Double close;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public Double getClose() {
		return close;
	}
	public void setClose(Double close) {
		this.close = close;
	}
}

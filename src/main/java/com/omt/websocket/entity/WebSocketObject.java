package com.omt.websocket.entity;

import java.util.Date;

import com.neovisionaries.ws.client.WebSocket;

public class WebSocketObject {
	private WebSocket ws;
	private Date dt;
	
	public WebSocket getWs() {
		return ws;
	}
	public void setWs(WebSocket ws) {
		this.ws = ws;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
}

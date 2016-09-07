package com.omt.websocket.entity.light;

import java.util.List;

public class LightCHRequest {
	private String type;
	private List<LightCHCode> codelist;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<LightCHCode> getCodelist() {
		return codelist;
	}
	public void setCodelist(List<LightCHCode> codelist) {
		this.codelist = codelist;
	}
}

package com.omt.webservice.omniloop.entity;

import java.util.List;

public class CodeMenu {
	private String code;
	private List<Menu> menu;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<Menu> getMenu() {
		return menu;
	}
	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}
}

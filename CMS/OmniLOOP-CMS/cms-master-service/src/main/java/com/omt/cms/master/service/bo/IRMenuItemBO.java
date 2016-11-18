package com.omt.cms.master.service.bo;

import org.springframework.stereotype.Component;

import com.omt.cms.core.service.bo.base.CommonBO;

@Component
public class IRMenuItemBO extends CommonBO {

	private static final long serialVersionUID = 1L;

	private String menuItemName;
	private String menuItemDesc;

	public IRMenuItemBO(){
		super();
	}

	public String getMenuItemName() {
		return menuItemName;
	}

	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
	}

	public String getMenuItemDesc() {
		return menuItemDesc;
	}

	public void setMenuItemDesc(String menuItemDesc) {
		this.menuItemDesc = menuItemDesc;
	}
}

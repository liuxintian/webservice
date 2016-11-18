package com.omt.cms.master.service.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.core.service.bo.base.CommonBO;

@Component
public class IRMenuBO extends CommonBO {

	private static final long serialVersionUID = 1L;

	private Long companyId;
	private List<Long> activeMenuIds;
	private List<IRMenuItemBO> menuItems;
	
	public IRMenuBO(){
		super();
		this.activeMenuIds = new ArrayList<>();
		this.menuItems = new ArrayList<>();
	}

	public IRMenuBO(Long companyId){
		this();
		this.companyId = companyId;
	}
	
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public List<Long> getMenuIds() {
		return activeMenuIds;
	}

	public void setMenuIds(List<Long> menuIds) {
		this.activeMenuIds = menuIds;
	}

	public List<IRMenuItemBO> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<IRMenuItemBO> menuItems) {
		this.menuItems = menuItems;
	}
	
	public void addMenuItem(Long menuItemId){
		if(!this.getMenuIds().contains(menuItemId)){
			this.getMenuIds().add(menuItemId);	
		}
	}

	public void addMenuItems(List<Long> menuItemIds){
		for(Long mid : menuItemIds){
			addMenuItem(mid);
		}
	}

}
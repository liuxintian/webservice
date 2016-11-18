package com.omt.cms.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.omt.cms.entity.BaseEntity;

@Entity
@Table(name = "ir_menu_items")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "ir_menu_items_seq")
public class IRMenuItem extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name="menu_item_name", length=255)
	@Size(max=255)
	private String menuItemName;
	
	@Column(name="menu_item_desc", length=500)
	@Size(max=500)
	private String menuItemDesc;

	public IRMenuItem(){
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

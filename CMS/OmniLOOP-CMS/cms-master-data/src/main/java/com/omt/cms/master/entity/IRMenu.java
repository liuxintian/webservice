package com.omt.cms.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.omt.cms.entity.BaseEntity;

@Entity
@Table(name = "ir_menus")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "ir_menus_seq")
public class IRMenu extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name="company_id")
	private Long companyId;

	@Column(name="menu_id")
	private Long menuId;

	public IRMenu(){
		super();
	}
	
	public IRMenu(Long companyId, Long menuId){
		this();
		this.companyId = companyId;
		this.menuId = menuId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
}

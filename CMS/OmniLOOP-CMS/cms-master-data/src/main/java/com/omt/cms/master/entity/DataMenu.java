package com.omt.cms.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.omt.cms.entity.BaseEntity;

@Entity
@Table(name = "data_menus")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "data_menus_seq")
public class DataMenu extends BaseEntity {

	private static final long serialVersionUID = 1L;


	@Column(name="data_menu", length=255)
	private String dataMenu;

	@Column(name="url", length=255)
	private String url;

	@Column(name="description", length=500)
	private String desc;

	public DataMenu(){
		super();
	}

	public String getDataMenu() {
		return dataMenu;
	}

	public void setDataMenu(String dataMenu) {
		this.dataMenu = dataMenu;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}


}

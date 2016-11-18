package com.omt.cms.master.service.bo;

import com.omt.cms.core.service.bo.base.CommonBO;

public class DataMenuBO extends CommonBO {

	private static final long serialVersionUID = 1L;


	private String dataMenu;
	private String url;
	private String desc;

	public DataMenuBO(){
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

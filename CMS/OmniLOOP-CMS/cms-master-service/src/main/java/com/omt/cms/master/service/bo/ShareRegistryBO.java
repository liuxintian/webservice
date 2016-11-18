package com.omt.cms.master.service.bo;

import com.omt.cms.core.service.bo.base.CommonBO;

public class ShareRegistryBO extends CommonBO {
	private static final long serialVersionUID = 1L;

	private String shareRegistry;
	private String srCountry;
	private String srAddress;
	private String srURL;
	private String srPhone;
	private String srEmail;
	private String srType;

	public ShareRegistryBO() {
		super();
	}

	public String getShareRegistry() {
		return shareRegistry;
	}

	public void setShareRegistry(String shareRegistry) {
		this.shareRegistry = shareRegistry;
	}

	public String getSrCountry() {
		return srCountry;
	}

	public void setSrCountry(String srCountry) {
		this.srCountry = srCountry;
	}

	public String getSrAddress() {
		return srAddress;
	}

	public void setSrAddress(String srAddress) {
		this.srAddress = srAddress;
	}

	public String getSrURL() {
		return srURL;
	}

	public void setSrURL(String srURL) {
		this.srURL = srURL;
	}

	public String getSrPhone() {
		return srPhone;
	}

	public void setSrPhone(String srPhone) {
		this.srPhone = srPhone;
	}

	public String getSrEmail() {
		return srEmail;
	}

	public void setSrEmail(String srEmail) {
		this.srEmail = srEmail;
	}

	public String getSrType() {
		return srType;
	}

	public void setSrType(String srType) {
		this.srType = srType;
	}

}

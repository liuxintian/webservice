package com.omt.cms.core.service.bo.base;

import org.apache.commons.lang3.StringUtils;

public class LoginUserBO extends BaseBusinessObject {
	
	private static final long serialVersionUID = 1L;
	public static final String MASK_PASSWORD = "****************";
	protected Long userId;
	protected String loginName;
	protected String password;
	protected String fpToken;
	protected String mailVerToken;
	protected Integer status;
	protected String contactNumber;
	
	public LoginUserBO(){ }

	public LoginUserBO(String loginName, String password){
		String loginNameLower = StringUtils.lowerCase(loginName);
		this.loginName = loginNameLower;
		this.password = password;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		String loginNameLower = StringUtils.lowerCase(loginName);
		this.loginName = loginNameLower;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFpToken() {
		return fpToken;
	}

	public void setFpToken(String fpToken) {
		this.fpToken = fpToken;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMailVerToken() {
		return mailVerToken;
	}

	public void setMailVerToken(String regVerToken) {
		this.mailVerToken = regVerToken;
	}
}
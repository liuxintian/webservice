package com.omt.cms.user.service.bo;

import java.sql.Timestamp;

import com.omt.cms.core.service.bo.base.BaseBusinessObject;

 public class UserSettingBO extends BaseBusinessObject  {

	private static final long serialVersionUID = 1L;
	private Long id;
 	private Long userId;
 	private Timestamp createdOn;
 	private Timestamp lastUpdated;
 	private String settingName;
 	private String settingValue;

	public UserSettingBO(){ }
 
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getSettingName() {
		return settingName;
	}

	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}

	public String getSettingValue() {
		return settingValue;
	}

	public void setSettingValue(String settingValue) {
		this.settingValue = settingValue;
	}

	public Long getId() {
		return id;
	}
	
}

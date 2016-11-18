package com.omt.cms.user.service.bo;

import java.sql.Timestamp;

import com.omt.cms.core.service.bo.base.BaseBusinessObject;

public class UserDeviceBO extends BaseBusinessObject  {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Timestamp createdOn;
	private Timestamp lastUpdated;
	private String deviceId;
	private String deviceOS;
	private String deviceVer;
	private String deviceSize;

	public UserDeviceBO(){
		super();
	}
    
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

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Long getId() {
		return id;
	}

	public String getDeviceOS() {
		return deviceOS;
	}

	public void setDeviceOS(String deviceOS) {
		this.deviceOS = deviceOS;
	}

	public String getDeviceVer() {
		return deviceVer;
	}

	public void setDeviceVer(String deviceVer) {
		this.deviceVer = deviceVer;
	}

	public String getDeviceSize() {
		return deviceSize;
	}

	public void setDeviceSize(String deviceSize) {
		this.deviceSize = deviceSize;
	}

}

package com.omt.cms.user.service.bo;

import java.io.Serializable;
import java.sql.Timestamp;

 public class UserRegisteredBO implements Serializable{

	private static final long serialVersionUID = 1L;
 
	private Long id;
 	private Long userId;
 	private Timestamp createdOn;
 	private Timestamp lastUpdated;
 	private String appRegistered;
 	private Boolean isPrimary;
	
	public UserRegisteredBO(){
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getAppRegistered() {
		return appRegistered;
	}

	public void setAppRegistered(String appRegistered) {
		this.appRegistered = appRegistered;
	}

	public Boolean getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long urId) {
		// TODO Auto-generated method stub
		this.id=urId;
	}


}

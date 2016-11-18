package com.omt.cms.user.service.bo;

import java.io.Serializable;
import java.sql.Timestamp;

 public class UserRegistrationBO implements Serializable{

	private static final long serialVersionUID = 1L;
 
	private Long id;
 	private Long userId;
 	private Timestamp createdOn;
 	private Timestamp lastUpdated;
	private String shareRegistry;
	private String tokenId;
	
	public UserRegistrationBO(){
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

	public Long getId() {
		return id;
	}

	public void setId(Long urId) {
		this.id=urId;
	}

	public String getShareRegistry() {
		return shareRegistry;
	}

	public void setShareRegistry(String shareRegistry) {
		this.shareRegistry = shareRegistry;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

}

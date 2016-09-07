package com.omt.exchanger.entity.user;

public class User {
	
	private DateTimeDT userInvitedDT;
	private String notes ;//": "string",
	private int userStatus;//": 0,
	private String pwdHash;//": "string",
	private String companyName;//": "string",
	private boolean isActive;//": true,
	private String title;//": "string","
	
	private DateTimeDT createdOn;
	private DateTimeDT lastUpdated;
	private int total;// 0,
	private boolean emailValid;// true,
	private String password;// "string",
	private String loginName;// "string",
	private String userEmail;// "string",
	private int id;// 0,
	private String tagRole;// "string",

	private Address address;
	private boolean shareSubscriber;// true,
	private boolean userInvited;// true,
	private String userName;// "string",
	private String userContact;// "string",
	private boolean phoneValid;// true,
	private int companyId;// 0,
	
	private DateTimeDT validUntil;
	private DateTimeDT userActivated;
	private int status;// 0,
	
	public DateTimeDT getUserInvitedDT() {
		return userInvitedDT;
	}
	public void setUserInvitedDT(DateTimeDT userInvitedDT) {
		this.userInvitedDT = userInvitedDT;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}
	public String getPwdHash() {
		return pwdHash;
	}
	public void setPwdHash(String pwdHash) {
		this.pwdHash = pwdHash;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public DateTimeDT getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(DateTimeDT createdOn) {
		this.createdOn = createdOn;
	}
	public DateTimeDT getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(DateTimeDT lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public boolean isEmailValid() {
		return emailValid;
	}
	public void setEmailValid(boolean emailValid) {
		this.emailValid = emailValid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTagRole() {
		return tagRole;
	}
	public void setTagRole(String tagRole) {
		this.tagRole = tagRole;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public boolean isShareSubscriber() {
		return shareSubscriber;
	}
	public void setShareSubscriber(boolean shareSubscriber) {
		this.shareSubscriber = shareSubscriber;
	}
	public boolean isUserInvited() {
		return userInvited;
	}
	public void setUserInvited(boolean userInvited) {
		this.userInvited = userInvited;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserContact() {
		return userContact;
	}
	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}
	public boolean isPhoneValid() {
		return phoneValid;
	}
	public void setPhoneValid(boolean phoneValid) {
		this.phoneValid = phoneValid;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public DateTimeDT getValidUntil() {
		return validUntil;
	}
	public void setValidUntil(DateTimeDT validUntil) {
		this.validUntil = validUntil;
	}
	public DateTimeDT getUserActivated() {
		return userActivated;
	}
	public void setUserActivated(DateTimeDT userActivated) {
		this.userActivated = userActivated;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
/*
	{
		  "userInvitedDT": {
		    "nanos": 0,
		    "time": 0,
		    "date": 0,
		    "year": 0,
		    "month": 0,
		    "hours": 0,
		    "minutes": 0,
		    "seconds": 0,
		    "day": 0,
		    "timezoneOffset": 0
		  },
		  "notes": "string",
		  "userStatus": 0,
		  "pwdHash": "string",
		  "companyName": "string",
		  "isActive": true,
		  "title": "string",
		  "createdOn": {
		    "nanos": 0,
		    "time": 0,
		    "date": 0,
		    "year": 0,
		    "month": 0,
		    "hours": 0,
		    "minutes": 0,
		    "seconds": 0,
		    "day": 0,
		    "timezoneOffset": 0
		  },
		  "lastUpdated": {
		    "nanos": 0,
		    "time": 0,
		    "date": 0,
		    "year": 0,
		    "month": 0,
		    "hours": 0,
		    "minutes": 0,
		    "seconds": 0,
		    "day": 0,
		    "timezoneOffset": 0
		  },
		  "total": 0,
		  "emailValid": true,
		  "password": "string",
		  "loginName": "string",
		  "userEmail": "string",
		  "id": 0,
		  "tagRole": "string",
		  "address": {
		    "addressCountry": "string",
		    "addressZip": "string",
		    "addressLine1": "string",
		    "addressLine2": "string",
		    "addressState": "string",
		    "addressLine3": "string",
		    "addressCity": "string"
		  },
		  "shareSubscriber": true,
		  "userInvited": true,
		  "userName": "string",
		  "userContact": "string",
		  "phoneValid": true,
		  "companyId": 0,
		  "validUntil": {
		    "nanos": 0,
		    "time": 0,
		    "date": 0,
		    "year": 0,
		    "month": 0,
		    "hours": 0,
		    "minutes": 0,
		    "seconds": 0,
		    "day": 0,
		    "timezoneOffset": 0
		  },
		  "userActivated": {
		    "nanos": 0,
		    "time": 0,
		    "date": 0,
		    "year": 0,
		    "month": 0,
		    "hours": 0,
		    "minutes": 0,
		    "seconds": 0,
		    "day": 0,
		    "timezoneOffset": 0
		  },
		  "status": 0
		}
		*/
}

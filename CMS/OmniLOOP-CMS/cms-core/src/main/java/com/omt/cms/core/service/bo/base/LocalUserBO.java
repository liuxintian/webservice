package com.omt.cms.core.service.bo.base;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.omt.cms.core.common.LongToDateJsonDeserializer;

public class LocalUserBO extends CommonBO {

	private static final long serialVersionUID = 1L;

	private Long companyId;
	private String companyName;
	private String loginName;
	private String userName;
	private AddressBO address;
	private String userContact;
	private Integer userStatus;
	private String pwdHash;
	private Boolean isActive;
	@JsonDeserialize(using=LongToDateJsonDeserializer.class)	
	private Timestamp userInvitedDT;
	@JsonDeserialize(using=LongToDateJsonDeserializer.class)
	private Timestamp userActivated;
	private Boolean userInvited = true;
	private Boolean shareSubscriber;
	private String userEmail;
	private Boolean emailValid;
	private Boolean phoneValid;
	private String password;
	private String title;
	private List<DeviceInfo> devices;
	
	public LocalUserBO(){ }
	
	public Long getCompanyId() {
		return companyId;
	}
	
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public Timestamp getUserInvitedDT() {
		return userInvitedDT;
	}
	
	public void setUserInvitedDT(Timestamp userInvited) {
		this.userInvitedDT = userInvited;
	}
	
	public Timestamp getUserActivated() {
		return userActivated;
	}
	
	public void setUserActivated(Timestamp userActivated) {
		this.userActivated = userActivated;
	}
	
	public String getLoginName() {
		return loginName;
	}
	
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public AddressBO getAddress() {
		return address;
	}
	
	public void setAddress(AddressBO address) {
		this.address = address;
	}
	
	public String getUserContact() {
		return userContact;
	}
	
	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}
	
	public Integer getUserStatus() {
		return userStatus;
	}
	
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	
	public String getPwdHash() {
		return pwdHash;
	}
	
	public void setPwdHash(String pwdHash) {
		this.pwdHash = pwdHash;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getUserInvited() {
		return userInvited;
	}

	public void setUserInvited(Boolean userInvited) {
		this.userInvited = userInvited;
	}
	
	public Boolean getShareSubscriber() {
		return shareSubscriber;
	}

	public void setShareSubscriber(Boolean shareSubscriber) {
		this.shareSubscriber = shareSubscriber;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Boolean getEmailValid() {
		return emailValid;
	}

	public void setEmailValid(Boolean emailValid) {
		this.emailValid = emailValid;
	}

	public Boolean getPhoneValid() {
		return phoneValid;
	}

	public void setPhoneValid(Boolean phoneValid) {
		this.phoneValid = phoneValid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<DeviceInfo> getDevices() {
		return devices;
	}

	public void setDevices(List<DeviceInfo> devices) {
		this.devices = devices;
	}

	public static class DeviceInfo{
		
		private String deviceId;
		private String deviceOS;
		private String deviceVer;
		private String deviceSize;
		
		public DeviceInfo(){ }

		public String getDeviceId() {
			return deviceId;
		}

		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
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

	public String toCSVRow() {
		StringBuilder builder = new StringBuilder();
		builder.append(loginName);
		builder.append(",");
		builder.append(userName);
		builder.append(",");
		builder.append(createdOn);
		builder.append(",");
		builder.append(address);
		builder.append(",");
		builder.append(userContact);
		builder.append(",");
		builder.append(userStatus);
		builder.append(",");
		builder.append(",");
		builder.append(userInvited);
		builder.append(",");
		builder.append(userEmail);
		builder.append(",");
		builder.append(emailValid);
		builder.append(",");
		builder.append(phoneValid);
		builder.append(",");
		builder.append(title);
		builder.append(",");
		builder.append(userActivated);
		return builder.toString();
	}

}

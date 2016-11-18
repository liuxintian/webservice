package com.omt.cms.user.service.bo;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.omt.cms.core.service.bo.base.AddressBO;
import com.omt.cms.core.service.bo.base.CommonBO;

public class UserBO extends CommonBO {

	private static final long serialVersionUID = 1L;

	private String loginName;
	private String userName;
	private AddressBO address;
	private String userContact;
	private Integer userStatus;
	private String userIdentifier;
	private String userUId;
	private int incorrectTries;
	private Integer accountState;
	private String mailVerToken;
	private String phoneVerToken;
	private Long curCpId;
	private Boolean userInvited;
	private String userEmail;
	private Boolean emailValid;
	private Boolean phoneValid;
	private String password;
	protected String fpToken;
	private String title;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date dateOfBirth;
	private Boolean useEmail;
	private Boolean usePhone;
	private Timestamp createdOn;

	public UserBO(){ }

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

	public String getUserIdentifier() {
		return userIdentifier;
	}

	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	public int getIncorrectTries() {
		return incorrectTries;
	}

	public void setIncorrectTries(int incorrectTries) {
		this.incorrectTries = incorrectTries;
	}

	public Integer getAccountState() {
		return accountState;
	}

	public void setAccountState(Integer accountState) {
		this.accountState = accountState;
	}

	public String getMailVerToken() {
		return mailVerToken;
	}

	public void setMailVerToken(String regVerToken) {
		this.mailVerToken = regVerToken;
	}

	public String getPhoneVerToken() {
		return phoneVerToken;
	}

	public void setPhoneVerToken(String phoneVerToken) {
		this.phoneVerToken = phoneVerToken;
	}

	public Long getCurCpId() {
		return curCpId;
	}

	public void setCurCpId(Long curCpId) {
		this.curCpId = curCpId;
	}

	public Boolean getUserInvited() {
		return userInvited;
	}

	public void setUserInvited(Boolean userInvited) {
		this.userInvited = userInvited;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
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

	public String getFpToken() {
		return fpToken;
	}

	public void setFpToken(String fpToken) {
		this.fpToken = fpToken;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Boolean getUseEmail() {
		return useEmail;
	}

	public void setUseEmail(Boolean useEmail) {
		this.useEmail = useEmail;
	}

	public Boolean getUsePhone() {
		return usePhone;
	}

	public void setUsePhone(Boolean usePhone) {
		this.usePhone = usePhone;
	}

	public boolean isUseEmail(){
		return this.useEmail!=null && this.getUseEmail();
	}

	public boolean isUsePhone(){
		return this.usePhone!=null && this.getUsePhone();
	}
	
	

	public String getUserUId() {
		return userUId;
	}

	public void setUserUId(String userUId) {
		this.userUId = userUId;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
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
		builder.append(userIdentifier);
		builder.append(",");
		builder.append(incorrectTries);
		builder.append(",");
		builder.append(accountState);
		builder.append(",");
		builder.append(curCpId);
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
		builder.append(dateOfBirth);
		builder.append(",");
		builder.append(useEmail);
		builder.append(",");
		builder.append(usePhone);
		return builder.toString();
	}

}

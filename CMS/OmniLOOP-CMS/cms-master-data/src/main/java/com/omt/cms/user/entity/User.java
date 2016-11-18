package com.omt.cms.user.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.omt.cms.core.common.SystemCodes.UserStatus;
import com.omt.cms.entity.BaseUser;

@Entity
@Table(name = "users")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "users_seq")
public class User extends BaseUser{

	private static final long serialVersionUID = 1L;

	@Column(name="user_email",length=300)
	@Size(max=300)
	private String userEmail;

	@Column(name="user_identifier", length=255)
	@Size(max=255)
	private String userIdentifier;
	
	@Column(name="user_uid", nullable=true, length=255)
	@Size(max=255)
	private String userUId;
	
	
	@Column(name="incorrect_tries")
	private int incorrectTries;
	
	@Column(name="account_state")
	protected Integer accountState;

	@Column(name="user_invited")
	private Boolean userInvited;

	@Column(name="email_valid")
	private Boolean emailValid;

	@Column(name="phone_valid")
	private Boolean phoneValid;

	@Column(name="title",length=300, nullable=true)
	@Size(max=300)
	private String title;

	@Column(name="date_of_birth", nullable=true)
	private Date dateOfBirth;

	public User(){
		super();
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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

	public Boolean getUserInvited() {
		return userInvited;
	}

	public void setUserInvited(Boolean userInvited) {
		this.userInvited = userInvited;
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

	public boolean isVerificationPending(){
		return this.userStatus == UserStatus.VERIFICATION_PENDING.getValue();
	}
	
	public boolean isActive(){
		if(this.userStatus!=null){
			return this.userStatus.intValue() == UserStatus.ACTIVE.getValue();
		}
		return false; 
	}

	public String getUserUId() {
		return userUId;
	}

	public void setUserUId(String userUId) {
		this.userUId = userUId;
	}

}

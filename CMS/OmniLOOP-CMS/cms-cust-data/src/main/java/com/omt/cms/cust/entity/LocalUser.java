package com.omt.cms.cust.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.omt.cms.core.common.DateHelper;
import com.omt.cms.entity.Address;

@Entity
@Table(name = "local_users")
public class LocalUser implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	protected Long id;

	@Column(name="created_on",nullable=true)
	protected Timestamp createdOn;
	
	@Column(name="last_updated")
	protected Timestamp lastUpdated;
	
	@Column(name="notes", length=500)
	@Size(max = 500)
	protected String notes;
	
	@Column(name="valid_untill")
	protected Timestamp validUntil;
	
	@Column(name="tag_role", length=100)
	@Size(max =100)
	protected String tagRole;
	
	@Column(name="status")
	protected Integer status;
	
	@Column(name="login_name",length=300)
	@Size(max=300)
	protected String loginName;
	
	@Column(name="user_name",length=300)
	@Size(max=300)
	protected String userName;
	
	@Column(name="address")
	protected Address address;
	
	@Column(name="user_contact",length=16)
	@Size(max=16)
	protected String userContact;
	
	@Column(name="user_status")
	protected Integer userStatus;
	
	@Column(name="pwd_hash",length=200)
	@Size(max=200)
	protected String pwdHash;
	
 	@ManyToOne(optional=false) 
	@JoinColumn(name="company_id", nullable=false, updatable=false)
	private CompanyInstance company;
	
	@Column(name="user_email",length=300)
	@Size(max=300)
	private String userEmail;
	
 	@Column(name="user_invited")
	protected Timestamp userInvitedDT;
	
 	@Column(name="user_activated")
	protected Timestamp userActivated;
	
	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="share_subscriber")
	private Boolean shareSubscriber;

	@Column(name="email_valid")
	private Boolean emailValid;
	
	@Column(name="phone_valid")
	private Boolean phoneValid;
	
	@Column(name="date_of_birth", nullable=true)
	private Date dateOfBirth;

	@Column(name="title",length=300, nullable=true)
	@Size(max=300)
	private String title;

	public LocalUser(){
		Timestamp now = DateHelper.getCurTimestamp();
		this.createdOn = now;
		this.lastUpdated = now;
	}

	public CompanyInstance getCompany() {
		return company;
	}

	public void setCompany(CompanyInstance company) {
		this.company = company;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getShareSubscriber() {
		return shareSubscriber;
	}

	public void setShareSubscriber(Boolean shareSubscriber) {
		this.shareSubscriber = shareSubscriber;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Long getId() {
		return id;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Timestamp getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Timestamp validUntil) {
		this.validUntil = validUntil;
	}

	public String getTagRole() {
		return tagRole;
	}

	public void setTagRole(String tagRole) {
		this.tagRole = tagRole;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

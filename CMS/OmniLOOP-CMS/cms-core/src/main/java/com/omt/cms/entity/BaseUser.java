package com.omt.cms.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import com.omt.cms.core.common.Encryptor;

@MappedSuperclass
public abstract class BaseUser extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@Column(name="login_name",length=300, unique=true)
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
	
	public boolean comparePassword(String password){
		return Encryptor.getInstance().checkPassword(password, this.pwdHash);
	}
	
	public void encryptAndSetPassword(String password){
		this.pwdHash = Encryptor.getInstance().encryptPassword(password);
	}

	public String createAndSetRandomPass(){
		String randPass = Encryptor.getInstance().getRandomPassword(10);
		this.encryptAndSetPassword(randPass);
		return randPass;
	}

}

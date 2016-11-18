package com.omt.cms.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import com.omt.cms.core.common.SystemCodes.ROLE;

@MappedSuperclass
public abstract class BaseManager extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@Column(name="manager_name", length=200)
	@Size(max=200)
	private String name; //full name
	
	@Column(name="login_name", length=100)
	@Size(max=100)
	private String loginName;
	
	@Column(name="manager_password", length=255)
	@Size(max=255)
	private String encryptedPassword; //encrypted password
	
	@Column(name="manager_type")
	private Integer type;
	
	public BaseManager(){
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getRole(){
		if(this.getType() == ManagerType.INSTANCE_ADMIN.getValue()){
			return ROLE.INSTANCE_ADMIN.getValue();
		}else if(this.getType() == ManagerType.MASTER_ADMIN.getValue()){
			return ROLE.MASTER_ADMIN.getValue();
		}else if(this.getType() == ManagerType.SUPER_ADMIN.getValue()){
			return ROLE.SUPER_ADMIN.getValue();
		}
		return ROLE.USER.getValue();
	}

	public static enum ManagerType{
		INSTANCE_ADMIN(1), MASTER_ADMIN(2), SUPER_ADMIN(4);
		
		private int value;
		
		ManagerType(int v){
			this.value=v;
		}
		
		public int getValue(){
			return value;
		}
	}
}
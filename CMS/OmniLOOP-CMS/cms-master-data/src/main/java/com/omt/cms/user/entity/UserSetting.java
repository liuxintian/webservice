package com.omt.cms.user.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_settings")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "user_settings_seq")
public class UserSetting implements Serializable{

	private static final long serialVersionUID = 1L;
	@Column(name="id")
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQGEN")
	private Long id;

	@ManyToOne(optional=false) 
	@JoinColumn(name="user_id", nullable=false, updatable=false)
	private User user;
	
	@Column(name="created_on")
	private Timestamp createdOn;
	
	@Column(name="last_updated")
	private Timestamp lastUpdated;
	
	@Column(name="setting_name", length=100)
	@Size(max=100)
	private String settingName;
	
	@Column(name="setting_value", length=500)
	@Size(max=500)
	private String settingValue;

	public UserSetting(){
		super();
		Timestamp now =new Timestamp((new Date()).getTime());
		createdOn=lastUpdated=now; 
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getSettingName() {
		return settingName;
	}

	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}

	public String getSettingValue() {
		return settingValue;
	}

	public void setSettingValue(String settingValue) {
		this.settingValue = settingValue;
	}

	public Long getId() {
		return id;
	}
	
}

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
@Table(name = "user_devices")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "user_devices_seq")
public class UserDevice implements Serializable{

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
	
	@Column(name="device_id", length=255)
	@Size(max=255)
	private String deviceId;
	
	@Column(name="device_os", length=100)
	@Size(max=100)
	private String deviceOS;
	
	@Column(name="device_ver", length=100)
	@Size(max=100)
	private String deviceVer;
	
	@Column(name="device_size", length=100)
	@Size(max=100)
	private String deviceSize;

	public UserDevice(){
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

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Long getId() {
		return id;
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

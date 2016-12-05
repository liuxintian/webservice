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
@Table(name = "user_registered")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "user_registered_seq")
public class UserRegistered implements Serializable{

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
	
	@Column(name="app_registered", length=100)
	@Size(max=100)
	private String appRegistered;
	
	@Column(name="is_primary")
	private Boolean isPrimary;
	

	public UserRegistered(){
		Date now=new Date();
		createdOn=lastUpdated=new Timestamp(now.getTime());
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

	public String getAppRegistered() {
		return appRegistered;
	}

	public void setAppRegistered(String appRegistered) {
		this.appRegistered = appRegistered;
	}

	public Boolean getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
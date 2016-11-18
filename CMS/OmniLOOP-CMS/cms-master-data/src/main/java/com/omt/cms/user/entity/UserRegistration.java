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
@Table(name = "user_registrations")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "user_registration_seq")
public class UserRegistration implements Serializable{

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
	
	@Column(name="share_registry", length=100)
	@Size(max=100)
	private String shareRegistry;
	
	@Column(name="token_id", length=200)
	@Size(max=200)
	private String tokenId;
	
	public UserRegistration(){
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

	public String getShareRegistry() {
		return shareRegistry;
	}

	public void setShareRegistry(String shareRegistry) {
		this.shareRegistry = shareRegistry;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public Long getId() {
		return id;
	}

}

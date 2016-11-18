package com.omt.cms.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import com.omt.cms.core.common.DateHelper;

@MappedSuperclass
public abstract class Verification implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final short TOKEN_EXPIRY_DAYS = 30;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQGEN")
	@Column(name="id")
	protected Long id;

	@Column(name="created_on", nullable=true)
	protected Timestamp createdOn;
	
	@Column(name="last_updated")
	protected Timestamp lastUpdated;
	
	@Column(name="metadata", length=500)
	@Size(max = 500)
	protected String metadata;
	
	@Column(name="valid_untill")
	protected Timestamp validUntil;

	@Column(name="status")
	protected Integer status;

	@Column(name="created_for")
	protected Long createdFor;
	
	@Column(name="context")
	protected Integer context;
	
	@Column(name="context_ref_key")
	protected Long contextRefKey;
	
	@Column(name="token",length=200)
	@Size(max=200)
	protected String token;

	
	public Verification(){
		Timestamp now = new Timestamp(System.currentTimeMillis());
		this.createdOn = now;
		this.lastUpdated = now;
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

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public Timestamp getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Timestamp validUntil) {
		this.validUntil = validUntil;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreatedFor() {
		return createdFor;
	}

	public void setCreatedFor(Long createdFor) {
		this.createdFor = createdFor;
	}

	public Integer getContext() {
		return context;
	}

	public void setContext(Integer context) {
		this.context = context;
	}

	public Long getContextRefKey() {
		return contextRefKey;
	}

	public void setContextRefKey(Long contextRefKey) {
		this.contextRefKey = contextRefKey;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isValidToken(){
		Date expiryDate = DateHelper.addDays(this.createdOn, TOKEN_EXPIRY_DAYS);		
		Date currentDate = DateHelper.getTodayDate(DateHelper.FORMAT_MMDDYYYY);
		return expiryDate.after(currentDate);
	}
}

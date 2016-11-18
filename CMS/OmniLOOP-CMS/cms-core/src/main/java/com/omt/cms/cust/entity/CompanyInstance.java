package com.omt.cms.cust.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.omt.cms.core.common.DateHelper;
import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.common.SystemCodes.RecordStatus;

@Entity
@Table(name = "company_instances")
public class CompanyInstance implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	protected Long id;

	@Column(name="created_on",nullable=true)
	protected Timestamp createdOn;

	@Column(name="last_updated")
	protected Timestamp lastUpdated;

	@Column(name="notes",length=500)
	@Size(max=500)
	protected String notes;

	@Column(name="valid_untill")
	protected Timestamp validUntil;

	@Column(name="tag_role",length=100)
	@Size(max=100)
	protected String tagRole;

	@Column(name="status")
	protected Integer status;

	@Column(name="company_name", length=255)
	@Size(max=255)
	private String companyName;

	public CompanyInstance(){
		super();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		this.createdOn = now;
		this.lastUpdated = now;
	}

	public CompanyInstance(Long id){
		this();
		this.id = id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setValidUntil(Timestamp validUntill) {
		this.validUntil = validUntill;
	}

	public Long getId() {
		return id;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public boolean isActive(){
		if(this.status!=null){
			return this.status.intValue() == RecordStatus.ACTIVE.getValue();
		}
		return false; 
	}
	public void setDefaultValidUntil(){
		if(this.validUntil==null){
			Date upd=DateHelper.addYear(this.createdOn,SystemCodes.DEFAULT_VALID_UNTIL_YEAR_INCREMENT);
			this.validUntil=new Timestamp(upd.getTime());
		}
	}

}

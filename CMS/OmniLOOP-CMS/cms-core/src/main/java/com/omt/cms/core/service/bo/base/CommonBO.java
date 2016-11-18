/**
 * 
 */
package com.omt.cms.core.service.bo.base;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.omt.cms.core.common.DateHelper;
import com.omt.cms.core.common.LongToDateJsonDeserializer;
import com.omt.cms.core.common.SystemCodes;

/**
 * @author muragesh
 *
 */
public abstract class CommonBO extends BaseBusinessObject  {

	private static final long serialVersionUID = 1L;

	protected Long id;
	protected Timestamp createdOn;
	protected Timestamp lastUpdated;
	protected String notes;
	
	@JsonDeserialize(using=LongToDateJsonDeserializer.class)
	protected Timestamp validUntil;
	
	protected String tagRole;
	protected Integer status;
	
	private Long total;

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

	public void setDefaultValidUntil(){
		if(this.validUntil==null){
			Date upd=DateHelper.addYear(this.createdOn,SystemCodes.DEFAULT_VALID_UNTIL_YEAR_INCREMENT);
			this.validUntil=new Timestamp(upd.getTime());
		}
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
}

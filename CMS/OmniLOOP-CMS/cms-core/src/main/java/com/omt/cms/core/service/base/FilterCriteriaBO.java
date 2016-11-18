package com.omt.cms.core.service.base;

import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;

public class FilterCriteriaBO {

	public Long cpId;
	public String status;
	public Timestamp validUntil;
	public String sortField;
	public Integer sortOrder;
	public String entityName;
	public Boolean tagRoleEmtpy;
	public String[] tagRoles;
	public String tagRole;
	public Integer offset;
	public Integer size;
	public String term;
	public String companyType;
	
	public FilterCriteriaBO(){ }

	public FilterCriteriaBO(String status, Timestamp validUntil){
		this.status = status;
		this.validUntil = validUntil;
	}

	public FilterCriteriaBO(Long cpId, String status, Timestamp validUntil){
		this(status, validUntil);
		this.cpId = cpId;
	}

	public FilterCriteriaBO(String status, Timestamp validUntil, String sortField, Integer sortOrder){
		this(status, validUntil);
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public FilterCriteriaBO(Long cpId, String status, Timestamp validUntil, String sortField, Integer sortOrder){
		this(status, validUntil, sortField, sortOrder);
		this.cpId = cpId;	
	}
	
	public FilterCriteriaBO(Long cpId, String status, Timestamp validUntil, String sortField, Integer sortOrder, String companyType){
		this(cpId,status, validUntil, sortField, sortOrder);
		this.companyType = companyType;	
	}

	public FilterCriteriaBO(String term, Integer page, Integer size, String sortBy, Integer sortOrder){
		this.term = term;
		this.offset = page;
		this.size = size;
		this.sortField = sortBy;
		this.sortOrder = sortOrder;
	}

	public void assignDefaultsIfNotDefined(int offset, int size, String sortField, int sortOrder){
		
		if(this.offset == null){
			this.offset = offset;
		}
		
		if(this.size == null){
			this.size = size;
		}

		if(StringUtils.isBlank(this.sortField)){
			this.sortField = sortField;
		}
		
		if(this.sortOrder == null){
			this.sortOrder = sortOrder;
		}
	}
}

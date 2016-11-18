package com.omt.cms.core.service.base;

import java.util.List;

import com.omt.cms.core.service.bo.base.CommonBO;
import com.omt.cms.core.service.bo.base.SerializableObject;

public class SearchResultBO extends SerializableObject{
	
	private static final long serialVersionUID = 1L;

	private FilterCriteriaBO criteria;
	private List<? extends CommonBO> result;
	private Long total; 
	
	public FilterCriteriaBO getCriteria() {
		return criteria;
	}
	
	public void setCriteria(FilterCriteriaBO criteria) {
		this.criteria = criteria;
	}
	
	public List<? extends CommonBO> getResult() {
		return result;
	}
	
	public void setResult(List<? extends CommonBO> result) {
		this.result = result;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}

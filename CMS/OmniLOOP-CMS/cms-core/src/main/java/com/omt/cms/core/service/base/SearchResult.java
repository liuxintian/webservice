package com.omt.cms.core.service.base;

import java.util.List;

import com.omt.cms.core.service.bo.base.SerializableObject;
import com.omt.cms.entity.BaseEntity;

public class SearchResult extends SerializableObject{
	
	private static final long serialVersionUID = 1L;

	private List<? extends BaseEntity> result;
	private Long total; 
	
	public List<? extends BaseEntity> getResult() {
		return result;
	}
	
	public void setResult(List<? extends BaseEntity> result) {
		this.result = result;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}

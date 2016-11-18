package com.omt.cms.user.service.bo;

import java.util.List;

import com.omt.cms.core.service.bo.base.CommonBO;

 
public class UserStockSubscriptionBO extends CommonBO{
	private static final long serialVersionUID = 1L;
	
 	private Long userId;
 	private Long companyId;
	private String companyTicker;
	private List<String> companyTickers;
	
	public UserStockSubscriptionBO(){ 
		super();
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyTicker() {
		return companyTicker;
	}

	public void setCompanyTicker(String companyTicker) {
		this.companyTicker = companyTicker;
	}

	public List<String> getCompanyTickers() {
		return companyTickers;
	}

	public void setCompanyTickers(List<String> companyTickers) {
		this.companyTickers = companyTickers;
	}
}

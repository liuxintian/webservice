package com.omt.cms.user.service.bo;

import com.omt.cms.core.service.bo.base.CommonBO;

 
public class UserStockWatchBO extends CommonBO{
	private static final long serialVersionUID = 1L;
	
 	private Long userId;
 	private Long companyId;
	private String companyName;
	private String companyTicker;
	private String companyLogoBig;
	private String companyLogoSmall;
	private String companyTeaser;
	private String companyURL;
	private Boolean isRegistered;
	private String shareRegistryId;
	private String sector;
	private String industry;
	private String subIndustry;
	private String companyType;
	private String companyEmail;

	public UserStockWatchBO(){ 
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyTicker() {
		return companyTicker;
	}

	public void setCompanyTicker(String companyTicker) {
		this.companyTicker = companyTicker;
	}

	public String getCompanyLogoBig() {
		return companyLogoBig;
	}

	public void setCompanyLogoBig(String companyLogoBig) {
		this.companyLogoBig = companyLogoBig;
	}

	public String getCompanyLogoSmall() {
		return companyLogoSmall;
	}

	public void setCompanyLogoSmall(String companyLogoSmall) {
		this.companyLogoSmall = companyLogoSmall;
	}

	public String getCompanyTeaser() {
		return companyTeaser;
	}

	public void setCompanyTeaser(String companyTeaser) {
		this.companyTeaser = companyTeaser;
	}

	public String getCompanyURL() {
		return companyURL;
	}

	public void setCompanyURL(String companyURL) {
		this.companyURL = companyURL;
	}

	public Boolean getIsRegistered() {
		return isRegistered;
	}

	public void setIsRegistered(Boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	public String getShareRegistryId() {
		return shareRegistryId;
	}

	public void setShareRegistryId(String shareRegistryId) {
		this.shareRegistryId = shareRegistryId;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getSubIndustry() {
		return subIndustry;
	}

	public void setSubIndustry(String subIndustry) {
		this.subIndustry = subIndustry;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
}

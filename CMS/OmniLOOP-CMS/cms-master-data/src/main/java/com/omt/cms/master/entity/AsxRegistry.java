package com.omt.cms.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.omt.cms.entity.BaseEntity;

@Entity
@Table(name = "asx_registries")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "asx_registries_seq")
public class AsxRegistry extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Column(name="company_name", length=255)
	@Size(max=255)
	private String companyName;
	
	@Column(name="company_ticker", length=255, unique=true)
	@Size(max=255)
	private String companyTicker;
	
	@Column(name="company_logo_b", length=255)
	@Size(max=255)
	private String companyLogoBig;
	
	@Column(name="company_logo_s", length=255)
	@Size(max=255)
	private String companyLogoSmall;
	
	@Column(name="company_teaser", length=255)
	@Size(max=255)
	private String companyTeaser;
	
	@Column(name="company_url", length=500)
	@Size(max=500)
	private String companyURL;
	
	@Column(name="company_desc", length=500)
	@Size(max=500)
	private String companyDescription;
	
	@Column(name="country", length=100)
	@Size(max=100)
	private String country;
	
	@Column(name="company_size")
	private Integer companySize;
	
	@Column(name="co_shareholders")
	private Integer companyShareholders;
	
	@Column(name="is_registered")
	private Boolean isRegistered;
	
	@Column(name="share_registry_id")
	private String shareRegistryId;
	
	@Column(name="sector", length=255)
	@Size(max=255)
	private String sector;
	
	@Column(name="industry", length=255)
	@Size(max=255)
	private String industry;
	
	@Column(name="sub_industry", length=255)
	@Size(max=255)
	private String subIndustry;
	
	@Column(name="company_type", length=100)
	@Size(max=100)
	private String companyType;

	@Column(name="company_email", length=300)
	@Size(max=300)
	private String companyEmail;

	public AsxRegistry(){
		super();
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

	public String getCompanyDescription() {
		return companyDescription;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getCompanySize() {
		return companySize;
	}

	public void setCompanySize(Integer companySize) {
		this.companySize = companySize;
	}

	public Integer getCompanyShareholders() {
		return companyShareholders;
	}

	public void setCompanyShareholders(Integer companyShareholders) {
		this.companyShareholders = companyShareholders;
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

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public void setId(Long cpId) {
		this.id=cpId;
	}
}

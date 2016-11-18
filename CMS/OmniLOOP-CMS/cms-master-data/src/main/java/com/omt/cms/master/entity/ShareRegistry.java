package com.omt.cms.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.omt.cms.entity.BaseEntity;

@Entity
@Table(name = "share_registries")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "share_registries_seq")
public class ShareRegistry extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name="share_registry", length=100)
	@Size(max=100)
	private String shareRegistry;
	
	@Column(name="sr_country", length=100)
	@Size(max=100)
	private String srCountry;
	
	@Column(name="sr_address", length=500)
	@Size(max=500)
	private String srAddress;
	
	@Column(name="sr_url", length=500)
	@Size(max=500)
	private String srURL;
	
	@Column(name="sr_phone", length=16)
	@Size(max=16)
	private String srPhone;
	
	@Column(name="sr_email", length=100)
	@Size(max=100)
	private String srEmail;
	
	@Column(name="sr_type", length=100)
	@Size(max=100)
	private String srType;

	public ShareRegistry() {
		super();
	}

	public String getShareRegistry() {
		return shareRegistry;
	}

	public void setShareRegistry(String shareRegistry) {
		this.shareRegistry = shareRegistry;
	}

	public String getSrCountry() {
		return srCountry;
	}

	public void setSrCountry(String srCountry) {
		this.srCountry = srCountry;
	}

	public String getSrAddress() {
		return srAddress;
	}

	public void setSrAddress(String srAddress) {
		this.srAddress = srAddress;
	}

	public String getSrURL() {
		return srURL;
	}

	public void setSrURL(String srURL) {
		this.srURL = srURL;
	}

	public String getSrPhone() {
		return srPhone;
	}

	public void setSrPhone(String srPhone) {
		this.srPhone = srPhone;
	}

	public String getSrEmail() {
		return srEmail;
	}

	public void setSrEmail(String srEmail) {
		this.srEmail = srEmail;
	}

	public String getSrType() {
		return srType;
	}

	public void setSrType(String srType) {
		this.srType = srType;
	}

}

package com.omt.cms.cust.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.omt.cms.entity.BaseEntity;

@Entity
@Table(name = "companies_executives")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "companies_executives_seq")
public class CompanyExecutive extends BaseEntity {

	private static final long serialVersionUID = 1L;

 	@ManyToOne(optional=false) 
	@JoinColumn(name="company_id", nullable=false, updatable=false)
	private CompanyInstance company;
 	
	@Column(name="exec_name", length=100)
	@Size(max=100)
	private String execName;
	
	@Column(name="exec_job_title", length=200)
	@Size(max=100)
	private String execJobTitle;
	
	@Column(name="exec_desc", length=500)
	@Size(max=500)
	private String execDesc;

	@Column(name="exec_email", length=100)
	@Size(max=100)
	private String execEmail;
	
	@Column(name="exec_phone", length=16)
	@Size(max=16)
	private String execPhone;
	
	@Column(name="exec_image_url", length=500)
	@Size(max=500)
	private String execImageURL;
	
	@Column(name="exec_type", length=100)
	@Size(max=100)
	private String execType;
	
	public CompanyExecutive(){
		super();
	}

	public CompanyInstance getCompany() {
		return company;
	}

	public void setCompany(CompanyInstance company) {
		this.company = company;
	}

	public String getExecName() {
		return execName;
	}

	public void setExecName(String execName) {
		this.execName = execName;
	}

	public String getExecJobTitle() {
		return execJobTitle;
	}

	public void setExecJobTitle(String execJobTitle) {
		this.execJobTitle = execJobTitle;
	}

	public String getExecDesc() {
		return execDesc;
	}

	public void setExecDesc(String execDesc) {
		this.execDesc = execDesc;
	}

	public String getExecEmail() {
		return execEmail;
	}

	public void setExecEmail(String execEmail) {
		this.execEmail = execEmail;
	}

	public String getExecPhone() {
		return execPhone;
	}

	public void setExecPhone(String execPhone) {
		this.execPhone = execPhone;
	}

	public String getExecImageURL() {
		return execImageURL;
	}

	public void setExecImageURL(String execImageURL) {
		this.execImageURL = execImageURL;
	}

	public String getExecType() {
		return execType;
	}

	public void setExecType(String execType) {
		this.execType = execType;
	}
	
}

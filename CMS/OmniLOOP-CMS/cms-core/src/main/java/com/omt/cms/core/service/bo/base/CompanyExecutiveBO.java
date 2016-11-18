package com.omt.cms.core.service.bo.base;

public class CompanyExecutiveBO extends CommonBO{

	private static final long serialVersionUID = 1L;

	private Long companyId;
	private String execName;
	private String execJobTitle;
	private String execDesc;
	private String execEmail;
	private String execPhone;
	private String execImageURL;
	private String execType;
	
	public CompanyExecutiveBO(){
		super();
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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

package com.omt.webservice.meetings.entity;


/**
 * Company details OMNILight
 * @author tonyliu
 *
 */

public class Company {
	
	private String companyID;
	private String companyName;
	private String tickerCode;
	private String companyLogo;
	
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTickerCode() {
		return tickerCode;
	}
	public void setTickerCode(String tickerCode) {
		this.tickerCode = tickerCode;
	}
	public String getCompanyLogo() {
		return companyLogo;
	}
	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}
	
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("companyID:").append(companyID).append("--")
		   .append("companyName:").append(companyName).append("--")
		   .append("tickerCode:").append(tickerCode).append("--")
		   .append("companyLogo:").append(companyLogo);
		return str.toString();
	}
}

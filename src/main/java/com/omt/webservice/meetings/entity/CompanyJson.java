package com.omt.webservice.meetings.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Company details OMNILight
 * @author tonyliu
 *
 */

@JsonPropertyOrder({ "companyID", "companyName", "tickerCode", "companyLogo", "meetings"})
public class CompanyJson {
	
	private String companyID;
	private String companyName;
	private String tickerCode;
	private String companyLogo;
	
	private List<MeetingsJson> meetings;

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
	public List<MeetingsJson> getMeetings() {
		return meetings;
	}
	public void setMeetings(List<MeetingsJson> meetings) {
		this.meetings = meetings;
	}
}

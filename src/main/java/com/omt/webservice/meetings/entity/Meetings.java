package com.omt.webservice.meetings.entity;

import java.util.List;

/**
 * Meeting all details
 * @author tonyliu
 *
 */
public class Meetings {
	
	private String companyID;
	
	private String meetingID;
	private String meetingName;
	private String meetingDate;
	private String meetingTime;
	private String meetingType;
	private String meetingLocation;
	private String meetingBeforeMsg;
	private String meetingAfterMsg;
	private String meetingContacts;
	private List<String> meetingDetails;
	private boolean hasPassword;
	private String password;

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getMeetingID() {
		return meetingID;
	}

	public void setMeetingID(String meetingID) {
		this.meetingID = meetingID;
	}

	public String getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(String meetingTime) {
		this.meetingTime = meetingTime;
	}

	public String getMeetingType() {
		return meetingType;
	}

	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}

	public String getMeetingBeforeMsg() {
		return meetingBeforeMsg;
	}

	public void setMeetingBeforeMsg(String meetingBeforeMsg) {
		this.meetingBeforeMsg = meetingBeforeMsg;
	}

	public String getMeetingAfterMsg() {
		return meetingAfterMsg;
	}

	public void setMeetingAfterMsg(String meetingAfterMsg) {
		this.meetingAfterMsg = meetingAfterMsg;
	}

	public String getMeetingContacts() {
		return meetingContacts;
	}

	public void setMeetingContacts(String meetingContacts) {
		this.meetingContacts = meetingContacts;
	}

	public String getMeetingLocation() {
		return meetingLocation;
	}

	public void setMeetingLocation(String meetingLocation) {
		this.meetingLocation = meetingLocation;
	}

	public List<String> getMeetingDetails() {
		return meetingDetails;
	}

	public void setMeetingDetails(List<String> meetingDetails) {
		this.meetingDetails = meetingDetails;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public boolean isHasPassword() {
		return hasPassword;
	}

	public void setHasPassword(boolean hasPassword) {
		this.hasPassword = hasPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

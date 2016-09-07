package com.omt.webservice.meetings.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Meeting all details
 * @author tonyliu
 *
 */
@JsonPropertyOrder({"meetingID", "hasPassword" ,"meetingName", "meetingDate", "meetingTime", "meetingLocation", "meetingType", "meetingBeforeMsg", "meetingAfterMsg", "meetingContacts", "meetingDetails"})
public class MeetingsJson {
	
	private String meetingID;
	private String meetingName;
	private String meetingDate;
	private String meetingTime;
	private String meetingType;
	private String meetingLocation;
	private String meetingBeforeMsg;
	private String meetingAfterMsg;
	private Contacts meetingContacts;
	private boolean hasPassword;

	private MeetingDetails meetingDetails;

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

	public Contacts getMeetingContacts() {
		return meetingContacts;
	}

	public void setMeetingContacts(Contacts meetingContacts) {
		this.meetingContacts = meetingContacts;
	}


	public String getMeetingLocation() {
		return meetingLocation;
	}

	public void setMeetingLocation(String meetingLocation) {
		this.meetingLocation = meetingLocation;
	}

	public MeetingDetails getMeetingDetails() {
		return meetingDetails;
	}

	public void setMeetingDetails(MeetingDetails meetingDetails) {
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
}

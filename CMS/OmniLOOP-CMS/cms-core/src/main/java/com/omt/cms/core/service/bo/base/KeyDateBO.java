package com.omt.cms.core.service.bo.base;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.omt.cms.core.common.LongToDateJsonDeserializer;

public class KeyDateBO extends CommonBO{

	private static final long serialVersionUID = 1L;

	private Long companyId;
	
	@JsonDeserialize(using=LongToDateJsonDeserializer.class)
	private Timestamp eventDateTime;
	
	@JsonDeserialize(using=LongToDateJsonDeserializer.class)
	private Timestamp eventStartDateTime;
	
	@JsonDeserialize(using=LongToDateJsonDeserializer.class)
	private Timestamp eventEndDateTime;
	
	private String eventTitle;
	private String eventSubTitle;
	private String eventLocation;
	private String eventDetails;
	private String keydateType;
	private Boolean eventFullDay;
	private Boolean eventRange;

	private String companyName;
	private String companyTicker;

	public KeyDateBO(){
		super();
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Timestamp getEventDateTime() {
		return eventDateTime;
	}

	public void setEventDateTime(Timestamp eventDateTime) {
		this.eventDateTime = eventDateTime;
	}
	
	public Timestamp getEventStartDateTime() {
		return eventStartDateTime;
	}

	public void setEventStartDateTime(Timestamp eventStartDateTime) {
		this.eventStartDateTime = eventStartDateTime;
	}

	public Timestamp getEventEndDateTime() {
		return eventEndDateTime;
	}

	public void setEventEndDateTime(Timestamp eventEndDateTime) {
		this.eventEndDateTime = eventEndDateTime;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEventSubTitle() {
		return eventSubTitle;
	}

	public void setEventSubTitle(String eventSubTitle) {
		this.eventSubTitle = eventSubTitle;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventDetails() {
		return eventDetails;
	}

	public void setEventDetails(String eventDetails) {
		this.eventDetails = eventDetails;
	}

	public String getKeydateType() {
		return keydateType;
	}

	public void setKeydateType(String keydateType) {
		this.keydateType = keydateType;
	}

	public Boolean getEventFullDay() {
		return eventFullDay;
	}

	public void setEventFullDay(Boolean fullDayEvent) {
		this.eventFullDay = fullDayEvent;
	}

	public Boolean getEventRange() {
		return eventRange;
	}

	public void setEventRange(Boolean eventRange) {
		this.eventRange = eventRange;
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
}

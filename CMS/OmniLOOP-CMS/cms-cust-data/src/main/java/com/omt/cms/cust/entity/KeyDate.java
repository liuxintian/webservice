package com.omt.cms.cust.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.omt.cms.entity.BaseEntity;

@Entity
@Table(name = "keydates")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "keydates_seq")
public class KeyDate extends BaseEntity {

	private static final long serialVersionUID = 1L;

 	@ManyToOne(optional=false) 
	@JoinColumn(name="company_id", nullable=false, updatable=false)
	private CompanyInstance company;

 	//TODO Remove this field after UI changes
 	@Column(name="event_date_time")
	private Timestamp eventDateTime;

 	@Column(name="event_start_date_time")
	private Timestamp eventStartDateTime;

 	@Column(name="event_end_date_time")
	private Timestamp eventEndDateTime;

 	@Column(name="event_title", length=255)
	@Size(max=255)
	private String eventTitle;
	
	@Column(name="event_subtitle", length=255)
	@Size(max=255)
	private String eventSubTitle;
	
	@Column(name="event_location", length=100)
	@Size(max=100)
	private String eventLocation;
	
	@Column(name="event_details", length=500)
	@Size(max=500)
	private String eventDetails;
	
	@Column(name="key_date_type", length=100)
	@Size(max=100)
	private String keydateType;

	@Column(name="event_full_day")
	private Boolean eventFullDay;

	@Column(name="event_range")
	private Boolean eventRange;

	public KeyDate(){
		super();
	}

	public CompanyInstance getCompany() {
		return company;
	}

	public void setCompany(CompanyInstance company) {
		this.company = company;
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

}

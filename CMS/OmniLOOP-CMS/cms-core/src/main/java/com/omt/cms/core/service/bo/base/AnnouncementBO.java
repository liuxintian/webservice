package com.omt.cms.core.service.bo.base;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.omt.cms.core.common.LongToDateJsonDeserializer;

public class AnnouncementBO extends CommonBO {

	private static final long serialVersionUID = 1L;

	private Long companyId;
	private Boolean isPriceSensitive;
	private String documentLink;
	private String documentTitle;
	private String announcementType;
	@JsonDeserialize(using=LongToDateJsonDeserializer.class)
	protected Timestamp announcementDate;

	private String companyName;
	private String companyTicker;

	public AnnouncementBO(){
		super();
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Boolean getIsPriceSensitive() {
		return isPriceSensitive;
	}

	public void setIsPriceSensitive(Boolean isPriceSensitive) {
		this.isPriceSensitive = isPriceSensitive;
	}

	public String getDocumentLink() {
		return documentLink;
	}

	public void setDocumentLink(String documentLink) {
		this.documentLink = documentLink;
	}

	public String getDocumentTitle() {
		return documentTitle;
	}

	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}

	public String getAnnouncementType() {
		return announcementType;
	}

	public void setAnnouncementType(String announcementType) {
		this.announcementType = announcementType;
	}

	public Timestamp getAnnouncementDate() {
		return announcementDate;
	}

	public void setAnnouncementDate(Timestamp announcementDate) {
		this.announcementDate = announcementDate;
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

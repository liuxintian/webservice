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
@Table(name = "announcements")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "announcments_seq")
public class Announcement extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false)
	@JoinColumn(name = "company_id", nullable = false, updatable = false)
	private CompanyInstance company;

	@Column(name = "is_price_sensitive")
	private Boolean isPriceSensitive;

	@Column(name = "document_link", length = 500)
	@Size(max = 500)
	private String documentLink;

	@Column(name = "document_title", length = 500)
	@Size(max = 500)
	private String documentTitle;

	@Column(name = "announcement_type", length = 100)
	@Size(max = 100)
	private String announcementType;

	@Column(name = "announcement_date", nullable = true)
	protected Timestamp announcementDate;

	public Announcement() {
		super();
	}

	public CompanyInstance getCompany() {
		return company;
	}

	public void setCompany(CompanyInstance company) {
		this.company = company;
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
}

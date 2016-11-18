package com.omt.cms.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.omt.cms.entity.BaseEntity;

@Entity
@Table(name = "feeds")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "feeds_seq")
public class Feed extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	@Column(name="feed_title", length=255)
	@Size(max=255)
	private String feedTitle;
	
	@Column(name="feed_desc", length=500)
	@Size(max=500)
	private String feedDesc;
	
	@Column(name="feed_url", length=500)
	@Size(max=500)
	private String feedURL;
	
	@Column(name="feed_type", length=100)
	@Size(max=100)
	private String feedType;
	
	@Column(name="feed_contact", length=100)
	@Size(max=100)
	private String feedContact;
	
	@Column(name="feed_company", length=100)
	@Size(max=100)
	private String feedCompany;
	
	public Feed(){ 
		super();
	}

	public String getFeedTitle() {
		return feedTitle;
	}

	public void setFeedTitle(String feedTitle) {
		this.feedTitle = feedTitle;
	}

	public String getFeedDesc() {
		return feedDesc;
	}

	public void setFeedDesc(String feedDesc) {
		this.feedDesc = feedDesc;
	}

	public String getFeedURL() {
		return feedURL;
	}

	public void setFeedURL(String feedURL) {
		this.feedURL = feedURL;
	}

	public String getFeedType() {
		return feedType;
	}

	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

	public String getFeedContact() {
		return feedContact;
	}

	public void setFeedContact(String feedContact) {
		this.feedContact = feedContact;
	}

	public String getFeedCompany() {
		return feedCompany;
	}

	public void setFeedCompany(String feedCompany) {
		this.feedCompany = feedCompany;
	} 
}

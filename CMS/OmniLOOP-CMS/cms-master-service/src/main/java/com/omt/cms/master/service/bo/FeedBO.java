package com.omt.cms.master.service.bo;

import com.omt.cms.core.service.bo.base.CommonBO;

/**
 * 
 * @author muragesh
 *
 */

public class FeedBO extends CommonBO {

	private static final long serialVersionUID = 1L;

	private String feedTitle;
	private String feedDesc;
	private String feedURL;
	private String feedType;
	private String feedContact;
	private String feedCompany;
	
	public FeedBO(){
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

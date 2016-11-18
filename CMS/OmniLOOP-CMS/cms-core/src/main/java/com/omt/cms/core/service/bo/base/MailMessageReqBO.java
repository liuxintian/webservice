package com.omt.cms.core.service.bo.base;

import java.util.List;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

public class MailMessageReqBO {
	
	private Long companyId;
	private String content;
	private List<Receipient> mailIds;
	private String subject;

	public MailMessageReqBO(){ }

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Receipient> getMailIds() {
		return mailIds;
	}

	public void setMailIds(List<Receipient> mailIds) {
		this.mailIds = mailIds;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}



	public static class Receipient{
		private String name;
		private String email;
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
	}
}

package com.omt.cms.core.service.bo.base;

public class DocumentLinksMediaBO extends CommonBO {

	private static final long serialVersionUID = 1L;

	private Long companyId;
	private String docTitle;
	private String docDesc;
	private long docSize;
	private String docExtension;
	private String docThumbnail;
	private String docLinkURL;
	private String docNote;
	private String docType;
	
	public DocumentLinksMediaBO(){
		super();
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public String getDocDesc() {
		return docDesc;
	}

	public void setDocDesc(String docDesc) {
		this.docDesc = docDesc;
	}

	public long getDocSize() {
		return docSize;
	}

	public void setDocSize(long docSize) {
		this.docSize = docSize;
	}

	public String getDocExtension() {
		return docExtension;
	}

	public void setDocExtension(String docExtension) {
		this.docExtension = docExtension;
	}

	public String getDocThumbnail() {
		return docThumbnail;
	}

	public void setDocThumbnail(String docThumbnail) {
		this.docThumbnail = docThumbnail;
	}

	public String getDocLinkURL() {
		return docLinkURL;
	}

	public void setDocLinkURL(String docLinkURL) {
		this.docLinkURL = docLinkURL;
	}

	public String getDocNote() {
		return docNote;
	}

	public void setDocNote(String docNote) {
		this.docNote = docNote;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}
}

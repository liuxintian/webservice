package com.omt.cms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name = "doc_media_links")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "docs_media_link_seq")
public class DocumentLinksMedia extends BaseEntity {

	private static final long serialVersionUID = 1L;


	//@ManyToOne(optional = false)
	@Column(name = "company_id", nullable = false, updatable = false)
	private Long companyId;

	@Column(name="doc_title", length=200)
	@Size(max=200)
	private String docTitle;

	@Column(name="doc_desc", length=500)
	@Size(max=500)
	private String docDesc;
	
	@Column(name="doc_size")
	private long docSize;
	
	@Column(name="doc_extension", length=10)
	@Size(max=10)
	private String docExtension;
	
	@Column(name="doc_thumbnail", length=255)
	@Size(max=255)
	private String docThumbnail;
	
	@Column(name="doc_url", length=255)
	@Size(max=255)
	private String docLinkURL;
	
	@Column(name="doc_note", length=500)
	@Size(max=500)
	private String docNote;
	
	@Column(name="doc_type", length=50)
	@Size(max=50)
	private String docType;
	
	public DocumentLinksMedia(){
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

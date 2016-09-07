package com.omt.webservice.meetings.entity;


/**
 * Document for meetings [done]
 * @author tonyliu
 *
 */
public class DocumentsWeb {

	private String documentID;
	private String documentType;
	private String documentName;
	private String documentLink;
	
	private String documentLength;
	private String documentSize;

	public String getDocumentLength() {
		return documentLength;
	}
	public void setDocumentLength(String documentLength) {
		this.documentLength = documentLength;
	}
	public String getDocumentSize() {
		return documentSize;
	}
	public void setDocumentSize(String documentSize) {
		this.documentSize = documentSize;
	}
	public String getDocumentID() {
		return documentID;
	}
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getDocumentLink() {
		return documentLink;
	}
	public void setDocumentLink(String documentLink) {
		this.documentLink = documentLink;
	}
}

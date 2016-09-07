package com.omt.webservice.meetings.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Document for meetings [done]
 * @author tonyliu
 *
 */
@JsonPropertyOrder({ "documentID", "documentType", "documentName", "documentLink", "documentAttributes"})
public class Documents {

	private String documentID;
	private String documentType;
	private String documentName;
	private String documentLink;
	
	private List<DocumentAttributes> documentAttributes;
	
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
	public List<DocumentAttributes> getDocumentAttributes() {
		return documentAttributes;
	}
	public void setDocumentAttributes(List<DocumentAttributes> documentAttributes) {
		this.documentAttributes = documentAttributes;
	}
}

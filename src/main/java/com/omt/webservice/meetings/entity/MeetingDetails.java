package com.omt.webservice.meetings.entity;

import java.util.List;

/**
 * Meeting details, current just including document, may expand in future;
 * @author tonyliu
 *
 */
public class MeetingDetails {

	private List<Documents> documents;

	public List<Documents> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Documents> documents) {
		this.documents = documents;
	}

}

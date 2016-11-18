package com.omt.cms.master.service.bo;

import java.sql.Timestamp;

import com.omt.cms.core.service.bo.base.CommonBO;

public class CompanyInstanceRegistryBO extends CommonBO{
	private static final long serialVersionUID = 1L;

	private Long cpId;
	private String instanceName;
	private String instanceURL;
	private String instanceTag;
	private Boolean whiteLabel;
	private String shareRegistry;
	private boolean activated;
	private Timestamp activatedOn;


	public CompanyInstanceRegistryBO(){ 
		super();
	}

	public Long getCpId() {
		return cpId;
	}

	public void setCpId(Long id) {
		this.cpId = id;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getInstanceURL() {
		return instanceURL;
	}

	public void setInstanceURL(String instanceURL) {
		this.instanceURL = instanceURL;
	}

	public String getInstanceTag() {
		return instanceTag;
	}

	public void setInstanceTag(String instanceTag) {
		this.instanceTag = instanceTag;
	}

	public Boolean getWhiteLabel() {
		return whiteLabel;
	}

	public void setWhiteLabel(Boolean whiteLabel) {
		this.whiteLabel = whiteLabel;
	}

	public String getShareRegistry() {
		return shareRegistry;
	}

	public void setShareRegistry(String shareRegistry) {
		this.shareRegistry = shareRegistry;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public Timestamp getActivatedOn() {
		return activatedOn;
	}

	public void setActivatedOn(Timestamp activatedOn) {
		this.activatedOn = activatedOn;
	}

}

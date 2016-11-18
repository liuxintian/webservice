package com.omt.cms.master.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import com.omt.cms.entity.BaseEntity;

@Entity
@Table(name = "instance_registries",
		uniqueConstraints = @UniqueConstraint(columnNames = "company_id")
		)	
		
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "instance_registries_seq")
public class CompanyInstanceRegistry extends BaseEntity{
	private static final long serialVersionUID = 1L;

 	@OneToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	@Column(name="instance_name", length=255, unique=true)
	@Size(max=255)
	private String instanceName;
	
	@Column(name="instance_url", length=500)
	@Size(max=500)
	private String instanceURL;
	
	@Column(name="instance_tag", length=500)
	@Size(max=500)
	private String instanceTag;
	
	@Column(name="is_whitelabel")
	private Boolean whiteLabel;
	
	@Column(name="share_registry", length=100)
	@Size(max=100)
	private String shareRegistry; 
	
	@Column(name="activated")
	private boolean activated;
	
	@Column(name="activated_on")
	private Timestamp activatedOn;
	
	public CompanyInstanceRegistry(){ 
		super();
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

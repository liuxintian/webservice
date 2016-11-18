package com.omt.cms.user.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.omt.cms.core.common.DateHelper;

@Entity
@Table(name = "mobile_users_logs")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "mobile_users_logs_seq")
public class MobileUserLog implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQGEN")
	@Column(name="id")
	protected Long id;

	@Column(name="created_on",nullable=true)
	protected Timestamp createdOn;

  	@Column(name="log_title", length=255)
	@Size(max=255)
	private String logTitle;
	
 	@Column(name="log_user_id", length=50)
	@Size(max=50)
	private String logUserId;
	
 	@Column(name="log_module", length=100)
	@Size(max=100)
	private String logModule;
	
 	@Column(name="log_appver", length=100)
	@Size(max=100)
 	private String logAppVer;

 	@Column(name="log_platform", length=100)
	@Size(max=100)
	private String logPlatform;
	
 	@Column(name="log_os_ver", length=100)
	@Size(max=100)
	private String logOSVer;
	
 	@Column(name="log_text", length=2555)
	@Size(max=2555)
	private String logText;

	public MobileUserLog(){
		this.createdOn = DateHelper.getCurTimestamp();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getLogTitle() {
		return logTitle;
	}

	public void setLogTitle(String logTitle) {
		this.logTitle = logTitle;
	}

	public String getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(String logUserId) {
		this.logUserId = logUserId;
	}

	public String getLogModule() {
		return logModule;
	}

	public void setLogModule(String logModule) {
		this.logModule = logModule;
	}

	public String getLogAppVer() {
		return logAppVer;
	}

	public void setLogAppVer(String logAppVer) {
		this.logAppVer = logAppVer;
	}

	public String getLogPlatform() {
		return logPlatform;
	}

	public void setLogPlatform(String logPlatform) {
		this.logPlatform = logPlatform;
	}

	public String getLogOSVer() {
		return logOSVer;
	}

	public void setLogOSVer(String logOSVer) {
		this.logOSVer = logOSVer;
	}

	public String getLogText() {
		return logText;
	}

	public void setLogText(String logText) {
		this.logText = logText;
	}

}

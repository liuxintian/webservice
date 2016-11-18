package com.omt.cms.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.omt.cms.entity.BaseEntity;

@Entity
@Table(name = "user_watchlists")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "user_watchlists_seq")
public class UserStockWatch extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(optional=false) 
	@JoinColumn(name="user_id", nullable=false, updatable=false)
	private User user;

	@Column(name="company_ticker", length=255)
	@Size(max=255)
	private String companyTicker;

	public UserStockWatch(){ 
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCompanyTicker() {
		return companyTicker;
	}

	public void setCompanyTicker(String companyTicker) {
		this.companyTicker = companyTicker;
	}
}

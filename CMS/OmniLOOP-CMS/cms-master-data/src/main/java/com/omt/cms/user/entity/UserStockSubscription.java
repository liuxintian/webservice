package com.omt.cms.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.omt.cms.entity.BaseEntity;

@Entity
@Table(name = "user_stock_subscriptions")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "user_stock_subscriptions_seq")
public class UserStockSubscription extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	@Column(name="user_id", nullable=false)
	private Long userId;

	@Column(name="company_ticker", length=255)
	@Size(max=255)
	private String companyTicker;

	public UserStockSubscription(){ 
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCompanyTicker() {
		return companyTicker;
	}

	public void setCompanyTicker(String companyTicker) {
		this.companyTicker = companyTicker;
	}
}

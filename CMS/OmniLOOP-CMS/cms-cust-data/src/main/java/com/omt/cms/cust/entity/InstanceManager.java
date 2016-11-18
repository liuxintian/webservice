package com.omt.cms.cust.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.omt.cms.entity.BaseManager;

@Entity
@Table(name = "instance_managers")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "instance_managers_seq")
public class InstanceManager extends BaseManager{

	private static final long serialVersionUID = 1L;

 	@ManyToOne(optional=false) 
	@JoinColumn(name="company_id", nullable=false, updatable=false)
	private CompanyInstance company;

	public InstanceManager(){
		super();
	}

	public CompanyInstance getCompany() {
		return company;
	}

	public void setCompany(CompanyInstance company) {
		this.company = company;
	}

}

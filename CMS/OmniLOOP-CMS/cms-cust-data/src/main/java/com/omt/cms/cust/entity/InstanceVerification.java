package com.omt.cms.cust.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.omt.cms.entity.Verification;

@Entity
@Table(name = "instance_verifications")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "instance_verification_seq")
public class InstanceVerification extends Verification{

	private static final long serialVersionUID = 1L;

	public InstanceVerification(){
		super();
	}
	
}

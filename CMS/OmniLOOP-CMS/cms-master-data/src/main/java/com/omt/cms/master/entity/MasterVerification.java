package com.omt.cms.master.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.omt.cms.entity.Verification;

@Entity
@Table(name = "master_verifications")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "master_verification_seq")
public class MasterVerification extends Verification{

	private static final long serialVersionUID = 1L;

	public MasterVerification(){
		super();
	}
	
}

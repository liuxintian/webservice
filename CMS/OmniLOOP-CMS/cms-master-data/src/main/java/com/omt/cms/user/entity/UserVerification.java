package com.omt.cms.user.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.omt.cms.entity.Verification;

@Entity
@Table(name = "user_verifications")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "user_verification_seq")
public class UserVerification extends Verification{

	private static final long serialVersionUID = 1L;

	public UserVerification(){
		super();
	}
	
}
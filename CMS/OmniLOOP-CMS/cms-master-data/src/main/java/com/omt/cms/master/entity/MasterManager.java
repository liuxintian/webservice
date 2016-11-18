package com.omt.cms.master.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.omt.cms.entity.BaseManager;

@Entity
@Table(name = "master_managers")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "master_managers_seq")
public class MasterManager extends BaseManager{

	private static final long serialVersionUID = 1L;

	public MasterManager(){
		super();
	}
	
}

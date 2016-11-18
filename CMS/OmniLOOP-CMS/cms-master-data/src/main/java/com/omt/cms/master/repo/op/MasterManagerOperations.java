package com.omt.cms.master.repo.op;

import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.master.entity.MasterManager;
import com.omt.cms.repo.op.base.CrudOperations;

@Component
public interface MasterManagerOperations extends CrudOperations<MasterManager>{

	public MasterManager findUser(String loginName);
	
	public boolean loginNameExists(String loginName);
	
	public List<MasterManager> listManagers();
	
}

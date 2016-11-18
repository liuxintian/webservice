package com.omt.cms.master.repo.op;

import org.springframework.stereotype.Component;

import com.omt.cms.master.entity.DataMenu;
import com.omt.cms.repo.op.base.CrudOperations;

@Component
public interface DataMenuOperations extends CrudOperations<DataMenu> {

	public DataMenu findByDataMenu(String dataMenu); 
}

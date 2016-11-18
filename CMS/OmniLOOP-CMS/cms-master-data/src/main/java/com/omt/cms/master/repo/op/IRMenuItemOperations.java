package com.omt.cms.master.repo.op;

import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.master.entity.IRMenuItem;
import com.omt.cms.repo.op.base.CrudOperations;

@Component
public interface IRMenuItemOperations extends CrudOperations<IRMenuItem> {

	public List<IRMenuItem> getActiveMenuItems();	
}

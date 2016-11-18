package com.omt.cms.master.repo.op;

import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.master.entity.IRMenu;
import com.omt.cms.repo.op.base.CrudOperations;

@Component
public interface IRMenuOperations extends CrudOperations<IRMenu> {
	
	public List<IRMenu> findByCompanyId(Long companyId);
	
	public IRMenu findByCompanyIdAndMenuId(Long companyId, Long menuId);
	
}

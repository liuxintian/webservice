/**
 * 
 */
package com.omt.cms.master.repo.op.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.omt.cms.master.entity.IRMenu;
import com.omt.cms.master.repo.IRMenuRepository;
import com.omt.cms.master.repo.op.IRMenuOperations;
import com.omt.cms.repo.op.base.BaseCrudOpImpl;

/**
 * @author muragesh
 *
 */
@Component
public class IRMenuOpImpl extends BaseCrudOpImpl<IRMenu> implements IRMenuOperations{

	@Autowired IRMenuRepository irmRepo;

	public IRMenuOpImpl() {
		this.entityType = IRMenu.class;
	}

	@Override
	public PagingAndSortingRepository<IRMenu, Long> getRepository() {
		return irmRepo;
	}

	@Override
	public List<IRMenu> findByCompanyId(Long companyId) {
		return irmRepo.findByCompanyId(companyId);
	}
	
	@Override
	public IRMenu findByCompanyIdAndMenuId(Long companyId, Long menuId) {
		return irmRepo.findByCompanyIdAndMenuId(companyId, menuId);
	}
}

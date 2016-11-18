/**
 * 
 */
package com.omt.cms.master.repo.op.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.omt.cms.master.entity.DataMenu;
import com.omt.cms.master.repo.DataMenuRepository;
import com.omt.cms.master.repo.op.DataMenuOperations;
import com.omt.cms.repo.op.base.BaseCrudOpImpl;

/**
 * @author muragesh
 *
 */
@Component
public class DataMenuOpImpl extends BaseCrudOpImpl<DataMenu> implements DataMenuOperations{

	@Autowired DataMenuRepository dmRepo;

	public DataMenuOpImpl() {
		this.entityType = DataMenu.class;
	}

	@Override
	public PagingAndSortingRepository<DataMenu, Long> getRepository() {
		return dmRepo;
	}

	@Override
	public  DataMenu  findByDataMenu(String dataMenu) {
		return dmRepo.findByDataMenu(dataMenu);
	}
}

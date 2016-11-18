package com.omt.cms.master.repo.op.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.master.entity.IRMenuItem;
import com.omt.cms.master.repo.IRMenuItemRepository;
import com.omt.cms.master.repo.op.IRMenuItemOperations;
import com.omt.cms.repo.op.base.BaseCrudOpImpl;

/**
 * @author muragesh
 *
 */
@Component
public class IRMenuItemOpImpl extends BaseCrudOpImpl<IRMenuItem> implements IRMenuItemOperations{

	@Autowired IRMenuItemRepository irmRepo;

	public IRMenuItemOpImpl() {
		this.entityType = IRMenuItem.class;
	}
	

	@Override
	public PagingAndSortingRepository<IRMenuItem, Long> getRepository() {
		return irmRepo;
	}

	@Override
	public List<IRMenuItem> getActiveMenuItems() {
		return irmRepo.findByStatus(RecordStatus.ACTIVE.getValue());
	}
}

package com.omt.cms.user.repo.op.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.user.entity.MobileUserLog;
import com.omt.cms.user.repo.MobileUserLogRepository;
import com.omt.cms.user.repo.op.MobileUserLogOperations;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public class MobileUserLogOpImpl implements MobileUserLogOperations {

	@Autowired private MobileUserLogRepository repo;
	
	@Override
	public MobileUserLog add(MobileUserLog entity) {
		return repo.save(entity);
	}

	@Override
	public List<MobileUserLog> filterByDates(Timestamp start, Timestamp end) {
		PageRequest pg = createPageRequest();
		return repo.findByCreatedOnBetweenOrderByCreatedOnAsc(start, end, pg);
	}
	
	protected PageRequest createPageRequest(){
		return new PageRequest(0, SystemCodes.PAGE_SIZE);
	}
}

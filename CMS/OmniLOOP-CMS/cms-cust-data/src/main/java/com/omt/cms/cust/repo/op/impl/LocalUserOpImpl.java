package com.omt.cms.cust.repo.op.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.entity.LocalUser;
import com.omt.cms.cust.repo.LocalUserRepository;
import com.omt.cms.cust.repo.op.LocalUserOperations;


/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public class LocalUserOpImpl implements LocalUserOperations{

	@Autowired LocalUserRepository luRepo;
	public static final String DEFAULT_SORT_ON = "userName";

	@Override
	public List<LocalUser> findByCompany(CompanyInstance company) {
		PageRequest pg = createPageRequest();
		return luRepo.findCompanySubscribers(company, true, pg);
	}

	@Override
	public LocalUser findByCompanyAndId(CompanyInstance company, Long id) {
		LocalUser kd = luRepo.findOne(id);
		if(kd!=null && company!=null){
			long cpId = company.getId().longValue();
			long cpid = kd.getCompany().getId().longValue();
			if(cpId==cpid){
				return kd;
			}
		}
		return null;
	}

	@Override
	public boolean loginNameExists(String loginName) {
		return luRepo.countByLoginName(loginName) > 0;
	}

	@Override
	public LocalUser findById(Long entityId) {
		return luRepo.findOne(entityId);
	}

	@Override
	public LocalUser add(LocalUser entity) {
		return luRepo.save(entity);
	}

	@Override
	public LocalUser update(LocalUser entity) {
		return luRepo.save(entity);
	}

	@Override
	public LocalUser delete(LocalUser entity) {
		return luRepo.save(entity);
	}

	@Override
	public List<LocalUser> findAll() {
		return (List<LocalUser>) luRepo.findAll();
	}

	@Override
	public LocalUser remove(LocalUser entity) {
		luRepo.delete(entity);
		return entity;
	}

	protected PageRequest createPageRequest(){
		return new PageRequest(0, SystemCodes.PAGE_SIZE);
	}

	protected PageRequest createPageRequest(int offset, int size, String sortField, int direction){
		Direction dir = Direction.ASC;
		if(direction == 2){
			dir = Direction.DESC;
		}
		Sort sort = new Sort(new Order(dir, sortField));
		return new PageRequest(offset, size, sort);
	}	
	
	@Override
	public List<LocalUser> findByFiltersAdmins(CompanyInstance company, FilterCriteriaBO filter) {
		List<LocalUser> entities = null;
		if(filter!=null){
			filter.assignDefaultsIfNotDefined(0, SystemCodes.PAGE_SIZE, DEFAULT_SORT_ON, 1);
			PageRequest page = createPageRequest(filter.offset, filter.size, filter.sortField, filter.sortOrder);
			if(StringUtils.isNotBlank(filter.term)){
				String queryTerm = StringUtils.lowerCase(filter.term);
				entities = luRepo.searchByCriteria(company, queryTerm, page);
			}else{
				entities = luRepo.findByCompany(company, page);
			}
		}else{
			PageRequest page = createPageRequest(0, SystemCodes.PAGE_SIZE, DEFAULT_SORT_ON, 1);
			entities = luRepo.findByCompany(company, page);
		}
		return entities;
	}

	@Override
	public Long countByFiltersAdmins(CompanyInstance company, FilterCriteriaBO filter) {
		Long total = null;
		if(filter!=null && StringUtils.isNotBlank(filter.term)){
			String queryTerm = StringUtils.lowerCase(filter.term);
			total = luRepo.countByCriteria(company, queryTerm);
		}else{
			total = luRepo.countByCompany(company);
		}
		return total;
	}	
	
	@Override
	public List<LocalUser> findByCompanyAndShareSubscriber(CompanyInstance company, boolean shareSubscriber) {
		PageRequest pg = createPageRequest();
		return luRepo.findByCompanyAndShareSubscriber(company, shareSubscriber, pg);
	}
	
	@Override
	public List<LocalUser> findByCompanyAndUserInvited(CompanyInstance company) {
		PageRequest pg = createPageRequest();
		return luRepo.findByCompanyAndUserInvited(company, pg);
	}
}

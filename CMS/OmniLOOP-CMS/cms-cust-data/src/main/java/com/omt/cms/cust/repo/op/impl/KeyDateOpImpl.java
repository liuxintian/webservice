package com.omt.cms.cust.repo.op.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.entity.KeyDate;
import com.omt.cms.cust.repo.KeyDateRepository;
import com.omt.cms.cust.repo.op.KeyDateOperations;
import com.omt.cms.repo.op.base.BaseCrudOpImpl;


/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public class KeyDateOpImpl extends BaseCrudOpImpl<KeyDate> implements KeyDateOperations{

	@Autowired KeyDateRepository kdRepo;
	public static final String DEFAULT_SORT_ON = "eventTitle";

	public KeyDateOpImpl() {
		this.entityType = KeyDate.class;
	}

	@Override
	public PagingAndSortingRepository<KeyDate, Long> getRepository() {
		return kdRepo;
	}
	
	@Override
	public List<KeyDate> findByCompany(CompanyInstance company) {
		return kdRepo.findByCompany(company);
	}
	
	@Override
	public KeyDate findByCompanyAndId(CompanyInstance company, Long id) {
		KeyDate kd = kdRepo.findOne(id);
		long cpId = company.getId().longValue();
		if(kd!=null){
			long exCPId = kd.getCompany().getId().longValue();
			if(cpId==exCPId){
				return kd;
			}
		}
		return null;
	}
	
	@Override
	public List<KeyDate> findByStatus(int status){
		PageRequest page = new PageRequest(0, 100);
		return kdRepo.findByStatus(status, page);
	}

	@Override
	public List<KeyDate> findByValidUntil(Timestamp vtd) {
		PageRequest page = new PageRequest(0, 100);
		return kdRepo.findByValidUntilAfter(vtd, page);
	}

	@Override
	public List<KeyDate> findByCompanyValidUntil(CompanyInstance company, Timestamp vtd) {
		PageRequest page = new PageRequest(0, 100);
		return kdRepo.findByCompanyAndValidUntilAfter(company, vtd, page);
	}

	@Override
	public List<KeyDate> findByCompanyStatus(CompanyInstance company, int status) {
		PageRequest page = new PageRequest(0, 100);
		return kdRepo.findByCompanyAndStatus(company, status, page);
	}

	@Override
	public List<KeyDate> findByStatusValidUntil(int status, Timestamp vtd) {
		PageRequest page = createPageRequest();
		return kdRepo.findByStatusAndValidUntilAfter(status, vtd, page);
	}

	@Override
	public List<KeyDate> findByCompanyStatusValidUntil(CompanyInstance company, int status, Timestamp vtd) {
		PageRequest page = createPageRequest();
		return kdRepo.findByCompanyAndStatusAndValidUntilAfter(company, status, vtd, page);
	}
	
	@Override
	public List<KeyDate> findByAllWithLimit() {
		PageRequest page = createPageRequest();
		return kdRepo.findAll(page).getContent();
	}

	@Override
	public List<KeyDate> findByFilters(FilterCriteriaBO filters) {
		List<KeyDate> entLst = null;
		if(filters!=null){
			filters.entityName = ENTITY_TABLE_NAME;
			String query = InstanceDataSearchCriteriaBuilder.build(filters);
			if(StringUtils.isNotBlank(query)){
				entLst = findByNativeQuery(query);
			}else{
				entLst = new ArrayList<>(1);
			}
		}
		return entLst;
	}

	@Override
	public List<KeyDate> findByFiltersAdmins(CompanyInstance company, FilterCriteriaBO filter) {
		List<KeyDate> entities = null;
		if(filter!=null){
			filter.assignDefaultsIfNotDefined(0, SystemCodes.PAGE_SIZE, DEFAULT_SORT_ON, 1);
			PageRequest page = createPageRequest(filter.offset, filter.size, filter.sortField, filter.sortOrder);
			if(StringUtils.isNotBlank(filter.term)){
				String queryTerm = StringUtils.lowerCase(filter.term);
				entities = kdRepo.searchByCriteria(company, queryTerm, page);
			}else{
				entities = kdRepo.findByCompany(company, page);
			}
		}else{
			PageRequest page = createPageRequest(0, SystemCodes.PAGE_SIZE, DEFAULT_SORT_ON, 1);
			entities = kdRepo.findByCompany(company, page);
		}
		return entities;
	}

	@Override
	public Long countByFiltersAdmins(CompanyInstance company, FilterCriteriaBO filter) {
		Long total = null;
		if(filter!=null && StringUtils.isNotBlank(filter.term)){
			String queryTerm = StringUtils.lowerCase(filter.term);
			total = kdRepo.countByCriteria(company, queryTerm);
		}else{
			total = kdRepo.countByCompany(company);
		}
		return total;
	}
}

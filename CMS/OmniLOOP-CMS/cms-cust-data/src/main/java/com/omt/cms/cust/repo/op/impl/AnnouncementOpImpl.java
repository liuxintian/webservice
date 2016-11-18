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
import com.omt.cms.cust.entity.Announcement;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.repo.AnnouncementRepository;
import com.omt.cms.cust.repo.op.AnnouncementOperations;
import com.omt.cms.repo.op.base.BaseCrudOpImpl;


/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public class AnnouncementOpImpl extends BaseCrudOpImpl<Announcement> implements AnnouncementOperations{

	@Autowired AnnouncementRepository annRepo;
	public static final String DEFAULT_SORT_ON = "documentTitle";
	
	public AnnouncementOpImpl() {
		this.entityType = Announcement.class;
	}

	@Override
	public PagingAndSortingRepository<Announcement, Long> getRepository() {
		return annRepo;
	}

	@Override
	public List<Announcement> findByCompany(CompanyInstance company) {
		return annRepo.findByCompany(company);
	}

	@Override
	public Announcement findByCompanyAndId(CompanyInstance company, Long id) {
		Announcement ann = annRepo.findOne(id);
		long cpId = company.getId().longValue();
		if(ann!=null){
			long exCPId = ann.getCompany().getId().longValue();
			if(cpId==exCPId){
				return ann;
			}
		}
		return null;
	}
	
	@Override
	public List<Announcement> findByStatus(int status){
		PageRequest page = createPageRequest();
		return annRepo.findByStatus(status, page);
	}

	@Override
	public List<Announcement> findByCompanyStatus(CompanyInstance company, int status) {
		PageRequest page = createPageRequest();
		return annRepo.findByCompanyAndStatus(company, status, page);
	}

	@Override
	public List<Announcement> findByCompanyValidUntil(CompanyInstance company, Timestamp vtd) {
		PageRequest page = createPageRequest();
		return annRepo.findByCompanyAndValidUntilAfter(company, vtd, page);
	}

	@Override
	public List<Announcement> findByCompanyStatusValidUntil(CompanyInstance company, int status, Timestamp vtd) {
		PageRequest page = createPageRequest();
		return annRepo.findByCompanyAndStatusAndValidUntilAfter(company, status, vtd, page);
	}

	@Override
	public List<Announcement> findByValidUntil(Timestamp vtd) {
		PageRequest page = createPageRequest();
		return annRepo.findByValidUntilAfter(vtd, page);
	}

	@Override
	public List<Announcement> findByStatusValidUntil(int status, Timestamp vtd) {
		PageRequest page = createPageRequest();
		return annRepo.findByStatusAndValidUntilAfter(status, vtd, page);
	}

	@Override
	public List<Announcement> findByAllWithLimit() {
		PageRequest page = createPageRequest();
		return annRepo.findAll(page).getContent();
	}
	
	@Override
	public List<Announcement> findByFilters(FilterCriteriaBO filters) {
		List<Announcement> entLst = null;
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
	public List<Announcement> findByFiltersAdmins(CompanyInstance company, FilterCriteriaBO filter) {
		List<Announcement> entities = null;
		if(filter!=null){
			filter.assignDefaultsIfNotDefined(0, SystemCodes.PAGE_SIZE, DEFAULT_SORT_ON, 1);
			PageRequest page = createPageRequest(filter.offset, filter.size, filter.sortField, filter.sortOrder);
			if(StringUtils.isNotBlank(filter.term)){
				entities = annRepo.searchByCriteria(company, filter.term, page);
			}else{
				entities = annRepo.findByCompany(company, page);
			}
		}else{
			PageRequest page = createPageRequest(0, SystemCodes.PAGE_SIZE, DEFAULT_SORT_ON, 1);
			entities = annRepo.findByCompany(company, page);
		}
		return entities;
	}
	
	@Override
	public Long countByFiltersAdmins(CompanyInstance company, FilterCriteriaBO filter) {
		Long total = null;
		if(filter!=null && StringUtils.isNotBlank(filter.term)){
			total = annRepo.countByCriteria(company, filter.term);
		}else{
			total = annRepo.countByCompany(company);
		}
		return total;
	}
}

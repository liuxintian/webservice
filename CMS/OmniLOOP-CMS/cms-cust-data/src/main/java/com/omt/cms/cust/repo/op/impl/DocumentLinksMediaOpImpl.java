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
import com.omt.cms.cust.repo.DocumentLinksMediaRepository;
import com.omt.cms.cust.repo.op.DocumentLinksMediaOperations;
import com.omt.cms.entity.DocumentLinksMedia;
import com.omt.cms.repo.op.base.BaseCrudOpImpl;

/**
 * 
 * @author Shiva Kalgudi
 *
 */
@Component
public class DocumentLinksMediaOpImpl extends BaseCrudOpImpl<DocumentLinksMedia> implements DocumentLinksMediaOperations{
	
	@Autowired DocumentLinksMediaRepository ceRepo;
	public static final String DEFAULT_SORT_ON = "docTitle";


	public DocumentLinksMediaOpImpl(){
		this.entityType = DocumentLinksMedia.class;
	}

	@Override
	public PagingAndSortingRepository<DocumentLinksMedia, Long> getRepository() {
		return ceRepo;
	}
	
	@Override
	public List<DocumentLinksMedia> findByCompany(CompanyInstance company) {
		return ceRepo.findByCompanyId(company.getId());
	}

	@Override
	public DocumentLinksMedia findByCompanyAndId(CompanyInstance company, Long id) {
		DocumentLinksMedia exec = ceRepo.findOne(id);
		long cpId = company.getId().longValue();
		if(exec!=null){
			long exCPId = exec.getCompanyId().longValue();
			if(cpId==exCPId){
				return exec;
			}
		}
		return null;
	}

	@Override
	public List<DocumentLinksMedia> findByCompanyValidUntil(CompanyInstance company, Timestamp vtd) {
		PageRequest pr = createPageRequest();
		return ceRepo.findByCompanyIdAndValidUntilAfter(company.getId(), vtd, pr);
	}

	@Override
	public List<DocumentLinksMedia> findByCompanyStatus(CompanyInstance company, int status) {
		PageRequest pr = createPageRequest();
		return ceRepo.findByCompanyIdAndStatus(company.getId(), status, pr);
	}

	@Override
	public List<DocumentLinksMedia> findByCompanyStatusValidUntil(CompanyInstance company, int status, Timestamp vtd) {
		PageRequest pr = createPageRequest();
		return ceRepo.findByCompanyIdAndStatusAndValidUntilAfter(company.getId(), status, vtd, pr);
	}

	@Override
	public List<DocumentLinksMedia> findByFilters(FilterCriteriaBO filters) {
		List<DocumentLinksMedia> entLst = null;
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
	public List<DocumentLinksMedia> findByFiltersAdmins(CompanyInstance company, FilterCriteriaBO filter) {
		List<DocumentLinksMedia> entities = null;
		if(filter!=null){
			filter.assignDefaultsIfNotDefined(0, SystemCodes.PAGE_SIZE, DEFAULT_SORT_ON, 1);
			PageRequest page = createPageRequest(filter.offset, filter.size, filter.sortField, filter.sortOrder);
			if(StringUtils.isNotBlank(filter.term)){
				String queryTerm = StringUtils.lowerCase(filter.term);
				entities = ceRepo.searchByCriteria(company.getId(), queryTerm, page);
			}else{
				entities = ceRepo.findByCompanyId(company.getId(), page);
			}
		}else{
			PageRequest page = createPageRequest(0, SystemCodes.PAGE_SIZE, DEFAULT_SORT_ON, 1);
			entities = ceRepo.findByCompanyId(company.getId(), page);
		}
		return entities;
	}

	@Override
	public Long countByFiltersAdmins(CompanyInstance company, FilterCriteriaBO filter) {
		Long total = null;
		if(filter!=null && StringUtils.isNotBlank(filter.term)){
			String queryTerm = StringUtils.lowerCase(filter.term);
			total = ceRepo.countByCriteria(company.getId(), queryTerm);
		}else{
			total = ceRepo.countByCompanyId(company.getId());
		}
		return total;
	}
	
}

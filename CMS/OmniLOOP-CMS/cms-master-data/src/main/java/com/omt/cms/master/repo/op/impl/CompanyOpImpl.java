/**	
 * 
 */
package com.omt.cms.master.repo.op.impl;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import org.apache.commons.lang3.StringUtils;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.master.entity.AsxRegistry;
import com.omt.cms.core.service.base.SearchCriteriaBuilder;
import com.omt.cms.master.entity.Company;
import com.omt.cms.master.repo.AsxRegistryRepository;
import com.omt.cms.master.repo.CompanyRepository;
import com.omt.cms.master.repo.op.CompanyOperations;
import com.omt.cms.repo.op.base.BaseCrudOpImpl;

/**
 * @author muragesh
 *
 */
@Component
public class CompanyOpImpl extends BaseCrudOpImpl<Company> implements CompanyOperations {

	@Autowired private CompanyRepository cpRepo;
	@Autowired private AsxRegistryRepository asxRegRepo;
	public static final String DEFAULT_SORT_ON = "companyName";

	public CompanyOpImpl() {
		this.entityType = Company.class;
	}

	public List<Company> findByStatus(int status) {
		return cpRepo.findByStatus(status);
	}

	@Override
	public PagingAndSortingRepository<Company, Long> getRepository() {
		return cpRepo;
	}

	@Override
	public Company findByCompanyTicker(String companyTicker) {
		List<Company> companies = cpRepo.findByCompanyTicker(companyTicker);
		Company result = null;
		if(companies!=null && !companies.isEmpty()){
			result = companies.get(0);
		}else{
			List<AsxRegistry> lst = asxRegRepo.findByCompanyTicker(companyTicker);
			if(lst!=null && !lst.isEmpty()){
				AsxRegistry reg = lst.get(0);
				result = new Company();
				BeanUtils.copyProperties(reg, result);
			}
		}
		return result;
	}

	@Override
	public boolean tickerNameExists(String companyTicker) {
		return cpRepo.countByCompanyTicker(companyTicker) > 0;
	}
	
	@Override
	public List<Company> getCompanyDetails(List<Long> cpIds) {
		return (List<Company>) cpRepo.findAll(cpIds);
	}
	
	@Override
	public List<Company> findByStatusAndCompanyType(int status, String companyType) {
		return cpRepo.findByStatusAndCompanyType(status, companyType);
	}
	
	@Override
	public List<AsxRegistry> getAsxRegList() {
		return (List<AsxRegistry>) asxRegRepo.findAll();
	}
	
	
	@Override
	public List<Company> findByFiltersAdmins(FilterCriteriaBO filter) {
		List<Company> companies = null;
		if(filter!=null){
			filter.assignDefaultsIfNotDefined(0, SystemCodes.PAGE_SIZE, DEFAULT_SORT_ON, 1);
			PageRequest page = createPageRequest(filter.offset, filter.size, filter.sortField, filter.sortOrder);
			if(StringUtils.isNotBlank(filter.term)){
				String queryTerm = StringUtils.lowerCase(filter.term);
				companies = cpRepo.searchByCriteria(queryTerm, page);
			}else{
				companies = cpRepo.findAll(page).getContent();
			}
		}else{
			PageRequest page = createPageRequest(0, SystemCodes.PAGE_SIZE, DEFAULT_SORT_ON, 1);
			companies = cpRepo.findAll(page).getContent();
		}
		return companies;
	}
	
	
	@Override
	public Long countByFiltersAdmins(FilterCriteriaBO filter) {
		Long total = null;
		if(filter!=null && StringUtils.isNotBlank(filter.term)){
			String queryTerm = StringUtils.lowerCase(filter.term);
			total = cpRepo.countByCriteria(queryTerm);
		}else{
			total = cpRepo.count();
		}
		return total;
	}
	
	@Override
	public List<Company> findByFilters(FilterCriteriaBO filters) {
		List<Company> registries = null;
		if(filters!=null){
			filters.entityName = ENTITY_TABLE_NAME;
			String query = SearchCriteriaBuilder.build(filters);
			if(StringUtils.isNotBlank(query)){
				registries = findByNativeQuery(query);
			}else{
				registries = new ArrayList<>(1);
			}
		}
		return registries;
	}	
}

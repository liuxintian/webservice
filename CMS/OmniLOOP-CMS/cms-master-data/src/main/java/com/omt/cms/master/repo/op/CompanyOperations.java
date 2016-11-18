/**
 * 
 */
package com.omt.cms.master.repo.op;

import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.master.entity.AsxRegistry;
import com.omt.cms.master.entity.Company;
import com.omt.cms.repo.op.base.CrudOperations;

/**
 * @author muragesh
 *
 */
@Component
public interface CompanyOperations extends CrudOperations<Company>{

	public static final String ENTITY_TABLE_NAME="companies";

	public List<Company> findByStatus(int status);

	public Company findByCompanyTicker(String companyTicker);

	public boolean tickerNameExists(String companyTicker);

	public List<Company> getCompanyDetails(List<Long> cpIds);
	
	public List<Company> findByStatusAndCompanyType(int status, String companyType);

	public List<AsxRegistry> getAsxRegList();

	public List<Company> findByFilters(FilterCriteriaBO filters);
	
	public List<Company> findByFiltersAdmins(FilterCriteriaBO filters);

	public Long countByFiltersAdmins(FilterCriteriaBO filters);

}

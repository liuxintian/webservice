package com.omt.cms.cust.repo.op;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.entity.DocumentLinksMedia;
import com.omt.cms.repo.op.base.CrudOperations;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public interface DocumentLinksMediaOperations extends CrudOperations<DocumentLinksMedia> {
	
	public static final String ENTITY_TABLE_NAME = "doc_media_links";

	public List<DocumentLinksMedia> findByCompany(CompanyInstance company);
	
	public DocumentLinksMedia findByCompanyAndId(CompanyInstance company, Long id);
	
	public List<DocumentLinksMedia> findByCompanyValidUntil(CompanyInstance company, Timestamp vtd);

	public List<DocumentLinksMedia> findByCompanyStatus(CompanyInstance company, int status);

	public List<DocumentLinksMedia> findByCompanyStatusValidUntil(CompanyInstance company, int status, Timestamp vtd);
	
	public List<DocumentLinksMedia> findByFilters(FilterCriteriaBO filters);

	public List<DocumentLinksMedia> findByFiltersAdmins(CompanyInstance company, FilterCriteriaBO filters);

	public Long countByFiltersAdmins(CompanyInstance company, FilterCriteriaBO filters);

}

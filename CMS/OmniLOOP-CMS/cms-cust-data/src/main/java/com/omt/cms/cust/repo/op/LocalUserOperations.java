package com.omt.cms.cust.repo.op;

import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.entity.LocalUser;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public interface LocalUserOperations{

	public List<LocalUser> findByCompany(CompanyInstance company);

	public LocalUser findByCompanyAndId(CompanyInstance company, Long id);
	
	public boolean loginNameExists(String loginName);

	public LocalUser findById(Long entityId);
	
	public LocalUser add(LocalUser entity);

	public LocalUser update(LocalUser entity);

	public LocalUser delete(LocalUser entity);
	
	public List<LocalUser> findAll();

	public LocalUser remove(LocalUser entity); //hard delete

	public List<LocalUser> findByFiltersAdmins(CompanyInstance company, FilterCriteriaBO filters);

	public Long countByFiltersAdmins(CompanyInstance company, FilterCriteriaBO filters);	

	public List<LocalUser> findByCompanyAndShareSubscriber(CompanyInstance company, boolean shareSubscriber);
	
	public List<LocalUser> findByCompanyAndUserInvited(CompanyInstance company);

}

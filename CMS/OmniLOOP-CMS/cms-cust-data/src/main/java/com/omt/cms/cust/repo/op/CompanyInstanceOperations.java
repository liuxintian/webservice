package com.omt.cms.cust.repo.op;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public interface CompanyInstanceOperations<CompanyInstance> {

	public CompanyInstance findById(Long entityId);
	
	public CompanyInstance add(CompanyInstance entity);

	public CompanyInstance update(CompanyInstance entity);

	public CompanyInstance delete(CompanyInstance entity);

}

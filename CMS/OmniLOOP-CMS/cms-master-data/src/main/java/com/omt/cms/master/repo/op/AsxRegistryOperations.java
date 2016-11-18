/**
 * 
 */
package com.omt.cms.master.repo.op;

import org.springframework.stereotype.Component;

import com.omt.cms.master.entity.AsxRegistry;
import com.omt.cms.repo.op.base.CrudOperations;

/**
 * @author muragesh
 *
 */
@Component
public interface AsxRegistryOperations extends CrudOperations<AsxRegistry>{

	public AsxRegistry findByCompanyTicker(String companyTicker);

	public boolean tickerNameExists(String companyTicker);

}

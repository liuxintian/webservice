/**
 * 
 */
package com.omt.cms.cust.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.cust.entity.CompanyInstance;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface CompanyInstanceRepository extends PagingAndSortingRepository<CompanyInstance, Long> {

	public List<CompanyInstance> findByValidUntilAfter(Timestamp validUntil, Pageable page);
}

/**
 * 
 */
package com.omt.cms.master.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.master.entity.Company;

/**
 * @author muragesh
 *
 */
@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {

	@Query("select comp from Company comp where comp.status <= ?1")
	public List<Company> findByStatus(int status);
	
	public List<Company> findByCompanyTicker(String companyTicker);
	
	public Long countByCompanyTicker(String companyTicker);
	
	public List<Company> findByValidUntilAfter(Timestamp validUntil, Pageable page);

	public List<Company> findByStatusAndCompanyType(int status, String companyType);
	
	@Query("SELECT e FROM #{#entityName} e WHERE "
	 		+ "LOWER(e.companyName) LIKE %?1% "
	 		+ "OR LOWER(e.companyTicker) LIKE %?1% "
	 		+ "OR LOWER(e.companyURL) LIKE %?1% "
	 		+ "OR LOWER(e.companyType) LIKE %?1% "
	 		+ "OR LOWER(e.subIndustry) LIKE %?1% "
	 		+ "OR LOWER(e.industry) LIKE %?1% "	 		
	 		+ "OR LOWER(e.sector) LIKE %?1% "	 		
	 		)
	public List<Company> searchByCriteria(String title, Pageable page);

	@Query("SELECT count(e) FROM #{#entityName} e WHERE "
	 		+ "LOWER(e.companyName) LIKE %?1% "
	 		+ "OR LOWER(e.companyTicker) LIKE %?1% "
	 		+ "OR LOWER(e.companyURL) LIKE %?1% "
	 		+ "OR LOWER(e.companyType) LIKE %?1% "
	 		+ "OR LOWER(e.subIndustry) LIKE %?1% "
	 		+ "OR LOWER(e.industry) LIKE %?1% "	 		
	 		+ "OR LOWER(e.sector) LIKE %?1% "	 		
			)
	public Long countByCriteria(String title);	


}

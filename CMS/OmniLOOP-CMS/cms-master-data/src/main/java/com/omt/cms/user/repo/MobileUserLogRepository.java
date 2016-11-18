/**
 * 
 */
package com.omt.cms.user.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.user.entity.MobileUserLog;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface MobileUserLogRepository extends PagingAndSortingRepository<MobileUserLog, Long> {
	
	public List<MobileUserLog> findByCreatedOnBetweenOrderByCreatedOnAsc(Timestamp start, Timestamp end, Pageable page);

}

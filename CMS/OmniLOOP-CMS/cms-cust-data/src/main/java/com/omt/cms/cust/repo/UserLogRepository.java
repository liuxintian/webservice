/**
 * 
 */
package com.omt.cms.cust.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.cust.entity.UserLog;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface UserLogRepository extends PagingAndSortingRepository<UserLog, Long> {

}

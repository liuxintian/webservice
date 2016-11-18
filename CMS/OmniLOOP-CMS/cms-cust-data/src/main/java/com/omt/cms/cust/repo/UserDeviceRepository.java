/**
 * 
 */
package com.omt.cms.cust.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.cust.entity.UserDevice;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface UserDeviceRepository extends PagingAndSortingRepository<UserDevice, Long> {

	List<UserDevice> findByUserId(long userId);

}

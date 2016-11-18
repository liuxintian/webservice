/**
 * 
 */
package com.omt.cms.user.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserDevice;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface UserDeviceRepository extends PagingAndSortingRepository<UserDevice, Long> {

	List<UserDevice> findByUser(User user);

}

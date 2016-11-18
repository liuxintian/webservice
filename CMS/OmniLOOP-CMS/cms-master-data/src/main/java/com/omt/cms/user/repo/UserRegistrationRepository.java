/**
 * 
 */
package com.omt.cms.user.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserRegistration;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface UserRegistrationRepository extends PagingAndSortingRepository<UserRegistration, Long> {
	
	List<UserRegistration> findByUser(User user);
}

/**
 * 
 */
package com.omt.cms.user.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserRegistered;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface UserRegisteredRepository extends PagingAndSortingRepository<UserRegistered, Long> {

	List<UserRegistered> findByUser(User user);

}

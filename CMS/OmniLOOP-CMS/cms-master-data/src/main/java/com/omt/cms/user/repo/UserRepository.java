package com.omt.cms.user.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.user.entity.User;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	public User findByLoginName(String loginName);
	
	public Long countByLoginName(String loginName);

}

/**
 * 
 */
package com.omt.cms.user.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserSetting;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface UserSettingRepository extends PagingAndSortingRepository<UserSetting, Long> {
	List<UserSetting> findByUser(User user);

}

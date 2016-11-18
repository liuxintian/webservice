/**
 * 
 */
package com.omt.cms.master.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.master.entity.DataMenu;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface DataMenuRepository extends PagingAndSortingRepository<DataMenu, Long> {

	public DataMenu findByDataMenu(String dataMenu);


}
package com.omt.cms.user.repo.op;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.user.entity.MobileUserLog;

/**
 * @author Shiva Kalgudi
 *
 */

@Component
public interface MobileUserLogOperations  {

	public MobileUserLog add(MobileUserLog entity);

	public List<MobileUserLog> filterByDates(Timestamp start, Timestamp end);
}

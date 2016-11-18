package com.omt.cms.cust.repo.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.omt.cms.core.common.DateHelper;
import com.omt.cms.cust.entity.Announcement;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.repo.AnnouncementRepository;

public class AnnouncementRepoTest extends RepoTestBase {

	@Autowired private AnnouncementRepository annRepo;
	
	//@Test
	public void testValingUntil(){
		Date today = new Date();
		CompanyInstance instance = new CompanyInstance();
		instance.setId(2L);
		Timestamp now = new Timestamp(DateHelper.addYear(today, 10).getTime());
		System.out.println(now);
		PageRequest page = new PageRequest(0, 10);
		System.out.println(annRepo.findByCompanyAndValidUntilAfter(instance, now, page).size());
	}

	//@Test
	public void testSearch(){
		List<Announcement> lst = annRepo.searchByTagRole(1L, "welcome & admin");
		for(Announcement ann : lst){
			System.err.println(ToStringBuilder.reflectionToString(ann));
		}
	}

	@Test
	public void testCriteria(){
		CompanyInstance instance = new CompanyInstance();
		instance.setId(1L);
		System.out.println(annRepo.searchByCriteria(instance, "welcome", null));
	}


}

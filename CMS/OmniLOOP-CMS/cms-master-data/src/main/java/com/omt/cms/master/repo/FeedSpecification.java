package com.omt.cms.master.repo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes.EntityColumnName;
import com.omt.cms.master.entity.Feed;

@Component
public class FeedSpecification {

	@PersistenceContext private EntityManager em;
	
	public void displayName(Integer status, Timestamp validUntil, String tagRole, String name){
		StringBuilder queryContent = new StringBuilder("SELECT * FROM " + name + " WHERE ");
		String operator = StringUtils.EMPTY;
		
		if(status!=null){
			queryContent.append(EntityColumnName.STATUS.getValue() + " = " + status.intValue());
			operator=" AND ";
		}

		if(validUntil!=null){
			queryContent.append( operator + EntityColumnName.VALID_UNTIL.getValue() + " > '" + validUntil + "'");
			if(StringUtils.isNotBlank(operator)){
				operator=" AND ";
			}
		}
		
		if(StringUtils.isNotBlank(tagRole)){
			queryContent.append( operator + EntityColumnName.TAG_ROLE.getValue() + " @@to_tsquery('");
			String[] tags= StringUtils.split(tagRole);
			for(int i=0; i<tags.length; i++){
				queryContent.append(tags[i]);
				if(i<tags.length-1){
					queryContent.append(" | ");
				}
			}
			queryContent.append("')");
		}
		System.out.println(queryContent);
		
		@SuppressWarnings("unchecked")
		List<Feed> feeds = em.createNativeQuery(queryContent.toString(), Feed.class).getResultList();
		System.out.println(feeds.size());
		
		
	}
}

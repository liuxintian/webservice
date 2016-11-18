package com.omt.cms.core.service.base;

import org.apache.commons.lang3.StringUtils;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.common.SystemCodes.EntityColumnName;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

public class SearchCriteriaBuilder {

	public static final String DEFAULT_SORT_BY = "last_updated";
	public static final String DEFAULT_SORT_ORDER = " DESC";
	
	public static String build(FilterCriteriaBO criteria){
		
		StringBuilder queryContent = new StringBuilder("SELECT * FROM " + criteria.entityName + " WHERE ");
		String operator = StringUtils.EMPTY;

		if(criteria.cpId!=null){
			queryContent.append(EntityColumnName.COMPANY_ID.getValue() + " = " + criteria.cpId.longValue());
			operator=" AND ";
		}

		if(criteria.status!=null && StringUtils.isNumeric(criteria.status)){
			queryContent.append(operator + EntityColumnName.STATUS.getValue() + " = " + criteria.status);
			operator=" AND ";
		}

		if(criteria.validUntil!=null){
			queryContent.append( operator + EntityColumnName.VALID_UNTIL.getValue() + " > '" + criteria.validUntil + "'");
			operator=" AND ";
		}
		
		if(criteria.tagRoleEmtpy!=null && criteria.tagRoleEmtpy){
			if(StringUtils.isNotBlank(operator) && criteria.tagRoles!=null && criteria.tagRoles.length > 0){
				queryContent.append(operator + "( (" + EntityColumnName.TAG_ROLE.getValue() + "='') IS NOT FALSE");
				operator=" OR ";
			}else if(StringUtils.isBlank(operator) && criteria.tagRoles!=null && criteria.tagRoles.length > 0){
				queryContent.append("( (" + EntityColumnName.TAG_ROLE.getValue() + "='') IS NOT FALSE");
				operator=" OR ";
			}else if(StringUtils.isNotBlank(operator)){
				queryContent.append(operator + " (" + EntityColumnName.TAG_ROLE.getValue() + "='') IS NOT FALSE");
			}else{
				queryContent.append(" (" + EntityColumnName.TAG_ROLE.getValue() + "='') IS NOT FALSE");
			}
		}
		
		if(criteria.tagRoles!=null && criteria.tagRoles.length > 0){
			if(StringUtils.isNotBlank(operator)){
				queryContent.append(operator);
			}
			queryContent.append(EntityColumnName.TAG_ROLE.getValue() + " @@to_tsquery('");
			addTagRoles(criteria, queryContent);
			if(criteria.tagRoleEmtpy!=null && criteria.tagRoleEmtpy){
				queryContent.append(" )");
			}
		}
		
		if(StringUtils.isNotBlank(criteria.sortField)){
			queryContent.append(" ORDER BY " + criteria.sortField);
		}else{
			queryContent.append(" ORDER BY " + DEFAULT_SORT_BY);
		}

		if(criteria.offset!=null){
			queryContent.append(" OFFSET " + criteria.offset);
		}
		
		int pageSize = SystemCodes.PAGE_SIZE;
		if(criteria.size!=null){
			pageSize = criteria.size;
		}
		queryContent.append(" LIMIT " + pageSize);

		return queryContent.toString();
	}

	private static void addTagRoles(FilterCriteriaBO criteria, StringBuilder queryContent) {
		for(int i=0; i<criteria.tagRoles.length; i++){
			queryContent.append(criteria.tagRoles[i]);
			if(i < criteria.tagRoles.length - 1){
				queryContent.append(" | ");
			}
		}
		queryContent.append("')");
	}
}

package com.omt.cms.cust.repo.op.impl;

import org.apache.commons.lang3.StringUtils;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.common.SystemCodes.EntityColumnName;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.cust.repo.op.AnnouncementOperations;
import com.omt.cms.cust.repo.op.DocumentLinksMediaOperations;
import com.omt.cms.cust.repo.op.KeyDateOperations;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

public class InstanceDataSearchCriteriaBuilder {

	public static final String DEFAULT_SORT_BY = "entity.last_updated";
	public static final String DEFAULT_SORT_ORDER = " DESC";
	
	public static String build(FilterCriteriaBO criteria){
		
		StringBuilder queryContent = new StringBuilder("SELECT " + selectEntityFields(criteria.entityName) + " FROM company_instances instance, " + criteria.entityName + " entity WHERE instance.status=20 AND instance.id=entity.company_id AND ");
		String operator = StringUtils.EMPTY;

		if(criteria.cpId!=null){
			queryContent.append(" entity." + EntityColumnName.COMPANY_ID.getValue() + " = " + criteria.cpId.longValue());
			operator=" AND ";
		}

		if(criteria.status!=null && StringUtils.isNumeric(criteria.status)){
			queryContent.append(operator + " entity." + EntityColumnName.STATUS.getValue() + " = " + criteria.status);
			operator=" AND ";
		}

		if(criteria.validUntil!=null){
			queryContent.append( operator + " entity." + EntityColumnName.VALID_UNTIL.getValue() + " > '" + criteria.validUntil + "'");
			operator=" AND ";
		}
		
		if(criteria.tagRoleEmtpy!=null && criteria.tagRoleEmtpy){
			if(StringUtils.isNotBlank(operator) && criteria.tagRoles!=null && criteria.tagRoles.length > 0){
				queryContent.append(operator + "( (" + " entity." + EntityColumnName.TAG_ROLE.getValue() + "='') IS NOT FALSE");
				operator=" OR ";
			}else if(StringUtils.isBlank(operator) && criteria.tagRoles!=null && criteria.tagRoles.length > 0){
				queryContent.append("( (" + " entity." + EntityColumnName.TAG_ROLE.getValue() + "='') IS NOT FALSE");
				operator=" OR ";
			}else if(StringUtils.isNotBlank(operator)){
				queryContent.append(operator + " (" + " entity." + EntityColumnName.TAG_ROLE.getValue() + "='') IS NOT FALSE");
			}else{
				queryContent.append(" (" + " entity." + EntityColumnName.TAG_ROLE.getValue() + "='') IS NOT FALSE");
			}
		}
		
		if(criteria.tagRoles!=null && criteria.tagRoles.length > 0){
			if(StringUtils.isNotBlank(operator)){
				queryContent.append(operator);
			}
			queryContent.append(" entity." + EntityColumnName.TAG_ROLE.getValue() + " @@to_tsquery('");
			addTagRoles(criteria, queryContent);
			if(criteria.tagRoleEmtpy!=null && criteria.tagRoleEmtpy){
				queryContent.append(" )");
			}
		}
		
		if(StringUtils.isNotBlank(criteria.sortField)){
			queryContent.append(" ORDER BY " + " entity." + criteria.sortField);
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
	
	private static String selectEntityFields(String entity){
		if(StringUtils.equals(entity, AnnouncementOperations.ENTITY_TABLE_NAME)){
			return "entity.id, "
					+ "entity.created_on, "
					+ "entity.last_updated, "
					+ "entity.notes, "
					+ "entity.company_id, "
					+ "entity.status, "
					+ "entity.tag_role, "
					+ "entity.valid_untill, "
					+ "entity.announcement_date, "
					+ "entity.announcement_type, "
					+ "entity.document_Link, "
					+ "entity.document_title, "
					+ "entity.is_price_sensitive ";	
		}else if(StringUtils.equals(entity, KeyDateOperations.ENTITY_TABLE_NAME)){
			return "entity.id, "
					+ "entity.created_on, "
					+ "entity.last_updated, "
					+ "entity.notes, "
					+ "entity.company_id, "
					+ "entity.status, "
					+ "entity.tag_role, "
					+ "entity.valid_untill, "
					+ "entity.event_date_time, "
					+ "entity.event_start_date_time, "
					+ "entity.event_end_date_time, "
					+ "entity.event_title, "
					+ "entity.event_subtitle, "
					+ "entity.event_location, "
					+ "entity.event_details, "
					+ "entity.key_date_type, "
					+ "entity.event_full_day, "
					+ "entity.event_range ";
		}else if(StringUtils.equals(entity, DocumentLinksMediaOperations.ENTITY_TABLE_NAME)){
			return "entity.id, "
					+ "entity.created_on, "
					+ "entity.last_updated, "
					+ "entity.notes, "
					+ "entity.company_id, "
					+ "entity.status, "
					+ "entity.tag_role, "
					+ "entity.valid_untill, "
					+ "entity.doc_title, "
					+ "entity.doc_desc, "
					+ "entity.doc_size, "
					+ "entity.doc_extension, "
					+ "entity.doc_thumbnail, "
					+ "entity.doc_url, "
					+ "entity.doc_note, "
					+ "entity.doc_type";
		}else{
			return "*";
		}
	}
}

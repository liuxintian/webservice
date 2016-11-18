package com.omt.cms.core.api.base;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.omt.cms.core.common.DateHelper;
import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.service.base.DataServiceException;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.base.ServiceResult;
import com.omt.cms.core.service.bo.base.CommonBO;
import com.omt.cms.core.service.bo.base.UserDetailsBO;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

public class BaseController {

	@ModelAttribute("serviceRequest")
	public ServiceRequest createServiceRequest(HttpServletRequest request) {
		ServiceRequest sreq = new ServiceRequest();
		sreq.addUser((UserDetailsBO)request.getAttribute(SystemCodes.USER_IDENTITY));
		request.setAttribute(SystemCodes.SERVICE_REQUEST, sreq);
		return sreq;
	}
	
	public <T> T getResultBO(ServiceResponse response, Class<T> type) {
		T bo = null;
		if(response.getServiceResult().isResult()){
    		bo = response.getResponseData(type);
    	}else{
    		throw new DataServiceException(response.getServiceResult());
    	}
		return bo;
	}

	public ServiceResult getServiceResult(ServiceResponse response) {
		if(response.getServiceResult().isResult()){
    		return response.getServiceResult();
    	}else{
    		throw new DataServiceException(response.getServiceResult());
    	}
	}

	public void handleResult(ServiceResponse response){
    	if(!response.getServiceResult().isResult()){
    		throw new DataServiceException(response.getServiceResult());
    	}
	}

	protected void addFilterValues(CommonBO bo, String status, Long validUntil){
		if(validUntil!=null){
			bo.setValidUntil(new Timestamp(validUntil.longValue()- SystemCodes.FILTER_TIME_LAG));	
		}else{
			bo.setValidUntil(DateHelper.getCurTimeAtStartOfDay());
		}

		if(StringUtils.isBlank(status) || !StringUtils.equals(status, SystemCodes.FILTER_STATUS_VALUE_ALL)){
			bo.setStatus(RecordStatus.ACTIVE.getValue());	
		}
	}

	protected FilterCriteriaBO createFilterValues(CommonBO bo, String status, Long validUntil, String tags, Boolean emptyTag){
		addFilterValues(bo, status, validUntil);
		FilterCriteriaBO criteria = new FilterCriteriaBO();
		if(bo.getStatus()!=null){
			criteria.status = bo.getStatus() + "";	
		}else{
			criteria.status = status;
		}
		criteria.validUntil = bo.getValidUntil();
		if(emptyTag!=null && emptyTag){
			criteria.tagRoleEmtpy = emptyTag;
		}
		if(StringUtils.isNotBlank(tags)){
			criteria.tagRole = tags;
			String[] tagRoles = StringUtils.split(tags, ",");
			criteria.tagRoles = tagRoles;
		}
		return criteria;
	}
	
	protected FilterCriteriaBO createFilterValues(CommonBO bo, String status, Long validUntil, String tags, Boolean emptyTag,String companyType){
		 FilterCriteriaBO criteria = createFilterValues(  bo,   status,   validUntil,   tags,   emptyTag);
		 if(StringUtils.isNotBlank(companyType))
			 criteria.companyType=companyType;
		 else
			 criteria.companyType=SystemCodes.DEFAULT_CP_TYPE;

		 return criteria;
	
	}


	protected FilterCriteriaBO createFilterValues(CommonBO bo, Long cpId, String status, Long validUntil, String tags, Boolean emptyTag){
		FilterCriteriaBO criteria = createFilterValues(bo, status, validUntil, tags, emptyTag);
		criteria.cpId = cpId;
		return criteria;
	}
	
	protected FilterCriteriaBO createFilterValues(CommonBO bo, Long cpId, String status, Long validUntil, 
			String tags, Boolean emptyTag, String sortField, Integer sortOrder){
		FilterCriteriaBO criteria = createFilterValues(bo, cpId, status, validUntil, tags, emptyTag);
		criteria.sortField = sortField;
		criteria.sortOrder = sortOrder;
		return criteria;
	}

}

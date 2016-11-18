package com.omt.cms.company.instance.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.cms.company.instance.service.CompanyInstanceProxyService;
import com.omt.cms.company.instance.service.common.BaseInstanceProxyService;
import com.omt.cms.core.common.DateHelper;
import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.master.entity.Company;
import com.omt.cms.master.entity.CompanyInstanceRegistry;
import com.omt.cms.master.repo.op.CompanyInstanceRegistryOperations;
import com.omt.cms.master.service.bo.CompanyInstanceRegistryBO;

@Service
public class CompanyInstanceProxyServiceImpl extends BaseInstanceProxyService implements CompanyInstanceProxyService {

	@Autowired CompanyInstanceRegistryOperations cirOps;
	
	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result=false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		ServiceResponse response=null;
		Long companyId = request.getRequestData(Long.class);
		if(companyId!=null){
			Company cp = cpOps.findById(companyId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(cp!=null){
				CompanyInstanceRegistry cir = cirOps.findByCompany(cp);
				CompanyInstance cpInst = new CompanyInstance();
				BeanUtils.copyProperties(cp, cpInst);
				cpInst.setCompanyName(cir.getInstanceName());
				response = add(cp, companyId, cpInst, "company-instances", CompanyInstance.class);
				if(response.getResponseData()!=null){
					cir.setActivatedOn(DateHelper.getCurTimestamp());
					cir.setActivated(true);
					CompanyInstanceRegistry citResult=cirOps.update(cir);
					CompanyInstanceRegistryBO cirBO=new CompanyInstanceRegistryBO();
					BeanUtils.copyProperties(citResult, cirBO);
					response.addResponseData(cirBO);
 				}
			}
		}
		if(response==null){
			response = new ServiceResponse(result, resultCode);
		}
		return response;
	}

	
	@Override
	public ServiceResponse deactivate(ServiceRequest request) {
		boolean result=false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		ServiceResponse response=null;
		Long companyId = request.getRequestData(Long.class);
		if(companyId!=null){
			Company cp = cpOps.findById(companyId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(cp!=null){
				response = instanceUpdate(cp, null, "company-instances/" + companyId + "/deactivate", CompanyInstance.class);
				if(response.getResponseData()!=null){
					cp.setStatus(RecordStatus.INACTIVE.getValue());
					cp.setLastUpdated(DateHelper.getCurTimestamp());
					cp = cpOps.update(cp);
					response.addResponseData(cp);
				}
			}
		}
		if(response==null){
			response = new ServiceResponse(result, resultCode);
		}
		return response;
	}

	
	@Override
	public ServiceResponse activate(ServiceRequest request) {
		boolean result=false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		ServiceResponse response=null;
		Long companyId = request.getRequestData(Long.class);
		if(companyId!=null){
			Company cp = cpOps.findById(companyId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(cp!=null){
				response = instanceUpdate(cp, null, "company-instances/" + companyId + "/activate", CompanyInstance.class);
				if(response.getResponseData()!=null){
					cp.setStatus(RecordStatus.ACTIVE.getValue());
					cp.setLastUpdated(DateHelper.getCurTimestamp());
					cp = cpOps.update(cp);
					response.addResponseData(cp);
				}
			}
		}
		if(response==null){
			response = new ServiceResponse(result, resultCode);
		}
		return response;
	}
	

}

package com.omt.cms.cust.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.cms.core.common.DateHelper;
import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.repo.op.CompanyInstanceOperations;
import com.omt.cms.cust.service.CompanyInstanceService;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Service
public class CompanyInstanceServiceImpl implements CompanyInstanceService {

	@Autowired private CompanyInstanceOperations<CompanyInstance> instanceOps;
	
	@Override
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyInstance company = null;
		Long cid = request.getRequestData(Long.class);
		if (cid != null) {
			company = instanceOps.findById(cid);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				result = true;
				resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(company);
		return response;
	}

	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyInstance company = null;
		CompanyInstance ciReq = request.getRequestData(CompanyInstance.class);
		if (ciReq != null && ciReq.getId()!=null) {
			ciReq.setStatus(RecordStatus.ACTIVE.getValue());
			ciReq.setDefaultValidUntil();
			company = instanceOps.add(ciReq);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
			if(company!=null){
				result = true;
				resultCode = ServiceResultCodes.RECORD_SAVED.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(company);
		return response;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyInstance company = null;
		CompanyInstance ciReq = request.getRequestData(CompanyInstance.class);
		if (ciReq != null && ciReq.getId()!=null) {
			company = instanceOps.findById(ciReq.getId());
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				company.setCompanyName(ciReq.getCompanyName());
				company.setLastUpdated(DateHelper.getCurTimestamp());
				company = instanceOps.update(company);
				result = true;
				resultCode = ServiceResultCodes.RECORD_SAVED.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(company);
		return response;
	}

	@Override
	public ServiceResponse delete(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyInstance company = null;
		Long cid = request.getRequestData(Long.class);
		if (cid != null) {
			company = instanceOps.findById(cid);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				company.setStatus(RecordStatus.DELETED.getValue());
				company.setLastUpdated(DateHelper.getCurTimestamp());
				company = instanceOps.update(company);
				result = true;
				resultCode = ServiceResultCodes.RECORD_SAVED.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(company);
		return response;
	}

	@Override
	public ServiceResponse activate(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyInstance company = null;
		Long cid = request.getRequestData(Long.class);
		if (cid != null) {
			company = instanceOps.findById(cid);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				company.setStatus(RecordStatus.ACTIVE.getValue());
				company.setLastUpdated(DateHelper.getCurTimestamp());
				company = instanceOps.update(company);
				result = true;
				resultCode = ServiceResultCodes.RECORD_SAVED.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(company);
		return response;
	}
	
	@Override
	public ServiceResponse deactivate(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyInstance company = null;
		Long cid = request.getRequestData(Long.class);
		if (cid != null) {
			company = instanceOps.findById(cid);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				company.setStatus(RecordStatus.INACTIVE.getValue());
				company.setLastUpdated(DateHelper.getCurTimestamp());
				company = instanceOps.update(company);
				result = true;
				resultCode = ServiceResultCodes.RECORD_SAVED.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(company);
		return response;
	}
}

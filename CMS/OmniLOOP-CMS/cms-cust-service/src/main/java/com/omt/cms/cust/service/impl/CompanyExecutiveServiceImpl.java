package com.omt.cms.cust.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.CompanyExecutiveBO;
import com.omt.cms.cust.entity.CompanyExecutive;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.repo.op.CompanyExecutiveOperations;
import com.omt.cms.cust.repo.op.CompanyInstanceOperations;
import com.omt.cms.cust.service.CompanyExecutiveService;
import com.omt.cms.cust.service.bo.mapper.CompanyExecutiveMapper;

@Service
public class CompanyExecutiveServiceImpl implements CompanyExecutiveService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyExecutiveServiceImpl.class);
	@Autowired CompanyInstanceOperations<CompanyInstance> ciOps;
	@Autowired CompanyExecutiveOperations ceOps;
	@Autowired CompanyExecutiveMapper mapper;

	@Override
	public ServiceResponse readAll(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		Long companyId = request.getRequestData(Long.class);
		List<CompanyExecutiveBO> resExList = new ArrayList<>();
		if (companyId != null) {
			CompanyInstance company = ciOps.findById(companyId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
				if(company.isActive()){
					List<CompanyExecutive> exList = ceOps.findByCompany(company);
					convertToBOList(resExList, exList);
					result = true;
					resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resExList);
		return response;
	}

	private void convertToBOList(List<CompanyExecutiveBO> resExList, List<CompanyExecutive> exList) {
		for(CompanyExecutive exec:exList){
			CompanyExecutiveBO exBO = mapper.fromEntityToBO(exec);
			resExList.add(exBO);
		}
	}

	@Override
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyExecutiveBO reqExec = request.getRequestData(CompanyExecutiveBO.class);
		CompanyExecutiveBO resExec = null;
		if (reqExec!=null && reqExec.getCompanyId()!=null && reqExec.getId()!=null) {
			Long execId = reqExec.getId();
			Long companyId = reqExec.getCompanyId();
			CompanyInstance company = ciOps.findById(companyId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
				if(company.isActive()){
					CompanyExecutive exec = ceOps.findByCompanyAndId(company, execId);
					resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
					if(exec!=null){
						resExec=mapper.fromEntityToBO(exec);
						result = true;
						resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
					}
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resExec);
		return response;
	}

	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyExecutiveBO reqExec = request.getRequestData(CompanyExecutiveBO.class);
		CompanyExecutiveBO resExec = null;
		if (reqExec!=null && reqExec.getCompanyId()!=null) {
			Long companyId = reqExec.getCompanyId();
			CompanyInstance company = ciOps.findById(companyId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
				if(company.isActive()){
					reqExec.setDefaultValidUntil();
					CompanyExecutive exec = mapper.createNewEntityFromBO(reqExec, null);
					exec.setCompany(company);
					if(exec!=null){
						ceOps.add(exec);
						resExec=mapper.fromEntityToBO(exec);
						result = true;
						resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
					}
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resExec);
		return response;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyExecutiveBO reqExec = request.getRequestData(CompanyExecutiveBO.class);
		CompanyExecutiveBO resExec = null;
		if (reqExec!=null && reqExec.getCompanyId()!=null && reqExec.getId()!=null) {
			Long execId = reqExec.getId();
			Long companyId = reqExec.getCompanyId();
			CompanyInstance company = ciOps.findById(companyId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
				if(company.isActive()){
					CompanyExecutive exec = ceOps.findByCompanyAndId(company, execId);
					resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
					if(exec!=null){
						mapper.copyUpdatedBOToEntity(reqExec, exec, null);
						ceOps.update(exec);
						resExec = mapper.fromEntityToBO(exec);
						result = true;
						resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
					}
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resExec);
		return response;
	}

	@Override
	public ServiceResponse readAllWithFilters(ServiceRequest request){
		boolean result = false;
		String resultMsg = null;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyExecutiveBO reqBO = request.getRequestData(CompanyExecutiveBO.class);
		List<CompanyExecutiveBO> annList = new ArrayList<>(1);
		try{
			if (reqBO!=null && reqBO.getCompanyId()!=null) {
				Long companyId = reqBO.getCompanyId();
				Timestamp validUntil = reqBO.getValidUntil();
				Integer status = reqBO.getStatus(); 
				CompanyInstance company = ciOps.findById(companyId);
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(company!=null){
					resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
					if(company.isActive()){
						List<CompanyExecutive> exList = null;
						if(validUntil==null && status==null){
							exList = ceOps.findByCompany(company);	
						}else if(validUntil!=null && status==null){
							exList = ceOps.findByCompanyValidUntil(company, validUntil);
						}else if(validUntil==null && status!=null){
							exList = ceOps.findByCompanyStatus(company, status);
						}else if(validUntil!=null && status!=null){
							exList = ceOps.findByCompanyStatusValidUntil(company, status, validUntil);
						}
						convertToBOList(annList, exList);
						result = true;
						resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
					}
				}
			}
		}catch (Exception e) {
			LOGGER.error("Error occurred: {}", e);
			resultMsg = "Error details:" + e.getMessage();
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(annList);
		return response;
	}

	@Override
	public ServiceResponse delete(ServiceRequest request) {
		
		return null;
	}

}

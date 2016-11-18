package com.omt.cms.cust.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.KeyDateBO;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.entity.KeyDate;
import com.omt.cms.cust.repo.op.CompanyInstanceOperations;
import com.omt.cms.cust.repo.op.KeyDateOperations;
import com.omt.cms.cust.service.KeyDateService;
import com.omt.cms.cust.service.bo.mapper.KeyDateMapper;

@Service
public class KeyDateServiceImpl implements KeyDateService {
	private static final Logger LOGGER = LoggerFactory.getLogger(KeyDateServiceImpl.class);
	@Autowired private CompanyInstanceOperations<CompanyInstance> ciOps;
	@Autowired private KeyDateOperations kdOps;
	@Autowired private KeyDateMapper mapper;


	@Override
	public ServiceResponse readAll(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		List<KeyDateBO> annList = new ArrayList<>();
		if (reqBO != null && reqBO.cpId!=null) {
			CompanyInstance company = ciOps.findById(reqBO.cpId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
				if(company.isActive()){
					List<KeyDate> exList = kdOps.findByFiltersAdmins(company, reqBO);
					Long total = kdOps.countByFiltersAdmins(company, reqBO);
					convertToBOList(annList, exList, total);
					result = true;
					resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(annList);
		return response;
	}


	private void convertToBOList(List<KeyDateBO> resExList, List<KeyDate> exList) {
		for(KeyDate exec:exList){
			KeyDateBO exBO = mapper.fromEntityToBO(exec);
			resExList.add(exBO);
		}
	}

	private void convertToBOList(List<KeyDateBO> annList, List<KeyDate> exList, Long total) {
		boolean addTotal = true;
		for(KeyDate ann:exList){
			KeyDateBO exBO = mapper.fromEntityToBO(ann);
			if(addTotal){
				exBO.setTotal(total);
				addTotal = false;
			}
			annList.add(exBO);
		}
	}

	@Override
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		KeyDateBO reqExec = request.getRequestData(KeyDateBO.class);
		KeyDateBO resExec = null;
		if (reqExec!=null && reqExec.getCompanyId()!=null && reqExec.getId()!=null) {
			Long execId = reqExec.getId();
			Long companyId = reqExec.getCompanyId();
			CompanyInstance company = ciOps.findById(companyId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
				if(company.isActive()){
					KeyDate exec = kdOps.findByCompanyAndId(company, execId);
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
		KeyDateBO reqExec = request.getRequestData(KeyDateBO.class);
		KeyDateBO resExec = null;
		if (reqExec!=null && reqExec.getCompanyId()!=null) {
			Long companyId = reqExec.getCompanyId();
			CompanyInstance company = ciOps.findById(companyId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
				if(company.isActive()){
					reqExec.setDefaultValidUntil();
					KeyDate exec = mapper.createNewEntityFromBO(reqExec, null);
					exec.setCompany(company);
					if(exec!=null){
						kdOps.add(exec);
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
		KeyDateBO reqExec = request.getRequestData(KeyDateBO.class);
		KeyDateBO resExec = null;
		if (reqExec!=null && reqExec.getCompanyId()!=null && reqExec.getId()!=null) {
			Long execId = reqExec.getId();
			Long companyId = reqExec.getCompanyId();
			CompanyInstance company = ciOps.findById(companyId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
				if(company.isActive()){
					KeyDate exec = kdOps.findByCompanyAndId(company, execId);
					resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
					if(exec!=null){
						mapper.copyUpdatedBOToEntity(reqExec, exec, null);
						kdOps.update(exec);
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
	public ServiceResponse readAllInstancesData(ServiceRequest request) {
		boolean result = false;
		String resultMsg = null;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		List<KeyDateBO> kdList = new ArrayList<>(1);
		try{
			List<KeyDate> exList = kdOps.findByFilters(reqBO);
			convertToBOList(kdList, exList);
			result = true;
			resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
		}catch (Exception e) {
			LOGGER.error("Error occurred: {}", e);
			resultMsg = "Error details:" + e.getMessage();
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(kdList);
		return response;
	}
	
	@Override
	public ServiceResponse readAllWithFilters(ServiceRequest request){
		boolean result = false;
		String resultMsg = null;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		List<KeyDateBO> kdList = new ArrayList<>(1);
		try{
			if (reqBO!=null && reqBO.cpId!=null) {
				Long companyId = reqBO.cpId;
				CompanyInstance company = ciOps.findById(companyId);
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(company!=null){
					resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
					if(company.isActive()){
						List<KeyDate> exList = kdOps.findByFilters(reqBO);
						convertToBOList(kdList, exList);
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
		response.addResponseData(kdList);
		return response;
	}
	
	@Override
	public ServiceResponse delete(ServiceRequest request) {return null; }

}

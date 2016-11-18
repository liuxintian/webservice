package com.omt.cms.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.master.entity.ShareRegistry;
import com.omt.cms.master.repo.op.ShareRegistryOperations;
import com.omt.cms.master.service.ShareRegistryService;
import com.omt.cms.master.service.bo.ShareRegistryBO;
import com.omt.cms.master.service.bo.mapper.ShareRegistryMapper;

@Service
public class ShareRegistryServiceImpl implements ShareRegistryService{
	private static final Logger LOGGER = LoggerFactory.getLogger(ShareRegistryServiceImpl.class);

	@Autowired private ShareRegistryOperations srOps;
	@Autowired private ShareRegistryMapper mapper;

	@Override
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		ShareRegistryBO resultSR = null;
		Long srId = request.getRequestData(Long.class);
		if (srId!=null) {
			ShareRegistry srEnt = srOps.findById(srId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(srEnt!=null){
				resultSR =  mapper.fromEntityToBO(srEnt);
				result = true;
				resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultSR);
		return response;
	}
	
	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		ShareRegistryBO resultSR = null;
		ShareRegistryBO reqSR = request.getRequestData(ShareRegistryBO.class);
		if (reqSR != null) {
			reqSR.setDefaultValidUntil();
			ShareRegistry srEnt = mapper.createNewEntityFromBO(reqSR, null);
			srEnt.setStatus(RecordStatus.ACTIVE.getValue());
			srOps.add(srEnt);
			resultSR =  mapper.fromEntityToBO(srEnt);
			result = true;
			resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultSR);
		return response;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		ShareRegistryBO resultSR = null;
		ShareRegistryBO reqSR = request.getRequestData(ShareRegistryBO.class);
		if (reqSR!=null) {
			ShareRegistry srEnt = srOps.findById(reqSR.getId());
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(srEnt!=null){
				srEnt = mapper.copyUpdatedBOToEntity(reqSR, srEnt, null);
				srEnt = srOps.update(srEnt);
				resultSR =  mapper.fromEntityToBO(srEnt);
				result = true;
				resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultSR);
		return response;
	}

	@Override
	public ServiceResponse delete(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		ShareRegistryBO resultSR = null;
		ShareRegistryBO reqSR = request.getRequestData(ShareRegistryBO.class);
		if (reqSR!=null) {
			ShareRegistry srEnt = srOps.findById(reqSR.getId());
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(srEnt!=null){
				srEnt.setStatus(SystemCodes.RecordStatus.DELETED.getValue());;
				srEnt = srOps.update(srEnt);
				resultSR =  mapper.fromEntityToBO(srEnt);
				result = true;
				resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultSR);
		return response;
	}


	@Override
	public ServiceResponse readAll(ServiceRequest request) {
		boolean result = true;
		String resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		List<ShareRegistryBO> srBOList = new ArrayList<ShareRegistryBO>();
		try {
			List<ShareRegistry> registryResult = srOps.findByFiltersAdmins(reqBO);
			Long total = srOps.countByFiltersAdmins(reqBO);
			convertToBOList(srBOList, registryResult, total);
		} catch (Exception e) {
			result = false;
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(srBOList);		
		return response;
	}

	
	private void convertToBOList(List<ShareRegistryBO> registryBOList, List<ShareRegistry> registryList) {
		for (ShareRegistry registryEnt : registryList) {
			ShareRegistryBO registryBO = mapper.fromEntityToBO(registryEnt);
			registryBOList.add(registryBO);
		}
	}
	
	
	private void convertToBOList(List<ShareRegistryBO> registryBOList, List<ShareRegistry> registryList, Long total) {
		boolean addTotal = true;
		for (ShareRegistry registryEnt : registryList) {
			ShareRegistryBO registryBO = mapper.fromEntityToBO(registryEnt);
			if(addTotal){
				registryBO.setTotal(total);
				addTotal = false;
			}
			registryBOList.add(registryBO);
		}
	}

	
	@Override
	public ServiceResponse readAllWithFilters(ServiceRequest request){
		boolean result = false;
		String resultMsg = null;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		List<ShareRegistryBO> respList = new ArrayList<>(1);
		try{
			if (reqBO!=null) {
				List<ShareRegistry> resList = srOps.findByFilters(reqBO);
				convertToBOList(respList, resList);
				result = true;
				resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
			}
		}catch (Exception e) {
			LOGGER.error("Error occurred: {}", e);
			resultMsg = "Error details:" + e.getMessage();
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(respList);
		return response;
	}
	
}
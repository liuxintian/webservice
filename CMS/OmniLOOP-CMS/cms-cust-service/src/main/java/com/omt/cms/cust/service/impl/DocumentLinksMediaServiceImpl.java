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
import com.omt.cms.core.service.bo.base.DocumentLinksMediaBO;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.repo.op.CompanyInstanceOperations;
import com.omt.cms.cust.repo.op.DocumentLinksMediaOperations;
import com.omt.cms.cust.service.DocumentLinksMediaService;
import com.omt.cms.cust.service.bo.mapper.DocumentLinksMediaMapper;
import com.omt.cms.entity.DocumentLinksMedia;

@Service
public class DocumentLinksMediaServiceImpl implements DocumentLinksMediaService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyExecutiveServiceImpl.class);
	@Autowired CompanyInstanceOperations<CompanyInstance> ciOps;
	@Autowired DocumentLinksMediaOperations dlmOps;
	@Autowired DocumentLinksMediaMapper mapper;

	@Override
	public ServiceResponse readAll(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		List<DocumentLinksMediaBO> resDlmList = new ArrayList<>(1);
		if (reqBO != null && reqBO.cpId!=null) {
			CompanyInstance company = ciOps.findById(reqBO.cpId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(company!=null){
					resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
					if(company.isActive()){
						//List<DocumentLinksMedia> exList = dlmOps.findByCompany(company);
						List<DocumentLinksMedia> exList = dlmOps.findByFiltersAdmins(company, reqBO);
						Long total = dlmOps.countByFiltersAdmins(company, reqBO);
						convertToBOList(resDlmList, exList, total);
						result = true;
						resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
					}
				}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resDlmList);
		return response;
	}

	private void convertToBOList(List<DocumentLinksMediaBO> resDlmList, List<DocumentLinksMedia> exList) {
		for(DocumentLinksMedia dlm:exList){
			DocumentLinksMediaBO exBO = mapper.fromEntityToBO(dlm);
			resDlmList.add(exBO);
		}
	}

	private void convertToBOList(List<DocumentLinksMediaBO> annList, List<DocumentLinksMedia> exList, Long total) {
		boolean addTotal = true;
		for(DocumentLinksMedia ann:exList){
			DocumentLinksMediaBO exBO = mapper.fromEntityToBO(ann);
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
		DocumentLinksMediaBO reqDlm = request.getRequestData(DocumentLinksMediaBO.class);
		DocumentLinksMediaBO resDlm = null;
		if (reqDlm!=null && reqDlm.getCompanyId()!=null && reqDlm.getId()!=null) {
			Long dlmId = reqDlm.getId();
			Long companyId = reqDlm.getCompanyId();
			CompanyInstance company = ciOps.findById(companyId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
				if(company.isActive()){
					DocumentLinksMedia dlm = dlmOps.findByCompanyAndId(company, dlmId);
					resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
					if(dlm!=null){
						resDlm=mapper.fromEntityToBO(dlm);
						result = true;
						resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
					}
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resDlm);
		return response;
	}

	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		DocumentLinksMediaBO reqDlm = request.getRequestData(DocumentLinksMediaBO.class);
		DocumentLinksMediaBO resDlm = null;
		if (reqDlm!=null && reqDlm.getCompanyId()!=null) {
			Long companyId = reqDlm.getCompanyId();
			CompanyInstance company = ciOps.findById(companyId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
				if(company.isActive()){
					reqDlm.setDefaultValidUntil();
					DocumentLinksMedia dlm = mapper.createNewEntityFromBO(reqDlm, null);
					if(dlm!=null){
						dlmOps.add(dlm);
						resDlm=mapper.fromEntityToBO(dlm);
						result = true;
						resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
					}
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resDlm);
		return response;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		DocumentLinksMediaBO reqDlm = request.getRequestData(DocumentLinksMediaBO.class);
		DocumentLinksMediaBO resDlm = null;
		if (reqDlm!=null && reqDlm.getCompanyId()!=null && reqDlm.getId()!=null) {
			Long dlmId = reqDlm.getId();
			Long companyId = reqDlm.getCompanyId();
			CompanyInstance company = ciOps.findById(companyId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
				if(company.isActive()){
					DocumentLinksMedia dlm = dlmOps.findByCompanyAndId(company, dlmId);
					resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
					if(dlm!=null){
						mapper.copyUpdatedBOToEntity(reqDlm, dlm, null);
						dlmOps.update(dlm);
						resDlm = mapper.fromEntityToBO(dlm);
						result = true;
						resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
					}
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resDlm);
		return response;
	}

	@Override
	public ServiceResponse readAllWithFilters(ServiceRequest request){
		boolean result = false;
		String resultMsg = null;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		List<DocumentLinksMediaBO> annList = new ArrayList<>(1);
		try{
			if (reqBO!=null && reqBO.cpId!=null) {
				Long companyId = reqBO.cpId;
				CompanyInstance company = ciOps.findById(companyId);
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(company!=null){
					resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
					if(company.isActive()){
						List<DocumentLinksMedia> exList = dlmOps.findByFilters(reqBO);
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

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
import com.omt.cms.core.service.bo.base.AnnouncementBO;
import com.omt.cms.cust.entity.Announcement;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.repo.op.AnnouncementOperations;
import com.omt.cms.cust.repo.op.CompanyInstanceOperations;
import com.omt.cms.cust.service.AnnouncementService;
import com.omt.cms.cust.service.bo.mapper.AnnouncementMapper;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AnnouncementServiceImpl.class);
	@Autowired CompanyInstanceOperations<CompanyInstance> ciOps;
	@Autowired AnnouncementOperations annOps;
	@Autowired AnnouncementMapper mapper;

	@Override
	public ServiceResponse readAll(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		List<AnnouncementBO> annList = new ArrayList<>();
		if (reqBO != null && reqBO.cpId!=null) {
			CompanyInstance company = ciOps.findById(reqBO.cpId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
				if(company.isActive()){
					List<Announcement> exList = annOps.findByFiltersAdmins(company, reqBO);
					Long total = annOps.countByFiltersAdmins(company, reqBO);
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

	private void convertToBOList(List<AnnouncementBO> annList, List<Announcement> exList) {
		for(Announcement ann:exList){
			AnnouncementBO exBO = mapper.fromEntityToBO(ann);
			annList.add(exBO);
		}
	}

	private void convertToBOList(List<AnnouncementBO> annList, List<Announcement> exList, Long total) {
		boolean addTotal = true;
		for(Announcement ann:exList){
			AnnouncementBO exBO = mapper.fromEntityToBO(ann);
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
		AnnouncementBO reqAnn = request.getRequestData(AnnouncementBO.class);
		AnnouncementBO resAnn = null;
		if (reqAnn!=null && reqAnn.getCompanyId()!=null && reqAnn.getId()!=null) {
			Long annId = reqAnn.getId();
			Long companyId = reqAnn.getCompanyId();
			CompanyInstance company = ciOps.findById(companyId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
				if(company.isActive()){
					Announcement ann = annOps.findByCompanyAndId(company, annId);
					resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
					if(ann!=null){
						resAnn=mapper.fromEntityToBO(ann);
						result = true;
						resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
					}
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resAnn);
		return response;
	}

	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		AnnouncementBO reqAnn = request.getRequestData(AnnouncementBO.class);
		AnnouncementBO resAnn = null;
		if (reqAnn!=null && reqAnn.getCompanyId()!=null) {
			Long companyId = reqAnn.getCompanyId();
			CompanyInstance company = ciOps.findById(companyId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
				if(company.isActive()){
					reqAnn.setDefaultValidUntil();
					Announcement ann = mapper.createNewEntityFromBO(reqAnn, null);
					ann.setCompany(company);
					if(ann!=null){
						annOps.add(ann);
						resAnn=mapper.fromEntityToBO(ann);
						result = true;
						resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
					}
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resAnn);
		return response;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		AnnouncementBO reqAnn = request.getRequestData(AnnouncementBO.class);
		AnnouncementBO resAnn = null;
		if (reqAnn!=null && reqAnn.getCompanyId()!=null && reqAnn.getId()!=null) {
			Long annId = reqAnn.getId();
			Long companyId = reqAnn.getCompanyId();
			CompanyInstance company = ciOps.findById(companyId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
				if(company.isActive()){
					Announcement ann = annOps.findByCompanyAndId(company, annId);
					resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
					if(ann!=null){
						mapper.copyUpdatedBOToEntity(reqAnn, ann, null);
						annOps.update(ann);
						resAnn = mapper.fromEntityToBO(ann);
						result = true;
						resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
					}
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resAnn);
		return response;
	}

	@Override
	public ServiceResponse readAllInstancesData(ServiceRequest request) {
		boolean result = false;
		String resultMsg = null;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		List<AnnouncementBO> annList = new ArrayList<>(1);
		try{
			List<Announcement> exList = annOps.findByFilters(reqBO);
			convertToBOList(annList, exList);
			result = true;
			resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
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

	@Override
	public ServiceResponse readAllWithFilters(ServiceRequest request){
		boolean result = false;
		String resultMsg = null;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		List<AnnouncementBO> annList = new ArrayList<>(1);
		try{
			if (reqBO!=null && reqBO.cpId!=null) {
				Long companyId = reqBO.cpId;
				CompanyInstance company = ciOps.findById(companyId);
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(company!=null){
					resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
					if(company.isActive()){
						List<Announcement> exList = annOps.findByFilters(reqBO);
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

}

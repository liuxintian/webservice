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
import com.omt.cms.master.entity.Feed;
import com.omt.cms.master.repo.op.FeedOperations;
import com.omt.cms.master.service.FeedService;
import com.omt.cms.master.service.bo.FeedBO;
import com.omt.cms.master.service.bo.mapper.FeedMapper;

@Service
public class FeedServiceImpl implements FeedService{
	private static final Logger LOGGER = LoggerFactory.getLogger(FeedServiceImpl.class);
	@Autowired private FeedOperations feedOps;
	@Autowired private FeedMapper mapper;

	@Override
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		FeedBO resultFeed = null;
		Long feedId = request.getRequestData(Long.class);
		if (feedId!=null) {
			Feed feedEnt = feedOps.findById(feedId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(feedEnt!=null){
				resultFeed =  mapper.fromEntityToBO(feedEnt);
				result = true;
				resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultFeed);
		return response;
	}
	
	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		FeedBO resultFeed = null;
		FeedBO reqIRMenu = request.getRequestData(FeedBO.class);
		if (reqIRMenu != null) {
			reqIRMenu.setDefaultValidUntil();
			Feed feedEnt = mapper.createNewEntityFromBO(reqIRMenu, null);
			feedEnt.setStatus(RecordStatus.ACTIVE.getValue());
			feedOps.add(feedEnt);
			resultFeed =  mapper.fromEntityToBO(feedEnt);
			result = true;
			resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultFeed);
		return response;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		FeedBO resultFeed = null;
		FeedBO reqIRMenu = request.getRequestData(FeedBO.class);
		if (reqIRMenu!=null) {
			Feed feedEnt = feedOps.findById(reqIRMenu.getId());
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(feedEnt!=null){
				feedEnt = mapper.copyUpdatedBOToEntity(reqIRMenu, feedEnt, null);
				feedEnt = feedOps.update(feedEnt);
				resultFeed =  mapper.fromEntityToBO(feedEnt);
				result = true;
				resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultFeed);
		return response;
	}

	@Override
	public ServiceResponse delete(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		FeedBO resultFeed = null;
		FeedBO reqIRMenu = request.getRequestData(FeedBO.class);
		if (reqIRMenu!=null) {
			Feed feedEnt = feedOps.findById(reqIRMenu.getId());
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(feedEnt!=null){
				feedEnt.setStatus(SystemCodes.RecordStatus.DELETED.getValue());;
				feedEnt = feedOps.update(feedEnt);
				resultFeed =  mapper.fromEntityToBO(feedEnt);
				result = true;
				resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultFeed);
		return response;
	}

	@Override
	public ServiceResponse readAll(ServiceRequest request) {
		boolean result = true;
		String resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		List<FeedBO> feedBOList = new ArrayList<FeedBO>(1);
		try {
			List<Feed> feedResult = feedOps.findByFiltersAdmins(reqBO);
			Long total = feedOps.countByFiltersAdmins(reqBO);
			convertToBOList(feedBOList, feedResult, total);
		} catch (Exception e) {
			result = false;
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(feedBOList);
		return response;
	}

	private void convertToBOList(List<FeedBO> feedBOList, List<Feed> feedList) {
		for (Feed feedEnt : feedList) {
			FeedBO feedBO = mapper.fromEntityToBO(feedEnt);
			feedBOList.add(feedBO);
		}
	}

	private void convertToBOList(List<FeedBO> feedBOList, List<Feed> feedList, Long total) {
		boolean addTotal = true;
		for (Feed feedEnt : feedList) {
			FeedBO feedBO = mapper.fromEntityToBO(feedEnt);
			if(addTotal){
				feedBO.setTotal(total);
				addTotal = false;
			}
			feedBOList.add(feedBO);
		}
	}

	@Override
	public ServiceResponse readAllWithFilters(ServiceRequest request){
		boolean result = false;
		String resultMsg = null;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		List<FeedBO> respList = new ArrayList<>(1);
		try{
			if (reqBO!=null) {
				List<Feed> resList = feedOps.findByFilters(reqBO);
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
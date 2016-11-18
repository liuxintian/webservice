package com.omt.cms.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.master.entity.IRMenuItem;
import com.omt.cms.master.repo.op.IRMenuItemOperations;
import com.omt.cms.master.service.IRMenuItemService;
import com.omt.cms.master.service.bo.IRMenuItemBO;
import com.omt.cms.master.service.bo.mapper.IRMenuItemMapper;

@Service
public class IRMenuItemServiceImpl implements IRMenuItemService{

	@Autowired private IRMenuItemOperations irmOps;
	@Autowired private IRMenuItemMapper mapper;

	@Override
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		IRMenuItemBO resultIRMenu = null;
		Long irmId = request.getRequestData(Long.class);
		if (irmId!=null) {
			IRMenuItem irmEnt = irmOps.findById(irmId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(irmEnt!=null){
				resultIRMenu =  mapper.fromEntityToBO(irmEnt);
				result = true;
				resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultIRMenu);
		return response;
	}
	
	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		IRMenuItemBO resultIRMenu = null;
		IRMenuItemBO reqIRMenu = request.getRequestData(IRMenuItemBO.class);
		if (reqIRMenu != null) {
			reqIRMenu.setDefaultValidUntil();
			IRMenuItem irmEnt = mapper.createNewEntityFromBO(reqIRMenu, null);
			irmEnt.setStatus(RecordStatus.ACTIVE.getValue());
			irmOps.add(irmEnt);
			resultIRMenu =  mapper.fromEntityToBO(irmEnt);
			result = true;
			resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultIRMenu);
		return response;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		IRMenuItemBO resultIRMenu = null;
		IRMenuItemBO reqIRMenu = request.getRequestData(IRMenuItemBO.class);
		if (reqIRMenu!=null) {
			IRMenuItem irmEnt = irmOps.findById(reqIRMenu.getId());
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(irmEnt!=null){
				irmEnt = mapper.copyUpdatedBOToEntity(reqIRMenu, irmEnt, null);
				irmEnt = irmOps.update(irmEnt);
				resultIRMenu =  mapper.fromEntityToBO(irmEnt);
				result = true;
				resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultIRMenu);
		return response;
	}

	@Override
	public ServiceResponse delete(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		IRMenuItemBO resultIRMenu = null;
		IRMenuItemBO reqIRMenu = request.getRequestData(IRMenuItemBO.class);
		if (reqIRMenu!=null) {
			IRMenuItem irmEnt = irmOps.findById(reqIRMenu.getId());
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(irmEnt!=null){
				irmEnt.setStatus(SystemCodes.RecordStatus.DELETED.getValue());;
				irmEnt = irmOps.update(irmEnt);
				resultIRMenu =  mapper.fromEntityToBO(irmEnt);
				result = true;
				resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultIRMenu);
		return response;
	}


	@Override
	public ServiceResponse readAll(ServiceRequest request) {
		boolean result = true;
		String resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
		List<IRMenuItemBO> irmBOList = new ArrayList<IRMenuItemBO>();
		try {
			List<IRMenuItem> irmList = irmOps.findAll();
			for (IRMenuItem irmEnt : irmList) {
				IRMenuItemBO irmBO = mapper.fromEntityToBO(irmEnt);
				irmBOList.add(irmBO);
			}
		} catch (Exception e) {
			result = false;
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(irmBOList);
		return response;
	}
}
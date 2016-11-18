package com.omt.cms.user.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.user.entity.MobileUserLog;
import com.omt.cms.user.repo.op.MobileUserLogOperations;
import com.omt.cms.user.service.MobileUserLogService;
import com.omt.cms.user.service.bo.MobileUserLogReqBO;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Service
public class MobileUserLogServiceImpl implements MobileUserLogService {

	@Autowired private MobileUserLogOperations mulOps;

	@Override
	public ServiceResponse read(ServiceRequest request) {
		return operationDeniedResp();
	}

	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		MobileUserLog reqData = request.getRequestData(MobileUserLog.class);
		try {
			if (reqData != null) {
				mulOps.add(reqData);
				result = true;
				resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		return response;
	}

	
	@Override
	public ServiceResponse readByDateRange(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		MobileUserLogReqBO reqData = request.getRequestData(MobileUserLogReqBO.class);
		List<MobileUserLog> resLst = new ArrayList<>(1);
		try {
			if (reqData != null) {
				long start = reqData.getStart();
				long end = reqData.getEnd();
				Timestamp st = new Timestamp(start);
				Timestamp et = new Timestamp(end);
				resLst = mulOps.filterByDates(st, et);
				result = true;
				resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resLst);
		return response;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		return operationDeniedResp();
	}

	@Override
	public ServiceResponse delete(ServiceRequest request) {
		return operationDeniedResp();
	}

	private ServiceResponse operationDeniedResp() {
		return new ServiceResponse(false, ServiceResultCodes.OPERATION_DENIED.getValue());
	}
}

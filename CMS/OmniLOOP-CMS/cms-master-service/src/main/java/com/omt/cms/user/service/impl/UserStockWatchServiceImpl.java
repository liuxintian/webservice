/**
 * 
 */
package com.omt.cms.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.master.entity.Company;
import com.omt.cms.master.repo.op.CompanyOperations;
import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserStockWatch;
import com.omt.cms.user.repo.op.UserOperations;
import com.omt.cms.user.repo.op.UserStockWatchOperations;
import com.omt.cms.user.service.UserStockWatchService;
import com.omt.cms.user.service.bo.UserStockWatchBO;
import com.omt.cms.user.service.bo.mapper.UserStockWatchMapper;

/**
 * @author muragesh
 *
 */
@Service
public class UserStockWatchServiceImpl implements UserStockWatchService {
	@Autowired private UserStockWatchOperations uwOp;
	@Autowired  private UserOperations userOp;
	@Autowired  private CompanyOperations cpOp;
	@Autowired  private UserStockWatchMapper mapper;

	@Override
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserStockWatchBO uw = request.getRequestData(UserStockWatchBO.class);
		List<UserStockWatchBO> uwRsltLst = new ArrayList<>(1);
		try{
			if(uw!=null && uw.getUserId()!=null){
				User user = userOp.findById(uw.getUserId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(user!=null){
					List<UserStockWatch> uwEntLst = uwOp.findByUser(user);
					for(UserStockWatch uwEnt:uwEntLst){
						UserStockWatchBO uwRslt = mapper.fromEntityToBO(uwEnt);
						addCompanyDetails(uwRslt);
						uwRsltLst.add(uwRslt);
					}
					result = true;
					resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
				}
			}
		} catch(Exception e){
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(uwRsltLst);
		return response;
	}

	private void addCompanyDetails(UserStockWatchBO uwEnt) {
		if(StringUtils.isNotBlank(uwEnt.getCompanyTicker())){
			String ticker = uwEnt.getCompanyTicker().toLowerCase();
			Company company = cpOp.findByCompanyTicker(ticker);
			if(company!=null){
				BeanUtils.copyProperties(company, uwEnt);
				uwEnt.setCompanyId(company.getId());
			}
		}
	}

	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserStockWatchBO reqBO = request.getRequestData(UserStockWatchBO.class);
		UserStockWatchBO resBO = null;
		try {
			if(reqBO!=null && reqBO.getUserId()!=null && StringUtils.isNotBlank(reqBO.getCompanyTicker())){
				String ticker = reqBO.getCompanyTicker().toLowerCase();
				Long userId = reqBO.getUserId();
				User user=userOp.findById(userId);
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				if(user!=null){
					resultCode = ServiceResultCodes.RECORD_FOUND.getValue();	
					boolean exists = uwOp.userCompanyTickerExists(user, ticker);
					if(!exists){
						reqBO.setCompanyTicker(ticker);
						UserStockWatch uwEnt = mapper.createNewEntityFromBO(reqBO, null);
						uwEnt = uwOp.add(uwEnt);
						resBO = mapper.fromEntityToBO(uwEnt);
						result = true;
						resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
					}
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resBO);
		return response;
	}

	@Override
	public ServiceResponse delete(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserStockWatchBO resBO = null;
		UserStockWatchBO reqBO = request.getRequestData(UserStockWatchBO.class);
		try {
			if(reqBO!=null && reqBO.getId()!=null){
				UserStockWatch uwEnt=uwOp.findById(reqBO.getId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(uwEnt!=null){
					resBO = mapper.fromEntityToBO(uwEnt);
					uwOp.remove(uwEnt);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resBO);
		return response;
	}

	@Override
	public ServiceResponse deleteByAsxCode(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserStockWatchBO resBO = null;
		UserStockWatchBO reqBO = request.getRequestData(UserStockWatchBO.class);
		try {
			if(reqBO!=null && reqBO.getUserId()!=null && StringUtils.isNotBlank(reqBO.getCompanyTicker())){
				String ticker = reqBO.getCompanyTicker().toLowerCase();
				Long userId = reqBO.getUserId();
				User user = new User();
				user.setId(userId);
				UserStockWatch uw = uwOp.getByCompanyTicker(user, ticker);
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(uw!=null){
					uwOp.remove(uw);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resBO);
		return response;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) { return null;}

}

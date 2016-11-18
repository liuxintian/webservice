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

import com.omt.cms.company.instance.service.LocalUserProxyService;
import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.LocalUserBO;
import com.omt.cms.master.entity.Company;
import com.omt.cms.master.repo.op.CompanyOperations;
import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserStockSubscription;
import com.omt.cms.user.repo.op.UserOperations;
import com.omt.cms.user.repo.op.UserStockSubscriptionOperations;
import com.omt.cms.user.service.UserStockSubscriptionService;
import com.omt.cms.user.service.bo.UserStockSubscriptionBO;
import com.omt.cms.user.service.bo.mapper.UserStockSubscriptionMapper;

/**
 * @author muragesh
 *
 */
@Service
public class UserStockSubscriptionServiceImpl implements UserStockSubscriptionService {
	@Autowired private UserStockSubscriptionOperations uwOp;
	@Autowired private UserOperations userOp;
	@Autowired private CompanyOperations cpOp;
	@Autowired private UserStockSubscriptionMapper mapper;
	@Autowired private LocalUserProxyService luProxy;
	
	@Override
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserStockSubscriptionBO uw = request.getRequestData(UserStockSubscriptionBO.class);
		List<UserStockSubscriptionBO> uwRsltLst = new ArrayList<>(1);
		try{
			if(uw!=null && uw.getUserId()!=null){
				User user = userOp.findById(uw.getUserId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(user!=null){
					List<UserStockSubscription> uwEntLst = uwOp.findByUser(user.getId());
					for(UserStockSubscription uwEnt:uwEntLst){
						UserStockSubscriptionBO uwRslt = mapper.fromEntityToBO(uwEnt);
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

	protected void addCompanyDetails(UserStockSubscriptionBO uwEnt) {
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
		UserStockSubscriptionBO reqBO = request.getRequestData(UserStockSubscriptionBO.class);
		List<String> addedLst = new ArrayList<>();
		try {
			if(reqBO!=null && reqBO.getUserId()!=null && reqBO.getCompanyTickers()!=null && !reqBO.getCompanyTickers().isEmpty()){
				Long userId = reqBO.getUserId();
				User user=userOp.findById(userId);
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				if(user!=null){
					for(String ticker : reqBO.getCompanyTickers()){
						ticker = ticker.toLowerCase();
						boolean exists = uwOp.userCompanyTickerExists(user.getId(), ticker);
						if(!exists){
							reqBO.setCompanyTicker(ticker);
							UserStockSubscription uwEnt = mapper.createNewEntityFromBO(reqBO, null);
							uwOp.add(uwEnt);
							//add user record to the local user list
							Company company = cpOp.findByCompanyTicker(ticker);
							if(company!=null){
								LocalUserBO lu = new LocalUserBO();
								BeanUtils.copyProperties(user, lu);
								lu.setShareSubscriber(true);
								lu.setCompanyId(company.getId());
								ServiceRequest sreq = new ServiceRequest();
								sreq.addRequestData(lu);
								luProxy.updateSubscriptionStatus(sreq);
							}
							addedLst.add(ticker);
						}
					}
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		reqBO.setCompanyTicker(null);
		reqBO.setCompanyTickers(addedLst);
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(reqBO);
		return response;
	}


	@Override
	public ServiceResponse deleteTickers(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserStockSubscriptionBO resBO = null;
		UserStockSubscriptionBO reqBO = request.getRequestData(UserStockSubscriptionBO.class);
		//List<String> addedLst = new ArrayList<>();
		try {
			if(reqBO!=null && reqBO.getUserId()!=null && reqBO.getCompanyTickers()!=null && !reqBO.getCompanyTickers().isEmpty()){
				Long userId = reqBO.getUserId();
				User user=userOp.findById(userId);
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				if(user!=null){
					for(String ticker : reqBO.getCompanyTickers()){
						ticker = ticker.toLowerCase();
						boolean exists = uwOp.userCompanyTickerExists(user.getId(), ticker);

						if(exists){
								UserStockSubscription uw = uwOp.getByCompanyTicker(userId, ticker);
								if(uw!=null){

									uwOp.remove(uw);
									Company company = cpOp.findByCompanyTicker(ticker);
									if(company!=null){
										LocalUserBO lu = new LocalUserBO();
										BeanUtils.copyProperties(user, lu);
										lu.setShareSubscriber(false);
										lu.setCompanyId(company.getId());
										ServiceRequest sreq = new ServiceRequest();
										sreq.addRequestData(lu);
										luProxy.updateSubscriptionStatus(sreq);

									}
								}
						}
					}
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
	public ServiceResponse delete(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserStockSubscriptionBO resBO = null;
		UserStockSubscriptionBO reqBO = request.getRequestData(UserStockSubscriptionBO.class);
		try {
			if(reqBO!=null && reqBO.getId()!=null){
				UserStockSubscription uwEnt = uwOp.findById(reqBO.getId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(uwEnt!=null){
					resBO = mapper.fromEntityToBO(uwEnt);
					uwOp.remove(uwEnt);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
					
					//add user record to the local user list
					Company company = cpOp.findByCompanyTicker(uwEnt.getCompanyTicker().toLowerCase());
					if(company!=null){
						User user = userOp.findById(uwEnt.getUserId());
						LocalUserBO lu = new LocalUserBO();
						BeanUtils.copyProperties(user, lu);
						lu.setShareSubscriber(false);
						lu.setCompanyId(company.getId());
						ServiceRequest sreq = new ServiceRequest();
						sreq.addRequestData(lu);
						luProxy.updateSubscriptionStatus(sreq);
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
	public ServiceResponse deleteByAsxCode(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserStockSubscriptionBO resBO = null;
		UserStockSubscriptionBO reqBO = request.getRequestData(UserStockSubscriptionBO.class);
		try {
			if(reqBO!=null && reqBO.getUserId()!=null && StringUtils.isNotBlank(reqBO.getCompanyTicker())){
				String ticker = reqBO.getCompanyTicker().toLowerCase();
				Long userId = reqBO.getUserId();
				UserStockSubscription uw = uwOp.getByCompanyTicker(userId, ticker);
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(uw!=null){
					uwOp.remove(uw);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
					//add user record to the local user list
					Company company = cpOp.findByCompanyTicker(ticker);
					if(company!=null){
						User user = userOp.findById(userId);
						LocalUserBO lu = new LocalUserBO();
						BeanUtils.copyProperties(user, lu);
						lu.setShareSubscriber(false);
						lu.setCompanyId(company.getId());
						ServiceRequest sreq = new ServiceRequest();
						sreq.addRequestData(lu);
						luProxy.updateSubscriptionStatus(sreq);
					}
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

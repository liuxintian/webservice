/**
 * 
 */
package com.omt.cms.user.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserRegistration;
import com.omt.cms.user.repo.op.UserOperations;
import com.omt.cms.user.repo.op.UserRegistrationOperations;
import com.omt.cms.user.service.UserRegistrationService;
import com.omt.cms.user.service.bo.UserRegistrationBO;

/**
 * @author muragesh
 *
 */
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
	@Autowired private UserRegistrationOperations urOp;
	@Autowired  private UserOperations userOp;

	@Override
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserRegistrationBO urBO = request.getRequestData(UserRegistrationBO.class);
		UserRegistrationBO urRs = null;
		try{
			if(urBO!=null && urBO.getUserId()!=null && urBO.getId()!=null){
				User uEnt=userOp.findById(urBO.getUserId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(uEnt!=null){
					UserRegistration uwEnt = urOp.findById(urBO.getId());
					if(uwEnt!=null ){
						urRs = new UserRegistrationBO();
						BeanUtils.copyProperties(uwEnt, urRs);
						urRs.setUserId(uwEnt.getUser().getId());
						result = true;
						resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
					}
				}
			}
		} catch(Exception e){
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(urRs);
		return response;
	}

	@Override
	public ServiceResponse readAll(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserRegistrationBO urBO = request.getRequestData(UserRegistrationBO.class);
		List<UserRegistrationBO> urRsltLst = new ArrayList<>();
		try{
			if(urBO!=null && urBO.getUserId()!=null){
				User uEnt=userOp.findById(urBO.getUserId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(uEnt!=null){
					List<UserRegistration> uwEntLst = urOp.findByUser(uEnt);
					if(uwEntLst!=null ){
						UserRegistrationBO urRslt=null;
						for(UserRegistration urEnt:uwEntLst){
							urRslt = new UserRegistrationBO();
							BeanUtils.copyProperties(urEnt, urRslt);
							urRslt.setUserId(urEnt.getUser().getId());
							urRsltLst.add(urRslt);
						}
						result = true;
						resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
					}
				}
			}
		} catch(Exception e){
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(urRsltLst);
		return response;
	}

	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserRegistrationBO urBO=request.getRequestData(UserRegistrationBO.class);
		UserRegistrationBO urRslt = null;
		try {
			if(urBO!=null){
				User user=userOp.findById(urBO.getUserId());
				if(user!=null){
					UserRegistration urEnt = new UserRegistration();
					urEnt.setShareRegistry(urBO.getShareRegistry());
					urEnt.setTokenId(urBO.getTokenId());
 					urEnt.setUser(user);
					urEnt = urOp.add(urEnt);
					urRslt=new UserRegistrationBO();
					BeanUtils.copyProperties(urEnt, urRslt);
					urRslt.setUserId(urEnt.getUser().getId());
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(urRslt);
		return response;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserRegistrationBO urBO=request.getRequestData(UserRegistrationBO.class);
		UserRegistrationBO urRslt = null;
		try {
			if(urBO!=null && urBO.getId()!=null){
				UserRegistration urEnt=urOp.findById(urBO.getId());
				User user=userOp.findById(urBO.getUserId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(urEnt!=null && user!=null){
					urEnt.setShareRegistry(urBO.getShareRegistry());
					urEnt.setTokenId(urBO.getTokenId());
					Date now=new Date();
					urEnt.setLastUpdated(new Timestamp(now.getTime()));
					urEnt.setUser(user);
					urEnt = urOp.update(urEnt);
					urRslt=new UserRegistrationBO();
					BeanUtils.copyProperties(urEnt, urRslt);
					urRslt.setUserId(urEnt.getUser().getId());
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(urRslt);
		return response;
	}


	@Override
	public ServiceResponse delete(ServiceRequest request) {
		return null;
	}
}

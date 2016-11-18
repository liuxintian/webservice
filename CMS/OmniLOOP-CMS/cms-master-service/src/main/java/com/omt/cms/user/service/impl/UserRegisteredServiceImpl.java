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
import com.omt.cms.user.entity.UserRegistered;
import com.omt.cms.user.repo.op.UserOperations;
import com.omt.cms.user.repo.op.UserRegisteredOperations;
import com.omt.cms.user.service.UserRegisteredService;
import com.omt.cms.user.service.bo.UserRegisteredBO;

/**
 * @author muragesh
 *
 */
@Service
public class UserRegisteredServiceImpl implements UserRegisteredService {
	@Autowired private UserRegisteredOperations urOp;
	@Autowired  private UserOperations userOp;

	@Override
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserRegisteredBO urBO = request.getRequestData(UserRegisteredBO.class);
		UserRegisteredBO urRs = null;
		try{
			if(urBO!=null && urBO.getUserId()!=null && urBO.getId()!=null){
				User uEnt=userOp.findById(urBO.getUserId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(uEnt!=null){
					UserRegistered uwEnt = urOp.findById(urBO.getId());
					if(uwEnt!=null ){
						urRs = new UserRegisteredBO();
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
		UserRegisteredBO urBO = request.getRequestData(UserRegisteredBO.class);
		List<UserRegisteredBO> urRsltLst = new ArrayList<>();
		try{
			if(urBO!=null && urBO.getUserId()!=null){
				User uEnt=userOp.findById(urBO.getUserId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(uEnt!=null){
					List<UserRegistered> uwEntLst = urOp.findByUser(uEnt);
					if(uwEntLst!=null ){
						UserRegisteredBO urRslt=null;
						for(UserRegistered urEnt:uwEntLst){
							urRslt = new UserRegisteredBO();
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
		UserRegisteredBO urBO=request.getRequestData(UserRegisteredBO.class);
		UserRegisteredBO urRslt = null;
		try {
			if(urBO!=null){
				User user=userOp.findById(urBO.getUserId());
				if(user!=null){
					UserRegistered urEnt = new UserRegistered();
					urEnt.setAppRegistered(urBO.getAppRegistered());
					urEnt.setIsPrimary(urBO.getIsPrimary());
 					urEnt.setUser(user);
					urEnt = urOp.add(urEnt);
					urRslt=new UserRegisteredBO();
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
		UserRegisteredBO urBO=request.getRequestData(UserRegisteredBO.class);
		UserRegisteredBO urRslt = null;
		try {
			if(urBO!=null && urBO.getId()!=null){
				UserRegistered urEnt=urOp.findById(urBO.getId());
				User user=userOp.findById(urBO.getUserId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(urEnt!=null && user!=null){
					if(urBO.getAppRegistered()!=null)
						urEnt.setAppRegistered(urBO.getAppRegistered());
					if(urBO.getIsPrimary()!=null)
						urEnt.setIsPrimary(urBO.getIsPrimary());
					
					Date now=new Date();
					urEnt.setLastUpdated(new Timestamp(now.getTime()));

					urEnt.setUser(user);
					urEnt = urOp.update(urEnt);
					urRslt=new UserRegisteredBO();
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

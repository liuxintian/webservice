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
import com.omt.cms.user.entity.UserSetting;
import com.omt.cms.user.repo.op.UserOperations;
import com.omt.cms.user.repo.op.UserSettingOperations;
import com.omt.cms.user.service.UserSettingService;
import com.omt.cms.user.service.bo.UserSettingBO;

/**
 * @author muragesh
 *
 */
@Service
public class UserSettingServiceImpl implements UserSettingService {
	@Autowired private UserSettingOperations utOp;
	@Autowired  private UserOperations userOp;

	@Override
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserSettingBO utBO = request.getRequestData(UserSettingBO.class);
		UserSettingBO utRs = null;
		try{
			if(utBO!=null && utBO.getUserId()!=null && utBO.getId()!=null){
				User uEnt=userOp.findById(utBO.getUserId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(uEnt!=null){
					UserSetting utEnt = utOp.findById(utBO.getId());
					if(utEnt!=null){
						utRs = new UserSettingBO();
						BeanUtils.copyProperties(utEnt, utRs);
						utRs.setUserId(utEnt.getUser().getId());
						result = true;
						resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
					}
				}
			}
		} catch(Exception e){
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(utRs);
		return response;
	}

	@Override
	public ServiceResponse readAll(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserSettingBO utBO = request.getRequestData(UserSettingBO.class);
		List<UserSettingBO> utRsltLst = new ArrayList<>();
		try{
			if(utBO!=null && utBO.getUserId()!=null){
				User uEnt=userOp.findById(utBO.getUserId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(uEnt!=null){
					List<UserSetting> utEntLst = utOp.findByUser(uEnt);
					if(utEntLst!=null ){
						UserSettingBO utRslt=null;
						for(UserSetting utEnt:utEntLst){
							utRslt = new UserSettingBO();
							BeanUtils.copyProperties(utEnt, utRslt);
							utRslt.setUserId(utEnt.getUser().getId());
							utRsltLst.add(utRslt);
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
		response.addResponseData(utRsltLst);
		return response;
	}

	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserSettingBO utBO=request.getRequestData(UserSettingBO.class);
		UserSettingBO utRslt = null;
		try {
			if(utBO!=null){
				User user=userOp.findById(utBO.getUserId());
				if(user!=null){
					UserSetting utEnt = new UserSetting();
					utEnt.setSettingName(utBO.getSettingName());
					utEnt.setSettingValue(utBO.getSettingValue()); 
					utEnt.setUser(user);
					utEnt = utOp.add(utEnt);
					utRslt = new UserSettingBO();
					BeanUtils.copyProperties(utEnt, utRslt);
					utRslt.setUserId(utEnt.getUser().getId());
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(utRslt);
		return response;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserSettingBO utBO=request.getRequestData(UserSettingBO.class);
		UserSettingBO utRslt = null;
		try {
			if(utBO!=null && utBO.getId()!=null){
				UserSetting utEnt=utOp.findById(utBO.getId());
				User user=userOp.findById(utBO.getUserId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(utEnt!=null && user!=null){
					if(utBO.getSettingName()!=null)
						utEnt.setSettingName(utBO.getSettingName());
					if(utBO.getSettingValue()!=null)
						utEnt.setSettingValue(utBO.getSettingValue());

					Date now=new Date();
					utEnt.setLastUpdated(new Timestamp(now.getTime()));

					utEnt.setUser(user);
					utEnt = utOp.update(utEnt);
					utRslt=new UserSettingBO();
					BeanUtils.copyProperties(utEnt, utRslt);
					utRslt.setUserId(utEnt.getUser().getId());
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(utRslt);
		return response;
	}


	@Override
	public ServiceResponse delete(ServiceRequest request) {
		return null;
	}
}

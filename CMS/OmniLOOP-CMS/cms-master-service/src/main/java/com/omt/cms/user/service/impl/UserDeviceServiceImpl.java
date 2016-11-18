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
import com.omt.cms.user.entity.UserDevice;
import com.omt.cms.user.repo.op.UserDeviceOperations;
import com.omt.cms.user.repo.op.UserOperations;
import com.omt.cms.user.service.UserDeviceService;
import com.omt.cms.user.service.bo.UserDeviceBO;

/**
 * @author muragesh
 *
 */
@Service
public class UserDeviceServiceImpl implements UserDeviceService {
	@Autowired private UserDeviceOperations udOp;
	@Autowired  private UserOperations userOp;

	@Override
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserDeviceBO udBO = request.getRequestData(UserDeviceBO.class);
		UserDeviceBO udRs = null;
		try{
			if(udBO!=null && udBO.getUserId()!=null && udBO.getId()!=null){
				User uEnt=userOp.findById(udBO.getUserId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(uEnt!=null){
					Long id = udBO.getId();
					UserDevice udEnt = udOp.findById(id);
					if(udEnt!=null){
						udRs = new UserDeviceBO();
						BeanUtils.copyProperties(udEnt, udRs);
						udRs.setUserId(udEnt.getUser().getId());
						result = true;
						resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
					}
				}
			}
		} catch(Exception e){
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(udRs);
		return response;
	}

	@Override
	public ServiceResponse readAll(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserDeviceBO udBO = request.getRequestData(UserDeviceBO.class);
		List<UserDeviceBO> udRsltLst = new ArrayList<>();
		try{
			if(udBO!=null && udBO.getUserId()!=null){
				User uEnt=userOp.findById(udBO.getUserId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(uEnt!=null){
					List<UserDevice> udEntLst = udOp.findByUser(uEnt);
					if(udEntLst!=null ){
						UserDeviceBO udRslt=null;
						for(UserDevice udEnt:udEntLst){
							udRslt = new UserDeviceBO();
							BeanUtils.copyProperties(udEnt, udRslt);
							udRslt.setUserId(udEnt.getUser().getId());
							udRsltLst.add(udRslt);

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
		response.addResponseData(udRsltLst);
		return response;
	}

	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserDeviceBO udBO=request.getRequestData(UserDeviceBO.class);
		UserDeviceBO udRslt = null;
		try {
			if(udBO!=null){
				User user=userOp.findById(udBO.getUserId());
				if(user!=null){
					UserDevice udEnt = new UserDevice();
					udEnt.setDeviceId(udBO.getDeviceId());
					udEnt.setDeviceOS(udBO.getDeviceOS()); 
					udEnt.setDeviceSize(udBO.getDeviceSize());
					udEnt.setDeviceVer(udBO.getDeviceVer());

					udEnt.setUser(user);
					udEnt = udOp.add(udEnt);
					udRslt=new UserDeviceBO();
					BeanUtils.copyProperties(udEnt, udRslt);
					udRslt.setUserId(udEnt.getUser().getId());
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(udRslt);
		return response;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserDeviceBO udBO=request.getRequestData(UserDeviceBO.class);
		UserDeviceBO udRslt = null;
		try {
			if(udBO!=null && udBO.getId()!=null){
				UserDevice udEnt=udOp.findById(udBO.getId());
				User user=userOp.findById(udBO.getUserId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(udEnt!=null && user!=null){
					if(udBO.getDeviceId()!=null)
						udEnt.setDeviceId(udBO.getDeviceId());
					if(udBO.getDeviceOS()!=null)
						udEnt.setDeviceOS(udBO.getDeviceOS());
					if(udBO.getDeviceSize()!=null)
						udEnt.setDeviceSize(udBO.getDeviceSize());
					if(udBO.getDeviceVer()!=null)
						udEnt.setDeviceVer(udBO.getDeviceVer());

					Date now=new Date();
					udEnt.setLastUpdated(new Timestamp(now.getTime()));

					udEnt.setUser(user);
					udEnt = udOp.update(udEnt);
					udRslt=new UserDeviceBO();
					BeanUtils.copyProperties(udEnt, udRslt);
					udRslt.setUserId(udEnt.getUser().getId());
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(udRslt);
		return response;
	}


	@Override
	public ServiceResponse delete(ServiceRequest request) {
		return null;
	}
}

package com.omt.cms.cust.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.cms.core.common.DateHelper;
import com.omt.cms.core.common.SystemCodes.UserStatus;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.LocalUserBO;
import com.omt.cms.core.service.bo.base.LocalUserBO.DeviceInfo;
import com.omt.cms.core.service.bo.base.MailMessageReqBO;
import com.omt.cms.core.service.bo.base.MailMessageReqBO.Receipient;
import com.omt.cms.core.service.bo.base.UsersBO;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.entity.LocalUser;
import com.omt.cms.cust.entity.UserDevice;
import com.omt.cms.cust.repo.op.CompanyInstanceOperations;
import com.omt.cms.cust.repo.op.LocalUserOperations;
import com.omt.cms.cust.repo.op.UserDeviceOperations;
import com.omt.cms.cust.service.LocalUserService;
import com.omt.cms.cust.service.UsersProxyService;
import com.omt.cms.cust.service.bo.mapper.LocalUserMapper;
import com.omt.cms.cust.service.util.InstanceMailHelper;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Service
public class LocalUserServiceImpl implements LocalUserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LocalUserServiceImpl.class);    
	@Autowired CompanyInstanceOperations<CompanyInstance> ciOps;
	@Autowired LocalUserOperations luOps;
	@Autowired LocalUserMapper mapper;
	@Autowired InstanceMailHelper mailHelper;
	@Autowired UsersProxyService usrProxyService;
	@Autowired UserDeviceOperations udOps;

	@Override
	public ServiceResponse readAll(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		List<LocalUserBO> annList = new ArrayList<>();
		if (reqBO != null && reqBO.cpId!=null) {
			CompanyInstance company = ciOps.findById(reqBO.cpId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
				if(company.isActive()){
					List<LocalUser> exList = luOps.findByFiltersAdmins(company, reqBO);
					Long total = luOps.countByFiltersAdmins(company, reqBO);
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

	@Override
	public ServiceResponse readAllWithDeviceInfo(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		List<LocalUserBO> annList = new ArrayList<>();
		if (reqBO != null && reqBO.cpId!=null) {
			CompanyInstance company = ciOps.findById(reqBO.cpId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
				if(company.isActive()){
					List<LocalUser> exList = luOps.findByFiltersAdmins(company, reqBO);
					Long total = luOps.countByFiltersAdmins(company, reqBO);
					convertToBOListWithDeviceInfo(annList, exList, total);
					result = true;
					resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(annList);
		return response;
	}	

	private void convertToBOListWithDeviceInfo(List<LocalUserBO> lusLst, List<LocalUser> exList) {
		for(LocalUser lu:exList){
			LocalUserBO luBO = mapper.fromEntityToBO(lu);
			List<UserDevice> devices = udOps.findByUserId(lu.getId());
			if(devices!=null && !devices.isEmpty()){
				List<DeviceInfo> dilst = new ArrayList<>();
				for(UserDevice ud : devices){
					DeviceInfo di = new DeviceInfo();
					BeanUtils.copyProperties(ud, di);
					dilst.add(di);
				}
				luBO.setDevices(dilst);
			}
			lusLst.add(luBO);
		}
	}
	
	
	private void convertToBOListWithDeviceInfo(List<LocalUserBO> lusLst, List<LocalUser> exList, Long total) {
		boolean addTotal = true;
		for(LocalUser lu:exList){
			LocalUserBO luBO = mapper.fromEntityToBO(lu);
			if(addTotal){
				luBO.setTotal(total);
				addTotal = false;
			}
			
			List<UserDevice> devices = udOps.findByUserId(lu.getId());
			if(devices!=null && !devices.isEmpty()){
				List<DeviceInfo> dilst = new ArrayList<>();
				for(UserDevice ud : devices){
					DeviceInfo di = new DeviceInfo();
					BeanUtils.copyProperties(ud, di);
					dilst.add(di);
				}
				luBO.setDevices(dilst);
			}
			lusLst.add(luBO);
		}
	}

	private void convertToBOList(List<LocalUserBO> lusLst, List<LocalUser> exList, Long total) {
		boolean addTotal = true;
		for(LocalUser lu:exList){
			LocalUserBO luBO = mapper.fromEntityToBO(lu);
			if(addTotal){
				luBO.setTotal(total);
				addTotal = false;
			}
			lusLst.add(luBO);
		}
	}
	
	@Override
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		LocalUserBO reqBO = request.getRequestData(LocalUserBO.class);
		LocalUserBO resBO = null;
		try{
			if (reqBO!=null && reqBO.getCompanyId()!=null && reqBO.getId()!=null) {
				Long boId = reqBO.getId();
				Long companyId = reqBO.getCompanyId();
				CompanyInstance company = ciOps.findById(companyId);
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(company!=null){
					resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
					if(company.isActive()){
						LocalUser entity = luOps.findByCompanyAndId(company, boId);
						resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
						if(entity!=null){
							resBO=mapper.fromEntityToBO(entity);
							result = true;
							resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
						}
					}
				}
			}
		}catch (Exception e) {
			LOGGER.error("Error occurred: {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resBO);
		return response;
	}

	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		LocalUserBO reqBO = request.getRequestData(LocalUserBO.class);
		LocalUserBO resBO = null;
		try{
			if (reqBO!=null && reqBO.getCompanyId()!=null) {
				Long companyId = reqBO.getCompanyId();
				CompanyInstance company = ciOps.findById(companyId);
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(company!=null){
					resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
					if(company.isActive()){
						reqBO.setDefaultValidUntil();
						LocalUser entity = mapper.createNewEntityFromBO(reqBO, null);
						if(entity!=null){
							entity.setCompany(company);
							entity.setPwdHash(null);
							entity.setUserStatus(UserStatus.INVITED.getValue());
							entity.setUserInvitedDT(DateHelper.getCurTimestamp());
							luOps.add(entity);
							resBO=mapper.fromEntityToBO(entity);
							result = true;
							resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
						}
					}
				}
			}
		}catch (Exception e) {
			LOGGER.error("Error occurred: {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resBO);
		return response;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		LocalUserBO reqBO = request.getRequestData(LocalUserBO.class);
		LocalUserBO resBO = null;
		try{
			if (reqBO!=null && reqBO.getCompanyId()!=null && reqBO.getId()!=null) {
				Long boId = reqBO.getId();
				Long companyId = reqBO.getCompanyId();
				CompanyInstance company = ciOps.findById(companyId);
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(company!=null){
					resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
					if(company.isActive()){
						LocalUser entity = luOps.findByCompanyAndId(company, boId);
						resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
						if(entity!=null){
							mapper.copyUpdatedBOToEntity(reqBO, entity, null);
							luOps.update(entity);
							resBO = mapper.fromEntityToBO(entity);
							result = true;
							resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
						}
					}
				}
			}
		}catch (Exception e) {
			LOGGER.error("Error occurred: {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}

		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resBO);
		return response;
	}

	@Override
	public ServiceResponse sendMail(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		MailMessageReqBO reqBO = request.getRequestData(MailMessageReqBO.class);
		try {
			if (reqBO!=null && reqBO.getCompanyId()!=null && StringUtils.isNotBlank(reqBO.getContent())) {
				Long companyId = reqBO.getCompanyId();
				CompanyInstance company = ciOps.findById(companyId);
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(company!=null){
					resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
					List<LocalUser> entList = null;
					if(company.isActive()){
						if(reqBO.getMailIds()!=null && !reqBO.getMailIds().isEmpty()){
							List<Receipient> emailIds = reqBO.getMailIds();
							entList = new ArrayList<>();
							for(Receipient rcpt  : emailIds){
								LocalUser user = new LocalUser();
								user.setUserEmail(rcpt.getEmail());
								user.setUserName(rcpt.getName());
								entList.add(user);
							}
						}else{
							entList = luOps.findByCompany(company);
						}
						mailHelper.sendMessageToLocalUsers(reqBO.getSubject(), entList, company.getCompanyName(), reqBO.getContent());
						result = true;
						resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
					}
				}
			}
		}catch (Exception e) {
			LOGGER.error("Error occurred: {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		return response;
	}

	@Override
	public ServiceResponse updateShareSubscription(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		LocalUserBO reqBO = request.getRequestData(LocalUserBO.class);
		LocalUserBO resBO = null;
		try{
			if (reqBO!=null && reqBO.getCompanyId()!=null && reqBO.getId()!=null) {
				Long boId = reqBO.getId();
				Long companyId = reqBO.getCompanyId();
				CompanyInstance company = ciOps.findById(companyId);
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(company!=null){
					resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
					if(company.isActive()){
						LocalUser entity = luOps.findByCompanyAndId(company, boId);
						resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
						if(entity!=null){
							entity.setShareSubscriber(reqBO.getShareSubscriber());
							entity.setLastUpdated(DateHelper.getCurTimestamp());
							luOps.update(entity);
							resBO = mapper.fromEntityToBO(entity);
							result = true;
							resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
						}else{
							reqBO.setDefaultValidUntil();
							entity = mapper.createNewEntityFromBO(reqBO, null);
							if(entity!=null){
								entity.setCompany(company);
								entity.setPwdHash(null);
								System.out.println("Entity Id is:" + entity.getId());
								luOps.add(entity);
								resBO = mapper.fromEntityToBO(entity);
								result = true;
								resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
							}
						}
					}
				}
			}
		}catch (Exception e) {
			LOGGER.error("Error occurred: {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}

		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resBO);
		return response;
	}

	@Override
	public ServiceResponse addToMasterAndInstance(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		LocalUserBO reqBO = request.getRequestData(LocalUserBO.class);
		LocalUserBO resBO = null;
		try{
			if (reqBO!=null && reqBO.getCompanyId()!=null) {
				Long companyId = reqBO.getCompanyId();
				UsersBO reqUsers = new UsersBO();
				List<LocalUserBO> userLst = new ArrayList<>(1);
				userLst.add(reqBO);
				reqUsers.setCpId(companyId);
				reqUsers.setUsers(userLst);
				ServiceRequest addUsrReq = new ServiceRequest();
				addUsrReq.addRequestData(reqUsers);
				ServiceResponse response = usrProxyService.addUsers(addUsrReq);
				if(response!=null && response.getServiceResult().isResult()){
					UsersBO resUsers = response.getResponseData(UsersBO.class);
					if(resUsers!=null && !resUsers.getUsers().isEmpty()){
						resBO = resUsers.getUsers().get(0);
					}
				}
			}
		}catch (Exception e) {
			LOGGER.error("Error occurred: {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);	
		response.addResponseData(resBO);
		return response;
	}

	@Override
	public ServiceResponse readAllByType(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		LocalUserBO reqBO = request.getRequestData(LocalUserBO.class);
		List<LocalUserBO> annList = new ArrayList<>();
		if (reqBO != null && reqBO.getCompanyId()!=null) {
			CompanyInstance company = ciOps.findById(reqBO.getCompanyId());
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
				if(company.isActive()){
					List<LocalUser> exList =  null;
					if(reqBO.getShareSubscriber()){
						exList = luOps.findByCompanyAndShareSubscriber(company, true);
					}else{
						exList = luOps.findByCompanyAndUserInvited(company);
					}
					convertToBOListWithDeviceInfo(annList, exList);
					result = true;
					resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(annList);
		return response;
	}	

	@Override
	public ServiceResponse delete(ServiceRequest request) { return null; }

}

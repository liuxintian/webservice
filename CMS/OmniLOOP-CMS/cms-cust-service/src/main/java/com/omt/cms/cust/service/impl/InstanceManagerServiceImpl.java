package com.omt.cms.cust.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.cms.core.common.Encryptor;
import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.common.SystemCodes.VerificationContext;
import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.LoginUserBO;
import com.omt.cms.core.service.bo.base.UserDetailsBO;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.entity.InstanceManager;
import com.omt.cms.cust.entity.InstanceVerification;
import com.omt.cms.cust.repo.op.InstanceManagerOperations;
import com.omt.cms.cust.repo.op.InstanceVerificationOperations;
import com.omt.cms.cust.service.InstanceManagerService;
import com.omt.cms.cust.service.bo.mapper.UserDetailsMapper;
import com.omt.cms.cust.service.util.InstanceMailHelper;
import com.omt.cms.entity.BaseManager.ManagerType;

@Service
public class InstanceManagerServiceImpl extends InstanceBaseServiceImpl implements InstanceManagerService{
	private static final Logger LOGGER = LoggerFactory.getLogger(InstanceManagerServiceImpl.class);    

	@Autowired private InstanceManagerOperations imOps;
	@Autowired private UserDetailsMapper mapper;
	@Autowired private InstanceVerificationOperations ivOps;
	@Autowired private InstanceMailHelper mailHelper;

	
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserDetailsBO resultUser = null;
		UserDetailsBO reqUser = request.getRequestData(UserDetailsBO.class);
		if (reqUser!=null) {
			Long companyId = reqUser.getCompanyId();
			Long userId = reqUser.getUserId();
			ServiceResponse response = checkInstanceStatus(companyId);
			result = response.getServiceResult().isResult();
			resultCode = response.getServiceResult().getResultCode();
			if(result){
				CompanyInstance company = response.getResponseData(CompanyInstance.class);
				InstanceManager manager = imOps.findByCompanyAndId(company, userId);
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				if(manager!=null){
					resultUser =  mapper.fromEntityToBO(manager);
					resultUser.setUserId(manager.getId());
					resultUser.setPassword(LoginUserBO.MASK_PASSWORD);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultUser);
		return response;
	}

	public ServiceResponse add(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserDetailsBO resultUser = null;
		UserDetailsBO reqUser = request.getRequestData(UserDetailsBO.class);
		if (reqUser != null && StringUtils.isNotBlank(reqUser.getLoginName())) {
			String loginName = reqUser.getLoginName().toLowerCase();
			Long companyId = reqUser.getCompanyId();
			ServiceResponse response = checkInstanceStatus(companyId);
			result = response.getServiceResult().isResult();
			resultCode = response.getServiceResult().getResultCode();
			if(result){
				CompanyInstance company = response.getResponseData(CompanyInstance.class);
				boolean loginExists = imOps.loginNameExists(loginName);
				resultCode = ServiceResultCodes.DUPLICATE_LOGINNAME.getValue();
				if(!loginExists){
					reqUser.setLoginName(loginName);
					InstanceManager manager = mapper.createNewEntityFromBO(reqUser, null);
					manager.setId(null);
					String epass = Encryptor.getInstance().encryptPassword(reqUser.getPassword());
					manager.setEncryptedPassword(epass);
					manager.setType(ManagerType.INSTANCE_ADMIN.getValue());
					manager.setStatus(RecordStatus.ACTIVE.getValue());
					manager.setCompany(company);
					imOps.add(manager);
					resultUser =  mapper.fromEntityToBO(manager);
					resultUser.setUserId(manager.getId());
					resultUser.setPassword(LoginUserBO.MASK_PASSWORD);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}			
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultUser);
		return response;
	}

	public ServiceResponse update(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserDetailsBO resultUser = null;
		UserDetailsBO reqUser = request.getRequestData(UserDetailsBO.class);
		if (reqUser!=null) {
			Long companyId = reqUser.getCompanyId();
			ServiceResponse response = checkInstanceStatus(companyId);
			result = response.getServiceResult().isResult();
			resultCode = response.getServiceResult().getResultCode();
			if(result){
				Long userId = reqUser.getUserId();
				CompanyInstance company = response.getResponseData(CompanyInstance.class);
				InstanceManager manager = imOps.findByCompanyAndId(company, userId);
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				if(manager!=null){
					manager = mapper.copyUpdatedBOToEntity(reqUser, manager, null);
					manager.setCompany(company);
					manager = imOps.update(manager);
					resultUser =  mapper.fromEntityToBO(manager);
					resultUser.setUserId(manager.getId());
					resultUser.setPassword(LoginUserBO.MASK_PASSWORD);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultUser);
		return response;
	}

	public ServiceResponse login(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserDetailsBO resultUser = null;
		LoginUserBO reqUser = request.getRequestData(LoginUserBO.class);
		if (reqUser != null && StringUtils.isNotBlank(reqUser.getLoginName())) {
			String loginName = reqUser.getLoginName().toLowerCase();
			String pass = reqUser.getPassword();
			InstanceManager manager = imOps.findUser(loginName);
			resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
			if(manager!=null){
				resultCode = ServiceResultCodes.USER_NOT_ACTIVE.getValue();
				if(manager.isActive()){
					CompanyInstance company = manager.getCompany();
					resultCode = ServiceResultCodes.INSTANCE_INACTIVE.getValue();
					if(company!=null && company.isActive()){
						String encpass = manager.getEncryptedPassword();
						resultCode = ServiceResultCodes.LOGIN_CREDENTIALS_INVALID.getValue();
						boolean validPass = Encryptor.getInstance().checkPassword(pass, encpass);
						if(validPass){
							resultUser = mapper.fromEntityToBO(manager);
							resultUser.setUserId(manager.getId());
							resultUser.setPassword(LoginUserBO.MASK_PASSWORD);
							resultUser.setRole(manager.getRole());
							resultUser.setCompanyName(company.getCompanyName());
							result = true;
							resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
						}
					}
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultUser);
		return response;
	}

	public ServiceResponse listManagers(ServiceRequest request) {
		boolean result = true;
		String resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
		List<UserDetailsBO> userList = new ArrayList<UserDetailsBO>();
		Long companyId = request.getRequestData(Long.class);
		ServiceResponse response = checkInstanceStatus(companyId);
		result = response.getServiceResult().isResult();
		resultCode = response.getServiceResult().getResultCode();
		if (result) {
			CompanyInstance company = response.getResponseData(CompanyInstance.class);
			if (company != null) {
				List<InstanceManager> mmList = imOps.findByCompany(company);
				for (InstanceManager mm : mmList) {
					UserDetailsBO resultUser = mapper.fromEntityToBO(mm);
					resultUser.setUserId(mm.getId());
					resultUser.setPassword(LoginUserBO.MASK_PASSWORD);
					userList.add(resultUser);
				}
				result = true;
				resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
			}
		}
		response = new ServiceResponse(result, resultCode);
		response.addResponseData(userList);
		return response;
	}
	
	@Override
	public ServiceResponse resetPassword(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserDetailsBO resultUser = null;
		LoginUserBO reqUser = request.getRequestData(LoginUserBO.class);
		if (reqUser!=null && StringUtils.isNotBlank(reqUser.getLoginName()) 
				&& reqUser.getUserId()!=null && StringUtils.isNotBlank(reqUser.getPassword())) {
			Long userId = reqUser.getUserId();
			InstanceVerification mver = ivOps.findByToken(reqUser.getFpToken());
			int context = VerificationContext.IM_FORGOT_PASSWORD.getValue();
			if(mver!=null && mver.isValidToken() && mver.getContext()==context && mver.getCreatedFor().longValue() == userId.longValue()){
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				String loginName = reqUser.getLoginName();
				InstanceManager manager = imOps.findById(userId);
				if(manager!=null){
					resultCode = ServiceResultCodes.USER_NOT_ACTIVE.getValue();
					if(manager.isActive()){
						resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
						if(StringUtils.equals(loginName, manager.getLoginName())){
							String epass = Encryptor.getInstance().encryptPassword(reqUser.getPassword());
							manager.setEncryptedPassword(epass);
							manager.setLastUpdated(new Timestamp(System.currentTimeMillis()));
							imOps.update(manager);
							resultUser = mapper.fromEntityToBO(manager);
							resultUser.setUserId(manager.getId());
							resultUser.setPassword(LoginUserBO.MASK_PASSWORD);
							resultUser.setRole(manager.getRole());
							ivOps.delete(mver);
							
							result = true;
							resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
						}
					}
				}
			}			
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultUser);
		return response;
	}

	@Override
	public ServiceResponse forgotPassword(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserDetailsBO reqUser = request.getRequestData(UserDetailsBO.class);
		if (reqUser!=null && StringUtils.isNotBlank(reqUser.getLoginName())) {
			String loginName = reqUser.getLoginName();
			InstanceManager manager = imOps.findUser(loginName);
			resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();			
			if(manager!=null){
				resultCode = ServiceResultCodes.USER_NOT_ACTIVE.getValue();
				if(manager.isActive()){
					InstanceVerification mv = ivOps.findByContextRefAndContext(manager.getId(), VerificationContext.IM_FORGOT_PASSWORD.getValue());
					if(mv==null){
						mv = new InstanceVerification();
						String token = Encryptor.getInstance().getRandomString(20);
						mv.setContext(VerificationContext.IM_FORGOT_PASSWORD.getValue());
						mv.setCreatedFor(manager.getId());
						mv.setContextRefKey(manager.getId());
						mv.setToken(token);
						ivOps.add(mv);
					}else if(!mv.isValidToken()){
						String token = Encryptor.getInstance().getRandomString(20);
						mv.setToken(token);
						mv.setCreatedOn(new Timestamp(System.currentTimeMillis()));
						ivOps.update(mv);
					}
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
					LOGGER.info("Forgot Password Verification Token : {}", mv.getToken());
					mailHelper.sendFPEmail(loginName, mv.getToken());
				}
			}
		}
		
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultCode);
		return response;
	}

	@Override
	public ServiceResponse verifyFPToken(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserDetailsBO resultUser = null;
		LoginUserBO reqUser = request.getRequestData(LoginUserBO.class);
		if (reqUser!=null && StringUtils.isNotBlank(reqUser.getFpToken())) {
			InstanceVerification mver = ivOps.findByToken(reqUser.getFpToken());
			int context = VerificationContext.IM_FORGOT_PASSWORD.getValue();
			if(mver!=null && mver.isValidToken() && mver.getContext()==context){
				Long userId = mver.getCreatedFor();
				InstanceManager manager = imOps.findById(userId);
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				if(manager!=null){
					resultCode = ServiceResultCodes.USER_NOT_ACTIVE.getValue();
					if(manager.isActive()){
						resultUser = new UserDetailsBO();
						resultUser.setUserId(userId);
						resultUser.setLoginName(manager.getLoginName());
						result = true;
						resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
					}
				}
			}
		}
		
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultUser);
		return response;
	}

	@Override
	public ServiceResponse delete(ServiceRequest request) {
		return null;
	}
	
	public ServiceResponse disable(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserDetailsBO resultUser = null;
		UserDetailsBO reqUser = request.getRequestData(UserDetailsBO.class);
		if (reqUser!=null) {
			Long companyId = reqUser.getCompanyId();
			ServiceResponse response = checkInstanceStatus(companyId);
			result = response.getServiceResult().isResult();
			resultCode = response.getServiceResult().getResultCode();
			if(result){
				Long userId = reqUser.getUserId();
				CompanyInstance company = response.getResponseData(CompanyInstance.class);
				InstanceManager manager = imOps.findByCompanyAndId(company, userId);
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				if(manager!=null){
					manager.setStatus(SystemCodes.RecordStatus.INACTIVE.getValue());
					manager.setCompany(company);
					manager = imOps.update(manager);
					resultUser =  mapper.fromEntityToBO(manager);
					resultUser.setUserId(manager.getId());
					resultUser.setPassword(LoginUserBO.MASK_PASSWORD);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultUser);
		return response;
	}

	
	public ServiceResponse enable(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserDetailsBO resultUser = null;
		UserDetailsBO reqUser = request.getRequestData(UserDetailsBO.class);
		if (reqUser!=null) {
			Long companyId = reqUser.getCompanyId();
			ServiceResponse response = checkInstanceStatus(companyId);
			result = response.getServiceResult().isResult();
			resultCode = response.getServiceResult().getResultCode();
			if(result){
				Long userId = reqUser.getUserId();
				CompanyInstance company = response.getResponseData(CompanyInstance.class);
				InstanceManager manager = imOps.findByCompanyAndId(company, userId);
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				if(manager!=null){
					manager.setStatus(SystemCodes.RecordStatus.ACTIVE.getValue());
					manager.setCompany(company);
					manager = imOps.update(manager);
					resultUser =  mapper.fromEntityToBO(manager);
					resultUser.setUserId(manager.getId());
					resultUser.setPassword(LoginUserBO.MASK_PASSWORD);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultUser);
		return response;
	}
	
}
package com.omt.cms.master.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.cms.core.common.Encryptor;
import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.common.SystemCodes.VerificationContext;
import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.LoginUserBO;
import com.omt.cms.core.service.bo.base.UserDetailsBO;
import com.omt.cms.entity.BaseManager.ManagerType;
import com.omt.cms.master.entity.MasterManager;
import com.omt.cms.master.entity.MasterVerification;
import com.omt.cms.master.repo.op.MasterManagerOperations;
import com.omt.cms.master.repo.op.MasterVerificationOperations;
import com.omt.cms.master.service.MasterManagerService;
import com.omt.cms.master.service.bo.mapper.UserDetailsMapper;
import com.omt.cms.master.service.util.ManagerMailHelper;

@Service
public class MasterManagerServiceImpl implements MasterManagerService{

	private static final Logger LOGGER = LoggerFactory.getLogger(MasterManagerServiceImpl.class);    

	@Autowired private MasterManagerOperations mmOps;
	@Autowired private MasterVerificationOperations mvOps;
	@Autowired private UserDetailsMapper mapper;
	@Autowired private ManagerMailHelper mailHelper;
	
	@Override
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserDetailsBO resultUser = null;
		Long userId = request.getRequestData(Long.class);
		if (userId!=null) {
			MasterManager manager = mmOps.findById(userId);
			resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
			if(manager!=null){
				resultUser =  mapper.fromEntityToBO(manager);
				resultUser.setUserId(manager.getId());
				resultUser.setPassword(LoginUserBO.MASK_PASSWORD);
				result = true;
				resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultUser);
		return response;
	}

	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserDetailsBO resultUser = null;
		UserDetailsBO reqUser = request.getRequestData(UserDetailsBO.class);
		if (reqUser != null && StringUtils.isNotBlank(reqUser.getLoginName())) {
			String loginName = reqUser.getLoginName().toLowerCase();
			boolean loginExists = mmOps.loginNameExists(loginName);
			resultCode = ServiceResultCodes.DUPLICATE_LOGINNAME.getValue();
			if(!loginExists){
				reqUser.setLoginName(loginName);
				MasterManager manager = mapper.createNewEntityFromBO(reqUser, null);
				manager.setId(null);
				String epass = Encryptor.getInstance().encryptPassword(reqUser.getPassword());
				manager.setEncryptedPassword(epass);
				manager.setType(ManagerType.SUPER_ADMIN.getValue());
				manager.setStatus(RecordStatus.ACTIVE.getValue());
				mmOps.add(manager);
				resultUser =  mapper.fromEntityToBO(manager);
				resultUser.setUserId(manager.getId());
				resultUser.setPassword(LoginUserBO.MASK_PASSWORD);
				result = true;
				resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultUser);
		return response;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserDetailsBO resultUser = null;
		UserDetailsBO reqUser = request.getRequestData(UserDetailsBO.class);
		if (reqUser!=null) {
			MasterManager manager = mmOps.findUser(reqUser.getLoginName());
			resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
			if(manager!=null){
				manager = mapper.copyUpdatedBOToEntity(reqUser, manager, null);
				manager = mmOps.update(manager);
				resultUser =  mapper.fromEntityToBO(manager);
				resultUser.setUserId(manager.getId());
				resultUser.setPassword(LoginUserBO.MASK_PASSWORD);
				result = true;
				resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultUser);
		return response;
	}

	@Override
	public ServiceResponse login(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserDetailsBO resultUser = null;
		LoginUserBO reqUser = request.getRequestData(LoginUserBO.class);
		if (reqUser != null && StringUtils.isNotBlank(reqUser.getLoginName())) {
			String loginName = reqUser.getLoginName().toLowerCase();
			String pass = reqUser.getPassword();
			MasterManager manager = mmOps.findUser(loginName);
			resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
			if(manager!=null){
				resultCode = ServiceResultCodes.USER_NOT_ACTIVE.getValue();
				if(manager.isActive()){
					String encpass = manager.getEncryptedPassword();
					resultCode = ServiceResultCodes.LOGIN_CREDENTIALS_INVALID.getValue();
					boolean validPass = Encryptor.getInstance().checkPassword(pass, encpass);
					if(validPass){
						resultUser = mapper.fromEntityToBO(manager);
						resultUser.setUserId(manager.getId());
						resultUser.setPassword(LoginUserBO.MASK_PASSWORD);
						resultUser.setRole(manager.getRole());
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
	public ServiceResponse listManagers(ServiceRequest request) {
		boolean result = true;
		String resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
		List<UserDetailsBO> userList = new ArrayList<UserDetailsBO>();
		List<MasterManager> mmList = mmOps.listManagers();
		for(MasterManager mm : mmList){
			UserDetailsBO resultUser = mapper.fromEntityToBO(mm);
			resultUser.setUserId(mm.getId());
			resultUser.setPassword(LoginUserBO.MASK_PASSWORD);
			userList.add(resultUser);
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
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
			MasterVerification mver = mvOps.findByToken(reqUser.getFpToken());
			int context = VerificationContext.MM_FORGOT_PASSWORD.getValue();
			if(mver!=null && mver.isValidToken() && mver.getContext()==context && mver.getCreatedFor().longValue() == userId.longValue()){
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				String loginName = reqUser.getLoginName();
				MasterManager manager = mmOps.findById(userId);
				if(manager!=null){
					resultCode = ServiceResultCodes.USER_NOT_ACTIVE.getValue();
					if(manager.isActive()){
						resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
						if(StringUtils.equals(loginName, manager.getLoginName())){
							String epass = Encryptor.getInstance().encryptPassword(reqUser.getPassword());
							manager.setEncryptedPassword(epass);
							manager.setLastUpdated(new Timestamp(System.currentTimeMillis()));
							mmOps.update(manager);
							resultUser = mapper.fromEntityToBO(manager);
							resultUser.setUserId(manager.getId());
							resultUser.setPassword(LoginUserBO.MASK_PASSWORD);
							resultUser.setRole(manager.getRole());
							mvOps.delete(mver);
							
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
			MasterManager manager = mmOps.findUser(loginName);
			resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();			
			if(manager!=null){
				resultCode = ServiceResultCodes.USER_NOT_ACTIVE.getValue();
				if(manager.isActive()){
					MasterVerification mv = mvOps.findByContextRefAndContext(manager.getId(), VerificationContext.MM_FORGOT_PASSWORD.getValue());
					if(mv==null){
						mv = new MasterVerification();
						String token = Encryptor.getInstance().getRandomString(20);
						mv.setContext(VerificationContext.MM_FORGOT_PASSWORD.getValue());
						mv.setCreatedFor(manager.getId());
						mv.setContextRefKey(manager.getId());
						mv.setToken(token);
						mvOps.add(mv);
					}else if(!mv.isValidToken()){
						String token = Encryptor.getInstance().getRandomString(20);
						mv.setToken(token);
						mv.setCreatedOn(new Timestamp(System.currentTimeMillis()));
						mvOps.update(mv);
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
			MasterVerification mver = mvOps.findByToken(reqUser.getFpToken());
			int context = VerificationContext.MM_FORGOT_PASSWORD.getValue();
			if(mver!=null && mver.isValidToken() && mver.getContext()==context){
				Long userId = mver.getCreatedFor();
				MasterManager manager = mmOps.findById(userId);
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
}
package com.omt.cms.user.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.cms.core.common.DateHelper;
import com.omt.cms.core.common.SystemCodes.ROLE;
import com.omt.cms.core.common.SystemCodes.UserStatus;
import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;

/**
 * @author Shiva Kalgudi
 */

import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.LocalUserBO;
import com.omt.cms.core.service.bo.base.UsersBO;
import com.omt.cms.core.service.bo.base.UsersLoginBO;
import com.omt.cms.core.service.bo.base.UsersLoginBO.LoginUser;
import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserVerification;
import com.omt.cms.user.repo.op.UserOperations;
import com.omt.cms.user.service.UserService;
import com.omt.cms.user.service.bo.UserBO;
import com.omt.cms.user.service.bo.mapper.UserMapper;
import com.omt.cms.user.service.util.UserMailHelper;
import com.omt.cms.user.service.util.UserSMSHelper;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);    
	@Autowired private UserOperations userOp;
	@Autowired private UserMapper mapper;
	@Autowired private UserMailHelper mailHelper;
	@Autowired private UserSMSHelper smsHelper;

	@Override
	public ServiceResponse register(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserBO resultBO = null;
		UserBO requestBO = request.getRequestData(UserBO.class);
		try {
			if(requestBO != null && StringUtils.isNotBlank(requestBO.getLoginName()) && StringUtils.isNotBlank(requestBO.getPassword())){
				String loginName = requestBO.getLoginName().toLowerCase();
				String email = null;
				if(StringUtils.isNotBlank(requestBO.getUserEmail())){
					email = requestBO.getUserEmail().toLowerCase();
				}
				boolean loginExists = userOp.loginNameExists(loginName);
				resultCode = ServiceResultCodes.DUPLICATE_LOGINNAME.getValue();
				if(!loginExists){
					requestBO.setLoginName(loginName);
					requestBO.setUserEmail(email);
					User user = mapper.createNewEntityFromBO(requestBO, null);
					String password = requestBO.getPassword();
					user.encryptAndSetPassword(password);
					user.setId(null);
					user.setUserStatus(UserStatus.ACTIVE.getValue());
					if(StringUtils.isBlank(user.getTagRole())){
						user.setTagRole(ROLE.USER.getValue());	
					}
					user = userOp.add(user);
					resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
					if(user!=null){
						resultBO = new UserBO();
						resultBO.setId(user.getId());
						resultBO.setLoginName(loginName);
						resultBO.setUserStatus(user.getUserStatus());
						result = true;
						resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
						sendMailVerifyToken(user);
						sendPhoneVerifyToken(user);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occurred while registering new user : {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultBO);
		return response;
	}

	private void sendMailVerifyToken(User user) {
		String email = user.getUserEmail();
		if(StringUtils.isNotBlank(email)){
			UserVerification userVT = userOp.genMailVerToken(user.getId(), email);
			mailHelper.sendMailVerifyEmail(email, userVT.getToken());
		}
	}

	private void sendPhoneVerifyToken(User user) {
		String phoneNbr = user.getUserContact();
		if(StringUtils.isNotBlank(phoneNbr)){
			UserVerification userVT = userOp.genPhoneVerToken(user.getId(), phoneNbr);
			smsHelper.sendPhoneVerifySMS(phoneNbr, userVT.getToken());
		}
	}

	@Override
	public ServiceResponse verifyEMail(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserBO resultBO = null;
		UserBO requestBO = request.getRequestData(UserBO.class);
		try {
			if (requestBO!=null && StringUtils.isNotBlank(requestBO.getMailVerToken())) {
				UserVerification userVer = userOp.findRecordByMailVerToken(requestBO.getMailVerToken());
				if(userVer!=null){
					Long userId = userVer.getCreatedFor();
					User user = userOp.findById(userId);
					resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
					if(user!=null){
						resultCode = ServiceResultCodes.OPERATION_DENIED.getValue();
						if(user.isActive()){
							user = userOp.verifyMailToken(user, userVer);
							if(user.getEmailValid()){
								resultBO = new UserBO();
								resultBO.setId(userId);
								resultBO.setLoginName(user.getLoginName());
								resultBO.setEmailValid(true);
								resultBO.setUserEmail(user.getUserEmail());
								result = true;
								resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();							
							}
						}
					}
				}
			}
		}catch (Exception e) {
			LOGGER.error("Error occurred while checking login-name : {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultBO);
		return response;
	}

	@Override
	public ServiceResponse loginNamesInUse(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UsersLoginBO requestBO = request.getRequestData(UsersLoginBO.class);
		try {
			if(requestBO != null && !requestBO.getUsers().isEmpty()){
				for(LoginUser lu : requestBO.getUsers()){
					String loginName = lu.getLoginName().toLowerCase();
					result = userOp.loginNameExists(loginName);
					lu.setInUse(result);
				}
			}
			result = true;
			resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
		}catch (Exception e) {
			LOGGER.error("Error occurred while checking login-name : {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(requestBO);
		return response;
	}

	@Override
	public ServiceResponse loginNameInUse(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		String reqLN = request.getRequestData(String.class);
		LoginUser resp = new LoginUser();
		try {
			if(StringUtils.isNotBlank(reqLN)){
				String loginName = reqLN.toLowerCase();
 				boolean exists = userOp.loginNameExists(loginName);
				resp.setInUse(exists);
				resp.setLoginName(loginName);
				result = true;
				resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
			}
		}catch (Exception e) {
			LOGGER.error("Error occurred while checking login-name : {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resp);
		return response;
	}

	@Override
	public ServiceResponse addUsers(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UsersBO requestBO = request.getRequestData(UsersBO.class);
		try {
			if(requestBO != null && !requestBO.getUsers().isEmpty()){
				for(LocalUserBO lu:requestBO.getUsers()){
					String loginName = lu.getLoginName().toLowerCase();
					boolean loginExists = userOp.loginNameExists(loginName);
					lu.setLoginName(loginName);
					if(loginExists){
						requestBO.addToFailedList(loginName);
					}else{
						User nwUser = new User();
						BeanUtils.copyProperties(lu, nwUser);
						nwUser.setId(null);
						if(StringUtils.isBlank(nwUser.getUserEmail())){
							nwUser.setUserEmail(loginName);	
						}
						nwUser.setUserStatus(UserStatus.ACTIVE.getValue());
						if(StringUtils.isBlank(lu.getTagRole())){
							nwUser.setTagRole(ROLE.USER.getValue());	
						}
						//create temp password
						String randPass = nwUser.createAndSetRandomPass();
						nwUser = userOp.add(nwUser);
						lu.setId(nwUser.getId());
						lu.setUserStatus(nwUser.getUserStatus());
						lu.setTagRole(nwUser.getTagRole());
						mailHelper.mailLoginDetails(nwUser.getUserEmail(), loginName, randPass);
						requestBO.addToSuccessList(loginName);
					}
				}
			}
			result = true;
			resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
		}catch (Exception e) {
			LOGGER.error("Error occurred while checking login-name : {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(requestBO);
		return response;
	}

	@Override
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		Long userId = request.getRequestData(Long.class);
		UserBO respBO = null;
		try {
			if(userId!=null){
				User entity = userOp.findById(userId);
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				if(entity!=null){
					respBO = mapper.fromEntityToBO(entity);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occurred while registering new user : {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(respBO);
		return response;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserBO reqBO = request.getRequestData(UserBO.class);
		UserBO respBO = null;
		try {
			if(reqBO!=null && reqBO.getId()!=null){
				User entity = userOp.findById(reqBO.getId());
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				if(entity!=null){
					entity = mapper.copyUpdatedBOToEntity(reqBO, entity, null);
					userOp.update(entity);
					respBO = mapper.fromEntityToBO(entity);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occurred while registering new user : {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(respBO);
		return response;
	}

	@Override
	public ServiceResponse listUsers(ServiceRequest request) {
		boolean result = true;
		String resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
		List<UserBO> respList = new ArrayList<>();
		try {
			List<User> users = userOp.findAll();
			for(User user:users){
				UserBO userBO = mapper.fromEntityToBO(user);
				respList.add(userBO);
			}
		} catch (Exception e) {
			LOGGER.error("Error occurred while registering new user : {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(respList);
		return response;
	}

	@Override
	public ServiceResponse delete(ServiceRequest request) {
		return null;
	}

	@Override
	public ServiceResponse add(ServiceRequest request) {
		return null;
	}


	@Override
	public ServiceResponse forgotPassword(ServiceRequest request) {
		String resultMsg = null;
		UserBO resUser = null;
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserBO reqUser = request.getRequestData(UserBO.class);
		if (reqUser!=null && StringUtils.isNotBlank(reqUser.getLoginName())) {
			String loginName = reqUser.getLoginName().toLowerCase();
			try{
				User user = userOp.findUser(loginName);
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();			
				if(user!=null){
					resultCode = ServiceResultCodes.USER_NOT_ACTIVE.getValue();
					if(user.isActive()){
						if(reqUser.isUsePhone()){
							resultCode = ServiceResultCodes.OPERATION_DENIED.getValue();
							resultMsg = "phone not found";
							if(StringUtils.isNotBlank(user.getUserContact())){
								resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
								UserVerification ver = userOp.genForgotPassTokenPhone(user.getId(), user.getUserContact());
								if(ver!=null){
									smsHelper.sendFPVerifySMS(user.getUserContact(), ver.getToken());
									resUser = new UserBO();
									resUser.setUserContact(user.getUserContact());
									resUser.setLoginName(loginName);
									result = true;
									resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
								}
							}
						}else{
							resultCode = ServiceResultCodes.OPERATION_DENIED.getValue();
							resultMsg = "Email not found";
							if(StringUtils.isNotBlank(user.getUserEmail())){
								resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
								UserVerification ver = userOp.genForgotPassTokenEmail(user.getId(), user.getUserEmail());
								if(ver!=null){
									mailHelper.sendForgotPasswordEmail(user.getUserEmail(), ver.getToken());
									resUser = new UserBO();
									resUser.setUserEmail(user.getUserEmail());
									resUser.setLoginName(loginName);
									result = true;
									resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
								}
							}
						}
					}
				}
			}catch (Exception e) {
				LOGGER.error("Error occurred : {}", e);
				resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(resUser);
		return response;
	}
	
	
	@Override
	public ServiceResponse verifyFPToken(ServiceRequest request) {
		String resultMsg = null;
		UserBO resUser = null;
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserBO reqUser = request.getRequestData(UserBO.class);
		if (reqUser!=null && StringUtils.isNotBlank(reqUser.getFpToken()) && StringUtils.isNotBlank(reqUser.getLoginName())) {
			String fpToken = reqUser.getFpToken();
			String loginName = reqUser.getLoginName().toLowerCase();
			try{
				User user = userOp.findUser(loginName);
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				if(user!=null){
					resultCode = ServiceResultCodes.USER_NOT_ACTIVE.getValue();
					if(user.isActive()){
						Long userId = user.getId();
						resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
						UserVerification uv = null;
						if(reqUser.isUsePhone()){
							uv = userOp.findRecordByPhoneFPVerToken(userId, fpToken);
						}else{
							uv = userOp.findRecordByEmailFPVerToken(userId, fpToken);
						}
						if(uv!=null){
							resUser = new UserBO();
							resUser.setLoginName(user.getLoginName());
							resUser.setId(user.getId());
							result = true;
							resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
						}
					}
				}
			}catch (Exception e) {
				LOGGER.error("Error occurred : {}", e);
				resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
			}
		}

		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(resUser);
		return response;
	}

	@Override
	public ServiceResponse resetPassword(ServiceRequest request) {
		String resultMsg = null;
		UserBO resUser = null;
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserBO reqUser = request.getRequestData(UserBO.class);
		if (reqUser!=null 
				&& StringUtils.isNotBlank(reqUser.getFpToken()) 
				&& StringUtils.isNotBlank(reqUser.getLoginName())
				&& StringUtils.isNotBlank(reqUser.getPassword())) {
			String fpToken = reqUser.getFpToken();
			String loginName = reqUser.getLoginName().toLowerCase();
			try{
				User user = userOp.findUser(loginName);
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				if (user!=null) {
					resultCode = ServiceResultCodes.USER_NOT_ACTIVE.getValue();
					if (user.isActive()) {
						resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
						UserVerification uv = null;
						Long userId = user.getId();
						if(reqUser.isUsePhone()){
							uv = userOp.findRecordByPhoneFPVerToken(userId, fpToken);
						}else{
							uv = userOp.findRecordByEmailFPVerToken(userId, fpToken);
						}
						if (uv != null) {
							if (userId.longValue() == uv.getContextRefKey().longValue()) {
								String password = reqUser.getPassword();
								if (StringUtils.isNotBlank(password)) {
									user.encryptAndSetPassword(password);
									user.setLastUpdated(DateHelper.getCurTimestamp());
									if(reqUser.isUsePhone()){
										user.setPhoneValid(true);	
									}else{
										user.setEmailValid(true);	
									}
									userOp.update(user);
									userOp.deleteVerRecord(uv);

									resUser = new UserBO();
									resUser.setLoginName(user.getLoginName());
									resUser.setId(user.getId());

									result = true;
									resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
								}
							}
						}
					}
				}
			}catch (Exception e) {
				LOGGER.error("Error occurred : {}", e);
				resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(resUser);
		return response;
	}

	@Override
	public ServiceResponse resendEmailVerifyToken(ServiceRequest request) {
		String resultMsg = null;
		UserBO resUser = null;
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		String loginName = request.getRequestData(String.class);
		try {
			if(StringUtils.isNotBlank(loginName)){
				loginName = loginName.toLowerCase();
				User entity = userOp.findUser(loginName);
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				if(entity!=null){
					resultCode = ServiceResultCodes.USER_NOT_ACTIVE.getValue();
					if(entity.isActive()){
						String email = entity.getUserEmail();	
						resultCode = ServiceResultCodes.OPERATION_DENIED.getValue();
						resultMsg = "email not registered";
						if(StringUtils.isNotBlank(email)){
							resultMsg = "email already verified";
							if(!entity.getEmailValid()){
								UserVerification userVT = userOp.genMailVerToken(entity.getId(), email);
								mailHelper.sendMailVerifyEmail(email, userVT.getToken());
								resUser = new UserBO();
								resUser.setUserEmail(entity.getUserEmail());
								resUser.setLoginName(loginName);
								result = true;
								resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
								resultMsg = "mail verification token sent";
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occurred while registering new user : {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
			resultMsg = e.getMessage();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(resUser);
		return response;
	}

	@Override
	public ServiceResponse changeUserEmail(ServiceRequest request) {
		String resultMsg = null;
		UserBO resUser = null;
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserBO reqUser = request.getRequestData(UserBO.class);
		try {
			if(reqUser!=null && StringUtils.isNotBlank(reqUser.getUserEmail()) && StringUtils.isNotBlank(reqUser.getLoginName())){
				String loginName = reqUser.getLoginName().toLowerCase();
				String email = reqUser.getUserEmail();
				User entity = userOp.findUser(loginName);
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				if(entity!=null){
					resultCode = ServiceResultCodes.USER_NOT_ACTIVE.getValue();
					if(entity.isActive()){
						entity.setUserEmail(email);
						entity.setLastUpdated(new Timestamp(System.currentTimeMillis()));
						entity.setEmailValid(false);
						userOp.update(entity);
						UserVerification userVT = userOp.genMailVerToken(entity.getId(), email);
						mailHelper.sendMailVerifyEmail(email, userVT.getToken());
						resUser = new UserBO();
						resUser.setUserEmail(entity.getUserEmail());
						resUser.setLoginName(loginName);
						result = true;
						resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
						resultMsg = "mail verification token sent";
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occurred while registering new user : {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
			resultMsg = e.getMessage();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(resUser);
		return response;
	}

	@Override
	public ServiceResponse verifyPhone(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserBO resultBO = null;
		UserBO requestBO = request.getRequestData(UserBO.class);
		try {
			if (requestBO!=null 
					&& StringUtils.isNotBlank(requestBO.getPhoneVerToken()) 
					&& StringUtils.isNotBlank(requestBO.getLoginName())) {
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				String loginName = requestBO.getLoginName().toLowerCase();
				String token = requestBO.getPhoneVerToken();
				User user = userOp.findUser(loginName);
				if (user!=null) {
					Long userId = user.getId();
					resultCode = ServiceResultCodes.OPERATION_DENIED.getValue();
					if (user.isActive()) {
						resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
						UserVerification userVer = userOp.findRecordByUserPhoneVerToken(userId, token);
						if (userVer != null) {
							user = userOp.verifyPhoneToken(user, userVer);
							if (user.getPhoneValid()) {
								resultBO = new UserBO();
								resultBO.setId(userId);
								resultBO.setLoginName(loginName);
								resultBO.setPhoneValid(true);
								resultBO.setUserContact(user.getUserContact());
								result = true;
								resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
							}
						}
					}
				}
			}
		}catch (Exception e) {
			LOGGER.error("Error occurred while checking login-name : {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultBO);
		return response;
	}


	@Override
	public ServiceResponse resendPhoneVerifyToken(ServiceRequest request) {
		String resultMsg = null;
		UserBO resUser = null;
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		String loginName = request.getRequestData(String.class);
		try {
			if(StringUtils.isNotBlank(loginName)){
				loginName = loginName.toLowerCase();
				User entity = userOp.findUser(loginName);
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				if(entity!=null){
					resultCode = ServiceResultCodes.USER_NOT_ACTIVE.getValue();
					if(entity.isActive()){
						String phone = entity.getUserContact();
						resultCode = ServiceResultCodes.OPERATION_DENIED.getValue();
						resultMsg = "phone not registered";
						if(StringUtils.isNotBlank(phone)){
							resultMsg = "phone already verified";
							if(!entity.getPhoneValid()){
								UserVerification userVT = userOp.genPhoneVerToken(entity.getId(), phone);
								smsHelper.sendPhoneVerifySMS(phone, userVT.getToken());
								resUser = new UserBO();
								resUser.setUserContact(phone);
								resUser.setLoginName(loginName);
								result = true;
								resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
								resultMsg = "phone verification token sent";
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occurred while registering new user : {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
			resultMsg = e.getMessage();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(resUser);
		return response;
	}

	@Override
	public ServiceResponse changeUserPhone(ServiceRequest request) {
		String resultMsg = null;
		UserBO resUser = null;
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserBO reqUser = request.getRequestData(UserBO.class);
		try {
			if(reqUser!=null && StringUtils.isNotBlank(reqUser.getUserContact()) && StringUtils.isNotBlank(reqUser.getLoginName())){
				String loginName = reqUser.getLoginName().toLowerCase();
				String phone = reqUser.getUserContact();
				User user = userOp.findUser(loginName);
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				if(user!=null){
					resultCode = ServiceResultCodes.USER_NOT_ACTIVE.getValue();
					if(user.isActive()){
						user.setUserContact(phone);
						user.setLastUpdated(DateHelper.getCurTimestamp());
						user.setPhoneValid(false);
						userOp.update(user);
						UserVerification userVT = userOp.genPhoneVerToken(user.getId(), phone);
						smsHelper.sendPhoneVerifySMS(phone, userVT.getToken());

						resUser = new UserBO();
						resUser.setUserContact(phone);
						resUser.setLoginName(loginName);
						result = true;
						resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
						resultMsg = "mail verification token sent";
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occurred while registering new user : {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
			resultMsg = e.getMessage();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(resUser);
		return response;
	}
	@Override
	public ServiceResponse updateLoginName(ServiceRequest request) {
		String resultMsg = null;
		UserBO resUser = null;
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		String loginName= request.getRequestData(String.class);
		try {
			if(loginName!=null && StringUtils.isNotBlank(loginName )){

				User entity = userOp.findUser(loginName);
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
				if(entity!=null){
					String []sArr=loginName.split("@");
					Date today=new Date();
					String nLoginName=today.getTime()+"_"+sArr[0]+"#"+sArr[1]; 
					entity.setLoginName(nLoginName);
					entity.setLastUpdated(new Timestamp(System.currentTimeMillis()));
					entity.setEmailValid(false);
					userOp.update(entity);
					resUser = new UserBO();
					resUser.setLoginName(nLoginName);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
					resultMsg = "mail verification token sent";

				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occurred while registering new user : {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
			resultMsg = e.getMessage();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(resUser);
		return response;
	}

	
	@Override
	public ServiceResponse getFPToken(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		String reqLN = request.getRequestData(String.class);
		String token = null;
		try {
			if(StringUtils.isNotBlank(reqLN)){
				String loginName = reqLN.toLowerCase();
				User user = userOp.findUser(loginName);
				if(user!=null){
					UserVerification uv = userOp.getForgotPasswordToken(user.getId());
					if(uv!=null){
						token = uv.getToken();
						result = true;
						resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
					}
				}
			}
		}catch (Exception e) {
			LOGGER.error("Error occurred while checking login-name : {}", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(token);
		return response;
	}
	

	@Override
	public String forgotPasswordClone(ServiceRequest request) {
		String resultMsg = null;
		UserBO resUser = null;
		boolean result = false;
		String loginName = null;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UserBO reqUser = request.getRequestData(UserBO.class);
		if (reqUser!=null && StringUtils.isNotBlank(reqUser.getLoginName())) {
			loginName = reqUser.getLoginName().toLowerCase();
			try{
				User user = userOp.findUser(loginName);
				resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();			
				if(user!=null){
					resultCode = ServiceResultCodes.USER_NOT_ACTIVE.getValue();
					if(user.isActive()){
						if(reqUser.isUsePhone()){
							resultCode = ServiceResultCodes.OPERATION_DENIED.getValue();
							resultMsg = "phone not found";
							if(StringUtils.isNotBlank(user.getUserContact())){
								resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
								UserVerification ver = userOp.genForgotPassTokenPhone(user.getId(), user.getUserContact());
								if(ver!=null){
									smsHelper.sendFPVerifySMS(user.getUserContact(), ver.getToken());
									resUser = new UserBO();
									resUser.setUserContact(user.getUserContact());
									resUser.setLoginName(loginName);
									result = true;
									resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
								}
							}
						}else{
							resultCode = ServiceResultCodes.OPERATION_DENIED.getValue();
							resultMsg = "Email not found";
							if(StringUtils.isNotBlank(user.getUserEmail())){
								resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
								UserVerification ver = userOp.genForgotPassTokenEmail(user.getId(), user.getUserEmail());
								if(ver!=null){
									mailHelper.sendForgotPasswordEmail(user.getUserEmail(), ver.getToken());
									resUser = new UserBO();
									resUser.setUserEmail(user.getUserEmail());
									resUser.setLoginName(loginName);
									result = true;
									resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
								}
							}
						}
					}
				}
			}catch (Exception e) {
				LOGGER.error("Error occurred : {}", e);
				resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
			}
		}
		//ServiceResponse response = new ServiceResponse(result, resultCode);
		//response.getServiceResult().setResultMessage(resultMsg);
		//response.addResponseData(resUser);
		return loginName;
	}	
	
	@Override
	public String fetchFPToken(String loginName) {
		//boolean result = false;
		//String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		//String reqLN = request.getRequestData(String.class);
		String token = null;
			
		try {
			if(StringUtils.isNotBlank(loginName)){
				//String loginName = reqLN.toLowerCase();
				User user = userOp.findUser(loginName);
				if(user!=null){
					UserVerification uv = userOp.getForgotPasswordToken(user.getId());
					if(uv!=null){
						token = uv.getToken();
						//result = true;
						//resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
					}
				}
			}
		}catch (Exception e) {
			//result = false;
			LOGGER.error("Error occurred while checking login-name : {}", e);
			//resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		//ServiceResponse response = new ServiceResponse(result, resultCode);
		//response.addResponseData(token);			

		return token;
	}
}

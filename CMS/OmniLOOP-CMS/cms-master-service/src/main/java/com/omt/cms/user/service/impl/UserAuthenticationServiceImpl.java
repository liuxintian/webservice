package com.omt.cms.user.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.LoginUserBO;
import com.omt.cms.core.service.bo.base.MobileUserDetailsBO;
import com.omt.cms.user.entity.User;
import com.omt.cms.user.repo.op.UserOperations;
import com.omt.cms.user.service.UserAuthenticationService;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

	@Autowired private UserOperations userOps;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userOps.findUser(username);
		if(user==null){
			throw new UsernameNotFoundException("User name not found:" + username);
		}
		MobileUserDetailsBO userDetails = copyUserDetails(user);
		return userDetails;
	}

	@Override
	public ServiceResponse autheticate(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		MobileUserDetailsBO resultUser = null;
		LoginUserBO reqLogin = request.getRequestData(LoginUserBO.class);
		if(reqLogin!=null && StringUtils.isNotBlank(reqLogin.getLoginName())){
			String loginName = reqLogin.getLoginName().toLowerCase();
			String reqPass = reqLogin.getPassword();
			User user = userOps.findUser(loginName);
			resultCode = ServiceResultCodes.USER_NOT_FOUND.getValue();
			if(user!=null){
				resultCode = ServiceResultCodes.USER_NOT_ACTIVE.getValue();
				if(user.isActive()){
					resultCode = ServiceResultCodes.LOGIN_CREDENTIALS_INVALID.getValue();
					if(user.comparePassword(reqPass)){
						resultUser = copyUserDetails(user);
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

	private MobileUserDetailsBO copyUserDetails(User user) {
		MobileUserDetailsBO resultUser = new MobileUserDetailsBO();
		resultUser.setUserId(user.getId());
		resultUser.setLoginName(user.getLoginName());
		resultUser.setName(user.getUserName());
		resultUser.setUserEmail(user.getUserEmail());
		resultUser.setEmailValid(user.getEmailValid());
		resultUser.setRole(user.getTagRole());
		resultUser.setStatus(user.getUserStatus());
		resultUser.setUserContact(user.getUserContact());
		resultUser.setDateOfBirth(user.getDateOfBirth());
		resultUser.setTitle(user.getTitle());
		resultUser.setUserUId(user.getUserUId());
		return resultUser;
	}

	@Override
	public ServiceResponse addCurrentCompanyGroups(ServiceRequest request) {
		
		return null;
	}

}

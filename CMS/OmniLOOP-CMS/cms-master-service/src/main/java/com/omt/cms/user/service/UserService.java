package com.omt.cms.user.service;

import org.springframework.stereotype.Service;

import com.omt.cms.core.service.base.CrudService;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Service
public interface UserService extends CrudService{
	
	public  ServiceResponse register(ServiceRequest request);

	public  ServiceResponse verifyEMail(ServiceRequest request);
	
	public  ServiceResponse verifyPhone(ServiceRequest request);

	public  ServiceResponse loginNamesInUse(ServiceRequest request);
	
	public  ServiceResponse loginNameInUse(ServiceRequest request);

	public  ServiceResponse addUsers(ServiceRequest request);
	
	public  ServiceResponse listUsers(ServiceRequest request);

	public  ServiceResponse resetPassword(ServiceRequest request);
	
	public  ServiceResponse forgotPassword(ServiceRequest request);
	
	public  ServiceResponse verifyFPToken(ServiceRequest request);

	public  ServiceResponse resendEmailVerifyToken(ServiceRequest request);

	public  ServiceResponse changeUserEmail(ServiceRequest request);

	public  ServiceResponse resendPhoneVerifyToken(ServiceRequest request);

	public  ServiceResponse changeUserPhone(ServiceRequest request);
	
	public ServiceResponse updateLoginName(ServiceRequest request);
	
	public ServiceResponse getFPToken(ServiceRequest request);
	
	public  String forgotPasswordClone(ServiceRequest request);
	
	public String fetchFPToken(String loginName);
	
}

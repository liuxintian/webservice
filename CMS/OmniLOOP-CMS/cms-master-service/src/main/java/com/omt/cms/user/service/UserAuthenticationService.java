package com.omt.cms.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Service
public interface UserAuthenticationService extends UserDetailsService {

	public ServiceResponse autheticate(ServiceRequest request);
	
	public ServiceResponse addCurrentCompanyGroups(ServiceRequest request);

}

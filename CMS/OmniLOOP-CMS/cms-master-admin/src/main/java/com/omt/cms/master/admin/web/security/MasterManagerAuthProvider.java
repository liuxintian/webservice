package com.omt.cms.master.admin.web.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.bo.base.LoginUserBO;
import com.omt.cms.core.service.bo.base.UserDetailsBO;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.master.service.MasterManagerService;

public class MasterManagerAuthProvider implements AuthenticationProvider {

	@Autowired private MasterManagerService mmService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getPrincipal() != null ? authentication.getPrincipal().toString() : null;
		String password = authentication.getCredentials() != null ? authentication.getCredentials().toString() : null;
 		ServiceRequest request = new ServiceRequest();
		LoginUserBO ulogin = new LoginUserBO(email, password);
		request.addRequestData(ulogin);
		ServiceResponse response = mmService.login(request);
		UsernamePasswordAuthenticationToken token = null;
        if(response.getServiceResult().isResult()){
        	UserDetailsBO user = response.getResponseData(UserDetailsBO.class);
            String roleName = user.getRole();
            token = new UsernamePasswordAuthenticationToken(email, password, Arrays.<GrantedAuthority>asList(new SimpleGrantedAuthority(roleName)));
            token.setDetails(user);
        }else{
        	if(ServiceResultCodes.LOGIN_CREDENTIALS_INVALID.getValue().equals(response.getServiceResult().getResultCode())){
        		throw new BadCredentialsException(response.getServiceResult().getResultCode());	
        	}else {
        		throw new DisabledException(response.getServiceResult().getResultCode());
        	}
        }
        return token;
  	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}

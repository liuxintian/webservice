package com.omt.cms.mobile.security;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.omt.cms.core.common.SystemCodes.ROLE;
import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.LoginUserBO;
import com.omt.cms.core.service.bo.base.MobileUserDetailsBO;
import com.omt.cms.user.service.UserAuthenticationService;


public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired private UserAuthenticationService authService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginName = authentication.getPrincipal() != null ? authentication.getPrincipal().toString() : null;
        String password = authentication.getCredentials() != null ? authentication.getCredentials().toString() : null;
 		ServiceRequest request = new ServiceRequest();
 		LoginUserBO login = new LoginUserBO(loginName, password);
		request.addRequestData(login);
		ServiceResponse response = authService.autheticate(request);
		UsernamePasswordAuthenticationToken token = null;
        if(response.getServiceResult().isResult()){
        	MobileUserDetailsBO user = response.getResponseData(MobileUserDetailsBO.class);
        	 String roleName = user.getRole();
        	if(StringUtils.isBlank(roleName)){
                roleName = ROLE.USER.toString();
                user.setRole(roleName);
        	}
            token = new UsernamePasswordAuthenticationToken(loginName, password, Arrays.<GrantedAuthority>asList(new SimpleGrantedAuthority(roleName)));
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
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}

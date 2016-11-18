package com.omt.cms.cust.admin.web.security;

import java.util.Arrays;
import java.util.List;

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
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.LoginUserBO;
import com.omt.cms.core.service.bo.base.UserDetailsBO;
import com.omt.cms.cust.service.DataMenuProxyService;
import com.omt.cms.cust.service.InstanceManagerService;
import com.omt.cms.master.service.bo.CompanyBO;
import com.omt.cms.master.service.bo.DataMenuBO;


public class InstanceManagerAuthProvider implements AuthenticationProvider {

	@Autowired private InstanceManagerService imService;
	@Autowired private DataMenuProxyService dmService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getPrincipal() != null ? authentication.getPrincipal().toString() : null;
		String password = authentication.getCredentials() != null ? authentication.getCredentials().toString() : null;
 		ServiceRequest request = new ServiceRequest();
		LoginUserBO ulogin = new LoginUserBO(email, password);
		request.addRequestData(ulogin);
		ServiceResponse response = imService.login(request);
		UsernamePasswordAuthenticationToken token = null;
        if(response.getServiceResult().isResult()){
        	UserDetailsBO user = response.getResponseData(UserDetailsBO.class);
        	ServiceRequest req=new ServiceRequest();
        	CompanyBO cp=new CompanyBO();
        	cp.setId(user.getCompanyId());
        	req.addRequestData(cp);
        	ServiceResponse res=dmService.readAll(req);
        	@SuppressWarnings("unchecked")
			List<DataMenuBO> dm=res.getResponseData(List.class);
            user.setDataMenus(dm);
        	String roleName = user.getRole();
            token = new UsernamePasswordAuthenticationToken(email, password, Arrays.<GrantedAuthority>asList(new SimpleGrantedAuthority(roleName)));
            token.setDetails(user);
        }else{
        	String resultCode = response.getServiceResult().getResultCode();
        	if(ServiceResultCodes.LOGIN_CREDENTIALS_INVALID.getValue().equals(resultCode)){
        		throw new BadCredentialsException(resultCode);	
        	}else {
        		throw new DisabledException(resultCode);
        	}
        }
        return token;
  	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}

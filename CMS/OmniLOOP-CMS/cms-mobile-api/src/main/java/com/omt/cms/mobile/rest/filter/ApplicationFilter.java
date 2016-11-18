package com.omt.cms.mobile.rest.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.bo.base.MobileUserDetailsBO;
import com.omt.cms.user.service.UserAuthenticationService;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

public class ApplicationFilter extends OncePerRequestFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationFilter.class);

	@Autowired UserAuthenticationService authService;
	
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		addUserIdentity(request);
		filterChain.doFilter(request, response);
	}

	private boolean addUserIdentity(HttpServletRequest httpRequest){
		boolean result = false;
		if(httpRequest.getUserPrincipal() == null){
			LOGGER.warn("User Identity not found: {}");
			return false;
		}
		OAuth2Authentication auth = (OAuth2Authentication) httpRequest.getUserPrincipal();
		String loginName = auth.getPrincipal() != null ? auth.getPrincipal().toString() : null;
		MobileUserDetailsBO userBO = null;
		if(StringUtils.isNotBlank(loginName)){
			userBO = (MobileUserDetailsBO) authService.loadUserByUsername(loginName);
		}else{
			userBO = (MobileUserDetailsBO) auth.getUserAuthentication().getDetails();
		}
		String cpId = httpRequest.getHeader(SystemCodes.CPID_HEADER);
		LOGGER.debug("User Identity found: {}, Company-Id:{}", userBO.getLoginName(), cpId);
		if(userBO != null){
			httpRequest.setAttribute(SystemCodes.USER_IDENTITY, userBO);
		}
		return result;
	}

}

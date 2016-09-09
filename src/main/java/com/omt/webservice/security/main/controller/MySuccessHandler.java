package com.omt.webservice.security.main.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.omt.webservice.security.main.domain.UserDao;

public class MySuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// Allocate session time for specific users
		//request.getSession().setMaxInactiveInterval(30 * 60);
        
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		request.getSession().removeAttribute("role");
		// Administrator
        if (roles.contains(UserDao.roleadmin)){
    		request.getSession().setAttribute("role", UserDao.roleadmin);
            response.sendRedirect("./welcome");   
            return;
        }
        // Common user
		request.getSession().setAttribute("role", UserDao.roleuser);
        response.sendRedirect("./agm/index");
	}    
}
package com.omt.cms.cust.admin.web.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping(method = RequestMethod.GET, value = { "/access-denied" })
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView();
		model.setViewName("access-denied");
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/home" })
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("home");
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/login" })
	public ModelAndView indexPage(Principal principal) {
		String view = "login";
		if (principal!=null){
	        view = "/admin/companies/dashboard";
		}
		ModelAndView model = new ModelAndView();
		model.setViewName(view);
		return model;
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/home?logout";
	}

}

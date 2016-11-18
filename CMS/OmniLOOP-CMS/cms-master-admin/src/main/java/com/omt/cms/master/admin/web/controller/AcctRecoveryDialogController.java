package com.omt.cms.master.admin.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AcctRecoveryDialogController {
	/*
	@RequestMapping(method = RequestMethod.GET, value = { "/access-denied" })
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView();
		model.setViewName("access-denied");
		return model;
	}
	*/
	@RequestMapping(method = RequestMethod.GET, value = { "/recovery" })
	public ModelAndView indexPage(Principal principal) {
		String view = "recovery";
		/*if (principal!=null){
	        view = "/admin/companies/list";
		}*/
		ModelAndView model = new ModelAndView();
		model.setViewName(view);
		return model;
	}
	/*
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/home?logout";
	}
	*/
}
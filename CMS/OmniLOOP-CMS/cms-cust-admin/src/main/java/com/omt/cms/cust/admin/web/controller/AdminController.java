package com.omt.cms.cust.admin.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.omt.cms.core.service.bo.base.UserDetailsBO;

@Controller

@RequestMapping(value = { "/cust-admin/ui" })
public class AdminController {

	@RequestMapping(method = RequestMethod.GET, value = { "/company" })
	public ModelAndView showCompany() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/landing");
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/landing" })
	public ModelAndView showLanding() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/landing");
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/external" })
	public ModelAndView showExternalPages() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/external_frame");
		return model;
	}	
	
    @RequestMapping(method = RequestMethod.GET, value = { "/download_users" })
    public ModelAndView downloadUserDump() {
            ModelAndView model = new ModelAndView();
            model.addObject("user", getPrincipal());
            model.setViewName("/admin/companies/user_download");
            return model;
    }	

    @RequestMapping(method = RequestMethod.GET, value = { "/profile" })
    public ModelAndView editUserProfile() {
            ModelAndView model = new ModelAndView();
            model.addObject("user", getPrincipal());
            model.setViewName("/admin/companies/admin_user_edit");
            return model;
    }

	@RequestMapping(method = RequestMethod.GET, value = { "/companies/{cpId}" })
	public ModelAndView editCompany(@PathVariable Long cpId) {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.addObject("custId", getPrincipal().getCompanyId());
		model.addObject("cpId", cpId);
		model.setViewName("/admin/companies/company_new_edit");
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/managers" })
	public ModelAndView listManagers() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/master_admin");
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/poweruser_add" })
	public ModelAndView invitePowerUsers() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/powerusers");
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/powerusers" })
	public ModelAndView listPowerUsers() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/powerusers_list");
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/executives" })
	public ModelAndView listExecutives() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/company_executives");
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/documents" })
	public ModelAndView listDocuments() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/company_documents");
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/announcements" })
	public ModelAndView listAnnouncements() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/announcements");
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/dates" })
	public ModelAndView listKeyDates() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/key_dates");
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/email" })
	public ModelAndView sendEmails() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/mass_emails");
		return model;
	}

    private UserDetailsBO getPrincipal(){
    	UserDetailsBO user = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (principal instanceof UserDetailsBO) {
        	user = (UserDetailsBO) principal;
        }
        return user;
    }

}

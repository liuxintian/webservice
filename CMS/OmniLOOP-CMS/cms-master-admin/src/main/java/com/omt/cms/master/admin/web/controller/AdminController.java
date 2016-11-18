package com.omt.cms.master.admin.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.omt.cms.core.service.bo.base.UserDetailsBO;

@Controller

@RequestMapping(value = { "/master-admin/ui" })
public class AdminController {

	@RequestMapping(method = RequestMethod.GET, value = { "/companies" })
	public ModelAndView listCompanies() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/list");
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/profile" })
	public ModelAndView editUserProfile() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/admin_user_edit");
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/company" })
	public ModelAndView newCompany() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/company_new_edit");
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/companies/{cpId}" })
	public ModelAndView editCompany(@PathVariable Long cpId) {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
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

	@RequestMapping(method = RequestMethod.GET, value = { "/getusers" })
	public ModelAndView downloadUsers() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/download_users");
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/feeds" })
	public ModelAndView listFeeds() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/list_of_feeds");
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/irmenus" })
	public ModelAndView listMenus() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/list_of_menus");
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/externalmenus" })
	public ModelAndView listExternalMenus() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/global_instance_menus");
		return model;
	}	
	
	@RequestMapping(method = RequestMethod.GET, value = { "/registries" })
	public ModelAndView listRegistries() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/list_of_registries");
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/resetuser" })
	public ModelAndView resetUSer() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/reset_users");
		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/recoveracct" })
	public ModelAndView recoverUserAcct() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", getPrincipal());
		model.setViewName("/admin/companies/recover_user_acct");
		return model;
	}	
	
    private UserDetailsBO getPrincipal(){
    	UserDetailsBO user = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getDetails();
 
        if (principal instanceof UserDetailsBO) {
        	user = (UserDetailsBO) principal;
        }else{
        	System.out.println(principal);
        }
        return user;
    }
}

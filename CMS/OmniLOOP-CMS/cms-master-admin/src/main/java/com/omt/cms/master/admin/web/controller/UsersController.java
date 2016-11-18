package com.omt.cms.master.admin.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.user.service.UserService;
import com.omt.cms.user.service.bo.UserBO;

@Controller
@RequestMapping("/master-admin/users/csv")
public class UsersController extends BaseController{
 	@Autowired  private UserService userService;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public void downloadUsers(HttpServletResponse res) throws IOException {
		String csvFileName="userList.csv";
		ServiceRequest sreq= new ServiceRequest();
		ServiceResponse response = userService.listUsers(sreq);
		List<UserBO> users=getResultBO(response, List.class);
		res.setContentType("text/csv");
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				csvFileName);
		res.setHeader(headerKey, headerValue);
		PrintWriter writer=res.getWriter();
		writer.println(getHeaders());
		for(UserBO bo:users)
			writer.println( bo.toString());
		writer.flush();
		writer.close();
	}
	
	private String getHeaders(){
		StringBuilder builder = new StringBuilder();
		builder.append(" loginName ");
		builder.append(", userName");
		builder.append(", dateOfRegistration");
 		builder.append(", address");
 		builder.append(", userContact");
 		builder.append(", userStatus");
 		builder.append(", userIdentifier");
 		builder.append(", incorrectTries");
 		builder.append(", accountState");
  		builder.append(", curCpId");
 		builder.append(", userInvited");
 		builder.append(", userEmail");
 		builder.append(", emailValid");
 		builder.append(", phoneValid");
 		builder.append(", title");
 		builder.append(", dateOfBirth");
 		builder.append(", useEmail");
 		builder.append(", usePhone");
 		return builder.toString();
  	}


}

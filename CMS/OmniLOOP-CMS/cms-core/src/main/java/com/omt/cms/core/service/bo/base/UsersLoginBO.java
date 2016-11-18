package com.omt.cms.core.service.bo.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

public class UsersLoginBO {
	
	private List<LoginUser> users = new ArrayList<>();
	
	public UsersLoginBO(){ }
	
	public List<LoginUser> getUsers() {
		return users;
	}

	public void setUsers(List<LoginUser> users) {
		this.users = users;
	}

	public static class LoginUser{
		private String loginName;
		private boolean inUse;
		
		public LoginUser(){ }
		
		public LoginUser(String loginName){ 
			this.loginName = loginName; 
		}
		
		public String getLoginName() { return loginName; }
		
		public void setLoginName(String loginName) { this.loginName = loginName; }
		
		public boolean isInUse() { return inUse; }
		
		public void setInUse(boolean newOne) { this.inUse = newOne;}
				
	}

}
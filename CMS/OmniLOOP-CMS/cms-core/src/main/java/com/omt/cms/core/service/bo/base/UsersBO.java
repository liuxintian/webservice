package com.omt.cms.core.service.bo.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

public class UsersBO {

	private Long cpId;
	private List<LocalUserBO> users;
	private List<String> successList = new ArrayList<>();
	private List<String> failedList = new ArrayList<>();
	
	public Long getCpId() {
		return cpId;
	}
	
	public void setCpId(Long cpId) {
		this.cpId = cpId;
	}
	
	public List<LocalUserBO> getUsers() {
		return users;
	}
	
	public void setUsers(List<LocalUserBO> users) {
		this.users = users;
	}
	
	public List<String> getSuccessList() {
		return successList;
	}

	public List<String> getFailedList() {
		return failedList;
	}

	public void addToSuccessList(String loginName){
		successList.add(loginName);
	}

	public void addToFailedList(String loginName){
		failedList.add(loginName);
	}
}

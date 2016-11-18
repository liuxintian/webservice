/**
 * 
 */
package com.omt.cms.core.service.bo.base;

import java.sql.Date;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.omt.cms.core.common.GsonHelper;
import com.omt.cms.master.service.bo.DataMenuBO;

/**
 * @author muragesh
 *
 */
public class UserDetailsBO extends LoginUserBO {

	private static final long serialVersionUID = 1L;
	private String name; //full name
	private Integer type;
	private String role;
	private Long companyId;
	private String companyName;
	private Boolean userInvited;
	private String userContact;
	private String userEmail;
	private Boolean emailValid;
	private Boolean phoneValid;
	private String title;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date dateOfBirth;
	private String userUId;
	private List<DataMenuBO> dataMenus;
	
	private String notes;

	public UserDetailsBO(){ } 
	
	public UserDetailsBO(String name, String loginName, String password){ 
		super(loginName, password);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Boolean getUserInvited() {
		return userInvited;
	}

	public void setUserInvited(Boolean userInvited) {
		this.userInvited = userInvited;
	}

	public String getUserContact() {
		return userContact;
	}

	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Boolean getEmailValid() {
		return emailValid;
	}

	public void setEmailValid(Boolean emailValid) {
		this.emailValid = emailValid;
	}

	public Boolean getPhoneValid() {
		return phoneValid;
	}

	public void setPhoneValid(Boolean phoneValid) {
		this.phoneValid = phoneValid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUserUId() {
		return userUId;
	}

	public void setUserUId(String userUId) {
		this.userUId = userUId;
	}

	public List<DataMenuBO> getDataMenus() {
		return dataMenus;
	}

	public String getDataMenusJson() {
		
		if(dataMenus!=null && !dataMenus.isEmpty()){
			return StringEscapeUtils.escapeEcmaScript(GsonHelper.toJsonAll(dataMenus));
		}else{
			return "";
		}
	}

	public void setDataMenus(List<DataMenuBO> dataMenus) {
		this.dataMenus = dataMenus;
	}
	
	public String getURLById(long id){
		for(DataMenuBO menu : this.dataMenus){
			if(menu.getId().longValue() == id){
				return menu.getUrl();
			}
		}
		return StringUtils.EMPTY;
	}

}

package com.omt.webservice.security.main.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * Persisted sessions: loading serializable entity
 * @author tonyliu
 *
 */
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String login; // username
	private String name; // full name
	private String password; // should be hashed, but doesn't matter in our example
	private String[] roles;
	
	private int status;
	private String description;
	private String email;
	private String phone;
	private String code;
	
	public User(){
		
	}
	public User(String login, String name, String password, String... roles) {
		this.login = login;
		this.name = name;
		this.password = password;
		this.roles = roles;
	}

	public String getLogin() {
		return login;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String[] getRoles() {
		return roles;
	}

	// login works as ID here, if there is normal ID column, use that of course
	// equals/hashCode is very important for AuthenticationServiceImpl#tokens to work properly
	@Override
	public boolean equals(Object o) {
		return this == o
			|| o != null && o instanceof User
			&& Objects.equals(login, ((User) o).login);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(login);
	}

	@Override
	public String toString() {
		return "User{" +
			"login='" + login + '\'' +
			", name='" + name + '\'' +
			", password='" + password + '\'' +
			", roles=" + Arrays.toString(roles) +
			'}';
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}

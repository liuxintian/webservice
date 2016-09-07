package com.omt.webservice.security.main.domain;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.omt.webservice.Constants;
import com.omt.webservice.StaticMongoTemplate;

/**
 * This is a hardcoded Dao, user configured here rather than in database
 * Later can be stored in db & encrypt pwd
 * @author tonyliu
 *
 */
@Component
public class UserDao {

	public static final String password = "omt@mel123";
	public static final String roleadmin = "ROLE_ADMIN";
	public static final String fullname = "Administrator";
	public static final String roleuser = "ROLE_USER";
	
	public User getByLogin(String login) {
		if(login.contains("@")){
			return retrieveUser(login);
		}
		
		switch (login) {
			case "admin":
				return new User("admin", fullname, password, roleadmin);
			default: // loginname is deviceid; here should return a user
				return new User(login, fullname, password, roleadmin);
		}
	}
	
	private User retrieveUser(String login) {
		User user = new User();
		Query query = new Query();
		query.addCriteria(Criteria.where("login").is(login));
		query.addCriteria(Criteria.where("status").is(Constants.USER_STATUS_NORMAL));
		User tmpuser = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, User.class);
		if(tmpuser != null){
			return new User(tmpuser.getLogin(), tmpuser.getName(), tmpuser.getPassword(), tmpuser.getCode());
		}
		return user;
	}
}

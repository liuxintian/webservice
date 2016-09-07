package com.omt.webservice.security.main.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.omt.webservice.Constants;
import com.omt.webservice.RestUriConstant;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.entity.DataGridModel;
import com.omt.webservice.security.main.domain.User;
import com.omt.websocket.entity.CompanyList;

/**
 * WebpageController
 * WebpageController using for web page to query and operate user messages
 * @author tonyliu 
 */
@Controller
@RequestMapping(value=RestUriConstant.WEB_USERS_PAGE)
public class UserPageController
{
	private Logger omtlogger = Logger.getLogger(UserPageController.class);

	
	/**
	 * Use this to:
	 * webpage query all the message list and return to view
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WEB_USERS_PAGE_INDEX , method = RequestMethod.GET)
	public String getuserweb(HttpServletRequest request) {
    	return "userlist";
	}
	
	/**
     * @Real
	 * Use this to:
	 * webpage query all the message list and return to view
	 * @return
	 */	
	@RequestMapping(value=RestUriConstant.WEB_USERS_PAGE_QUERYLIST, method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>(2); 
		List<User> list = FindByPage(dgm.getPage()-1, dgm.getRows(), Constants.MSG_STATUS_NORMAL);
		
		result.put("total", TotalCount(Constants.MSG_STATUS_NORMAL));
		result.put("rows", list);
    	omtlogger.trace("Result of queryList size:"+list.size());
	    return result;
	}
	
	private int TotalCount(int status){
		int count = 0;
		 Query query = new Query();
		 query.addCriteria(Criteria.where("login").ne(""));
		 query.addCriteria(Criteria.where("status").is(status));
		count = (int) StaticMongoTemplate.getStaticMongoTemplate().count(query, User.class);
		return count;
	}
	
	private List<User> FindByPage(int pageNumber, int pageSize, int status) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.addCriteria(Criteria.where("login").ne(""));
		 query.addCriteria(Criteria.where("status").is(status));
		 query.with(new Sort(Sort.Direction.DESC, "login"));
		 query.skip(pageSize * pageNumber);
		 query.limit(pageSize);
		 
		return StaticMongoTemplate.getStaticMongoTemplate().find(query, User.class);		
	}
	private User FindOne(String id) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.addCriteria(Criteria.where("id").is(id));
		 
		return StaticMongoTemplate.getStaticMongoTemplate().findOne(query, User.class);		
	}
	private void UpdateStatus(String id, int status){
		 Query query = new Query();
		 query.addCriteria(Criteria.where("id").is(id));
		 
		 Update update = new Update();
		 update.set("status", status);
		 
		 StaticMongoTemplate.getStaticMongoTemplate().updateFirst(query, update, User.class);		
	}
	/**
     * @Real
     * This method will delete an usermsg by it's msgid value.
     */
    @RequestMapping(value=RestUriConstant.WEB_USERS_PAGE_DETAIL, method=RequestMethod.GET)
	public ModelAndView findDetails(@RequestParam("id") String id, HttpServletRequest request) {
    	omtlogger.info("Start to finddetails...with id"+id);
    	User uvo = new User();
    	ModelAndView model = new ModelAndView();
    	try{
    		model.setViewName("userdetail");
    		uvo = FindOne(id);
    		model.addObject("usermessage", uvo);
    	}catch(Exception ex){
    		ex.printStackTrace();
    		omtlogger.fatal(ex.getMessage());
    	}
    	return model;
	}
    
	/**
     * @Real
     * This method will delete an usermsg by it's msgid value.
     */
    @RequestMapping(value=RestUriConstant.WEB_USERS_PAGE_DELETE, method=RequestMethod.POST)
    @ResponseBody
	public Map<String, String> deleteuser(@RequestParam("id") String id) {
    	omtlogger.info("Start to delete usermsg...with id: "+id);
    	Map<String, String> map = new HashMap<String, String>(); 
    	if(id == null || id.length() == 0) {
    		map.put("result", "Failed with wrong parameter.");
    		return map;
    	}
    	
    	try{
    		List<String> myList = new ArrayList<String>(Arrays.asList(id.split(",")));
    		for(String idstr: myList){
    			User current = FindOne(idstr);
    			// Only normal or approved messages can be deleted
    			if((current != null) && (current.getStatus() == Constants.USER_STATUS_NORMAL)){
    				UpdateStatus(idstr, Constants.USER_STATUS_DELETED);
    			}
    		}
    		map.put("result", "Successfully deleted the user.");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		map.put("result", "Failed to delete the user.");
    	}
    	return map;
	}

    
    @RequestMapping(value=RestUriConstant.WEB_USERS_PAGE_NEW, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, String> createuser(User user) {
    	omtlogger.info("Start to createuser...with: "+user);
    	Map<String, String> map = new HashMap<String, String>(); 
    	if(user == null || user.getLogin().length() == 0) {
    		map.put("result", "Failed with wrong parameter.");
    		return map;
    	}
    	
    	try{
    		Query query = new Query();
    		query.addCriteria(Criteria.where("login").is(user.getLogin()));
    		query.addCriteria(Criteria.where("status").is(Constants.USER_STATUS_NORMAL));

    		User uvo = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, User.class);
    		if(uvo != null){
        		map.put("result", "Failed create the user: Already exist.");
    		}else{
    			user.setStatus(Constants.USER_STATUS_NORMAL);
        		StaticMongoTemplate.getStaticMongoTemplate().insert(user);
        		map.put("result", "Successfully create the user.");
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    		map.put("result", "Failed to create the user.");
    	}
    	return map;
	}
    @RequestMapping(value=RestUriConstant.WEB_USERS_PAGE_EDIT, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, String> edituser(User user) {
    	omtlogger.info("Start to edituser...with id: "+user.getId());
    	Map<String, String> map = new HashMap<String, String>(); 
    	if(user == null || user.getLogin().length() == 0) {
    		map.put("result", "Failed with wrong parameter.");
    		return map;
    	}
    	
    	try{
    		Query query = new Query();
    		query.addCriteria(Criteria.where("id").is(user.getId()));
    		User uvo = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, User.class);
    		if(uvo != null){
        		Update update = new Update();
        		update.set("login", user.getLogin());
        		update.set("password", user.getPassword());
        		update.set("name", user.getName());
        		update.set("roles", user.getRoles());
        		update.set("email", user.getEmail());
        		update.set("phone", user.getPhone());
        		update.set("description", user.getDescription());
        		
        		StaticMongoTemplate.getStaticMongoTemplate().updateFirst(query, update, User.class);
        		map.put("result", "Successfully edit the user.");
    		}else{
    			map.put("result", "Failed as cannot find the user.");
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    		map.put("result", "Failed to edit the user.");
    	}
    	return map;
	}
    
	@RequestMapping(value=RestUriConstant.WEB_USERS_PAGE_ROLES , method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CompanyList> codelist() {
		return StaticMongoTemplate.getStaticMongoTemplate().findAll(CompanyList.class);
	}   
}
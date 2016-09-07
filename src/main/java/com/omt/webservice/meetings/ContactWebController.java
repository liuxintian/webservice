package com.omt.webservice.meetings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.omt.webservice.RestUriConstant;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.entity.DataGridModel;
import com.omt.webservice.meetings.entity.Contacts;
import com.omt.webservice.meetings.entity.Meetings;

/**
 * Contact management web controller
 * @author tonyliu
 *
 */
@Controller
@RequestMapping(value=RestUriConstant.WSS_WEB_CONTACT)
public class ContactWebController {
	private Logger omtlogger = Logger.getLogger(ContactWebController.class);
	
	/**
	 * Enter into the contact page
	 * @param request
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_INDEX , method = RequestMethod.GET)
	public String company(HttpServletRequest request) {
    	omtlogger.info("Enter into news list...");
    	
    	return "contact";
	}
	
	/**
	 * Get the contact list which can be selected on meeting page
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_SELECT , method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contacts> list() {
    	List<Contacts> list = StaticMongoTemplate.getStaticMongoTemplate().findAll(Contacts.class);
		return list;
	}
	
	/**
	 * Get all the record list
	 * @param dgm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_LIST, method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryNewsList(DataGridModel dgm) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>(2); 
		List<Contacts> list = findByPage(dgm.getPage()-1, dgm.getRows());
		if(list != null && list.size() > 0){
			result.put("total", totalCount());
			result.put("rows", list);
		}
	    return result;
	}
	public static List<Contacts> findByPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.skip(pageSize * pageNumber);
		 query.limit(pageSize);
		 
		return StaticMongoTemplate.getStaticMongoTemplate().find(query, Contacts.class);		
	}
	public static int totalCount(){
		int count = 0;
		count = (int) StaticMongoTemplate.getStaticMongoTemplate().count(new Query(), Contacts.class);
		return count;
	}	
	/**
	 * Create a new Contact
	 * @param contact
	 * @return
	 */
    @RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_NEW, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, String> createContacts(Contacts contact) {
    	omtlogger.info("Start to createContacts...with: "+contact);
    	Map<String, String> map = new HashMap<String, String>(); 
    	if(contact == null || contact.getName().length() == 0) {
    		map.put("result", "Failed with wrong parameter.");
    		return map;
    	}
    	
    	try{
    		Query query = new Query();
    		query.addCriteria(Criteria.where("name").is(contact.getName()));
    		query.addCriteria(Criteria.where("email").is(contact.getEmail()));

    		Contacts uvo = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, Contacts.class);
    		if(uvo != null){
        		map.put("result", "Failed create the Contacts: Already exist.");
    		}else{
    			contact.setContactID(UtilLibs.GetSysRondomString());
        		StaticMongoTemplate.getStaticMongoTemplate().insert(contact);
        		map.put("result", "Successfully create the contact.");
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    		map.put("result", "Failed to create the contact.");
    	}
    	return map;
	}

    /**
     * Edit the contact record
     * @param contact
     * @return
     */
    @RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_EDIT, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, String> editContacts(Contacts contact) {
    	omtlogger.info("Start to editContacts...with: "+contact);
    	Map<String, String> map = new HashMap<String, String>(); 
    	if(contact == null || contact.getName().length() == 0) {
    		map.put("result", "Failed with wrong parameter.");
    		return map;
    	}
    	
    	try{
    		Query query = new Query();
    		query.addCriteria(Criteria.where("contactID").ne(contact.getContactID()));
    		query.addCriteria(Criteria.where("email").is(contact.getEmail()));

    		Contacts uvo = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, Contacts.class);
    		if(uvo != null){
        		map.put("result", "Failed edit the Contacts: Already exist.");
    		}else{
        		Query queryedit = new Query();
        		queryedit.addCriteria(Criteria.where("contactID").is(contact.getContactID()));
    			
        		Update update = new Update();
        		update.set("name", contact.getName());
        		update.set("email", contact.getEmail());
        		update.set("phone", contact.getPhone());
        		update.set("weburl", contact.getWeburl());
    			
        		StaticMongoTemplate.getStaticMongoTemplate().updateFirst(queryedit, update, Contacts.class);
        		map.put("result", "Successfully edit the contact.");
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    		map.put("result", "Failed to edit the contact.");
    	}
    	return map;
	}
  
	/**
     * @Real
     * This method will delete one record.
     * The contact can only be deleted when no meeting use it.
     */
    @RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_DELETE, method=RequestMethod.POST)
    @ResponseBody
	public Map<String, String> delete(@RequestParam("id") String id) {
    	omtlogger.info("Start to delete record...with id: "+id);
    	Map<String, String> map = new HashMap<String, String>(); 
    	if(id == null || id.length() == 0) {
    		map.put("result", "Failed with wrong parameter.");
    		return map;
    	}
    	
    	try{
    		{
    			Query queryrelation = new Query();
    			queryrelation.addCriteria(Criteria.where("meetingContacts").is(id));
    			Meetings meeting = StaticMongoTemplate.getStaticMongoTemplate().findOne(queryrelation, Meetings.class);
    			if(meeting != null){
    	    		map.put("result", "This record can not be deleted as it is in using with Meeting:"+meeting.getMeetingID());
    			}else{

        			Query query = new Query();
        			query.addCriteria(Criteria.where("contactID").is(id));
        			Contacts contact = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, Contacts.class);
        			
        			// Only normal or approved messages can be deleted
        			if(contact == null){
        				map.put("result", "Failed to delete the record. Can't find this record.");
        			}else{
        				StaticMongoTemplate.getStaticMongoTemplate().remove(query, Contacts.class);
        	    		map.put("result", "Successfully deleted the record.");
        			}
    			}
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    		map.put("result", "Failed to delete the record.");
    	}
    	return map;
	}
    
}

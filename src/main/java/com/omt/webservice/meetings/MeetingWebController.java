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
import org.springframework.web.servlet.ModelAndView;

import com.omt.webservice.RestUriConstant;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.entity.DataGridModel;
import com.omt.webservice.meetings.entity.Company;
import com.omt.webservice.meetings.entity.Contacts;
import com.omt.webservice.meetings.entity.Documents;
import com.omt.webservice.meetings.entity.MeetingDetails;
import com.omt.webservice.meetings.entity.Meetings;
import com.omt.webservice.meetings.entity.MeetingsJson;

/**
 * Meeting management.
 * @author tonyliu
 *
 */
@Controller
@RequestMapping(value=RestUriConstant.WSS_WEB_MEETING)
public class MeetingWebController {
	private Logger omtlogger = Logger.getLogger(CompanyWebController.class);
	
	@RequestMapping(value=RestUriConstant.WSS_REST_MEETING_ALL , method = RequestMethod.GET)
	public String all(HttpServletRequest request) {
    	omtlogger.info("Enter into news list...");
    	
    	return "meetingmanage";
	}

	/**
	 * Enter into the meeting management
	 * @param request
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_INDEX , method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
    	omtlogger.info("Enter into news list...");
    	
    	return "meeting";
	}
	
	/**
	 * Get code list to be selected
	 * @param request
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WSS_WEB_MEETING_FINDDETAIL , method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView finddetail(@RequestParam("meetingID") String meetingID, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName("meetingdetail");
		
		Query querymeeting = new Query();
		querymeeting.addCriteria(Criteria.where("meetingID").is(meetingID));
		Meetings meeting = StaticMongoTemplate.getStaticMongoTemplate().findOne(querymeeting, Meetings.class);
		MeetingsJson meetingJson = new MeetingsJson();

		if(meeting != null){
			// meeting common info
			meetingJson.setMeetingID(meeting.getMeetingID());
			meetingJson.setMeetingName(meeting.getMeetingName());
			meetingJson.setMeetingDate(meeting.getMeetingDate());
			meetingJson.setMeetingTime(meeting.getMeetingTime());
			meetingJson.setMeetingLocation(meeting.getMeetingLocation());
			meetingJson.setMeetingType(meeting.getMeetingType());
			meetingJson.setMeetingBeforeMsg(meeting.getMeetingBeforeMsg());
			meetingJson.setMeetingAfterMsg(meeting.getMeetingAfterMsg());
			meetingJson.setHasPassword(meeting.isHasPassword());
			
			// meetingContacts
			Query querycontact = new Query();
			querycontact.addCriteria(Criteria.where("contactID").is(meeting.getMeetingContacts()));
			Contacts meetingContacts = (Contacts) StaticMongoTemplate.getStaticMongoTemplate().findOne(querycontact, Contacts.class);
			meetingJson.setMeetingContacts(meetingContacts);
			
			// meetingDetails
			MeetingDetails meetingDetails = new MeetingDetails();
			
			Query querydetail = new Query();
			querydetail.addCriteria(Criteria.where("documentID").in(meeting.getMeetingDetails()));
			List <Documents> documents = StaticMongoTemplate.getStaticMongoTemplate().find(querydetail, Documents.class);
			
			meetingDetails.setDocuments(documents);
			
			meetingJson.setMeetingDetails(meetingDetails);

		}
		model.addObject("meetingJson", meetingJson);
		
		return model;
	}
	
	/**
	 * Display meeting list on page
	 * @param dgm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_LIST, method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>(2); 
		List<Meetings> list = findByPage(dgm.getPage()-1, dgm.getRows());

		if(list != null && list.size() > 0){
			result.put("total", totalCount());
			result.put("rows", list);
		}
	    return result;
	}
	public static List<Meetings> findByPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.skip(pageSize * pageNumber);
		 query.limit(pageSize);
		 
		return StaticMongoTemplate.getStaticMongoTemplate().find(query, Meetings.class);		
	}
	public static int totalCount(){
		int count = 0;
		count = (int) StaticMongoTemplate.getStaticMongoTemplate().count(new Query(), Meetings.class);
		return count;
	}
	
	/**
	 * Get all company list that can be selected on meeting
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_SELECT , method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Company> list() {
    	List<Company> list = StaticMongoTemplate.getStaticMongoTemplate().findAll(Company.class);
		return list;
	}
	
	/**
	 * Create a meeting
	 * @param meeting
	 * @return
	 */
    @RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_NEW, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, String> createMeeting(Meetings meeting) {
    	omtlogger.info("Start to createMeeting...with: "+meeting);
    	Map<String, String> map = new HashMap<String, String>(); 
    	if(meeting == null || meeting.getMeetingDate().length() == 0) {
    		map.put("result", "Failed with wrong parameter.");
    		return map;
    	}

    	try{
    		Query query = new Query();
    		query.addCriteria(Criteria.where("meetingDate").is(meeting.getMeetingDate()));
    		query.addCriteria(Criteria.where("meetingTime").is(meeting.getMeetingTime()));
    		query.addCriteria(Criteria.where("meetingLocation").is(meeting.getMeetingLocation()));
    		query.addCriteria(Criteria.where("companyID").is(meeting.getCompanyID()));

    		Meetings uvo = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, Meetings.class);
    		if(uvo != null){
        		map.put("result", "Failed create the Meeting: Already exist.");
    		}else{
    			meeting.setMeetingID(UtilLibs.GetSysRondomString());
        		StaticMongoTemplate.getStaticMongoTemplate().insert(meeting);
        		map.put("result", "Successfully create the Meeting.");
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    		map.put("result", "Failed to create the Meeting.");
    	}
    	return map;
	}

    /**
     * Edit a meeting
     * @param meeting
     * @return
     */
    @RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_EDIT, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, String> editMeeting(Meetings meeting) {
    	omtlogger.info("Start to editMeeting...with: "+meeting);
    	Map<String, String> map = new HashMap<String, String>(); 
    	if(meeting == null || meeting.getMeetingDate().length() == 0) {
    		map.put("result", "Failed with wrong parameter.");
    		return map;
    	}

    	try{
    		// same company should not have the same meeting with the same date,time,location
    		Query query = new Query();
    		query.addCriteria(Criteria.where("meetingID").ne(meeting.getMeetingID())); // meetingID
    		query.addCriteria(Criteria.where("meetingDate").is(meeting.getMeetingDate()));
    		query.addCriteria(Criteria.where("meetingTime").is(meeting.getMeetingTime()));
    		query.addCriteria(Criteria.where("meetingLocation").is(meeting.getMeetingLocation()));
    		query.addCriteria(Criteria.where("companyID").is(meeting.getCompanyID())); // companyID

    		Meetings uvo = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, Meetings.class);
    		if(uvo != null){
        		map.put("result", "Failed edit the Meeting: Already exist.");
    		}else{
        		Query queryedit = new Query();
        		queryedit.addCriteria(Criteria.where("meetingID").is(meeting.getMeetingID()));
        		
        		Update update = new Update();
        		update.set("meetingDate", meeting.getMeetingDate());
        		update.set("meetingTime", meeting.getMeetingTime());
        		update.set("meetingName", meeting.getMeetingName());
        		update.set("meetingType", meeting.getMeetingType());
        		update.set("meetingLocation", meeting.getMeetingLocation());
        		update.set("meetingBeforeMsg", meeting.getMeetingBeforeMsg());
        		update.set("meetingAfterMsg", meeting.getMeetingAfterMsg());

        		update.set("meetingContacts", meeting.getMeetingContacts());
        		update.set("meetingDetails", meeting.getMeetingDetails());
        		
        		update.set("hasPassword", meeting.isHasPassword());
        		if(meeting.isHasPassword()){
            		update.set("password", meeting.getPassword());
        		}
        		
        		StaticMongoTemplate.getStaticMongoTemplate().updateFirst(queryedit, update, Meetings.class);
        		map.put("result", "Successfully edit the Meeting.");
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    		map.put("result", "Failed to edit the Meeting.");
    	}
    	return map;
	}
    
	/**
     * @Real
     * This method will delete one record.
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
    		Query query = new Query();
    		query.addCriteria(Criteria.where("meetingID").is(id));
    		Meetings uvo = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, Meetings.class);
    		
    		// Only normal or approved messages can be deleted
    		if(uvo == null){
    			map.put("result", "Failed to delete the record. Can't find this record.");
    		}else{
    			StaticMongoTemplate.getStaticMongoTemplate().remove(query, Meetings.class);
    			map.put("result", "Successfully deleted the record.");
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    		map.put("result", "Failed to delete the record.");
    	}
    	return map;
	}

}

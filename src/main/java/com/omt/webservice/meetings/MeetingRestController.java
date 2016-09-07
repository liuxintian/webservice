package com.omt.webservice.meetings;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.omt.webservice.RestUriConstant;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.meetings.entity.Company;
import com.omt.webservice.meetings.entity.CompanyJson;
import com.omt.webservice.meetings.entity.Contacts;
import com.omt.webservice.meetings.entity.DocumentAttributes;
import com.omt.webservice.meetings.entity.Documents;
import com.omt.webservice.meetings.entity.MeetingDetails;
import com.omt.webservice.meetings.entity.Meetings;
import com.omt.webservice.meetings.entity.MeetingsJson;
import com.omt.webservice.meetings.entity.ValidMeetingPwd;

/**
 * Meeting controller
 * @author tonyliu
 *
 */

@RestController
@RequestMapping(value=RestUriConstant.WSS_REST_MEETING)
public class MeetingRestController { 
	private Logger omtlogger = Logger.getLogger(MeetingRestController.class);

	@RequestMapping(value=RestUriConstant.WSS_REST_MEETING_ALL, method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)     
	public @ResponseBody List<CompanyJson> getAllMeeting(HttpServletResponse response) {
		omtlogger.info("----Receive Request for All meetings of Companies...");
		return getCompany(null);
    }
	
	@RequestMapping(value=RestUriConstant.WSS_REST_MEETING_QUERY, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)     
	public @ResponseBody List<CompanyJson> getQueryCompanyMeeting(@RequestBody List <String> companylist, HttpServletResponse response) {
		omtlogger.info("----Receive Request for Query meetings with list: "+ companylist);
		List<CompanyJson> ret =  new ArrayList<CompanyJson>();
		if(companylist != null && companylist.size() > 0){
			ret = getCompany(companylist);
		}else{
			omtlogger.error("Receive Request for Query meetings with parameter error.");
		}
		return ret;
    }
	
	@RequestMapping(value=RestUriConstant.WSS_REST_MEETING_VALIDPWD, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)     
	public @ResponseBody ValidMeetingPwd getQueryCompanyMeeting(@RequestBody ValidMeetingPwd meetingpwd, HttpServletResponse response) {
		omtlogger.info("----Receive Request for validate meeting's pwd: "+ meetingpwd);
		if(meetingpwd != null){
			meetingpwd.setValid(false);
			Query querymeeting = new Query();
			querymeeting.addCriteria(Criteria.where("meetingID").is(meetingpwd.getMeetingID()));
			Meetings meeting = StaticMongoTemplate.getStaticMongoTemplate().findOne(querymeeting, Meetings.class);
			if(meeting != null){
				if(meeting.isHasPassword()){
					if(meeting.getPassword().equals(meetingpwd.getPassword())){
						meetingpwd.setValid(true);
					}else{
						meetingpwd.setValid(false);
					}
				}else{
					meetingpwd.setValid(true);
				}
			}else{
				meetingpwd.setValid(false);
			}
		}
		return meetingpwd;
    }

	public static List<CompanyJson> getCompany(List <String> companylist){
		List <CompanyJson> companies = new ArrayList<CompanyJson>();
		Query query = new Query();
		if(companylist != null && companylist.size() > 0){
			query.addCriteria(Criteria.where("companyID").in(companylist));
		}
		
		List<Company> listcompany = StaticMongoTemplate.getStaticMongoTemplate().find(query, Company.class);
		if(listcompany != null && listcompany.size() > 0){
			for(Company company: listcompany){
				CompanyJson companyJson = new CompanyJson();
				companyJson.setCompanyID(company.getCompanyID());
				companyJson.setCompanyLogo(company.getCompanyLogo());
				companyJson.setCompanyName(company.getCompanyName());
				companyJson.setTickerCode(company.getTickerCode());
				
				Query querymeeting = new Query();
				querymeeting.addCriteria(Criteria.where("companyID").is(company.getCompanyID()));
				List<Meetings> listmeeting = StaticMongoTemplate.getStaticMongoTemplate().find(querymeeting, Meetings.class);
				if(listmeeting != null && listmeeting.size() > 0){
					List<MeetingsJson> meetingsList = new ArrayList<MeetingsJson>();
					for(Meetings meeting: listmeeting){
						MeetingsJson meetingJson = new MeetingsJson();
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
						meetingsList.add(meetingJson);
					}
					companyJson.setMeetings(meetingsList);
					
					// A company can only be returned with at least one meeting.
					companies.add(companyJson);
				}
			}
		}
		
		return companies;
	}
	
	/**
	 * Generate one company information
	 * @return
	 */
	public static CompanyJson generateCompany(String companyID){
		CompanyJson company = new CompanyJson();
		{
			company.setCompanyID(companyID);
			company.setCompanyLogo("http://omnimarkettide.com/wp-content/themes/coded/img/hero-logo.png");
			company.setCompanyName("Omni Market Tide Ltd.");
			company.setTickerCode("OMT");
			{
				List<MeetingsJson> meetingsList = new ArrayList<MeetingsJson>();
				
				MeetingsJson meeting = new MeetingsJson();
				meeting.setMeetingID("M001");
				meeting.setMeetingDate("08/08/2016");
				meeting.setMeetingTime("09:30");
				meeting.setMeetingLocation("200 Toorak Road, South Yarra VIC 3141");
				meeting.setMeetingType("AGM");
				meeting.setMeetingBeforeMsg("Please bring your laptop together.");
				meeting.setMeetingAfterMsg("Dont forget to take your laptop.");
				
				{
					Contacts meetingContacts = new Contacts();
					meetingContacts.setEmail("admin@omnimarkettide.com");
					meetingContacts.setName("Admin");
					meetingContacts.setPhone("61 3 8566 6888");
					meetingContacts.setWeburl("http://omnimarkettide.com/");
					
					meeting.setMeetingContacts(meetingContacts);
				}
				{
					MeetingDetails detail = new MeetingDetails();
					{
						List <Documents> doclist = new ArrayList<Documents>();
						Documents document = new Documents();
						document.setDocumentID("D001");
						document.setDocumentType("Video");
						document.setDocumentName("The Annual General Meeting.");
						document.setDocumentLink("http://omnimarkettide.com/wp-content/uploads/20160706-CoSec-and-CFO.pdf");
						{
							List<DocumentAttributes> documentAttributesList = new ArrayList<DocumentAttributes>();
							DocumentAttributes documentAttributes = new DocumentAttributes();
							documentAttributes.setAttributeName("length");
							documentAttributes.setAttributeValue("01:30");
							documentAttributesList.add(documentAttributes);
							
							documentAttributes = new DocumentAttributes();
							documentAttributes.setAttributeName("size");
							documentAttributes.setAttributeValue("10MB");
							documentAttributesList.add(documentAttributes);
							
							document.setDocumentAttributes(documentAttributesList);
						}
						doclist.add(document);
						detail.setDocuments(doclist);
						
					}
					
					meeting.setMeetingDetails(detail);
				}
				
				meetingsList.add(meeting);
				company.setMeetings(meetingsList);
			}
		}
		return company;
	}
}

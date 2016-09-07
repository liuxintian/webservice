package com.omt.webservice.voting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.omt.webservice.RestUriConstant;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.entity.DataGridModel;
import com.omt.webservice.meetings.entity.Meetings;
import com.omt.webservice.voting.entity.Holdings;
import com.omt.webservice.voting.entity.Votes;
import com.omt.webservice.voting.entity.VotingUser;
import com.omt.webservice.voting.entity.WebVotes;

/**
 * Voting web pages controller
 * @author tonyliu
 *
 */
@Controller
@RequestMapping(value=RestUriConstant.WSS_REST_VOTE_VOTELIST)
public class VotingController {
	private Logger omtlogger = Logger.getLogger(VotingController.class);
	
	@RequestMapping(value=RestUriConstant.WSS_REST_MEETING_ALL , method = RequestMethod.GET)
	public String votingall(HttpServletRequest request) {
    	omtlogger.info("Enter votingvote list...");
    	
    	return "votingmanage";
	}
	
	@RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_INDEX , method = RequestMethod.GET)
	public String votingvote(HttpServletRequest request) {
    	omtlogger.info("Enter votingvote list...");
    	
    	return "votinglist";
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
		List<Votes> list = findByPage(dgm.getPage()-1, dgm.getRows());
		if(list != null && list.size() > 0){
			result.put("total", totalCount());
			
			List<WebVotes> listNew = new ArrayList<WebVotes>();
			WebVotes uvo = null;
			for(Votes vote: list){
				uvo = new WebVotes();
				uvo.setVotingInfo(vote.getVotingInfo());
				uvo.setVotingTime(vote.getVotingTime());
				uvo.setVotingType(vote.getVotingType());
				
				uvo.setHoldingCode(getHoldingCode(vote.getHoldingId()));
				uvo.setMeetingName(getMeetingName(vote.getMeetingId()));
				uvo.setUserName(getUserName(vote.getUserId()));
				
				listNew.add(uvo);
			}
			result.put("rows", listNew); 
		}
	    return result;
	}
	
	/**
	 * Get holding code by holdingId
	 * @param holdingId
	 * @return
	 */
	public static String getHoldingCode(String holdingId){
		if(holdingId == null || holdingId.length() == 0) return holdingId;
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(new ObjectId(holdingId)));
		
		Holdings holding = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, Holdings.class);
		if(holding != null){
			return holding.getCode();
		}else{
			return holdingId;
		}
	}
	
	/**
	 * Get meeting name by meetingId
	 * @param meetingId
	 * @return
	 */
	public static String getMeetingName(String meetingId){
		if(meetingId == null || meetingId.length() == 0) return meetingId;
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(new ObjectId(meetingId)));
		
		Meetings meeting = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, Meetings.class);
		if(meeting != null){
			return meeting.getMeetingName();
		}else{
			return meetingId;
		}
	}
	
	/**
	 * Get user name by userId
	 * @param userId
	 * @return
	 */
	public static String getUserName(String userId){
		if(userId == null || userId.length() == 0) return userId;
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(new ObjectId(userId)));
		
		VotingUser user = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, VotingUser.class);
		if(user != null){
			return user.getUserName();
		}else{
			return userId;
		}
	}
	
	public static List<Votes> findByPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.skip(pageSize * pageNumber);
		 query.limit(pageSize);
		 
		return StaticMongoTemplate.getStaticMongoTemplate().find(query, Votes.class);		
	}
	public static int totalCount(){
		int count = 0;
		count = (int) StaticMongoTemplate.getStaticMongoTemplate().count(new Query(), Votes.class);
		return count;
	}	
	
}

package com.omt.webservice.voting;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.omt.webservice.RestUriConstant;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.meetings.entity.Meetings;
import com.omt.webservice.voting.entity.Holdings;
import com.omt.webservice.voting.entity.Votes;
import com.omt.webservice.voting.entity.VotingJson;
import com.omt.webservice.voting.entity.VotingUser;
import com.omt.webservice.voting.entity.WebVotes;
import com.omt.wsserver.spring.WssController;
import com.omt.webservice.voting.entity.VotingResult;

/**
 * Voting end points
 * @author tonyliu
 * 2016-08-23
 */
@RestController
@RequestMapping(value=RestUriConstant.WSS_REST_VOTING)
public class VotingEndPoints {
	private Logger omtlogger = Logger.getLogger(VotingEndPoints.class);
	
	/**
	 * Create a voting user from RESTful API
	 * @param entity
	 * @param code
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WSS_REST_NEWUSER, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE) 
    public @ResponseBody VotingResult newuser(@RequestBody VotingUser entity, @RequestHeader(value=RestUriConstant.HEADER_REQ_CODE, defaultValue=RestUriConstant.HEADER_REQ_CODE_DEFAULT) String code) {
    	VotingResult result = new VotingResult();
    	if(entity == null){
    		result.setResult(VotingConstant.VOTING_FAILED);
    		result.setReason(VotingConstant.REASON_INVALID_INPUT);
    		omtlogger.error("Error parameters:" + entity);
    		return result;
    	}
    	
    	Query queryuser = new Query();
    	queryuser.addCriteria(Criteria.where("userName").is(entity.getUserName()));
    	queryuser.addCriteria(Criteria.where("emailAddr").is(entity.getEmailAddr()));
    	VotingUser user = StaticMongoTemplate.getStaticMongoTemplate().findOne(queryuser, VotingUser.class);
		if(user != null){
    		result.setResult(VotingConstant.VOTING_FAILED);
    		result.setReason(VotingConstant.REASON_EXISTED);
    		return result;
		}
		StaticMongoTemplate.getStaticMongoTemplate().insert(entity);
		
		result.setResult(VotingConstant.VOTING_SUCCESS);
		result.setReason(VotingConstant.REASON_NON);
		return result;
	}
	
	/**
	 * Create a holding user from RESTful API
	 * @param entity
	 * @param code
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WSS_REST_NEWHOLDING, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE) 
    public @ResponseBody VotingResult newholding(@RequestBody Holdings entity, @RequestHeader(value=RestUriConstant.HEADER_REQ_CODE, defaultValue=RestUriConstant.HEADER_REQ_CODE_DEFAULT) String code) {
    	VotingResult result = new VotingResult();
    	if(entity == null){
    		result.setResult(VotingConstant.VOTING_FAILED);
    		result.setReason(VotingConstant.REASON_INVALID_INPUT);
    		omtlogger.error("Error parameters:" + entity);
    		return result;
    	}
    	
    	Query queryholding = new Query();
    	queryholding.addCriteria(Criteria.where("code").is(entity.getCode()));
    	queryholding.addCriteria(Criteria.where("market").is(entity.getMarket()));
    	VotingUser user = StaticMongoTemplate.getStaticMongoTemplate().findOne(queryholding, VotingUser.class);
		if(user != null){
    		result.setResult(VotingConstant.VOTING_FAILED);
    		result.setReason(VotingConstant.REASON_EXISTED);
    		return result;
		}
		StaticMongoTemplate.getStaticMongoTemplate().insert(entity);
		
		result.setResult(VotingConstant.VOTING_SUCCESS);
		result.setReason(VotingConstant.REASON_NON);
		return result;
	}
	
	/**
	 * Voting end point
	 * @param entity
	 * @param code
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WSS_REST_NEWVOTE, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE) 
    public @ResponseBody VotingResult newvoting(@RequestBody Votes entity, @RequestHeader(value=RestUriConstant.HEADER_REQ_CODE, defaultValue=RestUriConstant.HEADER_REQ_CODE_DEFAULT) String code) {
    	VotingResult result = new VotingResult();
    	if(entity == null){
    		result.setResult(VotingConstant.VOTING_FAILED);
    		result.setReason(VotingConstant.REASON_INVALID_INPUT);
    		omtlogger.error("Error parameters:" + entity);
    		return result;
    	}
		omtlogger.info("Start to voting with code:"+code+"|userId:"+ entity.getUserId() + "|meetingId:"+entity.getMeetingId()+ "|holdingId:"+entity.getHoldingId());
    	
    	// user should be existed
    	Query queryuser = new Query();
    	queryuser.addCriteria(Criteria.where("_id").is(entity.getUserId()));
    	VotingUser user = StaticMongoTemplate.getStaticMongoTemplate().findOne(queryuser, VotingUser.class);
    	if(user == null){
    		result.setResult(VotingConstant.VOTING_FAILED);
    		result.setReason(VotingConstant.REASON_NON_USER);
    		return result;
    	}
    	
    	// meeting should be existed
    	Query querymeeting = new Query();
    	querymeeting.addCriteria(Criteria.where("_id").is(new ObjectId(entity.getMeetingId())));
    	Meetings meeting = StaticMongoTemplate.getStaticMongoTemplate().findOne(querymeeting, Meetings.class);
    	if(meeting == null){
    		result.setResult(VotingConstant.VOTING_FAILED);
    		result.setReason(VotingConstant.REASON_NON_MEETING);
    		return result;
    	}
    	
    	// holding should be existed
    	Query queryholding = new Query();
    	queryholding.addCriteria(Criteria.where("_id").is(new ObjectId(entity.getHoldingId())));
    	Holdings holding = StaticMongoTemplate.getStaticMongoTemplate().findOne(queryholding, Holdings.class);
    	if(holding == null){
    		result.setResult(VotingConstant.VOTING_FAILED);
    		result.setReason(VotingConstant.REASON_NON_HOLDING);
    		return result;
    	}
    	
    	// upsert voting info
    	Query queryvote = new Query();
    	queryvote.addCriteria(Criteria.where("userId").is(entity.getUserId()));
    	queryvote.addCriteria(Criteria.where("meetingId").is(entity.getMeetingId()));
    	queryvote.addCriteria(Criteria.where("holdingId").is(entity.getHoldingId()));
    	
    	Update updatevote = new Update();
    	updatevote.set("votingType", entity.getVotingType());
    	updatevote.set("votingTime", entity.getVotingTime());
    	updatevote.set("votingInfo", entity.getVotingInfo());
    	StaticMongoTemplate.getStaticMongoTemplate().upsert(queryvote, updatevote, Votes.class);
		result.setResult(VotingConstant.VOTING_SUCCESS);
		result.setReason(VotingConstant.REASON_NON);
    	
		//WsServer.broadcast(new Gson().toJson(queryNewsList()));
		WssController.broadcast(queryNewsList());
    	return result;
	}
	
	public List<WebVotes> queryNewsList() {
		List<Votes> list = StaticMongoTemplate.getStaticMongoTemplate().findAll(Votes.class);
		List<WebVotes> listNew = new ArrayList<WebVotes>();
		if(list != null && list.size() > 0){
			
			WebVotes uvo = null;
			for(Votes vote: list){
				uvo = new WebVotes();
				uvo.setVotingInfo(vote.getVotingInfo());
				uvo.setVotingTime(vote.getVotingTime());
				uvo.setVotingType(vote.getVotingType());
				
				uvo.setHoldingCode(VotingController.getHoldingCode(vote.getHoldingId()));
				uvo.setMeetingName(VotingController.getMeetingName(vote.getMeetingId()));
				uvo.setUserName(VotingController.getUserName(vote.getUserId()));
				
				listNew.add(uvo);
			}
		}
	    return listNew;
	}
	
	/**
	 * Voting end point
	 * @param entity
	 * @param code
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WSS_REST_GETVOTELIST, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE) 
    public @ResponseBody VotingJson getvotes(@RequestBody String userName, @RequestHeader(value=RestUriConstant.HEADER_REQ_CODE, defaultValue=RestUriConstant.HEADER_REQ_CODE_DEFAULT) String code) {
		VotingJson voting = new VotingJson();
		if(userName == null || userName.trim().length() == 0){
			omtlogger.error("Error parameter userName:" + userName);
			return voting;
		}
		
    	Query queryuser = new Query();
    	queryuser.addCriteria(Criteria.where("userName").is(userName));
    	VotingUser user = StaticMongoTemplate.getStaticMongoTemplate().findOne(queryuser, VotingUser.class);
    	if(user != null){
    		voting.setEmailAddr(user.getEmailAddr());
    		voting.setUserName(user.getUserName());
    		voting.setHinSrn(user.getHinSrn());
    		voting.setPostcode(user.getPostcode());
    		voting.setHolderType(user.getHolderType());
    		
        	Query queryholding = new Query();
        	queryholding.addCriteria(Criteria.where("userId").is(user.getId()));
        	List<Holdings> holding = StaticMongoTemplate.getStaticMongoTemplate().find(queryholding, Holdings.class);
        	if(holding != null){
        		voting.setHoldings(holding);
        	}

        	Query queryvote = new Query();
        	queryvote.addCriteria(Criteria.where("userId").is(user.getId()));
        	List<Votes> votes = StaticMongoTemplate.getStaticMongoTemplate().find(queryvote, Votes.class);
        	if(votes != null){
        		voting.setVotes(votes);
        	}

    	}else{
    		omtlogger.warn("No record found with userName:" + userName);
    	}
    	
		return voting;
	}

}

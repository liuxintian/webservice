package com.omt.webservice.voting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.omt.webservice.RestUriConstant;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.entity.DataGridModel;
import com.omt.webservice.voting.entity.Holdings;

/**
 * votingholding web pages controller
 * @author tonyliu
 *
 */
@Controller
@RequestMapping(value=RestUriConstant.WSS_REST_VOTE_HOLDINGLIST)
public class VotingHoldingController {
	private Logger omtlogger = Logger.getLogger(VotingHoldingController.class);
	
	@RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_INDEX , method = RequestMethod.GET)
	public String votingholding(HttpServletRequest request) {
    	omtlogger.info("Enter votingholding list...");
    	
    	return "votingholding";
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
		List<Holdings> list = findByPage(dgm.getPage()-1, dgm.getRows());
		if(list != null && list.size() > 0){
			result.put("total", totalCount());
			result.put("rows", list); 
		}
	    return result;
	}
	
	public static List<Holdings> findByPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.skip(pageSize * pageNumber);
		query.limit(pageSize);
		
		return StaticMongoTemplate.getStaticMongoTemplate().find(query, Holdings.class);		
	}
	public static int totalCount(){
		int count = 0;
		count = (int) StaticMongoTemplate.getStaticMongoTemplate().count(new Query(), Holdings.class);
		return count;
	}	
}

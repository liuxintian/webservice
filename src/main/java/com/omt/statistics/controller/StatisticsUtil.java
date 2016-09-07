package com.omt.statistics.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.omt.statistics.entity.StPageShowVO;
import com.omt.statistics.entity.StatisticsVO;
import com.omt.webservice.Constants;
import com.omt.webservice.RestUriConstant;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;

public class StatisticsUtil {
	
	public static final String SharePrice = "SharePrice";
	public static final String ChartHistory = "ChartHistory";

	public static void statistics(String url, String code, String requestmsg, HttpServletRequest request){
		StPageShowVO newpagevo = OrganizeData(url, code, requestmsg, request);
		String type ="";
		switch(url){
		case RestUriConstant.STATIC_CHART_HISTORY_API:
			type = ChartHistory;
			break;
		case RestUriConstant.STATIC_CHART_HISTORY_NEWAPI:
			type = ChartHistory;
			break;
		case RestUriConstant.STATIC_SHARE_PRICE_API:
			type = SharePrice;
			break;
		default:
			break;
		}
		newpagevo.setType(type);
		insert(newpagevo);
	}
	
	public static void insert(StPageShowVO entity) {
		// TODO Auto-generated method stub
		
		StPageShowVO current = findOneHist(entity);
		if(current == null){
			StaticMongoTemplate.getStaticMongoTemplate().insert(entity);   
		}else{
			updatehist(current, entity);
		}
	}
	
	public static void updatehist(StPageShowVO oldentity, StPageShowVO newentity) {
		// TODO Auto-generated method stub
		if(oldentity == null || newentity == null)return;
		
		Query query = new Query();
		query.addCriteria(Criteria.where("url").is(newentity.getUrl()));
		query.addCriteria(Criteria.where("date").is(newentity.getDate()));
		query.addCriteria(Criteria.where("code").is(newentity.getCode()));
		
		// omtlogger.info("----upate name:"+entity.getName() +"----data:"+entity.getData());
		List<StatisticsVO> stdetails = new ArrayList<StatisticsVO>();
		if(oldentity.getStdetails() != null && newentity.getStdetails() != null){
			stdetails = oldentity.getStdetails();
			for(StatisticsVO uvo: newentity.getStdetails()){
				stdetails.add(uvo);
			}
			
	    	Update update = new Update();
			update.set("count", String.valueOf(stdetails.size()));
			update.set("stdetails", stdetails);
			
			StaticMongoTemplate.getStaticMongoTemplate().updateFirst(query , update, StPageShowVO.class);
		}
	}
	
	public static StPageShowVO findOneHist(StPageShowVO message) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("url").is(message.getUrl()));
		query.addCriteria(Criteria.where("date").is(message.getDate()));
		query.addCriteria(Criteria.where("code").is(message.getCode()));

		return (StPageShowVO) StaticMongoTemplate.getStaticMongoTemplate().findOne(query, StPageShowVO.class);   
	}	
	
	public static StPageShowVO OrganizeData(String url, String code, String requestmsg, HttpServletRequest request){
		StPageShowVO uvo = new StPageShowVO();
		uvo.setDate(UtilLibs.GetLocalDateFmt(Constants.SYS_DT_FMT));
		uvo.setUrl(url);
		uvo.setCode(code);
		
		List<StatisticsVO> stdetails = new ArrayList<StatisticsVO>();
		StatisticsVO detail = new StatisticsVO();
		detail.setRequestmsg(requestmsg);
		detail.setDatetime(UtilLibs.GetLocalDateFmt(Constants.SYS_TM_FMT));
		detail.setSpringmsg("RemoteAddr:"+request.getRemoteAddr()+"--RemoteHost:"+request.getRemoteHost()+"--RemotePort:"+request.getRemotePort()+"--RemoteUser:"+request.getRemoteUser());
		stdetails.add(detail);
		
		uvo.setStdetails(stdetails);
		uvo.setCount(String.valueOf(stdetails.size()));
		
		return uvo;
	}
}

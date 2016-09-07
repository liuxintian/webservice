package com.omt.webservice.morningstar.dao;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.omt.webservice.Constants;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.morningstar.entity.MsChartHistory;
import com.omt.webservice.morningstar.entity.MsSharePrice;
import com.omt.webservice.morningstar.entity.MsSharePriceOld;
import com.omt.websocket.entity.SharePriceReq;
import com.omt.websocket.entity.light.LightLastClose;

public class MsDao {
	
	//----------------------------------------------------------------------------------------------------------------------------
	// For share price
	public static void updateInsertMsSharePrice(MsSharePrice entity){
		// TODO Auto-generated method stub
    	Update update = new Update();
		update.set("data", entity.getData());
		update.set("datetime", UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_TM_FMT, Constants.SYS_TZ_UTC));
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(entity.getName()));
		query.addCriteria(Criteria.where("market").is(entity.getMarket()));
		
		StaticMongoTemplate.getStaticMongoTemplate().upsert(query, update, MsSharePrice.class);
	}	

	public static MsSharePrice findOneSharePrice(String code) {
		// TODO Auto-generated method stub
		 return (MsSharePrice) StaticMongoTemplate.getStaticMongoTemplate().findOne(new Query(Criteria.where("name").is(code)), MsSharePrice.class);   
	}
	
	public static MsSharePrice findOneSharePrice(SharePriceReq uvo) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(uvo.getCode().toUpperCase()));
		query.addCriteria(Criteria.where("market").is(uvo.getMarket().toUpperCase()));
		return (MsSharePrice) StaticMongoTemplate.getStaticMongoTemplate().findOne(query, MsSharePrice.class);   
	}

	/**
	 * Each time new share price comes update the old collection
	 * @param entity
	 */
	public static void updateInsertMsSharePriceOld(MsSharePrice entity){
		// TODO Auto-generated method stub
    	Update update = new Update();
		update.set("data", entity.getData());
		update.set("datetime", UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_TM_FMT, Constants.SYS_TZ_UTC));
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(entity.getName()));
		query.addCriteria(Criteria.where("market").is(entity.getMarket()));
		
		StaticMongoTemplate.getStaticMongoTemplate().upsert(query, update, MsSharePriceOld.class);
	}	

	//----------------------------------------------------------------------------------------------------------------------------
	// For chart history
	public static void updateInsertChartHistory(MsChartHistory entity){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("code").is(entity.getCode()));
		query.addCriteria(Criteria.where("date").is(entity.getDate()));
		query.addCriteria(Criteria.where("market").is(entity.getMarket()));
		
    	Update update = new Update();
		update.set("value", entity.getValue());
		update.set("count", entity.getCount());
		
		StaticMongoTemplate.getStaticMongoTemplate().upsert(query , update, MsChartHistory.class);
	}	
	//----------------------------------------------------------------------------------------------------------------------------
	// For One Day chart history and only Close price
	public static void updateInsertOneDayChartHistory(LightLastClose entity){
		
		StaticMongoTemplate.getStaticMongoTemplate().insert(entity);
	}	
	
	public static MsChartHistory findOneMsChartHistory(MsChartHistory entity) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.addCriteria(Criteria.where("code").is(entity.getCode()));
		 //query.addCriteria(Criteria.where("market").is(entity.getMarket()));
		 // comment the following line as using createAllItemsForEmpty
		 //query.addCriteria(Criteria.where("count").gte(entity.getCount()));
		 
		return (MsChartHistory) StaticMongoTemplate.getStaticMongoTemplate().findOne(query, MsChartHistory.class);		
	}	
	
	public static MsChartHistory findMarketMsChartHistory(MsChartHistory entity) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.addCriteria(Criteria.where("code").is(entity.getCode()));
		 query.addCriteria(Criteria.where("market").gte(entity.getMarket()));
		 
		return (MsChartHistory) StaticMongoTemplate.getStaticMongoTemplate().findOne(query, MsChartHistory.class);		
	}	
}

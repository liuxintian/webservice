package com.omt.webservice.morningstar.dao;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.omt.webservice.Constants;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.morningstar.entity.MsChartHistory;
import com.omt.webservice.morningstar.entity.MsSharePrice;
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
		
		//{
		Query queryoo = new Query();
		queryoo.addCriteria(Criteria.where("name").is(entity.getData().getCode()));
		queryoo.addCriteria(Criteria.where("market").is(entity.getMarket()));
		MsSharePrice oldone = StaticMongoTemplate.getStaticMongoTemplate().findOne(queryoo, MsSharePrice.class); 
		if(oldone != null){
			if((!(oldone.getData().toTriggerString().equals(entity.getData().toTriggerString())))){
				update.set("dataChangedAll", true);
			}
			if(UtilLibs.isChangedLast(oldone.getData(), entity.getData())) {
				update.set("dataChangedLast", true);
			}
			StaticMongoTemplate.getStaticMongoTemplate().updateFirst(query, update, MsSharePrice.class);
		}else{
			StaticMongoTemplate.getStaticMongoTemplate().insert(entity);
		}
		//}
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

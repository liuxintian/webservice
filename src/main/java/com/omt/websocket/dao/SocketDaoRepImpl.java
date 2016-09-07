package com.omt.websocket.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.omt.webservice.Constants;
import com.omt.webservice.UtilLibs;
import com.omt.websocket.entity.NotifyMessage;
import com.omt.websocket.entity.SharePriceReq;


/**
 * Desc: DaoRepImpl 
 * Author: TonyLiu
 */
@Repository
public class SocketDaoRepImpl implements SocketDaoAbsRep {

	@Autowired
	@Qualifier("mongoTemplate")
	private MongoTemplate mongoTemplate;

	public void insert(NotifyMessage entity) {
		// TODO Auto-generated method stub
		mongoTemplate.insert(entity);   
	}

	public NotifyMessage findOne(String code) {
		// TODO Auto-generated method stub
		 return (NotifyMessage) mongoTemplate.findOne(new Query(Criteria.where("name").is(code)), NotifyMessage.class);   
	}
	
	public NotifyMessage findOne(SharePriceReq uvo) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(uvo.getCode().toUpperCase()));
		query.addCriteria(Criteria.where("market").is(uvo.getMarket().toUpperCase()));
		return (NotifyMessage) mongoTemplate.findOne(query, NotifyMessage.class);   
	}

	public void deleteOne(String code) {
		// TODO Auto-generated method stub
		 mongoTemplate.remove(new Query(Criteria.where("name").is(code)), NotifyMessage.class);   
	}

	public List<NotifyMessage> findAll() {
		// TODO Auto-generated method stub
		return mongoTemplate.find(new Query(), NotifyMessage.class);
	}
	
	public int totalCount(){
		int count = 0;
		count = (int) mongoTemplate.count(new Query(), NotifyMessage.class);
		return count;
	}
	
	public List<NotifyMessage> findByPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.skip(pageSize * pageNumber);
		 query.limit(pageSize);
		 
		return mongoTemplate.find(query, NotifyMessage.class);		
	}
	
    public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void update(NotifyMessage entity) {
		// TODO Auto-generated method stub
    	
    	Update update = new Update();
		update.set("data", entity.getData());
		update.set("datetime", UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_TM_FMT, Constants.SYS_TZ_UTC));
		
		mongoTemplate.updateFirst(new Query(Criteria.where("name").is(entity.getName())), update, NotifyMessage.class);
	}


}

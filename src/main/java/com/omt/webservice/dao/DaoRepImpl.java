package com.omt.webservice.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.omt.webservice.Constants;
import com.omt.webservice.entity.UserMessage;

/**
 * Desc: DaoRepImpl 
 * Author: TonyLiu
 */
@Repository
public class DaoRepImpl implements DaoAbsRep {
	@Autowired
	@Qualifier("mongoTemplate")
	private MongoTemplate mongoTemplate;

	public void insert(UserMessage entity) {
		// TODO Auto-generated method stub
		mongoTemplate.insert(entity);   
	}

	public UserMessage findOne(String id) {
		// TODO Auto-generated method stub
		 return (UserMessage) mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), UserMessage.class);   
	}

	public void deleteOne(String id) {
		// TODO Auto-generated method stub
		 mongoTemplate.remove(new Query(Criteria.where("id").is(id)), UserMessage.class);   
	}
	
	@Override
	public void updateStatus(String id, int status) {
		// TODO Auto-generated method stub
		if(id == null || id.length() == 0) return;
    	Update update = new Update();
		update.set("status", status);
		
		List<String> myList = new ArrayList<String>(Arrays.asList(id.split(",")));
		mongoTemplate.updateFirst(new Query(Criteria.where("id").in(myList)), update, UserMessage.class);
	}
	
	// should be email, name = email
	@Override
	public List<UserMessage> findByName(String name) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.addCriteria(Criteria.where("email").is(name));
		 //query.addCriteria(Criteria.where("status").is(Constants.MSG_STATUS_NORMAL));
		return mongoTemplate.find(query, UserMessage.class);
	}	
	
	@Override
	public List<UserMessage> findAll() {
		// TODO Auto-generated method stub
		return mongoTemplate.find(new Query(), UserMessage.class);
	}
	
	@Override
	public List<UserMessage> findAllNormal() {
		// TODO Auto-generated method stub
		return mongoTemplate.find(new Query(Criteria.where("status").is(Constants.MSG_STATUS_NORMAL)), UserMessage.class);
	}

	public int totalCount(int status){
		int count = 0;
		count = (int) mongoTemplate.count(new Query(Criteria.where("status").is(status)), UserMessage.class);
		return count;
	}
	
	public List<UserMessage> findByPage(int pageNumber, int pageSize, int status) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.addCriteria(Criteria.where("email").exists(true));
		 query.addCriteria(Criteria.where("status").is(status));
		 query.with(new Sort(Sort.Direction.DESC, "datetime"));
		 query.skip(pageSize * pageNumber);
		 query.limit(pageSize);
		 mongoTemplate.indexOps(UserMessage.class).ensureIndex(new Index().on("datetime", Direction.DESC));
		 
		 return mongoTemplate.find(query, UserMessage.class);		
	}
	
    public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}


}

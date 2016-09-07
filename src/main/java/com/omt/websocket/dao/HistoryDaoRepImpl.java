package com.omt.websocket.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.omt.websocket.entity.HistoryPriceVO;


/**
 * Desc: DaoRepImpl 
 * Author: TonyLiu
 */
@Repository
public class HistoryDaoRepImpl implements HistoryDaoAbsRep {

	@Autowired
	@Qualifier("mongoTemplate")
	private MongoTemplate mongoTemplate;

	public void insert(HistoryPriceVO entity) {
		// TODO Auto-generated method stub
		mongoTemplate.insert(entity);   
	}

	public HistoryPriceVO findOne(String code) {
		// TODO Auto-generated method stub
		 return (HistoryPriceVO) mongoTemplate.findOne(new Query(Criteria.where("name").is(code)), HistoryPriceVO.class);   
	}

	public void deleteOne(String code) {
		// TODO Auto-generated method stub
		 mongoTemplate.remove(new Query(Criteria.where("name").is(code)), HistoryPriceVO.class);   
	}

	public List<HistoryPriceVO> findAll() {
		// TODO Auto-generated method stub
		return mongoTemplate.find(new Query(), HistoryPriceVO.class);
	}
	
	public int totalCount(){
		int count = 0;
		count = (int) mongoTemplate.count(new Query(), HistoryPriceVO.class);
		return count;
	}
	
	public List<HistoryPriceVO> findByPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.skip(pageSize * pageNumber);
		 query.limit(pageSize);
		 
		return mongoTemplate.find(query, HistoryPriceVO.class);		
	}
	
    public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void update(HistoryPriceVO entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HistoryPriceVO findRightOne(HistoryPriceVO entity) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.addCriteria(Criteria.where("code").is(entity.getCode()));
		 //query.addCriteria(Criteria.where("count").gte(entity.getCount()));
		 
		return (HistoryPriceVO) mongoTemplate.findOne(query, HistoryPriceVO.class);		
	}
	
	@Override
	public HistoryPriceVO findMarketOne(HistoryPriceVO entity) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.addCriteria(Criteria.where("code").is(entity.getCode()));
		 query.addCriteria(Criteria.where("market").gte(entity.getMarket()));
		 
		return (HistoryPriceVO) mongoTemplate.findOne(query, HistoryPriceVO.class);		
	}
}

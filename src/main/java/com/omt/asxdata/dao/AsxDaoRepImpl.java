package com.omt.asxdata.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.omt.webservice.Constants;
import com.omt.asxdata.entity.AsxDataVO;


/**
 * Desc: DaoRepImpl 
 * Author: TonyLiu
 */
@Repository
public class AsxDaoRepImpl implements AsxDaoAbsRep {
	@Autowired
	@Qualifier("mongoTemplate")
	private MongoTemplate mongoTemplate;

	public void insert(AsxDataVO entity) {
		// TODO Auto-generated method stub
		mongoTemplate.insert(entity);   
	}

	public AsxDataVO findOne(String id) {
		// TODO Auto-generated method stub
		 return (AsxDataVO) mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), AsxDataVO.class);   
	}

	public void deleteOne(String id) {
		// TODO Auto-generated method stub
		 mongoTemplate.remove(new Query(Criteria.where("id").is(id)), AsxDataVO.class);   
	}
	
	@Override
	public void updateDelete(String id) {
		// TODO Auto-generated method stub
    	Update update = new Update();
		update.set("status", Constants.MSG_STATUS_DELETED);
		
		mongoTemplate.updateFirst(new Query(Criteria.where("id").is(id)), update, AsxDataVO.class);
	}
	
	// should be email, name = email
	@Override
	public List<AsxDataVO> findByName(String name) {
		// TODO Auto-generated method stub
		return mongoTemplate.find(new Query(Criteria.where("email").is(name)), AsxDataVO.class);
	}	
	
	@Override
	public List<AsxDataVO> findAll() {
		// TODO Auto-generated method stub
		return mongoTemplate.find(new Query(), AsxDataVO.class);
	}
	
	@Override
	public List<AsxDataVO> findByType(int type) {
		// TODO Auto-generated method stub
		return mongoTemplate.find(new Query(Criteria.where("type").is(type)), AsxDataVO.class);
	}

	public int totalCount(){
		int count = 0;
		count = (int) mongoTemplate.count(new Query(Criteria.where("status").is(Constants.MSG_STATUS_NORMAL)), AsxDataVO.class);
		return count;
	}
	
	public List<AsxDataVO> findByPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.addCriteria(Criteria.where("email").exists(true));
		 query.addCriteria(Criteria.where("status").is(Constants.MSG_STATUS_NORMAL));
		 query.with(new Sort(Sort.Direction.DESC, "_id"));
		 query.skip(pageSize * pageNumber);
		 query.limit(pageSize);
		 
		return mongoTemplate.find(query, AsxDataVO.class);		
	}
	
    public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}


}

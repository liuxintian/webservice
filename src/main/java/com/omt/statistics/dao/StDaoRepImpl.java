package com.omt.statistics.dao;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.omt.statistics.entity.StPageShowVO;
import com.omt.webservice.entity.DataGridModel;


/**
 * Desc: DaoRepImpl 
 * Author: TonyLiu
 */
@Repository
public class StDaoRepImpl implements StDaoAbsRep {
	@Autowired
	@Qualifier("mongoTemplate")
	private MongoTemplate mongoTemplate;

	public void insert(StPageShowVO entity) {
		// TODO Auto-generated method stub
		mongoTemplate.insert(entity);   
	}

	public StPageShowVO findOne(String id) {
		// TODO Auto-generated method stub
		 return (StPageShowVO) mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), StPageShowVO.class);   
	}

	// should be email, name = email
	@Override
	public List<StPageShowVO> findByName(String name) {
		// TODO Auto-generated method stub
		return mongoTemplate.find(new Query(Criteria.where("email").is(name)), StPageShowVO.class);
	}	
	
	@Override
	public List<StPageShowVO> findAll() {
		// TODO Auto-generated method stub
		return mongoTemplate.find(new Query(), StPageShowVO.class);
	}

	public int totalCount(DataGridModel dgm){
		int count = 0;
		count = (int) mongoTemplate.count(createQuery(dgm), StPageShowVO.class);
		return count;
	}
	
	public List<StPageShowVO> findByPage(DataGridModel dgm) {
		// TODO Auto-generated method stub
		 Query query = createQuery(dgm);
		 query.with(new Sort(Sort.Direction.DESC, "_id"));
		 query.skip((dgm.getPage()-1) * dgm.getRows());
		 query.limit(dgm.getRows());
		 
		return mongoTemplate.find(query, StPageShowVO.class);		
	}
	
	private Query createQuery(DataGridModel dgm){
		 Query query = new Query();
		 //[{"field":"date","op":"equal","value":"27/04/2016"},{"field":"code","op":"contains","value":"ONT"},{"field":"type","op":"equal","value":"SharePrice"},{"field":"count","op":"equal","value":"3"}]
		 if(dgm != null && dgm.getFilterRules() != null && dgm.getFilterRules().length() > 3){
			 JSONArray dmgary = new JSONArray(dgm.getFilterRules());
			 for(Object obj: dmgary){
				 JSONObject fieldObj = new JSONObject(obj.toString());
				 switch(fieldObj.getString("field")){
				 case "code":
					 query.addCriteria(Criteria.where(fieldObj.getString("field")).regex(fieldObj.getString("value").toUpperCase()));
					 break;
				 case "date":
					 query.addCriteria(Criteria.where(fieldObj.getString("field")).is(fieldObj.getString("value")));
					 break;
				 case "count":
					 query.addCriteria(Criteria.where(fieldObj.getString("field")).is(fieldObj.getString("value")));
					 break;
				 case "type":
					 query.addCriteria(Criteria.where(fieldObj.getString("field")).is(fieldObj.getString("value")));
					 break;
			     default:
					 query.addCriteria(Criteria.where(fieldObj.getString("field")).is(fieldObj.getString("value")));
			    	 break;
				 }
			 }
		 }
		 return query;
	}
	
    public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}


}

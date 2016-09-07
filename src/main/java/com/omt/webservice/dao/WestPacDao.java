package com.omt.webservice.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.omt.asxdata.entity.WestNews;
import com.omt.webservice.StaticMongoTemplate;

public class WestPacDao {

	public static List<WestNews> findByPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.skip(pageSize * pageNumber);
		 query.limit(pageSize);
		 
		return StaticMongoTemplate.getStaticMongoTemplate().find(query, WestNews.class);		
	}
	public static int totalCount(){
		int count = 0;
		count = (int) StaticMongoTemplate.getStaticMongoTemplate().count(new Query(), WestNews.class);
		return count;
	}
}

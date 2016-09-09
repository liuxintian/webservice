package com.omt.webservice;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.BasicDBObject;

public class StaticMongoCollection {
	
	private static Logger omtlogger = Logger.getLogger(StaticMongoCollection.class);
	
	/**
	 * Create collections into specific dbName
	 * @param dbName
	 * @param collectionName
	 * @param collection
	 * @return with any errors FALSE, otherwise TRUE
	 */
	public static boolean createCollection(String dbName, String collectionName, BasicDBObject document){
		if(dbName == null || dbName.trim().length() == 0 || collectionName == null || collectionName.trim().length() == 0 || document == null){
			omtlogger.error("Get error while createCollection: error parameters.");
			return false;
		}
		try{
			MongoTemplate template = StaticMongoTemplate.createDBInstanceByName(dbName);
			CollectionOptions collectionOptions = new CollectionOptions(null, null, false);
			
			if(!template.exists(new Query(), collectionName)){
				template.createCollection(collectionName, collectionOptions);
				template.indexOps(collectionName).ensureIndex(new Index().on("date",Direction.ASC));
			}
			template.getCollection(collectionName).insert(document);
 
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return true;
	}
	
	public static void main(String args[]){
		for(int i=0;i<5; i++){
			BasicDBObject document = new BasicDBObject();
			document.put("name", "staff name: staff"+i);
			document.put("question", "staff's question number:"+i);
			document.put("vac", "vac state:"+i);
			document.put("status", "1");
			document.put("number", i);
			document.put("date", new Date());
			createCollection("dbAAA", "tableAAA", document);
		}
	}
}

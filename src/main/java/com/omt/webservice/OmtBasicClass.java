package com.omt.webservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.omt.config.StaticConfig;
import com.omt.webservice.entity.UserMessage;

/**
 * All Classes should be extended from this basic one
 * @author tonyliu
 *
 */
public abstract class OmtBasicClass {
	//--------------------------------------------------------------------------------------------------------------------------------------
	public final int USE_SAME_COLLECTIONS = 1;
	public final int USE_DIFF_COLLECTIONS = 2;
	
	private final String TABLE_PREFIX = "userMessage";
	private String code;

	private Mongo client;
	private MongoTemplate mongoTemplate;
	private DBCollection collection;

	//--------------------------------------------------------------------------------------------------------------------------------------
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		if(UtilLibs.validCode(code)){
			this.code = code;
		}else{
			this.code = "";
		}
	}
	//--------------------------------------------------------------------------------------------------------------------------------------
	
	public void releaseMongoDBTemplate(){
		if(client != null ){
			client.close();
			client = null;
		}
		if(mongoTemplate != null){
			mongoTemplate = null;
		}
	}
	//--------------------------------------------------------------------------------------------------------------------------------------
	
	//--------------------------------------------------------------------------------------------------------------------------------------
	public void insert(UserMessage entity){
		if(StaticConfig.MS_DATA_SWITCHER == USE_DIFF_COLLECTIONS){
			insertWithCollections(entity);
		}else{
			insertNormal(entity);
		}
		releaseMongoDBTemplate();
	}
	
	private void insertNormal(UserMessage entity){
		StaticMongoTemplate.getStaticMongoTemplate().insert(entity);
	}
	private void insertWithCollections(UserMessage entity){
		DBCollection collection = StaticMongoTemplate.getStaticMongoTemplate().getDb().getCollection(TABLE_PREFIX+code.toUpperCase());
		
		BasicDBObject document = new BasicDBObject();
		document.put("name", entity.getName());
		document.put("email", entity.getEmail());
		document.put("notes", entity.getNotes());
		document.put("datetime", entity.getDatetime());
		document.put("vac", entity.getVac());
		document.put("status", entity.getStatus());
		document.put("code", entity.getCode());
		collection.insert(document);
	}
	//--------------------------------------------------------------------------------------------------------------------------------------
	
	//--------------------------------------------------------------------------------------------------------------------------------------
	public void updateFirst(String id, int status){
		if(StaticConfig.MS_DATA_SWITCHER == USE_DIFF_COLLECTIONS){
			updateFirstWithCollections(id, status);
		}else{
			updateFirstNormal(id, status);
		}
		releaseMongoDBTemplate();
	}
	public void updateFirstNormal(String id, int status){
		if(id == null || id.length() == 0) return;
    	Update update = new Update();
		update.set("status", status);
		
		List<String> myList = new ArrayList<String>(Arrays.asList(id.split(",")));
		StaticMongoTemplate.getStaticMongoTemplate().updateFirst(new Query(Criteria.where("id").in(myList)), update, UserMessage.class);
	}	
	public void updateFirstWithCollections(String id, int status){
		DBCollection collection = StaticMongoTemplate.getStaticMongoTemplate().getDb().getCollection(TABLE_PREFIX+code.toUpperCase());
		List<String> myList = new ArrayList<String>(Arrays.asList(id.split(",")));		
		BasicDBObject query = new BasicDBObject();
		for(String ids: myList){
			query.append("_id", new ObjectId(ids));
		}
		
		BasicDBObject object = new BasicDBObject();
		object.append("$set", new BasicDBObject().append("status", status));
		
		collection.update(query, object);
	}
	//--------------------------------------------------------------------------------------------------------------------------------------
	
	//--------------------------------------------------------------------------------------------------------------------------------------
	public UserMessage findOne(String id){
		try{
			if(StaticConfig.MS_DATA_SWITCHER == USE_DIFF_COLLECTIONS){
				return findOneWithCollections(id);
			}else{
				return findOneNormal(id);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			releaseMongoDBTemplate();
		}
		
	}
	private UserMessage findOneNormal(String id) {
		 return (UserMessage) StaticMongoTemplate.getStaticMongoTemplate().findOne(new Query(Criteria.where("id").is(id)), UserMessage.class);   
	}
	private UserMessage findOneWithCollections(String id) {
		UserMessage usermsg = new UserMessage();
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		DBCollection collection = StaticMongoTemplate.getStaticMongoTemplate().getDb().getCollection(TABLE_PREFIX+code.toUpperCase());
		BasicDBObject object = (BasicDBObject) collection.findOne(query);
		if(object != null){
			usermsg.setId(object.getString("_id"));
			usermsg.setName(object.getString("name"));
			usermsg.setEmail(object.getString("email"));
			usermsg.setNotes(object.getString("notes"));
			usermsg.setDatetime(object.getString("datetime"));
			usermsg.setVac(object.getString("vac"));
			usermsg.setStatus(object.getInt("status"));
			usermsg.setCode(object.getString("code"));
		}
		return usermsg;
	}
	//--------------------------------------------------------------------------------------------------------------------------------------
	
	//--------------------------------------------------------------------------------------------------------------------------------------
	public int totalCount(int status){
		int count = 0;
		if(StaticConfig.MS_DATA_SWITCHER == USE_DIFF_COLLECTIONS){
			count = totalCountWithCollections(status);
		}else{
			count = totalCountNormal(status);
		}
		releaseMongoDBTemplate();
		return count;
	}
	private int totalCountNormal(int status){
		int count = 0;
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is(status));
		if(code != null && code.length() > 0){
			query.addCriteria(Criteria.where("code").is(code));
		}
		
		count = (int) StaticMongoTemplate.getStaticMongoTemplate().count(query, UserMessage.class);
		return count;
	}
	private int totalCountWithCollections(int status){
		int count = 0;
		BasicDBObject query = new BasicDBObject();
		query.put("status", status);
		DBCollection collection = StaticMongoTemplate.getStaticMongoTemplate().getDb().getCollection(TABLE_PREFIX+code.toUpperCase());
		count = (int) collection.count(query);
		
		return count;
	}
	//--------------------------------------------------------------------------------------------------------------------------------------
	
	//--------------------------------------------------------------------------------------------------------------------------------------
	public List<UserMessage> findByName(String name) {
		try{
			if(StaticConfig.MS_DATA_SWITCHER == USE_DIFF_COLLECTIONS){
				return findByNameWithCollections(name);
			}else{
				return findByNameNormal(name);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			releaseMongoDBTemplate();
		}
	}	
	public List<UserMessage> findByNameNormal(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(name));
		if(code != null && code.length() > 0){
			query.addCriteria(Criteria.where("code").is(code));
		}
		
		return StaticMongoTemplate.getStaticMongoTemplate().find(query, UserMessage.class);
	}
	public List<UserMessage> findByNameWithCollections(String name) {
		List<UserMessage> ret = new ArrayList<UserMessage>();
		
		BasicDBObject query = new BasicDBObject();
		query.put("email", name);
		DBCollection collection = StaticMongoTemplate.getStaticMongoTemplate().getDb().getCollection(TABLE_PREFIX+code.toUpperCase());
		DBCursor cursorDoc = collection.find(query);
		UserMessage usermsg = null;
		BasicDBObject object = null;
		while (cursorDoc.hasNext()) {
			usermsg = new UserMessage();
			object = (BasicDBObject) cursorDoc.next();
			usermsg.setId(object.getString("_id"));
			usermsg.setName(object.getString("name"));
			usermsg.setEmail(object.getString("email"));
			usermsg.setNotes(object.getString("notes"));
			usermsg.setDatetime(object.getString("datetime"));
			usermsg.setVac(object.getString("vac"));
			usermsg.setStatus(object.getInt("status"));
			usermsg.setCode(object.getString("code"));

			ret.add(usermsg);
		}
		
		return ret;
	}	
	//--------------------------------------------------------------------------------------------------------------------------------------

	//--------------------------------------------------------------------------------------------------------------------------------------
	public List<UserMessage> findByPage(int pageNumber, int pageSize, int status) {
		try{
			if(StaticConfig.MS_DATA_SWITCHER == USE_DIFF_COLLECTIONS){
				return findByPageWithCollections(pageNumber, pageSize, status);
			}else{
				return findByPageNormal(pageNumber, pageSize, status);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			releaseMongoDBTemplate();
		}
	}
	public List<UserMessage> findByPageNormal(int pageNumber, int pageSize, int status) {
		Query query = new Query();
		if(code != null && code.length() > 0){
			query.addCriteria(Criteria.where("code").is(code));
		}
		query.addCriteria(Criteria.where("email").exists(true));
		query.addCriteria(Criteria.where("status").is(status));
		query.with(new Sort(Sort.Direction.DESC, "datetime"));
		query.skip(pageSize * pageNumber);
		query.limit(pageSize);
		StaticMongoTemplate.getStaticMongoTemplate().indexOps(UserMessage.class).ensureIndex(new Index().on("datetime", Direction.DESC));
		
		return StaticMongoTemplate.getStaticMongoTemplate().find(query, UserMessage.class);	
	}
	
	public List<UserMessage> findByPageWithCollections(int pageNumber, int pageSize, int status) {
		List<UserMessage> ret = new ArrayList<UserMessage>();
		
		BasicDBObject query = new BasicDBObject();
		query.put("status", status);
		
		Query qry = new Query();
		qry.fields().include("_id").include("name").include("email").include("notes").include("datetime").include("vac").include("status");

		BasicDBObject order = new BasicDBObject();
		order.append("datetime", -1);
		StaticMongoTemplate.getStaticMongoTemplate().indexOps(TABLE_PREFIX+code.toUpperCase()).ensureIndex(new Index().on("datetime", Direction.DESC));
		
		DBCollection collection = StaticMongoTemplate.getStaticMongoTemplate().getCollection(TABLE_PREFIX+code.toUpperCase());
		DBCursor cursorDoc = collection.find(query, qry.getFieldsObject()).skip(pageSize * pageNumber).limit(pageSize).sort(order);
		
		UserMessage usermsg = null;
		BasicDBObject object = null;
		while (cursorDoc.hasNext()) {
			usermsg = new UserMessage();
			object = (BasicDBObject) cursorDoc.next();
			usermsg.setId(object.getString("_id"));
			usermsg.setName(object.getString("name"));
			usermsg.setEmail(object.getString("email"));
			usermsg.setNotes(object.getString("notes"));
			usermsg.setDatetime(object.getString("datetime"));
			usermsg.setVac(object.getString("vac"));
			usermsg.setStatus(object.getInt("status"));
			usermsg.setCode(object.getString("code"));
			
			ret.add(usermsg);
		}
		
		return ret;
	}
	//--------------------------------------------------------------------------------------------------------------------------------------
	
	//--------------------------------------------------------------------------------------------------------------------------------------
	public DBCollection getCollection() {
		return collection;
	}

	public void setCollection(DBCollection collection) {
		this.collection = collection;
	}

	public static boolean createCollection(String collectionName){
		try{
			String i = "defaultTest";
			boolean flag = true;
			
			BasicDBObject document = new BasicDBObject();
			document.put("name", "staff name: staff"+i);
			document.put("question", "staff's question number:"+i);
			document.put("vac", "vac state:"+i);
			document.put("status", "1");
			document.put("number", i);
			document.put("date", new Date());
			
			if(flag){
				Mongo client = new MongoClient("localhost");
				new MongoTemplate(client, "testdb", null);
				//UserCredentials credential = new UserCredentials("admin", "admin");
				//MongoTemplate templateAuth = new MongoTemplate(client, "testdb", credential);
				MongoTemplate templateAuth = new MongoTemplate(client, "testdb");
				if(templateAuth.getCollection(collectionName) != null){
					templateAuth.dropCollection(collectionName);
				}
				CollectionOptions collectionOptions = new CollectionOptions(null, null, false);
				templateAuth.createCollection(collectionName, collectionOptions);
				templateAuth.indexOps(collectionName).ensureIndex(new Index().on("date",Direction.ASC));
				templateAuth.getCollection(collectionName).insert(document);

			}else{
				if(StaticMongoTemplate.getStaticMongoTemplate().getCollection(collectionName) != null){
					StaticMongoTemplate.getStaticMongoTemplate().dropCollection(collectionName);
				}
				CollectionOptions collectionOptions = new CollectionOptions(null, null, false);
				StaticMongoTemplate.getStaticMongoTemplate().createCollection(collectionName, collectionOptions);
				StaticMongoTemplate.getStaticMongoTemplate().indexOps(collectionName).ensureIndex(new Index().on("date",Direction.ASC));
				StaticMongoTemplate.getStaticMongoTemplate().getCollection(collectionName).insert(document);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return true;
	}
	
	public static void main(String[] args){
		try{
			ServerAddress server = StaticMongoTemplate.getTemplateServer();
			System.out.println("server.host:"+ server.getHost());
			System.out.println("server.port:"+ server.getPort());
			System.out.println("server.sameHost:"+ server.sameHost("localhost"));
			System.out.println("server.getSocketAddress.getAddress:"+ server.getSocketAddress().getAddress());
			
			if(createCollection("QuestionTableOMT")) {
				return;
			}
			
			Mongo client = new MongoClient("localhost");
			new MongoTemplate(client, "testdb", null);
			// mongod --auth [ for security reason ]
			UserCredentials credential = new UserCredentials("admin", "admin");
			MongoTemplate templateAuth = new MongoTemplate(client, "testdb", credential);
			
			boolean done = false;
			long startmi = 0;
			long endmi = 0;
			String tableName = "";
			int total = 10000; // one company, all staff 1000, 10 questions/person
			int count = 10;
			int skipcount = total - 9000;
			int limit = 100;
			// MongoTemplate templateNormal = new MongoTemplate(client, "testdb");
			
			// 2000 companies, 100 staff, 10 questions/person / one collection
			long totalstarttime = System.currentTimeMillis();
			if(!done){
			for(int c=0;c<count;c++){
				tableName = "AsxQuestionWBCOf"+c;
			
				BasicDBObject document = null;
				startmi = System.currentTimeMillis();
				System.out.println("\n\n------------------------------------------------------------------------");
				System.out.println("------------tableName:"+tableName);
				for(int i=0; i<total; i++){
					document = new BasicDBObject();
					document.put("name", "staff name: staff"+i);
					document.put("question", "staff's question number:"+i);
					document.put("vac", "vac state:"+i);
					document.put("status", "1");
					document.put("number", i);
					document.put("date", new Date());
					
					templateAuth.getCollection(tableName).insert(document);
					templateAuth.indexOps(tableName).ensureIndex(new Index().on("date",Direction.ASC));
				}
				endmi = System.currentTimeMillis();
				System.out.println("-------------insert total count:"+total+" seconds used:"+ (endmi - startmi)/1000 );
				
				
				startmi = System.currentTimeMillis();
				BasicDBObject querydb = new BasicDBObject();
				querydb.put("status", "1");
				//querydb.put("name", new BasicDBObject("$regex", "staff.*").append("$options", "i"));
				
				BasicDBObject orderBy = new BasicDBObject();
				orderBy.put("date", -1);
				
				DBCursor cursor = templateAuth.getCollection(tableName).find(querydb).skip(skipcount).sort(orderBy).limit(limit);
				if(cursor != null && cursor.hasNext()){
					System.out.println("-------------cursor.count():"+cursor.count());
					while(cursor.hasNext()){
						cursor.next();
					}
				}
				cursor.close();
				endmi = System.currentTimeMillis();
				System.out.println("-------------cursor query seconds used:"+ (endmi - startmi)/1000);

	
			templateAuth.indexOps(tableName).ensureIndex(new Index().on("date",Direction.ASC));
			startmi = System.currentTimeMillis();
			Query query = new Query();
			query.addCriteria(Criteria.where("status").is("1"));
			query.addCriteria(Criteria.where("name").regex("staff.*","i"));
			
			query.with(new Sort(Sort.Direction.DESC, "date"));
			query.skip(skipcount);
			query.limit(limit);
			
			List<BasicDBObject> retList = templateAuth.find(query, BasicDBObject.class, tableName);
			endmi = System.currentTimeMillis();
			//System.out.println("-------------list query end   time:"+endmi);
			System.out.println("-------------list query seconds used:"+ (endmi - startmi)/1000);
			if(retList != null){
				System.out.println("-------------list query.size():"+retList.size());
				for(BasicDBObject obj : retList){
					obj.getString("name");
				}
			}
			System.out.println("------------------------------------------------------------------------\n\n");
			}
		}
		long totalendtime = System.currentTimeMillis();
		System.out.println("-------------total time used:"+ (totalendtime - totalstarttime)/1000 +" seconds with total collections number:"+count+" each table has record number:"+total);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	//--------------------------------------------------------------------------------------------------------------------------------------

}

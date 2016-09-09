package com.omt.webservice;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class StaticMongoTemplate {
	
	private static MongoTemplate mongoTemplate;
	
	public static MongoTemplate getStaticMongoTemplate(){
		if(mongoTemplate == null){
			@SuppressWarnings("resource")  
			ApplicationContext ctx = new GenericXmlApplicationContext("spring-mongodb.xml");
			mongoTemplate = (MongoTemplate)ctx.getBean("mongoTemplate");
		}		
		return mongoTemplate;  
	}
	
	public static void releaseMongoDBTemplate(){
		if(mongoTemplate != null){
			if(mongoTemplate.getDb() != null){
				mongoTemplate.getDb().getMongo().close();
			} 
		}
	}
	
	public static ServerAddress getTemplateServer(){
		if(getStaticMongoTemplate() != null){
			ServerAddress server = mongoTemplate.getDb().getMongo().getAddress();
			return server;
		}else{
			return null;
		}
	}
	
	/**
	 * Create and get database instance operation template 
	 * @param dbName [Only with dbName, automatically get default server and without credential]
	 * @return MongoTemplate
	 */
	public static MongoTemplate createDBInstanceByName(String dbName){
		if(dbName == null || dbName.trim().length() == 0){
			return null;
		}
		MongoTemplate templateAuth = null;
		Mongo client = null;
		try{
			ServerAddress server = StaticMongoTemplate.getTemplateServer();
			client = new MongoClient(server.getHost());//, server.getPort());
			templateAuth = new MongoTemplate(client, dbName);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			//			if(client != null){
			//				try{
			//					client.close();
			//				}catch(Exception ex){
			//					ex.printStackTrace();
			//				}
			//			}
		}
		return templateAuth;
	}

	/**
	 * Create database instance with credential while using default server
	 * @param dbName
	 * @param userName
	 * @param password
	 * @return MongoTemplate
	 */
	public static MongoTemplate createDBInstanceWithCredential(String dbName, String userName, String password){
		if(dbName == null || dbName.trim().length() == 0 || userName == null || password == null){
			return null;
		}
		MongoTemplate templateAuth = null;
		Mongo client = null;
		try{
			ServerAddress server = StaticMongoTemplate.getTemplateServer();
			client = new MongoClient(server.getHost(), server.getPort());
			UserCredentials credential = new UserCredentials(userName, password);
			templateAuth = new MongoTemplate(client, dbName, credential);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			if(client != null){
				try{
					client.close();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
		return templateAuth;
	}	

	/**
	 * Create database instance with given server and dbName without credential
	 * @param server
	 * @param dbName
	 * @return MongoTemplate
	 */
	public static MongoTemplate createDBInstanceWithServer(String host, int port, String dbName){
		if(dbName == null || dbName.trim().length() == 0 || host == null || host.trim().length() == 0 || port < 1000){
			return null;
		}
		MongoTemplate templateAuth = null;
		Mongo client = null;
		try{
			client = new MongoClient(host, port);
			templateAuth = new MongoTemplate(client, dbName);

		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(client != null){
				try{
					client.close();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
		return templateAuth;
	}
	
	/**
	 * Create database instance with given server and dbName with credential
	 * @param server
	 * @param dbName
	 * @param userName
	 * @param password
	 * @return MongoTemplate
	 */
	public static MongoTemplate createDBInstanceWithServerPwd(String host, int port, String dbName, String userName, String password){
		if(dbName == null || dbName.trim().length() == 0 || host.trim().length() == 0 || port < 1000 || userName == null || password == null){
			return null;
		}
		MongoTemplate templateAuth = null;
		Mongo client = null;
		try{
			client = new MongoClient(host, port);
			UserCredentials credential = new UserCredentials(userName, password);
			templateAuth = new MongoTemplate(client, dbName, credential);

		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(client != null){
				try{
					client.close();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
		return templateAuth;
	}
	
}

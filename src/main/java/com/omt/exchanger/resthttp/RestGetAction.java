package com.omt.exchanger.resthttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import com.omt.config.StaticConfig;
import com.omt.exchanger.entity.Company;
import com.omt.exchanger.entity.User;

/**
 * Get data from server use Restful API GET  
 * @author tonyliu
 *
 */
public class RestGetAction {
	
	private static Logger omtlogger = Logger.getLogger(RestGetAction.class);
	
	/**
	 * Use resttemplate get data from server
	 */
	public static Company[] RestTempGetCompanies(){
	    omtlogger.info("RestTempGetData-------start to get company list...");
	    Company[] ret = {};
	    
	    Map<String, String> params = new HashMap<String, String>();
	    RestTemplate restTemplate = new RestTemplate();
	    
	    try{
	    	ret = restTemplate.getForObject(StaticConfig.restCompaniesUrl, Company[].class, params);
		    
		    omtlogger.info("RestTempGetData-------company:"+ret);
		    if(ret != null){
			    omtlogger.info("RestTempGetData-------compan.length:"+ret.length);
			    for(Company obj : ret){
				    omtlogger.info("RestTempGetData-------compan.companyTicker:"+obj.getCompanyTicker() +" -- id:"+obj.getId() 
				    +"-- company name:"+obj.getCompanyName() +"-- company desc:" +obj.getCompanyDescription());
			    }
		    }
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
	    return ret;
	}
	
	/**
	 * Use resttemplate get data from server
	 */
	public static User[] RestTempGetUsers(String cpId){
	    omtlogger.info("RestTempGetData-------start to get user list of:"+cpId);
	    User[] ret = {};
	    
	    Map<String, String> params = new HashMap<String, String>();
	    RestTemplate restTemplate = new RestTemplate();
	    
	    try{
	    	ret = restTemplate.getForObject(StaticConfig.restLocalUserUrl.replace(StaticConfig.replaceValue, cpId), User[].class, params);
		    
		    omtlogger.info("RestTempGetData-------user:"+ret);
		    if(ret != null){
			    omtlogger.info("RestTempGetData-------user.length:"+ret.length);
			    for(User obj : ret){
				    omtlogger.info("RestTempGetData-------user.getName:"+obj.getName() +" -- getUserId:"+obj.getUserId());
			    }
		    }
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
	    return ret;
	}

	/**
	 * Use resttemplate get data from server
	 */
	public static String[] RestTempGetData(String code){
	    omtlogger.info("RestTempGetData-------start with code:" + code);
	    String[] DataListCache = {};
	    if(code == null || code.length() == 0){
	    	return DataListCache;
	    }
	    
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("code", code);
	    RestTemplate restTemplate = new RestTemplate();
	    
	    try{
	    	DataListCache = restTemplate.getForObject(StaticConfig.restCompaniesUrl, String[].class, params);
		    
		    omtlogger.info("RestTempGetData-------DataListCache:"+DataListCache);
		    if(DataListCache != null){
			    omtlogger.info("RestTempGetData-------DataListCache.length:"+DataListCache.length);

		    }
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
	    return DataListCache;
	}
	
	
	/**
	 * Get Json data list from specified URL via http
	 */
	public static void RestPureGetData(String urlstr){
		try{
			URL url = new URL(urlstr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
		
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
		
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		
			String output;
			omtlogger.info("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				omtlogger.info("\n--RestPureGetData"+output);
			}

		  } catch (MalformedURLException ex) {
	
			ex.printStackTrace();
	
		  } catch (IOException e) {
	
			e.printStackTrace();
	
		  }
	}
}

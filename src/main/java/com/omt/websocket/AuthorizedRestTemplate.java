package com.omt.websocket;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class AuthorizedRestTemplate extends RestTemplate{

	private Logger omtlogger = Logger.getLogger(AuthorizedRestTemplate.class);
 	private String userid;
	private String usersecure;
	
    private String username;
    private String password;
    
    private String granttype;
    private String scope;

    public AuthorizedRestTemplate(String inuserid, String inusersecure, String inusername, String inpassword, String ingranttype, String inscope){
    	this.userid = inuserid;
    	this.usersecure = inusersecure;
    	
        this.username = inusername;
        this.password = inpassword;
        
        this.granttype = ingranttype;
        this.scope = inscope;
    }

    //----------------------------------------------------------------------------------------------------------
    // For refresh token use
    public String getForObjectRefresh(String url, String refresh_token){
        return authorizedRestCallRefresh(this, url, refresh_token);
    }
    private String authorizedRestCallRefresh(RestTemplate restTemplate, String url, String refresh_token){
        HttpEntity<Object> request = getRequestRefresh(refresh_token);
        
        ResponseEntity<String> entity = restTemplate.postForEntity(url, request, String.class);
        return entity.getBody();
    }
    private HttpEntity<Object> getRequestRefresh(String refresh_token){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + getBase64Credentials());
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("grant_type", granttype);
        body.add("refresh_token", refresh_token);
        
        omtlogger.info("------Refresh getBase64Credentials:" + getBase64Credentials());
        return new HttpEntity<Object>(body,headers);
    } 

    //----------------------------------------------------------------------------------------------------------
    // For Firsh token use
    public String getForObject(String url, Object... urlVariables){
        return authorizedRestCall(this, url, urlVariables);
    }

    private String authorizedRestCall(RestTemplate restTemplate, String url, Object... urlVariables){
        HttpEntity<Object> request = getRequest();
        
        //ResponseEntity<String> entity = restTemplate.postForEntity(url, request, String.class, urlVariables);
        ResponseEntity<String> entity = restTemplate.postForEntity(url, request, String.class);
        return entity.getBody();
    }

    private HttpEntity<Object> getRequest(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + getBase64Credentials());
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("grant_type", granttype);
        body.add("username", username);
        body.add("password", password);
        body.add("scope", scope);
        
        omtlogger.info("------getBase64Credentials:" + getBase64Credentials());
        return new HttpEntity<Object>(body,headers);
    }

    private String getBase64Credentials(){
    	try{
            String plainCreds = userid + ":" + usersecure;
            byte[] plainCredsBytes = plainCreds.getBytes("UTF-8");
            byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
            return new String(base64CredsBytes);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
        return "";
    }
}
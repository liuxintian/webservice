package com.omt.exchanger.resthttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import com.omt.config.StaticConfig;

/**
 * Post data list via Restful Post Request
 * @author tonyliu
 *
 */
public class RestPostAction {
	
	private static Logger omtlogger = Logger.getLogger(RestPostAction.class);
	
	/**
	 * Rest Post via RestTemplate
	 */
	public static void RestTempPostData(String message){
	    
	    RestTemplate restTemplate = new RestTemplate();
	    try{
			String result = restTemplate.postForObject(StaticConfig.restPostMsgUrl, message, String.class);
			omtlogger.info("RestTempPostData-------result:"+result);		
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
	}
	
	/**
	 * Post data to the specified server via http Rest API
	 */
	public static void RestPurePostData(){
		try {

			URL url = new URL(StaticConfig.restPostMsgUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String input = "{\"name\":100,\"email\":\"email@email.exchanger.com\",\"datetime\":\"31/03/2016 10:22:20\"}";

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			omtlogger.info("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				omtlogger.info(output);
			}

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		 }		
	}
}

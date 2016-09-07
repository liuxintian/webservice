package com.omt.agency;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class GetFileAction {
	public static final String REST_GET_API_URL = "http://localhost:8080/omtwebservice/testexc/getdatalist/{name}";

	public static void restGetFile(){
		try{
			// 1. get the file from URI
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());    
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
			HttpEntity<String> entity = new HttpEntity<String>(headers);

			ResponseEntity<byte[]> response = restTemplate.exchange(REST_GET_API_URL, HttpMethod.GET, entity, byte[].class, "1");

			if(response.getStatusCode().equals(HttpStatus.OK))
			{       
				FileOutputStream output = new FileOutputStream(new File("filename.jar"));
				IOUtils.write(response.getBody(), output);
			}
			
			// 2. store files into mongodb with the datetime
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
}

package com.omt.agency;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.gridfs.GridFSDBFile;
import com.omt.agency.dao.FileStorageDaoImpl;
import com.omt.webservice.control.RestfulController;

/**
 * This is responsible for query mongodb to get File(s) and return to the client(s)
 * @author tonyliu
 *
 */
@RestController
@RequestMapping(value="/agency")
public class RestAgencyController {
	private Logger omtlogger = Logger.getLogger(RestfulController.class);

	@Autowired
	@Qualifier("fileStorageDaoImpl")
	public FileStorageDaoImpl fileStorageDaoImpl;

	@RequestMapping(value="/getonefileOne", method = RequestMethod.POST)
	public ResponseEntity<InputStream> getFiled(@RequestBody String fileName, HttpServletResponse response) throws IOException{

	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	    headers.add("Pragma", "no-cache");
	    headers.add("Expires", "0");
	    headers.add("attachment", "filename="+fileName);
	    
	    GridFSDBFile result = fileStorageDaoImpl.getByFilename("test.png");
	    result.writeTo("/Users/tonyliu/Desktop/testmid.png");
	    
	    InputStream is = new FileInputStream(new File("/Users/tonyliu/Desktop/testmid.png"));
	    return new ResponseEntity<InputStream>(is, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getonefileTwo", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Object> getFilew(@RequestBody String fileName, HttpServletRequest request) throws IOException{
		omtlogger.info("fileName:"+fileName);
	    ResponseEntity<Object> respEntity = null;
	    
		GridFSDBFile resultf = fileStorageDaoImpl.getByFilename(fileName);
		resultf.writeTo("/Users/tonyliu/Desktop/"+fileName);
		File result = new File("/Users/tonyliu/Desktop/"+fileName);

	    if(result.exists()){
	        InputStream inputStream = new FileInputStream("/Users/tonyliu/Desktop/"+fileName);
	        String type = URLConnection.guessContentTypeFromName("/Users/tonyliu/Desktop/"+fileName);
	        if(type == null){
	        	type = resultf.getContentType();
	        }
	        omtlogger.info("content-type: "+type);
	        
	        byte[]out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

	        HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.add("content-disposition", "attachment; filename=" + fileName);
	        responseHeaders.add("Content-Type",type);

	        respEntity = new ResponseEntity<Object>(out, responseHeaders,HttpStatus.OK);
	    }else{
	        respEntity = new ResponseEntity<Object> ("File Not Found", HttpStatus.OK);
	    }
	    return respEntity;
	}
	
	@RequestMapping(value = "/getonefileThree", method = RequestMethod.POST)
	public void getFileWithName(@RequestBody String fileName, HttpServletResponse response) {
		try {
			omtlogger.info("content-type fileName:"+fileName);
			//1. Find file from mongodb
			GridFSDBFile result = fileStorageDaoImpl.getByFilename("test.png");
			result.writeTo("/Users/tonyliu/Desktop/testmid.png");
			
			//2. set response header
		    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		    response.setHeader("Pragma", "no-cache");
		    response.setHeader("Expires", "0");
		    response.setHeader("attachment", "filename="+fileName);
		    
		    //3. return to the client [Any file types]
			InputStream is = new FileInputStream(new File("/Users/tonyliu/Desktop/testmid.png"));
			org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();
	    } catch (IOException ex) {
	    	throw new RuntimeException("IOError writing file to output stream");
	    }
	}
	
	@RequestMapping(value = "/getallfiles", method = RequestMethod.GET)
	public void getAllFiles(HttpServletResponse response) {
		try {
			
			//1. Find file from mongodb
			List<String> filelist = new ArrayList<String>();
			
			for(String fileName: filelist){
				//2. set response header
			    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			    response.setHeader("Pragma", "no-cache");
			    response.setHeader("Expires", "0");
			    response.setHeader("attachment", "filename="+fileName);
			    
			    //3. return to the client [Any file types]
				InputStream is = new FileInputStream(new File(fileName));
				org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
			}
			response.flushBuffer();

		} catch (IOException ex) {
	    	throw new RuntimeException("IOError writing file to output stream");
	    }
	}
	
//	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/pdf")
//	public ResponseEntity<InputStreamResource> downloadPDFFile() throws IOException {
//
//	    ClassPathResource pdfFile = new ClassPathResource("pdf-sample.pdf");
//
//	    HttpHeaders headers = new HttpHeaders();
//	    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//	    headers.add("Pragma", "no-cache");
//	    headers.add("Expires", "0");
//	    
//	    return ResponseEntity
//	            .ok()
//	            .headers(headers)
//	            .contentLength(pdfFile.contentLength())
//	            .contentType(MediaType.parseMediaType("application/octet-stream"))
//	            .body(new InputStreamResource(pdfFile.getInputStream()));
//	}
}

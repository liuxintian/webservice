package com.omt.agency;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.omt.agency.dao.FileStorageDaoImpl;
import com.omt.webservice.Constants;
import com.omt.webservice.UtilLibs;

@Controller
@RequestMapping(value="/agencyweb")
public class AgencyWebController {

	@Autowired
	@Qualifier("fileStorageDaoImpl")
	public FileStorageDaoImpl fileStorageDaoImpl;
	
	/**
	 * Use this to:
	 * webpage query all the message list and return to view
	 * @return
	 */
	@RequestMapping(value="/index" , method = RequestMethod.GET)
	public String getuserweb(HttpServletRequest request) {
    	InputStream inputStream = null;
    	DBObject metaData = new BasicDBObject();
    	metaData.put("datetime", UtilLibs.GetLocalDateFmt(Constants.SYS_TM_FMT));
    	
    	try {
    		inputStream = new FileInputStream("/Users/tonyliu/Desktop/png.png");
    		fileStorageDaoImpl.store(inputStream, "png.png", "image/png", metaData);

    		inputStream = new FileInputStream("/Users/tonyliu/Desktop/csv.csv");
    		fileStorageDaoImpl.store(inputStream, "csv.csv", "text/csv", metaData);
    		
    		inputStream = new FileInputStream("/Users/tonyliu/Desktop/pdf.pdf");
    		fileStorageDaoImpl.store(inputStream, "pdf.pdf", "application/pdf", metaData);
    		
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	} finally {
    		if (inputStream != null) {
    			try {
    				inputStream.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	} 
    	
    	GridFSDBFile result = fileStorageDaoImpl.getByFilename("test.png");

	 	{
	 		try {
	 			System.out.println(result.getFilename());
	 			System.out.println(result.getContentType());
	 				
	 			//save as another image
	 			result.writeTo("/Users/tonyliu/Desktop/testFromDb.png");
	 		} catch (IOException e) {
	 			e.printStackTrace();
	 		}
	 	}
 	
    	return "index";
	}
	
}

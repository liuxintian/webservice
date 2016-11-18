package com.omt.cms.cust.admin.rest.api.tools;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.omt.cms.core.service.bo.base.FileInfoBO;
import com.omt.cms.file.op.FileOperations;
import com.omt.cms.file.op.FileUrl;

/**
 * @author Shiva Kalgudi
 * 
 */

@Controller
@RequestMapping("/cust-admin/api/files")
public class FileController     {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);  
	
	public final static int  BUFFER_SIZE  = 2048;
	public final static String FileWebBasePath = "/assets/uploaded/";
	@Autowired
    ServletContext context; 
	@Autowired FileOperations fOps;

	@RequestMapping(value = "/upload/common", method = RequestMethod.POST)
	public ResponseEntity<FileInfoBO> commonFileUpload( @RequestParam("contextType") String contextType, @RequestParam("file") MultipartFile file) {
		logger.info("Request for file upload with file name "+file.getOriginalFilename());
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		FileInfoBO fileInfo= new FileInfoBO();
		if(file!=null && !file.isEmpty()){
			try {
				FileUrl finput = new FileUrl();
				finput.setFileFullPath(context.getRealPath(FileWebBasePath));
				
				FileUrl fu = fOps.store(file, finput);
				String url=FileWebBasePath+fu.getFileName();
				statusCode=HttpStatus.OK;
				fileInfo.setFileURL(url);
				fileInfo.setFileName(file.getOriginalFilename());
			}catch(Exception e){
				logger.error("Error occurred while uploading the file :{}", e);
				statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}
		return new ResponseEntity<FileInfoBO>(fileInfo, statusCode);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<FileInfoBO> delete( @RequestParam("key") String key ) {
		logger.info("Request for file delete with file name "+key);
		boolean status=false;
		FileInfoBO fileInfo= new FileInfoBO();
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		if(key!=null){
			FileUrl finput = new FileUrl();
			finput.setFileFullName(context.getRealPath(key));
			
			status=fOps.delete(finput);
			if(status){
				statusCode=HttpStatus.OK;
				fileInfo.setFileURL(key);

			} else
				statusCode=HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<FileInfoBO>(fileInfo, statusCode);
	}

}
package com.omt.cms.core.api.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.omt.cms.core.service.base.DataServiceException;
import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceResult;

/**
 * @author Shiva Kalgudi
 *
 */

@ControllerAdvice
public class ServiceExceptionHandler {

	public static final String SERVICE_RESULT_ATTR = "_sericeResult";
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceExceptionHandler.class);    

	
    @ExceptionHandler(value = DataServiceException.class)
    public ResponseEntity<ServiceResult> defaultErrorHandler(DataServiceException e) throws Exception {
    	
    	ServiceResult result = e.getResult();
       	HttpStatus statusCode = HttpStatus.BAD_REQUEST;   

       	if(ServiceResultCodes.USER_ATTRIBUTES_NF.getValue().equals(result.getResultCode())){
    		statusCode = HttpStatus.UNAUTHORIZED;
    	
       	}else if(ServiceResultCodes.OPERATION_DENIED.getValue().equals(result.getResultCode())){
    		statusCode = HttpStatus.CONFLICT;
    	
       	}else if(ServiceResultCodes.REQUEST_DATA_INVALID.getValue().equals(result.getResultCode())){
    		statusCode = HttpStatus.BAD_REQUEST;
    	
       	}else if(ServiceResultCodes.RECORD_NOT_FOUND.getValue().equals(result.getResultCode())){
    		statusCode = HttpStatus.NOT_FOUND;

       	}else if(ServiceResultCodes.RECORD_ACCESS_DENIED.getValue().equals(result.getResultCode())){
    		statusCode = HttpStatus.FORBIDDEN;
    	}
        
       	return new ResponseEntity<ServiceResult>(result, statusCode);
    }

    @ExceptionHandler(Exception.class)
    public void exception(Exception e) throws Exception{
    	LOGGER.info("Exception occurred while handling request :{}", e);
        throw e;
    }
}

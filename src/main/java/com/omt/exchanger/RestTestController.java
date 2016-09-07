package com.omt.exchanger;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Use this class to Test for both Rest Get and Rest Post
 * @author tonyliu
 *
 */

@RestController
@RequestMapping(value="/testexc")
public class RestTestController {
	private Logger omtlogger = Logger.getLogger(RestTestController.class);
	
	@RequestMapping(value="/getdatalist/{name}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE) 
    public @ResponseBody List<String> getdatalist(@PathVariable("name") String name) {  
    	omtlogger.info("Start to getdatalist...with name:"+name);
    	
    	List<String> usermsglist = new ArrayList<String>();
    	for(int i=0;i<100;i++){
    		usermsglist.add("Just a test string of count:"+i);
    	}

        return usermsglist;
    }
	
	@RequestMapping(value="/postdatalist", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE) 
    public @ResponseBody String postdatalist(@RequestBody List<String> list) {
		if(list != null && list.size() > 0){
			for(String v: list){
		    	omtlogger.info("Received list string:" + v);
			}
		}
        return "Success!";
    }	
}

package com.omt.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.omt.exchanger.entity.CommonUVO;
import com.omt.exchanger.entity.Company;
import com.omt.exchanger.entity.ProcessVO;
import com.omt.exchanger.entity.ProgressVO;
import com.omt.exchanger.resthttp.RestGetAction;
import com.omt.exchanger.resthttp.ThreadPoolTask;
import com.omt.webservice.DataCache;
import com.omt.webservice.UtilLibs;


/**
 * Get Specialized data list and send the list to black box via Restful APIs
 * @author tonyliu
 *
 */
@Controller
@RequestMapping(value="/exchanger")
public class ExchangeController {
	private Logger omtlogger = Logger.getLogger(ExchangeController.class);

	/**
	 * Use this to:
	 * return to data exchange view
	 * @return
	 */
	@RequestMapping(value="/getsend" , method = RequestMethod.GET)
	public String exchangeinit(HttpServletRequest request) {
    	omtlogger.info("Start to exchangeinit...");
		//WestWebClient.webclientPage();
		

		return "getsend";
	}
	
	@RequestMapping(value="/getbatchid" , method = RequestMethod.POST)
	@ResponseBody
	public String getbatchid() {
    	omtlogger.info("Start to getbatchid...");
    	String ret = UtilLibs.GetRandBatchId();
    	omtlogger.info("Getbatchid :" +ret);
    	
		return ret;
	}	
	
	@RequestMapping(value="/codelist" , method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CommonUVO> codelist() {
    	omtlogger.info("Start to exchangeinit...");
    	List<CommonUVO> companies = new ArrayList<CommonUVO>();
    	
    	Company[] ret = RestGetAction.RestTempGetCompanies();
    	if(ret != null && ret.length > 0){
        	CommonUVO vo = null;
        	for(Company company: ret){
        		vo = new CommonUVO();
        		vo.setText(company.getCompanyTicker().toUpperCase());
        		vo.setValue(String.valueOf(company.getId()));
        		if(vo.getText().equals("OMT")){
        			vo.setSelected(true);
        		}
        		companies.add(vo);
        	}
    	}else{
    		CommonUVO vo = new CommonUVO();
    		vo.setText("OMT");
    		vo.setValue("202");
    		vo.setSelected(true);
    		companies.add(vo);
    	}
		return companies;
	}
	
	/**
     * @Real
	 * Use this to:
	 * webpage query all the message list and return to view
	 * @return
	 */	
	@RequestMapping(value="/startprocess",method=RequestMethod.POST)
	@ResponseBody
	public String startprocess(ProcessVO uvo) throws Exception{
    	omtlogger.info("startprocess with cpId:" + uvo.getCpId() +"-- mode:"+uvo.getMode() +"-- batchid:"+uvo.getBatchid() +"-- message:"+uvo.getMessage());
    	
    	{
            ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 200, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), new ThreadPoolExecutor.DiscardOldestPolicy());  
            threadPool.execute(new ThreadPoolTask(uvo));
            
    	    return uvo.getCpId();
    	}
	}
	
	/**
     * @Real
	 * Use this to:
	 * webpage query all the message list and return to view
	 * @return
	 */	
	@RequestMapping(value="/queryprocess",method=RequestMethod.POST)
	@ResponseBody
	public String queryprocess(String batchid) throws Exception{
    	omtlogger.info("ExchangeMessage with batchid:" + batchid);
    	String value= "0";
    	if(batchid != null && DataCache.ProgressCache != null){
    		ProgressVO uvo = DataCache.ProgressCache.get(batchid);
    		if(uvo != null && uvo.getTotal() != 0){
        		value = String.valueOf(uvo.getDone()*100/uvo.getTotal());
    		}
    	}
    	
	    return value;
	}
	
}

package com.omt.webservice.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.omt.asxdata.entity.WestNews;
import com.omt.config.StaticConfig;
import com.omt.webservice.Constants;
import com.omt.webservice.OmtBasicClass;
import com.omt.webservice.RestUriConstant;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.dao.WestPacDao;
import com.omt.webservice.entity.DataGridModel;
import com.omt.webservice.entity.HistoryPureVO;
import com.omt.webservice.entity.MessageHistVO;
import com.omt.webservice.entity.UserMessage;
import com.omt.webservice.entity.UserStatus;
import com.omt.webservice.mail.MailTaskVO;
import com.omt.webservice.mail.SendMailCfm;
import com.omt.webservice.mail.ThreadMailTask;
import com.omt.webservice.security.main.domain.UserDao;

/**
 * WebpageController
 * WebpageController using for web page to query and operate user messages
 * @author tonyliu 
 */
@Controller
@RequestMapping(value=RestUriConstant.WEB_PAGE)
public class WebpageController extends OmtBasicClass
{
	private Logger omtlogger = Logger.getLogger(WebpageController.class);
	
	@Autowired
	HttpServletRequest request;
	
	private String getSessionCode(){
		String code = "";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles != null && roles.size() > 0 && !roles.contains(UserDao.roleadmin)){
            code = roles.toArray()[0].toString().trim().toLowerCase();
        }else{
        	if(roles.contains(UserDao.roleadmin)){
        	    code = (String) request.getSession().getAttribute("code");
        	    if(code == null || code.trim().length() == 0){
        	    	code = "";
        			request.getSession().removeAttribute("code");
        			request.getSession().setAttribute("code", code);
        	    }
        	}
        }
		return code;
	}
	
	@RequestMapping(value=RestUriConstant.WEB_PAGE_SETSESSIONCODE , method = RequestMethod.POST)
	@ResponseBody
	private void setSessionCode(String code){
		request.getSession().removeAttribute("code");
		request.getSession().setAttribute("code", code);
	}

    //----------------------------------------------------------------------------------------------------------
	/**Westpac 
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WEB_PAGE_NEWSLIST , method = RequestMethod.GET)
	public String newslist(HttpServletRequest request) {
    	omtlogger.info("Enter into news list...");
    	return "westpac";
	}
	/**
     * @Real
	 * Use this to:
	 * webpage query all the message list and return to view
	 * @return
	 */	
	@RequestMapping(value=RestUriConstant.WEB_PAGE_NEWSTABLE, method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryNewsList(DataGridModel dgm) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>(2); 
		List<WestNews> list = WestPacDao.findByPage(dgm.getPage()-1, dgm.getRows());
		
		result.put("total", WestPacDao.totalCount());
		result.put("rows", list);
    	omtlogger.trace("Result of queryList size:"+list.size());
	    return result;
	}	
	
    @RequestMapping(value=RestUriConstant.WEB_PAGE_NEWSDOWNLOAD, method=RequestMethod.GET)
    @ResponseBody
    public void downloadCSV(HttpServletResponse response) throws IOException {
 
        // creates mock data
        response.setHeader("Content-Disposition", "attachment; filename=\""+ StaticConfig.newslistfile +"\"");
        response.setContentType("text/csv");
 
        InputStream inputStream = new FileInputStream(new File(StaticConfig.filesBasePath+"/"+StaticConfig.newslistfile));
        OutputStream outStream = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead = -1;

        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outStream.flush();
        outStream.close();
        return;
    }
    
    //----------------------------------------------------------------------------------------------------------
    // sending emails
	@RequestMapping(value=RestUriConstant.WEB_EMAIL_CONFIRMATION, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE) 
	@ResponseBody 
    public List<String> sendEmailConfirm(@RequestBody List<SendMailCfm> cfmlist) {
		List<String> retlist = new ArrayList<String>();
		if(cfmlist != null && cfmlist.size() > 0){
			StringBuffer str = new StringBuffer();
			
			for(SendMailCfm cfm: cfmlist){
				// validate email address && number[]
				
				str = new StringBuffer();
				str.append(StaticConfig.mailBody.replace(StaticConfig.mailBodyReplacement, cfm.getNumber()));
				omtlogger.info("----Received SendMailConfirmation :"+str.toString());
				retlist.add(str.toString());
				
				MailTaskVO uvo = new MailTaskVO();
				uvo.setTo(cfm.getEmail());
				uvo.setSubject(StaticConfig.mailSubject);
				uvo.setBody(str.toString());
				
				omtlogger.info("----Check send mail switch is:"+StaticConfig.mailSwitcher);
				if(StaticConfig.mailSwitcher.equals(StaticConfig.MAIL_SWITCHER_ON)){
		            ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 200, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), new ThreadPoolExecutor.DiscardOldestPolicy());  
		            threadPool.execute(new ThreadMailTask(uvo));
				}
			}
		}
		return retlist;
	}
	
    //----------------------------------------------------------------------------------------------------------
	/** Online Users
	 */
	@RequestMapping(value=RestUriConstant.WEB_ST_PAGE_USERSTATUS , method = RequestMethod.GET)
	public String getuserstatus(HttpServletRequest request) {
    	omtlogger.info("Start to getuserstatus...");
    	
    	return "userstatus";
	}
	/**
     * @Real
	 * Use this to:
	 * webpage query all the message list and return to view
	 * @return
	 */	
	@RequestMapping(value=RestUriConstant.WEB_PAGE_QUERY_ONLINE, method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryOnline(DataGridModel dgm) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>(2); 
		List<UserStatus> list = OnlineFindByPage(dgm.getPage()-1, dgm.getRows(), 1);
		
		result.put("total", OnlineTotalCount(1));
		result.put("rows", list);
    	omtlogger.trace("Result of queryList size:"+list.size());
	    return result;
	}
	private int OnlineTotalCount(int status){
		int count = 0;
		Query query = new Query();
		query.addCriteria(Criteria.where("emailid").ne(""));
		query.addCriteria(Criteria.where("status").is(status));
		count = (int) StaticMongoTemplate.getStaticMongoTemplate().count(query, UserStatus.class);
		return count;
	}
	private List<UserStatus> OnlineFindByPage(int pageNumber, int pageSize, int status) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.addCriteria(Criteria.where("emailid").ne(""));
		 query.addCriteria(Criteria.where("status").is(status));
		 query.with(new Sort(Sort.Direction.DESC,"time"));
		 query.skip(pageSize * pageNumber);
		 query.limit(pageSize);
		 StaticMongoTemplate.getStaticMongoTemplate().indexOps(UserStatus.class).ensureIndex(new Index().on("time", Direction.DESC));
		 
		 return StaticMongoTemplate.getStaticMongoTemplate().find(query, UserStatus.class);		
	}
	
	
	//----------------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------------
	/**Approved Question list
	 */
	@RequestMapping(value=RestUriConstant.WEB_PAGE_ENTER_APPROVEMSG , method = RequestMethod.GET)
	public String getapproved( ModelMap model) {
    	omtlogger.info("Start to get user message approved...with code:"+getSessionCode());
    	model.put("code", getSessionCode());
    	return "approved";
	}

	/**
     * @Real
	 * Use this to:
	 * webpage query all the message list and return to view
	 * @return
	 */	
	@RequestMapping(value=RestUriConstant.WEB_PAGE_QUERY_APPROVEMSG, method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryApproved(DataGridModel dgm) throws Exception{
    	Map<String, Object> result = new HashMap<String, Object>(2); 
    	if(getSessionCode() == null){
    		return result;
    	}
    	try{
    		this.setCode(getSessionCode());
    		List<UserMessage> list = findByPage(dgm.getPage()-1, dgm.getRows(), Constants.MSG_STATUS_DIRE_APPROVED);
    		
    		result.put("total", totalCount(Constants.MSG_STATUS_DIRE_APPROVED));
    		result.put("rows", list);
        	omtlogger.trace("Result of queryList size:"+list.size());
    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
	    return result;
	}	
	/**
     * @Real
     * This method will delete an usermsg by it's msgid value.
     */
    @RequestMapping(value=RestUriConstant.WEB_PAGE_POST_APPROVEMSG, method=RequestMethod.POST)
    @ResponseBody
	public Map<String, String> approveusermsg(@RequestParam("msgid") String msgid) {
    	omtlogger.info("Start to approve usermsg...with id: "+msgid);
    	Map<String, String> map = new HashMap<String, String>(); 
    	if(msgid == null || msgid.length() == 0) {
    		map.put("result", "Failed with wrong parameter.");
    		return map;
    	}
    	try{
    		setCode(getSessionCode());
    		List<String> myList = new ArrayList<String>(Arrays.asList(msgid.split(",")));
    		for(String id: myList){
    			UserMessage current = findOne(id);
    			// Only normal messages can be approved
    			if(current.getStatus() == Constants.MSG_STATUS_NORMAL){
    				updateFirst(id, current.getStatus() + Constants.MSG_STATUS_APPROVED);
    			}
    		}
    		map.put("result", "Successfully approved the messages.");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		omtlogger.fatal(ex.getMessage());
    		map.put("result", "Failed to approved the messages.");
    	}
    	return map;
	}    

	//----------------------------------------------------------------------------------------------------------
    // Query Question History
	@RequestMapping(value=RestUriConstant.WEB_PAGE_QUESTIONHISTORY, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE) 
    public @ResponseBody List<MessageHistVO> getuserhistmsg(@RequestBody String names, @RequestHeader(value=RestUriConstant.HEADER_REQ_CODE, defaultValue=RestUriConstant.HEADER_REQ_CODE_DEFAULT) String code) {  
    	omtlogger.info("Start to noauth getuserhistmsg...with code:"+ code +"| names:"+names);
    	List<MessageHistVO> retlist = new ArrayList<MessageHistVO>();
    	if(code == null || code.trim().length() == 0){
    		omtlogger.warn("Invalid request code!");
    	}

    	try{
    		setCode(code.toUpperCase());
        	MessageHistVO uvo = new MessageHistVO();
        	if(names.indexOf(",") == -1){
        		uvo.setEmail(names);
        		List<UserMessage> hislist = new ArrayList<UserMessage>();
        		List<HistoryPureVO> inputlist = new ArrayList<HistoryPureVO>();
        		hislist = findByName(names);
        		if(hislist != null && hislist.size() > 0){
        			HistoryPureVO histvo = new HistoryPureVO();
            		for(UserMessage um: hislist){
            			histvo = new HistoryPureVO();
            			histvo.setMessage(um.getNotes());
            			histvo.setDatetime(um.getDatetime());
            			histvo.setStatus(um.getStatus());
            			inputlist.add(histvo);
            		}
        		}
        		uvo.setMessageList(inputlist);
            	retlist.add(uvo);
        	}else{
        		String[] namelist = names.split(",");
    			for(String name: namelist){
    	    		uvo.setEmail(name);
    	    		List<UserMessage> hislist = new ArrayList<UserMessage>();
    	    		List<HistoryPureVO> inputlist = new ArrayList<HistoryPureVO>();
    	    		hislist = findByName(name);
    	    		if(hislist != null && hislist.size() > 0){
    	    			HistoryPureVO histvo = new HistoryPureVO();
    	        		for(UserMessage um: hislist){
    	        			histvo = new HistoryPureVO();
    	        			histvo.setMessage(um.getNotes());
    	        			histvo.setDatetime(um.getDatetime());
    	        			histvo.setStatus(um.getStatus());
    	        			inputlist.add(histvo);
    	        		}
    	    		}
    	    		uvo.setMessageList(inputlist);
    	        	retlist.add(uvo);

        		}
        	}
        	omtlogger.info("return getuserhistmsg size:"+retlist.size());
   		
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
        return retlist;
    }
	//----------------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------------
	/**Common Question List
	 */
	@RequestMapping(value=RestUriConstant.WEB_PAGE_INDEX , method = RequestMethod.GET)
	public String getuserweb(ModelMap model) {
    	omtlogger.info("Start to get user message normal...with code: "+ getSessionCode());
    	model.put("code", getSessionCode());
    	return "index";
	}
	
	/**
     * @Real
	 * Use this to:
	 * webpage query all the message list and return to view
	 * @return
	 */	
	@RequestMapping(value=RestUriConstant.WEB_PAGE_QUERYLIST, method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>(2); 
    	if(getSessionCode() == null){
    		return result;
    	}
    	try{
        	setCode(getSessionCode());
    		List<UserMessage> list = findByPage(dgm.getPage()-1, dgm.getRows(), Constants.MSG_STATUS_NORMAL);
    		
    		result.put("total", totalCount(Constants.MSG_STATUS_NORMAL));
    		result.put("rows", list);
        	omtlogger.trace("Result of queryList size:"+list.size());
    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
	    return result;
	}

	/**
     * @Real
     * This method will delete an usermsg by it's msgid value.
     */
    @RequestMapping(value=RestUriConstant.WEB_PAGE_ITEMDETAILS, method=RequestMethod.GET)
	public ModelAndView findDetails(@RequestParam("msgid") String msgid, HttpServletRequest request) {
    	omtlogger.info("Start to finddetails...with code:"+getSessionCode()+" and msgid:"+msgid);
    	UserMessage uvo = new UserMessage();
    	ModelAndView model = new ModelAndView();
    	if(getSessionCode() == null){
    		return model;
    	}
    	try{
        	setCode(getSessionCode());
    		model.setViewName("details");
    		uvo = findOne(msgid);
    		model.addObject("usermessage", uvo);
    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		omtlogger.fatal(ex.getMessage());
    	}
    	return model;
	}
    
	/**
     * @Real
     * This method will delete an usermsg by it's msgid value.
     */
    @RequestMapping(value=RestUriConstant.WEB_PAGE_DELETEMSG, method=RequestMethod.POST)
    @ResponseBody
	public Map<String, String> deleteusermsg(@RequestParam("msgid") String msgid) {
    	omtlogger.info("Start to delete usermsg...with id: "+msgid);
    	Map<String, String> map = new HashMap<String, String>(); 
    	if(msgid == null || msgid.length() == 0) {
    		map.put("result", "Failed with wrong parameter.");
    		return map;
    	}
    	try{
        	setCode(getSessionCode());
        	List<String> myList = new ArrayList<String>(Arrays.asList(msgid.split(",")));
    		for(String id: myList){
    			UserMessage current = findOne(id);
    			// Only normal or approved messages can be deleted
    			if(current.getStatus() == Constants.MSG_STATUS_NORMAL || current.getStatus() == Constants.MSG_STATUS_DIRE_APPROVED){
    				//updateFirstWithDBs(id, current.getStatus() + Constants.MSG_STATUS_APPROVED);
    				updateFirstWithCollections(id, current.getStatus() + Constants.MSG_STATUS_DELETED);
    			}
    		}
    		map.put("result", "Successfully deleted the messages.");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		omtlogger.fatal(ex.getMessage());
    		map.put("result", "Failed to delete the messages.");
    	}
    	return map;
	}
	//----------------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------------
    

}
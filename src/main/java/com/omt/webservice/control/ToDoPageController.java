package com.omt.webservice.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.omt.webservice.Constants;
import com.omt.webservice.RestUriConstant;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.entity.DataGridModel;
import com.omt.webservice.entity.ToDoList;

/**
 * WebpageController
 * WebpageController using for web page to query and operate ToDoList messages
 * @author tonyliu 
 */
@Controller
@RequestMapping(value=RestUriConstant.WEB_TODO_PAGE)
public class ToDoPageController
{
	private Logger omtlogger = Logger.getLogger(ToDoPageController.class);

	
	/**
	 * Use this to:
	 * webpage query all the message list and return to view
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_INDEX , method = RequestMethod.GET)
	public String getToDoListweb(HttpServletRequest request) {
    	return "todolist";
	}
	
	/**
     * @Real
	 * Use this to:
	 * webpage query all the message list and return to view
	 * @return
	 */	
	@RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_LIST, method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>(2); 
		List<ToDoList> list = FindByPage(dgm.getPage()-1, dgm.getRows(), Constants.MSG_STATUS_NORMAL);
		
		result.put("total", TotalCount(Constants.MSG_STATUS_NORMAL));
		result.put("rows", list);
    	omtlogger.trace("Result of queryList size:"+list.size());
	    return result;
	}
	
	@RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_SUBGRID, method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryListFalse(DataGridModel dgm) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>(2); 
		List<ToDoList> list = FindByPageFalse(dgm.getPage()-1, dgm.getRows(), Constants.MSG_STATUS_NORMAL);
		
		result.put("total", TotalCountFalse(Constants.MSG_STATUS_NORMAL));
		result.put("rows", list);
    	omtlogger.trace("Result of queryList size:"+list.size());
	    return result;
	}	
	private int TotalCountFalse(int status){
		int count = 0;
		Query query = new Query();
		query.addCriteria(Criteria.where("content").ne(""));
		query.addCriteria(Criteria.where("status").is(false));
		count = (int) StaticMongoTemplate.getStaticMongoTemplate().count(query, ToDoList.class);
		return count;
	}
	
	private List<ToDoList> FindByPageFalse(int pageNumber, int pageSize, int status) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.addCriteria(Criteria.where("content").ne(""));
		 query.addCriteria(Criteria.where("status").is(false));
		 query.with(new Sort(Sort.Direction.DESC, "timestamp"));
		 query.skip(pageSize * pageNumber);
		 query.limit(pageSize);
		 
		return StaticMongoTemplate.getStaticMongoTemplate().find(query, ToDoList.class);		
	}	
	private int TotalCount(int status){
		int count = 0;
		Query query = new Query();
		query.addCriteria(Criteria.where("content").ne(""));
		count = (int) StaticMongoTemplate.getStaticMongoTemplate().count(query, ToDoList.class);
		return count;
	}
	
	private List<ToDoList> FindByPage(int pageNumber, int pageSize, int status) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.addCriteria(Criteria.where("content").ne(""));
		 query.with(new Sort(Sort.Direction.DESC, "timestamp"));
		 query.skip(pageSize * pageNumber);
		 query.limit(pageSize);
		 
		return StaticMongoTemplate.getStaticMongoTemplate().find(query, ToDoList.class);		
	}
    
    @RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_NEW, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, String> createToDoList(ToDoList toDoList) {
    	omtlogger.info("Start to createToDoList...with: "+toDoList);
    	Map<String, String> map = new HashMap<String, String>(); 
    	if(toDoList == null || toDoList.getContent() == null || toDoList.getContent().length() == 0) {
    		map.put("result", "Failed with wrong parameter.");
    		return map;
    	}
    	
    	try{
    		Query query = new Query();
    		query.addCriteria(Criteria.where("content").is(toDoList.getContent()));

    		ToDoList uvo = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, ToDoList.class);
    		if(uvo != null){
        		map.put("result", "Failed create the ToDoList: Already exist.");
    		}else{
    			toDoList.setStatus(Constants.TODO_LIST_TODO);
    			toDoList.setTimestamp(UtilLibs.GetLocalDateFmt(null));
    			
        		StaticMongoTemplate.getStaticMongoTemplate().insert(toDoList);
        		map.put("result", "Successfully create the ToDoList.");
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    		map.put("result", "Failed to create the ToDoList.");
    	}
    	return map;
	}
    @RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_EDIT, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, String> editToDoList(ToDoList toDoList) {
    	omtlogger.info("Start to editToDoList...with id: "+toDoList.getId());
    	Map<String, String> map = new HashMap<String, String>(); 
    	if(toDoList == null || toDoList.getId() == null) {
    		map.put("result", "Failed with wrong parameter.");
    		return map;
    	}
    	
    	try{
    		Query query = new Query();
    		query.addCriteria(Criteria.where("id").is(toDoList.getId()));
    		ToDoList uvo = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, ToDoList.class);
    		if(uvo != null){
        		Update update = new Update();
        		update.set("status", toDoList.isStatus());
        		StaticMongoTemplate.getStaticMongoTemplate().updateFirst(query, update, ToDoList.class);
        		map.put("result", "Successfully edit the ToDoList.");
    		}else{
    			map.put("result", "Failed as cannot find the ToDoList.");
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    		map.put("result", "Failed to edit the ToDoList.");
    	}
    	return map;
	}

    @RequestMapping(value=RestUriConstant.WEB_TODO_EDIT_ALL, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, String> editToDoListAll(ToDoList toDoList) {
    	omtlogger.info("Start to editToDoList...with id: "+toDoList.getId());
    	Map<String, String> map = new HashMap<String, String>(); 
    	if(toDoList == null || toDoList.getId() == null) {
    		map.put("result", "Failed with wrong parameter.");
    		return map;
    	}
    	
    	try{
    		List<String> myList = new ArrayList<String>(Arrays.asList(toDoList.getId().split(",")));
    		Query query = new Query();
    		query.addCriteria(Criteria.where("id").in(myList));
    		
    		Update update = new Update();
    		update.set("status", toDoList.isStatus());
    		StaticMongoTemplate.getStaticMongoTemplate().updateMulti(query, update, ToDoList.class);
    		map.put("result", "Successfully edit the ToDoList.");
    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		map.put("result", "Failed to edit the ToDoList.");
    	}
    	return map;
	}
}
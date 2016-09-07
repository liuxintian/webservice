package com.omt.webservice.meetings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.omt.exchanger.entity.CommonUVO;
import com.omt.webservice.RestUriConstant;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.entity.DataGridModel;
import com.omt.webservice.meetings.entity.Company;
import com.omt.webservice.meetings.entity.CompanyMin;
import com.omt.webservice.meetings.entity.Meetings;
import com.omt.webservice.morningstar.entity.Instrument;

/**
 * WEB page controller for meeting
 * @author tonyliu
 *
 */

@Controller
@RequestMapping(value=RestUriConstant.WSS_WEB_COMPANY)
public class CompanyWebController {
	private Logger omtlogger = Logger.getLogger(CompanyWebController.class);
	
	/**
	 * Get code list to be selected
	 * @param request
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WSS_WEB_COMPANY_CODELIST , method = RequestMethod.GET)
	@ResponseBody
	public List<CompanyMin> codelistForMobile(HttpServletRequest request) {
		List<CompanyMin> companies = new ArrayList<CompanyMin>();
		
		List <Company> instlist = StaticMongoTemplate.getStaticMongoTemplate().findAll(Company.class);
		if(instlist != null && instlist.size() > 0){
			CompanyMin uvo = null;
			for(Company inst: instlist){
				uvo = new CompanyMin();
				uvo.setCompanyID(inst.getCompanyID());
				uvo.setTickerCode(inst.getTickerCode());
				uvo.setCompanyName(inst.getCompanyName());
				companies.add(uvo);
			}
		}
    	return companies;
	}
	
	/**
	 * Get code list to be selected
	 * @param request
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WSS_WEB_COMPANY_FINDMEETING , method = RequestMethod.GET)
	@ResponseBody
	public List<Meetings> findmeeting(@RequestParam("companyID") String companyID, HttpServletRequest request) {
		Query query = new Query();
		query.addCriteria(Criteria.where("companyID").is(companyID));
		
		List<Meetings> meetings = StaticMongoTemplate.getStaticMongoTemplate().find(query, Meetings.class);
    	return meetings;
	}

	/**
	 * Get code list to be selected
	 * @param request
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_CODELIST , method = RequestMethod.GET)
	@ResponseBody
	public List<CommonUVO> codelist(HttpServletRequest request) {
		List<CommonUVO> companies = new ArrayList<CommonUVO>();
		
		Query query = new Query();
		query.addCriteria(Criteria.where("sectype").is("1"));
		List <Instrument> instlist = StaticMongoTemplate.getStaticMongoTemplate().find(query, Instrument.class);
		if(instlist != null && instlist.size() > 0){
			CommonUVO uvo = null;
			for(Instrument inst: instlist){
				uvo = new CommonUVO();
				uvo.setText(inst.getSymbolname());
				uvo.setValue(inst.getSymbol());
				companies.add(uvo);
			}
		}
    	return companies;
	}

	/**
	 * Enter company page management
	 * @param request
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_INDEX , method = RequestMethod.GET)
	public String company(HttpServletRequest request) {
    	omtlogger.info("Enter into news list...");
    	
    	return "company";
	}
	
    @RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_SUBGRID, method=RequestMethod.GET)
	public ModelAndView findDetails(HttpServletRequest request) {
    	ModelAndView model = new ModelAndView();
    	try{
    		model.setViewName("subgrid");
    		model.addObject("company", MeetingRestController.getCompany(null).get(0));
    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		omtlogger.fatal(ex.getMessage());
    	}
    	return model;
	}

	/**
	 * Display company list on web page
	 * @param dgm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_LIST, method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryNewsList(DataGridModel dgm) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>(2); 
		List<Company> list = findByPage(dgm.getPage()-1, dgm.getRows());
		
		if(list != null && list.size() > 0){
			result.put("total", totalCount());
			result.put("rows", list);
		}
	    return result;
	}
	public static List<Company> findByPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.skip(pageSize * pageNumber);
		 query.limit(pageSize);
		 
		return StaticMongoTemplate.getStaticMongoTemplate().find(query, Company.class);		
	}
	public static int totalCount(){
		int count = 0;
		count = (int) StaticMongoTemplate.getStaticMongoTemplate().count(new Query(), Company.class);
		return count;
	}	
	/**
	 * Create a company into database
	 * @param company
	 * @return
	 */
    @RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_NEW, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, String> createCompany(Company company) {
    	omtlogger.info("Start to createCompany...with: "+company);
    	Map<String, String> map = new HashMap<String, String>(); 
    	if(company == null || company.getCompanyName().length() == 0) {
    		map.put("result", "Failed with wrong parameter.");
    		return map;
    	}
    	
    	try{
    		Query query = new Query();
    		query.addCriteria(Criteria.where("companyName").is(company.getCompanyName()));
    		query.addCriteria(Criteria.where("tickerCode").is(company.getTickerCode()));

    		Company uvo = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, Company.class);
    		if(uvo != null){
        		map.put("result", "Failed create the Company: Already exist.");
    		}else{
    			company.setCompanyID(UtilLibs.GetSysRondomString());
        		StaticMongoTemplate.getStaticMongoTemplate().insert(company);
        		map.put("result", "Successfully create the user.");
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    		map.put("result", "Failed to create the user.");
    	}
    	return map;
	}

    /**
     * Edit a company into database
     * @param company
     * @return
     */
    @RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_EDIT, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, String> editCompany(Company company) {
    	omtlogger.info("Start to editCompany...with: "+company);
    	Map<String, String> map = new HashMap<String, String>(); 
    	if(company == null || company.getCompanyID() == null) {
    		map.put("result", "Failed with wrong parameter.");
    		return map;
    	}
    	
    	try{
    		Query query = new Query();
    		query.addCriteria(Criteria.where("companyID").ne(company.getCompanyID()));
    		query.addCriteria(Criteria.where("tickerCode").is(company.getTickerCode()));

    		Company uvo = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, Company.class);
    		if(uvo != null){
        		map.put("result", "Failed edit the Company: Already exist.");
    		}else{
        		Query queryedit = new Query();
        		queryedit.addCriteria(Criteria.where("companyID").is(company.getCompanyID()));
    			
        		Update update = new Update();
        		update.set("companyName", company.getCompanyName());
        		update.set("tickerCode", company.getTickerCode());
        		update.set("companyLogo", company.getCompanyLogo());
        		
        		StaticMongoTemplate.getStaticMongoTemplate().updateFirst(queryedit, update, Company.class);
        		map.put("result", "Successfully edit the Company.");
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    		map.put("result", "Failed to edit the Company.");
    	}
    	return map;
	}

	/**
     * @Real
     * This method will delete one record.
     * The contact can only be deleted when no meeting use it.
     */
    @RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_DELETE, method=RequestMethod.POST)
    @ResponseBody
	public Map<String, String> delete(@RequestParam("id") String id) {
    	omtlogger.info("Start to delete record...with id: "+id);
    	Map<String, String> map = new HashMap<String, String>(); 
    	if(id == null || id.length() == 0) {
    		map.put("result", "Failed with wrong parameter.");
    		return map;
    	}
    	
    	try{
    		Query queryrelation = new Query();
    		queryrelation.addCriteria(Criteria.where("companyID").is(id));
    		Meetings meeting = StaticMongoTemplate.getStaticMongoTemplate().findOne(queryrelation, Meetings.class);
    		if(meeting != null){
    			map.put("result", "This record can not be deleted as it is in using with Meeting:"+meeting.getMeetingID());
    		}else{
    			
    			Query query = new Query();
    			query.addCriteria(Criteria.where("companyID").is(id));
    			Company uvo = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, Company.class);
        			
    			// Only normal or approved messages can be deleted
    			if(uvo == null){
    				map.put("result", "Failed to delete the record. Can't find this record.");
    			}else{
    				StaticMongoTemplate.getStaticMongoTemplate().remove(query, Company.class);
    				map.put("result", "Successfully deleted the record.");
    			}
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    		map.put("result", "Failed to delete the record.");
    	}
    	return map;
	}
 
    
}

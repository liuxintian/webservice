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

import com.omt.webservice.RestUriConstant;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.entity.DataGridModel;
import com.omt.webservice.meetings.entity.DocumentAttributes;
import com.omt.webservice.meetings.entity.DocumentsWeb;
import com.omt.webservice.meetings.entity.Meetings;
import com.omt.webservice.meetings.entity.Documents;

/**
 * Meeting Documents Management 
 * @author tonyliu
 *
 */
@Controller
@RequestMapping(value=RestUriConstant.WSS_WEB_DOCUMENT)
public class DocumentWebController {
	private Logger omtlogger = Logger.getLogger(DocumentWebController.class);
	
	/**
	 * Enter into the document management page
	 * @param request
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_INDEX , method = RequestMethod.GET)
	public String company(HttpServletRequest request) {
    	omtlogger.info("Enter into news list...");
    	
    	return "document";
	}
	
	/**
	 * Get all the document list which can be used on meeting page
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_SELECT , method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Documents> list() {
    	List<Documents> list = StaticMongoTemplate.getStaticMongoTemplate().findAll(Documents.class);
		return list;
	}
	
	/**
	 * Display list for document web page
	 * @param dgm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_LIST, method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryNewsList(DataGridModel dgm) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>(2); 
		List<Documents> list = findByPage(dgm.getPage()-1, dgm.getRows());
		List<DocumentsWeb> listtmp = new ArrayList<DocumentsWeb>();
		
		if(list != null && list.size() > 0){
			
			for(Documents document : list){
				DocumentsWeb docweb = new DocumentsWeb();
				docweb.setDocumentID(document.getDocumentID());
				docweb.setDocumentLink(document.getDocumentLink());
				docweb.setDocumentName(document.getDocumentName());
				docweb.setDocumentType(document.getDocumentType());
				
				List<DocumentAttributes> documentAttributes = document.getDocumentAttributes();
				if(documentAttributes != null){
					for(DocumentAttributes attr: documentAttributes){
						switch(attr.getAttributeName()){
						case "length":
							docweb.setDocumentLength(attr.getAttributeValue());
							break;
						case "size":
							docweb.setDocumentSize(attr.getAttributeValue());
							break;
						default:
							break;
						}
					}
				}
				listtmp.add(docweb);
			}
			
			result.put("total", totalCount());
			result.put("rows", listtmp);
		}
	    return result;
	}
	public static List<Documents> findByPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.skip(pageSize * pageNumber);
		 query.limit(pageSize);
		 
		return StaticMongoTemplate.getStaticMongoTemplate().find(query, Documents.class);		
	}
	public static int totalCount(){
		int count = 0;
		count = (int) StaticMongoTemplate.getStaticMongoTemplate().count(new Query(), Documents.class);
		return count;
	}	
	/**
	 * Create a record into documents
	 * @param indocument
	 * @return
	 */
    @RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_NEW, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, String> createDocuments(DocumentsWeb indocument) {
    	omtlogger.info("Start to createDocuments...with: "+indocument);
    	Map<String, String> map = new HashMap<String, String>(); 
    	if(indocument == null || indocument.getDocumentName().length() == 0) {
    		map.put("result", "Failed with wrong parameter.");
    		return map;
    	}
    	
    	try{
    		Query query = new Query();
    		query.addCriteria(Criteria.where("documentName").is(indocument.getDocumentName()));
    		query.addCriteria(Criteria.where("documentLink").is(indocument.getDocumentLink()));

    		Documents uvo = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, Documents.class);
    		if(uvo != null){
        		map.put("result", "Failed create the Documents: Already exist.");
    		}else{
    			Documents document = new Documents();
    			document.setDocumentLink(indocument.getDocumentLink());
    			document.setDocumentName(indocument.getDocumentName());
    			document.setDocumentType(indocument.getDocumentType());
    			
    			List<DocumentAttributes> documentAttributes = new ArrayList<DocumentAttributes>();
    			
    			DocumentAttributes attr = new DocumentAttributes();
    			attr.setAttributeName("length");
    			attr.setAttributeValue(indocument.getDocumentLength());
    			documentAttributes.add(attr);

    			attr = new DocumentAttributes();
    			attr.setAttributeName("size");
    			attr.setAttributeValue(indocument.getDocumentSize());
    			documentAttributes.add(attr);
   			
    			document.setDocumentID(UtilLibs.GetSysRondomString());
    			document.setDocumentAttributes(documentAttributes);
    			
    			StaticMongoTemplate.getStaticMongoTemplate().insert(document);
        		map.put("result", "Successfully create the document.");
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    		map.put("result", "Failed to create the document.");
    	}
    	return map;
	}
	
    /**
     * Edit a document record.
     * @param indocument
     * @return
     */
    @RequestMapping(value=RestUriConstant.WSS_WEB_COMMON_EDIT, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, String> editDocuments(DocumentsWeb indocument) {
    	omtlogger.info("Start to editDocuments...with: "+indocument);
    	Map<String, String> map = new HashMap<String, String>(); 
    	if(indocument == null || indocument.getDocumentName().length() == 0) {
    		map.put("result", "Failed with wrong parameter.");
    		return map;
    	}
    	
    	try{
    		Query query = new Query();
    		query.addCriteria(Criteria.where("documentID").ne(indocument.getDocumentID()));
    		query.addCriteria(Criteria.where("documentLink").is(indocument.getDocumentLink()));

    		Documents uvo = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, Documents.class);
    		if(uvo != null){
        		map.put("result", "Failed edit the Documents: Already exist.");
    		}else{
        		Query queryedit = new Query();
        		queryedit.addCriteria(Criteria.where("documentID").is(indocument.getDocumentID()));
        		
        		Update update = new Update();
        		update.set("documentLink", indocument.getDocumentLink());
        		update.set("documentName", indocument.getDocumentName());
        		update.set("documentType", indocument.getDocumentType());

    			List<DocumentAttributes> documentAttributes = new ArrayList<DocumentAttributes>();
    			
    			DocumentAttributes attr = new DocumentAttributes();
    			attr.setAttributeName("length");
    			attr.setAttributeValue(indocument.getDocumentLength());
    			documentAttributes.add(attr);

    			attr = new DocumentAttributes();
    			attr.setAttributeName("size");
    			attr.setAttributeValue(indocument.getDocumentSize());
    			documentAttributes.add(attr);

    			update.set("documentAttributes", documentAttributes);

    			StaticMongoTemplate.getStaticMongoTemplate().updateFirst(queryedit, update, Documents.class);
        		map.put("result", "Successfully edit the document.");
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    		map.put("result", "Failed to edit the document.");
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
    		queryrelation.addCriteria(Criteria.where("meetingDetails").in(id));
    		Meetings meeting = StaticMongoTemplate.getStaticMongoTemplate().findOne(queryrelation, Meetings.class);
    		if(meeting != null){
    			map.put("result", "This record can not be deleted as it is in using with Meeting:"+meeting.getMeetingID());
    		}else{

    			Query query = new Query();
    			query.addCriteria(Criteria.where("documentID").is(id));
    			Documents uvo = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, Documents.class);
        			
    			// Only normal or approved messages can be deleted
    			if(uvo == null){
    				map.put("result", "Failed to delete the record. Can't find this record.");
    			}else{
    				StaticMongoTemplate.getStaticMongoTemplate().remove(query, Documents.class);
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

package com.omt.asxdata.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableBody;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.omt.asxdata.entity.AsxDataVO;
import com.omt.config.StaticConfig;
import com.omt.webservice.StaticMongoTemplate;

public class AsxWebClient {

	public static final int GAINS = 0;
	public static final int DECLINES = 1;
	private static List<AsxDataVO> list;
	private static boolean addable = true;
	private static Logger omtlogger = Logger.getLogger(AsxWebClient.class);
	
	public static void webclientPage(){
		
		try {
			
			// 2. Gains data
			if(StaticConfig.asxGainsUrl != null && StaticConfig.asxGainsUrl.length() > 0){
				addable = true;
				list = new ArrayList<AsxDataVO>();

				GetTypedTop(StaticConfig.asxGainsUrl, GAINS);
				
				if(addable && (list != null) && (list.size() > 0)){
					omtlogger.info("webclientPage get GAINS count:" + list.size());
					clear(GAINS);
					for(AsxDataVO uvo: list){
						insert(uvo);
					}
				}
			}
			
			// 3. Declines data
			if(StaticConfig.asxDeclinesUrl != null && StaticConfig.asxDeclinesUrl.length() > 0){
				addable = true;
				list = new ArrayList<AsxDataVO>();

				GetTypedTop(StaticConfig.asxDeclinesUrl, DECLINES);
				
				if(addable && (list != null) && (list.size() > 0)){
					omtlogger.info("webclientPage get DECLINES count:" + list.size());
					clear(DECLINES);
					for(AsxDataVO uvo: list){
						insert(uvo);
					}
				}
			}
			
			// if no 0.00 and list size is correct

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void GetTypedTop(String url, int type){

		final WebClient client = new WebClient();
		try {
	
			final HtmlPage page = client.getPage(url);
			final HtmlTable table = page.getHtmlElementById(StaticConfig.asxPageContainer);
			
			for (final HtmlTableBody body : table.getBodies()) {
			    final List<HtmlTableRow> rows = body.getRows();
				for (final HtmlTableRow row : rows) {
					AsxDataVO uvo = new AsxDataVO();
					uvo.setType(type);
					int index = 0;
				    for (final HtmlTableCell cell : row.getCells()) {
				    	switch(index){
				    	case 0:
				    		String asxcode = cell.asText();
				    		if(asxcode != null && asxcode.trim().length() > 0){
					    		uvo.setAsxcode(asxcode);
				    		}else{
					    		uvo.setAsxcode(asxcode);
				    			addable = false;
				    		}
				    		break;
				    	case 1:
				    		String name = cell.asText();
				    		if(name != null && name.trim().length() > 0){
					    		uvo.setName(name);
				    		}else{
				    			uvo.setName(name);
				    			addable = false;
				    		}
				    		break;
				    	case 2:
				    		String lastprice = cell.asText();
				    		if(lastprice != null && lastprice.trim().length() > 0){
					    		if(lastprice.equals("$0.00")){
					    			continue;
					    		}
					    		uvo.setLastprice(lastprice);
				    		}else{
					    		uvo.setLastprice("$0.00");
					    		addable = false;
				    		}
				    		break;
				    	case 3:
				    		break;
				    	case 4:
				    		String changepercent = cell.asText();
				    		if(changepercent != null && changepercent.trim().length() > 0){
					    		uvo.setChangepercent(changepercent);
					    		if(changepercent.equals("0.00%")){
					    			addable = false;
					    		}
				    		}else{
					    		uvo.setChangepercent("0.00%");
					    		addable = false;
				    		}
				    		break;
				    	case 5:
				    		String changevalue = cell.asText();
				    		if(changevalue != null && changevalue.trim().length() > 0){
					    		uvo.setChangevalues(changevalue);
					    		if(changevalue.equals("$0.00")){
					    			addable = false;
					    		}
				    		}else{
					    		uvo.setChangevalues("$0.00");
					    		addable = false;
				    		}
				    		break;
				    	default:
				    		break;
				    	}
				    	index ++;
				    }
				    list.add(uvo);
				}
			}
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
//			try{
//				client.close();
//			}catch(Exception ex){
//				ex.printStackTrace();
//			}
		}
	}
		
	private static void insert(AsxDataVO entity) {
		// TODO Auto-generated method stub
		StaticMongoTemplate.getStaticMongoTemplate().insert(entity);   
	}	
	private static void clear(int type){
		Query query = new Query();
		query.addCriteria(Criteria.where("type").is(type));
		StaticMongoTemplate.getStaticMongoTemplate().remove(query, AsxDataVO.class);
	}
	
	public static void main(String args[]){
		webclientPage();
	}
}

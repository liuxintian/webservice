package com.omt.asxdata.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableBody;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.omt.asxdata.entity.WestNews;
import com.omt.config.StaticConfig;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;

/**
 * Get wbc announcements from the web site and insert into mongodb
 * @author tonyliu
 *
 */
public class WestWebClient {

	public static void webPageLoad(){
		
		try {
			
			GetAndInsert(StaticConfig.westpacNewsUrl);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void GetAndInsert(String url){
		final WebClient client = new WebClient();

		try {
	
			final HtmlPage page = client.getPage(url);
			final DomNodeList<HtmlElement> tablelist = page.getElementsByTagName(StaticConfig.westpacNewsContainer);
			final HtmlTable table = (HtmlTable) tablelist.get(StaticConfig.westpacNewsContainerIndex);
			
			for (final HtmlTableBody body : table.getBodies()) {
			    final List<HtmlTableRow> rows = body.getRows();
			    
			    clear();
			    
				for (final HtmlTableRow row : rows) {
					StringBuffer st = new StringBuffer();
					WestNews uvo = new WestNews();
					int index = 0;
				    for (final HtmlTableCell cell : row.getCells()) {
				    	switch(index){
				    	case 0:
				    		uvo.setDate(cell.asText());
				    		st.append(cell.asText()).append(",");
				    		break;
				    	case 1:
				    		if(cell.hasChildNodes()){
				    			uvo.setTitle(cell.getFirstChild().asText());
				    			if(cell.getFirstChild().hasAttributes()){
					    			uvo.setUrl(cell.getFirstChild().getAttributes().getNamedItem("href").getNodeValue());
				    			}else{
						    		uvo.setUrl("_blank");
				    			}
				    		}else{
					    		uvo.setTitle(cell.asText());
					    		uvo.setUrl("_blank");
				    		}
				    		break;
				    	default:
				    		break;
				    	}
				    	index ++;
				    }
				    insert(uvo);
				}
			}
			// Generate a csv file if neccessary
			UtilLibs.generateCsvFile(csvFileName(), csvHeader(), csvLines());
			
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
	
	private static String csvFileName(){
		StringBuffer st = new StringBuffer();
		st.append(StaticConfig.filesBasePath+"/"+StaticConfig.newslistfile);
		return st.toString();
	}
	private static String csvHeader(){
		StringBuffer st = new StringBuffer();
		st.append("Date").append(",").append("Title").append(",").append("Link");
		return st.toString();
	}
	private static String[] csvLines(){
		List<WestNews> news = findAll();
		String[] ret = {};
		if(news != null && news.size() > 0){
			ret = new String[news.size()];
			StringBuffer line = new StringBuffer();
			for(int i=0;i<news.size();i++){
				line = new StringBuffer();
				WestNews uvo = news.get(i);
				line.append(uvo.getDate()).append(",").append(uvo.getTitle()).append(",").append(uvo.getUrl());
				ret[i] = line.toString();
			}
		}
		return ret;
	}
	
	private static void insert(WestNews entity) {
		// TODO Auto-generated method stub
		StaticMongoTemplate.getStaticMongoTemplate().insert(entity);   
	}	
	private static void clear(){
		StaticMongoTemplate.getStaticMongoTemplate().dropCollection(WestNews.class);
	}	

	private static List<WestNews> findAll() {
		// TODO Auto-generated method stub
		return StaticMongoTemplate.getStaticMongoTemplate().find(new Query(), WestNews.class);
	}
}

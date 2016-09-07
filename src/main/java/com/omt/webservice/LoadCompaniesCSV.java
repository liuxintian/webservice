package com.omt.webservice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.omt.webservice.morningstar.MsUtility;
import com.omt.webservice.morningstar.entity.Instrument;
import com.omt.websocket.WebSocketClient;
import com.omt.websocket.entity.CompanyList;

/**
 * Load company list formated file
 * Used when the first time establish OMT WEB Service
 * @author tonyliu
 *
 */
public class LoadCompaniesCSV {

	private static Logger omtlogger = Logger.getLogger(LoadCompaniesCSV.class);
	public static void loadCSVCompanies(String fullpath){
		
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			Map<String, String> maps = new HashMap<String, String>();
			
			br = new BufferedReader(new FileReader(fullpath));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] company = line.split(cvsSplitBy);
				// for removing repeat one
				if(company[1] == null || company[1].trim().equals("")) continue;
				maps.put(company[1].trim().toUpperCase(), WebSocketClient.DEFAULT_MARKT);
			}

			// Add other companies for Project using
			for(final String company: WebSocketClient.COMPANIES){
				JSONObject inobj = new JSONObject(company);
	   			String code = inobj.getJSONObject("Data").getString("Code");
	   			String market = inobj.getJSONObject("Data").getString("Market");
	   			maps.put(code, market);
			}
			
			String[] newcompanies = new String[maps.size()];
			int index = 0;
			for (Map.Entry<String, String> entry : maps.entrySet()) {
				//System.out.println("company [code= " + entry.getKey() + " , name="+ entry.getValue() + "]");
				newcompanies[index] = WebSocketClient.onPriceSubscribe(entry.getKey().toUpperCase().trim(), entry.getValue().toUpperCase().trim());
				index ++;
			}
			WebSocketClient.COMPANIES = newcompanies;
			omtlogger.info("----Load companies from file, WebSocketClient.COMPANIES.length:"+WebSocketClient.COMPANIES.length);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void loadCSVCompaniesTmp(String fullpath){
		if(StaticMongoTemplate.getStaticMongoTemplate().count(new Query(), CompanyList.class) > 0){
			return;
		};
		BufferedReader br = null;
		String line = "";

		try {
			
			br = new BufferedReader(new FileReader(fullpath));
			CompanyList company = null;
			while ((line = br.readLine()) != null) {
				if(line != null && line.trim().length() > 0){
					company =  new CompanyList();
					company.setCode(line.trim().toUpperCase());
					company.setMarket(WebSocketClient.DEFAULT_MARKT);
					StaticMongoTemplate.getStaticMongoTemplate().insert(company);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Load Morningstar instruments into hashtable
	 * Exchange|Security Type|Symbol|Root symbol
	 * Start,
	 * basic introduce, the starting conditions
	 * primarily, regarding the plan, time, blocks, lessons, framework for most
	 * moreover, based on your practice situation, can be dynamically regulated
	 * in addition, you can have your own requirement like, practice roundabout, reasonably add to the plan,
	 * Looking forward your feedback, we send you on board ASAP
	 * End
	 * 146|1|TL8||AUD|TLS Toress Basket [TL8]|||||AU||AVI|146||XASX|ax|||||0||
	 * @param fullpath
	 */
	public static void loadInstrumentForMS(String fullpath){
		if(!needInitialize()) return;
		
		BufferedReader br = null;
		String line = "";
		String splitBy = "[|]";

		try {
			br = new BufferedReader(new FileReader(fullpath));
			Instrument instrument;
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] ary = line.split(splitBy);
				if(ary != null && ary.length > 2){
					if((ary[2] != null && ary[2].trim().length() > 0) &&
					   (ary[1] != null && ary[1].trim().length() > 0) &&
					   (ary[0] != null && ary[0].trim().length() > 0) ){
						instrument = new Instrument();
						instrument.setMarket(WebSocketClient.DEFAULT_MARKT);
						instrument.setExchange(ary[0]);
						instrument.setSectype(ary[1]);
						instrument.setSymbol(ary[2]);
						if(ary.length > 4){
							instrument.setSymbolname(ary[5]);
						}else{
							instrument.setSymbolname(ary[2]);
						}
						
						StaticMongoTemplate.getStaticMongoTemplate().insert(instrument);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Check if it's need to read and insert 
	 * @return
	 */
	private static boolean needInitialize(){
		boolean ret = true;
		Query query = new Query();
		if(StaticMongoTemplate.getStaticMongoTemplate().count(query, Instrument.class) > 0){
			ret = false;
			try{
				DBCursor cursor = StaticMongoTemplate.getStaticMongoTemplate().getCollection("instrument").find(); 
				if(cursor != null && cursor.hasNext()){
					MsUtility.SYMBOL_INSTRUMENT_MAP.clear();
					Instrument instrument = new Instrument();
					while(cursor.hasNext()){
						DBObject obj = cursor.next();
						instrument = new Instrument();
						instrument.setExchange(obj.get("exchange").toString());
						instrument.setMarket(obj.get("market").toString());
						instrument.setSectype(obj.get("sectype").toString());
						instrument.setSymbol(obj.get("symbol").toString());
						MsUtility.SYMBOL_INSTRUMENT_MAP.put(instrument.getSymbol()+"|"+instrument.getMarket(), instrument);
					}
				} 
				cursor.close();
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return ret;
	}

}

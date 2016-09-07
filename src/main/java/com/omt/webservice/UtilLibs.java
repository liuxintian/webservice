package com.omt.webservice;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.codec.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.omt.config.StaticConfig;
import com.omt.exchanger.entity.HsArray;
import com.omt.exchanger.entity.HsOne;
import com.omt.exchanger.entity.ProgressVO;
import com.omt.webservice.entity.HashMessage;
import com.omt.webservice.entity.UserMessage;
import com.omt.webservice.morningstar.dao.MsDao;
import com.omt.webservice.morningstar.entity.ChartData;
import com.omt.webservice.morningstar.entity.Instrument;
import com.omt.webservice.morningstar.entity.MsSharePrice;
import com.omt.webservice.morningstar.entity.ShareDataM;
import com.omt.webservice.morningstar.entity.SmaResult;

/**
 * OMT WEB Service Utility Library
 * @author tonyliu
 *
 */
public class UtilLibs {

	/**
	 * Get the special date format string
	 * @return
	 */
	public static String GetLocalDateFmt(String fmt){
		DateFormat dateFormat = null;
		if(fmt != null && fmt.length() > 0){
			try{
				dateFormat = new SimpleDateFormat(fmt);
			}catch(Exception ex){
				dateFormat = new SimpleDateFormat(Constants.SYS_TM_FMT);
				ex.printStackTrace();
			}
		}else{
	    	 dateFormat = new SimpleDateFormat(Constants.SYS_TM_FMT);
		}
    	Calendar cal = Calendar.getInstance();
    	return dateFormat.format(cal.getTime());
    	
	}
	
	
	/**
	 * Get current time with format and timezone;
	 * @return
	 */
	public static String GetCurrentTimeWithTMZ(String fmt, String tmzID){
		if(fmt == null || fmt.length() == 0){
			fmt = Constants.SYS_TM_FMT;
		}
		if(tmzID == null || tmzID.length() == 0){
			tmzID = Constants.SYS_TZ_VIC;
		}
		DateFormat dateFormat = null;
		try{
			dateFormat = new SimpleDateFormat(fmt);
		}catch(Exception ex){
			dateFormat = new SimpleDateFormat(Constants.SYS_TM_FMT);
			ex.printStackTrace();
		}
		
		TimeZone tmz = null;
		try{
			tmz = TimeZone.getTimeZone(tmzID);
			dateFormat.setTimeZone(tmz);
		}catch(Exception ex){
			tmz = TimeZone.getTimeZone(Constants.SYS_TZ_VIC);
			dateFormat.setTimeZone(tmz);
			ex.printStackTrace();
		}
		
    	Calendar cal = Calendar.getInstance();
    	return dateFormat.format(cal.getTime());
	}
	
	/**
	 * Get everyday 1AM time
	 * @return
	 */
	public static Date GetNextDayTime(int day, int clock, int min, int second, String timeZone){
		String datestr = "";
		Date dt = null;
		try{
			DateFormat dateFormat = null;
			dateFormat = new SimpleDateFormat(Constants.SYS_TM_FMT);
	    	Calendar cal = Calendar.getInstance();
	    	cal.add(Calendar.DAY_OF_YEAR, day);
	    	cal.set(Calendar.HOUR_OF_DAY, clock); 
	    	cal.set(Calendar.MINUTE,min);
	    	cal.set(Calendar.SECOND,second);
	    	if(timeZone == null || timeZone.length() == 0){
		    	cal.setTimeZone(TimeZone.getDefault());
	    	}else{
	    		try{
			    	cal.setTimeZone(TimeZone.getTimeZone(timeZone));
	    		}catch(Exception ex){
			    	cal.setTimeZone(TimeZone.getDefault());
	    			ex.printStackTrace();
	    		}
	    	}
	    	
	    	datestr = dateFormat.format(cal.getTime());
	    	dt = dateFormat.parse(datestr);
		}catch(Exception ex){
			ex.printStackTrace();
		}
    	
    	return dt;
	}
	
	/**
	 * Get tomorrow xAM in Victoria time zone
	 * @return
	 */
	public static Date GetNextDay3Am(){
		return GetNextDayTime(1, 3, 0, 0, Constants.SYS_TZ_VIC);
	}
	public static Date GetNextDay4Am(){
		return GetNextDayTime(1, 3, 0, 0, Constants.SYS_TZ_VIC);
	}
	
	public static Date GetNextDay5Am(){
		return GetNextDayTime(1, 5, 0, 0, Constants.SYS_TZ_VIC);
	}
	
	public static Date GetNextDay6Am(){
		return GetNextDayTime(1, 6, 0, 0, Constants.SYS_TZ_VIC);
	}
	public static Date GetNextDay7Am(){
		return GetNextDayTime(1, 7, 0, 0, Constants.SYS_TZ_VIC);
	}
	public static Date GetNextDay8Am(){
		return GetNextDayTime(1, 8, 0, 0, Constants.SYS_TZ_VIC);
	}
	public static Date GetNextDay9Am(){
		return GetNextDayTime(1, 9, 0, 0, Constants.SYS_TZ_VIC);
	}
	public static Date GetNextDay10Am(){
		return GetNextDayTime(1, 10, 0, 0, Constants.SYS_TZ_VIC);
	}
	public static Date GetNextDay20PM(){
		return GetNextDayTime(1, 20, 0, 0, Constants.SYS_TZ_VIC);
	}
	
	/**
	 * Get preferred date string
	 * @return
	 */
	public static String GetPreferDay(int day, String timeFmt, String timeZone){
		String datestr = "";
		// Date dt = null;
		try{
	    	if(timeZone == null || timeZone.length() == 0){
	    		timeZone = Constants.SYS_DT_FMT;
	    	}
			DateFormat dateFormat;
			dateFormat = new SimpleDateFormat(timeFmt);
	    	Calendar cal = Calendar.getInstance();
	    	cal.add(Calendar.DAY_OF_YEAR, day);
	    	
	    	if(timeZone == null || timeZone.length() == 0){
		    	cal.setTimeZone(TimeZone.getDefault());
	    	}else{
		    	cal.setTimeZone(TimeZone.getTimeZone(timeZone));
	    	}
	    	
	    	datestr = dateFormat.format(cal.getTime());
	    	// dt = dateFormat.parse(datestr);
		}catch(Exception ex){
			ex.printStackTrace();
		}
    	
    	return datestr;
	}
	
	/**
	 * Get Australia holidays list by a year
	 * {2016-01-01, 2016-01-26}
	 * @param year
	 */
	public static Hashtable<String,String> GetAustraliaHolidays(String year){
		Hashtable<String,String> ht = new Hashtable<String,String>();
		
		if(year == null || year.trim().length() == 0){
			Calendar cal = Calendar.getInstance();
			year = String.valueOf(cal.get(Calendar.YEAR));
		}
		
		for(String dt: Constants.AUSTRALIA_HOLIDAY){
			ht.put(year+dt, year);
		}
		
		return ht;
	}
	
	/**
	 * Convert timestr from UTC to timzID
	 * @return
	 */
	public static String ConvertTimeToTMZ(String timestr, String fmt, String tmzID){
		if(timestr == null || timestr.length() < 19){
			return "";
		}
		if(fmt == null || fmt.length() == 0){
			fmt = Constants.SYS_TM_FMT;
		}
		if(tmzID == null || tmzID.length() == 0){
			tmzID = Constants.SYS_TZ_VIC;
		}
		
		DateFormat dateFormat = new SimpleDateFormat(fmt);
		dateFormat.setTimeZone(TimeZone.getTimeZone(Constants.SYS_TZ_UTC));
		Date date = null;
		try{
			date = dateFormat.parse(timestr);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		DateFormat dateFormatOut = new SimpleDateFormat(fmt);
		dateFormatOut.setTimeZone(TimeZone.getTimeZone(tmzID));
		
    	return dateFormatOut.format(date);
	}
	
	/**
	 * Return the json string for empty data from wss server;
	 * @param data
	 * @param realcount
	 * @param shouldcount
	 * @return
	 */
	public static String createAllItemsForEmpty(String data, int shouldcount, String code){
		if(data == null || data.length() == 0 || code == null || code.length() == 0){
			return data;
		}
		JSONArray retobj = new JSONArray();
		
		//String datastr = "[{\"Date\":\"2015-09-09Z\",\"Open\":99.0,\"High\":99.0,\"Low\":96.51,\"Close\":97.27,\"Volume\":33706},{\"Date\":\"2015-09-10Z\",\"Open\":97.44,\"High\":97.45,\"Low\":96.85,\"Close\":97.1,\"Volume\":27462}]";
		JSONArray objdata = new JSONArray(data);
		if(objdata.length() == 0) return data;
		
		JSONObject obj = objdata.getJSONObject(0);
		String datestr = obj.getString("Date").substring(0,10);
		int realcount = objdata.length();
		
		if(realcount < shouldcount){
			List<String> ret = getWorkingDaysList(datestr, shouldcount - realcount);
			if(ret != null && ret.size() > 0){
				for(String dtstr: ret){
					retobj.put(PreSetJSONObject(dtstr));
				}
				
				for(Object objal: objdata){
					retobj.put(objal);
				}
			}
		}else{
			int dif = realcount - shouldcount;
			for(int i=dif; i<realcount; i++){
				retobj.put(objdata.get(i));
			}
		}
		JSONObject objret = checkAddCurrentPrice(objdata,  code);
		if(objret != null){
			retobj.put(objret);
		}
			
		return retobj.toString();
	}
	
	public static JSONObject checkAddCurrentPrice(JSONArray input, String code){
		// invalid data
		if(input == null || code == null || code.length() == 0){
			return null;
		}
		// holidays
		if(UtilLibs.isWeekend(UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_DT_FMT, Constants.SYS_TZ_VIC)) ||
				   UtilLibs.isAuHoliday(UtilLibs.GetCurrentTimeWithTMZ(Constants.DATE_DT_FMT, Constants.SYS_TZ_VIC))){
			return null;
		}
		
		if(input != null){
			try{
				String today = GetCurrentTimeWithTMZ(Constants.DATE_DT_FMT, Constants.SYS_TZ_VIC)+"Z"; //Today
				JSONObject objal = input.getJSONObject(input.length()-1);
				if(objal != null){
					ObjectMapper mapper = new ObjectMapper();
					ChartData chartdata = mapper.readValue(objal.toString(), ChartData.class);
					if(chartdata.getDate().equals(today)){
						return null;
					}else{
						MsSharePrice message = MsDao.findOneSharePrice(code.toUpperCase());
						if(message != null){
							ShareDataM mmm = message.getData();
							if(mmm != null){
								chartdata.setClose(mmm.getLast());
								chartdata.setDate(today);
							}
							ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
							String json = ow.writeValueAsString(chartdata);
							return new JSONObject(json);
						}
					}
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Return a JSONString
	 * @param data
	 * @param type
	 * @return
	 */
	public static String createAllItemsForType(String data, String type, String code){
		if(data == null || data.length() == 0 || code == null || code.length() == 0){
			return data;
		}
		JSONArray retobj = new JSONArray();
		
		//String datastr = "[{\"Date\":\"2016-06-09Z\",\"Open\":99.0,\"High\":99.0,\"Low\":96.51,\"Close\":97.27,\"Volume\":33706},{\"Date\":\"2016-06-10Z\",\"Open\":97.44,\"High\":97.45,\"Low\":96.85,\"Close\":97.1,\"Volume\":27462}]";
		JSONArray objdata = new JSONArray(data);
		if(objdata.length() == 0) return data;
		
		List<String> ret = getAllDaysList(type);
		if(ret != null && ret.size() > 0){
			for(String retstr: ret){
				for(int i=0;i<objdata.length();i++){
					JSONObject objok = objdata.getJSONObject(i);
					if(objok.getString("Date").equals(retstr)){
						retobj.put(objok);
					}
				}
			}
		}
		JSONObject objret = checkAddCurrentPrice(objdata,  code);
		if(objret != null){
			retobj.put(objret);
		}
		return retobj.toString();
	}
	
	/**
	 * Return a JSONArray
	 * @param data
	 * @param type
	 * @return
	 */
	public static List<ChartData> createAllItemsForArray(String data, String type){
		List<ChartData> retlist = new ArrayList<ChartData>();
		if(data == null || data.length() == 0){
			return retlist;
		}
		try{
			JSONArray objdata = new JSONArray(data);
			if(objdata.length() == 0) return retlist;
			
			ObjectMapper mapper = new ObjectMapper();
			List<String> ret = getDaysListExceptHolsWeekend(type);
			ChartData chartdata = new ChartData();
			if(ret != null && ret.size() > 0){
				for(String retstr: ret){
					boolean have = false;
					for(int i=0;i<objdata.length();i++){
						JSONObject objok = objdata.getJSONObject(i);
						if(objok.getString("Date").equals(retstr)){
							chartdata = mapper.readValue(objok.toString(), ChartData.class);
							retlist.add(chartdata);
							have = true;
						}
					}
					if(!have){ 
						ChartData chartdataTmp = new ChartData();
						chartdataTmp.setDate(retstr);
						chartdataTmp.setClose(chartdata.getClose());
						chartdataTmp.setOpen(chartdata.getOpen());
						chartdataTmp.setHigh(chartdata.getHigh());
						chartdataTmp.setVolume(chartdata.getVolume());
						chartdataTmp.setLow(chartdata.getLow());

						retlist.add(chartdataTmp);
					}
				}
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return retlist;
	}

	public static JSONObject PreSetJSONObject(String datestr){
		String itemstr = "{\"Date\":\""+datestr+"\",\"Open\":0,\"High\":0,\"Low\":0,\"Close\":0,\"Volume\":0}";
		return new JSONObject(itemstr);
	}

	public static List<String> getDaysListExceptHolsWeekend(String type) {
		List<String> ret = new ArrayList<String>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setTimeZone(TimeZone.getTimeZone(Constants.SYS_TZ_VIC));
	    Calendar startCal = Calendar.getInstance(TimeZone.getTimeZone(Constants.SYS_TZ_VIC));
	    Calendar endCal = Calendar.getInstance(TimeZone.getTimeZone(Constants.SYS_TZ_VIC));
		try {
			Date startDate = new Date();
		    startCal.setTime(startDate);
		    endCal.setTime(startDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    switch(type){
	    case Constants.CHART_REQ_TYPE_1WEEK:
	        startCal.add(Calendar.DAY_OF_YEAR, -7);  
	    	break;
	    case Constants.CHART_REQ_TYPE_1MONTH:
	        startCal.add(Calendar.DAY_OF_YEAR, -30);  
	    	break;
	    case Constants.CHART_REQ_TYPE_3MONTH:
	        startCal.add(Calendar.DAY_OF_YEAR, -90);  
	    	break;
	    case Constants.CHART_REQ_TYPE_6MONTH:
	        startCal.add(Calendar.DAY_OF_YEAR, -182);  
	    	break;
	    case Constants.CHART_REQ_TYPE_1YEAR:
	        startCal.add(Calendar.DAY_OF_YEAR, -365);  
	    	break;
	    default:
	    	startCal.add(Calendar.DAY_OF_YEAR, -365);  
	    	break;
	    }
	    
	    while (startCal.compareTo(endCal) != 0) { 
	    	String date = dateFormat.format(startCal.getTime());
	    	
	    	if(isWeekendFmt(date) || isAuHoliday(date)) {
		    	startCal.add(Calendar.DAY_OF_YEAR, 1);
	    		continue;
	    	}
	    	
	    	ret.add(date+"Z");
	    	startCal.add(Calendar.DAY_OF_YEAR, 1);
	    	//System.out.println("---"+dateFormat.format(startCal.getTime()));
	    }  
	    
	    return ret;  
	}

	
	public static List<String> getAllDaysList(String type) {
		List<String> ret = new ArrayList<String>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setTimeZone(TimeZone.getTimeZone(Constants.SYS_TZ_VIC));
	    Calendar startCal = Calendar.getInstance(TimeZone.getTimeZone(Constants.SYS_TZ_VIC));
	    Calendar endCal = Calendar.getInstance(TimeZone.getTimeZone(Constants.SYS_TZ_VIC));
		try {
			Date startDate = new Date();
		    startCal.setTime(startDate);
		    endCal.setTime(startDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    switch(type){
	    case Constants.CHART_REQ_TYPE_1WEEK:
	        startCal.add(Calendar.DAY_OF_YEAR, -7);  
	    	break;
	    case Constants.CHART_REQ_TYPE_1MONTH:
	        startCal.add(Calendar.DAY_OF_YEAR, -30);  
	    	break;
	    case Constants.CHART_REQ_TYPE_3MONTH:
	        startCal.add(Calendar.DAY_OF_YEAR, -90);  
	    	break;
	    case Constants.CHART_REQ_TYPE_6MONTH:
	        startCal.add(Calendar.DAY_OF_YEAR, -182);  
	    	break;
	    case Constants.CHART_REQ_TYPE_1YEAR:
	        startCal.add(Calendar.DAY_OF_YEAR, -365);  
	    	break;
	    default:
	    	startCal.add(Calendar.DAY_OF_YEAR, -365);  
	    	break;
	    }
	    
	    do {  
	    	ret.add(dateFormat.format(startCal.getTime())+"Z");
		    //System.out.println("--dt:"+dateFormat.format(startCal.getTime()));
	    	startCal.add(Calendar.DAY_OF_YEAR, 1);
	    } while (startCal.compareTo(endCal) != 0);  
	    
	    //Collections.reverse(ret);
	    
	    return ret;  
	}
	
	public static List<String> getWorkingDaysList(String startdt, int backCount) {
		List<String> ret = new ArrayList<String>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	    
		dateFormat.setTimeZone(TimeZone.getTimeZone(Constants.SYS_TZ_VIC));
	    Calendar startCal = Calendar.getInstance(TimeZone.getTimeZone(Constants.SYS_TZ_VIC));
		Date startDate;
		try {
			startDate = dateFormat.parse(startdt);
		    startCal.setTime(startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    do {  
	        startCal.add(Calendar.DAY_OF_MONTH, -1);  
	        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY   
	        		&& startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) 
	        {
	        	//System.out.println(dateFormat.format(startCal.getTime())+"Z");
	        	ret.add(dateFormat.format(startCal.getTime())+"Z");
	        	--backCount;
	        }  
	    } while (backCount > 0);  
	    
	    Collections.reverse(ret);
	    
	    return ret;  
	}
	
	/**
	 * Check if a string is Australian Holiday
	 * @param date
	 * @return
	 */
	public static boolean isAuHoliday(String date){
		boolean ret = false;
		if(date == null || date.trim().length() == 0){
			return ret;
		}
		
		if(GetAustraliaHolidays("").get(date) != null){
			ret = true;
		}
		return ret;
	}
	
	/**
	 * Check if the date is on weekend with Constants.SYS_DT_FMT
	 * @param date
	 * @return
	 */
	public static boolean isWeekend(String date){
		boolean ret = false;
		if(date == null || date.trim().length() == 0) return ret;
		String regexDateTime = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/([0-9]{4})$";
		Pattern patternDateTime = Pattern.compile(regexDateTime);
		Matcher matcherDateTime = patternDateTime.matcher(date);
		if(!matcherDateTime.matches()){
			return ret;
		}
		
		DateFormat dateFormat = new SimpleDateFormat(Constants.SYS_DT_FMT);	    
		dateFormat.setTimeZone(TimeZone.getTimeZone(Constants.SYS_TZ_VIC));
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(Constants.SYS_TZ_VIC)); 
		Date startDate = null;
		try {
			startDate = dateFormat.parse(date);
			cal.setTime(startDate);
		    if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
		    	ret = true;
		    }
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * Check if the date is on weekend Constants.DATE_DT_FMT
	 * @param date
	 * @return
	 */
	public static boolean isWeekendFmt(String date){
		boolean ret = false;
		if(date == null || date.trim().length() == 0) return ret;
		String regexDateTime = "^([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
		Pattern patternDateTime = Pattern.compile(regexDateTime);
		Matcher matcherDateTime = patternDateTime.matcher(date);
		if(!matcherDateTime.matches()){
			return ret;
		}

		DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_DT_FMT);	    
		dateFormat.setTimeZone(TimeZone.getTimeZone(Constants.SYS_TZ_VIC));
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(Constants.SYS_TZ_VIC)); 
		Date startDate = null;
		try {
			startDate = dateFormat.parse(date);
			cal.setTime(startDate);
		    if((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)){
		    	ret = true;
		    }
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * Check a date is a Monday
	 * @param date
	 * @return
	 */
	public static boolean isMonday(String date){
		boolean ret = false;
		DateFormat dateFormat = new SimpleDateFormat(Constants.SYS_DT_FMT);	    
		dateFormat.setTimeZone(TimeZone.getTimeZone(Constants.SYS_TZ_VIC));
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(Constants.SYS_TZ_VIC)); 
		Date startDate;
		try {
			startDate = dateFormat.parse(date);
			cal.setTime(startDate);
		    if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
		    	ret = true;
		    }
		} catch (ParseException e) {
			
		}
		return ret;
	}

	/**
	 * get a int random number
	 * @return
	 */
	public static int GetSysRandomNumber(){
		Random r = new Random();
		return Math.abs(r.nextInt());
	}
	
	/**
	 * get a random string for identity of batchid
	 * @return
	 */
	public static String GetSysRondomString(){
		StringBuffer ret = new StringBuffer();
	    Calendar cal = Calendar.getInstance();
	    ret.append(cal.getTimeInMillis());
		
		return ret.toString();
	}
	
	/**
	 * Return a random string for batchid
	 * @return
	 */
	public static String GetRandBatchId(){
		String batchid = GetSysRondomString();
		if(DataCache.ProgressCache != null){
			Calendar cal = Calendar.getInstance();
			 Set<String> keys = DataCache.ProgressCache.keySet();
			 Iterator<String> itr = keys.iterator();
			 while (itr.hasNext()) { 
				 String key = itr.next();
				 ProgressVO uvo = DataCache.ProgressCache.get(key);
				 // if the record stay their over 10 minutes, then remove it
				 if((uvo != null) && ((cal.getTimeInMillis() - uvo.getDatetime()) > 10*60*1000)){
					 // should use this rather than hashtable.remove(key);
					 itr.remove();
				 }
			 } 
			 ProgressVO uvo = new ProgressVO();
			 uvo.setDatetime(cal.getTimeInMillis());
			 uvo.setProgress("Start to process...");
			 
			 DataCache.ProgressCache.put(batchid, uvo);
		}
		return batchid;
	}

	/**
	 * Write a  csv file
	 * @param sFileName
	 */
	public static void generateCsvFile(String sFileName, String sHeader, String[] aryLines)
	{
		try
		{
			if(sFileName == null || sFileName.length() == 0 || sHeader ==  null || sHeader.length() == 0 || aryLines == null){
				return;
			}
	    	File dir = new File(StaticConfig.filesBasePath);
	    	if(!dir.exists())
	    	{
	    		dir.mkdirs();
	    	}
	    	
			File f = new File(sFileName);
		    FileWriter writer = new FileWriter(f);
		    System.out.println("----Generated file for Westpac news history:"+f.getAbsolutePath());
		    
		    writer.append(sHeader).append('\n');
		    
		    for(String line: aryLines){
		    	writer.append(line).append('\n');
		    }
				
		    writer.flush();
		    writer.close();
		}
		catch(Exception e)
		{
		     e.printStackTrace();
		} 
	}
	
	public static byte[] readFully(InputStream stream) throws IOException
	{
	    byte[] buffer = new byte[8192];
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();

	    int bytesRead;
	    while ((bytesRead = stream.read(buffer)) != -1)
	    {
	        baos.write(buffer, 0, bytesRead);
	    }
	    return baos.toByteArray();
	}
	
	public static byte[] loadFile(String sourcePath) 
	{
	    InputStream inputStream = null;
	    try 
	    {
	        inputStream = new FileInputStream(sourcePath);
	        return readFully(inputStream);
	    }
	    catch (Exception ex){
	    	ex.printStackTrace();
	    }
	    finally
	    {
	        if (inputStream != null)
	        {
	            try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
	    return null;
	}	
	
	/**
	 * Convert date format From Morning start to Paritech (previously use)
	 * @param datestr
	 * @return
	 */
	public static String convertDateFormat(String datestr){
		if(datestr == null || datestr.length() == 0){
			return datestr;
		}
		try{
			SimpleDateFormat fromFmt = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat toFmt = new SimpleDateFormat("yyyy-MM-dd");
			Date dt = fromFmt.parse(datestr);
			String retstr = toFmt.format(dt)+"Z";
			return retstr;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "";
	}
	
	public static boolean isNeedRunMsTask(String start, String end){
		boolean ret = false;
		String startTime = Constants.USER_TM_START;
		String endTime = Constants.USER_TM_END;
		if(isLegalTime(start, end)){
			startTime = start + ":00:00";
			endTime = end + ":00:00";
		}else{
			return ret;
		}
		String currentTime = GetCurrentTimeWithTMZ(Constants.USER_TM_FMT, Constants.SYS_TZ_VIC);
		if(isTimeBetweenTwoTime(startTime, endTime, currentTime)){
			ret = true;
		}
		return ret;
	}
	
	public static boolean canRunAsxUpdate(){
		boolean ret = false;
		String currentTime = GetCurrentTimeWithTMZ(Constants.USER_TM_FMT, Constants.SYS_TZ_VIC);
		if(isTimeBetweenTwoTime("10:00:00", "10:30:00", currentTime)){
			ret = true;
		}
		return ret;
	}
	
	private static boolean isLegalTime(String start, String end){
		boolean ret = false;
		try{
			int st = Integer.parseInt(start);
			int ed = Integer.parseInt(end);
			if(st>0 && st<24 && ed>0 && ed < 24 && st<ed){
				ret = true;
			}
		}catch(Exception ex){
			ret = false;
			ex.printStackTrace();
		}
		return ret;
	}
	
	// check a given time is between other two given time
	public static boolean isTimeBetweenTwoTime(String argStartTime, String argEndTime, String argCurrentTime) {
        boolean valid = false;
		try{
	        String reg = "^([0-9]|[0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
	        //
	        if (argStartTime.matches(reg) && argEndTime.matches(reg) && argCurrentTime.matches(reg)) {
	            // Start Time
	            java.util.Date startTime = new SimpleDateFormat("HH:mm:ss").parse(argStartTime);
	            Calendar startCalendar = Calendar.getInstance();
	            startCalendar.setTime(startTime);

	            // Current Time
	            java.util.Date currentTime = new SimpleDateFormat("HH:mm:ss").parse(argCurrentTime);
	            Calendar currentCalendar = Calendar.getInstance();
	            currentCalendar.setTime(currentTime);

	            // End Time
	            java.util.Date endTime = new SimpleDateFormat("HH:mm:ss").parse(argEndTime);
	            Calendar endCalendar = Calendar.getInstance();
	            endCalendar.setTime(endTime);

	            if (currentTime.compareTo(endTime) < 0) {
	                currentCalendar.add(Calendar.DATE, 1);
	                currentTime = currentCalendar.getTime();
	            }

	            if (startTime.compareTo(endTime) < 0) {
	                startCalendar.add(Calendar.DATE, 1);
	                startTime = startCalendar.getTime();
	            }
	            if (currentTime.before(startTime)) {
	                valid = false;
	            } else {
	            	if (currentTime.after(endTime)) {
	                    endCalendar.add(Calendar.DATE, 1);
	                    endTime = endCalendar.getTime();
	                }
	                if (currentTime.before(endTime)) {
	                    valid = true;
	                } else {
	                    valid = false;
	                }
	            }
	            return valid;
	        } 
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return valid;
    }	
	
	/**
	 * SMA
	 * @param data
	 * @param type
	 * @return
	 */
	public static String createSMAForType(String data, String type){
		try{
			Gson gson = new Gson();
			SmaResult result = new SmaResult();
			if(data == null || data.length() == 0){
				return gson.toJson(result);
			}
			List<SmaResult> sma = new ArrayList<SmaResult>();
			JSONArray objdata = new JSONArray(data);
			if(objdata.length() == 0) return data;
			
			SmaResult middle = new SmaResult();
			List<String> ret = getAllDaysList(type);
			if(ret != null && ret.size() > 0){
				for(String retstr: ret){
					for(int i=0;i<objdata.length();i++){
						JSONObject objok = objdata.getJSONObject(i);
						if(objok.getString("Date").equals(retstr)){
							middle = new SmaResult();
							middle.setLow(objok.getDouble("Low"));
							middle.setHigh(objok.getDouble("High"));
							middle.setOpen(objok.getDouble("Open"));
							middle.setClose(objok.getDouble("Close"));
							middle.setVolume(objok.getDouble("Volume"));
							sma.add(middle);
						}
					}
				}
			}
			result = averageIt(sma);
			return gson.toJson(result);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "";
	}
	/**
	 * SMA Diff
	 * @param data
	 * @param type
	 * @return
	 */
	public static String createSMADiffForType(String data, String type, SmaResult today){
		try{
			Gson gson = new Gson();
			SmaResult result = new SmaResult();
			if(data == null || data.length() == 0){
				return gson.toJson(result);
			}
			List<SmaResult> sma = new ArrayList<SmaResult>();
			JSONArray objdata = new JSONArray(data);
			if(objdata.length() == 0) return data;
			
			SmaResult middle = new SmaResult();
			SmaResult firstday = null;
			List<String> ret = getAllDaysList(type);
			if(ret != null && ret.size() > 0){
				for(String retstr: ret){
					for(int i=0;i<objdata.length();i++){
						JSONObject objok = objdata.getJSONObject(i);
						if(objok.getString("Date").equals(retstr)){
							if(firstday == null){
								firstday = new SmaResult();
								firstday.setClose(objok.getDouble("Close"));
								firstday.setOpen(objok.getDouble("Open"));
								firstday.setLow(objok.getDouble("Low"));
								firstday.setHigh(objok.getDouble("High"));
								firstday.setVolume(objok.getDouble("Volume"));
							}
							middle = new SmaResult();
							middle.setLow(objok.getDouble("Low"));
							middle.setHigh(objok.getDouble("High"));
							middle.setOpen(objok.getDouble("Open"));
							middle.setClose(objok.getDouble("Close"));
							middle.setVolume(objok.getDouble("Volume"));
							sma.add(middle);
						}
					}
				}
			}
			SmaResult average = averageIt(sma);
			result = percentageIt(firstday, today, average, sma.size());
			return gson.toJson(result);
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return "";
	}	
	
	public static SmaResult averageIt(List<SmaResult> sma) {
		SmaResult ret = new SmaResult();
		try{
			if(sma == null || sma.size() == 0) return ret;
			
			double low = 0;
			double high = 0;
			double open = 0;
			double close = 0;
			double volume = 0;
			
			for (SmaResult sr: sma){
				low += sr.getLow();
				high += sr.getHigh();
				open += sr.getOpen();
				close += sr.getClose();
				volume += sr.getVolume();
			}
			
			ret.setClose(close/sma.size());
			ret.setOpen(open/sma.size());
			ret.setHigh(high/sma.size());
			ret.setLow(low/sma.size());
			ret.setVolume(volume/sma.size());
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return ret;
	}
	
	public static SmaResult percentageIt(SmaResult firstday, SmaResult today, SmaResult average, int count) {
		if(firstday == null ||today == null ||average == null || count == 0) return null;
		SmaResult percentage = new SmaResult();
		try{
			percentage.setClose((( ((today.getClose()==null?0:today.getClose()) - (firstday.getClose()==null?0:firstday.getClose()))/count ) / (average.getClose()==null?1:average.getClose())) * 100);
			percentage.setOpen((( ((today.getOpen()==null?0:today.getClose()) - (firstday.getOpen()==null?0:firstday.getOpen()))/count ) / (average.getOpen()==null?1:average.getOpen())) * 100);
			percentage.setLow((( ((today.getLow()==null?0:today.getClose()) - (firstday.getLow()==null?0:firstday.getLow()))/count ) / (average.getLow()==null?1:average.getLow())) * 100);
			percentage.setHigh((( ((today.getHigh()==null?0:today.getClose()) - (firstday.getHigh()==null?0:firstday.getHigh()))/count ) / (average.getHigh()==null?1:average.getHigh())) * 100);
			percentage.setVolume((( ((today.getVolume()==null?0:today.getClose()) - (firstday.getVolume()==null?0:firstday.getVolume()))/count ) / (average.getVolume()==null?1:average.getVolume())) * 100);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return percentage;
	}
	
	private static String generateToken() {
		byte[] tokenBytes = new byte[64];
		new SecureRandom().nextBytes(tokenBytes);
		return new String(Base64.encode(tokenBytes), StandardCharsets.UTF_8);
	}
	
	/**
	 * check if the code is valid 
	 * @param code
	 * @return
	 */
	public static boolean validCode(String code){
		boolean ret = false;
		if(code == null) return ret;
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]{3,6}");  
        Matcher isNum = pattern.matcher(code);  
        if (isNum.matches())
        {  
           ret = true;  
        }
        if(ret){
    		Query query = new Query();
    		query.addCriteria(Criteria.where("symbol").is(code.toUpperCase()));
        	Instrument instrument = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, Instrument.class);
        	if(instrument == null){
        		ret = false;
        	}
        }
        return ret;  
    }
	
	public static boolean validQuestion(UserMessage question){
		boolean ret = false;
		if(question != null){
			// check email by RFC 5322 [http://howtodoinjava.com/regex/java-regex-validate-email-address/]
			String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(question.getEmail());
			if(!matcher.matches()){
				return ret;
			}
			
			if(question.getDatetime() == null || question.getDatetime().length() > 19){
				return ret;
			}

			String regexDateTime = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}(\\s)([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";
			Pattern patternDateTime = Pattern.compile(regexDateTime);
			Matcher matcherDateTime = patternDateTime.matcher(question.getDatetime());
			if(!matcherDateTime.matches()){
				return ret;
			}
			
			if(question.getName() == null || question.getName().length() > 255){
				return ret;
			}
			if(question.getNotes() == null || question.getNotes().length() > 1024){
				return ret;
			}
			if(question.getVac() == null || question.getVac().length() > 255){
				return ret;
			}
			
		}else{
			return ret;
		}
		
		ret = true;
		return ret;
	}
	
	/**
	 * Keeping the testing code below
	 * @param args
	 */
	public static void main(String args[]){
		
		Double abc = new Double("2.321");
		Double abcd = new Double("2.321");
		if(abc.equals(abcd)){
			System.out.println("Equal...");
		}else{
			System.out.println("Not Equal...");
		}
		
		Query qqqq = new Query();
		qqqq.addCriteria(Criteria.where("sectype").ne("2"));
		
		Query queryrelation = new Query();
		queryrelation.addCriteria(Criteria.where("meetingDetails").in("1470275935757A1062751762"));

		Query querydetail = new Query();
		List<String> tlist = new ArrayList<String>();
		tlist.add("1470275974487A1515076258");
		querydetail.addCriteria(Criteria.where("documentID").in(tlist));
		
		String[] rr =  {"1470275935757A1062751762","1470275974487A1515076258"};
		System.out.println(Arrays.toString(rr));
		
		String aabb = "[{\"Date\":\"2016-07-26Z\",\"Open\":99.0,\"High\":99.0,\"Low\":96.51,\"Close\":97.27,\"Volume\":33706},{\"Date\":\"2016-07-27Z\",\"Open\":97.44,\"High\":97.45,\"Low\":96.85,\"Close\":97.1,\"Volume\":27462}]";
		createAllItemsForArray(aabb, "1M");
		
		byte[] tokenBytes = "admin".getBytes(StandardCharsets.UTF_8);
		System.out.println("---encode:"+ new String(Base64.encode(tokenBytes), StandardCharsets.UTF_8));
		System.out.println("---decode:"+ new String(Base64.decode(Base64.encode(tokenBytes)), StandardCharsets.UTF_8));
		
		System.out.println("---generateToken:"+generateToken());
		
		ArrayList<Double> groupList = new ArrayList<>();
		groupList.add(1.22);
		groupList.add(2.22);
		groupList.add(0.22);
		groupList.add(1.21);
		groupList.add(1.62);
		
		Collections.sort(groupList);
		System.out.println(new Gson().toJson(groupList)+"\n");
		Collections.sort(groupList, Collections.reverseOrder());
		System.out.println(new Gson().toJson(groupList)+"\n");
        Collections.reverse(groupList);
        System.out.println(new Gson().toJson(groupList)+"\n");
        
		List<HsOne> list1 = new ArrayList<HsOne>();
		HsOne one = new HsOne();
		one.setKey("a");
		one.setValue(1);
		list1.add(one);
		
		one = new HsOne();
		one.setKey("b");
		one.setValue(2);
		list1.add(one);
		
		one = new HsOne();
		one.setKey("c");
		one.setValue(4);
		list1.add(one);
		
		HsArray hsa = new HsArray();
		hsa.setHslist(list1);
		
		String json = new Gson().toJson(hsa);
		System.out.println(json+"\n\n");

		
		String data = "[{\"Date\":\"2016-09-01Z\",\"Open\":99.0,\"High\":99.0,\"Low\":96.51,\"Close\":97.27,\"Volume\":33706},{\"Date\":\"2016-09-02Z\",\"Open\":97.44,\"High\":97.45,\"Low\":96.85,\"Close\":97.1,\"Volume\":27462}]";
		SmaResult today = new SmaResult();
		today.setClose(99.77);
		today.setOpen(101.22);
		today.setHigh(102.99);
		today.setLow(97.11);
		today.setVolume((double)43201);
		
		today.setClose(95.77);
		today.setOpen(97.22);
		today.setHigh(98.99);
		today.setLow(95.11);
		today.setVolume((double)23201);
		
		System.out.println("==========================\n"+createSMAForType(data, "1W")           + "\n=============================");
		System.out.println("==========================\n"+createSMADiffForType(data,"1W", today) + "\n=============================");
		
		String aa = "146|1|MSTATS_ALL_MJ|||||||||||||";
		String[] tt = aa.split("[|]");
		System.out.println("----size:"+tt.length);
		
		String time     =   GetCurrentTimeWithTMZ(Constants.USER_TM_FMT, Constants.SYS_TZ_VIC);
		System.out.println("----time:"+time);

        Field[] fields = ShareDataM.class.getDeclaredFields();
        for(Field f : fields){
            Class<?> t = f.getType();
            System.out.println("----field name : " + f.getName() + " , type : " + t);
        }
		
		System.out.println(GetCurrentTimeWithTMZ("yyyy-MM-ddZ",""));
		StringBuffer str = new StringBuffer();
		str.append("{\"Low\":").append(2.123).append(",\"Open\":").append(3.22).append(",\"Close\":").append(1.222).append(",\"Date\":").append("\"").append("2016-05-03").append("Z\",\"Volume\":").append(1212121).append(",\"High\":").append(3.222).append("}");
		System.out.println("----"+str.toString());
		
		Hashtable<String, String> changedPriceHt = new Hashtable<String, String>();
		changedPriceHt.put("aa", "aaaa");
		changedPriceHt.put("aa1", "aa11");
		List<HashMessage> changedPriceList = new ArrayList<HashMessage>();
		HashMessage uvo = new HashMessage();
		uvo.setCode("aa");
		uvo.setValue("aaa");
		changedPriceList.add(uvo);
		
		HashMessage[] aryinput = new HashMessage[changedPriceList.size()];
		aryinput = changedPriceList.toArray(aryinput);
		
		for(int i=0;i<aryinput.length;i++){ 
			
			System.out.println("----code:"+aryinput[i].getCode());
		}
		
		System.out.println("\n-----"+ConvertTimeToTMZ("08/04/2016 05:05:39","dd/MM/yyyy HH:mm:ss","Australia/Victoria"));
		
	}
}

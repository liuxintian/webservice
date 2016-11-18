package com.omt.cms.core.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

public class DateHelper {

	public static final String	FORMAT_YYYYMMDD			= "yyyy/MM/dd";
	public static final String	FORMAT_YYYYMMMDD		= "yyyy/MMM/dd";
	public static final String	FORMAT_DDMMYYYY			= "dd/MM/yyyy";
	public static final String	FORMAT_MMDDYYYY			= "MM/dd/yyyy";
	public static final String	FORMAT_YYYYMMDD_TIME	= FORMAT_YYYYMMDD + " HH:mm:ss";
	public static final String	FORMAT_DDMMYYYY_TIME	= FORMAT_DDMMYYYY + " HH:mm:ss";
	public static final String	FORMAT_MMDDYYYY_TIME	= FORMAT_MMDDYYYY + " HH:mm:ss";
	public static final String	FORMAT_YYYYMMDD_TIME_MS	= FORMAT_YYYYMMDD_TIME + ":SSS";

	// Following are for JQuery ONLY; NOT TO BE USED IN JAVA
	public static final String	JQ_FORMAT_YYMMDD	= "yy/mm/dd";
	public static final String	JQ_FORMAT_DDMMYY	= "dd/mm/yy";
	public static final String	JQ_FORMAT_MMDDYY	= "mm/dd/yy";

	private static final Logger logger = Logger.getLogger(DateHelper.class.getName());
	
	
	// ***************************************************************************************************************
	// These methods get you date in object or string format **********************************************************
	// ***************************************************************************************************************
	// IMPORTANT -> The application should always store the time in GMT; so it is advisable to call the method without TZ
	public static Date getTodayDate(String format) {
		return getTodayDate(format, null);
	}
	
	// This method considers the format and the timezone to get the date representation.
	public static Date getTodayDate(String format, String timeZone) {
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1; 			// January starts from 0; strange Calendar constants!!
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		int ms = cal.get(Calendar.MILLISECOND);
		String value = "";

		if (FORMAT_YYYYMMDD.equals(format))
			value = "" + year + "/" + month + "/" + day;
		else if (FORMAT_DDMMYYYY.equals(format))
			value = "" + day + "/" + month + "/" + year;
		else if (FORMAT_MMDDYYYY.equals(format))
			value = "" + month + "/" + day + "/" + year;
		else
			value = "" + year + "/" + month + "/" + day + " " + hour + ":" + min + ":" + sec;

		if(FORMAT_YYYYMMDD_TIME_MS.equals(format)) {
			value += ":" + ms;
		}
		
		//Now add the timezone value as well
		if(timeZone==null) {
			value = value + " GMT";
		} else {
			value = value + " " + timeZone;
		}
		return getDate(format + " z", value);
		
	} // end of getString

	public static Date getDate(String format, String value) {
		Date date = null;
		DateFormat dateFormat = new SimpleDateFormat(format);

		try {
			// If the date is bad then an exception will be caught and a null
			// date will be returned.
			date = dateFormat.parse(value);
		} catch (ParseException e) {
		}

		return date;
	} // end of getDate method


	//Month starts from 1(Jan) to 12(Dec)
	public static Date getDate(int year, int month, int day) {
		DateTime dt = new DateTime(year, month, day, 0, 0, 0, 0);
		return dt.toDate();
	}

	
	public static Date getDate(int year, int month, int day, int hourOfDay, int minuteOfHour, int secondOfMinute) {
		//Last parameter is millisecond.
		DateTime dt = new DateTime(year, month, day, hourOfDay, minuteOfHour, secondOfMinute, 0);
		return dt.toDate();
	}
	
	
	@SuppressWarnings("finally")
	public static Date getDateFromUTCFormat(String utcDate) {
		Date javaDate = null;
		try {
			//The UTC = Google date format are as below for the different browsers
			//"Wed Aug 10 08:00:00 UTC+0530 2011" or "Thu Aug 19 07:03:28 UTC 2010" [IE behavior]
			//"Tue Aug 09 2011 00:00:00 GMT-0700" - notice the year is in different location [Chrome/Firefox behavior]
			String[] tokens = StringUtils.split(utcDate, " ");	//Will give 6 tokens
			String dateStr = "";
			
			if(tokens[3].length()==4) {
				//Means this is Chrome/Firefox format of UTC where the year is the fourth token
				dateStr = tokens[0] + " " + tokens[1] + " " + tokens[2] + " " + tokens[3] + " " + tokens[4] + " ";
			} else {
				//This is IE format for UTC where year is the last token
				dateStr = tokens[0] + " " + tokens[1] + " " + tokens[2] + " " + tokens[5] + " " + tokens[3] + " ";
			}
			SimpleDateFormat simpleFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss");
			javaDate = simpleFormat.parse(dateStr);
		}
		catch (ParseException e) {
			logger.log(Level.SEVERE, "Error in converting date from UTC format. Got " + utcDate + " Ex->" + e.getMessage());
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, "Error in converting date from UTC format. Got " + utcDate + " Ex->" + e.getMessage());
		}
		finally {
			return javaDate;
		}
	}

	public static String getString(String format, Date date) {
   		return getString(date, format, null);
	}

	// This method considers the format and the timezone to get the date representation.
	public static String getString(Date date, String format, String timeZone) {
		if (format == null)
			format = FORMAT_YYYYMMDD;
		else if (format.trim().length() == 0)
			format = FORMAT_YYYYMMDD;
		// If the supplied format does not belong to any of the standard options
		// then use the default format.
		else if ((!FORMAT_YYYYMMDD.equals(format)) && (!FORMAT_DDMMYYYY.equals(format)) && (!FORMAT_MMDDYYYY.equals(format))
				&& (!FORMAT_YYYYMMDD_TIME.equals(format)) && (!FORMAT_DDMMYYYY_TIME.equals(format)) && (!FORMAT_MMDDYYYY_TIME.equals(format))
				&& (!FORMAT_YYYYMMDD_TIME_MS.equals(format))
				)
			format = FORMAT_YYYYMMDD;

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		if(timeZone!=null) {
			TimeZone localTz = TimeZone.getTimeZone(timeZone);
			dateFormat.setTimeZone(localTz);
		}
		String dateString = "";
		try {
			// If the date is null then exception will be caught and a blank
			// string will be returned.
			dateString = dateFormat.format(date);
		} catch (Exception ex) { }

		return dateString;
	} // end of getString

	
	// ***************************************************************************************************************
	// A variety of date operations **********************************************************************************
	// ***************************************************************************************************************
	public static int getYear(Date date) {
		DateTime dt = new DateTime(date);
		return dt.getYear();
	}

	public static int getMonth(Date date) {
		DateTime dt = new DateTime(date);
		return dt.getMonthOfYear();
	}
	
	public static int getDay(Date date) {
		DateTime dt = new DateTime(date);
		return dt.getDayOfMonth();
	}
	
	//Get hour min and second is the Java calendar implementation; Joda time does not seem to behave well.
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	public static int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	public static int getSeconds(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	// This method calculates the days between two dates
	public static int getDays(Date a, Date b) {
		int tempDifference = 0;
		int difference = 0;
		Calendar earlier = Calendar.getInstance();
		Calendar later = Calendar.getInstance();

		if (a.compareTo(b) < 0) {
			earlier.setTime(a);
			later.setTime(b);
		} else {
			earlier.setTime(b);
			later.setTime(a);
		}

		while (earlier.get(Calendar.YEAR) != later.get(Calendar.YEAR)) {
			tempDifference = 365 * (later.get(Calendar.YEAR) - earlier.get(Calendar.YEAR));
			difference += tempDifference;
			earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
		}

		if (earlier.get(Calendar.DAY_OF_YEAR) != later.get(Calendar.DAY_OF_YEAR)) {
			tempDifference = later.get(Calendar.DAY_OF_YEAR) - earlier.get(Calendar.DAY_OF_YEAR);
			difference += tempDifference;
			earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
		}

		return difference + 1; // +1 because the date needs to be INCLUSIVE
	} // end of method getDays
	
	
	//In this API date a is expected to be earlier than date b
	public static long getMinutes(Date a, Date b) {
		DateTime dt1 = new DateTime(a);
		DateTime dt2 = new DateTime(b);
		
		Period p = new Period(dt1, dt2);
		long hours = p.getHours();
		long minutes = p.getMinutes();
		return (hours*60) + minutes;
	}
	
	
	//This method adds the number of years specified in the second argument
	//The dateTime class used below is Joda class
	public static Date addYear(Date date, int years) {
		DateTime dt = new DateTime(date);
		dt = dt.plusYears(years);	//This is needed because the DateTime instance is immutable
		return dt.toDate();
	}
	
	public static Date addMonth(Date date, int months) {
		DateTime dt = new DateTime(date);
		dt = dt.plusMonths(months);
		return dt.toDate();
	}

	public static Date addWeeks(Date date, int weeks) {
		DateTime dt = new DateTime(date);
		dt = dt.plusWeeks(weeks);
		return dt.toDate();
	}

	public static Date addDays(Date date, int days) {
		DateTime dt = new DateTime(date);
		dt = dt.plusDays(days);
		return dt.toDate();
	}

	public static Date addHours(Date date, int hours) {
		DateTime dt = new DateTime(date);
		dt = dt.plusHours(hours);
		return dt.toDate();
	}

	public static Date addMinutes(Date date, int minutes) {
		DateTime dt = new DateTime(date);
		dt = dt.plusMinutes(minutes);
		return dt.toDate();
	}

	public static Date addSeconds(Date date, int seconds) {
		DateTime dt = new DateTime(date);
		dt = dt.plusSeconds(seconds);
		return dt.toDate();
	}
	
	public static Date subtractYears(Date date, int years) {
		DateTime dt = new DateTime(date);
		dt = dt.minusYears(years);
		return dt.toDate();
	}
	
	public static Date subtractMonth(Date date, int months) {
		DateTime dt = new DateTime(date);
		dt = dt.minusMonths(months);
		return dt.toDate();
	}

	public static Date subtractWeeks(Date date, int weeks) {
		DateTime dt = new DateTime(date);
		dt = dt.minusWeeks(weeks);
		return dt.toDate();
	}

	public static Date subtractDays(Date date, int days) {
		DateTime dt = new DateTime(date);
		dt = dt.minusDays(days);
		return dt.toDate();
	}

	public static Date subtractHours(Date date, int hours) {
		DateTime dt = new DateTime(date);
		dt = dt.minusHours(hours);
		return dt.toDate();
	}

	public static Date subtractMinutes(Date date, int minutes) {
		DateTime dt = new DateTime(date);
		dt = dt.minusMinutes(minutes);
		return dt.toDate();
	}

	public static Date subtractSeconds(Date date, int seconds) {
		DateTime dt = new DateTime(date);
		dt = dt.minusSeconds(seconds);
		return dt.toDate();
	}

	public static boolean isSameYear(Date date1, Date date2){
		if(date1==null || date2==null){
			return false;
		}
		
		if(date1!=null && date2!=null){
			Calendar calDate1 = Calendar.getInstance();
			calDate1.setTime(date1);
			Calendar calDate2 = Calendar.getInstance();
			calDate2.setTime(date2);
			if(calDate1.get(Calendar.YEAR)==calDate2.get(Calendar.YEAR)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isDatesEqualIgnoreTime(Date dt1, Date dt2){
		DateTime dtn1 = new DateTime(dt1).withTimeAtStartOfDay();
		DateTime dtn2 = new DateTime(dt2).withTimeAtStartOfDay();
		return dtn1.compareTo(dtn2) == 0;
	}
	
	// ***************************************************************************************************************
	//This method returns the days list based on the interested days
	//Week day starts with Sunday and value is 1 and Saturday is end of the week - value 7
	public static List<Date> getMatchingDaysList(Calendar start, Calendar end, String[] days) {
		return getMatchingDaysList(start, end, convertToIntArray(days));
	}

	public static List<Date> getMatchingDaysList(Calendar start, Calendar end, int[] days) {
		List<Date> weekendList = new ArrayList<Date>();
		while (start.before(end)) {
			if (contains(start.get(Calendar.DAY_OF_WEEK), days)) {
				weekendList.add(start.getTime());				
			}
			start.add(Calendar.DATE, 1);
		}
		return weekendList;
	}
	
	private static boolean contains(int day, int[] days) {
		for (int i : days) {
			if (day == i) {
				return true;
			}
		}
		return false;
	}
	
	//Method to convert string number array to int array
	private static int[] convertToIntArray(String days[]){
		if(days==null){
			return null;
		}
		int intDays[] = new int[days.length];
		int arrayIndex = 0;
		for(String day:days){
			if(StringUtils.isNumeric(day)){
				intDays[arrayIndex++] = Integer.parseInt(day);
			}
		}
		return intDays;
	}

	public static long totalDaysExludeWeekends(Date start, Date end){
	    Calendar cstart = GregorianCalendar.getInstance();
	    cstart.setTime(start);
	    int sweek = cstart.get(Calendar.DAY_OF_WEEK);
	    cstart.add(Calendar.DAY_OF_WEEK, -sweek + 1);

	    Calendar cend = GregorianCalendar.getInstance();
	    cend.setTime(end);
	    int eweek = cend.get(Calendar.DAY_OF_WEEK);
	    cend.add(Calendar.DAY_OF_WEEK, -eweek + 1);

	    long days = (cend.getTimeInMillis() - cstart.getTimeInMillis())/(1000*60*60*24);
	    long weekDays = days - (days*2/7);

	    if (sweek == Calendar.SUNDAY) {
	        sweek = Calendar.MONDAY;
	    }
	    if (eweek == Calendar.SUNDAY) {
	        eweek = Calendar.MONDAY;
	    }
	    return weekDays - sweek + eweek;
	}
	
	
	public static Date calculateEndDate(Date start, int busDays){
	    Calendar cstart = GregorianCalendar.getInstance();
	    cstart.setTime(start);
	    for(int day=0; day<busDays; day++){
	    	int weekDay = cstart.get(Calendar.DAY_OF_WEEK);
	    	if(weekDay == Calendar.FRIDAY){
	    		cstart.add(Calendar.DATE, 3);
	    	}else if(weekDay == Calendar.SATURDAY){
	    		cstart.add(Calendar.DATE, 2);
	    	}else if(weekDay == Calendar.SUNDAY){
	    		cstart.add(Calendar.DATE, 1);
	    	}else{
	    		cstart.add(Calendar.DATE, 1);
	    	}
	    }
	    return cstart.getTime();
	}
	
	public static int daysLeftInCurrentMonth(Date now) {
		DateTime dt = new DateTime(now);
		return dt.dayOfMonth().getMaximumValue() - dt.dayOfMonth().get() + 1; //inclusive of today
	}
	
	public static int totalDaysInMonth(Date now) {
		DateTime dt = new DateTime(now);
		return dt.dayOfMonth().getMaximumValue();
	}
	
	
	public static boolean hasSameYearAndMonth(Date src, Date trgt) {
		int srcYear = getYear(src);
		int srcMonth = getMonth(src);
		int trgtYear = getYear(trgt);
		int trgtMonth = getMonth(trgt);
		return (srcYear == trgtYear && srcMonth == trgtMonth);
	}
	
	public static Timestamp getCurTimeAtStartOfDay(){
		return new Timestamp((new DateTime().withTimeAtStartOfDay().toDate().getTime())- SystemCodes.FILTER_TIME_LAG);
	}

	public static Timestamp getCurTimestamp(){
		return new Timestamp(new Date().getTime());
	}

}

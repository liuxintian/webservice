package com.omt.webservice;

/**
 * Define all system static parameters
 * @author tonyliu
 *
 */
public class Constants {
	// user messages' status
	public static final int MSG_STATUS_APPROVED = 2;
	public static final int MSG_STATUS_DELETED = 4;
	
	public static final int MSG_STATUS_NORMAL = 1;
	public static final int MSG_STATUS_DIRE_APPROVED = 3;
	public static final int MSG_STATUS_DIRE_DELETED = 5;
	public static final int MSG_STATUS_APPROVED_DELETED = 7;
	
	public static final String SYS_TZ_VIC = "Australia/Victoria";
	public static final String SYS_TZ_UTC = "UTC";
	public static final String SYS_TZ_EDT = "EDT";
	public static final String SYS_TM_FMT = "dd/MM/yyyy HH:mm:ss";
	public static final String SYS_DT_FMT = "dd/MM/yyyy";
	public static final String DATE_DT_FMT = "yyyy-MM-dd";
	public static final String MS_DT_FMT = "dd-MM-yyyy";
	public static final String USER_TM_FMT = "HH:mm:ss";
	public static final String USER_TM_START = "06:00:00";
	public static final String USER_TM_END = "18:00:00";
	
	public static final int ALL_DATA = 1;
	public static final int ONE_YEAR_DATA = 2;
	public static final int BY_COUNT_DATA = 3;
	
	public static final int START_ONE = 10 * 1000;
	public static final int START_TWO = 30 * 1000;
	public static final int START_THREE = 50 * 1000;
	public static final int START_FOUR = 70 * 1000;
	public static final int START_FIVE = 90 * 1000;
	public static final int START_SIX = 110 * 1000;
	
	
	public static final int USER_STATUS_NORMAL = 1;
	public static final int USER_STATUS_DELETED = 2;
	public static final int USER_STATUS_DISABLED = 3;
	
	public static final String CHART_REQ_TYPE_1WEEK = "1W";   //1Week
	public static final String CHART_REQ_TYPE_1MONTH = "1M";  //1Month
	public static final String CHART_REQ_TYPE_3MONTH = "3M";  //3Month
	public static final String CHART_REQ_TYPE_6MONTH = "6M";  //6Month
	public static final String CHART_REQ_TYPE_1YEAR = "1Y";   //1Year

	public static final String[] CHART_REQ_TYPE_LIST = {"1W","1M","3M","6M","1Y"};
	
	public static final int    HTTP_STATUS_420 = 420;
	public static final String ERROR_INFO_420 =  "Data was not returned in time.";
	
	public static final String[] AUSTRALIA_HOLIDAY = {
			"-01-01",
			"-01-26",
			"-03-25",
			"-03-28",
			"-04-25",
			"-06-13",
			"-12-26",
			"-12-27",
			"-12-30"
	};
}

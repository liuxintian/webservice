package com.omt.webservice;

/**
 * RESTFul API URI defined here
 * @author tonyliu
 *
 */
public class RestUriConstant {
	
	public static final String HEADER_REQ_CODE = "Accept-Code";
	public static final String HEADER_REQ_CODE_DEFAULT = "";
	
	public static final String WSS_REST = "/wssocket";
	public static final String WSS_REST_QUERYPRICE = "/wssrequestcode";
	public static final String WSS_REST_QUERYCHARTHISTORY_COUNT = "/wssreqhistory";
	public static final String WSS_REST_QUERYCHARTHISTORY_TYPE = "/charthistory";
	public static final String WSS_REST_CHARTHIST_CHLIST = "/chartlist";
	public static final String WSS_REST_ALL_LASTCLOSE = "/allclose";

	public static final String WSS_REST_CHARTHIST_SMA = "/sma";
	public static final String WSS_REST_CHARTHIST_SMA_DIFF = "/smadiff";
	public static final String WSS_REST_CHARTHIST_SMALIST = "/smalist";
	public static final String WSS_REST_CHARTHIST_SMALIST_DIFF = "/smadifflist";

	public static final String WSS_REST_DIF = "/difmarket";
	
	public static final String WEB_REST = "/rest";
	public static final String WEB_REST_CREATEUSER = "/creatusermsg";
	public static final String WEB_PAGE_QUESTIONHISTORY = "/getuserhistmsg";
	
	public static final String WEB_REST_STATUS = "/status";
	
	public static final String WEB_PAGE = "/agm";
	public static final String WEB_PAGE_INDEX = "/index";
	
	public static final String WEB_PAGE_QUERYLIST = "/querylist";
	public static final String WEB_PAGE_ITEMDETAILS = "/finddetails";
	public static final String WEB_PAGE_DELETEMSG = "/deleteusermsg";
	
	public static final String WEB_PAGE_QUERY_APPROVEMSG = "/queryapproved";
	public static final String WEB_PAGE_POST_APPROVEMSG = "/postapprove";
	public static final String WEB_PAGE_ENTER_APPROVEMSG = "/approved";

	public static final String WEB_EMAIL_CONFIRMATION = "/sendmailcfm";
	public static final String WEB_PAGE_NEWSLIST = "/news";
	public static final String WEB_PAGE_NEWSTABLE = "/newslist";
	public static final String WEB_PAGE_NEWSDOWNLOAD = "/newsdownload";
	public static final String WEB_PAGE_QUERY_ONLINE = "/queryonline";
	public static final String WEB_REST_SETUSERSTATUS = "/setuserstatus";
	public static final String WEB_PAGE_SETSESSIONCODE = "/setsessioncode";
	
	public static final String WEB_ASX_BASE = "/asxdata";
	public static final String WEB_ASX_GET_ALL = "/all";
	public static final String WEB_ASX_GET_GAINS = "/gains";
	public static final String WEB_ASX_GET_DECLINES = "/declines";

	public static final String WEB_ST_PAGE = "/stat";
	public static final String WEB_ST_PAGE_INDEX = "/index";
	public static final String WEB_ST_PAGE_QUERYLIST = "/querylist";
	public static final String WEB_ST_PAGE_ITEMDETAILS = "/finddetails";
	public static final String WEB_ST_PAGE_SMA_HIST = "/smahistory";
	public static final String WEB_ST_PAGE_SMA_HIST_QRY = "/smahistlist";
	public static final String WEB_ST_PAGE_SMA_DIFF = "/smadiff";
	public static final String WEB_ST_PAGE_SMA_DIFF_QRY = "/smadifflist";
	public static final String WEB_ST_PAGE_USERSTATUS = "/online";
	
	public static final String STATIC_SHARE_PRICE_API = WSS_REST+WSS_REST_QUERYPRICE;
	public static final String STATIC_CHART_HISTORY_API = WSS_REST+WSS_REST_QUERYCHARTHISTORY_COUNT;
	public static final String STATIC_CHART_HISTORY_NEWAPI = WSS_REST+WSS_REST_QUERYCHARTHISTORY_TYPE;

	public static final String WEB_SYS_CFG = "/cfg";
	public static final String WEB_SYS_CFG_INDEX = "/index";
	public static final String WEB_SYS_CFG_LIST = "/list";
	public static final String WEB_SYS_CFG_EDIT = "/edit";
	public static final String WEB_SYS_CFG_LOGIN = "/login";
	public static final String WEB_SYS_CFG_LOGOUT = "/logout";
	public static final String WEB_SYS_CFG_HELP = "/help";
	public static final String WEB_SYS_CFG_HELP_PDF_INTERFACE = "/getinterface";
	public static final String WEB_SYS_CFG_HELP_PDF_REFERENCE = "/getreference";
	public static final String WEB_SYS_INDEX_WELCOME = "/welcome";
	public static final String WEB_SYS_INDEX_ROOT = "/";
	
	public static final String WEB_USERS_PAGE = "/user";
	public static final String WEB_USERS_PAGE_INDEX = "/index";
	public static final String WEB_USERS_PAGE_QUERYLIST = "/query";
	public static final String WEB_USERS_PAGE_DETAIL = "/detail";
	
	public static final String WEB_USERS_PAGE_NEW = "/newuser";
	public static final String WEB_USERS_PAGE_EDIT = "/edituser";
	public static final String WEB_USERS_PAGE_DELETE = "/deleteuser";
	public static final String WEB_USERS_PAGE_ROLES = "/rolesuser";
	
	public static final String WEB_FILE = "/file";

	public static final String WSS_REST_MEETING = "/meeting";
	public static final String WSS_REST_MEETING_ALL = "/all";
	public static final String WSS_REST_MEETING_QUERY = "/query";
	public static final String WSS_REST_MEETING_VALIDPWD = "/validatepwd";
	
	public static final String WSS_WEB_COMPANY = "/company";
	public static final String WSS_WEB_MEETING = "/meetings";
	public static final String WSS_WEB_CONTACT = "/contact";
	public static final String WSS_WEB_DOCUMENT = "/document";
	public static final String WSS_WEB_COMPANY_FINDMEETING = "/findmeeting";
	public static final String WSS_WEB_MEETING_FINDDETAIL = "/finddetail";
	
	public static final String WSS_WEB_COMMON_SELECT = "/select";
	public static final String WSS_WEB_COMMON_INDEX = "/index";
	public static final String WSS_WEB_COMMON_LIST = "/list";
	public static final String WSS_WEB_COMMON_SUBGRID = "/subgrid";
	public static final String WSS_WEB_COMMON_CODELIST = "/codelist";
	
	public static final String WSS_WEB_COMPANY_CODELIST = "/allcompany";

	public static final String WSS_WEB_COMMON_NEW = "/new";
	public static final String WSS_WEB_COMMON_EDIT = "/edit";
	public static final String WSS_WEB_COMMON_DELETE = "/del";
	
	public static final String WSS_REST_VOTEPAGE = "/votepage";
	public static final String WSS_REST_VOTE_USERLIST = "/votinguser";
	public static final String WSS_REST_VOTE_VOTELIST = "/votinglist";
	public static final String WSS_REST_VOTE_HOLDINGLIST = "/votingholding";
	
	public static final String WSS_REST_VOTING = "/voting";
	public static final String WSS_REST_NEWVOTE = "/newvote";
	public static final String WSS_REST_NEWUSER = "/newuser";
	public static final String WSS_REST_NEWHOLDING = "/newholding";
	public static final String WSS_REST_GETVOTELIST = "/getvotes";

	public static final String WSS_SERVER_PAGE = "/wsspage";
	public static final String WSS_SERVER_REQ = "/wssrequest";
	public static final String WSS_SERVER_WEB_TOMCAT7 = "/tomcat7";
	public static final String WSS_SERVER_WEB_SPRING = "/spring";
	public static final String WSS_SERVER_WEB_PLUGIN = "/plugin";
}

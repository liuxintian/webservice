/**
 * 
 */
package com.omt.cms.core.common;

import org.apache.commons.lang3.StringUtils;

/**
 * @author muragesh
 *
 */
public class SystemCodes {

	public static final int DEFAULT_VALID_UNTIL_YEAR_INCREMENT =30;
	
	public static final String INSTACE_MOBILE_API_PATH_READALL="/mobile/api/company-instances/{cpId}/{endPoint}";
	public static final String INSTANCE_MOBILE_API_PATH_READ="/mobile/api/company-instances/{cpId}/{endPoint}/{entId}";
	public static final String INSTANCE_API_PATH="/oapi/company-instances/{cpId}/{endPoint}";
  	//public static final String S3_URL="http://{bucket}.s3.amazonaws.com/{file}";
	public static final String CACHE_CONTROL="max-age=31557600";
	public static final String IMAGE_TYPE="image"; 
  	public static final String DATAMENU_URL="http://sample.com/{code}";

	
	
	public static final String CPID_HEADER = "X-OMT-CMS-CPID";
	public static final String USER_IDENTITY = "_USER";
	public static final String SERVICE_REQUEST = "_SREQ";
	public static final String REG_VER_TOKEN = "regVerToken";

	public static final String FILTER_VALID_UNTIL = "vtd";
	public static final String FILTER_STATUS = "status";	
	public static final String FILTER_STATUS_VALUE_ALL = "all";
	public static final String FILTER_EMPTY_TAG = "etag";
	public static final String FILTER_TAGS = "tags";
	public static final String FILTER_CP_TYPE="companyType";
	public static final String FILTER_LU_TYPE="type"; //local user type

	public static final String FILTER_PAGE = "page";
	public static final String FILTER_SIZE = "size";	
	public static final String FILTER_TERM = "q";
	public static final String FILTER_SORT_BY = "sortBy";
	public static final String FILTER_SORT_ORDER = "sortOrder";

	public static final int FILTER_TIME_LAG = 1000;
	public static final int PAGE_SIZE=100;
	public static final int SORT_ASC=1;
	public static final int SORT_DESC=2;
	
	public static final String DEFAULT_CP_TYPE="OML";
	
	public enum RecordStatus{
		CREATED(10), ACTIVE(20), INACTIVE(30), DELETED(50);
		
		private int value;
		
		private RecordStatus(int v){ this.value=v; }
		
		public int getValue(){ return value; }
	}

	public enum ROLE {
		SUPER_ADMIN("ROLE_SA"), MASTER_ADMIN("ROLE_MA"), INSTANCE_ADMIN("ROLE_IA"), 
		USER("ROLE_USER"), POWER_USER("ROLE_POWER_USER");

		private String value;

		private ROLE(String v){ this.value=v; }
		
		public String getValue(){ return value; }
	}

	public static enum VerificationContext {
		MM_REGISTRATION(1), MM_FORGOT_PASSWORD(10), 
		IM_REGISTRATION(1), IM_FORGOT_PASSWORD(10),
		USER_REGISTRATION(1), USER_REGISTRATION_MOBILE(2), 
		USER_MAIL_VERIFY(3), USER_PHONE_VERIFY(4), 
		USER_FORGOT_PASSWORD_EMAIL(5), USER_FORGOT_PASSWORD_PHONE(6),  
		;

		private VerificationContext(final int context) { this.context = context; }

		private final int context;

		public int getValue() { return context;}
		
	}
	
	public enum UserStatus{
		CREATED(1), INVITED(10), VERIFICATION_PENDING(20), ACTIVE(30), LOCKED(40), EXPIRED(50), INACTIVE(60), DELETED(70);
		
		private int value;
		
		private UserStatus(int v){ this.value=v; }
		
		public int getValue(){ return value; }
	}
	
	public enum ApiResource {
		ANNOUNCEMENTS("announcements"), KEY_DATES("key-dates"), EXECUTIVES("executives"),  DOCUMENT_LINKS("document-links");

		private String value;

		private ApiResource(String v){ this.value=v; }
		
		public String getValue(){ return value; }
	}


	public enum CompanyType {
		BOARDROOM("BDR"), OMNILOOP("OML"), WHITELABEL("WHL"), 
		;

		private String value;

		private CompanyType(String v){ this.value=v; }
		
		public String getValue(){ return value; }
		
		public CompanyType getType(String value){
			CompanyType types [] = CompanyType.values();
			for(CompanyType type:types){
				if(StringUtils.equals(type.getValue(), value)){
					return type;
				}
			}
			return null;
		}
	}

	public enum EntityColumnName {
		STATUS("status"), VALID_UNTIL("valid_untill"), TAG_ROLE("tag_role"), COMPANY_ID("company_id");

		private String value;

		private EntityColumnName(String v){ this.value=v; }
		
		public String getValue(){ return value; }
	}
	
	
}

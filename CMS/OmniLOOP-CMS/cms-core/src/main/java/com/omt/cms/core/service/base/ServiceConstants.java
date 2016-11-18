package com.omt.cms.core.service.base;

import org.apache.commons.lang3.StringUtils;


/**
 * 
 * @author Shiva Kalgudi
 *
 */

public class ServiceConstants {

	public static final String CPID_HEADER = "X-OMT-CMS-CPID";
	public static final String USER_IDENTITY = "_USER";
	public static final String SERVICE_REQUEST = "_SREQ";

	public static enum ServiceResultCodes {
		
		OPERATION_NOT_SUPPORTED("operation-ns"),
		OPERATION_FAILURE("operation-failure"),
		OPERATION_SUCCESS("operation-success"),
		OPERATION_PARTIAL("operation-partial"),
		OPERATION_DENIED("operation-denied"),
		
		RECORD_SAVED("record-saved"),
		REQUEST_DATA_INVALID("request-data-invalid"),
		RECORD_NOT_FOUND("record-not-found"),
		RECORD_FOUND("record-found"),
		RECORD_INACTIVE("record-inactive"),
		RECORD_ACCESS_DENIED("record-access-denied"),
		
		USER_ATTRIBUTES_NF("user-attributes-nf"),
		DUPLICATE_LOGINNAME("duplicate-login-name"),
		USER_NOT_FOUND("user-not-found"),
		USER_NOT_ACTIVE("user-not-active"),
		USER_VARIFICATION_PENDING("user-verification-pending"),
		LOGIN_CREDENTIALS_INVALID("login-credentials-invalid"),
		INVALID_TOKEN("invalid_token"),
		
		INSTANCE_INACTIVE("instance-inactive"),
		INSTANCE_UNREACHABLE("instance-unreachable"),
		INSTANCE_ERROR("instance-error"),
		;
		
		private ServiceResultCodes(final String text) {
			this.text = text;
		}

		private final String text;

		public String getValue() {
			return text;
		}
		
		public ServiceResultCodes getType(String value){
			ServiceResultCodes types [] = ServiceResultCodes.values();
			for(ServiceResultCodes type:types){
				if(StringUtils.equals(value, type.getValue())){
					return type;
				}
			}
			return null;
		}
	}

}

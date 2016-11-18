package com.omt.cms.core.service.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.omt.cms.core.service.bo.base.SerializableObject;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

public class ServiceAttributes extends SerializableObject{

	private static final long serialVersionUID = 1L;

	private Map<String, Object> result = null;

	public ServiceAttributes(){
		this.result = new HashMap<String, Object>();
		
	}

	public Map<String, Object> getResult() {
		return result;
	}

	protected Set<String> getAttributesKey() {
		Set<String> paramNames = result.keySet();
		return paramNames;
	}

	public void removeAttribute(String key) {
		result.remove(key);
	}

	public void addAttribute(String key, Object value) {
		result.put(key, value);
	}

	public String getStringAttribute(String key) {
		Object value = result.get(key);
		if (value instanceof String) {
			return (value == null) ? StringUtils.EMPTY : (String) value;
		} else {
			return StringUtils.EMPTY;
		}
	}

	public Object getObjectAttribute(String key) {
		return result.get(key);	
	}

	@SuppressWarnings("unchecked")
	public <T> T getObjectAttribute(String key, Class<T> type) {
		T value = null;		
		try{
			value = (T) result.get(key);	
		}catch(Exception e){
			//TODO add logger
		}
		return value; 
	}

	public Long getLongAttribute(String key) {
		String val = getStringAttribute(key);
		if (val.length() > 0)
			return new Long(val);
		else
			return null;
	}

	public Double getDoubleAttribute(String key) {
		String val = getStringAttribute(key);
		if (val.length() > 0)
			return new Double(val);
		else
			return null;
	}
	
	public Integer getIntegerAttribute(String key) {
		String val = getStringAttribute(key);
		if (val.length() > 0)
			return new Integer(val);
		else
			return null;
	}
	
	public static enum  ServiceCommonAttributes {
		RESULT("result"), RESPONSE_DATA("responseData"), REQUEST_DATA("requestData"), USER("user"), DOCUMENT_ID("docId") ;
		
		private final String text;

		private ServiceCommonAttributes(final String text) {
			this.text = text;
		}

		public String getValue() {
			return text;
		}

	}


}

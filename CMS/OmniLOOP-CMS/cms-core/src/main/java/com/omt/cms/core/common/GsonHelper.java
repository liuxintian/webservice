package com.omt.cms.core.common;

import java.io.Reader;
import java.lang.reflect.Modifier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

public class GsonHelper {

	private static Gson gsonInstance = new Gson();
	private static Gson gsonConvertAllInstance = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL).setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
	
	private GsonHelper(){ }
	
	public static String toJson(Object object){
		if(object!=null){
			return gsonInstance.toJson(object);
		}else{
			return null;
		}
	}
	
	public static String toJsonAll(Object object){
		if(object!=null){
			return gsonConvertAllInstance.toJson(object);
		}else{
			return null;
		}
	}

	public static String toJsonAll(Object object, String dateFormat){
		Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL).
				setDateFormat(dateFormat).create();	
		return gson.toJson(object);
	}

	public static <T> T  fromJsonAll(String json, Class<T> type) {
		return  gsonConvertAllInstance.fromJson(json, type);
	}

	public static <T> T  fromJson(String json, Class<T> type) {
		return  gsonInstance.fromJson(json, type);
	}
	
	public static <T> T  fromJsonAll(Reader jsonStream, Class<T> type) {
		return  gsonConvertAllInstance.fromJson(jsonStream, type);
	}

}

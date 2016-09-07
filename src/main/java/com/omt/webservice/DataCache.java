package com.omt.webservice;

import java.util.Hashtable;

import com.omt.exchanger.entity.ProgressVO;

public class DataCache {
	
	// <String batchid, ProgressVO uvo 
	public static Hashtable<String, ProgressVO> ProgressCache = new Hashtable<String, ProgressVO>();
	
}

package com.omt.cms.core.common;

import java.io.IOException;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class SimpleDateJsonDeserializer extends JsonDeserializer<Date>{
	
	public static final String SIMPLE_DATE_FORMAT = "MM-dd-yyyy" ;

	DateTimeFormatter formatter = DateTimeFormat.forPattern(SIMPLE_DATE_FORMAT);
	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext context) 	throws IOException, JsonProcessingException {	
		DateTime dtTime = formatter.parseDateTime(jsonparser.getText());		
		return dtTime.toDate();
	}

}

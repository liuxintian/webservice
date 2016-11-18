package com.omt.cms.core.common;

import java.io.IOException;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

public class SimpleDateJsonSerializer extends JsonSerializer<Date>{

	DateTimeFormatter formatter = DateTimeFormat.forPattern(SimpleDateJsonDeserializer.SIMPLE_DATE_FORMAT);
	
	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		DateTime dateTime = new DateTime(value);
		String strValue = formatter.print(dateTime);
		jgen.writeString(strValue);
	}

}

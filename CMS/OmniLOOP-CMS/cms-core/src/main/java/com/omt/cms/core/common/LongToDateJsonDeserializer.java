package com.omt.cms.core.common;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LongToDateJsonDeserializer extends JsonDeserializer<Date> {

	public final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

	@Override
	public Timestamp deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException, JsonProcessingException {
		Timestamp dateTime = null;
		String date = jsonparser.getText();
		if (StringUtils.isNotBlank(date)) {
			try {
				if (StringUtils.isNumeric(date)) {
					Long tm = new Long(date);
					dateTime = new Timestamp(tm);
				} else {
					DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);
					DateTime dtTime = formatter.parseDateTime(date);
					dateTime = new Timestamp(dtTime.toDate().getTime());
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return dateTime;
	}
}

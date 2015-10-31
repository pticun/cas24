package org.alterq.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DateFormatUtil {
	public String ISO_DATETIME_TIME_ZONE_FORMAT="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public String DATE_DAY_FORMAT="dd/MM/yyyy";
	private DateFormat dfIso ;
	private DateFormat dfDay ;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public DateFormatUtil() {
		dfIso=new SimpleDateFormat(ISO_DATETIME_TIME_ZONE_FORMAT);
		dfDay=new SimpleDateFormat(DATE_DAY_FORMAT);
	}
	
	public String convertIsoTimeToFormatDay(String dateToFormat){
		String result="";
		try {
			Date result1 = dfIso.parse(dateToFormat);
			result=dfDay.format(result1);
		} catch (ParseException e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return result;
	}
	
	public String convertFormatDayToIsoTime(String dateToFormat){
		String result="";
		try {
			Date result1 = dfDay.parse(dateToFormat);
			result=dfIso.format(result1);
		} catch (ParseException e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return result;
	}
	
	

}

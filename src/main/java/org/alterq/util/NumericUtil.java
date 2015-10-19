package org.alterq.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.stereotype.Service;

@Service
public class NumericUtil {
	
	public static String TWO_DECIMAL_FORMATTER="#0.00"; 
	public static Locale LOCALE_SP=new Locale("es","ES"); 
	
	NumberFormat formatter = NumberFormat.getInstance(LOCALE_SP);
	DecimalFormatSymbols otherSymbols;
	
	public NumericUtil() {
		super();
		formatter= new DecimalFormat(TWO_DECIMAL_FORMATTER);
		formatter.setRoundingMode(RoundingMode.CEILING);
		otherSymbols = new DecimalFormatSymbols(LOCALE_SP);
		otherSymbols.setDecimalSeparator(',');
		otherSymbols.setGroupingSeparator('.'); 
	}

	public String getTwoDecimalFormat(String value){
		DecimalFormat df = new DecimalFormat(value, otherSymbols);		
		String valueConvert=df.format(value);
		return valueConvert;
	}
	
	public String getTwoDecimalFormat(BigDecimal value){
		String valueConvert=formatter.format(value);
		return valueConvert;
	}
	
}

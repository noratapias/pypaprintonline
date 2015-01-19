package com.pypaprint.pedidos.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatos {
	public Date parseStringToDate(String date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateInString = date;
	 
		try {
	 
			Date fecha = formatter.parse(dateInString);
			return fecha;
	 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String parseDateToString(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
}

package com.bota.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStrConvert {
	 public static String dateToStr(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(date);
		return dateString;
	 }
	 
	 
	 public static Date strToDate(String dateString, String format) {
			DateFormat dataformatter = new SimpleDateFormat(format);
			Date date = null;
			try {
				date = dataformatter.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
	 }
	 
	 public static void main(String[] args) {
		String date = "2015-10-22";
		System.out.println(strToDate(date, "yyyy-MM-dd HH:mm:ss"));
	}
}

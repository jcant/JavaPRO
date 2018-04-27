package com.gmail.gm.jcant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JDate {

	private static String defaultFormat = "dd-MM-yyyy";
	private static String defaultTimeFormat = "hh:mm:ss";

	public static void setDefaultFormat(String format) {
		defaultFormat = format;
	}

	// --- static ---
	public static Date getDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(defaultFormat);
		Date result = null;
		try {
			result = sdf.parse(date);
		} catch (ParseException e) {
			System.err.println("Error getting quick date!");
		}
		return result;
	}

	public static String getDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(defaultFormat);
		String result = sdf.format(date);
		return result;
	}
	
	public static String getTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(defaultTimeFormat);
		String result = sdf.format(date);
		return result;
	}

	public static int getDifferenceYears(Date from, Date to) {
		Calendar dayFrom = Calendar.getInstance();
		dayFrom.setTime(from);
		Calendar dayTo = Calendar.getInstance();
		dayTo.setTime(to);

		int diff = dayTo.get(Calendar.YEAR) - dayFrom.get(Calendar.YEAR);
		dayTo.set(Calendar.YEAR, dayFrom.get(Calendar.YEAR));

		if (dayFrom.compareTo(dayTo) >= 0) {
			diff--;
		}

		return diff;
	}

	public static Date incDay(Date date, int days) {
		Calendar day = Calendar.getInstance();
		day.setTime(date);
		day.add(Calendar.DAY_OF_MONTH, days);
		return day.getTime();
	}

	public static Date incMonth(Date date, int months) {
		Calendar day = Calendar.getInstance();
		day.setTime(date);
		day.add(Calendar.MONTH, months);
		return day.getTime();
	}

	public static Date incYear(Date date, int years) {
		Calendar day = Calendar.getInstance();
		day.setTime(date);
		day.add(Calendar.YEAR, years);
		return day.getTime();
	}
}

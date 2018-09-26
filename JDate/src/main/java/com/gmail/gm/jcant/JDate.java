package com.gmail.gm.jcant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JDate {

    private static String defaultDateFormat = "dd-MM-yyyy";
    private static String defaultTimeFormat = "HH:mm:ss";

    public static void setDefaultDateFormat(String format) {
        defaultDateFormat = format;
    }

    public static void setDefaultTimeFormat(String format) {
        defaultTimeFormat = format;
    }

    // --- static ---
    public static Date getDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(defaultDateFormat);
        Date result = null;
        try {
            result = sdf.parse(date);
        } catch (ParseException e) {
            System.err.println("Error getting quick date!");
        }
        return result;
    }

    public static Date getDate(String date, String time){
        return setTime(getDate(date), time);
    }

    public static String getDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(defaultDateFormat);
        String result = sdf.format(date);
        return result;
    }

    public static Date setDate(Date date, String set){
        if (date == null) {
            throw new IllegalArgumentException();
        }
        Calendar our = Calendar.getInstance();
        Calendar setter = Calendar.getInstance();
        our.setTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat(defaultDateFormat);
        try {
            setter.setTime(sdf.parse(set));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        our.set(setter.get(Calendar.YEAR), setter.get(Calendar.MONTH), setter.get(Calendar.DAY_OF_MONTH));
        return our.getTime();
    }

    public static String getTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(defaultTimeFormat);
        String result = sdf.format(date);
        return result;
    }

    public static Date setTime(Date date, String set){
        if (date == null) {
            throw new IllegalArgumentException();
        }
        Calendar our = Calendar.getInstance();
        Calendar setter = Calendar.getInstance();
        our.setTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat(defaultTimeFormat);
        try {
            setter.setTime(sdf.parse(set));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        our.set(Calendar.HOUR_OF_DAY, setter.get(Calendar.HOUR_OF_DAY));
        our.set(Calendar.MINUTE, setter.get(Calendar.MINUTE));
        our.set(Calendar.SECOND, 0);
        return our.getTime();
    }

    public static long getDifferenceDays(Date from, Date to) {
    	long msDiff = to.getTime() - from.getTime();
    	long diff =  TimeUnit.DAYS.convert(msDiff, TimeUnit.MILLISECONDS);

        return diff;
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

    //this is comment
}

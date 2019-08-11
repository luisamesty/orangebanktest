package com.orange.spring.utils;

import java.util.Date;
import java.sql.Timestamp;
import java.util.BitSet;
import java.util.Calendar;

import java.util.GregorianCalendar;
import java.util.List;

//GET DATE & TIME IN ANY FORMAT
import java.text.SimpleDateFormat;

/**
 *	Time Utilities
 *
 */
public class TimeUtil
{
	private Timestamp datetime;
	
	public final String DATE_FORMAT_NOW = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public SimpleDateFormat sdfString = new SimpleDateFormat(DATE_FORMAT_NOW);
	
    public Timestamp getDatetime() {
		return datetime;
	}
	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	/**
	 * getTodayDate Returns Today Date in Timestamp Format
	 * @return
	 */
	public Timestamp getTodayDate() {
		Calendar calendar = Calendar.getInstance();
		Date today = new Date();
		calendar.setTime(today);
	    calendar.set(Calendar.MILLISECOND, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    today = calendar.getTime();
		Timestamp TSDateTime=new Timestamp(today.getTime());
		return TSDateTime;
	}
	
	/**
	 * getTodayDateST  Returns Today Date in String Format with yyyy-MM-dd 00:00:00.000
	 * @return
	 */
	public String getTodayDateST() {
		String StingDateTime="";
		
		Calendar calendar = Calendar.getInstance();
		Date today = new Date();
		calendar.setTime(today);
	    calendar.set(Calendar.MILLISECOND, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    today = calendar.getTime();
		
		StingDateTime = sdfString.format(today);
		return StingDateTime;
	}
	
	/**
	 * convertStringDateToTimestap(String StringDateTime) :
	 * 		Convert String Fomatedd "yyyy-MM-ddTHH:mm:ss.SSSZ" in Timestap
	 * @param StringDateTime
	 * @return
	 */
	public Timestamp convertStringDateToTimestamp(String StringDateTime) {
		//  yyyy-MM-ddTHH:mm:ss.SSSZ
		//  012345678901234567890123
		String convString = StringDateTime.substring(0, 10)+" "+StringDateTime.substring(11, 22);
		Timestamp retTimestamp = Timestamp.valueOf(convString);
		//System.out.println("convertStringDateToTimestamp "+StringDateTime+"  convString to >>"+convString+ "  retTimestamp="+retTimestamp);
		return retTimestamp;
	}
	
	/**
	 * convertStringDateToTimestampZeroTime  Set TimeTime  00:00:00.000
	 * @param StringDateTime
	 * @return
	 */
	public Timestamp convertStringDateToTimestampZeroTime(String StringDateTime) {
		//  yyyy-MM-ddTHH:mm:ss.SSSZ
		//  012345678901234567890123
		String convString = StringDateTime.substring(0, 10)+" 00:00:00.000";
		Timestamp retTimestamp = Timestamp.valueOf(convString);
		System.out.println("convertStringDateToTimestamp "+StringDateTime+"  convString to >>"+convString+ "  retTimestamp="+retTimestamp);
		return retTimestamp;
	}
	
	/**
	 * compareStringDateWithToday:  Compare String DateTime with Today
	 * @param StringDateTime
	 * @return
	 */
	public int compareStringDateWithToday(String StringDateTime) {
		int retInt=0;
		Timestamp today = getTodayDate();
		Timestamp trdate = convertStringDateToTimestamp(StringDateTime);
	System.out.println("today="+today+"  TR Date="+trdate);
		if (trdate.compareTo(today) >= 0) 
			retInt = 1;
		else if (trdate.compareTo(today) < 0)
			retInt = -1;
		else if (trdate.compareTo(today) == 0)
			retInt =0;
		return retInt;
	}
	
	/**
	 * compareStringDateWithTodayOnlyDates:  Compare String DateTime with Today (Only Dates)
	 * @param StringDateTime
	 * @return
	 */
	public int compareStringDateWithTodayOnlyDates(String StringDateTime) {
		int retInt=0;
		Timestamp today = getTodayDate();
		Timestamp trdate = convertStringDateToTimestampZeroTime(StringDateTime);
		System.out.println("today="+today+"  TR Date="+trdate);
		if (trdate.compareTo(today) > 0) 
			retInt = 1;
		else if (trdate.compareTo(today) < 0)
			retInt = -1;
		else if (trdate.compareTo(today) == 0)
			retInt =0;
		return retInt;
	}
	
}	//	TimeUtil


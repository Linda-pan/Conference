package com.elin4it.ssm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {

	private final static SimpleDateFormat FORMAT_PATTERN_LONG = 
			new SimpleDateFormat("yyyy-MM-dd");
	
	private final static SimpleDateFormat FORMAT_PATTERN_SHORT = 
			new SimpleDateFormat("yyyy-M-d");
	
	private final static SimpleDateFormat FORMAT_PATTERN_FULL = 
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final SimpleDateFormat FORMAT_PATTERN_FULL_SHORT = 
			new SimpleDateFormat("yyyy-M-d H:m:s");
	
	public static Date fromString(String str) throws ParseException {
		try {
			return FORMAT_PATTERN_LONG.parse(str);
		} catch(ParseException e) {
			try {
				return FORMAT_PATTERN_SHORT.parse(str);
			} catch (ParseException e1) {
				throw e1;
			}
		}
	}
	
	public static String fromDate(Date date) {
		return FORMAT_PATTERN_LONG.format(date);
	}
	
	public static String fromDateFull(Date date) {
		return FORMAT_PATTERN_FULL.format(date);
	}
	
	public static String formatDate(String date) throws ParseException {
		Date tDate = fromString(date);
		return fromDate(tDate);
	}

	public static String longToFullTime(long timestamp){

		return FORMAT_PATTERN_FULL.format(timestamp);
	}

	public static Date stringToDate(String s, String format) throws ParseException{
		if(s == null || s.length() == 0)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(format);		 
		return formatter.parse(s);	 
	}
	
	public static String nowFullString() {
		return fromDateFull(new Date());
	}
	
	public static String dateToString(Date date){		 
		return dateToString(date, null);	 
	}	 
	 
	public static String dateToString(Date date, String format){
		if (date==null){
			return "";
		}
		
		if ((format==null) || (format.length()==0)){
			format = "yyyy-MM-dd HH:mm:ss";
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat(format);		 
		return formatter.format(date);	 
	}
	
	public static String formatDateTime(String srcDateStr) {
		Date date = null;

		if(date == null) {
			try {
				date = FORMAT_PATTERN_FULL.parse(srcDateStr);
			} catch(ParseException e) {
				Logger logger= LoggerFactory.getLogger(DateUtils.class);
				logger.error(e.getLocalizedMessage(),e);
				date = null;
			}
		}
		
		if(date == null) {
			try {
				date = FORMAT_PATTERN_FULL_SHORT.parse(srcDateStr);
			} catch(ParseException e) {
				Logger logger= LoggerFactory.getLogger(DateUtils.class);
				logger.error(e.getLocalizedMessage(),e);
				date = null;
			}
		}
		
		if(date == null) {
			return srcDateStr;
		}
		
		return FORMAT_PATTERN_FULL.format(date);
	}

	/**
	 * 把unix时间戳转为为date
	 * @param unixtime
	 * @return
	 */
	public static Date unixTimeStampToDate(long unixtime) {
		return new Date(unixtime * 1000);
	}

	/**
	 * unix时间戳转换为相应格式的字符串
	 * @param unixtime
	 * @param format
	 * @return
	 */
	public static String unixTimeStampToString(long unixtime, String format) {
		return dateToString(unixTimeStampToDate(unixtime), format);
	}
	
	/**
	 * 获得两个时间相差的毫秒数
	 * @param dateSrc 靠后的日期
	 * @param dateDest 靠前的日期
	 * @return
	 */
	public static long getDiffMilliSeconds(Date dateSrc, Date dateDest){
		return (long) Math.floor((dateSrc.getTime()-dateDest.getTime()));
	}
	
	/**
	 * 获得某一个月份的天数
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysInYearMonth(int year, int month){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		//calendar的月份是从0开始
		calendar.set(year, month-1, 1);
		
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 日期加上minuts个分钟后的日期时间
	 * @param date
	 * @param minuts
	 * @return
	 */
	public static Date addMinuts(Date date,int minuts){
		Calendar calendar = Calendar.getInstance();		
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minuts);
		
		return calendar.getTime();
	}
	
	/**
	 * 获得日期的年份
	 * @param date
	 * @return
	 */
	public static int getYearOfDate(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	
	/**
	 * 获得日期的月份
	 * @param date
	 * @return
	 */
	public static int getMonthOfDate(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}
	
	/**
	 * 判断两个时间是否相同
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isDateEquals(Date date1,Date date2){
		return date1.getTime()-date2.getTime()==0 ? true:false; 
	}
	
	/**
	 * 获得日期中的日
	 * @param date
	 * @return
	 */
	public static int getDayOfDate(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 返回日期的星期几
	 * @param date
	 * @return
	 */
	public static String getDayOfWeek(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		int weekDay=calendar.get(calendar.DAY_OF_WEEK);
		String wd;
		switch (weekDay) {
		case Calendar.MONDAY:
			wd="一";
			break;
		case Calendar.TUESDAY:
			wd="二";
			break;
		case Calendar.WEDNESDAY:
			wd="三";
			break;
		case Calendar.THURSDAY:
			wd="四";
			break;
		case Calendar.FRIDAY:
			wd="五";
			break;
		case Calendar.SATURDAY:
			wd="六";
			break;
		case Calendar.SUNDAY:
			wd="天";
			break;

		default:
			wd=null;
			break;
		}
		return wd;
	}
	
	/**
	 * 计算旧日期和新日期newDate之间相差几年
	 * @param oldDate
	 * @param newDate
	 * @return
	 */
	public static int getDiffYear(Date oldDate,Date newDate){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(oldDate);
		int i=0;
		calendar.add(Calendar.YEAR, 1);
		while(calendar.getTimeInMillis()<newDate.getTime()){
			calendar.add(Calendar.YEAR, 1);
			i++;
		}
		return i;
	}

	/**
	 *比较date1和date2的大小
	 */
	public static int compareDate(Date date1,Date date2){
		if (date1 ==null || date2 ==null){
			if (date1 != null) return 1;
			if (date2 != null) return -1;
			return 0;
		}else {
			if (date1.getTime()>date2.getTime()){
				return 1;
			}else if (date1.getTime()<date2.getTime()){
				return -1;
			}

			return 0;
		}
	}

	/**
	 * 日期加上days天后的日期时间
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDay(Date date,int days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);

		return calendar.getTime();
	}

	/**
	 * 日期加上months月后的日期时间
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date addMonth(Date date,int months){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);

		return calendar.getTime();
	}

	/**
	 * 日期加上years年后的日期时间
	 * @param date
	 * @param years
	 * @return
	 */
	public static Date addYear(Date date,int years){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);

		return calendar.getTime();
	}

	/**
	 * 时间转换成unix时间戳即秒数
	 * @param date
	 * @return
	 */
	public static int dateToUnixTimeStamp(Date date) {
		return (int) Math.floor(date.getTime() / 1000);
	}


}

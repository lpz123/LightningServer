package com.maven.lupz.java.LightningServer.tool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Alix
 */
public class LSDate {

	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String DATE_MONTH = "yyyy-MM";
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final int DATE = 0;
	public static final int MONTH = 1;
	public static final int WEEK = 2;
	public static final int ENDDASTEWEEK = 3;

	/**
	 * 获取当天时间，格式为yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getCurDay() {
		Date date = new Date();
		String today = new SimpleDateFormat(DATE_FORMAT).format(date);
		return today;
	}

	public static String getDetailTime() {
		Date date = new Date();
		String today = new SimpleDateFormat(DATE_TIME_FORMAT).format(date);
		return today;
	}

	/**
	 * 参数为需要格式化的格式,如yyyy-MM-dd HH:mm:ss
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurDay(String format) {
		Date date = new Date();
		String today = new SimpleDateFormat(format).format(date);
		return today;
	}

	/**
	 * 参数为需要格式化的格式,如yyyy-MM-dd HH:mm:ss 获取当前时间的前一天
	 * 
	 * @param format
	 * @return
	 */
	public static String getLastDay(String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		// 得到前一天
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 计算指定时间与当前时间相差的分钟数
	 */
	public static int getFenZhongCha(long time) {
		long timeNow = System.currentTimeMillis();
		timeNow -= time;
		return (int) (timeNow / 1000 / 60);
	}

	/**
	 * 计算指定时间与当前时间相差的秒数
	 */
	public static int getMiaoCha(long time) {
		long timeNow = System.currentTimeMillis();
		timeNow -= time;
		return (int) (timeNow / 1000);
	}

	/**
	 * 获取当前小时
	 * @param format
	 * @return
	 */
	public static String getCurHour() {
		Date date = new Date();
		String hour = new SimpleDateFormat("HH").format(date);
		return hour;
	}

	/**
	 * 获取当前小时
	 * @param format
	 * @return
	 */
	public static String getCurHourMinute() {
		Date date = new Date();
		String hour = new SimpleDateFormat("HH:mm").format(date);
		return hour;
	}

	/**
	 * 获取当前分钟
	 * @param format
	 * @return
	 */
	public static String getCurMinute() {
		Date date = new Date();
		String hour = new SimpleDateFormat("mm").format(date);
		return hour;
	}

	/**
	 * 取一个完整时间的年和月 2013-05-06 13:25:10或2013-05-06
	 * @param time
	 * @return 2013-05
	 */
	public static String getYearAndMonth(String time) {
		String returnValue = "";
		String[] ss = time.split(" ");
		String[] ss2 = null;
		ss2 = ss[0].split("-");
		returnValue = ss2[0] + "-" + ss2[1];
		return returnValue;
	}

	/**
	 * 对传来的日期进行加减运算，得到一个字符串类型 dayNum 正数是加，负数是减
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String subOrAddDays(String date, int dayNum) {
		String dateValue = "";
		Calendar c = Calendar.getInstance();
		c.setTime(LSDate.stringToDate(date, "yyyy-MM-dd"));
		c.add(c.DAY_OF_YEAR, dayNum);
		dateValue = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dateValue;
	}

	/**
	 * 字符串转换到时间格式
	 * 
	 * @param dateStr
	 *            需要转换的字符串
	 * @param formatStr
	 *            需要格式的目标字符串 举例 yyyy-MM-dd * @return Date 返回转换后的时间 * @throws
	 *            ParseException 转换异常
	 */
	public static Date stringToDate(String dateStr, String formatStr) {
		DateFormat sdf = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 时间转字符串
	 * 
	 * @param dateStr
	 *            需要转换的时间类型
	 * @param formatStr
	 *            需要格式的目标字符串 举例 yyyy-MM-dd * @return Date 返回转换后的时间 * @throws
	 *            ParseException 转换异常
	 */
	public static String dateToString(Date date, String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		String str = sdf.format(date);
		return str;
	}
	
	/**
	 * 获取上周周日
	 * 
	 * @return
	 */
	public static String getMondayBeforeWeek() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sd.parse(sd.format(new Date())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		String time = LSDate.dateToString(cal.getTime(), DATE_FORMAT);
		return time;
	}

	/**
	 * 获取上周周日
	 * 
	 * @return
	 */
	public static String getSunDayBeforeWeek() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sd.parse(sd.format(new Date())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String time = LSDate.dateToString(cal.getTime(), DATE_FORMAT);
		return time;
	}

	/**
	 * 获取本周周日
	 * 
	 * @return
	 */
	public static String getSunDayThisWeek() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sd.parse(sd.format(new Date())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		String time = LSDate.dateToString(cal.getTime(), DATE_FORMAT);
		return time;
	}
	
	/**
	 * 通过当前时刻，获取周日23:59:59毫秒数
	 * @return
	 */
	public static long getSunDayThisWeekByLong() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sd.parse(sd.format(new Date())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);	
		long a1=cal.getTimeInMillis();
		long a2=a1+24*60*60*1000-1;//减1才是最后一秒
		return a2;
	}

	/**
	 * 获取当月的第一天日期
	 * 
	 * @param date
	 * @return
	 */
	public static String getFirstDayOfMonth(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(LSDate.stringToDate(date, DATE_FORMAT));
		// cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String time = LSDate.dateToString(cal.getTime(), DATE_FORMAT);
		return time;
	}

	public static String getDefaultTime(Date date, int field, int amount) {
		Calendar cal = Calendar.getInstance();
		String time;
		cal.setTime(date);
		switch (field) {
		case LSDate.DATE:
			cal.add(Calendar.DATE, amount);
			time = LSDate.dateToString(cal.getTime(), LSDate.DATE_FORMAT);
			break;

		case LSDate.MONTH:
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.add(Calendar.MONTH, amount);
			time = LSDate.dateToString(cal.getTime(), LSDate.DATE_MONTH);
			break;
		case LSDate.WEEK:
			cal.add(Calendar.DATE, -cal.get(Calendar.DAY_OF_WEEK) + amount);

			time = LSDate.dateToString(cal.getTime(), LSDate.DATE_FORMAT);
			break;
		case LSDate.ENDDASTEWEEK:
			cal.add(Calendar.DATE, -cal.get(Calendar.DAY_OF_WEEK));
			cal.add(Calendar.DATE, 7 * amount + 1);
			time = LSDate.dateToString(cal.getTime(), LSDate.DATE_FORMAT);
			break;
		default:
			return null;
		}
		return time;
	}

	/**
	 * 获取当月的第一天日期
	 * 
	 * @param date
	 * @return
	 */
	public static String getlastDayOfMonth(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(LSDate.stringToDate(date, DATE_FORMAT));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		return LSDate.dateToString(cal.getTime(), LSDate.DATE_FORMAT);
	}

	/**
	 * 获取当前是一天的多少分钟
	 * 
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getMinute() {
		java.sql.Timestamp time = new java.sql.Timestamp(
				new java.util.Date().getTime());

		return time.getHours() * 60 + time.getMinutes();

	}

	/**
	 * 获取给定天的给定小时的时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDingTianDian(Date d, int h) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MINUTE, -1 * getMinute());
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.HOUR, h);
		d = cal.getTime();
		return d;

	}

	public static Date getDateByM(Date d) {
		return stringToDate(dateToString(d, "yyyy-MM-dd"), "yyyy-MM-dd");

	}

	public static Date getDateByMH(Date d) {
		return stringToDate(dateToString(d, "yyyy-MM-dd HH"), "yyyy-MM-dd HH");

	}

	public static Date getDateByMHM(Date d) {
		return stringToDate(dateToString(d, "yyyy-MM-dd HH:mm"),
				"yyyy-MM-dd HH:mm");

	}

	public static int getDateCha(Date d, Date d1) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(d);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(d1);
		long l = cal1.getTimeInMillis() - cal2.getTimeInMillis();
		return new Long(l / (1000 * 60 * 60 * 24)).intValue();

	}

	public static Date getAddMinute(Date date, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minute);
		date = cal.getTime();
		return date;

	}

	public static Date getAddHour(Date date, int hour) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hour);
		date = cal.getTime();
		return date;

	}

	/**
	 * 今天星期几
	 */
	public static int getWeekToDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;

	}

	/**
	 * 是否是今天
	 */
	public static boolean isToDay(long time){
		Calendar today=Calendar.getInstance();
		Calendar target = Calendar.getInstance();
		target.setTimeInMillis(time);
		return sameDay(today,target);
	}
	
	/**
	 * 是否是昨天
	 */
	public static boolean isYesterday(long time){
		Calendar c1=Calendar.getInstance();
		c1.add(Calendar.DAY_OF_YEAR, -1);
		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(time);
		return sameDay(c1,c2);
	}
	
	/**
	 * 是否是同一天
	 */
	public static boolean sameDay(long time1,long time2){
		Calendar c1=Calendar.getInstance();
		c1.setTimeInMillis(time1);
		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(time2);
		return sameDay(c1,c2);
	}
	
	/**
	 * 是否是明天
	 */
	public static boolean isTommrow(long time){
		Calendar c1=Calendar.getInstance();
		c1.add(Calendar.DAY_OF_YEAR, 1);
		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(time);
		return sameDay(c1,c2);
	}
	
	private static boolean sameDay(Calendar c1,Calendar c2){
		return (c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR)) && (c1.get(Calendar.DAY_OF_YEAR)==c2.get(Calendar.DAY_OF_YEAR));
	}

	public static int getYearNow(Calendar _CalNow) {
		return _CalNow.get(Calendar.YEAR);
	}

	public static int getMonthNow(Calendar _CalNow) {
		return _CalNow.get(Calendar.MONTH) + 1;
	}

	public static int getDayNow(Calendar _CalNow) {
		return _CalNow.get(Calendar.DATE);
	}
}

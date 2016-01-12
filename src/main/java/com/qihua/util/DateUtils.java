package com.qihua.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;

/**
 * Class description goes here.
 * 
 * @author zhen.ni@ebaotech.com
 * @since 2013-10-11
 * @version 1.0
 * @see
 */
public class DateUtils {

	public static final String DELIMITER = "-";

	public static final String YEAR = "yyyy";
	public static final String MONTH = "MM";
	public static final String DAY = "dd";
	public static final String DATE_DEFAULT = YEAR + DELIMITER + MONTH + DELIMITER + DAY;
	public static final String TIME_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATA_YYYYMMDD = "yyyyMMdd";
	public static final String TIME_HHMMSS = "HH:mm:ss";

	public static final int ONE_SECOND = 1000;
	public static final int ONE_MINUTE = 60 * ONE_SECOND;
	public static final int ONE_HOUR = 60 * ONE_MINUTE;
	public static final long ONE_DAY = 24 * ONE_HOUR;

	public static String toString(String format) {
		return new DateTime().toString(format);
	}

	public static String currentYear() {
		return toString(YEAR);
	}

	public static String currentMonth() {
		return toString(MONTH);
	}

	public static String currentDay() {
		return toString(DAY);
	}

	public static String currentDate() {
		return toString(DATE_DEFAULT);
	}

	public static String currentTime() {
		return toString(TIME_DEFAULT);
	}

	public static String toString(java.sql.Date date) {
		DateFormat df = new SimpleDateFormat(DATE_DEFAULT);

		return df.format(date);
	}

	public static String toString(java.sql.Date date, String fmt) {
		SimpleDateFormat df = new SimpleDateFormat(fmt/* , Locale.CHINA */);

		return df.format(date);
	}

	public static void main(String[] args) {
		// System.out.println(toString(new java.sql.Date(new Date().getTime()),
		// "E"));
	}

	public static String getDate(int period) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + period);

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);

		String cDate = Integer.toString(year);

		if (month < 10) {
			cDate = cDate + "-0" + Integer.toString(month);
		} else {
			cDate = cDate + "-" + Integer.toString(month);
		}

		if (day < 10) {
			cDate = cDate + "-0" + Integer.toString(day);
		} else {
			cDate = cDate + "-" + Integer.toString(day);
		}

		return cDate.trim();
	}

	public static String getCurrentDate() {
		return getDate(0);
	}

	public static String getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);

		String cDate = "";

		if (hour < 10) {
			cDate = cDate + " 0" + Integer.toString(hour);
		} else {
			cDate = cDate + " " + Integer.toString(hour);
		}

		if (minute < 10) {
			cDate = cDate + ":0" + Integer.toString(minute);
		} else {
			cDate = cDate + ":" + Integer.toString(minute);
		}

		if (second < 10) {
			cDate = cDate + ":0" + Integer.toString(second);
		} else {
			cDate = cDate + ":" + Integer.toString(second);
		}

		return cDate.trim();
	}

	public static String getCurrentFullTime() {
		return getCurrentDate() + " " + getCurrentTime();
	}

	public static Calendar toCalendar(String str) {
		Calendar cal = new GregorianCalendar();
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(str);
			cal.setTime(date);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cal;
	}

	public static Calendar addMonth(Calendar cal, int period) {
		Calendar cal2 = cal;
		cal2.set(Calendar.MONTH, cal.get(Calendar.MONTH) + period);

		return cal;
	}

	public static String printCalender(Calendar cal) {
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);

		String str = "";

		if (hour < 10) {
			str = str + " 0" + Integer.toString(hour);
		} else {
			str = str + " " + Integer.toString(hour);
		}

		if (minute < 10) {
			str = str + ":0" + Integer.toString(minute);
		} else {
			str = str + ":" + Integer.toString(minute);
		}

		if (second < 10) {
			str = str + ":0" + Integer.toString(second);
		} else {
			str = str + ":" + Integer.toString(second);
		}

		return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH) + " " + str;
	}

	/**
	 * 提取字符串的日期，只支持首个符合格式的字符串。
	 * 
	 * @param text
	 *            格式：2014-12-01, 2014/12/01
	 * @return
	 */
	public static String parseDate(String text) {
		String regex = "(\\d{4}[-|\\/]\\d{1,2}[-|\\/]\\d{1,2})";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(text);
		while (m.find()) {
			return m.group();
		}

		return "";
	}

	/**
	 * 提取字符串的日期和时间，只支持首个符合格式的字符串。
	 * 
	 * @param text
	 *            格式：2014-12-01 15:00:00, 2014/12/01 15:00:00
	 * @return
	 */
	public static String parseDateTime(String text) {
		String regex = "(\\d{4}[-|\\/]\\d{1,2}[-|\\/]\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2})";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(text);
		while (m.find()) {
			return m.group();
		}

		return "";
	}

	/**
	 * 提取字符串的时间，只支持首个符合格式的字符串。
	 * 
	 * @param text
	 *            格式：15:00:00
	 * @return
	 */
	public static String parseTime(String text) {
		String regex = "(\\d{1,2}:\\d{1,2}:\\d{1,2})";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(text);
		while (m.find()) {
			return m.group();
		}

		return "";
	}

	public static Date parseShadowDateTime(String dateTime) throws ParseException {
		if (dateTime.contains("-") && dateTime.contains(":")) {
			try {
				return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateTime);
			} catch (Exception e) {
				return new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dateTime);
			}
		} else if (dateTime.contains("/") && dateTime.contains(":")) {
			try {
				return new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse(dateTime);
			} catch (Exception e) {
				return new SimpleDateFormat("yyyy/MM/dd hh:mm").parse(dateTime);
			}
		} else if (dateTime.contains("-")) {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dateTime);
		} else if (dateTime.contains("/")) {
			return new SimpleDateFormat("yyyy/MM/dd").parse(dateTime);
		}

		throw new ParseException("解析异常：异常数据: " + dateTime, 1);
	}
}

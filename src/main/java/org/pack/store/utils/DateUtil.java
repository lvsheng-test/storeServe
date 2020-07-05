package org.pack.store.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {

	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	

	public static final long HOUR_MILS = 60 * 60 * 1000L;

	public static final long DAY_MILS = 24 * 60 * 60 * 1000L;

	// 相差的时差 (东 8 区)
	public static final long MIS_TIMING_MILS = 8 * HOUR_MILS;

	// 以 0:00 为基准 的当日 计算差量
	public static long DAY_MILS_DIFF = MIS_TIMING_MILS;

	/**
	 * 日期时间格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String dateTimeString = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期格式 yyyy-MM-dd
	 */
	public static String dateString = "yyyy-MM-dd";

	/**
	 * 日期时间格式For 文件名 yyyy_MM_dd_HH_mm_ss
	 */

	/**
	 * 日
	 */
	public final static int INTERVAL_DAY = 1;

	/**
	 * 周
	 */
	public final static int INTERVAL_WEEK = 2;

	/**
	 * 月
	 */
	public final static int INTERVAL_MONTH = 3;

	/**
	 * 年
	 */
	public final static int INTERVAL_YEAR = 4;

	/**
	 * 小时
	 */
	public final static int INTERVAL_HOUR = 5;

	/**
	 * 分钟
	 */
	public final static int INTERVAL_MINUTE = 6;

	/**
	 * 秒
	 */
	public final static int INTERVAL_SECOND = 7;

	// private static final Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);

	/**
	 * 解决日期字符串,日期格式为： yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parseStr(String dateStr) {
		if (StringUtil.isNullStr(dateStr)) {
			return null;
		}
		return parseStr(dateStr, dateTimeString);
	}

	/**
	 * 解决日期字符串
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date parseStr(String dateStr, String pattern) {
		if (StringUtil.isNullStr(dateStr)) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Date resultDate = null;
		try {
			resultDate = df.parse(dateStr);
		}
		catch (ParseException e) {
			// logger.error("日期解析错误,dateStr:"+dateStr,e);
			resultDate = null;
		}
		return resultDate;
	}

	/**
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (date == null || pattern == null) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		String result = df.format(date);
		if (result.equalsIgnoreCase("0001-01-01 00:00:00")) {
			result = "";
		}
		return result;
	}

	/**
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date) {
		return format(date, dateTimeString);
	}

	/**
	 * 两date比较
	 * 
	 * @param beforeDate
	 * @param afterDate
	 * @return
	 */
	public static int compareDate(Date beforeDate, Date afterDate) {
		Calendar beforeCalendar = Calendar.getInstance();
		Calendar afterCalendar = Calendar.getInstance();
		beforeCalendar.setTime(beforeDate);
		afterCalendar.setTime(afterDate);
		return beforeCalendar.compareTo(afterCalendar);
	}

	/**
	 * 判断目标日期是否在时间段类
	 * 
	 * @param beforeDate
	 * @param afterDate
	 * @param targetDate
	 * @return
	 */
	public static boolean isBetweenDate(Date beforeDate, Date afterDate, Date targetDate) {
		if (targetDate == null) {
			throw new RuntimeException("targetDate should not be null!");
		}
		if (beforeDate == null && afterDate == null) {
			return false;
		}
		if (afterDate == null) {
			return (compareDate(beforeDate, targetDate) <= 0);
		}
		if (beforeDate == null) {
			return (compareDate(targetDate, afterDate) <= 0);
		}
		return (compareDate(beforeDate, targetDate) <= 0) && (compareDate(targetDate, afterDate) <= 0);
	}


	/**
	 * 判断 是否为同日，根据getDayNums规则。
	 * 
	 * @param dateMils1
	 * @param dateMils2
	 * @return
	 */
	public static final boolean bSameDay(long dateMils1, long dateMils2) {
		return getDayNums(dateMils1) == getDayNums(dateMils2);
	}

	/**
	 * 获得从1970-01-01 09:30:00 到现在的天数 (9:30 - 9:30)
	 * 
	 * @param mils 当前时间的毫秒数
	 * @return
	 */
	public static long getDayNums(long mils) {
		return (mils + DAY_MILS_DIFF) / DAY_MILS;
	}

	/**
	 * 获得所在天的 毫秒数字 (根据MIS_TIMING_MILS的不同来判断基于几点钟，MIS_TIMING_MILS=0为00:00:00)
	 * 
	 * @param mils
	 * @return
	 */
	public static long getDayTimeMils(long mils) {
		mils = mils % DAY_MILS;

		if (mils < 0) {
			mils = DAY_MILS + mils;
		}
		mils += MIS_TIMING_MILS;
		if (mils >= DAY_MILS) {
			mils -= DAY_MILS;
		}

		return mils;
	}

	/**
	 * 获取系统时间 YYYY-mm-DD HH:mm:ss 格式
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateYMDHMs(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		try {
			d = sdf.parse(sdf.format(date));
		}
		catch (ParseException e) {
			logger.error("====>时间转换异常", e);
		}
		return d;
	}

	/**
	 * 获取系统时间 YYYY-mm-DD格式
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateYMD(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse(sdf.format(date));
		}
		catch (ParseException e) {
			logger.error("====>时间转换异常", e);
		}
		return d;
	}

	/**
	 * yesterday<br>
	 * 获取n日前的yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String dateYYYYMMDD(int n) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, n);
		String date2 = sdf.format(calendar.getTime());
		return date2;
	}
}

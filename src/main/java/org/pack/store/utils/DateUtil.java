package org.pack.store.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.alibaba.fastjson.JSONObject;
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
	 * @return
	 */
	public static String format(Date date) {
		return format(date, dateTimeString);
	}

	/**
	 * @param date
	 * @return
	 */
	public static String systemFormat(Date date) {
		return format(date, dateString);
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

	/**
	 * 判断当前时间距离第二天凌晨的时间
	 *
	 * @return 返回单位为[s:秒]
	 */
	public static Long getNextDateSeconds() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
	}

	public static String getSystmeTimeOldTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 1);
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(calendar.getTime());
	}

	public static String getSystmeTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 20);
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(calendar.getTime());
	}


	/**
	 * 系统当前时间
	 * @param time  HH:mm
	 * @return
	 */
	public static JSONObject getSelfTime(String time){
		JSONObject timeJson =new JSONObject();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sysTime = sdf.format(new Date());
		System.out.print("系统当前日期:"+sysTime);
		String [] arrTime={"00:00","00:20","00:40","01:00","01:20","01:40","02:00","02:20","02:40","03:00","03:20","03:40","04:00","04:20","04:40","05:00"
		,"05:20","05:40","06:00","06:20","06:40","07:00","07:20","07:40","08:00","08:20","08:40","09:00","09:20","09:40","10:00","10:20","10:40","11:00"
		,"11:20","11:40","12:00","12:20","12:40","13:00","13:20","13:40","14:00","14:20","14:40","15:00","15:20","15:40","16:00","16:20","16:40","17:00","17:20"
		,"17:40","18:00","18:20","18:40","19:00","19:20","19:40","20:00","20:20","20:40","21:00","21:20","21:40","22:00","22:20","22:40","23:00","23:20","23:40"
		,"24:00"};
		try{
			Date sysDate = df.parse(sysTime+" "+time);
			timeJson.put(1+"",time);
			for (int i=0;i<arrTime.length;i++){
				Date newDate = df.parse(sysTime+" "+arrTime[i]);
				if (sysDate.before(newDate)){
					timeJson.put(i+"",arrTime[i]);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.print(timeJson.toJSONString());
		return timeJson;
	}


	public static void main(String[] args) {
		//System.out.println(UuidUtil.getUuid());
		System.out.println("获取下一个月日期：" + getSelfTime("15:50"));

		System.out.print("日期："+systemFormat(new Date()));
	}
}

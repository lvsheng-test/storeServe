package org.pack.store.utils.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {


    public static void main(String[] args) {
        //System.out.println(UuidUtil.getUuid());
        System.out.println("获取下一个月日期：" + getSystimeAfterMonth(24));
    }

    /**
     * 获取当前系统后N个月
     * @param a
     * @return
     */
    public static Date getSystimeAfterMonth(int a){
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + a); // 设置为上一个月      +为后一个月  0 为本月
        date = calendar.getTime();
        return date;
    }


    public static String nextMonth(int a){
        Date date = new Date();
        int year=Integer.parseInt(new SimpleDateFormat("yyyy").format(date));//取到年份值
        int month=Integer.parseInt(new SimpleDateFormat("MM").format(date))+a;//取到鱼粉值
        int day=Integer.parseInt(new SimpleDateFormat("dd").format(date));//取到天值
        if(month==0){
            year-=1;month=12;
        }
        else if(day>28){
            if(month==2){
                if(year%400==0||(year %4==0&&year%100!=0)){
                    day=29;
                }else day=28;
            }else if((month==4||month==6||month==9||month==11)&&day==31)
            {
                day=30;
            }
        }
        String y = year+"";String m ="";String d ="";
        if(month<10) m = "0"+month;
        else m=month+"";
        if(day<10) d = "0"+day;
        else d = day+"";
        System.out.println(y+"-"+m+"-"+d);
        return y+"-"+m+"-"+d;
    }
}

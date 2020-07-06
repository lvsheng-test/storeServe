package org.pack.store.utils;

import org.pack.store.utils.common.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class IDGenerateUtil {

    private final static String key = "tab_store";

    @Autowired
    private RedisUtil redisUtil;


    public String getId(String key){
        String date = DateUtil.dateYYYYMMDD(0) + "00000000";
        long oid = Long.parseLong(date);
        if(StringUtil.isNullStr(key)) {
            key = IDGenerateUtil.key;
            long hincr = redisUtil.incr(key);
            oid = oid + hincr;
        }else{
            long hincr = redisUtil.incr(key);
            oid = oid + hincr;
        }
        int secondsNextEarlyMorning = getSecondsNextEarlyMorning().intValue();
        redisUtil.expire(key,secondsNextEarlyMorning);
        return String.valueOf(oid);
    }

    public String getId() {
       return getId(key);
    }
    /**
     * 判断当前时间距离第二天凌晨的秒数
     *
     * @return 返回值单位为[s:秒]
     */
    public static Long getSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }

    public static void main(String[] args) {
        Long secondsNextEarlyMorning = getSecondsNextEarlyMorning();
        System.out.println(secondsNextEarlyMorning);
    }
}

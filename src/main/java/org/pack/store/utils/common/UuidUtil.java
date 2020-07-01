package org.pack.store.utils.common;

import java.util.Random;
import java.util.UUID;

public class UuidUtil {  
    public static String getUuid(){  
        UUID uuid = UUID.randomUUID();  
        String uuidStr =  uuid.toString();  
        uuidStr = uuidStr.replace("-", "");  
        return uuidStr;  
    }

    /**
     * java生成随机数字和字母组合10位数
     * @param
     * @return
     */
    public static String getDictCode(){
        String val = "";
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            // 输出字母还是数字
            val += String.valueOf(random.nextInt(10));
        }
        return "ZD10000"+val;
    }

    public static String getThreeDigits(int rangeNum) {

        Integer num = new Random().nextInt(rangeNum) + 1;
        String str = num.toString();
        while (str.length() < 3) {

            str = "0" + str;
        }
        return str;
    }
    public static void main(String[] args) {  
        //System.out.println(UuidUtil.getUuid());
        System.out.println("java生成随机数字和字母组合10位数：" + getDictCode());
    }  
  
}
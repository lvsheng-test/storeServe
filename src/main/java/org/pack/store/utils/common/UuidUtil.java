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


    public static String getThreeDigits(int rangeNum) {

        Integer num = new Random().nextInt(rangeNum) + 1;
        String str = num.toString();
        while (str.length() < 3) {

            str = "0" + str;
        }
        return str;
    }
    public static void main(String[] args) {  
        System.out.println(UuidUtil.getUuid());   
    }  
  
}
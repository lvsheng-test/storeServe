package org.pack.store.utils.common;

import java.math.BigDecimal;

public class BigDecimalUtil {

    /**
     * 加法：add
     *
     * 减法：subtract
     *
     * 乘法：multiply
     *
     * 除法：divide
     *
     */

    public static BigDecimal multiply(BigDecimal bigDecimal1,long bigDecimal2){
        BigDecimal decimal=new BigDecimal(bigDecimal2);
         return bigDecimal1.multiply(decimal);
    }

    public static BigDecimal add(BigDecimal bigDecimal1,BigDecimal bigDecimal2){
        return bigDecimal1.add(bigDecimal2);
    }

    public static BigDecimal multiply(BigDecimal bigDecimal1,BigDecimal bigDecimal2){
        return bigDecimal1.multiply(bigDecimal2);
    }

    public static BigDecimal divide(BigDecimal bigDecimal1,BigDecimal bigDecimal2){
        return bigDecimal1.divide(bigDecimal2);
    }


}

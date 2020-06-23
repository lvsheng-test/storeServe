package org.pack.store.utils.common;

import java.math.BigDecimal;

public class BigDecimalUtil {


    public static BigDecimal multiply(BigDecimal bigDecimal1,long bigDecimal2){
        BigDecimal decimal=new BigDecimal(bigDecimal2);
         return bigDecimal1.multiply(decimal);
    }
}

package org.pack.store.utils.HttpClient;

import org.pack.store.utils.common.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class HttpApiUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpApiUtils.class);

    private static String recEncoding = "UTF-8";

    private static final String OPEN_LOCK = "http://139.196.194.172:8082/MidComPro/lock?action=OPENLOCK";

    private static final String GET_BIKE_INFOS = "http://139.196.194.172:8084/LockApi/lock?action=GETBIKEINFOS";

    @Autowired
    private RedisUtil redisUtil;

    public static String getRecEncoding() {
        return recEncoding;
    }

    public static void setRecEncoding(String recEncoding) {
        HttpApiUtils.recEncoding = recEncoding;
    }

    public static String getBikeInfos(Map params) {
        return HttpRequestProxy.doPost(GET_BIKE_INFOS, params, getRecEncoding());
    }

    public static String openLockByGprs(Map params) {
        return HttpRequestProxy.doPost(OPEN_LOCK, params, getRecEncoding());
    }

    /**
     * 微信支付请求
     *
     * @param params
     * @return
     */
    public static String sendRequest(String params) {
        return HttpsUtil.httpMethodPost("https://api.mch.weixin.qq.com/pay/unifiedorder", params);
    }

    public static String openLockBySms(Map params) {
        String url = "http://106.14.155.161/SMSComPro/Sms?action=SENDSMSUNLOCK";
        return HttpRequestProxy.doPost(url, params, getRecEncoding());
    }
}

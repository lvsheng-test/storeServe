package org.pack.store.common;

public class WXConst {

    //微信小程序appid
    public static String appId = "";
    //微信小程序appsecret
    public static String appSecret = "";
    //微信支付主体
    public static String title = "";
    public static String orderNo = "";
    //微信商户号
    public static String mch_id="";
    //微信支付的商户密钥
    public static final String key = "";
    //获取微信Openid的请求地址
    public static String WxGetOpenIdUrl = "";
    //支付成功后的服务器回调url
    public static final String notify_url="https://api.weixin.qq.com/sns/jscode2session";
    //签名方式
    public static final String SIGNTYPE = "MD5";
    //交易类型
    public static final String TRADETYPE = "JSAPI";
    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

}

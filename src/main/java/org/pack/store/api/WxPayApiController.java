package org.pack.store.api;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.pack.store.common.WXConst;
import org.pack.store.utils.PayUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 提供小程序API
 */
/*@Api(value="WxPayApiController",tags={"小程序微信支付操作API"})
@RestController
@RequestMapping(value = "/wxPayApi")*/
public class WxPayApiController {

    private static Logger logger = Logger.getLogger(WxPayApiController.class);

    public JSONObject wxPay(String openid, HttpServletRequest request){
        JSONObject json = new JSONObject();
        try{
            //生成的随机字符串
            String nonce_str = PayUtils.getRandomStringByLength(32);
            //商品名称
            String body = new String(WXConst.title.getBytes("ISO-8859-1"),"UTF-8");
            //获取本机的ip地址
            String spbill_create_ip = PayUtils.getIpAddr(request);
            String orderNo = WXConst.orderNo;
            String money = "1";//支付金额，单位：分，这边需要转成字符串类型，否则后面的签名会失败

            Map<String, String> packageParams = new HashMap<String, String>();
            packageParams.put("appid", WXConst.appId);
            packageParams.put("mch_id", WXConst.mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("body", body);
            packageParams.put("out_trade_no", orderNo);//商户订单号
            packageParams.put("total_fee", money);
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url", WXConst.notify_url);
            packageParams.put("trade_type", WXConst.TRADETYPE);
            packageParams.put("openid", openid);

            // 除去数组中的空值和签名参数
            packageParams = PayUtils.paraFilter(packageParams);
            String prestr = PayUtils.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串

            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String mysign = PayUtils.sign(prestr, WXConst.key, "utf-8").toUpperCase();
            logger.info("=======================第一次签名：" + mysign + "=====================");
            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml version='1.0' encoding='gbk'>" + "<appid>" + WXConst.appId + "</appid>"
                    + "<body><![CDATA[" + body + "]]></body>"
                    + "<mch_id>" + WXConst.mch_id + "</mch_id>"
                    + "<nonce_str>" + nonce_str + "</nonce_str>"
                    + "<notify_url>" + WXConst.notify_url + "</notify_url>"
                    + "<openid>" + openid + "</openid>"
                    + "<out_trade_no>" + orderNo + "</out_trade_no>"
                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                    + "<total_fee>" + money + "</total_fee>"
                    + "<trade_type>" + WXConst.TRADETYPE + "</trade_type>"
                    + "<sign>" + mysign + "</sign>"
                    + "</xml>";
            System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);
            //调用统一下单接口，并接受返回的结果
            String result = PayUtils.httpRequest(WXConst.pay_url, "POST", xml);
            System.out.println("调试模式_统一下单接口 返回XML数据：" + result);
            // 将解析结果存储在HashMap中
            Map map = PayUtils.doXMLParse(result);
            String return_code = (String) map.get("return_code");//返回状态码
            //返回给移动端需要的参数
            Map<String, Object> response = new HashMap<String, Object>();
            if(return_code == "SUCCESS" || return_code.equals(return_code)){
                // 业务结果
                String prepay_id = (String) map.get("prepay_id");//返回的预付单信息
                response.put("nonceStr", nonce_str);
                response.put("package", "prepay_id=" + prepay_id);
                Long timeStamp = System.currentTimeMillis() / 1000;
                response.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
                String stringSignTemp = "appId=" + WXConst.appId + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id+ "&signType=" + WXConst.SIGNTYPE + "&timeStamp=" + timeStamp;
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                String paySign = PayUtils.sign(stringSignTemp, WXConst.key, "utf-8").toUpperCase();
                logger.info("=======================第二次签名：" + paySign + "=====================");
                response.put("paySign", paySign);
                //更新订单信息
                //业务逻辑代码
            }
            response.put("appid", WXConst.appId);
            json.put("errMsg", "OK");
            //json.setSuccess(true);
            json.put("data", response);
            //json.setData(response);
        }catch (Exception e){
            e.printStackTrace();
            json.put("errMsg", "Failed");
            //json.setSuccess(false);
            //json.setMsg("发起失败");
        }
        return json;
    }
}

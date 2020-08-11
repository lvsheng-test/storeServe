package org.pack.store.api.impl;


import com.alibaba.fastjson.JSONObject;
import org.pack.store.api.UserService;
import org.pack.store.autoconf.DataConfig;
import org.pack.store.autoconf.JedisOperator;
import org.pack.store.mapper.MemberMapper;
import org.pack.store.requestVo.AppVO;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.HttpClient.HttpsUtil;
import org.pack.store.utils.IDGenerateUtil;
import org.pack.store.utils.ResultUtil;
import org.pack.store.utils.WXBizDataCrypt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserServiceImpl implements UserService {

    private static final String LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @Resource
    private MemberMapper memberMapper;
    @Resource
    private DataConfig dataConfig;
    @Resource
    private JedisOperator jedisOperator;
    @Resource
    private IDGenerateUtil idGenerateUtil;
    /**
     * login
     *
     * @param {"code":"微信code","encryptedData":"从微信获取","iv":"从微信获取","nickName":"微信昵称"}
     */
    @Override
    public AppletResult login(@RequestBody AppVO<JSONObject> jsonObject) {
        JSONObject data = jsonObject.getData();
        if(null == data){
            return ResultUtil.error(-1,"缺失参数");
        }
        int code = data.getIntValue("code");
        String appId = dataConfig.getAppId();
        String appSecret = dataConfig.getAppSecret();
        String encryptedData = data.getString("encryptedData");
        String iv = data.getString("iv");
        String nickName = data.getString("nickName");
        String result = HttpsUtil.httpMethodGet(String.format(LOGIN_URL, appId, appSecret, code));
        JSONObject jsonObj = JSONObject.parseObject(result);
        String openId = jsonObj.getString("openid");
        String sessionKey = jsonObj.getString("session_key");
        jedisOperator.setex(sessionKey,openId,60 * 60 * 24 * 30);
        //查用户是否存在
        JSONObject queryMember = memberMapper.queryMember(openId);
        if(null == queryMember){
            //插入用户表
            JSONObject insertJson  = new JSONObject();
            insertJson.put("userId", idGenerateUtil.getId());
            insertJson.put("openId", openId);
            String str = WXBizDataCrypt.decryptData(sessionKey, appId, encryptedData, iv);
            JSONObject json = JSONObject.parseObject(str);
            insertJson.put("mobile", json.getString("purePhoneNumber"));
            insertJson.put("nickName", nickName);
            memberMapper.insertMember(insertJson);
        }
        return ResultUtil.success(queryMember);
    }
}

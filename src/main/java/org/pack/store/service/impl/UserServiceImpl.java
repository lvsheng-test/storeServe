package org.pack.store.service.impl;


import com.alibaba.fastjson.JSONObject;
import org.pack.store.autoconf.DataConfig;
import org.pack.store.autoconf.JedisOperator;
import org.pack.store.entity.AddressEntity;
import org.pack.store.entity.MembershipEntity;
import org.pack.store.enums.ResultEnums;
import org.pack.store.mapper.*;
import org.pack.store.requestVo.*;
import org.pack.store.resposeVo.CityInfoRes;
import org.pack.store.service.UserService;
import org.pack.store.utils.*;
import org.pack.store.utils.HttpClient.HttpsUtil;
import org.pack.store.utils.common.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final String LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private MembershipMapper membershipMapper;

    @Resource
    private MemberMapper memberMapper;
    @Resource
    private DataConfig dataConfig;
    @Resource
    private JedisOperator jedisOperator;
    @Resource
    private IDGenerateUtil idGenerateUtil;

    @Resource
    private UserVipMapper userVipMapper;

    @Resource
    private CommissionDetailsMapper commissionDetailsMapper;

    @Resource
    private CashRecordsMapper cashRecordsMapper;

    public AppletResult queryMyAddress(String userId){
        if (StringUtil.isNullStr(userId)){
            return ResultUtil.error(ResultEnums.USERID_IS_NULL);
        }
        List<AddressEntity> addressList = addressMapper.queryMyAddress(userId);
        return ResultUtil.success(addressList);
    }

    public AppletResult updateMyAddress(AddressReq addressReq){
        try{
            if (StringUtil.isNullStr(addressReq.getId())){
                return ResultUtil.error(ResultEnums.ID_IS_NULL);
            }
            if (StringUtil.isNullStr(addressReq.getUserId())){
                return ResultUtil.error(ResultEnums.USERID_IS_NULL);
            }
            AddressEntity addressInfo = addressMapper.getById(addressReq.getId());
            if (addressInfo ==null){
                return ResultUtil.error(ResultEnums.NOT_FOUND_DATA);
            }
            //修改之前先校验本次是否修改默认地址，如果设置默认地址，先把原来默认地址取消掉，本次设置默认地址
            if (!StringUtil.isNullStr(addressReq.getDefaultAddress())){
                if (addressReq.getDefaultAddress()=='Y'){
                    AddressEntity address = addressMapper.queryAdressByUserIdAndDefalust(addressReq.getUserId());
                    if (address !=null){
                        address.setDefaultAddress('N');
                        addressMapper.updateAddress(address);
                    }
                }
            }
            addressMapper.updateMyAddress(addressReq);
        }catch (Exception e){
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
        return ResultUtil.success();
    }

    public AppletResult insertMyAddress(AddressReq addressReq){
        if (StringUtil.isNullStr(addressReq.getUserId())){
            return ResultUtil.error(ResultEnums.USERID_IS_NULL);
        }
        if (!StringUtil.isNullStr(addressReq.getDefaultAddress())){
            if (addressReq.getDefaultAddress()=='Y'){
                AddressEntity address = addressMapper.queryAdressByUserIdAndDefalust(addressReq.getUserId());
                if (address !=null){
                    address.setDefaultAddress('N');
                    addressMapper.updateAddress(address);
                }
            }
        }
        AddressEntity addressInfo =new AddressEntity();
        addressInfo.setId(UuidUtil.getUuid());
        addressInfo.setUserId(addressReq.getUserId());
        addressInfo.setContact(addressReq.getContact());
        addressInfo.setSex(addressReq.getSex());
        addressInfo.setPhone(addressReq.getPhone());
        addressInfo.setCityCode(addressReq.getCityCode());
        addressInfo.setCityName(addressReq.getCityName());
        addressInfo.setAddress(addressReq.getAddress());
        addressInfo.setDefaultAddress(addressReq.getDefaultAddress());
        addressInfo.setTs(new Date());
        addressMapper.insertMyAddress(addressInfo);
        return ResultUtil.success();
    }

    public AppletResult queryCommonDict(ParentCodeReq parentCodeReq){
        List<CityInfoRes> list = dictMapper.queryCommonParentCode(parentCodeReq.getParentCode());
        return ResultUtil.success(list);
    }
    //绑定会员卡操作
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public AppletResult bindingMembership(BindMemberReq bindMemberReq){
        MembershipEntity membership =new MembershipEntity();
        try {
            if (StringUtil.isNullStr(bindMemberReq.getUserId())){
                return ResultUtil.error(ResultEnums.PARAM_IS_NULL);
            }
            if (StringUtil.isNullStr(bindMemberReq.getMemberType())){
                return ResultUtil.error(ResultEnums.PARAM_IS_NULL);
            }
            if (StringUtil.isNullStr(bindMemberReq.getCardNo())){
                return ResultUtil.error(ResultEnums.PARAM_IS_NULL);
            }
            if (StringUtil.isNullStr(bindMemberReq.getMobile())){
                return ResultUtil.error(ResultEnums.PARAM_IS_NULL);
            }
            membership.setMobile(bindMemberReq.getMobile());
            membership.setMemberType(bindMemberReq.getMemberType());
            membership.setCardNo(bindMemberReq.getCardNo());
            MembershipEntity memberships = membershipMapper.getMemberTypeAndCardNo(membership);
            //校验一下改手机号是否开通会员卡
            if (memberships ==null){//未开通会员卡
                return ResultUtil.error(ResultEnums.MOILE_OPEN_NO);
            }
            //校验一下该用户有没有重复绑定会员卡，然后校验用户只允许绑定每种类型卡一张
            JSONObject object = userVipMapper.queryBindingMember(bindMemberReq);
            if (object !=null){//该用户已绑定过此类型会员卡
                return ResultUtil.error(ResultEnums.BIND_MEMBER_YES);
            }
            //校验该用户申请绑定的会员卡，是否被其他人绑定过
            JSONObject json = userVipMapper.queryIsBinding(bindMemberReq);
            if (json !=null){
                return ResultUtil.error(ResultEnums.IS_BIND_MEMBER_YES);
            }
            //校验通过完成绑定会员卡操作
            JSONObject jsonObject =new JSONObject();
            jsonObject.put("id",UuidUtil.getUuid());
            jsonObject.put("userId",bindMemberReq.getUserId());
            jsonObject.put("memberId",memberships.getId());
            jsonObject.put("vipType",memberships.getMemberType());
            jsonObject.put("vipName",memberships.getMemberName());
            jsonObject.put("cardNo",bindMemberReq.getCardNo());
            int i = userVipMapper.addBindingMember(jsonObject);
            if(i==0){
                return ResultUtil.error(ResultEnums.SERVER_ERROR);
            }
            if (i==1){//判断绑定的是否是会员卡，只有绑定会员卡才会给改用户创建一条个人账户信息
                if (memberships.getMemberName().equals("会员卡")){
                    JSONObject obj =new JSONObject();
                    obj.put("id",UuidUtil.getUuid());
                    obj.put("userId",bindMemberReq.getUserId());
                    obj.put("balance",0);
                    obj.put("integral",0);
                    userVipMapper.addAccountInfo(obj);
                }
            }
        }catch (Exception e){
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
        return ResultUtil.success();
    }

    /**
     * login
     *
     * @param {"code":"微信code","encryptedData":"从微信获取","iv":"从微信获取","nickName":"微信昵称"}
     */
    @Override
    public AppletResult login(AppVO<JSONObject> jsonObject){
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
    //查询我的会员卡
    @Override
    public AppletResult queryMyMembership(String userId){
        List<JSONObject> list=new ArrayList<>();
        try{
            if (StringUtil.isNullStr(userId)){
                return ResultUtil.error(ResultEnums.USERID_IS_NULL);
            }
            list = this.userVipMapper.queryMyMembership(userId);
        }catch (Exception e){
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
        return ResultUtil.success(list);
    }
    //解除绑定会员卡
    @Override
    public AppletResult releaseMembership(String memberId){
        try{
            int i= userVipMapper.deleteBindingMember(memberId);
            if (i==0) return ResultUtil.error(ResultEnums.DEL_BIND_MEMBER_ERROR);
        }catch (Exception e){
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
        return ResultUtil.success();
    }
    //查询我的佣金明细
    @Override
    public AppletResult queryCommissionDetails(SearchDateTimeReq searchDateTimeReq){
        List<JSONObject> list =new ArrayList<>();
        try{
            if (StringUtil.isNullStr(searchDateTimeReq.getUserId())){
                return ResultUtil.error(ResultEnums.PARAM_IS_NULL);
            }
            if (StringUtil.isNullStr(searchDateTimeReq.getStartTime())){
                return ResultUtil.error(ResultEnums.PARAM_IS_NULL);
            }
            if (StringUtil.isNullStr(searchDateTimeReq.getEndTime())){
                return ResultUtil.error(ResultEnums.PARAM_IS_NULL);
            }
            list =commissionDetailsMapper.queryCommissionDetails(searchDateTimeReq);
        }catch (Exception e){
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
        return ResultUtil.success(list);
    }
    //提现申请
    @Override
    public AppletResult doCashRecords(ApplyRecordsReq applyRecordsReq){
        JSONObject jsonObject =new JSONObject();
        try{
            if (StringUtil.isNullStr(applyRecordsReq.getUserId()) && StringUtil.isNullStr(applyRecordsReq.getName()) &&
                    StringUtil.isNullStr(applyRecordsReq.getType().toString()) && StringUtil.isNullStr(applyRecordsReq.getCardNo()) &&
                    StringUtil.isNullStr(applyRecordsReq.getAmount())){
                return ResultUtil.error(ResultEnums.PARAM_IS_NULL);
            }
            if (applyRecordsReq.getType()==3){
                if (StringUtil.isNullStr(applyRecordsReq.getBankName())){
                    return ResultUtil.error(ResultEnums.PARAM_IS_NULL);
                }
                jsonObject.put("bankName",applyRecordsReq.getBankName());
            }
            jsonObject.put("id",UuidUtil.getUuid());
            jsonObject.put("userId",applyRecordsReq.getUserId());
            jsonObject.put("type",applyRecordsReq.getType());
            jsonObject.put("name",applyRecordsReq.getName());
            jsonObject.put("cardNo",applyRecordsReq.getCardNo());
            jsonObject.put("amount",applyRecordsReq.getAmount());
            jsonObject.put("state",1);
            int i =cashRecordsMapper.addCashRecordsInfo(jsonObject);
            if (i==0)return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }catch (Exception e){
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
        return ResultUtil.success();
    }

    //查询提现记录
    @Override
    public AppletResult queryCashRecordsDetails(SearchDateTimeReq searchDateTimeReq){
        List<JSONObject> list =new ArrayList<>();
        try {
            if (StringUtil.isNullStr(searchDateTimeReq.getUserId()) && StringUtil.isNullStr(searchDateTimeReq.getStartTime()) &&
                    StringUtil.isNullStr(searchDateTimeReq.getEndTime())){
                return ResultUtil.error(ResultEnums.PARAM_IS_NULL);
            }
            List<JSONObject> recordsList = cashRecordsMapper.queryCashRecordsDetails(searchDateTimeReq);
            if (recordsList.size()>0){
                for (JSONObject object:recordsList){
                    if (object.getIntValue("state")==1){
                        object.put("state","待审核");
                        object.put("progress","0%");
                    }else if (object.getIntValue("state")==2){
                        object.put("state","出款完成");
                        object.put("progress","100%");
                    }else if (object.getIntValue("state")==3){
                        object.put("state","出款失败");
                        object.put("progress","0%");
                    }
                    list.add(object);
                }
            }
        }catch (Exception e){
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
        return ResultUtil.success(list);
    }
}

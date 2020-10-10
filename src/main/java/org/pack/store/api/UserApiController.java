package org.pack.store.api;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.pack.store.autoconf.JedisOperator;
import org.pack.store.requestVo.*;
import org.pack.store.service.UserService;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.ResultUtil;
import org.pack.store.utils.StringUtil;
import org.pack.store.utils.VerifyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 提供小程序API
 */
@Api(value="UserApiController",tags={"小程序用户操作API"})
@RestController
@RequestMapping(value = "/userApi")
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private JedisOperator jedisOperator;

    @CrossOrigin
    @ApiOperation(value = "获取收货地址信息")
    @PostMapping(value = "queryMyAddress")
    public AppletResult queryMyAddress(@RequestBody @ApiParam(name="用户ID",value="传入json格式",required = true) UserTokenReq userTokenReq){
        String openId = jedisOperator.get(userTokenReq.getToken());
        if(StringUtil.isNullStr(openId)){
            return ResultUtil.error(-1,"token失效，请重新登录");
        }
        return userService.queryMyAddress(userTokenReq.getUserId());
    }

    @CrossOrigin
    @ApiOperation(value = "编辑收货地址")
    @PostMapping(value = "updateMyAddress")
    public AppletResult updateMyAddress(@RequestBody @ApiParam(name="地址对象",value="传入json格式") AddressReq addressReq){
        return userService.updateMyAddress(addressReq);
    }

    @CrossOrigin
    @ApiOperation(value = "添加收货地址")
    @PostMapping(value = "insertMyAddress")
    public AppletResult insertMyAddress(@RequestBody @ApiParam(name="地址对象",value="传入json格式",required = true) AddressReq addressReq){
        return userService.insertMyAddress(addressReq);
    }

    @CrossOrigin
    @ApiOperation(value = "查询字典信息API")
    @PostMapping(value = "queryCommonDict")
    public AppletResult queryCommonDict(@RequestBody @ApiParam(name="字典编码对象",value="传入json格式",required = true) ParentCodeReq parentCodeReq){
        return userService.queryCommonDict(parentCodeReq);
    }

    @CrossOrigin
    @ApiOperation(value = "绑定会员卡信息")
    @PostMapping(value = "bindingMembership")
    public AppletResult bindingMembership(@RequestBody @ApiParam(name="会员卡对象",value="传入json格式",required = true) BindMemberReq bindMemberReq){
        return userService.bindingMembership(bindMemberReq);
    }

    @CrossOrigin
    @ApiOperation(value = "登录操作")
    @PostMapping(value = "login")
    public AppletResult login(@RequestBody @ApiParam(name="用户登录对象",value="传入json格式",required = true) AppVO<JSONObject> data){
        JSONObject jsonObject = data.getData();
        //VerifyUtils.isNotBlanks(jsonObject,"code","encryptedData","iv","nickName")
        if(VerifyUtils.isNotBlanks(jsonObject,"code")) {
            return userService.login(jsonObject);
        }
        return ResultUtil.error(-1,"缺失参数");
    }

    @CrossOrigin
    @ApiOperation(value = "手机号解密操作")
    @PostMapping(value = "doDecryptionMoblie")
    public AppletResult doDecryptionMoblie(@RequestBody @ApiParam(name="手机号解密对象",value="传入json格式",required = true) AppVO<JSONObject> data){
        JSONObject jsonObject = data.getData();
        if(VerifyUtils.isNotBlanks(jsonObject,"code","encryptedData","iv","nickName")) {
            return userService.doDecryptionMoblie(jsonObject);
        }
        return ResultUtil.error(-1,"缺失参数");
    }



    @CrossOrigin
    @ApiOperation(value = "查询我的会员卡")
    @PostMapping(value = "queryMyMembership")
    public AppletResult queryMyMembership(@RequestBody @ApiParam(name="用户ID",value="传入json格式",required = true) UserTokenReq userTokenReq){
        String openId = jedisOperator.get(userTokenReq.getToken());
        if(StringUtil.isNullStr(openId)){
            return ResultUtil.error(-1,"token失效，请重新登录");
        }
        return userService.queryMyMembership(userTokenReq.getUserId());
    }

    @CrossOrigin
    @ApiOperation(value = "解除绑定会员卡")
    @PostMapping(value = "releaseMembership/{memberId}")
    public AppletResult releaseMembership(@ApiParam("会员卡ID") @PathVariable("memberId") String memberId){
        return userService.releaseMembership(memberId);
    }

    @CrossOrigin
    @ApiOperation(value = "查询我的佣金明细")
    @PostMapping(value = "queryCommissionDetails")
    public AppletResult queryCommissionDetails(@RequestBody @ApiParam(name="搜索对象",value="传入json格式",required = true) SearchDateTimeReq searchDateTimeReq){
        String openId = jedisOperator.get(searchDateTimeReq.getToken());
        if(StringUtil.isNullStr(openId)){
            return ResultUtil.error(-1,"token失效，请重新登录");
        }
        return userService.queryCommissionDetails(searchDateTimeReq);
    }

    @CrossOrigin
    @ApiOperation(value = "提现申请")
    @PostMapping(value = "doCashRecords")
    public AppletResult doCashRecords(@RequestBody @ApiParam(name="提现申请对象",value="传入json格式",required = true) ApplyRecordsReq applyRecordsReq){
        String openId = jedisOperator.get(applyRecordsReq.getToken());
        if(StringUtil.isNullStr(openId)){
            return ResultUtil.error(-1,"token失效，请重新登录");
        }
        return userService.doCashRecords(applyRecordsReq);
    }

    @CrossOrigin
    @ApiOperation(value = "查询我的提现记录")
    @PostMapping(value = "queryCashRecordsDetails")
    public AppletResult queryCashRecordsDetails(@RequestBody @ApiParam(name="搜索对象",value="传入json格式",required = true) SearchDateTimeReq searchDateTimeReq){
        String openId = jedisOperator.get(searchDateTimeReq.getToken());
        if(StringUtil.isNullStr(openId)){
            return ResultUtil.error(-1,"token失效，请重新登录");
        }
        return userService.queryCashRecordsDetails(searchDateTimeReq);
    }

    @CrossOrigin
    @ApiOperation(value = "首页轮播图查询")
    @GetMapping(value = "queryBannerList")
    public AppletResult queryBannerList(){
        return userService.queryBannerList();
    }

    @CrossOrigin
    @ApiOperation(value = "查询我的账户信息")
    @PostMapping(value = "queryMyAccount")
    public AppletResult queryMyAccount(@RequestBody @ApiParam(name="用户ID",value="传入json格式",required = true) UserTokenReq userTokenReq){
        String openId = jedisOperator.get(userTokenReq.getToken());
        if(StringUtil.isNullStr(openId)){
            return ResultUtil.error(-1,"token失效，请重新登录");
        }
        return userService.queryMyAccount(userTokenReq.getUserId());
    }

    @CrossOrigin
    @ApiOperation(value = "删除收货地址")
    @PostMapping(value = "delAddressInfo")
    public AppletResult delAddressInfo(@RequestBody @ApiParam(name="删除地址对象",value="传入json格式",required = true) DelAddressReq delAddressReq){
        return userService.delAddressInfo(delAddressReq);
    }

    @CrossOrigin
    @ApiOperation(value = "邀请有礼界面展示接口")
    @PostMapping(value = "myInviteCourtesy")
    public AppletResult myInviteCourtesy(@RequestBody @ApiParam(name="用户对象",value="传入json格式",required = true) UserReq userReq){
        return userService.myInviteCourtesy(userReq);
    }

    @CrossOrigin
    @ApiOperation(value = "提交意见反馈接口")
    @PostMapping(value = "addFeedback")
    public AppletResult addFeedback(@RequestBody @ApiParam(name="意见反馈对象",value="传入json格式",required = true) FeedbackReq feedbackReq){
        String openId = jedisOperator.get(feedbackReq.getToken());
        if(StringUtil.isNullStr(openId)){
            return ResultUtil.error(-1,"token失效，请重新登录");
        }
        return userService.addFeedback(feedbackReq);
    }

    @CrossOrigin
    @ApiOperation(value = "常见问题信息接口")
    @GetMapping(value = "queryQuestions")
    public AppletResult queryQuestions(){
        return userService.queryQuestions();
    }

    @CrossOrigin
    @ApiOperation(value = "查询关于我们接口信息")
    @GetMapping(value = "getAbout")
    public AppletResult getAbout(){
        return userService.getAbout();
    }

    @CrossOrigin
    @ApiOperation(value = "判断用户是否注册")
    @PostMapping(value = "queryUserLoginState")
    public AppletResult queryUserLoginState(@RequestBody @ApiParam(name="判断用户登录",value="传入json格式",required = true) UserLoginReq userLoginReq){
        return userService.queryUserLoginState(userLoginReq);
    }

}

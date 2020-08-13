package org.pack.store.api;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.pack.store.requestVo.AddressReq;
import org.pack.store.requestVo.AppVO;
import org.pack.store.requestVo.BindMemberReq;
import org.pack.store.requestVo.ParentCodeReq;
import org.pack.store.service.UserService;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.ResultUtil;
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

    @CrossOrigin
    @ApiOperation(value = "获取收货地址信息")
    @GetMapping(value = "queryMyAddress/{userId}")
    public AppletResult queryMyAddress(@ApiParam("用户ID") @PathVariable("userId") String userId){
        return userService.queryMyAddress(userId);
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
    @GetMapping(value = "queryCommonDict")
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
        if(VerifyUtils.isNotBlanks(jsonObject,"code","encryptedData","iv","nickName")) {
            return userService.login(jsonObject);
        }
        return ResultUtil.error(-1,"缺失参数");
    }

    @CrossOrigin
    @ApiOperation(value = "查询我的会员卡")
    @GetMapping(value = "queryMyMembership/{userId}")
    public AppletResult queryMyMembership(@ApiParam("用户ID") @PathVariable("userId") String userId){
        return userService.queryMyMembership(userId);
    }

    @CrossOrigin
    @ApiOperation(value = "解除绑定会员卡")
    @PostMapping(value = "releaseMembership")
    public AppletResult releaseMembership(){
        return null;
    }




}

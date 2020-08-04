package org.pack.store.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.pack.store.requestVo.AddressReq;
import org.pack.store.service.UserService;
import org.pack.store.utils.AppletResult;
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

}

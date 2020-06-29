package org.pack.store.controller;

import io.swagger.annotations.ApiOperation;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @ApiOperation(value = "用户信息测试")
    @GetMapping(value = "/test")
    public AppletResult queryTest(){
        System.out.print("*********************进来测试*******************");
        return ResultUtil.success();
    }
}

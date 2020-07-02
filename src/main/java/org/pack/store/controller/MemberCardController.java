package org.pack.store.controller;

import io.swagger.annotations.ApiOperation;
import org.pack.store.requestVo.AddCardReq;
import org.pack.store.requestVo.MemberCardListReq;
import org.pack.store.service.MemberCardService;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/memberCard")
public class MemberCardController {

    @Autowired
    private MemberCardService memberCardService;

    @CrossOrigin
    @ApiOperation(value = "会员卡管理信息查询")
    @PostMapping(value = "queryMemberCardAll")
    public AppletResult queryMemberCardAll(@RequestBody MemberCardListReq memberCardListReq){
        return memberCardService.queryMemberCardAll(memberCardListReq);
    }

    @CrossOrigin
    @ApiOperation(value = "添加会员卡管理信息")
    @PostMapping(value = "insertMemberCardAll")
    public AppletResult insertMemberCardAll(@RequestBody AddCardReq addCardReq){
        return memberCardService.insertMemberCard(addCardReq);
    }

}

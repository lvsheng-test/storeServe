package org.pack.store.controller;

import io.swagger.annotations.ApiOperation;
import org.pack.store.requestVo.*;
import org.pack.store.service.MemberCardService;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.JSONResult;
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
    public JSONResult queryMemberCardAll(@RequestBody MemberCardListReq memberCardListReq){
        return memberCardService.queryMemberCardAll(memberCardListReq);
    }

    @CrossOrigin
    @ApiOperation(value = "添加会员卡管理信息")
    @PostMapping(value = "insertMemberCardAll")
    public AppletResult insertMemberCardAll(@RequestBody AddCardReq addCardReq){
        return memberCardService.insertMemberCard(addCardReq);
    }

    @CrossOrigin
    @ApiOperation(value = "条件查询用户会员卡信息")
    @PostMapping(value = "queryMembershipByPageList")
    public JSONResult queryMembershipByPageList(@RequestBody MembershipListReq membershipListReq){
        return memberCardService.queryMembershipByPageList(membershipListReq);
    }

    @CrossOrigin
    @ApiOperation(value = "开通会员卡操作")
    @PostMapping(value = "queryCardNoAndMoblie")
    public AppletResult queryCardNoAndMoblie(@RequestBody CardNoAndMoblieReq cardNoAndMoblieReq){
        return memberCardService.queryCardNoAndMoblie(cardNoAndMoblieReq);
    }

    @CrossOrigin
    @ApiOperation(value = "会员卡充值操作")
    @PostMapping(value = "doRecharge")
    public AppletResult doRecharge(@RequestBody RechargeMemberReq rechargeMemberReq){
        return memberCardService.doRecharge(rechargeMemberReq);
    }
}

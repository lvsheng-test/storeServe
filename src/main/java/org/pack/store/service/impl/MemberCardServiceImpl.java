package org.pack.store.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.pack.store.entity.DictEntity;
import org.pack.store.entity.MemberCardEntity;
import org.pack.store.entity.MembershipEntity;
import org.pack.store.enums.ResultEnums;
import org.pack.store.mapper.DictMapper;
import org.pack.store.mapper.MemberCardMapper;
import org.pack.store.mapper.MembershipMapper;
import org.pack.store.requestVo.AddCardReq;
import org.pack.store.requestVo.MemberCardListReq;
import org.pack.store.requestVo.MembershipListReq;
import org.pack.store.resposeVo.MemberCardRes;
import org.pack.store.service.MemberCardService;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.JSONResult;
import org.pack.store.utils.ResultUtil;
import org.pack.store.utils.common.CommonUtils;
import org.pack.store.utils.common.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("memberCardService")
public class MemberCardServiceImpl implements MemberCardService {

    @Autowired
    private MemberCardMapper memberCardMapper;

    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private MembershipMapper membershipMapper;

    public AppletResult insertMemberCard(AddCardReq addCardReq){
        int number =Integer.parseInt(addCardReq.getNumber());//开卡张数
        DictEntity dictInfo =dictMapper.getCode(addCardReq.getMemberType());
        if(dictInfo !=null){
            for (int i=0;i<number;i++){
                MemberCardEntity memberCardEntity =new MemberCardEntity();
                memberCardEntity.setId(UuidUtil.getUuid());
                memberCardEntity.setMemberType(dictInfo.getCode());
                memberCardEntity.setMemberName(dictInfo.getName());
                memberCardEntity.setCardNo(CommonUtils.getMemberCard(dictInfo.getName()));
                memberCardEntity.setCreateTime(new Date());
                memberCardEntity.setDr('1');//新开卡设置未启用状态
                memberCardMapper.insertMemberCard(memberCardEntity);
            }
            return ResultUtil.success();
        }else {
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }

    }

    public JSONResult queryMemberCardAll(MemberCardListReq memberCardListReq){
        MemberCardRes memberCardRes =new MemberCardRes();
        PageHelper.startPage(memberCardListReq.getPage(),memberCardListReq.getLimit(),true);
        List<MemberCardEntity> memberCardList = memberCardMapper.queryMemberCardAll(memberCardListReq);
        int count = memberCardMapper.queryMemberCardAllByCount(memberCardListReq);
        return ResultUtil.success(memberCardList,count);
    }
    public JSONResult queryMembershipByPageList(MembershipListReq membershipListReq){
        JSONObject json = new JSONObject();
        PageHelper.startPage(membershipListReq.getPage(),membershipListReq.getLimit(),true);
        List<MembershipEntity> membershipList=membershipMapper.queryMembershipByPageList(membershipListReq);
        int count = membershipMapper.getMembershipByPageCount(membershipListReq);
        return ResultUtil.success(membershipList,count);
    }
}
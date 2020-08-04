package org.pack.store.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import org.pack.store.entity.*;
import org.pack.store.enums.ResultEnums;
import org.pack.store.mapper.*;
import org.pack.store.requestVo.*;
import org.pack.store.resposeVo.MemberCardRes;
import org.pack.store.service.MemberCardService;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.JSONResult;
import org.pack.store.utils.ResultUtil;
import org.pack.store.utils.common.BigDecimalUtil;
import org.pack.store.utils.common.CommonUtils;
import org.pack.store.utils.common.DateUtil;
import org.pack.store.utils.common.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
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

    @Autowired
    private TransactionDetailMapper transactionDetailMapper;

    @Autowired
    private ConfigProportionMapper configProportionMapper;

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
        PageHelper.startPage(memberCardListReq.getPage(),memberCardListReq.getLimit(),true);
        List<MemberCardEntity> memberCardList = memberCardMapper.queryMemberCardAll(memberCardListReq);
        int count = memberCardMapper.queryMemberCardAllByCount(memberCardListReq);
        return ResultUtil.success(memberCardList,count);
    }
    public JSONResult queryMembershipByPageList(MembershipListReq membershipListReq){
        PageHelper.startPage(membershipListReq.getPage(),membershipListReq.getLimit(),true);
        List<MembershipEntity> membershipList=membershipMapper.queryMembershipByPageList(membershipListReq);
        int count = membershipMapper.getMembershipByPageCount(membershipListReq);
        return ResultUtil.success(membershipList,count);
    }

    public AppletResult queryCardNoAndMoblie(CardNoAndMoblieReq cardNoAndMoblieReq){
        Integer amount =Integer.parseInt(cardNoAndMoblieReq.getAmount());//充值金额
        BigDecimal actualAmount=new BigDecimal(amount);//实际充值金额
        BigDecimal money=null; //充值返现金额
        MemberCardEntity cardInfo = memberCardMapper.getCardNo(cardNoAndMoblieReq.getCardNo());
        if (cardInfo !=null){//卡号验证通过
            //验证此手机有没有开通同种类型的会员卡
            MembershipEntity membershipInfo = membershipMapper.getMoblieAndMemberType(cardNoAndMoblieReq.getMobile(),cardInfo.getMemberType());
            if (membershipInfo !=null){
                return ResultUtil.success(ResultEnums.MOILE_NO_YES);
            }else {//执行开通学员卡信息
                MembershipEntity membership =new MembershipEntity();
                membership.setId(UuidUtil.getUuid());
                membership.setMobile(cardNoAndMoblieReq.getMobile());
                membership.setMemberType(cardInfo.getMemberType());
                membership.setMemberName(cardInfo.getMemberName());
                membership.setCardNo(cardInfo.getCardNo());
                membership.setAmount(actualAmount);//缺少充值会员卡送钱比例
                membership.setStartTime(new Date());
                if (cardInfo.getMemberName().equals("消费卡")){
                    membership.setEndTime(DateUtil.getSystimeAfterMonth(3));
                }
                if (cardInfo.getMemberName().equals("押金卡")){
                    membership.setEndTime(DateUtil.getSystimeAfterMonth(12));
                }
                if (cardInfo.getMemberName().equals("会员卡")){
                    //查询会员充值返佣比例
                    ConfigProportionEntity configProportion = configProportionMapper.getConfigType("101");//会员卡充值返现佣金比例
                    money = BigDecimalUtil.multiply(actualAmount,configProportion.getProportion());//充值金额乘以佣金比例
                    membership.setAmount(BigDecimalUtil.add(actualAmount,money));
                    membership.setEndTime(DateUtil.getSystimeAfterMonth(120));
                }
                membership.setDr('0');
                membershipMapper.insertMembershipInfo(membership);
                //会员卡开通成功,把此会员卡状态设置为已激活
                cardInfo.setDr('0');
                memberCardMapper.updataMemberCardInfo(cardInfo);

                //生成充值流水明细--只有会员卡和押金卡类型才生成流水明细
                if (cardInfo.getMemberName().equals("押金卡")){
                    TransactionDetailEntity transactionDetail =new TransactionDetailEntity();
                    transactionDetail.setId(UuidUtil.getUuid());
                    transactionDetail.setMobile(cardNoAndMoblieReq.getMobile());
                    transactionDetail.setAmount(actualAmount);
                    transactionDetail.setStatus("102");
                    transactionDetail.setInOut('0');
                    transactionDetail.setRemark("缴纳押金");
                    transactionDetail.setTs(new Date());
                    transactionDetailMapper.insertTransactionDetailInfo(transactionDetail);
                }
                if (cardInfo.getMemberName().equals("会员卡")){
                    TransactionDetailEntity transactionDetail =new TransactionDetailEntity();
                    transactionDetail.setId(UuidUtil.getUuid());
                    transactionDetail.setMobile(cardNoAndMoblieReq.getMobile());
                    transactionDetail.setAmount(actualAmount);
                    transactionDetail.setStatus("101");
                    transactionDetail.setInOut('0');
                    transactionDetail.setRemark("会员卡充值");
                    transactionDetail.setTs(new Date());
                    transactionDetailMapper.insertTransactionDetailInfo(transactionDetail);

                    TransactionDetailEntity detail =new TransactionDetailEntity();
                    detail.setId(UuidUtil.getUuid());
                    detail.setMobile(cardNoAndMoblieReq.getMobile());
                    detail.setAmount(money);
                    detail.setStatus("103");
                    detail.setInOut('1');
                    detail.setRemark("充值返现");
                    detail.setTs(new Date());
                    transactionDetailMapper.insertTransactionDetailInfo(detail);
                }
                return ResultUtil.success();
            }
        }else {
            return ResultUtil.success(ResultEnums.CARD_NO_ERROR);
        }
    }

    public AppletResult doRecharge(RechargeMemberReq rechargeMemberReq){
        //Integer amount =Integer.parseInt(rechargeMemberReq.getAmount());//账户余额
        Integer rechargeAmount=Integer.parseInt(rechargeMemberReq.getRechargeAmount());//充值金额
        //BigDecimal balance=new BigDecimal(amount);//账户余额
        BigDecimal rechargeBalance=new BigDecimal(rechargeAmount);//充值金额
        BigDecimal money=null; //充值返现金额
        ConfigProportionEntity configProportion = configProportionMapper.getConfigType("101");//会员卡充值返现佣金比例
        money = BigDecimalUtil.multiply(rechargeBalance,configProportion.getProportion());//充值金额乘以佣金比例

        MembershipEntity membershipInfo =new MembershipEntity();
        membershipInfo.setId(rechargeMemberReq.getId());
        membershipInfo.setAmount(rechargeMemberReq.getAmount().add(rechargeBalance).add(money));//余额+充值金额+返现金额
        membershipMapper.doRechages(membershipInfo);//充值成功

        TransactionDetailEntity transactionDetail =new TransactionDetailEntity();
        transactionDetail.setId(UuidUtil.getUuid());
        transactionDetail.setMobile(rechargeMemberReq.getMobile());
        transactionDetail.setAmount(rechargeBalance);
        transactionDetail.setStatus("101");
        transactionDetail.setInOut('0');
        transactionDetail.setRemark("会员卡充值");
        transactionDetail.setTs(new Date());
        transactionDetailMapper.insertTransactionDetailInfo(transactionDetail);

        TransactionDetailEntity detail =new TransactionDetailEntity();
        detail.setId(UuidUtil.getUuid());
        detail.setMobile(rechargeMemberReq.getMobile());
        detail.setAmount(money);
        detail.setStatus("103");
        detail.setInOut('1');
        detail.setRemark("充值返现");
        detail.setTs(new Date());
        transactionDetailMapper.insertTransactionDetailInfo(detail);
        return ResultUtil.success();
    }
}

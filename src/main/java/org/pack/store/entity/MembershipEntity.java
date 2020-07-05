package org.pack.store.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MembershipEntity implements Serializable {

    private String id;         //主键ID

    private String mobile;     //手机号

    private String memberType;  //会员卡类型

    private String memberName;  //会员卡名称

    private String cardNo;   //会员卡卡号

    private BigDecimal amount; // 金额

    private Date startTime; //开始有效日期

    private Date endTime; //结束有效日期

    private char dr; //标识(0:有效,1:无效)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public char getDr() {
        return dr;
    }

    public void setDr(char dr) {
        this.dr = dr;
    }
}

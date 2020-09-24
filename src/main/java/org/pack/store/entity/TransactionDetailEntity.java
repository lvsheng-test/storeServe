package org.pack.store.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDetailEntity {

    private String id;      //主键ID

    private String userId;  //会员手机号

    private BigDecimal amount; //金额

    private String status;  //交易状态(101:会员卡充值,102:缴纳押金...)

    private char inOut;   //进出账标识(0:+,1:-)

    private String remark; //备注(发生的动作)

    private Date ts;  //发生交易时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public char getInOut() {
        return inOut;
    }

    public void setInOut(char inOut) {
        this.inOut = inOut;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

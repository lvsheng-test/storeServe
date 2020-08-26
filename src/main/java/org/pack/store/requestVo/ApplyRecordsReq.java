package org.pack.store.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="apply对象",description="申请提现JSON格式传参")
public class ApplyRecordsReq {

    @ApiModelProperty(value="token值",name="token",required=true)
    private String token;

    @ApiModelProperty(value="用户ID",name="userId",required=true)
    private String userId; //用户主键ID

    @ApiModelProperty(value="提现类型(1:微信,2:支付宝,3:银行卡)",name="type",required=true)
    private Integer type;

    @ApiModelProperty(value="真实姓名",name="name",required=true)
    private String name;

    @ApiModelProperty(value="账号/银行卡号",name="cardNo",required=true)
    private String cardNo;

    @ApiModelProperty(value="银行卡支行名称",name="bankName")
    private String bankName;

    @ApiModelProperty(value="提现金额",name="amount",required=true)
    private String amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

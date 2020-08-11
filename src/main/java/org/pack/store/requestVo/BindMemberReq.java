package org.pack.store.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="bindMember对象",description="JSON格式传参")
public class BindMemberReq {

    @ApiModelProperty(value="用户ID",name="userId",required=true)
    private String userId;

    @ApiModelProperty(value="会员卡类型编码",name="memberType",required=true)
    private String memberType;

    @ApiModelProperty(value="会员卡号",name="cardNo",required=true)
    private String cardNo;

    @ApiModelProperty(value="会员手机号",name="mobile",required=true)
    private String mobile;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

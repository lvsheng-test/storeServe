package org.pack.store.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="order对象",description="地址JSON格式传参")
public class OrderSerchReq {

    @ApiModelProperty(value="订单类型（101:全部，102:待支付，103:待收货）",name="type",required=true)
    private String type;

    @ApiModelProperty(value="用户ID",name="userId",required=true)
    private String userId; //用户主键ID

    @ApiModelProperty(value="用户ID",name="token",required=true)
    private String token; //Token信息

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

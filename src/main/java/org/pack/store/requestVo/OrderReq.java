package org.pack.store.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="DEL对象",description="地址JSON格式传参")
public class OrderReq {

    @ApiModelProperty(value="订单ID",name="orderId",required=true)
    private String orderId; //用户主键ID

    @ApiModelProperty(value="token",name="token",required=true)
    private String token; //Token信息

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

package org.pack.store.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="delAddress对象",description="删除地址JSON格式传参")
public class DelAddressReq {

    @ApiModelProperty(value="收货地址ID",name="addressId",required=true)
    private String addressId; //主键

    @ApiModelProperty(value="用户ID",name="userId",required=true)
    private String userId; //用户主键ID

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

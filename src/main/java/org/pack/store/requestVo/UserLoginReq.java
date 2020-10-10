package org.pack.store.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="userLogin对象",description="JSON格式传参")
public class UserLoginReq {

    @ApiModelProperty(value="手机号",name="moblie",required=true)
    private String mobile; //用户主键ID

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

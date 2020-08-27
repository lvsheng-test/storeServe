package org.pack.store.requestVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="user对象",description="用户JSON格式传参")
public class UserReq {

    @ApiModelProperty(value="用户ID",name="userId",required=true)
    private String userId; //用户主键ID

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

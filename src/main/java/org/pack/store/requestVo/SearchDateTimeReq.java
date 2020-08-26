package org.pack.store.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="dateTime对象",description="JSON格式传参")
public class SearchDateTimeReq {

    @ApiModelProperty(value="token值",name="token",required=true)
    private String token;

    @ApiModelProperty(value="用户ID",name="userId",required=true)
    private String userId; //用户主键ID

    @ApiModelProperty(value="开始时间",name="startTime",required=true)
    private String startTime;

    @ApiModelProperty(value="结束时间",name="endTime",required=true)
    private String endTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

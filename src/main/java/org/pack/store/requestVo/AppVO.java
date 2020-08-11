package org.pack.store.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="app对象",description="登录JSON格式传参")
public class AppVO<T> {

    @ApiModelProperty(value="appId",name="appId",required=true)
    private String appId;

    @ApiModelProperty(value="登录时间",name="reqTime")
    private String reqTime;

    @ApiModelProperty(value="版本号",name="version",required=true)
    private String version;

    @ApiModelProperty(value="opt",name="opt")
    private String opt;

    @ApiModelProperty(value="token信息",name="token",required=true)
    private String token;

    @ApiModelProperty(value="data",name="data")
    private T data;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

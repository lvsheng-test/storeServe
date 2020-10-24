package org.pack.store.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="goStore对象",description="自取信息JSON格式传参")
public class GoStoreReq {

    @ApiModelProperty(value="用户ID",name="userId",required=true)
    private String userId; //用户主键ID

    @ApiModelProperty(value="用户ID",name="token",required=true)
    private String token; //Token信息

    @ApiModelProperty(value="经度",name="longitude",required=true)
    private double longitude;

    @ApiModelProperty(value="纬度",name="latitude",required=true)
    private double latitude;

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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}

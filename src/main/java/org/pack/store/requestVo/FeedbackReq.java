package org.pack.store.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Feedback对象",description="用户JSON格式传参")
public class FeedbackReq {
    @ApiModelProperty(value="用户ID",name="userId",required=true)
    private String userId; //用户主键ID

    @ApiModelProperty(value="用户ID",name="token",required=true)
    private String token; //Token信息

    @ApiModelProperty(value="意见反馈内容",name="content",required=true)
    private String content;

    @ApiModelProperty(value="图片地址",name="imageUrl",required=true)
    private String imageUrl;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

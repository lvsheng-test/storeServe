package org.pack.store.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="字典编码对象",description="字典编码JSON格式参数")
public class ParentCodeReq {

    @ApiModelProperty(value="(VIP_TYPE:会员卡类型,OPEN_CITY:开通城市)",name="parentCode",required = true)
    private String parentCode;

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}

package org.pack.store.resposeVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="city对象",description="城市JSON格式返回")
public class CityInfoRes {

    @ApiModelProperty(value="编码",name="code")
    private String code;

    @ApiModelProperty(value="名称",name="name")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

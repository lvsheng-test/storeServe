package org.pack.store.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="search对象",description="条件搜索JSON格式传参")
public class SearchGoodsReq {

    @ApiModelProperty(value="搜索关键字",name="keys",required=true)
    private String keys;

    @ApiModelProperty(value="搜索类型(0:综合,1:销量,2:价格升序,3:价格降序)",name="num")
    private Integer num;

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}

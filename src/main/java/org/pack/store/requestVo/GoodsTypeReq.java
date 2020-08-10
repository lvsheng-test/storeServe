package org.pack.store.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="商品分类传参对象",description="分类JSON格式传参")
public class GoodsTypeReq {

    @ApiModelProperty(value="商品类型ID",name="pid",required=true)
    private String pid;

    @ApiModelProperty(value="当前页码",name="page",required=true)
    private Integer page; //代表当前页码

    @ApiModelProperty(value="每页展示数量",name="limit",required=true)
    private Integer limit;//代表每页数据量

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}

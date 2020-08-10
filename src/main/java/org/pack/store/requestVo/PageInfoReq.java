package org.pack.store.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="pageInfo对象",description="分页JSON格式传参")
public class PageInfoReq {

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
}

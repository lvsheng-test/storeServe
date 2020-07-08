package org.pack.store.requestVo;

public class ConfigReq {

    private Integer page; //代表当前页码

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

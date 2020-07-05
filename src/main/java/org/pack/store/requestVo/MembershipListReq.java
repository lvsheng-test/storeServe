package org.pack.store.requestVo;

public class MembershipListReq {
    private Integer page; //代表当前页码

    private Integer limit;//代表每页数据量

    private String mobile; //手机号

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

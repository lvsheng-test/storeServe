package org.pack.store.requestVo;

public class MemberCardListReq {

    private String memberType; //会员卡类型编码

    private char dr; //会员卡标识(0:已启用,1:未启用)

    private Integer page; //代表当前页码

    private Integer limit;//代表每页数据量

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public char getDr() {
        return dr;
    }

    public void setDr(char dr) {
        this.dr = dr;
    }

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

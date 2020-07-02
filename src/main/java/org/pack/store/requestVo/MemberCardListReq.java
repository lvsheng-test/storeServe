package org.pack.store.requestVo;

public class MemberCardListReq {

    private String memberType; //会员卡类型编码

    private char dr; //会员卡标识(0:已启用,1:未启用)

    private Integer currentPage; //当前页数

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

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}

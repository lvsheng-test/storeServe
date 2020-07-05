package org.pack.store.resposeVo;

import org.pack.store.entity.MemberCardEntity;

import java.util.List;

public class MemberCardRes {

    private List<MemberCardEntity> memberCardList;
    //总页数
    private Integer pageTotal;

    private Integer total; //总条数

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<MemberCardEntity> getMemberCardList() {
        return memberCardList;
    }

    public void setMemberCardList(List<MemberCardEntity> memberCardList) {
        this.memberCardList = memberCardList;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
}

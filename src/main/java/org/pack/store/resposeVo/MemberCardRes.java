package org.pack.store.resposeVo;

import org.pack.store.entity.MemberCardEntity;

import java.util.List;

public class MemberCardRes {

    private List<MemberCardEntity> memberCardList;
    //总页数
    private Integer pageTotal;

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

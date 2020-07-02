package org.pack.store.entity;

import java.util.Date;

public class MemberCardEntity {

    private String id;   //主键ID

    private String memberType;//会员卡类型

    private String cardNo;// 会员卡卡号

    private Date createTime;//创建时间

    private char dr;// 标识(0:已启用,1:未启用)

    //扩展字段
    private String memberName;//会员卡名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public char getDr() {
        return dr;
    }

    public void setDr(char dr) {
        this.dr = dr;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}

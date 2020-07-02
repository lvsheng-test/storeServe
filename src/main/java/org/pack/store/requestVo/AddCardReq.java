package org.pack.store.requestVo;

public class AddCardReq {

    private String memberType;//会员卡类型编码

    private String number; //开卡张数

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

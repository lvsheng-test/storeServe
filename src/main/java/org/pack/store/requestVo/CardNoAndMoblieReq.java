package org.pack.store.requestVo;

public class CardNoAndMoblieReq {

    //会员卡号
    private String cardNo;

    //手机号
    private String mobile;

    //充值金额
    private String amount;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

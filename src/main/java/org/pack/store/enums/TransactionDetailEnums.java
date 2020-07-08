package org.pack.store.enums;

/**
 * 交易明细类型-枚举
 */
public enum TransactionDetailEnums {

    RECHARGE_MEMBER("101","会员卡充值"),
    DEPOSIT_MEMBER("102","缴纳押金"),
    RETURN_MOENY("103","充值返现");


    private String code;

    private String message;

    TransactionDetailEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(String code) {
        TransactionDetailEnums[] businessModeEnums = values();
        for (TransactionDetailEnums businessModeEnum : businessModeEnums) {
            if (businessModeEnum.code.equals(code)) {
                return businessModeEnum.getMessage();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        //System.out.println(UuidUtil.getUuid());
        System.out.println("获取值：" + getMessage("VIP_TYPE"));
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}

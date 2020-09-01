package org.pack.store.enums;

public enum OrderEnums {

    ORDER_WAIT_PAY("0","待支付"),
    ORDER_AL_PAY("1","已支付")
    ;


    private String code;

    private String message;

    OrderEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(String code) {
        OrderEnums[] businessModeEnums = values();
        for (OrderEnums businessModeEnum : businessModeEnums) {
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

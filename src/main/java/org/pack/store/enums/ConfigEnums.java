package org.pack.store.enums;

public enum ConfigEnums {

    RECHARGE_MEMBER("101","充值返现"),
    COMMISSION_MEMBER("102","佣金返现");


    private String code;

    private String message;

    ConfigEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(String code) {
        ConfigEnums[] businessModeEnums = values();
        for (ConfigEnums businessModeEnum : businessModeEnums) {
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

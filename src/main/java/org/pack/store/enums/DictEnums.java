package org.pack.store.enums;

public enum DictEnums {

    SEX("SEX","性别"),
    VIP_TYPE("VIP_TYPE","会员卡类型"),
    USER_TYPE("USER_TYPE","用户类别"),
    PRO_TYPE("PRO_TYPE","商品类型");

    private String code;

    private String message;

    DictEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(String code) {
        DictEnums[] businessModeEnums = values();
        for (DictEnums businessModeEnum : businessModeEnums) {
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

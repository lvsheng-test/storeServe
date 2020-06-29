package org.pack.store.enums;

public enum DictEnums {

    SEX("SEX","性别"),
    VIP_TYPE("VIP_TYPE","会员卡类型"),
    USER_TYPE("USER_TYPE","用户类别");

    private String code;

    private String name;

    DictEnums(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String msg) {
        this.name = name;
    }

}

package org.pack.store.enums;

public enum ResultEnums {

    SERVER_ERROR(-1,"系统内部错误"),

    RETURN_SUCCESS(200,"SUCCESS"),

    NOT_FOUND_DATA(10001,"数据不存在"),

    CUSTOM_STORE_NULL_PARAM(10001,""),

    NOT_FOUND_SESSION(404, "错误SESSION"),

    PARAM_IS_NULL(1001,"参数为空"),

    USER_DATA_VALIDATE_FAIL(1002,"用户数据验证失败"),

    NOT_FOUND_USER(20001,"用户不存在"),

    OVER_TIME(20008,"操作超时"),

    WRONG_CERT(20009,"实名认证不一致"),

    USER_NAME_ERROR(20017,"已实名认证"),

    NO_CERTIFICATION(20018,"未实名认证，请先实名认证"),

    UNIONID_EXIST(20027,"该微信号已经被其他手机号授权"),

    REMOVE_BICYCLE_WX_INFO(20028,"解除微信绑定失败"),

    WX_AUTHORIZ_SUCCESS(20030,"微信授权成功"),

    REMOVE_BICYCLE_WX_SUCCESS(20031,"解除微信绑定成功");



    private Integer code;

    private String msg;

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}



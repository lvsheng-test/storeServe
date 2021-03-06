package org.pack.store.enums;

public enum ResultEnums {

    SERVER_ERROR(-1,"系统内部错误"),

    RETURN_SUCCESS(200,"SUCCESS"),

    CARD_NO_ERROR(202,"该会员卡已被使用或不存在"),

    MOILE_NO_YES(203,"该手机号已开通此类型会员卡"),

    USERID_IS_NULL(204,"此用户ID不能为空"),

    ID_IS_NULL(205,"主键ID参数不能为空"),

    UPLOAD_IS_ERROR(206,"上传文件格式错误"),

    MOILE_OPEN_NO(207,"该手机号未开通此类型会员卡"),

    BIND_MEMBER_YES(208,"该用户已绑定过此类型会员卡"),

    DEL_BIND_MEMBER_ERROR(209,"解除绑定失败"),

    IS_BIND_MEMBER_YES(210,"此会员卡被绑定"),

    DECRYPTION_ERROR(211,"解密失败"),

    WX_RETRUE_ERROR(212,"微信接口返回失败"),

    WX_LOGIN_ERROR(213,"微信登录失败，请先获取手机号"),

    WX_MOBLIE_ERROR(214,"微信手机号解密失败"),

    USER_UPDATE_ERROR(215,"个人信息更新失败"),

    ORDER_DEL_ERROR(216,"订单信息不存在"),

    USER_REGIST_YES(217,"该用户已注册过了"),

    HORSEMAN_NULL(218,"没有匹配到骑手"),

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



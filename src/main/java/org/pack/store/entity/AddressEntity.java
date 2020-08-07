package org.pack.store.entity;

import java.util.Date;

public class AddressEntity {

    private String id;   //主键ID

    private String userId; //用户主键ID

    private String contact; //联系人名称

    private char sex;  //性别(0:先生,1:女士)

    private String phone;  //联系人手机号

    private String cityCode;//城市编码

    private String cityName;  //城市名称

    private String address; //详细地址

    private char defaultAddress;//设置默认地址(Y:默认,N:不默认)

    private Date ts;          //更新时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public void setDefaultAddress(char defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public char getDefaultAddress() {
        return defaultAddress;
    }
}

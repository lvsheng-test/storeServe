package org.pack.store.entity;

import java.util.Date;

public class AddressEntity {

    private String id;   //主键ID

    private String userId; //用户主键ID

    private String contact; //联系人名称

    private char sex;  //性别(0:先生,1:女士)

    private String phone;  //联系人手机号

    private String provin; //省份

    private String city;  //市

    private String area; //区

    private String address; //详细地址

    private char defaultAddress;//设置默认地址(1:默认)

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

    public String getProvin() {
        return provin;
    }

    public void setProvin(String provin) {
        this.provin = provin;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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
}

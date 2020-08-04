package org.pack.store.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="address对象",description="地址JSON格式传参")
public class AddressReq {

    @ApiModelProperty(value="收货地址ID",name="id",required=true)
    private String id; //主键

    @ApiModelProperty(value="用户ID",name="userId",required=true)
    private String userId; //用户主键ID

    @ApiModelProperty(value="联系人名称",name="contact")
    private String contact; //联系人名称

    @ApiModelProperty(value="性别(0:先生,1:女士)",name="sex")
    private char sex;  //性别(0:先生,1:女士)

    @ApiModelProperty(value="联系人手机号",name="phone")
    private String phone;  //联系人手机号

    @ApiModelProperty(value="省份",name="provin")
    private String provin; //省份

    @ApiModelProperty(value="市",name="city")
    private String city;  //市

    @ApiModelProperty(value="区",name="area")
    private String area; //区

    @ApiModelProperty(value="详细地址",name="address")
    private String address; //详细地址

    @ApiModelProperty(value="设置默认地址(Y:默认,N:不默认)",name="defaultAddress")
    private char defaultAddress;//设置默认地址(0:默认)

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

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public char getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(char defaultAddress) {
        this.defaultAddress = defaultAddress;
    }
}

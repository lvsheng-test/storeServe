package org.pack.store.resposeVo;

import java.util.List;

public class GoodsDetailsRes {

    //商品ID
    private String goodsId;

    //商品名称
    private String goodsName;

    //商品实际价格
    private String goodsPrice;

    //商品优惠后价格
    private String goodsDiscount;

    //商品备注
    private String goodsIntro;

    //净含量(单位：g)
    private Integer netContent;

    //保存条件
    private String saveConditions;

    //保质期(单位:d,天)
    private Integer shelfLife;

    //产品介绍图
    private String ramarkUrl;

    //商品轮播图
    private List<String> bannerImg;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(String goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    public String getGoodsIntro() {
        return goodsIntro;
    }

    public void setGoodsIntro(String goodsIntro) {
        this.goodsIntro = goodsIntro;
    }

    public Integer getNetContent() {
        return netContent;
    }

    public void setNetContent(Integer netContent) {
        this.netContent = netContent;
    }

    public String getSaveConditions() {
        return saveConditions;
    }

    public void setSaveConditions(String saveConditions) {
        this.saveConditions = saveConditions;
    }

    public Integer getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Integer shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getRamarkUrl() {
        return ramarkUrl;
    }

    public void setRamarkUrl(String ramarkUrl) {
        this.ramarkUrl = ramarkUrl;
    }

    public List<String> getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(List<String> bannerImg) {
        this.bannerImg = bannerImg;
    }
}

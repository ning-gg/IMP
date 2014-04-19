package com.sdk.bean;

import org.apache.commons.lang.builder.ToStringBuilder;

public class PayNotifyBean {

    String game;        //游戏
    String platform;    //哪一种平台
    String uid;//用户平台ID
    String orderNo;//订单号
    String money;//支付金额，单位：元。

    public PayNotifyBean() {

    }

    public PayNotifyBean(String game, String platform, String orderNo, String money, String uid) {
        this.game = game;
        this.platform = platform;
        this.orderNo = orderNo;
        this.money = money;
        this.uid = uid;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

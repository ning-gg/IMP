package com.sdk.bean;

import org.apache.commons.lang.builder.ToStringBuilder;

public class PayNotifyBean {

	String orderNo;//订单号
	String money;//支付金额，单位：元。
	String uid;//用户平台ID
	
	public PayNotifyBean(){
		
	}
	
	public PayNotifyBean(String orderNo, String money, String uid){
		this.orderNo = orderNo;
		this.money = money;
		this.uid = uid;
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
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
}

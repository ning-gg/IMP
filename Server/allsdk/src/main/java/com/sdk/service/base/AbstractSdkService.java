package com.sdk.service.base;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdk.bean.LoginResult;
import com.sdk.bean.LogoutResult;
import com.sdk.bean.PayNotifyBean;

/*
 * 抽象goPay支付service，所有实现类继承该类
 */
public abstract class AbstractSdkService {

	//暂未用
    private InheritableThreadLocal<String> orderNo = new InheritableThreadLocal<String>();
    
    public abstract LoginResult login(HttpServletRequest request);
    
    public abstract LogoutResult logout(HttpServletRequest request);
	
    public abstract Map<String, String> goPay(HttpServletRequest request);
    
    public abstract boolean payResultNotify(String game, String platform, HttpServletRequest request);

    public abstract boolean refund(PayNotifyBean bean);

	public InheritableThreadLocal<String> getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(InheritableThreadLocal<String> orderNo) {
		this.orderNo = orderNo;
	}
}

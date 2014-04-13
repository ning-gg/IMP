package com.pay.common.pay;

import com.pay.common.model.UserInfo;



/**
 * 此接口封装了支付平台的一般表现
 * @author gecn1
 *
 */
public interface PayCallback {
	
	
	/**
	 * 初始化回调
	 * @param result
	 */
	public void initSDKCallbackListener(int result);
	/**
	 * 登入回调
	 * @param result
	 * @param userInfo
	 */
	public void loginCallListener(int result ,String code);
	

	/**
	 * 请求应用服务器 获取用户信息回调
	 * @param result
	 * @param userInfo
	 */
	public void getUserInfoCallbackListener(int result ,UserInfo userInfo);
	
	/**
	 * 登出回调
	 */
	public void logoutCallListener(int result);
	
	/**
	 * 支付完成回调
	 * @param result
	 * @param payment
	 */
	public void payCallListener(int result,Object payment);

	/**
	 * 退出SDK 回到接口
	 * 注意要区分 是uc平台还是360平台
	 * @param result
	 */
	public void exitSDK(int result);
	
	public String getAppName();

}

package com.pay.common.activity;
import android.content.Context;
import android.os.Bundle;

import com.pay.command.platform.CommandList;
import com.pay.command.platform.PlatformPayFactoryList;
import com.pay.command.platform.Platform;
import com.pay.common.command.CommandFactory;
import com.pay.common.command.factory.PlatformCommandFactory;
import com.pay.common.command.factory.PlatformPayFactory;
import com.pay.common.model.PayInfo;
import com.pay.common.model.UserInfo;
import com.pay.common.pay.PayCallback;
import com.pay.common.pay.PayInterface;
import com.unity3d.player.UnityPlayerActivity;


public class UnityCallPayActivity extends UnityPlayerActivity implements PayCallback {

	public static String TAG = "ExtendUnityPayActivity";
	private Context mContext;
	
	private  PayInterface mPay = null;
	private CommandFactory mCommand = null;
	
	
	public UnityCallPayActivity() {
		mContext = this;
		mPay = PlatformPayFactory.createPay(mContext,this,Platform.Downjoy);
		mCommand = PlatformCommandFactory.createCommand(mContext, Platform.Downjoy);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	
	/**
	 * Unity登入接口
	 * @param userName 用户名
	 * @param passWord 密码
	 */
	public void Login(){
		mPay.Login();
	}
	
	
	
	/**
	 * Unity支付入口
	 * @param payInfo 订单信息
	 */
	public void pay(PayInfo payInfo){
		mPay.pay(payInfo);
	}
	
	/**
	 * Unity登出接口
	 */
	public void Logout(){
		mPay.LogOut();
	}
	
	/**
	 * Unity推出接口
	 */
	public void ExitSDK(){
		mPay.ExitSDK();
	}




	@Override
	public void loginCallListener(int result, String code) {
		
	}

	@Override
	public void getUserInfoCallbackListener(int result, UserInfo userInfo) {
		
	}

	@Override
	public void logoutCallListener(int result) {
	}

	@Override
	public void payCallListener(int result, Object payment) {
		
	}

	@Override
	public void initSDKCallbackListener(int result) {
		
	}

	@Override
	public void exitSDK(int result) {
		
	}

	@Override
	public String getAppName() {
		return "test";
	}
	

}

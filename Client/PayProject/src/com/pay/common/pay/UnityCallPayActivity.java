package com.pay.common.pay;
import android.content.Context;
import android.os.Bundle;

import com.pay.common.model.UserInfo;
import com.unity3d.player.UnityPlayerActivity;


public class UnityCallPayActivity extends UnityPlayerActivity implements PayCallback {

	public static String TAG = "ExtendUnityPayActivity";
	private Context mContext;
	
	
	public UnityCallPayActivity() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		//首先调用sdk初始化
	}
	
	
	/**
	 * Unity登入接口
	 * @param userName 用户名
	 * @param passWord 密码
	 */
	public void Login(String userName,String passWord){
		
	}
	
	
	public void pay(){
		
	}
	
	public void Logout(){
	}
	
	public void ExitSDK(){
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

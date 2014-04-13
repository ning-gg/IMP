package com.pay.comon.dangle;

import android.content.Context;
import android.os.Bundle;

import com.downjoy.CallbackListener;
import com.downjoy.Downjoy;
import com.downjoy.DownjoyError;
import com.pay.common.pay.PayInterface;

public class DanglePay extends PayInterface {
	
    // 初始化当乐游戏中心
    final String merchantId = "101"; // 当乐分配的MERCHANT_ID
    final String appId = "195"; // 当乐分配的APP_ID
    final String serverSeqNum = "1"; // 当乐分配的 SERVER_SEQ_NUM，
                                     // 不同服务器序列号可使用不同计费通知地址
    final String appKey = "j5VEvxhc"; // 当乐分配的 APP_KEY
    
    private Downjoy downjoy;

	public DanglePay(Context context) {
		super(context);
        downjoy = Downjoy.getInstance(mContext, merchantId, appId,
                serverSeqNum, appKey);
	}

	/**
	 * 初始配置一些支付 平台的信息
	 */
	@Override
	public void initSDK() {
		downjoy.showDownjoyIconAfterLogined(true);
        downjoy.setInitLocation(Downjoy.LOCATION_CENTER_HORIZONTAL_BOTTOM);
	}

	@Override
	public void Login() {
		downjoy.openLoginDialog(mContext,new CallbackListener() {

			@Override
			public void onLoginError(DownjoyError arg0) {
				// TODO Auto-generated method stub
				super.onLoginError(arg0);
			}

			@Override
			public void onLoginSuccess(Bundle arg0) {
				super.onLoginSuccess(arg0);
				//do
			}
			
		});

	}

	@Override
	public void LogOut() {

	}

	@Override
	public void ExitSDK() {

	}

}

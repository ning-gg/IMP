package com.pay.common.pay;

import com.pay.common.model.PayInfo;

import android.content.Context;

/**
 * 所有支付的逻辑都必须实现此接口
 * @author gcn
 *
 */

public abstract class  PayInterface {
	
	protected Context mContext;
	public PayCallback mCallback;
	
	/**
	 * @param context
	 * @param callback
	 */
	public PayInterface(Context context,PayCallback callback){
		mContext = context;
		mCallback = callback;
	}
	
	public abstract void initSDK();
	
	public abstract void Login();
	
	//public void getUserInfoFromAppServer(String code);
	
	public abstract void pay(PayInfo payInfo);
	
	public abstract void LogOut();
	
	public abstract void ExitSDK();
    
}

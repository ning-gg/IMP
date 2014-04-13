package com.pay.common.pay;

import android.content.Context;

/**
 * 所有支付的逻辑都必须实现此接口
 * @author gcn
 *
 */

public abstract class  PayInterface {
	
	protected Context mContext;
	
	public PayInterface(Context context){
		mContext = context;
	}
	
	public abstract void initSDK();
	
	public abstract void Login();
	
	//public void getUserInfoFromAppServer(String code);
	
	//public void pay(PayInfo payInfo);
	
	public abstract void LogOut();
	
	public abstract void ExitSDK();
    
}

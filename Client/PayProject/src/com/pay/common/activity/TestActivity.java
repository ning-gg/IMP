package com.pay.common.activity;



import com.example.payproject.R;
import com.pay.command.platform.CommandList;
import com.pay.command.platform.Constant;
import com.pay.command.platform.Platform;
import com.pay.common.command.Command;
import com.pay.common.command.CommandFactory;
import com.pay.common.command.factory.PlatformCommandFactory;
import com.pay.common.command.factory.PlatformPayFactory;
import com.pay.common.model.UserInfo;
import com.pay.common.pay.PayCallback;
import com.pay.common.pay.PayInterface;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class TestActivity extends Activity implements PayCallback{

	
	private  PayInterface mPay = null;
	private CommandFactory mCommand = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mPay = PlatformPayFactory.createPay(this,this,Platform.Downjoy);
		mCommand = PlatformCommandFactory.createCommand(this, Platform.Downjoy);
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mPay.Login();
			}
		});
		
		findViewById(R.id.button2).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Command ss = mCommand.getCommand(CommandList.getFriendsList);
				if(ss != null){
					ss.excuteCommand();
				}else{
					Log.d(Constant.TAG, "没有额外的功能");
				}
				
			}
		});
		
	}

	@Override
	public void initSDKCallbackListener(int result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loginCallListener(int result, String code) {
		// TODO Auto-generated method stub
		Log.d(Constant.TAG, "code = " + code);
		
	}

	@Override
	public void getUserInfoCallbackListener(int result, UserInfo userInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logoutCallListener(int result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void payCallListener(int result, Object payment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitSDK(int result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAppName() {
		// TODO Auto-generated method stub
		return null;
	}

	
}

package com.pay.common.command;

import android.content.Context;

public abstract class Command implements CommandCallback {
	
	protected Context mContext;

	
	public Command(Context context){
		mContext = context;
		
	}
	public abstract void excuteCommand();
}

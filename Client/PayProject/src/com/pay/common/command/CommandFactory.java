package com.pay.common.command;

import com.pay.command.platform.CommandList;

import android.content.Context;

/**
 * 不同平台根据需求不同进行继承实现此类
 * @author gcn
 *
 */
public  class CommandFactory {
	protected Context mContext;
	
	public CommandFactory(Context context){
		mContext = context;
	}
	
	public Command getCommand(CommandList commandList){
		return null;
	}
	
	
    
}

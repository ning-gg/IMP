package com.pay.common.command.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import com.pay.command.platform.PlatformCommandFactoryList;
import com.pay.command.platform.CommandList;
import com.pay.command.platform.Constant;
import com.pay.command.platform.Platform;
import com.pay.common.command.Command;
import com.pay.common.command.CommandFactory;

public  class PlatformCommandFactory {
	
	

	/**
	 * 根据平台参数创建 特殊功能实例
	 * 请注意，commadnFuction子类不能实现构造函数，否则在某些情况下会抛出异常
	 * 如果没有配置，则说明没有特殊功能，则默认使用基类方法
	 * @param context
	 * @param platform
	 * @param commandList
	 * @return
	 */

	public  static CommandFactory createCommand(Context context,Platform platform){
		Class<?> classCommandFactory=null;
		try {
			classCommandFactory = Class.forName(PlatformCommandFactoryList.fromPlatfom(platform));
			Log.d(Constant.TAG, PlatformCommandFactoryList.fromPlatfom(platform));
		} catch (ClassNotFoundException e) {
			Log.d(Constant.TAG, e.toString());
		}
	    //取得全部的构造函数
        Constructor<?> cons[]=classCommandFactory.getConstructors();
        CommandFactory commandFactory = null;
        try {
        	commandFactory = (CommandFactory) cons[0].newInstance(context);
		} catch (IllegalArgumentException e) {
			Log.d(Constant.TAG, e.toString());
		} catch (InstantiationException e) {
			Log.d(Constant.TAG, e.toString());
		} catch (IllegalAccessException e) {
			Log.d(Constant.TAG, e.toString());
		} catch (InvocationTargetException e) {
			Log.d(Constant.TAG, e.toString());
		}
        
		return commandFactory;
	}  
}

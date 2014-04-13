package com.pay.common.command.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import android.content.Context;
import android.util.Log;

import com.pay.command.platform.Constant;
import com.pay.command.platform.PlatformPayFactoryList;
import com.pay.command.platform.Platform;
import com.pay.common.pay.PayCallback;
import com.pay.common.pay.PayInterface;

import java.lang.reflect.*; 

/**
 * 根据平台参数动态的生成支付的实例
 * @author gcn
 *
 */
public class PlatformPayFactory {
	/**
	 * 根据平台参数创建 支付实例
	 * 请注意，PayInterface子类不能实现构造函数，否则在某些情况下会抛出异常
	 * @param context 上下文信息
	 * @param payCallback 回调信息
	 * @param platform 平台参数
	 * @return
	 */
	public  static PayInterface createPay(Context context,PayCallback payCallback,Platform platform){
		Class<?> payObject=null;
		try {
			payObject = Class.forName(PlatformPayFactoryList.fromPlatfom(platform));
		} catch (ClassNotFoundException e) {
			Log.d(Constant.TAG, e.toString());
		}
	    //取得全部的构造函数
        Constructor<?> cons[]=payObject.getConstructors();
        PayInterface pay = null;
        try {
        	pay = (PayInterface) cons[0].newInstance(context,payCallback);
		} catch (IllegalArgumentException e) {
			Log.d(Constant.TAG, e.toString());
		} catch (InstantiationException e) {
			Log.d(Constant.TAG, e.toString());
		} catch (IllegalAccessException e) {
			Log.d(Constant.TAG, e.toString());
		} catch (InvocationTargetException e) {
			Log.d(Constant.TAG, e.toString());
		}
		return pay;
	}

}

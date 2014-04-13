package com.sdk.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import com.sdk.utils.Config;


@Service
public class AppContext {
    private static final Logger log = LoggerFactory.getLogger(AppContext.class);

	private static ThreadLocal<Boolean> userTrace = new ThreadLocal<Boolean>();
	//这个订单号只能在写日志时候使用
	public final static InheritableThreadLocal<String> orderNo = new InheritableThreadLocal<String>();
	
	//qt字段目前也只用于写日志用
	public final static InheritableThreadLocal<String> bookingId = new InheritableThreadLocal<String>();
	
    public static String getOrderNoForLog(){
    	return orderNo.get();
    }
    
    public static void setOrderNoForLog(String orderno){
    	orderNo.set(orderno);
    	if( orderno != null){
    	    MDC.put("orderNo", orderno);
    	}
    }
    
	public static void removeOrderNo4Order(){
		try {
			orderNo.remove();
		} catch (Exception e) {
			log.error("remvoeOrderNo4Order error",e);
		}
	}
	
	public static String getBookingId(){
	    return bookingId.get();
	}
	
	public static void setBookingId(String bookId){
	    bookingId.set(bookId);
	    if( bookId != null){
	        MDC.put("bookId", bookId);
	    }
	}
	
	public static boolean isDebugMode() {
		return Config.isDev() && Config.getBooleanItem("debug", false);
	}

	public static void setUserTrace(boolean needLog) {
		userTrace.set(needLog);
	}
	
	public static boolean isUserTraceEnabled() {
		Boolean ret = userTrace.get();
		if (ret == null) {
			return false;
		}
		return ret;
	}	
}
package com.sdk.utils;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sdk.constants.CommonConstants;
import com.sdk.constants.PlatformAPIType;

/**
 * 订单操作类
 * @author yongshan.xing
 *
 */
public class OrderUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderUtil.class);
	
	//从订单号获取平台
	public static PlatformAPIType getPlatform(String orderNo){
		String prefix = getOrderPrefix(orderNo);
		return PlatformAPIType.fromPrefix(prefix);
	}
	
	//取订单号前缀，订单号的前三位
	public static String getOrderPrefix(String orderNo){
		if(StringUtils.isBlank(orderNo) || StringUtils.length(orderNo)<3){
			logger.error("orderNo is blank or orderNo is illegal, wrong occurs.........orderNo:{}", orderNo);
			return null;
		}
		return StringUtils.substring(orderNo, 0, 3);
	}
	
	//构建内部Sign，用于GameServer之间的通信
	//规则：map内的key自然排序，末尾加入&privateKey=privateKeyValue
	public static String makeInnerSign(Map<String, String> map){
        if (map == null || map.size() < 1) {
            logger.warn("Map is null or size is litter than 1");
            return null;
        }
        SortedMap<String, Object> sort = new TreeMap<String, Object>(map);

        StringBuffer signBuffer = new StringBuffer("");
        for (String key : sort.keySet()) {
            signBuffer.append(key).append("=").append(sort.get(key)).append("&");
        }
        signBuffer.append("privateKey=").append(CommonConstants.privateKey);
        logger.info("sign original string = " + signBuffer.toString());
        String sign = MD5Util.md5(signBuffer.toString());
        return sign;
	}
	
	//构建请求参数
	public static String buildInnerRequestString(Map<String, String> map){
        if (map == null || map.size() < 1) {
            logger.warn("Map is null or size is litter than 1");
            return "";
        }
        StringBuffer requestBuffer = new StringBuffer("");
        for (String key : map.keySet()) {
            requestBuffer.append(key).append("=").append(map.get(key)).append("&");
        }
        String sign = makeInnerSign(map);
        String requestString = null;
        if(StringUtils.isBlank(sign)){
            requestString = StringUtils.substringBeforeLast(requestBuffer.toString(), "&");
        }else{
        	requestBuffer.append("sign=").append(sign);
        	requestString = requestBuffer.toString();
        }
        logger.info("request string = " + requestString);
        return requestString;
	}
}

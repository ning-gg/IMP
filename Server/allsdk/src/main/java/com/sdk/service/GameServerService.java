package com.sdk.service;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sdk.bean.PayNotifyBean;
import com.sdk.utils.Config;
import com.sdk.utils.DateUtil;
import com.sdk.utils.HttpUtil;
import com.sdk.utils.OrderUtil;

/**
 * 与游戏服务器之间交互service
 * @author yongshan.xing
 *
 */
@Service
public class GameServerService {

	private static final Logger logger = LoggerFactory.getLogger(GameServerService.class);

	private static final String gameServerPayNotifyUrl = Config.getItem("Game_pay_notify");

    protected boolean gameServerPayOk(PayNotifyBean bean){
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("orderNo", bean.getOrderNo());
    	map.put("money", bean.getMoney());
    	map.put("time", DateUtil.formatCurTimeLong());
    	String requestString = OrderUtil.buildInnerRequestString(map);
    	try{
    		String response = HttpUtil.getFromInputStream(gameServerPayNotifyUrl+"?"+requestString, false);
    		logger.info("notify game server process payrecord responseText={}", response);
            JSONObject json = JSONObject.fromObject(response);
            if(StringUtils.equalsIgnoreCase(json.getString("result"), "SUCCESS")) {
            	logger.info("game server process payrecord result success, orderNo:{}", bean.getOrderNo());
            	return true;
            }else{
            	logger.warn("game server process payrecord result failed, orderNo:{}, errmsg:{}", bean.getOrderNo(), json.getString("errMsg"));
            	return false;
            }
    	}catch(Exception e){
    		logger.error("notify game server process payrecord error", e);
    		return false;
    	}
    }
		
}

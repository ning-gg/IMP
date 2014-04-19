package com.sdk.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdk.bean.LoginResult;
import com.sdk.bean.LogoutResult;
import com.sdk.constants.PlatformAPIType;
import com.sdk.service.SdkServiceFactory;
import com.sdk.service.base.AbstractSdkService;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private SdkServiceFactory sdkServiceFactory;

    /**
     *
     * @param gameID  用于区分不同游戏款式
     * @param platCode  用于游戏平台区分
     * @param request   请求
     * @param response
     * @return
     */
    @RequestMapping("/{gameID}/{platCode}/login.html")
    @ResponseBody
	public Map<String, Object> login(@PathVariable(value = "gameID") String gameID,
                                     @PathVariable(value = "platCode") String platCode,
                                     HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = new HashMap<String, Object>();
		PlatformAPIType apiType = PlatformAPIType.fromPrefix(platCode);
		try{
			AbstractSdkService service = sdkServiceFactory.getSdkService(apiType);
	        LoginResult loginResult = service.login(request);
	        //判断loginResult成功与否情况
	        
	        //融合gameserver个人信息结果，getUserInfo，需要根据platCode与gameid（game）

	        result.put("ret", "success");
			result.put("info", new Gson().toJson(loginResult));
		}catch(Exception e){
			logger.error("SDK Server Login ERROR.", e);
			result.put("ret", "failed");
			result.put("errmsg", "sdk server login error");
		}
        logger.info("result:{}", result);
		return result;
    }
    
    @RequestMapping("/{gameID}/{platCode}/logout.html")
    @ResponseBody
	public Map<String, Object> logout(@PathVariable(value = "gameID") String gameID,
                                      @PathVariable(value = "platCode") String platCode,
                                      HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = new HashMap<String, Object>();
		PlatformAPIType apiType = PlatformAPIType.fromPrefix(platCode);
		try{
			AbstractSdkService service = sdkServiceFactory.getSdkService(apiType);
	        LogoutResult logoutResult = service.logout(request);
	        //判断loginResult成功与否情况
	        
	        //融合gameserver个人信息结果，getUserInfo
	        
			result.put("ret", "success");
            result.put("errmsg", "");
		}catch(Exception e){
			logger.error("SDK Server Logout ERROR.", e);
			result.put("ret", "failed");
			result.put("errmsg", "sdk server logout error");
		}
		return result;
    }
}

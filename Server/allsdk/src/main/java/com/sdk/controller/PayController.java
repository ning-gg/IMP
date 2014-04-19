package com.sdk.controller;

import com.sdk.bean.LoginResult;
import com.sdk.constants.PlatformAPIType;
import com.sdk.service.SdkServiceFactory;
import com.sdk.service.base.AbstractSdkService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PayController {

    private static final Logger logger = LoggerFactory.getLogger(PayController.class);

    @Resource
    private SdkServiceFactory sdkServiceFactory;

    //支付通知
    @RequestMapping("/{gameID}/payNotify.html")
    @ResponseBody
    public Map<String, Object> payNotify(@PathVariable(value = "gameID") String gameID,
                                     HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> result = new HashMap<String, Object>();
        String orderNo = request.getParameter("order_no");//need change
        PlatformAPIType apiType = PlatformAPIType.fromPrefix(StringUtils.substring(orderNo, 0, 3));
        String errmsg = "";
        try{
            AbstractSdkService service = sdkServiceFactory.getSdkService(apiType);
            //调用支付通知接口
            boolean flag = service.payResultNotify(gameID, apiType.getPrefix(), request);
            if(!flag){
                errmsg = "处理支付回调失败。orderNo:"+orderNo;
            }
        }catch(Exception e){
            logger.error("SDK Server Process Pay Result Notify Information ERROR.", e);
            errmsg = "sdk server process payresult notify error, orderNo:"+orderNo;
        }
        if(StringUtils.isBlank(errmsg)){
            result.put("ret", "success");
        }else{
            result.put("ret", "failed");
            result.put("errmsg", errmsg);
        }
        return result;
    }
}

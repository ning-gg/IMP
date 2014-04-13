package com.sdk.service;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sdk.annotation.SDKTypeAPIImpl;
import com.sdk.bean.LoginResult;
import com.sdk.bean.LogoutResult;
import com.sdk.bean.PayNotifyBean;
import com.sdk.constants.PlatformAPIType;
import com.sdk.service.base.AbstractSdkService;
import com.sdk.utils.Config;
import com.sdk.utils.HttpUtil;
import com.sdk.utils.MD5Util;

/**
 * 当乐实现类
 * @author yongshan.xing
 * 
 */
@SDKTypeAPIImpl(platformAPIType = PlatformAPIType.Downjoy)
@Service
public class DownjoyService extends AbstractSdkService {

	private static final Logger logger = LoggerFactory.getLogger(DownjoyService.class);

	private static final String appid = Config.getItem("Downjoy_appid");
	private static final String appkey = Config.getItem("Downjoy_appkey");
	private static final String sdkServerUrl = Config.getItem("Downjoy_serverurl");
	
	private static final String paymentKey = Config.getItem("Downjoy_payment_key"); // 12位支付密钥,当乐分配

	@Resource
	GameServerService gameServerService;
	
	@Override
	public LoginResult login(HttpServletRequest request) {
		LoginResult loginResult = new LoginResult();
		String mid = request.getParameter("mid");
		String token = request.getParameter("token");
		StringBuilder builder = new StringBuilder();
		builder.append("app_id").append(appid)
				.append("mid").append(mid)
				.append("token").append(token);
		String sign = MD5Util.md5(token+"|"+appkey);
		builder.append("sig").append(sign);
		try{
			String response = HttpUtil.getFromInputStream(sdkServerUrl+"?"+builder.toString(), true);
			//解析登录结果
			JSONObject json = JSONObject.fromObject(response);
	        if(json!=null) {//登录信息
	        	loginResult.setUid(json.getLong("memberId"));
	        	loginResult.setUsername(json.getString("username"));
	        	loginResult.setNickname(json.getString("nickname"));
	        	loginResult.setGender(json.getString("gender"));
	        	loginResult.setLevel(json.getInt("level"));
	        	loginResult.setAvatarUrl(json.getString("avatar_url"));
	        	loginResult.setCreateDate(json.getString("created_date"));
	        	loginResult.setToken(json.getString("token"));
	        	loginResult.setErrCode(json.getInt("error_code"));
	        }
		}catch(Exception e){
			logger.error("登录验证token失败", e);
			return null;
		}
		return loginResult;
	}
	
	@Override
	public LogoutResult logout(HttpServletRequest request) {
		return null;
	}

	@Override
	public boolean sdkPayNotify(HttpServletRequest request) {
		try{
			String result = request.getParameter("result");//支付结果，固定值。“1”代表成功，“0”代表失败
			String money = request.getParameter("money");//支付金额，单位：元。
			String orderNo = request.getParameter("order");//本次支付的订单号。
			String memberId = request.getParameter("mid");//本次支付用户的乐号，既登录后返回的mid参数。
			String dateTime = request.getParameter("time");//时间戳，格式：yyyymmddHH24mmss月日小时分秒小于10前面补充0
			String signature = request.getParameter("signature");//MD5验证串，用于与接口生成的验证串做比较，保证计费通知的合法性。
			String ext = request.getParameter("ext");//发起支付请求时传递的eif参数，此处原样返回。
			StringBuilder builder = new StringBuilder();
			builder.append("order=").append(orderNo)
					.append("&money=").append(money)
					.append("&mid=").append(memberId)
					.append("&time=").append(dateTime)
					.append("&result=").append(result)
					.append("&ext=").append(ext)
					.append("&key=").append(paymentKey);//拼接sign原始加密串，严格按照顺序
			String sig = MD5Util.md5(builder.toString());

			if (result != null && sig.equalsIgnoreCase(signature)) { // 验证通过
				if ("1".equals(result)) {
					logger.info("{}支付成功", orderNo);
					PayNotifyBean payNotify = new PayNotifyBean(orderNo, money, memberId);
					boolean isPayOk = gameServerService.gameServerPayOk(payNotify);//进行发货
					//处理支付发货是吧，切退款失败的订单要特殊记录，存入数据库，定时任务筛查后通知负责人。
					if(!isPayOk && !refund(payNotify)){
						//save to db and record........
					}
				} else {
					logger.warn("{}支付失败！", orderNo);
				}
			} else {
				logger.error("{} sig验证失败", orderNo);
			}
		}catch(Exception e){
			logger.error("处理支付回调通知失败，接收失败！", e);
			//应记录监控或邮件通知。。。。。。
			return false;
		}
		return true;//接收信息正常处理
	}

	@Override
	public boolean refund(PayNotifyBean bean) {
		StringBuilder signBuilder = new StringBuilder();
		signBuilder.append("app_id=").append(appid)
				.append("&mid=").append(bean.getMoney())
				.append("&order_no=").append(bean.getOrderNo())
				.append("&key=").append(paymentKey);//拼接sign原始加密串，严格按照顺序
		String sign = MD5Util.md5(signBuilder.toString());
		StringBuilder builder = new StringBuilder(sdkServerUrl);
		builder.append("?")
				.append("app_id=").append(appid)
				.append("&mid=").append(bean.getMoney())
				.append("&order_no=").append(bean.getOrderNo())
				.append("&sig=").append(sign);
		String response = HttpUtil.getFromInputStream(builder.toString(), false);
		logger.info("refund result:{}", response);
        JSONObject json = JSONObject.fromObject(response);
        if(json.getInt("error_code") == 0) {//退款成功
        	return true;
        }else{
        	logger.info("refund result failed, errmsg:{}", json.getString("error_msg"));
        	return false;
        }
	}

	@Override
	public Map<String, String> goPay(HttpServletRequest request) {
		//Downjoy没有goPay惭怍
		return null;
	}
}

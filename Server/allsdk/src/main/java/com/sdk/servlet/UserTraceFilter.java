package com.sdk.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;

import com.sdk.context.AppContext;
import com.sdk.utils.UserTraceLogger;


public class UserTraceFilter implements Filter {

	public static final String COOKIE_NAME = "QN29";
	private static final UserTraceLogger userTrace = UserTraceLogger.getLogger(UserTraceFilter.class);
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		
		HttpServletRequest req = (HttpServletRequest) request;
		long startTime = System.currentTimeMillis();		
		String ip = req.getHeader("X-Real-IP");
		String uuid = null;
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (StringUtils.equalsIgnoreCase(COOKIE_NAME, cookie.getName())) {
					uuid = cookie.getValue();
				}
			}
		}
		if (uuid == null || uuid.length() != 32) {
			uuid = UUID.randomUUID().toString().replaceAll("-", "");
			Cookie cookie = new Cookie(COOKIE_NAME, uuid);
			cookie.setPath("/");
			cookie.setMaxAge(10 * 365 * 24 * 60 * 60);
			((HttpServletResponse)resp).addCookie(cookie);
		}
		UserTraceLogger.setTraceId(uuid);
		MDC.put("traceid", uuid);		
		String name = getUserName(req);
		boolean needLog = isNeedLog(req);
		AppContext.setUserTrace(needLog);
        String logParams = "";
		if (userTrace.isInfoEnabled() && needLog) {
            logParams = logParams(req);
			userTrace.info("ip=" + ip + ", name=" + name +  ", request=" + logParams);
		}
		try {
			chain.doFilter(request, resp);
		} catch (IOException e) {
			logError(e);
			throw e;
		} catch (ServletException e) {
			logError(e);
			throw e;
		} catch (RuntimeException e) {
			logError(e);
			throw e;
		} finally {
			if (userTrace.isInfoEnabled() && needLog) {
				userTrace.info("ip=" + ip + ", name=" + name +  ", request=" + logParams + " Request End, Time=" + (System.currentTimeMillis() - startTime));
			}
			MDC.remove("traceid");
		}
		
	}

	/**
	 * need expand.......
	 * @param req
	 * @return
	 */
	private String getUserName(HttpServletRequest req) {
		return null;
	}

	private boolean isNeedLog(HttpServletRequest req) {
		String uri = req.getRequestURI();
		
		if ("/user/checkUser.jsp".equals(uri) 
				|| uri.endsWith(".css")
				|| uri.endsWith(".js")
				|| uri.endsWith(".jpg")
				|| uri.endsWith(".png")
				|| uri.startsWith("/version")
				|| uri.startsWith("/favicon.ico")
				|| uri.startsWith("/healthcheck.html")) {
			return false;
		}
		
		return true;
	}

	private static String logParams(ServletRequest request) {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			Enumeration<String> names = req.getParameterNames();
			StringBuilder url = new StringBuilder();
			url.append(req.getRequestURI());
			url.append("?");
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				String[] values = request.getParameterValues(name);
				if (values == null) {
					url.append(name).append("=").append("").append("&");
				} else {
					for (String v : values) {
                        url.append(name).append("=").append(StringUtils.defaultIfEmpty(v, "")).append("&");
                    }
                }
			}
			String ret = url.toString();
			if (ret.length() > 100000) {
				ret = ret.substring(0, 100000);
			}
			return ret;
		} catch (Exception e) {
			userTrace.error("logParamsError", e);
			return "error";
		}
	}
		
	private void logError(Exception e) {
		userTrace.error("Request Error", e);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}
}
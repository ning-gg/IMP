package com.sdk.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StartListener implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(StartListener.class);

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                logger.error("uncaughtException", e);
            }
        });
        //初始化加载内容
        //new SeoCache().load();
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		
	}
}

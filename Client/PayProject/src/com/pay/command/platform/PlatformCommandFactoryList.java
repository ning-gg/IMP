package com.pay.command.platform;

import com.pay.common.command.CommandFactory;

public enum PlatformCommandFactoryList {

    KuaiYong(Platform.KuaiYong, "com.pay.xx.xx"),
    Downjoy(Platform.Downjoy,"com.pay.common.dangle.DangleCommandFactory");
		
	Platform platform;
	String className;
	
	PlatformCommandFactoryList(Platform desc, String prefix){
		this.platform = desc;
		this.className = prefix;
	}

	public String getClassName() {
		return className;
	}

	public Platform getPlatform() {
		return platform;
	}

	/**
	 * 根据平台参数返回具体实现支付的类名
	 * 如果没有配置类名，则默认返回 基类
	 * @param platfrom
	 * @return
	 */
	public static String fromPlatfom(Platform platfrom){
		for(PlatformCommandFactoryList em : PlatformCommandFactoryList.values()){
			if(em.getPlatform()==platfrom){
				return em.getClassName();
			}
		}
		return CommandFactory.class.getName();
	}
	
}

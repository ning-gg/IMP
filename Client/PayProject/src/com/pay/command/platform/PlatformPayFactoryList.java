package com.pay.command.platform;

public enum PlatformPayFactoryList {

    KuaiYong(Platform.KuaiYong, "com.pay.xx.xx"),
    Downjoy(Platform.Downjoy,"com.pay.common.dangle.DanglePay");
	Platform platform;
	String className;
	
	PlatformPayFactoryList(Platform desc, String prefix){
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
	 * 如果没有配置类名，执行默认的包名
	 * @param platfrom
	 * @return
	 */
	public static String fromPlatfom(Platform platfrom){
		for(PlatformPayFactoryList em : PlatformPayFactoryList.values()){
			if(em.getPlatform()==platfrom){
				return em.getClassName();
			}
		}
		return "com.xx.xx";
	}
	
}

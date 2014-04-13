package com.pay.command.platform;

/**
 * 所有特殊功能的映射
 * @author gcn
 *
 */
public enum CommandList {
	getFriendsList("getFriendsList","com.pay.common.dangle.DangleCommand");
	
	String className;
	String interfaceDes;
	
	private CommandList(String interfaceDes,String className) {
		this.className = className;
		this.interfaceDes = interfaceDes;
	}
	public String getInterfaceDes() {
		return interfaceDes;
	}
	public String getClassName() {
		return className;
	}
	
	/**
	 * 如果没有配置command要执行的类，则返回null
	 * @param prefix
	 * @return
	 */
	public static String fromPrefix(String prefix){
		for(CommandList em : CommandList.values()){
			if(em.getInterfaceDes().equalsIgnoreCase(prefix)){
				return em.getClassName();
			}
		}
		return null;
	}
	
}

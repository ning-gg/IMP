package com.pay.common.command;

/**
 * 特殊功能的回调函数
 * @author gcn
 *
 */
public interface CommandCallback {
	void sendMessageToUnity3D(int reslutCode,String resultContent);
}

package com.pay.common.dangle;

import android.content.Context;
import android.util.Log;

import com.pay.command.platform.Constant;
import com.pay.common.command.Command;

public class DangleCommand extends Command {

	public DangleCommand(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sendMessageToUnity3D(int reslutCode, String resultContent) {
		// TODO Auto-generated method stub
		Log.d(Constant.TAG, "sendMessageToUnity3D  "+resultContent);

	}

	@Override
	public void excuteCommand() {
		// TODO Auto-generated method stub
		Log.d(Constant.TAG, "excuteCommand  ");
		sendMessageToUnity3D(1, "success");


	}

}

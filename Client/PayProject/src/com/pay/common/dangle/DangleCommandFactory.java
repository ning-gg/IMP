package com.pay.common.dangle;

import android.content.Context;

import com.pay.command.platform.CommandList;
import com.pay.common.command.Command;
import com.pay.common.command.CommandFactory;

public class DangleCommandFactory extends CommandFactory {

	public DangleCommandFactory(Context context) {
		super(context);
	}
	
	public Command getCommand(CommandList commandList){
		switch (commandList) {
		case getFriendsList:
			return new DangleCommand(mContext);

		default:
			return null;
		}
	}

}

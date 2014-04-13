package com.pay.common.command;

public class Commad {
    public static <T> void excuteCommand(CommandFuction<T> function){
        if(function != null){
            function.excuteFunction();
        }
    }
    
}

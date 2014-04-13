package com.sdk.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    
    public static String md5(String row){
        try {
            return DigestUtils.md5Hex(row.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return "md5 error";
        }
    }
}

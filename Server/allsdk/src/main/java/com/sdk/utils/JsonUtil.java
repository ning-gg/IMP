package com.sdk.utils;

import com.google.gson.Gson;


public class JsonUtil {
	public static String writeToJson(Object object) {
		return new Gson().toJson(object, object.getClass());
	}
}

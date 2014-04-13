package com.sdk.utils;


import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DnsUtil {

	private static Logger logger = LoggerFactory.getLogger(DnsUtil.class);
	
	private static String localHost = "";
	private static String hostAddress = "";
	private static String hostName = "";
	private static byte[] rawAddress = new byte[4];
	static {
		try {
			localHost = InetAddress.getLocalHost().toString();
			hostAddress = InetAddress.getLocalHost().getHostAddress();
			rawAddress = InetAddress.getLocalHost().getAddress();
			hostName = InetAddress.getLocalHost().getHostName();
		}
		catch (UnknownHostException e) {
			logger.error("UnknownHostException:", e);
		}
	}

	public static String getLocalHost() {
		return localHost;
	}

	public static String getHostAddress() {
		return hostAddress;
	}

	public static String getHostName() {
		return hostName;
	}
	public static byte[] getRawAddress() {
		return rawAddress;
	}
}

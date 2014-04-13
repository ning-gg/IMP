package com.sdk.constants;

import org.apache.commons.lang.StringUtils;

public enum PlatformAPIType {

	KuaiYong("快用", "kyo"),
	WanDouJia("豌豆荚", "wdj"),
	Downjoy("当乐", "dle"),
	Lenovo("联想", "len"),
	GFan("机锋", "jif"),
	_91("91", "p91"),
	PP("PP", "ppp"),
	AnZhi("安智", "anz"),
	ChangWan("畅玩", "cwn"),
	HuaWei("华为", "haw"),
	KuGou("酷狗", "kgo"),
	OuPeng("欧朋", "oup"),
	SouGou("搜狗", "sog"),
	TianYi("天翼空间", "tyi"),
	Amazon("亚马逊", "amz"),
	YingYongHui("应用汇", "yyh"),
	_17173("17173", "171"),
	_360("360", "360"),
	oppo("oppo", "opo"),
	UC("uc", "puc"),
	DuoKu("百度多酷", "dok"),
	KuWo("酷我", "kwo"),
	Unicom("联通", "uco"),
	MuMaYi("木蚂蚁", "mmy"),
	SiKai("斯凯", "sik"),
	TongBuTui("同步推", "tbt"),
	XiaoMi("小米", "xmi"),
	YiDongMM("移动MM", "ydm"),
	Tencent("腾讯", "ten");
	
	String desc;
	String prefix;
	
	PlatformAPIType(String desc, String prefix){
		this.desc = desc;
		this.prefix = prefix;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public static PlatformAPIType fromPrefix(String prefix){
		for(PlatformAPIType em : PlatformAPIType.values()){
			if(StringUtils.equalsIgnoreCase(em.getPrefix(), prefix)){
				return em;
			}
		}
		return null;
	}
}
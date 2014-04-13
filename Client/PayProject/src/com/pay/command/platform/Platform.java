package com.pay.command.platform;


/**
 * 所有平台的枚举 这枚举应该是与服务器是对应的
 * @author gcn
 *
 */
public enum Platform {
	downjoy("downjoy",1);
	
	private String name;
	private int index;
	
	private Platform(String name ,int index) {
		this.name = name;
		this.index = index;
	}

	
	public static String getName(int index) {
        for (Platform c : Platform.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}

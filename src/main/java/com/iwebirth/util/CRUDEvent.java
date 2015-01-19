package com.iwebirth.util;
/**
 * @author YY_410
 * 2015-1-19
 * ref:http://www.cnblogs.com/happyPawpaw/archive/2013/04/09/3009553.html
 * **/
public enum CRUDEvent {
	
	SAVE_SUCCESS(1),SAVE_FAIL(2),SAVE_EXCEPTION(3),
	QUERY_SUCCESS(4),QUERY_FAIL(5),QUERY_EXCEPTION(6);
	
	private int value;
	private CRUDEvent(int value){
		this.value = value;
	}
	
	/**
	 * 返回对应的数值
	 * **/
	public int getValue(){
		return value;
	}
	/**
	 * 返回name(调用的是enum的name()方法)
	 * **/
	public String getName(){
		return this.name();
	}

	public static String getNameByValue(int value) {
        for (CRUDEvent c : CRUDEvent.values()) {
            if (c.getValue() == value) {
                return c.name();
            }
        }
        return null;
    }
}

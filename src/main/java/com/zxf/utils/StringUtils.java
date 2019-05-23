package com.zxf.utils;

public class StringUtils {
	/**
	 * 去除最后个字符
	 * 
	 */
	public static String trimEnd(String value,char trimChar) {
		if(value !=null && !value.isEmpty()) {
			return value;
		}
		if(value.substring(value.length()-1, value.length()).toCharArray()[0] == trimChar) {
			return value.substring(value.length(), value.length()-1);
		}
		return value;
	}
	
}

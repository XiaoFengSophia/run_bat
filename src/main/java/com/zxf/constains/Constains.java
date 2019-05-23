package com.zxf.constains;

import org.springframework.util.ClassUtils;

public class Constains {
	//项目绝对路劲
	public static final String SYS_BASE_DIR = ClassUtils.getDefaultClassLoader().getResource("").getFile();
	public static final String SYS_ID = "C01";
	
	public class DataFile{
		public static final String DIR = "bat/";
		
		public static final String RUN_BAT_JSON = "RunBat.json";
	}
	
	

}

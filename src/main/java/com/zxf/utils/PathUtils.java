package com.zxf.utils;

public class PathUtils {
	public static String projectPath() {
		String resourcePath = PathUtils.class.getClassLoader().getResource("").toString();
		int len = resourcePath.length();//47
		int indexLen = resourcePath.indexOf("/target");//31
		String path = "";
		if(resourcePath.startsWith("file")) {
			//path = resourcePath.substring(6, resourcePath.indexOf("/target")).toString();
			//int length = path.length();
			System.out.println("resourcePath:" + resourcePath);
		}else if(resourcePath.startsWith("war:file")) {
			path = resourcePath.substring(10, resourcePath.indexOf("run_bat.war!/"));
		}
		if(!path.isEmpty()) {
			path = StringUtils.trimEnd(path, '/') + "/";
		}
		return path;
	}
	

}
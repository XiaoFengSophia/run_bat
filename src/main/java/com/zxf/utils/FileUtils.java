package com.zxf.utils;

import java.io.*;
public class FileUtils {
	public static String readFile(String path) {
		try {
			File file = new File(path);//定义一个file对象，用来初始化FileReader
	        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
	        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
	        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
	        String s = "";
	        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
	            sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
	        }
	        bReader.close();
	        return sb.toString();
		} catch (Exception e) {
			return "";
		}
	}

}

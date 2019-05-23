package com.zxf.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
public class RunBat {

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("dir")
	private String dir;
	
	@JsonProperty("file")
	private String file;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public RunBat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RunBat(String name, String dir, String file) {
		super();
		this.name = name;
		this.dir = dir;
		this.file = file;
	}

	@Override
	public String toString() {
		return "RunBat [name=" + name + ", dir=" + dir + ", file=" + file + "]";
	}
	
	


}

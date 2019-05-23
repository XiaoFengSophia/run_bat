package com.zxf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.util.ClassUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxf.constains.Constains;
import com.zxf.entity.RunBat;
import com.zxf.utils.FileUtils;
import com.zxf.utils.PathUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
@SpringBootApplication
public class RunBatApplication extends SpringBootServletInitializer{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
 
    	return builder.sources(SpringApplicationBuilder.class);
	}
	@PostConstruct//在初始化的时候初始化静态对象和它的静态成员变量bean对象，静态存储下来，防止被释放
    public void init() {
		List<RunBat> list = new ArrayList<RunBat>();
		try {
     		//String jsonFile = PathUtils.projectPath()+Constains.DataFile.DIR+Constains.DataFile.RUN_BAT_JSON;
			String jsonFile = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/RunBat.json";
			logger.error(jsonFile);
            String jsonStr = FileUtils.readFile(jsonFile);
            if(jsonStr == null || jsonStr.isEmpty()) {
            	logger.error("123456789"+jsonFile+"中没有任何可运行的bat文件！");
            }else {
            	ObjectMapper mapper = new ObjectMapper();
             	JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, RunBat.class);
             	list = mapper.readValue(jsonStr.getBytes("UTF-8"), javaType);
             	for(int i=0;i<list.size();i++) {
             		String cmd = "cmd /k start "+list.get(i).getDir()+"\\"+list.get(i).getFile();// pass
             		System.out.println(cmd);
             		 Runtime.getRuntime().exec(cmd);
             	}
            }
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}  catch (IOException e) {
            // TODO Auto-generated catch block
        	logger.error(e.getMessage());
        }
	}

	public static void main(String[] args) {
		SpringApplication.run(RunBatApplication.class, args);
	}

}

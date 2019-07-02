package com.zxf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.util.ClassUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxf.entity.RunBat;
import com.zxf.utils.FileUtils;
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
		//this.runBat();
		this.runXml();
	}
	
	/**
	 *  xml文件
	 *  url: /run_bat/src/main/resources/static/RunXmlt.xml
	 *  @author zhaoxiaofeng
	 *  @deprecated：总体思路：DocumentBuilderFactory--->DocumentBuilder--->Document
	 *  			 通过Document可获取所有的节点，拿到节点通过遍历可获取其属性和值
	     *        加载并执行
	 */
	public void runXml() {
		try {
			String xmlFile = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/RunXml.xml";
			logger.info("RunXml所在路劲："+xmlFile);
			//获得DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			//获得DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			//获得Document
			Document document = builder.parse(new File(xmlFile));
			NodeList productList = document.getElementsByTagName("Product");
			//遍历productList
			for(int i=0; i<productList.getLength(); i++) {
				//通过 item(i)方法 获取一个Product节点，nodelist的索引值从0开始
                Node Product = productList.item(i);
                //获取Product节点的所有属性集合
                NamedNodeMap attrs = Product.getAttributes();
                //遍历属性
                StringBuffer cmd = new StringBuffer();
                cmd.append("cmd /k start ");
                for(int j=0; j<attrs.getLength(); j++) {
                	 //通过item(index)方法获取Product节点的某一个属性
                    Node attr = attrs.item(j);
                    //获取属性名和获取属性值
                    logger.info("属性名：" + attr.getNodeName()+"------"+"属性值：" + attr.getNodeValue());
                    if(j==1) {
                    	cmd.append(attr.getNodeValue()+"/");// pass
                    }else if(j==2) {
                    	cmd.append(attr.getNodeValue());
                    	logger.info(cmd.toString());
                    	Runtime.getRuntime().exec(cmd.toString());
                    }
                }
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	/**
	 *  json文件
	 *  url: /run_bat/src/main/resources/static/RunBat.json
	 *  @author zhaoxiaofeng
	     *        加载并执行
	 */
	public void runBat() {

		List<RunBat> list = new ArrayList<RunBat>();
		try {
     		//String jsonFile = PathUtils.projectPath()+Constains.DataFile.DIR+Constains.DataFile.RUN_BAT_JSON;
			String jsonFile = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/RunBat.json";
			logger.info(jsonFile);
            String jsonStr = FileUtils.readFile(jsonFile);
            if(jsonStr == null || jsonStr.isEmpty()) {
            	logger.error("123456789"+jsonFile+"中没有任何可运行的bat文件！");
            }else {
            	ObjectMapper mapper = new ObjectMapper();
             	JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, RunBat.class);
             	list = mapper.readValue(jsonStr.getBytes("UTF-8"), javaType);
             	for(int i=0;i<list.size();i++) {
             		String cmd = "cmd /k start "+list.get(i).getDir()+"/"+list.get(i).getFile();// pass
             		logger.info(cmd);
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

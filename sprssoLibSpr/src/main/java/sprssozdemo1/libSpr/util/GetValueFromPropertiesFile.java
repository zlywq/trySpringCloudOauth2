package sprssozdemo1.libSpr.util;




import java.io.*;
import java.util.*;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;

import sprssozdemo1.libCommon.util.*;

/*
 * spring获取webapplicationcontext,applicationcontext几种方法详解 http://www.blogjava.net/Todd/archive/2010/04/22/295112.html
 * Spring使用程序方式读取properties文件 http://outofmemory.cn/code-snippet/2770/Spring-usage-program-mode-duqu-properties-file
 * 
 */

public class GetValueFromPropertiesFile implements ApplicationContextAware  {
	
	public static final String CommonConfig_key_ProductMode = "ProductMode";
	
	public static ApplicationContext m_ApplicationContext;
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		m_ApplicationContext = ctx;
		load();
	}
	
	private String m_filePath;
	public void setFilePath(String filePath){
		m_filePath = filePath;//看来setFilePath比setApplicationContext先调用，从而有空指针可能
		load();
	}
	
	private Properties m_Properties = null;
	void load(){
		if (m_Properties != null)
			return;
		if (m_ApplicationContext == null || m_filePath == null){
			return;
		}
		
		//Resource resource = new ClassPathResource(m_filePath);
		
		//Resource resource = ApplicationContextFactory.getApplicationContext().getResource("classpath:com/springdemo/resource/test.txt");
		//NO class ApplicationContextFactory
		
		//ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		//Resource[] resources = resolver.getResources("file:C:/Documents and Settings/Administrator/桌面/*.rar");
		
		Resource resource = m_ApplicationContext.getResource(m_filePath);
		
		try {
			m_Properties = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			DemoBaseException.throwEx2(ErrCode.Dev_Common,e, m_filePath,resource);
		}			
	}
	
	Properties getProperties(){
		return m_Properties;
	}
	public Properties getAllPropInFileExceptPwd(){
		Properties propData = new Properties();//new Properties(getProperties());//such code cause no data
		propData.putAll(getProperties());
		
//		propData.propertyNames()
		Set<String> keySet = propData.stringPropertyNames();
		for (String key : keySet) {
			if (key.contains("username") || key.contains("password")){
				propData.remove(key);
			}
		}  
		return propData;
	}
	
//	public String getMongoDBUrl(){
//		String s = m_Properties.getProperty("mongo.url");
//		return s;
//	}
	
	public boolean isReplicationSetMongo(){
		String serverAddresses = getServerAddressesForReplicationSet();
		boolean b = !Util1.isStringEmpty(serverAddresses);
		return b;
	}
	public String getServerAddressesForReplicationSet(){
		String s = m_Properties.getProperty("mongo.serverAddressesForReplicationSet");
		return s;
	}
	public String getMongoHost(){
		String s = m_Properties.getProperty("mongo.host");
		return s;
	}
	public int getMongoPort(){
		String s = m_Properties.getProperty("mongo.port");
		int i = Integer.parseInt(s);
		return i;
	}
	public String getMongoDatabaseName(){
		String s = m_Properties.getProperty("mongo.dbName");
		return s;
	}
	public String getMongoUserAuthDB(){
		String s = m_Properties.getProperty("mongo.userAuthDB");
		return s;
	}
	public String getMongoUserName(){
		String s = m_Properties.getProperty("mongo.username");
		return s;
	}
	public String getMongoUserPassword(){
		String s = m_Properties.getProperty("mongo.password");
		return s;
	}
	public int getMongoConnectionsPerHost(){
		String s = m_Properties.getProperty("mongo.connectionsPerHost");
		int i = Integer.parseInt(s);
		return i;
	}
	
	public String getMongoAsQueueHost(){
		String s = m_Properties.getProperty("mongoAsQueue.host");
		return s;
	}
	public int getMongoAsQueuePort(){
		String s = m_Properties.getProperty("mongoAsQueue.port");
		int i = Integer.parseInt(s);
		return i;
	}
	public String getMongoAsQueueDatabaseName(){
		String s = m_Properties.getProperty("mongoAsQueue.dbName");
		return s;
	}
	public String getMongoAsQueueUserAuthDB(){
		String s = m_Properties.getProperty("mongoAsQueue.userAuthDB");
		return s;
	}
	public String getMongoAsQueueUserName(){
		String s = m_Properties.getProperty("mongoAsQueue.username");
		return s;
	}
	public String getMongoAsQueueUserPassword(){
		String s = m_Properties.getProperty("mongoAsQueue.password");
		return s;
	}
	public int getMongoAsQueueConnectionsPerHost(){
		String s = m_Properties.getProperty("mongoAsQueue.connectionsPerHost");
		int i = Integer.parseInt(s);
		return i;
	}
	
	
	
	
	public String getProductMode(){
		String s = m_Properties.getProperty(CommonConfig_key_ProductMode);
		return s;
	}

}

package Reader;


import java.io.FileReader;
import java.util.Properties;

import BaseApiLayer.BaseApi;

public class PropertyReader extends BaseApi{
	
	public static String getProperty(String keyName) throws Exception
	{
	Properties prop= new Properties();
	String path=System.getProperty("user.dir")+"/src/main/resources/config.properties";
	
	FileReader file= new FileReader(path);
	
	prop.load(file);
	
	return prop.getProperty(keyName);
	}
}

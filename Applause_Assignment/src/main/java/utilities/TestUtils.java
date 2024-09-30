package utilities;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TestUtils extends BaseClass
{
	public static String path1;
	public static String path2;

	
	public static Logger log()
	{

		return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
	}

	public static String getGlobalValue(String key)
	{
		String value = null;
		try {
			path1 = System.getProperty("user.dir") + "/src/test/resources/data/data.properties";
			
			FileInputStream file = new FileInputStream(path1);
			Properties prop = new Properties();
			prop.load(file);
			value = prop.get(key).toString();
			
		} catch (Exception e) {
			TestUtils.log().error("Exception Occured while loading the Properties file" + e);
		}
		return value;
	}
	
//	public static String getURL(String key) 
//	{
//		String value = null;
//		try {
//			path1 = System.getProperty("user.dir") + "/src/test/resources/data/data.properties";
//			
//			FileInputStream file = new FileInputStream(path1);
//			Properties prop = new Properties();
//			prop.load(file);
//			value = prop.get(key).toString();
//		} catch (Exception e) {
//			TestUtils.log().error("Exception Occured while loading the Properties file" + e);
//		}
//		return value;
//	}

}

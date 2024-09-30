package utilities;

import java.util.Collections;
import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v129.network.Network;
import org.openqa.selenium.devtools.v129.network.model.RequestId;
import org.openqa.selenium.devtools.v129.network.model.Response;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import io.restassured.path.json.JsonPath;


public class BrowserSetupWeb
{
	public RemoteWebDriver driver = null;
	public static DevTools devTools;
	
	public static JsonPath jspValue;
	public static String aa;
	
	public static String responseBody1;
	public static String responseBody2;
	
	public RemoteWebDriver initializeDriver(String browserName) throws InterruptedException 
	{	
		try {
			
			if(browserName.equals("chrome"))
			{
				DesiredCapabilities cap = new DesiredCapabilities();
				ChromeOptions options = new ChromeOptions();
				
//				options.addArguments("--headless");
	//			options.addArguments("--headless=new");		//--window-size won't work
				
				options.addArguments("--incognito");
				options.addArguments("--disable-features=IsolateOrigins,site-per-process");
				options.addArguments("--disable-site-isolation-trials");
		        options.addArguments("--disable-application-cache");
		        options.addArguments("--disk-cache-size=0");
		        options.addArguments("--aggressive-cache-discard");
		        options.addArguments("--disable-cache");
		        options.addArguments("--disable-web-security");
				options.addArguments("enable-automation");
				options.addArguments("disable-infobars");
				options.addArguments("--disable-notifications");
				options.addArguments("--ignore-certificate-errors");
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-javascript");
				options.addArguments("--test-type");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--no-sandbox");
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--window-size=1440,900");
				options.addArguments("--disable-gpu");
				options.addArguments("--dns-prefetch-disable");
				options.addArguments("--disable-dev-shm-usage");
//				options.addArguments("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36");
				
	//			options.addArguments("--remote-debugging-port=9222"); //devTool not connected issue
	//			options.addArguments("--allow-insecure-localhost");
				
				options.setExperimentalOption("w3c", true);
				options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				
				cap.setCapability(ChromeOptions.CAPABILITY, options);
	
				driver = new ChromeDriver(options);
				driver.manage().deleteAllCookies();
				
				devTools = ((ChromeDriver) driver).getDevTools();
				devTools.createSession();
				
		        devTools.send(Network.enable(Optional.of(1000000), Optional.empty(), Optional.empty()));
					
			}else if(browserName.equals("safari")) {
				
				driver = new SafariDriver();
			}else if(browserName.equals("edge")) {
				
				driver = new EdgeDriver();
			}else {
				Assert.assertEquals(false, true, "Failed to setup browser driver");
			}
			
			driver.manage().window().maximize();
		}
		catch (Exception e) {
			System.out.println("Exception occured while starting the browser " + e.getMessage());
		}
		
		return driver;
	}
	
	public static void readAPIresponse1(String query)
	{
			devTools.addListener(Network.responseReceived(), responseReceived -> {
			Response response = responseReceived.getResponse();
			
			String responseURL = response.getUrl();
			
			RequestId requestID = responseReceived.getRequestId();
			
			if (responseURL.contains(query)) 
            {
				TestUtils.log().info(query + " API initiated");
				ExtentManager.test.log(Status.PASS, query + " API initiated");
				
				System.out.println("Url: " + responseURL);
				
				try {
//					devTools.send(Network.disable());
					responseBody1 = devTools.send(Network.getResponseBody(requestID)).getBody();

					TestUtils.log().info("Response body JSON: " +responseBody1);
					ExtentManager.test.log(Status.INFO, "Response body JSON: " +responseBody1);
	
                }catch(Exception e)
                {
//                	TestUtils.log().info("Exception: "+e);
                }
				
                
            }
 
        });
	}
	
	public static void readAPIresponse2(String query)
	{
			devTools.addListener(Network.responseReceived(), responseReceived -> {
			Response response = responseReceived.getResponse();
			
			String responseURL = response.getUrl();
			
			RequestId requestID = responseReceived.getRequestId();
			
			if (responseURL.contains(query)) 
            {
				TestUtils.log().info(query + " API initiated");
				ExtentManager.test.log(Status.PASS, query + " API initiated");
				
				System.out.println("Url: " + responseURL);
				
				try {
					responseBody2 = devTools.send(Network.getResponseBody(requestID)).getBody();

					TestUtils.log().info("Response body JSON: " +responseBody2);
					ExtentManager.test.log(Status.INFO, "Response body JSON: " +responseBody2);	
					
                }catch(Exception e)
                {
//                	TestUtils.log().info("Exception: "+e);
                }
				
                
            }
 
        });
	}
	
	


}

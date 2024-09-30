package utilities;

import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;

public class BaseClass 
{
	public static RemoteWebDriver driver = null;
	public static TakesScreenshot takesScreenshot;
	public static int totalCase=0;
	public static int failedCase=0;
	public static String environment;
	public static Scanner sc;
	public static String input;
	public static String url;
	public static BrowserSetupWeb bs1;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;

	@BeforeSuite
	public void environmentSetup() throws IOException
	{
		ExtentManager.setExtent();
		
		//Environment setup
		
		//For Jenkins parameter
//		environment = System.getProperty("selectEnvironment");
//		
//		if(environment == null)
//		{
//			//For local parameter
//			System.out.println("\n--- Provide any of the env for execution --- \n"
//					+ "1 for Prod \n"
//					+ "2 for QA");
//			
//			sc = new Scanner(System.in);
//			input = sc.nextLine();
//						
//			switch(input)
//			{
//				case "1":
//					url= TestUtils.getGlobalValue("urlProd");
//					break;
//				case "2":
//					url= TestUtils.getGlobalValue("urlQA");
//					break;
//				default:
//					TestUtils.log().info("No matching URL");
//			}
//			
//		}
	}
	
	@BeforeClass
	@Parameters("browserName")
	public void launchBrowser(@Optional("chrome") String browserName) throws InterruptedException
	{				
		bs1 = new BrowserSetupWeb();
		driver = bs1.initializeDriver(browserName);
		
		TestUtils.log().info("Testing on: "+browserName);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		openUrl();
		
	}
	
    public void openUrl() 
	{
    	String url = TestUtils.getGlobalValue("urlProd");
		driver.navigate().to(url);
		TestUtils.log().info("Open URL: "+url);
	}
	
	@AfterClass
	public void closeDriver()
	{
		try {
			driver.quit();
			TestUtils.log().info("Browser closed");
			ExtentManager.test.log(Status.INFO, "Browser closed");
		}catch(Exception e)
		{
			driver.quit();
			TestUtils.log().info("Browser closed");
			ExtentManager.test.log(Status.INFO, "Browser closed");
		}	
	}
	
	@AfterSuite
	public void afterSuite()
	{
		ExtentManager.endReport();
	}
	
	
	//Reusable actions
	
	public String getPageTitle() throws InterruptedException
	{
		Thread.sleep(2000);
		
		String title = driver.getTitle();
		TestUtils.log().info("Page title is: "+title);
		ExtentManager.test.log(Status.INFO, "Page title is: "+title);
		return title;
	}
	
	public boolean isElementVisible(WebElement element, String msg)
	{
		boolean result=false;
		try {
			wait = new WebDriverWait (driver,Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOf(element));

			result = element.isDisplayed();
			if(result)
			{
				TestUtils.log().info(msg);
				ExtentManager.test.log(Status.PASS, msg);
			}else {
				TestUtils.log().error("Failed: "+msg);
				ExtentManager.test.log(Status.FAIL, "Failed: "+msg);
			}
		}catch(Exception e)
		{
			TestUtils.log().error("Exception occurred: "+e);
			ExtentManager.test.log(Status.FAIL, "Exception occurred: "+e);
		}
		
		return result;
	}
	
	public boolean isElementNotVisible(WebElement element, String msg)
	{
		boolean result=false;
		try {
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			
			wait = new WebDriverWait (driver,Duration.ofSeconds(3));
			wait.until(ExpectedConditions.invisibilityOf(element));
			
			result = element.isDisplayed();
			if(result)
			{
				TestUtils.log().error("Failed to: "+msg);
				ExtentManager.test.log(Status.FAIL,"Failed to: "+msg);
				result = false;
			}else {
				TestUtils.log().info(msg);
				ExtentManager.test.log(Status.PASS,msg);
				result = true;
			}
		}catch(Exception e)
		{
//			TestUtils.log().info("Exception occurred: "+e);
//			ExtentManager.test.log(Status.FAIL,"Exception occurred: "+e);
			TestUtils.log().info(msg);
			ExtentManager.test.log(Status.PASS,msg);
			result = true;
		}
			
		
		return result;
	}
	
	public boolean isElementVisibleDummy(WebElement element, String msg)
	{
		boolean result=false;
		try {
			wait = new WebDriverWait (driver,Duration.ofSeconds(3));
			wait.until(ExpectedConditions.visibilityOf(element));

			result = element.isDisplayed();
			if(result)
			{
				TestUtils.log().info(msg);
				ExtentManager.test.log(Status.PASS, msg);
			}else {
				TestUtils.log().error("Failed: "+msg);
				ExtentManager.test.log(Status.FAIL, "Failed: "+msg);
			}
		}catch(Exception e)
		{
			
		}
		
		return result;
	}
	
	public boolean clickElement(WebElement element, String msg)
	{
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			
			element.click();
			TestUtils.log().info(msg);
			ExtentManager.test.log(Status.INFO, msg);
			flag = true;
		}catch(Exception e)
		{
			TestUtils.log().error("Failed "+msg);
			ExtentManager.test.log(Status.FAIL, "Failed "+msg);
		}
		
		return flag;
	}
	
	public boolean clickElementDummy(WebElement element, String msg)
	{
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			
			element.click();
			TestUtils.log().info(msg);
			ExtentManager.test.log(Status.INFO, msg);
			flag = true;
		}catch(Exception e)
		{
			
		}
		
		return flag;
	}
	
	public String getElementText(WebElement element, String msg)
	{
		String text = "";
		try {
			text = element.getText();
			TestUtils.log().info(msg+" : "+text);
			ExtentManager.test.log(Status.INFO, msg+" : "+text);
		}catch(Exception e)
		{
			TestUtils.log().error("Failed to get text");
			ExtentManager.test.log(Status.FAIL, "Failed to get text");
		}
		
		return text;
	}
	
	public boolean scrollTillElement(WebElement element, String msg)
	{
		boolean result = false;
		try {
			js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
			TestUtils.log().info(msg);
			ExtentManager.test.log(Status.INFO,msg);
			result = true;
		}catch(Exception e)
		{
			TestUtils.log().error("Failed to scroll: "+e);
			ExtentManager.test.log(Status.FAIL,"Failed to scroll: "+e);
		}
		
		return result;
	}

}

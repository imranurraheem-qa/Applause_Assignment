package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;


public class ListenersClass extends ExtentManager implements ITestListener
{

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getDescription());
		
//		BaseClass.totalCase=BaseClass.totalCase+1;
//		String desc = null;
//		if(result.getParameters() != null) {
//		Object[] obj= result.getParameters();
//		List<Object> list = new ArrayList<Object>();
//	    for (Object strTemp : obj){
//	      list.add(strTemp);
//	    }
//	    desc = "With Parameter " + list.toString();
//		}else {
//			desc = result.getMethod().getDescription();
//		}
		
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Pass Test case : " + result.getName());
		}
	}

	public void onTestFailure(ITestResult result) 
	{
		BaseClass.failedCase=BaseClass.failedCase+1;
		
		if (result.getThrowable() != null) {

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			result.getThrowable().printStackTrace(pw);
			System.out.println(sw.toString());
		}
		
		
		try {
			TakesScreenshot takesScreenshot = (TakesScreenshot) BaseClass.driver;
			File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
			
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");  
			String timeStamp = formatter.format(date);
			System.out.println("Current date: "+timeStamp);
			
			String path="./screenshots/"+timeStamp+"_"+result.getName()+" Failed"+".png";
			
			System.out.println("screenshot path:- "+path);
			String base64="";
			try {
				FileUtils.copyFile(screenshot, new File(path));
				InputStream is = new FileInputStream(path);
				byte[] ssByte = IOUtils.toByteArray(is);
				base64 = Base64.encodeBase64String(ssByte);
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			if (result.getStatus() == ITestResult.FAILURE) 
			{
				try {
					test.log(Status.FAIL,
							MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
					test.log(Status.FAIL,
							MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
					
					//test.addScreenCaptureFromPath(System.getProperty("user.dir")+"/screenshots/"+timeStamp+"_"+result.getName()+" Failed"+".png");
					test.addScreenCaptureFromBase64String(base64);
					//test.log(Status.FAIL, (Markup) test.addScreenCaptureFromPath(System.getProperty("user.dir")+"/screenshots/"+result.getName()+" Failed"+".png"));
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}catch(Exception e1)
		{
			TestUtils.log().error("Exception occurred: "+e1);
			ExtentManager.test.log(Status.FAIL, "Exception occured: "+e1);
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Skipped Test case is: " + result.getName());
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
	}

}

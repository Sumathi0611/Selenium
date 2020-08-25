package com.sse.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.sse.test.TestBase;

public class ListenerTest implements ISuiteListener, ITestListener, IInvokedMethodListener {
	
    private static Logger Log = Logger.getLogger(ListenerTest.class);

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ISuite arg0) {
		Log.info(arg0.getName() + "---Test Suite Completed---\n");		
	}

	@Override
	public void onStart(ISuite arg0) {
		Log.info(arg0.getName() + "---Test Suite starting---\n");		
	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		Log.info(tr.getName() + "---Test method failed---\n");
    	takeScreenShot(tr);		
	}
	
	public void takeScreenShot(ITestResult result) {
    	//get the driver
		Object currentclass=result.getMethod().getInstance();
    	WebDriver driver=((TestBase) currentclass).getDriver();

		String status="failed";
		if(result.isSuccess())
		{
			status="passed";
		}
    	String methodName=result.getName().toString().trim();
    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            	String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
            	String workingDir = System.getProperty("user.dir");
				FileUtils.copyFile(scrFile, new File(workingDir+"\\Screenshots\\"+status+"\\"+methodName+"-"+timeStamp+".png"));
				System.out.println(new File(workingDir+"\\Screenshots\\"+status+"\\"+methodName+"-"+timeStamp+".png"));
		} catch (IOException e) {
				e.printStackTrace();
		}
    }

	@Override
	public void onTestSkipped(ITestResult tr) {
		Log.info(tr.getName() + "---Test method blocked---\n");		
	}

	@Override
	public void onTestStart(ITestResult tr) {
		Log.info(tr.getName() + "---Test method starting---\n");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		// TODO Auto-generated method stub
		Log.info(tr.getName() + "---Test method passed---\n");
    	takeScreenShot(tr);	
	}
}
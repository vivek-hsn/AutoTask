package com.myauto.reports;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.myauto.base.BaseTest;


public class MyListnerReport extends BaseTest implements ITestListener{
	
	static ExtentTest test = null;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	public void onStart(ITestContext context){
		System.out.println("On the start");

		
	}
		
	public void onTestStart(ITestResult result){
		
		System.out.println("On Test Start");
		test = extent.createTest(result.getMethod().getMethodName() + browserName);
//		test = reports.startTest(result.getMethod().getMethodName() + browserName);
//		test.log(Status.INFO, result.getMethod().getMethodName() + browserName + " method is started");
		extentTest.set(test);
		
	}
	
	public void onTestSuccess(ITestResult result){
		
		System.out.println("On Test Success");
		test.log(Status.PASS, result.getMethod().getMethodName() + browserName + " method is Passed");
		extentTest.get().log(Status.PASS, result.getMethod().getMethodName() + browserName + " method is Passed");
	}
	
	public void onTestSkipped(ITestResult result){
		System.out.println("On test skipped");
		test.log(Status.SKIP, result.getMethod().getMethodName() + browserName + " method is Skipped");
		extentTest.get().log(Status.SKIP, result.getMethod().getMethodName() + browserName + " method is Skipped");
	}
	
	public void onTestFailure(ITestResult result){
		
		System.out.println("On test failure");
	    test.log(Status.FAIL, result.getMethod().getMethodName() + browserName + " method is failed");
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\results\\" + result.getMethod().getMethodName() + browserName +".png";
		try{
			FileUtils.copyFile(src, new File(path));
			extentTest.get().fail(result.getThrowable());
			extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName() + browserName);
//			test.log(Status.FAIL, result.getMethod().getMethodName() + browserName + " method is failed", file);
//			test.log(Status.FAIL, result.getMethod().getMethodName() + browserName + " method is failed", result.getThrowable().getMessage());
//			extentTest.get().log(Status.FAIL, result.getMethod().getMethodName() + browserName + " method is failed", result.getThrowable().getMessage());
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result){
		System.out.println("On test Failed but with Success Percentage");
	}
	
	public void onFinish(ITestContext context){
		System.out.println("On Finish");
	}

}

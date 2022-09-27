package utilltest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;
import utill.Base;

public class Listeners implements ITestListener{

	WebDriver driver;
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	//to resolve concurrency issue (Multiple test cases executing parallel way
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>(); //Thread safe: means no matter it runs concurrently, each object creation has it's own thread
	@Override
	public void onTestStart(ITestResult result) {
		ITestListener.super.onTestStart(result);
		
		// Before Starting the Test
		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //Pushing main object in ThreadLOcal so it will assign a unique thread id
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ITestListener.super.onTestSuccess(result);
		
		// On the TestCases are Passed
		extentTest.get().log(Status.PASS, "TestCase PASSED");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		ITestListener.super.onTestFailure(result);
		
		//On the TestCases are Failed
		extentTest.get().fail(result.getThrowable());
		//Screenshot, attached in Report
		Base baseTest=new Base();
		
		//WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String filepath = null;
		try {
			
			filepath = baseTest.getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		ITestListener.super.onFinish(context);
		
		//Report finally generated, without flush it will not available
		extent.flush();
	}



}

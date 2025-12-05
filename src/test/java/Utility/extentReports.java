package Utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseClass.BaseClass;

public class extentReports implements ITestListener{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	public void onStart(ITestContext context) {
		
		String format = new SimpleDateFormat("yy/mm/dd").format(new Date());//getting the current timestamp
		repName = "Extent-Report"+ format +".html";//getting the name for report
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);//path of report
		sparkReporter.config().setDocumentTitle("Opencart Automation report");
		sparkReporter.config().setReportName("Automation report");
		sparkReporter.config().setTheme(Theme.DARK);
		
		//sparkReporter deals with the UI of report 
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Environmant", "QA");
		extent.setSystemInfo("tester Name", "hbk");
		extent.setSystemInfo("user", System.getProperty("user.name"));//get the user who run the test
		
		
		
		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("os", os);
		
		String br = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("broswer", br);
		
		//extentReports deals with the info of the report
		
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestContext().getName());
		test.log(Status.PASS, "test case passed: "+result.getName());
		
	}
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.log(Status.FAIL, "test case failed: "+result.getName());
		test.log(Status.INFO, "Test case failed: "+ result.getThrowable().getMessage());
		
		try {
			String impPath = new BaseClass().captureScreenShot(result.getName());
			test.addScreenCaptureFromPath(impPath);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.log(Status.SKIP, "test case skipped: "+result.getName());
		test.log(Status.INFO, "Test case failed: "+ result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}

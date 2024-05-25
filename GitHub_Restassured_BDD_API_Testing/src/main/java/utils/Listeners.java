package utils;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Listeners extends TestListenerAdapter{
  public ExtentHtmlReporter htmlReporter;
  public ExtentReports extent;
  public ExtentTest test;
  
public void onStart(ITestContext TestContext) {
	
    //Specify the location where to save Reportes
	htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/Git-Hub Report.html");
	//Title of the report
    htmlReporter.config().setDocumentTitle("Automation Report");
    //Name of the Report
    htmlReporter.config().setReportName("Rest API Testing Report");
    //Loaction of the Chart
    //htmlReporter.config().setTestViewChartLoaction(ChartLoaction.TOP);
    //Theme of Report
    htmlReporter.config().setTheme(Theme.DARK);
    
    extent=new ExtentReports();
    extent.attachReporter(htmlReporter);
    extent.setSystemInfo("ProjectName", "Git-Hub Repository API");
    extent.setSystemInfo("Host name", "https://github.com/");
    extent.setSystemInfo("Environemnt", "QA");
    extent.setSystemInfo("user", "Manoj");
}
   
public void onTestSuccess(ITestResult result) {
	//create new Entry in the report
	test=extent.createTest(result.getName());
	test.log(Status.PASS, "Test Case Passed Is"+result.getName());
}

public void onTestFailure(ITestResult result) {
	//create new Entry in the report
	test=extent.createTest(result.getName());
	test.log(Status.FAIL, "Test Case Failed Is"+result.getName());
	test.log(Status.FAIL, "Test Case Failed Is"+result.getThrowable());
}

public void onTestSkipped(ITestResult result) {
	//create new Entry in the report
	test=extent.createTest(result.getName());
	test.log(Status.SKIP, "Test Case ed Is"+result.getName());
}

public void onFinish(ITestContext testContext) {
	extent.flush();
}
  

}

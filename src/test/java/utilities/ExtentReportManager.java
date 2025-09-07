package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;
	public String repName;
	
	
	public void onStart(ITestContext testContext) {
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.ss").format(new Date());
		repName ="Test-Report-"+timeStamp+".html";
		sparkreporter =new ExtentSparkReporter(".\\reports\\"+repName);
		sparkreporter.config().setDocumentTitle("OpenCart Automation Report");
		sparkreporter.config().setReportName("OpenCart Functional Testing");
		sparkreporter.config().setTheme(Theme.DARK);
		
		extent =new ExtentReports();
		extent.attachReporter(sparkreporter);
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub-Module", "Customers");
		extent.setSystemInfo("username", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		

        String os= testContext.getCurrentXmlTest().getParameter("os"); 
        extent.setSystemInfo("Operating System", os);
        String browser = testContext.getCurrentXmlTest().getParameter("browser"); 
        extent.setSystemInfo("Browser", browser);
       
        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups(); 
        
        if(!includedGroups.isEmpty()) {
        extent.setSystemInfo("Groups", includedGroups.toString());
        }
     }
	  
	  public  void onTestSuccess(ITestResult result) {
		    test=extent.createTest(result.getClass().getName());
		    test.assignCategory(result.getMethod().getGroups()); //to display groups in reports
		    test.log(Status.PASS, "Test Case passed is "+result.getName());
	  }
	   
	  public  void onTestFailure(ITestResult result) {
		    test=extent.createTest(result.getClass().getName());
		    test.assignCategory(result.getMethod().getGroups());
		    test.log(Status.FAIL, "Test Case Failed is "+result.getName());
		    test.log(Status.FAIL, "cause of test case Fail is "+result.getThrowable());
		    
		    try {
		    	String imgpath=new BaseClass().captureScreen(result.getName());
		    	test.addScreenCaptureFromPath(imgpath);
		    }
		    catch(IOException e1) {
		    	e1.printStackTrace();
		    }
		    
	  }
	  
	  public  void onTestSkipped(ITestResult result) {
		    test=extent.createTest(result.getClass().getName());
		    test.assignCategory(result.getMethod().getGroups());
		    test.log(Status.SKIP, "Test Case skipped is "+result.getName());
		    test.log(Status.INFO, result.getThrowable().getMessage());
	  }
	  
	  public void onFinish(ITestContext context) {
		 extent.flush();
		 
         String pathofExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName; 
         
         File extentReport = new File(pathofExtentReport);
            try {
                   Desktop.getDesktop().browse(extentReport.toURI());
                  } 
            catch (IOException e) {
            	e.printStackTrace();
            	}

            /*
            try { 
				   URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
				   
				   //Create the email message 
				   ImageHtmlEmail email = new ImageHtmlEmail();
				   email.setDataSourceResolver(new DataSourceUrlResolver(url));
				   email.setHostName("smtp.googlemail.com"); 
				   email.setSmtpPort(465);
				   email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com", "password"));
				   email.setSSLOnConnect(true); 
				   email.setFrom("pavanoltraining@gmail.com"); //Sender 
				   email.setSubject("Test Results");
				   email.setMsg("Please find Attached Report....");
				   email.addTo("pavankumar.busyqa@gmail.com"); //Receiver 
				   email.attach(url,"extent report", "please check report..."); 
				   email.send(); // send the email 
				   }  
				   catch (Exception e) 
				   { 
				   e.printStackTrace(); 
				   } 

*/
	  }
}

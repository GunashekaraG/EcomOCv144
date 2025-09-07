package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass {
	
public Logger logger;	
public static WebDriver driver;
public Properties p;

	@BeforeClass(groups={"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException {
		
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equals("remote"))
		{
		DesiredCapabilities capabilities = new DesiredCapabilities();

		//OS
        if (os.equalsIgnoreCase("windows")) {
            capabilities.setPlatform(Platform.WINDOWS);
        } 
        else if (os.equalsIgnoreCase("linux")) {
            capabilities.setPlatform(Platform.LINUX);
        } 
        else
        {
            capabilities.setPlatform(Platform.ANY);
           // System.out.println("No Matching OS");
            //return;
        }
        
        //Browser
        switch(br.toLowerCase()) {
        case "chrome" : capabilities.setBrowserName("chrome");break;
		case "edge" : capabilities.setBrowserName("edge"); break;
		case "firefox" : capabilities.setBrowserName("firefox"); break;
		default:System.out.println("Invalid browser name");return;
        }
        
		driver=new RemoteWebDriver(new URL("http://192.168.31.176:4444"),capabilities);
		
	}
		
		if(p.getProperty("execution_env").equals("local")) {
			switch(br.toLowerCase()) {
			case "chrome" : driver=new ChromeDriver();break;
			case "edge" : driver=new EdgeDriver(); break;
			case "firefox" : driver=new FirefoxDriver(); break;
			default:System.out.println("Invalid browser name");return;
			}
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(p.getProperty("appurl1")); //reading url from properties file
	}
	
		@AfterClass(groups={"Sanity","Regression","Master"})
	public void teardown() {
		driver.quit();
	}
		
		public String randomString() {
			String generatedString=RandomStringUtils.randomAlphabetic(5);
			return generatedString;
		}
		
		public String randomnumber() {
			String generatednumber=RandomStringUtils.randomNumeric(10);
			return generatednumber;
		}
		
		public String randomAlphaNumerical() {
			String generatedpassword=RandomStringUtils.randomAlphanumeric(8);
			return generatedpassword;
		}

        public String captureScreen (String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs (OutputType.FILE);
        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname +"_"+timeStamp+".png";
        File targetFile=new File(targetFilePath);
        sourceFile.renameTo(targetFile);
        return targetFilePath;

}		
		
}

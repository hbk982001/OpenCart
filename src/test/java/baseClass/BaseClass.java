package baseClass;

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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	

	@BeforeClass
	@Parameters({"os","browser"})
	public void Setup(String os, String br) throws IOException {
		//to initiate logger to get the current class
		logger = LogManager.getLogger(this.getClass());
		FileReader file = new FileReader("./src/test/resources/config.properties");
		p = new Properties();
		p.load(file);
		
		if(p.getProperty("executionEnviornment").equalsIgnoreCase("remote")) {
				DesiredCapabilities dc = new DesiredCapabilities();
				if(os.equalsIgnoreCase("windows")) {
					dc.setPlatform(Platform.WIN11);
					
				}
				else if(os.equalsIgnoreCase("mac")){
					dc.setPlatform(Platform.MAC);
				}
				else if(os.equalsIgnoreCase("linux")){
					dc.setPlatform(Platform.LINUX);
				}
				
				else {
					System.out.println("No matching os");
					return;
				}
				switch(br.toLowerCase()){ 
				  case "edge": dc.setBrowserName("MicrosoftEdge"); break;
				  case "firefox": dc.setBrowserName("firefox"); break; 
				  default: System.out.println("Invalid driver"); return;  
				  }
				
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),dc);
		}
		
		if(p.getProperty("executionEnviornment").equalsIgnoreCase("local")) {
			  switch(br.toLowerCase()){ 
				  case "edge": driver = new EdgeDriver();break;
				  case "firefox": driver = new FirefoxDriver(); break; 
				  default: System.out.println("Invalid driver"); return;  
				  }
		  
		}	 
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(p.getProperty("AppURL"));
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public String randomString() {
		String generated = RandomStringUtils.randomAlphabetic(5);
		return generated;
	}
	
	public String randomNO() {
		String generated = RandomStringUtils.randomNumeric(10);
		return generated;
	}
	public String randomStringCharacter() {
		String number = RandomStringUtils.randomNumeric(6);
		String generated = RandomStringUtils.randomAlphabetic(3);
		return "A"+generated+"@"+number;
	}
	
	public String captureScreenShot(String name) {
		String timestamp = new SimpleDateFormat("yymmddhhmmss").format(new Date());
		TakesScreenshot ts  = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir")+".\\screenshot\\"+name+"="+timestamp+".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

}

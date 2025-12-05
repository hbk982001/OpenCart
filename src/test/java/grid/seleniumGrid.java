package grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class seleniumGrid {

	public static void main(String[] args) throws MalformedURLException {
		/*
		 * //Instal the selenium grid in system save it anywhere 
		 * //then open the cmd where the grid is present for standalone run the command ->java -jar
		 * selenium-server-4.38.0.jar standalone then capture the url from cmd
		 */
			String huburl = "http://localhost:4444/wd/hub";
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setPlatform(Platform.WIN11);
			cap.setBrowserName("edge");
			
			WebDriver driver = new RemoteWebDriver(new URL(huburl),cap);
			driver.get("https://www.google.com");
			System.out.println(driver.getTitle());
			driver.quit();
	}
	

}

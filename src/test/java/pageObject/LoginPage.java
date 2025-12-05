package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends basePage{
	

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
@FindBy(id="input-email")	WebElement emailTab;
@FindBy(id="input-password") WebElement passTab;
@FindBy(xpath="//input[@type='submit']") WebElement LoginButton;
@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']") public WebElement Error;


public void enterLoginDetails(String mail, String pass) {
	emailTab.sendKeys(mail);
	passTab.sendKeys(pass);
	LoginButton.click();
}

public String getError() {
	String error = Error.getText();
	return error;
}



}

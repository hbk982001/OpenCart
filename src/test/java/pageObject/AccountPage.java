package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends basePage{
	
	public AccountPage(WebDriver driver) {
		super(driver);
	}

@FindBy(xpath="//h2[text()='My Account']") public WebElement MyAccounttext;
@FindBy(xpath="//span[text()='My Account']") WebElement MyAccountTab;
@FindBy(xpath="(//a[text()='Logout'])[1]") WebElement LogoutTab;
@FindBy(xpath="//a[contains(text(),'Continue')]") WebElement continueButton;


	public String getAccountText() {
		String success = MyAccounttext.getText();
		return success;
	}
	
	public void logout() {
		MyAccountTab.click();
		LogoutTab.click();
		continueButton.click();
	}

}

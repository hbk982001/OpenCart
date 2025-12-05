package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends basePage{
	
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	@FindBy (xpath= "//span[text()='My Account']") WebElement myAccountButton;
	@FindBy (xpath = "//a[text()='Register']") WebElement registrationTab;
	@FindBy(xpath="//a[contains(text(),'Login')]") WebElement LoginTab;
	@FindBy(id="gender-male") WebElement radioButton;
	@FindBy(name="firstname") WebElement firstName;
	@FindBy(name="lastname") WebElement lastName;
	@FindBy(name="email") WebElement email;
	@FindBy(name="telephone") WebElement phoneNo;
	@FindBy(name="password") WebElement password;
	@FindBy(name="confirm") WebElement confirmPassword;
	@FindBy(xpath="//input[@name='agree']") WebElement checkBox;
	@FindBy(xpath="//div[@class='pull-right']//input[2]") WebElement RegistrationButton;
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']") WebElement successMessage;
	
	public void clickRegistration() {
		myAccountButton.click();
		registrationTab.click();
	}
	
	public void enterDetails(String fName, String lname, String Email, String phone,String pass, String ConPass) {
	
		firstName.sendKeys(fName);
		lastName.sendKeys(lname);
		email.sendKeys(Email +"@gmail.com");
		phoneNo.sendKeys(phone);
		password.sendKeys(pass);
		confirmPassword.sendKeys(ConPass);
		checkBox.click();
		RegistrationButton.click();
		
	}
	
	public String captureMessage() {
		String message = successMessage.getText();
		return message;
	}
	
	public void clickLogin() {
		myAccountButton.click();
		LoginTab.click();
	}

}

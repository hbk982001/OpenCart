package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObject.AccountPage;
import pageObject.LoginPage;
import pageObject.RegistrationPage;

public class TC002_LoginwithConfig extends BaseClass{
	
	@Test
	public void Login() {
		try {
			logger.info("****** testing is started *******");
			
			RegistrationPage rp = new RegistrationPage(driver);
			rp.clickLogin();
			
			LoginPage lp = new LoginPage(driver);
			lp.enterLoginDetails(p.getProperty("Email"), p.getProperty("Pass"));
			
			AccountPage ap = new AccountPage(driver);
			Assert.assertEquals(ap.getAccountText(), "My Account");
			ap.logout();
			logger.info("****** testing is completed *******");
			
			
			}catch(Exception e) {
				logger.error("***** test failed ******");
				logger.debug("Debug logs");
				Assert.fail();
				
			}
	}

}

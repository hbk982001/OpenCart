package testCase;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObject.RegistrationPage;

public class TC001_RegistartionPage extends BaseClass{
	
	@Test
	public void Registration() {
		try {	
		logger.info("****** testing is started *******");
			
			RegistrationPage rs = new RegistrationPage(driver);
			rs.clickRegistration();
			String password = randomStringCharacter();
			 rs.enterDetails(randomString(), randomString(), randomString(), randomNO(),password, password); 
			 String Success = rs.captureMessage();
			 Assert.assertEquals(Success, "Your Account Has Been Created!");
			  
			 logger.info("****** testing is completed *******");
		}catch(Exception e) {
			logger.error("***** test failed ******");
			logger.debug("Debug logs");
			Assert.fail();
		
		}
		
	}
}

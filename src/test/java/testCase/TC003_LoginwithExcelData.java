package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utility.dataPro;
import baseClass.BaseClass;
import pageObject.AccountPage;
import pageObject.LoginPage;
import pageObject.RegistrationPage;


public class TC003_LoginwithExcelData extends BaseClass {
	
	@Test(dataProvider="testLogin", dataProviderClass = dataPro.class)
	public void Login(String mail, String pass, String res) {
		
		
		RegistrationPage rp = new RegistrationPage(driver);
		rp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.enterLoginDetails(mail, pass);
		
		AccountPage ap = new AccountPage(driver);
		
	
		
		if(res.equalsIgnoreCase("Pass")) {
			Boolean result = ap.MyAccounttext.isDisplayed();
			if(result==true) {
			
				ap.logout();
				Assert.assertTrue(true);
			
			}
			else {
				Assert.fail();
			}
		}
		if(res.equalsIgnoreCase("Fail")) {
			Boolean error = lp.Error.isDisplayed();
				if(error==true) {
					rp.clickLogin();
					Assert.assertTrue(true);
					
				}
				else {
					Assert.fail();
				}
				
		}
		
		
	}

}

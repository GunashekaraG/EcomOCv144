package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003LoginDDT extends BaseClass {

	@Test(dataProvider="loginData", dataProviderClass=DataProviders.class ,groups="DataDriven")
	public void verifyLoginDDT(String emailid,String pwd,String result) {
		
		logger.info("Starting TC_003LoginDDT");
	try {
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickaccount();
		hp.clicklogin();
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.Emailfield(emailid);
		lp.pwdfield(pwd);
		lp.login();
		
		//MyAccountPage
		MyAccountPage mp=new MyAccountPage(driver);
		 boolean targetPage = driver.getTitle().equals("My Account");
		/*boolean trg=mp.ismyAccountmsgexists();
		Assert.assertTrue(trg); */
		
		
		if(result.equalsIgnoreCase("Valid")) {
			if(targetPage==true) {
				Assert.assertTrue(true);
				mp.btnlogout();
			}
			else {
				Assert.assertTrue(false);
			}
			
		}
		if(result.equalsIgnoreCase("InValid")) {
			if(targetPage==true) {
				mp.btnlogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
	}
		catch (Exception e){
			Assert.fail();
		}
	logger.info("Finished TC_003LoginDDT");
}
}
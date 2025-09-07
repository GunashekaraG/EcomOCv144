package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002LoginTest extends BaseClass {

    @Test(groups={"Sanity","Master"})
	public void verifylogin() {
		logger.info("Starting TC_002LoginTest");
		try {
		//Homepage
		HomePage hp=new HomePage(driver);
		hp.clickaccount();
		hp.clicklogin();
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.Emailfield(p.getProperty("email"));
		lp.pwdfield(p.getProperty("pwd"));
		lp.login();
		
		//MyAccountPage
		MyAccountPage mp=new MyAccountPage(driver);
		boolean trg=mp.ismyAccountmsgexists();
		Assert.assertTrue(trg);
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("Finished TC_002LoginTest");
	}
}

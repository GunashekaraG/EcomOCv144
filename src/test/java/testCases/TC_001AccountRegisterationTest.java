package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001AccountRegisterationTest extends BaseClass {
	

	@Test(groups={"Regression","Master"})
public void verify_acc_reg() {
		
		logger.info("Starting TC_001AccountRegisterationTest");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickaccount();
		hp.clicRegister();
		
		AccountRegisterationPage ar=new AccountRegisterationPage(driver);
		logger.info("Providing the user details");
		ar.setFirstName(randomString().toUpperCase());
		ar.setLastName(randomString().toUpperCase());
		ar.setEmail(randomString()+"@gmail.com");
		ar.setTelePhone(randomnumber());
		
		String password=randomAlphaNumerical();
		ar.setPassword1(password);
		ar.setPassword2(password);
		ar.clickcheckbox();
		ar.Submit();
		
		logger.info("Validating the expected message");
		String confmsg=ar.getconfirmationmsg();
		
		if(confmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confmsg , "You Account Has Been Created!");
}
	catch(Exception e)
	{
		
		Assert.fail();
	}
		logger.info("Finished TC_001AccountRegisterationTest");
}
}

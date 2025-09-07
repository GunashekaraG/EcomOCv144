package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css="[name=email]")
	WebElement txtEmail;
	
	@FindBy(css="[id=input-password]")
	WebElement txtPassword;
	
	@FindBy(css="[value='Login']")
	WebElement btnLogin;
	
	public void Emailfield(String emailid) {
		txtEmail.sendKeys(emailid);
	}
	
	public void pwdfield(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void login() {
		btnLogin.click();
	}

}

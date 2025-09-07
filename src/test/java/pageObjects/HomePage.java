package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	WebDriver driver;
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css="[title='My Account']")
	WebElement lnkMyAccount;
	
	@FindBy(xpath="//a[text()='Register']")
	WebElement lnkAccountRegister;
	
	@FindBy(xpath="//a[text()='Login']")
	WebElement lnklogin;
	
	public void clickaccount() {
		lnkMyAccount.click();
	}
	
	public void clicRegister() {
		lnkAccountRegister.click();
	}
	
	public void clicklogin() {
		lnklogin.click();
	}
}

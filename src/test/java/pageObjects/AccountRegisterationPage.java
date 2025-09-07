package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterationPage extends BasePage {
	
	public AccountRegisterationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css="#input-firstname")
	WebElement FirstName;
	
	@FindBy(css="#input-lastname")
	WebElement LastName;
	
	@FindBy(css="#input-email")
	WebElement Email;
	
	@FindBy(css="#input-telephone")
	WebElement TelePhone;
	
	@FindBy(css="#input-password")
	WebElement Password1;
	
	@FindBy(css="#input-confirm")
	WebElement Password2;
	
	@FindBy(name="agree")
	WebElement Checkbox;
	
	@FindBy(css="input[value='Continue']")
	WebElement continuebtn;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgconfirmation;
	
	public void setFirstName(String fname) {
		FirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		LastName.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		Email.sendKeys(email);
	}
	
	public void setTelePhone(String telephone) {
		TelePhone.sendKeys(telephone);
	}
	
	public void setPassword1(String pwd1) {
		Password1.sendKeys(pwd1);
	}
	
	public void setPassword2(String pwd2) {
		Password2.sendKeys(pwd2);
	}
	
	public void clickcheckbox() {
		Checkbox.click();
	}
	
	public void Submit() {
		continuebtn.click();
	}
	
	public String getconfirmationmsg() {
		try {
			return(msgconfirmation.getText());
		}
		catch(Exception e) {
			return(e.getMessage());
		}
	}
}

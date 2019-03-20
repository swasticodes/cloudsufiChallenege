package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.BasicVariables;

public class LoginPage 
{
	public WebDriver driver;
	BasicVariables bv = new BasicVariables();
	
	@FindBy (xpath="//*[@id=\"loginEmail\"]") WebElement textField_Email;
	@FindBy (xpath="//*[@id=\"loginPassword\"]") WebElement textField_Password;
	@FindBy (css="#loginForm > div.row > div.col-12.heading > h2") WebElement text_ExistingCustomer;
	@FindBy (xpath="//*[contains(text(), 'Registrieren')]") WebElement btn_Register;
	@FindBy (xpath="(//*[contains(text(), 'Registrieren')])[2]") WebElement btn_Mobile_Register; 
	
	public LoginPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public WebElement textFieldEmail()
	{
		return textField_Email;
	}
	public WebElement textFieldPassword()
	{
		return textField_Password;
	}
	public WebElement textExistingCustomer()
	{
		return text_ExistingCustomer;
	}
	public WebElement buttonRegister()
	{
		bv.safariWaitWorkaround();
		return btn_Register;
	}
	public WebElement buttonMobileRegister()
	{
		return btn_Mobile_Register;
	}
}
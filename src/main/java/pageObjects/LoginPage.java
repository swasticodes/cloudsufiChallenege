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
	@FindBy (xpath="//*[@id='name']") WebElement csc_TextField_Username;
	@FindBy (xpath="//*[@id='pass']") WebElement csc_TextField_Password;
	@FindBy (xpath="//*[@value='Anmelden']") WebElement csc_Btn_Login;
	String cscUsename = "pgleich";
	String cscPassword = "Testing!";
	
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
	public WebElement cscTextFieldUsername()
	{
		return csc_TextField_Username;
	}
	public WebElement cscTextFieldPassword()
	{
		return csc_TextField_Password;
	}
	public WebElement cscButtonLogin()
	{
		return csc_Btn_Login;
	}
	public String cscUsername()
	{
		return cscUsename;
	}
	public String cscPassword()
	{
		return cscPassword;
	}
	public  void cscLogin()
	{
		cscTextFieldUsername().sendKeys(cscUsername());
		//log.info("Entered the CSC Username");
		cscTextFieldPassword().sendKeys(cscPassword());
		//log.info("Entered the CSC Password");
		cscButtonLogin().click();
		//log.info("Clicked on the 'Anmeleden' button");
	}
}
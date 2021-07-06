package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage 
{
	public WebDriver driver;
	
	@FindBy (xpath="//*[@id='SubmitCreate']") WebElement button_createAnAccount;
	@FindBy (xpath="//*[@id='email_create']") WebElement txtField_emailAddress;
	@FindBy (xpath="//*[@id='SubmitCreate']") WebElement btn_createAnAccount;
	@FindBy (xpath="//*[@id='create_account_error']") WebElement txt_accountCreationError;
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");
	
	public SignInPage(WebDriver driver) 
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory 
		PageFactory.initElements(driver, this);
	}

	public WebElement buttonCreatAnAccount()
	{
		return button_createAnAccount;
	}
	public WebElement textFieldEmailAddress()
	{
		return txtField_emailAddress;
	}
	public WebElement textAccountCreationError()
	{
		return txt_accountCreationError;
	}

}

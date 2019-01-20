package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	public WebDriver driver;
	
	@FindBy (xpath="//*[@id=\"loginEmail\"]") WebElement textField_Email;
	@FindBy (xpath="//*[@id=\"loginPassword\"]") WebElement textField_Password;
	@FindBy (css="#loginForm > div.row > div.col-12.heading > h2") WebElement text_ExistingCustomer;
	
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
}
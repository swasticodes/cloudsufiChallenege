package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PayPalPage
{
	public WebDriver driver;

	@FindBy (xpath="//*[@id=\"email\"]") WebElement textField_Paypal_Email;
	@FindBy (xpath="//*[@id=\"btnNext\"]") WebElement btn_Paypal_Page_Continue;
	@FindBy (xpath="//*[@id=\"password\"]") WebElement btn_Paypal_Page_Paassword;
	@FindBy (xpath="//*[@id=\"btnLogin\"]") WebElement btn_Paypal_Login;
	@FindBy (xpath="//*[@id=\"confirmButtonTop\"]") WebElement btn_Confirm_Paypal_Payment;
	String payPalEMail = "tulpe@blume2000.de";
	String payPalPassword = "flowerpower69";
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");

	public PayPalPage(WebDriver driver)
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory
		PageFactory.initElements(driver, this);
	}

	public WebElement textFieldEmail()
	{
		//Use the code below in case of using Page Factory Model
		return textField_Paypal_Email;
		//Comment the above code and Uncomment the code below in case using Page object instead of Page factory
		//return driver.findElement(link_Login);
	}
	public WebElement buttonPaypalPageContinue()
	{
		return btn_Paypal_Page_Continue;
	}
	public WebElement textFieldPassword()
	{
		return btn_Paypal_Page_Paassword;
	}
	public String payPalEMail()
	{
		return payPalEMail;
	}
	public String payPalPassword()
	{
		return payPalPassword;
	}
	public WebElement buttonPaypalLogin()
	{
		return btn_Paypal_Login;
	}
	public WebElement buttonPaypalPaymentConfirmation()
	{
		return btn_Confirm_Paypal_Payment;
	}
}

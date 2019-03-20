package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.BasicVariables;

public class PayPalPage
{
	public WebDriver driver;

	public static Logger log = LogManager.getLogger(BasicVariables.class.getName());

	@FindBy (xpath="//*[@id=\"email\"]") WebElement textField_Paypal_Email;
	@FindBy (xpath="//*[@id=\"btnNext\"]") WebElement btn_Paypal_Page_Continue;
	@FindBy (xpath="//*[@id=\"password\"]") WebElement btn_Paypal_Page_Password;
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
		return btn_Paypal_Page_Password;
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
	public boolean isPasswordFieldDisplayed()
	{
		try {
			return textFieldPassword().isDisplayed();	
		} catch(NoSuchElementException ex) {
			return false;
		}
	}
	
	public boolean PayPalLogin()
	{
		textFieldEmail().clear();
		textFieldEmail().sendKeys(payPalEMail());
		log.info("Entered the PayPal Email address as "+ payPalEMail());
		
		if (isPasswordFieldDisplayed()) {
			textFieldPassword().sendKeys(payPalPassword());
		    log.info("Entered the PayPal Password as <CENSORED>");
		    buttonPaypalLogin().click();
		    log.info("Clicked on the login button on Paypal page");
		} else {
			buttonPaypalPageContinue().click();
			textFieldPassword().sendKeys(payPalPassword());
		    log.info("Entered the PayPal Password as <CENSORED>");
		    buttonPaypalLogin().click();
		    log.info("Clicked on the login button on Paypal page");
		}
		return true;
	}
}

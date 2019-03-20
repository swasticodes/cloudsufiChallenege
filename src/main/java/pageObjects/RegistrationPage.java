package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.BasicVariables;

public class RegistrationPage
{
	public WebDriver driver;
	BasicVariables bv = new BasicVariables();

	@FindBy (id="paymentEmail") WebElement textField_Email;
	@FindBy (id="password") WebElement textField_Password;
	@FindBy (id="repeatPassword") WebElement textField_ConfirmPassword;
	@FindBy (xpath="//*[contains(text(),'Herr')]") WebElement radioButton_Herr_Salutation;
	@FindBy (id="shippingFirstName") WebElement textField_Shipping_FirstName;
	@FindBy (id="shippingLastName") WebElement textField_Shipping_LastName;
	@FindBy (id="shippingStreet") WebElement textField_Shipping_StreetName;
	@FindBy (id="shippingStreetNo") WebElement textField_Shipping_StreetNumber;
	@FindBy (id="shippingPostcode") WebElement textField_Shipping_PostalCode;
	@FindBy (id="shippingCity") WebElement textField_Shipping_City;
	@FindBy (id="shippingCountry") WebElement textField_Shipping_Country;
	@FindBy (xpath="//*[contains(text(),'Liefer- und Rechungsanschrift sind identisch')]") WebElement chkBox_SameDeliveryAndInvoiceAddress;
	@FindBy (xpath="//*[contains(text(), 'Weiter zur Übersicht')]") WebElement btn_Continue_To_Overview;
	@FindBy (xpath="//button[contains(text(), 'Als Gast bestellen')]") WebElement btn_Order_As_Guest;
	@FindBy (xpath="//button[contains(text(),'Einloggen')]") WebElement btn_Login;
	@FindBy (id="loginEmail") WebElement textField_LoginEmail;
	@FindBy (id="loginPassword") WebElement textField_LoginPassword;
	@FindBy (xpath="(//span[contains(text(),'Herr')])[1]") WebElement radioButton_tchibo_Herr_Salutation;
	@FindBy (xpath="//*[contains(@class,'feedback-text')]") WebElement textBox_IncorrectLoginMessage;

	String registeredUserEmail = "development@blume2000.de";
	String registeredUserPassword = "development";
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");

	public RegistrationPage(WebDriver driver)
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory
		PageFactory.initElements(driver, this);
	}

	public WebElement registrationEmail()
	{
		//Use the code below in case of using Page Factory Model
		bv.safariWaitWorkaround();
		return textField_Email;
		//Comment the above code and Uncomment the code below in case using Page object instead of Page factory
		//return driver.findElement(link_Login);
	}
	public WebElement registrationPassword()
	{
		return textField_Password;
	}
	public WebElement registrationConfrimPassword()
	{
		return textField_ConfirmPassword;
	}
	public WebElement registrationSalutation()
	{
		return radioButton_Herr_Salutation;
	}
	public WebElement registrationFirstName()
	{
		return textField_Shipping_FirstName;
	}
	public WebElement registrationLastName()
	{
		return textField_Shipping_LastName;
	}
	public WebElement registrationStreet()
	{
		return textField_Shipping_StreetName;
	}
	public WebElement registrationStreetNumber()
	{
		return textField_Shipping_StreetNumber;
	}
	public WebElement registrationPostalCode()
	{
		return textField_Shipping_PostalCode;
	}
	public WebElement registrationCity()
	{
		return textField_Shipping_City;
	}
	public WebElement registrationCountry()
	{
		return textField_Shipping_Country;
	}
	public WebElement copyDeliveryAndInvoiceAddress()
	{
		return chkBox_SameDeliveryAndInvoiceAddress;
	}
	public WebElement continueToOverview()
	{
		return btn_Continue_To_Overview;
	}
	public WebElement buttonOrderAsGuest()
	{
		return btn_Order_As_Guest;
	}
	public WebElement buttonLogin()
	{
		return btn_Login;
	}
	public WebElement textFieldEmail()
	{
		return textField_LoginEmail;
	}
	public WebElement textFieldPassword()
	{
		return textField_LoginPassword;
	}
	public String registeredUserEmail()
	{
		return registeredUserEmail;
	}
	public String registeredUserPassword()
	{
		return registeredUserPassword;
	}
	public WebElement tchiboSalutationHerr()
	{
		return radioButton_tchibo_Herr_Salutation;
	}
	public WebElement textBoxIncorrectLoginMessage()
	{
		return textBox_IncorrectLoginMessage;
	}

}

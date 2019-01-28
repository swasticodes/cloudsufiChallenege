package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage
{
	public WebDriver driver;

	@FindBy (id="paymentEmail") WebElement textField_Email;
	@FindBy (id="password") WebElement textField_Password;
	@FindBy (id="repeatPassword") WebElement textField_ConfirmPassword;
	//@FindBy (xpath="//*[contains(text(),'Herr')]/preceding-sibling::span") WebElement radioButton_Herr_Salutation;
	@FindBy (xpath="//*[@id=\"shippingAndPaymentForm\"]/section[2]/div/div/div[2]/div[1]/div/div/div/div/a[1]/div/label/span[1]") WebElement radioButton_Herr_Salutation;
	@FindBy (id="shippingFirstName") WebElement textField_Shipping_FirstName;
	@FindBy (id="shippingLastName") WebElement textField_Shipping_LastName;
	@FindBy (id="shippingStreet") WebElement textField_Shipping_StreetName;
	@FindBy (id="shippingStreetNo") WebElement textField_Shipping_StreetNumber;
	@FindBy (id="shippingPostcode") WebElement textField_Shipping_PostalCode;
	@FindBy (id="shippingCity") WebElement textField_Shipping_City;
	@FindBy (id="shippingCountry") WebElement textField_Shipping_Country;
	@FindBy (xpath="//*[@id=\"shippingAndPaymentForm\"]/section[3]/div/div/div[2]/label/span[1]") WebElement chkBox_SameDeliveryAndInvoiceAddress;
	@FindBy (xpath="//*[contains(text(), 'Weiter zur Übersicht')]") WebElement btn_Continue_To_Overview;
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

}

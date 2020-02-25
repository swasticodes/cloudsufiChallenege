package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.BasicVariables;

public class DeliveryPage 
{
	public WebDriver driver;
	BasicVariables bv = new BasicVariables();
	
	@FindBy (xpath="//*[@id=\"deliveryPostcode\"]") WebElement textField_DeliveryPostalCode;
	@FindBy (xpath="//*[@id=\"deliveryPostcodeSubmit\"]") WebElement btn_Next_PostalCode;
	@FindBy (xpath="//td[contains(@class, \"active day\")]") WebElement date_ActiveDay;
	@FindBy (xpath="//*[contains(text(), 'Weiter zur Gruﬂkarte')]") WebElement btn_Continue_To_GreeatingCard;
	@FindBy (xpath="//*[contains(text(), 'Weiter ohne Gruﬂkarte')]") WebElement btn_Continue_Without_GreetingCard;
	@FindBy (xpath="//*[contains(text(), 'Weiter ohne Geschenke')]") WebElement btn_Continue_Without_Gifts;
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");
	
	public DeliveryPage(WebDriver driver) 
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory 
		PageFactory.initElements(driver, this);
	}
	
	public WebElement textFieldDeliveryPostalCode()
	{
		return textField_DeliveryPostalCode;
	}
	public WebElement buttonNextPostalCode()
	{
		return btn_Next_PostalCode;
	}
	public WebElement dayActiveDay()
	{
		return date_ActiveDay;
	}
	public WebElement continueToGreetingCard()
	{
		return btn_Continue_To_GreeatingCard;
	}
	public WebElement continueWithoutGreetingCard()
	{
		bv.safariWaitWorkaround();
		return btn_Continue_Without_GreetingCard;
	}
	public WebElement continueWithoutGifts()
	{
		bv.safariWaitWorkaround();
		return btn_Continue_Without_Gifts;
	}
}

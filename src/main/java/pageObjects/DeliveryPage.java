package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeliveryPage 
{
	public WebDriver driver;
	
	@FindBy (xpath="//*[@id=\"deliveryPostcode\"]") WebElement textField_DeliveryPostalCode;
	@FindBy (xpath="//*[@id=\"deliveryPostcodeSubmit\"]") WebElement btn_Next_PostalCode;
	@FindBy (xpath="//td[contains(@class, \"active day\")]/following-sibling::td[1]") WebElement date_NextDay;
	@FindBy (xpath="//*[@id=\"formSubmitBtn\"]/div/button") WebElement btn_Continue_To_GreeatingCard;
	@FindBy (xpath="html/body/section[2]/div/div/div/div[2]/a") WebElement btn_Continue_Without_GreetingCard;
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
	public WebElement dayNextDay()
	{
		return date_NextDay;
	}
	public WebElement continueToGreetingCard()
	{
		return btn_Continue_To_GreeatingCard;
	}
	public WebElement continueWithoutGreetingCard()
	{
		return btn_Continue_Without_GreetingCard;
	}

}

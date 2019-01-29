package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GeneralPage 
{
	public WebDriver driver;
	
	@FindBy (xpath="//*[@id=\"openDeliveryBtn\"]") WebElement btn_SelectedItem_Next;
	@FindBy (xpath="//*[@id='cookieNote']/div/button") WebElement btn_Close_Cookie_Msg;
	@FindBy (xpath="//*[contains(@class,'text-center xs-two-line')]") WebElement text_Order_Confirmation;
	@FindBy (xpath="//*[contains(@class, \"widget-teaser-tile highlight-banner\")]/ancestor::div[1]/following-sibling::div/div[2]/div/div/div/div/div") WebElement link_First_Item;
	@FindBy (xpath="//span[contains(text(),'Mein Konto')]") WebElement link_MyAccount;
	@FindBy (xpath="//a[contains(text(),'Abmelden')]") WebElement link_Logout;
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");
	
	public GeneralPage(WebDriver driver) 
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory 
		PageFactory.initElements(driver, this);
	}

	public WebElement buttonSelectedItemNext()
	{
		//Use the code below in case of using Page Factory Model
		return btn_SelectedItem_Next;
		//Comment the above code and Uncomment the code below in case using Page object instead of Page factory
		//return driver.findElement(link_Login);
	}
	public WebElement closeCookieMessage()
	{
		return btn_Close_Cookie_Msg;
	}
	public WebElement textOrderConfirmation()
	{
		return text_Order_Confirmation;
	}
	public WebElement linkFirstItem()
	{
		return link_First_Item;
	}
	public WebElement linkMyAccount()
	{
		return link_MyAccount;
	}
	public WebElement linkLogout()
	{
		return link_Logout;
	}

}

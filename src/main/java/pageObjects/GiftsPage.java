package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GiftsPage 
{
	public WebDriver driver;
	
	@FindBy (xpath="//*[contains(@class, 'gift-list d-flex flex-wrap justify-content-around')]/div[1]") WebElement link_GreetingCard_First_Item;
	@FindBy (xpath="//*[contains(@class, 'gift-list d-flex flex-wrap justify-content-around')]/div[2]") WebElement link_GreetingCard_Second_Item;
	@FindBy (xpath="//*[@id=\"submitGift\"]") WebElement btn_Directly_To_Cash_Register;
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");
	
	public GiftsPage(WebDriver driver) 
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory 
		PageFactory.initElements(driver, this);
	}

	public WebElement linkGiftsFirstItem()
	{
		//Use the code below in case of using Page Factory Model
		return link_GreetingCard_First_Item;
		//Comment the above code and Uncomment the code below in case using Page object instead of Page factory
		//return driver.findElement(link_Login);
	}
	public WebElement linkGiftsSecondItem()
	{
		return link_GreetingCard_Second_Item;
	}
	public WebElement buttonDirectlyToCashRegister()
	{
		return btn_Directly_To_Cash_Register;
	}

}

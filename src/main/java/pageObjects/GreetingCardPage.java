package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GreetingCardPage 
{
	public WebDriver driver;
	
	@FindBy (xpath="//*[contains(@class,'greeting-card-list d-flex flex-wrap justify-content-around')]/div") WebElement link_GreetingCrad_First_Item;
	@FindBy (xpath="//*[@id=\"greetingTextForm\"]") WebElement textBox_GreetingText;
	@FindBy (xpath="//*[@id=\"submitGreetingCard\"]") WebElement btn_Continue_To_Gifts;
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");
	
	public GreetingCardPage(WebDriver driver) 
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory 
		PageFactory.initElements(driver, this);
	}

	public WebElement linkGreetingCardFirstItem()
	{
		//Use the code below in case of using Page Factory Model
		return link_GreetingCrad_First_Item;
		//Comment the above code and Uncomment the code below in case using Page object instead of Page factory
		//return driver.findElement(link_Login);
	}
	public WebElement textboxGreetingCardText()
	{
		return textBox_GreetingText;
	}
	public WebElement buttonContinueToGifts()
	{
		return btn_Continue_To_Gifts;
	}

}

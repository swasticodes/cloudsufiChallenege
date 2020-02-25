package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.BasicVariables;

public class GiftsPage 
{
	public WebDriver driver;
	BasicVariables bv = new BasicVariables();
	
	//@FindBy (xpath="//*[contains(@class, 'gift-list d-flex flex-wrap justify-content-around')]/div[1]") WebElement link_GiftsPage_FirstItem;
	@FindBy (xpath="//*[contains(@class, 'upselling-list tab-pane fade container p-md-0 show active')]/div/div[1]") WebElement link_GiftsPage_FirstItem;
	//@FindBy (xpath="//*[contains(@class, 'gift-list d-flex flex-wrap justify-content-around')]/div[2]") WebElement link_GiftsPage_SecondItem;
	@FindBy (xpath="//*[contains(@class, 'upselling-list tab-pane fade container p-md-0 show active')]/div/div[2]") WebElement link_GiftsPage_SecondItem;
	//@FindBy (xpath="//*[@id=\"submitGift\"]") WebElement btn_Directly_To_Cash_Register;
	@FindBy (xpath="//*[contains(text(),'Direkt zur Kasse')]") WebElement btn_Directly_To_Cash_Register;
	//@FindBy (xpath="//*[@id=\"submitFormUpselling\"]") WebElement btn_Continue_To_Packing;
	@FindBy (xpath="//span[contains(text(),'Verpackung')]") WebElement btn_Continue_To_Packing;
	//@FindBy (xpath="//*[@id=\"submitFormUpselling\"][1]") WebElement btn_Directly_To_Cash_Register;
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");
	
	public GiftsPage(WebDriver driver) 
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory 
		PageFactory.initElements(driver, this);
	}

	public WebElement linkGiftsPageFirstItem()
	{
		//Use the code below in case of using Page Factory Model
		bv.safariWaitWorkaround();
		return link_GiftsPage_FirstItem;
		//Comment the above code and Uncomment the code below in case using Page object instead of Page factory
		//return driver.findElement(link_Login);
	}
	public WebElement linkGiftsPageSecondItem()
	{
		bv.safariWaitWorkaround();
		return link_GiftsPage_SecondItem;
	}
	public WebElement buttonDirectlyToCashRegister()
	{
		bv.safariWaitWorkaround();
		return btn_Directly_To_Cash_Register;
	}
}

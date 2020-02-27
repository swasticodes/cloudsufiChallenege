package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import resources.BasicVariables;

public class GeneralPage 
{
	public WebDriver driver;
	BasicVariables bv = new BasicVariables();
	
	@FindBy (xpath="//*[@id=\"openDeliveryBtn\"]") WebElement btn_SelectedItem_Next;
	@FindBy (xpath="//*[@id='cookieNote']/div/button") WebElement btn_Close_Cookie_Msg;
	@FindBy (xpath="//*[contains(@class,'text-center thx-headline')]") WebElement text_Order_Confirmation;
	@FindBy (xpath="//*[contains(@class,\"container p-0 widget-product-highlight\")]/div[3]/div[3]") WebElement link_First_Item;
	@FindBy (xpath="//*[contains(@class,'container-list')]/div[3]/div[2]/div/div/div[2]/div[2]") WebElement link_Second_Item;
	@FindBy (xpath="//span[contains(text(),'Mein Konto')]") WebElement link_MyAccount;
	@FindBy (xpath="//a[contains(text(),'Abmelden')]") WebElement link_Logout;
	@FindBy (xpath="//span[contains(text(),'Premium-Lieferung')]") WebElement radioBtn_PremiumDelivery;
	@FindBy (xpath="//*[contains(@class,'icon-search')]") WebElement iconSearch;
	@FindBy (xpath="(//*[@name='searchparam'])[1]") WebElement textField_Search;
	@FindBy (xpath="(//*[contains(@class,'fa fa-search')])[2]") WebElement icon_StartSearch;
	@FindBy (xpath="//*[contains(@class,'sec-img')]") WebElement first_Search_Result;
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
		bv.safariWaitWorkaround();
		return btn_SelectedItem_Next;
		//Comment the above code and Uncomment the code below in case using Page object instead of Page factory
		//return driver.findElement(link_Login);
	}
	
	public WebElement closeCookieMessage()
	{
		return btn_Close_Cookie_Msg;
	}
	

	public void clickCloseCookieMessage(boolean notFoundIsError)
	{
		try {
			closeCookieMessage().click();
			System.out.println("PASSED : Clicked the closed cookie message"); 
		} catch(NoSuchElementException ex) {
			if (notFoundIsError) {
				Assert.assertTrue(false, "Could not click on the close cookie message. " + ex.getMessage());
			}
			//throw ex;
		}
	}

	public WebElement textOrderConfirmation()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bv.safariWaitWorkaround();
		return text_Order_Confirmation;
	}
	public WebElement linkFirstItem()
	{
		bv.safariWaitWorkaround();
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
	public WebElement radioButtonPremiumDelivery()
	{
		return radioBtn_PremiumDelivery;
	}
	public WebElement linkSecondItem()
	{
		bv.safariWaitWorkaround();
		return link_Second_Item;
	}
	public void searchFor(String articleId)
	{
		iconSearch.click();
		textField_Search.sendKeys(articleId);
		icon_StartSearch.click();
		first_Search_Result.click();
	}
}

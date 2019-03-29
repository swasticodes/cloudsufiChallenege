package pageObjects;

import org.apache.http.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import resources.BasicVariables;

public class HomePage 
{
	public WebDriver driver;
	BasicVariables bv = new BasicVariables();
	
	@FindBy (linkText="Anmelden") WebElement link_Login;
	@FindBy (linkText="Anlässe") WebElement link_Events;
	@FindBy (linkText="Geburtstag") WebElement link_Birthday;
	@FindBy (linkText="Dankeschön") WebElement link_ThankYou;
	@FindBy (linkText="Produkte") WebElement link_Products;
	@FindBy (linkText="Blumensträuße") WebElement link_Bouquets;
	@FindBy (linkText="Topseller") WebElement link_Topseller;
	@FindBy (linkText="Pflanzen") WebElement link_Plants;
	@FindBy (linkText="Geschenksets") WebElement link_GiftSets;
	@FindBy (linkText="Torten & Geschenke") WebElement link_PiesAndGifts;
	@FindBy (linkText="Gute Besserung") WebElement link_GetWell;
	@FindBy (linkText="Liebe Grüße") WebElement link_BestRegards;
	//-----------------------------------MOBILE-------------------------------------------------
	@FindBy (xpath="(//*[contains(@class,'icon-profile')])[1]") WebElement mobile_Link_Login;
	@FindBy (xpath="//span[contains(@class,'icon-menu')]") WebElement mobile_HamburgerMenu;
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");
	
	public HomePage(WebDriver driver) 
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory 
		PageFactory.initElements(driver, this);
	}

	public WebElement linkLogin()
	{
		//Use the code below in case of using Page Factory Model
		return link_Login;
		//Comment the above code and Uncomment the code below in case using Page object instead of Page factory
		//return driver.findElement(link_Login);
	}
	public WebElement linkEvents()
	{
		bv.safariWaitWorkaround();
		bv.safariWaitWorkaround();
		return link_Events;
	}
	public WebElement linkBirthday()
	{
		bv.safariWaitWorkaround();
		return link_Birthday;
	}
	public WebElement linkThankYou()
	{
		return link_ThankYou;
	}
	
	public WebElement linkProducts()
	{
		bv.safariWaitWorkaround();
		bv.safariWaitWorkaround();
		return link_Products;
	}
	
	public void clickLinkProducts()
	{
		try {
			linkProducts().click();
		} catch(NoSuchElementException ex) {
			Assert.assertTrue(false, "Clicked on the Produkte link. " + ex.getMessage());
			throw ex;
		}
	}
	
	public WebElement linkBouquets()
	{
		return link_Bouquets;
	}
	public WebElement linkTopseller()
	{
		bv.safariWaitWorkaround();
		return link_Topseller;
	}
	public WebElement linkPlants()
	{
		return link_Plants;
	}
	public WebElement linkGiftSets()
	{
		bv.safariWaitWorkaround();
		bv.safariWaitWorkaround();
		return link_GiftSets;
	}
	public WebElement linkPiesAndGifts()
	{
		bv.safariWaitWorkaround();
		return link_PiesAndGifts;
	}
	public WebElement linkGetWell()
	{
		bv.safariWaitWorkaround();
		return link_GetWell;
	}
	public WebElement linkBestRegards()
	{
		return link_BestRegards;
	}
	public WebElement mobileHamburgerMenu()
	{
		return mobile_HamburgerMenu;
	}
	public WebElement mobileLinkLogin()
	{
		return mobile_Link_Login;
	}

}

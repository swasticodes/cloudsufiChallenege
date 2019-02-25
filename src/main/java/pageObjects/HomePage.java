package pageObjects;

import org.apache.http.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage 
{
	public WebDriver driver;
	
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
		return link_Events;
	}
	public WebElement linkBirthday()
	{
		return link_Birthday;
	}
	public WebElement linkThankYou()
	{
		return link_ThankYou;
	}
	
	public WebElement linkProducts()
	{
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
		return link_Topseller;
	}
	public WebElement linkPlants()
	{
		return link_Plants;
	}
	public WebElement linkGiftSets()
	{
		return link_GiftSets;
	}
	public WebElement linkPiesAndGifts()
	{
		return link_PiesAndGifts;
	}
	public WebElement linkGetWell()
	{
		return link_GetWell;
	}
	public WebElement linkBestRegards()
	{
		return link_BestRegards;
	}

}

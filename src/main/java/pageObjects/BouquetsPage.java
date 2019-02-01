package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BouquetsPage
{
	public WebDriver driver;

	@FindBy (xpath="//*[contains(@class,'container-list')]/div[3]/div[2]/div/div/div[2]/div[2]") WebElement link_Bouquets_Second_Item;
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");

	public BouquetsPage(WebDriver driver)
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory
		PageFactory.initElements(driver, this);
	}

	public WebElement linkBouquetsSecondItem()
	{
		//Use the code below in case of using Page Factory Model
		return link_Bouquets_Second_Item;
		//Comment the above code and Uncomment the code below in case using Page object instead of Page factory
		//return driver.findElement(link_Login);
	}
}

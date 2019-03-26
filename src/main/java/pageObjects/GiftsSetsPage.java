package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.BasicVariables;

public class GiftsSetsPage
{
	public WebDriver driver;
	BasicVariables bv = new BasicVariables();

	@FindBy (xpath="//*[contains(@class,'container-list')]/div[3]/div[3]/div/div[1]/div[2]/div[2]") WebElement link_SecondItem;
	@FindBy (xpath="//*[contains(@class,'container-list')]/div[3]/div[3]/div/div[1]/div[2]/div[1]") WebElement link_FirstItem;
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");

	public GiftsSetsPage(WebDriver driver)
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory
		PageFactory.initElements(driver, this);
	}

	public WebElement linkGiftsSetsSecondItem()
	{
		//Use the code below in case of using Page Factory Model
		bv.safariWaitWorkaround();
		return link_SecondItem;
		//Comment the above code and Uncomment the code below in case using Page object instead of Page factory
		//return driver.findElement(link_Login);
	}
	public WebElement linkGiftsSetsFirstItem()
	{
		bv.safariWaitWorkaround();
		return link_FirstItem;
	}
}

package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.BasicVariables;

public class PiesAndGiftsPage
{
	public WebDriver driver;
	BasicVariables bv = new BasicVariables(); 

	@FindBy (xpath="//*[contains(@class,'container-list')]/div[3]/div[2]/div/div/div[2]/div[1]") WebElement link_FirstItem;
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");

	public PiesAndGiftsPage(WebDriver driver)
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory
		PageFactory.initElements(driver, this);
	}
	public WebElement linkPiesAndGiftsFirstItem()
	{
		bv.safariWaitWorkaround();
		return link_FirstItem;
	}
}

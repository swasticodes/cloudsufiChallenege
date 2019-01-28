package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderOverviewPage
{
	public WebDriver driver;

	@FindBy (xpath="//*[contains(text(), 'Kaufen')]") WebElement btn_To_Buy;
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");

	public OrderOverviewPage(WebDriver driver)
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory
		PageFactory.initElements(driver, this);
	}

	public WebElement buttonToBuy()
	{
		//Use the code below in case of using Page Factory Model
		return btn_To_Buy;
		//Comment the above code and Uncomment the code below in case using Page object instead of Page factory
		//return driver.findElement(link_Login);
	}
}

package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	public WebDriver driver;
	
	@FindBy (linkText="Anmelden") WebElement link_Login;
	@FindBy (linkText="Anlässe") WebElement link_Events;
	@FindBy (linkText="Geburtstag") WebElement link_Birthday;
	@FindBy (linkText="Dankeschön") WebElement link_ThankYou;
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

}

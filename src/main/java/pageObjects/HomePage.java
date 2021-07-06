package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.BasicVariables;

public class HomePage 
{
	public WebDriver driver;
	BasicVariables bv = new BasicVariables();
	
	@FindBy (xpath="//*[contains(@class, 'header_user_info')]/a") WebElement link_signIn;
	@FindBy (xpath="//*[contains(@alt, 'My Store')]") WebElement button_HeaderLogo;
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");
	
	public HomePage(WebDriver driver) 
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory 
		PageFactory.initElements(driver, this);
	}

	public WebElement linkSignIn()
	{
		return link_signIn;
	}
	public WebElement buttonHeaderLogo()
	{
		return button_HeaderLogo;
	}

}

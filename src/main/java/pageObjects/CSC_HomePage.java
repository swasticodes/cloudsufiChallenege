package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.BasicVariables;

public class CSC_HomePage
{
	public WebDriver driver;
	BasicVariables bv = new BasicVariables();

	@FindBy (xpath="(//*[contains(@class,'v-menubar-menuitem v-menubar-menuitem-primary')])[1]") WebElement btn_NewOrder;
	@FindBy (xpath="(//*[contains(@class,'v-menubar-menuitem v-menubar-menuitem-primary')])[2]") WebElement btn_NewCustomer;
	@FindBy (xpath="//*[@id = 'gwt-uid-5']") WebElement textField_OrderNumber;
	@FindBy (xpath="//*[@class='v-button v-widget tiny v-button-tiny friendly v-button-friendly']") WebElement btn_Search;
	@FindBy (xpath="(//*[text()='Vorname'])[1]/parent::div/following-sibling::input") WebElement textField_firstName;
	@FindBy (xpath="(//*[text()='Nachname'])[1]/parent::div/following-sibling::input") WebElement textField_lastName;
	@FindBy (xpath="(//*[text()='Straﬂe'])[1]/parent::div/following-sibling::input") WebElement textField_StreetName;
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");

	public CSC_HomePage(WebDriver driver)
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory
		PageFactory.initElements(driver, this);
	}

	public WebElement buttonNewCustomer()
	{
		//Use the code below in case of using Page Factory Model
		return btn_NewCustomer;
		//Comment the above code and Uncomment the code below in case using Page object instead of Page factory
		//return driver.findElement(link_Login);
	}
	public WebElement buttonNewOrder()
	{
		return btn_NewOrder;
	}
	public void searchOrderNumber(String ordrNo) throws InterruptedException
	{
		textField_OrderNumber.sendKeys(ordrNo);
		Thread.sleep(2000);
		btn_Search.click();
	}
	public void textFieldFirstName(String firstName)
	{
		textField_firstName.sendKeys(firstName);
	}
	public void textFieldLastName(String lastName)
	{
		textField_lastName.sendKeys(lastName);
	}
	public void textFieldStreetName(String streetName)
	{
		textField_StreetName.sendKeys(streetName);
		textField_StreetName.sendKeys(Keys.RETURN);
	}
}

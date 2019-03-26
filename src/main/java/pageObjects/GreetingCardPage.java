package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.BasicVariables;

public class GreetingCardPage 
{
	public WebDriver driver;
	BasicVariables bv = new BasicVariables();
	
	@FindBy (xpath="//*[contains(@class,'greeting-card-list d-flex flex-wrap justify-content-around')]/div") WebElement link_GreetingCrad_First_Item;
	@FindBy (xpath="//*[@id=\"greetingTextForm\"]") WebElement textBox_GreetingText;
	@FindBy (xpath="//button[@id=\"submitGreetingCard\"]") WebElement btn_Continue_To_Gifts;
	@FindBy (name="checkout.grusskarte.liebe") WebElement tab_Love;
	@FindBy (xpath="(//*[contains(@class,'card-body')])[6]") WebElement tabLove_SecondItem;
	@FindBy (xpath="//*[contains(@class,'greeting-card-list d-flex flex-wrap justify-content-around')]/div[7]") WebElement link_LoveGreetingCard_SecondItem;
	@FindBy (xpath="(//a[contains(text(),'Dankeschˆn')])[3]") WebElement tab_ThankYou;
	@FindBy (xpath="(//*[contains(@class,'greeting-card-list d-flex flex-wrap justify-content-around')]/div)[3]") WebElement link_ThankYoGreetingCard_SecondItem;
	@FindBy (xpath="(//*[contains(text(),'Direkt zur Kasse')])[4]") WebElement btn_DirectlyToCashRegister;
	@FindBy (id="dropdownMenuButton") WebElement dropDown_GreetingTextTemplate;
	@FindBy (xpath="//*[@id='greetingDropDown']/li[1]") WebElement dropdown_MenuOption_ThankYou;
	@FindBy (xpath="//*[@id='greetingDropDown']/li[2]") WebElement dropdown_MenuOption_Love;
	@FindBy (xpath="//*[contains(@class,'greeting-text-form-error error-char')]") WebElement textBox_GreetingCardTextError;
//------------------------------------------MOBILE------------------------------------------------------------	
	@FindBy (xpath="//*[contains(text(),\"Gruﬂ bearbeiten\")]") WebElement mobile_Btn_EditGreeting;
	@FindBy (xpath="//*[contains(text(),'Weiter ohne Gruﬂtext')]") WebElement mobile_Btn_ContinueWithoutGreetingCardText;
	@FindBy (xpath="(//button[contains(text(),'Gruﬂtextvorlage ausw‰hlen')])[1]") WebElement mobile_DropDown_GreetingTextTemplate;
	@FindBy (xpath="//*[contains(@class,'modal select-list-modal show')]/div/div/div[2]/ul/li[1]") WebElement mobile_Dropdown_MenuOption_ThankYou;
	@FindBy (xpath="//*[contains(@class,'modal select-list-modal show')]/div/div/div[2]/ul/li[2]") WebElement mobile_Dropdown_MenuOption_Love;
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");
	
	public GreetingCardPage(WebDriver driver) 
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory 
		PageFactory.initElements(driver, this);
	}

	public WebElement linkGreetingCardFirstItem()
	{
		//Use the code below in case of using Page Factory Model
		bv.safariWaitWorkaround();
		return link_GreetingCrad_First_Item;
		//Comment the above code and Uncomment the code below in case using Page object instead of Page factory
		//return driver.findElement(link_Login);
	}
	public WebElement textboxGreetingCardText()
	{
		bv.safariWaitWorkaround();
		return textBox_GreetingText;
	}
	public WebElement buttonContinueToGifts()
	{
		return btn_Continue_To_Gifts;
	}
	public WebElement tabLove()
	{
		return tab_Love;
	}
	public WebElement tabLoveSecondItem()
	{
		return tabLove_SecondItem;
	}
	public WebElement secondGreetingCardLoveTab()
	{
		return link_LoveGreetingCard_SecondItem;
	}
	public WebElement tabThankYou()
	{
		return tab_ThankYou;
	}
	public WebElement link_ThankYouGreetingCard_SecondItem()
	{
		return link_ThankYoGreetingCard_SecondItem;
	}
	public WebElement buttonGreetingCardsPageDirectlyToCashRegister()
	{
		return btn_DirectlyToCashRegister;
	}
	public WebElement dropdownSelectGreetingTextTemplate()
	{
		bv.safariWaitWorkaround();
		return dropDown_GreetingTextTemplate;
	}
	public WebElement greetingCardTemplateThankYou()
	{
		return dropdown_MenuOption_ThankYou;
	}
	public WebElement greetingCardTemplateLove()
	{
		return dropdown_MenuOption_Love;
	}
	public WebElement textboxGreetingCardTextCharError()
	{
		return textBox_GreetingCardTextError;
	}
	public WebElement mobileButtonEditGreetingText()
	{
		return mobile_Btn_EditGreeting;
	}
	public WebElement mobileDropdownSelectGreetingTextTemplate()
	{
		return mobile_DropDown_GreetingTextTemplate;
	}
	public WebElement mobileGreetingCardTemplateThankYou()
	{
		return mobile_Dropdown_MenuOption_ThankYou;
	}
	public WebElement mobileGreetingCardTemplateLove()
	{
		return mobile_Dropdown_MenuOption_Love;
	}
	public WebElement mobileButtonContinueWithoutGreetingCardText()
	{
		return mobile_Btn_ContinueWithoutGreetingCardText;
	}
}

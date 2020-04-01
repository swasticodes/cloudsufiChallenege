package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.BasicVariables;

public class CSC_GeneralPage
{
	public WebDriver driver;
	BasicVariables bv = new BasicVariables();

	@FindBy (xpath="//*[@class = 'v-button v-widget small v-button-small icon-align-right v-button-icon-align-right']") WebElement btn_next;
	@FindBy (xpath="//*[text() = 'Es trat ein Fehler auf. Bitte später versuchen oder an die IT wenden.']") WebElement error_popup_technical_support;
	@FindBy (xpath="//*[text() = 'Achtung']") WebElement popUp_Warning;
	@FindBy (xpath="//*[text() = 'Ok']") WebElement button_Ok;
	@FindBy (xpath="//*[contains(text(),'3 Hauptprodukt')]") WebElement tab_mainProduct;
	@FindBy (xpath="//*[contains(@class,'v-filterselect-input')]") WebElement textField_searchArticle;
	@FindBy (xpath="//*[contains(text(),'aktualisieren')]") WebElement btn_update;
	@FindBy (xpath="(//*[contains(text(),'Preis')])[2]/font") WebElement text_priceOnCsc;
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");

	public CSC_GeneralPage(WebDriver driver)
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory
		PageFactory.initElements(driver, this);
	}

	public WebElement buttonNext()
	{
		//Use the code below in case of using Page Factory Model
		return btn_next;
		//Comment the above code and Uncomment the code below in case using Page object instead of Page factory
		//return driver.findElement(link_Login);
	}
	public WebElement errorPopupTechnicalSupport()
	{
		return error_popup_technical_support;
	}
	public WebElement buttonOk()
	{
		return button_Ok;
	}
	public WebElement popUpWarning()
	{
		return popUp_Warning;
	}
	public WebElement tabMainProduct()
	{
		return tab_mainProduct;
	}
	public void searchArticle(String articleId) throws InterruptedException
	{
		textField_searchArticle.sendKeys(articleId);
		Thread.sleep(1000);
		textField_searchArticle.sendKeys(Keys.RETURN);
	}
	public String textPriceOnCsc()
	{
		return text_priceOnCsc.getText().substring(0, 5).replace(".", ",");
	}
}

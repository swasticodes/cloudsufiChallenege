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
	@FindBy (xpath="//*[text() = 'Es trat ein Fehler auf. Bitte sp�ter versuchen oder an die IT wenden.']") WebElement error_popup_technical_support;
	@FindBy (xpath="//*[text() = 'Achtung']") WebElement popUp_Warning;
	@FindBy (xpath="//*[text() = 'Ok']") WebElement button_Ok;
	@FindBy (xpath="//*[contains(text(),'3 Hauptprodukt')]") WebElement tab_mainProduct;
	@FindBy (xpath="//*[contains(@class,'v-filterselect-input')]") WebElement textField_searchArticle;
	@FindBy (xpath="//*[text()='aktualisieren']") WebElement btn_update;
	@FindBy (xpath="(//*[contains(text(),'Preis')])[2]/font") WebElement text_priceOnCsc;
	@FindBy (xpath="//*[@class='v-inline-datefield-calendarpanel-day v-inline-datefield-calendarpanel-day-today']/parent::*/following-sibling::*") WebElement date_deliveryDate;
	@FindBy (xpath="//*[contains(text(), 'Standard-Lieferung')]") WebElement radiioButton_standardDelivery;
	@FindBy (xpath="//*[text()='Lastschrift']") WebElement radioButton_directDebit;
	@FindBy (xpath="//*[text()='IBAN']/parent::*/parent::*/parent::*//td[3]/input") WebElement textField_numberIBAN;
	@FindBy (xpath="//*[text()='Kontoinhaber']/parent::*/parent::*/parent::*/td[3]/input") WebElement textField_accountHolder;
	@FindBy (xpath="//*[text()='�berpr�fen']") WebElement btn_verify;
	@FindBy (xpath="//*[text()='Bestellen']/parent::*/parent::*") WebElement btn_placeOrder;
	@FindBy (xpath="(//*[@class='v-label v-widget v-label-undef-w'])[2]") WebElement textbox_OrderConfirmationMessage;
	@FindBy (xpath="//*[text()='Editieren']/parent::*") WebElement btn_Edit;

	String testIbanNumber = "DE02120300000000202051";
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
	public void textFieldSearchArticle(String articleId) throws InterruptedException
	{
		textField_searchArticle.sendKeys(articleId);
		Thread.sleep(1000);
		textField_searchArticle.sendKeys(Keys.RETURN);
	}
	public String textPriceOnCsc()
	{
		return text_priceOnCsc.getText().substring(0, 5).replace(".", ",");
	}
	public WebElement dateDeliverDate()
	{
		return date_deliveryDate;
	}
	public WebElement radioButtonStandardDelivery()
	{
		return radiioButton_standardDelivery;
	}
	public WebElement buttonUpdate()
	{
		return btn_update;
	}
	public WebElement radioButtonDirectDebit()
	{
		return radioButton_directDebit;
	}
	public void textFieldNumberIBAN() throws InterruptedException
	{
		textField_numberIBAN.sendKeys(testIbanNumber);
		Thread.sleep(1000);
		textField_numberIBAN.sendKeys(Keys.RETURN);
	}
	public void textFieldAccountHolder()
	{
		textField_accountHolder.sendKeys("Max Mustrmann");
	}
	public WebElement buttonVerify()
	{
		return btn_verify;
	}
	public WebElement buttonPlaceOrder()
	{
		return btn_placeOrder;
	}
	public WebElement textBoxOrderConfirmationMessage()
	{
		return textbox_OrderConfirmationMessage;
	}
	public WebElement buttonEdit()
	{
		return btn_Edit;
	}
}

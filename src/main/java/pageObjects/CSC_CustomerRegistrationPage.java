package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.BasicVariables;

public class CSC_CustomerRegistrationPage
{
	public WebDriver driver;
	BasicVariables bv = new BasicVariables();

	@FindBy (xpath="(//*[@class = 'v-filterselect-button'])[1]") WebElement dropdownButton_Salutation;
	@FindBy (xpath="//*[contains(text() , 'Herr')]") WebElement dropdownOption_Herr;
	@FindBy (xpath="//*[text()='Vorname:']/parent::*/parent::*/parent::*/parent::*/tr[2]/td[3]/input") WebElement textField_FirstName;
	@FindBy (xpath="//*[text() ='Nachname:']/parent::*/parent::*/parent::*/parent::*/tr[2]/td[3]/input") WebElement textField_LastName;
	@FindBy (xpath="//*[text() = 'Strasse:']/parent::*/parent::*/parent::*/parent::*/tr[4]/td[3]/input") WebElement textField_Street;
	@FindBy (xpath="//*[text() = 'Nr.:']/parent::*/parent::*/parent::*/parent::*/tr[4]/td[3]/input") WebElement textField_HouseNumber;
	@FindBy (xpath="//*[text() = 'Plz:']/parent::*/parent::*/parent::*/parent::*/tr[5]/td[3]/input") WebElement textField_PostalCode;
	@FindBy (xpath="//*[text() = 'Ort:']/parent::*/parent::*/parent::*/parent::*/tr[5]/td[3]/input") WebElement textField_City;
	@FindBy (xpath="//*[@class= 'v-button v-widget friendly v-button-friendly']") WebElement button_Save;
	@FindBy (xpath="//*[text() = 'E-Mail:']/parent::*/parent::*/parent::*/parent::*/tr[6]/td[3]/input") WebElement textField_Email;
	@FindBy (xpath="//*[text() = 'Tel.-Nr. 1:']/parent::*/parent::*/parent::*/parent::*/tr[7]/td[3]/input") WebElement textField_TelNr;
	@FindBy (xpath="(//*[@class='v-button v-widget friendly v-button-friendly buttonMenuBar v-button-buttonMenuBar'])[2]") WebElement btn_SaveEditedAddress;
	@FindBy (xpath="(//*[@class='v-label v-widget v-label-undef-w'])[3]") WebElement textBox_SuccessfulEdit;
	@FindBy (xpath="(//*[@class='v-label v-widget v-label-undef-w'])[4]") WebElement textBox_SuccessfulOrderEdit;

	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");

	public CSC_CustomerRegistrationPage(WebDriver driver)
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory
		PageFactory.initElements(driver, this);
	}

	public WebElement dropdwonButtonSalutation()
	{
		//Use the code below in case of using Page Factory Model
		return dropdownButton_Salutation;
		//Comment the above code and Uncomment the code below in case using Page object instead of Page factory
		//return driver.findElement(link_Login);
	}
	public WebElement dropdownOptionHerr()
	{
		return dropdownOption_Herr;
	}
	public WebElement textFieldFirstName()
	{
		return textField_FirstName;
	}
	public WebElement textFieldStreet()
	{
		return textField_Street;
	}
	public WebElement textFieldPostalCode()
	{
		return textField_PostalCode;
	}
	public WebElement textFieldHouseNumber()
	{
		return textField_HouseNumber;
	}
	public WebElement textFieldCity()
	{
		return textField_City;
	}
	public WebElement textFieldLastName()
	{
		return textField_LastName;
	}
	public WebElement textFieldEmail() {
		return textField_Email;
	}
	public WebElement buttonSave()
	{
		return button_Save;
	}
	public WebElement textFieldTelepjoneNumber()
	{
		return textField_TelNr;
	}
	public WebElement buttonSaveEditedAddress()
	{
		return btn_SaveEditedAddress;
	}
	public WebElement textBoxSuccessfuledit()
	{
		return textBox_SuccessfulEdit;
	}
	public WebElement textBoxSuccessfulOrderEdit()
	{
		return textBox_SuccessfulOrderEdit;
	}
}

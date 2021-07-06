package pageObjects;

import org.apache.regexp.recompile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAnAccountPage 
{
	public WebDriver driver;
	
	@FindBy (xpath="(//*[contains(@id, 'noSlide')]/h1)") WebElement txt_headerCreateAnAccount;
	@FindBy (xpath="(//*[contains(@id, 'gender1')])[1]") WebElement radioBtn_salutationMr;	
	@FindBy (xpath="(//*[contains(@id, 'customer_firstname')])") WebElement textField_firstName;
	@FindBy (xpath="(//*[contains(@id, 'customer_lastname')])") WebElement textField_lastName;
	@FindBy (xpath="(//*[contains(@id, 'email')])") WebElement textField_email;
	@FindBy (xpath="(//*[contains(@id, 'passwd')])") WebElement textField_password;
	@FindBy (xpath="(//*[contains(@id, 'days')])") WebElement dropdown_date;
	@FindBy (xpath="(//*[contains(@id, 'months')])") WebElement dropdown_month;
	@FindBy (xpath="(//*[contains(@id, 'years')])") WebElement dropdown_year;
	@FindBy (xpath="(//*[contains(@id, 'address1')])") WebElement textField_address;
	@FindBy (xpath="(//*[contains(@id, 'city')])") WebElement textField_city;
	@FindBy (xpath="(//*[contains(@id, 'id_state')])") WebElement dropdown_state;
	@FindBy (xpath="(//*[contains(@id, 'postcode')])") WebElement textField_postalCode;
	@FindBy (xpath="(//*[contains(@id, 'phone_mobile')])") WebElement textField_phone_mobile;
	@FindBy (xpath="(//*[contains(@id, 'submitAccount')])") WebElement button_register;
	@FindBy (xpath="//*[contains(@class, 'info-account')]") WebElement text_accountInfo;
	@FindBy (xpath="//*[contains(@class, 'alert alert-danger')]") WebElement textBox_errorMsg;
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");
	
	public CreateAnAccountPage(WebDriver driver) 
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory 
		PageFactory.initElements(driver, this);
	}

	public WebElement textHeaderCreateAnAccount()
	{
		return txt_headerCreateAnAccount;
	}
	public WebElement radioBtnSalutationMr()
	{
		return radioBtn_salutationMr;
	}
	public WebElement textFieldFirstName()
	{
		return textField_firstName;
	}
	public WebElement textFieldLastName()
	{
		return textField_lastName;
	}
	public WebElement textFieldEmail()
	{
		return textField_email;
	}
	public WebElement textFieldPassword()
	{
		return textField_password;
	}
	public WebElement dropdownDate()
	{
		return dropdown_date;
	}
	public WebElement dropdownMonth()
	{
		return dropdown_month;
	}
	public WebElement dropdownYear()
	{
		return dropdown_year;
	}
	public WebElement textFieldAddress()
	{
		return textField_address;
	}
	public WebElement textFieldCity()
	{
		return textField_city;
	}
	public WebElement dropdownState()
	{
		return dropdown_state;
	}
	public WebElement textFieldPostalCode()
	{
		return textField_postalCode;
	}
	public WebElement textFieldPhoneMobile()
	{
		return textField_phone_mobile;
	}
	public WebElement buttonRegister()
	{
		return button_register;
	}
	public WebElement textAccountInfo()
	{
		return text_accountInfo;
	}
	public WebElement textBoxErrorMsg()
	{
		return textBox_errorMsg;
	}

}

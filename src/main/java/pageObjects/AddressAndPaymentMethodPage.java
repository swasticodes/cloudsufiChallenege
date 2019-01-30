package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddressAndPaymentMethodPage
{
	public WebDriver driver;

	@FindBy (xpath="//*[@id=\"shippingAndPaymentForm\"]/section[4]/div/div[2]/div[1]/ul/li[3]/a/div/div[1]/label/span[1]") WebElement radioButton_DebitCard_Payment;
	@FindBy (xpath="//*[@id=\"debitCardHolder\"]") WebElement textField_AccountHolder;
	@FindBy (xpath="//*[@id=\"debitIBAN\"]") WebElement textField_IBAN_Number;
	@FindBy (xpath="//*[@id=\"debitBIC\"]") WebElement textField_BIC_Number;
	@FindBy (xpath="//*[@id=\"debitBankName\"]") WebElement textField_Bank;
	@FindBy (xpath="//*[@id=\"tab-debit\"]/div[2]/div[5]/label/span[1]") WebElement chkBox_SEPADirectDebit;
	@FindBy (xpath="//span[contains(text(),'Kreditkarte')]") WebElement radioButton_CreditCard; ///html/body/div[2]/div[2]/form/footer/ul/li[3]/button
	@FindBy (xpath="//*[@id=\"sfpCardExpiryMonth\"]") WebElement dropDown_CreditCard_ExpiryMonth;
	@FindBy (xpath="//*[@id=\"sfpCardExpiryYear\"]") WebElement dropDown_CreditCard_ExpiryYear;
	@FindBy (xpath="//*[@id=\"sfpCardNumber\"]") WebElement textField_CreditCardNumber;
	@FindBy (name="SubmitToNext") WebElement btn_CreditCard_Continue;
	@FindBy (xpath="//span[contains(text(),'Rechnung')]") WebElement radioButton_Invoice;

	String SEPAAccountHolder = "Tester";
	String IBANNumber = "DE12500105170648489890";
	String CreditCardNumber = "9030100052000000"; //(Visa 9010100052000004, 12/2022, 123) (Amex 9070100052000001, 12/2018, 123)
	String CreditCardExpiryMonth = "12";
	String CreditCardExpiryYear = "2022";
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");

	public AddressAndPaymentMethodPage(WebDriver driver)
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory
		PageFactory.initElements(driver, this);
	}

	public WebElement radioButtonDebitCard()
	{
		//Use the code below in case of using Page Factory Model
		return radioButton_DebitCard_Payment;
		//Comment the above code and Uncomment the code below in case using Page object instead of Page factory
		//return driver.findElement(link_Login);
	}
	public WebElement textFieldAccountHolder()
	{
		return textField_AccountHolder;
	}
	public WebElement textFieldIBANNumber()
	{
		return textField_IBAN_Number;
	}
	public WebElement textFieldBICNumber()
	{
		return textField_BIC_Number;
	}
	public WebElement textFieldBank()
	{
		return textField_Bank;
	}
	public WebElement chkBoxSEPADirectDebit()
	{
		return chkBox_SEPADirectDebit;
	}
	public String accountHolderName()
	{
		return SEPAAccountHolder;
	}
	public String ibanNumber()
	{
		return IBANNumber;
	}
	public WebElement radioButtonCreditCard()
	{
		return radioButton_CreditCard;
	}
	public String creditCardExpiryMonth()
	{
		return CreditCardExpiryMonth;
	}
	public String creditCardExpiryYear()
	{
		return CreditCardExpiryYear;
	}
	public String creditCardNumber()
	{
		return CreditCardNumber;
	}
	public WebElement dropDownCreditCardExpiryMonth()
	{
		return dropDown_CreditCard_ExpiryMonth;
	}
	public WebElement dropDownCreditCardExpiryYear()
	{
		return dropDown_CreditCard_ExpiryYear;
	}
	public WebElement textFieldCreditCardNumber()
	{
		return textField_CreditCardNumber;
	}
	public WebElement creditCardContinueButton()
	{
		return btn_CreditCard_Continue;
	}
	public WebElement radioButtonInvoice()
	{
		return radioButton_Invoice;
	}
}

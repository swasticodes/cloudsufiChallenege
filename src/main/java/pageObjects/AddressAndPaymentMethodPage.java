package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.BasicVariables;

public class AddressAndPaymentMethodPage
{
	public WebDriver driver;
	BasicVariables bv = new BasicVariables();

	@FindBy (xpath="//span[contains(text(),'Bankeinzug/Lastschrift')]") WebElement radioButton_DebitCard_Payment;
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
	@FindBy (xpath="//span[contains(text(),'Ratepay Rechnung')]") WebElement radioButton_Ratepay;
	@FindBy (xpath="//span[contains(text(),'PayPal')]") WebElement radioButton_PayPal;
	@FindBy (xpath="//span[contains(text(),'Gutscheincode einlösen')]") WebElement chkBox_UseCouponCode;
	@FindBy (name="voucherCode") WebElement textField_VoucherCode;
	@FindBy (id="voucherCodeSubmit") WebElement btn_SubmitVoucherCode;
	@FindBy (xpath="//span[contains(text(),'Abwicklung des Rechnungskaufs')]") WebElement chkBox_InvoiceConsentDeclaration;
	@FindBy (id="invoiceBirthDay") WebElement dropdown_BirthDate;
	@FindBy (id="invoiceBirthMonth") WebElement dropdown_BirthMonth;
	@FindBy (id="invoiceBirthYear") WebElement dropdown_BirthYear;
	@FindBy (id="invoicePhone") WebElement textField_RatepayTelephoneNumber;
	@FindBy (xpath="//span[.//text()= 'Rechnung']")  WebElement radioButton_Invoice;
	@FindBy (xpath="//span[contains(text(),'Geschenkkarte einlösen')]") WebElement chkBox_UseGiftCard;
	@FindBy (id="giftCardNumber") WebElement textField_GiftCardNumber;
	@FindBy (id="giftCardPin") WebElement textField_GiftCardPin;
	@FindBy (id="giftCardSubmit") WebElement btn_RedeemGiftCard;
	@FindBy (id="giftCardSuccessMessage") WebElement textBox_GiftCardSuccessMessage;
	@FindBy (xpath="//*[@id='giftCardSuccessMessage']/div[2]/div[2]") WebElement textBox_GiftCardRemainingAmout;
	@FindBy (id="voucherSuccessMessage") WebElement textBox_VoucherCodeSuccessMessage;

	String SEPAAccountHolder = "Tester";
	String IBANNumber = "DE12500105170648489890";
	String CreditCardNumber = "9030100052000000"; //(Visa 9010100052000004, 12/2022, 123) (Amex 9070100052000001, 12/2018, 123)
	String CreditCardExpiryMonth = "12";
	String CreditCardExpiryYear = "2022";
	String VoucherCodeAllItems = "TESTFUERSUSI";
	String RatePayBirtDate = "27";
	String RatePayBirtMonth = "Mai";
	String RatePayBirtYear = "1956";
	String RatepayTelephoneNumber = "054837527";
	String GiftCardNumber = "2000365328296387";//2000365328296387(6164),2000480173588590(8972), 2000468883528648(4735), 
	String GiftCardPin = "6164";
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
		bv.safariWaitWorkaround();
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
	public WebElement radioButtonRatepay()
	{
		return radioButton_Ratepay;
	}
	public WebElement radioButtonPayPal()
	{
		return radioButton_PayPal;
	}
	public WebElement checkBox_UseCouponCode()
	{
		return chkBox_UseCouponCode;
	}
	public WebElement textField_VoucherCode()
	{
		return textField_VoucherCode;
	}
	public String voucherCode()
	{
		return VoucherCodeAllItems;
	}
	public WebElement buttonSubmitVoucherCode()
	{
		return btn_SubmitVoucherCode;
	}
	public WebElement checkBoxInvoiceConsentDeclaration()
	{
		return chkBox_InvoiceConsentDeclaration;
	}
	public WebElement dropdownBirthDate()
	{
		return dropdown_BirthDate;
	}
	public WebElement dropdownBirthMonth()
	{
		return dropdown_BirthMonth;
	}
	public WebElement dropdownBirthYear()
	{
		return dropdown_BirthYear;
	}
	public WebElement textFieldRatepayTelephoneNumber()
	{
		return textField_RatepayTelephoneNumber;
	}
	public String ratepayBirthDate()
	{
		return RatePayBirtDate;
	}
	public String ratepayBirthMonth()
	{
		return RatePayBirtMonth;
	}
	public String ratepayBirthYear()
	{
		return RatePayBirtYear;
	}
	public String ratepayTelephoneNumber()
	{
		return RatepayTelephoneNumber;
	}
	public WebElement radioButtonInvoice()
	{
		return radioButton_Invoice;
	}
	public WebElement chkBoxUseGiftCard()
	{
		return chkBox_UseGiftCard;
	}
	public WebElement textFieldGiftCardNumber()
	{
		return textField_GiftCardNumber;
	}
	public WebElement textFieldGiftCardPin()
	{
		return textField_GiftCardPin;
	}
	public WebElement buttonRedeemGiftCard()
	{
		bv.safariWaitWorkaround();
		return btn_RedeemGiftCard;
	}
	public String giftCardNumber()
	{
		return GiftCardNumber;
	}
	public String giftCardpin()
	{
		return GiftCardPin;
	}
	public WebElement textBoxGiftCardSuccessMessage()
	{
		bv.safariWaitWorkaround();
		bv.safariWaitWorkaround();
		return textBox_GiftCardSuccessMessage;
	}
	public WebElement textBoxGiftCardPayableAmount()
	{
		return textBox_GiftCardRemainingAmout;
	}
	public WebElement textBoxVoucherCodeSuccess()
	{
		bv.safariWaitWorkaround();
		return textBox_VoucherCodeSuccessMessage;
	}

}

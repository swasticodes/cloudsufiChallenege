package Blume2000;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.OrderOverviewPage;
import pageObjects.PayPalPage;
import pageObjects.RegistrationPage;
import pageObjects.AddressAndPaymentMethodPage;
import pageObjects.DeliveryPage;
import pageObjects.GeburtstagPage;
import pageObjects.GeneralPage;
import resources.BasicVariables;

public class accountTypeNewCustomer<inherits> extends BasicVariables {
	//Use the line below to enable logging in your test cases
	public static Logger log = LogManager.getLogger(accountTypeNewCustomer.class.getName());
	
	@BeforeMethod
	public void openBrowser() throws IOException
	{
		log.info("Driver is initializing");
		driver = initializeDriver();
		log.info("Opening the URL "+(prop.getProperty("URL")));
		driver.get(prop.getProperty("URL"));
	}
	@Test
	public void newCustomerPaypalPaymentMethodTest() throws InterruptedException, IOException
	{
		log.info("*** Starting Test: newCustomerPaypalPaymentMethodTest");
		
		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		GeburtstagPage birthdayPage = new GeburtstagPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		PayPalPage payPalPage = new PayPalPage(driver);

		ensurePageLoaded();
		
		generalPage.clickCloseCookieMessage(false);

		homePage.linkEvents().click();
		log.info("Clicked on the Anlässe link");
		homePage.linkBirthday().click();
		log.info("Clicked on the Geburtstag link");
		birthdayPage.linkBirthdayFirstItem().click();
		log.info("Selecting the first item on the birtday page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		Thread.sleep(1000);
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("22");
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("2");
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("97");
		log.info("Entered the delivery postal code 22297");
		deliveryPage.buttonNextPostalCode().click();
		log.info("Clicked the next button after entering Postal Code");
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grußkarte button");
		Thread.sleep(1000);
		deliveryPage.continueWithoutGreetingCard().click();
		log.info("Clicked on Weiter ohne Grußkarte button");
		Thread.sleep(1000);
		deliveryPage.continueWithoutGifts().click();
		log.info("Clicked on Weiter ohne Geschenke button");
		//scrolling the register button into view so that it is not hidden behind the confirmation for cookie message
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,120)");
		loginPage.buttonRegister().click();
		log.info("Clicked on the Register Button");
		//Creating a random email id to register user
		String emailId = RandomStringUtils.randomAlphabetic(8); 
		registerationPage.registrationEmail().sendKeys(emailId+"@testemail.com");
		log.info("For registeration entered email id "+ emailId);
		registerationPage.registrationPassword().sendKeys("123456");
		log.info("For registeration entered password as 123456");
		registerationPage.registrationConfrimPassword().sendKeys("123456");
		log.info("For registeration confirmed password as 123456");
		registerationPage.registrationSalutation().click();
		log.info("For registration selected salutation as Herr");
		registerationPage.registrationFirstName().sendKeys("TestFirst");
		log.info("For registeration entered first name as TestFirst");
		registerationPage.registrationLastName().sendKeys("TestLast");
		log.info("For registeration entered last name as TestLast");
		registerationPage.registrationStreet().sendKeys("Überseering");
		log.info("For registeration entered street name as Überseering");
		registerationPage.registrationStreetNumber().sendKeys("33");
		log.info("For registeration entered street number as 33");
		registerationPage.registrationCity().sendKeys("Hamburg");
		log.info("For registeration entered city as Hamburg");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-180)");
		registerationPage.copyDeliveryAndInvoiceAddress().click();
		log.info("Checking the checkbox so that delivery and invoice address are same");
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");

		payPalPage.PayPalLogin();
		driver.switchTo().defaultContent();
		payPalPage.buttonPaypalPaymentConfirmation().click();
		driver.switchTo().defaultContent();
		log.info("Clicked on the Jetzt bezhalen for payment confirmation");
		Assert.assertEquals("Glückwunsch! Gute Wahl getroffen", generalPage.textOrderConfirmation().getText());
		log.info("Order is placed successfully");
		
		log.info("*** Finished Test: newCustomerPaypalPaymentMethodTest");
	}

	@Test
	public void newCustomerSEPADirectDebitMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: newCustomerSEPADirectDebitMethodTest");
		
		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);

		ensurePageLoaded();
		
		generalPage.clickCloseCookieMessage(false);

		homePage.linkEvents().click();
		log.info("Clicked on the Anlässe link");
		homePage.linkThankYou().click();
		log.info("Clicked on the Dankeschön link");
		generalPage.linkFirstItem().click();
		log.info("Selecting the first item on Dankeschön page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		Thread.sleep(1000);
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("22");
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("2");
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("97");
		log.info("Entered the delivery postal code 22297");
		deliveryPage.buttonNextPostalCode().click();
		log.info("Clicked the next button after entering Postal Code");
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grußkarte button");
		Thread.sleep(1000);
		deliveryPage.continueWithoutGreetingCard().click();
		log.info("Clicked on Weiter ohne Grußkarte button");
		Thread.sleep(1000);
		deliveryPage.continueWithoutGifts().click();
		log.info("Clicked on Weiter ohne Geschenke button");
		//scrolling the register button into view so that it is not hidden behind the confirmation for cookie message
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,120)");
		loginPage.buttonRegister().click();
		log.info("Clicked on the Register Button");
		//Creating a random email id to register user
		String emailId = RandomStringUtils.randomAlphabetic(8);
		registerationPage.registrationEmail().sendKeys(emailId+"@testemail.com");
		log.info("For registeration entered email id "+ emailId);
		registerationPage.registrationPassword().sendKeys("123456");
		log.info("For registeration entered password as 123456");
		registerationPage.registrationConfrimPassword().sendKeys("123456");
		log.info("For registeration confirmed password as 123456");
		registerationPage.registrationSalutation().click();
		log.info("For registration selected salutation as Herr");
		registerationPage.registrationFirstName().sendKeys("TestFirst");
		log.info("For registeration entered first name as TestFirst");
		registerationPage.registrationLastName().sendKeys("TestLast");
		log.info("For registeration entered last name as TestLast");
		registerationPage.registrationStreet().sendKeys("Überseering");
		log.info("For registeration entered street name as Überseering");
		registerationPage.registrationStreetNumber().sendKeys("33");
		log.info("For registeration entered street number as 33");
		registerationPage.registrationCity().sendKeys("Hamburg");
		log.info("For registeration entered city as Hamburg");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-180)");
		registerationPage.copyDeliveryAndInvoiceAddress().click();
		log.info("Checking the checkbox so that delivery and invoice address are same");
		addressAndPaymentPage.radioButtonDebitCard().click();
		log.info("Selected the Bankeinzug/Lastschrift (Debit Card)radio button");
		addressAndPaymentPage.textFieldAccountHolder().sendKeys(addressAndPaymentPage.accountHolderName());
		log.info("Entered the account holder name as "+ addressAndPaymentPage.accountHolderName());
		addressAndPaymentPage.textFieldIBANNumber().sendKeys(addressAndPaymentPage.ibanNumber());
		log.info("Entered the INAN number as "+ addressAndPaymentPage.ibanNumber());
		addressAndPaymentPage.chkBoxSEPADirectDebit().click();
		log.info("Checked the Direct debit checkbox");
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");
		Assert.assertEquals("Glückwunsch! Gute Wahl getroffen", generalPage.textOrderConfirmation().getText());
		log.info("Order is placed successfully");
		
		log.info("*** Finished Test: newCustomerSEPADirectDebitMethodTest");
	}

	@Test
	public void newCustomerCreditCardMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: newCustomerCreditCardMethodTest");
		
		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);

		ensurePageLoaded();
		
		generalPage.clickCloseCookieMessage(false);

		homePage.linkProducts().click();
		log.info("Clicked on the Produkte link");
		homePage.linkBouquets().click();
		log.info("Clicked on the Blumensträuße link");
		generalPage.linkFirstItem().click();
		log.info("Selecting the first item on Blumensträuße page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		Thread.sleep(1000);
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("22");
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("2");
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("97");
		log.info("Entered the delivery postal code 22297");
		deliveryPage.buttonNextPostalCode().click();
		log.info("Clicked the next button after entering Postal Code");
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grußkarte button");
		Thread.sleep(1000);
		deliveryPage.continueWithoutGreetingCard().click();
		log.info("Clicked on Weiter ohne Grußkarte button");
		Thread.sleep(1000);
		deliveryPage.continueWithoutGifts().click();
		log.info("Clicked on Weiter ohne Geschenke button");
		//scrolling the register button into view so that it is not hidden behind the confirmation for cookie message
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,120)");
		loginPage.buttonRegister().click();
		log.info("Clicked on the Register Button");
		//Creating a random email id to register user
		String emailId = RandomStringUtils.randomAlphabetic(8);
		registerationPage.registrationEmail().sendKeys(emailId+"@testemail.com");
		log.info("For registeration entered email id "+ emailId);
		registerationPage.registrationPassword().sendKeys("123456");
		log.info("For registeration entered password as 123456");
		registerationPage.registrationConfrimPassword().sendKeys("123456");
		log.info("For registeration confirmed password as 123456");
		registerationPage.registrationSalutation().click();
		log.info("For registration selected salutation as Herr");
		registerationPage.registrationFirstName().sendKeys("TestFirst");
		log.info("For registeration entered first name as TestFirst");
		registerationPage.registrationLastName().sendKeys("TestLast");
		log.info("For registeration entered last name as TestLast");
		registerationPage.registrationStreet().sendKeys("Überseering");
		log.info("For registeration entered street name as Überseering");
		registerationPage.registrationStreetNumber().sendKeys("33");
		log.info("For registeration entered street number as 33");
		registerationPage.registrationCity().sendKeys("Hamburg");
		log.info("For registeration entered city as Hamburg");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-180)");
		registerationPage.copyDeliveryAndInvoiceAddress().click();
		log.info("Checking the checkbox so that delivery and invoice address are same");
		addressAndPaymentPage.radioButtonCreditCard().click();
		log.info("Selected the payment method as Credit Card by clicking radio button Kreditkarte");
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");
		driver.switchTo().frame(0);
		log.info("Switched Frame so that credit card details can be entered");
		addressAndPaymentPage.textFieldCreditCardNumber().sendKeys(addressAndPaymentPage.creditCardNumber());
		log.info("Entering the credit card number as "+addressAndPaymentPage.creditCardNumber());
		addressAndPaymentPage.dropDownCreditCardExpiryMonth().sendKeys(addressAndPaymentPage.creditCardExpiryMonth());
		log.info("Entered Credit Card Expiry Month as "+addressAndPaymentPage.creditCardExpiryMonth());
		addressAndPaymentPage.dropDownCreditCardExpiryYear().sendKeys(addressAndPaymentPage.creditCardExpiryYear());
		log.info("Entered Credit Card Expiry Year as "+addressAndPaymentPage.creditCardExpiryYear());
		addressAndPaymentPage.creditCardContinueButton().click();
		log.info("Clicked on the Weiter button on the Credit Card page");
		driver.switchTo().defaultContent();
		Assert.assertEquals("Glückwunsch! Gute Wahl getroffen", generalPage.textOrderConfirmation().getText());
		log.info("Order is placed successfully");
	
		log.info("*** Finished Test: newCustomerCreditCardMethodTest");
	}

	@Test(enabled=false)
	public void newCustomerInvoiceMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: newCustomerInvoiceMethodTest");
		
		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);

		ensurePageLoaded();
		
		generalPage.clickCloseCookieMessage(false);
		homePage.linkTopseller().click();
		log.info("Clicked on the Topseller link");
		generalPage.linkFirstItem().click();
		log.info("Selecting the first item on Blumensträuße page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		Thread.sleep(1000);
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("22");
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("2");
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("97");
		log.info("Entered the delivery postal code 22297");
		deliveryPage.buttonNextPostalCode().click();
		log.info("Clicked the next button after entering Postal Code");
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grußkarte button");
		Thread.sleep(1000);
		deliveryPage.continueWithoutGreetingCard().click();
		log.info("Clicked on Weiter ohne Grußkarte button");
		Thread.sleep(1000);
		deliveryPage.continueWithoutGifts().click();
		log.info("Clicked on Weiter ohne Geschenke button");
		//scrolling the register button into view so that it is not hidden behind the confirmation for cookie message
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,120)");
		loginPage.buttonRegister().click();
		log.info("Clicked on the Register Button");
		//Creating a random email id to register user
		String emailId = RandomStringUtils.randomAlphabetic(8);
		registerationPage.registrationEmail().sendKeys(emailId+"@testemail.com");
		log.info("For registeration entered email id "+ emailId);
		registerationPage.registrationPassword().sendKeys("123456");
		log.info("For registeration entered password as 123456");
		registerationPage.registrationConfrimPassword().sendKeys("123456");
		log.info("For registeration confirmed password as 123456");
		registerationPage.registrationSalutation().click();
		log.info("For registration selected salutation as Herr");
		registerationPage.registrationFirstName().sendKeys("TestFirst");
		log.info("For registeration entered first name as TestFirst");
		registerationPage.registrationLastName().sendKeys("TestLast");
		log.info("For registeration entered last name as TestLast");
		registerationPage.registrationStreet().sendKeys("Überseering");
		log.info("For registeration entered street name as Überseering");
		registerationPage.registrationStreetNumber().sendKeys("33");
		log.info("For registeration entered street number as 33");
		registerationPage.registrationCity().sendKeys("Hamburg");
		log.info("For registeration entered city as Hamburg");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-180)");
		registerationPage.copyDeliveryAndInvoiceAddress().click();
		log.info("Checking the checkbox so that delivery and invoice address are same");
		addressAndPaymentPage.radioButtonInvoice().click();
		log.info("Selected the payment method as Invoice by clicking radio button Rechnung");
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");
		Assert.assertEquals("Glückwunsch! Gute Wahl getroffen", generalPage.textOrderConfirmation().getText());
		log.info("Order is placed successfully");
		
		log.info("*** Finished Test: newCustomerInvoiceMethodTest");
	}

	@Test
	public void newCustomerRatePayMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: newCustomerRatePayMethodTest");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);

		ensurePageLoaded();

		generalPage.clickCloseCookieMessage(false);
		homePage.linkProducts().click();
		log.info("Clicked on the Produkte link");
		homePage.linkPlants().click();
		log.info("Clicked on the Pflanzen link");
		generalPage.linkFirstItem().click();
		log.info("Selecting the first item on Pflanzen page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		Thread.sleep(1000);
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("49536");
		log.info("Entered the delivery postal code 49536");
		deliveryPage.buttonNextPostalCode().click();
		log.info("Clicked the next button after entering Postal Code");
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grußkarte button");
		Thread.sleep(1000);
		deliveryPage.continueWithoutGreetingCard().click();
		log.info("Clicked on Weiter ohne Grußkarte button");
		Thread.sleep(1000);
		deliveryPage.continueWithoutGifts().click();
		log.info("Clicked on Weiter ohne Geschenke button");
		//scrolling the register button into view so that it is not hidden behind the confirmation for cookie message
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,120)");
		loginPage.buttonRegister().click();
		log.info("Clicked on the Register Button");
		//Creating a random email id to register user
		String emailId = RandomStringUtils.randomAlphabetic(8);
		registerationPage.registrationEmail().sendKeys(emailId+"@testemail.com");
		log.info("For registeration entered email id "+ emailId);
		registerationPage.registrationPassword().sendKeys("123456");
		log.info("For registeration entered password as 123456");
		registerationPage.registrationConfrimPassword().sendKeys("123456");
		log.info("For registeration confirmed password as 123456");
		registerationPage.registrationSalutation().click();
		log.info("For registration selected salutation as Herr");
		registerationPage.registrationFirstName().sendKeys("Han");
		log.info("For registeration entered first name as Han");
		registerationPage.registrationLastName().sendKeys("Solo");
		log.info("For registeration entered last name as Solo");
		registerationPage.registrationStreet().sendKeys("Sienebrink");
		log.info("For registeration entered street name as Sienebrink");
		registerationPage.registrationStreetNumber().sendKeys("7");
		log.info("For registeration entered street number as 7");
		registerationPage.registrationCity().sendKeys("Lienen");
		log.info("For registeration entered city as Lienen");
		registerationPage.copyDeliveryAndInvoiceAddress().click();
		log.info("Checking the checkbox so that delivery and invoice address are same");
		addressAndPaymentPage.radioButtonRatepay().click();
		log.info("Selected the payment method as Ratepay by clicking radio button Ratepay Rechnung");
		addressAndPaymentPage.dropdownBirthDate().sendKeys(addressAndPaymentPage.ratepayBirthDate());
		addressAndPaymentPage.dropdownBirthMonth().sendKeys(addressAndPaymentPage.ratepayBirthMonth());
		addressAndPaymentPage.dropdownBirthYear().sendKeys(addressAndPaymentPage.ratepayBirthYear());
		addressAndPaymentPage.textFieldRatepayTelephoneNumber().sendKeys(addressAndPaymentPage.ratepayTelephoneNumber());
		Thread.sleep(1000);
		addressAndPaymentPage.checkBoxInvoiceConsentDeclaration().click();
		log.info("Checked the Consent Declaration checkbox");
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");
		Assert.assertEquals("Glückwunsch! Gute Wahl getroffen", generalPage.textOrderConfirmation().getText());
		log.info("Order is placed successfully");

		log.info("*** Finished Test: newCustomerRatePayMethodTest");
	}

	@AfterMethod
	public void logout()
	{
		GeneralPage generalPage = new GeneralPage(driver);
		//Logging out if the user is signed in before starting the next test
		try {
			if (generalPage.linkMyAccount().isDisplayed())
			{
				generalPage.linkMyAccount().click();
				log.info("Clicked on the Mein Konto link for logging out");
				generalPage.linkLogout().click();
				log.info("Clicked on the Abmelden link for logging out");
			}
		} catch(NoSuchElementException ex) {
			log.info("No need for logging out.");
		}
		driver.quit();
	}
	
	@AfterTest
	public void closeBrowser()
	{
		//releasing the memory
		driver = null;
		log.info("---------------Cleaned Up The Setup--------------");
	}

}

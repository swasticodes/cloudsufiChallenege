package Blume2000;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;
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
import pageObjects.GiftsPage;
import pageObjects.GiftsSetsPage;
import pageObjects.GreetingCardPage;
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
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		PayPalPage payPalPage = new PayPalPage(driver);
		GreetingCardPage greetingCardPage = new GreetingCardPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentMethodPage = new AddressAndPaymentMethodPage(driver);

		ensurePageLoaded();
		generalPage.clickCloseCookieMessage(false);

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		homePage.linkEvents().click();
		log.info("Clicked on the Anlässe link");
		homePage.linkBirthday().click();
		log.info("Clicked on the Geburtstag link");
		generalPage.linkFirstItem().click();
		log.info("Selecting the first item on the birtday page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		Thread.sleep(1000);
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("22297");
		log.info("Entered the delivery postal code 22297");
		if(!browserName.equalsIgnoreCase("mobile")) {
			deliveryPage.buttonNextPostalCode().click();
			log.info("Clicked the next button after entering Postal Code");
		}
		Thread.sleep(2000);
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grußkarte button");
		Thread.sleep(1000);
		greetingCardPage.processStepToOrder().click();
		log.info("Clicked on 'Bestellen' in the Order Progress bar");
		if(browserName.equalsIgnoreCase("mobile")) {
			loginPage.buttonMobileRegister().click();
		}else {
			loginPage.buttonRegister().click();
		}
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
		registerationPage.copyDeliveryAndInvoiceAddress().click();
		log.info("Checking the checkbox so that delivery and invoice address are same");
		//Saving window handle name so that it can be used in the last step
		String parentHandle = driver.getWindowHandle();
		addressAndPaymentMethodPage.radioButtonPayPal().click();
		log.info("Clicking PayPal radio button");
		registerationPage.continueToOverview().click();
		log.info("Clicked on 'Weiter zur Uebersicht' button");
		//Scrolling the 'Direkt zu Paypal' button into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderOverviewPage.textFieldShippingCost());
		Thread.sleep(500);
		orderOverviewPage.buttonDirectToPaypal().click();
		log.info("Clcked on the button 'Direkt zur PayPal'");
		payPalPage.PayPalLogin();
		if(browserName.equals("safari")) {
			Thread.sleep(20000);
			driver.switchTo().frame(1);
			payPalPage.PayPalLogin();
			Thread.sleep(6000);
		}
		driver.switchTo().defaultContent();
		payPalPage.buttonPaypalPaymentConfirmation().click();
		log.info("Clicked on the 'Jetzt bezahlen' button for payment confirmation");
		driver.switchTo().defaultContent();
		if(browserName.equals("safari")) {
			Thread.sleep(12000);
		}
		//switching to the main window for verification
		driver.switchTo().window(parentHandle);
		Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
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

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		homePage.linkEvents().click();
		log.info("Clicked on the Anlässe link");
		homePage.linkThankYou().click();
		log.info("Clicked on the Dankeschön link");
		generalPage.linkFirstItem().click();
		log.info("Selecting the first item on Dankeschön page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		Thread.sleep(1000);
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("22297");
		log.info("Entered the delivery postal code 22297");
		if(!browserName.equalsIgnoreCase("mobile")) {
			deliveryPage.buttonNextPostalCode().click();
			log.info("Clicked the next button after entering Postal Code");
		}
		Thread.sleep(1000);
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
		if(browserName.equalsIgnoreCase("mobile")) {
			loginPage.buttonMobileRegister().click();
		}else {
			loginPage.buttonRegister().click();
		}
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
		Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
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

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		homePage.linkProducts().click();
		log.info("Clicked on the Produkte link");
		homePage.linkBouquets().click();
		log.info("Clicked on the Blumensträuße link");
		generalPage.linkFirstItem().click();
		log.info("Selecting the first item on Blumensträuße page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		Thread.sleep(1000);
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("22297");
		log.info("Entered the delivery postal code 22297");
		if(!browserName.equalsIgnoreCase("mobile")) {
			deliveryPage.buttonNextPostalCode().click();
			log.info("Clicked the next button after entering Postal Code");
		}
		Thread.sleep(1000);
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
		if(browserName.equalsIgnoreCase("mobile")) {
			loginPage.buttonMobileRegister().click();
		}else {
			loginPage.buttonRegister().click();
		}
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
		if(browserName.equals("safari")) {
			Select month = new Select(addressAndPaymentPage.dropDownCreditCardExpiryYear());
			month.selectByIndex(4);
		}else {
			addressAndPaymentPage.dropDownCreditCardExpiryYear().sendKeys(addressAndPaymentPage.creditCardExpiryYear());
		}
		log.info("Entered Credit Card Expiry Year as "+addressAndPaymentPage.creditCardExpiryYear());
		if(browserName.equalsIgnoreCase("mobile")) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addressAndPaymentPage.creditCardContinueButton());
			Thread.sleep(500);
		}
		addressAndPaymentPage.creditCardContinueButton().click();
		log.info("Clicked on the Weiter button on the Credit Card page");
		driver.switchTo().defaultContent();
		if(browserName.equals("safari")) {
			Thread.sleep(5000);
		}
		Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
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

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		homePage.linkTopseller().click();
		log.info("Clicked on the Topseller link");
		generalPage.linkFirstItem().click();
		log.info("Selecting the first item on Blumensträuße page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		Thread.sleep(1000);
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("22297");
		log.info("Entered the delivery postal code 22297");
		if(!browserName.equalsIgnoreCase("mobile")) {
			deliveryPage.buttonNextPostalCode().click();
			log.info("Clicked the next button after entering Postal Code");
		}
		Thread.sleep(1000);
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
		if(browserName.equalsIgnoreCase("mobile")) {
			loginPage.buttonMobileRegister().click();
		}else {
			loginPage.buttonRegister().click();
		}
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
		Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		log.info("Order is placed successfully");
		
		log.info("*** Finished Test: newCustomerInvoiceMethodTest");
	}

	@Test(enabled=false)
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

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
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
		if(!browserName.equalsIgnoreCase("mobile")) {
			deliveryPage.buttonNextPostalCode().click();
			log.info("Clicked the next button after entering Postal Code");
		}
		Thread.sleep(1000);
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
		if(browserName.equalsIgnoreCase("mobile")) {
			loginPage.buttonMobileRegister().click();
		}else {
			loginPage.buttonRegister().click();
		}
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
		log.info("Entered the Birth date");
		if(browserName.equals("safari")) {
			Select month = new Select(addressAndPaymentPage.dropdownBirthMonth());
			month.selectByIndex(4);
		}else {
			addressAndPaymentPage.dropdownBirthMonth().sendKeys(addressAndPaymentPage.ratepayBirthMonth());
		}
		log.info("Entered the Birth month");
		addressAndPaymentPage.dropdownBirthYear().sendKeys(addressAndPaymentPage.ratepayBirthYear());
		log.info("Entered the Birth Year");
		addressAndPaymentPage.textFieldRatepayTelephoneNumber().sendKeys(addressAndPaymentPage.ratepayTelephoneNumber());
		log.info("Entered the Telephone number");
		Thread.sleep(1000);
		addressAndPaymentPage.checkBoxInvoiceConsentDeclaration().click();
		log.info("Checked the Consent Declaration checkbox");
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");
		if(browserName.equalsIgnoreCase("mobile")) {
			Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		}else {
		Assert.assertEquals("Glückwunsch! Gute Wahl getroffen", generalPage.textOrderConfirmation().getText());
		}
		log.info("Order is placed successfully");

		log.info("*** Finished Test: newCustomerRatePayMethodTest");
	}

	@Test
	public void newCustomerSEPADirectDebitMethodTestTopSellerProduct() throws InterruptedException
	{
		log.info("*** Starting Test: newCustomerSEPADirectDebitMethodTestTopSellerProduct");

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

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		homePage.linkTopseller().click();
		log.info("Clicked on the Topseller link");
		generalPage.linkSecondItem().click();
		log.info("Selecting the Second item on Topseller page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		Thread.sleep(1000);
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("22297");
		log.info("Entered the delivery postal code 22297");
		if(!browserName.equalsIgnoreCase("mobile")) {
			deliveryPage.buttonNextPostalCode().click();
			log.info("Clicked the next button after entering Postal Code");
		}
		Thread.sleep(1000);
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
		if(browserName.equalsIgnoreCase("mobile")) {
			loginPage.buttonMobileRegister().click();
		}else {
			loginPage.buttonRegister().click();
		}
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
		registerationPage.copyDeliveryAndInvoiceAddress().click();
		log.info("Checking the checkbox so that delivery and invoice address are same");
		addressAndPaymentPage.radioButtonDebitCard().click();
		log.info("Selected the Bankeinzug/Lastschrift (Debit Card)radio button");
		addressAndPaymentPage.textFieldAccountHolder().sendKeys(addressAndPaymentPage.accountHolderName());
		log.info("Entered the account holder name as "+ addressAndPaymentPage.accountHolderName());
		addressAndPaymentPage.textFieldIBANNumber().sendKeys(addressAndPaymentPage.ibanNumber());
		log.info("Entered the IBAN number as "+ addressAndPaymentPage.ibanNumber());
		addressAndPaymentPage.chkBoxSEPADirectDebit().click();
		log.info("Checked the Direct debit checkbox");
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");
		Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		log.info("Order is placed successfully");

		log.info("*** Finished Test: newCustomerSEPADirectDebitMethodTestTopSellerProduct");
	}

	@Test(enabled=false)
	public void newCustomerInvoiceMethodTestWithGiftCard() throws InterruptedException
	{
		log.info("*** Starting Test: newCustomerInvoiceMethodTestWithGiftCard");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingsCardPage = new GreetingCardPage(driver);
		GiftsPage giftsPage = new GiftsPage(driver);

		ensurePageLoaded();
		generalPage.clickCloseCookieMessage(false);

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		homePage.linkEvents().click();
		log.info("Clicked on the Anlässe link");
		homePage.linkBirthday().click();
		log.info("Clicked on the Geburtstag link");
		generalPage.linkSecondItem().click();
		log.info("Selecting the Second item on Geburtstag page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		Thread.sleep(1000);
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("22297");
		log.info("Entered the delivery postal code 22297");
		if(!browserName.equalsIgnoreCase("mobile")) {
			deliveryPage.buttonNextPostalCode().click();
			log.info("Clicked the next button after entering Postal Code");
		}
		Thread.sleep(1000);
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		Thread.sleep(1000);
		generalPage.radioButtonPremiumDelivery().click();
		log.info("Selected Premium Delivery radio button");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grußkarte button");
		Thread.sleep(1000);
		greetingsCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingsCardPage.mobileButtonContinueWithoutGreetingCardText().click();
		}else {
			greetingsCardPage.buttonContinueToGifts().click();
		}
		log.info("Clicked on the button Weiter zu Geschenke");
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first gift item");
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicke on the button Direkt zur Kasse");
		if(browserName.equalsIgnoreCase("mobile")) {
			loginPage.buttonMobileRegister().click();
		}else {
			loginPage.buttonRegister().click();
		}
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
		registerationPage.copyDeliveryAndInvoiceAddress().click();
		log.info("Checking the checkbox so that delivery and invoice address are same");
		addressAndPaymentPage.chkBoxUseGiftCard().click();
		log.info("Checked the checkbox Geschenkkarte einlösen for using a gift card");
		addressAndPaymentPage.textFieldGiftCardNumber().sendKeys(addressAndPaymentPage.giftCardNumber());
		log.info("Entered the gift card number");
		addressAndPaymentPage.textFieldGiftCardPin().sendKeys(addressAndPaymentPage.giftCardpin());
		log.info("Entered the gift card pin");
		addressAndPaymentPage.buttonRedeemGiftCard().click();
		log.info("Clicked button Geschenkkarte einlösen for redeeming gift card");
		Assert.assertTrue(addressAndPaymentPage.textBoxGiftCardSuccessMessage().isDisplayed(),"The Gift Card Was not applied successfully. Check for balance of the gift card.");
		log.info("Verified Gift Card applied success message");
		if(addressAndPaymentPage.textBoxGiftCardPayableAmount().getText()!="0,00 €") {
			addressAndPaymentPage.radioButtonInvoice().click();
			log.info("Selected the payment method as Invoice by clicking radio button Rechnung");
		}
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");
		if(browserName.equalsIgnoreCase("mobile")) {
			Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		}else {
		Assert.assertEquals("Glückwunsch! Gute Wahl getroffen", generalPage.textOrderConfirmation().getText());
		}
		log.info("Order is placed successfully");

		log.info("*** Finished Test: newCustomerInvoiceMethodTestWithGiftCard");
	}

	@Test(enabled=false)
	public void newCustomerPaypalPaymentMethodTestWithGiftCard() throws InterruptedException, IOException
	{
		log.info("*** Starting Test: newCustomerPaypalPaymentMethodTestWithGiftCard");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		PayPalPage payPalPage = new PayPalPage(driver);
		GreetingCardPage greetingsCardPage = new GreetingCardPage(driver);
		GiftsPage giftsPage = new GiftsPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);

		ensurePageLoaded();
		generalPage.clickCloseCookieMessage(false);

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		homePage.linkProducts().click();
		log.info("Clicked on the Produkte link");
		homePage.linkBouquets().click();
		log.info("Clicked on the Blumensträuße link");
		generalPage.linkSecondItem().click();
		log.info("Selecting the Second item on Blumensträuße page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		Thread.sleep(1000);
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("22297");
		log.info("Entered the delivery postal code 22297");
		if(!browserName.equalsIgnoreCase("mobile")) {
			deliveryPage.buttonNextPostalCode().click();
			log.info("Clicked the next button after entering Postal Code");
		}
		Thread.sleep(1000);
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		Thread.sleep(1000);
		generalPage.radioButtonPremiumDelivery().click();
		log.info("Selected Premium Delivery radio button");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grußkarte button");
		Thread.sleep(1000);
		greetingsCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingsCardPage.mobileButtonContinueWithoutGreetingCardText().click();
		}else {
			greetingsCardPage.buttonContinueToGifts().click();
		}
		log.info("Clicked on the button Weiter zu Geschenke");
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first gift item");
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicke on the button Direkt zur Kasse");
		if(browserName.equalsIgnoreCase("mobile")) {
			loginPage.buttonMobileRegister().click();
		}else {
			loginPage.buttonRegister().click();
		}
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
		Thread.sleep(1000);
		addressAndPaymentPage.chkBoxUseGiftCard().click();
		log.info("Checked the checkbox Geschenkkarte einlösen for using a gift card");
		addressAndPaymentPage.textFieldGiftCardNumber().sendKeys(addressAndPaymentPage.giftCardNumber());
		log.info("Entered the gift card number");
		addressAndPaymentPage.textFieldGiftCardPin().sendKeys(addressAndPaymentPage.giftCardpin());
		log.info("Entered the gift card pin");
		addressAndPaymentPage.buttonRedeemGiftCard().click();
		log.info("Clicked button Geschenkkarte einlösen for redeeming gift card");
		Assert.assertTrue(addressAndPaymentPage.textBoxGiftCardSuccessMessage().isDisplayed(),"The Gift Card Was not applied successfully. Check for balance of the gift card.");
		log.info("Verified Gift Card applied success message");
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");
		//Only Go inside when after applying gift card some balance is payable
		if(payPalPage.textFieldEmail().isDisplayed()) {
			payPalPage.PayPalLogin();
			driver.switchTo().defaultContent();
			payPalPage.buttonPaypalPaymentConfirmation().click();
			driver.switchTo().defaultContent();
			log.info("Clicked on the Jetzt bezhalen for payment confirmation");
		}
		if(browserName.equalsIgnoreCase("mobile")) {
			Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		}else {
		Assert.assertEquals("Glückwunsch! Gute Wahl getroffen", generalPage.textOrderConfirmation().getText());
		}
		log.info("Order is placed successfully");

		log.info("*** Finished Test: newCustomerPaypalPaymentMethodTestWithGiftCard");
	}

	@Test(enabled=false)
	public void newCustomerSEPADirectDebitMethodTestWithGiftCard() throws InterruptedException
	{
		log.info("*** Starting Test: newCustomerSEPADirectDebitMethodTestWithGiftCard");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingsCardPage = new GreetingCardPage(driver);
		GiftsPage giftsPage = new GiftsPage(driver);

		ensurePageLoaded();
		generalPage.clickCloseCookieMessage(false);

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		homePage.linkProducts().click();
		log.info("Clicked on the Produkte link");
		homePage.linkPlants().click();
		log.info("Clicked on the Pflanzen link");
		generalPage.linkSecondItem().click();
		log.info("Selecting the second item on Pflanzen page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		Thread.sleep(1000);
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("22297");
		log.info("Entered the delivery postal code 22297");
		if(!browserName.equalsIgnoreCase("mobile")) {
			deliveryPage.buttonNextPostalCode().click();
			log.info("Clicked the next button after entering Postal Code");
		}
		Thread.sleep(1000);
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		Thread.sleep(1000);
		generalPage.radioButtonPremiumDelivery().click();
		log.info("Selected Premium Delivery radio button");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grußkarte button");
		Thread.sleep(1000);
		greetingsCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingsCardPage.mobileButtonContinueWithoutGreetingCardText().click();
		}else {
			greetingsCardPage.buttonContinueToGifts().click();
		}
		log.info("Clicked on the button Weiter zu Geschenke");
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first gift item");
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicked on the button Direkt zur Kasse");
		if(browserName.equalsIgnoreCase("mobile")) {
			loginPage.buttonMobileRegister().click();
		}else {
			loginPage.buttonRegister().click();
		}
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
		Thread.sleep(1000);
		addressAndPaymentPage.chkBoxUseGiftCard().click();
		log.info("Checked the checkbox Geschenkkarte einlösen for using a gift card");
		addressAndPaymentPage.textFieldGiftCardNumber().sendKeys(addressAndPaymentPage.giftCardNumber());
		log.info("Entered the gift card number");
		addressAndPaymentPage.textFieldGiftCardPin().sendKeys(addressAndPaymentPage.giftCardpin());
		log.info("Entered the gift card pin");
		addressAndPaymentPage.buttonRedeemGiftCard().click();
		log.info("Clicked button Geschenkkarte einlösen for redeeming gift card");
		Assert.assertTrue(addressAndPaymentPage.textBoxGiftCardSuccessMessage().isDisplayed(),"The Gift Card Was not applied successfully. Check for balance of the gift card.");
		log.info("Verified Gift Card applied success message");
		if(addressAndPaymentPage.textBoxGiftCardPayableAmount().getText()!="0,00 €") {
			//scrolling the Debit Card checkbox into view
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerationPage.copyDeliveryAndInvoiceAddress());
			Thread.sleep(500);
			addressAndPaymentPage.radioButtonDebitCard().click();
			log.info("Selected the Bankeinzug/Lastschrift (Debit Card)radio button");
			addressAndPaymentPage.textFieldAccountHolder().sendKeys(addressAndPaymentPage.accountHolderName());
			log.info("Entered the account holder name as "+ addressAndPaymentPage.accountHolderName());
			addressAndPaymentPage.textFieldIBANNumber().sendKeys(addressAndPaymentPage.ibanNumber());
			log.info("Entered the INAN number as "+ addressAndPaymentPage.ibanNumber());
			addressAndPaymentPage.chkBoxSEPADirectDebit().click();
			log.info("Checked the Direct debit checkbox");
		}
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");
		Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		log.info("Order is placed successfully");

		log.info("*** Finished Test: newCustomerSEPADirectDebitMethodTestWithGiftCard");
	}

	@Test(enabled=false)
	public void newCustomerCreditCardMethodTestWithGiftCard() throws InterruptedException
	{
		log.info("*** Starting Test: newCustomerCreditCardMethodTestWithGiftCard");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingsCardPage = new GreetingCardPage(driver);
		GiftsPage giftsPage = new GiftsPage(driver);
		GiftsSetsPage giftsSetsPage = new GiftsSetsPage(driver);

		ensurePageLoaded();
		generalPage.clickCloseCookieMessage(false);

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		homePage.linkProducts().click();
		log.info("Clicked on the Produkte link");
		homePage.linkGiftSets().click();
		log.info("Clicked on the link Geschenksets");
		giftsSetsPage.linkGiftsSetsSecondItem().click();
		log.info("Selecting the Second item on Geschenksets page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		Thread.sleep(1000);
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("22297");
		log.info("Entered the delivery postal code 22297");
		if(!browserName.equalsIgnoreCase("mobile")) {
			deliveryPage.buttonNextPostalCode().click();
			log.info("Clicked the next button after entering Postal Code");
		}
		Thread.sleep(1000);
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		Thread.sleep(1000);
		generalPage.radioButtonPremiumDelivery().click();
		log.info("Selected Premium Delivery radio button");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grußkarte button");
		Thread.sleep(1000);
		greetingsCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingsCardPage.mobileButtonContinueWithoutGreetingCardText().click();
		}else {
			greetingsCardPage.buttonContinueToGifts().click();
		}
		log.info("Clicked on the button Weiter zu Geschenke");
		Thread.sleep(1000);
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first gift item");
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicked on the button Direkt zur Kasse");
		if(browserName.equalsIgnoreCase("mobile")) {
			loginPage.buttonMobileRegister().click();
		}else {
			loginPage.buttonRegister().click();
		}
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
		registerationPage.copyDeliveryAndInvoiceAddress().click();
		log.info("Checking the checkbox so that delivery and invoice address are same");
		Thread.sleep(1000);
		addressAndPaymentPage.chkBoxUseGiftCard().click();
		log.info("Checked the checkbox Geschenkkarte einlösen for using a gift card");
		addressAndPaymentPage.textFieldGiftCardNumber().sendKeys(addressAndPaymentPage.giftCardNumber());
		log.info("Entered the gift card number");
		addressAndPaymentPage.textFieldGiftCardPin().sendKeys(addressAndPaymentPage.giftCardpin());
		log.info("Entered the gift card pin");
		addressAndPaymentPage.buttonRedeemGiftCard().click();
		log.info("Clicked button Geschenkkarte einlösen for redeeming gift card");
		Assert.assertTrue(addressAndPaymentPage.textBoxGiftCardSuccessMessage().isDisplayed(),"The Gift Card Was not applied successfully. Check for balance of the gift card.");
		log.info("Verified Gift Card applied success message");
		if(addressAndPaymentPage.textBoxGiftCardPayableAmount().getText()!="0,00 €") {
			//scrolling payment methods into view
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerationPage.copyDeliveryAndInvoiceAddress());
			Thread.sleep(500);
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
			if(browserName.equals("safari")) {
				Select month = new Select(addressAndPaymentPage.dropDownCreditCardExpiryYear());
				month.selectByIndex(4);
			}else {
				addressAndPaymentPage.dropDownCreditCardExpiryYear().sendKeys(addressAndPaymentPage.creditCardExpiryYear());
			}
			log.info("Entered Credit Card Expiry Year as "+addressAndPaymentPage.creditCardExpiryYear());
			if(browserName.equalsIgnoreCase("mobile")) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addressAndPaymentPage.creditCardContinueButton());
				Thread.sleep(500);
			}
			addressAndPaymentPage.creditCardContinueButton().click();
			log.info("Clicked on the Weiter button on the Credit Card page");
			driver.switchTo().defaultContent();
		}else {
			registerationPage.continueToOverview().click();
			log.info("Clicked on Weiter zur Übersicht button");
			orderOverviewPage.buttonToBuy().click();
			log.info("Clicked on Kaufen button");
		}
		if(browserName.equals("safari")) {
			Thread.sleep(5000);
		}
		Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		log.info("Order is placed successfully");

		log.info("*** Finished Test: newCustomerCreditCardMethodTestWithGiftCard");
	}

	@Test(enabled=false)
	public void newCustomerRatePayMethodTestWithGiftCard() throws InterruptedException
	{
		log.info("*** Starting Test: newCustomerRatePayMethodTestWithGiftCard");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingsCardPage = new GreetingCardPage(driver);

		ensurePageLoaded();
		generalPage.clickCloseCookieMessage(false);

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		homePage.linkProducts().click();
		log.info("Clicked on the Produkte link");
		homePage.linkPiesAndGifts().click();
		log.info("Clicked on the link Torten & Geschenke");
		generalPage.linkSecondItem().click();
		log.info("Selecting the Second item on Torten & Geschenke page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		Thread.sleep(1000);
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("22297");
		log.info("Entered the delivery postal code 22297");
		if(!browserName.equalsIgnoreCase("mobile")) {
			deliveryPage.buttonNextPostalCode().click();
			log.info("Clicked the next button after entering Postal Code");
		}
		Thread.sleep(1000);
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grußkarte button");
		Thread.sleep(1000);
		greetingsCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingsCardPage.mobileButtonContinueWithoutGreetingCardText().click();
		}else {
			greetingsCardPage.buttonContinueToGifts().click();
		}
		log.info("Clicked on the button Direkt zur Kasse");
		if(browserName.equalsIgnoreCase("mobile")) {
			loginPage.buttonMobileRegister().click();
		}else {
			loginPage.buttonRegister().click();
		}
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
		Thread.sleep(1000);
		addressAndPaymentPage.chkBoxUseGiftCard().click();
		log.info("Checked the checkbox Geschenkkarte einlösen for using a gift card");
		addressAndPaymentPage.textFieldGiftCardNumber().sendKeys(addressAndPaymentPage.giftCardNumber());
		log.info("Entered the gift card number");
		addressAndPaymentPage.textFieldGiftCardPin().sendKeys(addressAndPaymentPage.giftCardpin());
		log.info("Entered the gift card pin");
		addressAndPaymentPage.buttonRedeemGiftCard().click();
		log.info("Clicked button Geschenkkarte einlösen for redeeming gift card");
		Assert.assertTrue(addressAndPaymentPage.textBoxGiftCardSuccessMessage().isDisplayed(),"The Gift Card Was not applied successfully. Check for balance of the gift card.");
		log.info("Verified Gift Card applied success message");
		if(addressAndPaymentPage.textBoxGiftCardPayableAmount().getText()!="0,00 €") {
			//scrolling payment methods into view
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerationPage.copyDeliveryAndInvoiceAddress());
			addressAndPaymentPage.radioButtonRatepay().click();
			log.info("Selected the payment method as Ratepay by clicking radio button Ratepay Rechnung");
			addressAndPaymentPage.dropdownBirthDate().sendKeys(addressAndPaymentPage.ratepayBirthDate());
			log.info("Entered the birth date for Ratepay");
			if(browserName.equals("safari")) {
				Select month = new Select(addressAndPaymentPage.dropdownBirthMonth());
				month.selectByIndex(4);
			}else {
				addressAndPaymentPage.dropdownBirthMonth().sendKeys(addressAndPaymentPage.ratepayBirthMonth());
			}
			log.info("Entered the birth month for Ratepay");
			addressAndPaymentPage.dropdownBirthYear().sendKeys(addressAndPaymentPage.ratepayBirthYear());
			log.info("Entered the birth year for Ratepay");
			addressAndPaymentPage.textFieldRatepayTelephoneNumber().sendKeys(addressAndPaymentPage.ratepayTelephoneNumber());
			log.info("Entered the telephone number for Ratepay");
			Thread.sleep(1000);
			addressAndPaymentPage.checkBoxInvoiceConsentDeclaration().click();
			log.info("Checked the Consent Declaration checkbox");
		}
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");
		if(browserName.equalsIgnoreCase("mobile")) {
			Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		}else {
		Assert.assertEquals("Glückwunsch! Gute Wahl getroffen", generalPage.textOrderConfirmation().getText());
		}
		log.info("Order is placed successfully");

		log.info("*** Finished Test: newCustomerRatePayMethodTestWithGiftCard");
	}

	@Test(enabled=false)
	public void newCustomerCreditCardMethodTestWithGiftCardTopseller() throws InterruptedException
	{
		log.info("*** Starting Test: newCustomerCreditCardMethodTestWithGiftCardTopseller");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingsCardPage = new GreetingCardPage(driver);
		GiftsPage giftsPage = new GiftsPage(driver);

		ensurePageLoaded();
		generalPage.clickCloseCookieMessage(false);

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		homePage.linkTopseller().click();
		log.info("Clicked on the Topseller link");
		generalPage.linkSecondItem().click();
		log.info("Selecting the Second item on Topseller page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		Thread.sleep(1000);
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("22297");
		log.info("Entered the delivery postal code 22297");
		if(!browserName.equalsIgnoreCase("mobile")) {
			deliveryPage.buttonNextPostalCode().click();
			log.info("Clicked the next button after entering Postal Code");
		}
		Thread.sleep(1000);
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		Thread.sleep(1000);
		generalPage.radioButtonPremiumDelivery().click();
		log.info("Selected Premium Delivery radio button");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grußkarte button");
		Thread.sleep(1000);
		greetingsCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingsCardPage.mobileButtonContinueWithoutGreetingCardText().click();
		}else {
			greetingsCardPage.buttonContinueToGifts().click();
		}
		log.info("Clicked on the button Weiter zu Geschenke");
		Thread.sleep(1000);
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first gift item");
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicked on the button Direkt zur Kasse");
		if(browserName.equalsIgnoreCase("mobile")) {
			loginPage.buttonMobileRegister().click();
		}else {
			loginPage.buttonRegister().click();
		}
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
		registerationPage.copyDeliveryAndInvoiceAddress().click();
		log.info("Checking the checkbox so that delivery and invoice address are same");
		Thread.sleep(1000);
		addressAndPaymentPage.chkBoxUseGiftCard().click();
		log.info("Checked the checkbox Geschenkkarte einlösen for using a gift card");
		addressAndPaymentPage.textFieldGiftCardNumber().sendKeys(addressAndPaymentPage.giftCardNumber());
		log.info("Entered the gift card number");
		addressAndPaymentPage.textFieldGiftCardPin().sendKeys(addressAndPaymentPage.giftCardpin());
		log.info("Entered the gift card pin");
		addressAndPaymentPage.buttonRedeemGiftCard().click();
		log.info("Clicked button Geschenkkarte einlösen for redeeming gift card");
		Assert.assertTrue(addressAndPaymentPage.textBoxGiftCardSuccessMessage().isDisplayed(),"The Gift Card Was not applied successfully. Check for balance of the gift card.");
		log.info("Verified Gift Card applied success message");
		if(addressAndPaymentPage.textBoxGiftCardPayableAmount().getText()!="0,00 €") {
			//scrolling payment methods into view
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerationPage.copyDeliveryAndInvoiceAddress());
			Thread.sleep(500);
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
			if(browserName.equals("safari")) {
				Select month = new Select(addressAndPaymentPage.dropDownCreditCardExpiryYear());
				month.selectByIndex(4);
			}else {
				addressAndPaymentPage.dropDownCreditCardExpiryYear().sendKeys(addressAndPaymentPage.creditCardExpiryYear());
			}

			log.info("Entered Credit Card Expiry Year as "+addressAndPaymentPage.creditCardExpiryYear());
			if(browserName.equalsIgnoreCase("mobile")) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addressAndPaymentPage.creditCardContinueButton());
				Thread.sleep(500);
			}
			addressAndPaymentPage.creditCardContinueButton().click();
			log.info("Clicked on the Weiter button on the Credit Card page");
			driver.switchTo().defaultContent();
		}else {
			registerationPage.continueToOverview().click();
			log.info("Clicked on Weiter zur Übersicht button");
			orderOverviewPage.buttonToBuy().click();
			log.info("Clicked on Kaufen button");
		}
		Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		if(browserName.equals("safari")) {
			Thread.sleep(5000);
		}
		log.info("Order is placed successfully");

		log.info("*** Finished Test: newCustomerCreditCardMethodTestWithGiftCardTopseller");
	}


	@AfterMethod
	public void closeBrowser()
	{
		driver.quit();
		log.info("---------------Closed The Browser--------------");
	}

	@AfterTest
	public void releaseBrowserMemory()
	{
		driver = null;
		//releasing the memory
		log.info("---------------Cleaned Up The Setup--------------");
	}
}

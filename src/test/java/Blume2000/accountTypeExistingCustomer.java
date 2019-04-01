package Blume2000;

import java.io.IOException;

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
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.OrderOverviewPage;
import pageObjects.PayPalPage;
import pageObjects.PiesAndGiftsPage;
import pageObjects.RegistrationPage;
import pageObjects.AddressAndPaymentMethodPage;
import pageObjects.DeliveryPage;
import pageObjects.GeneralPage;
import pageObjects.GiftsPage;
import pageObjects.GiftsSetsPage;
import pageObjects.GreetingCardPage;
import resources.BasicVariables;

public class accountTypeExistingCustomer<inherits> extends BasicVariables {
	//Use the line below to enable logging in your test cases
	public static Logger log = LogManager.getLogger(accountTypeExistingCustomer.class.getName());

	@BeforeMethod
	public void openBrowser() throws IOException
	{
		log.info("Driver is initializing");
		driver = initializeDriver();
		log.info("Opening the URL "+(prop.getProperty("URL")));
		driver.get(prop.getProperty("URL"));
	}

	@Test
	public void existingCustomerCreditCardMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: existingCustomerCreditCardMethodTest");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingCardPage = new GreetingCardPage(driver);
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
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grußkarte button");
		greetingCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileButtonEditGreetingText().click();
			log.info("Clicked on Gruß bearbeiten button for enetring text");
		}
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileDropdownSelectGreetingTextTemplate().click();
		}else {
			greetingCardPage.dropdownSelectGreetingTextTemplate().click();
		}
		log.info("Opened the Grußtextvorlage auswählen dropdown");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileGreetingCardTemplateThankYou().click();
		}else {
			greetingCardPage.greetingCardTemplateThankYou().click();
		}
		log.info("Selectd Dankeschön from the dropdown");
		Thread.sleep(1000);
		greetingCardPage.buttonContinueToGifts().click();
		log.info("Clicked on the button Weiter zu Geschenke");
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first Gift Item");
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicked on the button Direkt zur Kasse");
		registerationPage.textFieldEmail().sendKeys(registerationPage.registeredUserEmail());
		log.info("Entered the email id as-> " + registerationPage.registeredUserEmail());
		registerationPage.textFieldPassword().sendKeys(registerationPage.registeredUserPassword());
		log.info("Entered the password as-> <CENSORED>");
		registerationPage.buttonLogin().click();
		log.info("Clicked on the 'Einloggen' button");
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

		log.info("*** Finished Test: existingCustomerCreditCardMethodTest");
	}

	@Test(enabled=true)
	public void existingCustomerInvoiceMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: existingCustomerInvoiceMethodTest");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingCardPage = new GreetingCardPage(driver);
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
		generalPage.linkFirstItem().click();
		log.info("Selecting the First item on Pflanzen page");
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
		greetingCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileButtonEditGreetingText().click();
			log.info("Clicked on Gruß bearbeiten button for enetring text");
		}
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileDropdownSelectGreetingTextTemplate().click();
		}else {
			greetingCardPage.dropdownSelectGreetingTextTemplate().click();
		}
		log.info("Opened the Grußtextvorlage auswählen dropdown");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileGreetingCardTemplateThankYou().click();
		}else {
			greetingCardPage.greetingCardTemplateThankYou().click();
		}
		log.info("Selectd Dankeschön from the dropdown");
		Thread.sleep(1000);
		greetingCardPage.buttonContinueToGifts().click();
		log.info("Clicked on the button Weiter zu Geschenke");
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first Gift Item");
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicked on the button Direkt zur Kasse");
		registerationPage.textFieldEmail().sendKeys(registerationPage.registeredUserEmail());
		log.info("Entered the email id as-> " + registerationPage.registeredUserEmail());
		registerationPage.textFieldPassword().sendKeys(registerationPage.registeredUserPassword());
		log.info("Entered the password as-> <CENSORED>");
		registerationPage.buttonLogin().click();
		log.info("Clicked on the 'Einloggen' button");
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
		addressAndPaymentPage.radioButtonInvoice().click();
		log.info("Selected the payment method as Invoice by clicking radio button Rechnung");
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");
		Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		log.info("Order is placed successfully");

		log.info("*** Finished Test: existingCustomerInvoiceMethodTest");
	}

	@Test(enabled=false)
	public void existingCustomerRatePayMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: existingCustomerRatePayMethodTest");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingCardPage = new GreetingCardPage(driver);
		GiftsPage giftsPage = new GiftsPage(driver);

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
		greetingCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileButtonEditGreetingText().click();
			log.info("Clicked on Gruß bearbeiten button for enetring text");
		}
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileDropdownSelectGreetingTextTemplate().click();
		}else {
			greetingCardPage.dropdownSelectGreetingTextTemplate().click();
		}
		log.info("Opened the Grußtextvorlage auswählen dropdown");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileGreetingCardTemplateLove().click();
		}else {
			greetingCardPage.greetingCardTemplateLove().click();
		}
		log.info("Selectd Liebe from the dropdown");
		Thread.sleep(1000);
		greetingCardPage.buttonContinueToGifts().click();
		log.info("Clicked on the button Weiter zu Geschenke");
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first Gift Item");
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicked on the button Direkt zur Kasse");
		registerationPage.textFieldEmail().sendKeys(registerationPage.registeredUserEmail());
		log.info("Entered the email id as-> " + registerationPage.registeredUserEmail());
		registerationPage.textFieldPassword().sendKeys(registerationPage.registeredUserPassword());
		log.info("Entered the password as-> <CENSORED>");
		registerationPage.buttonLogin().click();
		log.info("Clicked on the 'Einloggen' button");
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
		addressAndPaymentPage.radioButtonRatepay().click();
		log.info("Selected the payment method as Ratepay by clicking radio button Ratepay Rechnung");
		addressAndPaymentPage.dropdownBirthDate().sendKeys(addressAndPaymentPage.ratepayBirthDate());
		log.info("Entered the Birth date");
		addressAndPaymentPage.dropdownBirthMonth().sendKeys(addressAndPaymentPage.ratepayBirthMonth());
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
		Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		log.info("Order is placed successfully");

		log.info("*** Finished Test: existingCustomerRatePayMethodTest");
	}

	@Test
	public void existingCustomerPaypalMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: existingCustomerPaypalMethodTest");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingCardPage = new GreetingCardPage(driver);
		GiftsPage giftsPage = new GiftsPage(driver);
		PayPalPage payPalPage = new PayPalPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentMethodPage = new AddressAndPaymentMethodPage(driver);
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
		log.info("Clicked on the Geschenksets link");
		giftsSetsPage.linkGiftsSetsSecondItem().click();
		log.info("Selecting the second item on Geschenksets page");
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
		greetingCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileButtonEditGreetingText().click();
			log.info("Clicked on Gruß bearbeiten button for enetring text");
		}
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileDropdownSelectGreetingTextTemplate().click();
		}else {
			greetingCardPage.dropdownSelectGreetingTextTemplate().click();
		}
		log.info("Opened the Grußtextvorlage auswählen dropdown");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileGreetingCardTemplateLove().click();
		}else {
			greetingCardPage.greetingCardTemplateLove().click();
		}
		log.info("Selectd Liebe from the dropdown");
		Thread.sleep(1000);
		greetingCardPage.buttonContinueToGifts().click();
		log.info("Clicked on the button 'Weiter zu Geschenke'");
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first Gift Item");
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicked on the button 'Direkt zur Kasse'");
		registerationPage.textFieldEmail().sendKeys(registerationPage.registeredUserEmail());
		log.info("Entered the email id as-> " + registerationPage.registeredUserEmail());
		registerationPage.textFieldPassword().sendKeys(registerationPage.registeredUserPassword());
		log.info("Entered the password as-> <CENSORED>");
		registerationPage.buttonLogin().click();
		log.info("Clicked on the 'Einloggen' button");
		registerationPage.registrationSalutation().click();
		log.info("For registration selected salutation as Herr");
		registerationPage.registrationFirstName().sendKeys("TestFirst");
		log.info("For registeration entered first name as TestFirst");
		registerationPage.registrationLastName().sendKeys("TestLast");
		log.info("For registeration entered last name as TestLast");
		registerationPage.registrationStreet().sendKeys("berseering");
		log.info("For registeration entered street name as berseering");
		registerationPage.registrationStreetNumber().sendKeys("33");
		log.info("For registeration entered street number as 33");
		registerationPage.registrationCity().sendKeys("Hamburg");
		log.info("For registeration entered city as Hamburg");
		addressAndPaymentMethodPage.radioButtonPayPal().click();
		log.info("Clicking PayPal radio button");
		registerationPage.continueToOverview().click();
		log.info("Clicked on 'Weiter zur Uebersicht' button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on 'Kaufen' button");

		payPalPage.PayPalLogin();
		if(browserName.equals("safari")) {
			Thread.sleep(18000);
			driver.switchTo().frame(1);
			payPalPage.PayPalLogin();
			Thread.sleep(6000);
		}

		driver.switchTo().defaultContent();
		payPalPage.buttonPaypalPaymentConfirmation().click();
		driver.switchTo().defaultContent();
		log.info("Clicked on the 'Jetzt bezahlen' button for payment confirmation");
		if(browserName.equals("safari")) {
			Thread.sleep(12000);
		}
		Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		log.info("Order is placed successfully");

		log.info("*** Finished Test: existingCustomerPaypalMethodTest");
	}

	@Test
	public void existingCustomerSEPADirectDebitMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: existingCustomerSEPADirectDebitMethodTest");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingCardsPage = new GreetingCardPage(driver);

		ensurePageLoaded();
		generalPage.clickCloseCookieMessage(false);

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		homePage.linkProducts().click();
		log.info("Clicked on the Produkte link");
		homePage.linkPiesAndGifts().click();
		log.info("Clicked on the Torten & Geschenke link");
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
		log.info("Clicked on Weiter zur Grukarte button");
		Thread.sleep(1000);
		greetingCardsPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardsPage.mobileButtonEditGreetingText().click();
			log.info("Clicked on Gruß bearbeiten button for enetring text");
		}
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardsPage.mobileDropdownSelectGreetingTextTemplate().click();
		}else {
			greetingCardsPage.dropdownSelectGreetingTextTemplate().click();
		}
		log.info("Opened the Grußtextvorlage auswählen dropdown");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardsPage.mobileGreetingCardTemplateThankYou().click();
		}else {
			greetingCardsPage.greetingCardTemplateThankYou().click();
		}
		log.info("Selectd Dankeschön from the dropdown");
		Thread.sleep(1000);
		greetingCardsPage.buttonContinueToGifts().click();
		//The product does not have a gift item that can be selected with it, therefore moving to Kasse
		log.info("Clicked on the button 'Direkt zur Kasse'");
		registerationPage.textFieldEmail().sendKeys(registerationPage.registeredUserEmail());
		log.info("Entered the email id as-> " + registerationPage.registeredUserEmail());
		registerationPage.textFieldPassword().sendKeys(registerationPage.registeredUserPassword());
		log.info("Entered the password as-> <CENSORED>");
		registerationPage.buttonLogin().click();
		log.info("Clicked on the 'Einloggen' button");
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

		log.info("*** Finished Test: existingCustomerSEPADirectDebitMethodTest");
	}

	@Test
	public void existingCustomerPaypalMethodTopsellerTest() throws InterruptedException
	{
		log.info("*** Starting Test: existingCustomerPaypalMethodTopsellerTest");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingCardPage = new GreetingCardPage(driver);
		GiftsPage giftsPage = new GiftsPage(driver);
		PayPalPage payPalPage = new PayPalPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentMethodPage = new AddressAndPaymentMethodPage(driver);

		ensurePageLoaded();
		generalPage.clickCloseCookieMessage(false);

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		homePage.linkTopseller().click();
		log.info("Clicked on the Topseller link");
		generalPage.linkSecondItem().click();
		log.info("Selected the second item on the Topseller page");
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
		greetingCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileButtonEditGreetingText().click();
			log.info("Clicked on Gruß bearbeiten button for enetring text");
		}
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileDropdownSelectGreetingTextTemplate().click();
		}else {
			greetingCardPage.dropdownSelectGreetingTextTemplate().click();
		}
		log.info("Opened the Grußtextvorlage auswählen dropdown");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileGreetingCardTemplateLove().click();
		}else {
			greetingCardPage.greetingCardTemplateLove().click();
		}
		log.info("Selectd Liebe from the dropdown");
		Thread.sleep(1000);
		greetingCardPage.buttonContinueToGifts().click();
		log.info("Clicked on the button 'Weiter zu Geschenke'");
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first Gift Item");
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicked on the button 'Direkt zur Kasse'");
		registerationPage.textFieldEmail().sendKeys(registerationPage.registeredUserEmail());
		log.info("Entered the email id as-> " + registerationPage.registeredUserEmail());
		registerationPage.textFieldPassword().sendKeys(registerationPage.registeredUserPassword());
		log.info("Entered the password as-> <CENSORED>");
		registerationPage.buttonLogin().click();
		log.info("Clicked on the 'Einloggen' button");
		registerationPage.registrationSalutation().click();
		log.info("For registration selected salutation as Herr");
		registerationPage.registrationFirstName().sendKeys("TestFirst");
		log.info("For registeration entered first name as TestFirst");
		registerationPage.registrationLastName().sendKeys("TestLast");
		log.info("For registeration entered last name as TestLast");
		registerationPage.registrationStreet().sendKeys("berseering");
		log.info("For registeration entered street name as berseering");
		registerationPage.registrationStreetNumber().sendKeys("33");
		log.info("For registeration entered street number as 33");
		registerationPage.registrationCity().sendKeys("Hamburg");
		log.info("For registeration entered city as Hamburg");
		addressAndPaymentMethodPage.radioButtonPayPal().click();
		log.info("Clicking PayPal radio button");
		registerationPage.continueToOverview().click();
		log.info("Clicked on 'Weiter zur Uebersicht' button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on 'Kaufen' button");

		payPalPage.PayPalLogin();
		if(browserName.equals("safari")) {
			Thread.sleep(18000);
			driver.switchTo().frame(1);
			payPalPage.PayPalLogin();
			Thread.sleep(6000);
		}

		driver.switchTo().defaultContent();
		payPalPage.buttonPaypalPaymentConfirmation().click();
		driver.switchTo().defaultContent();
		if(browserName.equals("safari")) {
			Thread.sleep(12000);
		}
		log.info("Clicked on the 'Jetzt bezahlen' button for payment confirmation");
		Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		log.info("Order is placed successfully");

		log.info("*** Finished Test: existingCustomerPaypalMethodTopsellerTest");
	}

	@Test
	public void existingCustomerPreLoginPaypalMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: existingCustomerPreLoginPaypalMethodTest");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingCardPage = new GreetingCardPage(driver);
		PayPalPage payPalPage = new PayPalPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentMethodPage = new AddressAndPaymentMethodPage(driver);
		GiftsPage giftsPage = new GiftsPage(driver);

		ensurePageLoaded();
		generalPage.clickCloseCookieMessage(false);

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileLinkLogin().click();
		}
		else {
			homePage.linkLogin().click();
		}
		log.info("Clicked on the Anmelden link for logging in");
		registerationPage.textFieldEmail().sendKeys(registerationPage.registeredUserEmail());
		log.info("Entered the email id as-> " + registerationPage.registeredUserEmail());
		registerationPage.textFieldPassword().sendKeys(registerationPage.registeredUserPassword());
		log.info("Entered the password as-> <CENSORED>");
		registerationPage.buttonLogin().click();
		log.info("Clicked on the 'Einloggen' button");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		Thread.sleep(2000);
		if(browserName.equals("safari")||!generalPage.linkMyAccount().isDisplayed()) {
			Thread.sleep(3000);
		}
		homePage.linkTopseller().click();
		log.info("Clicked on the Topseller link");
		generalPage.linkFirstItem().click();
		log.info("Selected the first item on the Gute Besserung page");
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
		Thread.sleep(2000);
		greetingCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first Greeting Card on the the page");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileButtonEditGreetingText().click();
			log.info("Clicked on Gruß bearbeiten button for enetring text");
		}
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileDropdownSelectGreetingTextTemplate().click();
		}else {
			greetingCardPage.dropdownSelectGreetingTextTemplate().click();
		}
		log.info("Opened the Grußtextvorlage auswählen dropdown");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileGreetingCardTemplateThankYou().click();
		}else {
			greetingCardPage.greetingCardTemplateThankYou().click();
		}
		log.info("Selectd Dankeschön from the dropdown");
		Thread.sleep(1000);
		greetingCardPage.buttonContinueToGifts().click();
		log.info("Clicked on the button 'Weiter zu Geschenke'");
		Thread.sleep(1000);
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first gift item");
		giftsPage.linkGiftsPageSecondItem().click();
		log.info("Selected the Second gift item");
		Thread.sleep(1000);
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicked on the button 'Direkt zur Kasse'");
		Thread.sleep(3000);
		if(browserName.equals("safari")||!registerationPage.registrationSalutation().isDisplayed()) {
			Thread.sleep(3000);
		}
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
		addressAndPaymentMethodPage.checkBox_UseCouponCode().click();
		log.info("Clicked on the 'Gutscheincode einlösen' checkbox");
		addressAndPaymentMethodPage.textField_VoucherCode().sendKeys(addressAndPaymentMethodPage.voucherCode());
		log.info("Entered the Voucher code as -> " + addressAndPaymentMethodPage.voucherCode());
		addressAndPaymentMethodPage.buttonSubmitVoucherCode().click();
		log.info("Clicked on 'Gutscheincode einlösen' button");
		Thread.sleep(2000);
		Assert.assertTrue(addressAndPaymentMethodPage.textBoxVoucherCodeSuccess().isDisplayed(), "The Voucher code was not applied successfully.");
		//Scrolling the paypal radio button into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerationPage.registrationCountry());
		Thread.sleep(500);
		addressAndPaymentMethodPage.radioButtonPayPal().click();
		log.info("Clicking PayPal radio button");
		registerationPage.continueToOverview().click();
		log.info("Clicked on 'Weiter zur Uebersicht' button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on 'Kaufen' button");

		payPalPage.PayPalLogin();
		if(browserName.equals("safari")) {
			Thread.sleep(18000);
			driver.switchTo().frame(1);
			payPalPage.PayPalLogin();
			Thread.sleep(6000);
		}

		driver.switchTo().defaultContent();
		payPalPage.buttonPaypalPaymentConfirmation().click();
		driver.switchTo().defaultContent();
		log.info("Clicked on the 'Jetzt bezahlen' button for payment confirmation");
		if(browserName.equals("safari")) {
			Thread.sleep(12000);
		}
		Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		log.info("Order is placed successfully");

		log.info("*** Finished Test: existingCustomerPreLoginPaypalMethodTest");
	}

	@Test
	public void existingCustomerPreLoginSEPADirectDebitMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: existingCustomerPreLoginSEPADirectDebitMethodTest");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingCardsPage = new GreetingCardPage(driver);

		ensurePageLoaded();
		generalPage.clickCloseCookieMessage(false);

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileLinkLogin().click();
		}
		else {
			homePage.linkLogin().click();
		}
		log.info("Clicked on the Anmelden link for logging in");
		registerationPage.textFieldEmail().sendKeys(registerationPage.registeredUserEmail());
		log.info("Entered the email id as-> " + registerationPage.registeredUserEmail());
		registerationPage.textFieldPassword().sendKeys(registerationPage.registeredUserPassword());
		log.info("Entered the password as-> <CENSORED>");
		registerationPage.buttonLogin().click();
		log.info("Clicked on the 'Einloggen' button");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		homePage.linkEvents().click();
		log.info("Clicked on the Anlässe link");
		homePage.linkGetWell().click();
		log.info("Clicked on the Gute Besserung link");
		generalPage.linkFirstItem().click();
		log.info("Selected the first item on the Gute Besserung page");
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
		greetingCardsPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first Greeting Card on the the page");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardsPage.mobileButtonEditGreetingText().click();
			log.info("Clicked on Gruß bearbeiten button for enetring text");
		}
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardsPage.mobileDropdownSelectGreetingTextTemplate().click();
		}else {
			greetingCardsPage.dropdownSelectGreetingTextTemplate().click();
		}
		log.info("Opened the Grußtextvorlage auswählen dropdown");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardsPage.mobileGreetingCardTemplateThankYou().click();
		}else {
			greetingCardsPage.greetingCardTemplateThankYou().click();
		}
		log.info("Selectd Dankeschön from the dropdown");
		Thread.sleep(1000);
		greetingCardsPage.buttonContinueToGifts().click();
		log.info("Clicked on the button 'Weiter zu Geschenke'");
		deliveryPage.continueWithoutGifts().click();
		log.info("Clicked on Weiter ohne Geschenke button");
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
		addressAndPaymentPage.radioButtonDebitCard().click();
		log.info("Selected the Bankeinzug/Lastschrift (Debit Card)radio button");
		addressAndPaymentPage.textFieldAccountHolder().sendKeys(addressAndPaymentPage.accountHolderName());
		log.info("Entered the account holder name as "+ addressAndPaymentPage.accountHolderName());
		addressAndPaymentPage.textFieldIBANNumber().sendKeys(addressAndPaymentPage.ibanNumber());
		log.info("Entered the IBAN number as "+ addressAndPaymentPage.ibanNumber());
		addressAndPaymentPage.chkBoxSEPADirectDebit().click();
		log.info("Checked the Direct debit checkbox");
		addressAndPaymentPage.checkBox_UseCouponCode().click();
		log.info("Clicked on the 'Gutscheincode einlösen' checkbox");
		addressAndPaymentPage.textField_VoucherCode().sendKeys(addressAndPaymentPage.voucherCode());
		log.info("Entered the Voucher code as -> " + addressAndPaymentPage.voucherCode());
		addressAndPaymentPage.buttonSubmitVoucherCode().click();
		log.info("Clicked on 'Gutscheincode einlösen' button");
		Thread.sleep(2000);
		Assert.assertTrue(addressAndPaymentPage.textBoxVoucherCodeSuccess().isDisplayed(), "The Voucher code was not applied successfully.");
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");
		Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		log.info("Order is placed successfully");

		log.info("*** Finished Test: existingCustomerPreLoginSEPADirectDebitMethodTest");
	}

	@Test(enabled=false)
	public void existingCustomerPreLoginRatePayMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: existingCustomerPreLoginRatePayMethodTest");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingCardsPage = new GreetingCardPage(driver);
		GiftsPage giftsPage = new GiftsPage(driver);

		ensurePageLoaded();
		generalPage.clickCloseCookieMessage(false);

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileLinkLogin().click();
		}
		else {
			homePage.linkLogin().click();
		}
		log.info("Clicked on the Anmelden link for logging in");
		registerationPage.textFieldEmail().sendKeys(registerationPage.registeredUserEmail());
		log.info("Entered the email id as-> " + registerationPage.registeredUserEmail());
		registerationPage.textFieldPassword().sendKeys(registerationPage.registeredUserPassword());
		log.info("Entered the password as-> <CENSORED>");
		registerationPage.buttonLogin().click();
		log.info("Clicked on the 'Einloggen' button");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		homePage.linkTopseller().click();
		log.info("Clicked on the 'Topseller' button");
		generalPage.linkSecondItem().click();
		log.info("Selected the Second item on the Topseller page");
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
		greetingCardsPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardsPage.mobileButtonEditGreetingText().click();
			log.info("Clicked on Gruß bearbeiten button for enetring text");
		}
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardsPage.mobileDropdownSelectGreetingTextTemplate().click();
		}else {
			greetingCardsPage.dropdownSelectGreetingTextTemplate().click();
		}
		log.info("Opened the Grußtextvorlage auswählen dropdown");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardsPage.mobileGreetingCardTemplateLove().click();
		}else {
			greetingCardsPage.greetingCardTemplateLove().click();
		}
		log.info("Selectd Liebe from the dropdown");
		Thread.sleep(1000);
		greetingCardsPage.buttonContinueToGifts().click();
		log.info("Clicked on the button 'Weiter zu Geschenke'");
		Thread.sleep(1000);
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first gift item");
		giftsPage.linkGiftsPageSecondItem().click();
		log.info("Selected the Second gift item");
		Thread.sleep(1000);
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicked on the button 'Direkt zur Kasse'");
		Thread.sleep(1000);
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
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-450)");
			addressAndPaymentPage.radioButtonRatepay().click();
			log.info("Selected the payment method as Ratepay by clicking radio button Ratepay Rechnung");
			Thread.sleep(1000);
			addressAndPaymentPage.checkBoxInvoiceConsentDeclaration().click();
			log.info("Checked the Consent Declaration checkbox");
		}
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");
		Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		log.info("Order is placed successfully");

		log.info("*** Finished Test: existingCustomerPreLoginRatePayMethodTest");
	}

	@Test
	public void existingCustomerPreLoginCreditCardMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: existingCustomerPreLoginCreditCardMethodTest");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingCardPage = new GreetingCardPage(driver);
		GiftsPage giftsPage = new GiftsPage(driver);

		ensurePageLoaded();
		generalPage.clickCloseCookieMessage(false);

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileLinkLogin().click();
		}
		else {
			homePage.linkLogin().click();
		}
		log.info("Clicked on the Anmelden link for logging in");
		registerationPage.textFieldEmail().sendKeys(registerationPage.registeredUserEmail());
		log.info("Entered the email id as-> " + registerationPage.registeredUserEmail());
		registerationPage.textFieldPassword().sendKeys(registerationPage.registeredUserPassword());
		log.info("Entered the password as-> <CENSORED>");
		registerationPage.buttonLogin().click();
		log.info("Clicked on the 'Einloggen' button");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		homePage.linkEvents().click();
		log.info("Clicked on the Anlässe link");
		homePage.linkBestRegards().click();
		log.info("Clicked on the Liebe Grüße link");
		generalPage.linkFirstItem().click();
		log.info("Selecting the first item on Liebe Grüße page");
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
		greetingCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileButtonEditGreetingText().click();
			log.info("Clicked on Gruß bearbeiten button for enetring text");
		}
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileDropdownSelectGreetingTextTemplate().click();
		}else {
			greetingCardPage.dropdownSelectGreetingTextTemplate().click();
		}
		log.info("Opened the Grußtextvorlage auswählen dropdown");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileGreetingCardTemplateLove().click();
		}else {
			greetingCardPage.greetingCardTemplateLove().click();
		}
		log.info("Selectd Liebe from the dropdown");
		Thread.sleep(1000);
		greetingCardPage.buttonContinueToGifts().click();
		log.info("Clicked on the button Weiter zu Geschenke");
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first Gift Item");
		giftsPage.linkGiftsPageSecondItem().click();
		log.info("Selected the second Gift Item");
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicked on the button Direkt zur Kasse");
		Thread.sleep(1000);
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
		addressAndPaymentPage.checkBox_UseCouponCode().click();
		log.info("Clicked on the 'Gutscheincode einlösen' checkbox");
		addressAndPaymentPage.textField_VoucherCode().sendKeys(addressAndPaymentPage.voucherCode());
		log.info("Entered the Voucher code as -> " + addressAndPaymentPage.voucherCode());
		addressAndPaymentPage.buttonSubmitVoucherCode().click();
		log.info("Clicked on 'Gutscheincode einlösen' button");
		Thread.sleep(2000);
		Assert.assertTrue(addressAndPaymentPage.textBoxVoucherCodeSuccess().isDisplayed(), "The Voucher code was not applied successfully.");
		//Scrolling the Kreditkarte radio button into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerationPage.registrationCountry());
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

		log.info("*** Finished Test: existingCustomerPreLoginCreditCardMethodTest");
	}

	@Test(enabled=false)
	public void existingCustomerPreLoginGiftSetsCreditCardMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: existingCustomerPreLoginGiftSetsCreditCardMethodTest");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingCardPage = new GreetingCardPage(driver);
		GiftsPage giftsPage = new GiftsPage(driver);
		GiftsSetsPage giftsSetsPage = new GiftsSetsPage(driver);

		ensurePageLoaded();
		generalPage.clickCloseCookieMessage(false);

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileLinkLogin().click();
		}
		else {
			homePage.linkLogin().click();
		}
		log.info("Clicked on the Anmelden link for logging in");
		registerationPage.textFieldEmail().sendKeys(registerationPage.registeredUserEmail());
		log.info("Entered the email id as-> " + registerationPage.registeredUserEmail());
		registerationPage.textFieldPassword().sendKeys(registerationPage.registeredUserPassword());
		log.info("Entered the password as-> <CENSORED>");
		registerationPage.buttonLogin().click();
		log.info("Clicked on the 'Einloggen' button");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		homePage.linkProducts().click();
		log.info("Clicked on the Produkte link");
		homePage.linkGiftSets().click();
		log.info("Clicked on the Geschenksets link");
		giftsSetsPage.linkGiftsSetsFirstItem().click();
		log.info("Selecting the First item on Geschenksets page");
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
		greetingCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first Greeting Card on the the page");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileButtonEditGreetingText().click();
			log.info("Clicked on Gruß bearbeiten button for enetring text");
		}
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileDropdownSelectGreetingTextTemplate().click();
		}else {
			greetingCardPage.dropdownSelectGreetingTextTemplate().click();
		}
		log.info("Opened the Grußtextvorlage auswählen dropdown");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileGreetingCardTemplateThankYou().click();
		}else {
			greetingCardPage.greetingCardTemplateThankYou().click();
		}
		log.info("Selectd Dankeschön from the dropdown");
		Thread.sleep(1000);
		greetingCardPage.buttonContinueToGifts().click();
		log.info("Clicked on the button Weiter zu Geschenke");
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first Gift Item");
		giftsPage.linkGiftsPageSecondItem().click();
		log.info("Selected the second Gift Item");
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicked on the button Direkt zur Kasse");
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
			if(browserName.equalsIgnoreCase("mobile")) {
				//Scrolling the Kreditkarte radio button into view
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerationPage.registrationCountry());
			}
			addressAndPaymentPage.radioButtonCreditCard().click();
			log.info("Selected the payment method as Credit Card by clicking radio button Kreditkarte");
		}
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
		if(browserName.equalsIgnoreCase("mobile")) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addressAndPaymentPage.creditCardContinueButton());
			Thread.sleep(500);
		}
		addressAndPaymentPage.creditCardContinueButton().click();
		log.info("Clicked on the Weiter button on the Credit Card page");
		driver.switchTo().defaultContent();
		Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		log.info("Order is placed successfully");

		log.info("*** Finished Test: existingCustomerPreLoginGiftSetsCreditCardMethodTest");
	}

	@Test(enabled=true)
	public void existingCustomerPreLoginInvoiceMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: existingCustomerPreLoginInvoiceMethodTest");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingCardPage = new GreetingCardPage(driver);
		PiesAndGiftsPage piesAndGiftsPage = new PiesAndGiftsPage(driver);

		ensurePageLoaded();
		generalPage.clickCloseCookieMessage(false);

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileLinkLogin().click();
		}
		else {
			homePage.linkLogin().click();
		}
		log.info("Clicked on the Anmelden link for logging in");
		registerationPage.textFieldEmail().sendKeys(registerationPage.registeredUserEmail());
		log.info("Entered the email id as-> " + registerationPage.registeredUserEmail());
		registerationPage.textFieldPassword().sendKeys(registerationPage.registeredUserPassword());
		log.info("Entered the password as-> <CENSORED>");
		registerationPage.buttonLogin().click();
		log.info("Clicked on the 'Einloggen' button");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		homePage.linkProducts().click();
		log.info("Clicked on the Produkte link");
		homePage.linkPiesAndGifts().click();
		log.info("Clicked on the Torten & Geschenke link");
		piesAndGiftsPage.linkPiesAndGiftsFirstItem().click();
		log.info("Selecting the First item on Torten & Geschenke page");
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
		greetingCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the First greeting card on Dankeschön tab");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileButtonEditGreetingText().click();
			log.info("Clicked on Gruß bearbeiten button for enetring text");
		}
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileDropdownSelectGreetingTextTemplate().click();
		}else {
			greetingCardPage.dropdownSelectGreetingTextTemplate().click();
		}
		log.info("Opened the Grußtextvorlage auswählen dropdown");
		Thread.sleep(1000);
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileGreetingCardTemplateLove().click();
		}else {
			greetingCardPage.greetingCardTemplateLove().click();
		}
		log.info("Selectd Liebe from the dropdown");
		Thread.sleep(1000);
		greetingCardPage.buttonGreetingCardsPageDirectlyToCashRegister().click();
		log.info("Clicked on the button Direkt zur Kasse");
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
		addressAndPaymentPage.radioButtonInvoice().click();
		log.info("Selected the payment method as Invoice by clicking radio button Rechnung");
		addressAndPaymentPage.checkBox_UseCouponCode().click();
		log.info("Clicked on the 'Gutscheincode einlösen' checkbox");
		addressAndPaymentPage.textField_VoucherCode().sendKeys(addressAndPaymentPage.voucherCode());
		log.info("Entered the Voucher code as -> " + addressAndPaymentPage.voucherCode());
		addressAndPaymentPage.buttonSubmitVoucherCode().click();
		log.info("Clicked on 'Gutscheincode einlösen' button");
		Thread.sleep(2000);
		Assert.assertTrue(addressAndPaymentPage.textBoxVoucherCodeSuccess().isDisplayed(), "The Voucher code was not applied successfully.");
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");
		Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		log.info("Order is placed successfully");

		log.info("*** Finished Test: existingCustomerPreLoginInvoiceMethodTest");
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
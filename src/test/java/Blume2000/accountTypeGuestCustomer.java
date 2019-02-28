package Blume2000;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.OrderOverviewPage;
import pageObjects.PayPalPage;
import pageObjects.RegistrationPage;
import pageObjects.AddressAndPaymentMethodPage;
import pageObjects.BouquetsPage;
import pageObjects.DeliveryPage;
import pageObjects.GeneralPage;
import pageObjects.GiftsPage;
import pageObjects.GreetingCardPage;
import resources.BasicVariables;

public class accountTypeGuestCustomer<inherits> extends BasicVariables {
	//Use the line below to enable logging in your test cases
	public static Logger log = LogManager.getLogger(accountTypeGuestCustomer.class.getName());

	@BeforeMethod
	public void openBrowser() throws IOException
	{
		log.info("Driver is initializing");
		driver = initializeDriver();
		log.info("Opening the URL "+(prop.getProperty("URL")));
		driver.get(prop.getProperty("URL"));
	}

	@Test
	public void guestCustomerCreditCardMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: guestCustomerCreditCardMethodTest");

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

		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("mobile")) {
			homePage.mobileHamburgerMenu().click();
		}
		homePage.linkEvents().click();
		log.info("Clicked on the Anlässe link");
		homePage.linkBirthday().click();
		log.info("Clicked on the Geburtstag link");
		generalPage.linkFirstItem().click();
		log.info("Selecting the first item on Geburtstag page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		Thread.sleep(1000);
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("22");
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("2");
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("97");
		log.info("Entered the delivery postal code 22297");
		if(!browserName.equalsIgnoreCase("mobile")) {
			deliveryPage.buttonNextPostalCode().click();
			log.info("Clicked the next button after entering Postal Code");
		}
		Thread.sleep(2000);
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		Thread.sleep(1000);
		generalPage.radioButtonPremiumDelivery().click();
		log.info("Selected Premium Delivery radio button");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grukarte button");
		Thread.sleep(1000);
		greetingCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		if(browserName.equalsIgnoreCase("mobile")) {
			greetingCardPage.mobileButtonEditGreetingText().click();
			log.info("Clicked on Gruß bearbeiten button for enetring text");
		}
		greetingCardPage.textboxGreetingCardText().sendKeys("test message");
		log.info("Entered the greeting card text");
		greetingCardPage.buttonContinueToGifts().click();
		log.info("Clicked on the button Weiter zu Geschenke");
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first Gift Item");
		giftsPage.linkGiftsPageSecondItem().click();
		log.info("Selected the second Gift Item");
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicked on the button Direkt zur Kasse");
		registerationPage.buttonOrderAsGuest().click();
		log.info("Clicked on the button Als Gast bestellen");
		//Creating a random email id to register user
		String emailId = RandomStringUtils.randomAlphabetic(8);
		registerationPage.registrationEmail().sendKeys(emailId+"@testemail.com");
		log.info("For registeration entered email id "+ emailId);
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
		if(browserName.equalsIgnoreCase("mobile")) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addressAndPaymentPage.creditCardContinueButton());
			Thread.sleep(500);
		}
		addressAndPaymentPage.creditCardContinueButton().click();
		log.info("Clicked on the Weiter button on the Credit Card page");
		driver.switchTo().defaultContent();
		if(browserName.equalsIgnoreCase("mobile")) {
			Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		}else {
		Assert.assertEquals("Glückwunsch! Gute Wahl getroffen", generalPage.textOrderConfirmation().getText());
		}
		log.info("Order is placed successfully");
		
		log.info("*** Finished Test: guestCustomerCreditCardMethodTest");
	}

	@Test
	public void guestCustomerPaypalMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: guestCustomerPaypalMethodTest");
		
		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingCardPage = new GreetingCardPage(driver);
		GiftsPage giftsPage = new GiftsPage(driver);
		BouquetsPage bouquetsPage = new BouquetsPage(driver);
		PayPalPage payPalPage = new PayPalPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentMethodPage = new AddressAndPaymentMethodPage(driver);

		ensurePageLoaded();

		homePage.linkProducts().click();
		log.info("Clicked on the Produkte link");
		homePage.linkBouquets().click();
		log.info("Clicked on the Blumensträuße link");
		bouquetsPage.linkBouquetsSecondItem().click();
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
		generalPage.radioButtonPremiumDelivery().click();
		log.info("Selected Premium Delivery radio button");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter 'zur Grusskarte button'");
		Thread.sleep(1000);
		greetingCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		greetingCardPage.textboxGreetingCardText().sendKeys("test message");
		log.info("Entered the greeting card text");
		greetingCardPage.buttonContinueToGifts().click();
		log.info("Clicked on the button 'Weiter zu Geschenke'");
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first Gift Item");
		giftsPage.linkGiftsPageSecondItem().click();
		log.info("Selected the second Gift Item");
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicked on the button 'Direkt zur Kasse'");
		registerationPage.buttonOrderAsGuest().click();
		log.info("Clicked on the button 'Als Gast bestellen'");
		//Creating a random email id to register user
		String emailId = RandomStringUtils.randomAlphabetic(8);
		registerationPage.registrationEmail().sendKeys(emailId+"@testemail.com");
		log.info("For registeration entered email id "+ emailId);
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
		//((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-180)");
		registerationPage.copyDeliveryAndInvoiceAddress().click();
		log.info("Checking the checkbox so that delivery and invoice address are same");
		addressAndPaymentMethodPage.radioButtonPayPal().click();
		log.info("Clicking PayPal radio button");
		registerationPage.continueToOverview().click();
		log.info("Clicked on 'Weiter zur Uebersicht' button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on 'Kaufen' button");

		payPalPage.PayPalLogin();

		driver.switchTo().defaultContent();
		payPalPage.buttonPaypalPaymentConfirmation().click();
		driver.switchTo().defaultContent();
		log.info("Clicked on the 'Jetzt bezahlen' button for payment confirmation");
		Assert.assertEquals("Glückwunsch! Gute Wahl getroffen", generalPage.textOrderConfirmation().getText());
		log.info("Order is placed successfully");
		
		log.info("*** Finished Test: guestCustomerPaypalMethodTest");
	}

	@Test(enabled=false)
	public void guestCustomerInvoiceMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: guestCustomerInvoiceMethodTest");
		
		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingCardPage = new GreetingCardPage(driver);
		GiftsPage giftsPage = new GiftsPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentMethodPage = new AddressAndPaymentMethodPage(driver);

		ensurePageLoaded();

		homePage.linkProducts().click();
		log.info("Clicked on the Produkte link");
		homePage.linkTopseller().click();
		log.info("Clicked on the Topseller link");
		generalPage.linkSecondItem().click();
		log.info("Selecting the Second item on Topseller page");

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
		generalPage.radioButtonPremiumDelivery().click();
		log.info("Selected Premium Delivery radio button");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grukarte button");
		Thread.sleep(1000);
		greetingCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		greetingCardPage.textboxGreetingCardText().sendKeys("test message");
		log.info("Entered the greeting card text");
		greetingCardPage.buttonContinueToGifts().click();
		log.info("Clicked on the button Weiter zu Geschenke");
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first Gift Item");
		giftsPage.linkGiftsPageSecondItem().click();
		log.info("Selected the second Gift Item");
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicked on the button Direkt zur Kasse");
		registerationPage.buttonOrderAsGuest().click();
		log.info("Clicked on the button Als Gast bestellen");
		//Creating a random email id to register user
		String emailId = RandomStringUtils.randomAlphabetic(8);
		registerationPage.registrationEmail().sendKeys(emailId+"@testemail.com");
		log.info("For registeration entered email id "+ emailId);
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
		//((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-180)");
		registerationPage.copyDeliveryAndInvoiceAddress().click();
		log.info("Checking the checkbox so that delivery and invoice address are same");
		addressAndPaymentMethodPage.radioButtonInvoice().click();
		log.info("Selected the payment method as Invoice by clicking radio button Rechnung");
		addressAndPaymentMethodPage.checkBox_UseCouponCode().click();
		log.info("Clicked on the 'Gutscheincode einlösen' checkbox");
		addressAndPaymentMethodPage.textField_VoucherCode().sendKeys(addressAndPaymentMethodPage.voucherCode());
		log.info("Entered the Voucher code as -> " + addressAndPaymentMethodPage.voucherCode());
		addressAndPaymentMethodPage.buttonSubmitVoucherCode().click();
		log.info("Clicked on 'Gutscheincode einlösen' button");
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");
		Assert.assertEquals("Glückwunsch! Gute Wahl getroffen", generalPage.textOrderConfirmation().getText());
		log.info("Order is placed successfully");
	
		log.info("*** Finished Test: guestCustomerInvoiceMethodTest");
	}

	@Test
	public void guestCustomerSEPADebitCardMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: guestCustomerSEPADebitCardMethodTest");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingCardPage = new GreetingCardPage(driver);
		GiftsPage giftsPage = new GiftsPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentMethodPage = new AddressAndPaymentMethodPage(driver);

		ensurePageLoaded();

		homePage.linkProducts().click();
		log.info("Clicked on the 'Produkte' link");
		homePage.linkPlants().click();
		log.info("Clicked on the 'Pflanzen' link");
		generalPage.linkFirstItem().click();
		log.info("Selecting the First item on Pflanzen page");

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
		generalPage.radioButtonPremiumDelivery().click();
		log.info("Selected Premium Delivery radio button");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grukarte button");
		Thread.sleep(1000);
		greetingCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		greetingCardPage.textboxGreetingCardText().sendKeys("test message");
		log.info("Entered the greeting card text");
		greetingCardPage.buttonContinueToGifts().click();
		log.info("Clicked on the button Weiter zu Geschenke");
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first Gift Item");
		giftsPage.linkGiftsPageSecondItem().click();
		log.info("Selected the second Gift Item");
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicked on the button Direkt zur Kasse");
		registerationPage.buttonOrderAsGuest().click();
		log.info("Clicked on the button Als Gast bestellen");
		//Creating a random email id to register user
		String emailId = RandomStringUtils.randomAlphabetic(8);
		registerationPage.registrationEmail().sendKeys(emailId+"@testemail.com");
		log.info("For registeration entered email id "+ emailId);
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
		registerationPage.copyDeliveryAndInvoiceAddress().click();
		log.info("Checking the checkbox so that delivery and invoice address are same");
		addressAndPaymentMethodPage.radioButtonDebitCard().click();
		log.info("Selected the Bankeinzug/Lastschrift (Debit Card)radio button");
		addressAndPaymentMethodPage.textFieldAccountHolder().sendKeys(addressAndPaymentMethodPage.accountHolderName());
		log.info("Entered the account holder name as "+ addressAndPaymentMethodPage.accountHolderName());
		addressAndPaymentMethodPage.textFieldIBANNumber().sendKeys(addressAndPaymentMethodPage.ibanNumber());
		log.info("Entered the INAN number as "+ addressAndPaymentMethodPage.ibanNumber());
		addressAndPaymentMethodPage.chkBoxSEPADirectDebit().click();
		log.info("Checked the Direct debit checkbox");
		addressAndPaymentMethodPage.checkBox_UseCouponCode().click();
		log.info("Clicked on the 'Gutscheincode einlösen' checkbox");
		addressAndPaymentMethodPage.textField_VoucherCode().sendKeys(addressAndPaymentMethodPage.voucherCode());
		log.info("Entered the Voucher code as -> " + addressAndPaymentMethodPage.voucherCode());
		addressAndPaymentMethodPage.buttonSubmitVoucherCode().click();
		log.info("Clicked on 'Gutscheincode einlösen' button");
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");
		Assert.assertEquals("Glückwunsch! Gute Wahl getroffen", generalPage.textOrderConfirmation().getText());
		log.info("Order is placed successfully");

		log.info("*** Finished Test: guestCustomerSEPADebitCardMethodTest");
	}

	@Test
	public void guestCustomerRatePayMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: guestCustomerRatePayMethodTest");

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
		homePage.linkEvents().click();
		log.info("Clicked on the Anlässe link");
		homePage.linkBirthday().click();
		log.info("Clicked on the Geburtstag link");
		generalPage.linkFirstItem().click();
		log.info("Selecting the first item on Geburtstag page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		Thread.sleep(1000);
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("49536");
		log.info("Entered the delivery postal code 49536");
		deliveryPage.buttonNextPostalCode().click();
		log.info("Clicked the next button after entering Postal Code");
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		generalPage.radioButtonPremiumDelivery().click();
		log.info("Selected Premium Delivery radio button");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grußkarte button");
		Thread.sleep(1000);
		greetingCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		greetingCardPage.textboxGreetingCardText().sendKeys("test message");
		log.info("Entered the greeting card text");
		greetingCardPage.buttonContinueToGifts().click();
		log.info("Clicked on the button Weiter zu Geschenke");
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first Gift Item");
		giftsPage.linkGiftsPageSecondItem().click();
		log.info("Selected the second Gift Item");
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicked on the button Direkt zur Kasse");
		registerationPage.buttonOrderAsGuest().click();
		log.info("Clicked on the button Als Gast bestellen");
		//Creating a random email id to register user
		String emailId = RandomStringUtils.randomAlphabetic(8);
		registerationPage.registrationEmail().sendKeys(emailId+"@testemail.com");
		log.info("For registeration entered email id "+ emailId);
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
		log.info("Entered the birth day");
		addressAndPaymentPage.dropdownBirthMonth().sendKeys(addressAndPaymentPage.ratepayBirthMonth());
		log.info("Entered the birth month");
		addressAndPaymentPage.dropdownBirthYear().sendKeys(addressAndPaymentPage.ratepayBirthYear());
		log.info("Entered the birth year");
		addressAndPaymentPage.textFieldRatepayTelephoneNumber().sendKeys(addressAndPaymentPage.ratepayTelephoneNumber());
		log.info("Entered the telephone number");
		Thread.sleep(1000);
		addressAndPaymentPage.checkBoxInvoiceConsentDeclaration().click();
		log.info("Checked the Consent Declaration checkbox");
		addressAndPaymentPage.checkBox_UseCouponCode().click();
		log.info("Clicked on the 'Gutscheincode einlösen' checkbox");
		addressAndPaymentPage.textField_VoucherCode().sendKeys(addressAndPaymentPage.voucherCode());
		log.info("Entered the Voucher code as -> " + addressAndPaymentPage.voucherCode());
		addressAndPaymentPage.buttonSubmitVoucherCode().click();
		log.info("Clicked on 'Gutscheincode einlösen' button");
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");
		Assert.assertEquals("Glückwunsch! Gute Wahl getroffen", generalPage.textOrderConfirmation().getText());
		log.info("Order is placed successfully");

		log.info("*** Finished Test: guestCustomerRatePayMethodTest");
	}

	@Test(enabled=false)
	public void guestCustomerInvoiceMethodThankYouTest() throws InterruptedException
	{
		log.info("*** Starting Test: guestCustomerInvoiceMethodThankYouTest");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingCardPage = new GreetingCardPage(driver);
		GiftsPage giftsPage = new GiftsPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentMethodPage = new AddressAndPaymentMethodPage(driver);

		ensurePageLoaded();

		homePage.linkEvents().click();
		log.info("Clicked on the Anlässe link on the home page");
		homePage.linkThankYou().click();
		log.info("Clicked on the Dankeschön link in the menu");
		generalPage.linkFirstItem().click();
		log.info("Selected the first item on the Dankeschön page");

		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		Thread.sleep(1000);
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("22297");
		log.info("Entered the delivery postal code 22297");
		deliveryPage.buttonNextPostalCode().click();
		log.info("Clicked the next button after entering Postal Code");
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		Thread.sleep(1000);
		generalPage.radioButtonPremiumDelivery().click();
		log.info("Selected Premium Delivery radio button");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grukarte button");
		Thread.sleep(1000);
		greetingCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		greetingCardPage.textboxGreetingCardText().sendKeys("test message");
		log.info("Entered the greeting card text");
		greetingCardPage.buttonContinueToGifts().click();
		log.info("Clicked on the button Weiter zu Geschenke");
		giftsPage.linkGiftsPageFirstItem().click();
		log.info("Selected the first Gift Item");
		giftsPage.linkGiftsPageSecondItem().click();
		log.info("Selected the second Gift Item");
		giftsPage.buttonDirectlyToCashRegister().click();
		log.info("Clicked on the button Direkt zur Kasse");
		registerationPage.buttonOrderAsGuest().click();
		log.info("Clicked on the button Als Gast bestellen");
		//Creating a random email id to register user
		String emailId = RandomStringUtils.randomAlphabetic(8);
		registerationPage.registrationEmail().sendKeys(emailId+"@testemail.com");
		log.info("For registeration entered email id "+ emailId);
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
		//((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-180)");
		registerationPage.copyDeliveryAndInvoiceAddress().click();
		log.info("Checking the checkbox so that delivery and invoice address are same");
		addressAndPaymentMethodPage.radioButtonInvoice().click();
		log.info("Selected the payment method as Invoice by clicking radio button Rechnung");
		addressAndPaymentMethodPage.checkBox_UseCouponCode().click();
		log.info("Clicked on the 'Gutscheincode einlösen' checkbox");
		addressAndPaymentMethodPage.textField_VoucherCode().sendKeys(addressAndPaymentMethodPage.voucherCode());
		log.info("Entered the Voucher code as -> " + addressAndPaymentMethodPage.voucherCode());
		addressAndPaymentMethodPage.buttonSubmitVoucherCode().click();
		log.info("Clicked on 'Gutscheincode einlösen' button");
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");
		Assert.assertEquals("Glückwunsch! Gute Wahl getroffen", generalPage.textOrderConfirmation().getText());
		log.info("Order is placed successfully");

		log.info("*** Finished Test: guestCustomerInvoiceMethodThankYouTest");
	}

	@AfterMethod
	public void closeBrowser()
	{
		driver.quit();
		//releasing the memory
		driver = null;
		log.info("---------------Cleaned Up The Setup--------------");
	}

}

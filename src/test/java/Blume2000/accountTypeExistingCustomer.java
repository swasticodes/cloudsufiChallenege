package Blume2000;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.OrderOverviewPage;
import pageObjects.PayPalPage;
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
		deliveryPage.buttonNextPostalCode().click();
		log.info("Clicked the next button after entering Postal Code");
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grußkarte button");
		greetingCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		greetingCardPage.textboxGreetingCardText().sendKeys("test message");
		log.info("Entered the greeting card text");
		greetingCardPage.buttonContinueToGifts().click();
		log.info("Clicked on the button Weiter zu Geschenke");
		giftsPage.linkGiftsFirstItem().click();
		log.info("Selected the first Gift Item");
		giftsPage.linkGiftsSecondItem().click();
		log.info("Selected the second Gift Item");
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
		addressAndPaymentPage.dropDownCreditCardExpiryYear().sendKeys(addressAndPaymentPage.creditCardExpiryYear());
		log.info("Entered Credit Card Expiry Year as "+addressAndPaymentPage.creditCardExpiryYear());
		addressAndPaymentPage.creditCardContinueButton().click();
		log.info("Clicked on the Weiter button on the Credit Card page");
		driver.switchTo().defaultContent();
		Assert.assertEquals("Glückwunsch! Gute Wahl getroffen", generalPage.textOrderConfirmation().getText());
		log.info("Order is placed successfully");

		log.info("*** Finished Test: existingCustomerCreditCardMethodTest");
	}

	@Test(enabled=false)
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
		deliveryPage.buttonNextPostalCode().click();
		log.info("Clicked the next button after entering Postal Code");
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grußkarte button");
		greetingCardPage.linkGreetingCardFirstItem().click();
		log.info("Selected the first greeting card");
		greetingCardPage.textboxGreetingCardText().sendKeys("test message");
		log.info("Entered the greeting card text");
		greetingCardPage.buttonContinueToGifts().click();
		log.info("Clicked on the button Weiter zu Geschenke");
		giftsPage.linkGiftsFirstItem().click();
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
		Assert.assertEquals("Glückwunsch! Gute Wahl getroffen", generalPage.textOrderConfirmation().getText());
		log.info("Order is placed successfully");

		log.info("*** Finished Test: existingCustomerInvoiceMethodTest");
	}

	@Test
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
		deliveryPage.buttonNextPostalCode().click();
		log.info("Clicked the next button after entering Postal Code");
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grußkarte button");
		greetingCardPage.tabLove().click();
		log.info("Clicked on Liebe tab");
		greetingCardPage.tabLoveSecondItem().click();
		log.info("Selected the Second greeting card on Liebe tab");
		greetingCardPage.textboxGreetingCardText().sendKeys("test message");
		log.info("Entered the greeting card text");
		greetingCardPage.buttonContinueToGifts().click();
		log.info("Clicked on the button Weiter zu Geschenke");
		giftsPage.linkGiftsFirstItem().click();
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
		Thread.sleep(1000);
		addressAndPaymentPage.checkBoxInvoiceConsentDeclaration().click();
		log.info("Checked the Consent Declaration checkbox");
		registerationPage.continueToOverview().click();
		log.info("Clicked on Weiter zur Übersicht button");
		orderOverviewPage.buttonToBuy().click();
		log.info("Clicked on Kaufen button");
		Assert.assertEquals("Glückwunsch! Gute Wahl getroffen", generalPage.textOrderConfirmation().getText());
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
		deliveryPage.buttonNextPostalCode().click();
		log.info("Clicked the next button after entering Postal Code");
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grußkarte button");
		Thread.sleep(1000);
		greetingCardPage.tabLove().click();
		log.info("Clicked on the tab Liebe");
		greetingCardPage.secondGreetingCardLoveTab().click();
		log.info("Selected the second Greeting Card on the Love tab");
		greetingCardPage.textboxGreetingCardText().sendKeys("test message");
		log.info("Entered the greeting card text");
		greetingCardPage.buttonContinueToGifts().click();
		log.info("Clicked on the button 'Weiter zu Geschenke'");
		giftsPage.linkGiftsFirstItem().click();
		log.info("Selected the first Gift Item");
		giftsPage.linkGiftsSecondItem().click();
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

		log.info("*** Finished Test: existingCustomerPaypalMethodTest");
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
		log.info("---------------Cleaned Up The Setup--------------");
	}

	@AfterTest
	public void closeBrowser()
	{
		//releasing the memory
		driver = null;
	}

}
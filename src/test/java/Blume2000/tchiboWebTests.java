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
import pageObjects.GiftsPage;
import pageObjects.GiftsSetsPage;
import pageObjects.GreetingCardPage;
import resources.BasicVariables;

public class tchiboWebTests<inherits> extends BasicVariables {
	//Use the line below to enable logging in your test cases
	public static Logger log = LogManager.getLogger(tchiboWebTests.class.getName());

	@BeforeMethod
	public void openBrowser() throws IOException
	{
		log.info("Driver is initializing");
		driver = initializeDriver();
		log.info("Opening the URL "+(prop.getProperty("URL_Tchibo")));
		driver.get(prop.getProperty("URL_Tchibo"));
	}
	@Test
	public void tchiboGuestCustomerPaypalPaymentMethodTest() throws InterruptedException, IOException
	{
		log.info("*** Starting Test: tchiboGuestCustomerPaypalPaymentMethodTest");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		GeburtstagPage birthdayPage = new GeburtstagPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		PayPalPage payPalPage = new PayPalPage(driver);

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
		birthdayPage.linkBirthdayFirstItem().click();
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
		registerationPage.buttonOrderAsGuest().click();
		log.info("Clicked on the button Als Gast bestellen");
		//Creating a random email id to register user
		String emailId = RandomStringUtils.randomAlphabetic(8);
		registerationPage.registrationEmail().sendKeys(emailId+"@testemail.com");
		log.info("For registeration entered email id "+ emailId);
		registerationPage.tchiboSalutationHerr().click();
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
		Assert.assertTrue(generalPage.textOrderConfirmation().getText().contains("Glückwunsch"));
		log.info("Order is placed successfully");

		log.info("*** Finished Test: tchiboGuestCustomerPaypalPaymentMethodTest");
	}

	@Test
	public void tchiboExistingCustomerSEPADirectDebitMethodTest() throws InterruptedException
	{
		log.info("*** Starting Test: tchiboExistingCustomerSEPADirectDebitMethodTest");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
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
		registerationPage.textFieldEmail().sendKeys(registerationPage.registeredUserEmail());
		log.info("Entered the email id as-> " + registerationPage.registeredUserEmail());
		registerationPage.textFieldPassword().sendKeys(registerationPage.registeredUserPassword());
		log.info("Entered the password as-> <CENSORED>");
		registerationPage.buttonLogin().click();
		log.info("Clicked on the 'Einloggen' button");
		registerationPage.tchiboSalutationHerr().click();
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

		log.info("*** Finished Test: tchiboExistingCustomerSEPADirectDebitMethodTest");
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

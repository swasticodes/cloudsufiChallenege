package Blume2000;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.AddressAndPaymentMethodPage;
import pageObjects.CSC_CustomerDetailsPage;
import pageObjects.CSC_CustomerRegistrationPage;
import pageObjects.CSC_GeneralPage;
import pageObjects.CSC_HomePage;
import pageObjects.DeliveryPage;
import pageObjects.GeneralPage;
import pageObjects.GreetingCardPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.OrderOverviewPage;
import pageObjects.RegistrationPage;
import resources.BasicVariables;

public class cscToolTests<inherits> extends BasicVariables {
	//Use the line below to enable logging in your test cases
	public static Logger log = LogManager.getLogger(cscToolTests.class.getName());

	@BeforeMethod
	public void openBrowser() throws IOException
	{
		log.info("Driver is initializing");
		driver = initializeDriver();
		log.info("Opening the URL "+(prop.getProperty("URL_CSC")));
		driver.get(prop.getProperty("URL_CSC"));
	}


	@Test
	public void cscNewCustomerCreation() throws InterruptedException
	{
		log.info("*** Starting Test: cscNewCustomerCreation");

		//Creating the Objects below to access the functions
		LoginPage loginPage = new LoginPage(driver);
		CSC_HomePage cscHomePage = new CSC_HomePage(driver);
		CSC_CustomerRegistrationPage cscCustomerRegistration = new CSC_CustomerRegistrationPage(driver);
		CSC_CustomerDetailsPage cscCustomerDetailsPage = new CSC_CustomerDetailsPage(driver);

		ensurePageLoaded();

		loginPage.cscLogin();
		cscHomePage.buttonNewCustomer().click();
		log.info("Clicked on the button 'Neuer Kunde'");
		cscCustomerRegistration.dropdwonButtonSalutation().click();
		log.info("Clicked on the button for opening the dropdown 'Anrede'");
		cscCustomerRegistration.dropdownOptionHerr().click();
		log.info("Clicked on the Anrede option 'Herr'");
		cscCustomerRegistration.textFieldFirstName().sendKeys("Max");
		log.info("Enterd the Vorname as 'Max'");
		cscCustomerRegistration.textFieldLastName().sendKeys("Mustermann");
		log.info("Enterd the Nachname as 'Mustermann'");
		cscCustomerRegistration.textFieldStreet().sendKeys("Vogelsanger Str.");
		log.info("Enterd the Str. as 'Vogelsanger Str.'");
		cscCustomerRegistration.textFieldHouseNumber().sendKeys("286");
		log.info("Enterd the Nr. as '286'");
		cscCustomerRegistration.textFieldPostalCode().sendKeys("50825");
		log.info("Enterd the PLZ as '50825'");
		cscCustomerRegistration.textFieldCity().sendKeys("Cologne");
		log.info("Enterd the Ort as 'Cologne'");
		cscCustomerRegistration.textFieldEmail().sendKeys("test@testemail.de");
		log.info("Enterd the E-mail id");
		cscCustomerRegistration.buttonSave().click();
		Assert.assertTrue(cscCustomerDetailsPage.bannerCustomerNumber().getText().contains("Kunde"));

		log.info("*** Finished Test: cscNewCustomerCreation");
	}

	@Test(enabled=true)
	public void cscNewOrderCreation() throws InterruptedException
	{
		log.info("*** Starting Test: cscNewOrderCreation");

		//Creating the Objects below to access the functions
		LoginPage loginPage = new LoginPage(driver);
		CSC_HomePage cscHomePage = new CSC_HomePage(driver);
		CSC_CustomerRegistrationPage cscCustomerRegistration = new CSC_CustomerRegistrationPage(driver);
		CSC_GeneralPage cscGeneralPage = new CSC_GeneralPage(driver);

		ensurePageLoaded();

		loginPage.cscLogin();
		cscHomePage.buttonNewOrder().click();
		log.info("Clicked on the button 'Neu'");
		cscCustomerRegistration.dropdwonButtonSalutation().click();
		log.info("Clicked on the button for opening the dropdown 'Anrede'");
		cscCustomerRegistration.dropdownOptionHerr().click();
		log.info("Clicked on the Anrede option 'Herr'");
		Thread.sleep(2000);
		cscCustomerRegistration.textFieldFirstName().sendKeys("Max");
		log.info("Enterd the Vorname as 'Max'");
		cscCustomerRegistration.textFieldLastName().sendKeys("Mustermann");
		log.info("Enterd the Nachname as 'Mustermann'");
		cscCustomerRegistration.textFieldStreet().sendKeys("Vogelsanger Str.");
		log.info("Enterd the Str. as 'Vogelsanger Str.'");
		cscCustomerRegistration.textFieldHouseNumber().sendKeys("286");
		log.info("Enterd the Nr. as '286'");
		cscCustomerRegistration.textFieldPostalCode().sendKeys("50825");
		log.info("Enterd the PLZ as '50825'");
		cscCustomerRegistration.textFieldCity().sendKeys("Cologne");
		log.info("Enterd the Ort as 'Cologne'");
		cscCustomerRegistration.textFieldEmail().sendKeys("test@testemail.de");
		log.info("Enterd the E-mail id");
		cscCustomerRegistration.textFieldTelepjoneNumber().sendKeys("017649419647");
		log.info("Enterd theTelephone Number");
		cscGeneralPage.buttonNext().click();
		log.info("Clicked on the button 'Weiter'");
		Thread.sleep(3000);
		cscCustomerRegistration.dropdwonButtonSalutation().click();
		log.info("Clicked on the button for opening the dropdown 'Anrede'");
		Thread.sleep(1000);
		cscCustomerRegistration.dropdownOptionHerr().click();
		log.info("Clicked on the Anrede option 'Herr'");
		Thread.sleep(2000);
		cscCustomerRegistration.textFieldFirstName().sendKeys("Max");
		log.info("Enterd the Vorname as 'Max'");
		cscCustomerRegistration.textFieldLastName().sendKeys("Mustermann");
		log.info("Enterd the Nachname as 'Mustermann'");
		cscCustomerRegistration.textFieldStreet().sendKeys("Vogelsanger Str.");
		log.info("Enterd the Str. as 'Vogelsanger Str.'");
		cscCustomerRegistration.textFieldHouseNumber().sendKeys("286");
		log.info("Enterd the Nr. as '286'");
		cscCustomerRegistration.textFieldPostalCode().sendKeys("50825");
		log.info("Enterd the PLZ as '50825'");
		cscCustomerRegistration.textFieldCity().click();
		//Remove the if condition when the bug is fixed
		if( cscGeneralPage.errorPopupTechnicalSupport().isDisplayed())
		{
			Thread.sleep(4000);
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.ENTER);
			actions.build().perform();
		}
		Thread.sleep(1000);
		cscCustomerRegistration.textFieldCity().sendKeys("Cologne");	
		log.info("Enterd the Ort as 'Cologne'");
		cscCustomerRegistration.textFieldEmail().sendKeys("test@testemail.de");
		log.info("Enterd the E-mail id");
		cscCustomerRegistration.textFieldTelepjoneNumber().sendKeys("017649419647");
		log.info("Enterd theTelephone Number");
		cscGeneralPage.dateDeliverDate().click();
		log.info("Selected the delivery date");
		Thread.sleep(2000);
		//Remove the if condition when the bug is fixed
		if( cscGeneralPage.errorPopupTechnicalSupport().isDisplayed())
		{
			Thread.sleep(4000);
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.ENTER);
			actions.build().perform();
		}
		Thread.sleep(1000);
		cscGeneralPage.radioButtonStandardDelivery().click();
		log.info("Selected Standard Delivery Option");
		cscGeneralPage.buttonNext().click();
		log.info("Clicked on the button 'Weiter'");
		Thread.sleep(2000);
		cscGeneralPage.textFieldSearchArticle("B");
		log.info("Selecting the first item after serarching for 'B");
		cscGeneralPage.buttonNext().click();
		log.info("Clicked on the button 'Weiter'");
		Thread.sleep(2000);
		cscGeneralPage.buttonNext().click();
		log.info("Clicked on the button 'Weiter'");
		Thread.sleep(2000);
		cscGeneralPage.buttonNext().click();
		log.info("Clicked on the button 'Weiter'");
		Thread.sleep(2000);
		cscGeneralPage.buttonNext().click();
		log.info("Clicked on the button 'Weiter'");
		Thread.sleep(2000);
		cscGeneralPage.radioButtonDirectDebit().click();
		log.info("Clicked on the radio button 'Lastschrift'");
		cscGeneralPage.textFieldAccountHolder();
		log.info("Entered the account holder name");
		cscGeneralPage.textFieldNumberIBAN();
		log.info("Enterd the test IBAN number");
		Thread.sleep(2000);
		cscGeneralPage.buttonNext().click();
		log.info("Clicked on the button 'Weiter'");
		Thread.sleep(2000);
		cscGeneralPage.buttonPlaceOrder().click();
		log.info("Clicked on the button 'Bestellen'");
		Thread.sleep(2000);
		Assert.assertTrue(cscGeneralPage.textBoxOrderConfirmationMessage().getText().contains("Es wurde erfolgreich ein neuer Auftrag mit der Nummer:"));

		log.info("*** Finished Test: cscNewOrderCreation");
	}


	@Test
	public void cscOrderNumberComparison() throws InterruptedException, IOException
	{
		log.info("*** Starting Test: cscOrderNumberComparison");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		GreetingCardPage greetingCardPage = new GreetingCardPage(driver);
		CSC_HomePage cscHomePage = new CSC_HomePage(driver);
		LoginPage loginPage = new LoginPage(driver);
		CSC_CustomerDetailsPage cscCustomerDetailsPage = new CSC_CustomerDetailsPage(driver);

		ensurePageLoaded();
		driver.get(prop.getProperty("URL"));
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
		Thread.sleep(2000);
		deliveryPage.dayActiveDay().click();
		log.info("Selecting the active day for delivery");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Grußkarte button");
		Thread.sleep(1000);
		greetingCardPage.processStepToOrder().click();
		log.info("Clicked on 'Bestellen' in the Order Progress bar");
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
		//Storing the order number from the front end in a variable
		String frontEndOrderNumber = generalPage.getOrderNumber();
		log.info("The order number is ->"+frontEndOrderNumber);
		driver.get(prop.getProperty("URL_CSC"));
		log.info("Opened the CSC URL");
		loginPage.cscLogin();
		log.info("Logged in CSC");
		cscHomePage.searchOrderNumber(frontEndOrderNumber);
		log.info("Searched the order number in the CSC");
		Assert.assertTrue(cscCustomerDetailsPage.bannerCustomerNumber().getText().contains("Kunde"));

		log.info("*** Finished Test: cscOrderNumberComparison");
	}


	@Test
	public void cscPriceComparison() throws InterruptedException, IOException
	{
		log.info("*** Starting Test: cscPriceComparison");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		GreetingCardPage greetingCardPage = new GreetingCardPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);
		OrderOverviewPage orderOverviewPage = new OrderOverviewPage(driver);
		CSC_HomePage cscHomePage = new CSC_HomePage(driver);
		LoginPage loginPage = new LoginPage(driver);
		CSC_GeneralPage cscGeneralPage = new CSC_GeneralPage(driver);

		ensurePageLoaded();
		driver.get(prop.getProperty("URL"));
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
		//Storing the Article Number in a variable
		String articleNumber = generalPage.textArticleNumber();
		log.info("Stored the article id from the front end to a variable for comparison");
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
		//Storing the price in a variable from front end
		String itemPrice = orderOverviewPage.textItemPrice();
		log.info("Stored the item price from the front end to a variable");
		driver.get(prop.getProperty("URL_CSC"));
		log.info("Opened the CSC URL");
		loginPage.cscLogin();
		log.info("Logged in CSC");
		cscHomePage.buttonNewOrder().click();
		log.info("Clicked on the button 'Neu'");
		cscGeneralPage.tabMainProduct().click();
		log.info("Clicked on the tab 'Hauptprodukt'");
		//Closing the Warning pop up
		if( cscGeneralPage.popUpWarning().isDisplayed())
		{
			Thread.sleep(2000);
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.ENTER);
			actions.build().perform();
		}
		log.info("Closed the warning pop up");
		Thread.sleep(2000);
		cscGeneralPage.textFieldSearchArticle(articleNumber);
		log.info("Searching for the article number on CSC");
		Assert.assertTrue(itemPrice.contains(cscGeneralPage.textPriceOnCsc()));
		log.info("The price on the front end matches the price on CSC");

		log.info("*** Finished Test: cscPriceComparison");
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

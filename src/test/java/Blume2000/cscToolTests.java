package Blume2000;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
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

	@Test
	public void cscEditOrderDetails() throws InterruptedException, IOException
	{
		log.info("*** Starting Test: cscEditOrderDetails");

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
		CSC_GeneralPage cscGeneralpage = new CSC_GeneralPage(driver);
		CSC_CustomerRegistrationPage cscCustomerRegistration = new CSC_CustomerRegistrationPage(driver);

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
		cscGeneralpage.buttonEdit().click();
		log.info("Clicked on the 'Editieren' button");
		Thread.sleep(1000);
		cscCustomerRegistration.textFieldHouseNumber().clear();
		log.info("Cleared the old House Number");
		Thread.sleep(1000);
		cscCustomerRegistration.textFieldHouseNumber().sendKeys("286");
		log.info("Enterd the new Nr. as '286'");
		Thread.sleep(1000);
		cscCustomerRegistration.buttonSaveEditedAddress().click();
		log.info("Clicked on the Save button");
		Thread.sleep(2000);
		Assert.assertTrue(cscCustomerRegistration.textBoxSuccessfulOrderEdit().getText().contains("Die Änderung wurde gespeichert."));

		log.info("*** Finished Test: cscEditOrderDetails");
	}

	@Test
	public void cscSerachCustomerTest() throws InterruptedException
	{
		log.info("*** Starting Test: cscSerachCustomerTest");

		//Creating the Objects below to access the functions
		LoginPage loginPage = new LoginPage(driver);
		CSC_HomePage cscHomePage = new CSC_HomePage(driver);
		CSC_CustomerDetailsPage cscCustomerDetailsPage = new CSC_CustomerDetailsPage(driver);

		ensurePageLoaded();

		loginPage.cscLogin();
		log.info("Logged in CSC");
		Thread.sleep(1000);
		cscHomePage.textFieldFirstName("testFirst");
		log.info("Entered the first name in 'Vorname'");
		Thread.sleep(1000);
		cscHomePage.textFieldLastName("testLast");
		log.info("Entered the last name in 'Nachname'");
		Thread.sleep(1000);
		cscHomePage.textFieldStreetName("test");
		log.info("Entered the street name in 'Straße'");
		Thread.sleep(1000);
		Assert.assertTrue(cscCustomerDetailsPage.bannerCustomerNumber().getText().contains("Kunde"));

		log.info("*** Finished Test: cscSerachCustomerTest");
	}

	@Test(enabled=true)
	public void cscEditCustomerDetails() throws InterruptedException
	{
		log.info("*** Starting Test: cscEditCustomerDetails");

		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		CSC_HomePage cscHomePage = new CSC_HomePage(driver);
		CSC_CustomerRegistrationPage cscCustomerRegistration = new CSC_CustomerRegistrationPage(driver);
		CSC_GeneralPage cscGeneralpage = new CSC_GeneralPage(driver);

		ensurePageLoaded();
		driver.get(prop.getProperty("URL"));
		generalPage.clickCloseCookieMessage(false);

		homePage.linkLogin().click();
		loginPage.buttonRegister().click();
		//Creating a random email id to register user
		String emailId = RandomStringUtils.randomAlphabetic(8);
		registerationPage.registrationEmail().sendKeys(emailId+"@testemail.de");
		log.info("For registeration entered email id "+ emailId);
		registerationPage.registrationPassword().sendKeys("123456");
		log.info("For registeration entered password as 123456");
		registerationPage.registrationConfrimPassword().sendKeys("123456");
		log.info("For registeration confirmed password as 123456");
		registerationPage.registrationSalutation().click();
		log.info("For registration selected salutation as Herr");
		registerationPage.textFieldNewUserCreationFirstName().sendKeys("TestFirst");
		log.info("For registeration entered first name as TestFirst");
		registerationPage.textFieldNewUserCreationLastName().sendKeys("TestLast");
		log.info("For registeration entered last name as TestLast");
		registerationPage.textFieldNewUserCreationStreetName().sendKeys("Überseering");
		log.info("For registeration entered street name as Überseering");
		registerationPage.textFieldNewUserCreationHouseNumber().sendKeys("33");
		log.info("For registeration entered street number as 33");
		registerationPage.textFieldNewUserCreationCity().sendKeys("Hamburg");
		log.info("For registeration entered city as Hamburg");
		registerationPage.textFieldNewUserCreationPostCode().sendKeys("20148");
		log.info("For registeration entered postal code as '20148'");
		registerationPage.dropdownNewUserCreationCountry().click();
		registerationPage.dropDownentryDetuschland().click();
		log.info("For registeration entered Country as 'Deutschland'");
		registerationPage.buttonNewUserRegister().click();
		log.info("Cliecked on the Register button");
		Thread.sleep(2000);
		driver.get(prop.getProperty("URL_CSC"));
		log.info("Opened the CSC URL");
		loginPage.cscLogin();
		log.info("Logged in CSC");
		Thread.sleep(2000);
		cscHomePage.buttonAdvancedSearch().click();
		log.info("Clicked on 'Erweitert' button");
		cscHomePage.textFieldAdvancedSearchEmail(emailId+"@testemail.de");
		log.info("Searched for the email id");
		cscGeneralpage.buttonEdit().click();
		log.info("Clicked on the 'Editieren' button");
		Thread.sleep(1000);
		cscCustomerRegistration.textFieldHouseNumber().clear();
		log.info("Cleared the old House Number");
		Thread.sleep(1000);
		cscCustomerRegistration.textFieldHouseNumber().sendKeys("286");
		log.info("Enterd the new Nr. as '286'");
		Thread.sleep(1000);
		cscCustomerRegistration.buttonSaveEditedAddress().click();
		log.info("Clicked on the Save button");
		Thread.sleep(2000);
		Assert.assertTrue(cscCustomerRegistration.textBoxSuccessfuledit().getText().contains("Die Änderung wurde gespeichert."));

		log.info("*** Finished Test: cscEditCustomerDetails");
	}

	@Test
	public void cscNewOrderUsingCC() throws InterruptedException, IOException
	{
		log.info("*** Starting Test: cscNewOrderUsingCC");

		//Creating the Objects below to access the functions
		LoginPage loginPage = new LoginPage(driver);
		CSC_HomePage cscHomePage = new CSC_HomePage(driver);
		CSC_CustomerRegistrationPage cscCustomerRegistration = new CSC_CustomerRegistrationPage(driver);
		CSC_GeneralPage cscGeneralPage = new CSC_GeneralPage(driver);
		AddressAndPaymentMethodPage addressAndPaymentPage = new AddressAndPaymentMethodPage(driver);

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
		Thread.sleep(1000);
		cscGeneralPage.buttonUpdate().click();
		log.info("Clicked on the button 'aktualisieren'");
		Thread.sleep(1000);
		cscGeneralPage.buttonAddToCartFirstItem().click();
		log.info("Selected the first gift item");
		Thread.sleep(1000);
		cscGeneralPage.buttonNext().click();
		log.info("Clicked on the button 'Weiter'");
		Thread.sleep(1000);
		cscGeneralPage.buttonUpdate().click();
		log.info("Clicked on the button 'aktualisieren'");
		Thread.sleep(1000);
		cscGeneralPage.buttonAddToCartFirstItem().click();
		log.info("Selected the first Greeting card item");
		Thread.sleep(1000);
		cscGeneralPage.textFieldGreetingCardText().sendKeys("test message");
		log.info("Entered test message in greeting card text");
		cscGeneralPage.buttonNext().click();
		log.info("Clicked on the button 'Weiter'");
		Thread.sleep(1000);
		cscGeneralPage.buttonUpdate().click();
		log.info("Clicked on the button 'aktualisieren'");
		Thread.sleep(1000);
		cscGeneralPage.buttonAddToCartFirstItem().click();
		log.info("Selected the first Packaging option");
		Thread.sleep(1000);
		cscGeneralPage.buttonNext().click();
		log.info("Clicked on the button 'Weiter'");
		Thread.sleep(1000);
		cscGeneralPage.radioButtonCreditCard().click();
		log.info("Clicked on the radio button 'Kreditkarte'");
		Thread.sleep(10000);
		driver.switchTo().frame("985");
		Thread.sleep(1000);
		driver.switchTo().frame("braintree-hosted-field-number");
		Thread.sleep(1000);
		addressAndPaymentPage.textFieldCreditCardNumber().sendKeys(addressAndPaymentPage.creditCardNumber());
		log.info("Enetered the Credit Card number");
		driver.switchTo().parentFrame();
		Thread.sleep(1000);
		driver.switchTo().frame("braintree-hosted-field-expirationDate");
		Thread.sleep(1000);
		addressAndPaymentPage.textFieldCreditcardExpiryDate().sendKeys(addressAndPaymentPage.creditCardExpiryDate());
		log.info("Enetered the Credit Card Expiry Date");
		driver.switchTo().parentFrame();
		driver.switchTo().frame("braintree-hosted-field-cvv");
		addressAndPaymentPage.textFieldCreditCardCvvNumber().sendKeys(addressAndPaymentPage.creditCardCvvNumber());
		log.info("Enetered the Credit Card CVV number");
		Thread.sleep(1000);
		driver.switchTo().parentFrame();
		cscGeneralPage.buttonSaveCreditCardInfo().click();
		Thread.sleep(1000);
		driver.switchTo().parentFrame();
		Thread.sleep(1000);
		cscGeneralPage.buttonNext().click();
		log.info("Clicked on the button 'Weiter'");
		Thread.sleep(1000);
		//Scrolling the Bestellen button into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cscGeneralPage.buttonPlaceOrder());
		Thread.sleep(500);
		cscGeneralPage.buttonPlaceOrder().click();
		log.info("Clicked on the button 'Bestellen'");
		Thread.sleep(4000);
		Assert.assertTrue(cscGeneralPage.textBoxOrderConfirmationMessage().getText().contains("Es wurde erfolgreich ein neuer Auftrag mit der Nummer:"));

		log.info("*** Finished Test: cscNewOrderUsingCC");
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

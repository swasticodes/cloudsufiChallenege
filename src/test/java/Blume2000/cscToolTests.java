package Blume2000;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.CSC_CustomerDetailsPage;
import pageObjects.CSC_CustomerRegistrationPage;
import pageObjects.CSC_HomePage;
import pageObjects.LoginPage;
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
		cscCustomerRegistration.textFieldStreet().sendKeys("test str");
		log.info("Enterd the Str. as 'test str'");
		cscCustomerRegistration.textFieldHouseNumber().sendKeys("12");
		log.info("Enterd the Nr. as '12'");
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

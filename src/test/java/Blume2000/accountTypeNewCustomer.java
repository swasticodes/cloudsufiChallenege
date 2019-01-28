package Blume2000;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegistrationPage;
import pageObjects.DeliveryPage;
import pageObjects.GeburtstagPage;
import pageObjects.GeneralPage;
import resources.BasicVariables;

public class accountTypeNewCustomer<inherits> extends BasicVariables {
	//Use the line below to enable logging in your test cases
	public static Logger log = LogManager.getLogger(BasicVariables.class.getName());
	
	@BeforeTest
	public void openBrowser() throws IOException
	{
		log.info("Driver is initializing");
		driver = initializeDriver();
		log.info("Opening the URL"+(prop.getProperty("URL")));
		driver.get(prop.getProperty("URL"));
	}
	@Test
	public void newCustomerPaypalPaymentMethodTest() throws InterruptedException 
	{
		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		GeburtstagPage birthdayPage = new GeburtstagPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		RegistrationPage registerationPage = new RegistrationPage(driver);
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		generalPage.closeCookieMessage().click();
		homePage.linkEvents().click();
		log.info("Clicked on the Anl‰sse link");
		homePage.linkBirthday().click();
		log.info("Clicked on the Geburtstag link");
		birthdayPage.linkBirthdayFirstItem().click();
		log.info("Selecting the first item on the birtday page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("22");
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("2");
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("97");
		log.info("Entered the delivery postal code 22297");
		deliveryPage.buttonNextPostalCode().click();
		log.info("Clicked the next button after entering Postal Code");
		deliveryPage.dayNextDay().click();
		log.info("Selecting the next day to active day for delivery");
		Thread.sleep(1000);
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Gruﬂkarte button");
		Thread.sleep(1000);
		deliveryPage.continueWithoutGreetingCard().click();
		log.info("Clicked on Weiter ohne Gruﬂkarte button");
		Thread.sleep(1000);
		deliveryPage.continueWithoutGifts().click();
		log.info("Clicked on Weiter ohne Geschenke button");
		generalPage.closeCookieMessage().click();
		log.info("Closed the cookie message at the bottom so that register button is visible");
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
		generalPage.closeCookieMessage().click();
		log.info("Closing the cookie message at the bottom so that salutauion field is visible");
		registerationPage.registrationSalutation().click();
		log.info("For registration selected salutation as Herr");
		registerationPage.registrationFirstName().sendKeys("TestFirst");
		log.info("For registeration entered first name as TestFirst");
		registerationPage.registrationLastName().sendKeys("TestLast");
		log.info("For registeration entered last name as TestLast");
		registerationPage.registrationStreet().sendKeys("‹berseering");
		log.info("For registeration entered street name as ‹berseering");
		registerationPage.registrationStreetNumber().sendKeys("33");
		log.info("For registeration entered street number as 33");
		registerationPage.registrationCity().sendKeys("Hamburg");
		log.info("For registeration entered city as Hamburg");
		//registerationPage.registrationSalutation().click();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-180)");
		registerationPage.copyDeliveryAndInvoiceAddress().click();
		log.info("Checking he checkbox so that delivery and invoice address are same");
		registerationPage.continueToOverview().click();
		
	}
	
	@AfterTest
	public void closeBrowser()
	{
		//driver.quit();
		//releasing the memory
		driver = null;
	}

}

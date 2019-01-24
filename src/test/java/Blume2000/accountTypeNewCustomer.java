package Blume2000;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePage;
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
	public void newCustomerPaypalPaymentMethodTest() 
	{
		//Creating the Objects below to access the functions
		HomePage homePage = new HomePage(driver);
		GeneralPage generalPage = new GeneralPage(driver);
		GeburtstagPage birthdayPage = new GeburtstagPage(driver);
		DeliveryPage deliveryPage = new DeliveryPage(driver);
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		homePage.linkEvents().click();
		log.info("Clicked on the Anl‰sse link");
		homePage.linkBirthday().click();
		log.info("Clicked on the Geburtstag link");
		birthdayPage.linkBirthdayFirstItem().click();
		log.info("Selecting the first item on the birtday page");
		generalPage.buttonSelectedItemNext().click();
		log.info("Clicked on the Next button after selecting item");
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("22");
		deliveryPage.textFieldDeliveryPostalCode().sendKeys("297");
		//deliveryPage.textFieldDeliveryPostalCode().sendKeys("22297");
		log.info("Entered the delivery postal code 22297");
		deliveryPage.buttonNextPostalCode().click();
		log.info("Clicked the next button after entering Postal Code");
		deliveryPage.dayNextDay().click();
		log.info("Selecting the next day to active day for delivery");
		deliveryPage.continueToGreetingCard().click();
		log.info("Clicked on Weiter zur Gruﬂkarte button");
		deliveryPage.continueWithoutGreetingCard().click();
		log.info("Clicked on Weiter ohne Gruﬂkarte button");
	}
	
	@AfterTest
	public void closeBrowser()
	{
		driver.quit();
		//releasing the memory
		driver = null;
	}

}

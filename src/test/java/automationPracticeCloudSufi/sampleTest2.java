package automationPracticeCloudSufi;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import resources.BasicVariables;
import pageObjects.HomePage;
import pageObjects.SignInPage;
import pageObjects.*;

public class sampleTest2<inherits> extends BasicVariables {
	//Use the line below to enable logging in your test cases
	public static Logger log = LogManager.getLogger(sampleTest2.class.getName());

	@BeforeMethod
	public void openBrowser() throws IOException
	{
		log.info("Driver is initializing");
		driver = initializeDriver();
		log.info("Opening the URL "+(prop.getProperty("URL")));
		driver.get(prop.getProperty("URLAutomationPractice"));
	}

	@Test(enabled=true)
	public void verifySignInButtonOnHomePage() throws InterruptedException
	{
		log.info("Started Test: verifySignInButtonOnHomePage");
		
		//Creating the Objects to access the functions
		HomePage homePage = new HomePage(driver);

		Assert.assertTrue(homePage.linkSignIn().isDisplayed());
		log.info("The Sign In Button is displayed");
		
		log.info("Finished Test: verifySignInButtonOnHomePage");
	}
	
	@Test(enabled=true)
	public void verifyVisibilityOfCreateAnAccountButton() throws InterruptedException
	{
		log.info("Started Test: verifyCreateAnAccountButtonEnableAndVisible");
		
		//Creating the Objects to access the functions
		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);

		homePage.linkSignIn().click();
		log.info("Clicked on Sign In button");
		Assert.assertTrue(signInPage.buttonCreatAnAccount().isDisplayed());
		log.info("Create An Account Button is Displayed");
		Assert.assertTrue(signInPage.buttonCreatAnAccount().isEnabled());
		log.info("Create An Account Button is Enabled");

		log.info("Finished Test: verifyVisibilityOfCreateAnAccountButton");
	}
	
	@Test(enabled=true)
	public void verifyUserAccountCreationPageUsingNewEmail() throws InterruptedException
	{
		log.info("Started Test: verifyUserAccountCreationPageUsingNewEmail");
		
		//Creating the Objects to access the functions
		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);
		CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage(driver);
		
		//Variables
		String emailID = "testuser";

		homePage.linkSignIn().click();
		log.info("Clicked on Sign In Button");
		emailID = emailID + RandomStringUtils.randomAlphanumeric(4) + "@testaura.com";
		log.info("Crated the id ---> " + emailID + " for registration");
		signInPage.textFieldEmailAddress().sendKeys(emailID);
		log.info("Enetred the email id ---> "+ emailID);
		signInPage.buttonCreatAnAccount().click();
		log.info("Clicked on the button Create An Account");
		Thread.sleep(5000);
		Assert.assertTrue(createAnAccountPage.textHeaderCreateAnAccount().getText().contains("CREATE AN ACCOUNT"));
		log.info("Verified Header of 'Create An Account' Page");	
		
		log.info("Finished Test: verifyUserAccountCreationPageUsingNewEmail");
	}
	
	@Test(enabled=true)
	public void verifyNewUserAccountCreation() throws InterruptedException
	{
		log.info("Started Test: verifyNewUserAccountCreation");
		
		//Creating the Objects to access the functions
		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);
		CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage(driver);
		
		//Variables
		String emailID = "testuser";

		homePage.linkSignIn().click();
		log.info("Clicked on Sign In Button");
		emailID = emailID + RandomStringUtils.randomAlphanumeric(4) + "@testaura.com";
		log.info("Crated the id ---> " + emailID + " for registration");
		signInPage.textFieldEmailAddress().sendKeys(emailID);
		log.info("Enetred the email id ---> "+ emailID);
		signInPage.buttonCreatAnAccount().click();
		log.info("Clicked on the button Create An Account");
		createAnAccountPage.radioBtnSalutationMr().click();
		log.info("Clicked on the radio button Mr.");
		createAnAccountPage.textFieldFirstName().sendKeys("testaurafirst");
		createAnAccountPage.textFieldLastName().sendKeys("testauralast");
		createAnAccountPage.textFieldPassword().sendKeys("testaura1234");
		createAnAccountPage.textFieldPassword().sendKeys(Keys.TAB);
		Thread.sleep(5000);
		Actions keyDown = new Actions(driver);
		keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN)).perform();
		Thread.sleep(5000);
		createAnAccountPage.textFieldPassword().sendKeys(Keys.TAB,Keys.TAB);
		keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN)).perform();
		Thread.sleep(5000);
		createAnAccountPage.textFieldPassword().sendKeys(Keys.TAB,Keys.TAB,Keys.TAB);
		Thread.sleep(2000);
		keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN)).perform();
		createAnAccountPage.textFieldAddress().sendKeys("testaddress");
		createAnAccountPage.textFieldCity().sendKeys("testCity");
		createAnAccountPage.textFieldCity().sendKeys(Keys.TAB);
		keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN)).perform();
		createAnAccountPage.textFieldPostalCode().sendKeys("50145");
		createAnAccountPage.textFieldPhoneMobile().sendKeys("8956565656");
		createAnAccountPage.buttonRegister().click();
		log.info("Submitted the registeration form after completion");
		Assert.assertTrue(createAnAccountPage.textAccountInfo().isDisplayed());	
		log.info("The account was successfully created");
		
		log.info("Finished Test: verifyNewUserAccountCreation");
	}
	
	@Test(enabled=true)
	public void verifyUserAccountCreationErrorMessageFirstName() throws InterruptedException
	{
		log.info("Started Test: verifyUserAccountCreationErrorMessageFirstName");
		
		//Creating the Objects to access the functions
		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);
		CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage(driver);
		
		//Variables
		String emailID = "testuser";

		homePage.linkSignIn().click();
		log.info("Clicked on Sign In Button");
		emailID = emailID + RandomStringUtils.randomAlphanumeric(4) + "@testaura.com";
		log.info("Crated the id ---> " + emailID + " for registration");
		signInPage.textFieldEmailAddress().sendKeys(emailID);
		log.info("Enetred the email id ---> "+ emailID);
		signInPage.buttonCreatAnAccount().click();
		log.info("Clicked on the button Create An Account");
		createAnAccountPage.radioBtnSalutationMr().click();
		log.info("Clicked on the radio button Mr.");
		//createAnAccountPage.textFieldFirstName().sendKeys("testaurafirst");
		createAnAccountPage.textFieldLastName().sendKeys("testauralast");
		createAnAccountPage.textFieldPassword().sendKeys("testaura1234");
		createAnAccountPage.textFieldPassword().sendKeys(Keys.TAB);
		Thread.sleep(5000);
		Actions keyDown = new Actions(driver);
		keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN)).perform();
		Thread.sleep(5000);
		createAnAccountPage.textFieldPassword().sendKeys(Keys.TAB,Keys.TAB);
		keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN)).perform();
		Thread.sleep(5000);
		createAnAccountPage.textFieldPassword().sendKeys(Keys.TAB,Keys.TAB,Keys.TAB);
		Thread.sleep(2000);
		keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN)).perform();
		createAnAccountPage.textFieldAddress().sendKeys("testaddress");
		createAnAccountPage.textFieldCity().sendKeys("testCity");
		createAnAccountPage.textFieldCity().sendKeys(Keys.TAB);
		keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN)).perform();
		createAnAccountPage.textFieldPostalCode().sendKeys("50145");
		createAnAccountPage.textFieldPhoneMobile().sendKeys("8956565656");
		createAnAccountPage.buttonRegister().click();
		log.info("Submitted the registeration form after completion");
		Assert.assertTrue(createAnAccountPage.textBoxErrorMsg().isDisplayed());	
		log.info("Account not created and an The error was displayed");
	
		log.info("Finished Test: verifyUserAccountCreationErrorMessageFirstName");
	}
	

	@AfterTest
	public void closeBrowser()
	{
		//releasing the memory
		driver.quit();
		driver = null;
		log.info("---------------Cleaned Up The Setup--------------");
	}

}

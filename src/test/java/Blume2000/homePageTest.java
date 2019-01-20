package Blume2000;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import resources.BasicVariables;

public class homePageTest<inherits> extends BasicVariables {
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
	public void registerationTest() 
	{
		//Creating the homePage Object below to access the functions from HomePage.java
		HomePage homePage = new HomePage(driver);
		homePage.linkLogin().click();
	}
	
	@AfterTest
	public void closeBrowser()
	{
		driver.quit();
	}

}

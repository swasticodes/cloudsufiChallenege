package Blume2000;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import resources.BasicVariables;

public class homePageTest<inherits> extends BasicVariables {
	@BeforeTest
	public void openBrowser() throws IOException
	{
		driver = initializeDriver();
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

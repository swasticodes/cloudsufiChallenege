package Blume2000;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import resources.BasicVariables;

public class loginPageTest<inherits> extends BasicVariables{
	@BeforeTest
	public void openBrowser() throws IOException
	{
		driver = initializeDriver();
		driver.get(prop.getProperty("URL"));
	}
	
	@Test(dataProvider="testData")
	public void loginTest(String uname, String pass)
	{
		//Creating the Objects below to access the functions from Page object files
		HomePage homePage = new HomePage(driver);
		LoginPage loginPage = new LoginPage(driver);
		homePage.linkLogin().click();
		Assert.assertEquals(loginPage.textExistingCustomer().getText(),"Ich bin bereits Kunde bei Blume2000.de");
		loginPage.textFieldEmail().sendKeys(uname);
		loginPage.textFieldPassword().sendKeys(pass);
	}
	
	@DataProvider
	public Object[][] testData()
	{
		//Row defines how many types of data needs to be passed
		//Column defines how many values per test case need to be executed
		//user1 data
		Object[][] data = new Object[2][2];
		data[0][0] = "test@test.com";
		data[0][1] = "testpassword";
		//user2 data
		data[1][0] = "test2@test.com";
		data[1][1] = "test2password";		
		return data;
	}
	@AfterTest
	public void closeBrowser()
	{
		driver.quit();
	}
}

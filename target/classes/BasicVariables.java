package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BasicVariables {
	public static WebDriver driver;
	public Properties prop;
	public WebDriver initializeDriver() throws IOException
	{
		 prop= new Properties();
		 //ToDo: Make the path mentioned below relative
		 FileInputStream fis=new FileInputStream("C:\\Users\\Comp-7\\eclipse-workspace\\Blume2000Automation\\src\\main\\java\\resources\\data.properties");
		 //Load the properties file
		 prop.load(fis);
		 String browserName=prop.getProperty("browser");
		 System.out.println("The browser used for the test is "+browserName);
		 String URL = prop.getProperty("URL");
		 if(browserName.equals("chrome"))
		 {
			//ToDo: Make the path mentioned below relative
		 	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\Comp-7\\Desktop\\Estimates\\Chrome Driver\\chromedriver.exe");
		 	 driver= new ChromeDriver();
		 		//execute in chrome driver
		 	
		 }
		 else if (browserName.equals("firefox"))
		 {
		 	 driver= new FirefoxDriver();
		 	//execute in firefox browser
		 }
		 else if (browserName.equals("IE"))
		 {
			 //IE code
		 }
		 else
		 {
			 //Print Message that invalid driver is selected
			 System.out.println("Invalid Browser Selected");
		 }
		 
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.manage().window().maximize();
		 return driver;	
	}
}

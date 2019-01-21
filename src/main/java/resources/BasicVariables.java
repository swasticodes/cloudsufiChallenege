package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
		 FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		 //Load the properties file
		 prop.load(fis);
		 String browserName=prop.getProperty("browser");
		 System.out.println("The browser used for the test is "+browserName);
		 String URL = prop.getProperty("URL");
		 if(browserName.equals("chrome"))
		 {
			//ToDo: Make the path mentioned below relative
		 	 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\chromedriver.exe");
		 	 driver = new ChromeDriver();
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
	
	public void takeScreenshot(String result) throws IOException
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//Make the path below relative
		FileUtils.copyFile(src, new File(("user.dir")+"\\src\\test\\Screenshots" + result + System.currentTimeMillis() + "_ss.png"));
	}
}

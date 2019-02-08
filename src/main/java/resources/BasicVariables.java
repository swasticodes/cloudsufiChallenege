package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BasicVariables {
	public static WebDriver driver;
	public Properties prop;
	public WebDriver initializeDriver() throws IOException
	{
		prop= new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		//Load the properties file
		prop.load(fis);
	
		String remoteHost = prop.getProperty("remote");
		if (remoteHost != null) {
			initializeRemoteWebDriver(remoteHost);
		}
		else
		{
			initializeDirectWebDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;
	}

	public WebDriver initializeRemoteWebDriver(String remoteHost) throws IOException
	{
		String browserName=prop.getProperty("browser");
		DesiredCapabilities capabilities = null;
		if(browserName.equals("chrome"))
		{
			capabilities = DesiredCapabilities.chrome();
		}
		else if (browserName.equals("firefox"))
		{
			capabilities = DesiredCapabilities.firefox();
		}
		else if (browserName.equals("IE"))
		{
			capabilities = DesiredCapabilities.internetExplorer();
		}
		else if (browserName.equals("edge"))
		{
			capabilities = DesiredCapabilities.edge();
		}
		else
		{
			//Print Message that invalid driver is selected
			System.out.println("Invalid Browser Selected");
		}
		
		try {
			driver = new RemoteWebDriver(new URL(remoteHost), capabilities);
		} catch(MalformedURLException ex) {
			throw new IOException("Could not open remote host");
		}
		return driver;
	}

	public WebDriver initializeDirectWebDriver() throws IOException
	{
		String browserName=prop.getProperty("browser");
		System.out.println("The browser used for the test is "+browserName);
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
			//execute in chrome driver
		}
		else if (browserName.equals("firefox"))
		{
			 System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\geckodriver.exe");
			 driver = new FirefoxDriver();
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
		
		return driver;	
	}

	public void takeScreenshot(String result) throws IOException
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//Make the path below relative
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\src\\test\\Screenshots\\"+ result + System.currentTimeMillis()+"_ss.png"));
	}
	
	public void ensurePageLoaded() {
		int secsToWait = 20;
		String initialWait = prop.getProperty("initialWaitInSecs");
		if (initialWait != null && !initialWait.isEmpty()) {
			try {
				secsToWait = Integer.parseInt("initialWait");
			} catch(NumberFormatException ex) {
				secsToWait = 20;
			}
		}
		
		driver.manage().timeouts().implicitlyWait(secsToWait, TimeUnit.SECONDS);
	}
}

package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

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
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
			capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		}
		else if (browserName.equals("firefox"))
		{
			capabilities = DesiredCapabilities.firefox();
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("devtools.console.stdout.content", true);
			capabilities.setCapability(FirefoxDriver.PROFILE, profile);
		}
		else if (browserName.equals("IE"))
		{
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("requireWindowFocus", true);
			capabilities.setCapability("ignoreZoomSetting", true);
		}
		else if (browserName.equals("edge"))
		{
			capabilities = DesiredCapabilities.edge();
		}
		else if (browserName.equals("mobile"))
		{
			capabilities = DesiredCapabilities.chrome();
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\chromedriver.exe");
			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", "Nexus 5");

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		}
		else if (browserName.equals("safari"))
		{
			capabilities = DesiredCapabilities.safari();			
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
		String MacNodeIp = prop.getProperty("NodeIP");
		DesiredCapabilities capabilities = null;
		System.out.println("The browser used for the test is "+browserName);
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\chromedriver.exe");
			capabilities = DesiredCapabilities.chrome();
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
			capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			driver = new ChromeDriver(capabilities);
			//execute in chrome driver
		}
		else if (browserName.equals("firefox"))
		{
			 System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\geckodriver.exe");
			 FirefoxProfile profile = new FirefoxProfile();
			 profile.setPreference("devtools.console.stdout.content", true);
			 driver = new FirefoxDriver(profile);
			//execute in firefox browser
		}
		else if (browserName.equals("IE"))
		{
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\main\\java\\resources\\IEDriverServer.exe");
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("requireWindowFocus", true);
			capabilities.setCapability("ignoreZoomSetting", true);
			driver = new InternetExplorerDriver(capabilities);
			//execute in IE browser
		}
		else if (browserName.equals("mobile"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\chromedriver.exe");
			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", "Nexus 5");

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

			driver = new ChromeDriver(chromeOptions);
			//execute in mobile browser
		}
		else if (browserName.equals("safari"))
		{
			capabilities = DesiredCapabilities.safari();
			driver = new RemoteWebDriver(new URL(MacNodeIp),capabilities);
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
	 public void logBrowserConsoleLogs() throws IOException {
		 prop= new Properties();
		 FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		 prop.load(fis);
		 String browserName=prop.getProperty("browser");
		 if (browserName.equals("chrome")) {
	     System.out.println("================== BROWSER LOGS =======================");
	     LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
	     for (LogEntry entry : logEntries) {
	    	 System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
	        }
	        System.out.println("===============END OF BROWSER LOGS======================");
		 	}
	    }
}

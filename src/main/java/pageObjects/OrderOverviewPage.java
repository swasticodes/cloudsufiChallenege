package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.BasicVariables;

public class OrderOverviewPage
{
	public WebDriver driver;
	BasicVariables bv = new BasicVariables();

	@FindBy (xpath="//*[contains(text(), 'Kaufen')]") WebElement btn_To_Buy;
	@FindBy (xpath="//*[@id=\"orderConfirm\"]") WebElement btn_Direct_To_Paypal;
	@FindBy (xpath="//*[contains(text(), 'Versandkosten')]") WebElement txtFld_Shipping_Cost;
	//Comment the above code and uncomment the code below in case of using Page Object model instead of Page Factory Model
	//By link_Login = By.linkText("Anmelden");

	public OrderOverviewPage(WebDriver driver)
	{
		this.driver = driver;
		//Comment the code below in case using Page object instead of Page Factory
		PageFactory.initElements(driver, this);
	}

	public WebElement buttonToBuy()
	{
		//Use the code below in case of using Page Factory Model
		try {
			bv.safariOrderConfirmLongWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return btn_To_Buy;
		//Comment the above code and Uncomment the code below in case using Page object instead of Page factory
		//return driver.findElement(link_Login);
	}

	public WebElement buttonDirectToPaypal()
	{
		try {
			bv.safariOrderConfirmLongWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return btn_Direct_To_Paypal;
	}
	public WebElement textFieldShippingCost()
	{
		try {
			bv.safariOrderConfirmLongWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return txtFld_Shipping_Cost;
	}
}

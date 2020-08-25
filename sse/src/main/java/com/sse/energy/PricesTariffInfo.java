package com.sse.energy;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sse.common.UtilClass;
import com.sse.energy.pricestariff.Prices;

public class PricesTariffInfo extends UtilClass {
	
    private static Logger Log = Logger.getLogger(PricesTariffInfo.class);
	private WebDriver driver;
	private WebDriverWait wait;
		
	@FindBy(id = "postcode")
    private WebElement inputPostcode;
	
	@FindBy(xpath = "//input[@type='button' and @value='View tariffs']")
    private WebElement btnViewtariffs;

	public PricesTariffInfo(WebDriver driver) 
	{
		Log.info("Prices and Tariff constructor is Invoked");
		this.driver = driver;
		wait = new WebDriverWait(driver,30);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.titleContains("Our Energy Prices"));
	}
	
	public PricesTariffInfo setPostcode(String postcode) throws Exception
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(inputPostcode));
			wait.until(ExpectedConditions.elementToBeClickable(inputPostcode));
			inputPostcode.clear();
			inputPostcode.sendKeys(postcode);
			Log.info("postcode is inputted -" + postcode);
		}
		catch(Exception e)
		{
			Log.error("Problem in setting the postcode."+e);
			throw new Exception("Problem in setting the postcode."+e);
		}
		return new PricesTariffInfo(driver);
	}
	
	public Prices clickViewtariffs() throws Exception
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(btnViewtariffs));
			wait.until(ExpectedConditions.elementToBeClickable(btnViewtariffs)).click();
			//waitForSpinner(driver);
			Log.info("View Tariff button is clicked");
		}
		catch(Exception e)
		{
			Log.error("Problem in clicking View Tariff button."+e);
			throw new Exception("Problem in clicking View Tariff button."+e);
		}
		return new Prices(driver);
	}
}
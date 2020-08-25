package com.sse.energy;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Energy {
	
    private static Logger Log = Logger.getLogger(Energy.class);
	private WebDriver driver;
	private WebDriverWait wait;
		
	@FindBy(xpath = "//div/a[@title='Energy']")
    private WebElement submenuHeaderEnergy;
	
	@FindBy(linkText = "Prices and tariff information")
    private WebElement submenuPricesandTariffInfo;
	
		
	public Energy(WebDriver driver) 
	{
		Log.info("Energy constructor is Invoked");
		this.driver = driver;
		wait = new WebDriverWait(driver,30);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.visibilityOf(submenuHeaderEnergy));
	}
	
	public PricesTariffInfo clickSubmenuPricesandTariffInfo() throws Exception 
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(submenuPricesandTariffInfo));
			wait.until(ExpectedConditions.elementToBeClickable(submenuPricesandTariffInfo)).click();
			Log.info("Submenu Prices and Tariff Information is Clicked");
		}
		catch(Exception e)
		{
			Log.error("Problem in clicking the Submenu Prices and Tariff Information."+e);
			throw new Exception("Problem in clicking the Submenu Prices and Tariff Information."+e);
		}
		return new PricesTariffInfo(driver);
	}
}
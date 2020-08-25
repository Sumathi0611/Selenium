package com.sse.energy.pricestariff;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.sse.common.UtilClass;

public class Prices extends UtilClass{

    private Logger Log = Logger.getLogger(Prices.class);
	private WebDriver driver;
	private WebDriverWait wait;
		
	@FindBy(id = "tariff-type-button")
    private WebElement dropdownTariffType;
	
	@FindBy(id = "tariff-availability-button")
    private WebElement dropdownTariffAvailability;

	@FindBy(id = "tariffs-button")
    private WebElement dropdownTariff;
	
	public Prices(WebDriver driver) 
	{
		Log.info("Prices constructor is Invoked");
		this.driver = driver;
		wait = new WebDriverWait(driver,20);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.titleIs("Our Prices - SSE"));
	}
	
	public Prices selectTariffType(String tarifftype) throws Exception
	{
		try
		{	
			sleepinSeconds(2);
			wait.until(ExpectedConditions.visibilityOf(dropdownTariffType));
			wait.until(ExpectedConditions.elementToBeClickable(dropdownTariffType)).click();
			
			By listTariffType=By.xpath("//ul[@id='tariff-type-menu']/li[text()='"+ tarifftype + "']");
			System.out.println(listTariffType);
			wait.until(ExpectedConditions.visibilityOfElementLocated(listTariffType));
			wait.until(ExpectedConditions.elementToBeClickable(listTariffType)).click();
			waitForSpinner(driver);
			
			Log.info("Tariff Type is selected - " + tarifftype);
		}
		catch(Exception e)
		{
			Log.error("Problem in selecting the Tariff Type - " + tarifftype + e);
			//throw new Exception("Problem in selecting the Tariff Type - " +tarifftype + e);
		}
		return new Prices(driver);
	}
	
	public Prices selectTariffAvailability(String tariffavailabilitytype) throws Exception
	{
		try
		{	
			wait.until(ExpectedConditions.visibilityOf(dropdownTariffAvailability));
			wait.until(ExpectedConditions.elementToBeClickable(dropdownTariffAvailability)).click();
			
			By listTariffavailable=By.xpath("//ul[@id='tariff-availability-menu']/li[text()='"+ tariffavailabilitytype + "']");
			wait.until(ExpectedConditions.visibilityOfElementLocated(listTariffavailable));
			wait.until(ExpectedConditions.elementToBeClickable(listTariffavailable)).click();
			waitForSpinner(driver);
			
			Log.info("Tariff Availability Type is selected - " + tariffavailabilitytype);
		}
		catch(Exception e)
		{
			Log.error("Problem in selecting the Tariff Availability type - " + tariffavailabilitytype + e);
			throw new Exception("Problem in selecting the Tariff Availability type - " + tariffavailabilitytype + e);
		}
		return new Prices(driver);
	}
	
	public Prices selectTariff(String tariff) throws Exception
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(dropdownTariff));
			wait.until(ExpectedConditions.elementToBeClickable(dropdownTariff)).click();
			
			By listTariff=By.xpath("//ul[@id='tariffs-menu']/li[text()='"+ tariff + "']");
			wait.until(ExpectedConditions.visibilityOfElementLocated(listTariff));
			wait.until(ExpectedConditions.elementToBeClickable(listTariff)).click();
			
			Log.info("Tariff is selected - " + tariff);
		}
		catch(Exception e)
		{
			Log.error("Problem in selecting the Tariff - " + tariff + e);
			throw new Exception("Problem in selecting the Tariff - "+ tariff + e);
		}
		return new Prices(driver);
	}
	
	public String getStandingCharge(String tariff, String option) throws Exception
	{
		try
		{
			By standcharge=By.xpath("//div/h3[text()='"+tariff+"']/following-sibling::table//th[text()='"+option+"']/following-sibling::td[2]");
			wait.until(ExpectedConditions.presenceOfElementLocated(standcharge));
			WebElement stdchrge=driver.findElement(standcharge);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", stdchrge);
			String standingcharge = stdchrge.getText();
			Log.info("Standing charge is " + standingcharge);
			By languageLocator=By.id("");
			driver.findElement(languageLocator);
			return standingcharge;
		}
		catch(Exception e)
		{
			Log.error("Problem in reading the Tariff Information - " + tariff + option + e);
			throw new Exception("Problem in reading the Tariff Information - "+ tariff + option + e);
		}
	}
}
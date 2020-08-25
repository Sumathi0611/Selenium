package com.sse;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.sse.about.About;
import com.sse.energy.Energy;
import com.sse.help.Help;
import com.sse.homeservices.HomeServices;
import com.sse.phoneandbroadband.PhoneandBroadband;
import com.sse.search.Search;
import com.sse.useraccount.UserAccount;

public class HomePage {
	
    private static Logger Log = Logger.getLogger(HomePage.class);
	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(xpath = ".//div[@class='cookieContainer']//span[@id='OkButton']")
    private WebElement btnCookieOk;
	
	@FindBy(xpath = "//li/a[@title='Energy']")
    private WebElement menuLinkEnergy;
	
	@FindBy(linkText = "Phone & Broadband")
    private WebElement menuLinkPhoneandBroadBand;
	
	@FindBy(linkText = "Home Services")
    private WebElement menuLinkHomeServices;
	
	@FindBy(linkText = "Help")
    private WebElement menuLinkHelp;

	@FindBy(linkText = "About")
    private WebElement menuLinkAbout;
	
	@FindBy(xpath = "//a[@title='SSE']")
    private WebElement menuIconHome;
	
	@FindBy(xpath = "//a[contains(@href,'search')]/img")
    private WebElement iconLinkSearch;
	
	@FindBy(xpath = "//a/img[@title='User Account']")
    private WebElement iconLinkUserAccount;
	
	public HomePage(WebDriver driver)
	{
		Log.info("Homepage constructor is Invoked");
		this.driver = driver;
		wait = new WebDriverWait(driver,30);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.titleContains("SSE"));
		wait.until(ExpectedConditions.visibilityOf(menuLinkEnergy));
		if(btnCookieOk.isDisplayed())
			btnCookieOk.click();
	}
	
	public Energy clickMenuEnergy() throws Exception 
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(menuLinkEnergy));
			wait.until(ExpectedConditions.elementToBeClickable(menuLinkEnergy)).click();
			Log.info("Menu Link Energy is Clicked");
		}
		catch(Exception e)
		{
			Log.error("Problem in clicking the Menu Link Energy."+e);
			throw new Exception("Problem in clicking the Menu Link Energy."+e);
		}
		return new Energy(driver);
	}
	
	public PhoneandBroadband clickMenuPhoneandBroadband() throws Exception 
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(menuLinkPhoneandBroadBand));
			wait.until(ExpectedConditions.elementToBeClickable(menuLinkPhoneandBroadBand)).click();
			Log.info("Menu Link Phone and Broadband is Clicked");
		}
		catch(Exception e)
		{
			Log.error("Problem in clicking the Menu Link Phone and Broadband."+e);
			throw new Exception("Problem in clicking the Menu Link Phone and Broadband."+e);
		}
		return new PhoneandBroadband(driver);
	}
	
	public HomeServices clickMenuHomeServices() throws Exception 
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(menuLinkHomeServices));
			wait.until(ExpectedConditions.elementToBeClickable(menuLinkHomeServices)).click();
			Log.info("Menu Link Home Services is Clicked");
		}
		catch(Exception e)
		{
			Log.error("Problem in clicking the Menu Link Home Services."+e);
			throw new Exception("Problem in clicking the Menu Link Home Services."+e);
		}
		return new HomeServices(driver);
	}
	
	public Help clickMenuHelp() throws Exception 
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(menuLinkHelp));
			wait.until(ExpectedConditions.elementToBeClickable(menuLinkHelp)).click();
			Log.info("Menu Link Help is Clicked");
		}
		catch(Exception e)
		{
			Log.error("Problem in clicking the Menu Link Help."+e);
			throw new Exception("Problem in clicking the Menu Link Help."+e);
		}
		return new Help(driver);
	}
	
	public About clickMenuAbout() throws Exception 
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(menuLinkAbout));
			wait.until(ExpectedConditions.elementToBeClickable(menuLinkAbout)).click();
			Log.info("Menu Link About is Clicked");
		}
		catch(Exception e)
		{
			Log.error("Problem in clicking the Menu Link About."+e);
			throw new Exception("Problem in clicking the Menu Link About."+e);
		}
		return new About(driver);
	}
	
	public HomePage clickIconHome() throws Exception
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(menuIconHome));
			wait.until(ExpectedConditions.elementToBeClickable(menuIconHome)).click();
			Log.info("Menu Icon Home is Clicked");
		}
		catch(Exception e)
		{
			Log.error("Problem in clicking the Menu Icon Home."+e);
			throw new Exception("Problem in clicking the Menu Icon Home."+e);
		}
		return new HomePage(driver);
	}
	
	public Search clickIconSearch() throws Exception
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(iconLinkSearch));
			wait.until(ExpectedConditions.elementToBeClickable(iconLinkSearch)).click();
			Log.info("Menu Icon Search is Clicked");
		}
		catch(Exception e)
		{
			Log.error("Problem in clicking the Menu Icon Search."+e);
			throw new Exception("Problem in clicking the Menu Icon Search."+e);
		}
		return new Search(driver);
	}
	
	public UserAccount clickIconUserAccount() throws Exception
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(iconLinkUserAccount));
			wait.until(ExpectedConditions.elementToBeClickable(iconLinkUserAccount)).click();
			Log.info("Menu Icon User Account is Clicked");
		}
		catch(Exception e)
		{
			Log.error("Problem in clicking the Menu Icon User Account."+e);
			throw new Exception("Problem in clicking the Menu Icon User Account."+e);
		}
		return new UserAccount(driver);
	}
}
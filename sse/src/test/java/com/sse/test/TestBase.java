package com.sse.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.sse.HomePage;
import com.sse.common.BaseProperties;
import com.sse.common.WebDriverListener;

public class TestBase extends BaseProperties
{
    private static Logger Log = Logger.getLogger(TestBase.class);
    private WebDriver basedriver;
    public WebDriver driver;
    protected HomePage homepage;
	
  @BeforeTest
  public void createDriver() throws IOException, InterruptedException {
	  readProperties();
	  if(browser.toLowerCase().contains("chrome"))
	  {
		  ChromeOptions options=new ChromeOptions();
		  options.addArguments("disable-infobars");
		  DesiredCapabilities cap=DesiredCapabilities.chrome();
		  cap.setCapability(ChromeOptions.CAPABILITY, options);
		  System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		  basedriver = new ChromeDriver(cap);
	  }
	  else
	  {
		  DesiredCapabilities cap=new DesiredCapabilities();
		  cap.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
		  cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		  basedriver = new FirefoxDriver(cap);
	  }
	  
	  EventFiringWebDriver efwd=new EventFiringWebDriver(basedriver);
	  WebDriverListener eventListener=new WebDriverListener(basedriver);
	  efwd.register(eventListener);
	  driver=efwd;
	  
	  driver.manage().window().maximize();
	  Log.info("Opening the Url - "+ url);
	  
	  driver.get(url);
	  driver.manage().deleteAllCookies();
	  
	  try{			
		     
	        File file = new File("C:\\Users\\senthil\\Desktop\\JMeter\\cookies.txt");							
	        FileReader fileReader = new FileReader(file);							
	        BufferedReader Buffreader = new BufferedReader(fileReader);							
	        String strline;			
	        while((strline=Buffreader.readLine())!=null){
	        String name1="", value="", domain="", path="";
	        Date expiry = new Date();
	        boolean isSecure=false;
	        	
	        String[] param=strline.split(";");
	        for(String s:param)
	        {
	        	name1 = s.split("=")[0];
        		value = s.split("=")[1];
        		break;
	        	/*Cookie ck = new Cookie(s.split("=")[0],s.split("=")[1]);			
		        System.out.println(ck);
		        driver.manage().addCookie(ck);*/
	        	/*if(s.startsWith(" expires"))
	        	 {
	        		//System.out.println(s.split("=")[1]);
	        		expiry = new Date();
	        	 }
	        	if(s.startsWith(" path"))
	        	 {
	        		 path = s.split("=")[1];
	        	 }
	        	else if(s.startsWith(" domain"))
	        	 {
	        		 domain = s.split("=")[1];
	        	 }
	        	else if(s.startsWith("secure"))
	        	 {
	        		 isSecure = true;
	        	 }
	        	else
	        	{
	        		name1 = s.split("=")[0];
	        		value = s.split("=")[1];
	        	}*/
	        }  	
	        Cookie ck = new Cookie(name1, value);
	        System.out.println(ck);
	        driver.manage().addCookie(ck);
	        }
	        fileReader.close();
	        Buffreader.close();
	        
	        
	        File f=new File("C:\\Users\\senthil\\Desktop\\JMeter\\cookies1.txt");
			f.createNewFile();
			Path cookiesFile = Paths.get("C:\\Users\\senthil\\Desktop\\JMeter\\cookies1.txt");
	
			// save the cookies to a file for the current domain
			try (PrintWriter file1 = new PrintWriter(cookiesFile.toFile(), "UTF-8")) {
			    for (Cookie c : driver.manage().getCookies()) {
			        file1.println(c.toString());
			    }
			}
			
			Cookie name = new Cookie("myCookie", "abcde-erfr-4redfr4-4rt5");
	        driver.manage().addCookie(name);
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
  
  public WebDriver getDriver()
  {
	  return driver;
  }
  
  @AfterTest()
  public void quitDriver() {
	  if(driver!=null)
	  {
		  try
		  {
			  driver.close();
		  }
		  catch(WebDriverException e)
		  {
			  System.out.println("**********CAUGHT EXCEPTION IN DRIVER TEAR DOWN**********");
			  System.out.println(e);
		  }
	  }
  }
}
  
package com.sse.test.energy;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;
import com.sse.test.TestBase;

public class PricesTariffTest2 extends TestBase {
	
	
	@Test
	public void standingCharge() throws Exception {
		File f=new File("C:\\Users\\senthil\\Desktop\\JMeter\\cookies2.txt");
		f.createNewFile();
		Path cookiesFile = Paths.get("C:\\Users\\senthil\\Desktop\\JMeter\\cookies2.txt");

		// save the cookies to a file for the current domain
		try (PrintWriter file = new PrintWriter(cookiesFile.toFile(), "UTF-8")) {
		    for (Cookie c : driver.manage().getCookies()) {
		        file.println(c.toString());
		    }
		}
	}
}
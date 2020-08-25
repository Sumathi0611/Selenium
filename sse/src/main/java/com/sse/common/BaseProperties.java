package com.sse.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.testng.annotations.Listeners;

@Listeners(ListenerTest.class)
public class BaseProperties extends UtilClass {
	
	public String browser, url;
	
	public void readProperties() 
	{
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("src/main/resources/config.properties");
			prop.load(input);
			browser=prop.getProperty("browsername");
			url=prop.getProperty("url");
			System.out.println(prop.getProperty("browsername"));
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
		}
	}
}
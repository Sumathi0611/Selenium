package com.sse.test.energy;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.sse.HomePage;
import com.sse.common.UtilClass;
import com.sse.test.TestBase;

public class PricesTariffTest extends TestBase {
	
	@DataProvider
    public Object[][] readTestdata() throws Exception{
         Object[][] testObjArray = UtilClass.getTableArray(System.getProperty("user.dir")+"\\src\\test\\resources\\Testdata\\TestData.xlsx","Energy-standingcharge");
         return (testObjArray);
	}
	
	
	@Test(dataProvider="readTestdata")
	public void standingCharge(String postcode, String tarifftype, 
					String availability, String tariff, String option, String standingcharge) throws Exception {
		homepage=new HomePage(driver);
		String actualValue = homepage.clickMenuEnergy().clickSubmenuPricesandTariffInfo().setPostcode(postcode).clickViewtariffs()
				.selectTariffType(tarifftype).selectTariffAvailability(availability)
				.selectTariff(tariff).getStandingCharge(tariff, option);
		assertTrue(standingcharge.equals(actualValue),"Value mismatch - Expected = "+standingcharge+" but actual value is "+actualValue);
	}
}
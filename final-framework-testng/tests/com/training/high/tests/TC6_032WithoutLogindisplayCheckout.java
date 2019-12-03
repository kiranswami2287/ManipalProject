package com.training.high.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.HomePOM;
import com.training.pom.ProductDetailPOM;
import com.training.pom.ShoppingCartDetailsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC6_032WithoutLogindisplayCheckout
{
	//Declare all variables and objects
		private WebDriver driver;
		private String baseUrl;
		private HomePOM homePOM;
		private ProductDetailPOM productDetailPOM;
		private ShoppingCartDetailsPOM shoppingCartDetailsPOM;
		private static Properties properties;
		private ScreenShot screenShot;

		@BeforeClass
		public static void setUpBeforeClass() throws IOException 
		{
			//initialize properties file
			properties = new Properties();
			//Read properties file from given path
			FileInputStream inStream = new FileInputStream("./resources/others.properties");
			//load data from properties file
			properties.load(inStream);
		}

		@BeforeMethod
		public void setUp() throws Exception
		{
			//initialize driver
			driver = DriverFactory.getDriver(DriverNames.CHROME);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			//initialize POM files
			homePOM= new HomePOM(driver);
			productDetailPOM=new ProductDetailPOM(driver);
			shoppingCartDetailsPOM=new ShoppingCartDetailsPOM(driver);
			screenShot = new ScreenShot(driver); 
			//call URL from properties file
			baseUrl = properties.getProperty("baseURL");
			// open the browser 
			driver.get(baseUrl);
		}
		
		@AfterMethod
		public void tearDown() throws Exception 
		{
			Thread.sleep(1000);
			// capture screenshot
			screenShot.captureScreenShot("TC2");
			//close all opened windows
			driver.quit();
		}
		
		@Test
		public void withoutLogin()
		{
			//select premium school uniform
			homePOM.premiumSchoolUniform();
			//select Rust color T-shirt
			homePOM.selectRustTshirt();
			//add to cart
			productDetailPOM.addToCartRustTshirt();
			//click on cart icon
			productDetailPOM.clickCartIcon();
			//click on view cart
			productDetailPOM.clickViewCart();
			//click on checkout button
			shoppingCartDetailsPOM.clickCheckoutBtn();
			
		}

}

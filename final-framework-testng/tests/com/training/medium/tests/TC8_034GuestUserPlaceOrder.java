package com.training.medium.tests;

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
import com.training.pom.CheckoutPOM;
import com.training.pom.HomePOM;
import com.training.pom.ProductDetailPOM;
import com.training.pom.ShoppingCartDetailsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC8_034GuestUserPlaceOrder 
{

	//Declare all variables and objects
			private WebDriver driver;
			private String baseUrl;
			private String firstName;
			private String lastName;
			private String email;
			private String telephone;
			private String address1;
			private String city;
			private String postcode;
			private HomePOM homePOM;
			private ProductDetailPOM productDetailPOM;
			private ShoppingCartDetailsPOM shoppingCartDetailsPOM;
			private CheckoutPOM checkoutPOM;
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
				checkoutPOM=new CheckoutPOM(driver);
				screenShot = new ScreenShot(driver); 
				//call URL from properties file
				baseUrl = properties.getProperty("baseURL");
				firstName = properties.getProperty("firstname");
				lastName = properties.getProperty("lastname");
				email = properties.getProperty("email");
				telephone = properties.getProperty("telephone");
				address1 = properties.getProperty("address1");
				city = properties.getProperty("city");
				postcode = properties.getProperty("postcode");
				// open the browser 
				driver.get(baseUrl);
			}
			
			@AfterMethod
			public void tearDown() throws Exception 
			{
				Thread.sleep(1000);
				// capture screenshot
				screenShot.captureScreenShot("TC8");
				//close all opened windows
				driver.quit();
			}
			
			@Test
			public void withoutLogin() throws InterruptedException
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
				//send customer details
				checkoutPOM.sendBillingDetails(firstName, lastName, email, telephone,  address1, city, postcode);
				//continue on delivery method
				checkoutPOM.deliveryMethod();
				//continue on payment method
				checkoutPOM.paymentMethod();
				//confirm order
				checkoutPOM.confirmOrder();
				
			}
}



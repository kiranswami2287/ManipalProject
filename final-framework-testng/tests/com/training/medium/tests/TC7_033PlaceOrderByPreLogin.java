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
import com.training.pom.LoginPOM;
import com.training.pom.MyAccountPOM;
import com.training.pom.ProductDetailPOM;
import com.training.pom.ShoppingCartDetailsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC7_033PlaceOrderByPreLogin 
{
	//Declare all variables and objects
			private WebDriver driver;
			private String baseUrl;
			private String username;
			private String password;
			private HomePOM homePOM;
			private LoginPOM loginPOM;
			private MyAccountPOM myAccountPOM;
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
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				//initialize POM files
				homePOM= new HomePOM(driver);
				loginPOM=new LoginPOM(driver);
				myAccountPOM=new MyAccountPOM(driver);
				productDetailPOM=new ProductDetailPOM(driver);
				shoppingCartDetailsPOM=new ShoppingCartDetailsPOM(driver);
				checkoutPOM=new CheckoutPOM(driver);
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
				screenShot.captureScreenShot("TC7");
				//close all opened windows
				driver.quit();
			}
			
			@Test
			public void placeOrderByLogin()
			{
								
				//Move mouse over to My Account
				homePOM.selectMyAccount();
				
				//select Login option from My Account
				homePOM.myAccountLogin();
				//read username from property file
				username=properties.getProperty("username");
				//read password from property file
				password=properties.getProperty("password");
				
				//login to Application
				loginPOM.sendLoginDetails(username,password);
				
				//validate login
				loginPOM.loginValidate();
				//back to home page
				myAccountPOM.uniformStoreHomePage();
				
				// select premium school uniform
				homePOM.premiumSchoolUniform();
				//select Maroon color Regular t-shirt
				homePOM.selectMaroonTshirt();
				//add to cart
				productDetailPOM.addToCartMaroonTshirt();
				//click on cart icon
				productDetailPOM.MouseOverToCartIcon();
				//click on view cart
				
				productDetailPOM.clickViewCart();
				//click on checkout button
				shoppingCartDetailsPOM.clickCheckoutBtn(); 
				// continue on billing details
				checkoutPOM.billingDetailContinue();
				// continue on delivery details
				checkoutPOM.deliveryDetailContinue();
				//continue on delivery method 
				checkoutPOM.deliveryMethod();
				// continue on payment method
				checkoutPOM.paymentMethod();
				//confirm order
				checkoutPOM.confirmOrder();
			
			}
}

	


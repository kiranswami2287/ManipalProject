//packages
package com.training.simple.tests;

//import classes & Interfaces
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.BrandDetailsPOM;
import com.training.pom.HomePOM;
import com.training.pom.LoginPOM;
import com.training.pom.MyAccountPOM;
import com.training.pom.ProductDetailPOM;
import com.training.pom.UniformMakerPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC4_DisplayOrderHistory
{
	
	//Declare all variables and objects
	private WebDriver driver;
	private String baseUrl;
	private String userName;
	private String password;
	private LoginPOM loginPOM;
	private HomePOM homePOM;
	private MyAccountPOM myAccountPOM;
	private BrandDetailsPOM brandDetailsPOM;
	private UniformMakerPOM uniformMakerPOM;
	private ProductDetailPOM productDetailPOM;
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
		loginPOM = new LoginPOM(driver); 
		homePOM= new HomePOM(driver);
		myAccountPOM=new MyAccountPOM(driver);
		brandDetailsPOM=new BrandDetailsPOM(driver);
		uniformMakerPOM=new UniformMakerPOM(driver);
		productDetailPOM=new ProductDetailPOM(driver);
		screenShot = new ScreenShot(driver); 
		
		//call URL from properties file
		baseUrl = properties.getProperty("baseURL");
		
		// open the browser 
		driver.get(baseUrl);
		//Move mouse over to My Account
		homePOM.selectMyAccount();
			
		//select Login option from My Account
		homePOM.myAccountLogin();
			
		//read username from property file
		userName=properties.getProperty("username");
		//read password from property file
		password=properties.getProperty("password");
		//login to Application
		loginPOM.sendLoginDetails(userName,password);
			
		//validate login
		loginPOM.loginValidate();
				
		
	}
	
	@AfterClass
	public void tearDown() throws Exception
	{
		Thread.sleep(1000);
		// capture screenshot
		screenShot.captureScreenShot("TC4");
		//close all opened windows
		driver.quit();
	}
	
	
	@Test
	public void orderHistoryDisplay() throws InterruptedException
	{
	//select order History option from my Account
		myAccountPOM.myAccountOrderHistory();	
		
		//Validating landing to Order History page or not
		myAccountPOM.orderHistoryValidate();
		
	}
}


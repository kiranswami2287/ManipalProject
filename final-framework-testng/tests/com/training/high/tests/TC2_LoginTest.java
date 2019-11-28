//packages
package com.training.high.tests;
//import classes & Interfaces
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
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC2_LoginTest
{
	//Declare all variables and objects
	private WebDriver driver;
	private String baseUrl;
	private String username;
	private String password;
	private LoginPOM loginPOM;
	private HomePOM homePOM;
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
	public void validLoginTest()
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
		
	}
}

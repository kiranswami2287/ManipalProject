//packages
package com.training.high.tests;
//import classes & Interfaces
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
		
		//login to Application
		loginPOM.sendLoginDetails("kiran@yahoo.com","jun22jun");
		
		//validate login
		loginPOM.loginValidate();
		
	}
}

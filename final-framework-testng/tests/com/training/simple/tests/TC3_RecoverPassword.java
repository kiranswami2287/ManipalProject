//packages
package com.training.simple.tests;
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
import com.training.pom.RecoverPasswordPOM;
import com.training.pom.HomePOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC3_RecoverPassword 
{
	//Declare all variables and objects
	private WebDriver driver;
	private String baseUrl;
	private String validEmail;
	private String invalidEmail;
	private String invalidPassword;
	private LoginPOM loginPOM;
	private HomePOM homePOM;
	private RecoverPasswordPOM recoverPasswordPOM;
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
		recoverPasswordPOM=new RecoverPasswordPOM(driver);
		//call URL from properties file
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception 
	{
		Thread.sleep(1000);
		// capture screenshot
		screenShot.captureScreenShot("TC3");
		//close all opened windows
		driver.quit();
	}
	
	@Test
	public void forgotPasswordTest() throws InterruptedException
	{
		//Move mouse over to My Account
		homePOM.selectMyAccount();
		
		//select Login option from My Account
		homePOM.myAccountLogin();
		
		invalidEmail=properties.getProperty("invalidemail");
		invalidPassword=properties.getProperty("invalidpassword");
		//login to Application
		loginPOM.sendLoginDetails(invalidEmail,invalidPassword);
		
		//verify invalid login
		loginPOM.invalidLogin();
		
		
		loginPOM.forgotPWLink();
		
		validEmail=properties.getProperty("username");
		//Recover Password details
		recoverPasswordPOM.sendForgotPasswordDetails(validEmail);
		
		//validate email and password
		recoverPasswordPOM.forgotPasswordValidate();
		
	}
}




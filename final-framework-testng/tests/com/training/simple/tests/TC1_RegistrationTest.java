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

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.RegistrationPOM;
import com.training.pom.HomePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

	public class TC1_RegistrationTest
	{
		//Declare all variables and objects
		private WebDriver driver;
		private String baseUrl;
		private String filename;
		private RegistrationPOM registrationPOM;
		private HomePOM homePOM;
		private LoginDataProviders loginDataProviders;
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
			registrationPOM=new RegistrationPOM(driver);
			//call URL from properties file
			baseUrl = properties.getProperty("baseURL");
			loginDataProviders=new LoginDataProviders();
			filename=properties.getProperty("registrationexcelpath");
			
			screenShot = new ScreenShot(driver); 
			// open the browser 
			driver.get(baseUrl);
		}
		
		@AfterMethod
		public void tearDown() throws Exception 
		{
			Thread.sleep(1000);
			// capture screenshot
			screenShot.captureScreenShot("TC1");
			//close all opened windows
			driver.quit();
		}
		
		
		
		
		
		@Test(dataProvider = "excel-inputs", dataProviderClass = LoginDataProviders.class)
		public void validRegistrationTest(String firstName, String lastName, String email, String telephone, String address1, String city, String postcode, String country,String state,String password, String confirmPassword) throws InterruptedException, IOException 
		{
			
			
			//Move mouse over to My Account
			homePOM.selectMyAccount();
			
			//select Register option from My Account
			homePOM.myAccountRegister();
				
			//Register new user
			registrationPOM.validRegistrationDetails(firstName, lastName, email, telephone, address1, city, postcode, country, state, password, confirmPassword);
		}
	}		
	

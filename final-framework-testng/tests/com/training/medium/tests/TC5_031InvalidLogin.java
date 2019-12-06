package com.training.medium.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.AccountCreatedSuccessPOM;
import com.training.pom.HomePOM;
import com.training.pom.LoginPOM;
import com.training.pom.MyAccountPOM;
import com.training.pom.RegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC5_031InvalidLogin 
{
	//Declare all variables and objects
			private  static WebDriver driver;
			private  static String baseUrl;
			private  static LoginPOM loginPOM;
			private  static RegistrationPOM registrationPOM;
			private  static HomePOM homePOM;
			private  static MyAccountPOM myAccountPOM;
			private static AccountCreatedSuccessPOM accountCreatedSuccessPOM;
			private  static Properties properties;
			private  static ScreenShot screenShot;

			@BeforeClass
			public static void setUpBeforeClass() throws IOException 
			{
				//initialize properties file
				properties = new Properties();
				//Read properties file from given path
				FileInputStream inStream = new FileInputStream("./resources/others.properties");
				//load data from properties file
				properties.load(inStream);
		
				//initialize driver
				driver = DriverFactory.getDriver(DriverNames.CHROME);
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				//initialize POM files
				homePOM= new HomePOM(driver);
				loginPOM=new LoginPOM(driver);
				registrationPOM=new RegistrationPOM(driver);
				accountCreatedSuccessPOM=new AccountCreatedSuccessPOM(driver);
				//call URL from properties file
				baseUrl = properties.getProperty("baseURL");
				screenShot = new ScreenShot(driver); 
				// open the browser 
				driver.get(baseUrl);
			}
			
			@AfterClass
			public void tearDown() throws Exception 
			{
				Thread.sleep(1000);
				// capture screenshot
				screenShot.captureScreenShot("TC5");
				//close all opened windows
				driver.quit();
			}
			
			
			@Test(dataProvider = "excel-inputs", dataProviderClass = LoginDataProviders.class,priority=0)
			public void validRegistrationTest(String firstName, String lastName, String email, String telephone, String address1, String city, String postcode,String country,String state, String password, String confirmPassword) throws InterruptedException 
			{
				//Move mouse over to My Account
				homePOM.selectMyAccount();
				
				//select Register option from My Account
				homePOM.myAccountRegister();
						
				//Register new user
				registrationPOM.validRegistrationDetails(firstName,lastName,email,telephone,address1,city,postcode,country,state,password,confirmPassword);
								
						
				accountCreatedSuccessPOM.logout();
			
			 }	
			

			@Test(dataProvider="inputs",dataProviderClass = LoginDataProviders.class,priority=1)
			public void invalidLogin1(String userName, String password ) throws InterruptedException
			{
				//Move mouse over to My Account
				homePOM.selectMyAccount();
				
				//select Login option from My Account
				homePOM.myAccountLogin();
				
				//login to Application
				loginPOM.sendLoginDetails(userName,password);
				
				//verify invalid login
				loginPOM.invalidLogin();
			
			}		

}

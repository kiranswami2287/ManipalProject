package com.training.complex.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.AdminLoginPOM;
import com.training.pom.CatagoriesPOM;
import com.training.pom.CustomerPOM;
import com.training.pom.DashboardPOM;
import com.training.pom.HomePOM;
import com.training.pom.RegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC9_061AddCustomer
{
	private static WebDriver driver;
	private static String adminUrl;
	private static String username;
	private static String password;
	private static AdminLoginPOM adminLoginPOM;
	private static DashboardPOM dashboardPOM;
	private static CustomerPOM customerPOM;
	private static Properties properties;
	private static ScreenShot screenShot;

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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//initialize POM files
		adminLoginPOM = new AdminLoginPOM(driver); 
		dashboardPOM=new DashboardPOM(driver);
		customerPOM=new CustomerPOM(driver);
		screenShot = new ScreenShot(driver); 
		//call URL from properties file
		adminUrl = properties.getProperty("adminURL");
		// open the browser 
		driver.get(adminUrl);
		//read username from property file
		username=properties.getProperty("adminUsername");
		//read password from property file
		password=properties.getProperty("adminPassword");
		adminLoginPOM.adminLogin(username, password);
		adminLoginPOM.adminLoginValidate();
		dashboardPOM.clickMenuBtn();
		dashboardPOM.clickCustomerTab();
		dashboardPOM.clickCustomerOption();
	}
	
	@AfterClass
	public void tearDown() throws Exception 
	{
		Thread.sleep(1000);
		// capture screenshot
		screenShot.captureScreenShot("TC09");
		//close all opened windows
		driver.quit();
	}
	
	@Test(dataProvider = "excel-inputs", dataProviderClass = LoginDataProviders.class)
	public void addCatagory(String firstName,String lastName,String email,String telephone,String password,String confirmPassword)
	{
		
		customerPOM.addCustomer(firstName, lastName, email, telephone,password,confirmPassword);
		
	}

}
package com.training.high.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.RegistrationPOM;
import com.training.pom.HomePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

	public class TC1_RegistrationTest {

		private WebDriver driver;
		private String baseUrl;
		private LoginPOM loginPOM;
		private RegistrationPOM registrationPOM;
		private HomePOM homePOM;
		private static Properties properties;
		private ScreenShot screenShot;

		@BeforeClass
		public static void setUpBeforeClass() throws IOException {
			properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/others.properties");
			properties.load(inStream);
		}

		@BeforeMethod
		public void setUp() throws Exception {
			driver = DriverFactory.getDriver(DriverNames.CHROME);
			//loginPOM = new LoginPOM(driver); 
			homePOM= new HomePOM(driver);
			registrationPOM=new RegistrationPOM(driver);
			baseUrl = properties.getProperty("baseURL");
			screenShot = new ScreenShot(driver); 
			// open the browser 
			driver.get(baseUrl);
		}
		
		@AfterMethod
		public void tearDown() throws Exception {
			Thread.sleep(1000);
			screenShot.captureScreenShot("First");
			driver.quit();
		}
		
		
		@Test
		public void validRegistrationTest() throws InterruptedException 
		{
			homePOM.myAccountRegister("Register");
			
			registrationPOM.sendRegistrationDetails("Pradnya","B","pradnya2@gmail.com","9241835892","Jayanagar","Bangalore","560082","Neha123","Neha123");
							
			registrationPOM.clickContinueBtn();		
			
			registrationPOM.registrationValidate();
		}
	}		
	


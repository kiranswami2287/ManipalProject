package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AdminLoginPOM {

	//Declare webdriver
		private WebDriver driver; 
		
		//define constructor
		public AdminLoginPOM(WebDriver driver) 
		{
			this.driver = driver; 
			PageFactory.initElements(driver, this);
		}
		
		//find Webelements by locators
		@FindBy(id="input-username")
		private WebElement userName; 
		
		@FindBy(id="input-password")
		private WebElement password;
		
		@FindBy(xpath="//button[@class='btn btn-primary']")
		private WebElement loginBtn; 
		
		@FindBy(xpath="//h1[contains(text(),'Dashboard')]")
		private WebElement actualMessage; 
		
		
		public void adminLogin(String userName, String password)
		{	
			//clear username field
			this.userName.clear();
			//send username
			this.userName.sendKeys(userName);
			//clear password field
			this.password.clear(); 
			//send password
			this.password.sendKeys(password); 
			//click on login button
			this.loginBtn.click(); 
			
		}	
		
		
		
		public void adminLoginValidate()
		{
			//Validate login
			String expectedMessage = "Dashboard";
			String actualMessage=this.actualMessage.getText();
			Assert.assertEquals(expectedMessage,actualMessage);
		}
		
			
}



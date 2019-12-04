package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CustomerPOM 
{

	//Declare webdriver
		private WebDriver driver; 
		
		//define constructor
		public CustomerPOM(WebDriver driver) 
		{
			this.driver = driver; 
			PageFactory.initElements(driver, this);
		}
		
		//find Webelements by locators
		@FindBy(xpath="//i[@class='fa fa-plus']")
		private WebElement addNewIcon; 
		
		@FindBy(xpath="//input[@id='input-firstname']")
		private WebElement firstName;
		
		@FindBy(xpath="//input[@id='input-lastname']")
		private WebElement lastName; 
		
		@FindBy(xpath="//input[@id='input-email']")
		private WebElement email;
		
		@FindBy(xpath="//input[@id='input-telephone']")
		private WebElement telephone; 
		
		@FindBy(xpath="//input[@id='input-password']")
		private WebElement password; 
		
		@FindBy(xpath="//input[@id='input-confirm']")
		private WebElement confirmPassword; 
		
		@FindBy(xpath="//button[@class='btn btn-primary']")
		private WebElement saveBtn; 
		
				
		@FindBy(xpath="//div[@class='alert alert-success']")
		private WebElement alertSuccessMessage; 
		
		public void addCustomer(String firstName,String lastName,String email,String telephone,String password,String confirmPassword)
		{	
			this.addNewIcon.click();
			this.firstName.clear();
			this.firstName.sendKeys(firstName);
			this.lastName.clear();
			this.lastName.sendKeys(lastName);
			this.email.clear();
			this.email.sendKeys(email);
			this.telephone.clear();
			this.telephone.sendKeys(telephone);
			this.password.clear();
			this.password.sendKeys(password);
			this.confirmPassword.clear();
			this.confirmPassword.sendKeys(confirmPassword);
			
			
			this.saveBtn.click();
			
			String expectedMessage = "Success: You have modified customers!\r\n" + 
					"×";
			String actualMessage=this.alertSuccessMessage.getText();
			Assert.assertTrue((expectedMessage!=actualMessage), "customer not added");
			
	   }

}

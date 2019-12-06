package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreatedSuccessPOM 
{
	//Declare webdriver
		private WebDriver driver;
		
		//define constructor
		public AccountCreatedSuccessPOM(WebDriver driver)
		{
			this.driver = driver; 
			PageFactory.initElements(driver, this);
		}
		
		//find Webelements by locators
		@FindBy(xpath="//*[@id=\"top-links1\"]/ul/li[3]/a")
		private WebElement myAccount; 
		
		@FindBy(xpath="//a[@class='btn btn-primary']")
		private WebElement continueBtn; 
		
		
		@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right myaccount-menu']//a[contains(text(),'Logout')]")
		private WebElement logout; 
			
		
		public void clickCountinue()
		{
			this.continueBtn.click();
		}
		
		public void logout() throws InterruptedException
		{
		
			Actions action=new Actions(driver);
			action.moveToElement(myAccount).build().perform();
			this.myAccount.click();
			this.logout.click();
		}

}

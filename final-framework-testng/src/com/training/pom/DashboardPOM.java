package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class DashboardPOM
{	
			//Declare webdriver
			private WebDriver driver; 
			
			//define constructor
			public DashboardPOM(WebDriver driver) 
			{
				this.driver = driver; 
				PageFactory.initElements(driver, this);
			}
			
			//find Webelements by locators
			@FindBy(xpath="//span[contains(text(),'Catalog')]")
			private WebElement catalogTab; 
			
			@FindBy(xpath="//a[contains(text(),'Categories')]")
			private WebElement catagoriesTab;
			
			@FindBy(xpath="//button[@class='btn btn-primary']")
			private WebElement loginBtn; 
			
			@FindBy(xpath="//h1[contains(text(),'Dashboard')]")
			private WebElement actualMessage; 
			
			@FindBy(xpath="//a[@id='button-menu']")
			private WebElement menuBtn; 
			
			@FindBy(xpath="//li[@id='customer']//a[@class='parent']")
			private WebElement customerTab; 
			
			@FindBy(xpath="//ul[@class='collapse in']//a[contains(text(),'Customers')]")
			private WebElement customerOption; 
			
			
			public void clickMenuBtn()
			{
				this.menuBtn.click();
			}
			
			public void clickCatalog()
			{	
				catalogTab.click();
				
			}	
			
			public void clickCatagories()
			{	
				catagoriesTab.click();
				
			}	
			
			
			public void clickCustomerTab()
			{	
				customerTab.click();
				
			}	
			
			public void clickCustomerOption()
			{	
				customerOption.click();
				
			}	
				
}


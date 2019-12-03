package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ShoppingCartDetailsPOM 
{
	//Declare webdriver
		private WebDriver driver; 
		private WebElement expicitWait2;
		
		//define constructor
		public ShoppingCartDetailsPOM(WebDriver driver)
		{
			this.driver = driver; 
			PageFactory.initElements(driver, this);
		}

		//find Webelements by locators
		@FindBy(xpath="//a[@class='btn btn-primary']")
		private WebElement checkoutBtn;
		
		@FindBy(xpath="//h1[contains(text(),'Checkout')]")
		private WebElement validateCheckoutPage;
		
		
		public void clickCheckoutBtn()
		{
			
			checkoutBtn.click();
			
			String expectedMessage = "CHECKOUT";
			String actualMessage=this.validateCheckoutPage.getText();
			
			Assert.assertTrue((expectedMessage!=actualMessage), "Page for checkout not displayed");
			
		}
		
		
		
}




package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductDetailPOM 
{
	//Declare webdriver
			private WebDriver driver; 
			private WebElement expicitWait2;
			
			//define constructor
			public ProductDetailPOM(WebDriver driver)
			{
				this.driver = driver; 
				PageFactory.initElements(driver, this);
			}

			//find Webelements by locators
			@FindBy(xpath="//select[contains(@id, 'input-option')]")
			private WebElement size;
			
			@FindBy(id="input-option413input-quantity")
			private WebElement quantity;
			
			@FindBy(id="button-cart")
			private WebElement AddToCartBtn;
			
			@FindBy(xpath="//div[@class='alert alert-success']")
			private WebElement actualMessage;
			
			@FindBy(xpath="//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']")
			private WebElement cart;
			
			@FindBy(xpath="//a[2]")
			private WebElement checkoutBtn;
			
			@FindBy(xpath="//a[@class='btn btn-primary']")
			private WebElement continueCheckoutBtn;
			
			
			
			public void addToCart()
			{
				
				Select sizeDropdown=new Select(this.size);
				sizeDropdown.selectByVisibleText("12 x 20                                ");
							
				AddToCartBtn.click();
				
			}
			
			public void selectCart()
			{
				cart.click();
			}

			public void clickCheckout()
			{
				checkoutBtn.click();
			}
			
			
}

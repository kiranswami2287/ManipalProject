package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
			private WebElement rustSize;
			
			@FindBy(xpath="//*[@id=\"input-option386\"]")
			private WebElement maroonSize;
			
			@FindBy(id="input-option413input-quantity")
			private WebElement quantity;
			
			@FindBy(id="button-cart")
			private WebElement addToCartBtn;
			
			@FindBy(xpath="//div[@class='alert alert-success']")
			private WebElement actualMessage;
			
			@FindBy(xpath="//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']")
			private WebElement cartIcon;
			
			@FindBy(xpath="//a[2]")
			private WebElement checkoutBtn;
			
			@FindBy(xpath="//a[@class='btn btn-primary']")
			private WebElement continueCheckoutBtn;
			
			@FindBy(xpath="//div[@class='alert alert-success']")
			private WebElement successvalidatemsg;
			
			@FindBy(xpath="//div[@class='alert alert-success']")
			private WebElement successvalidatemsg1;
			
			@FindBy(xpath="//*[@id=\"cart\"]/ul/li[2]/div/p/a[1]/strong") 
			private WebElement viewCartBtn;
			
			public void addToCartRustTshirt()
			{
				
				Select sizeDropdown=new Select(this.rustSize);
				sizeDropdown.selectByVisibleText("42                                ");
							
				addToCartBtn.click();
				
				String expectedMessage = "Success: You have added REGULAR T-SHIRTS (Rust) to your shopping cart!\r\n" + 
						"×";
				String actualMessage=this.successvalidatemsg.getText();
				
				Assert.assertTrue((expectedMessage!=actualMessage), "product not added in Cart");
				//Assert.assertTrue(driver.findElement(By.id("id")).getText().contains("Success:"));
				
			}
			public void addToCartMaroonTshirt()
			{
				
				Select sizeDropdown=new Select(this.maroonSize);
				sizeDropdown.selectByVisibleText("40                                ");
							
				addToCartBtn.click();
				
				String expectedMessage = "Success: You have added Regular T-Shirt (Maroon) to your shopping cart!" + 
						"×";
				String actualMessage=this.successvalidatemsg1.getText();
				
				Assert.assertTrue((expectedMessage!=actualMessage), "product not added in Cart");
				
				
			}
			
			public void clickCartIcon()
			{
				cartIcon.click();
			}
			
			public void MouseOverToCartIcon()
			{
				Actions action=new Actions(driver);
				action.moveToElement(cartIcon).build().perform();
				cartIcon.click();
			}
			
			public void clickViewCart()
			{
				viewCartBtn.click();
			}

			public void clickCheckout()
			{
				checkoutBtn.click();
			}
			
			
}

package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UniformMakerPOM {

	//Declare webdriver
			private WebDriver driver; 
			
			//define constructor
			public UniformMakerPOM(WebDriver driver)
			{
				this.driver = driver; 
				PageFactory.initElements(driver, this);
			}

			//find Webelements by locators
			@FindBy(xpath="//div[3]//div[1]//div[1]//div[1]//a[1]//img[1]")
			private WebElement product;
			
			public void addProduct()
			{
				this.product.click();
			}
}


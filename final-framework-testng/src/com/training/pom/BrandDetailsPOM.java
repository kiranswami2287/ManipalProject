package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BrandDetailsPOM {

	//Declare webdriver
		private WebDriver driver; 
		
		//define constructor
		public BrandDetailsPOM(WebDriver driver)
		{
			this.driver = driver; 
			PageFactory.initElements(driver, this);
		}

		//find Webelements by locators
		@FindBy(xpath="//a[contains(text(),'UniformMaker')]")
		private WebElement uniformMaker;
	
	
		public void selectUniformMaker()
		{
			this.uniformMaker.click();
		}
}

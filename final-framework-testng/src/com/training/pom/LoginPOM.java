package com.training.pom;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPOM {
	private WebDriver driver; 
	
	public LoginPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="input-email")
	private WebElement emailAddress; 
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(xpath="//*[@id='content']/div/div[2]/div/form/input")
	private WebElement loginBtn; 
	
	@FindBy(xpath="//h1[contains(text(),'My Account')]")
	private WebElement actualMessage; 
	
	@FindBy(xpath="//div[@class='alert alert-danger']")
	private WebElement loginErrorMessage; 
	
	@FindBy(xpath="//div[@class='form-group']//a[contains(text(),'Forgotten Password')]")
	private WebElement forgotPasswordLink; 
	
	
	
	
	public void sendLoginDetails(String userName, String password)
	{
		this.emailAddress.clear();
		this.emailAddress.sendKeys(userName);
	
		this.password.clear(); 
		this.password.sendKeys(password); 
	
		this.loginBtn.click(); 
	}	
	
	public void invalidLogin() throws InterruptedException
	{
		
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		String errorMessage=this.loginErrorMessage.getText();
		Assert.assertEquals(expectedMessage,errorMessage);
		Thread.sleep(500);
		forgotPasswordLink.click();
			
	}
	
	public void loginValidate()
	{
		String expectedMessage = "MY ACCOUNT";
		String actualMessage=this.actualMessage.getText();
		Assert.assertEquals(expectedMessage,actualMessage);
	}
	
	
	
}


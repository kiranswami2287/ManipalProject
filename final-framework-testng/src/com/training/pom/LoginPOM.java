//package
package com.training.pom;
//import class and interfaces
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPOM 
{
	//Declare webdriver
	private WebDriver driver; 
	
	//define constructor
	public LoginPOM(WebDriver driver) 
	{
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//find Webelements by locators
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
		//clear username field
		this.emailAddress.clear();
		//send username
		this.emailAddress.sendKeys(userName);
		//clear password field
		this.password.clear(); 
		//send password
		this.password.sendKeys(password); 
		//click on login button
		this.loginBtn.click(); 
		
	}	
	
	public void invalidLogin() throws InterruptedException
	{
		//validate invalid login
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		String errorMessage=this.loginErrorMessage.getText();
		Assert.assertEquals(expectedMessage,errorMessage);
	}	
	
	public void forgotPWLink()
	{
		//click on forgotten password link
		forgotPasswordLink.click();
			
	}
	
	public void loginValidate()
	{
		//Validate login
		String expectedMessage = "MY ACCOUNT";
		String actualMessage=this.actualMessage.getText();
		Assert.assertEquals(expectedMessage,actualMessage);
	}
	
	
	
}


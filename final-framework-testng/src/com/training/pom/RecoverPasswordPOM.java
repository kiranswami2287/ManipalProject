//package
package com.training.pom;
//import classes and interfaces
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class RecoverPasswordPOM
{	
	//Declare webdriver
	private WebDriver driver; 
	//define constructor
	public RecoverPasswordPOM(WebDriver driver) 
	{
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//find Webelements by locators
	@FindBy(id="input-email")
	private WebElement emailAddress;
	
	@FindBy(xpath="//input[@class='btn btn-primary']")
	private WebElement continueBtn;
	
	@FindBy(xpath="//div[@class='alert alert-success']")
	private WebElement forgotPasswordSuccessMessage; 
	
	public void sendForgotPasswordDetails(String emailAddress)
	{
		//clear email address field
		this.emailAddress.clear();
		//send email address
		this.emailAddress.sendKeys(emailAddress);
		//click on continue button
		continueBtn.click();
	}
	
	public void forgotPasswordValidate()
	{
		//validate recover password message
		String expectedMessage = "An email with a confirmation link has been sent your email address.";
		String actualMessage=this.forgotPasswordSuccessMessage.getText();
		Assert.assertEquals(expectedMessage,actualMessage);
	}
	
	
}
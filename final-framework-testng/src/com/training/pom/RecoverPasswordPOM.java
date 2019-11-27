package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class RecoverPasswordPOM
{
private WebDriver driver; 
	
	public RecoverPasswordPOM(WebDriver driver) 
	{
		this.driver = driver; 
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(id="input-email")
	private WebElement emailAddress;
	
	@FindBy(xpath="//input[@class='btn btn-primary']")
	private WebElement continueBtn;
	
	@FindBy(xpath="//div[@class='alert alert-success']")
	private WebElement forgotPasswordSuccessMessage; 
	
	public void sendForgotPasswordDetails(String emailAddress)
	{
		this.emailAddress.clear();
		this.emailAddress.sendKeys(emailAddress);
		
		continueBtn.click();
	}
	
	public void forgotPasswordValidate()
	{
		String expectedMessage = "An email with a confirmation link has been sent your email address.";
		String actualMessage=this.forgotPasswordSuccessMessage.getText();
		Assert.assertEquals(expectedMessage,actualMessage);
	}
	
	
}
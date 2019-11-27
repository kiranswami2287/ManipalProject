package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPOM 
{	
private WebDriver driver; 
	
	public RegistrationPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[contains(text(),'Your Account Has Been Created!')]")
	private WebElement actualMessage; 
		
	@FindBy(id="input-firstname")
	private WebElement firstName; 
	
	@FindBy(id="input-lastname")
	private WebElement lastName;
	
	@FindBy(id="input-email")
	private WebElement email; 
	
	@FindBy(id="input-telephone")
	private WebElement telephone;
	
	@FindBy(id="input-address-1")
	private WebElement address1;
	
	@FindBy(id="input-city")
	private WebElement city;
	
	@FindBy(id="input-postcode")
	private WebElement postcode;
	
	@FindBy(id="input-country")
	private WebElement country;
	
	
	@FindBy(name="zone_id")
	private WebElement state;
	
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(id="input-confirm")
	private WebElement confirmPassword;
	
	@FindBy(xpath="//input[@name='newsletter' and @value='0']")
	private WebElement radioNo;
	
	@FindBy(xpath="//input[@name='agree']")
	private WebElement agree;
	
	@FindBy(xpath="//input[@class='btn btn-primary']") 
	private WebElement continueBtn;
	
	private WebElement expicitWait1,expicitWait2;
	
	public void sendRegistrationDetails(String firstName, String lastName, String email, String telephone, String address1, String city, String postcode, String password, String confirmPassword) throws InterruptedException 
	{
				
		this.firstName.clear();
		this.firstName.sendKeys(firstName);
		this.lastName.clear();
		this.lastName.sendKeys(lastName);
		this.email.clear();
		this.email.sendKeys(email);
		this.telephone.clear();
		this.telephone.sendKeys(telephone);
		this.address1.clear();
		this.address1.sendKeys(address1);
		this.city.clear();
		this.city.sendKeys(city);
		this.postcode.clear();
		this.postcode.sendKeys(postcode);
		
		//Select countryDropdown=new Select(driver.findElement(By.id("input-country")));
		Select countryDropdown=new Select(this.country);
		countryDropdown.selectByVisibleText("India");
		
		Thread.sleep(1000);
		
		//Select stateDropdown=new Select(driver.findElement(By.name("zone_id")));
		Select stateDropdown=new Select(this.state);
		stateDropdown.selectByVisibleText("Karnataka"); 
		
		this.password.clear(); 
		this.password.sendKeys(password); 
		this.confirmPassword.clear(); 
		this.confirmPassword.sendKeys(confirmPassword); 
		
		
		this.radioNo.click();
	
		WebDriverWait wait2=new WebDriverWait(driver, 30);
		expicitWait2 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.name("agree")));
		if (this.agree.isSelected())
		{
			this.continueBtn.click(); 
		}
		else {
		this.agree.click();
		}
}
	public void clickContinueBtn() {
		this.continueBtn.click(); 
	}
	
	public void registrationValidate()
	{
		String expectedMessage = "YOUR ACCOUNT HAS BEEN CREATED!";
		String actualMessage=this.actualMessage.getText();
		Assert.assertEquals(expectedMessage,actualMessage);
	}
	
}	
	

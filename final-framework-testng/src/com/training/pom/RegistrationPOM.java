//packages
package com.training.pom;
//import classes and interfaces
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
	//Declare webdriver
	private WebDriver driver;
	private String expectedMessageEmail = "E-Mail Address does not appear to be valid!";
	private String actMesgEmail;
	
	//define constructor
	public RegistrationPOM(WebDriver driver)
	{
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//find Webelements by locators
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
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right myaccount-menu']//a[contains(text(),'Logout')]") 
	private WebElement logoutBtn;
	
	@FindBy(xpath="//a[@class='btn btn-primary']") 
	private WebElement logoutContinueBtn;
	
	@FindBy(xpath="//input[@id='input-email']\\following::div") 
	private WebElement actualMessageEmail;
	
	private WebElement expicitWait1,expicitWait2;
	
	public void sendRegistrationDetails(String firstName, String lastName, String email, String telephone, String address1, String city, String postcode, String password, String confirmPassword) throws InterruptedException 
	{
		//clear firstname field		
		this.firstName.clear();
		//send first name 
		this.firstName.sendKeys(firstName);
		
		
		//clear last name field
		this.lastName.clear();
		//send last name
		this.lastName.sendKeys(lastName);
		//clear email field
		this.email.clear();
		//send email
		this.email.sendKeys(email);
		
		String emailvalue = this.email.getAttribute("value");
		if((emailvalue).contains("@") && (emailvalue).contains(".") )
		{
			System.out.println("It is a valid email ID");
		    
		}
		else{
		   		   
			actMesgEmail=this.actualMessageEmail.getText();
		    Assert.assertEquals(expectedMessageEmail,actMesgEmail);
		}

		//clear telephone field
		this.telephone.clear();
		//send telephone no.
		this.telephone.sendKeys(telephone);
		//clear address1 field
		this.address1.clear();
		//send Address1 
		this.address1.sendKeys(address1);
		//clear city field
		this.city.clear();
		//send city
		this.city.sendKeys(city);
		//clear postcode
		this.postcode.clear();
		//send postcode
		this.postcode.sendKeys(postcode);
		//select country from dropdown
		Select countryDropdown=new Select(this.country);
		countryDropdown.selectByVisibleText("India");
		
		Thread.sleep(1000);
		//select state from dropdown
		Select stateDropdown=new Select(this.state);
		stateDropdown.selectByVisibleText("Karnataka"); 
		//clear password field
		this.password.clear(); 
		//send password
		this.password.sendKeys(password); 
		//clear confirm password field
		this.confirmPassword.clear(); 
		//send confirm password
		this.confirmPassword.sendKeys(confirmPassword); 
		
		//click radio button
		this.radioNo.click();
	
		//Explicit wait
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
	public void clickContinueBtn() 
	{	
		//click on continue button
		this.continueBtn.click(); 
	}
	
	public void registrationValidate()
	{
		//validate success message
		String expectedMessage = "YOUR ACCOUNT HAS BEEN CREATED!";
		String actualMessage=this.actualMessage.getText();
		Assert.assertEquals(expectedMessage,actualMessage);
	}
	
	public void logout()
	{
		logoutBtn.click();
		logoutContinueBtn.click();
	}
}	
	

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
	
	@FindBy(xpath="//div[contains(text(),'First Name must be between 1 and 32 characters!')]") 
	private WebElement errormsgfirstName;
	
	@FindBy(xpath="//div[contains(text(),'Last Name must be between 1 and 32 characters!')]") 
	private WebElement errormsgLastName;
	
	@FindBy(xpath="//div[contains(text(),'E-Mail Address does not appear to be valid!')]") 
	private WebElement errormsgEmail;
	
	@FindBy(xpath="//div[contains(text(),'Telephone must be between 3 and 32 characters!')]") 
	private WebElement errormsgTelephone;
	
	@FindBy(xpath="//div[contains(text(),'Telephone must be between 3 and 32 characters!')]") 
	private WebElement errormsgAddress1;
	
	@FindBy(xpath="//div[contains(text(),'City must be between 2 and 128 characters!')]") 
	private WebElement errormsgCity;
	
	@FindBy(xpath="//div[contains(text(),'Postcode must be between 2 and 10 characters!')]") 
	private WebElement errormsgPostcode;
	
	@FindBy(xpath="//div[contains(text(),'Please select a region / state!')]") 
	private WebElement errormsgState;
	
	@FindBy(xpath="//div[contains(text(),'Password must be between 4 and 20 characters!')]") 
	private WebElement errormsgPassword;
	
	private WebElement expicitWait1,expicitWait2;
	
	
	public void validRegistrationDetails(String firstName, String lastName, String email, String telephone, String address1, String city, String postcode, String country,String state,String password, String confirmPassword) throws InterruptedException
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
				
				//clear telephone field
				this.telephone.clear();
				//send telephone no.
				//this.telephone.sendKeys(""+telephone);
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
				
				Thread.sleep(1000);
				Select countryDropdown=new Select(this.country);
				countryDropdown.selectByVisibleText(country);
				
				Thread.sleep(1000);
				//select state from dropdown
				Select stateDropdown=new Select(this.state);
				stateDropdown.selectByVisibleText(state); 
				
				
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
				WebDriverWait wait1=new WebDriverWait(driver, 30);
				expicitWait2 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.name("agree")));
				
				if (this.agree.isSelected())
				{
					this.continueBtn.click(); 
				}
				else {
				this.agree.click();
				}

				this.continueBtn.click(); 
				String expectedMessage = "YOUR ACCOUNT HAS BEEN CREATED!";
				String actualMessage=this.actualMessage.getText();
				Assert.assertEquals(expectedMessage,actualMessage);
	}
	
	
	
	public void invalidRegistrationDetails(String firstName, String lastName, String email, String telephone, String address1, String city, String postcode, String country,String state,String password, String confirmPassword,String firstNameErrorMsg,String lastNameErrorMsg,String emailErrorMsg,String telephoneErrorMsg,String address1ErrorMsg,String cityErrorMsg,String postcodeErrorMsg,String stateErrorMsg,String passwordErroMsg) throws InterruptedException 
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
		
		//clear telephone field
		this.telephone.clear();
		//send telephone no.
		//this.telephone.sendKeys(""+telephone);
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
		
		Thread.sleep(1000);
		Select countryDropdown=new Select(this.country);
		countryDropdown.selectByVisibleText(country);
		
		Thread.sleep(1000);
		//select state from dropdown
		Select stateDropdown=new Select(this.state);
		stateDropdown.selectByVisibleText(state); 
		
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
		WebDriverWait wait1=new WebDriverWait(driver, 30);
		expicitWait2 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.name("agree")));
		
		if (this.agree.isSelected())
		{
			this.continueBtn.click(); 
		}
		else {
		this.agree.click();
		}

		this.continueBtn.click(); 
		//WebDriverWait wait2=new WebDriverWait(driver, 50);
		//expicitWait2 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")));
		
		if (this.firstName.getAttribute("value")=="null")
		{
			this.firstName.sendKeys("");
			String expctmsgfirstName="First Name must be between 1 and 32 characters!";
			
			Assert.assertEquals(errormsgfirstName.getText(),expctmsgfirstName);
		}
		else if (lastName.isEmpty())
		{
			String expctmsgLastName="Last Name must be between 1 and 32 characters!";
			Assert.assertEquals(errormsgLastName.getText(),expctmsgLastName);
		}
		else if (email.isEmpty())
		{
			String expctmsgEmail="Last Name must be between 1 and 32 characters!";
			Assert.assertEquals(errormsgEmail.getText(),expctmsgEmail);
		}
		else if (telephone.isEmpty())
		{
			String expctmsgTelephone="Last Name must be between 1 and 32 characters!";
			Assert.assertEquals(errormsgTelephone.getText(),expctmsgTelephone);
		}	
		else if (address1.isEmpty())
		{
			String expctmsgAddress1="Last Name must be between 1 and 32 characters!";
			Assert.assertEquals(errormsgAddress1.getText(),expctmsgAddress1);
		}	
		else if (city.isEmpty())
		{
			String expctmsgCity="Last Name must be between 1 and 32 characters!";
			Assert.assertEquals(errormsgCity.getText(),expctmsgCity);
		}	
		else if (postcode.isEmpty())
		{
			String expctmsgPostcode="Last Name must be between 1 and 32 characters!";
			Assert.assertEquals(errormsgPostcode.getText(),expctmsgPostcode);
		}	
		else if (state.isEmpty())
		{
			String expctmsgState="Last Name must be between 1 and 32 characters!";
			Assert.assertEquals(errormsgState.getText(),expctmsgState);
		}	
		else if (password.isEmpty())
		{
			String expctmsgPassword="Last Name must be between 1 and 32 characters!";
			Assert.assertEquals(errormsgPassword.getText(),expctmsgPassword);
		}	
		
		
			
		   
	}

	
	
		
	
	
	public void logout()
	{
		WebDriverWait wait2=new WebDriverWait(driver, 30);
		expicitWait2 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right myaccount-menu']//a[contains(text(),'Logout')]")));
		logoutBtn.click();
		logoutContinueBtn.click();
	}
}	
	

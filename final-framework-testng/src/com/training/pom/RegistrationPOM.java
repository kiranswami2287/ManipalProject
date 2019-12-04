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
	private String regexNumber = "/^[0-9]a-zA-Z*$/";
	private String regexName="/^[a-zA-Z]0-9*$/";
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
	
		
	private WebElement expicitWait1,expicitWait2;
	
	public void sendRegistrationDetails(String firstName, String lastName, String email, String telephone, String address1, String city, String postcode, String country,String state,String password, String confirmPassword) throws InterruptedException 
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
		
		String firstnamevalue=this.firstName.getAttribute("value");
		Assert.assertTrue(firstnamevalue.matches("["+ regexName + "]+"), "First Name is Invalid");
		Assert.assertTrue(firstnamevalue.length()>1 && firstnamevalue.length()<32, "First Name must be between 1 and 32 characters");
		
		String lastnamevalue=this.lastName.getAttribute("value");
		Assert.assertTrue(lastnamevalue.matches("["+ regexName + "]+"), "Last Name is Invalid");
		Assert.assertTrue(lastnamevalue.length()>1 && lastnamevalue.length()<32, "Last Name must be between 1 and 32 characters");
			
		String emailvalue = this.email.getAttribute("value");
		Assert.assertTrue((emailvalue).contains("@") && (emailvalue).contains("."), "E-Mail Address does not appear to be valid");
		
		String telephoneValue=this.telephone.getAttribute("value");
		Assert.assertTrue(telephoneValue.matches(regexNumber), "Telephone is Invalid");	
		Assert.assertTrue(telephoneValue.length() ==10, "Telephone no. should be 10 digit");
		
		String address1value = this.address1.getAttribute("value");
		Assert.assertTrue(address1value.length()>3 && address1value.length()<128, "Address1 must be between 3 and 128 characters");
		
		String cityvalue = this.city.getAttribute("value");
		Assert.assertTrue(cityvalue.length()>2 && address1value.length()<128, "City must be between 2 and 128 characters");
		
		String postcodeValue=this.postcode.getAttribute("value");
		Assert.assertTrue(postcodeValue.matches(regexNumber), "Post Code is Invalid");	
		Assert.assertTrue(postcodeValue.length() ==5, "Post Code is Invalid");
		
		Assert.assertTrue(this.state.isSelected(), "Please Select Region/State");
		
		String passwordvalue=this.password.getAttribute("value");
		String confirmpasswordvalue=this.confirmPassword.getAttribute("value");	
		Assert.assertTrue((passwordvalue.length()>4 && passwordvalue.length()<20), "Password must be between 4 and 20 characters");
		Assert.assertTrue((passwordvalue==confirmpasswordvalue), "Password and Confirm Password not matching");
		
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
		WebDriverWait wait2=new WebDriverWait(driver, 30);
		expicitWait2 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right myaccount-menu']//a[contains(text(),'Logout')]")));
		logoutBtn.click();
		logoutContinueBtn.click();
	}
}	
	

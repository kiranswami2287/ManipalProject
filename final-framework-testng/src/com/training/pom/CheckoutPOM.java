package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CheckoutPOM 
{
	//Declare webdriver
	private WebDriver driver; 
	private WebElement expicitWait2;
	
	//define constructor
	public CheckoutPOM(WebDriver driver)
	{
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	//find Webelements by locators
	@FindBy(xpath="//input[@id='button-payment-address']")
	private WebElement billingDetailscontinueBtn;
	
	@FindBy(id="button-shipping-address")
	private WebElement deliveryDetailscontinueBtn;
	
	@FindBy(xpath="//input[@name='shipping_method']")
	private WebElement freeShippingRadioBtn;
	
	@FindBy(xpath="//textarea[@name='comment']")
	private WebElement comment;
	
	@FindBy(xpath="//input[@id='button-shipping-method']")
	private WebElement deliveryMethodcontinueBtn;
	
	@FindBy(xpath="//input[@name='agree']")
	private WebElement agreeCheck;
	
	@FindBy(xpath="//input[@id='button-payment-method']")
	private WebElement paymentMethodcontinueBtn;
	
	@FindBy(xpath="//input[@id='button-confirm']")
	private WebElement confirmOrderBtn;
	
	
	@FindBy(xpath="//h1[contains(text(),'Your order has been placed!')]")
	private WebElement confirmOrderSuccessMsg;
	
	@FindBy(xpath="//*[@id=\"collapse-checkout-option\"]/div/div/div[1]/div[2]/label/input")
	private WebElement guestCheckoutRadio;
	
	@FindBy(xpath="//input[@id='button-account']")
	private WebElement guestCheckoutcontinueBtn;
	
	@FindBy(xpath="//input[@id='input-payment-firstname']")
	private WebElement firstName;
	
	@FindBy(xpath="//input[@id='input-payment-lastname']")
	private WebElement lastName;
	
	@FindBy(xpath="//input[@id='input-payment-email']")
	private WebElement email;
	
	@FindBy(xpath="//input[@id='input-payment-telephone']")
	private WebElement telephone;
	
	@FindBy(xpath="//input[@id='input-payment-address-1']")
	private WebElement address1;
	
	@FindBy(xpath="//input[@id='input-payment-city']")
	private WebElement city;
	
	@FindBy(xpath="//input[@id='input-payment-postcode']")
	private WebElement postCode;
	
	@FindBy(xpath="//select[@id='input-payment-country']")
	private WebElement country;
	
	@FindBy(xpath="//select[@id='input-payment-zone']")
	private WebElement state;
	
	@FindBy(xpath="//input[@name='shipping_address']")
	private WebElement shippingAddressChkbx;
		
	@FindBy(xpath="//input[@id='button-guest']")
	private WebElement guestContinuBtn;
	
	
	
	public void billingDetailContinue()
	{
		
		billingDetailscontinueBtn.click();
		
	}	
	
	public void deliveryDetailContinue()
	{
		WebDriverWait wait1=new WebDriverWait(driver, 30);
		expicitWait2 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-shipping-address")));
		deliveryDetailscontinueBtn.click();
	}	
	
	public void deliveryMethod()
	{
		
		if (freeShippingRadioBtn.isSelected())
		{
			comment.sendKeys("Please Deliver between 7am to 10 am");
		}
		else
		{
			freeShippingRadioBtn.click();
			comment.sendKeys("Please Deliver between 7am to 10 am");
		}
		
		deliveryMethodcontinueBtn.click();
		
		//String actual= comment.getText();
		//String expected="Please Deliver between 7am to 10 am";
		//Assert.assertEquals(actual, expected);
		
	}
	
	public void sendBillingDetails(String firstName, String lastName, String email, String telephone, String address1, String city, String postcode) throws InterruptedException
	{
		guestCheckoutRadio.click();
		guestCheckoutcontinueBtn.click();
		
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
				this.postCode.clear();
				//send postcode
				this.postCode.sendKeys(postcode);
				//select country from dropdown
				Select countryDropdown=new Select(this.country);
				countryDropdown.selectByVisibleText("India");
				
				Thread.sleep(1000);
				//select state from dropdown
				Select stateDropdown=new Select(this.state);
				stateDropdown.selectByVisibleText("Maharashtra"); 
				
				if (shippingAddressChkbx.isSelected())
				{
					guestContinuBtn.click();
				}
				else
				{
					shippingAddressChkbx.click();
					guestContinuBtn.click();
				}
		
		}
		
		public void paymentMethod()
		{
			WebDriverWait wait2=new WebDriverWait(driver, 30);
			expicitWait2 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.name("agree")));
			
			if (this.agreeCheck.isSelected())
			{
				this.paymentMethodcontinueBtn.click();
			}
			else {
					this.agreeCheck.click();
					this.paymentMethodcontinueBtn.click();
				}
		}
		
		public void confirmOrder()
		{
			confirmOrderBtn.click();
			
			String actual2= confirmOrderSuccessMsg.getText();
			String expected2="YOUR ORDER HAS BEEN PLACED!";
			Assert.assertEquals(actual2, expected2);
		}
		

}




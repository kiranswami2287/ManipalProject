package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPOM 
{
private WebDriver driver; 
	
	public MyAccountPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//li[@class='dropdown myaccount open']")
	private WebElement myAccount;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right myaccount-menu']//a[contains(text(),'Order History')]")
	private WebElement orderHistoryOption;
	
	public void myAccountOrderHistory(String register)
	{
	Actions action=new Actions(driver);
	action.moveToElement(myAccount).build().perform();
	myAccount.click();
	orderHistoryOption.click();
	}
	
}

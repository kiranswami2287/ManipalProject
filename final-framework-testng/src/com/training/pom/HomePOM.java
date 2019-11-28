//package
package com.training.pom;
//import classes and interfaces
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePOM {
	//Declare webdriver
	private WebDriver driver; 
	//define constructor
	public HomePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
}
	//find Webelements by locators
	@FindBy(xpath="//*[@id='top-links1']/ul/li[3]/a")
	private WebElement myAccount;
	
	@FindBy(xpath="//*[@id='top-links1']/ul/li[3]/ul/li[1]/a")
	private WebElement registerOption;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right myaccount-menu']//a[contains(text(),'Login')]")
	private WebElement loginOption;
	
	
	public void selectMyAccount()
	{
		//move mouse over to My Account
		Actions action=new Actions(driver);
		action.moveToElement(myAccount).build().perform();
		myAccount.click();
	}
	
	public void myAccountRegister()
	{
		//click on Register option from MyAccount
		registerOption.click();
	}
	
	public void myAccountLogin()
	{
		//click on Login option from MyAccount
		loginOption.click();
	}
	
	
}
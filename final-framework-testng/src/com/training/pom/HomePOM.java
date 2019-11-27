package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePOM {

private WebDriver driver; 
	
	public HomePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
}
	
	@FindBy(xpath="//*[@id='top-links1']/ul/li[3]/a")
	private WebElement myAccount;
	
	@FindBy(xpath="//*[@id='top-links1']/ul/li[3]/ul/li[1]/a")
	private WebElement registerOption;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right myaccount-menu']//a[contains(text(),'Login')]")
	private WebElement loginOption;
	
	public void myAccountRegister(String register)
	{
	Actions action=new Actions(driver);
	action.moveToElement(myAccount).build().perform();
	myAccount.click();
	registerOption.click();
	}
	
	public void myAccountLogin(String login)
	{
	Actions action=new Actions(driver);
	action.moveToElement(myAccount).build().perform();
	myAccount.click();
	loginOption.click();
	}
	
	
}
//package
package com.training.pom;
import org.openqa.selenium.By;
//import classes and interfaces
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
	
	@FindBy(xpath="//div[@id='banner0']//div//img[@class='img-responsive']")
	private WebElement preSchoolUni;
	
	@FindBy(xpath="//div[@class='box-content']//div[2]//div[1]//div[1]//div[1]//a[1]//img[1]")
	private WebElement selectTshirtRust;
	
	@FindBy(xpath="//*[@id=\"featured-grid\"]/div[3]/div/div/div[1]/a/img")
	private WebElement selectMaroonTshirt;
	
	@FindBy(xpath="//h3[contains(text(),'REGULAR T-SHIRTS (Rust)')]")
	private WebElement productvalidatemsgRust;
	
	@FindBy(xpath="//h3[contains(text(),'Regular T-Shirt (Maroon)')]")
	private WebElement productvalidatemsgMaroon;
	
	private WebElement expicitWait2;
	
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
		WebDriverWait wait2=new WebDriverWait(driver, 30);
		expicitWait2 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Login')]")));
		//click on Login option from MyAccount
		loginOption.click();
	}
	
	public void premiumSchoolUniform()
	{
		preSchoolUni.click();
	}
	
	public void selectRustTshirt()
	{
		selectTshirtRust.click();
		String expectedMessage = "REGULAR T-SHIRTS (RUST)";
		String actualMessage=this.productvalidatemsgRust.getText();
		Assert.assertEquals(expectedMessage,actualMessage);
	}
	
	public void selectMaroonTshirt()
	{
		selectMaroonTshirt.click();
		String expectedMessage = "REGULAR T-SHIRT (MAROON)";
		String actualMessage=this.productvalidatemsgMaroon.getText();
		Assert.assertEquals(expectedMessage,actualMessage);
	}
	
}
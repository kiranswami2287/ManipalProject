//packages
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

public class MyAccountPOM 
{	
	//Declare webdriver
	private WebDriver driver; 
	private WebElement expicitWait2;
	
	//define constructor
	public MyAccountPOM(WebDriver driver)
	{
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	//find Webelements by locators
	@FindBy(xpath="//*[@id='top-links1']/ul/li[3]/a")
	private WebElement myAccount;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right myaccount-menu']//a[contains(text(),'Order History')]")
	private WebElement orderHistoryOption;
	
	@FindBy(xpath="//h1[contains(text(),'Order History')]")
	private WebElement actualMessage; 
	
	@FindBy(xpath="//a[contains(text(),'Brands')]")
	private WebElement brandOption; 
	
	@FindBy(xpath="//a[contains(text(),'Uniform Store')]")
	private WebElement uniformStoreHome; 
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right myaccount-menu']//a[contains(text(),'Logout')]")
	private WebElement logoutBtn; 
	
	@FindBy(xpath="//a[@class='btn btn-primary']")
	private WebElement logoutContinueBtn; 
	
	
	
	public void myAccountOrderHistory()
	{	
		//move mouse over to My Account
		Actions action=new Actions(driver);
		action.moveToElement(myAccount).build().perform();
		//Click on my account 
		myAccount.click();
		//select Order History option
		orderHistoryOption.click();
	}
	
	
	public void orderHistoryValidate()
	{
		//validate order history page
		String expectedMessage = "ORDER HISTORY";
		String actualMessage=this.actualMessage.getText();
		Assert.assertEquals(expectedMessage,actualMessage);
	}
	
	public void clickBrands()
	{
		this.brandOption.click();
	}
	
	public void uniformStoreHomePage()
	{
		uniformStoreHome.click();
	}
	
	public void logout()
	{
		WebDriverWait wait2=new WebDriverWait(driver, 30);
		expicitWait2 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right myaccount-menu']//a[contains(text(),'Logout')]")));
		logoutBtn.click();
		logoutContinueBtn.click();
	}
}

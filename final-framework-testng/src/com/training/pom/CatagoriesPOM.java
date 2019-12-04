package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CatagoriesPOM
{
	//Declare webdriver
	private WebDriver driver; 
	
	//define constructor
	public CatagoriesPOM(WebDriver driver) 
	{
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//find Webelements by locators
	@FindBy(xpath="//i[@class='fa fa-plus']")
	private WebElement addNewIcon; 
	
	@FindBy(xpath="//input[@id='input-name1']")
	private WebElement catagoryName;
	
	@FindBy(xpath="//div[@class='note-editable panel-body']")
	private WebElement catagoryDescription; 
	
	@FindBy(xpath="//input[@id='input-meta-title1']")
	private WebElement metaTagTitle;
	
	@FindBy(xpath="//textarea[@id='input-meta-description1']")
	private WebElement metaTagDescription; 
	
	@FindBy(xpath="//a[contains(text(),'Data')]")
	private WebElement dataSubTab; 
	
	@FindBy(xpath="//a[contains(text(),'Design')]")
	private WebElement designSubTab; 
	
	@FindBy(xpath="//input[@name='category_store[]']")
	private WebElement dataSubTabDefault; 
	
	@FindBy(xpath="//select[@name='category_layout[0]']")
	private WebElement designSubTabDefault; 
	
	@FindBy(xpath="//button[@class='btn btn-primary']")
	private WebElement saveBtn; 
	
	@FindBy(xpath="//div[@class='alert alert-success']")
	private WebElement alertSuccessMessage; 
	
	public void addCategory(String categoryName,String categoryDescription,String metaTagTitle,String metaTagDescription)
	{	
		this.addNewIcon.click();
		this.catagoryName.clear();
		this.catagoryName.sendKeys(categoryName);
		this.catagoryDescription.clear();
		this.catagoryDescription.sendKeys(categoryDescription);
		this.metaTagTitle.clear();
		this.metaTagTitle.sendKeys(metaTagTitle);
		this.metaTagDescription.clear();
		this.metaTagDescription.sendKeys(metaTagDescription);
		
		this.dataSubTab.click();
		
		if(dataSubTabDefault.isSelected())
		{
			this.designSubTab.click();
		}
		else
		{
			this.dataSubTabDefault.click();
			this.designSubTab.click();
		}
		
		Select designDefaultDropdwn= new Select(this.designSubTabDefault);
		designDefaultDropdwn.selectByVisibleText("Account");
		
		this.saveBtn.click();
		
		String expectedMessage = "Success: You have modified categories!\r\n" + 
				"×";
		String actualMessage=this.alertSuccessMessage.getText();
		Assert.assertTrue((expectedMessage!=actualMessage), "product not added in Cart");
		
   }
	

}

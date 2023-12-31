package com.project.testcases.pTracker;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

import com.project.utilities.ExcelReader;

public class TC_ValidateActiveProjectsTab extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	Operations op ;
	ControlActions controlActions;
	private String uName = "admin";
	private String uPassword = "admin";
	private String xpath;
	private static final int DELAY = 20000;
	String eName = "Mahajan, Milind";

	@BeforeClass
	public void groupInit() throws Exception {
		driver = launchbrowser();
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, DELAY);
		controlActions = new ControlActions(driver);
		//wait = new WebDriverWait(driver, 20000);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		op = new Operations(driver);
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		loginPage.waitForPageLoaded(driver);
		loginPage.TC_Login(driver,uName, uPassword);
		loginPage.TC_ChangeUser(driver,eName);
	}

	@Test(priority = 1,groups = { "sanity", "regression" }, description = "Validate Functionalites on Active Project Tab")
	public void TC_ValidateProjectCardTitleOnActiveProjectTab() throws Exception 
	{
		String[] strActProType = {"Fixed Price", "Support Project", "Staffing","Time and Material","Pending Closure"};  
		if(newProject.ActiveProjectTabFocused.isDisplayed())
		{ 
			log4jInfo("Active Project Tab is focused on P-Tracker page");
			try {
				xpath = "//*[@id='R505691641167493060_cards']/li";
				List<WebElement> ListElements = driver.findElements(By.xpath(xpath));
				int size = ListElements.size();
				System.out.println(" Size of element is: " + ListElements.size());
				System.out.println(" Size of Project type Arry is: " + strActProType.length);
					logInfo(" ------------------------------------------ ");
				    for(int i=1;i<=size;i++){ 
						WebElement ele = driver.findElement(By.xpath(xpath+"["+i+"]//h3[contains(text(),'')]"));
						//System.out.println(xpath+"["+i+"]//h3[contains(text(),'')]"); 
						if (ele.getText().equals(strActProType[i-1])) {
							log4jInfo("Element at ["+i+"] -----> " + ele.getText() );
							threadsleep(1000);
							JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
							jsExecutor.executeScript("arguments[0].style.border='2px solid yellow'", ele);
						}
						else
						{
							logError("Failed to find the project name: " +strActProType[i-1]+" :On Active Project Tab");
						}
				    }
				}
			catch(Exception e)
			{
				log4jError("Failed to Select the element " + xpath + e.getMessage());
				e.printStackTrace();
			}
		}
		else
		{
			logError("Failed to Focus Active Project Tab on P-Tracker page");
		}
	}

	@Test(priority = 2,groups = { "sanity", "regression" }, description = "Validate Functionalites on Closed Project Tab")
	public void TC_ValidateProjectCardTitleOnClosedProjectTab() throws Exception 
	{
		threadsleep(1000);
		String[] strCloProType = {"Fixed Price", "Support Project", "Staffing","Time and Material"};  
		op.clickElement(newProject.ClosedProjectTabLink);
		wait.until(ExpectedConditions.visibilityOf(newProject.ClosedProjectTabFocused)); 
		
		if(newProject.ClosedProjectTabFocused.isDisplayed())
		{
			log4jInfo("Closed Project Tab is focused on P-Tracker page");
			try {
				xpath = "//*[@id='R530072318487821345_cards']/li";
				List<WebElement> ListElements = driver.findElements(By.xpath(xpath));
				int size = ListElements.size();
				System.out.println(" Size of element is: " + ListElements.size());
				System.out.println(" Size of Project type Arry is: " + strCloProType.length);
					logInfo(" ------------------------------------------ ");
				    for(int i=1;i<=size;i++){ 
						WebElement ele = driver.findElement(By.xpath(xpath+"["+i+"]//h3[contains(text(),'')]"));
						//System.out.println(xpath+"["+i+"]//h3[contains(text(),'')]"); 
							if (ele.getText().equals(strCloProType[i-1])) {
							log4jInfo("Element at ["+i+"] -----> " + ele.getText() );
							threadsleep(1000);
							JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
							jsExecutor.executeScript("arguments[0].style.border='2px solid yellow'", ele);
						}
						else
						{
							logError("Failed to find the project name: " +strCloProType[i-1]+" :On Active Project Tab");
						}
				    }
				}
			catch(Exception e)
			{
				log4jError("Failed to Select the element " + xpath + e.getMessage());
				e.printStackTrace();
			}
		}
		else
		{
			logError("Failed to Focus Closed Project Tab on P-Tracker page");
		}
	}
	
	@Test(priority = 3,groups = { "sanity", "regression" }, description = "Validate Functionalites on New Project Tab")
	public void TC_ValidateProjectCardTitleOnNewProjectsTab() throws Exception 
	{
		threadsleep(1000);
		String[] strNewProType = {"Draft", "Pending with DH", "Pending with PMO","Pending with Finance"};  
		op.clickElement(newProject.NewProjectLink);
		wait.until(ExpectedConditions.visibilityOf(newProject.NewProjectTabFocused)); 
		
		if(newProject.NewProjectTabFocused.isDisplayed())
		{
			log4jInfo("New Project Tab is focused on P-Tracker page");
			try {
				xpath = "//*[@id='card_region_cards']/li";
				List<WebElement> ListElements = driver.findElements(By.xpath(xpath));
				int size = ListElements.size();
				System.out.println(" Size of element is: " + ListElements.size());
				System.out.println(" Size of Project type Arry is: " + strNewProType.length);
				    for(int i=1;i<=size;i++){ 
						WebElement ele = driver.findElement(By.xpath(xpath+"["+i+"]//h3[contains(text(),'')]"));
						//System.out.println(xpath+"["+i+"]//h3[contains(text(),'')]"); 
						if (ele.getText().equals(strNewProType[i-1])) {
							log4jInfo("Element at ["+i+"] -----> " + ele.getText() );
							threadsleep(1000);
							JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
							jsExecutor.executeScript("arguments[0].style.border='2px solid yellow'", ele);
						}
						else
						{
							logError("Failed to find the project name: " +strNewProType[i-1]+" :On Active Project Tab");
						}
				    }
				}
			catch(Exception e)
			{
				log4jError("Failed to Select the element " + xpath + e.getMessage());
				e.printStackTrace();
			}
			threadsleep(9000);
		}
		else
		{
			logError("Failed to Focus New Project Tab on P-Tracker page");
		}
	}
	
	@AfterClass (alwaysRun = true)
	public void tearDown() {
		try {
			op.closeBrowser(driver);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

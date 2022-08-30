package com.project.testcases.pTracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

import scala.collection.mutable.HashMap;

public class TCG_ValidateProjectActionsTab_SubManger extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage LoginPage;
	
	NewProjectsPage NewProject;
	Operations op ;
	ExcelUtils ExcelUtils;
	ControlActions controlActions;
	private String uName = "abc";
	private String uPassword = "xyz";
	private String xpath;
	private static final int DELAY = 20;
	NewProjectsPage newProject;
	XSSFWorkbook wb = null;
	XSSFSheet sh = null;
	String datapoolPath;
	int tcID;
    String tcStatus;
	

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
		newProject = new NewProjectsPage(driver);
		LoginPage = new PTrackerLoginPage(driver);
		NewProject = new NewProjectsPage(driver);
		ExcelUtils = new ExcelUtils();
		LoginPage.waitForPageLoaded(driver);
		LoginPage.TC_Login(driver,uName, uPassword);
		LoginPage.changeUser(driver, "Thakur, Monika");
	}

		
	@Test(priority = 1,groups = { "sanity", "regression" }, description = "Verify the access on Project Actions Tab")
	public void TC_ValidateProjectCardTitleOnProjectActionsTab() throws Exception 
	{
		threadsleep(1000);
		int counter=0;
		String[] strCloProType = {"Extension Actions", "Closure Actions","New Project Actions","Modification Actions"};  
		op.clickElement(NewProject.ProjectActionTabTxt);
		wait.until(ExpectedConditions.visibilityOf(NewProject.ProjectActionTabFocused)); 
		
		if(NewProject.ProjectActionTabFocused.isDisplayed())
		{
			logInfo("Project Action Tab is focused on P-Tracker page");
			try 
			{
				xpath = "//*[@id='R154473969590676725_cards']/li";
				List<WebElement> ListElements = driver.findElements(By.xpath(xpath));
				int size = ListElements.size();
				System.out.println("Size of element is: " + ListElements.size());
				System.out.println("Size of Project type Arry is: " + strCloProType.length);
					//logInfo(" ------------------------------------------ ");
				    for(int i=1;i<=size;i++){ 
						WebElement ele = driver.findElement(By.xpath(xpath+"["+i+"]//h3[contains(text(),'')]"));
						//System.out.println(xpath+"["+i+"]//h3[contains(text(),'')]"); 
						if (ele.getText().equals(strCloProType[i-1])) 
						{
							Assert.assertEquals(ele.getText(), strCloProType[i-1], "TAB NOT FOUND");
							logInfo("Element at ["+i+"] -----> " + ele.getText() );
							threadsleep(1000);
							JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
							jsExecutor.executeScript("arguments[0].style.border='2px solid yellow'", ele);
						    counter = counter + 1;
						}
						else
						{
							logError("Failed to find the project name: " +strCloProType[i-1]+" :On Project Action Tab");
						}
				    }
				    if(counter==size)
	                {
			    		logInfo("Testcase is pass");
						tcStatus = "PASS";
						ExcelUtils.logTestResult("TCG_ValidateProjectActionsTab_SubManger",tcStatus);
					}
					else 
					{ 
						logError("Testcase is failed"); 
						tcStatus = "FAIL";
						ExcelUtils.logTestResult("TCG_ValidateProjectActionsTab_SubManger",tcStatus);
					}
    
				}
			catch(Exception e)
			{
				logError("Failed to Select the element " + xpath + e.getMessage());
				e.printStackTrace();
			}
		}
		else
		{
			logError("Failed to Focus Closed Project Tab on P-Tracker page");
		}
	}
	
	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}

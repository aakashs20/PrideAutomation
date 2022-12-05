package com.project.testcases.pTracker;

import java.io.IOException;
import java.util.List;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PL_ActiveProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;
import com.project.utilities.ExcelReader;

//Aishwarya Bachhav	 
	public class TCG_ValidateFiltersForActiveProjectAllActions extends TestBase {
		WebDriverWait wait;
		PTrackerLoginPage loginPage;
		NewProjectsPage newProject;
		Operations op ;
		ControlActions controlActions;
		private String uName = "admin";
		private String uPassword = "admin";
		private String xpath;
		private static final int DELAY = 20;
		String eName = "Mahajan, Milind";
		PL_ActiveProjectsPage plActiveProjectPage;
		ExcelReader reader;

		@BeforeClass(alwaysRun = true)
		public void groupInit() throws Exception {
			driver = launchbrowser();
			driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, DELAY);
			controlActions = new ControlActions(driver);
			plActiveProjectPage=new PL_ActiveProjectsPage(driver);
			controlActions.getUrl(prop.getProperty("appl_url_dev"));
			op = new Operations(driver);
			loginPage = new PTrackerLoginPage(driver);
			newProject = new NewProjectsPage(driver);
			loginPage.waitForPageLoaded(driver);
			loginPage.TC_Login(driver,uName, uPassword);
			loginPage.TC_ChangeUser(driver,eName);
		}
		
		@Test(groups = {"sanity", "UI"}, description = "Validate Filters Functionalites for Active Projects")
		public void validateActiveProjectsFilters() throws Exception {
		String tcID = "TC_VALIDATE_ACTIVE_FILTER_TAB";
		logInfo("Starting of Test Case : " + tcID );
		reader = new ExcelReader();
		int tcRowNum; 
		String projectState = "SUBMIT"; 
	    String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		String sheetName = "Automation";
		int header=0; 
		tcRowNum = 3;
		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
			
	    op.clickElement(newProject.ActiveProjectTabLink);
	    wait.until(ExpectedConditions.visibilityOf(newProject.ActiveProjectTabFocused));
	    Assert.assertTrue( plActiveProjectPage.ActiveFilterButton.isDisplayed(), "filter button is not displayed");
	    logInfo("Clicking on ActiveFilterButton : " + plActiveProjectPage.ActiveFilterButton.getText());
	    op.clickElement(plActiveProjectPage.ActiveFilterButton); 
	    op.waitImplicitely(driver, 60);
	    for(int i=0;i<plActiveProjectPage.ActiveFilterHeader.size();i++) 
	    {
	    	logInfo("Searching for the ActiveProjectFilters : " + plActiveProjectPage.ActiveFilterHeader.get(i).getText());
	    	op.waitImplicitely(driver, 60);
	    }
			logInfo("Clicking on Checkboxes.........");
			plActiveProjectPage.ActiveFilterCheckbox.get(0).click();
			//op.waitTillSpinnerDisable();
			threadsleep(3000);
			op.click(plActiveProjectPage.ActiveFilterCheckbox.get(1));
			//op.waitTillSpinnerDisable();
			threadsleep(4000);
			boolean elementPresent=plActiveProjectPage.AppliedFilters.get(0).isDisplayed();
			IsTrue(plActiveProjectPage.AppliedFilters.get(0).isDisplayed() ,"fixed price display","fixed price not displayed");
			logInfo("Searching for the ActiveButton : " + plActiveProjectPage.AppliedFilters.get(0).getText());
			Equals(op.getText(plActiveProjectPage.AppliedFilters.get(0)),"Fixed Price","Fixed Price is present","Fixed Price is not present");
			boolean elementPresent1=plActiveProjectPage.AppliedFilters.get(1).isDisplayed();
			IsTrue(plActiveProjectPage.AppliedFilters.get(1).isDisplayed(),"Support Project Displayed", "Support project not displayed");
			logInfo("Searching for the ActiveButton : " + plActiveProjectPage.AppliedFilters.get(1).getText());
			Equals(op.getText(plActiveProjectPage.AppliedFilters.get(1)),"Support Project","Support project is present","Support project is not present ");
			op.waitImplicitely(driver, 60);
			op.doubleClickElement(plActiveProjectPage.AppliedFilters.get(0));
			op.waitImplicitely(driver, 60);
			Assert.assertNotEquals(plActiveProjectPage.AppliedFilters.get(0), "Fixed Project", "Filter did not disappear");
			if(elementPresent&&elementPresent1)
			{
				logInfo("Fixed and Support Project displayed"); 
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
			}
			else
			{
				 logError("Fail to display Project"); 
				 ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			}
			  logInfo("End of Test Case : " + tcID );
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



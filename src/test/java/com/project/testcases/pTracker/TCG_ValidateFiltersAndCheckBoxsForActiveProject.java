package com.project.testcases.pTracker;

import java.io.IOException;
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
import com.project.pageobjects.pTracker.CommonPages;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PL_ActiveProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;
import com.project.utilities.ExcelReader;

//Aishwarya Bachhav
public class TCG_ValidateFiltersAndCheckBoxsForActiveProject extends TestBase {
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	Operations op;
	ControlActions controlActions;
	CommonPages commonPage;
	private String uName = "admin";
	private String uPassword = "admin";
	private String xpath;
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";
	PL_ActiveProjectsPage plActiveProjectPage;
	ExcelReader reader;
	String workspace = System.getProperty("user.dir");
	String datapoolPath = workspace + "\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {
		driver = launchbrowser();
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
		commonPage=new CommonPages(driver);
		wait = new WebDriverWait(driver, DELAY);
		controlActions = new ControlActions(driver);
		plActiveProjectPage = new PL_ActiveProjectsPage(driver);
		// wait = new WebDriverWait(driver, 20000);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		op = new Operations(driver);
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		loginPage.waitForPageLoaded(driver);
		loginPage.TC_Login(driver, uName, uPassword);
		loginPage.TC_ChangeUser(driver, eName);
		
	}

	@Test(groups = {"sanity", "UI"}, priority = 1 ,description = "Validate Functionalites on Active Project")
	public void TC_ValidateActiveProjectType() throws Exception {
		reader = new ExcelReader();
		int tcRowNum;
		String projectState = "SUBMIT";
		String workspace = System.getProperty("user.dir");
		String sheetName = "Automation";
		int header = 0;
		tcRowNum = 3;
		tcRowNum = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase","TC_VALIDATE_ACTIVE_PROJECT_STATUS_FILTER");

		op.clickElement(newProject.ActiveProjectTabLink);
		wait.until(ExpectedConditions.visibilityOf(newProject.ActiveProjectTabFocused));
		Thread.sleep(3000);

		for (int i = 0; i < plActiveProjectPage.AppliedProjectTypeFilters.size(); i++) {
			logInfo("Searching for the ActiveProjectFilters : "+ plActiveProjectPage.AppliedProjectTypeFilters.get(i).getText());
			Thread.sleep(3000);
		}
		IsTrue(plActiveProjectPage.AppliedProjectTypeFilters.get(1).isDisplayed(), "Support Project is present","Support Project is not Displayed");
		logInfo("Searching for the Support Project : "+ plActiveProjectPage.AppliedProjectTypeFilters.get(1).getText());
		plActiveProjectPage.AppliedProjectTypeFilters.get(1).click();
		Thread.sleep(3000);
		for (int i = 0; i < plActiveProjectPage.AppliedCustomerTypeFilterCol.size(); i++) 
		{
			logInfo("Searching for the ActiveCustomerFilters : " + plActiveProjectPage.AppliedCustomerTypeFilterCol.get(i).getText());
			}
		logInfo("Customer filter: " + plActiveProjectPage.AppliedCustomerTypeFilterCol.get(3).getText());
		logInfo("VALIDATING THE APPLIED FILTER");

		logInfo("Project Type filter: " + plActiveProjectPage.AppliedFixedPriceFilterRow.get(3).getText());
		logInfo("Status Filter: " + plActiveProjectPage.AppliedFixedPriceFilterRow.get(9).getText());

		boolean projectFilter = plActiveProjectPage.AppliedFixedPriceFilterRow.get(3).isDisplayed();
		boolean statusFilter = plActiveProjectPage.AppliedFixedPriceFilterRow.get(9).isDisplayed();
		boolean customerFilter = plActiveProjectPage.AppliedCustomerTypeFilterCol.get(3).isDisplayed();
		
		logInfo("VALIDATING THE ACTIVE FILTER HEADERS ROW ");
		for (int i = 0; i < plActiveProjectPage.ActiveProjectFilterHeadersRow.size(); i++) {
			logInfo("Searching for the ActiveProjectFiltersHeadersRow : "+ plActiveProjectPage.ActiveProjectFilterHeadersRow.get(i).getText());
			threadsleep(2000);
		}
		Assert.assertEquals(plActiveProjectPage.ActiveProjectFilterHeadersRow.size(), 10, "Count is same");
		
		boolean status = false;
		for (WebElement ele : plActiveProjectPage.ActiveProjectFilterHeadersRow) {
			String value = ele.getText();
			System.out.println(value);
			if (value.contains("Project Manager")) {
				status = true;
				break;
			}
		}
		IsTrue(status, "Header is present", "Header not present");
		threadsleep(3000);

		if (projectFilter && statusFilter&&customerFilter) {
			logInfo("ProjectType ,Staus and Customer Type Filter displayed");
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} else {
			logError("Fail to display Filter");
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
		}
	}
	
	@Test(groups = {"sanity", "UI"}, priority = 2, description = "Validate Filter Applied on Filter Check Box marked Active Project Tab")
	public void verifyAppliedFilterFromBoxAndDropdownForActiveProject() {
		int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath, sheetName, "testCase","TC_VALIDATE_APPLIED_FILTER_AND_CHECKBOX_MARKED_FOR_ACTIVE_PROJECT");
			int counter=0;
			threadsleep(3000);
			for (int i = 0; i < 4; i++) {
				logInfo("Filter Number: " + i);
				//apex-item-option is-checked
				boolean isFilterCheckBoxOn = commonPage.verifyFilterCheckBox(plActiveProjectPage.ActiveProjectSelector.get(i),plActiveProjectPage.ActiveFilterCheckbox, plActiveProjectPage.ActiveFilterButton);
				IsTrue(isFilterCheckBoxOn,"Applied Filter is Checked in filter Box", "Applied Filter is not Checked in filter Box");
				op.clickElement(driver, "//div[@class='ui-widget-overlay ui-front']");
				counter++;
			}
			threadsleep(3000);
			if(counter== 4)
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Throwable e) {
			e.printStackTrace();
			logError("Error occurred during execution of test " + e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Fail("verifyAppliedFilterFromBoxAndDropdownForActiveProject Failed" );
		
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
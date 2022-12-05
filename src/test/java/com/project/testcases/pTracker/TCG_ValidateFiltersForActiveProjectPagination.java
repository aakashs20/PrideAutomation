package com.project.testcases.pTracker;

import java.io.IOException;
import java.util.List;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.util.SystemOutLogger;
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
public class TCG_ValidateFiltersForActiveProjectPagination extends TestBase {
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	Operations op;
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
		plActiveProjectPage = new PL_ActiveProjectsPage(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		op = new Operations(driver);
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		loginPage.waitForPageLoaded(driver);
		loginPage.TC_Login(driver, uName, uPassword);
		loginPage.TC_ChangeUser(driver, eName);
	}

	@Test(groups = {"sanity", "UI"}, description = "Validate Active Project Pagination")
	public void TC_ValidateActiveNoDataFound() throws Exception {
		reader = new ExcelReader();
		int tcRowNum;
		String projectState = "SUBMIT";
		String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace + "\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		String sheetName = "Automation";
		int header = 0;
		tcRowNum = 3;
		tcRowNum = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase",	"TC_VALIDATE_ACTIVE_PROJECT_PAGINATION");

		op.clickElement(newProject.ActiveProjectTabLink);
		wait.until(ExpectedConditions.visibilityOf(newProject.ActiveProjectTabFocused));
		logInfo("........Validating Filters.........");

		for (int i = 0; i < plActiveProjectPage.AppliedProjectTypeFilters.size(); i++) {
			logInfo("Searching for the ActiveProjectFilters : "
					+ plActiveProjectPage.AppliedProjectTypeFilters.get(i).getText());
			threadsleep(2000);
		}

		IsTrue(plActiveProjectPage.AppliedProjectTypeFilters.get(2).isDisplayed(), "Staffing project displayed",
				"Staffing Project is not Displayed");
		threadsleep(3000);
		op.click(plActiveProjectPage.AppliedProjectTypeFilters.get(2));
		threadsleep(3000);
		int size = plActiveProjectPage.ActiveProjectPagination.size();
		if (size > 0) {
			logInfo("Pages are present");
		} else {
			logInfo("No Pages are present");
		}
		for (int i = 0; i < plActiveProjectPage.ActiveProjectPagination.size(); i++) {
			logInfo("Searching for the Pagination: " + plActiveProjectPage.ActiveProjectPagination.get(i).getText());
			threadsleep(2000);
		}
		boolean pagePrsent = plActiveProjectPage.ActiveProjectPagination.get(1).isDisplayed();

		if (pagePrsent) {
			logInfo("Pagination Present");
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} else {
			logError("Pagination not Present");
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
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

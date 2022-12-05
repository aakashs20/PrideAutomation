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
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PL_ActiveProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;
import com.project.utilities.ExcelReader;

//Aishwarya Bachhav
public class TCG_ValidateFiltersForActiveProjectData extends TestBase {
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

	@Test(groups = {"sanity", "UI"}, description = "Validate on Active Project Data")
	public void TC_ValidateActiveProjectData() throws Exception {
		reader = new ExcelReader();
		int tcRowNum;
		String projectState = "SUBMIT";
		String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace + "\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		String sheetName = "Automation";
		int header = 0;
		tcRowNum = 3;
		tcRowNum = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", "TC_VALIDATE_ACTIVE_DATA");

		op.clickElement(newProject.ActiveProjectTabLink);
		wait.until(ExpectedConditions.visibilityOf(newProject.ActiveProjectTabFocused));
		threadsleep(2000);
		IsTrue(plActiveProjectPage.ActiveFilterButton.isDisplayed(), "filter button displayed",	"filter button is not displayed");
		logInfo("Clicking on ActiveFilterButton : " + plActiveProjectPage.ActiveFilterButton.getText());
		op.clickElement(plActiveProjectPage.ActiveFilterButton);
		threadsleep(2000);

		for (int i = 0; i < plActiveProjectPage.ActiveFilterHeader.size(); i++) {
			logInfo("Searching for the ActiveProjectFilters : "+ plActiveProjectPage.ActiveFilterHeader.get(i).getText());
			threadsleep(2000);
		}
		logInfo("Clicking On The CheckBox");
		plActiveProjectPage.ActiveFilterCheckbox.get(3).click();
		threadsleep(2000);
		op.click(plActiveProjectPage.ActiveFilterCheckbox.get(4));
		threadsleep(2000);
		op.click(plActiveProjectPage.ActiveFilterCheckbox.get(10));
		threadsleep(2000);
		logInfo("Data Present After Clicking on CheckBoxs");
		for (int i = 0; i < plActiveProjectPage.ActiveFilterData.size(); i++) {
			logInfo("Searching for the ActivePresentProjectData : " + plActiveProjectPage.ActiveFilterData.get(i).getText());
			threadsleep(2000);
		}
		boolean presentData = plActiveProjectPage.ActiveFilterData.get(1).isDisplayed();
		Equals(op.getText(plActiveProjectPage.ActiveFilterData.get(3)), "Time and Material",
				"Time and material is present", "Time and material not present");
		if (presentData) {
			logInfo("Present Data displayed");
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} else {
			logError("Fail to display Data");
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

package com.project.testcases.pTracker;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.CommonPages;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class TCG_GetProjectHistory extends TestBase {
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	ControlActions controlActions;
	Operations op;
	CommonPages cp;
	private String userName = "admin";
	private String userPassword = "admin";
	private String empName = "Mahajan, Milind";
	private static final int DELAY = 10;
	String projectNumber;
	String projectState;
	String projectStatus = "Pending Extension by PMO";
	List<String> ElementsList;
	String workspace = System.getProperty("user.dir");
	String dataControlPath = workspace + "\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	int tcRowNum1;
	String tcID1 = "TC_GET_PROJECT_HISTORY_FOR_NON_ACTIVE_PROJECTS";
	boolean isTestSkiped;

	@BeforeClass
	public void groupInit() throws Exception {
		System.setProperty("webdriver.chrome.silentOutput", "true");
		// ****** Launching the Web browser ******//
		driver = launchbrowser();
		String currentWindow = driver.getWindowHandle();
		driver.switchTo().window(currentWindow);
		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, DELAY);
		// ****** Instantiate Class Variables ******//
		op = new Operations(driver);
		controlActions = new ControlActions(driver);
		loginPage = new PTrackerLoginPage(driver);
		loginPage.waitForPageLoaded(driver);
		cp = new CommonPages(driver);
		// ****** Access the Excel sheet ******//
		tcRowNum1 = ExcelUtils.getRowNum(dataControlPath, "Automation", "testCase", tcID1);
		projectNumber = prop.getProperty("projectID");
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		// ****** Login ******//
		logInfo("Login to Pride application");
		loginPage.TC_Login(driver, userName, userPassword);
		// ****** Change User ******//
		logInfo("Change authorized user");
		loginPage.TC_ChangeUser(driver, empName);
		// ****** Search for the Project in the list ******//
		logInfo("Search Active Project");
		cp.searchActiveProject(driver, projectNumber);
	}

	@Test(priority = 1, groups = { "sanity",
			"UI" }, description = "Validate Project History for Non Active Project with Valid Input")
	public void getProjectHistoryforNonActiveProjects() {
		logInfo("Reading Excel:   " + dataControlPath);
		logInfo("Test Case Row No Is: " + tcRowNum1);
		logInfo("Starting of Test Case: " + tcID1);
		try {
			// ****** Calling method to get row details ******//
			logInfo("Entering to the searched project for valid inputs");
			cp.getProjectRow();
			// ****** Verification of Project Name & State ******//
			Boolean isProjectFound = cp.projectNumberActual.equalsIgnoreCase(projectNumber);
			Boolean isprojectState = cp.projectState.equalsIgnoreCase(projectStatus);
			logInfo("Verify searched project is found or not");
			IsTrue(isProjectFound, "Searched Project is found as: " + cp.projectNumberActual,
					"Searched Project is not found");
			logInfo("Verify existing status of project");
			IsTrue(isprojectState, "Current Status of Project is: " + cp.projectState, "Fail");
			// ****** Entering into the Non Active project ******//
			if (isProjectFound == true) {
				logInfo("Click on the serched project");
				op.clickElement(cp.ActiveProjectRow.get(0));
			} else {
				logInfo("Searched Project is not found");
				isTestSkiped = true;
			}

			// ****** Go to Project History tab ******//
			op.waitForElementToBeDisplayed(cp.HoverRight);
			op.hoverElement(cp.HoverRight);
			op.waitForElementToBeDisplayed(cp.ProjectHistory);
			op.clickElement(cp.ProjectHistory);

			// ****** Fetch the Project History ******//
			op.isElementDisplayed(cp.ProjectCreation);
			log("Get Project Creation Date");
			op.getText(cp.ProjectCreation);
			op.getText(cp.ProjectCreationDate);
			log("Get Project Extension Date");
			op.getText(cp.ProjectExtension);
			op.getText(cp.ProjectExtensionDate);
			log("Get Project Modification Date");
			op.getText(cp.ProjectModification);
			op.getText(cp.ProjectModificationDate);

			ExcelUtils.setCellData(dataControlPath, "Status", tcRowNum1, "PASS", "GREEN");

		} catch (Exception e) {
			e.printStackTrace();
			logError("Error " + e.getMessage());
			ExcelUtils.setCellData(dataControlPath, "Status", tcRowNum1, "FAIL", "RED");
			Assert.fail();
		}
		if (isTestSkiped) {
			ExcelUtils.setCellData(dataControlPath, "Status", tcRowNum1, "SKIP", "YELLOW");
			throw new SkipException("Skipping Test Exception");
		}
		logInfo("End of Test Case : " + tcID1);
	}

	@AfterClass(alwaysRun = true)
	public void ptrackerClose() {
		try {
			op.closeBrowser(driver);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

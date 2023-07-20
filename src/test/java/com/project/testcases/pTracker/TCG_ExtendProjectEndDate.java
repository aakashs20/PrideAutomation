package com.project.testcases.pTracker;

import java.util.HashMap;
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

public class TCG_ExtendProjectEndDate extends TestBase {
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
	String projectNumberActual;
	String extendedYear;
	String extendedMonth;
	String extendedDate;
	String commentText;
	String projectState;
	String projectStatus = "Active";
	String newProjectStatus = "Pending Extension by PMO";
	String dateErrorMsg = "Please select Extend End Date.";
	String commentErrorMsg = "Please add valid comments.";
	List<String> ElementsList;
	String workspace = System.getProperty("user.dir");
	String dataControlPath = workspace + "\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	int tcRowNum1;
	int tcRowNum2;
	String tcID1 = "TC_EXTEND_DATE_INVALID_INPUT_FOR_ACTIVE_PROJECTS";
	String tcID2 = "TC_EXTEND_DATE_VALID_INPUT_FOR_ACTIVE_PROJECTS";
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
		tcRowNum2 = ExcelUtils.getRowNum(dataControlPath, "Automation", "testCase", tcID2);
		HashMap<String, String> inputData = ExcelUtils.getTestDataXls(dataControlPath, "Automation", 0, tcRowNum1 - 1);
		projectNumber = prop.getProperty("projectID");
		extendedYear = inputData.get("EndYear");
		extendedMonth = inputData.get("EndMonth");
		extendedDate = inputData.get("EndDate");
		commentText = prop.getProperty("extendEndDateComments");
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
			"UI" }, description = "Validate Extend Date for Active Project with Invalid Input")
	public void extendDateInvalidInputforActiveProjects() {
		// ****** Entering into the test-data-files ******//
		logInfo("Reading Excel:   " + dataControlPath);
		logInfo("Test Case Row No Is: " + tcRowNum1);
		logInfo("Starting of Test Case: " + tcID1);
		try {
			// ****** Calling method to get row details ******//
			logInfo("Entering to the searched project for invalid inputs");
			cp.getProjectRow();
			// ****** Verification of Project Name & State ******//
			Boolean isProjectFound = cp.projectNumberActual.equalsIgnoreCase(projectNumber);
			Boolean isprojectState = cp.projectState.equalsIgnoreCase(projectStatus);
			logInfo("Verify searched project is found or not");
			IsTrue(isProjectFound, "Searched Project is found as: " + cp.projectNumberActual,
					"Searched Project is not found");
			logInfo("Verify existing status of project");
			IsTrue(isprojectState, "Current Status of Project is: " + cp.projectState, "Fail");
			// ****** Entering into the Active project ******//
			if (isProjectFound && isprojectState == true) {
				logInfo("Click on the serched project");
				op.clickElement(cp.ActiveProjectRow.get(0));
			} else {
				logInfo("Searched Project is not found");
				isTestSkiped = true;
			}

			// ****** Calling Date picker method ******//
			logInfo("Navigate to project extend end date");
			List<String> errorMsg = cp.projectEndDateCalender(driver, extendedYear, extendedMonth, null, null,
					"negative");
			// ****** Verification of Warning messages ******//
			op.Equals(errorMsg.get(0), dateErrorMsg, "Error message displayed as: " + errorMsg.get(0), "Fail");
			op.Equals(errorMsg.get(1), commentErrorMsg, "Error message displayed as: " + errorMsg.get(1), "Fail");

			logInfo("Navigate back to searched active project list");
			op.waitUntilElementIsClickable(cp.BackToActive);
			op.clickButton(cp.BackToActive);

			// ****** Verification of project status remain intact ******//
			logInfo("Verify new status of project");
			cp.getProjectRow();
			Boolean isnewprojectState = cp.projectState.equalsIgnoreCase(newProjectStatus);
			IsFalse(isnewprojectState, "New Status of Project is: " + cp.projectState,
					"Project Status is still 'Active'");
			if (isnewprojectState == false) {
				logInfo("Test was Successfully Passed");
				ExcelUtils.setCellData(dataControlPath, "Status", tcRowNum1, "PASS", "GREEN");
			} else {
				logInfo("Test Failed");
				ExcelUtils.setCellData(dataControlPath, "Status", tcRowNum1, "FAIL", "RED");
			}

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

	@Test(priority = 2, groups = { "sanity",
			"UI" }, description = "Validate Extend Date for Active Project with Valid Input")
	public void extendDateValidInputforActiveProjects() {
		logInfo("Reading Excel:   " + dataControlPath);
		logInfo("Test Case Row No Is: " + tcRowNum2);
		logInfo("Starting of Test Case: " + tcID2);
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
			// ****** Entering into the Active project ******//
			if (isProjectFound == true) {
				logInfo("Click on the serched project");
				op.clickElement(cp.ActiveProjectRow.get(0));
			} else {
				logInfo("Searched Project is not found");
				isTestSkiped = true;
			}

			// ****** Calling Date picker method ******//
			logInfo("Navigate to project extend end date");
			List<String> passMsg = cp.projectEndDateCalender(driver, extendedYear, extendedMonth, extendedDate,
					commentText, "positive");
			// ****** Verification of added comment ******//
			op.Equals(passMsg.get(0), commentText, "Comments added as: " + passMsg.get(0), "No comment added");

			// ****** Verification of new project end date ******//
			logInfo("Fetch the New Project End date");
			op.waitForElementToBeDisplayed(cp.NewEndDate);
			String newExtendedDate = op.getText(cp.NewEndDate);
			Boolean isnewExtendedDate = newExtendedDate.contains(extendedDate);
			IsTrue(isnewExtendedDate, newExtendedDate, "Project end date is not extended");

			logInfo("Navigate back to searched active project list");
			op.waitUntilElementIsClickable(cp.BackToActive);
			op.clickButton(cp.BackToActive);

			// ****** Verification of project status after extend the project end date
			// ******//
			logInfo("Verify new status of project");
			cp.getProjectRow();
			Boolean isnewprojectState = cp.projectState.equalsIgnoreCase(newProjectStatus);
			IsTrue(isnewprojectState, "New Status of Project is: " + cp.projectState,
					"Project Status is still 'Active'");
			if (isnewprojectState == true) {
				logInfo("Test was Successfully Passed");
				ExcelUtils.setCellData(dataControlPath, "Status", tcRowNum2, "PASS", "GREEN");
			} else {
				logInfo("Test Failed");
				ExcelUtils.setCellData(dataControlPath, "Status", tcRowNum2, "FAIL", "RED");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logError("Error " + e.getMessage());
			ExcelUtils.setCellData(dataControlPath, "Status", tcRowNum2, "FAIL", "RED");
			Assert.fail();
		}
		if (isTestSkiped) {
			ExcelUtils.setCellData(dataControlPath, "Status", tcRowNum2, "SKIP", "YELLOW");
			throw new SkipException("Skipping Test Exception");
		}
		logInfo("End of Test Case : " + tcID2);
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

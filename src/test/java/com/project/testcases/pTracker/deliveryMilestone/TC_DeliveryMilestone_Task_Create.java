package com.project.testcases.pTracker.deliveryMilestone;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.Constants;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.CommonPages;
import com.project.pageobjects.pTracker.DeliveryMilestoneConstants;
import com.project.pageobjects.pTracker.DeliveryMilestonePage;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class TC_DeliveryMilestone_Task_Create extends TestBase {
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	DeliveryMilestonePage deliveryMilestonePageObj;
	ExcelUtils excel;
	Constants con;
	Operations op;
	CommonPages cp;
	Properties prop;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";

	private String searchProjectNumber;
	private String excelPath = System.getProperty("user.dir")+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Task.xls";
	private static int rowNum = 1;
	static int tcRowNum;
	String expectedErrorMsg[];
	int errorCnt;

	@BeforeClass
	public void groupInit() throws Exception {
		prop = new Properties();
		prop.load(new FileInputStream(Constants.configFile));
		searchProjectNumber = prop.getProperty("projectID");
		// setting up property to suppress the warning
		System.setProperty("webdriver.chrome.silentOutput", "true");
		driver = launchbrowser();
		String currentWindow = driver.getWindowHandle();
		driver.switchTo().window(currentWindow);
		// setting up property to suppress the warning
		System.setProperty("webdriver.chrome.silentOutput", "true");
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, DELAY);
		controlActions = new ControlActions(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		op = new Operations(driver);
		loginPage.waitForPageLoaded(driver);
		loginPage.TC_Login(driver, uName, uPassword);
		loginPage.TC_ChangeUser(driver, eName);
		deliveryMilestonePageObj = new DeliveryMilestonePage(driver);
		deliveryMilestonePageObj.verifyActiveProject(driver, searchProjectNumber);
		deliveryMilestonePageObj.verifyDeliveryMilestoneButton(driver);
	}

	@Test (priority = 1 , groups = {"sanity", "UI"}, description = "Validate Create Task")
	public void create_new_Task() throws Exception {
		String tcID = "TC_DELIVERY_MILESTONE_CREATE_TASK";
		logInfo("Starting of Test Case : " + tcID);
		excel = new ExcelUtils();
		int rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Create", "Test case",tcID);
		String milestoneId = deliveryMilestonePageObj.createMilestone();
		//threadsleep(3000);
		String status = deliveryMilestonePageObj.createNewTask(milestoneId);
		try {
			IsTrue(status != null, "Test case '" + tcID + "' is passed", "Test case '" + tcID + "' is failed");
			ExcelUtils.setCellData(excelPath, "Create", rowNum - 1, "TC_Status", "PASS", "GREEN");
			try {
				excel.logTestResult(tcID, "PASS");
			} catch (Exception e1) {
				logError(e1.getMessage());
			}
		} catch (AssertionError e) {
			logError("Failed to add task: '" + e.getMessage() + "'");
			ExcelUtils.setCellData(excelPath, "Create", rowNum - 1, "TC_Status", "FAIL", "RED");
			try {
				excel.logTestResult(tcID, "FAIL");
			} catch (Exception e1) {
				logError(e1.getMessage());
			}
		} catch (Exception e) {
			logError("Failed to add task: '" + e.getMessage() + "'");
			ExcelUtils.setCellData(excelPath, "Create", rowNum - 1, "TC_Status", "FAIL", "RED");
			try {
				excel.logTestResult(tcID, "FAIL");
			} catch (Exception e1) {
				logError(e1.getMessage());
			}
		}
		logInfo("End of Test Case : " + tcID);
	}

//	@DataProvider
//	public Object[][] readTaskData() throws Exception {
//		rowNum = ExcelUtils.getRowNumXSSF(excelPath, "Create", "Test case", "TC_1_task_ID_blank");
//		tcRowNum = rowNum - 1;
//		Object[][] testObjArray = ExcelUtils.getTableArray(System.getProperty("user.dir") + "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Task.xlsx",
//				"Create", tcRowNum);
//		return (testObjArray);
//	}
//
//	@Test(dataProvider = "readTaskData", priority = 1)
//	public void verify_negative_scenarios(String taskID, String taskName, String startDate, String endDate,
//			String parentMilestone) {
//		String tcID = null;
//		try {
//			tcID = ExcelUtils.getCellData(tcRowNum, 0, excelPath);
//		} catch (Exception e1) {
//			logError(e1.getMessage());
//		}
//		logInfo("Starting of Test Case : " + tcID);
//		// try {
//		boolean isAddDeliveryMilestoneButtonClicked = deliveryMilestonePageObj.clkAddDeliveryMilestoneButton(); // User
//		IsTrue(isAddDeliveryMilestoneButtonClicked, "Successfully clicked on Add Delivery Milestone buttons","Failed to click on Add Delivery Milestone button.");
//		boolean isTaskOptionClicked = deliveryMilestonePageObj.clkTaskOption();
//		IsTrue(isTaskOptionClicked, "Release option selected", "Failed to click on Release option.");
//		// threadsleep(2000);
//		boolean isSwitchedToAddTaskFrame = deliveryMilestonePageObj.switchToAddFrame();
//		IsTrue(isSwitchedToAddTaskFrame, "Switched to add task frame.", "Failed to switch to add task frame.");
//		logInfo("Task ID: " + taskID);
//		logInfo("Task Name " + taskName);
//		logInfo("Start date: " + startDate);
//		logInfo("End Date " + endDate);
//		logInfo("Parent milestone " + parentMilestone);
//
//		errorCnt = 0;
//		expectedErrorMsg = new String[5];
//		op = new Operations(driver);
//		if (parentMilestone.equals("")) {
//			this.expectedErrorMsg = deliveryMilestonePageObj.getParentMilestoneExpectedMsg(expectedErrorMsg, errorCnt);
//			errorCnt++;
//
//		} else {
//			boolean isParentSelected = deliveryMilestonePageObj.enterParentMilestone(driver, parentMilestone);
//			IsTrue(isParentSelected, "Successfully selected parent milestone", "Failed to select parent milestone.");
//		}
//		if (taskID.equals("") || op.checkSpecialChar(taskID) || taskID.equalsIgnoreCase("null")
//				|| op.checkOnlyCharacter(taskID)) {
//			this.expectedErrorMsg = deliveryMilestonePageObj.getTaskIdExpectedMsg(taskID, expectedErrorMsg, errorCnt);
//			errorCnt++;
//		} else {
//			boolean isTaskIDSet = deliveryMilestonePageObj.enterTextTaskID(taskID);
//			IsTrue(isTaskIDSet, "Entered task id: " + taskID, "Failed to add text to task ID as '" + taskID + "'");
//		}
//		if (taskName.equals("") || op.checkSpecialChar(taskName) || NumberUtils.isNumber(taskName)
//				|| taskName.equalsIgnoreCase("null")) {
//			this.expectedErrorMsg = deliveryMilestonePageObj.getTaskNameExpectedMsg(taskName, expectedErrorMsg,
//					errorCnt);
//			errorCnt++;
//		} else {
//			boolean isTaskNameSet = deliveryMilestonePageObj.enterTextTaskName(taskName);
//			IsTrue(isTaskNameSet, "Entered task id: " + taskName,
//					"Failed to add text to task ID as '" + taskName + "'");
//		}
//		if (startDate.equals("")) {
//			this.expectedErrorMsg = deliveryMilestonePageObj.getStartDateExpectedMsg(startDate, expectedErrorMsg,
//					errorCnt);
//			errorCnt++;
//		} else {
//			boolean isStartDateCalendarClicked = deliveryMilestonePageObj.clkStartDateCalendar();
//			IsTrue(isStartDateCalendarClicked, "Clicked on Start Date Calendar.",
//					"Failed to click on Start Date Calendar.");
//			boolean isStartDateClicked = deliveryMilestonePageObj.clkStartDate();
//			IsTrue(isStartDateClicked, "Clicked on Start Date.", "Failed to click on Start Date.");
//		}
//		if (endDate.equals("")) {
//			expectedErrorMsg = deliveryMilestonePageObj.getEndDateExpectedMsg(endDate, expectedErrorMsg, errorCnt);
//			errorCnt++;
//		} else {
//			boolean isEndDateCalendarClicked = deliveryMilestonePageObj.clkEndDateCalendar();
//			IsTrue(isEndDateCalendarClicked, "Clicked on end date Calendar", "Failed to click on End Date Calendar.");
//			boolean isEndDateClicked = deliveryMilestonePageObj.clkEndDate();
//			IsTrue(isEndDateClicked, "Clicked on end date", "Failed to click on End Date.");
//		}
//		logInfo("Expected error count is " + errorCnt);
//		boolean isAddButtonOnAddTaskClicked = deliveryMilestonePageObj.clkAddButton();
//		IsTrue(isAddButtonOnAddTaskClicked, "Clicked on Add button",
//				"Failed to click on Add button on Add Milestone page.");
//		String errorTitle = deliveryMilestonePageObj.getErrorTitle();
//		try {
//			if (errorCnt > 1) {
//				Equals(errorTitle, +errorCnt + " errors have occurred",
//						"Error notification is displayed as expected: " + errorTitle,
//						"Error notification is not displayed as expected: " + errorTitle);
//			} else {
//				Equals(errorTitle, +errorCnt + " error has occurred",
//						"Error notification is displayed as expected: " + errorTitle,
//						"Error notification is not displayed as expected: " + errorTitle);
//			}
//		} catch (AssertionError e) {
//			logError("Error notification is not displayed as expected.");
//			deliveryMilestonePageObj.clkCancelButtonOnAddMilestoneFrame();
//			op.switchToDefault();
//			ExcelUtils.setCellData("TC_DeliveryMilestone_Task", "Create", tcRowNum, "TC_Status", "FAIL", "RED");
////			try {
////				excel.logTestResult(tcID, "FAIL");
////			} catch (Exception e1) {
////				logError(e1.getMessage());
////			}
//		}
//		String errormsgs[][] = new String[errorCnt][2];
//		errormsgs = deliveryMilestonePageObj.getErrorMessages(errorCnt, expectedErrorMsg);
//		for (int i = 0; i < errormsgs.length; i++) {
//			logInfo("errormsgs[i][0]: " + errormsgs[i][0]);
//			logInfo("errormsgs[i][1]: " + errormsgs[i][1]);
//		}
//		for (int i = 0; i < errormsgs.length; i++) {
//			try {
//				Equals(errormsgs[i][0], errormsgs[i][1],
//						"Error notification is displayed as expected: " + errormsgs[i][0],
//						"Error notification is not displayed as expected");
//			} catch (AssertionError e) {
//				logError("Error notification is not displayed as expected.");
//				deliveryMilestonePageObj.clkCancelButtonOnAddMilestoneFrame();
//				// op.switchToDefault();
//				ExcelUtils.setCellData("TC_DeliveryMilestone_Task", "Create", tcRowNum, "TC_Status", "FAIL", "RED");
////				try {
////					excel.logTestResult(tcID, "FAIL");
////				} catch (Exception e1) {
////					logError(e1.getMessage());
////				}
//			}
//		}
//		tcRowNum++;
//		logInfo("End of Test Case : " + tcID);
//		deliveryMilestonePageObj.clkCancelButtonOnAddMilestoneFrame();
//		// op.switchToDefault();
//	}
	
	

//			try {
//				IsTrue(status, "Test case '" + tcID + "' is passed", "Test case '" + tcID + "' is failed");
//				ExcelUtils.setCellData("TC_DeliveryMilestone_Release", "Create", tcRowNum, "TC_Status", "PASS",
//						"GREEN");
//				try {
//					excel.logTestResult(tcID, "PASS");
//				} catch (Exception e) {
//					logError(e.getMessage());
//				}
//			} catch (AssertionError e) {
//
//				logError("Error notification is not displayed as expected.");
//				ExcelUtils.setCellData("TC_DeliveryMilestone_Release", "Create", tcRowNum, "TC_Status", "FAIL", "RED");
//				try {
//					excel.logTestResult(tcID, "FAIL");
//				} catch (Exception e1) {
//					logError(e1.getMessage());
//				}
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		tcRowNum++;
//		logInfo("End of Test Case : " + tcID);

	@AfterClass
	public void closeBrowser() throws Exception {
			driver.close();
	}
}

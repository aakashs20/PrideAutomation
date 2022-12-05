package com.project.testcases.pTracker.deliveryMilestone;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
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

public class TC_DeliveryMilestone_Task_Delete extends TestBase {

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

	private String searchProjectNumber = "KJANDVCL";
	private String milestoneID;
	private String datapoolPath = System.getProperty("user.dir")+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	private String excelPath = System.getProperty("user.dir")+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Task.xls";
	private String taskID;
	private int errorCnt;
	private String[] expectedErrorMsg;
	private static int rowNum = 2;

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
		milestoneID = deliveryMilestonePageObj.createMilestone();
		//milestoneID = "MILEID365";
		taskID = deliveryMilestonePageObj.createNewTask(milestoneID);
		IsTrue(taskID != null, "Task is successfully created", "Failed to add task");
		threadsleep(1000);
	}

	@DataProvider
	public Object[] getReasonCodes() throws Exception {
		String reasonCodeList = ExcelUtils.getCellDataX(excelPath, 2, "Reason code", "TC_DELIVERY_MILESTONE_DELETE_TASK");
		String reasonCodes[] = reasonCodeList.split(";");
		return (reasonCodes);
	}

	@Test(dataProvider = "getReasonCodes")
	public void deleteTask(String reasonCode) throws Exception {
//		rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Delete", "Test case", "TC_DELIVERY_MILESTONE_DELETE_TASK");
//		String tcID = ExcelUtils.getCellDataX(rowNum, 0, excelPath, "Delete");
//		logInfo("Starting of Test Case : " + tcID);
		
		String tcID = ExcelUtils.getCellDataX(rowNum, 0, excelPath, "Delete");
		rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Delete", "testCase", tcID);
		logInfo("Starting of Test Case : " + tcID);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		try {
			WebElement mileStonePlusIcon;
			mileStonePlusIcon = op.perform_waitUntilVisibility(By.xpath(DeliveryMilestoneConstants.VIEW_RELEASE_ICON.replace("MilestoneIDToBeReplaced", milestoneID)));
			op.click(mileStonePlusIcon);
			threadsleep(1000);
			WebElement releaseIcon;
			controlActions.click(controlActions.performGetElementByXPath(DeliveryMilestoneConstants.DELETE_RELEASE_BUTTON.replace("releaseIDToBeUpdated", taskID)));
			boolean isSwitchedToDeleteMilestoneFrame = deliveryMilestonePageObj.switchToDeleteMilestoneFrame();
			IsTrue(isSwitchedToDeleteMilestoneFrame, "Switched to delete frame successfully","Failed to switch to delete frame");
			String statusUpdate = ExcelUtils.getCellDataX(excelPath, 2, "Status Update", tcID);
			boolean isStatusUpdateSet = deliveryMilestonePageObj.enterTextToStatusUpdateField(statusUpdate);
			IsTrue(isStatusUpdateSet, "Entered status update", "Failed to enter status update: " + statusUpdate);
			controlActions.waitForElementToBeClickable(deliveryMilestonePageObj.reasonCodeDrpDown);
			controlActions.clickElement(deliveryMilestonePageObj.reasonCodeDrpDown);
			driver.switchTo().defaultContent();
			deliveryMilestonePageObj.selectReasonCode(reasonCode);
			boolean isSwitchedToDeleteMilestoneFrame1 = deliveryMilestonePageObj.switchToDeleteMilestoneFrame();
			IsTrue(isSwitchedToDeleteMilestoneFrame1, "Switched to delete milestone frame","Failed to switch to delete frame");
			boolean isButtonClicked = deliveryMilestonePageObj.clkObsoleteButton();
			IsTrue(isButtonClicked, "Clicked on Obsolete button", "Failed to delete milestone");
			op.switchToDefault();
			//deliveryMilestonePageObj.verifySuccessMsg();
			boolean isMsgDisplayed = deliveryMilestonePageObj.verifySuccessMsg();
			IsTrue(isMsgDisplayed, "Phase deleted successfully", "Failed to delete phase");
			if(isMsgDisplayed)
			{
				ExcelUtils.logTestResult(excelPath, "Delete", rowNum, "PASS", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "GREEN");
			}
			else
			{
				ExcelUtils.logTestResult(excelPath, "Delete", rowNum, "FAIL", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "FAIL", "RED");
			}
		} catch (Exception e) {
			ExcelUtils.logTestResult(excelPath, "Delete", rowNum, "FAIL", tcID);
			ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "FAIL", "RED");
			logError(e.getMessage());
			}
//		rowNum++;
		logInfo("End of Test Case : " + tcID);
	}

//	@DataProvider
//	public Object[][] readTaskDataFromExcel() throws Exception {
//		int tcRowNum = ExcelUtils.getRowNumXSSF(excelPath, "Delete", "Test case",
//				"TC_DeliveryMilestone_Delete_Task_StatusUpdateField_Blank");
//		rowNum = tcRowNum - 1;
//		Object[][] testObjArray = ExcelUtils.getTableArray(
//				System.getProperty("user.dir") + "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Task.xlsx",
//				"Delete", rowNum);
//		for (Object[] obj : testObjArray) {
//			for (Object str : obj) {
//				System.out.println(str);
//			}
//		}
//		return (testObjArray);
//	}
//
//	@Test(dataProvider = "readTaskDataFromExcel", priority = 2)
//	public void deleteTaskNegativeTcs(String reasonCode, String statusUpdate) throws Exception {
////		rowNum = ExcelUtils.getRowNumXSSF(excelPath, "Delete", "Test case",
////				"TC_DeliveryMilestone_Delete_Task_StatusUpdateField_Blank");
//		// int tcRowNum=rowNum-1;
//		String tcID = ExcelUtils.getCellData(rowNum, 0, excelPath);
//		logInfo("Starting of Test Case : " + tcID);
//		try {
//			WebElement releaseIcon;
//			controlActions.click(controlActions.performGetElementByXPath(
//					DeliveryMilestoneConstants.DELETE_RELEASE_BUTTON.replace("releaseIDToBeUpdated", taskID)));
//			boolean isSwitchedToDeleteMilestoneFrame = deliveryMilestonePageObj.switchToDeleteMilestoneFrame();
//			IsTrue(isSwitchedToDeleteMilestoneFrame, "Switched to delete frame successfully",
//					"Failed to switch to delete frame");
////			String statusUpdate = ExcelUtils.getCellData(excelPath, 2, "Status Update",
////					"TC_DeliveryMilestone_Delete_Task_StatusUpdateField_Blank");
//			logInfo("Status update: " + statusUpdate);
////			String reasonCode = ExcelUtils.getCellData(excelPath, 2, "Reason code",
////					"TC_DeliveryMilestone_Delete_Task_ReasonCodeField_Blank");
//			logInfo("Reason code: " + reasonCode);
//			errorCnt = 0;
//			expectedErrorMsg = new String[2];
//			if (reasonCode.equals("")) {
//				expectedErrorMsg[errorCnt] = "Enter Reason Code for making this Milestone Obsolete.";
//				logInfo("Expected error message: Enter Reason Code for making this Milestone Obsolete.");
//				errorCnt++;
//			} else {
//				controlActions.waitForElementToBeClickable(deliveryMilestonePageObj.reasonCodeDrpDown);
//				controlActions.clickElement(deliveryMilestonePageObj.reasonCodeDrpDown);
//				controlActions.switchToDefault();
//				boolean reasonCodeSelected = deliveryMilestonePageObj.selectReasonCode(reasonCode);
//				IsTrue(reasonCodeSelected, "Reason code selected: " + reasonCode, "Failed to select reason code");
//				boolean isSwitchedToDeleteMilestoneFrame1 = deliveryMilestonePageObj.switchToDeleteMilestoneFrame();
//				IsTrue(isSwitchedToDeleteMilestoneFrame1, "Switched to delete frame",
//						"Failed to switch to delete frame");
//			}
//			if (statusUpdate.equals("") || statusUpdate.equalsIgnoreCase("null")) {
//				expectedErrorMsg[errorCnt] = "Enter Status Update for making this Milestone Obsolete.";
//				logInfo("Expected error message: Enter Status Update for making this Milestone Obsolete.");
//				errorCnt++;
//			} else {
//				boolean isStatusUpdateSet = deliveryMilestonePageObj.enterTextToStatusUpdateField(statusUpdate);
//				IsTrue(isStatusUpdateSet, "Status updated as: " + statusUpdate,
//						"Failed to update status as : " + statusUpdate);
//			}
//			// ls.logTestResult("TC_DeliveryMilestone_Task", "Delete", rowNum, "PASS",
//			// tcID);
//
//			logInfo("Expected error count is " + errorCnt);
////				boolean isButtonClicked = deliveryMilestonePageObj.clkSaveChangesButton();
////				Assert.assertTrue(isButtonClicked, "Failed to edit changes");
//			String errorTitle = deliveryMilestonePageObj.getErrorTitle();
//			try {
//				if (errorCnt > 1) {
//					Equals(errorTitle, +errorCnt + " errors have occurred",
//							"Error notification is displayed as expected: " + errorTitle,
//							"Error notification is not displayed as expected: " + errorTitle);
//				} else {
//					Equals(errorTitle, +errorCnt + " error has occurred",
//							"Error notification is displayed as expected: " + errorTitle,
//							"Error notification is not displayed as expected: " + errorTitle);
//				}
//			} catch (AssertionError e) {
//				logError("Error notification is not displayed as expected.");
//				deliveryMilestonePageObj.clkCancelButtonOnObsoleteMilestoneFrame();
//				op.switchToDefault();
//				try {
//					ExcelUtils.logTestResult("TC_DeliveryMilestone_Task", "Delete", rowNum, "FAIL", tcID);
//				} catch (Exception e1) {
//					e1.printStackTrace();
//				}
//				throw e;
//			}
//			String errormsgs[][] = new String[errorCnt][2];
//			errormsgs = deliveryMilestonePageObj.getErrorMessages(errorCnt, expectedErrorMsg);
//			for (int i = 0; i < errormsgs.length; i++) {
//				logInfo("errormsgs[i][0]: " + errormsgs[i][0]);
//				logInfo("errormsgs[i][1]: " + errormsgs[i][1]);
//			}
//			for (int i = 0; i < errormsgs.length; i++) {
//				try {
//					Equals(errormsgs[i][0], errormsgs[i][1],
//							"Error notification is displayed as expected: " + errormsgs[i][0],
//							"Error notification is not displayed as expected");
//
//				} catch (AssertionError e) {
//					logError("Error notification is not displayed as expected.");
//					deliveryMilestonePageObj.clkCancelButtonOnObsoleteMilestoneFrame();
//					op.switchToDefault();
//					try {
//						ExcelUtils.logTestResult("TC_DeliveryMilestone_Task", "Delete", rowNum, "FAIL", tcID);
//					} catch (Exception e2) {
//						e2.printStackTrace();
//					}
//					try {
//						// excel.logTestResult(tcID, "FAIL");
//					} catch (Exception e1) {
//						e1.printStackTrace();
//					}
//					throw e;
//				}
//			}
//			rowNum++;
//			logInfo("End of Test Case : " + tcID);
//			deliveryMilestonePageObj.clkCancelButtonOnObsoleteMilestoneFrame();
//			op.switchToDefault();
//			try {
//				ExcelUtils.logTestResult("TC_DeliveryMilestone_Task", "Delete", rowNum, "PASS", tcID);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} catch (Exception e) {
//			logError(e.getMessage());
//			ExcelUtils.logTestResult("TC_DeliveryMilestone_Task", "Delete", rowNum, "FAIL", tcID);
//			try {
//				// excel.logTestResult(tcID, "FAIL");
//			} catch (Exception e1) {
//				logError(e.getMessage());
//			}
//		}
//	}
//
	
	@AfterClass
	public void closeBrowser() throws Exception {
			driver.close();
	}
}

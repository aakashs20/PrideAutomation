package com.project.testcases.pTracker.deliveryMilestone;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
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

public class TC_DeliveryMilestone_Phase_Update extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
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
//	private String searchProjectNumber = "TEST8899";
	private String searchProjectNumber ;
	private String datapoolPath = System.getProperty("user.dir")+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	private String excelPath = System.getProperty("user.dir")+"\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Phase.xls";
	private String phaseID;
	private String milestoneId;
	private String[] expectedErrorMsg;
	private int errorCnt;
	private static int rowNum =2;
	String tcID = "TC_DELIVERY_MILESTONE_UPDATE_PHASE";

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
		op = new Operations(driver);
		op.getUrl(prop.getProperty("appl_url_dev"));
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		loginPage.waitForPageLoaded(driver);
		loginPage.TC_Login(driver, uName, uPassword);
		loginPage.TC_ChangeUser(driver, eName);
		deliveryMilestonePageObj = new DeliveryMilestonePage(driver);
		deliveryMilestonePageObj.verifyActiveProject(driver, searchProjectNumber);
		deliveryMilestonePageObj.verifyDeliveryMilestoneButton(driver);
		milestoneId = deliveryMilestonePageObj.createMilestone();
		phaseID = deliveryMilestonePageObj.createNewPhase(milestoneId);
		IsTrue(phaseID != null, "Phase is successfully created", "Failed to add phase");
		// deliveryMilestonePageObj.clickExpandIcon(milestoneId);
	}

	@DataProvider
	public Object[] getStatus() throws Exception {
		//rowNum = ExcelUtils.getRowNumXSSF(excelPath, "Update", "Test case", "TC_DeliveryMilestone_Update_Phase");
		String statusList = ExcelUtils.getCellDataX(excelPath, 1, "State", "TC_DELIVERY_MILESTONE_UPDATE_PHASE");
		String status[] = statusList.split(";");
		return (status);
	}
	
	@Test(priority = 1, dataProvider = "getStatus", description = "To update Phase with different status")
	public void updatePhase(String status) throws IOException, Exception {
		String tcID = ExcelUtils.getCellDataX(rowNum, 0, excelPath, "Update");
		rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Update", "testCase", tcID);
		logInfo("Starting of Test Case : " + tcID);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		try {
			boolean updateIconClicked = deliveryMilestonePageObj.clickUpdateIcon(phaseID);
			IsTrue(updateIconClicked, "Update icon clicked.", "Failed to click on update icon.");
			boolean isSwitchedToUpdateReleaseFrame = deliveryMilestonePageObj.switchToUpdateFrame();
			IsTrue(isSwitchedToUpdateReleaseFrame, "Switched to update phase frame.","Failed to switch to update phase frame.");
			deliveryMilestonePageObj.updateStatus(status);
			String statusUpdate = ExcelUtils.getCellDataX(excelPath, 1, "Status Update",tcID);
			logInfo("Entering reson for the phase update is: " + statusUpdate);
			boolean isStatusUpdateSet = deliveryMilestonePageObj.enterTextToStatusUpdateField(statusUpdate);
			logInfo("Phase update reason entered successfully : " + isStatusUpdateSet);
			deliveryMilestonePageObj.clkSaveChangesButton();
			op.switchToDefault();
			deliveryMilestonePageObj.verifySuccessMsg();
//			plusIcon = op.perform_waitUntilVisibility(By.xpath(DeliveryMilestoneConstants.VIEW_RELEASE_ICON.replace("MilestoneIDToBeReplaced", milestoneId)));
//			op.click(plusIcon);
//			String releaseStatus = op.getText(op.performGetElementByXPath(DeliveryMilestoneConstants.ADDED_TASK_STATUS.replace("TaskIDToBeReplaced", phaseID)));
			deliveryMilestonePageObj.clickExpandIcon(milestoneId);
			String phaseStatus = deliveryMilestonePageObj.verifyStatus(phaseID);
			IsTrue(phaseStatus.contains(status), "Task status is correctly updated to: " + status,"Task status is not updated correctly");
			if(phaseStatus.contains(status))
			{
				ExcelUtils.logTestResult(excelPath, "Update", rowNum, "PASS", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "GREEN");
			}
			else
			{
				ExcelUtils.logTestResult(excelPath, "Update", rowNum, "FAIL", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "RED");
			}
		} catch (Exception e) {
			ExcelUtils.logTestResult(excelPath, "Update", rowNum, "FAIL", tcID);
			ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "RED");
			logError(e.getMessage());
		}
		  //rowNum++;
		  logInfo("\nEnd of Test Case : " + tcID);
	}

//	@DataProvider
//	public Object[][] readPhaseDataFromExcel() throws Exception {
//		int tcRowNum = ExcelUtils.getRowNumXSSF(excelPath, "Update", "Test case","TC_DeliveryMilestone_Update_Phase_StatusUpdateField_Blank");
//		rowNum = tcRowNum - 1;
//		Object[][] testObjArray = ExcelUtils.getTableArray(
//				System.getProperty("user.dir") + "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Phase.xlsx",
//				"Update", rowNum);
//		for (Object[] obj : testObjArray) {
//			for (Object str : obj) {
//				System.out.println(str);
//			}
//		}
//		return (testObjArray);
//	}
//
//	@Test(priority = 2, dataProvider = "readPhaseDataFromExcel", description = "Negative scenarios for update task", enabled = false)
//	public void updatePhaseNegativeScenarios(String status, String statusUpdate, String percentCompletion) {
//		String tcID = null;
//		try {
//			tcID = ExcelUtils.getCellData(rowNum, 0, excelPath, "Update");
//		} catch (Exception e1) {
//			logError(e1.getMessage());
//		}
//		logInfo("Starting of Test Case : " + tcID);
//		deliveryMilestonePageObj.clickUpdateIcon(phaseID);
//		boolean isSwitchedToUpdatePhaseFrame = deliveryMilestonePageObj.switchToUpdateFrame();
//		IsTrue(isSwitchedToUpdatePhaseFrame, "Switched to update phase frame.",
//				"Failed to switch to update phase frame.");
//		logInfo("Checking for status: " + status);
//		logInfo("Status update: " + statusUpdate);
//		logInfo("Percent completion " + percentCompletion);
//
//		errorCnt = 0;
//		expectedErrorMsg = new String[3];
//		op = new Operations(driver);
//		if (statusUpdate.equals("")) {
//			this.expectedErrorMsg = deliveryMilestonePageObj.getStatusUpdateExpectedMsg(expectedErrorMsg, errorCnt);
//			errorCnt++;
//			op.actionClearTextBox(deliveryMilestonePageObj.getStatusUpdateField());
//		} else {
//			boolean isStatusUpdateSet = deliveryMilestonePageObj.enterTextToStatusUpdateField(statusUpdate);
//			IsTrue(isStatusUpdateSet, "Entered text to status update field: " + statusUpdate,
//					"Failed to add text to status update field: " + statusUpdate);
//		}
//		if (percentCompletion.equals("")) {
//			this.expectedErrorMsg = deliveryMilestonePageObj.getPerComplExpectedMsg(expectedErrorMsg, errorCnt);
//			errorCnt++;
//			op.actionClearTextBox(deliveryMilestonePageObj.getReleasePercCompletion());
//		} else if (Integer.parseInt(percentCompletion) > 100) {
//			this.expectedErrorMsg = deliveryMilestonePageObj.getPerComplRangeExpectedMsg(expectedErrorMsg, errorCnt);
//			errorCnt++;
//			op.actionClearTextBox(deliveryMilestonePageObj.getReleasePercCompletion());
//			deliveryMilestonePageObj.enterTextToReleasePercCompletion(percentCompletion);
//		} else {
//			op.actionClearTextBox(deliveryMilestonePageObj.getReleasePercCompletion());
//			boolean isPercCompletionSet = deliveryMilestonePageObj.enterTextToReleasePercCompletion(percentCompletion);
//			IsTrue(isPercCompletionSet, "Entered Text to Percent completion field: " + percentCompletion,
//					"Failed to add text to Percent completion field: " + percentCompletion);
//			op.perform_waitUntilClickable(By.xpath(DeliveryMilestoneConstants.ACTUAL_START_DATE));
//		}
//		logInfo("Expected error count is " + errorCnt);
//		boolean isButtonClicked = deliveryMilestonePageObj.clkSaveChangesButton();
//		IsTrue(isButtonClicked, "Clicked on save changes button", "Failed to click on save changes button");
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
//			deliveryMilestonePageObj.clkCancelButton();
//			op.switchToDefault();
//			try {
//				ExcelUtils.logTestResult("TC_DeliveryMilestone_Phase", "Update", rowNum, "FAIL", tcID);
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			throw e;
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
//				if (errormsgs[i][0].equalsIgnoreCase("Completion % is not between the valid range of 0 and 100.")) {
//					op.switchToDefault();
//					String alertText = op.getText(deliveryMilestonePageObj.getPerCpmlErrorPopup());
//					Equals(alertText, "% Completion cannot be greater than 100.",
//							"Error message on alert is as expected", "Error message on alert is not as expected");
//					op.clickElement(deliveryMilestonePageObj.getOKButton());
//					deliveryMilestonePageObj.switchToUpdateFrame();
//				}
//
//			} catch (AssertionError e) {
//				logError("Error notification is not displayed as expected.");
//				deliveryMilestonePageObj.clkCancelButton();
//				op.switchToDefault();
//				try {
//					ExcelUtils.logTestResult("TC_DeliveryMilestone_Phase", "Update", rowNum, "FAIL", tcID);
//				} catch (Exception e2) {
//					e2.printStackTrace();
//				}
//				try {
//					// excel.logTestResult(tcID, "FAIL");
//				} catch (Exception e1) {
//					e1.printStackTrace();
//				}
//				throw e;
//			}
//		}
//
//		logInfo("End of Test Case : " + tcID);
//		deliveryMilestonePageObj.clkCancelButton();
//		op.switchToDefault();
//		try {
//			ExcelUtils.logTestResult("TC_DeliveryMilestone_Phase", "Update", rowNum, "PASS", tcID);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		rowNum++;
//	}

	@AfterClass
	public void closeBrowser() throws Exception {
			driver.close();
	}
}

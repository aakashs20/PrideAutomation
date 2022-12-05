package com.project.testcases.pTracker.deliveryMilestone;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class TC_DeliveryMilestone_Phase_Delete extends TestBase {

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
	private String milestoneID;
	private String datapoolPath = System.getProperty("user.dir")+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	private String excelPath = System.getProperty("user.dir")+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Phase.xls";
	private String phaseID;
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
		phaseID = deliveryMilestonePageObj.createNewPhase(milestoneID);
		IsTrue(phaseID != null, "Phase is successfully created", "Failed to create new phase");
	}

	@DataProvider
	public Object[] getReasonCodes() throws Exception {
		String reasonCodeList = ExcelUtils.getCellDataX(excelPath, 2, "Reason code","TC_DELIVERY_MILESTONE_DELETE_PHASE");
		String reasonCodes[] = reasonCodeList.split(";");
		return (reasonCodes);
	}
	
	@Test(priority = 1, dataProvider = "getReasonCodes", description = "Validate Delete Phase with different Reason Codes")
	public void deleteTask(String reasonCode) throws Exception, NullPointerException {
		phaseID = deliveryMilestonePageObj.createNewPhase(milestoneID);
		IsTrue(phaseID != null, "Phase is successfully created", "Failed to add phase");
		String tcID = ExcelUtils.getCellDataX(rowNum, 0, excelPath, "Delete");
		rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Delete", "testCase", tcID);
		logInfo("Starting of Test Case : " + tcID);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		try {
			boolean isDeleteIconClicked = deliveryMilestonePageObj.clickDeleteIcon(phaseID);
			IsTrue(isDeleteIconClicked, "Delete icon is clicked", "Failed to click on delete icon");
			boolean isSwitchedToDeleteMilestoneFrame = deliveryMilestonePageObj.switchToDeleteMilestoneFrame();
			IsTrue(isSwitchedToDeleteMilestoneFrame, "Switched to delete frame successfully","Failed to switch to delete frame");
			String statusUpdate = ExcelUtils.getCellDataX(excelPath, 2, "Status Update", tcID);
			logInfo("Status update: " + statusUpdate);
			boolean isStatusUpdateSet = deliveryMilestonePageObj.enterTextToStatusUpdateField(statusUpdate);
			IsTrue(isStatusUpdateSet, "Entered status update", "Failed to enter status update: " + statusUpdate);
			boolean isReasonCodeDropdownClicked = deliveryMilestonePageObj.clkReasonCodeDropDown();
			IsTrue(isReasonCodeDropdownClicked, "Reason code dropdown clicked to view available reason codes","Failed to click on reason code dropdown");
			boolean isSelected = deliveryMilestonePageObj.selectReasonCode(reasonCode);
			IsTrue(isSelected, "Reason code selected: " + reasonCode, "Failed to select reason code: " + reasonCode);
			boolean isSwitchedToDeleteMilestoneFrame1 = deliveryMilestonePageObj.switchToDeleteMilestoneFrame();
			IsTrue(isSwitchedToDeleteMilestoneFrame1, "Switched to delete milestone frame","Failed to switch to delete frame");
			boolean isButtonClicked = deliveryMilestonePageObj.clkObsoleteButton();
			IsTrue(isButtonClicked, "Clicked on Obsolete button", "Failed to delete milestone");
			// op.switchToDefault();
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
		  //rowNum++;
		  logInfo("End of Test Case : " + tcID);
		}



//	@DataProvider
//	public Object[][] readPhaseTestData() throws Exception {
//		int tcRowNum = ExcelUtils.getRowNumXSSF(excelPath, "Delete", "Test case",
//				"TC_DeliveryMilestone_Delete_Phase_StatusUpdateField_Blank");
//		rowNum = tcRowNum - 1;
//		Object[][] testObjArray = ExcelUtils.getTableArray(
//				System.getProperty("user.dir") + "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Phase.xlsx",
//				"Delete", rowNum);
//		return (testObjArray);
//	}
//
//	@Test(dataProvider = "readPhaseTestData", priority = 2)
//	public void deleteTaskNegativeTcs(String reasonCode, String statusUpdate) throws Exception {
//		phaseID = deliveryMilestonePageObj.createNewPhase(milestoneID);
////		WebElement releaseIcon = op.perform_waitUntilVisibility(
////				By.xpath(DeliveryMilestoneConstants.VIEW_RELEASE_ICON.replace("MilestoneIDToBeReplaced", milestoneID)));
////		op.click(releaseIcon);
//		String tcID = ExcelUtils.getCellData(rowNum, 0, excelPath, "Delete");
//		logInfo("Starting of Test Case : " + tcID);
//		try {
//			boolean isDeleteIconClicked = deliveryMilestonePageObj.clickDeleteIcon(phaseID);
//			IsTrue(isDeleteIconClicked, "Delete icon is clicked", "Failed to click on delete icon");
//			boolean isSwitchedToDeleteMilestoneFrame = deliveryMilestonePageObj.switchToDeleteMilestoneFrame();
//			IsTrue(isSwitchedToDeleteMilestoneFrame, "Switched to delete frame successfully",
//					"Failed to switch to delete frame");
//			logInfo("Status update: " + statusUpdate);
//			logInfo("Reason code: " + reasonCode);
//			errorCnt = 0;
//			expectedErrorMsg = new String[2];
//			if (reasonCode.equals("")) {
//				expectedErrorMsg = deliveryMilestonePageObj.getReasonCodeExpectedMsg("Phase", expectedErrorMsg,
//						errorCnt);
//				logInfo("Expected error message: Enter Reason Code for making this Phase Obsolete.");
//				errorCnt++;
//			} else {
//				boolean isReasonCodeDropdownClicked = deliveryMilestonePageObj.clkReasonCodeDropDown();
//				IsTrue(isReasonCodeDropdownClicked, "Reason code dropdown clicked to view available reason codes",
//						"Failed to click on reason code dropdown");
//				boolean isSelected = deliveryMilestonePageObj.selectReasonCode(reasonCode);
//				IsTrue(isSelected, "Reason code selected: " + reasonCode,
//						"Failed to select reason code: " + reasonCode);
//				boolean isSwitchedToDeleteMilestoneFrame1 = deliveryMilestonePageObj.switchToDeleteMilestoneFrame();
//				IsTrue(isSwitchedToDeleteMilestoneFrame1, "Switched to delete milestone frame",
//						"Failed to switch to delete frame");
//			}
//			if (statusUpdate.equals("") || statusUpdate.equalsIgnoreCase("null")) {
//				expectedErrorMsg = deliveryMilestonePageObj.getStatusUpdateExpectedMsg("Phase", expectedErrorMsg,
//						errorCnt);
//				logInfo("Expected error message: Enter Status Update for making this Phase Obsolete.");
//				errorCnt++;
//			} else {
//				boolean isStatusUpdateSet = deliveryMilestonePageObj.enterTextToStatusUpdateField(statusUpdate);
//				IsTrue(isStatusUpdateSet, "Status updated as: " + statusUpdate,
//						"Failed to update status as : " + statusUpdate);
//			}
//			boolean isButtonClicked = deliveryMilestonePageObj.clkObsoleteButton();
//			IsTrue(isButtonClicked, "Clicked on Obsolete button", "Failed to delete milestone");
//			logInfo("Expected error count is " + errorCnt);
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
//				try {
//					ExcelUtils.logTestResult("TC_DeliveryMilestone_Phase", "Delete", rowNum, "FAIL", tcID);
//					tcStatusFlag = 1;
//					excel.logTestResult("TC_DeliveryMilestone_Delete_Phase", "FAIL");
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
//					try {
//						ExcelUtils.logTestResult("TC_DeliveryMilestone_Phase", "Delete", rowNum, "FAIL", tcID);
//					} catch (Exception e2) {
//						e2.printStackTrace();
//					}
//					try {
//						tcStatusFlag = 1;
//						excel.logTestResult("TC_DeliveryMilestone_Delete_Phase", "FAIL");
//					} catch (Exception e1) {
//						e1.printStackTrace();
//					}
//					throw e;
//				}
//			}
//
//			logInfo("End of Test Case : " + tcID);
//			deliveryMilestonePageObj.clkCancelButtonOnObsoleteMilestoneFrame();
//			// op.switchToDefault();
//			try {
//				ExcelUtils.logTestResult("TC_DeliveryMilestone_Phase", "Delete", rowNum, "PASS", tcID);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} catch (Exception e) {
//			logError(e.getMessage());
//			ExcelUtils.logTestResult("TC_DeliveryMilestone_Phase", "Delete", rowNum, "FAIL", tcID);
//			try {
//				tcStatusFlag = 1;
//				excel.logTestResult("TC_DeliveryMilestone_Delete_Phase", "FAIL");
//			} catch (Exception e1) {
//				logError(e.getMessage());
//			}
//		}
//		rowNum++;
//	}

//	@AfterTest
//	public void closeBrowser() throws Exception {
//		if (tcStatusFlag == 0)
//			//excel.logTestResult("TC_DeliveryMilestone_Delete_Phase", "PASS");
//		driver.close();
//	}
	
	@AfterClass
	public void closeBrowser() throws Exception {
			driver.close();
	}
}

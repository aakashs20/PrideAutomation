package com.project.testcases.pTracker.deliveryMilestone;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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

public class TC_DeliveryMilestone_Phase_Create extends TestBase {
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
	private String datapoolPath = System.getProperty("user.dir")+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	private String excelPath = System.getProperty("user.dir")+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Phase.xls";
	private int rowNum = 1;
	int tcRowNum;
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

	@Test(priority = 1, description = "Validate add Phase")
	public void create_new_phase() throws Exception {
		//String tcID = "TC_DELIVERY_MILESTONE_CREATE_PHASE";
		String tcID = ExcelUtils.getCellDataX(rowNum, 0, excelPath, "Create");
		logInfo("Starting of Test Case : " + tcID);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		excel = new ExcelUtils();
		int rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Create", "testCase", "TC_DELIVERY_MILESTONE_CREATE_PHASE");
		String milestoneId = deliveryMilestonePageObj.createMilestone();
		try {
			int randomNum = op.generateRandomNum();
			String phaseID = "PHASEID" + randomNum;
			String phaseName = "PHASENAME" + randomNum;
			boolean isAddDeliveryMilestoneButtonClicked = deliveryMilestonePageObj.clkAddDeliveryMilestoneButton();
			IsTrue(isAddDeliveryMilestoneButtonClicked, "Successfully clicked on Add Delivery Milestone buttons","Failed to click on Add Delivery Milestone button.");
			boolean isPhaseOptionClicked = deliveryMilestonePageObj.clkPhaseOption();
			IsTrue(isPhaseOptionClicked, "Phase option selected", "Failed to click on phase option.");
			boolean isSwitchedToAddTaskFrame = deliveryMilestonePageObj.switchToAddFrame();
			IsTrue(isSwitchedToAddTaskFrame, "Switched to add task frame.", "Failed to switch to add task frame.");
			boolean isPhaseIDSet = deliveryMilestonePageObj.enterTextPhaseID(phaseID);
			IsTrue(isPhaseIDSet, "Entered phase ID: " + phaseID, "Failed to add text to phase ID as '" + phaseID + "'");
			boolean isParentSelected = deliveryMilestonePageObj.enterParentMilestone(driver, milestoneId);
			IsTrue(isParentSelected, "Successfully selected parent milestone", "Failed to select parent milestone.");
			boolean isPhaseNameSet = deliveryMilestonePageObj.enterTextPhaseName(phaseName);
			IsTrue(isPhaseNameSet, "Entered phase Name: " + phaseName,"Failed to add text to phase name as '" + phaseName + "'");
			boolean isStartDateCalendarClicked = deliveryMilestonePageObj.clkStartDateCalendar();
			IsTrue(isStartDateCalendarClicked, "Clicked on start date calender","Failed to click on Start Date Calendar.");
			boolean isStartDateClicked = deliveryMilestonePageObj.clkStartDate();
			IsTrue(isStartDateClicked, "Clicked on start date", "Failed to click on Start Date.");
			boolean isEndDateCalendarClicked = deliveryMilestonePageObj.clkEndDateCalendar();
			IsTrue(isEndDateCalendarClicked, "Clicked on end date calender", "Failed to click on End Date Calendar.");
			boolean isEndDateClicked = deliveryMilestonePageObj.clkEndDate();
			IsTrue(isEndDateClicked, "Clicked on end date", "Failed to click on End Date.");
			boolean isAddButtonOnAddMilestonePageClicked = deliveryMilestonePageObj.clkAddButton();
			IsTrue(isAddButtonOnAddMilestonePageClicked, "Successfully clicked on Add button","Failed to click on Add button on Add Milestone page.");
			deliveryMilestonePageObj.clickExpandIcon(milestoneId);
			String actualPhaseID = deliveryMilestonePageObj.verifyId(milestoneId, phaseID);
			Equals(actualPhaseID, phaseID, "Phase ID is correctly displayed", "Phase ID is not displayed correctly");
			String actualPhaseName = deliveryMilestonePageObj.verifyName(actualPhaseID);
			Equals(actualPhaseName, phaseName, "Phase name is correctly displayed",	"Phase name is not displayed correctly");
			String milestoneType = deliveryMilestonePageObj.verifyMilestoneType(actualPhaseID);
			Equals(milestoneType, "Phase", "Milestone type is correctly displayed",	"Milestone type is not displayed correctly");
			String status = deliveryMilestonePageObj.verifyStatus(actualPhaseID);
			IsTrue(status.contains("Open"), "Phase status is correctly displayed","Phase status is not displayed correctly");
			if(status.contains("Open"))
			{
				ExcelUtils.logTestResult(excelPath, "Create", rowNum, "PASS", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "GREEN");
			}
			else
			{
				ExcelUtils.logTestResult(excelPath, "Create", rowNum, "FAIL", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "FAIL", "RED");
			}
		} catch (Exception e) {
			ExcelUtils.logTestResult(excelPath, "Create", rowNum, "FAIL", tcID);
			ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "FAIL", "RED");
			logError(e.getMessage());
			}
		logInfo("End of Test Case : " + tcID);
	}

//	@DataProvider
//	public Object[][] readPhaseData() throws Exception {
//		rowNum = ExcelUtils.getRowNumXSSF(excelPath, "Create", "Test case", "TC_1_Phase_ID_blank");
//		tcRowNum = rowNum - 1;
//		Object[][] testObjArray = ExcelUtils.getTableArray(System.getProperty("user.dir") + "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Phase.xlsx",
//				"Create", tcRowNum);
//		return (testObjArray);
//	}
//
//	@Test(dataProvider = "readPhaseData", priority = 1)
//	public void verify_negative_scenarios(String phaseID, String phaseName, String startDate, String endDate,
//			String parentMilestone) {
//		String tcID = null;
//		try {
//			tcID = ExcelUtils.getCellData(tcRowNum, 0, excelPath, "Create");
//		} catch (Exception e1) {
//			logError(e1.getMessage());
//		}
//		logInfo("Starting of Test Case : " + tcID);
//		boolean isAddDeliveryMilestoneButtonClicked = deliveryMilestonePageObj.clkAddDeliveryMilestoneButton(); // User
//		IsTrue(isAddDeliveryMilestoneButtonClicked, "Successfully clicked on Add Delivery Milestone buttons",
//				"Failed to click on Add Delivery Milestone button.");
//		boolean isPhaseOptionClicked = deliveryMilestonePageObj.clkPhaseOption();
//		IsTrue(isPhaseOptionClicked, "Release option selected", "Failed to click on Release option.");
//		boolean isSwitchedToAddPhaseFrame = deliveryMilestonePageObj.switchToAddFrame();
//		IsTrue(isSwitchedToAddPhaseFrame, "Switched to add task frame.", "Failed to switch to add task frame.");
//		logInfo("Task ID: " + phaseID);
//		logInfo("Task Name " + phaseName);
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
//		} else {
//			boolean isParentSelected = deliveryMilestonePageObj.enterParentMilestone(driver, parentMilestone);
//			IsTrue(isParentSelected, "Successfully selected parent milestone", "Failed to select parent milestone.");
//		}
//		if (phaseID.equals("") || op.checkSpecialChar(phaseID) || phaseID.equalsIgnoreCase("null")
//				|| op.checkOnlyCharacter(phaseID)) {
//			this.expectedErrorMsg = deliveryMilestonePageObj.getphaseIDExpectedMsg(expectedErrorMsg, errorCnt);
//			errorCnt++;
//		} else {
//			boolean isphaseIDSet = deliveryMilestonePageObj.enterTextPhaseID(phaseID);
//			IsTrue(isphaseIDSet, "Entered phase id: " + phaseID, "Failed to add text to phase ID as '" + phaseID + "'");
//		}
//		if (phaseName.equals("") || op.checkSpecialChar(phaseName) || NumberUtils.isNumber(phaseName)
//				|| phaseName.equalsIgnoreCase("null")) {
//			this.expectedErrorMsg = deliveryMilestonePageObj.getphaseNameExpectedMsg(phaseName, expectedErrorMsg,
//					errorCnt);
//			errorCnt++;
//		} else {
//			boolean isphaseNameSet = deliveryMilestonePageObj.enterTextPhaseName(phaseName);
//			IsTrue(isphaseNameSet, "Entered phase name: " + phaseName,
//					"Failed to add text to phase name as '" + phaseName + "'");
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
//		boolean isAddButtonOnAddPhaseClicked = deliveryMilestonePageObj.clkAddButton();
//		IsTrue(isAddButtonOnAddPhaseClicked, "Clicked on Add button",
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
//			try {
//				ExcelUtils.logTestResult("TC_DeliveryMilestone_Phase", "Create", tcRowNum, "FAIL", tcID);
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
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
//				try {
//					ExcelUtils.logTestResult("TC_DeliveryMilestone_Phase", "Create", tcRowNum, "FAIL", tcID);
//				} catch (Exception e1) {
//					e1.printStackTrace();
//				}
//			}
//		}
//		tcRowNum++;
//		logInfo("End of Test Case : " + tcID);
//		deliveryMilestonePageObj.clkCancelButtonOnAddMilestoneFrame();
//		try {
//			ExcelUtils.logTestResult("TC_DeliveryMilestone_Phase", "Create", tcRowNum, "PASS", tcID);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
	
	@AfterClass
	public void closeBrowser() throws Exception {
			driver.close();
	}
}

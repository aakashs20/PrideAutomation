package com.project.testcases.pTracker.deliveryMilestone;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
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

public class TC_DeliveryMilestone_Release_Create extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	DeliveryMilestonePage deliveryMilestonePageObj;
	ExcelUtils excel;
    PropertiesConfiguration config ;

	Operations op;
	CommonPages cp;
	Properties prop;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";

	private String searchProjectNumber = "PIUDNDMF";
	private String datapoolPath = System.getProperty("user.dir")+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	private String excelPath = System.getProperty("user.dir")+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Release.xls";
	private static int rowNum = 1;
	static int tcRowNum;
	String expectedErrorMsg[];
	int errorCnt;
	String tcID = "TC_DELIVERY_MILESTONE_CREATE_RELEASE";

	@BeforeTest
	public void groupInit() throws Exception {
		prop = new Properties();
		prop.load(new FileInputStream(Constants.configFile));
		searchProjectNumber = prop.getProperty("projectID");
		config = new PropertiesConfiguration(Constants.configFile);  
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

	@Test (priority = 1 , groups = {"sanity", "UI"}, description = "Validate Create Release")
	public void createnewRelease() throws Exception {
		String tcID = ExcelUtils.getCellDataX(rowNum, 0, excelPath, "Create");
		logInfo("Starting of Test Case : " + tcID);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		excel = new ExcelUtils();
		int rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Create", "testCase",tcID );
		try {
			String milestoneId = deliveryMilestonePageObj.createMilestone();
			int randomNum = op.generateRandomNum();
			String releaseID = "RELEASEID" + randomNum;
			String releaseName = "RELEASENAME" + randomNum;
			boolean isAddDeliveryMilestoneButtonClicked = deliveryMilestonePageObj.clkAddDeliveryMilestoneButton();
			IsTrue(isAddDeliveryMilestoneButtonClicked, "Successfully clicked on Add Delivery Milestone buttons","Failed to click on Add Delivery Milestone button.");
			boolean isSprintOptionClicked = deliveryMilestonePageObj.clkReleaseOption();
			IsTrue(isSprintOptionClicked, "Release option selected", "Failed to click on Release option.");
			// threadsleep(2000);
			boolean isSwitchedToAddReleaseFrame = deliveryMilestonePageObj.switchToAddFrame();
			IsTrue(isSwitchedToAddReleaseFrame, "Switched to add release frame.","Failed to switch to add release frame.");
			boolean isMilestoneIDSet = deliveryMilestonePageObj.enterTextReleaseID(releaseID);
			IsTrue(isMilestoneIDSet, "Entered release ID: " + releaseID,"Failed to add text to release ID as '" + releaseID + "'");
			boolean isParentSelected = deliveryMilestonePageObj.enterParentMilestone(driver, milestoneId);
			IsTrue(isParentSelected, "Successfully selected parent milestone", "Failed to select parent milestone.");
			boolean isReleaseNameSet = deliveryMilestonePageObj.enterTextReleaseName(releaseName);
			IsTrue(isReleaseNameSet, "Entered release Name: " + releaseName,"Failed to add text to release name as '" + releaseName + "'");
			boolean isStartDateCalendarClicked = deliveryMilestonePageObj.clkStartDateCalendar();
			IsTrue(isStartDateCalendarClicked, "Clicked on start date calender","Failed to click on Start Date Calendar.");
			boolean isStartDateClicked = deliveryMilestonePageObj.clkStartDate();
			IsTrue(isStartDateClicked, "Clicked on start date", "Failed to click on Start Date.");
			boolean isEndDateCalendarClicked = deliveryMilestonePageObj.clkEndDateCalendar();
			IsTrue(isEndDateCalendarClicked, "Clicked on end date calender", "Failed to click on End Date Calendar.");
			boolean isEndDateClicked = deliveryMilestonePageObj.clkEndDate();
			IsTrue(isEndDateClicked, "Clicked on end date", "Failed to click on End Date.");
			boolean isAddButtonOnAddMilestonePageClicked = deliveryMilestonePageObj.clkAddButton();
			IsTrue(isAddButtonOnAddMilestonePageClicked, "", "Failed to click on Add button on Add Milestone page.");
			op.switchToDefault();
			deliveryMilestonePageObj.verifySuccessMsg();
			boolean verifyStatus = deliveryMilestonePageObj.verifyReleaseDetails(releaseID, releaseName, milestoneId);
			IsTrue(verifyStatus, "Release is added successfully", "Failed to add release ");
			// return releaseID;
			if(verifyStatus)
			{
			     logInfo("Writing MileStone ID -> "+ milestoneId + " and Release ID -> " + releaseID +" to Confing.Properties file.");
			     config.setProperty("release.milestoneId", milestoneId);
			     config.setProperty("release.releaseID", releaseID);
			     config.save();
				ExcelUtils.logTestResult(excelPath, "Create", rowNum, "PASS", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "GREEN");
			}
			else
			{
				ExcelUtils.logTestResult(excelPath, "Create", rowNum, "FAIL", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "RED");
			}
		} catch (Exception e) {
			ExcelUtils.logTestResult(excelPath, "Create", rowNum, "FAIL", tcID);
			ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "RED");
			logError(e.getMessage());
		}
		logInfo("End of Test Case : " + tcID);
	}

//	@DataProvider
//	public Object[][] readReleaseData() throws Exception {
//		rowNum = ExcelUtils.getRowNumXSSF(excelPath, "Create", "Test case", "TC_1_release_ID_blank");
//		tcRowNum = rowNum - 1;
//		Object[][] testObjArray = ExcelUtils.getTableArray(System.getProperty("user.dir") + "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Release.xlsx",
//				"Create", tcRowNum);
//		return (testObjArray);
//	}
//
//	@Test(dataProvider = "readReleaseData", priority = 1)
//	public void verify_negative_scenarios(String releaseId, String releaseName, String startDate, String endDate,
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
//		boolean isPhaseOptionClicked = deliveryMilestonePageObj.clkReleaseOption();
//		IsTrue(isPhaseOptionClicked, "Release option selected", "Failed to click on Release option.");
//		boolean isSwitchedToAddReleaseFrame = deliveryMilestonePageObj.switchToAddFrame();
//		IsTrue(isSwitchedToAddReleaseFrame, "Switched to add release frame.", "Failed to switch to add release frame.");
//		logInfo("release ID: " + releaseId);
//		logInfo("release Name " + releaseName);
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
//		if (releaseId.equals("") || op.checkSpecialChar(releaseId) || releaseId.equalsIgnoreCase("null")
//				|| op.checkOnlyCharacter(releaseId)) {
//			this.expectedErrorMsg = deliveryMilestonePageObj.getReleaseIDExpectedMsg(expectedErrorMsg, errorCnt);
//			errorCnt++;
//		} else {
//			boolean isReleaseIDSet = deliveryMilestonePageObj.enterTextToReleaseID(releaseId);
//			IsTrue(isReleaseIDSet, "Entered release id: " + releaseId,
//					"Failed to add text to release ID as '" + releaseId + "'");
//		}
//		if (releaseName.equals("") || op.checkSpecialChar(releaseName) || NumberUtils.isNumber(releaseName)
//				|| releaseName.equalsIgnoreCase("null")) {
//			this.expectedErrorMsg = deliveryMilestonePageObj.getReleaseNameExpectedMsg(releaseName, expectedErrorMsg,
//					errorCnt);
//			errorCnt++;
//		} else {
//			boolean isphaseNameSet = deliveryMilestonePageObj.enterTextToReleaseNameField(releaseName);
//			IsTrue(isphaseNameSet, "Entered phase name: " + releaseName,
//					"Failed to add text to phase name as '" + releaseName + "'");
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
//				ExcelUtils.logTestResult("TC_DeliveryMilestone_Release", "Create", tcRowNum, "FAIL", tcID);
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//////			try {
//////				excel.logTestResult(tcID, "FAIL");
//////			} catch (Exception e1) {
//////				logError(e1.getMessage());
//////			}
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
//					ExcelUtils.logTestResult("TC_DeliveryMilestone_Release", "Create", tcRowNum, "FAIL", tcID);
//				} catch (Exception e1) {
//					e1.printStackTrace();
//				}
//////				try {
//////					excel.logTestResult(tcID, "FAIL");
//////				} catch (Exception e1) {
//////					logError(e1.getMessage());
//////				}
//			}
//		}
//		logInfo("End of Test Case : " + tcID);
//		deliveryMilestonePageObj.clkCancelButtonOnAddMilestoneFrame();
//		try {
//			ExcelUtils.logTestResult("TC_DeliveryMilestone_Release", "Create", tcRowNum, "PASS", tcID);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		tcRowNum++;
//	}
//
	@AfterClass
	void closeBrowser() throws InterruptedException {
		driver.close();
	}
}

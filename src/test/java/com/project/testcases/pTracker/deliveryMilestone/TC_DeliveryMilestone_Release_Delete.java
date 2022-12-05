package com.project.testcases.pTracker.deliveryMilestone;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

public class TC_DeliveryMilestone_Release_Delete extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	DeliveryMilestonePage deliveryMilestonePageObj;
	ExcelUtils excel;

	Operations op;
	CommonPages cp;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";

	private String searchProjectNumber = "MKSDVFIK";
	private String milestoneID;
	private String milestoneName;
	private String datapoolPath = System.getProperty("user.dir")+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	private String excelPath = System.getProperty("user.dir")+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Release.xls";
	private String releaseID;
	private String releaseName;
	String tcID = "TC_DELIVERY_MILESTONE_DELETE_RELEASE";
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
		releaseID = deliveryMilestonePageObj.createRelease(milestoneID);
		IsTrue(releaseID != null, "Release is successfully created", "Failed to add release");
	}

//	@Test(priority = 0)
//	public void verify_delivery_milestone() throws Exception {
//		deliveryMilestonePageObj.verifyActiveProject(driver, searchProjectNumber);
//		deliveryMilestonePageObj.verifyDeliveryMilestoneButton(driver);
//	}

//	@Test(priority = 1)
//	public void add_delivery_milestone() throws Exception {
//		int randomNum = op.generateRandomNum();
//		milestoneID = "MILEID" + randomNum;
//		milestoneName = "MILENAME" + randomNum;
//		boolean isAddDeliveryMilestoneButtonClicked = deliveryMilestonePageObj.clkAddDeliveryMilestoneButton(); // User
//		IsTrue(isAddDeliveryMilestoneButtonClicked, "Successfully clicked on Add Delivery Milestone buttons",
//				"Failed to click on Add Delivery Milestone button.");
//		boolean isDeliveryMilestoneOptionClicked = deliveryMilestonePageObj.clkMilestoneOption();
//		IsTrue(isDeliveryMilestoneOptionClicked, "Delivery Milestone option clicked.",
//				"Failed to click on Delivery Milestone option.");
//		boolean isSwitchedToAddMilestoneFrame = deliveryMilestonePageObj.switchToAddMilestoneFrame();
//		IsTrue(isSwitchedToAddMilestoneFrame, "Switched to add milestone frame.",
//				"Failed to switch to add miestone frame.");
//		boolean isMilestoneIDSet = deliveryMilestonePageObj.enterTextToMilestoneID(milestoneID);
//		IsTrue(isMilestoneIDSet, "Added text to milestone ID: " + milestoneID,
//				"Failed to add text to Milestone ID as '" + milestoneID + "'");
//		boolean isMilestoneNameSet = deliveryMilestonePageObj.enterTextTomilestoneNameField(milestoneName);
//		IsTrue(isMilestoneNameSet, "Added text to milestone name: " + milestoneName,
//				"Failed to add text to Milestone Name as '" + milestoneName + "'");
//		boolean isStartDateCalendarClicked = deliveryMilestonePageObj.clkStartDateCalendar();
//		IsTrue(isStartDateCalendarClicked, "Clicked on start date calender", "Failed to click on Start Date Calendar.");
//		boolean isStartDateClicked = deliveryMilestonePageObj.clkStartDate();
//		IsTrue(isStartDateClicked, "Clicked on start date", "Failed to click on Start Date.");
//		boolean isEndDateCalendarClicked = deliveryMilestonePageObj.clkEndDateCalendar();
//		IsTrue(isEndDateCalendarClicked, "Clicked on end date calender", "Failed to click on End Date Calendar.");
//		boolean isEndDateClicked = deliveryMilestonePageObj.clkEndDate();
//		IsTrue(isEndDateClicked, "Clicked on end date", "Failed to click on End Date.");
//		boolean isAddButtonOnAddMilestonePageClicked = deliveryMilestonePageObj.clkAddButtonOnAddMilestoneFrame();
//		IsTrue(isAddButtonOnAddMilestonePageClicked, "Clicked on Add button on Add Milestone page.",
//				"Failed to click on Add button on Add Milestone page.");
//		driver.switchTo().defaultContent();
//		boolean verifyStatus = deliveryMilestonePageObj.verifyMilestoneDetails(driver, milestoneID, milestoneName, "0%",
//				"Open");
//		IsTrue(verifyStatus, "Milestone is added successfully", "Failed to add milestone ");
//	}
//
//	// @Test(priority = 2)
//	public void createRelease() throws Exception {
//		int randomNum = op.generateRandomNum();
//		// milestoneID = "MILEID158";
//		releaseID = "RELEASEID" + randomNum;
//		releaseName = "RELEASENAME" + randomNum;
//		boolean isAddDeliveryMilestoneButtonClicked = deliveryMilestonePageObj.clkAddDeliveryMilestoneButton(); // User
//		IsTrue(isAddDeliveryMilestoneButtonClicked, "Successfully clicked on Add Delivery Milestone buttons",
//				"Failed to click on Add Delivery Milestone button.");
//		boolean isSprintOptionClicked = deliveryMilestonePageObj.clkReleaseOption();
//		IsTrue(isSprintOptionClicked, "Release option selected", "Failed to click on Release option.");
//		boolean isSwitchedToAddReleaseFrame = deliveryMilestonePageObj.switchToAddFrame();
//		IsTrue(isSwitchedToAddReleaseFrame, "Switched to add release frame.", "Failed to switch to add release frame.");
//		boolean isMilestoneIDSet = deliveryMilestonePageObj.enterTextReleaseID(releaseID);
//		IsTrue(isMilestoneIDSet, "Entered release ID: " + releaseID,
//				"Failed to add text to release ID as '" + releaseID + "'");
//
//		boolean isReleaseNameSet = deliveryMilestonePageObj.enterTextReleaseName(releaseName);
//		IsTrue(isReleaseNameSet, "Entered release Name: " + releaseName,
//				"Failed to add text to release name as '" + releaseName + "'");
//		boolean isStartDateCalendarClicked = deliveryMilestonePageObj.clkStartDateCalendar(); // Add Milestone
//		// Calendar icon
//		IsTrue(isStartDateCalendarClicked, "Clicked on start date calender", "Failed to click on Start Date Calendar.");
//		boolean isStartDateClicked = deliveryMilestonePageObj.clkStartDate(); // Add Milestone window->Click on Start
//		IsTrue(isStartDateClicked, "Clicked on start date", "Failed to click on Start Date.");
//		boolean isEndDateCalendarClicked = deliveryMilestonePageObj.clkEndDateCalendar(); // Add Milestone window->Click
//// icon
//		IsTrue(isEndDateCalendarClicked, "Clicked on end date calender", "Failed to click on End Date Calendar.");
//		boolean isEndDateClicked = deliveryMilestonePageObj.clkEndDate(); // Add Milestone window->Click on End Date
//		IsTrue(isEndDateClicked, "Clicked on end date", "Failed to click on End Date.");
//		boolean isParentSelected = deliveryMilestonePageObj.enterParentMilestone(driver, milestoneID);
//		IsTrue(isParentSelected, "Successfully selected parent milestone", "Failed to select parent milestone.");
//
//		boolean isAddButtonOnAddMilestonePageClicked = deliveryMilestonePageObj.clkAddButton(); // Add Milestone
//		// on Add button
//		IsTrue(isAddButtonOnAddMilestonePageClicked, "", "Failed to click on Add button on Add Milestone page.");
//		driver.switchTo().defaultContent(); // After adding a milestone, the control switches back to main pageE
//	}

//	@Test(priority = 3)
//	public void verifyReleaseDetails() {
//		WebElement releaseIcon;
//		releaseIcon = controlActions.perform_waitUntilVisibility(
//				By.xpath(DeliveryMilestoneConstants.VIEW_RELEASE_ICON.replace("MilestoneIDToBeReplaced", milestoneID)));
//		controlActions.click(releaseIcon);
//		String actualReleaseID = controlActions.getText(controlActions.performGetElementByXPath(
//				DeliveryMilestoneConstants.ADDED_RELEASE_ID.replace("MilestoneIDToBeReplaced", milestoneID)));
//		Equals(actualReleaseID, releaseID, "Release ID is correctly displayed",
//				"Release ID is not displayed correctly");
//		String actualReleaseName = controlActions.getText(controlActions.performGetElementByXPath(
//				DeliveryMilestoneConstants.ADDED_RELEASE_NAME.replace("MilestoneIDToBeReplaced", milestoneID)));
//		Equals(actualReleaseName, releaseName, "Release name is correctly displayed",
//				"Release name is not displayed correctly");
//		String milestoneType = controlActions.getText(controlActions.performGetElementByXPath(
//				DeliveryMilestoneConstants.MILESTONE_TYPE.replace("MilestoneIDToBeReplaced", milestoneID)));
//		Equals(milestoneType, "Release", "Milestone type is correctly displayed",
//				"Milestone type is not displayed correctly");
//		String releaseStatus = controlActions.getText(controlActions.performGetElementByXPath(
//				DeliveryMilestoneConstants.ADDED_RELEASE_STATUS.replace("MilestoneIDToBeReplaced", milestoneID)));
//		IsTrue(releaseStatus.contains("Open"), "Release status is correctly displayed",
//				"Release status is not displayed correctly");
//		releaseIcon = controlActions.perform_waitUntilVisibility(
//				By.xpath(DeliveryMilestoneConstants.VIEW_RELEASE_ICON.replace("MilestoneIDToBeReplaced", milestoneID)));
//		controlActions.click(releaseIcon);
//	}
//
	@DataProvider
	public Object[] getReasonCodes() throws Exception {
		String reasonCodeList = ExcelUtils.getCellDataX(excelPath, 2, "Reason code", tcID);
		String reasonCodes[] = reasonCodeList.split(";");
		return (reasonCodes);
	}


	@Test(priority = 1,dataProvider = "getReasonCodes", description = "Validate Delete Release with different status")
	public void deleteRelease(String reasonCode) throws Exception {
		String tcID = ExcelUtils.getCellDataX(rowNum, 0, excelPath, "Delete");
		logInfo("Starting of Test Case : " + tcID);
		rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Delete", "testCase", tcID);
		logInfo("Test case Row NO is : " + rowNum);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		try {
//			milestoneID = prop.getProperty("release.milestoneId");
//			releaseID = prop.getProperty("release.releaseID");
			logInfo("Milestone ID is : " + milestoneID);
			logInfo("Release ID is : " + releaseID);
			WebElement releaseIcon;
			//threadsleep(2000);
			deliveryMilestonePageObj.clickExpandIcon(milestoneID);
			deliveryMilestonePageObj.clickDeleteIcon(releaseID);
			//controlActions.click(controlActions.performGetElementByXPath(DeliveryMilestoneConstants.DELETE_RELEASE_BUTTON.replace("releaseIDToBeUpdated", releaseID)));
			boolean isSwitchedToDeleteMilestoneFrame = deliveryMilestonePageObj.switchToDeleteMilestoneFrame();
			IsTrue(isSwitchedToDeleteMilestoneFrame, "Switched to delete frame successfully","Failed to switch to delete frame");
			String statusUpdate = ExcelUtils.getCellDataX(excelPath, 2, "Status Update", "TC_DELIVERY_MILESTONE_DELETE_RELEASE");
			logInfo("Status update : " + statusUpdate);
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
		logInfo("End of Test Case : " + tcID);
	}

}

package com.project.testcases.pTracker;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.CommonPages;
import com.project.pageobjects.pTracker.DeliveryMilestoneConstants;
import com.project.pageobjects.pTracker.DeliveryMilestonePage;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class TC_DeliveryMilestone_Release_Update_NegativeScenarios extends TestBase {
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	DeliveryMilestonePage deliveryMilestonePageObj;
	ExcelUtils excel;
	private static int rowNum = 2;

	Operations op;
	CommonPages cp;
	private String uName = "abc";
	private String uPassword = "xyz";
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";

	private String searchProjectNumber = "MKSDVFIK";
	private String milestoneID;
	private String milestoneName;
	private String excelPath = System.getProperty("user.dir")
			+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Release.xlsx";
	private String releaseID;
	private String releaseName;

	@BeforeClass
	public void groupInit() throws Exception {
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
	}

	@Test(priority = 0)
	public void verify_delivery_milestone() throws Exception {
//		milestoneID = "MILEID158";
//		releaseID = "RELEASEID204";
		deliveryMilestonePageObj.verifyActiveProject(driver, searchProjectNumber);
		deliveryMilestonePageObj.verifyDeliveryMilestoneButton(driver);
//		WebElement releaseIcon;
//		releaseIcon = controlActions.perform_waitUntilVisibility(
//				By.xpath(DeliveryMilestoneConstants.VIEW_RELEASE_ICON.replace("MilestoneIDToBeReplaced", milestoneID)));
//		controlActions.click(releaseIcon);
	}

	@Test(priority = 1)
	public void add_delivery_milestone() throws Exception {
		int randomNum = op.generateRandomNum();
		milestoneID = "MILEID" + randomNum;
		milestoneName = "MILENAME" + randomNum;
		boolean isAddDeliveryMilestoneButtonClicked = deliveryMilestonePageObj.clkAddDeliveryMilestoneButton(); // User
		IsTrue(isAddDeliveryMilestoneButtonClicked, "Successfully clicked on Add Delivery Milestone buttons",
				"Failed to click on Add Delivery Milestone button.");
		boolean isDeliveryMilestoneOptionClicked = deliveryMilestonePageObj.clkMilestoneOption(); // User clicks on
		// Delivery Milestone
		IsTrue(isDeliveryMilestoneOptionClicked, "Delivery Milestone option clicked.",
				"Failed to click on Delivery Milestone option.");
		boolean isSwitchedToAddMilestoneFrame = deliveryMilestonePageObj.switchToAddMilestoneFrame(); // Switches the
		// frame
		IsTrue(isSwitchedToAddMilestoneFrame, "Switched to add milestone frame.",
				"Failed to switch to add miestone frame.");
		boolean isMilestoneIDSet = deliveryMilestonePageObj.enterTextToMilestoneID(milestoneID); // Add Milestone
		IsTrue(isMilestoneIDSet, "Added text to milestone ID: " + milestoneID,
				"Failed to add text to Milestone ID as '" + milestoneID + "'");
		boolean isMilestoneNameSet = deliveryMilestonePageObj.enterTextTomilestoneNameField(milestoneName); // Add
		// milestone Name
		IsTrue(isMilestoneNameSet, "Added text to milestone name: " + milestoneName,
				"Failed to add text to Milestone Name as '" + milestoneName + "'");
		boolean isStartDateCalendarClicked = deliveryMilestonePageObj.clkStartDateCalendar(); // Add Milestone
		// Calendar icon
		IsTrue(isStartDateCalendarClicked, "Clicked on start date calender", "Failed to click on Start Date Calendar.");
		boolean isStartDateClicked = deliveryMilestonePageObj.clkStartDate(); // Add Milestone window->Click on Start
		IsTrue(isStartDateClicked, "Clicked on start date", "Failed to click on Start Date.");
		boolean isEndDateCalendarClicked = deliveryMilestonePageObj.clkEndDateCalendar(); // Add Milestone window->Click
																							// on End Date Calendar
// icon
		IsTrue(isEndDateCalendarClicked, "Clicked on end date calender", "Failed to click on End Date Calendar.");
		boolean isEndDateClicked = deliveryMilestonePageObj.clkEndDate(); // Add Milestone window->Click on End Date
		IsTrue(isEndDateClicked, "Clicked on end date", "Failed to click on End Date.");
		boolean isAddButtonOnAddMilestonePageClicked = deliveryMilestonePageObj.clkAddButtonOnAddMilestoneFrame(); // Add
		IsTrue(isAddButtonOnAddMilestonePageClicked, "Clicked on Add button on Add Milestone page.",
				"Failed to click on Add button on Add Milestone page.");
		driver.switchTo().defaultContent(); // After adding a milestone, the control switches back to main page
		boolean verifyStatus = deliveryMilestonePageObj.verifyMilestoneDetails(driver, milestoneID, milestoneName, "0%",
				"Open");
		IsTrue(verifyStatus, "Milestone is added successfully", "Failed to add milestone ");
	}

	@Test(priority = 2)
	public void createRelease() throws Exception {
		int randomNum = op.generateRandomNum();
		// milestoneID = "MILEID158";
		releaseID = "RELEASEID" + randomNum;
		releaseName = "RELEASENAME" + randomNum;
		boolean isAddDeliveryMilestoneButtonClicked = deliveryMilestonePageObj.clkAddDeliveryMilestoneButton(); // User
		IsTrue(isAddDeliveryMilestoneButtonClicked, "Successfully clicked on Add Delivery Milestone buttons",
				"Failed to click on Add Delivery Milestone button.");
		boolean isSprintOptionClicked = deliveryMilestonePageObj.clkReleaseOption();
		IsTrue(isSprintOptionClicked, "Release option selected", "Failed to click on Release option.");
		boolean isSwitchedToAddReleaseFrame = deliveryMilestonePageObj.switchToAddFrame();
		IsTrue(isSwitchedToAddReleaseFrame, "Switched to add release frame.", "Failed to switch to add release frame.");
		boolean isMilestoneIDSet = deliveryMilestonePageObj.enterTextReleaseID(releaseID);
		IsTrue(isMilestoneIDSet, "Entered release ID: " + releaseID,
				"Failed to add text to release ID as '" + releaseID + "'");

		boolean isReleaseNameSet = deliveryMilestonePageObj.enterTextReleaseName(releaseName);
		IsTrue(isReleaseNameSet, "Entered release Name: " + releaseName,
				"Failed to add text to release name as '" + releaseName + "'");
		boolean isStartDateCalendarClicked = deliveryMilestonePageObj.clkStartDateCalendar(); // Add Milestone
		// Calendar icon
		IsTrue(isStartDateCalendarClicked, "Clicked on start date calender", "Failed to click on Start Date Calendar.");
		boolean isStartDateClicked = deliveryMilestonePageObj.clkStartDate(); // Add Milestone window->Click on Start
		IsTrue(isStartDateClicked, "Clicked on start date", "Failed to click on Start Date.");
		boolean isEndDateCalendarClicked = deliveryMilestonePageObj.clkEndDateCalendar(); // Add Milestone window->Click
// icon
		IsTrue(isEndDateCalendarClicked, "Clicked on end date calender", "Failed to click on End Date Calendar.");
		boolean isEndDateClicked = deliveryMilestonePageObj.clkEndDate(); // Add Milestone window->Click on End Date
		IsTrue(isEndDateClicked, "Clicked on end date", "Failed to click on End Date.");
		boolean isParentSelected = deliveryMilestonePageObj.enterParentMilestone(driver, milestoneID);
		IsTrue(isParentSelected, "Successfully selected parent milestone", "Failed to select parent milestone.");

		boolean isAddButtonOnAddMilestonePageClicked = deliveryMilestonePageObj.clkAddButton(); // Add Milestone
		// on Add button
		IsTrue(isAddButtonOnAddMilestonePageClicked, "", "Failed to click on Add button on Add Milestone page.");
		driver.switchTo().defaultContent(); // After adding a milestone, the control switches back to main pageE
	}

	@Test(priority = 3)
	public void verifyReleaseDetails() {
		WebElement releaseIcon = controlActions.perform_waitUntilVisibility(
				By.xpath(DeliveryMilestoneConstants.VIEW_RELEASE_ICON.replace("MilestoneIDToBeReplaced", milestoneID)));
		controlActions.click(releaseIcon);
		String actualReleaseID = controlActions.getText(controlActions.performGetElementByXPath(
				DeliveryMilestoneConstants.ADDED_RELEASE_ID.replace("MilestoneIDToBeReplaced", milestoneID)));
		Equals(actualReleaseID, releaseID, "Release ID is correctly displayed",
				"Release ID is not displayed correctly");
		String actualReleaseName = controlActions.getText(controlActions.performGetElementByXPath(
				DeliveryMilestoneConstants.ADDED_RELEASE_NAME.replace("MilestoneIDToBeReplaced", milestoneID)));
		Equals(actualReleaseName, releaseName, "Release name is correctly displayed",
				"Release name is not displayed correctly");
		String milestoneType = controlActions.getText(controlActions.performGetElementByXPath(
				DeliveryMilestoneConstants.MILESTONE_TYPE.replace("MilestoneIDToBeReplaced", milestoneID)));
		Equals(milestoneType, "Release", "Milestone type is correctly displayed",
				"Milestone type is not displayed correctly");
		String releaseStatus = controlActions.getText(controlActions.performGetElementByXPath(
				DeliveryMilestoneConstants.ADDED_RELEASE_STATUS.replace("MilestoneIDToBeReplaced", milestoneID)));
		IsTrue(releaseStatus.contains("Open"), "Release status is correctly displayed",
				"Release status is not displayed correctly");
	}

	@DataProvider
	public Object[][] readReleaseDataFromExcel() throws Exception {
		int rowNum = ExcelUtils.getRowNum(excelPath, "Create", "Test case", "TC_DeliveryMilestone_Create_Milestone");
		Object[][] testObjArray = ExcelUtils.getTableArray(
				System.getProperty("user.dir") + "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Release.xlsx",
				"Update", 2);
		for (Object[] obj : testObjArray) {
			for (Object str : obj) {
				System.out.println(str);
			}
		}
		return (testObjArray);
	}

	@Test(priority = 4, dataProvider = "readReleaseDataFromExcel")
	public void updateRelease(String status, String statusUpdate, String percentCompletion) {
//		milestoneID = "MILEID158";
//		releaseID = "RELEASEID204";
		controlActions.perform_waitUntilVisibility(
				By.xpath(DeliveryMilestoneConstants.UPDATE_BUTTON.replace("releaseIDToBeUpdated", releaseID)));
		controlActions.click(controlActions.performGetElementByXPath(
				DeliveryMilestoneConstants.UPDATE_BUTTON.replace("releaseIDToBeUpdated", releaseID)));
		boolean isSwitchedToUpdateReleaseFrame = deliveryMilestonePageObj.switchToUpdateFrame();
		IsTrue(isSwitchedToUpdateReleaseFrame, "Switched to update release frame.",
				"Failed to switch to update release frame.");

		boolean tc_status;
		try {
			tc_status = deliveryMilestonePageObj.verify_negScenarios_UpdateRelease(driver, status, statusUpdate,
					percentCompletion);
			if (tc_status == true) {
				ExcelUtils.setCellData("TC_DeliveryMilestone_Release", "Update", rowNum, "TC_Status", "PASS", "GREEN");
				excel.logTestResult("TC_UPDATE_DELIVERY_MILESTONE", "PASS");
			} else {
				ExcelUtils.setCellData("TC_DeliveryMilestone_Release", "Update", rowNum, "TC_Status", "FAIL", "RED");
				excel.logTestResult("TC_UPDATE_DELIVERY_MILESTONE", "FAIL");
			}
		} catch (Exception e) {
			logError(e.getMessage());
		}

		rowNum++;

	}

	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}

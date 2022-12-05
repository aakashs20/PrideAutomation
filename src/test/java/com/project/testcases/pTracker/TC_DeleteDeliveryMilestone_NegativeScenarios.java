package com.project.testcases.pTracker;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.CommonPages;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;
import com.project.pageobjects.pTracker.DeliveryMilestoneConstants;
import com.project.pageobjects.pTracker.DeliveryMilestonePage;

public class TC_DeleteDeliveryMilestone_NegativeScenarios extends TestBase {
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	DeliveryMilestonePage deliveryMilestonePageObj;
	ExcelUtils excel;

	Operations op;
	CommonPages cp;
	private String uName = "abc";
	private String uPassword = "xyz";
	private static final int DELAY = 30;
	String eName = "Mahajan, Milind";

	private String searchProjectNumber = "YUKO0001";
	private String milestoneID;
	private String milestoneName;
	private String excelPath = System.getProperty("user.dir")
			+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone.xlsx";
	private static int rowNum = 2;

	@BeforeClass
	public void groupInit() throws Exception {
		// setting up property to suppress the warning
		System.setProperty("webdriver.chrome.silentOutput", "true");
		driver = launchbrowser();
		String currentWindow = driver.getWindowHandle();
		driver.switchTo().window(currentWindow);
		// setting up property to suppre ss the warning
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
		//deliveryMilestonePageObj.verifyActiveProject(driver, searchProjectNumber);

	}

	@Test(priority = 0)
	public void verify_delivery_milestone() throws Exception {
		boolean isProjectSelected = deliveryMilestonePageObj.verifyActiveProject(driver, searchProjectNumber);
		IsTrue(isProjectSelected, "Project " + searchProjectNumber + "is selected successfully",
				"Failed to select project: " + searchProjectNumber);
		boolean addDeliveryMilestoneButtoonClicked = deliveryMilestonePageObj.verifyDeliveryMilestoneButton(driver);
		IsTrue(isProjectSelected, "Add delivery milestone button is clicked successfully",
				"Failed to click on Add delivery milestonee button");
	}

	@Test(priority = 1)
	public void add_delivery_milestone() throws Exception {
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
// Thread.sleep(3000);
		boolean verifyStatus = deliveryMilestonePageObj.verifyMilestoneDetails(driver, milestoneID, milestoneName, "0%",
				"Open");
		IsTrue(verifyStatus, "Milestone is added successfully", "Failed to add milestone ");
	}

	@DataProvider
	public Object[][] readMilestoneDataFromExcel() throws Exception {

		Object[][] testObjArray = ExcelUtils.getTableArray(
				System.getProperty("user.dir") + "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone.xlsx",
				"DeleteMilestone", 2);
		for (Object[] obj : testObjArray) {
			for (Object str : obj) {
				System.out.println(str);
			}
		}
		return (testObjArray);
	}

	@Test(dataProvider = "readMilestoneDataFromExcel", priority = 2)
	public void verify_delMilestone_negative_tcs(String reasonCode, String statusUpdate) {
		deliveryMilestonePageObj.clickMilestoneDeleteButton(driver, milestoneID);
		boolean status = deliveryMilestonePageObj.verify_negScenarios_deleteMilestone(driver, milestoneID, reasonCode,
				statusUpdate);
		if (status == true) {
			ExcelUtils.setCellData("TC_DeliveryMilestone", "DeleteMilestone", rowNum, "TC_Status", "PASS", "GREEN");
			try {
				excel.logTestResult("TC_DELETE_DELIVERY_MILESTONE", "PASS");
			} catch (Exception e) {
				logError(e.getMessage());
			}
		} else {
			ExcelUtils.setCellData("TC_DeliveryMilestone", "Sheet1", rowNum, "TC_Status", "FAIL", "RED");
			try {
				excel.logTestResult("TC_DELETE_DELIVERY_MILESTONE", "FAIL");
			} catch (Exception e) {
				logError(e.getMessage());
			}
		}
		rowNum++;
	}
}

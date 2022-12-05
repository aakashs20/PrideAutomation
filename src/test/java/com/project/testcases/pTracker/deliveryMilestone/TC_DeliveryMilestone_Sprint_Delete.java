package com.project.testcases.pTracker.deliveryMilestone;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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
import com.project.pageobjects.pTracker.DeliveryMilestonePage;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class TC_DeliveryMilestone_Sprint_Delete extends TestBase {
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	DeliveryMilestonePage deliveryMilestonePageObj;
	ExcelUtils excel;

	Operations op;
	CommonPages cp;
	Properties prop;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";
	private String searchProjectNumber = "ADES9903";
	private String milestoneID;
	private String excelPath = System.getProperty("user.dir")+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Sprint.xlsx";
	private int tcStatusFlag = 0;

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
	}

	@DataProvider
	public Object[] getReasonCodes() throws Exception {
		String reasonCodeList = ExcelUtils.getCellData(excelPath, 2, "Reason code",	"TC_DeliveryMilestone_Delete_Sprint");
		String reasonCodes[] = reasonCodeList.split(";");
		return (reasonCodes);
	}

	@Test(dataProvider = "getReasonCodes")
	public void deleteSprint(String reasonCode) throws Exception {
		excel = new ExcelUtils();
		int rowNum;
		String tcID = null;

		// **************Create sprint***********************
		rowNum = ExcelUtils.getRowNumXSSF(excelPath, "Create", "Test case", "TC_DeliveryMilestone_Sprint_Create");
//		String milestoneId ="MILEID366";
//		String sprintID = "SPRINTID75";
		try {
			int randomNum = op.generateRandomNum();
			String sprintID = "SPRINTID" + randomNum;
			String sprintName = "SPRINTNAME" + randomNum;
			boolean isAddDeliveryMilestoneButtonClicked = deliveryMilestonePageObj.clkAddDeliveryMilestoneButton();
			IsTrue(isAddDeliveryMilestoneButtonClicked, "Successfully clicked on Add Delivery Milestone buttons",
					"Failed to click on Add Delivery Milestone button.");
			boolean isSprintOptionClicked = deliveryMilestonePageObj.clkSprintOption();
			IsTrue(isSprintOptionClicked, "Sprint option selected", "Failed to click on sprint option.");
			boolean isSwitchedToAddSprintFrame = deliveryMilestonePageObj.switchToAddFrame();
			IsTrue(isSwitchedToAddSprintFrame, "Switched to add sprint frame.",
					"Failed to switch to add sprint frame.");
			boolean isPhaseIDSet = deliveryMilestonePageObj.enterTextToSprintID(sprintID);
			IsTrue(isPhaseIDSet, "Entered sprint ID: " + sprintID,
					"Fail,d to add text to sprint ID as '" + sprintID + "'");
			boolean isSprintNameSet = deliveryMilestonePageObj.enterTextToSprintName(sprintName);
			IsTrue(isSprintNameSet, "Entered sprint Name: " + sprintName,
					"Failed to add text to sprint name as '" + sprintName + "'");
			boolean isStartDateCalendarClicked = deliveryMilestonePageObj.clkStartDateCalendar();
			IsTrue(isStartDateCalendarClicked, "Clicked on start date calender",
					"Failed to click on Start Date Calendar.");
			boolean isStartDateClicked = deliveryMilestonePageObj.clkStartDate();
			IsTrue(isStartDateClicked, "Clicked on start date", "Failed to click on Start Date.");
			boolean isEndDateCalendarClicked = deliveryMilestonePageObj.clkEndDateCalendar();
			IsTrue(isEndDateCalendarClicked, "Clicked on end date calender", "Failed to click on End Date Calendar.");
			boolean isEndDateClicked = deliveryMilestonePageObj.clkEndDate();
			IsTrue(isEndDateClicked, "Clicked on end date", "Failed to click on End Date.");
			HashMap<String, String> map = ExcelUtils.getTestDataXlsx(excelPath, "Create", 0, rowNum);
			String team = map.get("Team");
			String sprintCycle = map.get("Sprint Cycle");
			String uom = map.get("Unit of Measure");
			String plannedCapacity = map.get("Planned Capacity");
			String stories = map.get("Stories");
			boolean isSprintCycleSelected = deliveryMilestonePageObj.selectSprintCycle(sprintCycle);
			IsTrue(isSprintCycleSelected, "Successfully selected sprint cycle: " + sprintCycle,
					"Failed to select sprint cycle: " + sprintCycle);
			boolean isUOMSelected = deliveryMilestonePageObj.selectUOM(uom);
			IsTrue(isUOMSelected, "Successfully selected Unit of Measure: " + uom,
					"Failed to select Unit of Measure: " + uom);
			boolean isStoriesSelected = deliveryMilestonePageObj.enterTextToSprintStories(stories);
			IsTrue(isStoriesSelected, "Successfully entered sprint stories: " + stories,
					"Failed to enter sprint stories: " + stories);
			boolean isPlannedCapacitySelected = deliveryMilestonePageObj
					.enterTextToSprintPlannedCapacity(plannedCapacity);
			IsTrue(isPlannedCapacitySelected, "Successfully entered sprint planned capacity: " + plannedCapacity,
					"Failed to enter sprint planned capacity: " + plannedCapacity);
			boolean isTeamSelected = deliveryMilestonePageObj.selectTeam(team);
			IsTrue(isTeamSelected, "Successfully entered team: " + team, "Failed to enter team: " + team);
			boolean isParentSelected = deliveryMilestonePageObj.enterParentMilestone(driver, milestoneID);
			IsTrue(isParentSelected, "Successfully selected parent milestone", "Failed to select parent milestone.");
			boolean isAddButtonOnAddMilestonePageClicked = deliveryMilestonePageObj.clkAddButton();
			IsTrue(isAddButtonOnAddMilestonePageClicked, "Successfully clicked on Add button",
					"Failed to click on Add button on Add Milestone page.");
			boolean foundMilestoneId = deliveryMilestonePageObj.checkListMilestone(milestoneID, driver);
			IsTrue(foundMilestoneId != false, "Milestone ID is correctly displayed",
					"Milestone ID is not displayed correctly");
			deliveryMilestonePageObj.clickExpandIcon(milestoneID);
			String actualSprintID = deliveryMilestonePageObj.verifyId(milestoneID, sprintID);
			Equals(actualSprintID, sprintID, "Sprint ID is correctly displayed",
					"Sprint ID is not displayed correctly");
			String actualSprintName = deliveryMilestonePageObj.verifyName(sprintID);
			Equals(actualSprintName, sprintName, "Sprint name is correctly displayed",
					"Sprint name is not displayed correctly");
			String milestoneType = deliveryMilestonePageObj.verifyMilestoneType(sprintID);
			Equals(milestoneType, "Sprint", "Milestone type is correctly displayed",
					"Milestone type is not displayed correctly");
			String status = deliveryMilestonePageObj.verifyStatus(sprintID);
			IsTrue(status.contains("Open"), "Sprint status is correctly displayed",
					"Sprint status is not displayed correctly");

			// ***************Delete Sprint*****************************//
			rowNum = ExcelUtils.getRowNumXSSF(excelPath, "Delete", "Test case", "TC_DeliveryMilestone_Delete_Sprint");
			tcID = ExcelUtils.getCellData(rowNum, 0, excelPath, "Delete");
			logInfo("Starting of Test Case : " + tcID);
			boolean isDeleteIconClicked = deliveryMilestonePageObj.clickDeleteIcon(sprintID);
			IsTrue(isDeleteIconClicked, "Delete icon is clicked", "Failed to click on delete icon");
			boolean isSwitchedToDeleteMilestoneFrame = deliveryMilestonePageObj.switchToDeleteMilestoneFrame();
			IsTrue(isSwitchedToDeleteMilestoneFrame, "Switched to delete frame successfully",
					"Failed to switch to delete frame");
			String statusUpdate = ExcelUtils.getCellData(excelPath, 2, "Status Update", tcID);
			logInfo("Status update: " + statusUpdate);
			boolean isStatusUpdateSet = deliveryMilestonePageObj.enterTextToStatusUpdateField(statusUpdate);
			IsTrue(isStatusUpdateSet, "Entered status update", "Failed to enter status update: " + statusUpdate);
			boolean isReasonCodeDropdownClicked = deliveryMilestonePageObj.clkReasonCodeDropDown();
			IsTrue(isReasonCodeDropdownClicked, "Reason code dropdown clicked to view available reason codes",
					"Failed to click on reason code dropdown");
			boolean isSelected = deliveryMilestonePageObj.selectReasonCode(reasonCode);
			IsTrue(isSelected, "Reason code selected: " + reasonCode, "Failed to select reason code: " + reasonCode);
			boolean isSwitchedToDeleteMilestoneFrame1 = deliveryMilestonePageObj.switchToDeleteMilestoneFrame();
			IsTrue(isSwitchedToDeleteMilestoneFrame1, "Switched to delete milestone frame",
					"Failed to switch to delete frame");
			boolean isButtonClicked = deliveryMilestonePageObj.clkObsoleteButton();
			IsTrue(isButtonClicked, "Clicked on Obsolete button", "Failed to delete sprint");
			// op.switchToDefault();
			boolean isMsgDisplayed = deliveryMilestonePageObj.verifySuccessMsg();
			IsTrue(isMsgDisplayed, "Sprint deleted successfully", "Failed to delete sprint");
			try {
				ExcelUtils.logTestResult("TC_DeliveryMilestone_Sprint", "Delete", rowNum, "PASS", tcID);
				// excel.logTestResult(tcID, "PASS");
			} catch (Exception e) {
				logError(e.getMessage());
			}
		} catch (Exception e) {
			ExcelUtils.logTestResult("TC_DeliveryMilestone_Sprint", "Delete", rowNum, "FAIL", tcID);
			try {
				// excel.logTestResult("TC_DeliveryMilestone_Delete_Sprint", "FAIL");
			} catch (Exception e1) {
				logError(e.getMessage());
			}
		}
		rowNum++;
		logInfo("End of Test Case : " + tcID);
	}

	@AfterClass
	public void closeBrowser() throws Exception {
			driver.close();
	}
}

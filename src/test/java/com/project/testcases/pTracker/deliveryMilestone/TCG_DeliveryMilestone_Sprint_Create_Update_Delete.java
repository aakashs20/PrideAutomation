package com.project.testcases.pTracker.deliveryMilestone;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
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

public class TCG_DeliveryMilestone_Sprint_Create_Update_Delete extends TestBase {
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	DeliveryMilestonePage deliveryMilestonePageObj;
	Properties prop;
	Operations op;
	CommonPages cp;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";
	Constants con;
	private String datapoolPath = System.getProperty("user.dir")
			+ "\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	private String excelPath = System.getProperty("user.dir")
			+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Sprint.xls";
	private String searchProjectNumber;
	String tcID = "TC_DeliveryMilestone_Sprint_Create";
	PropertiesConfiguration config;
	PropertiesConfiguration config_DeliveryProp;
	Properties deliveryProp;
	String milestoneID;
	private static int rowNum = 2;
	String newMilestoneID;
	String statusUpdate;
	String milestoneName;
	ExcelUtils excel;

	@BeforeClass
	public void groupInit() throws Exception {
		config = new PropertiesConfiguration(Constants.configFile);
		prop = new Properties();
		prop.load(new FileInputStream(Constants.configFile));

		config_DeliveryProp = new PropertiesConfiguration(Constants.deliveryMilestonePropertyFile);
		deliveryProp = new Properties();
		deliveryProp.load(new FileInputStream(Constants.deliveryMilestonePropertyFile));

		searchProjectNumber = prop.getProperty("projectID");
		driver = launchbrowser();
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		controlActions = new ControlActions(driver);
		op = new Operations(driver);
		op.getUrl(prop.getProperty("appl_url_dev"));
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		cp = new CommonPages(driver);
		loginPage.waitForPageLoaded(driver);
		loginPage.TC_Login(driver, uName, uPassword);
		threadsleep(2000);
		loginPage.TC_ChangeUser(driver, eName);
		deliveryMilestonePageObj = new DeliveryMilestonePage(driver);
		deliveryMilestonePageObj.verifyActiveProject(driver, searchProjectNumber);
		deliveryMilestonePageObj.verifyDeliveryMilestoneButton(driver);
	}

	@Test(priority = 1)
	public void validateAddDeliveryMilestone() throws Exception {
		logInfo("Starting of Test Case : " + tcID);
		int rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Create", "testCase", tcID);
		logInfo("Test case Row No is : " + rowNum);
		int tc_Row_Num = ExcelUtils.getRowNumHSSF(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row No is : " + tc_Row_Num);

		op.waitForAnElementToBeClickable(deliveryMilestonePageObj.openAddDeliveryMilestone);
		op.clickElement(deliveryMilestonePageObj.openAddDeliveryMilestone);
		boolean isSprintOptionClicked = deliveryMilestonePageObj.clkSprintOption();
		IsTrue(isSprintOptionClicked, "Sprint option selected", "Failed to click on sprint option.");
		boolean isSwitchedToAddSprintFrame = deliveryMilestonePageObj.switchToAddFrame();
		IsTrue(isSwitchedToAddSprintFrame, "Switched to add sprint frame.", "Failed to switch to add sprint frame.");

		boolean isAddButtonOnAddReleasePageClicked = deliveryMilestonePageObj.clkAddButton();
		IsTrue(isAddButtonOnAddReleasePageClicked, "", "Failed to click on Add button on Add Sprint page.");

		deliveryMilestonePageObj.validateAddSprint();
		op.waitForAnElementToBeClickable(deliveryMilestonePageObj.closeNotification);
		Thread.sleep(1000);
		op.click(deliveryMilestonePageObj.closeNotification);
		op.waitForAnElementToBeClickable(deliveryMilestonePageObj.cancelMilestone);
		op.clickElement(deliveryMilestonePageObj.cancelMilestone);
	}

	@Test(priority = 2, enabled = true)
	public void create_new_sprint() throws Exception {
		logInfo("Starting of Test Case : " + tcID);
		excel = new ExcelUtils();
		int rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Create", "Test case", tcID);

		String tcID = ExcelUtils.getCellDataX(rowNum, 0, excelPath, "Create");
		logInfo("Starting of Test Case : " + tcID);
		int tc_Row_Num = ExcelUtils.getRowNumHSSF(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		excel = new ExcelUtils();
		milestoneID = deliveryMilestonePageObj.createMilestone();
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
					"Failed to add text to sprint ID as '" + sprintID + "'");
			boolean isSprintNameSet = deliveryMilestonePageObj.enterTextToSprintName(sprintName);
			IsTrue(isSprintNameSet, "Entered sprint Name: " + sprintName,
					"Failed to add text to sprint name as '" + sprintName + "'");
			HashMap<String, String> createTestDataMap = ExcelUtils.getTestDataXls(excelPath, "Create", 0, rowNum - 1);
			String team = createTestDataMap.get("Team");
			String sprintCycle = createTestDataMap.get("Sprint Cycle");
			String uom = createTestDataMap.get("Unit of Measure");
			String plannedCapacity = createTestDataMap.get("Planned Capacity");
			String stories = createTestDataMap.get("Stories");

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
			boolean isSprintCycleSelected = deliveryMilestonePageObj.selectSprintCycle(sprintCycle);
			IsTrue(isSprintCycleSelected, "Successfully selected sprint cycle: " + sprintCycle,
					"Failed to select sprint cycle: " + sprintCycle);
			driver.switchTo().defaultContent();
			threadsleep(2000);
			op.waitForAnElementToBeClickable(deliveryMilestonePageObj.okButton);
			op.clickElement(deliveryMilestonePageObj.okButton);
			deliveryMilestonePageObj.switchToAddFrame();
			boolean isAddButtonOnAddMilestonePageClicked = deliveryMilestonePageObj.clkAddButton();
			IsTrue(isAddButtonOnAddMilestonePageClicked, "Successfully clicked on Add button",
					"Failed to click on Add button on Add Milestone page.");
			boolean foundMilestoneId = deliveryMilestonePageObj.checkListMilestone(milestoneID, driver);
			IsTrue(foundMilestoneId != false, "Milestone ID is correctly displayed",
					"Milestone ID is not displayed correctly");
			boolean expandIconClicked = deliveryMilestonePageObj.clickExpandIcon(milestoneID);
			IsTrue(expandIconClicked, "Clicked (+) icon ", "Fail to click on (+) icon");
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

			if (status.contains("Open")) {
				logInfo("Writing MileStone ID -> " + milestoneID + " and Sprint ID -> " + sprintID
						+ " to Properties file.");
				deliveryProp.setProperty("sprint.milestoneId", milestoneID);
				deliveryProp.setProperty("sprint.sprintID", sprintID);
			}
			try {
				ExcelUtils.logTestResult(excelPath, "Create", rowNum - 1, "PASS", tcID);
			} catch (Exception e1) {
				logError(e1.getMessage());
				throw e1;
			}
		} catch (AssertionError e) {
			logError(e.getMessage());
			ExcelUtils.logTestResult(excelPath, "Create", rowNum - 1, "FAIL", tcID);
			throw e;
		} catch (Exception e) {
			logError(e.getMessage());
			ExcelUtils.logTestResult(excelPath, "Create", rowNum - 1, "FAIL", tcID);
			throw e;
		}
		logInfo("End of Test Case : " + tcID);
	}

	@Test(priority = 3)
	public void updateSprint() throws IOException, Exception {
		String tcID1 = "TC_DeliveryMilestone_Update_Sprint";
		logInfo("Starting of Test Case : " + tcID1);
		excel = new ExcelUtils();
		int rowNum;
		rowNum = ExcelUtils.getRowNumXSSF(excelPath, "Create", "Test case", tcID);
		try {
			boolean updateIconClicked = deliveryMilestonePageObj
					.clickUpdateIcon(deliveryProp.getProperty("sprint.sprintID"));
			IsTrue(updateIconClicked, "Update icon clicked.", "Failed to click on update icon.");
			boolean isSwitchedToUpdateReleaseFrame = deliveryMilestonePageObj.switchToUpdateFrame();
			IsTrue(isSwitchedToUpdateReleaseFrame, "Switched to update phase frame.",
					"Failed to switch to update phase frame.");
			rowNum = ExcelUtils.getRowNumXSSF(excelPath, "Update", "Test case", tcID1);
			HashMap<String, String> updateTestDataMap = ExcelUtils.getTestDataXlsx(excelPath, "Update", 0, rowNum - 1);
			String scopeChange = updateTestDataMap.get("Scope Change");
			boolean isScopeChangeSet = deliveryMilestonePageObj.enterTextToScopeChange(scopeChange);
			IsTrue(isScopeChangeSet, "Successfully entered sprint scope change: " + scopeChange,
					"Failed to enter sprint scope change: " + scopeChange);
			String inProgressStories = updateTestDataMap.get("In Progress");
			boolean isInProgressValueSet = deliveryMilestonePageObj.enterTextToInProgressField(inProgressStories);
			IsTrue(isInProgressValueSet, "Successfully entered value in 'In Progress' field: " + inProgressStories,
					"Failed to enter value in 'In Progress' field: " + inProgressStories);
			String blockedStories = updateTestDataMap.get("Blocked");
			boolean isBlockedFieldSet = deliveryMilestonePageObj.enterTextToBlockedField(blockedStories);
			IsTrue(isBlockedFieldSet, "Successfully entered value in 'Blocked' field: " + blockedStories,
					"Failed to enter value in 'Blocked' field: " + blockedStories);
			String completedStories = updateTestDataMap.get("Completed");
			boolean isCompletedFieldSet = deliveryMilestonePageObj.enterTextToCompletedField(completedStories);
			IsTrue(isCompletedFieldSet, "Successfully entered value in 'Completed'vbg" + " neld: " + completedStories,
					"Failed to enter value in 'Completed' field: " + completedStories);
			String ragStatus = updateTestDataMap.get("RAG Status");
			boolean isRAGStatusSet = deliveryMilestonePageObj.selectRAGStatus(ragStatus);
			IsTrue(isRAGStatusSet, "Successfully selected RAG status" + ragStatus,
					"Failed to select RAG status: " + ragStatus);
			String p1DefectCount = updateTestDataMap.get("P1 - High Priority");
			boolean isP1DefectFieldSet = deliveryMilestonePageObj.enterTextToP1DefectField(p1DefectCount);
			IsTrue(isP1DefectFieldSet, "Successfully entered value in 'P1 Defect' field: " + p1DefectCount,
					"Failed to enter value in 'P1 Defect' field: " + p1DefectCount);
			String p2DefectCount = updateTestDataMap.get("P2- High Priority");
			boolean isP2DefectFieldSet = deliveryMilestonePageObj.enterTextToP2DefectField(p2DefectCount);
			IsTrue(isP2DefectFieldSet, "Successfully entered value in 'P2 Defect' field: " + p2DefectCount,
					"Failed to enter value in 'P2 Defect' field: " + p2DefectCount);
			String p3DefectCount = updateTestDataMap.get("P3");
			boolean isP3DefectFieldSet = deliveryMilestonePageObj.enterTextToP3DefectField(p3DefectCount);
			IsTrue(isP3DefectFieldSet, "Successfully entered value in 'P3 Defect' field: " + p3DefectCount,
					"Failed to enter value in 'P3 Defect' field: " + p3DefectCount);
			String p4DefectCount = updateTestDataMap.get("P4");
			boolean isP4DefectFieldSet = deliveryMilestonePageObj.enterTextToP4DefectField(p4DefectCount);
			IsTrue(isP4DefectFieldSet, "Successfully entered value in 'P4 Defect' field: " + p4DefectCount,
					"Failed to enter value in 'P4 Defect' field: " + p4DefectCount);
			deliveryMilestonePageObj.clkSaveChangesButton();
			boolean successMsg = deliveryMilestonePageObj.verifySuccessMsg();
			IsTrue(successMsg, "Sprint is updated successfully", "Fail to update sprint");
			ExcelUtils.logTestResult("TC_DeliveryMilestone_Sprint", "Update", rowNum - 1, "PASS", tcID);
		} catch (AssertionError e) {
			logError(e.getMessage());
			ExcelUtils.logTestResult("TC_DeliveryMilestone_Sprint", "Update", rowNum - 1, "FAIL", tcID);
			throw e;
		} catch (Exception e) {
			logError(e.getMessage());
			ExcelUtils.logTestResult("TC_DeliveryMilestone_Sprint", "Update", rowNum - 1, "FAIL", tcID);
			throw e;
		}
		logInfo("End of Test Case : " + tcID);
	}

	@DataProvider
	public Object[] getReasonCodes() throws Exception {
		String reasonCodeList = ExcelUtils.getCellData(excelPath, 2, "Reason code",
				"TC_DeliveryMilestone_Delete_Sprint");
		String reasonCodes[] = reasonCodeList.split(";");
		return (reasonCodes);
	}

	@Test(dataProvider = "getReasonCodes")
	public void deleteSprint(String reasonCode) throws Exception {
		excel = new ExcelUtils();
		int rowNum;

		rowNum = ExcelUtils.getRowNumXSSF(excelPath, "Create", "Test case", "TC_DeliveryMilestone_Sprint_Create");
		try {
			rowNum = ExcelUtils.getRowNumXSSF(excelPath, "Delete", "Test case", "TC_DeliveryMilestone_Delete_Sprint");
			tcID = ExcelUtils.getCellData(rowNum, 0, excelPath, "Delete");
			logInfo("Starting of Test Case : " + tcID);
			boolean isDeleteIconClicked = deliveryMilestonePageObj
					.clickDeleteIcon(deliveryProp.getProperty("sprint.sprintID"));
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
			boolean isMsgDisplayed = deliveryMilestonePageObj.verifySuccessMsg();
			IsTrue(isMsgDisplayed, "Sprint deleted successfully", "Failed to delete sprint");
			try {
				ExcelUtils.logTestResult("TC_DeliveryMilestone_Sprint", "Delete", rowNum, "PASS", tcID);
			} catch (Exception e) {
				logError(e.getMessage());
			}
		} catch (Exception e) {
			ExcelUtils.logTestResult("TC_DeliveryMilestone_Sprint", "Delete", rowNum, "FAIL", tcID);

		}
		rowNum++;
		logInfo("End of Test Case : " + tcID);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		try {
			op.closeBrowser(driver);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
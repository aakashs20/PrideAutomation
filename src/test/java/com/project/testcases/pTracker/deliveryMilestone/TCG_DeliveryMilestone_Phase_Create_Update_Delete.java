package com.project.testcases.pTracker.deliveryMilestone;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
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

public class TCG_DeliveryMilestone_Phase_Create_Update_Delete extends TestBase {
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	DeliveryMilestonePage deliveryMilestonePageObj;
	ExcelUtils excel;
	PropertiesConfiguration config;
	PropertiesConfiguration config_DeliveryProp;

	Operations op;
	CommonPages cp;
	Properties prop;
	Properties deliveryProp;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";

	private String searchProjectNumber = "PIUDNDMF";
	private String datapoolPath = System.getProperty("user.dir")
			+ "\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	private String excelPath = System.getProperty("user.dir")
			+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Phase.xls";
	private static int rowNum = 1;
	static int tcRowNum;
	String expectedErrorMsg[];
	int errorCnt;
	String tcID = "TC_DELIVERY_MILESTONE_CREATE_PHASE";
	private String milestoneID;
	String newMilestoneID;
	String statusUpdate;
	String milestoneName;

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

	@Test(priority = 1, description = "Validate Error Messages on Add Phase Popup")
	public void validateAndAddPhase() throws Exception {
		logInfo("Starting of Test Case : " + tcID);
		int rowNum = ExcelUtils.getRowNum(excelPath, "Create", "testCase", tcID);
		logInfo("Test case Row No is : " + rowNum);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row No is : " + tc_Row_Num);
		op.waitForAnElementToBeClickable(deliveryMilestonePageObj.openAddDeliveryMilestone);
		op.clickElement(deliveryMilestonePageObj.openAddDeliveryMilestone);
		boolean isPhaseOptionClicked = deliveryMilestonePageObj.clkPhaseOption();
		IsTrue(isPhaseOptionClicked, "Phase option selected", "Failed to click on phase option.");
		boolean isSwitchedToAddPhaseFrame = deliveryMilestonePageObj.switchToAddFrame();
		IsTrue(isSwitchedToAddPhaseFrame, "Switched to add phase frame.", "Failed to switch to add phase frame.");
		boolean isAddButtonOnAddMilestonePageClicked = deliveryMilestonePageObj.clkAddButton();
		IsTrue(isAddButtonOnAddMilestonePageClicked, "", "Failed to click on Add button on Add Milestone page.");
		deliveryMilestonePageObj.validateAddRelease_Task_Phase_Popup(driver, deliveryProp.getProperty("Phase"));
		op.waitForAnElementToBeClickable(deliveryMilestonePageObj.closeNotification);
		threadsleep(2000);
		op.click(deliveryMilestonePageObj.closeNotification);
		op.waitForAnElementToBeClickable(deliveryMilestonePageObj.cancelMilestone);
		op.clickElement(deliveryMilestonePageObj.cancelMilestone);
	}

	@Test(priority = 2, description = "Create phase through Add phase popup")
	public void create_new_phase() throws Exception {
		String tcID = ExcelUtils.getCellDataX(rowNum, 0, excelPath, "Create");
		logInfo("Starting of Test Case : " + tcID);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		excel = new ExcelUtils();
		int rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Create", "testCase", tcID);
		try {
			milestoneID = deliveryMilestonePageObj.createMilestone();
			int randomNum = op.generateRandomNum();
			String phaseID = "PHASEID" + randomNum;
			String phaseName = "PHASENAME" + randomNum;
			threadsleep(2000);
			logInfo("Creating Phase with Phase ID -> " + phaseID + " and Phase Name ->" + phaseName);
			boolean isAddDeliveryMilestoneButtonClicked = deliveryMilestonePageObj.clkAddDeliveryMilestoneButton();
			IsTrue(isAddDeliveryMilestoneButtonClicked, "Successfully clicked on Add Delivery Milestone buttons",
					"Failed to click on Add Delivery Milestone button.");
			boolean isPhaseOptionClicked = deliveryMilestonePageObj.clkPhaseOption();
			IsTrue(isPhaseOptionClicked, "Phase option selected", "Failed to click on phase option.");
			boolean isSwitchedToAddTaskFrame = deliveryMilestonePageObj.switchToAddFrame();
			IsTrue(isSwitchedToAddTaskFrame, "Switched to add task frame.", "Failed to switch to add task frame.");
			boolean isPhaseIDSet = deliveryMilestonePageObj.enterTextPhaseID(phaseID);
			IsTrue(isPhaseIDSet, "Entered phase ID: " + phaseID, "Failed to add text to phase ID as '" + phaseID + "'");
			boolean isParentSelected = deliveryMilestonePageObj.enterParentMilestone(driver, milestoneID);
			IsTrue(isParentSelected, "Successfully selected parent milestone", "Failed to select parent milestone.");
			boolean isPhaseNameSet = deliveryMilestonePageObj.enterTextPhaseName(phaseName);
			IsTrue(isPhaseNameSet, "Entered phase Name: " + phaseName,
					"Failed to add text to phase name as '" + phaseName + "'");
			boolean isStartDateCalendarClicked = deliveryMilestonePageObj.clkStartDateCalendar();
			IsTrue(isStartDateCalendarClicked, "Clicked on start date calender",
					"Failed to click on Start Date Calendar.");
			boolean isStartDateClicked = deliveryMilestonePageObj.clkStartDate();
			IsTrue(isStartDateClicked, "Clicked on start date", "Failed to click on Start Date.");
			boolean isEndDateCalendarClicked = deliveryMilestonePageObj.clkEndDateCalendar();
			IsTrue(isEndDateCalendarClicked, "Clicked on end date calender", "Failed to click on End Date Calendar.");
			boolean isEndDateClicked = deliveryMilestonePageObj.clkEndDate();
			IsTrue(isEndDateClicked, "Clicked on end date", "Failed to click on End Date.");
			boolean isAddButtonOnAddMilestonePageClicked = deliveryMilestonePageObj.clkAddButton();
			IsTrue(isAddButtonOnAddMilestonePageClicked, "Successfully clicked on Add button",
					"Failed to click on Add button on Add Milestone page.");
			deliveryMilestonePageObj.clickExpandIcon(milestoneID);
			String actualPhaseID = deliveryMilestonePageObj.verifyId(milestoneID, phaseID);
			Equals(actualPhaseID, phaseID, "Phase ID is correctly displayed", "Phase ID is not displayed correctly");
			String actualPhaseName = deliveryMilestonePageObj.verifyName(actualPhaseID);
			Equals(actualPhaseName, phaseName, "Phase name is correctly displayed",
					"Phase name is not displayed correctly");
			String milestoneType = deliveryMilestonePageObj.verifyMilestoneType(actualPhaseID);
			Equals(milestoneType, "Phase", "Milestone type is correctly displayed",
					"Milestone type is not displayed correctly");
			String status = deliveryMilestonePageObj.verifyStatus(actualPhaseID);
			IsTrue(status.contains("Open"), "Phase status is correctly displayed",
					"Phase status is not displayed correctly");
			if (status.contains("Open")) {
				logInfo("Writing MileStone ID -> " + milestoneID + " and Release ID -> " + phaseID
						+ " to Properties file.");
				config_DeliveryProp.setProperty("phase.milestoneId", milestoneID);
				config_DeliveryProp.setProperty("phase.phaseID", phaseID);
				config_DeliveryProp.save();
				ExcelUtils.logTestResult(excelPath, "Create", rowNum, "PASS", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "GREEN");
			} else {
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

	@DataProvider
	public Object[] getStatus() throws Exception {
		String statusList = ExcelUtils.getCellDataX(excelPath, 1, "Reason code", "TC_DELIVERY_MILESTONE_UPDATE_PHASE");
		String status[] = statusList.split(";");
		return (status);
	}

	@Test(priority = 3, dataProvider = "getStatus", description = "To update Phase with different status")
	public void updatePhase(String status) throws IOException, Exception {
		String tcID = ExcelUtils.getCellDataX(1, 0, excelPath, "Update");
		rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Update", "testCase", tcID);
		logInfo("Starting of Test Case : " + tcID);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		try {
			logInfo("Milestone ID is : " + config_DeliveryProp.getProperty("phase.milestoneId"));
			logInfo("Phase ID is : " + config_DeliveryProp.getProperty("phase.phaseID"));
			deliveryMilestonePageObj.clickExpandIcon(milestoneID);
			deliveryMilestonePageObj.clickUpdateIcon(config_DeliveryProp.getProperty("phase.phaseID").toString());
			boolean isSwitchedToUpdateReleaseFrame = deliveryMilestonePageObj.switchToUpdateFrame();
			IsTrue(isSwitchedToUpdateReleaseFrame, "Switched to update phase frame.",
					"Failed to switch to update phase frame.");
			deliveryMilestonePageObj.updateStatus(status);
			String statusUpdate = ExcelUtils.getCellDataX(excelPath, 1, "Comment", tcID);
			boolean isStatusUpdateSet = deliveryMilestonePageObj.enterTextToStatusUpdateField(statusUpdate);
			logInfo("Successfully entered status update as: " + statusUpdate);
			deliveryMilestonePageObj.clkSaveChangesButton();
			controlActions.switchToDefault();
			threadsleep(2000);
			deliveryMilestonePageObj.verifySuccessMsg();
			deliveryMilestonePageObj.clickExpandIcon(config_DeliveryProp.getProperty("phase.milestoneId").toString());
			String phaseStatus = controlActions.getText(
					controlActions.performGetElementByXPath(DeliveryMilestoneConstants.ADDED_RELEASE_STATUS.replace(
							"MilestoneIDToBeReplaced", config_DeliveryProp.getProperty("phase.phaseID").toString())));
			threadsleep(2000);
			IsTrue(phaseStatus.contains(status.trim()), "Phase status is correctly updated to: " + status,
					"Phase status is not updated correctly");
			if (phaseStatus.contains(status)) {
				ExcelUtils.logTestResult(excelPath, "Update", rowNum, "PASS", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "GREEN");
			} else {
				ExcelUtils.logTestResult(excelPath, "Update", rowNum, "FAIL", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "RED");
			}
		} catch (Exception e) {
			ExcelUtils.logTestResult(excelPath, "Update", rowNum, "FAIL", tcID);
			ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "RED");
			logError(e.getMessage());
		}
		logInfo("\nEnd of Test Case : " + tcID);
	}

	@Test(priority = 4, description = "Validate Delete Phase with different Reason Codes")
	public void deletePhase() throws Exception {
		String tcID = ExcelUtils.getCellDataX(2, 0, excelPath, "Delete");
		rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Delete", "testCase", tcID);
		logInfo("Starting of Test Case : " + tcID);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);

		try {
			logInfo("Milestone ID is : " + config_DeliveryProp.getProperty("phase.milestoneId"));
			logInfo("Phase ID is : " + config_DeliveryProp.getProperty("phase.phaseID"));
			deliveryMilestonePageObj.clickDeleteIcon(config_DeliveryProp.getProperty("phase.phaseID").toString());
			boolean isSwitchedToDeleteMilestoneFrame = deliveryMilestonePageObj.switchToDeleteMilestoneFrame();
			IsTrue(isSwitchedToDeleteMilestoneFrame, "Switched to delete frame successfully",
					"Failed to switch to delete frame");
			boolean isStatusUpdateSet = deliveryMilestonePageObj.enterTextToStatusUpdateField("Updated");
			IsTrue(isStatusUpdateSet, "Entered status update", "Failed to enter status update: " + statusUpdate);
			boolean isReasonCodeDropdownClicked = deliveryMilestonePageObj.clkReasonCodeDropDown();
			IsTrue(isReasonCodeDropdownClicked, "Reason code dropdown clicked to view available reason codes",
					"Failed to click on reason code dropdown");
			List<WebElement> reasonCodes = deliveryMilestonePageObj.reasonCodeList;
			boolean isSelected = deliveryMilestonePageObj.selectReasonCode(reasonCodes.get(0).getText());
			IsTrue(isSelected, "Reason code selected: " + reasonCodes.get(0).getText(),
					"Failed to select reason code: " + reasonCodes.get(0).getText());
			boolean isSwitchedToDeleteMilestoneFrame1 = deliveryMilestonePageObj.switchToDeleteMilestoneFrame();
			IsTrue(isSwitchedToDeleteMilestoneFrame1, "Switched to delete milestone frame",
					"Failed to switch to delete frame");
			boolean isButtonClicked = deliveryMilestonePageObj.clkObsoleteButton();
			IsTrue(isButtonClicked, "Clicked on Obsolete button", "Failed to delete milestone");
			op.switchToDefault();
			boolean isMsgDisplayed = deliveryMilestonePageObj.verifySuccessMsg();
			IsTrue(isMsgDisplayed, "Phase deleted successfully", "Failed to delete phase");
			if (isMsgDisplayed) {
				ExcelUtils.logTestResult(excelPath, "Delete", rowNum, "PASS", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "GREEN");
			} else {
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

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		try {
			op.closeBrowser(driver);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
package com.project.testcases.pTracker.deliveryMilestone;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;
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

public class TCG_DeliveryMilestone_Task_Create_Update_Delete extends TestBase {
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
			+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Task.xls";
	private static int rowNum = 1;
	static int tcRowNum;
	String expectedErrorMsg[];
	int errorCnt;
	String tcID = "TC_DELIVERY_MILESTONE_CREATE_TASK";
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

	@Test(priority = 1, description = "Validate Error Messages on Add Task Popup")
	public void validateAndAddTask() throws Exception {
		logInfo("Starting of Test Case : " + tcID);
		int rowNum = ExcelUtils.getRowNum(excelPath, "Create", "testCase", tcID);
		logInfo("Test case Row No is : " + rowNum);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row No is : " + tc_Row_Num);
		op.waitForAnElementToBeClickable(deliveryMilestonePageObj.openAddDeliveryMilestone);
		op.clickElement(deliveryMilestonePageObj.openAddDeliveryMilestone);
		op.waitForAnElementToBeClickable(deliveryMilestonePageObj.taskOption);
		op.clickElement(deliveryMilestonePageObj.taskOption);
		boolean isSwitchedToAddTaskFrame = deliveryMilestonePageObj.switchToAddFrame();
		IsTrue(isSwitchedToAddTaskFrame, "Switched to add task frame.", "Failed to switch to add task frame.");
		boolean isAddButtonOnAddTaskPageClicked = deliveryMilestonePageObj.clkAddButton();
		IsTrue(isAddButtonOnAddTaskPageClicked, "", "Failed to click on Add button on Add task page.");
		deliveryMilestonePageObj.validateAddRelease_Task_Phase_Popup(driver, deliveryProp.getProperty("Task"));
		op.waitForAnElementToBeClickable(deliveryMilestonePageObj.closeNotification);
		Thread.sleep(1000);
		op.click(deliveryMilestonePageObj.closeNotification);
		op.waitForAnElementToBeClickable(deliveryMilestonePageObj.cancelMilestone);
		op.clickElement(deliveryMilestonePageObj.cancelMilestone);
	}

	@Test(priority = 2, description = "Create task through Add Task popup")
	public void createAddDeliveryTask() throws Exception {
		String tcID = ExcelUtils.getCellDataX(rowNum, 0, excelPath, "Create");
		logInfo("Starting of Test Case : " + tcID);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		excel = new ExcelUtils();
		int rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Create", "testCase", tcID);
		try {
			milestoneID = config_DeliveryProp.getProperty("phase.milestoneId").toString();
			int randomNum = op.generateRandomNum();
			String taskID = "TASKID" + randomNum;
			String taskName = "TASKNAME" + randomNum;
			threadsleep(2000);
			logInfo("Creating Task with Task ID -> " + taskID + " and Task Name ->" + taskName);
			boolean isAddDeliveryMilestoneButtonClicked = deliveryMilestonePageObj.clkAddDeliveryMilestoneButton();
			IsTrue(isAddDeliveryMilestoneButtonClicked, "Successfully clicked on Add Delivery Milestone buttons",
					"Failed to click on Add Delivery Milestone button.");
			boolean isTaskOptionClicked = deliveryMilestonePageObj.clkTaskOption();
			IsTrue(isTaskOptionClicked, "Task option selected", "Failed to click on task option.");
			boolean isSwitchedToAddTaskFrame = deliveryMilestonePageObj.switchToAddFrame();
			IsTrue(isSwitchedToAddTaskFrame, "Switched to add task frame.", "Failed to switch to add task frame.");
			boolean isMilestoneIDSet = deliveryMilestonePageObj.enterTextTaskID(taskID);
			IsTrue(isMilestoneIDSet, "Entered task ID: " + taskID, "Failed to add text to task ID as '" + taskID + "'");
			boolean isParentSelected = deliveryMilestonePageObj.enterParentMilestone(driver, milestoneID);
			IsTrue(isParentSelected, "Successfully selected parent milestone", "Failed to select parent milestone.");
			boolean isReleaseNameSet = deliveryMilestonePageObj.enterTextTaskName(taskName);
			IsTrue(isReleaseNameSet, "Entered task Name: " + taskName,
					"Failed to add text to task name as '" + taskName + "'");
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
			IsTrue(isAddButtonOnAddMilestonePageClicked, "", "Failed to click on Add button on Add Milestone page.");
			op.switchToDefault();
			deliveryMilestonePageObj.verifySuccessMsg();
			op.refreshPage();
			WebElement releaseIcon = op.perform_waitUntilVisibility(By.xpath(
					DeliveryMilestoneConstants.VIEW_RELEASE_ICON.replace("MilestoneIDToBeReplaced", milestoneID)));
			op.click(releaseIcon);
			String actualTaskID = deliveryMilestonePageObj.verifyId(milestoneID, taskID);
			Equals(actualTaskID, taskID, "Task ID is correctly displayed", "Task ID is not displayed correctly");
			String actualTaskName = deliveryMilestonePageObj.verifyName(taskID);
			Equals(actualTaskName, taskName, "Task name is correctly displayed",
					"Task name is not displayed correctly");
			String milestoneType = deliveryMilestonePageObj.verifyMilestoneType(taskID);
			Equals(milestoneType, "Tasks", "Milestone type is correctly displayed",
					"Milestone type is not displayed correctly");
			String status = deliveryMilestonePageObj.verifyStatus(taskID);
			IsTrue(status.contains("Open"), "Task status is correctly displayed",
					"Task status is not displayed correctly");
			if (status.contains("Open")) {
				logInfo("Writing MileStone ID -> " + milestoneID + " and task ID -> " + taskID
						+ " to Properties file.");
				config_DeliveryProp.setProperty("task.milestoneId", milestoneID);
				config_DeliveryProp.setProperty("task.taskID", taskID);
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
		String statusList = ExcelUtils.getCellDataX(excelPath, 1, "Reason code", "TC_DELIVERY_MILESTONE_UPDATE_TASK");
		String status[] = statusList.split(";");
		return (status);
	}

	@Test(priority = 3, dataProvider = "getStatus", description = "To update task with different status")
	public void updateTask(String status) throws IOException, Exception {
		String tcID = ExcelUtils.getCellDataX(1, 0, excelPath, "Update");
		rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Update", "testCase", tcID);
		logInfo("Starting of Test Case : " + tcID);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		try {
			logInfo("Milestone ID is : " + milestoneID);
			logInfo("Task ID is : " + config_DeliveryProp.getProperty("task.taskID"));
			deliveryMilestonePageObj.clickExpandIcon(milestoneID);
			deliveryMilestonePageObj.clickUpdateIcon(config_DeliveryProp.getProperty("task.taskID").toString());
			boolean isSwitchedToUpdateReleaseFrame = deliveryMilestonePageObj.switchToUpdateFrame();
			IsTrue(isSwitchedToUpdateReleaseFrame, "Switched to update task frame.","Failed to switch to update task frame.");
			deliveryMilestonePageObj.updateStatus(status);
			boolean isStatusUpdateSet = deliveryMilestonePageObj.enterTextToStatusUpdateField("Updated");
			IsTrue(isStatusUpdateSet, "Entered status update", "Failed to enter status update: " + statusUpdate);
			logInfo("Successfully entered status update as: " + statusUpdate);
			deliveryMilestonePageObj.clkSaveChangesButton();
			controlActions.switchToDefault();
			deliveryMilestonePageObj.verifySuccessMsg();
			deliveryMilestonePageObj.clickExpandIcon(milestoneID);
			threadsleep(2000);
			String taskStatus = controlActions.getText(controlActions.performGetElementByXPath(DeliveryMilestoneConstants.ADDED_RELEASE_STATUS.replace("MilestoneIDToBeReplaced", config_DeliveryProp.getProperty("task.taskID").toString())));
			IsTrue(taskStatus.contains(status.trim()), "Task status is correctly updated to: " + status,"Task status is not updated correctly");

			if (taskStatus.contains(status)) {
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
		logInfo("End of Test Case : " + tcID);
	}

	@Test(priority = 4, description = "Validate Delete Task with different Reason Codes")
	public void deleteTask() throws Exception {
		String tcID = ExcelUtils.getCellDataX(2, 0, excelPath, "Delete");
		rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Delete", "testCase", tcID);
		logInfo("Starting of Test Case : " + tcID);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		try {
			logInfo("Milestone ID is : " + milestoneID);
			logInfo("Task ID is : " + config_DeliveryProp.getProperty("task.taskID"));
			deliveryMilestonePageObj.clickDeleteIcon(config_DeliveryProp.getProperty("task.taskID").toString());
			boolean isSwitchedToDeleteMilestoneFrame = deliveryMilestonePageObj.switchToDeleteMilestoneFrame();
			IsTrue(isSwitchedToDeleteMilestoneFrame, "Switched to delete frame successfully","Failed to switch to delete frame");
			boolean isStatusUpdateSet = deliveryMilestonePageObj.enterTextToStatusUpdateField("Updated");
			IsTrue(isStatusUpdateSet, "Entered status update", "Failed to enter status update: " + statusUpdate);
			boolean isReasonCodeDropdownClicked = deliveryMilestonePageObj.clkReasonCodeDropDown();
			IsTrue(isReasonCodeDropdownClicked, "Reason code dropdown clicked to view available reason codes","Failed to click on reason code dropdown");
			List<WebElement> reasonCodes = deliveryMilestonePageObj.reasonCodeList;
			boolean isSelected = deliveryMilestonePageObj.selectReasonCode(reasonCodes.get(0).getText());
			IsTrue(isSelected, "Reason code selected: " + reasonCodes.get(0).getText(),"Failed to select reason code: " + reasonCodes.get(0).getText());
			boolean isSwitchedToDeleteMilestoneFrame1 = deliveryMilestonePageObj.switchToDeleteMilestoneFrame();
			IsTrue(isSwitchedToDeleteMilestoneFrame1, "Switched to delete milestone frame","Failed to switch to delete frame");
			boolean isButtonClicked = deliveryMilestonePageObj.clkObsoleteButton();
			IsTrue(isButtonClicked, "Clicked on Obsolete button", "Failed to delete milestone");
			op.switchToDefault();
			boolean isMsgDisplayed = deliveryMilestonePageObj.verifySuccessMsg();
			IsTrue(isMsgDisplayed, "Task deleted successfully", "Failed to delete task");
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
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

public class TCG_DeliveryMilestone_Release_Create_Update_Delete extends TestBase {
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
			+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Release.xls";
	private static int rowNum = 1;
	static int tcRowNum;
	String expectedErrorMsg[];
	int errorCnt;
	String tcID = "TC_DELIVERY_MILESTONE_CREATE_RELEASE";
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

	@Test(priority = 1)
	public void validateAndAddRelease() throws Exception {
		int randomNum = op.generateRandomNum();
		milestoneID = "MILESTONEID" + randomNum + "@";
		milestoneName = "MILESTONENAME" + randomNum + "#";

		logInfo("Starting of Test Case : " + tcID);
		int rowNum = ExcelUtils.getRowNum(excelPath, "Create", "testCase", tcID);
		logInfo("Test case Row No is : " + rowNum);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row No is : " + tc_Row_Num);

		op.waitForAnElementToBeClickable(deliveryMilestonePageObj.openAddDeliveryMilestone);
		op.clickElement(deliveryMilestonePageObj.openAddDeliveryMilestone);
		op.waitForAnElementToBeClickable(deliveryMilestonePageObj.ReleaseOption);
		op.clickElement(deliveryMilestonePageObj.ReleaseOption);
		boolean isSwitchedToAddReleaseFrame = deliveryMilestonePageObj.switchToAddFrame();
		IsTrue(isSwitchedToAddReleaseFrame, "Switched to add release frame.", "Failed to switch to add release frame.");
		boolean isAddButtonOnAddReleasePageClicked = deliveryMilestonePageObj.clkAddButton();
		IsTrue(isAddButtonOnAddReleasePageClicked, "", "Failed to click on Add button on Add Milestone page.");

		deliveryMilestonePageObj.validateAddRelease_Task_Phase_Popup(driver, deliveryProp.getProperty("Release"));
		op.waitForAnElementToBeClickable(deliveryMilestonePageObj.closeNotification);
		Thread.sleep(2000);
		op.click(deliveryMilestonePageObj.closeNotification);
		op.waitForAnElementToBeClickable(deliveryMilestonePageObj.cancelMilestone);
		op.clickElement(deliveryMilestonePageObj.cancelMilestone);
	}

	@Test(priority = 2)
	public void createAddDeliveryRelease() throws Exception {
		String tcID = ExcelUtils.getCellDataX(rowNum, 0, excelPath, "Create");
		logInfo("Starting of Test Case : " + tcID);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		excel = new ExcelUtils();
		int rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Create", "testCase", tcID);
		try {
			milestoneID=config_DeliveryProp.getProperty("phase.milestoneId").toString();
			int randomNum = op.generateRandomNum();
			String releaseID = "RELEASEID" + randomNum;
			String releaseName = "RELEASENAME" + randomNum;
			boolean isAddDeliveryMilestoneButtonClicked = deliveryMilestonePageObj.clkAddDeliveryMilestoneButton();
			IsTrue(isAddDeliveryMilestoneButtonClicked, "Successfully clicked on Add Delivery Milestone buttons",
					"Failed to click on Add Delivery Milestone button.");
			boolean isSprintOptionClicked = deliveryMilestonePageObj.clkReleaseOption();
			IsTrue(isSprintOptionClicked, "Release option selected", "Failed to click on Release option.");
			boolean isSwitchedToAddReleaseFrame = deliveryMilestonePageObj.switchToAddFrame();
			IsTrue(isSwitchedToAddReleaseFrame, "Switched to add release frame.",
					"Failed to switch to add release frame.");
			boolean isMilestoneIDSet = deliveryMilestonePageObj.enterTextReleaseID(releaseID);
			IsTrue(isMilestoneIDSet, "Entered release ID: " + releaseID,
					"Failed to add text to release ID as '" + releaseID + "'");
			boolean isParentSelected = deliveryMilestonePageObj.enterParentMilestone(driver, milestoneID);
			IsTrue(isParentSelected, "Successfully selected parent milestone", "Failed to select parent milestone.");
			boolean isReleaseNameSet = deliveryMilestonePageObj.enterTextReleaseName(releaseName);
			IsTrue(isReleaseNameSet, "Entered release Name: " + releaseName,
					"Failed to add text to release name as '" + releaseName + "'");
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
			boolean verifyStatus = deliveryMilestonePageObj.verifyReleaseDetails(releaseID, releaseName, milestoneID);
			IsTrue(verifyStatus, "Release is added successfully", "Failed to add release ");
			if (verifyStatus) {
				logInfo("Writing MileStone ID -> " + milestoneID + " and Release ID -> " + releaseID
						+ " to Properties file.");
				config_DeliveryProp.setProperty("release.milestoneId", milestoneID);
				config_DeliveryProp.setProperty("release.releaseID", releaseID);
				config_DeliveryProp.save();
				ExcelUtils.logTestResult(excelPath, "Create", rowNum, "PASS", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "GREEN");
			} else {
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

	@DataProvider
	public Object[] getStatus() throws Exception {
		String statusList = ExcelUtils.getCellDataX(excelPath, 1, "Reason code",
				"TC_DELIVERY_MILESTONE_UPDATE_RELEASE");
		String status[] = statusList.split(";");
		return (status);
	}

	@Test(priority = 3, dataProvider = "getStatus", description = "Validate Update Release with different status")
	public void updateRelease(String status) throws IOException, Exception {
		tcID = ExcelUtils.getCellDataX(1, 0, excelPath, "Update");
		logInfo("\nStarting of Test Case : " + tcID);
		rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Update", "testCase", tcID);
		logInfo("Test case Row NO is : " + rowNum);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		try {
			logInfo("Milestone ID is : " + milestoneID);
			logInfo("Release ID is : " +config_DeliveryProp.getProperty("release.releaseID"));
			deliveryMilestonePageObj.clickExpandIcon(milestoneID);
			deliveryMilestonePageObj.clickUpdateIcon(config_DeliveryProp.getProperty("release.releaseID").toString());
			boolean isSwitchedToUpdateReleaseFrame = deliveryMilestonePageObj.switchToUpdateFrame();
			IsTrue(isSwitchedToUpdateReleaseFrame, "Switched to update release frame.",
					"Failed to switch to update release frame.");
			deliveryMilestonePageObj.updateStatus(status);
			String statusUpdate = ExcelUtils.getCellDataX(excelPath, 1, "Comment", tcID); 
			statusUpdate = "Updated";
			boolean isStatusUpdateSet = deliveryMilestonePageObj.enterTextToStatusUpdateField(statusUpdate);
			logInfo("***** Successfully entered status update as: " + statusUpdate);
			deliveryMilestonePageObj.clkSaveChangesButton(); 
			controlActions.switchToDefault();
			deliveryMilestonePageObj.verifySuccessMsg();
			deliveryMilestonePageObj.clickExpandIcon(milestoneID);
			threadsleep(2000);
			String releaseStatus = controlActions
					.getText(controlActions.performGetElementByXPath(DeliveryMilestoneConstants.ADDED_RELEASE_STATUS
							.replace("MilestoneIDToBeReplaced", config_DeliveryProp.getProperty("release.releaseID").toString())));
			IsTrue(releaseStatus.contains(status), "Release status is correctly updated to: " + status,
					"Release status is not updated correctly");
			if (true) {
				ExcelUtils.logTestResult(excelPath, "Update", rowNum, "PASS", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "GREEN");
			} 
		} catch (Exception e) {
			ExcelUtils.logTestResult(excelPath, "Update", rowNum, "FAIL", tcID);
			ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "RED");
			logError(e.getMessage());
		} 
		logInfo("End of Test Case : " + tcID);
	}

	@Test(priority = 4, description = "Validate Delete Release with different status")
	public void deleteRelease() throws Exception {
		String tcID = ExcelUtils.getCellDataX(2, 0, excelPath, "Delete");
		rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Delete", "testCase", tcID);
		logInfo("Starting of Test Case : " + tcID);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		try {
			logInfo("Milestone ID is : " + milestoneID);
			logInfo("Release ID is : " + config_DeliveryProp.getProperty("release.releaseID"));
			deliveryMilestonePageObj.clickDeleteIcon(config_DeliveryProp.getProperty("release.releaseID").toString());
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
			threadsleep(3000);
			boolean isButtonClicked = deliveryMilestonePageObj.clkObsoleteButton();
			IsTrue(isButtonClicked, "Clicked on Obsolete button", "Failed to delete milestone");
			op.switchToDefault();
			boolean isMsgDisplayed = deliveryMilestonePageObj.verifySuccessMsg();
			IsTrue(isMsgDisplayed, "Release deleted successfully", "Failed to delete release");
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
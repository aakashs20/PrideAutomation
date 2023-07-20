package com.project.testcases.pTracker.deliveryMilestone;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
import com.project.pageobjects.pTracker.DeliveryMilestonePageTab;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class TCG_DeliveryMilestone_CR_Create_Update_Delete extends TestBase {
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	DeliveryMilestonePage deliveryMilestonePageObj;
	DeliveryMilestonePageTab deliveryMilestonePageTabObj;
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
			+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_ChangeRequest.xls";
	private static int rowNum = 1;
	static int tc_Row_Num;
	String expectedErrorMsg[];
	int errorCnt;
	String tcID = "TC_DELIVERY_MILESTONE_CREATE_CHANGE_REQUEST";
	private String milestoneID;
	String newMilestoneID;
	String statusUpdate;
	String milestoneName;
	public static String changeRequestNumberStr;
	Random rn;

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

	@Test(priority = 1, description = "Validate Error Messages on Change Request Popup")
	public void validateAndAddCR() throws Exception {
		logInfo("Starting of Test Case : " + tcID);
		int rowNum = ExcelUtils.getRowNum(excelPath, "Create", "testCase", tcID);
		logInfo("Test case Row No is : " + rowNum);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row No is : " + tc_Row_Num);
		op.waitForAnElementToBeClickable(deliveryMilestonePageObj.openAddDeliveryMilestone);
		op.clickElement(deliveryMilestonePageObj.openAddDeliveryMilestone);
		boolean isChangeRequestOptionClicked = deliveryMilestonePageObj.clkChangeRequestOption();
		IsTrue(isChangeRequestOptionClicked, "Change request option selected",
				"Failed to click on Change request option.");
		boolean isSwitchedToAddCRFrame = deliveryMilestonePageObj.switchToAddFrame();
		IsTrue(isSwitchedToAddCRFrame, "Switched to add Change request frame.",
				"Failed to switch to add Change request frame.");
		deliveryMilestonePageTabObj = new DeliveryMilestonePageTab(driver);
		op.waitForAnElementToBeClickable(deliveryMilestonePageTabObj.createButtonCR);
		op.clickElement(deliveryMilestonePageTabObj.createButtonCR);
		deliveryMilestonePageObj.validateChangeRequestErrorMsgs();
		op.waitForAnElementToBeClickable(deliveryMilestonePageObj.closeNotification);
		threadsleep(2000);
		op.click(deliveryMilestonePageObj.closeNotification);
	}

	@Test(priority = 2, description = "Create new change request through Add change request popup")
	public void AddChangeRequest() throws Exception {
		try {
			milestoneID = config_DeliveryProp.getProperty("phase.milestoneId").toString();
			rn = new Random();
			int result = rn.nextInt(10000 - 1000) + 1000;

			logInfo("Starting of Test Case : " + tcID);
			int rowNum = ExcelUtils.getRowNum(excelPath, "Create", "testCase", tcID);
			logInfo("Test case Row No is : " + rowNum);
			int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
			logInfo("Test Suite test case Row No is : " + tc_Row_Num);
			
			changeRequestNumberStr = "CR" + result;
			logInfo("ChangeRequestNumber is displayed: "
					+ controlActions.isElementDisplay(deliveryMilestonePageTabObj.changeRequestNumber));
			driver.findElement(By.xpath(DeliveryMilestoneConstants.CHANGE_REQUEST_NUMBER))
					.sendKeys(changeRequestNumberStr);
			threadsleep(1000);
			logInfo("ChangeRequestType is displayed: "
					+ controlActions.isElementDisplay(deliveryMilestonePageTabObj.changeRequestType));
			controlActions.click(deliveryMilestonePageTabObj.changeRequestType);
			driver.switchTo().defaultContent();
			logInfo("ChangeType option 1 is isplayed : "
					+ controlActions.isElementDisplayed(deliveryMilestonePageTabObj.changeTypeOption1));
			controlActions.clickElement(deliveryMilestonePageTabObj.changeTypeOption1);
			logInfo("business change is clicked");
			driver.switchTo().frame(driver.findElement(By.xpath(DeliveryMilestoneConstants.IFRAME_CHANGE_REQUEST)));
			logInfo("impactedArear is displayed: "
					+ controlActions.isElementDisplay(deliveryMilestonePageTabObj.impactedArea));
			controlActions.click(deliveryMilestonePageTabObj.impactedArea);
			driver.switchTo().defaultContent();
			logInfo("Requirement is displayed: " + deliveryMilestonePageTabObj.impactedAreaRequirement);
			controlActions.click(deliveryMilestonePageTabObj.impactedAreaRequirement);
			driver.switchTo().frame(driver.findElement(By.xpath(DeliveryMilestoneConstants.IFRAME_CHANGE_REQUEST)));
			threadsleep(1000);
			logInfo("Requested Date Picker is diplayed: "
					+ controlActions.isElementDisplay(deliveryMilestonePageTabObj.requestDatePicker));
			controlActions.click(deliveryMilestonePageTabObj.requestDatePicker);
			Select selectYear = new Select(deliveryMilestonePageTabObj.yearRequestDate);
			selectYear.selectByVisibleText("2023");
			Select selectMonth = new Select(deliveryMilestonePageTabObj.monthRequestDate);
			selectMonth.selectByVisibleText("Aug");
			logInfo("Request date is clicked "
					+ controlActions.isElementDisplayed(deliveryMilestonePageTabObj.dateRequestDate));
			controlActions.click(deliveryMilestonePageTabObj.dateRequestDate);
			logInfo("Now work on planed date");
			threadsleep(1000);
			logInfo("Requested Date Picker is diplayed: "
					+ controlActions.isElementDisplay(deliveryMilestonePageTabObj.plannedStartDate));
			controlActions.click(deliveryMilestonePageTabObj.plannedStartDate);
			Select selectPlannedStartYear = new Select(deliveryMilestonePageTabObj.yearPlannedStartDate);
			selectPlannedStartYear.selectByVisibleText("2023");
			Select selectPlannedStartMonth = new Select(deliveryMilestonePageTabObj.monthPlannedStartDate);
			selectPlannedStartMonth.selectByVisibleText("Aug");
			logInfo("Planned start date is clicked "
					+ controlActions.isElementDisplayed(deliveryMilestonePageTabObj.datePlannedStartDate));
			controlActions.click(deliveryMilestonePageTabObj.datePlannedStartDate);
			threadsleep(1000);
			logInfo("Requested Date Picker is diplayed: "
					+ controlActions.isElementDisplay(deliveryMilestonePageTabObj.plannedEndDatePicker));
			controlActions.click(deliveryMilestonePageTabObj.plannedEndDatePicker);
			Select selectPlannedEndYear = new Select(deliveryMilestonePageTabObj.yearPlannedEndDate);
			selectPlannedEndYear.selectByVisibleText("2023");
			Select selectPlannedEndMonth = new Select(deliveryMilestonePageTabObj.monthPlannedEndDate);
			selectPlannedEndMonth.selectByVisibleText("Aug");
			logInfo("Request date is clicked "
					+ controlActions.isElementDisplayed(deliveryMilestonePageTabObj.datePlannedEndDate));
			controlActions.click(deliveryMilestonePageTabObj.datePlannedEndDate);
			logInfo("ParentMilestone is displayed: "
					+ controlActions.isElementDisplay(deliveryMilestonePageTabObj.parentMilestone));
			controlActions.click(deliveryMilestonePageTabObj.parentMilestone);
			driver.switchTo().defaultContent();
			logInfo("Parent Milestone option 1 is isplayed : " + controlActions.isElementDisplayed(DeliveryMilestoneConstants.PARENT_MILESTONE_CR_OPTION1
							.replace("MilestoneIDToBeReplaced", milestoneID)));
			controlActions.clickElement(op.perform_getElementByXPath(DeliveryMilestoneConstants.PARENT_MILESTONE_CR_OPTION1
							.replace("MilestoneIDToBeReplaced", milestoneID)));
			logInfo("Parent Milestone is selected");
			driver.switchTo().frame(driver.findElement(By.xpath(DeliveryMilestoneConstants.IFRAME_CHANGE_REQUEST)));
			logInfo("Change Request value is displayed: "  + controlActions.isElementDisplayed(deliveryMilestonePageTabObj.changeRequestValue));
			controlActions.sendKeys(deliveryMilestonePageTabObj.changeRequestValue, "500");
			threadsleep(500);
			controlActions.click(deliveryMilestonePageTabObj.clientApproval);
			driver.switchTo().defaultContent();
			controlActions.clickElement(deliveryMilestonePageTabObj.clientApprovalApproved);
			driver.switchTo().frame(driver.findElement(By.xpath(DeliveryMilestoneConstants.IFRAME_CHANGE_REQUEST)));
			controlActions.clickElement(deliveryMilestonePageTabObj.internalApproval);
			driver.switchTo().defaultContent();
			controlActions.clickElement(deliveryMilestonePageTabObj.internalApprovalApproved);
			driver.switchTo().frame(driver.findElement(By.xpath(DeliveryMilestoneConstants.IFRAME_CHANGE_REQUEST)));
			controlActions.sendKeys(deliveryMilestonePageTabObj.description, "Testing Add CR");
			op.waitForAnElementToBeClickable(deliveryMilestonePageTabObj.createButtonCR);
			op.clickElement(deliveryMilestonePageTabObj.createButtonCR);
			logInfo("Successfully Clicked on Create Change Request Button");
			threadsleep(2000);
			driver.switchTo().defaultContent();
			deliveryMilestonePageObj.clickExpandIcon(milestoneID);
			String actualChangeRequestID = deliveryMilestonePageObj.verifyId(milestoneID, changeRequestNumberStr);
			Equals(actualChangeRequestID, changeRequestNumberStr, "Change Request ID is correctly displayed","Change Request ID is not displayed correctly");
			String status = deliveryMilestonePageObj.verifyStatus(actualChangeRequestID);
			IsTrue(status.contains("Open"), "Change Request status is correctly displayed","Change Request status is not displayed correctly");
			logInfo("Change Request status is correctly displayed ? " + status.contains("Open"));
			if (status.contains("Open")) {
			logInfo("Writing MileStone ID -> " + milestoneID + " and Change Request ID -> " + actualChangeRequestID + " to Properties file.");
			config_DeliveryProp.setProperty("changeRequest.milestoneId", milestoneID);
			config_DeliveryProp.setProperty("changeRequest.crID", actualChangeRequestID);
			config_DeliveryProp.save();
			logInfo("Test Case -> " + tcID + "-> PASS");
			ExcelUtils.logTestResult(excelPath, "Create", rowNum, "PASS", tcID);
			ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "GREEN");
			} else {
				logInfo("Test Case -> " + tcID + "-> Fail");
				ExcelUtils.logTestResult(excelPath, "Create", rowNum, "FAIL", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "FAIL", "RED");
			}
		} catch (NoSuchElementException e) {
			logInfo("Test Case -> " + tcID + "-> Fail");
			ExcelUtils.logTestResult(excelPath, "Create", rowNum, "FAIL", tcID);
			ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "FAIL", "RED");
			logError("no such element found" + e.getMessage());
		}
		logInfo("End of Test Case : " + tcID);
	}

	@DataProvider
	public Object[] getStatus() throws Exception {
		String statusList = ExcelUtils.getCellDataX(excelPath, 1, "Reason code","TC_DELIVERY_MILESTONE_UPDATE_CHANGE_REQUEST");
		String status[] = statusList.split(";");
		return (status);
	}

	@Test(priority = 3, dataProvider = "getStatus", description = "To update change request with different status")
	public void updateChangeRequest(String status) throws Exception {
		String tcID = ExcelUtils.getCellDataX(1, 0, excelPath, "Update");
		rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Update", "testCase", tcID);
		logInfo("Starting of Test Case : " + tcID);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		try {
			logInfo("Milestone ID is : " + milestoneID);
			logInfo("Change Request ID is : " + config_DeliveryProp.getProperty("changeRequest.crID"));
			threadsleep(2000);
			deliveryMilestonePageObj.clickExpandIcon(milestoneID);
			deliveryMilestonePageObj.clickUpdateIcon(config_DeliveryProp.getProperty("changeRequest.crID").toString());
			boolean isSwitchedToUpdateCRFrame = deliveryMilestonePageObj.switchToUpdateFrame();
			IsTrue(isSwitchedToUpdateCRFrame, "Switched to update change request frame.","Failed to switch to update change request frame.");
		//	deliveryMilestonePageObj.updateStatus(status);
		//	logInfo("Change Request status -> " + deliveryMilestonePageObj.updateStatus(status));
			op.waitForAnElementToBeClickable(deliveryMilestonePageObj.saveChangesButtonCR);
			op.clickElement(deliveryMilestonePageObj.saveChangesButtonCR);
			controlActions.switchToDefault();
			deliveryMilestonePageObj.verifySuccessMsg();
			threadsleep(2000);
			deliveryMilestonePageObj.clickExpandIcon(milestoneID);
			String changeRequestStatus = controlActions.getText(controlActions.performGetElementByXPath(DeliveryMilestoneConstants.ADDED_RELEASE_STATUS.replace("MilestoneIDToBeReplaced",
							config_DeliveryProp.getProperty("changeRequest.crID").toString())));
			threadsleep(2000);
			logInfo("Edit Change Request status matched ? " + changeRequestStatus.contains(status));
			if (changeRequestStatus.contains(status)) {
				logInfo("Test Case -> " + tcID + "-> PASS");
				ExcelUtils.logTestResult(excelPath, "Update", rowNum, "PASS", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "GREEN");
			} else {
				logInfo("Test Case -> " + tcID + "-> Fail");
				ExcelUtils.logTestResult(excelPath, "Update", rowNum, "FAIL", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "RED");
			}
		} catch (Exception e) {
			logInfo("Test Case -> " + tcID + "-> Fail");
			ExcelUtils.logTestResult(excelPath, "Update", rowNum, "FAIL", tcID);
			ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "RED");
			logError(e.getMessage());
		}
		logInfo("\nEnd of Test Case : " + tcID);
	}

	@Test(priority = 4, description = "Validate Delete Change Request with different Reason Codes")
	public void deleteChangeRequest() throws Exception {
		String tcID = ExcelUtils.getCellDataX(2, 0, excelPath, "Delete");
		rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Delete", "testCase", tcID);
		logInfo("Starting of Test Case : " + tcID);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		try {
			logInfo("Milestone ID is : " + milestoneID);
			logInfo("Change Request ID is : " + config_DeliveryProp.getProperty("changeRequest.crID"));
			deliveryMilestonePageObj.clickDeleteIcon(config_DeliveryProp.getProperty("changeRequest.crID").toString());
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
			IsTrue(isButtonClicked, "Clicked on Obsolete button", "Failed to delete Change Request");
			op.switchToDefault();
			boolean isMsgDisplayed = deliveryMilestonePageObj.verifySuccessMsg();
			logInfo("Change Request deleted messsage is shown ? " + isMsgDisplayed);
			IsTrue(isMsgDisplayed, "Phase deleted successfully", "Failed to delete phase");
			if (isMsgDisplayed) {
				logInfo("Test Case -> " + tcID + "-> PASS");
				ExcelUtils.logTestResult(excelPath, "Delete", rowNum, "PASS", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "GREEN");
			} else {
				logInfo("Test Case -> " + tcID + "-> Fail");
				ExcelUtils.logTestResult(excelPath, "Delete", rowNum, "FAIL", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "FAIL", "RED");
			}
		} catch (Exception e) {
			logInfo("Test Case -> " + tcID + "-> Fail");
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
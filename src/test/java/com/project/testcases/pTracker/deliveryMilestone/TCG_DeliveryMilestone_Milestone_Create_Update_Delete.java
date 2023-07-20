package com.project.testcases.pTracker.deliveryMilestone;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
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
import com.project.pageobjects.pTracker.DeliveryMilestonePage;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class TCG_DeliveryMilestone_Milestone_Create_Update_Delete extends TestBase {
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
			+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Milestone.xls";
	private String searchProjectNumber;
	String tcID = "TC_DELIVERY_MILESTONE_CREATE_MILESTONE";
	String tcID1 = "TC_DELIVERY_MILESTONE_UPDATE_MILESTONE";
	PropertiesConfiguration config;
	private String milestoneID;
	private static int rowNum = 2;
	String newMilestoneID;
	String statusUpdate;
	String milestoneName;

	@BeforeClass
	public void groupInit() throws Exception {
		config = new PropertiesConfiguration(Constants.configFile);
		prop = new Properties();
		prop.load(new FileInputStream(Constants.configFile));
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
		op.waitForAnElementToBeClickable(deliveryMilestonePageObj.milestoneOption);
		op.clickElement(deliveryMilestonePageObj.milestoneOption);
		op.switchToFrameByLocators(deliveryMilestonePageObj.addMilestoneFrame);
		Boolean addButtonIsClickable = op.waitForAnElementToBeClickable(deliveryMilestonePageObj.addMilestonePageButton);
		IsTrue(addButtonIsClickable, "Add button on the Add MileStone Popup is Clickable","Add button on the Add MileStone Popup is NOT Clickable");
		op.clickElement(deliveryMilestonePageObj.addMilestonePageButton);
		deliveryMilestonePageObj.validateAndAddMilestone(driver, milestoneID, milestoneName);
	}

	@Test(priority = 2)
	public void createAddDeliveryMilestone() throws Exception {
		int randomNum = op.generateRandomNum();
		milestoneID = "MILESTONEID" + randomNum;
		milestoneName = "MILESTONENAME" + randomNum;
		logInfo("Starting of Test Case : " + tcID);
		int rowNum = ExcelUtils.getRowNum(excelPath, "Create", "testCase", tcID);
		logInfo("Test case Row No is : " + rowNum);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row No is : " + tc_Row_Num);
		try {
			op.sendKeys(deliveryMilestonePageObj.milestoneIdField, milestoneID);
			op.sendKeys(deliveryMilestonePageObj.milestoneNameField, milestoneName);
			boolean isStartDateCalendarClicked = deliveryMilestonePageObj.clkStartDateCalendar();
			IsTrue(isStartDateCalendarClicked, "Clicked on start date calender","Failed to click on Start Date Calendar.");
			boolean isStartDateClicked = deliveryMilestonePageObj.clkStartDate();
			IsTrue(isStartDateClicked, "Clicked on start date", "Failed to click on Start Date.");
			boolean isEndDateCalendarClicked = deliveryMilestonePageObj.clkEndDateCalendar();
			IsTrue(isEndDateCalendarClicked, "Clicked on end date calender", "Failed to click on End Date Calendar.");
			boolean isEndDateClicked = deliveryMilestonePageObj.clkEndDate();
			IsTrue(isEndDateClicked, "Clicked on end date", "Failed to click on End Date.");
			boolean isAddButtonOnAddMilestonePageClicked = deliveryMilestonePageObj.clkAddButtonOnAddMilestoneFrame();
			IsTrue(isAddButtonOnAddMilestonePageClicked, "Clicked on Add button on Add Milestone page.","Failed to click on Add button on Add Milestone page.");
			threadsleep(3000);
			List<WebElement> milestoneIdsList = deliveryMilestonePageObj.milestoneIdsList;
			List<WebElement> milestoneStatusList = deliveryMilestonePageObj.milestoneStatusList;
			for (int i = 0; i < milestoneIdsList.size(); i++) {
				System.out.println(milestoneIdsList.get(i).getText());
				boolean isMilestoneIdExists = milestoneIdsList.get(i).getText().trim().contains(milestoneID);
				IsTrue(isMilestoneIdExists, "Milestone has been successfully added to the list","Milestone has NOT been successfully added to the list");
				logInfo("Open Elemenet: " + milestoneStatusList.get(milestoneStatusList.size() - 1).getText());
				Equals(milestoneStatusList.get(milestoneStatusList.size()-1).getText().trim(), "Open","Newly Created milestone have OPEN status", "Newly Created milestone doesn't have OPEN status");
			}

			if (milestoneStatusList.get(milestoneStatusList.size() - 1).getText().trim().contains("Open")) {
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
		String statusList = ExcelUtils.getCellDataX(excelPath, 1, "State", "TC_DELIVERY_MILESTONE_UPDATE_MILESTONE");
		String status[] = statusList.split(";");
		return (status);
	}

	@Test(priority = 2, dataProvider = "getStatus", groups = { "sanity","UI" }, description = "To update milestone with different status")
	public void updateMilestone(String status) throws IOException, Exception {
		int randomNum = op.generateRandomNum();
		String tcID = ExcelUtils.getCellDataX(1, 0, excelPath, "Update");
		rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Update", "testCase", tcID);
		logInfo("Starting of Test Case : " + tcID);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		try {
			boolean updateIconClicked = deliveryMilestonePageObj.clickUpdateIcon(milestoneID);
			IsTrue(updateIconClicked, "Update icon clicked.", "Failed to click on update icon.");
			boolean isSwitchedToUpdateReleaseFrame = deliveryMilestonePageObj.switchToUpdateFrame();
			IsTrue(isSwitchedToUpdateReleaseFrame, "Switched to update phase frame","Failed to switch to update phase frame");
			WebElement element = driver.findElement(By.xpath("//input[@id='P95_STATUS']"));
			String status1 = element.getAttribute("value");
			if (status1.equalsIgnoreCase("Open")) {
				HashMap<String, String> map = ExcelUtils.getTestDataXls(excelPath, "Update", 0, rowNum-1);
				newMilestoneID = map.get("Milestone ID") + randomNum;
				String newMilestoneName = map.get("Milestone name") + randomNum;
				logInfo("Milestone ID: " + map.get("Milestone ID"));
				logInfo("Milestone Name: " + map.get("Milestone name"));
				statusUpdate = map.get("Status Update");
				logInfo("New milestone ID: " + newMilestoneID);
				logInfo("New Milestone Name: " + newMilestoneName);
				boolean isMilestoneIDSet = deliveryMilestonePageObj.enterTextToMilestoneID(newMilestoneID);
				IsTrue(isMilestoneIDSet, "Added text to milestone ID: " + newMilestoneID,"Failed to add text to Milestone ID as '" + newMilestoneID + "'");
				boolean isMilestoneNameSet = deliveryMilestonePageObj.enterTextTomilestoneNameField(newMilestoneName);
				IsTrue(isMilestoneNameSet, "Added text to milestone name: " + newMilestoneName,"Failed to add text to Milestone Name as '" + newMilestoneName + "'");
				milestoneID = newMilestoneID;
			}

			deliveryMilestonePageObj.updateStatus(status);
			boolean isStatusUpdateSet = deliveryMilestonePageObj.enterTextToStatusUpdateField(statusUpdate);
			IsTrue(isStatusUpdateSet, "Added text to status update: " + statusUpdate,"Added text to status update: '" + statusUpdate + "'");
			logInfo("Successfully entered status update as: " + statusUpdate);

			if (status.equalsIgnoreCase("Obsolete")) {
				driver.findElement(By.xpath("//*[@id='P95_REASON_CODE']")).click();
				op.switchToDefault();
				driver.findElement(By.xpath("//*[@id='PopupLov_95_P95_REASON_CODE_dlg']/div[3]/div/div[3]/ul/li[1]")).click();
				isSwitchedToUpdateReleaseFrame = deliveryMilestonePageObj.switchToUpdateFrame();
				IsTrue(isSwitchedToUpdateReleaseFrame, "Switched to update phase frame.","Failed to switch to update phase frame.");
			}
			deliveryMilestonePageObj.clkSaveChangesButton();
			op.switchToDefault();
			deliveryMilestonePageObj.verifySuccessMsg();
			boolean isMilestoneUpdated = deliveryMilestonePageObj.searchMilestone(driver, milestoneID, status);
			logInfo("Is Milestone updated successfully? " + isMilestoneUpdated);

			milestoneID = newMilestoneID;
			if (isMilestoneUpdated) {
				ExcelUtils.logTestResult(excelPath, "Update", rowNum, "PASS", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "GREEN");
			} else {
				ExcelUtils.logTestResult(excelPath, "Update", rowNum, "FAIL", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "FAIL", "RED");
			}
		} catch (Exception e) {
			ExcelUtils.logTestResult(excelPath, "Update", rowNum, "FAIL", tcID);
			ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "FAIL", "RED");
			logError(e.getMessage());
		}
		logInfo("End of Test Case : " + tcID);
	}

	@Test(priority = 4)
	public void deleteMilestone() throws Exception {
		String tcID = ExcelUtils.getCellDataX(1, 0, excelPath, "Delete");
		rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Delete", "testCase", tcID);
		logInfo("Starting of Test Case : " + tcID);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);

		try {
			deliveryMilestonePageObj.clickMilestoneDeleteButton(driver, milestoneID);
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
			IsTrue(isSwitchedToDeleteMilestoneFrame1, "Switched to delete milestone frame",	"Failed to switch to delete frame");
			boolean isButtonClicked = deliveryMilestonePageObj.clkObsoleteButton();
			IsTrue(isButtonClicked, "Clicked on Obsolete button", "Failed to delete milestone");
			boolean isMsgDisplayed1 = deliveryMilestonePageObj.verifySuccessMsg();
			logInfo("Milestone deleted successfully Message was visible? " + isMsgDisplayed1);
			IsTrue(isMsgDisplayed1, "Milestone deleted successfully", "Failed to delete milestone");
			boolean isMilestoneDeleted = deliveryMilestonePageObj.searchMilestone(driver, milestoneID, "Obsolete");
			logInfo("Is Milestone deleted successfully? " + isMilestoneDeleted);
			if (isMilestoneDeleted) {
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
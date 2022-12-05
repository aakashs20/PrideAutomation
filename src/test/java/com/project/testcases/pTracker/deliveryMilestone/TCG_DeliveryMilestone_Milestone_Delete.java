package com.project.testcases.pTracker.deliveryMilestone;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
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

public class TCG_DeliveryMilestone_Milestone_Delete extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	DeliveryMilestonePage deliveryMilestonePageObj;
	//ExcelUtils excel;
	Properties prop;
	Constants con;
	Operations op;
	CommonPages cp;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";
	private String searchProjectNumber;
	private String milestoneID;
	private String milestoneName;
	private String datapoolPath = System.getProperty("user.dir")+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	private String excelPath = System.getProperty("user.dir")+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Milestone.xls";
	String tcID = "TC_DELIVERY_MILESTONE_DELETE_MILESTONE";
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
		op = new Operations(driver);
		op.getUrl(prop.getProperty("appl_url_dev"));
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		loginPage.waitForPageLoaded(driver);
		loginPage.TC_Login(driver, uName, uPassword);
		loginPage.TC_ChangeUser(driver, eName);
		deliveryMilestonePageObj = new DeliveryMilestonePage(driver);
		deliveryMilestonePageObj.verifyActiveProject(driver, searchProjectNumber);
		deliveryMilestonePageObj.verifyDeliveryMilestoneButton(driver);
	}

	@DataProvider
	public Object[] getReasonCodes() throws Exception {
		String reasonCodeList = ExcelUtils.getCellDataX(excelPath, 2, "ReasonCode",tcID);
		String reasonCodes[] = reasonCodeList.split(";");
		return (reasonCodes);
	}

@Test (priority = 1 ,dataProvider = "getReasonCodes", groups = {"sanity", "UI"}, description = "Delete Delivery Milestone with different Reason Code")
	public void deleteDeliveryMilestone(String reasonCode) throws Exception {
		logInfo("------------------------------------- " + searchProjectNumber);
		String tcID = ExcelUtils.getCellDataX(rowNum, 0, excelPath, "Delete");
		logInfo("Starting of Test Case : " + tcID);
		rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Delete", "testCase", tcID);
		logInfo("Test case Row NO is : " + rowNum);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		try {
			op.waitForAnElementToBeClickable(deliveryMilestonePageObj.openAddDeliveryMilestone);
			if(op.isElementDisplayedOnPage(deliveryMilestonePageObj.openAddDeliveryMilestone))
			{
				logInfo("Delivery Milestone Page Opened Successfully");
			}

			milestoneID = deliveryMilestonePageObj.createMilestone();
			boolean isMsgDisplayed = deliveryMilestonePageObj.verifySuccessMsg();
			logInfo("Milestone Added Successfully Message was Visible? " + isMsgDisplayed);
			boolean isMilestoneAdded = deliveryMilestonePageObj.searchMilestone(driver, milestoneID,"Open");
			logInfo("Is Milestone added successfully: " + isMilestoneAdded);
//			if(isMilestoneAdded)
//			{
//				ExcelUtils.logTestResult(excelPath, "Delete", rowNum, "PASS", tcID);
//			}
//			else
//			{
//				ExcelUtils.logTestResult(excelPath, "Delete", rowNum, "FAIL", tcID);
//			}
//			isMilestoneAdded = deliveryMilestonePageObj.verifyMilestoneDetails(driver, milestoneID, milestoneName, "0%","Open");
//			IsTrue(isMilestoneAdded, "Milestone is added successfully", "Failed to add milestone ");
			deliveryMilestonePageObj.clickMilestoneDeleteButton(driver, milestoneID);
			boolean isSwitchedToDeleteMilestoneFrame = deliveryMilestonePageObj.switchToDeleteMilestoneFrame();
			IsTrue(isSwitchedToDeleteMilestoneFrame, "Switched to delete frame successfully","Failed to switch to delete frame");
			String statusUpdate = ExcelUtils.getCellDataX(excelPath, 2, "Status Update", tcID);
			logInfo("Status update: " + statusUpdate);
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
			boolean isMsgDisplayed1 = deliveryMilestonePageObj.verifySuccessMsg();
			logInfo("Milestone deleted successfully Message was visible? " + isMsgDisplayed1);
			IsTrue(isMsgDisplayed1, "Milestone deleted successfully", "Failed to delete milestone");
			boolean isMilestoneDeleted = deliveryMilestonePageObj.searchMilestone(driver, milestoneID,"Obsolete");
			logInfo("Is Milestone deleted successfully? " + isMilestoneDeleted);
			//boolean status = deliveryMilestonePageObj.verifyMilestoneDetails(driver, milestoneID, milestoneName, "0%", "Obsolete");
			if(isMilestoneDeleted)
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

	@AfterClass
	public void closeBrowser() throws Exception {
			driver.close();
	}
}

package com.project.testcases.pTracker.deliveryMilestone;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class TCG_DeliveryMilestone_Milestone_Update extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	DeliveryMilestonePage deliveryMilestonePageObj;
	Properties prop;
	Constants con;
	Operations op;
	CommonPages cp;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";
	//private String searchProjectNumber = "MKSDVFIK";
	private String searchProjectNumber;
	private String milestoneID;
	private String datapoolPath = System.getProperty("user.dir")+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	private String excelPath = System.getProperty("user.dir")+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Milestone.xls";
	String statusUpdate ;
	String newMilestoneID ;
	private static int rowNum = 2;
	String tcID = "TC_DELIVERY_MILESTONE_UPDATE_MILESTONE";

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
		milestoneID = deliveryMilestonePageObj.createMilestone();
		IsTrue(milestoneID != null, "Milestone is successfully created", "Failed to add milestone");
	}

	@DataProvider
	public Object[] getStatus() throws Exception {
		//rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Update", "testCase",tcID);
		String statusList = ExcelUtils.getCellDataX(excelPath, 1, "State",tcID);
		String status[] = statusList.split(";");
		return (status);
	}
	
	@Test(priority = 1, dataProvider = "getStatus",groups = {"sanity", "UI"}, description = "To update milestone with different status")
	public void updateMilestone(String status) throws IOException, Exception {
		logInfo("------------------------------------- " + searchProjectNumber);
		int randomNum = op.generateRandomNum();
		String tcID = ExcelUtils.getCellDataX(rowNum, 0, excelPath, "Update");
		logInfo("Starting of Test Case : " + tcID);
		rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Update", "testCase", tcID);
		logInfo("Test case Row NO is : " + rowNum);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		try {
			boolean updateIconClicked = deliveryMilestonePageObj.clickUpdateIcon(milestoneID);
			IsTrue(updateIconClicked, "Update icon clicked.", "Failed to click on update icon.");
			boolean isSwitchedToUpdateReleaseFrame = deliveryMilestonePageObj.switchToUpdateFrame();
			IsTrue(isSwitchedToUpdateReleaseFrame, "Switched to update phase frame.","Failed to switch to update phase frame.");

			if(status.equalsIgnoreCase("Open"))
			{
				HashMap<String, String> map = ExcelUtils.getTestDataXls(excelPath, "Update", 0, rowNum-1);
				//logInfo("------>  : " + map);
				newMilestoneID = map.get("Milestone ID") + randomNum;
				String newMilestoneName = map.get("Milestone name") + randomNum;
				String percentCompletion = map.get("% Completion");
				logInfo("Milestone ID: " + map.get("Milestone ID"));
				logInfo("Milestone Name: " + map.get("Milestone name"));
				statusUpdate = map.get("Status Update");
				logInfo("New milestone ID: " + newMilestoneID);
				logInfo("New Milestone Name: " + newMilestoneName);
				boolean isMilestoneIDSet = deliveryMilestonePageObj.enterTextToMilestoneID(newMilestoneID);
				IsTrue(isMilestoneIDSet, "Added text to milestone ID: " + newMilestoneID,"Failed to add text to Milestone ID as '" + newMilestoneID + "'");
				boolean isMilestoneNameSet = deliveryMilestonePageObj.enterTextTomilestoneNameField(newMilestoneName);
				IsTrue(isMilestoneNameSet, "Added text to milestone name: " + newMilestoneName,"Failed to add text to Milestone Name as '" + newMilestoneName + "'");
//				boolean isPercentCompletionSet = deliveryMilestonePageObj.enterTextToPercCompletionField(percentCompletion);
//				IsTrue(isPercentCompletionSet, "Added text to percent completion: " + percentCompletion,"Failed to update percent completion as '" + isPercentCompletionSet + "'");
//				logInfo("Updated percentCompletion: " + percentCompletion);
				milestoneID = newMilestoneID;
			}
			//newMilestoneID = milestoneID;

			deliveryMilestonePageObj.updateStatus(status);
			boolean isStatusUpdateSet = deliveryMilestonePageObj.enterTextToStatusUpdateField(statusUpdate);
			IsTrue(isStatusUpdateSet, "Added text to status update: " + statusUpdate,"Added text to status update:  '" + statusUpdate + "'");
			logInfo("Successfully entered status update as: " + statusUpdate);
					
			if(status.equalsIgnoreCase("Obsolete"))
			{
				threadsleep(2000);
				driver.findElement(By.xpath("//*[@id='P95_REASON_CODE']")).click();
				op.switchToDefault();
				threadsleep(2000);
				driver.findElement(By.xpath("//*[@id='PopupLov_95_P95_REASON_CODE_dlg']/div[3]/div/div[3]/ul/li[1]")).click();
				isSwitchedToUpdateReleaseFrame = deliveryMilestonePageObj.switchToUpdateFrame();
				IsTrue(isSwitchedToUpdateReleaseFrame, "Switched to update phase frame.","Failed to switch to update phase frame.");
				threadsleep(2000);
			}
			deliveryMilestonePageObj.clkSaveChangesButton();
			op.switchToDefault();
			deliveryMilestonePageObj.verifySuccessMsg();
			boolean isMilestoneUpdated= deliveryMilestonePageObj.searchMilestone(driver, milestoneID,status);
			logInfo("Is Milestone updated successfully? " + isMilestoneUpdated);
			
//			boolean foundMilestoneId = deliveryMilestonePageObj.checkListMilestone(newMilestoneID, driver);
//			IsTrue(foundMilestoneId != false, "Milestone ID is correctly updated","Milestone ID is not updated correctly");

			milestoneID = newMilestoneID;
//			boolean tcStatus = deliveryMilestonePageObj.verifyMilestoneDetails(driver, newMilestoneID, newMilestoneName,percentCompletion, status);
			if(isMilestoneUpdated)
			{
				ExcelUtils.logTestResult(excelPath, "Update", rowNum, "PASS", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "GREEN");
			}
			else
			{
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

	@AfterClass
	public void closeBrowser() throws Exception {
			driver.close();
	}
}

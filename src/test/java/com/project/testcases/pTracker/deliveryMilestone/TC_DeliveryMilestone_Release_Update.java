package com.project.testcases.pTracker.deliveryMilestone;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.CommonPages;
import com.project.pageobjects.pTracker.DeliveryMilestoneConstants;
import com.project.pageobjects.pTracker.DeliveryMilestonePage;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class TC_DeliveryMilestone_Release_Update extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	DeliveryMilestonePage deliveryMilestonePageObj;
	ExcelUtils excel;

	Operations op;
	CommonPages cp;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 50;
	String eName = "Mahajan, Milind";

	private String searchProjectNumber = "ACCE8917";
	private String milestoneID;
	private String milestoneName;
	private String datapoolPath = System.getProperty("user.dir")+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	private String excelPath = System.getProperty("user.dir")+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Release.xls";
	private String releaseID;
	private String releaseName;
	private static int rowNum = 2;
	String tcID ;

	@BeforeClass
	public void groupInit() throws Exception {
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
		releaseID = deliveryMilestonePageObj.createRelease(milestoneID);
		IsTrue(releaseID != null, "Release is successfully created", "Failed to add release");
	}

	@DataProvider
	public Object[] getStatus() throws Exception {
		String statusList = ExcelUtils.getCellDataX(excelPath, 1, "Reason code", "TC_DELIVERY_MILESTONE_UPDATE_RELEASE");
		String status[] = statusList.split(";");
		return (status);
	}

	@Test(priority = 1, dataProvider = "getStatus", description = "Validate Update Release with different status")
	public void updateRelease(String status) throws IOException, Exception{
		tcID = ExcelUtils.getCellDataX(rowNum, 0, excelPath, "Update");
		logInfo("\nStarting of Test Case : " + tcID);
		rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Update", "testCase", tcID);
		logInfo("Test case Row NO is : " + rowNum);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		try {
			logInfo("Milestone ID is : " + milestoneID);
			logInfo("Release ID is : " + releaseID);
			deliveryMilestonePageObj.clickExpandIcon(milestoneID);
			deliveryMilestonePageObj.clickUpdateIcon(releaseID);
			boolean isSwitchedToUpdateReleaseFrame = deliveryMilestonePageObj.switchToUpdateFrame();
			IsTrue(isSwitchedToUpdateReleaseFrame, "Switched to update release frame.",	"Failed to switch to update release frame.");
			deliveryMilestonePageObj.updateStatus(status);
			String statusUpdate = ExcelUtils.getCellDataX(excelPath, 1, "Comment",tcID);
			//String statusUpdate = "Updated" ;
			boolean isStatusUpdateSet = deliveryMilestonePageObj.enterTextToStatusUpdateField(statusUpdate);
			logInfo("***** Successfully entered status update as: " + statusUpdate);
			deliveryMilestonePageObj.clkSaveChangesButton();
			//controlActions.switchToDefault();
			deliveryMilestonePageObj.verifySuccessMsg();
			deliveryMilestonePageObj.clickExpandIcon(milestoneID);
			String releaseStatus = controlActions.getText(controlActions.performGetElementByXPath(DeliveryMilestoneConstants.ADDED_RELEASE_STATUS.replace("MilestoneIDToBeReplaced", milestoneID)));
			IsTrue(releaseStatus.contains(status), "Release status is correctly updated to: " + status,	"Release status is not updated correctly");
			if(true)
			{
				ExcelUtils.logTestResult(excelPath, "Update", rowNum, "PASS", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "GREEN");
			}
			else
			{
				ExcelUtils.logTestResult(excelPath, "Update", rowNum, "FAIL", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "RED");
			}
		} catch (Exception e) {
			ExcelUtils.logTestResult(excelPath, "Update", rowNum, "FAIL", tcID);
			ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "RED");
			logError(e.getMessage());
		}
		  //rowNum++;
		  logInfo("End of Test Case : " + tcID);
	}
		
}


package com.project.testcases.pTracker.deliveryMilestone;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
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

public class TC_DeliveryMilestone_Sprint_Update extends TestBase {
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

	private String searchProjectNumber = "ATHOO005";
	private String excelPath = System.getProperty("user.dir")+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Sprint.xls";
	private String datapoolPath = System.getProperty("user.dir")+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	private static int rowNum = 1;
	static int tcRowNum;
	String expectedErrorMsg[];
	int errorCnt;

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
	}

	@Test(priority = 1)
	public void createUpdateSprint() throws IOException, Exception {
		//String tcID = "TC_DeliveryMilestone_Update_Sprint";
		String tcID = ExcelUtils.getCellDataX(rowNum, 0, excelPath, "Create");
		logInfo("Starting of Test Case : " + tcID);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		excel = new ExcelUtils();
		int rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Create", "testCase",tcID );
    	String milestoneId = deliveryMilestonePageObj.createMilestone();
//		String milestoneId ="MILEID366";
//		String sprintID = "SPRINTID75";
		try {
			int randomNum = op.generateRandomNum();
			String sprintID = "SPRINTID" + randomNum;
			String sprintName = "SPRINTNAME" + randomNum;
			boolean isAddDeliveryMilestoneButtonClicked = deliveryMilestonePageObj.clkAddDeliveryMilestoneButton();
			IsTrue(isAddDeliveryMilestoneButtonClicked, "Successfully clicked on Add Delivery Milestone buttons","Failed to click on Add Delivery Milestone button.");
			boolean isSprintOptionClicked = deliveryMilestonePageObj.clkSprintOption();
			IsTrue(isSprintOptionClicked, "Sprint option selected", "Failed to click on sprint option.");
			boolean isSwitchedToAddSprintFrame = deliveryMilestonePageObj.switchToAddFrame();
			IsTrue(isSwitchedToAddSprintFrame, "Switched to add sprint frame.",	"Failed to switch to add sprint frame.");
			boolean isPhaseIDSet = deliveryMilestonePageObj.enterTextToSprintID(sprintID);
			IsTrue(isPhaseIDSet, "Entered sprint ID: " + sprintID,"Failed to add text to sprint ID as '" + sprintID + "'");
			boolean isSprintNameSet = deliveryMilestonePageObj.enterTextToSprintName(sprintName);
			IsTrue(isSprintNameSet, "Entered sprint Name: " + sprintName,"Failed to add text to sprint name as '" + sprintName + "'");
			boolean isStartDateCalendarClicked = deliveryMilestonePageObj.clkStartDateCalendar();
			IsTrue(isStartDateCalendarClicked, "Clicked on start date calender","Failed to click on Start Date Calendar.");
			boolean isStartDateClicked = deliveryMilestonePageObj.clkStartDate();
			IsTrue(isStartDateClicked, "Clicked on start date", "Failed to click on Start Date.");
			boolean isEndDateCalendarClicked = deliveryMilestonePageObj.clkEndDateCalendar();
			IsTrue(isEndDateCalendarClicked, "Clicked on end date calender", "Failed to click on End Date Calendar.");
			boolean isEndDateClicked = deliveryMilestonePageObj.clkEndDate();
			IsTrue(isEndDateClicked, "Clicked on end date", "Failed to click on End Date.");
			HashMap<String, String> map = ExcelUtils.getTestDataXls(excelPath, "Create", 0, rowNum-1);
			logInfo("Create Sprint Data are - > " + map);
			String team = map.get("Team");
			String sprintCycle = map.get("Sprint Cycle");
			String uom = map.get("Unit of Measure");
			String plannedCapacity = map.get("Planned Capacity");
			String stories = map.get("Stories");
			boolean isSprintCycleSelected = deliveryMilestonePageObj.selectSprintCycle(sprintCycle);
			IsTrue(isSprintCycleSelected, "Successfully selected sprint cycle: " + sprintCycle,"Failed to select sprint cycle: " + sprintCycle);
			boolean isUOMSelected = deliveryMilestonePageObj.selectUOM(uom);
			IsTrue(isUOMSelected, "Successfully selected Unit of Measure: " + uom,"Failed to select Unit of Measure: " + uom);
			boolean isStoriesSelected = deliveryMilestonePageObj.enterTextToSprintStories(stories);
			IsTrue(isStoriesSelected, "Successfully entered sprint stories: " + stories,"Failed to enter sprint stories: " + stories);
			boolean isPlannedCapacitySelected = deliveryMilestonePageObj.enterTextToSprintPlannedCapacity(plannedCapacity);
			IsTrue(isPlannedCapacitySelected, "Successfully entered sprint planned capacity: " + plannedCapacity,"Failed to enter sprint planned capacity: " + plannedCapacity);
			boolean isTeamSelected = deliveryMilestonePageObj.selectTeam(team);
			IsTrue(isTeamSelected, "Successfully entered team: " + team, "Failed to enter team: " + team);
			boolean isParentSelected = deliveryMilestonePageObj.enterParentMilestone(driver, milestoneId);
			IsTrue(isParentSelected, "Successfully selected parent milestone", "Failed to select parent milestone.");
			boolean isAddButtonOnAddMilestonePageClicked = deliveryMilestonePageObj.clkAddButton();
			IsTrue(isAddButtonOnAddMilestonePageClicked, "Successfully clicked on Add button","Failed to click on Add button on Add Milestone page.");
			boolean foundMilestoneId = deliveryMilestonePageObj.checkListMilestone(milestoneId, driver);
			IsTrue(foundMilestoneId != false, "Milestone ID is correctly displayed","Milestone ID is not displayed correctly");
			deliveryMilestonePageObj.clickExpandIcon(milestoneId);
			String actualSprintID = deliveryMilestonePageObj.verifyId(milestoneId, sprintID);
			Equals(actualSprintID, sprintID, "Sprint ID is correctly displayed","Sprint ID is not displayed correctly");
			String actualSprintName = deliveryMilestonePageObj.verifyName(sprintID);
			Equals(actualSprintName, sprintName, "Sprint name is correctly displayed","Sprint name is not displayed correctly");
			String milestoneType = deliveryMilestonePageObj.verifyMilestoneType(sprintID);
			Equals(milestoneType, "Sprint", "Milestone type is correctly displayed","Milestone type is not displayed correctly");
			String status = deliveryMilestonePageObj.verifyStatus(sprintID);
			IsTrue(status.contains("Open"), "Sprint status is correctly displayed",	"Sprint status is not displayed correctly");
			if(status.contains("Open"))
			{
				ExcelUtils.logTestResult(excelPath, "Create", rowNum, "PASS", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "GREEN");
			}
			else
			{
				ExcelUtils.logTestResult(excelPath, "Create", rowNum, "FAIL", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "FAIL", "RED");
			}
			logInfo("End of Test Case : " + tcID);
			
			// ***************Update sprint*****************************//
			rowNum = 1;
			tcID = ExcelUtils.getCellDataX(rowNum, 0, excelPath, "Update");
			logInfo("Starting of Test Case : " + tcID);
			tc_Row_Num = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
			logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
			excel = new ExcelUtils();
			rowNum = ExcelUtils.getRowNumHSSF(excelPath, "Update", "testCase",tcID );
			
			boolean updateIconClicked = deliveryMilestonePageObj.clickUpdateIcon(sprintID);
			IsTrue(updateIconClicked, "Update icon clicked.", "Failed to click on update icon.");
			boolean isSwitchedToUpdateReleaseFrame = deliveryMilestonePageObj.switchToUpdateFrame();
			IsTrue(isSwitchedToUpdateReleaseFrame, "Switched to update phase frame.","Failed to switch to update phase frame.");
			rowNum = ExcelUtils.getRowNumXSSF(excelPath, "Update", "testCase", tcID);
			HashMap<String, String> updateTestDataMap = ExcelUtils.getTestDataXls(excelPath, "Update", 0, rowNum - 1);
			logInfo("Update Sprint Data are - > " + updateTestDataMap);
			String scopeChange = updateTestDataMap.get("Scope Change");
			boolean isScopeChangeSet = deliveryMilestonePageObj.enterTextToScopeChange(scopeChange);
			IsTrue(isScopeChangeSet, "Successfully entered sprint scope change: " + scopeChange,"Failed to enter sprint scope change: " + scopeChange);
			String inProgressStories = updateTestDataMap.get("In Progress");
			boolean isInProgressValueSet = deliveryMilestonePageObj.enterTextToInProgressField(inProgressStories);
			IsTrue(isInProgressValueSet, "Successfully entered value in 'In Progress' field: " + inProgressStories,"Failed to enter value in 'In Progress' field: " + inProgressStories);
			String blockedStories = updateTestDataMap.get("Blocked");
			boolean isBlockedFieldSet = deliveryMilestonePageObj.enterTextToBlockedField(blockedStories);
			IsTrue(isBlockedFieldSet, "Successfully entered value in 'Blocked' field: " + blockedStories,"Failed to enter value in 'Blocked' field: " + blockedStories);
			String completedStories = updateTestDataMap.get("Completed");
			boolean isCompletedFieldSet = deliveryMilestonePageObj.enterTextToCompletedField(completedStories);
			IsTrue(isCompletedFieldSet, "Successfully entered value in 'Completed'vbg" + " neld: " + completedStories,"Failed to enter value in 'Completed' field: " + completedStories);
			String ragStatus = updateTestDataMap.get("RAG Status");
			boolean isRAGStatusSet = deliveryMilestonePageObj.selectRAGStatus(ragStatus);
			IsTrue(isRAGStatusSet, "Successfully selected RAG status" + ragStatus,"Failed to select RAG status: " + ragStatus);
			String p1DefectCount = updateTestDataMap.get("P1 - High Priority");
			boolean isP1DefectFieldSet = deliveryMilestonePageObj.enterTextToP1DefectField(p1DefectCount);
			IsTrue(isP1DefectFieldSet, "Successfully entered value in 'P1 Defect' field: " + p1DefectCount,"Failed to enter value in 'P1 Defect' field: " + p1DefectCount);
			String p2DefectCount = updateTestDataMap.get("P2- High Priority");
			boolean isP2DefectFieldSet = deliveryMilestonePageObj.enterTextToP2DefectField(p2DefectCount);
			IsTrue(isP2DefectFieldSet, "Successfully entered value in 'P2 Defect' field: " + p2DefectCount,"Failed to enter value in 'P2 Defect' field: " + p2DefectCount);
			String p3DefectCount = updateTestDataMap.get("P3");
			boolean isP3DefectFieldSet = deliveryMilestonePageObj.enterTextToP3DefectField(p3DefectCount);
			IsTrue(isP3DefectFieldSet, "Successfully entered value in 'P3 Defect' field: " + p3DefectCount,"Failed to enter value in 'P3 Defect' field: " + p3DefectCount);
			String p4DefectCount = updateTestDataMap.get("P4");
			boolean isP4DefectFieldSet = deliveryMilestonePageObj.enterTextToP4DefectField(p4DefectCount);
			IsTrue(isP4DefectFieldSet, "Successfully entered value in 'P4 Defect' field: " + p4DefectCount,"Failed to enter value in 'P4 Defect' field: " + p4DefectCount);
			deliveryMilestonePageObj.clkSaveChangesButton();
			boolean successMsg = deliveryMilestonePageObj.verifySuccessMsg();
			IsTrue(successMsg, "Sprint is updated successfully", "Fail to update sprint");
			if(successMsg)
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

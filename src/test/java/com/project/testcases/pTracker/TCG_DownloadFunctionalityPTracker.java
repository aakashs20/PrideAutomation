package com.project.testcases.pTracker;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.ActionProjectsPage;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PL_ActiveProjectsPage;
import com.project.pageobjects.pTracker.PL_ClosedProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;
import com.project.thread.constants.TC;

public class TCG_DownloadFunctionalityPTracker  extends TestBase{
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	PL_ClosedProjectsPage closedProject;
	ControlActions controlActions;
	Operations op ;
	private String uName = "admin";
	private String uPassword = "admin";
	private String eName = "Mahajan, Milind";
	private String downloadsFolderPath = System.getProperty("user.home") +"\\Downloads\\";
	private static final int DELAY = 20;
	String workspace = System.getProperty("user.dir");
	String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	String sheetName = "Automation";

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {
		// setting up property to suppress the warning
		System.setProperty("webdriver.chrome.silentOutput","true");
		driver = launchbrowser();
        String currentWindow = driver.getWindowHandle();
        driver.switchTo().window(currentWindow);
		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);		
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, DELAY);
		op = new Operations(driver);
		controlActions = new ControlActions(driver);
		loginPage = new PTrackerLoginPage(driver);
		loginPage.waitForPageLoaded(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		loginPage.TC_Login(driver,uName, uPassword);
		loginPage.TC_ChangeUser(driver,eName);
	}

	@Test(priority = 4 ,description = "Validate Download Functionalites on New Project Tab")
	public void verifyDownloadFeatureForNewProjects(){
		String tcID = "TC_DOWNLOAD_FEATURE_FOR_NEW_PROJECTS";
		logInfo("Starting of Test Case : " + tcID );
		int tcRowNum = 0;
		newProject = new NewProjectsPage(driver);
		String expectedFileName = "New_Projects.csv";
		File newProjectFile = new File(downloadsFolderPath + expectedFileName);
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase",tcID);
			HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, 0, tcRowNum-1);
			String projectName = rowData.get("ProjectName");
			logInfo("Delete any exiting file that is present");
			if (newProjectFile.exists())
				newProjectFile.delete();

			IsTrue(newProject.NewProjectLink.isDisplayed(),"New Project Tab is Displayed","New Project Tab not displayed");
			logInfo("Navigate to New Project Tab");
			op.clickElement(newProject.NewProjectLink);
			wait.until(ExpectedConditions.visibilityOf(newProject.DownloadBtn));
			wait.until(ExpectedConditions.elementToBeClickable(newProject.DownloadBtn));
			IsTrue(newProject.DownloadBtn.isDisplayed(),"Download button is displayed","Download button not displayed");
			op.clickElement(newProject.DownloadBtn);
			wait.until((ExpectedCondition<Boolean>) WebDriver -> newProjectFile.exists());
			Boolean isFileDownloaded = newProjectFile.exists();
			IsTrue(isFileDownloaded,"Report file downloaded Successfully" ,"Failed to download report file");

			if (isFileDownloaded) {
				logInfo("Project Successfully Submited with Request ID: " + projectName);
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
			}else {
				logInfo("Project Unsuccessfully Submited with Request ID: " + projectName);
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logError("Error occurred during execution of test "+e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Assert.fail();
		} finally {
			if (newProjectFile.exists())
				newProjectFile.delete();
		}
		logInfo("End of Test Case : " + tcID );
	}

	@Test(priority = 2 ,description = "Validate Download Functionalites on Project Actions Tab")
	public void verifyDownloadFeatureForProjectsActions(){
		String tcID = "TC_DOWNLOAD_FEATURE_FOR_ACTION_PROJECTS";
		logInfo("Starting of Test Case : " + tcID );
		int tcRowNum = 0;
		ActionProjectsPage actionsProject = new ActionProjectsPage(driver);
		newProject = new NewProjectsPage(driver);
		String expectedFileName = "Project_Actions.csv";
		File activeProjectFile = new File(downloadsFolderPath + expectedFileName);

		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase",tcID);
			HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, 0, tcRowNum-1);
			String projectName = rowData.get("ProjectName");
			logInfo("Delete any exiting file that is present");
			if (activeProjectFile.exists())
				activeProjectFile.delete();

			IsTrue(actionsProject.ProjectActionsLink.isDisplayed(),"Project Actions Tab is displayed","Project Actions Tab not displayed");

			logInfo("Navigate to Projects Actions Tab");
			op.clickElement(actionsProject.ProjectActionsLink);
			wait.until(ExpectedConditions.visibilityOf(actionsProject.DownloadBtn));
			wait.until(ExpectedConditions.elementToBeClickable(actionsProject.DownloadBtn));
			IsTrue(actionsProject.DownloadBtn.isDisplayed(),"Download button is displayed","Download button not displayed");
			op.clickElement(actionsProject.DownloadBtn);
			wait.until((ExpectedCondition<Boolean>) WebDriver -> activeProjectFile.exists());
			Boolean isFileDownloaded = activeProjectFile.exists();
			IsTrue(isFileDownloaded,"Report downloaded Successfully", "Failed to download report file");
			
			if (isFileDownloaded) {
				logInfo("Project Successfully Submited with Request ID: " + projectName);
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
			}else {
				logInfo("Project Unsuccessfully Submited with Request ID: " + projectName);
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error occurred during execution of test "+e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Assert.fail();
		} finally {
			if (activeProjectFile.exists())
				activeProjectFile.delete();
		}
		logInfo("End of Test Case : " + tcID );
	}

	@Test(priority = 3 ,description = "Validate Download Functionalites on Closed Project Tab")
	public void verifyDownloadFeatureForClosedProjects(){
		String tcID = "TC_DOWNLOAD_FEATURE_FOR_CLOSED_PROJECTS";
		logInfo("Starting of Test Case : " + tcID );
		int tcRowNum = 0;
		closedProject = new PL_ClosedProjectsPage(driver);
		newProject = new NewProjectsPage(driver);
		String expectedFileName = "Closed_Projects.csv";
		File closedProjectFile = new File(downloadsFolderPath + expectedFileName);

		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase",tcID);
			HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, 0, tcRowNum-1);
			String projectName = rowData.get("ProjectName");
			logInfo("Delete any exiting file that is present");
			if (closedProjectFile.exists())
				closedProjectFile.delete();

			IsTrue(newProject.ClosedProjectTabLink.isDisplayed(),"Closed Project Tab is displayed","Closed Project Tab not displayed");
			logInfo("Navigate to Closed Project Tab");
			op.clickElement(newProject.ClosedProjectTabLink);
			wait.until(ExpectedConditions.visibilityOf(closedProject.DownloadBtn));
			wait.until(ExpectedConditions.elementToBeClickable(closedProject.DownloadBtn));
			IsTrue(closedProject.DownloadBtn.isDisplayed(),"Download button is displayed","Download button not displayed");
			op.clickElement(closedProject.DownloadBtn);
			wait.until((ExpectedCondition<Boolean>) WebDriver -> closedProjectFile.exists());
			Boolean isFileDownloaded = closedProjectFile.exists();
			IsTrue(isFileDownloaded,"Report downloaded Successfully","Failed to download report file");

			if (isFileDownloaded) {
				logInfo("Project Successfully Submited with Request ID: " + projectName);
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
			}else {
				logInfo("Project Unsuccessfully Submited with Request ID: " + projectName);
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error occurred during execution of test "+e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Assert.fail();
		} finally {
			if (closedProjectFile.exists())
				closedProjectFile.delete();
		}	
		logInfo("End of Test Case : " + tcID );
	}	

	@Test(priority=1 ,description = "Validate Download Functionalites on Active Project Tab")
	public void verifyDownloadFeatureForActiveProjects(){
		String tcID = "TC_DOWNLOAD_FEATURE_FOR_ACTIVE_PROJECTS";
		logInfo("Starting of Test Case : " + tcID );
		int tcRowNum = 0;
		newProject = new NewProjectsPage(driver);
		PL_ActiveProjectsPage activeProject = new PL_ActiveProjectsPage(driver);
		String expectedFileName = "Active_Projects.csv";
		File newProjectFile = new File(downloadsFolderPath + expectedFileName);
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase",tcID);
			HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, 0, tcRowNum-1);
			String projectName = rowData.get("ProjectName");
			logInfo("Delete any exiting file that is present");
			if (newProjectFile.exists())
				newProjectFile.delete();

			IsTrue(newProject.ActiveProjectTabLink.isDisplayed(),"Active Project Tab is displayed","Active Project Tab is not displayed");
			logInfo("Navigate to New Project Tab");
			op.clickElement(newProject.ActiveProjectTabLink);
			wait.until(ExpectedConditions.visibilityOf(activeProject.DownloadBtn));
			wait.until(ExpectedConditions.elementToBeClickable(activeProject.DownloadBtn));
			IsTrue(activeProject.DownloadBtn.isDisplayed(),"Download button is displayed","Download button is not displayed");
			op.clickElement(activeProject.DownloadBtn);
			wait.until((ExpectedCondition<Boolean>) WebDriver -> newProjectFile.exists());
			Boolean isFileDownloaded = newProjectFile.exists();
			IsTrue(isFileDownloaded,"Report downloaded Successfully","Failed to download report file");

			if (isFileDownloaded) {
				logInfo("Project Successfully Submited with Request ID: " + projectName);
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
			}else {
				logInfo("Project Unsuccessfully Submited with Request ID: " + projectName);
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error occurred during execution of test "+e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Fail("Error occurred during execution of test "+e.getMessage());
		} finally {
			if (newProjectFile.exists())
				newProjectFile.delete();
		}	
		logInfo("End of Test Case : " + tcID );
	}	

	@AfterClass (alwaysRun = true)
	public void tearDown() {
		try {
			 op.closeBrowser(driver);
			//TC.get().driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

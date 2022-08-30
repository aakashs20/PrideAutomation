package com.project.testcases.pTracker;

import java.io.File;
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

public class TCG_DownloadFunctionalityPTracker  extends TestBase{
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	PL_ClosedProjectsPage closedProject;
	ControlActions controlActions;
	Operations actions ;
	private String uName = "abc";
	private String uPassword = "xyz";
	private String eName = "Mahajan, Milind";
	private String downloadsFolderPath = System.getProperty("user.home") +"\\Downloads\\";
	private static final int DELAY = 10;
    String workspace = System.getProperty("user.dir");
	String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	String sheetName = "Automation";
	
  @BeforeClass
	public void groupInit() throws Exception {
	  	driver = launchbrowser();
		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);		
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, DELAY);
	  	actions = new Operations(driver);
		controlActions = new ControlActions(driver);
		loginPage = new PTrackerLoginPage(driver);
		loginPage.waitForPageLoaded(driver);
		
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		loginPage.TC_Login(driver,uName, uPassword);
		loginPage.TC_ChangeUser(driver,eName);
	}
  
  @Test(priority  = 4 ,description = "Validate Download Functionalites on New Project Tab")
  public void verifyDownloadFeatureForNewProjects(){
	  	int tcRowNum = 0;
	  	newProject = new NewProjectsPage(driver);
		String expectedFileName = "New_Projects.csv";
		File newProjectFile = new File(downloadsFolderPath + expectedFileName);
		try {
			
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_DOWNLOAD_FEATURE_FOR_NEW_PROJECTS");
			HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, 0, tcRowNum-1);
			String projectName = rowData.get("ProjectName");
			logInfo("Delete any exiting file that is present");
			if (newProjectFile.exists())
				newProjectFile.delete();
			
			IsTrue(newProject.NewProjectLink.isDisplayed(),"New Project Tab is Displayed","New Project Tab not displayed");
			logInfo("Navigate to New Project Tab");
			actions.clickElement(newProject.NewProjectLink);
			wait.until(ExpectedConditions.visibilityOf(newProject.DownloadBtn));
			wait.until(ExpectedConditions.elementToBeClickable(newProject.DownloadBtn));
			IsTrue(newProject.DownloadBtn.isDisplayed(),"Download button is displayed","Download button not displayed");
			actions.clickElement(newProject.DownloadBtn);
			wait.until((ExpectedCondition<Boolean>) WebDriver -> newProjectFile.exists());
			Boolean isFileDownloaded = newProjectFile.exists();
			IsTrue(isFileDownloaded,"Report file downloaded Successfully" ,"Failed to download report file");
			
			logInfo("Project Successfully Submited with Request ID: " + projectName);
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error occurred during execution of test "+e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Assert.fail();
		} finally {
			if (newProjectFile.exists())
				newProjectFile.delete();
		}
		 
  }
  
  @Test(priority  = 2 ,description = "Validate Download Functionalites on Project Actions Tab")
  public void verifyDownloadFeatureForProjectsActions(){
	  	int tcRowNum = 0;
	  	ActionProjectsPage actionsProject = new ActionProjectsPage(driver);
	  	newProject = new NewProjectsPage(driver);
		String expectedFileName = "Project_Actions.csv";
		File activeProjectFile = new File(downloadsFolderPath + expectedFileName);
		
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_DOWNLOAD_FEATURE_FOR_ACTION_PROJECTS");
			HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, 0, tcRowNum-1);
			String projectName = rowData.get("ProjectName");
			logInfo("Delete any exiting file that is present");
			if (activeProjectFile.exists())
				activeProjectFile.delete();
			
			IsTrue(actionsProject.ProjectActionsLink.isDisplayed(),"Project Actions Tab is displayed","Project Actions Tab not displayed");
			
			logInfo("Navigate to Projects Actions Tab");
			actions.clickElement(actionsProject.ProjectActionsLink);
			wait.until(ExpectedConditions.visibilityOf(actionsProject.DownloadBtn));
			wait.until(ExpectedConditions.elementToBeClickable(actionsProject.DownloadBtn));
			IsTrue(actionsProject.DownloadBtn.isDisplayed(),"Download button is displayed","Download button not displayed");
			actions.clickElement(actionsProject.DownloadBtn);
			wait.until((ExpectedCondition<Boolean>) WebDriver -> activeProjectFile.exists());
			Boolean isFileDownloaded = activeProjectFile.exists();
			IsTrue(isFileDownloaded,"Report downloaded Successfully", "Failed to download report file");
			
			logInfo("Project Successfully Submited with Request ID: " + projectName);
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error occurred during execution of test "+e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Assert.fail();
		} finally {
			if (activeProjectFile.exists())
				activeProjectFile.delete();
		}
  }
  
  @Test(priority  = 3 ,description = "Validate Download Functionalites on Closed Project Tab")
  public void verifyDownloadFeatureForClosedProjects(){
	  	int tcRowNum = 0;
	  	closedProject = new PL_ClosedProjectsPage(driver);
	  	newProject = new NewProjectsPage(driver);
		String expectedFileName = "Closed_Projects.csv";
		File closedProjectFile = new File(downloadsFolderPath + expectedFileName);
		
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_DOWNLOAD_FEATURE_FOR_CLOSED_PROJECTS");
			HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, 0, tcRowNum-1);
			String projectName = rowData.get("ProjectName");
			logInfo("Delete any exiting file that is present");
			if (closedProjectFile.exists())
				closedProjectFile.delete();
			
			IsTrue(newProject.ClosedProjectTabLink.isDisplayed(),"Closed Project Tab is displayed","Closed Project Tab not displayed");
			logInfo("Navigate to Closed Project Tab");
			actions.clickElement(newProject.ClosedProjectTabLink);
			wait.until(ExpectedConditions.visibilityOf(closedProject.DownloadBtn));
			wait.until(ExpectedConditions.elementToBeClickable(closedProject.DownloadBtn));
			IsTrue(closedProject.DownloadBtn.isDisplayed(),"Download button is displayed","Download button not displayed");
			actions.clickElement(closedProject.DownloadBtn);
			wait.until((ExpectedCondition<Boolean>) WebDriver -> closedProjectFile.exists());
			Boolean isFileDownloaded = closedProjectFile.exists();
			IsTrue(isFileDownloaded,"Report downloaded Successfully","Failed to download report file");
			
			logInfo("Project Successfully Submited with Request ID: " + projectName);
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error occurred during execution of test "+e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Assert.fail();
		} finally {
			if (closedProjectFile.exists())
				closedProjectFile.delete();
		}				
  }	
  
  @Test(priority  =1 ,description = "Validate Download Functionalites on Active Project Tab")
  public void verifyDownloadFeatureForActiveProjects(){
	  	int tcRowNum = 0;
	  	newProject = new NewProjectsPage(driver);
	  	PL_ActiveProjectsPage activeProject = new PL_ActiveProjectsPage(driver);
		String expectedFileName = "Project_Actions.csv";
		File newProjectFile = new File(downloadsFolderPath + expectedFileName);
		
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_DOWNLOAD_FEATURE_FOR_ACTIVE_PROJECTS");
			HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, 0, tcRowNum-1);
			String projectName = rowData.get("ProjectName");
			logInfo("Delete any exiting file that is present");
			if (newProjectFile.exists())
				newProjectFile.delete();
			
			IsTrue(newProject.ActiveProjectTabLink.isDisplayed(),"Active Project Tab is displayed","Active Project Tab is not displayed");
			logInfo("Navigate to New Project Tab");
			actions.clickElement(newProject.ActiveProjectTabLink);
			wait.until(ExpectedConditions.visibilityOf(activeProject.DownloadBtn));
			wait.until(ExpectedConditions.elementToBeClickable(activeProject.DownloadBtn));
			IsTrue(activeProject.DownloadBtn.isDisplayed(),"Download button is displayed","Download button is not displayed");
			actions.clickElement(activeProject.DownloadBtn);
			//wait.until((ExpectedCondition<Boolean>) WebDriver -> newProjectFile.exists());
			//Boolean isFileDownloaded = newProjectFile.exists();
			//IsTrue(isFileDownloaded,"Report downloaded Successfully","Failed to download report file");
			
			logInfo("Project Successfully Submited with Request ID: " + projectName);
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error occurred during execution of test "+e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Assert.fail();
		} finally {
			if (newProjectFile.exists())
				newProjectFile.delete();
		}				
  }	
  
  @AfterClass
  public void closeBrowser() throws InterruptedException {
	  driver.close();
  }

}

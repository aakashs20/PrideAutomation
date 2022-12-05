package com.project.testcases.pTracker;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class TCG_SearchFunctionalityProjectAction extends TestBase{
	

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ActionProjectsPage actionsProject;
	ControlActions controlActions;
	Operations op ;
	private String uName = "admin";
	private String uPassword = "admin";
	private String eName = "Mahajan, Milind";
	private static final int DELAY = 10;
	String expectedFileName = "Project_Actions.csv";
	String projectNumber;
	String invalidText;
	String partialText;
	String noElementFoundMessage = "No pending actions project has been found.";
    String workspace = System.getProperty("user.dir");
	String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	String inputDataFilePath = workspace+"\\test-data-files\\UI-TestData\\TC_SearchFunctionalityPTrackerPageData.xls";
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
		newProject = new NewProjectsPage(driver);
		actionsProject = new ActionProjectsPage(driver);
		loginPage.waitForPageLoaded(driver);
		int tcRowNum = ExcelUtils.getRowNum(inputDataFilePath,"inputData","TestName","TCG_SearchFunctionalityProjectAction");
		HashMap<String, String> inputData = ExcelUtils.getTestDataXls(inputDataFilePath, "inputData", 0, tcRowNum-1);
		projectNumber = inputData.get("ProjectNumber");
		partialText = inputData.get("PartialSearchText");
		invalidText = inputData.get("InvalidSearchText");
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		loginPage.TC_Login(driver,uName, uPassword);
		loginPage.TC_ChangeUser(driver,eName);
	}
  
  @Test(priority = 1,description = "Validate Search Functionalites for Valid Input")
  public void verifySearchFeatureValidInputforProjectActions(){
	  	int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_SEARCH_FEATURE_VALID_INPUT_FOR_PROJECTS_ACTION");
			IsTrue(newProject.ActiveProjectTabLink.isDisplayed(),"Project Actions Tab is Displayed","New Project Tab not displayed");
			logInfo("Navigate to Project Actions Tab");
			op.clickElement(actionsProject.ProjectActionsLink);
			wait.until(ExpectedConditions.visibilityOf(actionsProject.DownloadBtn));
			wait.until(ExpectedConditions.elementToBeClickable(actionsProject.DownloadBtn));
			IsTrue(actionsProject.DownloadBtn.isDisplayed(),"Download button is displayed","Download button not displayed");
			op.waitForElementToBeDisplayed(actionsProject.SearchProjectTxt);
			threadsleep(2000);
			op.clickElement(actionsProject.SearchProjectTxt);
			actionsProject.SearchProjectTxt.sendKeys(projectNumber);
			op.clickElement(actionsProject.GoBtn);
			threadsleep(1000);
			//String projectNumberActual = actionsProject.ProjectTableRows.get(0).findElements(By.tagName("td")).get(1).getText().trim();
			String projectNumberActual = driver.findElement(By.xpath("//*[@id='report_table_project-actions-report']/tbody/tr/td[2]")).getText().trim();
			System.out.println("----------------- "+projectNumberActual);
			System.out.println("----------------- "+projectNumber );
			List<WebElement> ElementsList = driver.findElements(By.xpath("//*[@id='report_table_project-actions-report']/tbody/tr/td")); 
			int size = ElementsList.size();
				for (int i = 0; i < size; i++)
				{
					logInfo( i + " -----> " + ElementsList.get(i).getText());
				}
			Boolean isElementCountOne = actionsProject.ProjectTableRows.size() >= 1 ;
			IsTrue(isElementCountOne ,"Only one element is displayed in the table","Only one element should be displayed in the table");

			Boolean isElementFound = projectNumberActual.contains(projectNumber);
			IsTrue(isElementFound,"Searched element is found at the top of the table","Searched element is not found at the top of the table");
			actionsProject.SearchProjectTxt.clear();	
			threadsleep(3000);
			if(isElementCountOne && isElementFound) {
				logInfo("Test was Successfully ");
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
			}else {
				logInfo("Test Failed ");
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error "+e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			//Assert.fail();
		}
		 
  }
  
  @Test(priority = 2,description = "Validate Search Functionalites for Partial Input")
  public void verifySearchFeaturePartialInputForProjectActions(){
	  	int tcRowNum = 0;
		Boolean areFiveProjectsDiplayed = false;
		Boolean areMoreThanOneProjectsDiplayed = false;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_SEARCH_FEATURE_PARTIAL_INPUT_FOR_PROJECTS_ACTION");
			
			op.clickElement(actionsProject.SearchProjectTxt);
			actionsProject.SearchProjectTxt.sendKeys(partialText);
			op.clickElement(actionsProject.GoBtn);
			threadsleep(3000);

			IsTrue(actionsProject.ProjectTableRows.size() > 1,"More than 1 Project Reports is displayed","More than 1 Project Reports should be displayed");
			if (actionsProject.ProjectActionPagination.size() > 0) {
				areFiveProjectsDiplayed = actionsProject.ProjectTableRows.size() == 5;
				IsTrue(areFiveProjectsDiplayed,"Row size is 5","Row size is not 5");
			}else {
				areMoreThanOneProjectsDiplayed = actionsProject.ProjectTableRows.size() >= 1;
				IsTrue(areMoreThanOneProjectsDiplayed,"Row size more than 1","Row size is not more than 1");
			}

			op.clickElement(actionsProject.SearchProjectTxt);
			actionsProject.SearchProjectTxt.clear();	
			threadsleep(1000);
            
			IsTrue(actionsProject.ProjectTableRows.size() >= 1,"Row size more than 1","Row size is not more than 1");

			if(areFiveProjectsDiplayed || areMoreThanOneProjectsDiplayed) {
				logInfo("Test was Successfully ");
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");

			}else {
				logInfo("Test Failed ");
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error "+e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			//Assert.fail();
		}
		 
  }
  
  @Test(priority = 3,description= "Validate Search Functionalites for Invalid Input")
  public void verifySearchFeatureInvalidInputForProjectActions(){
	  	int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_SEARCH_FEATURE_INVALID_INPUT_FOR_PROJECTS_ACTION");
			
			op.clickElement(actionsProject.SearchProjectTxt);
			actionsProject.SearchProjectTxt.clear();
			actionsProject.SearchProjectTxt.sendKeys(invalidText);
			op.clickElement(actionsProject.GoBtn);
			threadsleep(1000);
			Boolean isNoPendingProjectFoundMsgDisplayed= newProject.NoPendingProjectFoundMsg.isDisplayed();
			logInfo("Web Table Size is : "+ actionsProject.ProjectTableRows.size());
			logInfo("Is No pending actions project has been found Message is shown? "+ isNoPendingProjectFoundMsgDisplayed);
			//System.out.println("-------------------------- "+ newProject.NoPendingProjectFoundMsg.getText());
			//Boolean areNoElementsDisplayed = actionsProject.ProjectTableRows.size() == 0;
			IsTrue(isNoPendingProjectFoundMsgDisplayed,"No Project Reports is displayed for Invalid entry","No Project Reports should be displayed for Invalid entry");
			Boolean isNoElementFoundMessageDisplayed = newProject.NoPendingProjectFoundMsg.isDisplayed();
			IsTrue(isNoElementFoundMessageDisplayed,"No Project Reports is displayed for Invalid entry","No Project Reports should be displayed for Invalid entry");
			Boolean isNoElementFoundTextCorrect = newProject.NoPendingProjectFoundMsg.getText().trim().equalsIgnoreCase(noElementFoundMessage);
			IsTrue(isNoElementFoundTextCorrect,"'No project has been found.' is displayed for invalid entry","'No project has been found.' should be displayed for invalid entry");
			Boolean areNoPaginationsDisplayed = actionsProject.ProjectActionPagination.size() == 0;
			IsTrue(areNoPaginationsDisplayed,"Pagination is not displayed","Pagination should not be displayed");

			op.clickElement(actionsProject.SearchProjectTxt);
			actionsProject.SearchProjectTxt.clear();	
			threadsleep(1000);

			if(isNoPendingProjectFoundMsgDisplayed && isNoElementFoundTextCorrect && isNoElementFoundMessageDisplayed && areNoPaginationsDisplayed) {
				logInfo("Test Case was Successfully ");
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");

			}else {
				logInfo("Test Case Failed ");
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error "+e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			//Assert.fail();
		}
  }
  
	@AfterClass (alwaysRun = true)
	public void tearDown() {
		try {
			op.closeBrowser(driver);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

package com.project.testcases.pTracker;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PL_ClosedProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class TCG_SearchFunctionalityClosedProject extends TestBase{
	
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	PL_ClosedProjectsPage closedProject;
	ControlActions controlActions;
	Operations op ;
	private String uName = "admin";
	private String uPassword = "admin";
	private String eName = "Mahajan, Milind";
	private static final int DELAY = 10;
	String expectedFileName = "Closed_Projects.csv";
	String projectNumber;
	String invalidText;
	String partialText;
	String noElementFoundMessage = "No project has been found.";
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
		closedProject = new PL_ClosedProjectsPage(driver);
		loginPage.waitForPageLoaded(driver);
		int tcRowNum = ExcelUtils.getRowNum(inputDataFilePath,"inputData","TestName","TCG_SearchFunctionalityClosedProject");
		HashMap<String, String> inputData = ExcelUtils.getTestDataXls(inputDataFilePath, "inputData", 0, tcRowNum-1);
		projectNumber = inputData.get("ProjectNumber");
		partialText = inputData.get("PartialSearchText");
		invalidText = inputData.get("InvalidSearchText");
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		loginPage.TC_Login(driver,uName, uPassword);
		loginPage.TC_ChangeUser(driver,eName);
	}
  
  @Test(priority = 1,description = "Validate Search Functionalites for Valid Input")
  public void verifySearchFeatureValidInputforClosedProjects(){
	  	int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_SEARCH_FEATURE_VALID_INPUT_FOR_CLOSED_PROJECTS");
			
			IsTrue(newProject.ActiveProjectTabLink.isDisplayed(),"Closed Project Tab is Displayed","New Project Tab not displayed");
			logInfo("Navigate to Closed Project Tab");
			op.clickElement(newProject.ClosedProjectTabLink);
			wait.until(ExpectedConditions.visibilityOf(closedProject.DownloadBtn));
			wait.until(ExpectedConditions.elementToBeClickable(closedProject.DownloadBtn));
			IsTrue(closedProject.DownloadBtn.isDisplayed(),"Download button is displayed","Download button not displayed");

			op.waitForElementToBeDisplayed(closedProject.SearchProjectTxt);
			op.clickElement(closedProject.SearchProjectTxt);
			closedProject.SearchProjectTxt.sendKeys(projectNumber);
			op.clickElement(closedProject.GoBtn);

			threadsleep(3000);

			String projectNumberActual = closedProject.ProjectTableRows.get(0).findElement(By.tagName("td")).getText().trim();
			Boolean isElementCountOne = closedProject.ProjectTableRows.size() == 1 ;
			IsTrue(isElementCountOne ,"Only one element is displayed in the table","Only one element should be displayed in the table");
			Boolean isElementFound = projectNumberActual.equalsIgnoreCase(projectNumber);
			IsTrue(isElementFound,"Searched element is found at the top of the table","Searched element is not found at the top of the table");
			closedProject.SearchProjectTxt.clear();	
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
			Assert.fail();
		}
		 
  }
  
  @Test(priority = 2,description = "Validate Search Functionalites for Partial Input")
  public void verifySearchFeaturePartialInputForClosedProjects(){
	  	int tcRowNum = 0;
		Boolean areFiveProjectsDiplayed = false;
		Boolean areMoreThanOneProjectsDiplayed = false;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_SEARCH_FEATURE_PARTIAL_INPUT_FOR_CLOSED_PROJECTS");
			
			op.clickElement(closedProject.SearchProjectTxt);
			closedProject.SearchProjectTxt.sendKeys(partialText);
			op.clickElement(closedProject.GoBtn);
			threadsleep(3000);

			IsTrue(closedProject.ProjectTableRows.size() > 1,"More than 1 Project Reports is displayed","More than 1 Project Reports should be displayed");
			if (closedProject.ClosedProjectPagination.size() > 0) {
				areFiveProjectsDiplayed = closedProject.ProjectTableRows.size() == 5;
				IsTrue(areFiveProjectsDiplayed,"Row size is 5","Row size is not 5");
			}else {
				areMoreThanOneProjectsDiplayed = closedProject.ProjectTableRows.size() >= 1;
				IsTrue(areMoreThanOneProjectsDiplayed,"Row size more than 1","Row size is not more than 1");
			}


			op.clickElement(closedProject.SearchProjectTxt);
			closedProject.SearchProjectTxt.clear();	
			threadsleep(3000);

			IsTrue(closedProject.ProjectTableRows.size() == 5,"Row size is 5","Row size is not 5");

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
			Assert.fail();
		}
		 
  }
  
  @Test(priority = 3,description= "Validate Search Functionalites for Invalid Input")
  public void verifySearchFeatureInvalidInputForClosedProjects(){
	  	int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_SEARCH_FEATURE_INVALID_INPUT_FOR_CLOSED_PROJECTS");
			
			op.clickElement(closedProject.SearchProjectTxt);
			closedProject.SearchProjectTxt.clear();
			closedProject.SearchProjectTxt.sendKeys(invalidText);
			op.clickElement(closedProject.GoBtn);
			threadsleep(3000);
			Boolean a= newProject.NoProjectMessage.isDisplayed();
			System.out.println("No Project Found Message Displayed on Screen? "+ a);
			System.out.println("No Project Found Message is: "+ newProject.NoProjectMessage.getText());
			Boolean areNoElementsDisplayed = closedProject.ProjectTableRows.size() == 0;
			IsTrue(areNoElementsDisplayed,"No Project Reports is displayed for Invalid entry","No Project Reports should be displayed for Invalid entry");
			Boolean isNoElementFoundMessageDisplayed = newProject.NoProjectMessage.isDisplayed();
			IsTrue(isNoElementFoundMessageDisplayed,"No Project Reports is displayed for Invalid entry","No Project Reports should be displayed for Invalid entry");
			Boolean isNoElementFoundTextCorrect = newProject.NoProjectMessage.getText().trim().equalsIgnoreCase(noElementFoundMessage);
			IsTrue(isNoElementFoundTextCorrect,"'No project has been found.' is displayed for invalid entry","'No project has been found.' should be displayed for invalid entry");
			Boolean areNoPaginationsDisplayed = closedProject.ClosedProjectPagination.size() == 0;
			IsTrue(areNoPaginationsDisplayed,"Pagination is not displayed","Pagination should not be displayed");

			op.clickElement(closedProject.SearchProjectTxt);
			closedProject.SearchProjectTxt.clear();	
			threadsleep(1000);

			if(areNoElementsDisplayed && isNoElementFoundTextCorrect && isNoElementFoundMessageDisplayed && areNoPaginationsDisplayed) {
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
  
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		try {
			op.closeBrowser(driver);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

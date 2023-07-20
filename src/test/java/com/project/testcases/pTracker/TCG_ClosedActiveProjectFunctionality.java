package com.project.testcases.pTracker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.Constants;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PL_ActiveProjectsPage;
import com.project.pageobjects.pTracker.PL_ClosedProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class TCG_ClosedActiveProjectFunctionality extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	PL_ClosedProjectsPage closedProject;
	ControlActions controlActions;
	Operations op;
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
	String datapoolPath = workspace + "\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	String inputDataFilePath = workspace + "\\test-data-files\\UI-TestData\\TC_SearchFunctionalityPTrackerPageData.xls";
	String sheetName = "Automation";
	PropertiesConfiguration config_DeliveryProp;
	Properties deliveryProp;
	PL_ActiveProjectsPage activeProject;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {
		config_DeliveryProp = new PropertiesConfiguration(Constants.deliveryMilestonePropertyFile);
		deliveryProp = new Properties();
		deliveryProp.load(new FileInputStream(Constants.deliveryMilestonePropertyFile));

		System.setProperty("webdriver.chrome.silentOutput", "true");
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
		activeProject = new PL_ActiveProjectsPage(driver);
		loginPage.waitForPageLoaded(driver);
		int tcRowNum = ExcelUtils.getRowNum(inputDataFilePath, "inputData", "TestName",	"TCG_SearchFunctionalityClosedProject");
		HashMap<String, String> inputData = ExcelUtils.getTestDataXls(inputDataFilePath, "inputData", 0, tcRowNum - 1);
		projectNumber = inputData.get("ProjectNumber");
		partialText = inputData.get("PartialSearchText");
		invalidText = inputData.get("InvalidSearchText");
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		loginPage.TC_Login(driver, uName, uPassword);
		loginPage.TC_ChangeUser(driver, eName);
	}

	@Test(priority = 1, groups = { "sanity", "UI" }, description = "Validate Close Project Funcationality")
	public void verifyClosedProjectFunctionalityForActiveProject() throws IOException, Exception {
		String tcID = "TC_CLOSE_ACTIVE_PROJECT_FUNCTIONALITY";
		logInfo("Starting of Test Case : " + tcID);
		int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath, sheetName, "testCase", tcID);
			IsTrue(newProject.ActiveProjectTabLink.isDisplayed(), "Active Project Tab is Displayed","New Project Tab not displayed");
			logInfo("Navigate to Active Project Tab");
			op.clickElement(newProject.ActiveProjectTabLink);
			op.waitForElementToBeDisplayed(activeProject.fixedPriceActiveFilterBtn);
			op.clickElement(activeProject.fixedPriceActiveFilterBtn);
			op.waitForElementToBeDisplayed(activeProject.SearchProjectTxt);
			op.clickElement(activeProject.SearchProjectTxt);
			activeProject.SearchProjectTxt.sendKeys("FixPri");
			op.clickElement(activeProject.GoBtn);
			threadsleep(3000);
			String projectNumberActual = activeProject.ProjectTableRows.get(0).findElement(By.tagName("td")).getText().trim();
			config_DeliveryProp.setProperty("projectIdToBeClosed", projectNumberActual);
			config_DeliveryProp.save();
			op.click(activeProject.ProjectTableRows.get(0).findElement(By.tagName("td")));
			op.waitForElementToBeDisplayed(closedProject.closedProjectBtn);
			op.clickElement(closedProject.closedProjectBtn);
			driver.switchTo().frame(closedProject.closureRequestFrame);
			op.waitForAnElementToBeClickable(closedProject.revisedClosureDate);
			op.clickElement(closedProject.revisedClosureDate);
			op.clickElement(closedProject.calendarEnabledDateLists.get(closedProject.calendarEnabledDateLists.size()-3));
			op.sendKeys(closedProject.closureRemarkTxtBox, "Project Marked as Closed");
			op.waitForElementToBeClickable(closedProject.closureRequestSubmitBtn);
			op.clickElement(closedProject.closureRequestSubmitBtn);
			threadsleep(8000);
			activeProject.SearchProjectTxt.sendKeys(config_DeliveryProp.getProperty("projectIdToBeClosed").toString());
			threadsleep(1000);
			op.clickElement(activeProject.GoBtn);
			threadsleep(3000);

			if (!activeProject.ProjectTableRows.isEmpty()) {
				logInfo(projectNumberActual + " Project has NOT been closed successfully");
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
				Assert.fail();
			} else {
				op.clickElement(newProject.ClosedProjectTabLink);
				threadsleep(2000);
				closedProject.SearchProjectTxt
						.sendKeys(config_DeliveryProp.getProperty("projectIdToBeClosed").toString());
				threadsleep(2000);
				op.clickElement(closedProject.GoBtn);
				threadsleep(3000);
				if (!closedProject.ProjectTableRows.isEmpty()) {
					logInfo(projectNumberActual + " Project has been closed successfully");
					Equals(projectNumberActual, closedProject.projectNum.getText(),
							"Project number is in Closed state.", "Project Number is not in closed state");
					ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
				} else {
					logInfo(projectNumberActual + " Project has NOT been closed successfully");
					ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
					Assert.fail();
				}
			}
		} catch (Exception e) {
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
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
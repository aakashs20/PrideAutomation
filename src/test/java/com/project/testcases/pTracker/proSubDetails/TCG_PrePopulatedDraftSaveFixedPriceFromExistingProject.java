package com.project.testcases.pTracker.proSubDetails;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;
import com.project.utilities.ExcelReader;

public class TCG_PrePopulatedDraftSaveFixedPriceFromExistingProject extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	Operations op;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";
	String projectName = null;
	Boolean isTestSkiped;

	int tcRowNum;
	String workspace = System.getProperty("user.dir");
	String datapoolPath = workspace + "\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	String sheetName = "Automation";

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {
		// setting up property to suppress the warning
		System.setProperty("webdriver.chrome.silentOutput", "true");
		driver = launchbrowser();
		String currentWindow = driver.getWindowHandle();
		driver.switchTo().window(currentWindow);
		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		op = new Operations(driver);
		controlActions = new ControlActions(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		loginPage.waitForPageLoaded(driver);
		loginPage.TC_Login(driver, uName, uPassword);
		loginPage.TC_ChangeUser(driver, eName);
	}

	@Test(priority = 1,description = "Only few filelds get prepopulated when a new project is created from the exiting project")
	public void createFixedPriceProjectFromExiting() throws Exception {
		String tcID = "TC_PREPOPULATED_DRAFT_SAVE_FROM_EXISTING_PROJECT";
		logInfo("Starting of Test Case : " + tcID);
		tcRowNum = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcID);
		logInfo("Test Case Row No Is: " + tcRowNum);
		logInfo("Reading Excel:   " + datapoolPath);

		List<WebElement> activeProjectNumber = newProject.ActiveProjectNameList;
		Random random = new Random();

		int result = random.nextInt(activeProjectNumber.size());
		projectName = activeProjectNumber.get(result).getText();
		logInfo("Project Name is:   " + projectName);
		ExcelUtils.setCellData(datapoolPath, "ProjectName", tcRowNum, projectName, "YELLOW");

		if (newProject.createProjectFromExiting(projectName)) {
			threadsleep(3000);

			Boolean expected = hasEmptyField();
			if (expected == true) {
				logError("Mandatory Values are pre-populated in all the expected fields");
				passLog("Mandatory Values are pre-populated in all the expected fields");
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");

			} else {
				logError("Mandatory Values are NOT pre-populated in all the expected fields");
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
				Fail("Mandatory Values are NOT pre-populated in all the expected fields");
			}

			op.waitUntilElementIsClickable(newProject.NewProjectPageCancelBtn);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", newProject.NewProjectPageCancelBtn);
			logInfo("End of Test Case : " + tcID);
		} else {
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "SKIP", "YELLOW");
			isTestSkiped = true;
		}
	}

	/*
	 * This method is to validate that the mentioned fields are mandatory and should
	 * be pre-populated while creating project from the existing one.
	 */
	public Boolean hasEmptyField() {
		String projectMode = newProject.ProjectMode.getAttribute("value");
		String signingOrg = newProject.SigningOrganization.getAttribute("value");
		String signingLoc = newProject.SigningLocation.getAttribute("value");
		String businessUnit = newProject.BusinessUnit.getAttribute("value");
		String lineOfBusiness = newProject.LineofBusiness.getAttribute("value");
		String operationOfcLoc = newProject.OperationOfficeLocation.getAttribute("value");
		String operationLocCity = newProject.OperationLocationCity.getAttribute("value");
		String projCategory = newProject.ProjectCategory.getAttribute("value");
		String projType = newProject.ProjectType.getAttribute("value");
		String progressMethod = newProject.ProgressMethod.getAttribute("value");
		String billingMethod = newProject.BillingMethod.getAttribute("value");
		String billingEndDay = newProject.BillingEndDay.getAttribute("value");
		String cust = newProject.Customer.getAttribute("value");
		String salesPerson = newProject.Salesperson.getAttribute("value");
		String accManager = newProject.AccountManager.getAttribute("value");
		String deliveryHead = newProject.DeliveryHead.getAttribute("value");
		String programManager = newProject.ProgramManager.getAttribute("value");
		String projManager = newProject.ProjectManager.getAttribute("value");

		// To Update pre-populated mandatory data in the excel file
		ExcelUtils.setCellData(datapoolPath, "ProjectMode", tcRowNum, projectMode, "GREEN");
		ExcelUtils.setCellData(datapoolPath, "SigningOrganization", tcRowNum, signingOrg, "GREEN");
		ExcelUtils.setCellData(datapoolPath, "SigningLocation", tcRowNum, signingLoc, "GREEN");
		ExcelUtils.setCellData(datapoolPath, "BusinessUnit", tcRowNum, businessUnit, "GREEN");
		ExcelUtils.setCellData(datapoolPath, "LineofBusiness", tcRowNum, lineOfBusiness, "GREEN");
		ExcelUtils.setCellData(datapoolPath, "OperationOfficeLocation", tcRowNum, operationOfcLoc, "GREEN");
		ExcelUtils.setCellData(datapoolPath, "OperationLocationCity", tcRowNum, operationLocCity, "GREEN");
		ExcelUtils.setCellData(datapoolPath, "ProjectCategory", tcRowNum, projCategory, "GREEN");
		ExcelUtils.setCellData(datapoolPath, "ProjectType", tcRowNum, projType, "GREEN");
		ExcelUtils.setCellData(datapoolPath, "ProgressMethod", tcRowNum, progressMethod, "GREEN");
		ExcelUtils.setCellData(datapoolPath, "BillingMethod", tcRowNum, billingMethod, "GREEN");
		ExcelUtils.setCellData(datapoolPath, "BillingEndDay", tcRowNum, billingEndDay, "GREEN");
		ExcelUtils.setCellData(datapoolPath, "Customer", tcRowNum, cust, "GREEN");
		ExcelUtils.setCellData(datapoolPath, "Salesperson", tcRowNum, salesPerson, "GREEN");
		ExcelUtils.setCellData(datapoolPath, "AccountManager", tcRowNum, accManager, "GREEN");
		ExcelUtils.setCellData(datapoolPath, "DeliveryHead", tcRowNum, deliveryHead, "GREEN");
		ExcelUtils.setCellData(datapoolPath, "ProgramManager", tcRowNum, programManager, "GREEN");
		ExcelUtils.setCellData(datapoolPath, "ProjectManager", tcRowNum, projManager, "GREEN");

		return !projectMode.trim().isEmpty() && !projType.trim().isEmpty() && !signingOrg.trim().isEmpty()
				&& !signingLoc.trim().isEmpty() && !businessUnit.trim().isEmpty() && !lineOfBusiness.trim().isEmpty()
				&& !operationOfcLoc.trim().isEmpty() && !operationLocCity.trim().isEmpty()
				&& !projCategory.trim().isEmpty() && !progressMethod.trim().isEmpty() && !billingMethod.trim().isEmpty()
				&& !billingEndDay.trim().isEmpty() && !cust.trim().isEmpty() && !salesPerson.trim().isEmpty()
				&& !accManager.trim().isEmpty() && !deliveryHead.trim().isEmpty() && !programManager.trim().isEmpty()
				&& !projManager.trim().isEmpty();
	}

	@DataProvider
	public Object[][] dataexcel() throws IOException {
		Object[][] exceldata = null;
		try {
			ExcelReader e = new ExcelReader();
			String excel = prop.getProperty("excel_file_path");
			System.out.println(excel);
			exceldata = e.readDataFromExcel(System.getProperty("user.dir") + File.separator + "test-data-files"
					+ File.separator + "UI-TestData" + File.separator + excel, "Sheet1");

			return exceldata;
		} catch (Exception e) {
			log4jError("Error while reading from excel check the excel path or sheet name: " + e.toString());
			return exceldata;
		}
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException, IOException {
		op.closeBrowser(driver);
	}
}

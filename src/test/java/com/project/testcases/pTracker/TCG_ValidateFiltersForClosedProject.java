package com.project.testcases.pTracker;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.CommonPages;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PL_ClosedProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

//Vaishnavi Gupta

public class TCG_ValidateFiltersForClosedProject extends TestBase {
	
	
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	PL_ClosedProjectsPage closedProjectPage;
	Operations op;
	ControlActions controlActions;
	private String uName = "admin";
	private String uPassword = "admin";
	private String xpath;
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";
	private SoftAssert sAssert;
	private CommonPages commonPage;
	String workspace = System.getProperty("user.dir");
	String datapoolPath = workspace + "\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	String sheetName = "Automation";
	
	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {
		
		Reporter.log("Launching the Browser");
		driver = launchbrowser();
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, DELAY);
		controlActions = new ControlActions(driver);
		sAssert = new SoftAssert();
		
		//Launching the Url
		Reporter.log("Launching the url=" + prop.getProperty("appl_url_dev"));
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		op = new Operations(driver);
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		closedProjectPage = new PL_ClosedProjectsPage(driver);
		loginPage.waitForPageLoaded(driver);
		
		//Login into application
		Reporter.log("Login into application");
		loginPage.TC_Login(driver, uName, uPassword);
		loginPage.TC_ChangeUser(driver, eName);
		commonPage = new CommonPages(driver);

	}

	/*
	 * this test is to verify that afer selection of Fixed Price Filter all the row
	 * in below table should only list Fixed Price. i.e Project Type column of all
	 * row should be Fixed Price.
	 */

	@Test(priority = 1, description = "Validate Fixed Price Filter applied on all project for Closed Project Tab")

	public void verifyFixedPriceFilterForClosedProject() {

		String tcID = "TC_VALIDATE_FIXED_PRICE_FILTER_FOR_CLOSED_PROJECT";
		logInfo("Reading Excel:   "+datapoolPath);
		logInfo("Starting of Test Case : " + tcID );
		Reporter.log("<b> Testcase No-:<b> " + "<b><font color='black'><u> Validate Fixed Price Filter applied on all project for Closed Project Tab </font></b></u>");

		int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath, sheetName, "testCase","TC_VALIDATE_FIXED_PRICE_FILTER_FOR_CLOSED_PROJECT");
			logInfo("Test Case Row No Is: " + tcRowNum);
			// click on closed Project Menu
			Reporter.log("Click on closed projects menu");
			closedProjectPage.clickOnCloseProjectsMenu();

			// Click on fixed price
			Reporter.log("Click on Fixed price under closed projects Module");
			closedProjectPage.clickOnFixedPriceUnderClosedProjectsModule();
			// op.clickElement(closedProjectPage.closedProjectTypeSelector.get(0));
			threadsleep(3000);

			// Validate whether records are displayed as per with Project Type as -: Fixed
			// price
			Reporter.log("Verify whether records are displayed as per with Project Type as -: Fixed price");
			closedProjectPage.verifyProjectTypes(closedProjectPage.closedProjectTableProjectType, "Fixed Price");
			Reporter.log("Validated that records are displayed as per with Project Type as -: Fixed price");

			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Throwable e) {
			e.printStackTrace();
			logError("Error occurred during execution of test " + e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
		}
		logInfo("End of Test Case : " + tcID );
	}

	/*
	 * this test is to verify that afer selection of Support Project Filter all the
	 * row in below table should only list Support Project. i.e Project Type column
	 * of all row should be Support Project
	 */

	@Test(priority = 2, description = "Validate Support Project Filter on Closed Project Tab")
	public void verifySupportProjectFilterForClosedProject() {
		String tcID = "TC_VALIDATE_SUPPORT_PROJECT_FILTER_FOR_CLOSED_PROJECT";
		logInfo("Reading Excel:   "+datapoolPath);
		logInfo("Starting of Test Case : " + tcID );
		Reporter.log("<b> Testcase No -3-:<b> " + "<b><font color='black'><u> Validate Support Project Filter on Closed Project Tab </font></b></u>");
		int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath, sheetName, "testCase", "TC_VALIDATE_SUPPORT_PROJECT_FILTER_FOR_CLOSED_PROJECT");
			logInfo("Test Case Row No Is: " + tcRowNum);
			// click on closed Project Menu
			Reporter.log("Click on closed projects menu");
			closedProjectPage.clickOnCloseProjectsMenu();

			// Click on support project
			Reporter.log("Click on Support Project under closed projects module");
			closedProjectPage.clickOnSupportProjectUnderClosedProjectsModule();
			threadsleep(3000);

			// Validate whether records are displayed as per with Project Type as -:
			// SupportProject
			Reporter.log("Verify whether records are displayed as per with Project Type as -: SupportProject");
			closedProjectPage.verifyProjectTypes(closedProjectPage.closedProjectTableProjectType, "Support Project");
			Reporter.log("Validated that records are displayed as per with Project Type as -: SupportProject");

			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Throwable e) {
			e.printStackTrace();
			logError("Error occurred during execution of test " + e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Fail("verifySupportProjectFilterForClosedProject failed");
		}
		logInfo("End of Test Case : " + tcID );
	}

	/*
	 * this test is to verify that afer selection of Staffing Filter all the row in
	 * below table should only list Staffing. i.e Project Type column of all row
	 * should be Staffing
	 */

	@Test(priority = 3, description = "Validate Staffing Filter on Closed Project Tab")
	public void verifyStaffingFilterForClosedProject() {
		String tcID = "TC_VALIDATE_STAFFING_FILTER_FOR_CLOSED_PROJECT";
		logInfo("Reading Excel:   "+datapoolPath);
		logInfo("Starting of Test Case : " + tcID );
		Reporter.log("<b> Testcase No -4-:<b> " + "<b><font color='black'><u> Validate Staffing Filter on Closed Project Tab </font></b></u>");

		int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath, sheetName, "testCase", "TC_VALIDATE_STAFFING_FILTER_FOR_CLOSED_PROJECT");
			logInfo("Test Case Row No Is: " + tcRowNum);
			// click on closed Project Menu
			Reporter.log("Click on closed projects menu");
			closedProjectPage.clickOnCloseProjectsMenu();

			// Click on Staffing project under Closed Projects Module
			Reporter.log("Click on Staffing Project under closed projects module");
			closedProjectPage.clickOnStaffingUnderClosedProjectsModule();
			threadsleep(3000);

			// Validate whether records are displayed as per with Project Type as -:
			// Staffing
			Reporter.log("Verify whether records are displayed as per with Project Type as -: Staffing");
			closedProjectPage.verifyProjectTypes(closedProjectPage.closedProjectTableProjectType, "Staffing");
			Reporter.log("Validated that records are displayed as per with Project Type as -: Staffing");

			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Throwable e) {
			e.printStackTrace();
			logError("Error occurred during execution of test " + e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Fail("verifyStaffingFilterForClosedProject failed");
		}
		logInfo("End of Test Case : " + tcID );
	}

	/*
	 * this test is to verify that afer selection of Time And MaterialFilter all the
	 * row in below table should only list Time And Material. i.e Project Type
	 * column of all row should be Time And Material
	 */

	@Test(priority = 4, description = "Validate Time And Material Filter on Closed Project Tab")
	public void verifyTimeAndMaterialFilterForClosedProject() {
		String tcID = "TC_VALIDATE_TIME_AND_MATERIAL_FILTER_FOR_CLOSED_PROJECT";
		logInfo("Reading Excel:   "+datapoolPath);
		logInfo("Starting of Test Case : " + tcID );
		Reporter.log(" <b> Testcase<b> "+ "<b><font color='black'><u> Validate Time And Material Filter on Closed Project Tab </font></b></u>");
		int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath, sheetName, "testCase","TC_VALIDATE_TIME_AND_MATERIAL_FILTER_FOR_CLOSED_PROJECT");
			logInfo("Test Case Row No Is: " + tcRowNum);
			// click on closed Project Menu
			Reporter.log("Click on closed projects menu");
			closedProjectPage.clickOnCloseProjectsMenu();

			// Click on Time and Material under Closed Projects Module
			Reporter.log("Click on Time and Material under closed projects module");
			closedProjectPage.clickOnTimeAndMaterialUnderClosedProjectsModule();
			threadsleep(3000);

			// Validate whether records are displayed as per with Project Type as -: Time
			// and Material
			Reporter.log("Verify whether records are displayed as per with Project Type as -: Time and Material");
			closedProjectPage.verifyProjectTypes(closedProjectPage.closedProjectTableProjectType, "Time and Material");
			Reporter.log("Validated that records are displayed as per with Project Type as -: Time and Material");

			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Throwable e) {
			e.printStackTrace();
			logError("Error occurred during execution of test " + e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Fail("verifyTimeAndMaterialFilterForClosedProject Failed");
		}
		logInfo("End of Test Case : " + tcID );
	}

	/*
	 * This test case verifies that if user selects multiple checkboxes from filter
	 * popup then project table's project type column should contain only selected
	 * option from popup.
	 */

	@Test(priority = 5, description = "verify Project Types for multiple Filter Applied ")
	public void verifyProjectTypeForMultipleFilter() {
		String tcID = "TC_VALIDATE_PROJECT_TYPE_FOR_MULTIPLE_FILTER_FOR_CLOSED_PROJECT";
		logInfo("Reading Excel:   "+datapoolPath);
		logInfo("Starting of Test Case : " + tcID );
		Reporter.log(" <b> Testcase No:<b> "+ "<b><font color='black'><u> verify Project Types for multiple Filter Applied </font></b></u>");
		int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath, sheetName, "testCase","TC_VALIDATE_PROJECT_TYPE_FOR_MULTIPLE_FILTER_FOR_CLOSED_PROJECT");
			logInfo("Test Case Row No Is: " + tcRowNum);
			// click on closed Project Menu
			Reporter.log("Click on closed projects menu");
			closedProjectPage.clickOnCloseProjectsMenu();
			threadsleep(3000);

			// Click on Filter under closed Project Tab
			Reporter.log("Click on Filter Icon under closed projects menu");
			closedProjectPage.clickOnFilterIconUnderClosedProjectsModule();
			threadsleep(3000);

			// Click on Filter under closed Project Tab
			Reporter.log("Click on clear link for Project type in the filter panel");
			closedProjectPage.clickOnClearForProjectTypeFilter();
			threadsleep(3000);

			// Select project type Filters
			Reporter.log("Click on closed projects menu");
			closedProjectPage.selectProjectTypeFiltersOnFilterPanel();
			threadsleep(3000);

			// Validate Whether Records are displayed as per the Project Type Filter
			// Selected
			Reporter.log("Validate whether Records are displayed As per the Project Type Filters Selected");
			closedProjectPage.validateWhetherRecordsAreDisplayedAsperTheProjectTypeFiltersSelected();
			threadsleep(3000);
			Reporter.log("Validated that records are displayed as per As per the Project Type Filters Selected");

			// Click On Clear All Link To clear the filters applied
			Reporter.log("Click On Clear All Link To clear the filters applied");
			closedProjectPage.clickOnClearAllButtonForFiltersAppliedUnderClosedProject();

			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Throwable e) {
			e.printStackTrace();
			logError("Error occurred during execution of test " + e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Fail("verifyProjectTypeForMultipleFilter failed");
		}
		logInfo("End of Test Case : " + tcID );
	}

	/*
	 * this test case Validates Filter Applied in Filter Panel under Multiple Filter
	 * Headers for closed Projects
	 */

	@Test(priority = 6, description = "Validate Filter Applied in Filter Panel under Multiple Filter Headers for closed Projects")
	public void verifyAppliedFilterFromBoxAndDropdownForClosedProject() {
		String tcID = "TC_VALIDATE_APPLIED_FILTER_FOR_MULTIPLE_FILTERHEADERS_FOR_CLOSED_PROJECT";
		logInfo("Reading Excel:   "+datapoolPath);
		logInfo("Starting of Test Case : " + tcID );
		Reporter.log(" <b> Testcase<b> "+ "<b><font color='black'><u> Validate Filter Applied on Filter Check Box marked Closed Project Tab </font></b></u>");
		int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath, sheetName, "testCase","TC_VALIDATE_APPLIED_FILTER_FOR_MULTIPLE_FILTERHEADERS_FOR_CLOSED_PROJECT");
			logInfo("Test Case Row No Is: " + tcRowNum);
			// click on closed Project Menu
			Reporter.log("Click on closed projects menu");
			closedProjectPage.clickOnCloseProjectsMenu();
			threadsleep(3000);

			// Click on Filter under closed Project Tab
			Reporter.log("Click on Filter Icon under closed projects menu");
			closedProjectPage.clickOnFilterIconUnderClosedProjectsModule();
			threadsleep(3000);

			// Select project type Filters
			Reporter.log("Select Project Type Filters");
			closedProjectPage.selectaProjectTypeFiltersOnFilterPanel();
			threadsleep(3000);

			// Select Sub vertical type Filters
			Reporter.log("Select Sub Vertical Type Filters");
			closedProjectPage.selectSubVerticalFilterOnFilterPanelForClosedProjects();
			threadsleep(3000);

			// Select Sub vertical head type Filters
			Reporter.log("Select Sub Vertical Head Type Filters");
			closedProjectPage.selectSubVerticalHeadFiltersOnFilterPanelForClosedProjects();
			threadsleep(3000);

			// Validate Whether Records are displayed as per the Project Type Filter
			// Selected
			Reporter.log("Validate whether Records are displayed As per the Filters Selected Under Multiple Filter Headers");
			closedProjectPage.validateWhetherRecordsAreDisplayedAsperTheFiltersSelectedUnderMultipleFiltersHeaders();
			threadsleep(3000);
			Reporter.log("Validated that records are displayed as per the Filters Selected Under Multiple Filter Headers");

			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Throwable e) {
			e.printStackTrace();
			logError("Error occurred during execution of test " + e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Fail("verifyAppliedFilterFromBoxAndDropdownForClosedProject Failed");
		}
		logInfo("End of Test Case : " + tcID );
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

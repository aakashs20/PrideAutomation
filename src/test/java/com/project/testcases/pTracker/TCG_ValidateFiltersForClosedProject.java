package com.project.testcases.pTracker;

import java.io.IOException;
import java.util.ArrayList;
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
		driver = launchbrowser();
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, DELAY);
		controlActions = new ControlActions(driver);
		sAssert = new SoftAssert();
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		op = new Operations(driver);
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		closedProjectPage = new PL_ClosedProjectsPage(driver);
		loginPage.waitForPageLoaded(driver);
		loginPage.TC_Login(driver, uName, uPassword);
		loginPage.TC_ChangeUser(driver, eName);
		commonPage = new CommonPages(driver);

	}
	
	/*
	 * In this test checkboxs in filter popup is checked and validated that it gets listed in Project Types above project table
	 * after unchecking the checkbox ,it should get removed from list .
	 */

	@Test(priority = 5, description = "Validate Filter applied correctly on checking the filter from filter popup")
	public void TC_ValidateFilterDropdown() {
		int tcRowNum = 0;
		try {

			tcRowNum = ExcelUtils.getRowNum(datapoolPath, sheetName, "testCase", "TC_VALIDATE_FILTER_DROPDOWN");
			op.clickElement(newProject.ClosedProjectTabLink);
			wait.until(ExpectedConditions.visibilityOf(newProject.ClosedProjectTabFocused));
			IsTrue(op.isElementDisplay(closedProjectPage.closedProjectFilter), "Closed Project Filter Visible","Closed Project Filter Not Visible");
			op.clickButton(closedProjectPage.closedProjectFilter);
			IsTrue(op.isElementDisplay(closedProjectPage.closedProjectFilterBox),"Closed Project Filter opened after Clicking on filter button ","Closed Project Filter Not opened after Clicking on filter button  ");
			if (op.isElementDisplay(closedProjectPage.closedProjectFilterClearButton))
			op.click(closedProjectPage.closedProjectFilterClearButton);
			op.clickElement(closedProjectPage.closedProjectFilterMenuCheckBox.get(0));
			Equals(op.getText(closedProjectPage.closedProjectAppliedFilters.get(0)), "Fixed Price","Fixed Price listed inside Applied Filter List","Fixed Price did not list inside Applied Filter List");
			threadsleep(2000);
			System.out.println(op.getText(closedProjectPage.closedProjectFilterMenuCheckBox.get(1)));
			System.out.println(closedProjectPage.closedProjectFilterMenuCheckBox.get(1).isEnabled());
			WebElement Staffing = driver.findElement(By.xpath("//*[@id='fccp_fr_0_1']"));
			op.javascriptclick(driver, Staffing);
			threadsleep(2000);
			op.clickElement(closedProjectPage.closedProjectFilterMenuCheckBox.get(2));
			threadsleep(2000);
			Equals(op.getText(closedProjectPage.closedProjectAppliedFilters.get(2)), "Support Project","Support Project listed inside Applied Filter List","Support Project not listed inside Applied Filter List");
			op.clickElement(closedProjectPage.closedProjectFilterMenuCheckBox.get(3));
			threadsleep(2000);
			op.javascriptclick(driver, closedProjectPage.closedProjectFilterMenuCheckBox.get(1));
			threadsleep(2000);
			// uncheck Staffing
			op.clickElement(closedProjectPage.closedProjectFilterMenuCheckBox.get(2));
			threadsleep(2000);
			Equals(op.getText(closedProjectPage.closedProjectAppliedFilters.get(2)), "Time and Material","After Unchecking listed filter did not changed place in applied filter List","After Unchecking listed filter changed place in applied filter List");
			op.clickElement(driver, "//div[@class='ui-widget-overlay ui-front']");
			threadsleep(2000);
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Throwable e) {
			e.printStackTrace();
			logError("Error occurred during execution of test " + e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Fail("TC_ValidateFilterDropdown failled ");
		}
	}

	
	/*this test is to verify that afer selection of Fixed Price Filter
	 *all the row in below table should only list Fixed Price.
	 *i.e Project Type column of all row should be Fixed Price.
	 */

	@Test(priority = 1, description = "Validate Fixed Price Filter applied on all project for Closed Project Tab")
	public void verifyFixedPriceFilterForClosedProject() {
		int tcRowNum = 0;
		try {

			tcRowNum = ExcelUtils.getRowNum(datapoolPath, sheetName, "testCase","TC_VALIDATE_FIXED_PRICE_FILTER_FOR_CLOSED_PROJECT");
			op.clickElement(newProject.ClosedProjectTabLink);
			wait.until(ExpectedConditions.visibilityOf(newProject.ClosedProjectTabFocused));
			op.clickElement(closedProjectPage.closedProjectTypeSelector.get(0));
			threadsleep(3000);
			commonPage.verifyProjectTypes(closedProjectPage.closedProjectTableProjectType, "Fixed Price");
			IsTrue(op.isElementDisplay(closedProjectPage.closedProjectPagination), "Pagination Is Displayed ","Pagination Not displyed");
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Throwable e) {
			e.printStackTrace();
			logError("Error occurred during execution of test " + e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
		}
	}
	
	/*this test is to verify that afer selection of Support Project Filter
	 *all the row in below table should only list Support Project.
	 *i.e Project Type column of all row should be Support Project
	 */

	@Test(priority = 2, description = "Validate Support Project Filter on Closed Project Tab")
	public void verifySupportProjectFilterForClosedProject() {
		int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath, sheetName, "testCase","TC_VALIDATE_SUPPORT_PROJECT_FILTER_FOR_CLOSED_PROJECT");
			op.clickElement(newProject.ClosedProjectTabLink);
			wait.until(ExpectedConditions.visibilityOf(newProject.ClosedProjectTabFocused));
			op.clickElement(closedProjectPage.closedProjectTypeSelector.get(1));
			threadsleep(3000);
			commonPage.verifyProjectTypes(closedProjectPage.closedProjectTableProjectType, "Support Project");
			IsTrue(op.isElementDisplay(closedProjectPage.closedProjectPagination), "Pagination Is Displayed ","Pagination Not displyed");
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Throwable e) {
			e.printStackTrace();
			logError("Error occurred during execution of test " + e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Fail("verifySupportProjectFilterForClosedProject failed");
		}
	}
	
	/*this test is to verify that afer selection of Staffing Filter
	 *all the row in below table should only list Staffing.
	 *i.e Project Type column of all row should be Staffing
	 */

	@Test(priority = 3, description = "Validate Staffing Filter on Closed Project Tab")
	public void verifyStaffingFilterForClosedProject() {
		int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath, sheetName, "testCase","TC_VALIDATE_STAFFING_FILTER_FOR_CLOSED_PROJECT");
			op.clickElement(newProject.ClosedProjectTabLink);
			wait.until(ExpectedConditions.visibilityOf(newProject.ClosedProjectTabFocused));
			op.clickElement(closedProjectPage.closedProjectTypeSelector.get(2));
			threadsleep(3000);
			commonPage.verifyProjectTypes(closedProjectPage.closedProjectTableProjectType, "Staffing");
			IsTrue(op.isElementDisplay(closedProjectPage.closedProjectPagination), "Pagination Is Displayed ","Pagination Not displyed");
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Throwable e) {
			e.printStackTrace();
			logError("Error occurred during execution of test " + e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Fail("verifyStaffingFilterForClosedProject failed");
		}
	}
	
	/*this test is to verify that afer selection of Time And MaterialFilter
	 *all the row in below table should only list Time And Material.
	 *i.e Project Type column of all row should be Time And Material
	 */

	@Test(priority = 4, description = "Validate Time And Material Filter on Closed Project Tab")
	public void verifyTimeAndMaterialFilterForClosedProject() {
		int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath, sheetName, "testCase","TC_VALIDATE_TIME_AND_MATERIAL_FILTER_FOR_CLOSED_PROJECT");
			op.clickElement(newProject.ClosedProjectTabLink);
			wait.until(ExpectedConditions.visibilityOf(newProject.ClosedProjectTabFocused));
			op.clickElement(closedProjectPage.closedProjectTypeSelector.get(3));
			threadsleep(3000);
			commonPage.verifyProjectTypes(closedProjectPage.closedProjectTableProjectType, "Time and Material");
			IsTrue(op.isElementDisplay(closedProjectPage.closedProjectPagination), "Pagination Is Displayed ","Pagination Not displyed");
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Throwable e) {
			e.printStackTrace();
			logError("Error occurred during execution of test " + e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
	Fail("verifyTimeAndMaterialFilterForClosedProject Failed");
		}
	}
	
	
	/*
	 * this test case verifies that if user selects any filter from displayed menu , corresponding checkbox should be checked in filter popup. 
	 */

	@Test(priority = 7, description = "Validate Filter Applied on Filter Check Box marked Closed Project Tab")
	public void verifyAppliedFilterFromBoxAndDropdownForClosedProject() {
		int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath, sheetName, "testCase","TC_VALIDATE_APPLIED_FILTER_AND_CHECKBOX_MARKED_FOR_CLOSED_PROJECT");
			op.clickElement(newProject.ClosedProjectTabLink);
			wait.until(ExpectedConditions.visibilityOf(newProject.ClosedProjectTabFocused));
			int counter=0;
			for (int i = 0; i < closedProjectPage.closedProjectTypeSelector.size(); i++) {
				logInfo("Filter Number" + i);
				IsTrue(commonPage.verifyFilterCheckBox(closedProjectPage.closedProjectTypeSelector.get(i),closedProjectPage.closedProjectFilterMenuCheckBox, closedProjectPage.closedProjectFilter),"Applied Filter is Checked in filter Box", "Applied Filter is not Checked in filter Box");
				op.clickElement(driver, "//div[@class='ui-widget-overlay ui-front']");
				counter++;
			}
			if(counter== closedProjectPage.closedProjectTypeSelector.size())
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Throwable e) {
			e.printStackTrace();
			logError("Error occurred during execution of test " + e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Fail("verifyAppliedFilterFromBoxAndDropdownForClosedProject Failed" );
		}
	}
	
	/*
	 * This test case verifies that if user selects multiple checkboxes from filter popup then project table's project type 
	 * column should contain only selected option from popup.
	 */

	@Test(priority = 6, description = "verify Project Types for multiple Filter Applied ")
	public void verifyProjectTypeForMultipleFilter() {
		int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath, sheetName, "testCase","TC_VALIDATE_PROJECT_TYPE_FOR_MULTIPLE_FILTER_FOR_CLOSED_PROJECT");
			op.clickElement(newProject.ClosedProjectTabLink);
			wait.until(ExpectedConditions.visibilityOf(newProject.ClosedProjectTabFocused));
			op.clickButton(closedProjectPage.closedProjectFilter);
			threadsleep(3000);
			if (op.isElementDisplay(closedProjectPage.closedProjectFilterClearButton))
			op.click(closedProjectPage.closedProjectFilterClearButton);
			threadsleep(3000);
			op.clickElement(closedProjectPage.closedProjectFilterMenuCheckBox.get(0));
			op.clickElement(closedProjectPage.closedProjectFilterMenuCheckBox.get(2));
			List<String> projectTypes = new ArrayList<>();
			projectTypes.add(op.getText(closedProjectPage.closedProjectFilterMenuCheckBox.get(0)).replaceAll("[0-9]", ""));
			projectTypes.add(op.getText(closedProjectPage.closedProjectFilterMenuCheckBox.get(2)).replaceAll("[0-9]", ""));
			op.clickElement(driver, "//div[@class='ui-widget-overlay ui-front']");
			threadsleep(2000);
			int counter=0;
			for (int rowNumber = 0; rowNumber < closedProjectPage.closedProjectTableProjectType.size(); rowNumber++) 
			{
				IsTrue(projectTypes.contains(op.getText(closedProjectPage.closedProjectTableProjectType.get(rowNumber))),"Filter applied to Row Number " + rowNumber + 1,"Filter did not applied to Row Number " + rowNumber + 1);
				counter++;
			}
			if(counter==closedProjectPage.closedProjectTableProjectType.size());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Throwable e) {
			e.printStackTrace();
			logError("Error occurred during execution of test " + e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Fail("verifyProjectTypeForMultipleFilter failed");
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

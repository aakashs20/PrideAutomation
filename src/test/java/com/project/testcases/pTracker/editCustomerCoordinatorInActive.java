package com.project.testcases.pTracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.CommonPages;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;
import com.project.utilities.WebTable;

public class editCustomerCoordinatorInActive extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	Operations op;
	CommonPages cp;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";
	String workspace = System.getProperty("user.dir");
	String datapoolPath = workspace + "\\test-data-files\\UI-TestData\\TC_ADD_CUSTOMER_COORDINATOR.xlsx";

	@BeforeMethod
	public void groupInit() throws Exception {
		driver = launchbrowser();
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		controlActions = new ControlActions(driver);
		op = new Operations(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		cp = new CommonPages(driver);
		loginPage.waitForPageLoaded(driver);
		loginPage.TC_Login(driver, uName, uPassword);
		threadsleep(2000);
		loginPage.TC_ChangeUser(driver, eName);
	}

	@Test(priority = 1, groups = { "endtoend" }, description = "editCustomerCoordinatorInActive" , dataProvider = "readDataFromExcel")
	public void TC_editInActive(String ProjectName, String CoordinatorName, String ProjectRole, String Designation,
			String EmailAddress, String Remark) throws IOException, Exception {
		op.actionSendKeys(cp.Search_project, ProjectName);
		logInfo("project number successfully entered in search project box: ");
		op.clickButton(cp.go_button);
		logInfo("Successfully clicked on go button: ");
		threadsleep(1000);
		cp.verifyActiveProject(driver, ProjectName);
		logInfo("Verify active project name is correct: ");
		threadsleep(1000);
		op.click(cp.customer_coordinator);
		logInfo("Successfully clicked on customer_coordinator: ");
		threadsleep(1000);
		op.click(driver.findElement(By.xpath("//a[@title='CoordinatorNameInactive']")));
		threadsleep(1000);
		logInfo("Above iframe");
		op.switchToFrameByIFrameElement(cp.edit_customer_coordinator_iframe_inactive);
		logInfo("Successfully entered edit_customer_coordinator_iframe_inactive:");
		threadsleep(1000);
		op.waitForElementToBeClickable(cp.edit_coordinator_name);
		op.click(cp.edit_coordinator_name);
		op.clearAndSetText(cp.edit_coordinator_name, CoordinatorName);
		logInfo("Coordinator Name edited");
		op.waitForElementToBeClickable(cp.edit_project_role);
		op.click(cp.edit_project_role);
		op.clearAndSetText(cp.edit_project_role, ProjectRole);
		logInfo("Project Role edited");
		threadsleep(1000);
		op.clearAndSetText(cp.edit_designation, Designation);
		logInfo("Designation edited");
		threadsleep(1000);
		op.clearAndSetText(cp.edit_email_address, EmailAddress);
		logInfo("Email Address edited");
		threadsleep(1000);
		logInfo("waiting for click on inactive radio button");
		op.waitForElementToBeClickable(cp.inactive_button);
		op.click(cp.inactive_button);
		logInfo("clicked successfully on Inactive radio button");
		op.clearAndSetText(cp.edit_remark, Remark);
		logInfo("Reamrk edited");
		threadsleep(1000);
		op.waitForElementToBeClickable(cp.save_changes_button);
		op.click(cp.save_changes_button);
		logInfo("Save Changes Button clicked");
		threadsleep(1000);
		logInfo("Test Case for Edit Customer Coordinator InActive successfully passed");

	}
	
	@DataProvider
	public Object[][] readDataFromExcel() throws Exception {
		int tcRowNum = ExcelUtils.getRowNumXSSF(datapoolPath, "editInactive", "TestCaseName","TC_EDIT_CUSTOMER_COORDINATOR_INACTIVE");
		System.out.println(tcRowNum);
		Object[][] testObjArray = ExcelUtils.getTableArray(System.getProperty("user.dir") + "\\test-data-files\\UI-TestData\\TC_ADD_CUSTOMER_COORDINATOR.xlsx",
		"editInactive", tcRowNum - 1);
		for (Object[] obj : testObjArray) {
			for (Object str : obj) {
				System.out.println(str);
	}
}
		return (testObjArray);
}

	@AfterMethod
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}

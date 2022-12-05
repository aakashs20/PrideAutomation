//addCustomerCoordinatorInActive by using hashmap :- 

package com.project.testcases.pTracker;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.CommonPages;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class addCustomerCoordinatorInactive1 extends TestBase {

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

	@BeforeClass
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

	@Test(priority = 1, groups = { "sanity", "UI" }, description = "addCustomerCoordinatorInActive_PositiveData")
	public void TC_addCustomerCoordinatorInActive_PositiveData() throws IOException, Exception {
		String tcID = "TC_ADD_CUSTOMER_COORDINATOR_POSITIVE_DATA";
		logInfo("Starting of Test Case : " + tcID);
		int tcRowNum;
		int header = 0; // Excel first row is 0
		String sheetName = "Inactive";
		String ProjectName;
		String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace + "\\test-data-files\\UI-TestData\\TC_ADD_CUSTOMER_COORDINATOR.xls";
		tcRowNum = ExcelUtils.getRowNum(datapoolPath, "Inactive", "TestCaseName","TC_ADD_CUSTOMER_COORDINATOR_POSITIVE_DATA");
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum - 1);
		ProjectName = rowData.get("ProjectName").trim();
		logInfo("Test Case Row No Is: " + tcRowNum);
		logInfo("Reading Excel:   "+ datapoolPath);
		String CoordinatorName = rowData.get("CoordinatorName").trim();
		String ProjectRole = rowData.get("ProjectRole").trim();
		String Designation = rowData.get("Designation").trim();
		String EmailAddress = rowData.get("EmailAddress").trim();
		String Remark = rowData.get("Remark").trim();
		op.actionSendKeys(cp.Search_project, ProjectName);
		logInfo("project number successfully entered in search project box: " + ProjectName );
		op.clickButton(cp.go_button);
		logInfo("Successfully clicked on go button: ");
		threadsleep(1000);
		cp.verifyActiveProject(driver, ProjectName);
		logInfo("Verify active project name is correct: "  + ProjectName);
		threadsleep(1000);
		op.click(cp.customer_coordinator);
		logInfo("Successfully clicked on customer_coordinator: ");
		threadsleep(1000);
		op.click(cp.add_coordinator);
		logInfo("Successfully clicked on add_coordinator: ");
		threadsleep(1000);
		logInfo("Above iframe");
		op.switchToFrameByIFrameElement(cp.add_customer_coordinator_iframe);
		logInfo("Successfully entered add_customer_coordinator_iframe:");
		logInfo("coordinator name entry");
		op.waitForElementToBeClickable(cp.coordinator_name);
		op.clearAndSetText(cp.coordinator_name, CoordinatorName);
		logInfo("successfuly entered coordinator name: "   + CoordinatorName);
		threadsleep(2000);
		op.waitForElementToBeClickable(cp.project_role);
		op.click(cp.project_role);
		op.sendKeys(cp.project_role, ProjectRole);
		logInfo("successfuly entered project role: "   + ProjectRole);
		threadsleep(2000);
		op.clearAndSetText(cp.designation, Designation);
		logInfo("successfuly entered designation: "   +  Designation);
		threadsleep(1000);
		op.click(cp.email_address);
		op.clearAndSetText(cp.email_address, EmailAddress);
		logInfo("successfuly entered email address: "   +  EmailAddress);
		threadsleep(2000);
		logInfo("waiting for click on inactive radio button");
		op.waitForElementToBeClickable(cp.inactive_button);
		op.click(cp.inactive_button);
		logInfo("clicked successfully on Inactive radio button");
		op.click(cp.remark);
		controlActions.clearAndSetText(cp.remark, Remark);
		logInfo("successfuly entered remark : " + Remark);
		threadsleep(2000);
		op.clickButton(cp.add_button);
		logInfo("add button clicked successfully");
		logInfo("All the details added successfully for customer coordinator: ");
		op.switchToDefault();
		logInfo("Enter status in excel sheet");
		ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		logInfo("Status entered successfully in excel sheet");
		logInfo("End of Test Case : " + tcID);
	}

//	@Test(priority = 2, groups = { "sanity", "UI" }, description = "TC_ADD_CUSTOMER_COORDINATOR_NEGATIVE_DATA_COORDINATORNAME")
//	public void TC_addCustomerCoordinatorInActive_NegativeData_CoordinatorName() throws IOException, Exception {
//		String tcID = "TC_ADD_CUSTOMER_COORDINATOR_NEGATIVE_DATA_COORDINATORNAME";
//		logInfo("Starting of Test Case : " + tcID );
//		int tcRowNum ;
//		int header=0; //Excel first row is 0
//		String sheetName = "Inactive";
//		String ProjectName ;
//		String workspace = System.getProperty("user.dir");
//		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_ADD_CUSTOMER_COORDINATOR.xls";
//		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Inactive","TestCaseName","TC_ADD_CUSTOMER_COORDINATOR_NEGATIVE_DATA_COORDINATORNAME");
//		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
//		ProjectName = rowData.get("ProjectName").trim();
//		logInfo("Test Case Row No Is: " + tcRowNum);
//		logInfo("Reading Excel:   "+datapoolPath);
//		String CoordinatorName=rowData.get("CoordinatorName").trim();
//		String ProjectRole=rowData.get("ProjectRole").trim();
//		String Designation=rowData.get("Designation").trim();
//		String EmailAddress=rowData.get("EmailAddress").trim();
//		String Remark = rowData.get("Remark").trim();
//		threadsleep(1000);
//		op.click(cp.add_coordinator);
//		logInfo("Successfully clicked on add_coordinator: ");
//		threadsleep(1000);
//		logInfo("Above iframe");
//		op.switchToFrameByIFrameElement(cp.add_customer_coordinator_iframe);
//		logInfo("Successfully entered add_customer_coordinator_iframe:");
//		logInfo("coordinator name entry");
//		op.waitForElementToBeClickable(cp.coordinator_name);
//		op.clearAndSetText(cp.coordinator_name, CoordinatorName);
//		logInfo("successfuly entered coordinator name: "   + CoordinatorName);
//		threadsleep(2000);
//		op.waitForElementToBeClickable(cp.project_role);
//		op.click(cp.project_role);
//		op.sendKeys(cp.project_role, ProjectRole);
//		logInfo("successfuly entered project role: "  +  ProjectRole);
//		threadsleep(2000);
//		op.clearAndSetText(cp.designation, Designation);
//		logInfo("successfuly entered designation: "   +  Designation);
//		threadsleep(1000);
//		op.click(cp.email_address);
//		op.clearAndSetText(cp.email_address, EmailAddress);
//		logInfo("successfuly entered email address: "  +  EmailAddress);
//		threadsleep(2000);
//		logInfo("waiting for click on inactive radio button");
//		op.waitForElementToBeClickable(cp.inactive_button);
//		op.click(cp.inactive_button);
//		logInfo("clicked successfully on Inactive radio button");
//		op.click(cp.remark);
//		controlActions.clearAndSetText(cp.remark, Remark);
//		logInfo("successfuly entered remark : " + Remark);
//		threadsleep(2000);
//		op.clickButton(cp.add_button);
//		threadsleep(1000);
//		logInfo("add button clicked successfully");
//		
//		
//		//Validation for coordinator name
//		String expectedErrorMsg=rowData.get("ErrorMessage").trim();
//		String actualErrorMessage = "Please enter valid Coordinator Name.";
//		Assert.assertEquals(expectedErrorMsg, actualErrorMessage);
//		logInfo("Actual Error Message equals to Expected Error Message");
//		
//		
//		op.clickButton(cp.cancel_button);
//		logInfo("All the details added successfully for customer coordinator: ");
//		op.switchToDefault();
//		logInfo("Enter status in excel sheet");
//		ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
//		logInfo("Status entered successfully in excel sheet");
//		logInfo("End of Test Case : " + tcID);
//	}
	
//	//@Test(priority = 3, groups = { "sanity", "UI" }, description = "TC_ADD_CUSTOMER_COORDINATOR_NEGATIVE_DATA_PROJECTROLE")
//	public void TC_addCustomerCoordinatorInActive_NegativeData_ProjectRole() throws IOException, Exception {
//		String tcID = "TC_ADD_CUSTOMER_COORDINATOR_NEGATIVE_DATA_PROJECTROLE";
//		logInfo("Starting of Test Case : " + tcID );
//		int tcRowNum ;
//		int header=0; //Excel first row is 0
//		String sheetName = "Inactive";
//		String ProjectName ;
//		String workspace = System.getProperty("user.dir");
//		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_ADD_CUSTOMER_COORDINATOR.xls";
//		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Inactive","TestCaseName","TC_ADD_CUSTOMER_COORDINATOR_NEGATIVE_DATA_PROJECTROLE");
//		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
//		ProjectName = rowData.get("ProjectName").trim();
//		logInfo("Test Case Row No Is: " + tcRowNum);
//		logInfo("Reading Excel:   "+datapoolPath);
//		String CoordinatorName=rowData.get("CoordinatorName").trim();
//		String ProjectRole=rowData.get("ProjectRole").trim();
//		String Designation=rowData.get("Designation").trim();
//		String EmailAddress=rowData.get("EmailAddress").trim();
//		String Remark = rowData.get("Remark").trim();
//		threadsleep(1000);
//		op.click(cp.add_coordinator);
//		logInfo("Successfully clicked on add_coordinator: ");
//		threadsleep(1000);
//		logInfo("Above iframe");
//		op.switchToFrameByIFrameElement(cp.add_customer_coordinator_iframe);
//		logInfo("Successfully entered add_customer_coordinator_iframe:");
//		logInfo("coordinator name entry");
//		op.waitForElementToBeClickable(cp.coordinator_name);
//		op.clearAndSetText(cp.coordinator_name, CoordinatorName);
//		logInfo("successfuly entered coordinator name: "  +  CoordinatorName);
//		threadsleep(2000);
//		op.waitForElementToBeClickable(cp.project_role);
//		op.click(cp.project_role);
//		op.sendKeys(cp.project_role, ProjectRole);
//		logInfo("successfuly entered project role: "  + ProjectRole);
//		threadsleep(2000);
//		op.clearAndSetText(cp.designation, Designation);
//		logInfo("successfuly entered designation: "  +  Designation);
//		threadsleep(1000);
//		op.click(cp.email_address);
//		op.clearAndSetText(cp.email_address, EmailAddress);
//		logInfo("successfuly entered email address: "  +  EmailAddress);
//		threadsleep(2000);
//		logInfo("waiting for click on inactive radio button");
//		op.waitForElementToBeClickable(cp.inactive_button);
//		op.click(cp.inactive_button);
//		logInfo("clicked successfully on Inactive radio button");
//		op.click(cp.remark);
//		controlActions.clearAndSetText(cp.remark, Remark);
//		logInfo("successfuly entered remark : " + Remark);
//		threadsleep(2000);
//		op.clickButton(cp.add_button);
//		threadsleep(1000);
//		logInfo("add button clicked successfully");
//		logInfo("All the details added successfully for customer coordinator: ");
//		op.click(cp.cancel_button);
//		op.switchToDefault();
//		logInfo("Enter status in excel sheet");
//		ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
//		logInfo("Status entered successfully in excel sheet");
//		logInfo("End of Test Case : " + tcID);
//	}
//	
//	@Test(priority = 4, groups = { "sanity", "UI" }, description = "TC_ADD_CUSTOMER_COORDINATOR_NEGATIVE_DATA_DESIGNATION")
//	public void TC_addCustomerCoordinatorInActive_NegativeData_Designation() throws IOException, Exception {
//		String tcID = "TC_ADD_CUSTOMER_COORDINATOR_NEGATIVE_DATA_DESIGNATION";
//		logInfo("Starting of Test Case : " + tcID );
//		int tcRowNum ;
//		int header=0; //Excel first row is 0
//		String sheetName = "Inactive";
//		String ProjectName ;
//		String workspace = System.getProperty("user.dir");
//		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_ADD_CUSTOMER_COORDINATOR.xls";
//		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Inactive","TestCaseName","TC_ADD_CUSTOMER_COORDINATOR_NEGATIVE_DATA_DESIGNATION");
//		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
//		ProjectName = rowData.get("ProjectName").trim();
//		logInfo("Test Case Row No Is: " + tcRowNum);
//		logInfo("Reading Excel:   "+datapoolPath);
//		String CoordinatorName=rowData.get("CoordinatorName").trim();
//		String ProjectRole=rowData.get("ProjectRole").trim();
//		String Designation=rowData.get("Designation").trim();
//		String EmailAddress=rowData.get("EmailAddress").trim();
//		String Remark = rowData.get("Remark").trim();
//		threadsleep(1000);
//		op.click(cp.add_coordinator);
//		logInfo("Successfully clicked on add_coordinator: ");
//		threadsleep(1000);
//		logInfo("Above iframe");
//		op.switchToFrameByIFrameElement(cp.add_customer_coordinator_iframe);
//		logInfo("Successfully entered add_customer_coordinator_iframe:");
//		logInfo("coordinator name entry");
//		op.waitForElementToBeClickable(cp.coordinator_name);
//		op.clearAndSetText(cp.coordinator_name, CoordinatorName);
//		logInfo("successfuly entered coordinator name: " + CoordinatorName);
//		threadsleep(2000);
//		op.waitForElementToBeClickable(cp.project_role);
//		op.click(cp.project_role);
//		op.sendKeys(cp.project_role, ProjectRole);
//		logInfo("successfuly entered project role: " + ProjectRole);
//		threadsleep(2000);
//		op.clearAndSetText(cp.designation, Designation);
//		logInfo("successfuly entered designation: " + Designation);
//		threadsleep(1000);
//		op.click(cp.email_address);
//		op.clearAndSetText(cp.email_address, EmailAddress);
//		logInfo("successfuly entered email address: " + EmailAddress);
//		threadsleep(2000);
//		logInfo("waiting for click on inactive radio button");
//		op.waitForElementToBeClickable(cp.inactive_button);
//		op.click(cp.inactive_button);
//		logInfo("clicked successfully on Inactive radio button");
//		op.click(cp.remark);
//		controlActions.clearAndSetText(cp.remark, Remark);
//		logInfo("successfuly entered remark : " + Remark);
//		threadsleep(2000);
//		op.clickButton(cp.add_button);
//		threadsleep(1000);
//		logInfo("add button clicked successfully");
//		logInfo("All the details added successfully for customer coordinator: ");
//		op.click(cp.cancel_button);
//		op.switchToDefault();
//		logInfo("Enter status in excel sheet");
//		ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
//		logInfo("Status entered successfully in excel sheet");
//		logInfo("End of Test Case : " + tcID);
//	}
//	
//	@Test(priority = 5, groups = { "sanity", "UI" }, description = "TC_ADD_CUSTOMER_COORDINATOR_NEGATIVE_DATA_EMAILADDRESS")
//	public void TC_addCustomerCoordinatorInActive_NegativeData_EmailAddress() throws IOException, Exception {
//		String tcID = "TC_ADD_CUSTOMER_COORDINATOR_NEGATIVE_DATA_EMAILADDRESS";
//		logInfo("Starting of Test Case : " + tcID );
//		int tcRowNum ;
//		int header=0; //Excel first row is 0
//		String sheetName = "Inactive";
//		String ProjectName ;
//		String workspace = System.getProperty("user.dir");
//		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_ADD_CUSTOMER_COORDINATOR.xls";
//		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Inactive","TestCaseName","TC_ADD_CUSTOMER_COORDINATOR_NEGATIVE_DATA_EMAILADDRESS");
//		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
//		ProjectName = rowData.get("ProjectName").trim();
//		logInfo("Test Case Row No Is: " + tcRowNum);
//		logInfo("Reading Excel:   "+datapoolPath);
//		String CoordinatorName=rowData.get("CoordinatorName").trim();
//		String ProjectRole=rowData.get("ProjectRole").trim();
//		String Designation=rowData.get("Designation").trim();
//		String EmailAddress=rowData.get("EmailAddress").trim();
//		String Remark = rowData.get("Remark").trim();
//		threadsleep(1000);
//		op.click(cp.add_coordinator);
//		logInfo("Successfully clicked on add_coordinator: ");
//		threadsleep(1000);
//		logInfo("Above iframe");
//		op.switchToFrameByIFrameElement(cp.add_customer_coordinator_iframe);
//		logInfo("Successfully entered add_customer_coordinator_iframe:");
//		logInfo("coordinator name entry");
//		op.waitForElementToBeClickable(cp.coordinator_name);
//		op.clearAndSetText(cp.coordinator_name, CoordinatorName);
//		logInfo("successfuly entered coordinator name: " + CoordinatorName);
//		threadsleep(2000);
//		op.waitForElementToBeClickable(cp.project_role);
//		op.click(cp.project_role);
//		op.sendKeys(cp.project_role, ProjectRole);
//		logInfo("successfuly entered project role: " + ProjectRole);
//		threadsleep(2000);
//		op.clearAndSetText(cp.designation, Designation);
//		logInfo("successfuly entered designation: "  + Designation);
//		threadsleep(1000);
//		op.click(cp.email_address);
//		op.clearAndSetText(cp.email_address, EmailAddress);
//		logInfo("successfuly entered email address: "  + EmailAddress);
//		threadsleep(2000);
//		logInfo("waiting for click on inactive radio button");
//		op.waitForElementToBeClickable(cp.inactive_button);
//		op.click(cp.inactive_button);
//		logInfo("clicked successfully on Inactive radio button");
//		op.click(cp.remark);
//		controlActions.clearAndSetText(cp.remark, Remark);
//		logInfo("successfuly entered remark : " + Remark);
//		threadsleep(2000);
//		op.clickButton(cp.add_button);
//		threadsleep(1000);
//		logInfo("add button clicked successfully");
//		logInfo("All the details added successfully for customer coordinator: ");
//		op.click(cp.cancel_button);
//		op.switchToDefault();
//		logInfo("Enter status in excel sheet");
//		ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
//		logInfo("Status entered successfully in excel sheet");
//		logInfo("End of Test Case : " + tcID);
//	}

	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}

package com.project.testcases.pTracker;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.NewProjectEndToEndApproval;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class TCG_ProjectRejectedByPMO extends TestBase{

	WebDriverWait wait;
	public String uName = "abc";
	public String uPassword = "xyz";
	String projectName;
	String initialProjectState;
	String expectedProjectState;
	String testDataExcelFileName;
	String sheetName = "Automation";
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	Operations op ;
	WebDriver driver;
	SoftAssert sAssert ;
	String rolePMO = "PMODesk"; 
	String roleDH = "Release 12 - Project Management";
	// Prepare the path of excel file
	String workspace = System.getProperty("user.dir");
	int header=0;
	int tcRow; 
	NewProjectEndToEndApproval epa = new NewProjectEndToEndApproval();

	@BeforeMethod(enabled = true)
	public void launchBrowser() throws Exception
	{
		boolean isLoginSuccess = epa.groupInit();
		IsTrue(isLoginSuccess, "Login Success and Dashboard ICON is visible", "Login Failed and Dashboard ICON not visible");
	}

	@Test(priority = 1,groups = {"endtoend"}, description = "Perfrom Pending Approve By PMO")
	public void testRejectPendingByPMO() throws InterruptedException{
		int tcRowNum = 0; 
		sAssert = new SoftAssert();
		String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_END_TO_END_FIXED_PRICE_PROJECT.xls";
		try {

			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_REJECT_FIXED_PRICE_PROJECT_BY_PMO");
			HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
			String projectName = rowData.get("ProjectName").trim();
			String approver = rowData.get("ApprovingManager");
			String expectedProjectState=rowData.get("expectedProjectState");
			String initialProjectState=rowData.get("initialProjectState");			
			String approveOrReject=rowData.get("approveOrReject");
			String projectOriginator =rowData.get("ProgramManager");
			String approverRole = "PMO";
			logInfo("Excel RowData : " + rowData);	
			logInfo("Test Case Row No Is: " + tcRowNum);
			logInfo("Reading Excel:   "+ datapoolPath);
			logInfo("Searching for the Project : " + projectName);
			logInfo("Project Approver is : "+approver);
			logInfo("Expected Project State is : "+expectedProjectState);
			logInfo("Initial Project State is : "+initialProjectState);
			logInfo("Project Approve Or Reject? : "+approveOrReject);

			threadsleep(10000);
			epa.changeUser(approver);
			epa.openPride(rolePMO);

			epa.approveRejectByManagerDH(projectName, initialProjectState, approveOrReject ,approverRole);
			threadsleep(5000);
			epa.clickOnHomeButton();
			threadsleep(5000);
			epa.changeUser(projectOriginator);
			epa.openPride(roleDH);
			Boolean isProjectStateMatched = epa.assertApproveRejectByManager(projectName, expectedProjectState,approverRole);

			if(isProjectStateMatched) {
				logInfo("Test was Successfully ");
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
			}else {
				logInfo("Test was Failed ");
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			}
			sAssert.assertAll();

		} catch (Exception e) {
			logInfo("Test was Failed ");
			e.printStackTrace();
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Assert.fail();
		}finally {
			epa.closeBrowser();
		}
	}

}

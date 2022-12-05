package com.project.testcases.pTracker;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;
import com.project.pageobjects.pTracker.NewProjectEndToEndApproval;
public class TCG_ProjectApproveRejectByDH extends TestBase{
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
	// Prepare the path of excel file
	String workspace = System.getProperty("user.dir");
	int header=0;
	//private HashMap<String, String> rowData;
	int tcRow; 
	//int headerRow;
	
	NewProjectEndToEndApproval epa = new NewProjectEndToEndApproval();
	
	@BeforeMethod(enabled = true)
	public void launchBrowser() throws Exception
	{
		boolean isLoginSuccess = epa.groupInit();
		IsTrue(isLoginSuccess, "Login Success and Dashboard ICON is visible", "Login Failed and Dashboard ICON not visible");
	}

	//@Test(priority = 1,groups = {"endtoend"}, description = "Perfrom Pending Approve By DH")
	public void testApprovePendingByDH(){
		int tcRowNum; 
		try {
			    String workspace = System.getProperty("user.dir");
				String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_END_TO_END_FIXED_PRICE_PROJECT.xls";
				tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_APPROVE_FIXED_PRICE_PROJECT_BY_DH");
				HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
				logInfo("Excel RowData : " + rowData);	
				String projectName = rowData.get("ProjectName").trim();
				String approver = rowData.get("ApprovingManager");
				logInfo("Test Case Row No Is: " + tcRowNum);
				logInfo("Reading Excel:   "+ datapoolPath);
				logInfo("Searching for the Project : " + projectName);
				logInfo("Project Approver is : "+approver);
			
//			    testDataExcelFileName="TC_END_TO_END_FIXED_PRICE_PROJECT.xls";
//				String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\"+testDataExcelFileName;
//				int tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCaseID","TC_APPROVE_FIXED_PRICE_PROJECT_BY_DH");
//				logInfo("Test Case Row No Is: " + tcRowNum);
//				logInfo("Reading Excel:   "+datapoolPath);
				//first row is 0 while reading excel,so 1 is subtracted	
				//tcRow=tcRowNum-1;
				//header row just above testcase data
				//logInfo("Test Case Row No Is: " + tcRow);
				//rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRow);
				//String projectName=rowData.get("projectName");
				//logInfo(" RowData is  : " + rowData);
				//logInfo("Searching for the Project : " + projectName);
				//NewProjectEndToEndApproval newProjectEndToEndApproval=new NewProjectEndToEndApproval();
				
				epa.changeUser(approver);
				epa.openPride();

				String expectedProjectState=rowData.get("expectedProjectState");
				String initialProjectState=rowData.get("initialProjectState");			
				String approveOrReject=rowData.get("approveOrReject");
				logInfo("Expected Project State is : "+expectedProjectState);
				logInfo("Initial Project State is : "+initialProjectState);
				logInfo("Project Approve Or Reject? : "+approveOrReject);
				
				//epa.approveRejectByManagerDH(projectName, initialProjectState, approveOrReject);
				//epa.assertApproveRejectByManager(projectName, expectedProjectState);	
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test( priority = 2, groups = { "endtoend" }, description = "Perfrom Project Reject By DH")
	public void testRejectPendingByDH(){
		int tcRowNum; 
		try {
			//NewProjectEndToEndApproval newProjectEndToEndApproval=new NewProjectEndToEndApproval();
			//testDataExcelFileName="TC_END_TO_END_FIXED_PRICE_PROJECT.xls";
			//String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\"+testDataExcelFileName;
			//int tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCaseID","TC_REJECT_FIXED_PRICE_PROJECT_BY_DH");
			//rowData=epa.changeUser(testDataExcelFileName,"Automation", header, tcRowNum);
			
		    String workspace = System.getProperty("user.dir");
			String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_END_TO_END_FIXED_PRICE_PROJECT.xls";
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_REJECT_FIXED_PRICE_PROJECT_BY_DH");
			HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
			logInfo("Excel RowData : " + rowData);	
			String projectName = rowData.get("ProjectName").trim();
			String approver = rowData.get("ApprovingManager");
			logInfo("Test Case Row No Is: " + tcRowNum);
			logInfo("Reading Excel:   "+ datapoolPath);
			logInfo("Searching for the Project : " + projectName);
			logInfo("Project Approver is : "+approver);
			
			epa.changeUser(approver);
			epa.openPride();

			String expectedProjectState=rowData.get("expectedProjectState");
			String initialProjectState=rowData.get("initialProjectState");			
			String approveOrReject=rowData.get("approveOrReject");
			logInfo("Expected Project State is : "+expectedProjectState);
			logInfo("Initial Project State is : "+initialProjectState);
			logInfo("Project Approve Or Reject? : "+approveOrReject);
			
			//epa.approveRejectByManagerDH(projectName, initialProjectState, approveOrReject);
			//epa.assertApproveRejectByManager(projectName, expectedProjectState);	
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	@AfterMethod(alwaysRun = true)
	public void closeBrowser(){
		try {
			epa.closeBrowser();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
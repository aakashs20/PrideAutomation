package com.project.testcases.pTracker;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.CommonPages;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

import com.project.utilities.ExcelReader;

public class TC_AddNew_Payment_Milestone extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	Operations op ;
	CommonPages cp;
	private String uName = "abc";
	private String uPassword = "xyz";
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";

	
	@BeforeClass
	public void groupInit() throws Exception {
		driver = launchbrowser();
        driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		controlActions = new ControlActions(driver);
		op = new Operations(driver);
		//controlActions = new ControlActions(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		cp = new CommonPages(driver);
		loginPage.waitForPageLoaded(driver);
		loginPage.TC_Login(driver,uName, uPassword);
		threadsleep(2000);
		loginPage.TC_ChangeUser(driver,eName);
	}

	
	  @Test(priority=1,groups = { "endtoend" }, description ="Add payment Mileston")
	  public void addpaymentMilestoneProject() throws IOException, Exception {
	    String sheetName = "Automation";
		int header=0; //Excel first row is 0
		int tcRowNum; 
	    // Prepare the path of excel file
	    String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase","TC_ADD_NEW_PAYMENT_MILESTONE");
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
		String projectName = rowData.get("ProjectName").trim();
		logInfo("Test Case Row No Is: " + tcRowNum);
		logInfo("Reading Excel:   "+datapoolPath);
		boolean activeResponce = controlActions.waitUntilElementIsClickable(cp.ActiveProjectGoBtn);
		IsTrue(activeResponce, "Go Button on Active Project Tab is visible", "Go Button on Active Project Tab is Not visible");
		threadsleep(1000); 
		logInfo("Searching for the Project : " + projectName);
		String tableXpath = "//*[@id='report_table_projects-active-report']/tbody/tr[1]/td";
		cp.ActiveProjectSearchTxt.clear();
		cp.ActiveProjectSearchTxt.sendKeys("");
		cp.ActiveProjectSearchTxt.sendKeys(projectName);
		cp.ActiveProjectGoBtn.click();
		threadsleep(5000); 
		
	    List<String> searchResult = op.searchReportTable(driver, tableXpath, projectName);
		Assert.assertNotNull(searchResult, "Active Project Search failed");
		//List<String> ElementsList = op.searchReportTable(driver, tableXpath, projectName);
		 
		logInfo("Table Rows Count is: " + searchResult.size());
		logInfo("Project Number : " + searchResult.get(0));
		logInfo("Project Name : " + searchResult.get(1));
		logInfo("Project State : " + searchResult.get(10));
		Assert.assertEquals(searchResult.get(10), "Active", searchResult.get(1) + "is not an Active Project" );
		String projectID = "//*[@id='report_table_projects-active-report']/tbody/tr[1]//a[contains(text(),'"+searchResult.get(0)+"')]";
		WebElement element  = driver.findElement(By.xpath(projectID));
		element.click();
		threadsleep(10000);
		boolean isProjectOpened = controlActions.isElementDisplayedOnPage(cp.ManagerDetailsTab);
		IsTrue(isProjectOpened, "Selected Project Opend Successfully", "Failed to Open Selected Project");
		 
		cp.ManagerDetailsTab.click();
		cp.paymentMilestoneDetailsTab.click();
		 
		boolean isPaymentMilestoneDetailsTabOpened = controlActions.isElementDisplayedOnPage(cp.addPaymentMilestone);
		IsTrue(isPaymentMilestoneDetailsTabOpened, "Open Payment Milestone Details Tab", "Failed to Open Payment Milestone Details Tab");
		cp.addPaymentMilestone.click();
		 
		String milestoneName=rowData.get("MilestoneName").trim();
		String Amount=rowData.get("Amount").trim();
		String milestoneStartDate=rowData.get("ProjectStartDate").trim();
		String milestoneEndDate=rowData.get("ProjectEndDate").trim();
		boolean paymentMilestone=cp.addPaymentMilestone(driver, milestoneName, Amount, tcRowNum);
		IsTrue(paymentMilestone, "added Payment Milestone Details", "Failed to add Payment Milestone Details");
		 	
		String paymentMilestoneNamevfnameVerify = cp.paymentMilestoneNameVerify();
		String paymentMilestoneStartDatevfnameVerify = cp.paymentMilestoneStartDateVerify();
		String paymentMilestoneEndDatevfnameVerify = cp.paymentMilestoneEndDateVerify();
		String paymentMilestoneAmountvfnameVerify = cp.paymentMilestoneAmountVerify();
		
		Equals(paymentMilestoneNamevfnameVerify, milestoneName, "Milestone Name is Verify", "Milestone Name is not Verify");
		Equals(paymentMilestoneStartDatevfnameVerify, milestoneStartDate, "Milestone StartDate is Verify", "Milestone StartDate is not Verify");
		Equals(paymentMilestoneEndDatevfnameVerify, milestoneEndDate, "Milestone EndDate is Verify", "Milestone EndDate is not Verify");
		Equals(paymentMilestoneAmountvfnameVerify, Amount, "Milestone Amount is Verify", "Milestone Amount is not Verify");

		if(cp.paymentMilestoneNameDisplay() && cp.paymentMilestoneStartDateDisplay() && cp.paymentMilestoneEndDateDisplay() && cp.paymentMilestoneAmountDisplay())
		{
			logInfo("Payment milestone added Successfully."); 
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		}
		else
		{
			 logError("Fail to add Payment milestone."); 
			 ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
		}
	  }
	  
	  @Test(priority=2,groups = { "endtoend" }, description ="Modify payment Mileston")
	  public void modifyPaymentMilestoneProject() throws IOException, Exception {
		String sheetName = "Automation";
		int header=0; //Excel first row is 0
		int tcRowNum; 
		// Prepare the path of excel file
		String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase","MODIFY_PAYMENT_MILESTONE");
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);

		String AmountModify=rowData.get("Amount").trim();
		String milestoneStartDate=rowData.get("ProjectStartDate").trim();;
		String milestoneEndDate=rowData.get("ProjectEndDate").trim();
		logInfo("Milestone modified Start Date is "+milestoneStartDate); 
		logInfo("Milestone modified End Date is "+milestoneEndDate); 
		boolean paymentMilestone=cp.modifyPaymentMilestone(driver, AmountModify, tcRowNum);
		IsTrue(paymentMilestone, "added Payment Milestone Details", "Failed to add Payment Milestone Details");
		
		String paymentMilestoneStartDatevfnameVerify = cp.paymentMilestoneStartDateVerify();
		String paymentMilestoneEndDatevfnameVerify = cp.paymentMilestoneEndDateVerify();
		String paymentMilestoneAmountvfnameVerify = cp.paymentMilestoneAmountVerify();
		
		Equals(paymentMilestoneStartDatevfnameVerify, milestoneStartDate, "Milestone StartDate is Verify", "Milestone StartDate is not Verify");
		Equals(paymentMilestoneEndDatevfnameVerify, milestoneEndDate, "Milestone EndDate is Verify", "Milestone EndDate is not Verify");
		Equals(paymentMilestoneAmountvfnameVerify, AmountModify, "Milestone Amount is Verify", "Milestone Amount is not Verify");
			
		if(cp.paymentMilestoneNameDisplay() && cp.paymentMilestoneStartDateDisplay() && cp.paymentMilestoneEndDateDisplay() && cp.paymentMilestoneAmountDisplay())
		{
			logInfo("Payment milestone modified Successfully."); 
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		}
		else
		{
			 logError("Fail to modified Payment milestone."); 
			 ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
		}
	  
	  }
	
	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}

package com.project.testcases.pTracker.proSubDetails;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.Constants;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.CommonPages;
import com.project.pageobjects.pTracker.DeliveryMilestonePage;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.pageobjects.pTracker.PaymentMilestonePage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class TCG_AddUpdateDeletePaymentMilestone extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	Operations op ;
	CommonPages cp;
	PaymentMilestonePage pmp;
	DeliveryMilestonePage dmp;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";
	private String milestoneName;
	private String searchProjectName;

	
	@BeforeClass
	public void groupInit() throws Exception {
		prop = new Properties();
		prop.load(new FileInputStream(Constants.configFile));
		searchProjectName = prop.getProperty("projectName");
		driver = launchbrowser();
        driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		controlActions = new ControlActions(driver);
		op = new Operations(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		cp = new CommonPages(driver);
		pmp = new PaymentMilestonePage(driver);
		dmp = new DeliveryMilestonePage(driver);
		loginPage.waitForPageLoaded(driver);
		loginPage.TC_Login(driver,uName, uPassword);
		threadsleep(2000);
		loginPage.TC_ChangeUser(driver,eName);
	}

	
	  @Test(priority=1,groups = {"sanity","UI"}, description ="Add payment Mileston")
	  public void addpaymentMilestone() throws IOException, Exception {
		String tcID = "TC_ADD_NEW_PAYMENT_MILESTONE";
		logInfo("Starting of Test Case : " + tcID );
	    String sheetName = "Automation";
		int header=0; //Excel first row is 0
		int tcRowNum; 
	    // Prepare the path of excel file
	    String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
		//String projectName = rowData.get("ProjectName").trim();
		String projectName = searchProjectName;
		boolean activeResponce = controlActions.waitUntilElementIsClickable(cp.ActiveProjectGoBtn);
		IsTrue(activeResponce, "Go Button on Active Project Tab is visible", "Go Button on Active Project Tab is Not visible");
		threadsleep(1000); 
		String tableXpath = "//*[@id='report_table_projects-active-report']/tbody/tr[1]/td";
		cp.ActiveProjectSearchTxt.clear();
		cp.ActiveProjectSearchTxt.sendKeys("");
		cp.ActiveProjectSearchTxt.sendKeys(projectName);
		cp.ActiveProjectGoBtn.click();
		threadsleep(5000); 
	    List<String> searchResult = op.searchReportTable(driver, tableXpath, projectName);
		Assert.assertNotNull(searchResult, "Active Project Search failed");
		Assert.assertEquals(searchResult.get(9), "Active", searchResult.get(1) + "is not an Active Project" );
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
		String readonly = cp.addPaymentMilestonebtn.getAttribute("class");
		logInfo("IS Add Payment Milestone Button is ReadOnly? " + readonly.contains("disable_button"));
		if (!readonly.contains("disable_button")) 
		{ 
			cp.addPaymentMilestone.click();
			 
			milestoneName=rowData.get("MilestoneName").trim();
			String amount=rowData.get("Amount").trim();
			String milestoneStartDate=rowData.get("ProjectStartDate").trim();
			String milestoneEndDate=rowData.get("ProjectEndDate").trim();
			
//			int randomNum = op.generateRandomNum();
//			milestoneName = milestoneName + randomNum;
			
			boolean paymentMilestone=cp.addPaymentMilestone(driver, milestoneName, amount, tcRowNum);
			IsTrue(paymentMilestone, "Payment Milestone Details Added Successfully", "Failed to add Payment Milestone Details");
			logInfo("Is Payment Milestone Details Added Successfully? " + paymentMilestone); 
//			String paymentMilestoneNamevfnameVerify = cp.paymentMilestoneNameVerify();
//			String paymentMilestoneStartDatevfnameVerify = cp.paymentMilestoneStartDateVerify();
//			String paymentMilestoneEndDatevfnameVerify = cp.paymentMilestoneEndDateVerify();
//			String paymentMilestoneAmountvfnameVerify = cp.paymentMilestoneAmountVerify();
			
			threadsleep(2000);
			String milestoneStatus="Open";
			boolean isMilestoneAdded= dmp.verifyPayemtMilestoneDetails(driver, milestoneName,milestoneStatus);
			IsTrue(isMilestoneAdded, "Added Payment Milestone Details", "Failed to add Payment Milestone Details");
			
//			Equals(paymentMilestoneNamevfnameVerify, milestoneName, "Milestone Name is Verify", "Milestone Name is not Verify");
//			Equals(paymentMilestoneStartDatevfnameVerify, milestoneStartDate, "Milestone StartDate is Verify", "Milestone StartDate is not Verify");
//			Equals(paymentMilestoneEndDatevfnameVerify, milestoneEndDate, "Milestone EndDate is Verify", "Milestone EndDate is not Verify");
//			Equals(paymentMilestoneAmountvfnameVerify, amount, "Milestone Amount is Verify", "Milestone Amount is not Verify");
//	
//			if(cp.paymentMilestoneNameDisplay() && cp.paymentMilestoneStartDateDisplay() && cp.paymentMilestoneEndDateDisplay() && cp.paymentMilestoneAmountDisplay())
			if(isMilestoneAdded)
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
		else
		{
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "SKIP", "YELLOW");
			throw new SkipException("Skipping Test Exception");
		}
		logInfo("End of Test Case : " + tcID );
	  }
	  
	  @Test(priority=2,dependsOnMethods = { "addpaymentMilestone"}, groups = { "sanity","UI" }, description ="Modify Payment Milestone")
	  public void modifyPaymentMilestone() throws IOException, Exception {
		String tcID = "TC_MODIFY_PAYMENT_MILESTONE";
		logInfo("Starting of Test Case : " + tcID );
		String sheetName = "Automation";
		int header=0; //Excel first row is 0
		int tcRowNum; 
		// Prepare the path of excel file
		String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase","TC_MODIFY_PAYMENT_MILESTONE");
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
		String AmountModify=rowData.get("Amount").trim();
		String milestoneStartDate=rowData.get("ProjectStartDate").trim();;
		String milestoneEndDate=rowData.get("ProjectEndDate").trim();
		boolean isPayemtMilestoneEdited = dmp.searchAndEditPayemtMilestone(driver, milestoneName,"edit");
		logInfo("Is Payment Milestone Modified Successfully? -> " + isPayemtMilestoneEdited); 
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
		logInfo("End of Test Case : " + tcID );
	  }
	  
	  
	  @Test(priority=3,dependsOnMethods={"addpaymentMilestone"},groups = {"sanity","UI"}, description ="Delete Payment Mileston")
	  public void deletePaymentMilestoneTest() throws IOException, Exception {
			String tcID = "TC_DELETE_PAYMENT_MILESTONE";
			logInfo("Starting of Test Case : " + tcID );
			String sheetName = "Automation";
			int header=0; //Excel first row is 0
			int tcRowNum; 
			// Prepare the path of excel file
			String workspace = System.getProperty("user.dir");
			String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase","TC_DELETE_PAYMENT_MILESTONE");
			HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
			dmp.searchAndEditPayemtMilestone(driver, milestoneName,"delete");
			threadsleep(1000);
			//pmp.deletePaymentMilestone();
			pmp.verifyPopUpExists();
			pmp.clickOkButton();
			//pmp.getNoDataFoundText();
			threadsleep(3000);
			
			boolean isPayemtMilestoneDeleted=op.isElementDisplayed(dmp.noPaymentMilestoneError);
			//boolean isPayemtMilestoneDeleted=dmp.searchAndEditPayemtMilestone(driver, milestoneName,"search");
			IsTrue(isPayemtMilestoneDeleted, "Payment Milestone Deleted Successfullys", "Failed to Delete Payment Milestone");
			logInfo("Payment milestone Deleted Successfully." + !isPayemtMilestoneDeleted); 
			if(isPayemtMilestoneDeleted)
			{
				logInfo("Payment milestone modified Successfully."); 
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
			}
			else
			{
				 logError("Fail to modified Payment milestone."); 
				 ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			}
			logInfo("End of Test Case : " + tcID );
		  		  
		 // Assert.assertEquals(ep.getErrorText(), "The username and password could not be verified.");
	  }
	  
	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}

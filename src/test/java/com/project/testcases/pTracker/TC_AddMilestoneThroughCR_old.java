package com.project.testcases.pTracker;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.CommonPages;
import com.project.pageobjects.pTracker.DeliveryMilestonePageTab;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class TC_AddMilestoneThroughCR_old extends TestBase{

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	Operations op ;
	CommonPages cp;
	private String uName = "abc";
	private String uPassword = "xyz";
	private static final int DELAY = 10;
	String eName = "Mahajan, Milind";
	String verifyCRNumFromList="";
	String updatedDescription="";
	private boolean duplicateErrorMsg;
	
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
		loginPage.TC_ChangeUser(driver,eName);
	}
	
	@Test
	public void AddMilestoneThroughCR() throws Exception
	{

		String sheetName = "Automation";
		int header=0; //Excel first row is 0
		int tcRowNum;  
		// Prepare the path of excel file
	    String workspace = System.getProperty("user.dir");
	    String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	    tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase","Add_Milestone_Through_CR");
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
		String projectName1 = rowData.get("ProjectName").trim();
		String paymentMilestoneNm = rowData.get("MilestoneName").trim();
		String amount=rowData.get("Amount").trim();
		String milestoneStartDate=rowData.get("ProjectStartDate").trim();
		String milestoneEndDate=rowData.get("ProjectEndDate").trim();
		
		DeliveryMilestonePageTab deliveryMilestonePageObj= new DeliveryMilestonePageTab(driver);		
		deliveryMilestonePageObj.verifyActiveProject(driver,projectName1);
		deliveryMilestonePageObj.deliverMileStoneTab(driver);
		
		boolean paymentMilestoneCR=deliveryMilestonePageObj.addPaymentMilestoneCR(driver, tcRowNum, paymentMilestoneNm,amount);
		
		IsTrue(paymentMilestoneCR, "added Payment Milestone Details", "Failed to add Payment Milestone Details");
		String paymentMilestoneNamevfnameVerify = cp.paymentMilestoneNameVerify();
		String paymentMilestoneStartDatevfnameVerify = cp.paymentMilestoneStartDateVerify();
		String paymentMilestoneEndDatevfnameVerify = cp.paymentMilestoneEndDateVerify();
		String paymentMilestoneAmountvfnameVerify = cp.paymentMilestoneAmountVerify();
		
		Equals(paymentMilestoneNamevfnameVerify, paymentMilestoneNm, "Milestone Name is Verify", "Milestone Name is not Verify");
		Equals(paymentMilestoneStartDatevfnameVerify, milestoneStartDate, "Milestone StartDate is Verify", "Milestone StartDate is not Verify");
		Equals(paymentMilestoneEndDatevfnameVerify, milestoneEndDate, "Milestone EndDate is Verify", "Milestone EndDate is not Verify");
		Equals(paymentMilestoneAmountvfnameVerify, amount, "Milestone Amount is Verify", "Milestone Amount is not Verify");

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
	
	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

	
}

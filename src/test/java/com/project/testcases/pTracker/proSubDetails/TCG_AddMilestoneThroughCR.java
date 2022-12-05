package com.project.testcases.pTracker.proSubDetails;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.Constants;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.CommonPages;
import com.project.pageobjects.pTracker.DeliveryMilestonePage;
import com.project.pageobjects.pTracker.DeliveryMilestonePageTab;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class TCG_AddMilestoneThroughCR extends TestBase{

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	DeliveryMilestonePage dmp;
	DeliveryMilestonePageTab deliveryMilestonePageTabObj;
	Operations op ;
	CommonPages cp;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";
	String verifyCRNumFromList="";
	String updatedDescription="";
	private boolean duplicateErrorMsg;
	private String searchProjectName;
	Random random = new Random(); 
	int ranNo = random.nextInt(99); 
	
	@BeforeClass
	public void groupInit() throws Exception {
		prop = new Properties();
		prop.load(new FileInputStream(Constants.configFile));
		searchProjectName = prop.getProperty("projectName");
		driver = launchbrowser();
        driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		controlActions = new ControlActions(driver);
		dmp = new DeliveryMilestonePage(driver);
		deliveryMilestonePageTabObj= new DeliveryMilestonePageTab(driver);	
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
		String tcID = "TC_ADD_MILESTONE_THROUGH_CR";
		logInfo("Starting of Test Case : " + tcID );
		String sheetName = "Automation";
		int header=0; //Excel first row is 0
		int tcRowNum;  
		// Prepare the path of excel file
	    String workspace = System.getProperty("user.dir");
	    String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	    tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
		//String projectName1 = rowData.get("ProjectName").trim();
		String projectName1 = searchProjectName;
		String paymentMilestoneNm = rowData.get("MilestoneName").trim()+ranNo;
		String amount=rowData.get("Amount").trim();
		String milestoneStartDate=rowData.get("ProjectStartDate").trim();
		String milestoneEndDate=rowData.get("ProjectEndDate").trim();
	
		deliveryMilestonePageTabObj.verifyActiveProject(driver,projectName1);
		deliveryMilestonePageTabObj.deliverMileStoneTab(driver);
		
		boolean paymentMilestoneCR=deliveryMilestonePageTabObj.addPaymentMilestoneCR(driver, tcRowNum, paymentMilestoneNm,amount);
	
		String milestoneStatus="Open";
		cp.waitSwitch(cp.addPaymentMilestonebtn);
		//dmp.verifySuccessMsg();
		threadsleep(2000);
		boolean isMilestoneAdded= dmp.verifyPayemtMilestoneDetails(driver, paymentMilestoneNm,milestoneStatus);
		IsTrue(isMilestoneAdded, "Added Payment Milestone Details", "Failed to add Payment Milestone Details");
		
//		IsTrue(paymentMilestoneCR, "added Payment Milestone Details", "Failed to add Payment Milestone Details");
//		String expectedPaymentMilestoneName = cp.paymentMilestoneNameVerify();
//		String expectedPaymentMilestoneStartDate = cp.paymentMilestoneStartDateVerify();
//		String expectedPaymentMilestoneEndDate = cp.paymentMilestoneEndDateVerify();
//		String expectedPaymentMilestoneAmount = cp.paymentMilestoneAmountVerify();
//		
//		logInfo("expectedPaymentMilestoneName " + expectedPaymentMilestoneName + " expectedPaymentMilestoneStartDate " + expectedPaymentMilestoneStartDate + " expectedPaymentMilestoneEndDate " + expectedPaymentMilestoneEndDate+ " expectedPaymentMilestoneAmount " + expectedPaymentMilestoneAmount ); 
//		logInfo("ActPaymentMilestoneName " + paymentMilestoneNm + " ActmilestoneStartDate " + milestoneStartDate + " ActmilestoneEndDate " + milestoneEndDate + " Actamount" + amount); 
//		Equals(expectedPaymentMilestoneName, paymentMilestoneNm, "Milestone Name is Verify", "Milestone Name is not Verify");
//		Equals(expectedPaymentMilestoneStartDate, milestoneStartDate, "Milestone StartDate is Verify", "Milestone StartDate is not Verify");
//		Equals(expectedPaymentMilestoneEndDate, milestoneEndDate, "Milestone EndDate is Verify", "Milestone EndDate is not Verify");
//		Equals(expectedPaymentMilestoneAmount, amount, "Milestone Amount is Verify", "Milestone Amount is not Verify");
//		if(cp.paymentMilestoneNameDisplay() && cp.paymentMilestoneStartDateDisplay() && cp.paymentMilestoneEndDateDisplay() && cp.paymentMilestoneAmountDisplay())
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
		logInfo("End of Test Case : " + tcID );
	}	
	
	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

	
}

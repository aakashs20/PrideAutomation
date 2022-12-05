package com.project.testcases.pTracker;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
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
import com.project.pageobjects.pTracker.DeliveryMilestoneConstants;
import com.project.pageobjects.pTracker.DeliveryMilestonePageTab;

public class TC_DeliveryMilestoneTab extends TestBase{

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	Operations op ;
	CommonPages cp;
	private String uName = "admin";
	private String uPassword = "admin";
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
	public void createChangeRequest() throws Exception
	{
		DeliveryMilestonePageTab deliveryMilestonePageObj= new DeliveryMilestonePageTab(driver);		
		deliveryMilestonePageObj.verifyActiveProject(driver);
		deliveryMilestonePageObj.deliverMileStoneTab(driver);
		
		deliveryMilestonePageObj.createChangeRequest(driver);
		verifyCRNumFromList= deliveryMilestonePageObj.checkListMilestone(driver);
		controlActions.Equals(verifyCRNumFromList, DeliveryMilestonePageTab.changeRequestNumberStr, "create change request number is found from the list", "incorrect change request is found");
		String tcNam1="TC_CHANGE_REQUEST_OF_DELIVERY_MILESTONE";
		String tcNam2="TC_UPDATE_CHANGE_REQUEST_OF_DELIVERY_MILESTONE";
		String tcStatPass="Pass";
		String tcStatFail="Fail";
		if(verifyCRNumFromList.equalsIgnoreCase(DeliveryMilestonePageTab.changeRequestNumberStr))
		{
			deliveryMilestonePageObj.logTestResult(tcNam1,tcStatPass);
			logInfo("Status set to pass");
		}
		else
		{
			deliveryMilestonePageObj.logTestResult(tcNam1,tcStatFail);
			logError("Status set to Fail");
		}
		
		
		deliveryMilestonePageObj.editDeliveryMilestone(driver);
		updatedDescription = deliveryMilestonePageObj.verifyUpdate(driver);
		controlActions.Equals(updatedDescription,"This my updated value","Description field is updated","Description field is not updated"); // failing
		
		if(verifyCRNumFromList.equalsIgnoreCase(DeliveryMilestonePageTab.changeRequestNumberStr))
		{
			deliveryMilestonePageObj.logTestResult(tcNam2,tcStatPass);
			logInfo("Status set to pass");
		}
		else
		{
			deliveryMilestonePageObj.logTestResult(tcNam2,tcStatFail);
			logError("Status set to Fail");
		}
	}
	
	@Test
	public void dupliateChangeRequestError() throws Exception
	{
		String tcNam="TC_DUPLICATE_CHANGE_REQUEST";
		String tcStatPass="Pass";
		String tcStatFail="Fail";
		DeliveryMilestonePageTab dupErrorObj= new DeliveryMilestonePageTab(driver);
		duplicateErrorMsg=dupErrorObj.createDuplicateChangeRequest(driver);
		controlActions.Equals(duplicateErrorMsg,true,"Duplicate Error message received, Testcase Passed","No Duplicate Error message, Test case Failed");
		if(duplicateErrorMsg)
		{
			dupErrorObj.logTestResult(tcNam, tcStatPass);
			logInfo("Status set to pass");
		}
		else
		{
			dupErrorObj.logTestResult(tcNam, tcStatFail);	
			logError("Status set to fail");
		}
	}
	
	/*
	 * @Test public void verifyUpdateStatusNotStarted(WebDriver driver) throws
	 * Exception { String tcNam="TC_UPDATE_STATUS_NOTSTARTED_CHANGE_REQUEST"; String
	 * tcStatPass="Pass"; String tcStatFail="Fail"; DeliveryMilestonePageTab
	 * statusObj=new DeliveryMilestonePageTab(driver); String
	 * ActualSatus=statusObj.updateNotStarted(driver);
	 * controlActions.Equals(ActualSatus, "Open", "Status message is Open",
	 * "Status message is not Open"); if(ActualSatus.equalsIgnoreCase("Open")) {
	 * statusObj.logTestResult(tcNam, tcStatPass); logInfo("Status set to pass"); }
	 * else { statusObj.logTestResult(tcNam, tcStatFail);
	 * logError("Status set to fail"); } }
	 */
	
	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

	
}

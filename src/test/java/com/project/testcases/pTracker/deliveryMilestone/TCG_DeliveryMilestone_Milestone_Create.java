package com.project.testcases.pTracker.deliveryMilestone;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class TCG_DeliveryMilestone_Milestone_Create extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	DeliveryMilestonePage deliveryMilestonePageObj;
	Properties prop;
	Operations op;
	CommonPages cp;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";
	Constants con;
	private String datapoolPath = System.getProperty("user.dir")+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	private String excelPath = System.getProperty("user.dir")+ "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone_Milestone.xls";
	private String searchProjectNumber ;
	String tcID = "TC_DELIVERY_MILESTONE_CREATE_MILESTONE";
	PropertiesConfiguration config ;

	@BeforeClass
	public void groupInit() throws Exception {
		config = new PropertiesConfiguration(Constants.configFile);  
		prop = new Properties();
		prop.load(new FileInputStream(Constants.configFile));
		searchProjectNumber = prop.getProperty("projectID");
		driver = launchbrowser();
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		controlActions = new ControlActions(driver);
		op = new Operations(driver);
		op.getUrl(prop.getProperty("appl_url_dev"));
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		cp = new CommonPages(driver);
		loginPage.waitForPageLoaded(driver);
		loginPage.TC_Login(driver, uName, uPassword);
		threadsleep(2000);
		loginPage.TC_ChangeUser(driver, eName);
		deliveryMilestonePageObj = new DeliveryMilestonePage(driver);
		deliveryMilestonePageObj.verifyActiveProject(driver, searchProjectNumber);
		deliveryMilestonePageObj.verifyDeliveryMilestoneButton(driver);
	}


	@Test (priority = 1 ,groups = {"sanity", "UI"}, description = "Add Delivery Milestone to new Project")
	public void addDeliveryMilestone() throws Exception {
		logInfo("Starting of Test Case : " + tcID);
		int rowNum = ExcelUtils.getRowNum(excelPath, "Create", "testCase", tcID);
		logInfo("Test case Row NO is : " + rowNum);
		int tc_Row_Num = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
		logInfo("Test Suite test case Row NO is : " + tc_Row_Num);
		try {
			String milestoneId = deliveryMilestonePageObj.createMilestone();
			IsTrue(milestoneId!= null, "Milestone is successfuly created with id: " + milestoneId,"Failed to create milestone");
			threadsleep(2000);
			if(milestoneId!= null)
			{
				logInfo("For Project -> " + searchProjectNumber + " Milestone is successfuly created with id: " + milestoneId );
			    config.setProperty("milestone.id", milestoneId);
			    config.save();
				ExcelUtils.logTestResult(excelPath, "Create", rowNum, "PASS", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "GREEN");
			}
			else
			{
				logInfo("Failed to Create Milestone for project- > " + searchProjectNumber );
				ExcelUtils.logTestResult(excelPath, "Create", rowNum, "FAIL", tcID);
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "FAIL", "RED");
			}
		} catch (Exception e) {
			logInfo("Error while Creating Milestone -> " + e.getMessage() );
			ExcelUtils.logTestResult(excelPath, "Create", rowNum, "FAIL", tcID);
			ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "FAIL", "RED");
		}
		logInfo("End of Test Case : " + tcID);
	}

	@AfterClass
	public void closeBrowser() throws Exception {
			driver.close();
	}

}

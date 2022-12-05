package com.project.testcases.pTracker;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.ActiveGrantedTabPage;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class TCG_Validate_Granted_Tabs_FR extends TestBase {
	
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	ActiveGrantedTabPage dashboardPage;
	WebDriver driver;
	ControlActions controlActions;
	Operations op ;
	public static int DELAY=20;
	ExcelUtils excelUtils;
	int tcRowNum;
	int header=0;
	
	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {
		System.setProperty("webdriver.chrome.silentOutput","true");
		driver = launchbrowser();
		controlActions =new ControlActions(driver);
		op = new Operations(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		IsTrue(true, "PRIDE - Sign In", "PRIDE - Sign In");
	}
	
	@Test(groups = {"sanity", "UI"}, description = "Validate Project Granted Tabs for Finance Representative")
	public void validateFR() throws Exception {
		String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		String sheetName = "Automation";
		loginPage =new PTrackerLoginPage(driver);
		tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_VALIDATE_GRANTED_PROJECT_TAB_FR");
		logInfo("Test Case Row No Is: " + tcRowNum);
		logInfo("Reading Excel:   "+datapoolPath);
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
		loginPage.TC_Login(driver, "admin", "admin");
		String value = rowData.get("FinanceRepresentative");
		//String value=excelUtils.getRowValue(62, 68);
		logInfo(value);
	    loginPage.TC_PTrack_ChangeUser(driver, value);
		loginPage.goToPTrack("");
		boolean searchPTrackProjects = loginPage.searchPTrackProjects(driver,"On Behalf Of"); // Finance
	
		//Active Tab which under FR is to be validated with testcase
		List<String> defaultValue = new ArrayList<String>(Arrays.asList("Active Projects","Closed Projects", "New Projects"));
		dashboardPage =new ActiveGrantedTabPage(driver);
		threadsleep(5000);
		List<WebElement> validateGrantedProjectsTabs = dashboardPage.validateGrantedProjectsTabs(driver, defaultValue);
		List<String> expt = new ArrayList<String>();
		
		for (WebElement webElement : validateGrantedProjectsTabs) {
			String text = webElement.getText();
			expt.add(text);
		}
		boolean changeProjectTab = dashboardPage.changeProjectTab(driver, expt);
		AssertJUnit.assertEquals(true, changeProjectTab);
//		if(changeProjectTab==true) {
//			logInfo("Project Tab is validate succesfull");
//		}
//		else {
//			logError("Project Tab is not validate succesfull");
//		}
		if(changeProjectTab==true) {
			String result="PASS";
			ExcelUtils.setCellData(datapoolPath,"Status",tcRowNum,result,"GREEN");
			logInfo("Granted Project Tabs Validated Successfully");
		}
		else {
			String result="FAIL";
			ExcelUtils.setCellData(datapoolPath,"Status",tcRowNum,result,"RED");
			logInfo("Failed to Validate Granted Project Tabs");
		}
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException, IOException {
		op.closeBrowser(driver);
	}

}

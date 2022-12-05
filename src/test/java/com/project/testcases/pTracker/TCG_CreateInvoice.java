package com.project.testcases.pTracker;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.Constants;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.DeliveryMilestoneConstants;
import com.project.pageobjects.pTracker.DeliveryMilestonePage;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PL_ActiveProjectConstant;
import com.project.pageobjects.pTracker.PL_ActiveProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class TCG_CreateInvoice  extends TestBase{

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	PL_ActiveProjectsPage activeProject;
	DeliveryMilestonePage deliveryMilestonePageObj;
	ControlActions controlActions;
	Operations op ;
	Properties prop;
	private String uName = "admin";
	private String uPassword = "admin";
	private String eName = "Mahajan, Milind";
	private static final int DELAY = 10;
	String InputPaymentMilestone=null;
	String projectNumber=null;
	String invalidText;
	String errorMessageText="Please select payment milestone for invoicing.";
	String partialText;
	String noElementFoundMessage = "No project has been found.";
	String workspace = System.getProperty("user.dir");
	String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	//String inputDataFilePath = workspace+"\\test-data-files\\UI-TestData\\TCG_InvoiceFunctionalityNewProject.xls";
	String sheetName = "Automation";
	String strExpectedErrorMessage;
	int tcRowNum;
	boolean isTestSkiped;
	private String searchProjectNumber;
	String tcID = "TC_CREATE_INVOICE_SCENARIO";

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {
		prop = new Properties();
		prop.load(new FileInputStream(Constants.configFile));
		searchProjectNumber = prop.getProperty("projectID");
		// setting up property to suppress the warning
		System.setProperty("webdriver.chrome.silentOutput","true");
		driver = launchbrowser();
		String currentWindow = driver.getWindowHandle();
		driver.switchTo().window(currentWindow);
		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);		
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, DELAY);
		op = new Operations(driver);
		controlActions = new ControlActions(driver);
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		activeProject = new PL_ActiveProjectsPage(driver);
		deliveryMilestonePageObj = new DeliveryMilestonePage(driver);
		loginPage.waitForPageLoaded(driver);
		//	int tcRowNum = ExcelUtils.getRowNum(inputDataFilePath,"inputData","TestName","TCG_Invoice_second_Functionality");
		//int tcRowNum = ExcelUtils.getRowNum(inputDataFilePath,"inputData","testCase","TC_CREATE_INVOICE_SCENARIO");
		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
		//HashMap<String, String> inputData = ExcelUtils.getTestDataXls(inputDataFilePath, "inputData", 0, tcRowNum-1);
		HashMap<String, String> inputData_02 = ExcelUtils.getTestDataXls(datapoolPath, "Automation", 0, tcRowNum-1);

		//projectNumber = inputData_02.get("ProjectID");
		projectNumber = searchProjectNumber;
		if(!projectNumber.equalsIgnoreCase(null))
		{
		//	partialText = inputData.get("PartialSearchText");
		//	invalidText = inputData.get("InvalidSearchText");
		//errorMessageText = inputData.get("ErrorMessage");
		InputPaymentMilestone = inputData_02.get("MilestoneName");
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		logInfo("Expected Test Data is : " + inputData_02 );
		logInfo("Expected Project ID Number is : " + projectNumber );
		logInfo("Expected Payment Milestone  is : " + InputPaymentMilestone );
		logInfo("Expected Error Message is : " + errorMessageText );
		loginPage.TC_Login(driver,uName, uPassword);
		loginPage.TC_ChangeUser(driver,eName);
		}
	}

	@Test(priority = 1,description = "Validate Search Functionality and Invoice")
	public void verifySearchAndInvoiceForActiveProjects(){
		logInfo("Reading Excel:   "+ datapoolPath);
		logInfo("Test Case Row No Is: " + tcRowNum);
		logInfo("Starting of Test Case : " + tcID );
		try {
//			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase",tcID);
//			logInfo("------------------------ : " + tcRowNum);
			IsTrue(newProject.ActiveProjectTabLink.isDisplayed(),"Active Project Tab is Displayed","New Project Tab not displayed");
			logInfo("Navigate to Active Project Tab");
			wait.until(ExpectedConditions.visibilityOf(activeProject.DownloadBtn));
			wait.until(ExpectedConditions.visibilityOf(activeProject.DownloadBtn));
			wait.until(ExpectedConditions.elementToBeClickable(activeProject.DownloadBtn));
			IsTrue(activeProject.DownloadBtn.isDisplayed(),"Download button is displayed","Download button not displayed");

			op.waitForElementToBeDisplayed(activeProject.SearchProjectTxt);
			op.clickElement(activeProject.SearchProjectTxt);
			activeProject.SearchProjectTxt.sendKeys(projectNumber);
			//op.sendKeys(activeProject.SearchProjectTxt, projectNumber);
			//threadsleep(2000);
			op.clickElement(activeProject.GoBtn);
			threadsleep(5000);
			String projectNumberActual = activeProject.ProjectTableRows.get(0).findElement(By.tagName("td")).getText().trim();
			Boolean isElementCountOne = activeProject.ProjectTableRows.size() == 1 ;
			IsTrue(isElementCountOne ,"Only one element is displayed in the table","Only one element should be displayed in the table");
			Boolean isElementFound = projectNumberActual.equalsIgnoreCase(projectNumber);
			IsTrue(isElementFound,"Searched element is found at the top of the table","Searched element is not found at the top of the table");
			//activeProject.SearchProjectTxt.clear();	
			threadsleep(3000);
			// 01- Click on Project Link
			//String releaseStatus = controlActions.getText(controlActions.performGetElementByXPath(DeliveryMilestoneConstants.ADDED_RELEASE_STATUS.replace("MilestoneIDToBeReplaced", milestoneID)));
			op.clickElement(driver, PL_ActiveProjectConstant.ACTIVE_PROJECT_NUMBER.replace("ProjectNumberToReplace", projectNumber));
			
			//op.click(activeProject.ActiveProjectNumber);
			logInfo("Active Project Number is clicked " + activeProject.ActiveProjectNumber);
			// Mouse Hover Code
			op.mouseHoverScript(driver,activeProject.ActiveProMouseHover);
			logInfo("Mouse Over Action performed "+activeProject.ActiveProMouseHover);
			op.waitUntilElementIsClickable(activeProject.InvoiceButton);
			op.clickLiElementByText(activeProject.Apex_RDX_CONTAINER_UL, "Invoices");
			logInfo("Invoice Button is clicked "+activeProject.Apex_RDX_CONTAINER_UL);
			String readonly = activeProject.RaiseInvoiceButton.getAttribute("class");
			if (!readonly.contains("disable_button")) 
			{
				// Working On Frame Scenario : 1 Verify Error Message
				op.click(activeProject.RaiseInvoiceButton);
				logInfo("RaiseInvoiceButton Button is clicked "+activeProject.RaiseInvoiceButton);
				//op.switchToFrameByLocators(activeProject.Frame_01);
				op.switchToiFrameByXpath(driver, PL_ActiveProjectConstant.FRAME_01);
				logInfo("Frame is switched "+activeProject.Frame_01);
				op.click(activeProject.Request_RaiseInvoice_Button);
				logInfo("Request_RaiseInvoice Button is clicked "+activeProject.Request_RaiseInvoice_Button);
				op.waitImplicitely(driver, 10);
				strExpectedErrorMessage = op.getText(activeProject.Payment_Milestone_Error_Text);
				IsTrue(errorMessageText.equalsIgnoreCase(strExpectedErrorMessage), "Error message is coming as expected", "Error messsage is not displayed or is NOT matching");
				// 02- Click on Cancel Button Verify Visibility of Frame 
				op.click(activeProject.Cancel_Button);
				logInfo("Cancel Button is clicked "+activeProject.Cancel_Button);
				//	To Verify Frame is not Visible after clicking on invoicing
				op.switchToDefaultContent(driver);
				IsTrue(op.checkElementPresent(activeProject.RaiseInvoiceButton),"Raise Invoice Request Frame Closed Successfully","Failed to Close Raise Invoice Request Frame");
				//03- Verify the Payment Milestone Number
				op.click(activeProject.RaiseInvoiceButton);
				logInfo("Raise Invoice Button is clicked "+activeProject.RaiseInvoiceButton);
				threadsleep(2000);
				IsTrue(op.checkElementPresent(activeProject.Frame_01),"Frame is Visible","Frame is not visible");
				op.switchToiFrameByXpath(driver, PL_ActiveProjectConstant.FRAME_01);
				//op.switchToFrameByLocators(activeProject.Frame_01);
				logInfo("Frame is switched "+activeProject.Frame_01);
				threadsleep(2000);
				//op.sendKeys(activeProject.Payment_Milestone, InputPaymentMilestone);
				//activeProject.Payment_Milestone.sendKeys(InputPaymentMilestone);
				op.clickElement(activeProject.Payment_Milestone,driver);
				op.switchToDefaultContent(driver);
				logInfo("Frame is switched to default ");
				threadsleep(2000);
				InputPaymentMilestone = activeProject.Select_Payment_Milestone.getText();
				logInfo("Selected Payment Milestone is -> "+ InputPaymentMilestone);
				//activeProject.Select_Payment_Milestone.click();
				op.clickElement(activeProject.Select_Payment_Milestone,driver);
				logInfo("Select Payment Milestone is clicked "+activeProject.Select_Payment_Milestone);
				//op.switchToFrameByLocators(activeProject.Frame_01);
				op.switchToiFrameByXpath(driver, PL_ActiveProjectConstant.FRAME_01);
				logInfo("Frame is switched "+activeProject.Frame_01);
				op.click(activeProject.Request_RaiseInvoice_Button);
				//op.switchToDefault();
				deliveryMilestonePageObj.verifySuccessMsg();
				logInfo("Request_RaiseInvoice_Button is clicked "+activeProject.Request_RaiseInvoice_Button);
				op.waitImplicitely(driver, 10);
				boolean isInvoiceFound = op.searchTextInList(activeProject.Ptrack_Payment_Milestone,InputPaymentMilestone);
				IsTrue(isInvoiceFound,"Milestone found","Milestone NOT found");
				if(isInvoiceFound) {
					logInfo("For the Payment Milestone -> "+ InputPaymentMilestone +" , invoice is Successfully Created");
					ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
	
				}else {
					logInfo("Failed to Create Invoice for the Payment Milestone -> "+ InputPaymentMilestone);
					ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
				}
			}
			else
			{
					logInfo("Invoice doesn't have any active Milestone and it is readonly -> "+ readonly);
					ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "SKIP", "YELLOW");
					isTestSkiped = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error: Failed to Create Invoice -> "+e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Assert.fail();
		}
		if(isTestSkiped)
		{
			throw new SkipException("Skipping Test Exception");
		}
		logInfo("End of Test Case : " + tcID );
	}

	@AfterClass(alwaysRun = true) public void tearDown() 
	{ 
		try 
		{
			op.closeBrowser(driver);
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace(); 
		}
	}

}

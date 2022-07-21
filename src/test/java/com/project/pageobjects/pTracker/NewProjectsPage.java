package com.project.pageobjects.pTracker;

import java.awt.AWTException;

//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;


import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;


public class NewProjectsPage extends TestBase{
	
	WebDriverWait wait;
	PTrackerLoginPage LoginPage;
	ControlActions controlActions;
	WebDriver driver1 ;
	Operations op ;
	Actions actions;
	
	public static boolean bResult;
	
	public NewProjectsPage(WebDriver driver) {
		driver1 = driver ;
		actions = new Actions(driver1);
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20000);
		controlActions = new ControlActions(driver);
		op = new Operations(driver1);
	}
	
	// Tab Links 
	@FindBy(xpath = NewProjectsPageConstants.NEWPROJECTS_LNK)
	public WebElement NewProjectLink;
	
	@FindBy(xpath = NewProjectsPageConstants.NEW_PROJECTS_TAB_FOCUSED)
	public WebElement NewProjectTabFocused;
	
	@FindBy(xpath = NewProjectsPageConstants.CLOSED_PROJECTS_TAB_LNK)
	public WebElement ClosedProjectTabLink;
	
	@FindBy(xpath = NewProjectsPageConstants.CLOSED_PROJECTS_TAB_FOCUSED)
	public WebElement ClosedProjectTabFocused;
	
	@FindBy(xpath = NewProjectsPageConstants.ACTIVE_PROJECTS_TAB_LNK)
	public WebElement ActiveProjectTabLink;
	
	@FindBy(xpath = NewProjectsPageConstants.ACTIVE_PROJECTS_TAB_FOCUSED)
	public WebElement ActiveProjectTabFocused;
	
	@FindBy(xpath = NewProjectsPageConstants.CREATEPROJECTS_LNK)
	public WebElement CreateProjectLink;
	
	@FindBy(xpath = NewProjectsPageConstants.DRAFTPROJECTS_LNK)
	public WebElement DraftProjectLink;
	
	@FindBy(xpath = NewProjectsPageConstants.PENDINGWITH_DH_LNK)
	public WebElement PendingWithDHLink;
	
	@FindBy(xpath = NewProjectsPageConstants.PENDINGWITH_PMO_LNK)
	public WebElement PendingWithPMOLink;
	
	@FindBy(xpath = NewProjectsPageConstants.PENDINGWITH_FINANCE_LNK)
	public WebElement PendingWithFinanceLink;
	
	@FindBy(xpath = NewProjectsPageConstants.SEARCH_PROJECTS_TXT)
	public WebElement SearchProjectTxt;
	
	@FindBy(xpath = NewProjectsPageConstants.GO_BTN)
	public WebElement GoBtn;
	
	@FindBy(xpath = NewProjectsPageConstants.FILTER_BTN)
	public WebElement FilterBtn;
	
	@FindBy(xpath = NewProjectsPageConstants.DOWNLOAD_BTN)
	public WebElement DownloadBtn;
	
	@FindBy(xpath = NewProjectsPageConstants.NEXTSET_BTN)
	public WebElement NextSetBtn;
	
	@FindBy(xpath = NewProjectsPageConstants.CREATE_FROM_EXISTING_PROJECT_LNK)
	public WebElement CreateFromExitingProjectLink;
	
	@FindBy(xpath = NewProjectsPageConstants.CREATE_NEW_PROJECT_LNK)
	public WebElement CreateNewProjectLink;
	
	@FindBy(xpath = NewProjectsPageConstants.SELECT_FROM_EXITING_DLG)
	public WebElement SelectFromExistingDlg;
	
	@FindBy(xpath = NewProjectsPageConstants.EXITING_PROJECT_LBL)
	public WebElement ExistingProjectLbl;
	
	@FindBy(xpath = NewProjectsPageConstants.SELECT_PROJECT_BTN)
	public WebElement SelectProjectBtn;
	
	@FindBy(css = NewProjectsPageConstants.SELECT_PROJECT_LST)
	public WebElement SelectProjectLst;
	
	@FindBy(xpath = NewProjectsPageConstants.ADD_PROJECT_BTN)
	public WebElement AddProjectBtn;
	
	@FindBy(xpath = NewProjectsPageConstants.PROJECT_SEARCH_TXT)
	public WebElement ProjectSearchTxt;
	
	@FindBy(xpath = NewProjectsPageConstants.SELECT_EXITING_PROJECT_LST)
	public WebElement SelectExitingProjectList;
	
	@FindBy(xpath = NewProjectsPageConstants.NEWPROJECT_PAGE_SAVE_BTN)
	public WebElement NewProjectPageSaveBtn;
	
	@FindBy(xpath = NewProjectsPageConstants.NEWPROJECT_PAGE_SUBMIT_BTN)
	public WebElement NewProjectPageSubmitBtn;
	
	@FindBy(xpath = NewProjectsPageConstants.NEWPROJECT_PAGE_CANCEL_BTN)
	public WebElement NewProjectPageCancelBtn;
	
	@FindBy(xpath = NewProjectsPageConstants.SAVEPROJECT_ALERT_MSG)
	public WebElement SaveProjectAlertMsg;
	
	@FindBy(xpath = NewProjectsPageConstants.PMO_APPROVAL_ALERT_MSG)
	public WebElement PmoApprovalAlertMsg;
	
	@FindBy(xpath = NewProjectsPageConstants.PROJECT_SAVE_ALERT_MSG)
	public WebElement ProjectSaveAlertMsg;
	
	@FindBy(xpath = NewProjectsPageConstants.CLOSE_ALERT_MSG)
	public WebElement CloseAlertMsg;
	
	
	@FindBy(xpath = NewProjectsPageConstants.NEWPROJECT_PAGE_ALERT_MSG)
	public WebElement NewProjectPageAlertMsg;
	
	@FindBy(xpath = NewProjectsPageConstants.NEWPROJECT_PAGE_ALERT_CLOSE_BTN)
	public WebElement NewProjectPageCloseBtn;
	
	@FindBy(xpath = ProjectRegistrationConstants.PROJECT_NAME)
	public WebElement ProjectName;
	
	@FindBy(xpath = ProjectRegistrationConstants.PROJECT_MODE)
	public WebElement ProjectMode;
	
	@FindBy(xpath = ProjectRegistrationConstants.INTERNAL_PROJECT_TYPE)
	public WebElement InternalProjectType;
	
	
	@FindBy(xpath = ProjectRegistrationConstants.PROJECT_MODE_DLG)
	public WebElement ProjectModeDlg;
	
	@FindBy(xpath = ProjectRegistrationConstants.PROJECT_MODE_BTN)
	public WebElement ProjectModeBtn;
	
	@FindBy(xpath = ProjectRegistrationConstants.PROJECT_START_DATE)
	public WebElement ProjectStartDate;
	
	@FindBy(xpath = ProjectRegistrationConstants.PROJECT_END_DATE)
	public WebElement ProjectEndDate;
	
	@FindBy(xpath = ProjectRegistrationConstants.SIGNING_ORGANISATION)
	public WebElement SigningOrganization;
	
	@FindBy(xpath = ProjectRegistrationConstants.SIGNING_LOCATION)
	public WebElement SigningLocation;
	
	@FindBy(xpath = ProjectRegistrationConstants.BUSINESS_UNIT)
	public WebElement BusinessUnit;
	
	@FindBy(xpath = ProjectRegistrationConstants.BUSINESS_UNIT_DLG)
	public WebElement BusinessUnitDlg;
	
	@FindBy(xpath = ProjectRegistrationConstants.LINES_OF_BUSINESS)
	public WebElement LineofBusiness;
	
	@FindBy(xpath = ProjectRegistrationConstants.LINES_OF_BUSINESS_DLG)
	public WebElement LineofBusinessDlg;
	
	@FindBy(xpath = ProjectRegistrationConstants.VERTICAL)
	public WebElement Vertical;
	
	@FindBy(xpath = ProjectRegistrationConstants.OPERATION_OFFICE_LOCATION)
	public WebElement OperationOfficeLocation;
	
	@FindBy(xpath = ProjectRegistrationConstants.OPRATION_LOCATION_CITY)
	public WebElement OperationLocationCity;
	
	@FindBy(xpath = ProjectRegistrationConstants.PROJECT_CATEGORY)
	public WebElement ProjectCategory;
	
	@FindBy(xpath = ProjectRegistrationConstants.PROJECT_TYPE)
	public WebElement ProjectType;
	
	@FindBy(xpath = ProjectRegistrationConstants.PROGRESS_METHOD)
	public WebElement ProgressMethod;
	
	@FindBy(xpath = ProjectRegistrationConstants.PROJECT_CYCLE)
	public WebElement ProjectCycle;
	
	@FindBy(xpath = ProjectRegistrationConstants.ENGAGEMENT_MODEL)
	public WebElement EngagementModel;
	
	@FindBy(xpath = ProjectRegistrationConstants.SALESFORCE_OPPORTUNITY_ID)
	public WebElement SalesforceOpportunityID;

	@FindBy(xpath = ProjectRegistrationConstants.BILLING_METHOD)
	public WebElement BillingMethod;
	
	@FindBy(xpath = ProjectRegistrationConstants.BILLING_END_DAY)
	public WebElement BillingEndDay;
	
	@FindBy(xpath = ProjectRegistrationConstants.BILLING_CYCLE)
	public WebElement BillingCycle;

	@FindBy(xpath = ProjectRegistrationConstants.CAP_HOURS)
	public WebElement CAPHours;

	@FindBy(xpath = ProjectRegistrationConstants.TECHNOLOGY_STACK)
	public WebElement TechnologyStack;
	
	@FindBy(xpath = ProjectRegistrationConstants.XORIANT_NICHE_SKILLS)
	public WebElement XoriantNicheSkills;
	
	@FindBy(xpath = ProjectRegistrationConstants.TECHNOLOGY_DOMAIN)
	public WebElement TechnologyDomain;
	
	@FindBy(xpath = ProjectRegistrationConstants.PLANNED_GM)
	public WebElement PlannedGM;
	
	// Project Information Fields
	@FindBy(xpath = ProjectRegistrationConstants.PROJECT_BRIEF)
	public WebElement ProjectBrief;
	
	@FindBy(xpath = ProjectRegistrationConstants.XORIANT_CONTRIBUTION)
	public WebElement XoriantContribution;

	@FindBy(xpath = ProjectRegistrationConstants.BUSINESS_GOAL)
	public WebElement BusinessGoal;

	@FindBy(xpath = ProjectRegistrationConstants.INVESTMENT_GOAL)
	public WebElement InvestmentGoal;
	
	@FindBy(xpath = ProjectRegistrationConstants.GO_TO_MARKET_PLAN)
	public WebElement GoToMarketPlan;

	// Practice Information Fields
	@FindBy(xpath = ProjectRegistrationConstants.PRACTICE_CONTRIBUTION)
	public WebElement PracticeContribution;
	
	@FindBy(xpath = ProjectRegistrationConstants.PRACTICE_BUSINESS_UNIT)
	public WebElement PracticeBusinessUnit;
	
	@FindBy(xpath = ProjectRegistrationConstants.PRACTICE_LEAD)
	public WebElement PracticeLead;
	
	@FindBy(xpath = ProjectRegistrationConstants.PRACTICE_HEAD)
	public WebElement PracticeHead;

	@FindBy(xpath = ProjectRegistrationConstants.PRACTICE_PROGRAM_MANAGER)
	public WebElement PracticeProgramManager;
	
	// Customer Details Fields
	@FindBy(xpath = ProjectRegistrationConstants.CUSTOMER)
	public WebElement Customer;
	
	@FindBy(xpath = ProjectRegistrationConstants.CUSTOMER_ADDR1)
	public WebElement CustomerAdress1;
	
	@FindBy(xpath = ProjectRegistrationConstants.CUSTOMER_ADDR2)
	public WebElement CustomerAdress2;
	
	@FindBy(xpath = ProjectRegistrationConstants.CUSTOMER_COUNTRY)
	public WebElement CustomerCountry;
	
	@FindBy(xpath = ProjectRegistrationConstants.CUSTOMER_STATE)
	public WebElement CustomerState;
	
	@FindBy(xpath = ProjectRegistrationConstants.CUSTOMER_CITY)
	public WebElement CustomerCity;
	
	@FindBy(xpath = ProjectRegistrationConstants.CUSTOMER_PINCODE)
	public WebElement CustomerPincode;
	
	@FindBy(xpath = ProjectRegistrationConstants.CUSTOMER_POINT_OF_CONTACT)
	public WebElement CustomerContact;
	
	@FindBy(xpath = ProjectRegistrationConstants.CUSTOMER_PHONE)
	public WebElement CustomerPhone;
	
	@FindBy(xpath = ProjectRegistrationConstants.CUSTOMER_EMAIL)
	public WebElement CustomerEmail;

	// SOW Fields
	
	//Customer Coordinator
	
	
	//Attachments
	@FindBy(xpath = ProjectRegistrationConstants.UPLOAD_BTN)
	public WebElement UploadBtn;
	
	@FindBy(xpath = ProjectRegistrationConstants.ATTACHMENT_DLG_TXT)
	public WebElement AttachmentDlgTxt;
	
	@FindBy(xpath = ProjectRegistrationConstants.ATTACHMENT_DLG)
	public WebElement AttachmentDlg;
	
	@FindBy(xpath = ProjectRegistrationConstants.FILE_TYPE_LST)
	public WebElement FileTypeLst;
	
	@FindBy(xpath = ProjectRegistrationConstants.BROWSE_FILE)
	public WebElement BrowseFile;
	
	@FindBy(xpath = ProjectRegistrationConstants.UPLOAD_ATTACHMENT_BTN)
	public WebElement UploadAttachmentBtn;
		
	@FindBy(xpath = ProjectRegistrationConstants.CANCEL_BTN)
	public WebElement CancelBtn;
	
	// Manager Details Fields
	@FindBy(xpath = ProjectRegistrationConstants.PROJECT_SPONSOR)
	public WebElement ProjectSponsor;
	
	@FindBy(xpath = ProjectRegistrationConstants.PROJECT_SPONSOR_SEARCH_DLG)
	public WebElement ProjectSponsorSearchDlg;

	@FindBy(xpath = ProjectRegistrationConstants.SALESPERSON)
	public WebElement Salesperson;
	
	@FindBy(xpath = ProjectRegistrationConstants.SALESPERSON_SEARCH_DLG)
	public WebElement SalespersonSearchDlg;
	
	@FindBy(xpath = ProjectRegistrationConstants.ACCOUNT_MANAGER)
	public WebElement AccountManager;
	
	@FindBy(xpath = ProjectRegistrationConstants.ACCOUNT_MANAGER_SEARCH_DLG)
	public WebElement AccountManagerSearchDlg;

	@FindBy(xpath = ProjectRegistrationConstants.DELIVERY_HEAD)
	public WebElement DeliveryHead;
	
	@FindBy(xpath = ProjectRegistrationConstants.DELIVERY_HEAD_SEARCH_DLG)
	public WebElement DeliveryHeadSearchDlg;
	
	@FindBy(xpath = ProjectRegistrationConstants.PROGRAM_MANAGER)
	public WebElement ProgramManager;
	
	@FindBy(xpath = ProjectRegistrationConstants.PROGRAM_MANAGER_SEARCH_DLG)
	public WebElement ProgramManagerSearchDlg;

	@FindBy(xpath = ProjectRegistrationConstants.PROJECT_MANAGER)
	public WebElement ProjectManager;
	
	@FindBy(xpath = ProjectRegistrationConstants.PROJECT_MANAGER_SEARCH_DLG)
	public WebElement ProjectManagerSearchDlg;
	
	@FindBy(xpath = ProjectRegistrationConstants.SUB_MANAGER)
	public WebElement SubManager;
	
	@FindBy(xpath = ProjectRegistrationConstants.SUB_MANAGER_SEARCH_DLG)
	public WebElement SubManagerSearchDlg;

	@FindBy(xpath = ProjectRegistrationConstants.FINANCE_REPRESENTATIVE)
	public WebElement FinanceRepresentative;
	
	@FindBy(xpath = ProjectRegistrationConstants.FINANCE_REPRESENTATIVE_SEARCH_DLG)
	public WebElement FinanceRepresentativeSearchDlg;
	
	// HEADINGS

	@FindBy(xpath = ProjectRegistrationConstants.REQUEST_ID_TEXT)
	public WebElement RequestIDText;
	
	@FindBy(xpath = ProjectRegistrationConstants.NEWPROJECT_PAGE_HEADINGS)
	public WebElement NewProjectPageHeadings;
	
	@FindBy(xpath = ProjectRegistrationConstants.PROJECT_DETAILS_HEADINGS)
	public WebElement ProjectDetailsHeadings;
	
	@FindBy(xpath = ProjectRegistrationConstants.PROJECT_INFORMATION_HEADINGS)
	public WebElement ProjectInformationHeadings;
	
	@FindBy(xpath = ProjectRegistrationConstants.PRACTICE_INFORMATION_HEADINGS)
	public WebElement PracticeInformationHeadings;
	
	@FindBy(xpath = ProjectRegistrationConstants.CUSTOMER_INFORMATION_HEADINGS)
	public WebElement CustomerInformationHeadings;
	
	@FindBy(xpath = ProjectRegistrationConstants.CONNECT_SOW_HEADINGS)
	public WebElement SOWHeadings;
	
	@FindBy(xpath = ProjectRegistrationConstants.MANGER_DETAILS_HEADINGS)
	public WebElement ManagerDetailsHeadings;
	
	@FindBy(xpath = ProjectRegistrationConstants.HISTORY_HEADINGS)
	public WebElement HistoryHeadings;




	public void fillProjectCreation(String datapoolPath,String projectState) throws Exception{
		bResult = false;
		String readonly = null;
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		String sheetName = "Automation";
		int header=0; //Excel first row is 0
		int tc=1;
		
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tc);
		op.waitImplicitely(driver1, 10);
		System.out.println("\n******************** Filling Project Creation Details ********************\n");	
		
		//((JavascriptExecutor)driver1).executeScript("arguments[0].scrollIntoView(true);", NewProjectPageHeadings);
		op.scrollPageTo(NewProjectPageHeadings, driver1);
		
		// Filling Project Name Field if it is editable
		readonly = null;
		readonly = ProjectName.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("ProjectName"));
			logInfo(rowData.get("ProjectName") + " Field is editable " + readonly + " With Property : "
					+ readonly.contains("disableFields"));
			op.clickElement(ProjectName, driver1);
			String projectName = rowData.get("ProjectName") + timeStamp;
			op.setText(driver1, ProjectRegistrationConstants.PROJECT_NAME, projectName);
		    String val = ProjectBrief.getAttribute("value");
		    System.out.println("Entered text is: " + val);
			//String a_text_1 = (String) ((JavascriptExecutor) driver1).executeScript("return arguments[0].value",ProjectName);
			String a_text_1 = op.getElementTextValueByActions(driver1, ProjectName);
			ExcelUtils.updateCellData(datapoolPath, 4, 5, a_text_1);
			try {
				Assert.assertEquals(a_text_1.contains(rowData.get("ProjectName")), "Failed to fill Project Name Field.");
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Project Name Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("ProjectName") + " Field is readonly and not editable " + readonly + " With Property : "
					+ readonly.contains("disableFields"));
			bResult = false;
		}
		
		// Filling Project Mode Field if it is editable
		readonly = null;
		readonly = ProjectMode.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("ProjectMode"));
			op.waitImplicitely(driver1, 10);
			logInfo(rowData.get("ProjectMode") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(ProjectMode, driver1);
			op.selectFromList(driver1,"//*[@id='PopupLov_2_P2_PROJECT_MODE_dlg']/div[2]/div/div[3]/ul/li", rowData.get("ProjectMode"),"ProjectMode" );
			String a_text_2 = (String) ((JavascriptExecutor) driver1).executeScript("return arguments[0].value",ProjectMode);
			try {
				Assert.assertEquals(rowData.get("ProjectMode"), a_text_2, "Failed to fill Project Mode Field." );
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Project Mode Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("ProjectMode") + " Field is readonly and not editable " + readonly + " With Property : "
					+ readonly.contains("disableFields"));
			bResult = false;
		}

		// Filling Internal Project Type Field if it is editable
		readonly = null;
		readonly = InternalProjectType.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("InternalProjectType"));
			op.waitImplicitely(driver1, 10);
			logInfo(rowData.get("InternalProjectType") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(InternalProjectType, driver1);
			op.selectFromList(driver1,"//*[@id='PopupLov_2_P2_XORIANT_INTERNAL_PROJ_TYPE_dlg']/div[2]/div/div[3]/ul/li", rowData.get("InternalProjectType"),"InternalProjectType" );
			String a_text_2 = (String) ((JavascriptExecutor) driver1).executeScript("return arguments[0].value",InternalProjectType);
			try {
				Assert.assertEquals(rowData.get("InternalProjectType"), a_text_2, "Failed to fill Internal Project Type Field." );
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Internal Project Type Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("InternalProjectType") + " Field is readonly and not editable " + readonly + " With Property : "
					+ readonly.contains("disableFields"));
			bResult = false;
		}
		
		System.out.println("\n******************** " + rowData.get("ProjectStartDate"));
		op.clickElement(ProjectStartDate, driver1);
		op.selectDropdown(driver1, "//*[@class='ui-datepicker-month']", "Dec");
		op.selectDropdown(driver1, "//*[@class='ui-datepicker-year']", "2021");
		op.selectFromList(driver1,"//*[@id='ui-datepicker-div']/table/tbody/tr/td/a", rowData.get("StartDate"),"Start Day" );
		op.waitImplicitely(driver1, 120);
		System.out.println("\n******************** " + op.getText(ProjectStartDate));
		//3

		System.out.println("\n******************** " + rowData.get("ProjectEndDate"));
		op.clickElement(ProjectEndDate, driver1);
		op.selectDropdown(driver1, "//*[@class='ui-datepicker-month']", "Dec");
		op.selectDropdown(driver1, "//*[@class='ui-datepicker-year']", "2022");
		op.selectFromList(driver1,"//*[@id='ui-datepicker-div']/table/tbody/tr/td/a", rowData.get("EndDate"),"End Day" );
		op.waitImplicitely(driver1, 120);
		System.out.println("\n******************** " + op.getText(ProjectEndDate));
		//4
		
		// Filling Signing Organization Field if it is editable 
		readonly = null;
		readonly = SigningOrganization.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("SigningOrganization"));
			op.waitImplicitely(driver1, 10);
			logInfo(rowData.get("SigningOrganization") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(SigningOrganization, driver1);
			op.selectFromList(driver1,"//*[@id='PopupLov_2_P2_ORG_ID_dlg']/div[2]/div/div[3]/ul/li", rowData.get("SigningOrganization"),"SigningOrganization");	
			String a_text_5 = (String) ((JavascriptExecutor) driver1).executeScript("return arguments[0].value",SigningOrganization);
			try {
				Assert.assertEquals(rowData.get("SigningOrganization"), a_text_5, "Failed to fill Signing Organization Field." );
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Signing Organization Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("SigningOrganization") + " Field is readonly and not editable " + readonly + " With Property : "
					+ readonly.contains("disableFields"));
			bResult = false;
		}

		// Filling Signing Location Field if it is editable 
		readonly = null;
		readonly = SigningLocation.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("SigningLocation"));
			op.waitImplicitely(driver1, 10);
			logInfo(rowData.get("SigningLocation") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(SigningLocation, driver1);
			op.selectFromList(driver1,"//*[@id='PopupLov_2_P2_LOCATION_ID_dlg']/div[2]/div/div[3]/ul/li", rowData.get("SigningLocation"),"SigningLocation");
			String a_text_6 = (String) ((JavascriptExecutor) driver1).executeScript("return arguments[0].value",SigningLocation);
			try {
				Assert.assertEquals(rowData.get("SigningLocation"), a_text_6, "Failed to fill Signing Location Field." );
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Signing Location Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("SigningLocation") + " Field is readonly and not editable " + readonly + " With Property : "
					+ readonly.contains("disableFields"));
			bResult = false;
		}
		
		// Filling Business Unit Field if it is editable 
		readonly = null;
		readonly = BusinessUnit.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("BusinessUnit"));
			op.waitImplicitely(driver1, 10);
			logInfo(rowData.get("BusinessUnit") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(BusinessUnit, driver1);
			op.selectFromList(driver1,"//*[@id='PopupLov_2_P2_UNIT_SEGMENT_dlg']/div[2]/div/div[3]/ul/li", rowData.get("BusinessUnit"),"BusinessUnit");
			String a_text_7 = (String) ((JavascriptExecutor) driver1).executeScript("return arguments[0].value",BusinessUnit);
			try {
				Assert.assertEquals(rowData.get("BusinessUnit"), a_text_7, "Failed to fill Business Unit Field." );
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Business Unit Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("BusinessUnit") + " Field is readonly and not editable " + readonly + " With Property : "
					+ readonly.contains("disableFields"));
			bResult = false;
		}

		// Filling Line of Business Field if it is editable 
		readonly = null;
		readonly = LineofBusiness.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("LineofBusiness"));
			op.waitImplicitely(driver1, 10);
			logInfo(rowData.get("LineofBusiness") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(LineofBusiness, driver1);
			op.clickElement(driver1,"//*[@id='PopupLov_2_P2_LOB_dlg']/div[2]/div[2]/div[4]/table/tbody/tr[5]//td[2]");
			String a_text_8 = (String) ((JavascriptExecutor) driver1).executeScript("return arguments[0].value",LineofBusiness);
			try {
				Assert.assertEquals(rowData.get("LineofBusiness"), a_text_8, "Failed to fill Line of Business  Field." );
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Line of Business Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("LineofBusiness") + " Field is readonly and not editable " + readonly + " With Property : "
					+ readonly.contains("disableFields"));
			bResult = false;
		}
		
		// Filling Vertical Field if it is editable 
		readonly = null;
		readonly = Vertical.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("Vertical"));
			op.waitImplicitely(driver1, 10);
			logInfo(rowData.get("Vertical") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(Vertical, driver1);
			op.selectFromList(driver1,"//*[@id='PopupLov_2_P2_VERTICAL_dlg']/div[3]/div/div[3]/ul/li", rowData.get("Vertical"),"Vertical");
			String a_text_9 = (String) ((JavascriptExecutor) driver1).executeScript("return arguments[0].value",Vertical);
			try {
				Assert.assertEquals(rowData.get("Vertical"), a_text_9, "Failed to fill Vertical Field." );
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Vertical Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("Vertical") + " Field is readonly and not editable " + readonly + " With Property : "
					+ readonly.contains("disableFields"));
			bResult = false;
		}
		
		// Filling Operation Office Location Field if it is editable 
		readonly = null;
		readonly = OperationOfficeLocation.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("OperationOfficeLocation"));
			op.waitImplicitely(driver1, 10);
			logInfo(rowData.get("OperationOfficeLocation") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(OperationOfficeLocation, driver1);
			op.selectFromList(driver1,"//*[@id='PopupLov_2_P2_PROJECT_STATE_dlg']/div[2]/div/div[3]/ul/li", rowData.get("OperationOfficeLocation"),"OperationOfficeLocation");
			String a_text_10 = (String) ((JavascriptExecutor) driver1).executeScript("return arguments[0].value",OperationOfficeLocation);
			try {
				Assert.assertEquals(rowData.get("OperationOfficeLocation"), a_text_10, "Failed to fill Operation Office Location Field." );
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Operation Office Location Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("OperationOfficeLocation") + " Field is readonly and not editable " + readonly + " With Property : "
					+ readonly.contains("disableFields"));
			bResult = false;
		}
		
		// Filling Operation Location City Field if it is editable 
		readonly = null;
		readonly = OperationLocationCity.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("OperationLocationCity"));
			op.waitImplicitely(driver1, 10);
			logInfo(rowData.get("OperationLocationCity") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(OperationLocationCity, driver1);
			op.selectFromList(driver1,"//*[@id='PopupLov_2_P2_PROJECT_CITY_dlg']/div[2]/div/div[3]/ul/li", rowData.get("OperationLocationCity"),"OperationLocationCity");
			String a_text_11 = (String) ((JavascriptExecutor) driver1).executeScript("return arguments[0].value",OperationLocationCity);
			try {
				Assert.assertEquals(rowData.get("OperationLocationCity"), a_text_11, "Failed to fill Operation Location City Field." );
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Operation Location City Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("OperationLocationCity") + " Field is readonly and not editable " + readonly + " With Property : "
					+ readonly.contains("disableFields"));
			bResult = false;
		}
		
		// Filling Project Category Field if it is editable
		readonly = null;
		readonly = ProjectCategory.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("ProjectCategory"));
			op.waitImplicitely(driver1, 10);
			logInfo(rowData.get("ProjectCategory") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(ProjectCategory, driver1);
			op.selectFromList(driver1,"//*[@id='PopupLov_2_P2_PROJECT_CATEGORY_dlg']/div[2]/div/div[3]/ul/li", rowData.get("ProjectCategory"),"ProjectCategory");
			String a_text_12 = (String) ((JavascriptExecutor)driver1).executeScript("return arguments[0].value", ProjectCategory);  
			try {
				Assert.assertEquals(rowData.get("ProjectCategory"), a_text_12, "Failed to fill Project Category Field." );
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Internal Project Type Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("ProjectCategory") + " Field is readonly and not editable " + readonly + " With Property : "
					+ readonly.contains("disableFields"));
			bResult = false;
		}
		
		((JavascriptExecutor)driver1).executeScript("arguments[0].scrollIntoView(true);", ProjectDetailsHeadings);
		op.moveToElementAction(ProjectDetailsHeadings);
		//op.clickElement(ProjectDetailsHeadings, driver1);
		op.waitImplicitely(driver1, 20);
		
		// Filling Project Type Field if it is editable 
		readonly = null;
		readonly = ProjectType.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("ProjectType"));
			op.waitImplicitely(driver1, 10);
			logInfo(rowData.get("ProjectType") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(ProjectType, driver1);
			op.selectFromList(driver1,"//*[@id='PopupLov_2_P2_PROJECT_TYPE_dlg']/div[2]/div/div[3]/ul/li", rowData.get("ProjectType"),"ProjectType");
			String a_text_13 = (String) ((JavascriptExecutor) driver1).executeScript("return arguments[0].value",ProjectType);
			try {
				Assert.assertEquals(rowData.get("ProjectType"), a_text_13, "Failed to fill Project Type Field." );
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Project Type Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("ProjectType") + " Field is readonly and not editable " + readonly + " With Property : "
					+ readonly.contains("disableFields"));
			bResult = false;
		}
		
		// Filling Progress Method Field if it is editable 
		readonly = null;
		readonly = ProgressMethod.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("ProgressMethod"));
			op.waitImplicitely(driver1, 10);
			logInfo(rowData.get("ProgressMethod") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(ProgressMethod, driver1);
			op.selectFromList(driver1,"//*[@id='PopupLov_2_P2_PROGRESS_METHOD_dlg']/div[2]/div/div[3]/ul/li", rowData.get("ProgressMethod"),"ProgressMethod");
			String a_text_14 = (String) ((JavascriptExecutor)driver1).executeScript("return arguments[0].value", ProgressMethod);  
			try {
				Assert.assertEquals(rowData.get("ProgressMethod"), a_text_14, "Failed to fill Progress Method Field." );
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Progress Method Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("ProgressMethod") + " Field is readonly and not editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			bResult = false;
		}
		
		// Filling Project Cycle Field if it is editable 
		readonly = null;
		readonly = ProjectCycle.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("ProjectCycle"));
			op.waitImplicitely(driver1, 10);
			logInfo(rowData.get("ProjectCycle") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(ProjectCycle, driver1);
			op.selectFromList(driver1,"//*[@id='PopupLov_2_P2_PROJECT_LIFECYCLE_dlg']/div[2]/div/div[3]/ul/li", rowData.get("ProjectCycle"),"ProjectCycle");
			String a_text_15 = (String) ((JavascriptExecutor) driver1).executeScript("return arguments[0].value",ProjectCycle);
			try {
				Assert.assertEquals(rowData.get("ProjectCycle"), a_text_15, "Failed to fill Project Type Field." );
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Project Type Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("ProjectCycle") + " Field is readonly and not editable " + readonly + " With Property : "
					+ readonly.contains("disableFields"));
			bResult = false;
		}
		
		// Filling Engagement Model Field if it is editable 
		readonly = null;
		readonly = EngagementModel.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("EngagementModel"));
			op.waitImplicitely(driver1, 10);
			logInfo(rowData.get("EngagementModel") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(EngagementModel, driver1);
			op.selectFromList(driver1,"//*[@id='PopupLov_2_P2_ENGAGEMENT_TYPE_dlg']/div[2]/div/div[3]/ul/li", rowData.get("EngagementModel"),"EngagementModel");
			String a_text_16 = (String) ((JavascriptExecutor)driver1).executeScript("return arguments[0].value", EngagementModel);  
			try {
				Assert.assertEquals(rowData.get("EngagementModel"), a_text_16, "Failed to Set Engagement Model Field." );
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to Set Engagement Model Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("EngagementModel") + " Field is readonly and not editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			bResult = false;
		}
		
		System.out.println("\n******************** " + rowData.get("SalesforceOpportunityID"));
		op.clickElement(SalesforceOpportunityID, driver1);
		
		// Filling Billing Method Field if it is editable 
		readonly = null;
		readonly = BillingMethod.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("BillingMethod"));
			op.waitImplicitely(driver1, 10);
			logInfo(rowData.get("BillingMethod") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(BillingMethod, driver1);
			op.selectFromList(driver1,"//*[@id='PopupLov_2_P2_BILLING_METHOD_dlg']/div[2]/div/div[3]/ul/li", rowData.get("BillingMethod"),"BillingMethod");
			String a_text_17 = (String) ((JavascriptExecutor) driver1).executeScript("return arguments[0].value",BillingMethod);
			try {
				Assert.assertEquals(rowData.get("BillingMethod"), a_text_17, "Failed to fill Billing Method Field." );
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Billing Method Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("BillingMethod") + " Field is readonly and not editable " + readonly + " With Property : "
					+ readonly.contains("disableFields"));
			bResult = false;
		}
		
		
		// Filling Billing End Day Field if it is editable 
		readonly = null;
		readonly = BillingEndDay.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("BillingEndDay"));
			op.waitImplicitely(driver1, 10);
			logInfo(rowData.get("BillingEndDay") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(BillingEndDay, driver1);
			op.selectFromList(driver1,"//*[@id='PopupLov_2_P2_BILLING_END_DAY_dlg']/div[2]/div/div[3]/ul/li", rowData.get("BillingEndDay"),"BillingEndDay");
			String a_text_18 = (String) ((JavascriptExecutor) driver1).executeScript("return arguments[0].value",BillingEndDay);
			try {
				Assert.assertEquals(rowData.get("BillingEndDay"), a_text_18, "Failed to fill Billing End Day Field." );
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Billing End Day Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("BillingEndDay") + " Field is readonly and not editable " + readonly + " With Property : "
					+ readonly.contains("disableFields"));
			bResult = false;
		}
		
		// Filling Billing Cycle Field if it is editable 
		readonly = null;
		readonly = BillingCycle.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("BillingCycle"));
			op.waitImplicitely(driver1, 10);
			logInfo(rowData.get("BillingCycle") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(BillingCycle, driver1);
			op.selectFromList(driver1,"//*[@id='PopupLov_2_P2_BILLING_CYCLE_dlg']/div[2]/div/div[3]/ul/li", rowData.get("BillingCycle"),"BillingCycle");
			String a_text_19 = (String) ((JavascriptExecutor) driver1).executeScript("return arguments[0].value",BillingCycle);
			try {
				Assert.assertEquals(rowData.get("BillingCycle"), a_text_19, "Failed to fill Billing Cycle Field." );
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Billing Cycle Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("BillingCycle") + " Field is readonly and not editable " + readonly + " With Property : "
					+ readonly.contains("disableFields"));
			bResult = false;
		}
		
		//op.moveToElementAction(CAPHours);
		//((JavascriptExecutor)driver1).executeScript("arguments[0].scrollIntoView(true);", BillingCycle);
		
		// Filling CAPHours Field if it is editable 
		readonly = null;
		readonly = CAPHours.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("CAPHours"));
			op.waitImplicitely(driver1, 10);
			logInfo(rowData.get("CAPHours") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(CAPHours, driver1);
			op.selectFromList(driver1,"//*[@id='PopupLov_2_P2_CAP_HOURS_dlg']/div[3]/div/div[3]/ul/li", rowData.get("CAPHours"),"CAPHours");
			String a_text_20 = (String) ((JavascriptExecutor) driver1).executeScript("return arguments[0].value",CAPHours);
			try {
				Assert.assertEquals(rowData.get("CAPHours"), a_text_20, "Failed to fill CAPHours Field." );
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill CAPHours Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("CAPHours") + " Field is readonly and not editable " + readonly + " With Property : "
					+ readonly.contains("disableFields"));
			bResult = false;
		}
		
		System.out.println("\n******************** " + rowData.get("TechnologyStack"));
		op.clickElement(TechnologyStack, driver1);
		op.setText(driver1, ProjectRegistrationConstants.TECHNOLOGY_STACK, rowData.get("TechnologyStack"));
		//21
		System.out.println("\n******************** " + rowData.get("XoriantNicheSkills"));
		op.clickElement(XoriantNicheSkills, driver1);
		op.setText(driver1, ProjectRegistrationConstants.XORIANT_NICHE_SKILLS, rowData.get("XoriantNicheSkills"));
		//22
		System.out.println("\n******************** " + rowData.get("TechnologyDomain"));
		op.clickElement(TechnologyDomain, driver1);
		op.setText(driver1, ProjectRegistrationConstants.TECHNOLOGY_DOMAIN, rowData.get("TechnologyDomain"));
		//23
		// Filling PlannedGM Field if it is editable 
		readonly = null;
		readonly = PlannedGM.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("PlannedGM"));
			logInfo(rowData.get("PlannedGM") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(PlannedGM, driver1);
			//String PlannedGM = rowData.get("PlannedGM");
			op.setText(driver1, ProjectRegistrationConstants.PLANNED_GM, rowData.get("PlannedGM"));
			//String a_text_24 = (String) ((JavascriptExecutor) driver1).executeScript("return arguments[0].value",PlannedGM);
			String a_text_24 = op.getElementTextValueByActions(driver1, PlannedGM);
			try {
				Assert.assertEquals(a_text_24.contains(rowData.get("PlannedGM")), "Failed to fill Planned GM Field.");
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Planned GM Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("PlannedGM") + " Field is readonly and not editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			bResult = false;
		}
		
		((JavascriptExecutor)driver1).executeScript("arguments[0].scrollIntoView(true);", PlannedGM);
		//op.clickElement(PracticeInformationHeadings, driver1);
		op.moveToElementAction(PlannedGM);
		op.waitImplicitely(driver1, 20);
		
		System.out.println("\n ******* Filling Project Information Fields ******* ");
		
		// Filling ProjectBrief Field if it is editable 
		System.out.println("\n******************** " + rowData.get("ProjectBrief"));
		op.clickElement(ProjectBrief, driver1);
		op.setText(driver1, ProjectRegistrationConstants.PROJECT_BRIEF, rowData.get("ProjectBrief"));
		//25
		threadsleep(100);
		String a_text_26 = op.getElementTextValueByActions(driver1, ProjectBrief);
		System.out.println("\n***************************** ");
		System.out.println("***** " + a_text_26);
		System.out.println("***** " + rowData.get("ProjectBrief"));
		System.out.println("\n***************************** ");
		threadsleep(100);
		
		
		// Filling XoriantContribution Field if it is editable 
		readonly = null;
		readonly = XoriantContribution.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("XoriantContribution"));
			logInfo(rowData.get("XoriantContribution") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(XoriantContribution, driver1);
			//String XoriantContribution = rowData.get("XoriantContribution");
			op.setText(driver1, ProjectRegistrationConstants.XORIANT_CONTRIBUTION, rowData.get("XoriantContribution"));
			threadsleep(100);
			String a_text_27 = op.getElementTextValueByActions(driver1, XoriantContribution);
			System.out.println("\n***************************** ");
			System.out.println("*$$$$$ " + a_text_27);
			System.out.println("***** " + rowData.get("XoriantContribution"));
			System.out.println("\n***************************** ");
			threadsleep(100);
			try {
				Assert.assertEquals(a_text_27.contains(rowData.get("XoriantContribution")), "Failed to fill Xoriant Contribution Field.");
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Xoriant Contribution Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("XoriantContribution") + " Field is readonly and not editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			bResult = false;
		}
		
		// Filling BusinessGoal Field if it is editable 
		readonly = null;
		readonly = BusinessGoal.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("BusinessGoal"));
			logInfo(rowData.get("BusinessGoal") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(BusinessGoal, driver1);
			//String BusinessGoal = rowData.get("BusinessGoal");
			op.setText(driver1, ProjectRegistrationConstants.BUSINESS_GOAL, rowData.get("BusinessGoal"));
			String a_text_28 = op.getElementTextValueByActions(driver1, BusinessGoal);
			try {
				Assert.assertEquals(a_text_28.contains(rowData.get("BusinessGoal")), "Failed to fill Business Goal Field.");
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Business Goal Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("BusinessGoal") + " Field is readonly and not editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			bResult = false;
		}
		
		// Filling InvestmentGoal Field if it is editable 
		readonly = null;
		readonly = InvestmentGoal.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("InvestmentGoal"));
			logInfo(rowData.get("InvestmentGoal") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(InvestmentGoal, driver1);
			//String InvestmentGoal = rowData.get("InvestmentGoal");
			op.setText(driver1, ProjectRegistrationConstants.INVESTMENT_GOAL, rowData.get("InvestmentGoal"));
			String a_text_29 = op.getElementTextValueByActions(driver1, InvestmentGoal);
			try {
				Assert.assertEquals(a_text_29.contains(rowData.get("InvestmentGoal")), "Failed to fill Investment Goal Field.");
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill Investment GoalField." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("InvestmentGoal") + " Field is readonly and not editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			bResult = false;
		}
		
		// Filling GoToMarketPlan Field if it is editable 
		readonly = null;
		readonly = GoToMarketPlan.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("GoToMarketPlan"));
			logInfo(rowData.get("GoToMarketPlan") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(GoToMarketPlan, driver1);
			//String GoToMarketPlan = rowData.get("GoToMarketPlan");
			op.setText(driver1, ProjectRegistrationConstants.GO_TO_MARKET_PLAN, rowData.get("GoToMarketPlan"));;
			String a_text_30 = op.getElementTextValueByActions(driver1, GoToMarketPlan);
			try {
				Assert.assertEquals(a_text_30.contains(rowData.get("GoToMarketPlan")), "Failed to fill GoTo Market Plan Field.");
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to fill GoTo Market Plan Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("GoToMarketPlan") + " Field is readonly and not editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			bResult = false;
		}
		
		System.out.println("\n ******* Filling Practice Information Fields ******* ");
		op.moveToElementAction(PracticeInformationHeadings);
		
		System.out.println("\n******************** " + rowData.get("PracticeContribution"));
		op.clickElement(PracticeContribution, driver1);
		op.selectFromList(driver1,"//*[@id='PopupLov_2_P2_XORIANT_PRACTICE_TYPE_dlg']/div[2]/div/div[3]/ul/li", rowData.get("PracticeContribution"),"PracticeContribution");
		String a_text_31 = (String) ((JavascriptExecutor)driver1).executeScript("return arguments[0].value", PracticeContribution);  
		Assert.assertEquals(rowData.get("PracticeContribution"), a_text_31, "Failed to Set Practice Contribution Field");
		
		System.out.println("\n******************** " + rowData.get("PracticeBusinessUnit"));
		//op.clickElement(PracticeBusinessUnit, driver1);
		//32
		System.out.println("\n******************** " + rowData.get("PracticeLead"));
		//op.clickElement(PracticeBusinessUnit, driver1);
		//33
		System.out.println("\n******************** " + rowData.get("PracticeHead"));
		//op.clickElement(PracticeHead, driver1);
		//34
		System.out.println("\n******************** " + rowData.get("PracticeProgramManager"));
		//op.clickElement(PracticeProgramManager, driver1);
		//35
		
		System.out.println("\n ******* Customer Information Fields ******* ");
		//op.scrollPageTo(CustomerInformationHeadings, driver1);
		op.moveToElementAction(CustomerInformationHeadings);
		
		// Filling Customer Field if it is editable 
		readonly = null;
		readonly = Customer.getAttribute("class");
		if (!readonly.contains("disableFields")) {
			System.out.println("\n******************** " + rowData.get("Customer"));
			op.waitImplicitely(driver1, 10);
			logInfo(rowData.get("Customer") + " Field is editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			op.clickElement(Customer, driver1);
			op.selectFromList(driver1,"//*[@id='PopupLov_2_P2_CUSTOMER_ID_dlg']/div[2]/div/div[3]/ul/li", rowData.get("Customer"),"Customer");
			String a_text_36 = (String) ((JavascriptExecutor)driver1).executeScript("return arguments[0].value", Customer);  
			try {
				Assert.assertEquals(rowData.get("Customer"), a_text_36, "Failed to Set Customer Field." );
				op.waitImplicitely(driver1, 10);
				bResult = true;
			} catch (AssertionError e) {
				logError("Failed to Set Customer Field." + e);
				bResult = false;
			}
		} else {
			logInfo(rowData.get("Customer") + " Field is readonly and not editable " + readonly + " With Property : " + readonly.contains("disableFields"));
			bResult = false;
		}
		
		System.out.println("\n******************** " + rowData.get("Name"));
		//37
		System.out.println("\n******************** " + rowData.get("AddressLine1"));
		//38
		System.out.println("\n******************** " + rowData.get("AddressLine2"));
		//39
		System.out.println("\n******************** " + rowData.get("Country"));
		//40
		System.out.println("\n******************** " + rowData.get("StateCounty"));
		//41
		System.out.println("\n******************** " + rowData.get("City"));
		//42
		System.out.println("\n******************** " + rowData.get("ZipPinCode"));
		//43
		System.out.println("\n******************** " + rowData.get("PointofContact"));
		//44
		System.out.println("\n******************** " + rowData.get("CustomerPhone"));
		//45
		System.out.println("\n******************** " + rowData.get("EmailAddress"));
		//46
		
		System.out.println("\n ******* SOW Information Fields ******* ");
		
		System.out.println("\n******************** " + rowData.get("ConnectSOW"));
		//47 
		System.out.println("\n******************** " + rowData.get("StartDate"));
		//48
		System.out.println("\n******************** " + rowData.get("EndDate"));
		//48
		System.out.println("\n******************** " + rowData.get("Amount"));
		//49
		System.out.println("\n******************** " + rowData.get("AddCoordinator"));
		//50
		System.out.println("\n******************** " + rowData.get("UploadFile"));
		//51

		String xpath = "//*[@id='connect_sow_region_tab']//span[contains(text(),'Connect SOW')]";
		WebElement wb= driver1.findElement(By.xpath("//*[@id='attachments_region1_tab']//span[contains(text(),'Attachments')]"));

		System.out.println("\n ******* Manager Information Fields ******* ");
		op.scrollPageTo(ManagerDetailsHeadings, driver1);
		
		System.out.println("\n******************** " + rowData.get("ProjectSponsor"));
		readonly = null;
		readonly = ProjectSponsor.getAttribute("class");
		if(!readonly.contains("disableFields"))
		{
			op.clickElement(ProjectSponsor, driver1);
			String a_text_52 = op.setManagerDetailsText(driver1, ProjectSponsorSearchDlg, rowData.get("ProjectSponsor"),"PROJECT_SPONSOR");
			Assert.assertEquals(rowData.get("ProjectSponsor"), a_text_52, "Failed to entered  Projct Sponsor Field.");
			threadsleep(260);
			System.out.println("Projct Sponsor Field is filled successfully") ;
			System.out.println("Projct Sponsor is editable " + readonly.contains("disableFields") ) ;
			System.out.println("Projct Sponsor is editable " + readonly ) ;
		}
		else
		{
			logInfo("Projct Sponsor Field is readonly and not editable") ;
			System.out.println("Projct Sponsor Field is readonly and not editable " + readonly.contains("disableFields") ) ;
			System.out.println("Projct Sponsor Field is readonly and not editable" + readonly ) ;
		}
	
		System.out.println("\n******************** " + rowData.get("Salesperson"));
		op.clickElement(Salesperson, driver1);
		String a_text_53 = op.setManagerDetailsText(driver1, SalespersonSearchDlg, rowData.get("Salesperson"),"SALES_PERSON");
		Assert.assertEquals(rowData.get("Salesperson"), a_text_53, "Failed to entered  Sales person Field.");
		threadsleep(260);
		
		System.out.println("\n******************** " + rowData.get("AccountManager"));
		op.clickElement(AccountManager, driver1);
		String a_text_54 = op.setManagerDetailsText(driver1, AccountManagerSearchDlg, rowData.get("AccountManager"),"ACCOUNT_MANAGER");
		Assert.assertEquals(rowData.get("AccountManager"), a_text_54, "Failed to entered Account Manager Field.");
		threadsleep(260);
		
		System.out.println("\n******************** " + rowData.get("DeliveryHead"));
		op.clickElement(DeliveryHead, driver1);
		String a_text_55 = op.setManagerDetailsText(driver1, DeliveryHeadSearchDlg, rowData.get("DeliveryHead"),"DELIVERY_HEAD");
		//String a_text_23 = (String) ((JavascriptExecutor)driver1).executeScript("return arguments[0].value", DeliveryHeadSearchDlg);  
		Assert.assertEquals(rowData.get("DeliveryHead"), a_text_55, "Failed to entered Delivery Head Field.");
		threadsleep(260);
		
		System.out.println("\n******************** " + rowData.get("ProgramManager"));
		op.clickElement(ProgramManager, driver1);
		String a_text_56 = op.setManagerDetailsText(driver1, ProgramManagerSearchDlg, rowData.get("ProgramManager"),"PROGRAM_MANAGER");
		//String a_text_24 = (String) ((JavascriptExecutor)driver1).executeScript("return arguments[0].value", ProgramManagerSearchDlg);  
		Assert.assertEquals(rowData.get("ProgramManager"), a_text_56, "Failed to entered Program Manager Field.");
		threadsleep(260);
		
		System.out.println("\n******************** " + rowData.get("ProjectManager"));
		op.clickElement(ProjectManager, driver1);
		String a_text_57 = op.setManagerDetailsText(driver1, ProjectManagerSearchDlg, rowData.get("ProjectManager"),"PROJECT_MANAGER");
		//String a_text_25 = (String) ((JavascriptExecutor)driver1).executeScript("return arguments[0].value", ProjectManagerSearchDlg);  
		Assert.assertEquals(rowData.get("ProjectManager"), a_text_57, "Failed to entered Project Manager Field.");
		threadsleep(260);
		
		System.out.println("\n******************** " + rowData.get("SubManager"));
		//58
		System.out.println("\n******************** " + rowData.get("FinanceRepresentative"));
		//59
		readonly = null;
		readonly = FinanceRepresentative.getAttribute("class");
		if(!readonly.contains("disableFields"))
		{
			op.clickElement(FinanceRepresentative, driver1);
			String a_text_60 = op.setManagerDetailsText(driver1, FinanceRepresentativeSearchDlg, rowData.get("FinanceRepresentative"),"FINANCE_REPRESENTATIVE");
			Assert.assertEquals(rowData.get("FinanceRepresentative"), a_text_60, "Failed to entered Finance Representative Field.");
			threadsleep(260);
			System.out.println("Finance Representative Field is filled successfully") ;
			System.out.println("Finance Representative is editable " + readonly.contains("disableFields") ) ;
     		System.out.println("Finance Representative is editable " + readonly ) ;
		}
		else
		{
			logInfo("Finance Representative Field is readonly and not editable") ;
			System.out.println("Finance Representative Field is readonly and not editable " + readonly.contains("disableFields") ) ;
			System.out.println("Finance Representative Field is readonly and not editable" + readonly ) ;
		}
		
		op.scrollPageTo(NewProjectPageHeadings, driver1);
		op.sleepInMiliSeconds(1000);
		if (projectState.equals("DRAFT"))
		{
			NewProjectPageSaveBtn.click();
			logInfo("Project Save as Draft Option Selected") ;
			wait.until(ExpectedConditions.visibilityOf(SaveProjectAlertMsg));
			if(controlActions.isElementDisplayedOnPage(SaveProjectAlertMsg))
			{
				logInfo("Draft Project Saved Successfully") ;
				op.scrollPageTo(RequestIDText, driver1);
				
			}
			else
			{
				logError("Fail to save Draft project");
			}
		}
		else if(projectState.equals("SUBMIT"))
		{
			NewProjectPageSubmitBtn.click();
			logInfo("Project Submit Option Selected") ;
			wait.until(ExpectedConditions.visibilityOf(ProjectSaveAlertMsg));
			if(controlActions.isElementDisplayedOnPage(ProjectSaveAlertMsg))
			{
				logInfo("Test Case: Validate failur on Submit Project if No document uploded is passed successfully");
			}
			CloseAlertMsg.click();
			op.clickElement(wb,driver1);
			if(performFileUpload(driver1))
			{
				NewProjectPageSubmitBtn.click();
				wait.until(ExpectedConditions.visibilityOf(PmoApprovalAlertMsg));
				if(controlActions.isElementDisplayedOnPage(PmoApprovalAlertMsg))
				{
					logInfo("Project Saved Successfully");
					op.clickElement(NewProjectLink);
				}
				else
				{
					logError("Fail to save the project");
				}
			}
		}
		else
		{
			NewProjectPageSaveBtn.click();
			logInfo("Saving the Project for incorrect Option") ;
		}
		// *[@class='t-Alert-title'and contains(text(),'Changes Saved')]
		//controlActions.isElementDisplayedOnPage(SaveProjectAlertMsg);
		//wait.until(ExpectedConditions.visibilityOf(SaveProjectAlertMsg));
		
		// Validate for Request ID 
//		wait.until(ExpectedConditions.visibilityOf(RequestIDText));
//		op.scrollPageTo(RequestIDText, driver1);
		threadsleep(9000);
		
	}
	
	//get RequestID on Submitting or Saving the Project
	public String getRequestID()
	{
		String RequestID = null;
		try
		{
			RequestID = RequestIDText.getText();
			logInfo("Project Request ID is: " + RequestID) ;
		}
		catch(Exception e) 
		{
			logError("Fail to get Request ID for Saved/Submited Project." + e) ;
		}
		return RequestID;
	}
	
	public boolean performFileUpload(WebDriver driver1) throws AWTException {
		logInfo("Performing File Upload Operation");
		String workspace = System.getProperty("user.dir");
		String filename =  "Sample.txt";
		String filepath =  		workspace+"\\test-data-files\\"+filename;
		wait.until(ExpectedConditions.visibilityOf(UploadBtn));
		op.sleepInMiliSeconds(1000);
		//op.clickElement(UploadBtn);
		actions.click(UploadBtn).perform();
		//UploadBtn.click();
		op.sleepInMiliSeconds(2000);
		//wait.until(ExpectedConditions.visibilityOf(AttachmentDlgTxt));
		wait.until(ExpectedConditions.visibilityOf(driver1.findElement(By.xpath("//*[@id='apex_dialog_1']/iframe"))));
		//setFileOnElement(filename);
		driver1.switchTo().frame(driver1.findElement(By.xpath("//*[@id='apex_dialog_1']/iframe")));
		wait.until(ExpectedConditions.visibilityOf(AttachmentDlg));
		logInfo("Attachment Dialog Frame is displayed on the page: " + controlActions.isElementDisplayedOnPage(AttachmentDlg));
		driver1.findElement(By.xpath("//input[@id='P4_FILE_BLOB']")).sendKeys(filepath);

		boolean isAttachmentDlgExist;
		isAttachmentDlgExist = controlActions.isElementDisplayedOnPage(AttachmentDlg);
		logInfo("File Upload Dialog opened successfully: " + isAttachmentDlgExist );
		//op.clickElement(CancelBtn, driver1);
		//op.clickElement(UploadAttachmentBtn, driver1);
		driver1.findElement(By.xpath("//*[@id='B427327610526930984' and contains(text(),'Upload Attachment')]")).click();
		 op.sleepInMiliSeconds(1000);
		
		// switch back
		//driver1.switchTo().activeElement();
		driver1.switchTo().defaultContent();
        op.sleepInMiliSeconds(1000);
        WebElement wb= driver1.findElement(By.xpath("//*[@id='report_table_attachments']//a[contains(text(),'"+filename+"')]"));
      //*[@id='report_table_attachments']//a[contains(text(),'Sample.docx')]
        if(wb.isDisplayed())
        {
        	logInfo("Document uploaded Successfully: " + filepath) ;
        	op.scrollPageTo(NewProjectPageHeadings, driver1);
        	return true;
         }
        else
        {
        	logError("Fail to upload document: " + filepath) ;
        	op.scrollPageTo(NewProjectPageHeadings, driver1);
        	return false;
        }
 		//WebElement wb= driver1.findElement(By.xpath("//*[@id='manager_details_region_tab']//span[contains(text(),'Manager Details')]"));
		//op.clickElement(wb,driver1);
	}
	
	public boolean createNewProject() 
	{
	try{
			//controlActions.clickElement(NewProjectLink);
			wait.until(ExpectedConditions.visibilityOf(NewProjectLink));
			op.clickElement(NewProjectLink);
			wait.until(ExpectedConditions.visibilityOf(CreateProjectLink)); 
			if(controlActions.isElementDisplayedOnPage(CreateProjectLink))
			{
				logInfo("New Project Tab Opened Successfully clicked");
				op.clickElement(CreateProjectLink);
				op.clickElement(CreateNewProjectLink);
				op.waitImplicitely(driver1, 20);
				((JavascriptExecutor)driver1).executeScript("arguments[0].scrollIntoView(true);", ProjectDetailsHeadings);
				//op.clickElement(ProjectDetailsHeadings, driver1);
				//controlActions.moveToElementAction(NewProjectPageHeadings);
				op.waitImplicitely(driver1, 20);
				if(controlActions.isElementDisplayedOnPage(ProjectDetailsHeadings))
				{
					logInfo("Create New Project Page opened successfully") ;
					return true;
				}
				else
				{
					logInfo("Failed to Open Select from Exiting Project Popup");
					return false;			
				}				
			}
			else
			{
				logInfo("Failed to open New Project Tab");
				return false;
			}
	}
	catch(Exception e) 
	{
		logError("Failed to open New Project Page "+ e.getMessage());
		return false;
	}
	}
	
	public boolean validateAlert() 
	{
		try{
				controlActions.clickElement(NewProjectPageSubmitBtn);
				if(controlActions.isElementDisplayedOnPage(NewProjectPageAlertMsg))
				{
					logInfo("Field validation alert message poped up successfully") ;
					controlActions.clickElement(NewProjectPageCloseBtn);
					return true;
				}
				else
				{
					logInfo("Failed to open field validation alert message");
					return false;			
				}	
		}
		catch(Exception e) 
		{
			logError("Failed to open field validation alert message "+ e.getMessage());
			return false;
		}
	}
	
	public boolean createProjectFromExiting(String projectName) {
		//try {
			controlActions.clickElement(NewProjectLink);
			if (controlActions.isElementDisplayedOnPage(CreateProjectLink)) {
				logInfo("New Project Tab Opened Successfully clicked");
				// controlActions.clickElement(CreateProjectLink);
				// controlActions.clickElement(CreateFromExitingProjectLink);
				op.clickElement(CreateProjectLink, driver1);
				op.clickElement(CreateFromExitingProjectLink, driver1);

				logInfo("Dialog Select From Existing Project displayed on the page: " + controlActions.isElementDisplayedOnPage(SelectFromExistingDlg));
				if (controlActions.isElementDisplayedOnPage(SelectFromExistingDlg)) {
					threadsleep(1000);
					driver1.switchTo().frame(driver1.findElement(By.xpath("//*[@id='apex_dialog_1']/iframe")));
					logInfo("Lable Existing Project is displayed on the page: " + controlActions.isElementDisplayedOnPage(ExistingProjectLbl));
					op.clickElement(SelectProjectLst, driver1);
					driver1.switchTo().defaultContent();

					logInfo("Select from Exiting Project Popup Opened Successfully");
					logInfo("Field Project Search Text is displayed on the page: " + controlActions.isElementDisplayedOnPage(ProjectSearchTxt));
					op.clickElement(ProjectSearchTxt, driver1);
					
					if (controlActions.isElementDisplayed(ProjectSearchTxt)) {
						logInfo("Search from Exiting Project Popup opened successfully");
						op.setElementTextValueByActions(driver1, ProjectSearchTxt, projectName);
						driver1.findElement(By.xpath("//tr//span[@class='popup-lov-highlight' and contains(text(),'" + projectName + "')]")).click();
						threadsleep(1000);
						driver1.switchTo().frame(driver1.findElement(By.xpath("//*[@id='apex_dialog_1']/iframe")));
						threadsleep(1000);
						boolean isProjectSelected = driver1.findElement(By.xpath("//*[@class='t-AVPList-value' and contains(text(),'" + projectName + "')]")).isDisplayed();
						// *[@class='t-AVPList-value' and contains(text(),'Test0006')]
						if (isProjectSelected) {
							logInfo("Project " + projectName + " is selected succesfully");
							
							// Clicking on Add button after selecting the project
							op.clickElement(AddProjectBtn, driver1);
							threadsleep(5000);
							op.scrollPageTo(NewProjectPageHeadings, driver1);
							//NewProjectPageSaveBtn.click();
							//*[@class='t-Alert-title'and contains(text(),'Changes Saved')]
							wait.until(ExpectedConditions.visibilityOf(NewProjectPageHeadings));
						    //NewProjectPageSubmitBtn.click();
							return true;
						} else {
							logInfo("Failed to Select Project " + projectName);
							return false;
						}
					} else {
						logInfo("Failed to Open Search from Exiting Project Popup");
						threadsleep(5000);
						return false;
					}
				} else {
					logInfo("Failed to Open Select from Exiting Project Popup");
					return false;
				}
			} else {
				logInfo("Failed to open New Project Tab");
				return false;
			}
/*		} catch (Exception e) {
			logError("Failed to open New Project Tab " + e.getMessage());
			return false;
		}*/
	}
	

	
} // End of Class

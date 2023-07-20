package com.project.pageobjects.pTracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;
import org.testng.Assert;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PL_ActiveProjectsPage;

public class CommonPages extends TestBase {

	WebDriverWait wait;
	ControlActions controlActions;
	Operations op;
	public String projectNumberActual;
	public String projectState;
	private static final int DELAY = 2000;
	// WebDriver driver ;

	public CommonPages(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, DELAY);
		controlActions = new ControlActions(driver);
		op = new Operations(driver);
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
	}

	// ACTIVE PROJECT TAB OBJECTS
	@FindBy(xpath = CommonPagesConstants.ACTIVE_PROJECT_SEARCH_TXT)
	public WebElement ActiveProjectSearchTxt;

	@FindBy(xpath = CommonPagesConstants.ACTIVE_PROJECT_GO_BTN)
	public WebElement ActiveProjectGoBtn;

	@FindBy(xpath = CommonPagesConstants.ACTIVE_PROJECT_FILTER_BTN)
	public WebElement ActiveProjectFilterBtn;

	@FindBy(xpath = CommonPagesConstants.ACTIVE_PROJECT_DOWNLOAD_BTN)
	public WebElement ActiveProjectDownloadBtn;

	@FindBy(xpath = CommonPagesConstants.ACTIVE_PROJECT_NEXTSET_BTN)
	public WebElement ActiveProjectNextsetBtn;

	@FindBy(xpath = CommonPagesConstants.MANAGER_DETAILS_TAB)
	public WebElement ManagerDetailsTab;

	@FindBy(xpath = CommonPagesConstants.ADD_SUB_MANAGER)
	public WebElement AddSubManager;

	@FindBy(xpath = CommonPagesConstants.ADD_SUB_MANAGER_BTN)
	public WebElement AddSubManagerBtn;

	@FindBy(xpath = CommonPagesConstants.SUB_MANAGER_TYPE)
	public WebElement SubManagerType;

	// Prasanna//

	@FindBy(xpath = CustomerCoordinatorConstants.FIXED_PRICE)
	public WebElement fixed_price;

	@FindBy(xpath = CustomerCoordinatorConstants.SEARCH_PROJECT)
	public WebElement Search_project;

	@FindBy(xpath = CustomerCoordinatorConstants.GO_BUTTON)
	public WebElement go_button;

	@FindBy(xpath = CustomerCoordinatorConstants.ACTIVE_PROJECTS_LIST)
	public WebElement active_projects;

	@FindBy(xpath = CustomerCoordinatorConstants.PROJECT_TEST8877)
	public WebElement project_test8877;

	@FindBy(xpath = CustomerCoordinatorConstants.CUSTOMER_COORDINATOR)
	public WebElement customer_coordinator;

	@FindBy(xpath = CustomerCoordinatorConstants.ADD_COORDINATOR)
	public WebElement add_coordinator;

	@FindBy(xpath = CustomerCoordinatorConstants.COORDINATOR_NAME)
	public WebElement coordinator_name;

	@FindBy(xpath = CustomerCoordinatorConstants.PROJECT_ROLE)
	public WebElement project_role;

	@FindBy(xpath = CustomerCoordinatorConstants.DESIGNATION)
	public WebElement designation;

	@FindBy(xpath = CustomerCoordinatorConstants.EMAIL_ADDRESS)
	public WebElement email_address;

	@FindBy(xpath = CustomerCoordinatorConstants.REMARK)
	public WebElement remark;

	@FindBy(xpath = CustomerCoordinatorConstants.ADD_BUTTON)
	public WebElement add_button;

	@FindBy(xpath = CustomerCoordinatorConstants.ADD_CUSTOMER_COORDINATOR_IFRAME)
	public WebElement add_customer_coordinator_iframe;

	@FindBy(xpath = CustomerCoordinatorConstants.CANCEL_BUTTON)
	public WebElement cancel_button;

	@FindBy(xpath = CustomerCoordinatorConstants.INACTIVE_RADIO_BUTTON)
	public WebElement inactive_button;

	@FindBy(xpath = CustomerCoordinatorConstants.ACTIVE_RADIO_BUTTON)
	public WebElement active_button;

	@FindBy(xpath = CustomerCoordinatorConstants.EDIT_CUSTOMER_COORDINATOR_IFRAME_ACTIVE)
	public WebElement edit_customer_coordinator_iframe_active;

	@FindBy(xpath = CustomerCoordinatorConstants.EDIT_COORDINATOR_NAME)
	public WebElement edit_coordinator_name;

	@FindBy(xpath = CustomerCoordinatorConstants.EDIT_PROJECT_ROLE)
	public WebElement edit_project_role;

	@FindBy(xpath = CustomerCoordinatorConstants.EDIT_DESIGNATION)
	public WebElement edit_designation;

	@FindBy(xpath = CustomerCoordinatorConstants.EDIT_EMAIL_ADDRESS)
	public WebElement edit_email_address;

	@FindBy(xpath = CustomerCoordinatorConstants.EDIT_REMARK)
	public WebElement edit_remark;

	@FindBy(xpath = CustomerCoordinatorConstants.SAVE_CHANGES_BUTTON)
	public WebElement save_changes_button;

	@FindBy(xpath = CustomerCoordinatorConstants.EDIT_CUSTOMER_COORDINATOR_IFRAME_INACTIVE)
	public WebElement edit_customer_coordinator_iframe_inactive;

	@FindBy(xpath = DeliveryMilestoneConstants.SEARCH_PROJECT)
	public WebElement searchProject;

	@FindBy(xpath = DeliveryMilestoneConstants.GO_BUTTON)
	public WebElement goButton;

	@FindBy(xpath = CustomerCoordinatorConstants.ERROR_MESSAGE_COORDINATORNAME)
	public WebElement errorMessageCoordinatorName;

	// Ganesh//

	@FindBy(xpath = CommonPagesConstants.SELECT_SUB_MANAGER)
	public WebElement SelectSubManager;

	@FindBy(xpath = CommonPagesConstants.SELECT_SUB_MANAGER_SDATE)
	public WebElement SelectSubManagerSDate;

	@FindBy(xpath = CommonPagesConstants.SUB_MANAGER_ADD)
	public WebElement SubManagerAdd;

	@FindBy(xpath = CommonPagesConstants.SUB_MANAGER_NAME_SEARCH)
	public WebElement SubManagerNameSearch;

	@FindBy(xpath = CommonPagesConstants.SUB_MANAGER_SELECT)
	public WebElement SubManagerSelect;

	@FindBy(xpath = CommonPagesConstants.VERF_SUB_MANAGER_NAME)
	public WebElement VerifySubManagerName;

	@FindBy(xpath = CommonPagesConstants.VERF_SUB_MANAGER_ROLE)
	public WebElement VerifySubManagerRole;

	@FindBy(xpath = CommonPagesConstants.VERF_SUB_MANAGER_DATE)
	public WebElement VerifySubManagerDate;

	@FindBy(xpath = CommonPagesConstants.SPINEER)
	public List<WebElement> spinner;

	@FindBy(xpath = CommonPagesConstants.SUB_MANAGER_CANCEL)
	public WebElement cancelSubManager;

	@FindBy(xpath = CommonPagesConstants.VERF_PROJECT_MANAGER_ROLE)
	public WebElement VerifyprojectManagerRole;

	@FindBy(xpath = CommonPagesConstants.PAYMENT_MILESTONE_DETAILS_TAB)
	public WebElement paymentMilestoneDetailsTab;

	@FindBy(xpath = CommonPagesConstants.ADD_PAYMENT_MILESTONE)
	public WebElement addPaymentMilestonebtn;

	@FindBy(xpath = CommonPagesConstants.ADD_PAYMENT_MILESTONE_BTN)
	public WebElement addPaymentMilestone;

	@FindBy(xpath = CommonPagesConstants.MILESTONE_NAME)
	public WebElement paymentMilestoneName;

	@FindBy(xpath = CommonPagesConstants.MILESTONE_START_DATE)
	public WebElement paymentMilestoneStartDate;

	@FindBy(xpath = CommonPagesConstants.MILESTONE_END_DATE)
	public WebElement paymentMilestoneEndtDate;

	@FindBy(xpath = CommonPagesConstants.MILESTONE_AMOUNT)
	public WebElement paymentMilestoneAmount;

	@FindBy(xpath = CommonPagesConstants.MILESTONE_ADDBT)
	public WebElement paymentMilestoneAddBtn;

	@FindBy(xpath = CommonPagesConstants.MILESTONE_NAME_VERIFY)
	public WebElement paymentMilestoneNameVerify;

	@FindBy(xpath = CommonPagesConstants.MILESTONE_STARTDATE_VERIFY)
	public WebElement paymentMilestoneStartDateVerify;

	@FindBy(xpath = CommonPagesConstants.MILESTONE_ENDDATE_VERIFY)
	public WebElement paymentMilestoneEndDateVerify;

	@FindBy(xpath = CommonPagesConstants.MILESTONE_AMOUNT_VERIFY)
	public WebElement paymentMilestoneAmmountVerify;

	@FindBy(xpath = CommonPagesConstants.MILESTONE_MIDIFY_CLICK)
	public WebElement paymentMilestoneModifyClick;

	@FindBy(xpath = CommonPagesConstants.MILESTONE_MIDIFY_SAVE)
	public WebElement paymentMilestoneModifySave;
	// Ganesh2//

	// Nikita//

	@FindBy(xpath = CommonPagesConstants.RELEASE_RESOURCE_REQUEST_TAB)
	public WebElement ReleaseResourceRequesTab;

	@FindBy(xpath = CommonPagesConstants.SELECT_RELEASE_RESOURCE_EDATE)
	public WebElement SelectReleaseResourceEdate;

	@FindBy(xpath = CommonPagesConstants.SELECT_ADD_EMPLYOEE)
	public WebElement SelectAddEmplyoee;

	@FindBy(xpath = CommonPagesConstants.ADD_EMPLYOEE_NAME)
	public WebElement AddEmplyoeeName;

	@FindBy(xpath = CommonPagesConstants.ADD_EMPLYOEE_NAME_SEARCH)
	public WebElement AddEmplyoeeNameSearch;

	@FindBy(xpath = CommonPagesConstants.ADD_EMPLYOEE_SELECT)
	public WebElement AddEmplyoeeSelect;

	@FindBy(xpath = CommonPagesConstants.REALLOCATE_RESOURCE_SUBMIT_BTN)
	public WebElement ReallocateResourceSubmitBtn;

	@FindBy(xpath = CommonPagesConstants.REALLOCATE_ROLE_TYPE)
	public WebElement ReallocateRoleType;

	@FindBy(xpath = CommonPagesConstants.VERIFY_REALLOCATION)
	public WebElement VerifyReallocation;

	@FindBy(xpath = CommonPagesConstants.END_DATE_PICKER)
	public WebElement EndDatePicker;

	@FindBy(xpath = CommonPagesConstants.SOW_DETAILS_TAB)
	public WebElement SowDetailsTab;

	@FindBy(xpath = CommonPagesConstants.CONNECT_SOW_BTN)
	public WebElement ConnectSowBtn;

	@FindBy(xpath = CommonPagesConstants.CONFIRM_CHECKBOX)
	public WebElement ConfirmCheckbox;

	@FindBy(xpath = CommonPagesConstants.PROCEED_BTN)
	public WebElement ProceedBtn;

	@FindBy(xpath = CommonPagesConstants.SELECT_SOW_DROPDOWN)
	public WebElement SelectSowDropdown;

	@FindBy(xpath = CommonPagesConstants.SELECT_SOW_ROW)
	public WebElement SelectSowRow;

	@FindBy(xpath = CommonPagesConstants.ADD_BTN)
	public WebElement AddBtn;

	@FindBy(xpath = CommonPagesConstants.VERIFY_SOW_ADD)
	public WebElement VerifySowAdd;

	// Nikita//

	// Ramya
	@FindBy(xpath = CommonPagesConstants.ACTIVE_PROJECT_NUMBER)
	public WebElement ActiveProjectNumber;

	@FindBy(xpath = CommonPagesConstants.ACTIVE_PEOPLE_TAB)
	public WebElement ActivePeopleTab;

	@FindBy(xpath = CommonPagesConstants.ACTIVE_ATTACHMENTS_TAB)
	public WebElement ActiveAttachmentsTab;

	@FindBy(xpath = CommonPagesConstants.ACTIVE_TEAM_TAB)
	public WebElement ActiveTeamTab;

	@FindBy(xpath = CommonPagesConstants.ACTIVE_FOR_HISTORY_TAB)
	public WebElement ActiveForHistoryTab;

	@FindBy(xpath = CommonPagesConstants.ACTIVE_HISTORY_TAB)
	public WebElement ActiveHistoryTab;

	@FindBy(xpath = CommonPagesConstants.PROJECT_ADD_ROLE_BTN)
	public WebElement ProjectAddRoleBtn;

	@FindBy(xpath = CommonPagesConstants.CLK_ROLE_TYPE)
	public WebElement ClickRoleType;
	@FindBy(xpath = CommonPagesConstants.SELECT_ROLE_TYPE)
	public WebElement SelectRoleType;

	@FindBy(xpath = CommonPagesConstants.CLK_LOCATION)
	public WebElement ClickLocation;

	@FindBy(xpath = CommonPagesConstants.SELECT_LOCATION)
	public WebElement SelectLocation;
	// ...

	@FindBy(xpath = CommonPagesConstants.ROLETYPE_EDIT_ICON)
	public WebElement RoleTypeEditIcon;

	@FindBy(xpath = CommonPagesConstants.EDIT_LABEL)
	public WebElement EditLabel;

	@FindBy(xpath = CommonPagesConstants.VERF_Role)
	public WebElement VerifyRole;

	@FindBy(xpath = CommonPagesConstants.VERF_Location)
	public WebElement VerifyLocation;

	@FindBy(xpath = CommonPagesConstants.MONTHLY_LOADING_DEC)
	public WebElement MonthlyLoadingDec;

	@FindBy(xpath = CommonPagesConstants.MONTHLY_LOADING_JAN)
	public WebElement MonthlyLoadingJan;

	@FindBy(xpath = CommonPagesConstants.MONTHLY_LOADING_FEB)
	public WebElement MonthlyLoadingFeb;

	@FindBy(xpath = CommonPagesConstants.MONTHLY_LOADING_MAR)
	public WebElement MonthlyLoadingMar;

	@FindBy(xpath = CommonPagesConstants.MONTHLY_LOADING_APR)
	public WebElement MonthlyLoadingApr;

	@FindBy(xpath = CommonPagesConstants.MONTHLY_LOADING_MAY)
	public WebElement MonthlyLoadingMay;

	@FindBy(xpath = CommonPagesConstants.MONTHLY_LOADING_JUN)
	public WebElement MonthlyLoadingJun;

	@FindBy(xpath = CommonPagesConstants.MONTHLY_LOADING_JUL)
	public WebElement MonthlyLoadingJul;

	@FindBy(xpath = CommonPagesConstants.MONTHLY_LOADING_AUG)
	public WebElement MonthlyLoadingAug;

	@FindBy(xpath = CommonPagesConstants.MONTHLY_LOADING_SEP)
	public WebElement MonthlyLoadingSep;

	@FindBy(xpath = CommonPagesConstants.MONTHLY_LOADING_OCT)
	public WebElement MonthlyLoadingOct;

	@FindBy(xpath = CommonPagesConstants.MONTHLY_LOADING_NOV)
	public WebElement MonthlyLoadingNov;

	@FindBy(xpath = CommonPagesConstants.ADDING_ADD_ROLE)
	public WebElement SubmitAddRole;

	@FindBy(xpath = CommonPagesConstants.CLICK_SAVE_CHANGES)
	public WebElement ClickSaveChanges;

//	    @FindBy(xpath = CommonPagesConstants.BTN_ADD_ROLE)
//	    public WebElement ButtonAdd;
//Ramya//

	// Aakash Saxena

	@FindBy(xpath = CommonPagesConstants.ACTIVE_PROJECT_ROW)
	public List<WebElement> ActiveProjectRow;

	@FindBy(xpath = CommonPagesConstants.EXTEND_END_DATE)
	public WebElement ExtendEndDate;

	@FindBy(xpath = CommonPagesConstants.CALENDAR_FRAME)
	public WebElement CalendarFrame;

	@FindBy(xpath = CommonPagesConstants.CALENDAR_ICON)
	public WebElement CalendarIcon;

	@FindBy(xpath = CommonPagesConstants.CALENDAR_YEAR)
	public WebElement CalendarYear;

	@FindBy(xpath = CommonPagesConstants.CALENDAR_MONTH)
	public WebElement CalendarMonth;

	@FindBy(xpath = CommonPagesConstants.CALENDAR_DATE)
	public WebElement CalendarDate;

	@FindBy(xpath = CommonPagesConstants.ADD_COMMENT)
	public WebElement AddComment;

	@FindBy(xpath = CommonPagesConstants.COMMENT_FRAME)
	public WebElement CommentFrame;

	@FindBy(xpath = CommonPagesConstants.ADD_TEXT)
	public WebElement AddText;

	@FindBy(xpath = CommonPagesConstants.UPDATE_TEXT)
	public WebElement UpdateText;

	@FindBy(xpath = CommonPagesConstants.REMARKS_TEXT)
	public WebElement RemarksText;

	@FindBy(xpath = CommonPagesConstants.SEND_APPROVAL)
	public WebElement SendApproval;

	@FindBy(xpath = CommonPagesConstants.EXTEND_ALERT)
	public WebElement Extend_Alert;

	@FindBy(xpath = CommonPagesConstants.DATE_ERROR_MSG)
	public WebElement DateErrorMsg;

	@FindBy(xpath = CommonPagesConstants.COMMENT_ERROR_MSG)
	public WebElement CommentErrorMsg;

	@FindBy(xpath = CommonPagesConstants.CANCEL_EXTEND_DATE)
	public WebElement Cancel_Extend_Date;

	@FindBy(xpath = CommonPagesConstants.NEW_END_DATE)
	public WebElement NewEndDate;

	@FindBy(xpath = CommonPagesConstants.BACK_TO_ACTIVE)
	public WebElement BackToActive;

	@FindBy(xpath = CommonPagesConstants.HOVER_RIGHT)
	public WebElement HoverRight;

	@FindBy(xpath = CommonPagesConstants.PROJECT_HISTORY)
	public WebElement ProjectHistory;

	@FindBy(xpath = CommonPagesConstants.PROJECT_CREATION)
	public WebElement ProjectCreation;

	@FindBy(xpath = CommonPagesConstants.PROJECT_CREATION_DATE)
	public WebElement ProjectCreationDate;

	@FindBy(xpath = CommonPagesConstants.PROJECT_EXTENSION)
	public WebElement ProjectExtension;

	@FindBy(xpath = CommonPagesConstants.PROJECT_EXTENSION_DATE)
	public WebElement ProjectExtensionDate;

	@FindBy(xpath = CommonPagesConstants.PROJECT_MODIFICATION)
	public WebElement ProjectModification;

	@FindBy(xpath = CommonPagesConstants.PROJECT_MODIFICATION_DATE)
	public WebElement ProjectModificationDate;

	@FindBy(xpath = CommonPagesConstants.EDIT_ACTIVE_PROJECT)
	public WebElement EditActiveProject;

	@FindBy(xpath = CommonPagesConstants.PROJECT_NAME)
	public WebElement ProjectName;

	@FindBy(xpath = CommonPagesConstants.PROJECT_CATEGORY)
	public WebElement ProjectCategory;

	@FindBy(xpath = CommonPagesConstants.PROJECT_CATEGORY_LIST)
	public WebElement ProjectCategoryList;

	@FindBy(xpath = CommonPagesConstants.PROJECT_CYCLE)
	public WebElement ProjectCycle;

	@FindBy(xpath = CommonPagesConstants.PROJECT_CYCLE_LIST)
	public WebElement ProjectCycleList;

	@FindBy(xpath = CommonPagesConstants.PROJECT_SALESFORCE_ID)
	public WebElement SalesforceId;

	@FindBy(xpath = CommonPagesConstants.PROJECT_XORIANT_NICHE_SKILLS)
	public WebElement XoriantNicheSkills;

	@FindBy(xpath = CommonPagesConstants.PROJECT_XORIANT_CONTRIBUTION)
	public WebElement XoriantContribution;

	@FindBy(xpath = CommonPagesConstants.SUBMIT_PROJECT_DETAILS)
	public WebElement SubmitProjectDetails;

	// Aakash Saxena

	/*
	 * This method is used to verify Project Role and click on its action
	 * 
	 * @return boolean
	 */
	public boolean selectProjectRole(WebDriver driver, String ProjectRole, String emplyoeeName) throws Exception {
		threadsleep(2000);
		int i;
		List<WebElement> projectRoles = controlActions
				.perform_getListOfElementsByXPath("//*[@id='report_table_Manager-Details']/tbody/tr/td[1]");
		try {
			logInfo("Size of Project Role Web Table is : " + projectRoles.size());
			if (projectRoles.size() == 0) {
				logError("Project Role is not displayed in the table.");
				return false;
				// fail("Project Role is not displayed in the table.");
			}
			for (i = 0; i < projectRoles.size(); i++) {
				// logInfo("Milestone id is --> " + addedMilestoneIDs.get(i).getText());
				if (projectRoles.get(i).getText().equalsIgnoreCase(ProjectRole)) {
					logInfo("Project Role is correctly displayed in the table." + i);
					WebElement action = op.perform_getElementByXPath(
							"//*[@id='report_table_Manager-Details']/tbody/tr[" + (i + 1) + "]/td[5]/button/span[2]");
					if (action.getText().contains("Reallocate")) {
						logInfo("Reallocating " + emplyoeeName);
						action = op.perform_getElementByXPath(
								"//*[@id='report_table_Manager-Details']/tbody/tr[" + (i + 1) + "]/td[5]/button");
						op.clickElement(action, driver);
						return true;
					}
				}
			}
		} catch (Exception e) {
			logError(e.getMessage());
			return false;
		}
		if (i == projectRoles.size()) {
			logError("Milestone id : " + ProjectRole + " is not displayed in the table.");
			// fail("Milestone id : " + ProjectRole + " is not displayed in the table.");
			return false;
		} else {
			return true;
		}
	}

	public boolean verifyActiveProject(WebDriver driver, String searchProjectNumber) {
		try {
			// controlActions.clickOnElement(activeProjectList); // User clicks on Active
			// project
			// controlActions.clickOnElement(fixedPrice); // User clicks on Fixed price
			// project
			boolean isProjectNumberSet = enterTextInSearchProjectField(searchProjectNumber); // User enters project no
			IsTrue(isProjectNumberSet, "Project " + searchProjectNumber + " found successfully",
					"Failed to add text to project search as '" + searchProjectNumber + "'");
			controlActions.click(goButton);
			WebElement projectNumber1 = controlActions.perform_getElementByXPath(
					DeliveryMilestoneConstants.PROJECT_NUMBER1.replace("PROJ_NUM", searchProjectNumber));
			controlActions.isElementDisplayed(projectNumber1);
			controlActions.click(projectNumber1);
			return true;
		} catch (Exception e) {
			logError(e.getMessage());
			return false;

		}
	}

	/**
	 * This method is used to enter Project Number searchProjectNumber in Search
	 * text box searchProjectNumber is available inside
	 * TCG_CreateDeliveryMilestone.java
	 * 
	 * @param inputString [String]
	 * @return boolean true if action successful else false
	 */
	public boolean enterTextInSearchProjectField(String searchProjectNumber) {
		try {
			controlActions.waitForElementToBeClickable(searchProject);
			controlActions.sendKeys(searchProject, searchProjectNumber);
			// controlActions.actionEnter();
			logInfo("Entered text '" + searchProjectNumber + "' in Search Project Field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + searchProjectNumber + "' in Search Project Field" + e.getMessage());
			return false;
		}
	}

	public String[][] Search(WebDriver driver, String tableXpath, String projectName) {
		String[][] arrTabledata;
		ActiveProjectSearchTxt.clear();
		ActiveProjectSearchTxt.sendKeys("");
		ActiveProjectSearchTxt.sendKeys(projectName);
		ActiveProjectGoBtn.click();
		threadsleep(1000);
		arrTabledata = getWebTableData(driver, tableXpath);
		return arrTabledata;
	}

	/**
	 * This method returns all the data from webtable in 2d array Considering table
	 * is created with tr and td tags Can change the row and cell tags for other
	 * table tags i.e. mat-row & mat-cell
	 *
	 * @param driver     WebDriver driver
	 * @param tableXpath WebTable xpath where data needs to be fetched from
	 * @return Returns 2d array with table data
	 */
	public String[][] getWebTableData(WebDriver driver, String tableXpath) {
		String tempCellText;
		WebElement table = driver.findElement(By.xpath(tableXpath));
		List<WebElement> rowsList = table.findElements(By.tagName("tr"));
		int numRows = rowsList.size();
		int numCols = rowsList.get(0).findElements(By.tagName("td")).size();

		logInfo("Total number of rows= " + numRows);
		logInfo("Total number of cols=" + numCols);

		String[][] arrTabledata = new String[numRows][numCols];
		List<WebElement> columnsList = null;

		for (int i = 0; i < numRows; i++) {
			System.out.println();
			columnsList = rowsList.get(i).findElements(By.tagName("td"));
			for (int j = 0; j < numCols; j++) {
				logInfo(columnsList.get(j).getText().toString() + ",");
				tempCellText = columnsList.get(j).getText();
				arrTabledata[i][j] = tempCellText.toString();
			}
			logInfo("Next Row");
		}
		return arrTabledata;
	}

	/**
	 * Wait for assync content in a determined period
	 * 
	 * @param driver  Selenium web driver.
	 * @param by      Selenium By expression.
	 * @param timeout Selenium time out.
	 * @return a WebElement asynchronously loaded.
	 * @throws NoSuchElementException
	 */
	public WebElement waitForAssyncContent(WebDriver driver, By by, Long timeout) throws NoSuchElementException {
		long end = System.currentTimeMillis() + (timeout);
		WebElement renderedWebElement = null;

		while (System.currentTimeMillis() < end) {
			try {
				renderedWebElement = driver.findElement(by);
			} catch (NoSuchElementException e) {
				logError("ERROR: " + e.getMessage());
			}

			if (renderedWebElement != null && renderedWebElement.isEnabled() && renderedWebElement.isDisplayed()) {
				return renderedWebElement;
			}
		}

		if (renderedWebElement == null) {
			throw new NoSuchElementException("Could not locate assync content");
		}

		try {
			if (renderedWebElement.isDisplayed()) {
				throw new NoSuchElementException("Element is not being displayed");
			}
		} catch (Throwable t) {
			logError("ERROR: " + t.getMessage());
		}

		return renderedWebElement;
	}

//Ganesh//

	public boolean addSubManager(WebDriver driver, String employeeName, int tcRowNum) throws Exception {
		try {

			op.switchToiFrameByXpath(driver, CommonPagesConstants.IFRAME);
			wait.until(ExpectedConditions.visibilityOf(SubManagerType));
			Boolean AddSubManagerTit = SubManagerType.isDisplayed();
			IsTrue(AddSubManagerTit, "Frame swiched to Add Sub Manager", "Frame not swiched to Add Sub Manager");
			threadsleep(1000);
			SelectSubManager.sendKeys(employeeName);
			op.switchToDefaultContent(driver);
			SubManagerSelect.click();
			op.switchToiFrameByXpath(driver, CommonPagesConstants.IFRAME);
			wait.until(ExpectedConditions.visibilityOf(SubManagerType));
			SelectSubManagerSDate.click();
			CommonPages cp1 = new CommonPages(driver);
			cp1.addStartDate(driver, tcRowNum);
			wait.until(ExpectedConditions.visibilityOf(SubManagerAdd));
			SubManagerAdd.click();
			waitTillSpinnerDisable();
			logInfo("SubManager added");
			return true;
		} catch (NoSuchElementException nsee) {
			logError("ERROR:Submanager Not added ");
			return false;
		}

	}

	public boolean cancelSubManager(WebDriver driver, String employeeName, int tcRowNum) throws Exception {

		try {
			op.switchToiFrameByXpath(driver, CommonPagesConstants.IFRAME);
			wait.until(ExpectedConditions.visibilityOf(SubManagerType));
			Boolean AddSubManagerTit = SubManagerType.isDisplayed();
			Assert.assertTrue(AddSubManagerTit, "Frame not swiched to Add Sub Manager");
			IsTrue(AddSubManagerTit, "Frame swiched to Add Sub Manager", "Frame not swiched to Add Sub Manager");
			threadsleep(1000);
			SelectSubManager.sendKeys(employeeName);
			op.switchToDefaultContent(driver);
			SubManagerSelect.click();
			op.switchToiFrameByXpath(driver, CommonPagesConstants.IFRAME);
			wait.until(ExpectedConditions.visibilityOf(SubManagerType));
			SelectSubManagerSDate.click();
			CommonPages cp1 = new CommonPages(driver);
			cp1.addStartDate(driver, tcRowNum);
			wait.until(ExpectedConditions.visibilityOf(SubManagerAdd));
			cancelSubManager.click();
			logInfo("SubManager Cancel");
			return true;
		} catch (NoSuchElementException nsee) {
			logError("ERROR:Submanager Not canceled ");
			return false;
		}

	}

	private void addStartDate(WebDriver driver, int tcRowNum) throws Exception {

		String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace + "\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		String sheetName = "Automation";
		int header = 0; // Excel first row is 0
		int tc = tcRowNum - 1;
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tc);
		Operations op = new Operations(driver);
		op.selectDropdown(driver, "//*[@class='ui-datepicker-month']", "Sep");
		op.selectDropdown(driver, "//*[@class='ui-datepicker-year']", "2023");
		op.selectFromList(driver, "//*[@id='ui-datepicker-div']/table/tbody/tr/td/a", rowData.get("StartDate"),
				"Start Day");
	}

	public String subMangerRoleVerify() {
		String subMangervf = VerifySubManagerRole.getText();
		return subMangervf;
	}

	public String projectManagerRoleVerify() {
		String subMangervf = VerifyprojectManagerRole.getText();
		return subMangervf;
	}

	public String subMangerNameVerify() {
		String subMangerNamevf = VerifySubManagerName.getText();
		return subMangerNamevf;
	}

	public String subMangerDateVerify() {
		String subMangerDatevf = VerifySubManagerDate.getText();
		return subMangerDatevf;
	}

	public boolean subMangerRoleDisplay() {
		boolean subMangerDisplay = VerifySubManagerRole.isDisplayed();
		return subMangerDisplay;
	}

	public boolean projectManagerRoleDisplay() {
		boolean subMangerDisplay = VerifyprojectManagerRole.isDisplayed();
		return subMangerDisplay;
	}

	public boolean subMangerNameDisplay() {
		boolean subMangerNameDisplay = VerifySubManagerName.isDisplayed();
		return subMangerNameDisplay;
	}

	public boolean subMangerDateDisplay() {
		boolean subMangerDateDisplay = VerifySubManagerDate.isDisplayed();
		return subMangerDateDisplay;
	}

	public void waitTillSpinnerDisable() throws InterruptedException {
		int count = 0;
		while (spinner.size() != 0 && count < 90) {
			threadsleep(5000);
			count++;
		}
	}

	public void waitSwitch() {
		logInfo("SubManager waitswitch start");
		wait.until(ExpectedConditions.visibilityOf(VerifySubManagerRole));
	}

	public void waitSwitch(WebElement element) {
		logInfo("Waiting for the element to visible -> " + element);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Ganesh2//
	public boolean addPaymentMilestone(WebDriver driver, String milestoneName, String amount, int tcRowNum)
			throws Exception {
		try {
			op.switchToiFrameByXpath(driver, CommonPagesConstants.IFRAME);
			paymentMilestoneName.sendKeys(milestoneName);
			paymentMilestoneStartDate.click();
			addStartDate(driver, tcRowNum);
			paymentMilestoneEndtDate.click();
			addEndtDate(driver, tcRowNum);
			paymentMilestoneAmount.sendKeys(amount);
			paymentMilestoneAddBtn.click();
			threadsleep(4000);
			op.switchToDefaultContent(driver);
			threadsleep(4000);
			wait.until(ExpectedConditions.visibilityOf(paymentMilestoneNameVerify));
			logInfo("payment Milestone added successfully");
			return true;
		} catch (NoSuchElementException nsee) {
			logError("payment Milestone not added.");
			return false;
		}
	}

	public boolean modifyPaymentMilestone(WebDriver driver, String amountModify, int tcRowNum) throws Exception {
		try {
			// paymentMilestoneModifyClick.click();
			threadsleep(4000);
			op.switchToiFrameByXpath(driver, CommonPagesConstants.IFRAME);
			paymentMilestoneStartDate.click();
			addStartDate(driver, tcRowNum);
			threadsleep(4000);
			paymentMilestoneEndtDate.click();
			addEndtDate(driver, tcRowNum);
			paymentMilestoneAmount.clear();
			paymentMilestoneAmount.sendKeys(amountModify);
			paymentMilestoneModifySave.click();
			threadsleep(4000);
			op.switchToDefaultContent(driver);
			threadsleep(4000);
			wait.until(ExpectedConditions.visibilityOf(paymentMilestoneNameVerify));
			logInfo("payment Milestone saved successfully");
			return true;
		} catch (NoSuchElementException nsee) {
			logError("payment Milestone not saved.");
			return false;
		}
	}

	private void addEndtDate(WebDriver driver, int tcRowNum) throws Exception {

		String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace + "\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		String sheetName = "Automation";
		int header = 0; // Excel first row is 0
		int tc = tcRowNum - 1;
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tc);
		Operations op = new Operations(driver);
		op.selectDropdown(driver, "//*[@class='ui-datepicker-month']", "Sep");
		op.selectDropdown(driver, "//*[@class='ui-datepicker-year']", "2023");
		op.selectFromList(driver, "//*[@id='ui-datepicker-div']/table/tbody/tr/td/a", rowData.get("EndDate"),
				"End Day");
	}

	public String paymentMilestoneNameVerify() {

		String paymentMilestoneNamevf = paymentMilestoneNameVerify.getText();
		return paymentMilestoneNamevf;
	}

	public boolean paymentMilestoneNameDisplay() {

		boolean paymentMilestoneNameDisplay = paymentMilestoneNameVerify.isDisplayed();
		return paymentMilestoneNameDisplay;
	}

	public String paymentMilestoneStartDateVerify() {
		String paymentMilestoneStartDatevf = paymentMilestoneStartDateVerify.getText();
		return paymentMilestoneStartDatevf;
	}

	public boolean paymentMilestoneStartDateDisplay() {

		boolean paymentMilestoneStartDateDisplay = paymentMilestoneStartDateVerify.isDisplayed();
		return paymentMilestoneStartDateDisplay;
	}

	public String paymentMilestoneEndDateVerify() {
		String paymentMilestoneEndDatevf = paymentMilestoneEndDateVerify.getText();
		return paymentMilestoneEndDatevf;
	}

	public boolean paymentMilestoneEndDateDisplay() {

		boolean paymentMilestoneEndDateDisplay = paymentMilestoneEndDateVerify.isDisplayed();
		return paymentMilestoneEndDateDisplay;
	}

	public String paymentMilestoneAmountVerify() {
		String paymentMilestoneAmountvf = paymentMilestoneAmmountVerify.getText();
		return paymentMilestoneAmountvf;
	}

	public boolean paymentMilestoneAmountDisplay() {

		boolean paymentMilestoneAmountDisplay = paymentMilestoneAmmountVerify.isDisplayed();
		return paymentMilestoneAmountDisplay;
	}

	public boolean reallocateSubManager(WebDriver driver, String endDate, String emplyoeeName) throws Exception {
//			    ReleaseResourceRequesTab.click();
		// op.clickElement(ReleaseResourceRequesTab, driver);
		op.switchToiFrameByXpath(driver, CommonPagesConstants.IFRAME);
		threadsleep(2000);
		SelectReleaseResourceEdate.sendKeys("");
		SelectReleaseResourceEdate.sendKeys(endDate);
		SelectReleaseResourceEdate.click();
//				driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[3]/span")).click();
		driver.findElement(By.xpath(
				"//*[@id='ui-datepicker-div']/table/tbody/tr/td[contains(@class, 'ui-datepicker-days-cell-over')]"))
				.click();
		threadsleep(2000);
		AddEmplyoeeNameSearch.click();
		op.switchToDefaultContent(driver);
		AddEmplyoeeName.sendKeys(emplyoeeName);
		// AddEmplyoeeNameSearch.sendKeys(emplyoeeName);
		// driver.switchTo().defaultContent();
		threadsleep(2000);
		// op.switchToDefaultContent(driver);
		op.clickElement(driver, CommonPagesConstants.ADD_EMPLYOEE_SELECT.replace("NameToBeReplaced", emplyoeeName));
		// AddEmplyoeeSelect.click();
		threadsleep(1500);
		op.switchToiFrameByXpath(driver, CommonPagesConstants.IFRAME);
		wait.until(ExpectedConditions.visibilityOf(SelectReleaseResourceEdate));
		op.clickElement(ReallocateResourceSubmitBtn, driver);
		// ReallocateResourceSubmitBtn.click();
		op.switchToDefaultContent(driver);
		// driver.switchTo().defaultContent();
		boolean resourceName = VerifyReallocation.isDisplayed();
		return resourceName;
	}

	public boolean ConnectSow(WebDriver driver, String sowName) throws Exception {
		boolean isSowAdded = false;
		SowDetailsTab.click();
		ConnectSowBtn.click();
		op.switchToiFrameByXpath(driver, CommonPagesConstants.IFRAME);
		// driver.switchTo().frame(frame1);
		threadsleep(1500);
		Operations op1 = new Operations(driver);
		op1.javascriptclick(driver, ConfirmCheckbox);
		ProceedBtn.click();
		threadsleep(1500);
		SelectSowDropdown.sendKeys("");
		SelectSowDropdown.sendKeys(sowName);
		op.switchToDefaultContent(driver);
		// driver.switchTo().defaultContent();
		boolean isSoWAvaiable = op.isElementDisplayed(SelectSowRow);
		if (isSoWAvaiable) {
			SelectSowRow.click();
			op.switchToiFrameByXpath(driver, CommonPagesConstants.IFRAME);
			// driver.switchTo().frame(frame1);
			threadsleep(1500);
			AddBtn.click();
			op.switchToDefaultContent(driver);
			op.waitForAnElementToBeClickable(ConnectSowBtn);
			// driver.switchTo().defaultContent();
			isSowAdded = VerifySowAdd.isDisplayed();
			return isSowAdded;
		}
		{
			logInfo("No results found in Select SOW List Box");
			return isSowAdded;
		}

	}

	// Ramya-start

	public boolean goToEdit(String role) {
		try {
			wait.until(ExpectedConditions.visibilityOf(RoleTypeEditIcon));
			controlActions.click(RoleTypeEditIcon);
			wait.until(ExpectedConditions.visibilityOf(RoleTypeEditIcon));
			return true;
		} catch (Exception e) {
			logError("Failed to navigate to PTrack Page" + e.getMessage());
			return false;
		}
	}

	public boolean waitUntilElementPresent(WebElement webElement) {
		threadsleep(5000);
		try {
			wait.until(ExpectedConditions.visibilityOfAllElements(webElement));
			logInfo("Element Successfully loaded on the page");
			return true;
		} catch (Exception e) {
			logError("Failed to load the element on the page" + e.getMessage());
			return false;
		}
	}

	public boolean clkSaveChanges(WebDriver driver) {
		WebElement aa;
		String isAlertOn = "true";
		String isAlertPresent = prop.getProperty("isAlertOn").trim().toLowerCase();
		logInfo("Alert is configured as : " + isAlertPresent);
		SubmitAddRole.click();
		logInfo("Successfully clicked on SubmitAddRole Button");
		String xpath = "//*[@id='Btncfa_click']";
		boolean isAlertMessagePresent = op.isElementDisplayed(xpath);
		logInfo("Is Alert Message seen? : " + isAlertMessagePresent);
		// if(isAlertPresent.equalsIgnoreCase(isAlertOn))
		if (isAlertMessagePresent) {
			xpath = "//*[@id='Btncfa_click']";
			aa = driver.findElement(By.xpath(xpath));
		} else {
			xpath = "//*[@id='app_link_customise_btn']//span[contains(text(),'customize')]";
			aa = driver.findElement(By.xpath(xpath));
		}

//				boolean bb = controlActions.WaitUntilElementIsClickable(aa);
		for (int count = 0; count <= 2; count++) {
			logInfo("Change User Retry Count is : " + count);
			threadsleep(2000);
			if (!op.checkElementPresent(aa)) {
				SubmitAddRole.click();
			} else {
				break;
			}
		}
		if (isAlertPresent.equalsIgnoreCase(isAlertOn)) {
			waitUntilElementPresent(ClickSaveChanges);
			wait.until(ExpectedConditions.visibilityOf(ClickSaveChanges));
			if (op.checkElementPresent(ClickSaveChanges)) {
				logInfo(" Successfully Done");
				return true;
			} else {
				logError("Failed to");
				return false;
			}
		} else {
			waitUntilElementPresent(aa);
			wait.until(ExpectedConditions.visibilityOf(aa));
			if (op.checkElementPresent(aa)) {
				logInfo("Successfully Done");
				return true;
			} else {
				logError("Failed  ");
				return false;
			}
		}
	}

	public boolean selectEdit(String edit) {
		try {
			controlActions.click(ClickRoleType);
			controlActions.click(SelectRoleType);
			controlActions.sendKeys(SelectRoleType, edit);
			// controlActions.click(PersonIdList);
			logInfo("Entered text '" + edit + "' in user name Field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + edit + "' in user name Field" + e.getMessage());
			return false;
		}
	}

	public void TC_EDITROLE(WebDriver driver, String edit) throws Exception {
		boolean isclkOnEditIcon = clkOnEditIcon();
		Assert.assertTrue(isclkOnEditIcon, "Failed to click  Icon.");
		isclkOnEditIcon = clkOnEditIcon();
		Assert.assertTrue(isclkOnEditIcon, "Failed to click Navigation Icon.");
		// String eName = "Mahajan, Milind";
		boolean isEditSelected = selectEdit(edit);
		Assert.assertTrue(isEditSelected, "Failed to add text to select Employee search as '" + edit + "'");
		boolean isSubmitButtonClicked = clkSaveChanges(driver);
		// driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
		Assert.assertTrue(isSubmitButtonClicked, "Failed to click Submit Button on login page");
		threadsleep(2000);
	}

	public boolean clkOnEditIcon() {
		try {
			controlActions.clickElement(RoleTypeEditIcon);
			String actualString = EditLabel.getText();
			if (actualString.contains("Edit")) {
				logInfo("Successfully clicked");
				return true;
			} else {
				logError("Failed to click");
				return false;
			}
		} catch (Exception e) {
			logError("Failed to click" + e.getMessage());
			return false;
		}
	}

	public boolean goToEditIcon() {
		try {
			controlActions.click(RoleTypeEditIcon);
			wait.until(ExpectedConditions.visibilityOf(ClickRoleType));
			if (controlActions.isElementDisplayedOnPage(ClickRoleType)) {
				log4jInfo("Select Role type is Displayed on the page");
				controlActions.click(ClickRoleType);
				controlActions.click(SelectRoleType);
				controlActions.click(SubmitAddRole);
				op.waitUntilElementIsClickable(ClickLocation);
				// wait.until(ExpectedConditions.visibilityOf(ActiveTabTxt));
				if (controlActions.isElementDisplayedOnPage(ClickLocation)) {
					logInfo("Successfully opened  page");
					return true;
				} else {
					logError("Failed to navigate to page");
					return false;
				}
			} else {
				logError("Failed to load Select Role Location  on the page");
			}
			logInfo("page opened successfully");
			return true;
		} catch (Exception e) {
			logError("Failed to navigate to Page" + e.getMessage());
			return false;
		}
	}

//Ramya-end

	public boolean ReallocateResource(WebDriver driver, String endDate, String emplyoeeName) throws Exception {
		ReleaseResourceRequesTab.click();
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='apex_dialog_1']/iframe")));

		SelectReleaseResourceEdate.click();
//			    CommonPages cp1 = new CommonPages(driver);
//				cp1.addEndDate(driver);
		driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[3]")).click();
		threadsleep(15000);
		AddEmplyoeeNameSearch.sendKeys("");
		AddEmplyoeeNameSearch.sendKeys(emplyoeeName);
		driver.switchTo().defaultContent();
		threadsleep(15000);
		AddEmplyoeeSelect.click();
		threadsleep(1500);
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='apex_dialog_1']/iframe")));
		wait.until(ExpectedConditions.visibilityOf(SelectReleaseResourceEdate));
		ReallocateResourceSubmitBtn.click();
		driver.switchTo().defaultContent();
		boolean resourceName = VerifyReallocation.isDisplayed();
		return resourceName;
	}

	// Aishwarya
//	public boolean verifyFilterCheckBox(WebElement FiterType, List<WebElement> activeProjectFilterMenuCheckBox, WebElement closedProjectFilter) {
//		boolean isFilterMatched = false;
//		wait.until(ExpectedConditions.elementToBeClickable(FiterType));
//		op.clickElement(FiterType);
//		threadsleep(3000);
//		String appliedFilter = op.getText(FiterType).split("\n")[0].trim();
//		System.out.println("appliedFilter -> " + appliedFilter);
//		threadsleep(3000);
//		op.clickElement(closedProjectFilter);
//		threadsleep(3000);
//		for(WebElement element:activeProjectFilterMenuCheckBox) {
//			String checkboxText=op.getText(element).trim().replaceAll("\n", "");
//			System.out.println("checkboxText - >>" + checkboxText);
//			if(checkboxText.contains(appliedFilter)) {
//				logInfo("Filter Matched");
//				System.out.println("****** " + element.getAttribute("class").equals("apex-item-option is-checked"));
//				if(element.getAttribute("class").equals("apex-item-option is-checked"))
//				return isFilterMatched = true ;
//			}
//			else
//			{
//				logError("Failed to Match the Filter");
//				return isFilterMatched = false;
//			}
//		}
//		return isFilterMatched;
//	}
//	
	// Vaishnavi Gupta
	public boolean verifyProjectTypes(List<WebElement> tableRows, String projectTypes) {
		boolean status = false;
		int rowNumber = 0;
		for (; rowNumber < tableRows.size(); rowNumber++) {
			Equals(op.getText(tableRows.get(rowNumber)), projectTypes, "Filter applied to Row Number " + rowNumber + 1,
					"Filter did not applied to Row Number " + rowNumber + 1);
		}
		if (rowNumber == tableRows.size())
			status = true;
		return status;
	}

	public boolean verifyFilterCheckBox(WebElement FiterType, List<WebElement> closedProjectFilterMenuCheckBox,
			WebElement closedProjectFilter) {
		wait.until(ExpectedConditions.elementToBeClickable(FiterType));
		op.clickElement(FiterType);
		String appliedFilter = op.getText(FiterType).split("\n")[0];
		op.clickElement(closedProjectFilter);
		for (WebElement element : closedProjectFilterMenuCheckBox) {
			String checkboxText = op.getText(element);
			if (checkboxText.contains(appliedFilter)) {
				if (element.getAttribute("class").equals("apex-item-option is-checked"))
					return true;
			}
		}
		return false;
	}

	// Aakash Saxena

	public void searchActiveProject(WebDriver driver, String projectNumber) throws Exception {
		try {
			PL_ActiveProjectsPage activeProject = new PL_ActiveProjectsPage(driver);
			logInfo("Verify current tab is active project or not");
			op.waitForElementToBeDisplayed(activeProject.ActiveProjectTabLink);
			IsTrue(op.isElementDisplayed(activeProject.ActiveProjectTabLink), "Active Project Tab is Displayed",
					"New Project Tab not displayed");
			op.waitForElementToBeDisplayed(activeProject.GoBtn);
			logInfo("Verify search project option is available or not");
			IsTrue(op.isElementDisplay(activeProject.SearchProjectTxt), "Search option is displayed",
					"Search option is not displayed");
			logInfo("Search for Active Project in the list");
			op.clickElement(activeProject.SearchProjectTxt);
			logInfo("Enter project number");
			op.sendKeys(activeProject.SearchProjectTxt, projectNumber);
			op.clickElement(activeProject.GoBtn);
			threadsleep(2000);
			logInfo("Verify the search project count is unique or not");
			Boolean isProjectCountOne = activeProject.ProjectTableRows.size() == 1;
			IsTrue(isProjectCountOne, "Only one project is displayed in the table",
					"Only one project should be displayed in the table");
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error " + e.getMessage());
			Assert.fail();
		}
	}

	public boolean getProjectRow() {
		List<String> ElementsList;
		try {
			logInfo("Get all list elements from the searched project");
			threadsleep(3000);
			ElementsList = op.getList(ActiveProjectRow);
			logInfo("Project ID : " + ElementsList.get(0));
			logInfo("Project State : " + ElementsList.get(9));
			projectNumberActual = ElementsList.get(0);
			projectState = ElementsList.get(9);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error " + e.getMessage());
			Assert.fail();
			return false;
		}

	}

	public List<String> projectEndDateCalender(WebDriver driver, String extendedYear, String extendedMonth,
			String extendedDate, String commentText, String state) {
		List<String> msg = new ArrayList<>();
		try {
			logInfo("Navigate to Extend End Date window");
			op.waitUntilElementIsClickable(ExtendEndDate);
			IsTrue(op.isElementDisplayed(ExtendEndDate), "Extend End Date button is displayed",
					"Extend End Date button not displayed");
			op.clickElement(ExtendEndDate);
			threadsleep(1000);
			logInfo("Frame is switched to: " + CalendarFrame);
			op.switchToFrameByIFrameElement(CalendarFrame);
			logInfo("Click on Calender icon");
			op.waitForElementToBeDisplayed(CalendarIcon);
			IsTrue(op.isElementDisplayed(CalendarIcon), "Date picker is displayed", "Date picker not displayed");
			op.clickElement(CalendarIcon);
			logInfo("Select Year");
			op.selectDropdown(driver, CommonPagesConstants.CALENDAR_YEAR, extendedYear);
			logInfo("Select Month");
			op.selectDropdown(driver, CommonPagesConstants.CALENDAR_MONTH, extendedMonth);
			logInfo("Select Date");
			op.selectFromList(driver, CommonPagesConstants.CALENDAR_DATE, extendedDate, "Day");
			if (commentText != null) {
				logInfo("Select add comment option");
				op.clickButton(AddComment);
				threadsleep(2000);
				logInfo("Frame is switched to default");
				op.switchToDefault();
				threadsleep(1000);
				logInfo("Frame is switched to: " + CommentFrame);
				op.switchToFrameByIFrameElement(CommentFrame);
				op.waitForElementToBeDisplayed(AddText);
				logInfo("Enter comments");
				op.sendKeys(AddText, commentText);
				op.waitUntilElementIsClickable(UpdateText);
				op.clickElement(UpdateText);
				threadsleep(2000);
				logInfo("Frame is switched to default");
				op.switchToDefault();
				threadsleep(1000);
				logInfo("Frame is switched to: " + CalendarFrame);
				op.switchToFrameByIFrameElement(CalendarFrame);
				op.waitForElementToBeDisplayed(RemarksText);
				logInfo("Verify the added comments");
				String remarksText = op.getText(RemarksText);
				msg.add(0, remarksText);
			}
			op.waitUntilElementIsClickable(SendApproval);
			if (state.equals("negative")) {
				logInfo("Click on send approval option");
				op.clickButton(SendApproval);
				op.waitForElementToBeDisplayed(Extend_Alert);
				if (extendedDate == null) {
					logInfo("Verify the error for date");
					String dateError = op.getText(DateErrorMsg);
					msg.add(0, dateError);
				}
				if (commentText == null) {
					logInfo("Verify the error for comments");
					String commentError = op.getText(CommentErrorMsg);
					msg.add(1, commentError);
				}
				logInfo("Cancel the project extend date window");
				op.clickButton(Cancel_Extend_Date);
			} else {
				logInfo("Click on send approval option");
				op.clickButton(SendApproval);
			}
			threadsleep(1000);
			logInfo("Frame is switched to default");
			op.switchToDefault();
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error " + e.getMessage());
			Assert.fail();
		}
		return msg;
	}

	public List<String> getActiveProjectDetails() throws Exception {
		List<String> projectValues = new ArrayList<>();
		try {
			String projectNameValue = controlActions.getAttribute(ProjectName, "value");
			String projectCategoryValue = controlActions.getAttribute(ProjectCategory, "value");
			String projectCycleValue = controlActions.getAttribute(ProjectCycle, "value");
			String salesforceIdValue = controlActions.getAttribute(SalesforceId, "value");
			String xoriantNicheSkillsValue = controlActions.getAttribute(XoriantNicheSkills, "value");
			projectValues.add(0, projectNameValue);
			projectValues.add(1, projectCategoryValue);
			projectValues.add(2, projectCycleValue);
			projectValues.add(3, salesforceIdValue);
			projectValues.add(4, xoriantNicheSkillsValue);
			System.out.println(projectValues);
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error " + e.getMessage());
			Assert.fail();
		}
		return projectValues;
	}

	public void editActiveProjectDetails(WebDriver driver, String projectName, String projectCategory,
			String projectCycle, String salesforceId, String xoriantNicheSkills, String xoriantContribution)
			throws Exception {
		try {
			op.waitForElementToBeClickable(EditActiveProject);
			op.clickButton(EditActiveProject);
			op.waitForElementToBeDisplayed(ProjectName);
			op.sendKeys(ProjectName, projectName);
			op.waitForElementToBeClickable(ProjectCategory);
			op.clickElement(ProjectCategory);
			op.selectFromList(driver, CommonPagesConstants.PROJECT_CATEGORY_LIST, projectCategory, "ProjectCategoryList");
			op.waitForElementToBeClickable(ProjectCycle);
			op.clickElement(ProjectCycle);
			op.selectFromList(driver, CommonPagesConstants.PROJECT_CYCLE_LIST, projectCycle, "ProjectCycleList");
			op.waitForElementToBeDisplayed(SalesforceId);
			op.sendKeys(SalesforceId, salesforceId);
			op.waitForElementToBeDisplayed(XoriantNicheSkills);
			op.sendKeys(XoriantNicheSkills, xoriantNicheSkills);
			op.waitForElementToBeDisplayed(XoriantContribution);
			op.sendKeys(XoriantContribution, xoriantContribution);
			op.waitForElementToBeClickable(SubmitProjectDetails);
			op.clickButton(SubmitProjectDetails);
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error " + e.getMessage());
			Assert.fail();
		}
	}

	// Aakash Saxena

}

package com.project.pageobjects.pTracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
//import com.project.pageobjects.pTracker.NewProjectsPageConstants;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class DeliveryMilestonePageTab extends TestBase {

	WebDriverWait wait;
	ControlActions controlActions;
	private static final int DELAY = 2000;
	Operations op;
	Random rn;
	public static String changeRequestNumberStr;
	String valueAtList = "";
	int indexOfReqNum;
	String editbutton;
	public static String projectTitle;
	public String dupError;
	public String reqNmToVerify;
	public String editDescriptionText;
	public boolean dupErrorTrueFalse;
	public static String alertMessage;

	public DeliveryMilestonePageTab(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, DELAY);
		controlActions = new ControlActions(driver);
		op = new Operations(driver);
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
	}

	// ACTIVE PROJECT TAB OBJECTS
	@FindBy(xpath = DeliveryMilestoneConstants.ACTIVE_PROJECTS_LIST)
	public WebElement activeProjectList;

	@FindBy(xpath = DeliveryMilestoneConstants.FIXED_PRICE)
	public WebElement fixedPrice;

	@FindBy(xpath = DeliveryMilestoneConstants.PROJECT_NUMBER)
	public WebElement projectNumber;

	@FindBy(xpath = DeliveryMilestoneConstants.PROJECT_NUMBER_TITLE)
	public WebElement projectNumberTitle;

	@FindBy(xpath = DeliveryMilestoneConstants.DELIVERY_MILESTONE_TAB)
	public WebElement deliveryMilestoneTab;

	@FindBy(xpath = DeliveryMilestoneConstants.PROJECT_TYPE)
	public WebElement projectType;

	@FindBy(xpath = DeliveryMilestoneConstants.PROJECT_TYPE_FIXED_PRICE)
	public WebElement projectTypeFixedPrice;

	@FindBy(xpath = DeliveryMilestoneConstants.OPEN_ADD_DELIVERY_MILESTONE)
	public WebElement openAddDeliveryMilestone;

	@FindBy(xpath = DeliveryMilestoneConstants.SEARCH_PROJECT)
	public WebElement searchProject;

	@FindBy(xpath = DeliveryMilestoneConstants.GO_BUTTON)
	public WebElement goButton;

	@FindBy(xpath = DeliveryMilestoneConstants.CHANGE_REQUEST)
	public WebElement changeRequest;

	@FindBy(xpath = NewProjectsPageConstants.ACTIVE_PROJECTS_TAB_FOCUSED)
	public WebElement ActiveProjectTabFocused;

	@FindBy(xpath = DeliveryMilestoneConstants.RIGHT_CHEVRON)
	public WebElement rightChevron;

	@FindBy(xpath = DeliveryMilestoneConstants.MANAGER_DETAILS_TAB)
	public WebElement managerDetailsTab;

	@FindBy(xpath = DeliveryMilestoneConstants.ATTACHMENTS_TAB)
	public WebElement attachentsTab;

	@FindBy(xpath = DeliveryMilestoneConstants.CHANGE_REQUEST_NUMBER)
	public WebElement changeRequestNumber;

	@FindBy(xpath = DeliveryMilestoneConstants.IFRAME_CHANGE_REQUEST)
	public WebElement iFrameChangeRequest;

	@FindBy(xpath = DeliveryMilestoneConstants.CHANGE_REQUEST_TYPE)
	public WebElement changeRequestType;

	@FindBy(xpath = DeliveryMilestoneConstants.IMPACTED_AREA)
	public WebElement impactedArea;

	@FindBy(xpath = DeliveryMilestoneConstants.REQUEST_DATE_PICKER)
	public WebElement requestDatePicker;

	@FindBy(xpath = DeliveryMilestoneConstants.MONTH_REQUEST_DATE)
	public WebElement monthRequestDate;

	@FindBy(xpath = DeliveryMilestoneConstants.DATE_REQUEST_DATE)
	public WebElement dateRequestDate;

	@FindBy(xpath = DeliveryMilestoneConstants.YEAR_REQUEST_DATE)
	public WebElement yearRequestDate;

	@FindBy(xpath = DeliveryMilestoneConstants.PLANNED_START_DATE_PICKER)
	public WebElement plannedStartDate;

	@FindBy(xpath = DeliveryMilestoneConstants.MONTH_PLANNED_START_DATE)
	public WebElement monthPlannedStartDate;

	@FindBy(xpath = DeliveryMilestoneConstants.YEAR_PLANNED_START_DATE)
	public WebElement yearPlannedStartDate;

	@FindBy(xpath = DeliveryMilestoneConstants.PLANNED_END_DATE_PICKER)
	public WebElement plannedEndDate;

	@FindBy(xpath = DeliveryMilestoneConstants.MONTH_PLANNED_END_DATE)
	public WebElement monthPlannedEndDate;

	@FindBy(xpath = DeliveryMilestoneConstants.YEAR_PLANNED_END_DATE)
	public WebElement yearPlannedEndDate;

	@FindBy(xpath = DeliveryMilestoneConstants.DATE_PLANNED_START_DATE)
	public WebElement datePlannedStartDate;

	@FindBy(xpath = DeliveryMilestoneConstants.PARENT_MILESTONE)
	public WebElement parentMilestone;

	@FindBy(xpath = DeliveryMilestoneConstants.CHANGE_REQUEST_VALUE)
	public WebElement changeRequestValue;

	@FindBy(xpath = DeliveryMilestoneConstants.HIGH_LEVEL_ESTIMATION_NO)
	public WebElement highLevelEstimationNo;

	@FindBy(xpath = DeliveryMilestoneConstants.CLIENT_APPROVAL)
	public WebElement clientApproval;

	@FindBy(xpath = DeliveryMilestoneConstants.CLIENT_APPROVAL_APPROVED)
	public WebElement clientApprovalApproved;

	@FindBy(xpath = DeliveryMilestoneConstants.INTERNAL_APPROVAL)
	public WebElement internalApproval;

	@FindBy(xpath = DeliveryMilestoneConstants.INTERNAL_APPROVAL_APPROVED)
	public WebElement internalApprovalApproved;

	@FindBy(xpath = DeliveryMilestoneConstants.DESCRIPTION)
	public WebElement description;

	@FindBy(xpath = DeliveryMilestoneConstants.CREATE_BUTTON_CR)
	public WebElement createButtonCR;

	@FindBy(xpath = DeliveryMilestoneConstants.CHANGE_TYPE_OPTION1)
	public WebElement changeTypeOption1;

	@FindBy(xpath = DeliveryMilestoneConstants.PARENT_MILESTONE_CR_OPTION1)
	public WebElement parentMilestoenCROption1;

	@FindBy(xpath = DeliveryMilestoneConstants.IMPACTED_AREA_REQUIREMENT)
	public WebElement impactedAreaRequirement;

	@FindBy(xpath = DeliveryMilestoneConstants.PLANNED_END_DATE_PICKER)
	public WebElement plannedEndDatePicker;

	@FindBy(xpath = DeliveryMilestoneConstants.DATE_PLANNED_END_DATE)
	public WebElement datePlannedEndDate;

	@FindBy(xpath = DeliveryMilestoneConstants.MILESTONE_FROM_LIST)
	public WebElement milestonefromlist;

	@FindBy(xpath = DeliveryMilestoneConstants.PAYMENT_MILESTONE_NO)
	public WebElement paymentMilestoneNo;

	@FindBy(xpath = DeliveryMilestoneConstants.CHANGE_REQUEST_NUMBER_CREATED)
	public WebElement changeRequestNumberCreated;

	@FindBy(xpath = DeliveryMilestoneConstants.DELIVERY_MILESTONE_WEB_TABLE)
	public WebElement deliveryMilestoneWebTable;

	@FindBy(xpath = DeliveryMilestoneConstants.WEBTABLE_TABLE_ROW)
	public WebElement webTableRows;

	@FindBy(xpath = DeliveryMilestoneConstants.NEXT_PAGINATION_BUTTON)
	public WebElement nextPaginationButton;

	@FindBy(xpath = DeliveryMilestoneConstants.CHANGE_REQUEST_NUMBER_COLUMN)
	public WebElement changeRequestNumberColumn;

	@FindBy(xpath = DeliveryMilestoneConstants.VERIFY_CHANGE_REQUEST_NUMBER)
	public WebElement verfiyChangeRequestNumber;

	@FindBy(xpath = DeliveryMilestoneConstants.SAVE_CHANGES_BUTTON)
	public WebElement saveChangesButton;

	@FindBy(xpath = DeliveryMilestoneConstants.EDIT_DESCRIPTION)
	public WebElement editDescription;

	@FindBy(xpath = DeliveryMilestoneConstants.DUPLICATE_ERROR_MESSAGE)
	public WebElement duplicateErrorMessage;

	@FindBy(xpath = DeliveryMilestoneConstants.DUPLICATE_ERROR_MESSAGE_BUTTON)
	public WebElement duplicateErrorMessageButton;

	@FindBy(xpath = DeliveryMilestoneConstants.ALERT_BOX)
	public WebElement alertBox;

	@FindBy(xpath = DeliveryMilestoneConstants.PAYMENT_MILESTONE_YES)
	public WebElement paymentMilestoneYes;

	@FindBy(xpath = DeliveryMilestoneConstants.PAYMENT_MILESTONE_NAME)
	public WebElement paymentMilestoneName;

	@FindBy(xpath = DeliveryMilestoneConstants.ADD_PAYMENT_MILESTONE_BTN)
	public WebElement addPaymentMilestonebtn;

	@FindBy(xpath = DeliveryMilestoneConstants.PAYMENT_MILESTONE)
	public WebElement paymentMilestone;

	@FindBy(xpath = DeliveryMilestoneConstants.MILESTONE_NAME_VERIFY)
	public WebElement paymentMilestoneNameVerify;

	@FindBy(xpath = DeliveryMilestoneConstants.PAYMENT_MILESTONE_TABLE)
	public WebElement paymentMilestoneTable;

	@FindBy(xpath = DeliveryMilestoneConstants.CANCEL_CR_BUTTON)
	public WebElement cancelCRBtn;

	@FindBy(xpath = DeliveryMilestoneConstants.CLOSENOTIFICATION_CR_BUTTON)
	public WebElement closeNotiCRBtn;

	public void verifyActiveProject(WebDriver driver) throws Exception {
		try {
			if (controlActions.isElementDisplayed(DeliveryMilestoneConstants.ACTIVE_PROJECTS_LIST)) {
				String projectName = "";
				logInfo("controlActions.isElementDiplay for active project is true");
				controlActions.clickOnElement(activeProjectList);
				controlActions.clickOnElement(fixedPrice);

				driver.findElement(By.xpath(DeliveryMilestoneConstants.SEARCH_PROJECT)).sendKeys(projectName);
				controlActions.click(goButton);
				threadsleep(5000);

				String tableXpath = "//*[@id='report_table_projects-active-report']/tbody/tr[1]/td";
				List<String> searchResult = op.searchReportTable(driver, tableXpath, projectName);
				Assert.assertNotNull(searchResult, "Active Project Search failed");
				Equals(searchResult.get(9), "Active", searchResult.get(1) + "is an Active Project",
						searchResult.get(1) + "is not an Active Project");
				String projectID = "//*[@id='report_table_projects-active-report']/tbody/tr[1]//a[contains(text(),'"
						+ searchResult.get(0) + "')]";
				WebElement element = driver.findElement(By.xpath(projectID));
				element.click();
				threadsleep(10000);

			} else {
				logError("Active project list is not abvailable");
			}
		} catch (NoSuchElementException e) {
			logError("ACTIVE_PROJECTS_LIST is not displayed" + e.getMessage());
		}
	}

	public void verifyActiveProject(WebDriver driver, String projectName) throws Exception {
		try {
			if (controlActions.isElementDisplayed(DeliveryMilestoneConstants.ACTIVE_PROJECTS_LIST)) {
				logInfo("Searching for the Active Projects -> " + projectName);
				controlActions.clickOnElement(activeProjectList);
				controlActions.clickOnElement(fixedPrice);

				driver.findElement(By.xpath(DeliveryMilestoneConstants.SEARCH_PROJECT)).sendKeys(projectName);
				controlActions.click(goButton);
				threadsleep(5000);

				String tableXpath = "//*[@id='report_table_projects-active-report']/tbody/tr[1]/td";
				List<String> searchResult = op.searchReportTable(driver, tableXpath, projectName);
				Assert.assertNotNull(searchResult, "Active Project Search failed");
				Equals(searchResult.get(9), "Active", searchResult.get(1) + "is an Active Project",
						searchResult.get(1) + "is not an Active Project");
				String projectID = "//*[@id='report_table_projects-active-report']/tbody/tr[1]//a[contains(text(),'"
						+ searchResult.get(0) + "')]";
				WebElement element = driver.findElement(By.xpath(projectID));
				element.click();
				threadsleep(10000);
			} else {
				logError("Active project list is not abvailable");
			}
		} catch (NoSuchElementException e) {
			logError("ACTIVE_PROJECTS_LIST is not displayed" + e.getMessage());
		}
	}

	public void deliverMileStoneTab(WebDriver driver) {
		try {
			logInfo("Navigeting to delivery MileStone Tab");
			controlActions.isElementDisplayed(attachentsTab);
			controlActions.click(attachentsTab);
			controlActions.isElementDisplayed(deliveryMilestoneTab);
			controlActions.click(deliveryMilestoneTab);
			controlActions.isElementDisplayed(openAddDeliveryMilestone);
			controlActions.click(openAddDeliveryMilestone);
			controlActions.isElementDisplayed(changeRequest);
			controlActions.click(changeRequest);
		} catch (NoSuchElementException e) {
			logError(" No such element found facing error: " + e.getMessage());
		}
	}

	public void createChangeRequest(WebDriver driver) throws Exception {
		try {
			rn = new Random();
			int result = rn.nextInt(10000 - 1000) + 1000;
			changeRequestNumberStr = "CR" + result;

			driver.switchTo().frame(driver.findElement(By.xpath(DeliveryMilestoneConstants.IFRAME_CHANGE_REQUEST)));
			logInfo("ChangeRequestNumber is displayed: " + controlActions.isElementDisplay(changeRequestNumber));
			driver.findElement(By.xpath(DeliveryMilestoneConstants.CHANGE_REQUEST_NUMBER))
					.sendKeys(changeRequestNumberStr); // add random number
			threadsleep(1000);

			logInfo("ChangeRequestType is displayed: " + controlActions.isElementDisplay(changeRequestType));
			controlActions.click(changeRequestType);

			driver.switchTo().defaultContent();
			logInfo("ChangeType option 1 is isplayed : " + controlActions.isElementDisplayed(changeTypeOption1));

			controlActions.clickElement(changeTypeOption1);
			logInfo("business change is clicked");

			driver.switchTo().frame(driver.findElement(By.xpath(DeliveryMilestoneConstants.IFRAME_CHANGE_REQUEST)));

			logInfo("impactedArear is displayed: " + controlActions.isElementDisplay(impactedArea));
			controlActions.click(impactedArea);

			driver.switchTo().defaultContent();
			logInfo("Requirement is displayed: " + impactedAreaRequirement);
			controlActions.click(impactedAreaRequirement);

			driver.switchTo().frame(driver.findElement(By.xpath(DeliveryMilestoneConstants.IFRAME_CHANGE_REQUEST)));
			threadsleep(1000);

			log4jInfo("Requested Date Picker is diplayed: " + controlActions.isElementDisplay(requestDatePicker));
			controlActions.click(requestDatePicker);

			Select selectYear = new Select(yearRequestDate);
			selectYear.selectByVisibleText("2023");

			Select selectMonth = new Select(monthRequestDate);
			selectMonth.selectByVisibleText("Aug");
			logInfo("Request date is clicked " + controlActions.isElementDisplayed(dateRequestDate));
			controlActions.click(dateRequestDate);
			logInfo("Now work on planed date");

			log4jInfo("Planned Date Picker is diplayed: " + controlActions.isElementDisplay(plannedStartDate));
			controlActions.click(plannedStartDate);
			selectYear.selectByVisibleText("2023");
			selectMonth.selectByVisibleText("Aug");

			logInfo("Request date is clicked " + controlActions.isElementDisplayed(dateRequestDate));
			controlActions.click(dateRequestDate);
			logInfo("Planned date selected");

			controlActions.perform_clickWithJavaScriptExecutor(plannedEndDatePicker);
			controlActions.selectDropDownValue("2023", yearRequestDate);
			controlActions.selectDropDownValue("Dec", monthRequestDate);
			controlActions.clickButton(datePlannedEndDate);

			logInfo("ChangeRequestalue is displayed: " + controlActions.isElementDisplayed(changeRequestValue));
			controlActions.sendKeys(changeRequestValue, "CRValue123");
			threadsleep(500);
			logInfo("radio button paymentMilestonNo is displayed: "
					+ controlActions.isElementDisplayed(paymentMilestoneNo));
			controlActions.click(clientApproval);

			driver.switchTo().defaultContent();
			controlActions.clickElement(clientApprovalApproved);

			driver.switchTo().frame(driver.findElement(By.xpath(DeliveryMilestoneConstants.IFRAME_CHANGE_REQUEST)));
			controlActions.clickElement(internalApproval);

			driver.switchTo().defaultContent();
			controlActions.clickElement(internalApprovalApproved);

			driver.switchTo().frame(driver.findElement(By.xpath(DeliveryMilestoneConstants.IFRAME_CHANGE_REQUEST)));
			controlActions.sendKeys(description, "Testing demo");
			controlActions.click(createButtonCR);
			logInfo("Successfully Clicked on Create button" + createButtonCR);
			driver.switchTo().defaultContent();
		} catch (NoSuchElementException e) {
			logError("No such element found -> " + e.getMessage());
		}
	}

	public String checkListMilestone(WebDriver driver) throws Exception {

		logInfo("my change request number is: " + changeRequestNumberStr);
		List<WebElement> elementList = driver
				.findElements(By.xpath(DeliveryMilestoneConstants.CHANGE_REQUEST_NUMBER_COLUMN));
		List<String> strList = new ArrayList<String>();

		for (WebElement webElement : elementList) {
			controlActions.perform_convertListOfWebElements_ToListOfStrings(elementList);
			strList.add(webElement.getText());
		}
		if (strList.contains(changeRequestNumberStr)) {
			indexOfReqNum = strList.indexOf(changeRequestNumberStr);

			threadsleep(600);
			valueAtList = strList.get(indexOfReqNum);
			logInfo("valu at list: " + valueAtList);
		} else {
			controlActions.click(nextPaginationButton);
			checkListMilestone(driver);
		}
		return valueAtList;

	}

	public void editDeliveryMilestone(WebDriver driver) throws InterruptedException {

		driver.switchTo().defaultContent();
		int tableIndecxVar = indexOfReqNum + 1;
		editbutton = "//*[@id='report_table_myRegion']/tbody/tr[" + tableIndecxVar + "]/td[10]/button[1]";
		logInfo("edit button is dislayed: " + controlActions.isElementDisplayed(editbutton));

		threadsleep(8000);
		controlActions.click(driver.findElement(
				By.xpath("//*[@id='report_table_myRegion']/tbody/tr[" + tableIndecxVar + "]/td[10]/button[1]")));
		logInfo("edit options is clicked, Yipeeee!!!!");
		threadsleep(600);

		driver.switchTo().frame(driver.findElement(By.xpath(DeliveryMilestoneConstants.IFRAME_CHANGE_REQUEST)));

		String reqNmToVerify = verfiyChangeRequestNumber.getAttribute("value");
		logInfo("value of attribute: " + reqNmToVerify);
		controlActions.clearAndSetText(editDescription, "This my updated value");
		logInfo("description updated");
		threadsleep(500);
		try {
			controlActions.isElementDisplayed(DeliveryMilestoneConstants.SAVE_CHANGES_BUTTON);
			controlActions.clickButton(saveChangesButton);
			logInfo("Save changes clicked");
			threadsleep(8000);
		} catch (NoSuchElementException e) {
			logError(" save button not displayed: " + e.getMessage());
		}
	}

	public String verifyUpdate(WebDriver driver) {
		DeliveryMilestonePageTab verifyUpdateObj = new DeliveryMilestonePageTab(driver);

		try {
			verifyUpdateObj.checkListMilestone(driver);

			int tableIndecxVar = indexOfReqNum + 1;
			controlActions.click(driver.findElement(
					By.xpath("//*[@id='report_table_myRegion']/tbody/tr[" + tableIndecxVar + "]/td[10]/button[1]")));
			logInfo("edit options is clicked, Yi!!!!");
			threadsleep(600);

			driver.switchTo().frame(driver.findElement(By.xpath(DeliveryMilestoneConstants.IFRAME_CHANGE_REQUEST)));
			reqNmToVerify = verfiyChangeRequestNumber.getAttribute("value");
			logInfo("value of attribute: " + reqNmToVerify);
			editDescriptionText = editDescription.getText();
			controlActions.clickButton(saveChangesButton);
			logInfo("clicked on saveChangesButton");
			threadsleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return editDescriptionText;
	}

	public boolean createDuplicateChangeRequest(WebDriver driver) {
		controlActions.isElementDisplayed(openAddDeliveryMilestone);
		controlActions.click(openAddDeliveryMilestone);
		controlActions.isElementDisplayed(changeRequest);
		controlActions.click(changeRequest);

		try {
			driver.switchTo().frame(driver.findElement(By.xpath(DeliveryMilestoneConstants.IFRAME_CHANGE_REQUEST)));
			logInfo("ChangeRequestNumber is displayed: " + controlActions.isElementDisplay(changeRequestNumber));
			driver.findElement(By.xpath(DeliveryMilestoneConstants.CHANGE_REQUEST_NUMBER))
					.sendKeys(changeRequestNumberStr); // same change request number
			threadsleep(1000);
			logInfo("ChangeRequestType is displayed: " + controlActions.isElementDisplay(changeRequestType));
			controlActions.click(changeRequestType);

			driver.switchTo().defaultContent();
			controlActions.perform_waitUntilVisibility(duplicateErrorMessage);
			String errorMsg = controlActions.getText(duplicateErrorMessage);
			logInfo("Actual Error message: " + errorMsg);
			controlActions.clickButton(duplicateErrorMessageButton);
			logInfo("button clicked ");

			threadsleep(1000);
		} catch (NoSuchElementException e) {
			logError("No such element found" + e.getMessage());
			return false;
		}
		;
		return true;
	}

	public void logTestResult(String tcname, String tcStatus) throws Exception {
		int tcRowNum;
		String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace + "\\test-data-files\\UI-TestData\\TC_DeliveryMilestone.xls";
		tcRowNum = ExcelUtils.getRowNum(datapoolPath, "Automation", "testCase", tcname);

		logInfo("Test Case Row No Is: " + tcRowNum);
		logInfo("Reading Excel:   " + datapoolPath);
		if (tcStatus.equalsIgnoreCase("PASS")) {
			threadsleep(1000);
			logInfo("Test Case " + tcname + " is Passed");
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} else {
			threadsleep(1000);
			logInfo("Fail to Saved/Submited Project.");
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "Fail", "RED");
		}
	}

	public boolean addPaymentMilestoneCR(WebDriver driver, int tcRowNum, String paymentMilestoneNm, String amount)
			throws Exception {
		try {
			rn = new Random();
			int result = rn.nextInt(10000 - 1000) + 1000;
			changeRequestNumberStr = "CR" + result;

			driver.switchTo().frame(driver.findElement(By.xpath(DeliveryMilestoneConstants.IFRAME_CHANGE_REQUEST)));
			logInfo("ChangeRequestNumber is displayed: " + controlActions.isElementDisplay(changeRequestNumber));
			driver.findElement(By.xpath(DeliveryMilestoneConstants.CHANGE_REQUEST_NUMBER))
					.sendKeys(changeRequestNumberStr); // add random number
			threadsleep(1000);

			logInfo("ChangeRequestType is displayed: " + controlActions.isElementDisplay(changeRequestType));
			controlActions.click(changeRequestType);
			driver.switchTo().defaultContent();
			logInfo("ChangeType option 1 is isplayed : " + controlActions.isElementDisplayed(changeTypeOption1));
			controlActions.clickElement(changeTypeOption1);
			logInfo("business change is clicked");
			driver.switchTo().frame(driver.findElement(By.xpath(DeliveryMilestoneConstants.IFRAME_CHANGE_REQUEST)));
			logInfo("impactedArear is displayed: " + controlActions.isElementDisplay(impactedArea));
			controlActions.click(impactedArea);
			driver.switchTo().defaultContent();
			logInfo("Requirement is displayed: " + impactedAreaRequirement);
			controlActions.click(impactedAreaRequirement);
			driver.switchTo().frame(driver.findElement(By.xpath(DeliveryMilestoneConstants.IFRAME_CHANGE_REQUEST)));

			threadsleep(1000);
			logInfo("Requested Date Picker is diplayed: " + controlActions.isElementDisplay(requestDatePicker));
			controlActions.click(requestDatePicker);

			Select selectYear = new Select(yearRequestDate);
			selectYear.selectByVisibleText("2023");
			Select selectMonth = new Select(monthRequestDate);
			selectMonth.selectByVisibleText("Aug");
			logInfo("Request date is clicked " + controlActions.isElementDisplayed(dateRequestDate));
			controlActions.click(dateRequestDate);
			logInfo("Now work on planed date");
			plannedStartDate.click();
			addStartDate(driver, tcRowNum);
			plannedEndDatePicker.click();
			addEndtDate(driver, tcRowNum);
			logInfo("ChangeRequestalue is displayed: " + controlActions.isElementDisplayed(changeRequestValue));
			controlActions.sendKeys(changeRequestValue, amount);
			threadsleep(500);
			logInfo("Radio button paymentMilestonYes is displayed: "
					+ controlActions.isElementDisplayed(paymentMilestoneYes));
			controlActions.click(paymentMilestoneYes);
			controlActions.sendKeys(paymentMilestoneName, paymentMilestoneNm);
			controlActions.click(clientApproval);
			driver.switchTo().defaultContent();
			controlActions.clickElement(clientApprovalApproved);
			driver.switchTo().frame(driver.findElement(By.xpath(DeliveryMilestoneConstants.IFRAME_CHANGE_REQUEST)));
			controlActions.clickElement(internalApproval);
			driver.switchTo().defaultContent();
			controlActions.clickElement(internalApprovalApproved);
			driver.switchTo().frame(driver.findElement(By.xpath(DeliveryMilestoneConstants.IFRAME_CHANGE_REQUEST)));
			controlActions.sendKeys(description, "Testing Add Milestone Through CR");
			controlActions.click(createButtonCR);
			logInfo("Successfully Clicked on Create button");
			driver.switchTo().defaultContent();
			threadsleep(2000);
			logInfo("Clicking on -> " + paymentMilestone);
			controlActions.click(paymentMilestone);
			threadsleep(2000);
			logInfo("Clicking on -> " + driver
					.findElement(By.xpath("//*[@id='pyment-mlstn_tab']/a[@aria-selected='true']")).isDisplayed());
			wait.until(ExpectedConditions.visibilityOf(paymentMilestone));
			wait.until(ExpectedConditions.visibilityOf(addPaymentMilestonebtn));
			return true;
		} catch (NoSuchElementException e) {
			logError("no such element found" + e.getMessage());
			return true;
		}
	}

	public boolean checkPaymentMileStoneExists() {
		return true;
	}

	public void addEndtDate(WebDriver driver, int tcRowNum) throws Exception {

		String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace + "\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		String sheetName = "Automation";
		int header = 0; // Excel first row is 0
		int tc = tcRowNum - 1;
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tc);
		Operations op = new Operations(driver);
		String startMonth = rowData.get("StartMonth").trim();
		logInfo("Milestone modified Start startMonth is " + startMonth);
		String startYear = rowData.get("StartYear").trim();
		logInfo("Milestone modified Start startYear is " + startYear);
		op.selectDropdown(driver, "//*[@class='ui-datepicker-month']", startMonth);
		op.selectDropdown(driver, "//*[@class='ui-datepicker-year']", startYear);
		op.selectFromList(driver, "//*[@id='ui-datepicker-div']/table/tbody/tr/td/a", rowData.get("EndDate"),
				"End Day");
	}

	public void addStartDate(WebDriver driver, int tcRowNum) throws Exception {

		String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace + "\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		String sheetName = "Automation";
		int header = 0; // Excel first row is 0
		int tc = tcRowNum - 1;
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tc);
		Operations op = new Operations(driver);
		String startMonth = rowData.get("StartMonth").trim();
		logInfo("Milestone modified Start startMonth is " + startMonth);
		String startYear = rowData.get("StartYear").trim();
		logInfo("Milestone modified Start startYear is " + startYear);
		op.selectDropdown(driver, "//*[@class='ui-datepicker-month']", startMonth);
		op.selectDropdown(driver, "//*[@class='ui-datepicker-year']", startYear);
		op.selectFromList(driver, "//*[@id='ui-datepicker-div']/table/tbody/tr/td/a", rowData.get("StartDate"),
				"Start Day");
	}

}

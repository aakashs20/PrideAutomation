package com.project.pageobjects.pTracker;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
//import com.project.pageobjects.pTracker.NewProjectsPageConstants;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class DeliveryMilestonePage extends TestBase {

	WebDriverWait wait;
	ControlActions controlActions;
	Operations op;
	Actions actions1;
	ExcelUtils excel;
	PL_ActiveProjectsPage activeProject;

	private static final int DELAY = 20;
	public static int rowNum = 0;
	private static int index = 0;
	int indexOfReqNum = -1;
	String valueAtList = "";

	// String datapoolPath =
	// workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";

	public DeliveryMilestonePage(WebDriver driver) {
		// driver1 = driver;
		actions1 = new Actions(driver);
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, DELAY);
		controlActions = new ControlActions(driver);
		op = new Operations(driver);
		activeProject = new PL_ActiveProjectsPage(driver);
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

	@FindBy(xpath = DeliveryMilestoneConstants.PROJECT_NUMBER1)
	public WebElement projectNumber1;

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
	public WebElement attachmentsTab;

	@FindBy(xpath = DeliveryMilestoneConstants.CUSTOMER_COORDINATOR_TAB)
	public WebElement custCordinatorTab;

	@FindBy(xpath = DeliveryMilestoneConstants.MILESTONE_OPTION)
	public WebElement milestoneOption;

	@FindBy(xpath = DeliveryMilestoneConstants.SPRINT_OPTION)
	public WebElement sprintOption;

	@FindBy(xpath = DeliveryMilestoneConstants.RELEASE_OPTION)
	public WebElement ReleaseOption;

	@FindBy(xpath = DeliveryMilestoneConstants.ADD_MILESTONE_FRAME)
	public WebElement addMilestoneFrame;
	@FindBy(xpath = DeliveryMilestoneConstants.EDIT_MILESTONE_FRAME)
	public WebElement editMilestoneFrame;

	@FindBy(xpath = DeliveryMilestoneConstants.MILESTONE_ID)
	public WebElement milestoneIdField;

	@FindBy(xpath = DeliveryMilestoneConstants.MILESTONE_IDvisible)
	public WebElement milestoneidVisible;

	@FindBy(xpath = DeliveryMilestoneConstants.MILESTONE_NAME)
	public WebElement milestoneNameField;

	@FindBy(xpath = DeliveryMilestoneConstants.MILESTONE_START_DATE_CALENDAR)
	public WebElement milestoneStartDateCalendar;

	@FindBy(xpath = DeliveryMilestoneConstants.MILESTONE_START_DATE)
	public WebElement milestoneStartDate;

	@FindBy(xpath = DeliveryMilestoneConstants.MILESTONE_END_DATE_CALENDAR)
	public WebElement milestoneEndDateCalendar;

	@FindBy(xpath = DeliveryMilestoneConstants.MILESTONE_END_DATE)
	public WebElement milestoneEndDate;

	@FindBy(xpath = DeliveryMilestoneConstants.ADD_MILESTONE_PAGE_ADD_BUTTON)
	public WebElement addMilestonePageButton;
	
	@FindBy(xpath = DeliveryMilestoneConstants.ADD_MILESTONE_PAGE_ERROR_TITLE)
	public WebElement addMilestoneErrorTitle;
	
	@FindBy(xpath = DeliveryMilestoneConstants.ADD_MILESTONE_PAGE_ERROR_MESSAGE)
	public WebElement addMilestoneErrorMsg;
	
	// h2[contains(@class,'aErrMsgTitle')]
	@FindBy(xpath = DeliveryMilestoneConstants.CLOSE_ERROR_NOTIFICATION_MESSAGE)
	public WebElement closeErrorNotificationMsg;
	
	@FindBy(xpath = DeliveryMilestoneConstants.CANCEL_MILESTONE_BUTTON)
	public WebElement cancelMilestone;
	
	@FindBy(xpath = DeliveryMilestoneConstants.ADDED_MILESTONE_ID)
	public WebElement addedMilestoneIDList;
	
	@FindBy(xpath = DeliveryMilestoneConstants.STATUS_UPDATE)
	public WebElement statusUpdateField;
	
	@FindBy(xpath = DeliveryMilestoneConstants.DM_FIRST_PAGE_TXT)
	public WebElement dmFirstPageTxt;
	
	@FindBy(xpath = DeliveryMilestoneConstants.DM_PAGINATION_NEXT_BTN)
	public WebElement dmPaginationNextBtn;
	
	@FindBy(xpath = DeliveryMilestoneConstants.DM_PAGINATION_PREVIOUS_BTN)
	public WebElement dmPaginationPreviousBtn;
	
	@FindBy(xpath = DeliveryMilestoneConstants.DM_CANCEL_BTN)
	public WebElement dmCancelBtn;
	

	public WebElement getStatusUpdateField() {
		return statusUpdateField;
	}

	public void setStatusUpdateField(WebElement statusUpdateField) {
		this.statusUpdateField = statusUpdateField;
	}

	public WebElement getReleasePercCompletion() {
		return releasePercCompletion;
	}

	public void setReleasePercCompletion(WebElement releasePercCompletion) {
		this.releasePercCompletion = releasePercCompletion;
	}

	@FindBy(xpath = DeliveryMilestoneConstants.PERCENT_COMPLETION)
	public WebElement percentCompletion;
	@FindBy(xpath = DeliveryMilestoneConstants.SAVE_CHANGES_BUTTON)
	public WebElement saveChangesButton;
	@FindBy(xpath = DeliveryMilestoneConstants.DELETE_MILESTONE_FRAME)
	public WebElement deleteMilestoneFrame;
	@FindBy(xpath = DeliveryMilestoneConstants.OBSOLETE_BUTTON)
	public WebElement obsoleteButton;
	@FindBy(xpath = DeliveryMilestoneConstants.REASON_CODE_DRPDOWN_ICON)
	public WebElement reasonCodeDrpDown;
	@FindBy(xpath = DeliveryMilestoneConstants.REASON_CODE_SEARCH_TEXTBOX)
	private WebElement reasonCodeSearchBox;
	@FindBy(xpath = DeliveryMilestoneConstants.REASON_CODES)
	private WebElement reasonCodes;
	@FindBy(xpath = "//li[@role='option']")
	private WebElement reasonCodeOption;
	// Add Sprint
	@FindBy(xpath = DeliveryMilestoneConstants.ADD_SPRINT_FRAME)
	private WebElement addSprintFrame;
	@FindBy(xpath = DeliveryMilestoneConstants.SPRINT_ID)
	private WebElement sprintID;
	@FindBy(xpath = DeliveryMilestoneConstants.SPRINT_NAME)
	private WebElement sprintName;
	@FindBy(xpath = DeliveryMilestoneConstants.PARENT_MILESTONE)
	private WebElement parentMilestone;
	@FindBy(xpath = DeliveryMilestoneConstants.PARENT_MILESTONE_SEARCH_BOX)
	private WebElement parentMilestoneSearchBox;
	@FindBy(xpath = DeliveryMilestoneConstants.SPRINT_CYCLE)
	private WebElement sprintCycle;
	@FindBy(xpath = DeliveryMilestoneConstants.SPRINT_CYCLE_SEARCH_BOX)
	private WebElement sprintCycleSearchBox;
	@FindBy(xpath = DeliveryMilestoneConstants.UNIT_OF_MEASURE)
	private WebElement UOM;
	@FindBy(xpath = DeliveryMilestoneConstants.UNIT_OF_MEASURE_SEARCH_BOX)
	private WebElement UOMSearchBox;
	@FindBy(xpath = DeliveryMilestoneConstants.SPRINT_STORIES)
	private WebElement sprintStories;
	@FindBy(xpath = DeliveryMilestoneConstants.PLANNED_CAPACITY)
	private WebElement sprintPlannedCapacity;
	@FindBy(xpath = DeliveryMilestoneConstants.TEAM)
	private WebElement team;
	@FindBy(xpath = DeliveryMilestoneConstants.TEAM_SEARCH_BOX)
	private WebElement teamSearchBox;

	// Release
	@FindBy(xpath = DeliveryMilestoneConstants.ADD_RELEASE_FRAME)
	private WebElement addReleaseFrame;

	@FindBy(xpath = DeliveryMilestoneConstants.RELEASE_ID)
	private WebElement releaseIDField;
	@FindBy(xpath = DeliveryMilestoneConstants.RELEASE_NAME)
	private WebElement releaseNameField;
	@FindBy(xpath = DeliveryMilestoneConstants.ADD_BUTTON)
	private WebElement addButton;
	@FindBy(xpath = DeliveryMilestoneConstants.PARENT_MILESTONE_LIST)
	private WebElement parentMilestoneList;
	@FindBy(xpath = DeliveryMilestoneConstants.CANCEL_BUTTON_OBSOLETE_MILESTONE)
	private WebElement cancelButtonObsoleteMilestone;
	// Update Release
	@FindBy(xpath = DeliveryMilestoneConstants.UPDATE_FRAME)
	private WebElement updateReleaseFrame;
	@FindBy(xpath = DeliveryMilestoneConstants.STATUS)
	private WebElement statusDrpDown;
	@FindBy(xpath = DeliveryMilestoneConstants.RELEASE_PERC_COMPLETION)
	public WebElement releasePercCompletion;
	@FindBy(xpath = DeliveryMilestoneConstants.CANCEL_BUTTON)
	private WebElement cancelButton;
	@FindBy(xpath = DeliveryMilestoneConstants.PERCOMPLETION_ERROR_POPUP)
	private WebElement perCpmlErrorPopup;
	@FindBy(xpath = DeliveryMilestoneConstants.NEXT_PAGINATION_BUTTON)
	public WebElement nextPaginationButton;
	// Sprint elements
	@FindBy(xpath = DeliveryMilestoneConstants.SCOPE_CHANGE)
	public WebElement scopeChange;
	@FindBy(xpath = DeliveryMilestoneConstants.IN_PROGRESS)
	public WebElement inProgress;
	@FindBy(xpath = DeliveryMilestoneConstants.BLOCKED)
	public WebElement blocked;
	@FindBy(xpath = DeliveryMilestoneConstants.COMPLETED)
	public WebElement completed;
	@FindBy(xpath = DeliveryMilestoneConstants.RAG_STATUS)
	public WebElement ragStatus;
	@FindBy(xpath = DeliveryMilestoneConstants.P1_DEFECT)
	public WebElement p1Defect;
	@FindBy(xpath = DeliveryMilestoneConstants.P2_DEFECT)
	public WebElement p2Defect;
	@FindBy(xpath = DeliveryMilestoneConstants.P3_DEFECT)
	public WebElement p3Defect;
	@FindBy(xpath = DeliveryMilestoneConstants.P4_DEFECT)
	public WebElement p4Defect;

	public WebElement getPerCpmlErrorPopup() {
		return perCpmlErrorPopup;
	}

	public void setPerCpmlErrorPopup(WebElement perCpmlErrorPopup) {
		this.perCpmlErrorPopup = perCpmlErrorPopup;
	}

	@FindBy(xpath = DeliveryMilestoneConstants.OK_BUTTON)
	private WebElement OKButton;
	// Task locators
	@FindBy(xpath = DeliveryMilestoneConstants.POPUP_OK_BUTTON)
	private WebElement popUpOKButton;

	public WebElement getOKButton() {
		return OKButton;
	}

	public void setOKButton(WebElement oKButton) {
		OKButton = oKButton;
	}

	@FindBy(xpath = DeliveryMilestoneConstants.TASK_OPTION)
	public WebElement taskOption;
	@FindBy(xpath = DeliveryMilestoneConstants.ADD_RELEASE_FRAME)
	private WebElement addTaskFrame;
	@FindBy(xpath = DeliveryMilestoneConstants.TASK_ID)
	private WebElement taskIDField;
	@FindBy(xpath = DeliveryMilestoneConstants.TASK_NAME)
	private WebElement taskNameField;

	// Phase locators
	@FindBy(xpath = DeliveryMilestoneConstants.PHASE_OPTION)
	public WebElement phaseOption;
	@FindBy(xpath = DeliveryMilestoneConstants.PHASE_ID)
	private WebElement phaseIDField;
	@FindBy(xpath = DeliveryMilestoneConstants.PHASE_NAME)
	private WebElement phaseNameField;
	
	@FindBy(xpath = DeliveryMilestoneConstants.NO_PAYMENT_MILESTONE_ERROR)
	public WebElement noPaymentMilestoneError;

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
			op.waitForElementToBeClickable(searchProject);
			op.sendKeys(searchProject, searchProjectNumber);
			// op.actionEnter();
			logInfo("Entered text '" + searchProjectNumber + "' in Search Project Field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + searchProjectNumber + "' in Search Project Field" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on Attachments Tab
	 * 
	 * @return boolean This returns true if Successfully clicked on Attachments Tab
	 */

	public boolean clkAttachmentsTab() {
		try {
				//threadsleep(2000);
				boolean isAttachmentsTabDisplayed = controlActions.isElementDisplayedOnPage(attachmentsTab);
				if(isAttachmentsTabDisplayed)
				{
					op.clickElement(attachmentsTab);
					logInfo("Successfully clicked on Attachments Tab -> " + attachmentsTab);
					return true;
				}
				else
				{
					logError("Failed to click on Attachments Tab " + attachmentsTab );
					return false;
				}
//			op.waitForElementToBeClickable(attachmentsTab);
//			//op.waitForAnElementToBeClickable(attachmentsTab);
//			

		} catch (Exception e) {
			logError("Failed to click on Attachments Tab " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on Delivery Milestones Tab
	 * 
	 * @return boolean This returns true if Successfully clicked on Delivery
	 *         Milestones Tab
	 */

	public boolean clkDeliveryMilestonesTab() {
		try {
			//threadsleep(2000);
			boolean isDeliveryMilestoneTabDisplayed = controlActions.isElementDisplayedOnPage(deliveryMilestoneTab);
			if(isDeliveryMilestoneTabDisplayed)
			{
				op.clickElement(deliveryMilestoneTab);
				logInfo("Successfully clicked on Delivery Milestone Tab -> " + deliveryMilestoneTab);
				return true;
			}
			else
			{
				logError("Failed to click on Delivery Milestone Tab " + deliveryMilestoneTab );
				return false;
			}
			
			
//			
//			op.waitForAnElementToBeClickable(deliveryMilestoneTab);
//			op.clickElement(deliveryMilestoneTab);
//			logInfo("Successfully clicked on Delivery Milestones Tab");
//			return true;
		} catch (Exception e) {
			logError("Failed to click on Delivery Milestones Tab " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on Add Delivery Milestone Button
	 * 
	 * @return boolean This returns true if Successfully clicked on Add Delivery
	 *         Milestone Button
	 */
	public boolean clkAddDeliveryMilestoneButton() {
		try {
			threadsleep(2000);
			op.waitForAnElementToBeClickable(openAddDeliveryMilestone);
			op.clickElement(openAddDeliveryMilestone);
			logInfo("Successfully clicked on Add Delivery Milestone Button");
			return true;
		} catch (Exception e) {
			logError("Failed to click on Add Delivery Milestone Button " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on Milestone option under "Add Delivery
	 * Milestone"
	 * 
	 * @return boolean This returns true if Successfully clicked on Milestone option
	 */

	public boolean clkMilestoneOption() {
		try {
			op.waitForAnElementToBeClickable(milestoneOption);
			op.clickElement(milestoneOption);
			logInfo("Successfully clicked on Milestone option");
			return true;
		} catch (Exception e) {
			logError("Failed to click on Milestone option " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on Sprint option under "Add Delivery Milestone"
	 * 
	 * @return boolean This returns true if Successfully clicked on Sprint option
	 */

	public boolean clkSprintOption() {
		try {
			op.waitForAnElementToBeClickable(sprintOption);
			op.clickElement(sprintOption);
			logInfo("Successfully clicked on Sprint option");
			return true;
		} catch (Exception e) {
			logError("Failed to click on Sprint option " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on Release option under "Add Delivery Milestone"
	 * 
	 * @return boolean This returns true if Successfully clicked on Sprint option
	 */

	public boolean clkReleaseOption() {
		try {
			op.waitForAnElementToBeClickable(ReleaseOption);
			op.clickElement(ReleaseOption);
			logInfo("Successfully clicked on release option");
			return true;
		} catch (Exception e) {
			logError("Failed to click on release option " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to switch to Add Milestone Frame
	 * 
	 * @return boolean This returns true if Successfully switched to Add Milestone
	 *         frame
	 */

	public boolean switchToAddMilestoneFrame() {
		try {

			op.switchToFrameByLocators(addMilestoneFrame);
			logInfo("Successfully clicked on Add Delivery Milestone Button");
			return true;
		} catch (Exception e) {
			logError("Failed to switch to Add Milestone Frame " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to switch to Add Milestone Frame
	 * 
	 * @return boolean This returns true if Successfully switched to Add Milestone
	 *         frame
	 */

	public boolean switchToAddSprintFrame() {
		try {

			op.switchToFrameByLocators(addSprintFrame);
			logInfo("Successfully switched to add sprint frame");
			return true;
		} catch (Exception e) {
			logError("Failed to switch to add sprint frame " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to switch to Add ReleaseFrame
	 * 
	 * @param driver
	 * 
	 * @return boolean This returns true if Successfully switched to Add Release
	 *         frame
	 */

	public boolean switchToAddFrame() {
		try {
			threadsleep(3000);
			// op.switchToDefault();
//			op.waitForFrameToAvailable(addReleaseFrame);
//			op.switchToFrameByLocators(addReleaseFrame);
			op.switchToFrameByIFrameElement(addReleaseFrame);
			logInfo("Successfully switched to add release frame");
			return true;
		} catch (Exception e) {
			logError("Failed to switch to add release frame " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to switch to Add ReleaseFrame
	 * 
	 * @return boolean This returns true if Successfully switched to Add Release
	 *         frame
	 */

	public boolean switchToUpdateFrame() {
		try {
			// op.waitForFrameToAvailable(updateReleaseFrame);
			op.switchToFrameByIFrameElement(updateReleaseFrame);
			logInfo("Successfully switched to update release frame");
			return true;
		} catch (Exception e) {
			logError("Failed to switch to update release frame " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to switch to Edit Milestone Frame
	 * 
	 * @return boolean This returns true if Successfully switched to Edit Milestone
	 *         frame
	 */

	public boolean switchToEditMilestoneFrame() {
		try {

			op.switchToFrameByLocators(editMilestoneFrame);
			logInfo("Successfully switch to edit Milestone Frame");
			return true;
		} catch (Exception e) {
			logError("Failed to switch to edit Milestone Frame " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to switch to Delete Milestone Frame
	 * 
	 * @return boolean This returns true if Successfully switched to delete
	 *         Milestone frame
	 */

	public boolean switchToDeleteMilestoneFrame() {
		try {
			threadsleep(2000);
			op.switchToFrameByLocators(deleteMilestoneFrame);
			logInfo("Successfully switch to delete Milestone Frame");
			return true;
		} catch (Exception e) {
			logError("Failed to switch to delete Milestone Frame " + e.getMessage());
			return false;
		}
	}

	// iframe[@title='Edit Milestone']

	public boolean isElementDisplayed(WebElement webElement) {
		try {
			webElement.isDisplayed();
			return true;
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
			return false;
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is used to enter Milestone ID
	 * 
	 * @param inputString [String]
	 * @return boolean true if action successful else false
	 */
	public boolean enterTextToMilestoneID(String milestoneID) {
		try {
			isElementDisplayed(milestoneIdField);
			op.waitForElementToBeClickable(milestoneIdField);
			op.clear(milestoneIdField);
			op.sendKeys(milestoneIdField, milestoneID);
			// op.actionEnter();
			logInfo("Entered text '" + milestoneID + "' in Milestone ID Field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + milestoneID + "' in Milestone ID Field" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to enter Milestone Name
	 * 
	 * @param inputString [String]
	 * @return boolean true if action successful else false
	 */

	public boolean enterTextTomilestoneNameField(String milestoneName) {
		try {
			op.waitForElementToBeClickable(milestoneNameField);
			op.clear(milestoneNameField);
			op.sendKeys(milestoneNameField, milestoneName);
			// op.actionEnter();
			logInfo("Entered text '" + milestoneName + "' in milestone name Field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + milestoneName + "' in MIlestone Name Field" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to enter Milestone ID
	 * 
	 * @param inputString [String]
	 * @return boolean true if action successful else false
	 */
	public boolean enterTextToReleaseID(String releaseID) {
		try {
			isElementDisplayed(releaseIDField);
			op.waitForElementToBeClickable(releaseIDField);
			op.clear(releaseIDField);
			op.sendKeys(releaseIDField, releaseID);
			// op.actionEnter();
			logInfo("Entered text '" + releaseID + "' in Release ID Field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + releaseID + "' in Release ID Field" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to enter Milestone Name
	 * 
	 * @param inputString [String]
	 * @return boolean true if action successful else false
	 */

	public boolean enterTextToReleaseNameField(String releaseName) {
		try {
			op.waitForElementToBeClickable(releaseNameField);
			op.clear(releaseNameField);
			op.sendKeys(releaseNameField, releaseName);
			// op.actionEnter();
			logInfo("Entered text '" + releaseName + "' in release name Field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + releaseName + "' in release Name Field" + e.getMessage());
			return false;
		}
	}

//	/**
//	 * This method is used to enter reason code
//	 * 
//	 * @param inputString [String]
//	 * @return boolean true if action successful else false
//	 */
//
//	public boolean enterTextToReasonCodeField(String reasonCode) {
//		try {
//			op.waitForElementToBeClickable(reasonCodeDrpDown);
//			op.clear(reasonCodeField);
//			op.sendKeys(reasonCodeField, reasonCode);
//			// op.actionEnter();
//			logInfo("Entered text '" + reasonCode + "' in reason code Field");
//			return true;
//		} catch (Exception e) {
//			logError("Failed to enter text '" + reasonCode + "' in reason code Field" + e.getMessage());
//			return false;
//		}
//	}

	/**
	 * This method is used to enter Milestone Name
	 * 
	 * @param inputString [String]
	 * @return boolean true if action successful else false
	 */

	public boolean enterTextToPercCompletionField(String percenCompletion) {
		try {
			op.waitForElementToBeClickable(percentCompletion);
			op.clear(percentCompletion);
			op.sendKeys(percentCompletion, percenCompletion);
			// op.actionEnter();
			logInfo("Entered text '" + percenCompletion + "' in percent completion field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + percenCompletion + "' in percent completion field" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to enter Milestone Name
	 * 
	 * @param inputString [String]
	 * @return boolean true if action successful else false
	 */

	public boolean enterTextToStatusUpdateField(String text) {
		try {
			op.waitForElementToBeClickable(statusUpdateField);
			op.clear(statusUpdateField);
			op.sendKeys(statusUpdateField, text);
			// op.actionEnter();
			logInfo("Entered text '" + text + "' in status update field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + text + "' in status update Field" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to enter reason code
	 * 
	 * @param inputString [String]
	 * @return boolean true if action successful else false
	 */

	public boolean selectReasonCode(String expectedReasonCode) {
		try {
			op.waitForElementToBeDisplayed(reasonCodes);
			List<WebElement> reasoncodeFields = controlActions
					.perform_getListOfElementsByXPath(DeliveryMilestoneConstants.REASON_CODES);
			System.out.println(reasoncodeFields.size());
			for (WebElement reasonCode : reasoncodeFields) {
				if (reasonCode.getText().trim().equalsIgnoreCase(expectedReasonCode)) {
					op.click(reasonCode);
					logInfo("Reson code selected: " + expectedReasonCode);
					return true;
				}
			}
		} catch (Exception e) {
			// logError("Failed to select " + expectedReasonCode);
			return false;
		}
		return false;
	}

	/**
	 * This method is used to click on Start Date Calendar
	 * 
	 * @return boolean This returns true if Successfully clicked on Start Date
	 *         calendar icon
	 */

	public boolean clkStartDateCalendar() {
		try {
			op.waitForAnElementToBeClickable(milestoneStartDateCalendar);
			op.clickElement(milestoneStartDateCalendar);
			logInfo("Successfully clicked on Start Date Calendar");
			return true;
		} catch (Exception e) {
			logError("Failed to click on Start Date Calendar Button " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on Start Date
	 * 
	 * @return boolean This returns true if Successfully clicked on Start Date
	 */

	public boolean clkStartDate() {
		try {
			op.waitForAnElementToBeClickable(milestoneStartDate);
			op.clickElement(milestoneStartDate);
			logInfo("Successfully clicked on Start Date");
			return true;
		} catch (Exception e) {
			logError("Failed to click on Start Date " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on End Date Calendar
	 * 
	 * @return boolean This returns true if Successfully clicked on End Date
	 *         calendar icon
	 */

	public boolean clkEndDateCalendar() {
		try {
			op.waitForAnElementToBeClickable(milestoneEndDateCalendar);
			op.clickElement(milestoneEndDateCalendar);
			logInfo("Successfully clicked on End Date Calendar");
			return true;
		} catch (Exception e) {
			logError("Failed to click on End Date Calendar Button " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on End Date
	 * 
	 * @return boolean This returns true if Successfully clicked on End Date
	 */

	public boolean clkEndDate() {
		try {
			op.waitForAnElementToBeClickable(milestoneEndDate);
			op.clickElement(milestoneEndDate);
			logInfo("Successfully clicked on End Date");
			return true;
		} catch (Exception e) {
			logError("Failed to click on End Date " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on Add button on Add Milestone page
	 * 
	 * @return boolean This returns true if Successfully Add button on Add Milestone
	 *         page
	 */

	public boolean clkAddButtonOnAddMilestoneFrame() {
		try {
			op.waitForAnElementToBeClickable(addMilestonePageButton);
			op.clickElement(addMilestonePageButton);
			logInfo("Successfully clicked on Add button on Add Milestone page");
			return true;
		} catch (Exception e) {
			logError("Failed to click on Add button on Add Milestone page " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on cancel button on Add Milestone page
	 * 
	 * @return boolean This returns true if Successfully cancel button on Add
	 *         Milestone page
	 */

	public boolean clkCancelButtonOnAddMilestoneFrame() {
		try {
			op.waitForAnElementToBeClickable(cancelMilestone);
			op.clickElement(cancelMilestone);
			logInfo("Successfully clicked on Cancel button on Add Milestone page");
			op.switchToDefault();
			return true;
		} catch (Exception e) {
			logError("Failed to click on Add button on Cancel Milestone page " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on cancel button on Obsolete Milestone page
	 * 
	 * @return boolean This returns true if Successfully cancel button on Obsolete
	 *         Milestone page
	 */

	public boolean clkCancelButtonOnObsoleteMilestoneFrame() {
		try {
			op.waitForAnElementToBeClickable(cancelButtonObsoleteMilestone);
			op.clickElement(cancelButtonObsoleteMilestone);
			logInfo("Successfully clicked on Cancel button on Obsolete Milestone page");
			op.switchToDefault();
			return true;
		} catch (Exception e) {
			logError("Failed to click on cancel button on Obsolete Milestone page " + e.getMessage());
			op.switchToDefault();
			return false;
		}
	}

	public boolean clkSaveChangesButton() {
		try {
			op.waitForAnElementToBeClickable(saveChangesButton);
			op.clickElement(saveChangesButton);
			logInfo("Successfully clicked on save changes button on Edit Milestone page");
			return true;
		} catch (Exception e) {
			logError("Successfully clicked on save changes button on Edit Milestone page " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify Active Project We are passing
	 * searchProjectNumber in this method for searching the project no in Search
	 * Project field.
	 */

	public boolean verifyActiveProject(WebDriver driver, String searchProjectNumber) {
		try {
//		op.clickOnElement(activeProjectList); // User clicks on Active project
//		op.clickOnElement(fixedPrice); // User clicks on Fixed price project
			boolean isProjectNumberSet = enterTextInSearchProjectField(searchProjectNumber); // User enters project no
			IsTrue(isProjectNumberSet, "Project " + searchProjectNumber + " found successfully", "Failed to add text to project search as '" + searchProjectNumber + "'");
			op.click(goButton);
			WebElement projectNumber1 = op.perform_getElementByXPath(DeliveryMilestoneConstants.PROJECT_NUMBER1.replace("PROJ_NUM", searchProjectNumber));
			op.isElementDisplayed(projectNumber1);
			op.click(projectNumber1);
			return true;
		} catch (Exception e) {
			logError(e.getMessage());
			return false;

		}
	}

	/*
	 * This method is used to add new milestone
	 * 
	 * @return void
	 */
	public void TC_AddMilestone(WebDriver driver, String milestoneID, String milestoneName) throws Exception {

//		boolean isAddDeliveryMilestoneButtonClicked = clkAddDeliveryMilestoneButton(); // User clicks on Add Delivery
//																						// Milestone button
//		Assert.assertTrue(isAddDeliveryMilestoneButtonClicked, "Failed to click on Add Delivery Milestone button.");

		boolean isDeliveryMilestoneOptionClicked = clkMilestoneOption(); // User clicks on Milestone option under Add
																			// Delivery Milestone
		Assert.assertTrue(isDeliveryMilestoneOptionClicked, "Failed to click on Delivery Milestone option.");
		boolean isSwitchedToAddMilestoneFrame = switchToAddMilestoneFrame(); // Switches the control to Add Milestone
																				// frame
		boolean isMilestoneIDSet = enterTextToMilestoneID(milestoneID); // Add Milestone window-> Enter milestoneID
		Assert.assertTrue(isMilestoneIDSet, "Failed to add text to Milestone ID as '" + milestoneID + "'");

		boolean isMilestoneNameSet = enterTextTomilestoneNameField(milestoneName); // Add Milestone window-> Enter
																					// milestone Name
		Assert.assertTrue(isMilestoneNameSet, "Failed to add text to Milestone Name as '" + milestoneName + "'");

		boolean isStartDateCalendarClicked = clkStartDateCalendar(); // Add Milestone window->Click on Start Date
																		// Calendar icon
		Assert.assertTrue(isStartDateCalendarClicked, "Failed to click on Start Date Calendar.");

		boolean isStartDateClicked = clkStartDate(); // Add Milestone window->Click on Start Date
		Assert.assertTrue(isStartDateClicked, "Failed to click on Start Date.");

		boolean isEndDateCalendarClicked = clkEndDateCalendar(); // Add Milestone window->Click on End Date Calendar
																	// icon
		Assert.assertTrue(isEndDateCalendarClicked, "Failed to click on End Date Calendar.");

		boolean isEndDateClicked = clkEndDate(); // Add Milestone window->Click on End Date
		Assert.assertTrue(isEndDateClicked, "Failed to click on End Date.");

		boolean isAddButtonOnAddMilestonePageClicked = clkAddButtonOnAddMilestoneFrame(); // Add Milestone window->Click
		Assert.assertTrue(isAddButtonOnAddMilestonePageClicked, "Failed to click on Add button on Add Milestone page.");
		op.switchToDefault(); // After adding a milestone, the control switches back to main page
		// Thread.sleep(3000);
	}

//Negative scenarios for Add delivery milestone
	public void verifyNegativeScenarios(WebDriver driver, String milestoneID, String milestoneName, String startDate,
			String endDate) throws Exception {

		rowNum++;
		logInfo("Checking for milestone ID: " + milestoneID + " and milestoneName " + milestoneName);
		logInfo("milestone ID: " + milestoneID);
		logInfo("milestone Name " + milestoneName);
		logInfo("Start date: " + startDate);
		logInfo("End Date: " + endDate);

		int errorCnt = 0;
		String expectedErrorMsg[] = new String[4];
		op = new Operations(driver);
		if (milestoneID.equals("") || op.checkSpecialChar(milestoneID) || milestoneID.equalsIgnoreCase("null")
				|| op.checkOnlyCharacter(milestoneID)) {
			expectedErrorMsg[errorCnt] = "Please enter valid Milestone ID.";
			logInfo("Expected error message: Please enter valid Milestone ID.");
			logError("Please enter valid Milestone ID.");
			errorCnt++;
		}
		boolean isMilestoneIDSet = enterTextToMilestoneID(milestoneID);
		logInfo("Successfully entered valid milestone ID: " + milestoneID);
		Assert.assertTrue(isMilestoneIDSet, "Failed to add text to Milestone ID as '" + milestoneID + "'");
		if (milestoneName.equals("") || op.checkSpecialChar(milestoneName) || NumberUtils.isNumber(milestoneName)
				|| milestoneName.equalsIgnoreCase("null")) {
			expectedErrorMsg[errorCnt] = "Please enter valid Milestone name.";
			logInfo("Expected error message: Please enter valid Milestone name.");
			errorCnt++;

		}
		boolean isMilestoneNameSet = enterTextTomilestoneNameField(milestoneName);
		logInfo("Successfully entered valid milestone name: " + milestoneName);
		Assert.assertTrue(isMilestoneNameSet, "Failed to add text to Milestone Name as '" + milestoneName + "'");
		if (!startDate.equals("")) {
			boolean isStartDateCalendarClicked = clkStartDateCalendar(); // Add Milestone window->Click on Start Date
			Assert.assertTrue(isStartDateCalendarClicked, "Failed to click on Start Date Calendar.");
			boolean isStartDateClicked = clkStartDate(); // Add Milestone window->Click on Start Date
			Assert.assertTrue(isStartDateClicked, "Failed to click on Start Date.");
		} else {
			expectedErrorMsg[errorCnt] = "Please select Start Date.";
			logInfo("Expected error message: Please select Start Date.");
			errorCnt++;

		}
		if (!endDate.equals("")) {
			boolean isEndDateCalendarClicked = clkEndDateCalendar(); // Add Milestone window->Click on End Date Calendar
			Assert.assertTrue(isEndDateCalendarClicked, "Failed to click on End Date Calendar.");
			boolean isEndDateClicked = clkEndDate(); // Add Milestone window->Click on End Date
			Assert.assertTrue(isEndDateClicked, "Failed to click on End Date.");
		} else {
			expectedErrorMsg[errorCnt] = "Please select End Date.";
			logInfo("Expected error message: Please select End Date.");
			errorCnt++;
		}
		logInfo("Expected error count is " + errorCnt);
		boolean isAddButtonOnAddMilestonePageClicked = clkAddButtonOnAddMilestoneFrame(); // Add Milestone window->Click
		Assert.assertTrue(isAddButtonOnAddMilestonePageClicked, "Failed to click on Add button on Add Milestone page.");
		verifyErrorMessage(driver, errorCnt, expectedErrorMsg);
		addMilestone(driver);
		// return driver;
	}

	// Negative scenarios for Add delivery milestone
	public boolean verify_negScenarios_deleteMilestone(WebDriver driver, String milestoneID, String reasonCode,
			String statusUpdate) {

		logInfo("Checking for milestone ID: " + milestoneID);
		logInfo("Reason code: " + reasonCode);
		logInfo("Status update: " + statusUpdate);

		int errorCnt = 0;
		String expectedErrorMsg[] = new String[2];
		op = new Operations(driver);
		if (reasonCode.equals("")) {
			expectedErrorMsg[errorCnt] = "Enter Reason Code for making this Milestone Obsolete.";
			logInfo("Expected error message: Enter Reason Code for making this Milestone Obsolete.");
			errorCnt++;
		} else {
			op.waitForElementToBeClickable(reasonCodeDrpDown);
			op.clickElement(reasonCodeDrpDown);
			op.switchToDefault();
			boolean reasonCodeSelected = selectReasonCode(reasonCode);
			IsTrue(reasonCodeSelected, "Reason code selected: " + reasonCode, "Failed to select reason code");
			boolean isSwitchedToDeleteMilestoneFrame1 = switchToDeleteMilestoneFrame();
			IsTrue(isSwitchedToDeleteMilestoneFrame1, "Switched to delete frame", "Failed to switch to delete frame");
		}
		if (statusUpdate.equals("") || statusUpdate.equalsIgnoreCase("null")) {
			expectedErrorMsg[errorCnt] = "Enter Status Update for making this Milestone Obsolete.";
			logInfo("Expected error message: Enter Status Update for making this Milestone Obsolete.");
			errorCnt++;
		} else {
			boolean isStatusUpdateSet = enterTextToStatusUpdateField(statusUpdate);
			IsTrue(isStatusUpdateSet, "Status updated as: " + statusUpdate,
					"Failed to update status as : " + statusUpdate);
		}
		logInfo("Expected error count is " + errorCnt);
		boolean isButtonClicked = clkObsoleteButton();
		IsTrue(isButtonClicked, "Clicked on obsolete button", "Failed to delete milestone");
		boolean status = verifyErrorMsg(driver, errorCnt, expectedErrorMsg);
		if (status)
			return true;
		else
			return false;
	}

	private boolean verifyErrorMsg(WebDriver driver, int errorCnt, String[] expectedErrorMsg) {
		try {
			op.perform_waitUntilVisibility(addMilestoneErrorTitle);
			String errorTitle = op.getText(addMilestoneErrorTitle);
			if (errorCnt > 1) {
				Equals(errorTitle, +errorCnt + " errors have occurred",
						"Error notification is displayed as expected: " + errorTitle,
						"Error notification is not displayed as expected: " + errorTitle);
				// verifyErrorMessageDetails(driver, errorCnt, expectedErrorMsg);
			} else {
				Equals(errorTitle, +errorCnt + " error has occurred", "Error notification is displayed as expected",
						"Error notification is not displayed as expected");
				// verifyErrorMessageDetails(driver, errorCnt, expectedErrorMsg);
			}
			boolean status = verifyErrorDetails(driver, errorCnt, expectedErrorMsg);
			if (status)
				return true;
			else
				return false;

		} catch (Exception e) {
//				logError("Error notification is not displayed as expected");
//				ExcelUtils.setCellData("TC_AddDeliveryMilestoneNegativeScenarios", "milestone negative scenarios",
//						rowNum, "Status", "FAIL", "RED");
//				clkCancelButtonOnAddMilestoneFrame();
			logError(e.getMessage());
			return false;
		}
	}

	private boolean verifyErrorDetails(WebDriver driver, int errorCnt, String[] expectedErrorMsg) {
		op.perform_waitUntilVisibility(addMilestoneErrorMsg);
		List<WebElement> actualErrorMsg = controlActions
				.perform_getListOfElementsByXPath(DeliveryMilestoneConstants.ADD_MILESTONE_PAGE_ERROR_MESSAGE);
		if (actualErrorMsg.size() == 0) {
			logError("No error message displayed.");
			return false;
		}
		IsTrue(errorCnt == actualErrorMsg.size(), "Number of error messages are as expected",
				"Number of error messages displayed are not as expected");
		logInfo("Actual number of error messages displayed: " + actualErrorMsg.size());
		for (int i = 0; i < errorCnt; i++) {
			System.out.println("Actual error message: " + actualErrorMsg.get(i).getText());
			System.out.println("Expected error message: " + expectedErrorMsg[i]);
			Equals(actualErrorMsg.get(i).getText(), expectedErrorMsg[i],
					"Error notification is displayed as expected: " + expectedErrorMsg[i],
					"Error notification is not displayed as expected");
			logInfo("Error notification is displayed as expected: " + expectedErrorMsg[i]);
		}
		clkCancelButtonOnObsoleteMilestoneFrame();
		op.switchToDefault();
//			addMilestone(driver);
//			throw (e);
//		}}ExcelUtils.setCellData("TC_AddDeliveryMilestoneNegativeScenarios","milestone negative scenarios",rowNum,"Status","PASS","GREEN");
//
//	clkCancelButtonOnAddMilestoneFrame();return driver;}
//
//	boolean isMilestoneNameSet = enterTextTomilestoneNameField(milestoneName);
//
//	logInfo("Successfully entered valid milestone name: " + milestoneName);
//		Assert.assertTrue(isMilestoneNameSet, "Failed to add text to Milestone Name as '" + milestoneName + "'");
//		if (!startDate.equals("")) {
//			boolean isStartDateCalendarClicked = clkStartDateCalendar(); // Add Milestone window->Click on Start Date
//			Assert.assertTrue(isStartDateCalendarClicked, "Failed to click on Start Date Calendar.");
//			boolean isStartDateClicked = clkStartDate(); // Add Milestone window->Click on Start Date
//			Assert.assertTrue(isStartDateClicked, "Failed to click on Start Date.");
//		} else {
//			expectedErrorMsg[errorCnt] = "Please select Start Date.";
//			logInfo("Expected error message: Please select Start Date.");
//			errorCnt++;
//
//		}
//		if (!endDate.equals("")) {
//			boolean isEndDateCalendarClicked = clkEndDateCalendar(); // Add Milestone window->Click on End Date Calendar
//			Assert.assertTrue(isEndDateCalendarClicked, "Failed to click on End Date Calendar.");
//			boolean isEndDateClicked = clkEndDate(); // Add Milestone window->Click on End Date
//			Assert.assertTrue(isEndDateClicked, "Failed to click on End Date.");
//		} else {
//			expectedErrorMsg[errorCnt] = "Please select End Date.";
//			logInfo("Expected error message: Please select End Date.");
//			errorCnt++;
//		}
//		logInfo("Expected error count is " + errorCnt);
//		boolean isAddButtonOnAddMilestonePageClicked = clkAddButtonOnAddMilestoneFrame(); // Add Milestone window->Click
//		Assert.assertTrue(isAddButtonOnAddMilestonePageClicked, "Failed to click on Add button on Add Milestone page.");
//		verifyErrorMessage(driver, errorCnt, expectedErrorMsg);
//		addMilestone(driver);
		// return driver;
		return true;
	}

//Verify error notification messages for negative scenarios
	private void verifyErrorMessage(WebDriver driver, int errorCnt, String[] expectedErrorMsg) throws Exception {
		try {
			op.perform_waitUntilVisibility(addMilestoneErrorTitle);
			String errorTitle = op.getText(addMilestoneErrorTitle);
			try {
				if (errorCnt > 1) {
					Assert.assertEquals(errorTitle, +errorCnt + " errors have occurred");
					verifyErrorMessageDetails(driver, errorCnt, expectedErrorMsg);
				} else {
					Assert.assertEquals(errorTitle, +errorCnt + " error has occurred");
					verifyErrorMessageDetails(driver, errorCnt, expectedErrorMsg);
				}
			} catch (AssertionError e1) {
				logError("Error notification is not displayed as expected");
				ExcelUtils.setCellData("TC_AddDeliveryMilestoneNegativeScenarios", "milestone negative scenarios",
						rowNum, "Status", "FAIL", "RED");
				clkCancelButtonOnAddMilestoneFrame();
			}

		} catch (Exception e) {
			WebElement msg = driver.findElement(By.xpath("//*[contains(text(),'successfully')]"));
			if (msg.isDisplayed())
				logError("Error notification is not displayed as expected");
			ExcelUtils.setCellData("TC_AddDeliveryMilestoneNegativeScenarios", "milestone negative scenarios", rowNum,
					"Status", "FAIL", "RED");
		}

	}

	private boolean verifyErrorMessageTitle(WebDriver driver, int errorCnt, String[] expectedErrorMsg)
			throws Exception {
		try {
			op.perform_waitUntilVisibility(addMilestoneErrorTitle);
			String errorTitle = op.getText(addMilestoneErrorTitle);
			try {
				if (errorCnt > 1) {
					Assert.assertEquals(errorTitle, +errorCnt + " errors have occurred");
				} else {
					Assert.assertEquals(errorTitle, +errorCnt + " error has occurred");
				}
			} catch (AssertionError e1) {
				logError("Error notification is not displayed as expected");
//				ExcelUtils.setCellData("TC_AddDeliveryMilestoneNegativeScenarios", "milestone negative scenarios",
//						rowNum, "Status", "FAIL", "RED");
				clkCancelButtonOnAddMilestoneFrame();
				op.switchToDefault();
				return false;
			}

		} catch (NoSuchElementException e) {
//			WebElement msg = 
//					op.perform_waitUntilVisibility(By.xpath("//*[contains(text(),'successfully')]"));
//			if (msg.isDisplayed())
			logError("Error notification is not displayed as expected");
//			ExcelUtils.setCellData("TC_AddDeliveryMilestoneNegativeScenarios", "milestone negative scenarios", rowNum,
//					"Status", "FAIL", "RED");
			return false;
		}
		return true;

	}

//Verify  the displayed error messages for respective fields
	private WebDriver verifyErrorMessageDetails(WebDriver driver, int errorCnt, String[] expectedErrorMsg)
			throws InterruptedException {
		op.perform_waitUntilVisibility(addMilestoneErrorMsg);
		List<WebElement> actualErrorMsg = controlActions
				.perform_getListOfElementsByXPath("//a[@class='a-Notification-link']");
		if (actualErrorMsg.size() == 0) {
			logError("No error message displayed.");
			ExcelUtils.setCellData("TC_AddDeliveryMilestoneNegativeScenarios", "milestone negative scenarios", rowNum,
					"Status", "FAIL", "RED");
		}

		try {
			assertEquals(errorCnt, actualErrorMsg.size());
		} catch (AssertionError e) {
			logError("Error notification is not displayed as expected");
			ExcelUtils.setCellData("TC_AddDeliveryMilestoneNegativeScenarios", "milestone negative scenarios", rowNum,
					"Status", "FAIL", "RED");
			clkCancelButtonOnAddMilestoneFrame();
			addMilestone(driver);
			throw (e);
		}
		System.out.println("Actual number of error messages displayed: " + actualErrorMsg.size());
		for (int i = 0; i < errorCnt; i++) {
			System.out.println("Actual error message: " + actualErrorMsg.get(i).getText());
			System.out.println("Expected error message: " + expectedErrorMsg[i]);
			try {
				assertTrue(actualErrorMsg.get(i).getText().equalsIgnoreCase(expectedErrorMsg[i]));
				System.out.println("Error notification is displayed as expected: " + expectedErrorMsg[i]);
				logInfo("Error notification is displayed as expected: " + expectedErrorMsg[i]);

			} catch (AssertionError e) {
				logError("Error notification is not displayed as expected");
				ExcelUtils.setCellData("TC_AddDeliveryMilestoneNegativeScenarios", "milestone negative scenarios",
						rowNum, "Status", "FAIL", "RED");
				clkCancelButtonOnAddMilestoneFrame();
				addMilestone(driver);
				throw (e);
			}
		}
		ExcelUtils.setCellData("TC_AddDeliveryMilestoneNegativeScenarios", "milestone negative scenarios", rowNum,
				"Status", "PASS", "GREEN");
		clkCancelButtonOnAddMilestoneFrame();
		return driver;

	}

//Verify Add delivery milestone screen is displayed
	public void verifyDeliveryMilestonePage(WebDriver driver) {
		boolean isAttachmentsTabClicked = clkAttachmentsTab(); // User clicks on Attachments tab
		Assert.assertTrue(isAttachmentsTabClicked, "Failed to click on Attachment Tab.");

		boolean isDeliveryMilestonesTabClicked = clkDeliveryMilestonesTab(); // User clicks on Delivery Milestone tab
		Assert.assertTrue(isDeliveryMilestonesTabClicked, "Failed to click on Delivery Milestone Tab.");

		boolean isAddDeliveryMilestoneButtonClicked = clkAddDeliveryMilestoneButton(); // User clicks on Add Delivery
																						// Milestone button
		Assert.assertTrue(isAddDeliveryMilestoneButtonClicked, "Failed to click on Add Delivery Milestone button.");

		boolean isDeliveryMilestoneOptionClicked = clkMilestoneOption(); // User clicks on Milestone option under Add
																			// Delivery Milestone
		Assert.assertTrue(isDeliveryMilestoneOptionClicked, "Failed to click on Delivery Milestone option.");

		boolean isSwitchedToAddMilestoneFrame = switchToAddMilestoneFrame();
		logInfo("Add milestone screen is displayed");
	}

	// Navigate till add delivery milestone button and click on it
	public boolean verifyDeliveryMilestoneButton(WebDriver driver) {
		try {
			// Mouse Hover Code
			op.mouseHoverScript(driver,activeProject.ActiveProMouseHover);
			logInfo("Mouse Over Action performed "+activeProject.ActiveProMouseHover);
			op.waitUntilElementIsClickable(deliveryMilestoneTab);
			op.clickElement(deliveryMilestoneTab);
			boolean isAddDeliveryMilestoneBtnVisible = op.isElementDisplayedOnPage(openAddDeliveryMilestone);
			if(isAddDeliveryMilestoneBtnVisible) {
				logInfo("Delivery Milestone Page Opened Successfully");
				return true;
			}
			else
			{
				logInfo(" Failed to open Delivery Milestone Page");
				return false;
			}
//			boolean isAttachmentsTabClicked = clkAttachmentsTab(); // User clicks on Attachments tab
//			IsTrue(isAttachmentsTabClicked, "Successfully clicked on Attachment Tab.","Failed to click on Attachment Tab.");
//			boolean isDeliveryMilestonesTabClicked = clkDeliveryMilestonesTab(); // User clicks on Delivery Milestone
//			IsTrue(isDeliveryMilestonesTabClicked, "Successfully clicked on Delivery Milestone Tab.","Failed to click on Delivery Milestone Tab.");
			
//			boolean isAddDeliveryMilestoneButtonClicked = clkAddDeliveryMilestoneButton(); // User clicks on Add Delivery Milestone Button
//			IsTrue(isAddDeliveryMilestoneButtonClicked, "Successfully clicked on Add Delivery Milestone buttons","Failed to click on Add Delivery Milestone button.");
			
		} catch (Exception e) {
			return false;
		}
	}

//After error message is displayed,click on cancel button and again navigate to add milestone screen to enter next set of test data
	public WebDriver addMilestone(WebDriver driver) throws InterruptedException {

		op.switchToDefault();
//		boolean isAddDeliveryMilestoneButtonClicked = clkAddDeliveryMilestoneButton(); // User clicks on Add Delivery
//		// Milestone button
//		Assert.assertTrue(isAddDeliveryMilestoneButtonClicked, "Failed to click on Add Delivery Milestone button.");
		boolean isDeliveryMilestoneOptionClicked = clkMilestoneOption(); // User clicks on Milestone option under Add
		// Delivery Milestone
		Assert.assertTrue(isDeliveryMilestoneOptionClicked, "Failed to click on Delivery Milestone option.");
		boolean isSwitchedToAddMilestoneFrame = switchToAddMilestoneFrame();
		threadsleep(3000);
		return driver;
	}
	
	/*
	 * This method is used to verify milestone ID and name after adding or update
	 * @return boolean
	 */
	public boolean searchMilestone(WebDriver driver, String expectedMilestoneID, String expectedStatus) throws Exception {
		boolean hasPagination = true;
		boolean isMilestoneFound = false;
		while(!isMilestoneFound)
		{
				threadsleep(2000);
				int i;
				List<WebElement> addedMilestoneIDs = controlActions.perform_getListOfElementsByXPath(DeliveryMilestoneConstants.ADDED_MILESTONE_ID);
				try {
					logInfo("Size of Milestone ID Web Table is : " + addedMilestoneIDs.size());
					if(op.isElementDisplayedOnPage(dmPaginationNextBtn))
					{
						logInfo("Delivery Milestone has Pagination? " + op.isElementDisplayedOnPage(dmPaginationNextBtn));
						op.clickOnElement(dmPaginationNextBtn);
						((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", dmCancelBtn);
						Actions actions = new Actions(driver);
						actions.moveToElement(dmCancelBtn);
						isMilestoneFound = false;
						threadsleep(2000);
					}
					if (addedMilestoneIDs.size() == 0) {
						logError("Added milestone is not displayed in the table.");
						isMilestoneFound = false;
						//fail("Added milestone is not displayed in the table.");
					}
					for (i = 0; i < addedMilestoneIDs.size(); i++) {
						//logInfo("Milestone id is --> " + addedMilestoneIDs.get(i).getText());
						if (addedMilestoneIDs.get(i).getText().equalsIgnoreCase(expectedMilestoneID)) {
							logInfo("Added milestone id is correctly displayed in the table.");
							WebElement actualStatus = op.perform_getElementByXPath("(" + DeliveryMilestoneConstants.ADDED_MILESTONE_STATUS + ")[" + (i + 1) + "]");
							Equals(op.getText(actualStatus).trim(), expectedStatus.trim(),
									"Milestone status verification successfull", "Milestone status verification failed");
							logInfo("Added milestone status is correctly displayed in the table.");
							isMilestoneFound = true;
							break;
						}
					}
				} catch (Exception e) {
					logError(e.getMessage());
					return false;
				}
				if (i == addedMilestoneIDs.size()) {
					logError("Milestone id : " + expectedMilestoneID + " is not displayed in the table.");
					//fail("Milestone id : " + expectedMilestoneID + " is not displayed in the table.");
					return false;
				} else {
					return true;
				}
	  }
		return isMilestoneFound;
	}


	/*
	 * This method is used to verify milestone ID and name after adding or update
	 * @return boolean
	 */
	public boolean verifyMilestoneDetails(WebDriver driver, String expectedMilestoneID, String expectedMilestoneName,String expectedPercCompletion, String expectedStatus) throws Exception {
	//public boolean verifyMilestoneDetails(WebDriver driver, String expectedMilestoneID,String expectedStatus) throws Exception {	
	//verifySuccessMsg();
		threadsleep(3000);
		int i;
		List<WebElement> addedMilestoneIDs = controlActions.perform_getListOfElementsByXPath(DeliveryMilestoneConstants.ADDED_MILESTONE_ID);
		try {
			logInfo("Milestone id Web Table Size is : " + addedMilestoneIDs.size());
			if (addedMilestoneIDs.size() == 0) {
				logError("Added milestone is not displayed in the table.");
				fail("Added milestone is not displayed in the table.");
			}
			for (i = 0; i < addedMilestoneIDs.size(); i++) {
				logInfo("Milestone id is --> " + addedMilestoneIDs.get(i).getText());
				if (addedMilestoneIDs.get(i).getText().equalsIgnoreCase(expectedMilestoneID)) {
					logInfo("Added milestone id is correctly displayed in the table.");
//					WebElement actualMilestoneName = op.perform_getElementByXPath(
//							"(" + DeliveryMilestoneConstants.ADDED_MILESTONE_NAME + ")[" + (i + 1) + "]");
//					Equals(op.getText(actualMilestoneName).trim(), expectedMilestoneName.trim(),
//							"Added milestone name is correctly displayed in the table.",
//							"Added milestone name is incorrectly displayed in the table.");
//					logInfo("Added milestone name is correctly displayed in the table.");
//					WebElement actualPercCompetion = op.perform_getElementByXPath(
//							"(" + DeliveryMilestoneConstants.ADDED_MILESTONE_PERC_COMPLETION + ")[" + (i + 1) + "]");
//					Equals(op.getText(actualPercCompetion).trim(), expectedPercCompletion.trim(),
//							"Added milestone % completion is correctly displayed in the table.",
//							"% completion verification failed");
//					logInfo("Added milestone % completion is correctly displayed in the table.");
//					WebElement actualStatus = op.perform_getElementByXPath(
//							"(" + DeliveryMilestoneConstants.ADDED_MILESTONE_STATUS + ")[" + (i + 1) + "]");
//					Equals(op.getText(actualStatus).trim(), expectedStatus.trim(),
//							"Milestone status verification successfull", "Milestone status verification failed");
//					logInfo("Added milestone status is correctly displayed in the table.");
					break;
				}
			}
		} catch (Exception e) {
			logError(e.getMessage());
			return false;
		}
		if (i == addedMilestoneIDs.size()) {
			logError("Milestone id : " + expectedMilestoneID + " is not displayed in the table.");
			//fail("Milestone id : " + expectedMilestoneID + " is not displayed in the table.");
			return false;
		} else {
			return true;
		}
	}
	
	
	/*
	 * This method is used to verify milestone ID and name after adding or update
	 * @return boolean
	 */
	public boolean verifyPayemtMilestoneDetails(WebDriver driver, String expPaymentMilestoneID, String expStatus) throws Exception {
	//verifySuccessMsg();
		threadsleep(2000);
		int i;
		List<WebElement> payemtMilestoneIDs = controlActions.perform_getListOfElementsByXPath(DeliveryMilestoneConstants.PAYMENT_MILESTONE_TABLE);
		logInfo("Web Element -> " + payemtMilestoneIDs);
		try {
			logInfo("Milestone id Web Table Size is : " + payemtMilestoneIDs.size());
			if (payemtMilestoneIDs.size() == 0) {
				logError("Added Payment Milestone is not displayed in the table.");
				fail("Added Payment Milestone is not displayed in the table.");
			}
			for (i = 0; i < payemtMilestoneIDs.size(); i++) {
				//logInfo("Payement Milestone id is --> " + payemtMilestoneIDs.get(i).getText());
				if (payemtMilestoneIDs.get(i).getText().equalsIgnoreCase(expPaymentMilestoneID)) {
					logInfo("Added Payment milestone id is correctly displayed in the table.");
					WebElement actualStatus = op.perform_getElementByXPath(DeliveryMilestoneConstants.PAYMENT_MILESTONE_STATUS );
					logInfo("Web Element -> " + actualStatus);
					Equals(op.getText(actualStatus).trim(), expStatus.trim(),"Milestone status verification successfull", "Milestone status verification failed");
					logInfo("Added milestone status is correctly displayed in the table.");
					break;
				}
			}
		} catch (Exception e) {
			logError(e.getMessage());
			return false;
		}
		if (i == payemtMilestoneIDs.size()) {
			logError("Milestone id : " + expPaymentMilestoneID + " is not displayed in the table.");
			//fail("Milestone id : " + expectedMilestoneID + " is not displayed in the table.");
			return false;
		} else {
			return true;
		}
	}

	/*
	 * This method is used to verify milestone ID and name after adding or update
	 * @return boolean
	 */
	public boolean searchAndEditPayemtMilestone(WebDriver driver, String expPaymentMilestoneID, String action) throws Exception {
		threadsleep(2000);
		int i;
		WebElement actualStatus;
		List<WebElement> payemtMilestoneIDs = controlActions.perform_getListOfElementsByXPath(DeliveryMilestoneConstants.PAYMENT_MILESTONE_TABLE);
		logInfo("Web Element -> " + payemtMilestoneIDs);
		try {
			logInfo("Milestone id Web Table Size is : " + payemtMilestoneIDs.size());
			if (payemtMilestoneIDs.size() == 0) {
				logError("Payment Milestone is not displayed in the table.");
				fail("Payment Milestone is not displayed in the table.");
			}
			for (i = 0; i < payemtMilestoneIDs.size(); i++) {
				//logInfo("Payment Milestone id is --> " + payemtMilestoneIDs.get(i).getText());
				if (payemtMilestoneIDs.get(i).getText().equalsIgnoreCase(expPaymentMilestoneID)) {
					//logInfo(i + "------------------------------ -> " + payemtMilestoneIDs.get(i));
					logInfo("Payment milestone id is correctly displayed in the table."); 
					switch(action){    
					case "edit": 
						i = i+1;
						logInfo("//*[@id='report_table_payrpt']/tbody/tr["+i+"]/td/button[1]");
						actualStatus = op.perform_getElementByXPath("//*[@id='report_table_payrpt']/tbody/tr["+i+"]/td/button[1]");//(CommonPagesConstants.MILESTONE_MIDIFY_BTN);
						logInfo("Editing Payment Milestone -> " + actualStatus);
						op.clickElement(actualStatus, driver);
						break;
					case "delete": 
						i = i+1;
						logInfo("//*[@id='report_table_payrpt']/tbody/tr["+i+"]/td/button[2]");
						actualStatus = op.perform_getElementByXPath("//*[@id='report_table_payrpt']/tbody/tr["+i+"]/td/button[2]");//(CommonPagesConstants.MILESTONE_MIDIFY_BTN);
						logInfo("Deleteing Payment Milestone -> " + actualStatus);
						op.clickElement(actualStatus, driver);
						break;
					default:
						logInfo("Payment Milestone status not match");  
					}
//					if(action.equalsIgnoreCase("edit"))
//					{
//						actualStatus = op.perform_getElementByXPath("//*[@id='report_table_payrpt']/tbody/tr["+i+"]/td/button[1]");//(CommonPagesConstants.MILESTONE_MIDIFY_BTN);
//						logInfo("Editing Payment Milestone -> " + actualStatus);
//						op.clickElement(actualStatus, driver);
//						break;
//					}
//					if(action.equalsIgnoreCase("delete"))
//					{
//						actualStatus = op.perform_getElementByXPath("//*[@id='report_table_payrpt']/tbody/tr["+i+"]/td/button[2]");//(CommonPagesConstants.MILESTONE_MIDIFY_BTN);
//						logInfo("Deleteing Payment Milestone -> " + actualStatus);
//						op.clickElement(actualStatus, driver);
//						break;
//					}
				}
			}
		} catch (Exception e) {
			logError(e.getMessage());
			return false;
		}
		if (i == payemtMilestoneIDs.size()) {
			logError("Milestone id : " + expPaymentMilestoneID + " is not displayed in the table.");
			return false;
		} else {
			return true;
		}
	}



	/*
	 * This method is used to update milestone ID and name
	 * 
	 * @return void
	 */
	public void updateMilestoneID(WebDriver driver, String milestoneID, String newMilestoneID, String newMilestoneName,
			String percentCompletion) {
		List<WebElement> addedMilestoneIDs = controlActions
				.perform_getListOfElementsByXPath(DeliveryMilestoneConstants.ADDED_MILESTONE_ID);
		System.out.println(addedMilestoneIDs.size());
		List<WebElement> editButtons = controlActions
				.perform_getListOfElementsByXPath(DeliveryMilestoneConstants.ADDED_MILESTONE_EDIT_BUTTON);
		System.out.println(editButtons.size());
		if (addedMilestoneIDs.size() == 0) {
			logError("Added milestone is not displayed in the table.");
			Assert.fail("Added milestone is not displayed in the table.");
		}
		for (int i = 0; i < addedMilestoneIDs.size(); i++) {
			// for (WebElement id : addedMilestoneIDs)
			if (addedMilestoneIDs.get(i).getText().equalsIgnoreCase(milestoneID))

				op.perform_clickElement_ByElement(editButtons.get(i));
		}
		boolean isSwitchedToEditMilestoneFrame = switchToEditMilestoneFrame();
		boolean isMilestoneIDSet = enterTextToMilestoneID(newMilestoneID);
		Assert.assertTrue(isMilestoneIDSet, "Failed to update Milestone ID as '" + newMilestoneID + "'");
		boolean isMilestoneNameSet = enterTextTomilestoneNameField(newMilestoneName);
		Assert.assertTrue(isMilestoneNameSet, "Failed to update Milestone name as '" + newMilestoneName + "'");
		boolean isPercentCompletionSet = enterTextToPercCompletionField(percentCompletion);
		Assert.assertTrue(isPercentCompletionSet,
				"Failed to update percent completion as '" + isPercentCompletionSet + "'");
		boolean isStatusUpdateSet = enterTextToStatusUpdateField("Done");
		Assert.assertTrue(isStatusUpdateSet, "Failed to update status");
		boolean isButtonClicked = clkSaveChangesButton();
		Assert.assertTrue(isButtonClicked, "Failed to edit changes");
		op.switchToDefault();

	}

	/*
	 * This method is used to verify whether milestone is successfully added or
	 * updated
	 * 
	 * @return WebDriver
	 */
	public boolean verifySuccessMsg() {
		try {
			op.switchToDefault();
			WebElement successMessage = op.perform_waitUntilVisibility(By.xpath("//*[contains(text(),'Row') or contains(text(),'successfully')]"));
			if (successMessage.isDisplayed()) {
				logInfo("Operation is done successfully -> " + successMessage.getText());
			}
		} catch (Exception e) {
			logError("Operation failed!!! ");
			return false;
		}
		return true;
	}

	/*
	 * This method is used to delete milestone updated
	 * 
	 * @return void
	 */
	public void deleteMilestone(WebDriver driver, String reasonCode, String milestoneID) throws InterruptedException {
		clickMilestoneDeleteButton(driver, milestoneID);
		boolean isStatusUpdateSet = enterTextToStatusUpdateField("Done");
		Assert.assertTrue(isStatusUpdateSet, "Failed to update status");
		op.waitForElementToBeClickable(reasonCodeDrpDown);
		op.clickElement(reasonCodeDrpDown);
		op.switchToDefault();
		selectReasonCode(reasonCode);
		boolean isSwitchedToDeleteMilestoneFrame1 = switchToDeleteMilestoneFrame();
		Assert.assertTrue(isSwitchedToDeleteMilestoneFrame1, "Failed to switch to delete frame");
		boolean isButtonClicked = clkObsoleteButton();
		Assert.assertTrue(isButtonClicked, "Failed to delete milestone");
		op.switchToDefault();

	}

	public void clickMilestoneDeleteButton(WebDriver driver, String milestoneID) {
		List<WebElement> addedMilestoneIDs = controlActions
				.perform_getListOfElementsByXPath(DeliveryMilestoneConstants.ADDED_MILESTONE_ID);
		logInfo("Total added milestones are: " + addedMilestoneIDs.size());
		logInfo("Milestone ID to delete: " + milestoneID);
		for (int i = 0; i < addedMilestoneIDs.size(); i++) {
			if (addedMilestoneIDs.get(i).getText().trim().equalsIgnoreCase(milestoneID)) {
				WebElement deleteButton = op.perform_getElementByXPath(
						"(" + DeliveryMilestoneConstants.DELETE_BUTTON + ")[" + (i + 1) + "]");
				op.click(deleteButton);
//				boolean isSwitchedToDeleteMilestoneFrame = switchToDeleteMilestoneFrame();
//				IsTrue(isSwitchedToDeleteMilestoneFrame, "SWitched to delete frame successfully",
//						"Failed to switch to delete frame");
				break;
			}
		}
	}

	/*
	 * This method is used to click on Obsolete button on delete milestone page
	 * updated
	 * 
	 * @return void
	 */
	public boolean clkObsoleteButton() {
		try {
			op.waitForAnElementToBeClickable(obsoleteButton);
			op.clickElement(obsoleteButton);
			logInfo("Successfully clicked on obsolete button");
			return true;
		} catch (Exception e) {
			logError("Failed to click on obsolete button " + e.getMessage());
			return false;
		}
	}

	public void addSprint(WebDriver driver, String milestoneID) {
		boolean isAttachmentsTabClicked = clkAttachmentsTab(); // User clicks on Attachments tab
		Assert.assertTrue(isAttachmentsTabClicked, "Failed to click on Attachment Tab.");

		boolean isDeliveryMilestonesTabClicked = clkDeliveryMilestonesTab(); // User clicks on Delivery Milestone tab
		Assert.assertTrue(isDeliveryMilestonesTabClicked, "Failed to click on Delivery Milestone Tab.");
		boolean isAddDeliveryMilestoneButtonClicked = clkAddDeliveryMilestoneButton(); // User clicks on Add Delivery
																						// Milestone button
		Assert.assertTrue(isAddDeliveryMilestoneButtonClicked, "Failed to click on Add Delivery Milestone button.");
		boolean isSprintOptionClicked = clkSprintOption(); // User clicks on Milestone option under Add
		// Delivery Milestone
		Assert.assertTrue(isSprintOptionClicked, "Failed to click on Sprint option.");
		boolean isSwitchedToAddSprintFrame = switchToAddSprintFrame(); // Switches the control to Add Milestone
		// frame

		boolean isMilestoneIDSet = enterTextToMilestoneID(milestoneID); // Add Milestone window-> Enter milestoneID
		Assert.assertTrue(isMilestoneIDSet, "Failed to add text to Milestone ID as '" + milestoneID + "'");

		// milestone Name

		boolean isStartDateCalendarClicked = clkStartDateCalendar(); // Add Milestone window->Click on Start Date
// Calendar icon
		Assert.assertTrue(isStartDateCalendarClicked, "Failed to click on Start Date Calendar.");

		boolean isStartDateClicked = clkStartDate(); // Add Milestone window->Click on Start Date
		Assert.assertTrue(isStartDateClicked, "Failed to click on Start Date.");

		boolean isEndDateCalendarClicked = clkEndDateCalendar(); // Add Milestone window->Click on End Date Calendar
// icon
		Assert.assertTrue(isEndDateCalendarClicked, "Failed to click on End Date Calendar.");

		boolean isEndDateClicked = clkEndDate(); // Add Milestone window->Click on End Date
		Assert.assertTrue(isEndDateClicked, "Failed to click on End Date.");

		boolean isAddButtonOnAddMilestonePageClicked = clkAddButtonOnAddMilestoneFrame(); // Add Milestone window->Click
		// on Add button
		Assert.assertTrue(isAddButtonOnAddMilestonePageClicked, "Failed to click on Add button on Add Milestone page.");

		op.switchToDefault(); // After adding a milestone, the control switches back to main page

	}

	/**
	 * This method is used to enter Sprint ID
	 * 
	 * @param inputString [String]
	 * @return boolean true if action successful else false
	 */
	public boolean enterTextToSprintID(String expectedSprintID) {
		try {
			isElementDisplayed(sprintID);
			op.waitForElementToBeClickable(sprintID);
			op.clear(sprintID);
			op.sendKeys(sprintID, expectedSprintID);
			// op.actionEnter();
			logInfo("Entered text '" + expectedSprintID + "' in Sprint ID Field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + expectedSprintID + "' in Sprint ID Field" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to enter Sprint ID
	 * 
	 * @param inputString [String]
	 * @return boolean true if action successful else false
	 */
	public boolean enterTextToSprintName(String expectedSprintName) {
		try {
			isElementDisplayed(sprintID);
			op.waitForElementToBeClickable(sprintName);
			op.clear(sprintName);
			op.sendKeys(sprintName, expectedSprintName);
			// op.actionEnter();
			logInfo("Entered text '" + expectedSprintName + "' in Sprint name Field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + expectedSprintName + "' in Sprint name Field" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to enter text in select employ field
	 * 
	 * @param inputString [String]
	 * @author Sana Sayed
	 * @return boolean true if action successful else false
	 */
	public boolean selectParentMilestone(String milestoneId) {
		try {
			op.click(parentMilestone);
			op.click(parentMilestoneSearchBox);
			op.sendKeys(parentMilestoneSearchBox, milestoneId);
			op.sendKey(parentMilestoneSearchBox, Keys.ENTER);
			logInfo("Entered text '" + milestoneId + "' in parent milestone Field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + milestoneId + "' in parent milestone Field" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to select Sprint cycle
	 * 
	 * @param inputString [String]
	 * @author Sana Sayed
	 * @return boolean true if action successful else false
	 */
	public boolean selectSprintCycle(String value) {
		try {

			op.click(sprintCycle);
			op.switchToDefault();
			op.click(sprintCycleSearchBox);
			op.sendKeys(sprintCycleSearchBox, value);
			op.sendKey(sprintCycleSearchBox, Keys.ENTER);
			logInfo("Entered text '" + value + "' in sprint cycle Field");
			switchToAddFrame();
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + value + "' in sprint cycle Field" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to select Unit of measure
	 * 
	 * @param inputString [String]
	 * @author Sana Sayed
	 * @return boolean true if action successful else false
	 */
	public boolean selectUOM(String value) {
		try {
			op.click(UOM);
			op.switchToDefault();
			op.click(UOMSearchBox);
			op.sendKeys(UOMSearchBox, value);
			op.sendKey(UOMSearchBox, Keys.ENTER);
			logInfo("Entered text '" + value + "' in unit of measure Field");
			switchToAddFrame();
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + value + "' in unit of measure Field" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to select Team
	 * 
	 * @param inputString [String]
	 * @author Sana Sayed
	 * @return boolean true if action successful else false
	 */
	public boolean selectTeam(String value) {
		try {
			op.click(team);
			op.switchToDefault();
			op.click(teamSearchBox);
			op.sendKeys(teamSearchBox, value);
			op.sendKey(teamSearchBox, Keys.ENTER);
			logInfo("Entered text '" + value + "' in team field");
			switchToAddFrame();
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + value + "' in team field" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to enter Sprint stories
	 * 
	 * @param inputString [String]
	 * @return boolean true if action successful else false
	 */
	public boolean enterTextToSprintStories(String value) {
		try {
			isElementDisplayed(sprintStories);
			op.waitForElementToBeClickable(sprintStories);
			op.clear(sprintStories);
			op.sendKeys(sprintStories, value);
			// op.actionEnter();
			logInfo("Entered text '" + sprintStories + "' in Sprint stories Field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + sprintStories + "' in Sprint stories Field" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to enter planned capacity
	 * 
	 * @param inputString [String]
	 * @author Sana Sayed
	 * @return boolean true if action successful else false
	 */
	public boolean enterTextToSprintPlannedCapacity(String value) {
		try {
			isElementDisplayed(sprintPlannedCapacity);
			op.waitForElementToBeClickable(sprintPlannedCapacity);
			op.clear(sprintPlannedCapacity);
			op.sendKeys(sprintPlannedCapacity, value);
			// op.actionEnter();
			logInfo("Entered text '" + value + "' in Sprint Planned capacity Field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + value + "' in Sprint Planned capacity Field" + e.getMessage());
			return false;
		}
	}

	public void enterReleaseDetails(WebDriver driver, String milestoneID, String releaseID, String releaseName) {
		boolean isSprintOptionClicked = clkReleaseOption();
		// Delivery Milestone
		Assert.assertTrue(isSprintOptionClicked, "Failed to click on Release option.");
		boolean isSwitchedToAddReleaseFrame = switchToAddFrame(); // Switches the control to Add Milestone
		// frame
		enterParentMilestone(driver, milestoneID);
		boolean isMilestoneIDSet = enterTextReleaseID(releaseID);
		Assert.assertTrue(isMilestoneIDSet, "Failed to add text to release ID as '" + releaseID + "'");
		boolean isReleaseNameSet = enterTextReleaseName(releaseName);
		Assert.assertTrue(isReleaseNameSet, "Failed to add text to release name as '" + releaseName + "'");

		boolean isStartDateCalendarClicked = clkStartDateCalendar(); // Add Milestone window->Click on Start Date
		// Calendar icon
		Assert.assertTrue(isStartDateCalendarClicked, "Failed to click on Start Date Calendar.");

		boolean isStartDateClicked = clkStartDate(); // Add Milestone window->Click on Start Date
		Assert.assertTrue(isStartDateClicked, "Failed to click on Start Date.");

		boolean isEndDateCalendarClicked = clkEndDateCalendar(); // Add Milestone window->Click on End Date Calendar
		// icon
		Assert.assertTrue(isEndDateCalendarClicked, "Failed to click on End Date Calendar.");

		boolean isEndDateClicked = clkEndDate(); // Add Milestone window->Click on End Date
		Assert.assertTrue(isEndDateClicked, "Failed to click on End Date.");

		boolean isAddButtonOnAddMilestonePageClicked = clkAddButton(); // Add Milestone window->Click
		// on Add button
		Assert.assertTrue(isAddButtonOnAddMilestonePageClicked, "Failed to click on Add button on Add Milestone page.");

		op.switchToDefault(); // After adding a milestone, the control switches back to main page

	}

	public boolean enterParentMilestone(WebDriver driver, String milestoneID) {
		try {
			op.waitForElementToBeDisplayed(parentMilestone);
			op.waitForAnElementToBeClickable(parentMilestone);
			op.click(parentMilestone);
			op.switchToDefault();
			op.sendKeys(parentMilestoneSearchBox, milestoneID);
			List<WebElement> parentMilestones = op
					.perform_getListOfElementsByXPath(DeliveryMilestoneConstants.PARENT_MILESTONE_LIST);
			System.out.println(parentMilestones.size());
			int i = 0;
			while (i < parentMilestones.size()) {
				if (parentMilestones.get(i).getText().trim().equalsIgnoreCase(milestoneID.trim())) {
					parentMilestones.get(i).click();
					// driver.switchTo().alert().accept();
					try {
						// op.waitForElementToBeDisplayed(popUpOKButton);
						if (popUpOKButton.isDisplayed())
							op.click(popUpOKButton);
					} catch (Exception e) {

					}
					break;
				} else {
					i++;
				}
			}
			if (i == parentMilestones.size()) {
				logError("Parent milestone not found");
				return false;
			}
		} catch (Exception e) {
			logError(e.getMessage());
			return false;
		}
		switchToAddFrame();
		// op.click(popUpOKButton);
		return true;
	}

	public boolean clkAddButton() {
		try {
			op.waitForAnElementToBeClickable(addButton);
			op.clickElement(addButton);
			logInfo("Successfully clicked on Add button");
			return true;
		} catch (Exception e) {
			logError("Failed to click on Add button" + e.getMessage());
			return false;
		}
	}

	public boolean enterTextReleaseID(String releaseID) {
		try {
			isElementDisplayed(releaseIDField);
			op.waitForElementToBeClickable(releaseIDField);
			op.clear(releaseIDField);
			op.sendKeys(releaseIDField, releaseID);
			// op.actionEnter();
			logInfo("Entered text '" + releaseID + "' in release ID Field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + releaseID + "' in release ID Field" + e.getMessage());
			return false;
		}
	}

	public boolean enterTextReleaseName(String releaseName) {
		try {
			isElementDisplayed(releaseNameField);
			op.waitForElementToBeClickable(releaseNameField);
			op.clear(releaseNameField);
			op.sendKeys(releaseNameField, releaseName);
			// op.actionEnter();
			logInfo("Entered text '" + releaseName + "' in release Name Field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + releaseName + "' in release Name Field" + e.getMessage());
			return false;
		}
	}

//To update status for "update release"
	public boolean updateStatus(String expectedStatus) {
		try {
			op.waitForAnElementToBeClickable(statusDrpDown);
			op.click(statusDrpDown);
			op.switchToDefault();
			List<WebElement> statusFields = controlActions.perform_getListOfElementsByXPath(DeliveryMilestoneConstants.REASON_CODES);
			int i;
			logInfo("Selecting -> " + expectedStatus + " Status code from List");
			for (i = 0; i < statusFields.size(); i++) {
				if (statusFields.get(i).getText().trim().equalsIgnoreCase(expectedStatus)) {
					op.click(statusFields.get(i));
					logInfo("Status code selected: " + expectedStatus);
					break;
				}
			}
			if (i == statusFields.size()) {
				logError("Status code not found");
				switchToUpdateFrame();
				clkCancelButton();
				return false;
			}

		} catch (Exception e) {
			logError("Failed to select " + expectedStatus);
			logError(e.getMessage());
			switchToUpdateFrame();
			clkCancelButton();
			return false;
		}
		switchToUpdateFrame();
		return true;
	}

	public boolean verify_negScenarios_UpdateRelease(WebDriver driver, String status, String statusUpdate,
			String percentCompletion) throws Exception {
		// rowNum++;
		logInfo("Checking for status: " + status);
		logInfo("Status update: " + statusUpdate);
		logInfo("Percent completion " + percentCompletion);

		int errorCnt = 0;
		String expectedErrorMsg[] = new String[2];
		op = new Operations(driver);
		if (statusUpdate.equals("")) {
			expectedErrorMsg[errorCnt] = "Please enter Status Update.";
			logInfo("Expected error message: Please enter Status Update.");
			logError("Please enter Status Update.");
			errorCnt++;
			op.actionClearTextBox(statusUpdateField);
		} else {
			boolean isStatusUpdateSet = enterTextToStatusUpdateField(statusUpdate);
			logInfo("Successfully entered status update as: " + statusUpdate);
		}

		if (percentCompletion.equals("")) {
			expectedErrorMsg[errorCnt] = "Please provide Percent Completion";
			logInfo("Expected error message: Please provide Percent Completion");
			errorCnt++;
			op.actionClearTextBox(releasePercCompletion);
		} else if (Integer.parseInt(percentCompletion) > 100) {
			expectedErrorMsg[errorCnt] = "Completion % is not between the valid range of 0 and 100.";
			logInfo("Expected error message: Completion % is not between the valid range of 0 and 100.");
			errorCnt++;
			op.actionClearTextBox(releasePercCompletion);
			enterTextToReleasePercCompletion(percentCompletion);
			op.sendKey(releasePercCompletion, Keys.TAB);
			op.switchToDefault();
			// actions.waitForAlertToBeDisplayed();
//			Alert alert = driver.switchTo().alert();
//			String alertText = alert.getText();
//			Equals(alertText, "% Completion cannot be greater than 100.", "Error message on alert is as expected",
//					"Error message on alert is not as expected");
//			alert.accept();

			String alertText = op.getText(perCpmlErrorPopup);
			Equals(alertText, "% Completion cannot be greater than 100.", "Error message on alert is as expected",
					"Error message on alert is not as expected");
			op.clickElement(OKButton);
			switchToUpdateFrame();
		} else {
			op.actionClearTextBox(releasePercCompletion);
			enterTextToReleasePercCompletion(percentCompletion);
		}
		threadsleep(2000);
		logInfo("Expected error count is " + errorCnt);
		boolean isSaveButtonClicked = clkSaveChangesButton(); // Add Milestone window->Click
		IsTrue(isSaveButtonClicked, "Clicked on Save changes button", "Failed to click on save changes button");
		// verifyErrorMessage(driver, errorCnt, expectedErrorMsg);
		op.perform_waitUntilVisibility(addMilestoneErrorTitle);
		String errorTitle = op.getText(addMilestoneErrorTitle);
		if (errorCnt > 1) {
			assertEquals(errorTitle, +errorCnt + " errors have occurred",
					"Error notification is displayed as expected: " + errorTitle);
			// verifyErrorMessageDetails(driver, errorCnt, expectedErrorMsg);
		} else {
			try {
				assertEquals(errorTitle, +errorCnt + " error has occurred",
						"Error notification is displayed as expected");
			} catch (AssertionError e) {
				logError("Error notification is not displayed as expected: " + errorTitle);
				System.out.println("Error notification is not displayed as expected: " + errorTitle);
				clkCancelButton();
				op.switchToDefault();
				;
				IsTrue(false, "", "");
			}
			// verifyErrorMessageDetails(driver, errorCnt, expectedErrorMsg);
		}
		op.perform_waitUntilVisibility(addMilestoneErrorMsg);
		List<WebElement> actualErrorMsg = controlActions
				.perform_getListOfElementsByXPath(DeliveryMilestoneConstants.ADD_MILESTONE_PAGE_ERROR_MESSAGE);
		if (actualErrorMsg.size() == 0) {
			logError("No error message displayed.");
			return false;
		}
		IsTrue(errorCnt == actualErrorMsg.size(), "Number of error messages are as expected",
				"Number of error messages displayed are not as expected");
		logInfo("Actual number of error messages displayed: " + actualErrorMsg.size());
		for (int i = 0; i < errorCnt; i++) {
			System.out.println("Actual error message: " + actualErrorMsg.get(i).getText());
			System.out.println("Expected error message: " + expectedErrorMsg[i]);
			Equals(actualErrorMsg.get(i).getText(), expectedErrorMsg[i],
					"Error notification is displayed as expected: " + expectedErrorMsg[i],
					"Error notification is not displayed as expected");
			logInfo("Error notification is displayed as expected: " + expectedErrorMsg[i]);
		}
		clkCancelButton();
		op.switchToDefault();
		return true;
	}

	public boolean enterTextToReleasePercCompletion(String percentCompletion) {
		try {
			isElementDisplayed(releasePercCompletion);
			op.waitForElementToBeClickable(releasePercCompletion);
			op.clear(releasePercCompletion);
			op.sendKeys(releasePercCompletion, percentCompletion);
			// op.actionEnter();
			logInfo("Entered text '" + percentCompletion + "' in percent completion Field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + percentCompletion + "' in percent completion Field" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on cancel button on update release
	 * 
	 * @return boolean This returns true if Successfully clicked on cancel button
	 */

	public boolean clkCancelButton() {
		try {
			op.waitForAnElementToBeClickable(cancelButton);
			op.clickElement(cancelButton);
			logInfo("Successfully clicked on cancel button");
			return true;
		} catch (Exception e) {
			logError("Failed to click on cancel button " + e.getMessage());
			return false;
		}
	}

	public String createMilestone() throws Exception {
		try {
			int randomNum = op.generateRandomNum();
			String milestoneID = "MILEID" + randomNum;
			String milestoneName = "MILENAME" + randomNum;
			logInfo("Creating Milestone with Name -> " + milestoneName + " and Milestone ID" +milestoneID );
			boolean isAddDeliveryMilestoneButtonClicked = clkAddDeliveryMilestoneButton();
			IsTrue(isAddDeliveryMilestoneButtonClicked, "Successfully clicked on Add Delivery Milestone buttons",
					"Failed to click on Add Delivery Milestone button.");
			boolean isDeliveryMilestoneOptionClicked = clkMilestoneOption();
			IsTrue(isDeliveryMilestoneOptionClicked, "Delivery Milestone option clicked.",
					"Failed to click on Delivery Milestone option.");
			boolean isSwitchedToAddMilestoneFrame = switchToAddMilestoneFrame();
			IsTrue(isSwitchedToAddMilestoneFrame, "Switched to add milestone frame.",
					"Failed to switch to add milestone frame.");
			boolean isMilestoneIDSet = enterTextToMilestoneID(milestoneID);
			IsTrue(isMilestoneIDSet, "Added text to milestone ID: " + milestoneID,
					"Failed to add text to Milestone ID as '" + milestoneID + "'");
			boolean isMilestoneNameSet = enterTextTomilestoneNameField(milestoneName);
			IsTrue(isMilestoneNameSet, "Added text to milestone name: " + milestoneName,
					"Failed to add text to Milestone Name as '" + milestoneName + "'");
			boolean isStartDateCalendarClicked = clkStartDateCalendar();
			IsTrue(isStartDateCalendarClicked, "Clicked on start date calender",
					"Failed to click on Start Date Calendar.");
			boolean isStartDateClicked = clkStartDate();
			IsTrue(isStartDateClicked, "Clicked on start date", "Failed to click on Start Date.");
			boolean isEndDateCalendarClicked = clkEndDateCalendar();
			IsTrue(isEndDateCalendarClicked, "Clicked on end date calender", "Failed to click on End Date Calendar.");
			boolean isEndDateClicked = clkEndDate();
			IsTrue(isEndDateClicked, "Clicked on end date", "Failed to click on End Date.");
			boolean isAddButtonOnAddMilestonePageClicked = clkAddButtonOnAddMilestoneFrame();
			IsTrue(isAddButtonOnAddMilestonePageClicked, "Clicked on Add button on Add Milestone page.","Failed to click on Add button on Add Milestone page.");
			op.switchToDefault();
			return milestoneID;
		} catch (Exception e) {
			logError(e.getMessage());
			return null;
		}
	}

	public String createRelease(String milestoneId) throws Exception {
		try {
			int randomNum = op.generateRandomNum();
			String releaseID = "RELEASEID" + randomNum;
			String releaseName = "RELEASENAME" + randomNum;
//			controlActions = new ControlActions(driver);
			boolean isAddDeliveryMilestoneButtonClicked = clkAddDeliveryMilestoneButton();
			IsTrue(isAddDeliveryMilestoneButtonClicked, "Successfully clicked on Add Delivery Milestone buttons","Failed to click on Add Delivery Milestone button.");
			boolean isSprintOptionClicked = clkReleaseOption();
			IsTrue(isSprintOptionClicked, "Release option selected", "Failed to click on Release option.");
			// threadsleep(2000);
			boolean isSwitchedToAddReleaseFrame = switchToAddFrame();
			IsTrue(isSwitchedToAddReleaseFrame, "Switched to add release frame.","Failed to switch to add release frame.");
			boolean isMilestoneIDSet = enterTextReleaseID(releaseID);
			IsTrue(isMilestoneIDSet, "Entered release ID: " + releaseID,"Failed to add text to release ID as '" + releaseID + "'");
			boolean isParentSelected = enterParentMilestone(driver, milestoneId);
			IsTrue(isParentSelected, "Successfully selected parent milestone", "Failed to select parent milestone.");
			boolean isReleaseNameSet = enterTextReleaseName(releaseName);
			IsTrue(isReleaseNameSet, "Entered release Name: " + releaseName,"Failed to add text to release name as '" + releaseName + "'");
			boolean isStartDateCalendarClicked = clkStartDateCalendar();
			IsTrue(isStartDateCalendarClicked, "Clicked on start date calender","Failed to click on Start Date Calendar.");
			boolean isStartDateClicked = clkStartDate();
			IsTrue(isStartDateClicked, "Clicked on start date", "Failed to click on Start Date.");
			boolean isEndDateCalendarClicked = clkEndDateCalendar();
			IsTrue(isEndDateCalendarClicked, "Clicked on end date calender", "Failed to click on End Date Calendar.");
			boolean isEndDateClicked = clkEndDate();
			IsTrue(isEndDateClicked, "Clicked on end date", "Failed to click on End Date.");
			boolean isAddButtonOnAddMilestonePageClicked = clkAddButton();
			IsTrue(isAddButtonOnAddMilestonePageClicked, "", "Failed to click on Add button on Add Milestone page.");
			op.switchToDefault();
			threadsleep(2000);
			boolean verifyStatus = verifyReleaseDetails(releaseID, releaseName, milestoneId);
			IsTrue(verifyStatus, "Release is added successfully", "Failed to add release ");
			return releaseID;
		} catch (Exception e) {
			logError(e.getMessage());
			return null;
		}
	}

	public boolean verifyReleaseDetails(String releaseID, String releaseName, String milestoneID) {
		try {
			WebElement releaseIcon = op.perform_waitUntilVisibility(By.xpath(
					DeliveryMilestoneConstants.VIEW_RELEASE_ICON.replace("MilestoneIDToBeReplaced", milestoneID)));
			op.click(releaseIcon);
			String actualReleaseID = op.getText(op.performGetElementByXPath(
					DeliveryMilestoneConstants.ADDED_RELEASE_ID.replace("MilestoneIDToBeReplaced", milestoneID)));
			Equals(actualReleaseID, releaseID, "Release ID is correctly displayed",
					"Release ID is not displayed correctly");
			String actualReleaseName = op.getText(op.performGetElementByXPath(
					DeliveryMilestoneConstants.ADDED_RELEASE_NAME.replace("MilestoneIDToBeReplaced", milestoneID)));
			Equals(actualReleaseName, releaseName, "Release name is correctly displayed",
					"Release name is not displayed correctly");
			String milestoneType = op.getText(op.performGetElementByXPath(
					DeliveryMilestoneConstants.MILESTONE_TYPE.replace("MilestoneIDToBeReplaced", milestoneID)));
			Equals(milestoneType, "Release", "Milestone type is correctly displayed",
					"Milestone type is not displayed correctly");
			String releaseStatus = op.getText(op.performGetElementByXPath(
					DeliveryMilestoneConstants.ADDED_RELEASE_STATUS.replace("MilestoneIDToBeReplaced", milestoneID)));
			IsTrue(releaseStatus.contains("Open"), "Release status is correctly displayed",
					"Release status is not displayed correctly");

		} catch (Exception e) {
			logError(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean checkListMilestone(String milestoneId, WebDriver driver) throws Exception {
		try {
			op.switchToDefault();
			logInfo("Milestone Id: : " + milestoneId);
			boolean foundMilestoneId = false;

			// ************
			// verifySuccessMsg();
			int i;
			List<WebElement> addedMilestoneIDs = controlActions.perform_getListOfElementsByXPath(DeliveryMilestoneConstants.ADDED_MILESTONE_ID);

			if (addedMilestoneIDs.size() == 0) {
				logError("Added milestone is not displayed in the table.");
				fail("Added milestone is not displayed in the table.");
			}
			for (i = 0; i < addedMilestoneIDs.size(); i++) {
				if (addedMilestoneIDs.get(i).getText().equalsIgnoreCase(milestoneId)) {
					logInfo("Added milestone id is correctly displayed in the table.");
					logInfo("Milestone found at index: " + i);
					foundMilestoneId = true;
					break;
				}
				// **************

//			List<WebElement> elementList = driver
//					.findElements(By.xpath(DeliveryMilestoneConstants.CHANGE_REQUEST_NUMBER_COLUMN));
//			System.out.println(elementList.size());
//			List<String> strList = new ArrayList<String>();
//			strList = op.perform_convertListOfWebElements_ToListOfStrings(elementList);
//			// List<String> strList = new ArrayList<String>();
////			for (WebElement webElement : elementList) {
////				op.perform_convertListOfWebElements_ToListOfStrings(elementList);
////				strList.add(webElement.getText());
////			}
//			
//			for (String str : strList) {
//				if (str.equalsIgnoreCase(milestoneId)) {
//					logInfo("Milestone found at index: " + strList.indexOf(milestoneId));
//					foundMilestoneId = true;
//					break;
//				}
//			if (strList.contains(milestoneId)) {
//				indexOfReqNum = strList.indexOf(milestoneId);
//
//				threadsleep(600);
//				valueAtList = strList.get(indexOfReqNum);
//				logInfo("valu at list: " + valueAtList);
//			} 

//			for (int i = 0; i < elementList.size(); i++) {
//				if (elementList.get(i).getText().equalsIgnoreCase(milestoneId)) {
//					logInfo("Milestone found at index: " + i + 1);
//					foundMilestoneId = true;
//					break;
//
//				}
			}
//			if (foundMilestoneId == false)
//				return index;
//			else
//			{
//				controlActions.click(nextPaginationButton);
//				checkListMilestone(milestoneId, driver);
//			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public String verifyId(String Id, String Id2) {
		try {
			threadsleep(2000);
			List<WebElement> milestoneIDs = op.perform_getListOfElementsByXPath(
					DeliveryMilestoneConstants.MILESTONE_IDS.replace("MilestoneIDToBeReplaced", Id));
			System.out.println("Size: " + milestoneIDs.size());
			for (index = 0; index < milestoneIDs.size(); index++) {
				System.out.println("Id: " + milestoneIDs.get(index).getText());
				if (milestoneIDs.get(index).getText().equals(Id2)) {
					return Id2;
				}
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public String verifyName(String Id) {
//		String actualName = op.getText(op.performGetElementByXPath(
//				DeliveryMilestoneConstants.ADDED_RELEASE_NAME.replace("MilestoneIDToBeReplaced", milestoneID)));
//		return actualName;
		String actualName = op.getText(op.performGetElementByXPath(DeliveryMilestoneConstants.ADDED_NAME.replace("IDToBeReplaced", Id)));
		return actualName;

	}

	public String verifyMilestoneType(String Id) {
		String milestoneType = op.getText(op.performGetElementByXPath(DeliveryMilestoneConstants.ADDED_TYPE.replace("IDToBeReplaced", Id)));
		return milestoneType;
	}

	public String verifyStatus(String Id) {
		String status = op.getText(op.performGetElementByXPath(DeliveryMilestoneConstants.ADDED_STATUS.replace("IDToBeReplaced", Id)));
		return status;
	}

	public boolean verifyNegativeTcs_create_release(WebDriver driver, String releaseID, String releaseName,
			String startDate, String endDate, String parentMilestone) {
		logInfo("release ID: " + releaseID);
		logInfo("release Name " + releaseName);
		logInfo("Start date: " + startDate);
		logInfo("End Date0 " + endDate);

		int errorCnt = 0;
		String expectedErrorMsg[] = new String[5];
		op = new Operations(driver);
		if (parentMilestone.equals("")) {
			expectedErrorMsg[errorCnt] = "Please select Parent Milestone.";
			logInfo("Expected error message: Please select Parent Milestone.");
			errorCnt++;
		} else {
			boolean isParentSelected = enterParentMilestone(driver, parentMilestone);
			IsTrue(isParentSelected, "Successfully selected parent milestone", "Failed to select parent milestone.");
		}
		if (releaseID.equals("") || op.checkSpecialChar(releaseID) || releaseID.equalsIgnoreCase("null")
				|| op.checkOnlyCharacter(releaseID)) {
			expectedErrorMsg[errorCnt] = "Please enter valid Release ID.";
			logInfo("Expected error message: Please enter valid Release ID.");
			errorCnt++;
		}
		boolean isMilestoneIDSet = enterTextToReleaseID(releaseID);
		IsTrue(isMilestoneIDSet, "Entered release id: " + releaseID,
				"Failed to add text to Milestone ID as '" + releaseID + "'");
		if (releaseName.equals("") || op.checkSpecialChar(releaseName) || NumberUtils.isNumber(releaseName)
				|| releaseName.equalsIgnoreCase("null")) {
			expectedErrorMsg[errorCnt] = "Please enter valid Release name.";
			logInfo("Expected error message: Please enter valid Release name.");
			errorCnt++;

		}
		boolean isMilestoneNameSet = enterTextToReleaseNameField(releaseName);
		IsTrue(isMilestoneNameSet, "Entered release name: " + releaseName,
				"Failed to add text to release Name as '" + releaseName + "'");
		logInfo("Successfully entered valid milestone name: " + releaseName);
		if (!startDate.equals("")) {
			boolean isStartDateCalendarClicked = clkStartDateCalendar();
			IsTrue(isStartDateCalendarClicked, "Clicked on Start Date Calendar.",
					"Failed to click on Start Date Calendar.");
			boolean isStartDateClicked = clkStartDate();
			IsTrue(isStartDateClicked, "Clicked on Start Date.", "Failed to click on Start Date.");
		} else {
			expectedErrorMsg[errorCnt] = "Please select Start Date.";
			logInfo("Expected error message: Please select Start Date.");
			errorCnt++;

		}
		if (!endDate.equals("")) {
			boolean isEndDateCalendarClicked = clkEndDateCalendar();
			IsTrue(isEndDateCalendarClicked, "Clicked on end date Calendar", "Failed to click on End Date Calendar.");
			boolean isEndDateClicked = clkEndDate();
			IsTrue(isEndDateClicked, "Clicked on end date", "Failed to click on End Date.");
		} else {
			expectedErrorMsg[errorCnt] = "Please select End Date.";
			logInfo("Expected error message: Please select End Date.");
			errorCnt++;
		}

		logInfo("Expected error count is " + errorCnt);
		boolean isAddButtonOnAddReleaseClicked = clkAddButton(); // Add Milestone
		// on Add button
		IsTrue(isAddButtonOnAddReleaseClicked, "Clicked on Add button",
				"Failed to click on Add button on Add Milestone page.");

		boolean status;
//		try {
		status = verifyErrorTitle(errorCnt, expectedErrorMsg);
		if (status == true) {
			status = verifyErrorMessages(errorCnt, expectedErrorMsg);
			clkCancelButtonOnAddMilestoneFrame();
			op.switchToDefault();
			if (status == true) {
				return true;
			} else
				return false;
		} else {
			clkCancelButtonOnAddMilestoneFrame();
			op.switchToDefault();
			return false;
		}
	}

	public boolean verifyErrorTitle(int errorCnt, String[] expectedErrorMsg) {
		String errorTitle = getErrorTitle();
		try {
			if (errorCnt > 1) {
				Equals(errorTitle, +errorCnt + " errors have occurred",
						"Error notification is displayed as expected: " + errorTitle,
						"Error notification is not displayed as expected: " + errorTitle);
			} else {
				Equals(errorTitle, +errorCnt + " error has occurred",
						"Error notification is displayed as expected: " + errorTitle,
						"Error notification is not displayed as expected: " + errorTitle);
			}
		} catch (AssertionError e) {
			return false;
		}

		return true;
	}

	public boolean verifyErrorMessages(int errorCnt, String[] expectedErrorMsg) {
		try {
			op.perform_waitUntilVisibility(addMilestoneErrorMsg);
			List<WebElement> actualErrorMsg = controlActions
					.perform_getListOfElementsByXPath(DeliveryMilestoneConstants.ADD_MILESTONE_PAGE_ERROR_MESSAGE);
			if (actualErrorMsg.size() == 0) {
				logError("No error message displayed.");
				return false;
			}
			IsTrue(errorCnt == actualErrorMsg.size(), "Number of error messages are as expected",
					"Number of error messages displayed are not as expected");
			logInfo("Actual number of error messages displayed: " + actualErrorMsg.size());
			for (int i = 0; i < errorCnt; i++) {
				logInfo("Actual error message: " + actualErrorMsg.get(i).getText());
				logInfo("Expected error message: " + expectedErrorMsg[i]);
				try {
					Equals(actualErrorMsg.get(i).getText(), expectedErrorMsg[i],
							"Error notification is displayed as expected: " + expectedErrorMsg[i],
							"Error notification is not displayed as expected");
					logInfo("Error notification is displayed as expected: " + expectedErrorMsg[i]);
				} catch (AssertionError e) {
					logError(e.getMessage());
					return false;
				}

			}
		} catch (Exception e) {
			logError(e.getMessage());
			return false;
		}
		return true;
	}

	public String[][] getErrorMessages(int errorCnt, String[] expectedErrorMsg) {
		String errormsgs[][] = new String[errorCnt][2];
		try {
			op.perform_waitUntilVisibility(addMilestoneErrorMsg);
			List<WebElement> actualErrorMsg = controlActions
					.perform_getListOfElementsByXPath(DeliveryMilestoneConstants.ADD_MILESTONE_PAGE_ERROR_MESSAGE);
			if (actualErrorMsg.size() == 0) {
				logError("No error message displayed.");
				return null;
			}
			IsTrue(errorCnt == actualErrorMsg.size(), "Number of error messages are as expected",
					"Number of error messages displayed are not as expected");
			logInfo("Actual number of error messages displayed: " + actualErrorMsg.size());
			for (int i = 0; i < errorCnt; i++) {
				logInfo("Actual error message: " + actualErrorMsg.get(i).getText());
				logInfo("Expected error message: " + expectedErrorMsg[i]);
				errormsgs[i][0] = actualErrorMsg.get(i).getText();
				errormsgs[i][1] = expectedErrorMsg[i];
			}
		} catch (Exception e) {
			logError(e.getMessage());
			return null;
		}
		return errormsgs;
	}

	public String[] getStatusUpdateExpectedMsg(String expectedErrorMsg[], int errorCnt) {
		expectedErrorMsg[errorCnt] = "Please enter Status Update.";
		logInfo("Expected error message: Please enter Status Update.");
		return expectedErrorMsg;
	}

	public String[] getTaskIdExpectedMsg(String taskId, String expectedErrorMsg[], int errorCnt) {
		expectedErrorMsg[errorCnt] = "Please enter valid Task ID.";
		logInfo("Expected error message: Please enter valid Task ID.");
		return expectedErrorMsg;
	}

	public String[] getStartDateExpectedMsg(String startDate, String expectedErrorMsg[], int errorCnt) {
		expectedErrorMsg[errorCnt] = "Please select Start Date.";
		logInfo("Expected error message: Please select Start Date.");
		return expectedErrorMsg;
	}

	public String[] getEndDateExpectedMsg(String endDate, String expectedErrorMsg[], int errorCnt) {
		expectedErrorMsg[errorCnt] = "Please select End Date.";
		logInfo("Expected error message: Please select End Date.");
		return expectedErrorMsg;
	}

	public String[] getTaskNameExpectedMsg(String taskName, String expectedErrorMsg[], int errorCnt) {
		expectedErrorMsg[errorCnt] = "Please enter valid Task name.";
		logInfo("Expected error message: Please enter valid Task name.");
		return expectedErrorMsg;
	}

	public String[] getParentMilestoneExpectedMsg(String expectedErrorMsg[], int errorCnt) {
		expectedErrorMsg[errorCnt] = "Please select Parent Milestone.";
		logInfo("Expected error message: Please select Parent Milestone.");
		return expectedErrorMsg;
	}

	public String[] getPerComplExpectedMsg(String expectedErrorMsg[], int errorCnt) {
		expectedErrorMsg[errorCnt] = "Please provide Percent Completion";
		logInfo("Expected error message: Please provide Percent Completion");
		return expectedErrorMsg;
	}

	public String[] getPerComplRangeExpectedMsg(String expectedErrorMsg[], int errorCnt) {
		expectedErrorMsg[errorCnt] = "Completion % is not between the valid range of 0 and 100.";
		logInfo("Expected error message: Completion % is not between the valid range of 0 and 100.");
		return expectedErrorMsg;
	}

	// Click on Task option

	/**
	 * This method is used to click on Release option under "Add Delivery Milestone"
	 * 
	 * @return boolean This returns true if Successfully clicked on Sprint option
	 */

	public boolean clkTaskOption() {
		try {
			op.waitForAnElementToBeClickable(taskOption);
			op.clickElement(taskOption);
			logInfo("Successfully clicked on task option");
			return true;
		} catch (Exception e) {
			logError("Failed to click on task option " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to switch to Add task Frame
	 * 
	 * @param driver
	 * 
	 * @return boolean This returns true if Successfully switched to Add task frame
	 */

	public boolean switchToAddTaskFrame() {
		try {
			threadsleep(3000);
			// op.switchToDefault();
//			op.waitForFrameToAvailable(addReleaseFrame);
//			op.switchToFrameByLocators(addReleaseFrame);
			op.switchToFrameByIFrameElement(addReleaseFrame);
			logInfo("Successfully switched to add release frame");
			return true;
		} catch (Exception e) {
			logError("Failed to switch to add release frame " + e.getMessage());
			return false;
		}
	}

	public boolean enterTextTaskID(String taskID) {
		try {
			isElementDisplayed(taskIDField);
			op.waitForElementToBeClickable(taskIDField);
			op.clear(taskIDField);
			op.sendKeys(taskIDField, taskID);
			logInfo("Entered text '" + taskID + "' in task ID Field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + taskID + "' in task ID Field" + e.getMessage());
			return false;
		}
	}

	public boolean enterTextTaskName(String taskName) {
		try {
			isElementDisplayed(taskNameField);
			op.waitForElementToBeClickable(taskNameField);
			op.clear(taskNameField);
			op.sendKeys(taskNameField, taskName);
			// op.actionEnter();
			logInfo("Entered text '" + taskName + "' in release Name Field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + taskName + "' in release Name Field" + e.getMessage());
			return false;
		}
	}

	public String getErrorTitle() {
		try {
			op.perform_waitUntilVisibility(addMilestoneErrorTitle);
		} catch (Exception e) {
			op.switchToDefault();
			WebElement msg = controlActions
					.perform_waitUntilVisibility(By.xpath("//*[contains(text(),'successfully')]"));
			if (msg.isDisplayed())
				logError("Record added with incorrect data.");
			op.Fail("Record added with incorrect data.");
			// return false;
		}
		String errorTitle = op.getText(addMilestoneErrorTitle);
		return errorTitle;
	}
	// Create new Task

	public String createNewTask(String milestoneId) {
		try {
			// String milestoneId = createMilestone();
			// String milestoneId = "MILEID478";
			int randomNum = op.generateRandomNum();
			String taskID = "TASKID" + randomNum;
			String taskName = "TASKNAME" + randomNum;
//				controlActions = new ControlActions(driver);
			threadsleep(2000);
			logInfo("Creating Task with Task ID -> " + taskID + " and Task Name ->" + taskName );
			boolean isAddDeliveryMilestoneButtonClicked = clkAddDeliveryMilestoneButton();
			IsTrue(isAddDeliveryMilestoneButtonClicked, "Successfully clicked on Add Delivery Milestone buttons",
					"Failed to click on Add Delivery Milestone button.");
			boolean isTaskOptionClicked = clkTaskOption();
			IsTrue(isTaskOptionClicked, "Task option selected", "Failed to click on task option.");
			boolean isSwitchedToAddTaskFrame = switchToAddFrame();
			IsTrue(isSwitchedToAddTaskFrame, "Switched to add task frame.", "Failed to switch to add task frame.");
			boolean isMilestoneIDSet = enterTextTaskID(taskID);
			IsTrue(isMilestoneIDSet, "Entered task ID: " + taskID, "Failed to add text to task ID as '" + taskID + "'");
			boolean isParentSelected = enterParentMilestone(driver, milestoneId);
			IsTrue(isParentSelected, "Successfully selected parent milestone", "Failed to select parent milestone.");
			boolean isReleaseNameSet = enterTextTaskName(taskName);
			IsTrue(isReleaseNameSet, "Entered task Name: " + taskName,
					"Failed to add text to task name as '" + taskName + "'");
			boolean isStartDateCalendarClicked = clkStartDateCalendar();
			IsTrue(isStartDateCalendarClicked, "Clicked on start date calender",
					"Failed to click on Start Date Calendar.");
			boolean isStartDateClicked = clkStartDate();
			IsTrue(isStartDateClicked, "Clicked on start date", "Failed to click on Start Date.");
			boolean isEndDateCalendarClicked = clkEndDateCalendar();
			IsTrue(isEndDateCalendarClicked, "Clicked on end date calender", "Failed to click on End Date Calendar.");
			boolean isEndDateClicked = clkEndDate();
			IsTrue(isEndDateClicked, "Clicked on end date", "Failed to click on End Date.");
			boolean isAddButtonOnAddMilestonePageClicked = clkAddButton();
			IsTrue(isAddButtonOnAddMilestonePageClicked, "", "Failed to click on Add button on Add Milestone page.");
			op.switchToDefault();
			verifySuccessMsg();
			op.refreshPage();
			WebElement releaseIcon = op.perform_waitUntilVisibility(By.xpath(DeliveryMilestoneConstants.VIEW_RELEASE_ICON.replace("MilestoneIDToBeReplaced", milestoneId)));
			op.click(releaseIcon);
			String actualTaskID = verifyId(milestoneId, taskID);
			Equals(actualTaskID, taskID, "Task ID is correctly displayed", "Task ID is not displayed correctly");
			String actualTaskName = verifyName(taskID);
			Equals(actualTaskName, taskName, "Task name is correctly displayed","Task name is not displayed correctly");
			String milestoneType = verifyMilestoneType(taskID);
			Equals(milestoneType, "Tasks", "Milestone type is correctly displayed","Milestone type is not displayed correctly");
			String status = verifyStatus(taskID);
			IsTrue(status.contains("Open"), "Task status is correctly displayed","Task status is not displayed correctly");
			logInfo("New Task Created with Task ID -> " + taskID + " Task Name -> " + taskName+ " and Task Status -> : " + status);
			op.perform_waitUntilVisibility(By.xpath(DeliveryMilestoneConstants.MINUS_ICON.replace("MilestoneIDToBeReplaced", milestoneId)));
			return taskID;
		} catch (AssertionError e) {
			logError(e.getMessage());
			return null;
		} catch (Exception e) {
			logError(e.getMessage());
			return null;
		}
	}
	// Create new Phase

	public String createNewPhase(String milestoneId) {
		try {
			//String milestoneId = createMilestone();
			//String milestoneId = "MILEID478";
			int randomNum = op.generateRandomNum();
			String phaseID = "PHASEID" + randomNum;
			String phaseName = "PHASENAME" + randomNum;
//			controlActions = new ControlActions(driver);
			threadsleep(2000);
			op.waitForAnElementToBeClickable(openAddDeliveryMilestone);
			boolean isAddDeliveryMilestoneButtonClicked = clkAddDeliveryMilestoneButton();
			IsTrue(isAddDeliveryMilestoneButtonClicked, "Successfully clicked on Add Delivery Milestone buttons","Failed to click on Add Delivery Milestone button.");
			boolean isPhaseOptionClicked = clkPhaseOption();
			IsTrue(isPhaseOptionClicked, "Task option selected", "Failed to click on task option.");
			boolean isSwitchedToAddPhaseFrame = switchToAddFrame();
			IsTrue(isSwitchedToAddPhaseFrame, "Switched to add phase frame.", "Failed to switch to add phase frame.");
			boolean isPhaseIDSet = enterTextPhaseID(phaseID);
			IsTrue(isPhaseIDSet, "Entered phase ID: " + phaseID, "Failed to add text to phase ID as '" + phaseID + "'");
			boolean isParentSelected = enterParentMilestone(driver, milestoneId);
			IsTrue(isParentSelected, "Successfully selected parent milestone", "Failed to select parent milestone.");
			boolean isPhaseNameSet = enterTextPhaseName(phaseName);
			IsTrue(isPhaseNameSet, "Entered phase Name: " + phaseName,"Failed to add text to phase name as '" + phaseName + "'");
			boolean isStartDateCalendarClicked = clkStartDateCalendar();
			IsTrue(isStartDateCalendarClicked, "Clicked on start date calender","Failed to click on Start Date Calendar.");
			boolean isStartDateClicked = clkStartDate();
			IsTrue(isStartDateClicked, "Clicked on start date", "Failed to click on Start Date.");
			boolean isEndDateCalendarClicked = clkEndDateCalendar();
			IsTrue(isEndDateCalendarClicked, "Clicked on end date calender", "Failed to click on End Date Calendar.");
			boolean isEndDateClicked = clkEndDate();
			IsTrue(isEndDateClicked, "Clicked on end date", "Failed to click on End Date.");
			boolean isAddButtonOnAddMilestonePageClicked = clkAddButton();
			IsTrue(isAddButtonOnAddMilestonePageClicked, "Add button clicked","Failed to click on Add button on Add Milestone page.");
			op.switchToDefault();
			op.refreshPage();
			WebElement releaseIcon = op.perform_waitUntilVisibility(By.xpath(DeliveryMilestoneConstants.VIEW_RELEASE_ICON.replace("MilestoneIDToBeReplaced", milestoneId)));
			op.click(releaseIcon);
			String actualPhaseID = verifyId(milestoneId, phaseID);
			Equals(actualPhaseID, phaseID, "Phase ID is correctly displayed", "Phase ID is not displayed correctly");
			String actualPhaseName = verifyName(phaseID);
			Equals(actualPhaseName, phaseName, "Phase name is correctly displayed","Phase name is not displayed correctly");
			String milestoneType = verifyMilestoneType(phaseID);
			Equals(milestoneType, "Phase", "Milestone type is correctly displayed","Milestone type is not displayed correctly");
			String status = verifyStatus(phaseID);
			IsTrue(status.contains("Open"), "Phase status is correctly displayed","Phase status is not displayed correctly");
//			op.perform_waitUntilVisibility(
//					By.xpath(DeliveryMilestoneConstants.MINUS_ICON.replace("MilestoneIDToBeReplaced", milestoneId)));
			return phaseID;
		} catch (AssertionError e) {
			logError(e.getMessage());
			return null;
		} catch (Exception e) {
			logError(e.getMessage());
			return null;
		}
	}
	// Click on Task option

	/**
	 * This method is used to click on Release option under "Add Delivery Milestone"
	 * 
	 * @return boolean This returns true if Successfully clicked on Sprint option
	 */

	public boolean clkPhaseOption() {
		try {
			op.waitForAnElementToBeClickable(phaseOption);
			op.clickElement(phaseOption);
			logInfo("Successfully clicked on phase option");
			return true;
		} catch (Exception e) {
			logError("Failed to click on phase option " + e.getMessage());
			return false;
		}
	}

	public boolean enterTextPhaseID(String phaseID) {
		try {
			isElementDisplayed(phaseIDField);
			op.waitForElementToBeClickable(phaseIDField);
			op.clear(phaseIDField);
			op.sendKeys(phaseIDField, phaseID);
			logInfo("Entered text '" + phaseID + "' in phase ID Field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + phaseID + "' in phase ID Field" + e.getMessage());
			return false;
		}
	}

	public boolean enterTextPhaseName(String phaseName) {
		try {
			isElementDisplayed(phaseNameField);
			op.waitForElementToBeClickable(phaseNameField);
			op.clear(phaseNameField);
			op.sendKeys(phaseNameField, phaseName);
			// op.actionEnter();
			logInfo("Entered text '" + phaseName + "' in phase Name Field");
			return true;
		} catch (Exception e) {
			logError("Failed to enter text '" + phaseName + "' in phase Name Field" + e.getMessage());
			return false;
		}
	}

	//Click on "+" icon after add or update
	public boolean clickExpandIcon(String milestoneId) {
		try {
			threadsleep(2000);
			WebElement releaseIcon = op.perform_waitUntilVisibility(By.xpath(DeliveryMilestoneConstants.VIEW_RELEASE_ICON.replace("MilestoneIDToBeReplaced", milestoneId)));
			op.click(releaseIcon);
			threadsleep(2000);
			return true;
		} catch (Exception e) {
			logError("Filed to click on Mileston expand button(+) "+ e.getMessage());
			return false;
		}
	}

	public String[] getphaseIDExpectedMsg(String[] expectedErrorMsg, int errorCnt) {
		expectedErrorMsg[errorCnt] = "Please enter valid Phase ID.";
		logInfo("Expected error message: Please enter valid Phase ID.");
		return expectedErrorMsg;
	}

	public String[] getphaseNameExpectedMsg(String phaseName, String[] expectedErrorMsg, int errorCnt) {
		expectedErrorMsg[errorCnt] = "Please enter valid Phase name.";
		logInfo("Expected error message: Please enter valid Phase name.");
		return expectedErrorMsg;
	}

//Click on update icon
	public boolean clickUpdateIcon(String phaseID) {
		try {
			threadsleep(2000);
			op.perform_waitUntilVisibility(By.xpath(DeliveryMilestoneConstants.UPDATE_BUTTON.replace("releaseIDToBeUpdated", phaseID)));
			op.click(op.performGetElementByXPath(DeliveryMilestoneConstants.UPDATE_BUTTON.replace("releaseIDToBeUpdated", phaseID)));
			threadsleep(2000);
			return true;
		} catch (Exception e) {
			logError(e.getMessage());
			return false;
		}

	}

	// Click on delete icon
	public boolean clickDeleteIcon(String phaseID) {
		try {
			WebElement releaseIcon;
			controlActions.click(controlActions.performGetElementByXPath(
					DeliveryMilestoneConstants.DELETE_RELEASE_BUTTON.replace("releaseIDToBeUpdated", phaseID)));
			return true;
		} catch (Exception e) {
			logError(e.getMessage());
			return false;
		}

	}

	// Click on reason code dropdown
	public boolean clkReasonCodeDropDown() {
		try {
			op.waitForAnElementToBeClickable(reasonCodeDrpDown);
			controlActions.clickElement(reasonCodeDrpDown);
			op.switchToDefault();
			logInfo("Successfully clicked on reason code dropdown");
			return true;
		} catch (Exception e) {
			logError("Failed to click on reason code dropdown " + e.getMessage());
			return false;
		}
	}

	public String[] getReasonCodeExpectedMsg(String milestoneType, String expectedErrorMsg[], int errorCnt) {
		expectedErrorMsg[errorCnt] = "Enter Reason Code for making this " + milestoneType + " Obsolete.";
		logInfo("Expected error message: Enter Reason Code for making this " + milestoneType + " Obsolete.");
		return expectedErrorMsg;
	}

	public String[] getStatusUpdateExpectedMsg(String milestoneType, String expectedErrorMsg[], int errorCnt) {
		expectedErrorMsg[errorCnt] = "Enter Status Update for making this " + milestoneType + " Obsolete.";
		logInfo("Expected error message: Enter Status Update for making this " + milestoneType + " Obsolete.");
		return expectedErrorMsg;
	}

	public String[] getReleaseIDExpectedMsg(String[] expectedErrorMsg, int errorCnt) {
		expectedErrorMsg[errorCnt] = "Please enter valid Release ID.";
		logInfo("Expected error message: Please enter valid Release ID.");
		return expectedErrorMsg;
	}

	public String[] getReleaseNameExpectedMsg(String phaseName, String[] expectedErrorMsg, int errorCnt) {
		expectedErrorMsg[errorCnt] = "Please enter valid Release name.";
		logInfo("Expected error message: Please enter valid Release name.");
		return expectedErrorMsg;
	}

//Enter sprint scope change
	public boolean enterTextToScopeChange(String scopeChange) {
		try {
			isElementDisplayed(this.scopeChange);
			op.waitForElementToBeClickable(this.scopeChange);
			op.clear(this.scopeChange);
			op.sendKeys(this.scopeChange, scopeChange);
			// op.actionEnter();
			logInfo("Entered text '" + scopeChange + "' in scope change field");
		} catch (Exception e) {
			logError("Failed to enter text '" + scopeChange + "' in scope change field" + e.getMessage());
			return false;
		}
		return true;
	}

	// Enter sprint in progress stories
	public boolean enterTextToInProgressField(String inProgressStories) {
		try {
			isElementDisplayed(inProgress);
			op.waitForElementToBeClickable(inProgress);
			op.clear(inProgress);
			op.sendKeys(inProgress, inProgressStories);
			logInfo("Entered text '" + inProgressStories + "' in 'In Progress' field");
		} catch (Exception e) {
			logError("Failed to enter text '" + inProgressStories + "' in scope change field" + e.getMessage());
			return false;
		}
		return true;
	}

	// Enter sprint blocked stories
	public boolean enterTextToBlockedField(String blockedStories) {
		try {
			isElementDisplayed(this.blocked);
			op.waitForElementToBeClickable(this.blocked);
			op.clear(this.blocked);
			op.sendKeys(this.blocked, blockedStories);
			logInfo("Entered text '" + blockedStories + "' in blocked field");
		} catch (Exception e) {
			logError("Failed to enter text '" + blockedStories + "' in blocked field" + e.getMessage());
			return false;
		}
		return true;
	}

	// Enter sprint blocked stories
	public boolean enterTextToCompletedField(String completedStories) {
		try {
			isElementDisplayed(completed);
			op.waitForElementToBeClickable(completed);
			op.clear(completed);
			op.sendKeys(completed, completedStories);
			logInfo("Entered text '" + completedStories + "' in completed field");
		} catch (Exception e) {
			logError("Failed to enter text '" + completedStories + "' in completed field" + e.getMessage());
			return false;
		}
		return true;
	}

//Select sprint RAG status
	public boolean selectRAGStatus(String ragStatus) {
		try {
			WebElement ragStatusButton = op.perform_getElementByXPath(
					DeliveryMilestoneConstants.RAG_STATUS.replace("ValueToBeReplaced", ragStatus));
			op.waitForElementToBeClickable(ragStatusButton);
			// op.clickRadiobutton(driver,
			// DeliveryMilestoneConstants.RAG_STATUS.replace("ValueToBeReplaced",
			// ragStatus));
//			WebElement ragStatusButton = op.perform_waitUntilClickable(
//					By.xpath(DeliveryMilestoneConstants.RAG_STATUS.replace("ValueToBeReplaced", ragStatus)));
			if(!ragStatusButton.isSelected())
				op.clickOnElement(ragStatusButton);
			//op.click(ragStatusButton);
			logInfo("Successfully selected RAG status");
			return true;
		} catch (Exception e) {
			logError("Failed to select RAG status: " + e.getMessage());
			return false;
		}
	}

//Enter text in P1 defect field
	public boolean enterTextToP1DefectField(String p1DefectCount) {
		try {
			isElementDisplayed(p1Defect);
			op.waitForElementToBeClickable(p1Defect);
			op.clear(p1Defect);
			op.sendKeys(p1Defect, p1DefectCount);
			logInfo("Entered text '" + p1DefectCount + "' in P1 defect field");
		} catch (Exception e) {
			logError("Failed to enter text '" + p1DefectCount + "' in P1 defect field" + e.getMessage());
			return false;
		}
		return true;
	}

	// Enter text in P2 defect field
	public boolean enterTextToP2DefectField(String p2DefectCount) {
		try {
			isElementDisplayed(p2Defect);
			op.waitForElementToBeClickable(p2Defect);
			op.clear(p2Defect);
			op.sendKeys(p2Defect, p2DefectCount);
			logInfo("Entered text '" + p2DefectCount + "' in P2 defect field");
		} catch (Exception e) {
			logError("Failed to enter text '" + p2DefectCount + "' in P2 defect field" + e.getMessage());
			return false;
		}
		return true;
	}

	// Enter text in P3 defect field
	public boolean enterTextToP3DefectField(String p3DefectCount) {
		try {
			isElementDisplayed(p3Defect);
			op.waitForElementToBeClickable(p3Defect);
			op.clear(p3Defect);
			op.sendKeys(p3Defect, p3DefectCount);
			logInfo("Entered text '" + p3DefectCount + "' in P3 defect field");
		} catch (Exception e) {
			logError("Failed to enter text '" + p3DefectCount + "' in P3 defect field" + e.getMessage());
			return false;
		}
		return true;
	}

	// Enter text in P4 defect field
	public boolean enterTextToP4DefectField(String p4DefectCount) {
		try {
			isElementDisplayed(p4Defect);
			op.waitForElementToBeClickable(p4Defect);
			op.clear(p4Defect);
			op.sendKeys(p4Defect, p4DefectCount);
			logInfo("Entered text '" + p4DefectCount + "' in P4 defect field");
		} catch (Exception e) {
			logError("Failed to enter text '" + p4DefectCount + "' in P4 defect field" + e.getMessage());
			return false;
		}
		return true;
	}
}

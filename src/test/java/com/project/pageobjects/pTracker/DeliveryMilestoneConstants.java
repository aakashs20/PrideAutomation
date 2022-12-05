package com.project.pageobjects.pTracker;

public class DeliveryMilestoneConstants{
	public static final String ACTIVE_PROJECTS_LIST="//*[@id='active_tab']/a/span[contains(text(),'Active Projects')]";// verify that active project
	public static final String SELECT_ROLE_RELEASE_DROPDOWN_BUTTON = "//*[@id='P8_SEL_ROLE_lov_btn']"; // click on drop down button
	public static final String PROCEED_BUTTON="//*[@id='B77297519692893844'][contains(text(),'Proceed')]"; //click event
	public static final String FIXED_PRICE="//*[@id='R505691641167493060_cards']/li[1]"; // click event
	public static final String SELECT_ROLE_RELEASE_NAME= "//*[@id='PopupLov_8_P8_SEL_ROLE_dlg']//li[contains(text(),'Release 10 - Project Management')]"; //click event. select option
	public static final String PROJECT_TYPE="//*[@id='ap_top_region']//span[contains(text(),'Project Type')]"; //Verify projectType is Fixed price
	public static final String PROJECT_TYPE_FIXED_PRICE="//*[@id='ap_top_region']//button[contains(text(),'Fixed Price')]";
	public static final String VERIFY_STATUS_OF_PROJECT="//*[@id='ap_top_region']//span[contains(text(),'Status')]";	
	public static final String VERIFY_STATUS_OF_PROJECT_ACTIVE = "//*[@id='ap_top_region']//button[contains(text(),'Active')]";
	public static final String PROJECT_NUMBER="//*[@id='report_table_projects-active-report']//a[contains(text(),'A2CO0010')]"; //VERIFY PROJECT NUMBER
	public static final String PROJECT_NUMBER_TITLE="//*[@id='R382922062436694454']//span[contains(text(),'A2CO0010')]"; // verify you landed on correct project page
	public static final String RIGHT_CHEVRON="//*[@id='rgnSticky']//span[contains(@class,'a-Icon icon-right-chevron')]";
	public static final String DELIVERY_MILESTONE_TAB="//*[@id='mlstn_tab']";
	public static final String SEARCH_PROJECT="//*[@id='P30_AP_FC_SEACH']";
	public static final String GO_BUTTON="//*[@id='B604616602516884943'][contains(text(),'GO')]";
	public static final String CHANGE_REQUEST="//*[@id='actions_task_menu_5i']";
	public static final String ACTIVE_PROJECTS_TAB_FOCUSED = "//*[@id='active_tab']/a[@aria-selected='true']";
	public static final String MANAGER_DETAILS_TAB="//*[@id=mng_dtl_tab]";
	public static final String ATTACHMENTS_TAB="//*[@id='R394493418279145435_tab']/a[@aria-selected='false']";
	public static final String CHANGE_REQUEST_NUMBER="//*[@id='P89_TASK_NUMBER']";
	public static final String IFRAME_CHANGE_REQUEST="//*[@id='apex_dialog_1']/iframe";
	public static final String CHANGE_REQUEST_TYPE="//*[@id='P89_CR_CHANGE_TYPE_lov_btn']";
	public static final String IMPACTED_AREA="//*[@id='P89_CR_IMPACTED_AREA_lov_btn']";
	public static final String REQUEST_DATE_PICKER="//*[@id='P89_REQUEST_DATE_CONTAINER']/div[2]/div/button";//DROP DOWN
	public static final String MONTH_REQUEST_DATE="//*[@id='ui-datepicker-div']//select[@class='ui-datepicker-month']";//DROP DOWN
	public static final String YEAR_REQUEST_DATE="//*[@id='ui-datepicker-div']//select[@class='ui-datepicker-year']";//DROP DOWN
	public static final String DATE_REQUEST_DATE="//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[2]/a";
	public static final String PLANNED_START_DATE_PICKER="//*[@id=\"P89_START_DATE_CONTAINER\"]/div[2]/div/button";//DROP DOWN
	public static final String MONTH_PLANNED_START_DATE="//*[@id=\"ui-datepicker-div\"]//select[@class='ui-datepicker-month']";//DROP DOWN
	public static final String YEAR_PLANNED_START_DATE="//*[@id='ui-datepicker-div']//select[@class='ui-datepicker-year']"; //DROP DOWN
	public static final String DATE_PLANNED_START_DATE="//*[@id=\'ui-datepicker-div\']/table/tbody/tr[1]/td[5]/a";
	public static final String PLANNED_END_DATE_PICKER="//*[@id='P89_END_DATE_CONTAINER']/div[2]/div/button";
	public static final String MONTH_PLANNED_END_DATE="//*[@id='ui-datepicker-div']/div/div/select[1]";
	public static final String YEAR_PLANNED_END_DATE="//*[@id='ui-datepicker-div']/div/div/select[2]";
	public static final String DATE_PLANNED_END_DATE="//*[@id='ui-datepicker-div']/table/tbody/tr[5]/td[4]/a";
	public static final String MILESTONE_FROM_LIST= "//*[@id='PopupLov_89_P89_PARENT_TASK_ID_dlg']//td[contains(text(),'Tenant provisioning')]";
	public static final String CHANGE_REQUEST_VALUE="//*[@id='P89_CR_VALUE']";
	public static final String PAYMENT_MILESTONE_NO= "//*[@id='P89_PAYMENT_MILESTONE_1']";
	public static final String HIGH_LEVEL_ESTIMATION_NO="//*[@id='P89_HLE_1']";
	public static final String CLIENT_APPROVAL="//*[@id='P89_CR_CLIENT_STATUS_lov_btn']";
	public static final String CLIENT_APPROVAL_APPROVED= "//*[@id='PopupLov_89_P89_CR_CLIENT_STATUS_dlg']//li[contains(text(),'Approved')]";
	public static final String INTERNAL_APPROVAL="//*[@id='P89_CR_INTERNAL_lov_btn']";
	public static final String INTERNAL_APPROVAL_APPROVED="//*[@id='PopupLov_89_P89_CR_INTERNAL_dlg']//li[contains(text(),'Approved')]";
	public static final String DESCRIPTION="//*[@id='P89_REMARK']";
	public static final String CREATE_BUTTON_CR="//*[@id='B129410828861092580']";
	public static final String CHANGE_TYPE_OPTION1="//*[@id='PopupLov_89_P89_CR_CHANGE_TYPE_dlg']/div[3]/div/div[3]/ul/li[1]";
	public static final String IMPACTED_AREA_REQUIREMENT="//*[@id='PopupLov_89_P89_CR_IMPACTED_AREA_dlg']/div[2]/div/div[3]/ul/li[1]";
	public static final String CHANGE_REQUEST_NUMBER_CREATED="//*[@id='report_table_myRegion']/tbody/tr[10]/td[2]/span";
	public static final String DELIVERY_MILESTONE_WEB_TABLE="//*[@id='report_table_myRegion']";
	public static final String WEBTABLE_TABLE_ROW="//table[contains(@id,'report_table_myRegion')]//tr";
	public static final String WEBTABLE_TABLE_COL="//table[contains(@id,'report_table_myRegion')]//td";
	public static final String NEXT_PAGINATION_BUTTON="//*[@id='report_myRegion']/div/table[2]/tbody/tr/td/table/tbody/tr/td[4]/a";
	public static final String VERIFY_CHANGE_REQUEST_NUMBER="//*[@id='P90_TASK_NUMBER']";
	public static final String EDIT_CHANGE_REQUEST_IFRAME="//*[@id='apex_dialog_3']/iframe";
	public static final String EDIT_DESCRIPTION="//*[@id='P90_REMARK']";
	public static final String DUPLICATE_ERROR_MESSAGE="//p[contains(text(),'Change Request number is already exists.')]";
	public static final String DUPLICATE_ERROR_MESSAGE_BUTTON="//button[contains(text(),'OK')]";
	public static final String ALERT_BOX="//*[@id='t_PageBody']/div[17]";

	//Ganesh
	public static final String PAYMENT_MILESTONE_YES = "//*[@id='P89_PAYMENT_MILESTONE']/div[1]/label";
	public static final String PAYMENT_MILESTONE_NAME = "//*[@id='P89_PAYMENT_ML_NAME']";
	public static final String PAYMENT_MILESTONE = "//*[@id='pyment-mlstn_tab']/a/span";
	public static final String MILESTONE_NAME_VERIFY = "//*[@id='report_table_payrpt']/tbody/tr[1]/td[1]/span";
	public static final String PAYMENT_MILESTONE_TABLE = "//*[@id='report_table_payrpt']/tbody/tr/td/span";
	public static final String PAYMENT_MILESTONE_STATUS = "//*[@id='report_table_payrpt']/tbody/tr/td[5]/span[2]";
  	public static final String ADD_PAYMENT_MILESTONE_BTN = "//*[@id='ADD_PAYMENT_MLSTN']";
  	public static final String NO_PAYMENT_MILESTONE_ERROR = "//span[contains(text(),'No payment milestone has been added')]";

	// public static final String
	// FIXED_PRICE="//*[@id='R505691641167493060_cards']//h3[contains(text(),'Fixed
	// Price')]"; // click event

	// public static final String FILTER_BUTTON="//*[@id='FCAP_BTN']";
	public static final String PROJECT_NUMBER1 = "//*[@id='report_table_projects-active-report']//a[contains(text(),'PROJ_NUM')]";
	// PROJECT
	// NUMBER
	public static final String ADDED_MILESTONE_ID = "//table[@id='report_table_myRegion']//td[2]/span";
	public static final String ADDED_MILESTONE_NAME = "//table[@id='report_table_myRegion']//td[3]/span";
	public static final String ADDED_MILESTONE_STATUS = "//table[@id='report_table_myRegion']//td[8]/span[2]";
	public static final String ADDED_MILESTONE_PERC_COMPLETION = "//table[@id='report_table_myRegion']//td[9]";
	public static final String DELETE_BUTTON = "//button[@title='Obsolete']";
	public static final String ADDED_MILESTONE_EDIT_BUTTON = "//table[@id='report_table_myRegion']//td[10]/button[1]";
	public static final String ADD_MILESTONE = "//*[@id='actions_task_menu_0i']";

	// ----------------------------------------------------------------------------------

	public static final String MILESTONE_OPTION = "//*[@id='actions_task_menu_0i']";

	// public static final String ADD_MILESTONE_FRAME =
	// "//*[@id='apex_dialog_1']/iframe";
	public static final String ADD_MILESTONE_FRAME = "//iframe[@title='Add ' or @title='Add Milestone']";
	public static final String OPEN_ADD_DELIVERY_MILESTONE = "//*[@id='ADD_TASK']//span[@class='t-Button-label' and contains(text(),'Add Delivery Milestone')]";
	public static final String MILESTONE_ID = "//*[contains(@id,'TASK_NUMBER') and contains(@class,'text_field')]";
	public static final String MILESTONE_IDvisible = "//*[@id='P59_TASK_NUMBER_LABEL']";
	public static final String MILESTONE_NAME = "//*[text()='Milestone Name ']//following::input[1]";
	public static final String MILESTONE_START_DATE_CALENDAR = "//*[text()='Start Date ']//following::input[1]";
	public static final String PERCENT_COMPLETION = "//*[text()='% Completion ']//following::input[1]";
	public static final String MILESTONE_END_DATE_CALENDAR = "//*[text()='End Date ']//following::input[1]";
	public static final String MILESTONE_START_DATE = "//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[5]/a";
	public static final String MILESTONE_END_DATE = "//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[6]/a";
	public static final String MILESTONE_START_DATE_CALENDAR_DATE_PICKER_BUTTON = "//*[@id='P59_START_DATE_CONTAINER']/div[2]/div/button";
	public static final String MILESTONE_END_DATE_CALENDAR_DATE_PICKER_BUTTON = "//*[@id='P59_END_DATE_CONTAINER']/div[2]/div/button";
	public static final String ADD_MILESTONE_PAGE_ADD_BUTTON = "//*[@id='B544932878788095505']\r\n";
	public static final String ADD_MILESTONE_PAGE_ERROR_TITLE = "//h2[contains(@class,'aErrMsgTitle')]";
	public static final String ADD_MILESTONE_PAGE_ERROR_MESSAGE = "//a[contains(@class,'Notification-link')]";
	public static final String CLOSE_ERROR_NOTIFICATION_MESSAGE = "//button[@title='Close Notification']";
	public static final String CANCEL_MILESTONE_BUTTON = "//button[text()='Add']//preceding::button[1]";
	public static final String EDIT_MILESTONE_FRAME = "//iframe[@title='Edit Milestone']";
	public static final String STATUS_UPDATE = "//*[text()='Status Update ']//following::textarea";
	public static final String SAVE_CHANGES_BUTTON = "//button[@id='SAVE']";
	public static final String DELETE_MILESTONE_FRAME = "//iframe[contains(@title,'Obsolete')]";
	public static final String OBSOLETE_BUTTON = "//button[contains(text(),'Obsolete')]";
	public static final String REASON_CODE_DRPDOWN_ICON = "//button[contains(@id,'REASON_CODE')]";
	public static final String REASON_CODE_OPTION = "//li[text()='###']";
	// public static final String REASON_CODE_SEARCH_TEXTBOX =
	// "//div[contains(@class,'searchBar')]";
	public static final String REASON_CODE_SEARCH_TEXTBOX = "//div[@id='PopupLov_85_P85_REASON_CODE_dlg']/div[1]/input";
	public static final String CUSTOMER_COORDINATOR_TAB = "//*[@id='R385363655964807528_tab']/a[@aria-selected='false']";
	public static final String OPEN_ADD_COORDINATOR = "//*[@id='addco']/span[2]";
	public static final String REASON_CODES = "//ul[@role='listbox']//li";
	public static final String SPRINT_OPTION = "//a[text()='Sprint' and @role='menuitem']";
	public static final String ADD_SPRINT_FRAME = "//iframe[@title='Add Sprint']";
	public static final String SPRINT_ID = "//*[text()='Sprint ID ']//following::input[1]";
	public static final String SPRINT_NAME = "//*[text()='Sprint Name ']//following::input[1]";
	public static final String SPRINT_STORIES = "//*[text()='Stories/Storypoints/Tickets ']//following::input[1]";
	public static final String PLANNED_CAPACITY = "//*[text()='Planned Capacity ']//following::input[1]";
	// public static final String PARENT_MILESTONE =
	// "//*[contains(@id,'PARENT_TASK_ID')]//following::input[2]";
	public static final String PARENT_MILESTONE_SEARCH_BOX = "//div[contains(@id,'PARENT_TASK_ID')]/div[1]/input";
	public static final String SPRINT_CYCLE = "(//input[contains(@id,'SPRINT_CYCLE')])[2]";
	public static final String SPRINT_CYCLE_SEARCH_BOX = "//div[contains(@id,'SPRINT_CYCLE_dlg')]/div[1]/input";
	public static final String UNIT_OF_MEASURE = "//*[contains(@id,'SPRINT_UOM')]//following::input[2]";
	public static final String UNIT_OF_MEASURE_SEARCH_BOX = "//div[contains(@id,'SPRINT_UOM')]/div[1]/input";
	public static final String TEAM = "//*[text()='Team ']//following::input[@type='text'][1]";
	public static final String TEAM_SEARCH_BOX = "//div[contains(@id,'SPRINT_TEAM')]/div[1]/input";
	public static final String ADD_BUTTON = "//button[contains(text(),'Add')]";
	// Add Release
	public static final String RELEASE_OPTION = "//a[text()='Release']";
	public static final String RELEASE_ID = "//*[text()='Release ID ']//following::input[1]";
	public static final String RELEASE_NAME = "//*[text()='Release Name ']//following::input[1]";
	public static final String PARENT_MILESTONE = "//*[contains(text(),'Parent Milestone')]//following::input[@type!='hidden']";
	public static final String VIEW_RELEASE_ICON = "//span[@title='MilestoneIDToBeReplaced']//preceding::span[contains(@class,'fa fa-plus')][1]";
	public static final String MINUS_ICON = "//span[@title='MilestoneIDToBeReplaced']//preceding::span[1]";
	// verify release details
	public static final String ADDED_RELEASE_ID = "//span[@title='MilestoneIDToBeReplaced']//following::tr[1]/td[2]/span";
	public static final String MILESTONE_TYPE = "//span[@title='MilestoneIDToBeReplaced']//following::tr[1]/td[4]";
	public static final String ADDED_RELEASE_NAME = "//span[@title='MilestoneIDToBeReplaced']//following::tr[1]/td[3]/span";
	public static final String ADDED_NAME = "//span[@title='IDToBeReplaced']//following::td[1]/span";
	public static final String ADDED_TYPE = "//span[@title='IDToBeReplaced']//following::td[2]";
	public static final String ADDED_RELEASE_STATUS = "//span[@title='MilestoneIDToBeReplaced']//following::tr[1]/td[8]/span[2]";
	public static final String ADDED_STATUS = "//span[@title='IDToBeReplaced']//following::td[6]/span[2]";

	public static String getPercompletionErrorPopup() {
		return PERCOMPLETION_ERROR_POPUP;
	}

	public static final String ADDED_TASK_STATUS = "//span[@title='TaskIDToBeReplaced']//following::td[@headers='STATUS1']/span[2]";
	// span[@title='TASKID404']//following::td[@headers="STATUS1"]/span[2]
	public static final String ADD_RELEASE_FRAME = "//iframe[contains(@title,'Add')]";
	public static final String PARENT_MILESTONE_LIST = "//table[contains(@class,'GV-table')]//following::tr[contains(@class,'row is-readonly')]/td[1]";

	// Delete milestone
	public static final String CANCEL_BUTTON_OBSOLETE_MILESTONE = "//button[text()='Obsolete']//preceding::button[1]";
	// Update release
	public static final String UPDATE_BUTTON = "//span[@title='releaseIDToBeUpdated']//following::button[1]";
	public static final String UPDATE_FRAME = "//iframe[contains(@title,'Edit')]";
	public static final String STATUS = "//label[contains(text(),'Status')]//following::input[@type!='hidden']";
	public static final String RELEASE_PERC_COMPLETION = "//*[text()='Completion % ']//following::input[1]";
	public static final String CANCEL_BUTTON = "//button[text()='Save Changes']//preceding::button[1]";
	public static final String PERCOMPLETION_ERROR_POPUP = "// div[contains(@id,'ui-id')]/p";
	public static final String OK_BUTTON = "//button[contains(text(),'OK')]";
	public static final String DELETE_RELEASE_BUTTON = "//span[@title='releaseIDToBeUpdated']//following::button[2]";
	// Task locators
	public static final String TASK_OPTION = "//a[text()='Task']";
	public static final String TASK_ID = "//*[text()='Task ID ']//following::input[1]";
	public static final String TASK_NAME = "//*[text()='Task Name ']//following::input[1]";
	public static final String ACTUAL_START_DATE = "//label[contains(text(),'Actual Start Date')]//following::input[1]";
	// Phase locators
	public static final String PHASE_OPTION = "//a[text()='Phase']";
	public static final String PHASE_ID = "//*[text()='Phase ID ']//following::input[1]";
	public static final String PHASE_NAME = "//*[text()='Phase Name ']//following::input[1]";;

	public static final String MILESTONE_IDS = "//span[@title='MilestoneIDToBeReplaced']//following::td[@headers='TASK_NUMBER']/span";
	//public static final String NEXT_PAGINATION_BUTTON = "//*[@id='report_myRegion']/div/table[2]/tbody/tr/td/table/tbody/tr/td[4]/a";
	public static final String CHANGE_REQUEST_NUMBER_COLUMN = "//*[@id='report_table_myRegion']/tbody/tr/td[2]/span";
	public static final String POPUP_OK_BUTTON = "//div[@class='ui-dialog-content ui-widget-content']//following::button[1]";
	// Sprint locators
	public static final String SCOPE_CHANGE = "//*[contains(text(),'Scope Change')]//following::input[1]";
	public static final String IN_PROGRESS = "//*[contains(text(),'In Progress')]//following::input[1]";
	public static final String BLOCKED = "//*[contains(text(),'Blocked')]//following::input[1]";
	public static final String COMPLETED = "//*[contains(text(),'Completed')]//following::input[1]";
	public static final String RAG_STATUS = "//input[@type='radio' and @value='ValueToBeReplaced']//following::label[1]";
	public static final String P1_DEFECT = "//*[contains(text(),'P1')]//following::input[1]";
	public static final String P2_DEFECT = "//*[contains(text(),'P2')]//following::input[1]";
	public static final String P3_DEFECT = "//*[contains(text(),'P3')]//following::input[1]";
	public static final String P4_DEFECT = "//*[text()='P4']//following::input[1]";
	
	// *[contains(text(),'P1')]//following::input[1]
	public static final String DM_FIRST_PAGE_TXT = "//*[@id='report_myRegion']//span[@class='t-Report-paginationText' and contains(text(),'1 - ')]";
	public static final String DM_PAGINATION_NEXT_BTN = "//td[@class='pagination']/a[contains(text(),'Next')]";
	public static final String DM_PAGINATION_PREVIOUS_BTN = "//td[@class='pagination']/a[contains(@class,'paginationLink--prev')]";
	public static final String DM_CANCEL_BTN = "//*[@id='B564683242560764481']";
}
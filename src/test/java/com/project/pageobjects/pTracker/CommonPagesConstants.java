package com.project.pageobjects.pTracker;

import org.openqa.selenium.By;

public class CommonPagesConstants {
	public static final String IFRAME = "//iframe";
	// ACTIVE PROJECT TAB OBJECTS
	public static final String ACTIVE_PROJECT_SEARCH_TXT = "//input[@id='P30_AP_FC_SEACH']";
	public static final String ACTIVE_PROJECT_GO_BTN = "//button[@id='B604616602516884943' and contains(text(),'GO')]";
	public static final String ACTIVE_PROJECT_FILTER_BTN = "//*[@id='B559085308147581244']/span[contains(text(),'Filter')]";
	public static final String ACTIVE_PROJECT_DOWNLOAD_BTN = "//*[@id='B571598892348051371']/span[contains(text(),'Download')]";
	public static final String ACTIVE_PROJECT_NEXTSET_BTN = "//*[@id='report_projects-active-report']//a[contains(text(),'Next Set')]";

	// PROJECT TABS
	public static final String MANAGER_DETAILS_TAB = "//*[@id='mng_dtl_tab']//span[contains(text(),'Manager Details')]";
	public static final String ADD_SUB_MANAGER = "//*[@id='ADD_SUB_MGR']";
	public static final String ADD_SUB_MANAGER_BTN = "//*[@id='ADD_SUB_MGR']/span[contains(text(),'Add Sub Manager')]";
	public static final String SUB_MANAGER_TYPE = "//input[@id='P84_NEW_PERSON_ID']";

	// Ganesh//
	public static final String SELECT_SUB_MANAGER = "//input[@id='P84_NEW_PERSON_ID']";
	public static final String SELECT_SUB_MANAGER_SDATE = "//input[@id='P84_NEW_START_DATE']";
	public static final String SUB_MANAGER_ADD = "//button[contains(text(),'Add')]";
	public static final String SUB_MANAGER_CANCEL = "//button[@id='B422896789222081648']";
	// public static final String SUB_MANAGER_NAME_SEARCH=
	// "//input[@class='a-PopupLOV-search apex-item-text']";
	public static final String SUB_MANAGER_NAME_SEARCH = "//*[@id='PopupLov_84_P84_NEW_PERSON_ID_dlg']/div[1]/input";
	public static final String SUB_MANAGER_SELECT = "//*[@id='PopupLov_84_P84_NEW_PERSON_ID_dlg']//span[@class='popup-lov-highlight' and contains(text(),'Thakur, Monika')]";
	public static final String VERF_SUB_MANAGER_ROLE = "//td[text()='Sub Manager']";
	public static final String VERF_PROJECT_MANAGER_ROLE = "//td[text()='Project Manager']";
	public static final String VERF_SUB_MANAGER_NAME = "//*[@id='report_table_Manager-Details']//td[contains(text(),'Sub Manager')]/../td[2]";
	public static final String VERF_SUB_MANAGER_DATE = "//*[@id='report_table_Manager-Details']//td[contains(text(),'Sub Manager')]/../td[3]";
	public static final String SPINEER = "//span[text()='Processing']";

	// Nikita//

	public static final String RELEASE_RESOURCE_REQUEST_TAB = "//*[@id='report_table_Manager-Details']/tbody/tr[4]/td[5]/button";
	public static final String SELECT_RELEASE_RESOURCE_EDATE = "//input[@id='P66_END_DATE']";
	public static final String SELECT_ADD_EMPLYOEE = "//input[@id='P66_NEW_PERSON_ID']";
	public static final String ADD_EMPLYOEE_NAME_SEARCH = "//input[@id='P66_NEW_PERSON_ID']";
	public static final String ADD_EMPLYOEE_NAME = "//*[@id='PopupLov_66_P66_NEW_PERSON_ID_dlg']/div[1]/input";
	public static final String ADD_EMPLYOEE_SELECT = "//*[@id='PopupLov_66_P66_NEW_PERSON_ID_dlg']//span[contains(text(),'NameToBeReplaced')]";
	public static final String REALLOCATE_RESOURCE_SUBMIT_BTN = "/html/body/form/div/div[3]/div/div/div[3]/div/button[2]";
	public static final String REALLOCATE_ROLE_TYPE = "//span[@id='ui-id-5']";
	public static final String VERIFY_REALLOCATION = "//*[@id='EMPLOYEE_NAME']";
	public static final String END_DATE_PICKER = "//*[@class='ui-datepicker-calendar']/tbody/tr[1]/td[4]/button";

	public static final String PAYMENT_MILESTONE_DETAILS_TAB = "//*[@id='pyment-mlstn_tab']//*[text()='Payment Milestone']";
	public static final String ADD_PAYMENT_MILESTONE = "//*[@id='ADD_PAYMENT_MLSTN']";
	public static final String ADD_PAYMENT_MILESTONE_BTN = "//*[@id='ADD_PAYMENT_MLSTN']/span[contains(text(),'Add Payment Milestone')]";
	public static final String MILESTONE_NAME = "//input[@id='P64_DESCRIPTION']";

	public static final String MILESTONE_START_DATE = "//*[@id='P64_START_DATE']";
	public static final String MILESTONE_END_DATE = "//*[@id='P64_END_DATE']";
	public static final String MILESTONE_AMOUNT = "//*[@id='P64_COST']";
	public static final String MILESTONE_ADDBT = "//button[@id='CREATE']";

	public static final String MILESTONE_NAME_VERIFY = "//*[@id='report_table_payrpt']/tbody/tr[1]/td[1]/span";
	public static final String MILESTONE_STARTDATE_VERIFY = "//*[@id='report_table_payrpt']/tbody/tr[1]/td[2]";
	public static final String MILESTONE_ENDDATE_VERIFY = "//*[@id='report_table_payrpt']/tbody/tr[1]/td[3]";
	public static final String MILESTONE_AMOUNT_VERIFY = "//*[@id='report_table_payrpt']/tbody/tr[1]/td[7]";

	public static final String MILESTONE_MIDIFY_BTN = "//*[@id='report_table_payrpt']/tbody/tr/td/button[1]";
	public static final String MILESTONE_MIDIFY_CLICK = "//*[@id='report_table_payrpt']/tbody/tr[1]/td[8]/button[1]";
	public static final String MILESTONE_MIDIFY_SAVE = "//button[@id='SAVE']";

	public static final String SOW_DETAILS_TAB = "//*[@id='sow_tab']/a//span[contains(text(),'SOW ')]";
	public static final String CONNECT_SOW_BTN = "//*[@id='AddContract']/span[contains(text(),'Connect SOW')]";
	public static final String CONFIRM_CHECKBOX = "//*[@id='P92_CHECK_0']";
	public static final String PROCEED_BTN = "/html/body/form/div/div[2]/div/div/div/div[2]/div/div/table/tbody/tr/td/button[2]";
	public static final String SELECT_SOW_DROPDOWN = "//*[@id='P54_CONTRACT_ID']";
	public static final String SELECT_SOW_ROW = "//*[@id='PopupLov_54_P54_CONTRACT_ID_dlg']/div[3]/div/div[3]/ul/li";
	public static final String ADD_BTN = "/html/body/form/div/div[3]/div/div/div[3]/div/button[2]";
	public static final String VERIFY_SOW_ADD = "//*[@id='DESCRIPTION']//a[contains(text(),'SOW')]";
	public static final String VERIFY_ALERT_POP_UP = "//div[@class='t-Dialog' and @aria-label='Alert!!']";
	public static final String CANCEL_BTN = "//*[@id='B450352044803244736']";
	public static final String Frame1 = "//*[@id='apex_dialog_1']/iframe";
	public static final String Frame2 = "//*[@id='apex_dialog_2']/iframe";

	public static final String DELIVERY_MILESTONES = "//*[@id='mlstn_tab']/a//span[contains(text(),' Delivery Milestones')]";
	public static final String ADD_DELIVERY_MILESTONES_BTN = "//*[@id='ADD_TASK']/span[2]";
	public static final String PHASE_OPTION = "//*[@id='actions_task_menu_4i']";
	// Ganesh2//

	// RamyaG
	public static final String ACTIVE_PROJECT_NUMBER = "//*[@id='report_table_projects-active-report']/tbody/tr[2]/td[1]/a";

	public static final String ACTIVE_PEOPLE_TAB = "//*[@id='roles_tab']//span[contains(text(),'People')]";
	public static final String ACTIVE_ATTACHMENTS_TAB = "//*[@id='R394493418279145435_tab']//span[contains(text(),'Attachments')]";
	public static final String ACTIVE_TEAM_TAB = "//*[@id='teams_tab']//span[contains(text(),'Team')]";
	public static final String ACTIVE_FOR_HISTORY_TAB = "//*[@id='t_PageBody']";
	public static final String ACTIVE_HISTORY_TAB = "//*[@id='R626599589145054854_tab']//span[contains(text(),'History')]";

	public static final String PROJECT_ADD_ROLE_BTN = "//*[@id='ADD_ROLE']/span[contains(text(),'Add Role')]";

	public static final String CLK_ROLE_TYPE = "//input[@id='P63_ROLE_TYPE']";

	// public static final String NEWPROJECTS_LNK =
	// "//*[@id='new_tab']/a/span[contains(text(),'New Projects')]";
	// public static final String NEW_PROJECTS_TAB_FOCUSED =
	// "//*[@id='new_tab']/a[@aria-selected='true'] ";

	public static final String SELECT_ROLE_TYPE = "//*[@id='PopupLov_63_P63_ROLE_TYPE_dlg']/div[1]/input//li[contains(text(),'Assistant Manager -HR')]";
	// *[@id="PopupLov_63_P63_ROLE_TYPE_dlg"]/div[3]/div/div[3]/ul/li[1]
	public static final String NEW_ROLETYPE_TAB_FOCUSED = "//*[@id='PopupLov_63_P63_ROLE_TYPE_dlg']/div[1]/input/a[@aria-selected='true']	";

	// public static final String VERF_Role=
	// "//*[@id='PopupLov_63_P63_ROLE_TYPE_dlg']/div[3]/div/div[3]/ul/li[2]";
	public static final String CLK_LOCATION = "//*[@id='P63_LOCATION']";
	public static final String SELECT_LOCATION = "//*[@id='PopupLov_63_P63_LOCATION_dlg']/div[1]/input//li[contains(text(),'Offshore')]";
	// public static final String SELECT_NUMBER_OF_RESOURCES=
	// "//input[@id='P63_TOTAL_RESOURCES']";
	public static final String ADD_MONTH_LOADING_SEP = "//*[@id='P63_MTH1_PCT']";
	public static final String ADD_MONTH_LOADING_OCT = "//*[@id=\"P63_MTH2_PCT\"]";

	public static final String VERF_Role = "//td[text()='Assistant Manager -HR']";
	public static final String VERF_Location = "//*[@id='PopupLov_63_P63_LOCATION_dlg']/div[2]/div/div[3]/ul/li[1]";

//    public static final String VERF_LOCATION= "//td[text()='Thakur, Monika']";
//    public static final String VERF_NUMBER_OF_RESOURCES= "//td[text()='02-AUG-2023']";

	public static final String MONTHLY_LOADING_DEC = "//input[@id='P63_MTH1_PCT']";
	public static final String MONTHLY_LOADING_JAN = "//input[@id='P63_MTH2_PCT']";
	public static final String MONTHLY_LOADING_FEB = "//input[@id='P63_MTH3_PCT']";
	public static final String MONTHLY_LOADING_MAR = "//input[@id='P63_MTH4_PCT']";
	public static final String MONTHLY_LOADING_APR = "//input[@id='P63_MTH5_PCT']";
	public static final String MONTHLY_LOADING_MAY = "//input[@id='P63_MTH6_PCT']";
	public static final String MONTHLY_LOADING_JUN = "//input[@id='P63_MTH7_PCT']";
	public static final String MONTHLY_LOADING_JUL = "//input[@id='P63_MTH8_PCT']";
	public static final String MONTHLY_LOADING_AUG = "//input[@id='P63_MTH9_PCT']";
	public static final String MONTHLY_LOADING_SEP = "//input[@id='P63_MTH1_PCT']";// *[@id="P63_MTH1_PCT"]
	public static final String MONTHLY_LOADING_OCT = "//input[@id='P63_MTH2_PCT']";
	public static final String MONTHLY_LOADING_NOV = "//input[@id='P63_MTH3_PCT']";
	public static final String ADDING_ADD_ROLE = "//button[@id='CREATE']";

	// public static final String BTN_ADD_ROLE="//button[@id=\'ADD_ROLE\']";
	public static final String ASSISTANT_MANAGER_HR_NAME_SEARCH = "//*[@id='PopupLov_63_P63_ROLE_TYPE_dlg']/div[3]/div/div[3]/ul/li[8]/input";
	public static final String ASSISTANT_MANAGER_HR_NAME_SELECT = "//*[@id='PopupLov_84_P84_NEW_PERSON_ID_dlg']//span[@class='popup-lov-highlight' and contains(text(),'Thakur, Monika')]";
	public static final String VERF_ASSISTANT_MANAGER_HR_ROLE = "//td[text()='Assistant Manager -HR']";
	public static final String VERF_LOCATION = "//td[text()='Offshore']";
	public static final String VERF_NUMBER_OF_RESOURCES = "//td[text()='1']";

	public static final String ROLETYPE_EDIT_ICON = "//span[@class='t-Icon fa fa-edit']";
	public static final String EDIT_LABEL = "//*[@id='report_role-report']//a[contains(text(),'Edit')]";
	public static final String CLICK_SAVE_CHANGES = "//*[@id='SAVE']";
	// class="t-Button t-Button--noLabel t-Button--icon t-Button--link"><span
	// class="t-Icon fa fa-edit" aria-hidden="true"></span></button>

	// ramya//

	// Aakash Saxena

	public static final String ACTIVE_PROJECT_ROW = "//*[@id='report_table_projects-active-report']/tbody/tr/td";
	public static final String EXTEND_END_DATE = "//span[contains(text(),'Extend End Date')]";
	public static final String CALENDAR_FRAME = "//div[@id='apex_dialog_1']//iframe";
	public static final String CALENDAR_ICON = "//button[@class='ui-datepicker-trigger a-Button a-Button--calendar']";
	public static final String CALENDAR_YEAR = "//div[@id='ui-datepicker-div']//select[@class='ui-datepicker-year']";
	public static final String CALENDAR_MONTH = "//div[@id='ui-datepicker-div']//select[@class='ui-datepicker-month']";
	public static final String CALENDAR_DATE = "//div[@id='ui-datepicker-div']/table/tbody/tr/td/a";
	public static final String ADD_COMMENT = "//button[@id='addimport']";
	public static final String COMMENT_FRAME = "//div[@class='ui-dialog-content ui-widget-content js-dialogReady']//iframe[@title='Remark']";
	public static final String ADD_TEXT = "//textarea[@id='P107_STATUS_UPDATE']";
	public static final String UPDATE_TEXT = "//button[@id='B67092796111627053']";
	public static final String REMARKS_TEXT = "//div[@class='remarks-text']";
	public static final String SEND_APPROVAL = "//button[@id='B130362723376102847']";
	public static final String EXTEND_ALERT = "//div[@id='t_Alert_Notification']";
	public static final String DATE_ERROR_MSG = "//li[normalize-space()='Please select Extend End Date.']";
	public static final String COMMENT_ERROR_MSG = "//li[normalize-space()='Please add valid comments.']";
	public static final String CANCEL_EXTEND_DATE = "//button[@id='B130360280220102845']";
	public static final String NEW_END_DATE = "//span[contains(text(),'Extended Project End Date')]";
	public static final String BACK_TO_ACTIVE = "//button[@id='B564683242560764481']";

	public static final String HOVER_RIGHT = "//div[@id='rgnSticky']//span[@class='a-Icon icon-right-chevron']";
	public static final String PROJECT_HISTORY = "//li[@id='R626599589145054854_tab']//span";
	public static final String PROJECT_CREATION = "//div[@id='R614164536159391044']//p";
	public static final String PROJECT_CREATION_DATE = "//div[@id='R614164536159391044']//td[contains(text(),'Created date')]//span";
	public static final String PROJECT_EXTENSION = "//div[@id='R626599705647054855']//p";
	public static final String PROJECT_EXTENSION_DATE = "//div[@id='R626599705647054855']//td[contains(text(),'Initiated date')]//span";
	public static final String PROJECT_MODIFICATION = "//div[@id='R97591260268108047']//p";
	public static final String PROJECT_MODIFICATION_DATE = "//div[@id='R97591260268108047']//td[contains(text(),'Initiated date')]//span";

	public static final String EDIT_ACTIVE_PROJECT = "//span[contains(text(),'Edit Project Details')]";
	public static final String PROJECT_NAME = "//div[contains(@id,'PROJECT_NAME')]//input";
	public static final String PROJECT_CATEGORY = "//label[contains(text(),'Project Category')]/../..//input[@type='text']";
	public static final String PROJECT_CATEGORY_LIST = "//div[contains(@id,'PROJECT_CATEGORY_dlg')]//li";
	public static final String PROJECT_CYCLE = "//div[contains(@id,'LIFECYCLE_CONTAINER')]//input[@type='text']";
	public static final String PROJECT_CYCLE_LIST = "//div[contains(@id,'PROJECT_LIFECYCLE_dlg')]//li";
	public static final String PROJECT_SALESFORCE_ID = "//div[contains(@id,'OPPORTUNITY_ID')]//input";
	public static final String PROJECT_XORIANT_NICHE_SKILLS = "//div[contains(@id,'NICHE_SKILLS')]//input";
	public static final String PROJECT_XORIANT_CONTRIBUTION = "//div[contains(@id,'XORIANT_CONTRIBUTION')]//textarea";
	public static final String SUBMIT_PROJECT_DETAILS = "//div[@id='R471160034471059392']//button[contains(@id,'SUBMIT')]";

	// Aakash Saxena

}

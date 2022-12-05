package com.project.pageobjects.pTracker;

public class ProjectRegistrationConstants {
	
	// Project Details Fields
	
	public static final String PROJECT_NAME = "//*[@id='P2_PROJECT_NAME']";
	public static final String PROJECT_MODE = "//*[@id='P2_PROJECT_MODE']";
	public static final String PROJECT_MODE_BTN = "//*[@id='P2_PROJECT_MODE_lov_btn']/span";
	public static final String PROJECT_MODE_DLG = "//*[@id='PopupLov_2_P2_PROJECT_MODE_dlg']//input[contains(@aria-label,'Search')]";
	public static final String INTERNAL_PROJECT_TYPE = "//*[@id='P2_XORIANT_INTERNAL_PROJ_TYPE']";
	public static final String PROJECT_START_DATE = "//*[@id='P2_PROJECT_START_DATE']";
	public static final String PROJECT_END_DATE = "//*[@id='P2_PROJECT_END_DATE']";
	public static final String SIGNING_ORGANISATION = "//*[@id='P2_ORG_ID']";
	public static final String SIGNING_ORGANISATION_DLG = "//*[@id='PopupLov_2_P2_ORG_ID_dlg']//input[contains(@aria-label,'Search')]";
	public static final String SIGNING_LOCATION = "//*[@id='P2_LOCATION_ID']";
	public static final String SIGNING_LOCATION_DLG = "//*[@id='PopupLov_2_P2_LOCATION_ID_dlg']//input[contains(@aria-label,'Search')]";
	public static final String BUSINESS_UNIT = "//*[@id='P2_UNIT_SEGMENT']";
	public static final String BUSINESS_UNIT_DLG = "//*[@id='PopupLov_2_P2_UNIT_SEGMENT_dlg']//input[contains(@aria-label,'Search')]";
	public static final String LINES_OF_BUSINESS = "//*[@id='P2_LOB']";
	public static final String LINES_OF_BUSINESS_DLG = "//*[@id='PopupLov_2_P2_LOB_dlg']//input[contains(@aria-label,'Search')]";
	public static final String VERTICAL = "//*[@id='P2_VERTICAL']";
	public static final String VERTICAL_DLG = "//*[@id='PopupLov_2_P2_VERTICAL_dlg']//input[contains(@aria-label,'Search')]";
	public static final String OPERATION_OFFICE_LOCATION = "//*[@id='P2_PROJECT_STATE']";
	public static final String OPERATION_OFFICE_LOCATION_DLG = "//*[@id='PopupLov_2_P2_PROJECT_STATE_dlg']//input[contains(@aria-label,'Search')]";
	public static final String OPRATION_LOCATION_CITY = "//*[@id='P2_PROJECT_CITY']";
	public static final String OPRATION_LOCATION_CITY_DLG = "//*[@id='PopupLov_2_P2_PROJECT_CITY_dlg']//input[contains(@aria-label,'Search')]";
	public static final String PROJECT_CATEGORY = "//*[@id='P2_PROJECT_CATEGORY']"; 
	public static final String PROJECT_CATEGORY_DLG = "//*[@id='PopupLov_2_P2_PROJECT_CATEGORY_dlg']//input[contains(@aria-label,'Search')]";
	public static final String PROJECT_TYPE = "//*[@id='P2_PROJECT_TYPE']";
	public static final String PROJECT_TYPE_DLG = "//*[@id='PopupLov_2_P2_PROJECT_TYPE_dlg']//input[contains(@aria-label,'Search')]";
	
	public static final String PROGRESS_METHOD = "//*[@id='P2_PROGRESS_METHOD']";
	public static final String PROJECT_CYCLE = "//*[@id='P2_PROJECT_LIFECYCLE']";
	public static final String ENGAGEMENT_MODEL = "//*[@id='P2_ENGAGEMENT_TYPE']";
	public static final String SALESFORCE_OPPORTUNITY_ID = "//*[@id='P2_SFDC_OPPORTUNITY_ID']";
	public static final String BILLING_METHOD = "//*[@id='P2_BILLING_METHOD']";
	public static final String BILLING_END_DAY = "//*[@id='P2_BILLING_END_DAY']";
	public static final String BILLING_CYCLE = "//*[@id='P2_BILLING_CYCLE']";
	public static final String CAP_HOURS = "//*[@id='P2_CAP_HOURS']"; 
	public static final String TECHNOLOGY_STACK = "//*[@id='P2_TECH_STACK']";
	public static final String XORIANT_NICHE_SKILLS = "//*[@id='P2_NICHE_SKILLS']";
	public static final String TECHNOLOGY_DOMAIN = "//*[@id='P2_TECH_DOMAIN']"; 
	public static final String PLANNED_GM = "//*[@id='P2_PLANNED_GM']";
	public static final String END_CUSTOMER_NAME ="//*[@id='oj-inputsearch-input-P2_END_CUSTOMER_NAME_HIDDEN']";
	public static final String PARENT_PROJECT="//*[@id='oj-inputsearch-input-P2_PARENT_PROJECT_HIDDEN']";
	public static final String PARENT_PROJECT_TEXTBOX = "//div[contains(@id,'P16_PARENT_PROJECT_CONTAINER')]//input[contains(@aria-labelledby,'P16_PARENT_PROJECT')]";
	public static final String PROJECT_CURRENCY_CODE = "//*[@id='P2_PROJECT_CURRENCY_CODE']";
	public static final String PROJECT_NUMBER = "//div[contains(@id,'P16_PROJECT_NUMBER_CONTAINER')]//input[contains(@aria-labelledby,'P16_PROJECT_NUMBER_LABEL')]";
	public static final String PROJECT_NUMBER_FIELD = "//*[@id='oj-inputsearch-input-P2_PROJECT_NUMBER_HIDDEN']";	
	// Project Information Fields
	public static final String PROJECT_BRIEF = "//*[@id='P2_PROJECT_DESCRIPTION']";
	public static final String XORIANT_CONTRIBUTION = "//*[@id='P2_XORIANT_CONTRIBUTION']";
	public static final String BUSINESS_GOAL = "//*[@id='P2_BUSINESS_GOAL']";
	public static final String INVESTMENT_GOAL = "//*[@id='P2_INVESTMENT_GOAL']";
	public static final String GO_TO_MARKET_PLAN = "//*[@id='P2_MARKET_PLAN']";
	
	// Practice Information Fields
	public static final String PRACTICE_CONTRIBUTION = "//*[@id='P2_XORIANT_PRACTICE_TYPE']";
	public static final String PRACTICE_BUSINESS_UNIT = "//*[@id='P2_BU']";
	public static final String PRACTICE_LEAD= "//*[@id='P2_PRACTICE_LEAD_EMP_ID']";
	public static final String PRACTICE_HEAD = "//*[@id='P2_PRACTICE_HEAD_EMP_ID']";
	public static final String PRACTICE_PROGRAM_MANAGER = "//*[@id='P2_PRACTICE_PROG_MGR_EMP_ID']";
	
	// Customer Details Fields
	public static final String CUSTOMER = "//*[@id='P2_CUSTOMER_ID']";
	public static final String CUSTOMER_ADDR1 = "//*[@id='P2_CUSTOMER_ADDR1']";
	public static final String CUSTOMER_ADDR2 = "//*[@id='P2_CUSTOMER_ADDR2']";
	public static final String CUSTOMER_COUNTRY = "//*[@id='P2_CUSTOMER_COUNTRY1']";
	public static final String CUSTOMER_STATE = "//*[@id='P2_CUST_STATE1']";
	public static final String CUSTOMER_CITY = "//*[@id='P2_CUST_CITY1']";
	public static final String CUSTOMER_PINCODE = "//*[@id='P2_CUST_PINCODE1']";
	public static final String CUSTOMER_POINT_OF_CONTACT = "//*[@id='P2_CUSTOMER_CONTACT']";
	public static final String CUSTOMER_PHONE = "//*[@id='P2_CUSTOMER_PH']";
	public static final String CUSTOMER_EMAIL = "//*[@id='P2_CUST_EMAIL_ADDRESS']";
	
	
	// SOW Fields
	
	//Customer Coordinator
	public static final String ADD_COORDINATOR_BTN = "//*[@id='custimport']//span[contains(text(),'Add Coordinator')]";
	public static final String ADD_CUSTOMER_COORDINATOR_DLG_TXT = "//*[@class='ui-dialog-title' and contains(text(),'Add Customer Coordinator')]";
	public static final String ADD_CUSTOMER_COORDINATOR_DLG = "//*[@id='wwvFlowForm']//div[@aria-label='Add Customer Coordinator']";
	public static final String COORDINATOR_NAME_TXT = "//*[@id='oj-inputsearch-input-P96_NAME_HIDDEN']";
	public static final String PROJECT_ROLE_TXT = "//*[@id='P96_PROJECT_ROLE']";
	public static final String DESIGNATION_TXT = "//*[@id='P96_DESIGNATION']";
	public static final String EMAIL_ADDRESS_TXT = "//*[@id='P96_EMAIL_ADDRESS']";
	public static final String ACTIVE_RADIO_BTN = "//*[@id='P96_STATUS']//label[contains(text(),'Active')]";
	public static final String INACTIVE_RADIO_BTN = "//*[@id='P96_STATUS']//label[contains(text(),'Inactive')]";
	public static final String ADD_COORDINATOR_CANCEL_BTN = "//*[@id='B379682200144213909' and contains(text(),'Cancel')]";
	public static final String ADD_BTN = "//*[@id='B379684541596213911' and contains(text(),'Add')]";
	
	//Attachments
	public static final String UPLOAD_BTN = "//*[@id='attach']//span[contains(text(),'Upload File')]";
	public static final String ATTACHMENT_DLG_TXT = "//*[@class='ui-dialog-title' and contains(text(),'Attachment')]";
	public static final String ATTACHMENT_DLG = "//*[@id='wwvFlowForm']//div[@aria-label='Attachment']";
	public static final String FILE_TYPE_LST = "//*[@id='P8_FILE_TYPE']";
	public static final String BROWSE_FILE = "//*[@id='P4_FILE_BLOB' and contains(@data-caption,'Choose file')]";
	//*[@id='P8_FILE_BLOB_DROPZONE']//span[contains(text(),'Choose file')]
	public static final String CANCEL_BTN = "//*[@id='B427327189639930983' and contains(text(),'Cancel')] ";
	public static final String UPLOAD_ATTACHMENT_BTN = "//*[@id='B427327610526930984' and contains(text(),'Upload Attachment')]";
	
	// Manager Details Fields
	public static final String PROJECT_SPONSOR = "//*[@id='P2_PROJECT_SPONSOR']";
	public static final String PROJECT_SPONSOR_SEARCH_DLG = "//*[@id='PopupLov_2_P2_PROJECT_SPONSOR_dlg']//input[contains(@aria-label,'Search')]";
	public static final String SALESPERSON = "//*[@id='P2_SALESPERSON_ID']";
	public static final String SALESPERSON_SEARCH_DLG = "//*[@id='PopupLov_2_P2_SALESPERSON_ID_dlg']//input[contains(@aria-label,'Search')]";
	public static final String ACCOUNT_MANAGER = "//*[@id='P2_ACCOUNT_MANAGER_ID']";
	public static final String ACCOUNT_MANAGER_SEARCH_DLG = "//*[@id='PopupLov_2_P2_ACCOUNT_MANAGER_ID_dlg']//input[contains(@aria-label,'Search')]";
	public static final String DELIVERY_HEAD = "//*[@id='P2_DELIVERY_HEAD_ID']";
	public static final String DELIVERY_HEAD_SEARCH_DLG = "//*[@id='PopupLov_2_P2_DELIVERY_HEAD_ID_dlg']//input[contains(@aria-label,'Search')]";
	
	public static final String SUBVERTICAL_HEAD = "//*[@id='P2_SUB_VERTICAL_HEAD_ID']";
	public static final String SUBVERTICAL_HEAD_SEARCH_DLG = "//*[@id='PopupLov_2_P2_SUB_VERTICAL_HEAD_ID_dlg']//input[contains(@aria-label,'Search')]";
	
	public static final String PROGRAM_MANAGER = "//*[@id='P2_PROGRAM_MANAGER_ID']";
	public static final String PROGRAM_MANAGER_SEARCH_DLG = "//*[@id='PopupLov_2_P2_PROGRAM_MANAGER_ID_dlg']//input[contains(@aria-label,'Search')]";
	public static final String PROJECT_MANAGER = "//*[@id='P2_PROJECT_MANAGER_ID']";
	public static final String PROJECT_MANAGER_SEARCH_DLG = "//*[@id='PopupLov_2_P2_PROJECT_MANAGER_ID_dlg']//input[contains(@aria-label,'Search')]";
	public static final String SUB_MANAGER = "//*[@id='P2_SUB_MANAGER_ID']";
	public static final String SUB_MANAGER_SEARCH_DLG = "//*[@id='PopupLov_2_P2_SUB_MANAGER_ID_dlg']//input[contains(@aria-label,'Search')]";
	public static final String FINANCE_REPRESENTATIVE = "//*[@id='P2_FINANCE_REPRESENTATIVE_ID']";
	public static final String FINANCE_REPRESENTATIVE_SEARCH_DLG = "//*[@id='PopupLov_2_P2_FINANCE_REPRESENTATIVE_ID_dlg']//input[contains(@aria-label,'Search')]";
	
	// HEADINGS
	public static final String REQUEST_ID_TEXT = "//*[@id='R421705453839552944']//h1[@class='t-Breadcrumb-label']";
	public static final String NEWPROJECT_PAGE_HEADINGS = "//*[@id='R565985495473226764']//p[contains(text(),'New project')]";
	public static final String PROJECT_DETAILS_HEADINGS = "//*[@id='project_detail_region_heading']";
	public static final String PROJECT_INFORMATION_HEADINGS = "//*[@id='project_information_region_heading']";
	public static final String PRACTICE_INFORMATION_HEADINGS = "//*[@id='project_information_region_heading']";
	public static final String CUSTOMER_INFORMATION_HEADINGS = "//*[@id='customer_details_region_heading']";
	public static final String CONNECT_SOW_HEADINGS = "//*[@id='connect_sow_region_heading']";
	public static final String MANGER_DETAILS_HEADINGS = "//*[@id='manager_details_region_heading']";
	public static final String HISTORY_HEADINGS = "//*[@id='history_region_heading']";
	public static final String CLASSIFICATION_HEADINGS ="//*[@id='CLASS_heading']";
	

}

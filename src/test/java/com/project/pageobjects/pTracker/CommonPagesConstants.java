package com.project.pageobjects.pTracker;

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
	
	public static final String ADD_SUB_MANAGER_BTN = "//*[@id='ADD_SUB_MGR']/span[contains(text(),'Add Sub Manager')]";
	
	
	public static final String SUB_MANAGER_TYPE= "//input[@id='P84_NEW_PERSON_ID']";
    
	//Ganesh// 
    public static final String SELECT_SUB_MANAGER= "//input[@id='P84_NEW_PERSON_ID']";
    public static final String SELECT_SUB_MANAGER_SDATE= "//input[@id='P84_NEW_START_DATE']";
    public static final String SUB_MANAGER_ADD= "//button[contains(text(),'Add')]";
    public static final String SUB_MANAGER_CANCEL= "//button[@id='B422896789222081648']";
    //public static final String SUB_MANAGER_NAME_SEARCH= "//input[@class='a-PopupLOV-search apex-item-text']";
    public static final String SUB_MANAGER_NAME_SEARCH= "//*[@id='PopupLov_84_P84_NEW_PERSON_ID_dlg']/div[1]/input";
    public static final String SUB_MANAGER_SELECT= "//*[@id='PopupLov_84_P84_NEW_PERSON_ID_dlg']//span[@class='popup-lov-highlight' and contains(text(),'Thakur, Monika')]"; 
    public static final String VERF_SUB_MANAGER_ROLE= "//td[text()='Sub Manager']";
	public static final String VERF_PROJECT_MANAGER_ROLE= "//td[text()='Project Manager']";
    public static final String VERF_SUB_MANAGER_NAME= "//*[@id='report_table_Manager-Details']//td[contains(text(),'Sub Manager')]/../td[2]";
    public static final String VERF_SUB_MANAGER_DATE= "//*[@id='report_table_Manager-Details']//td[contains(text(),'Sub Manager')]/../td[3]";
    public static final String SPINEER= "//span[text()='Processing']";
    
	//Nikita//
    
	public static final String RELEASE_RESOURCE_REQUEST_TAB =  "//*[@id='report_table_Manager-Details']/tbody/tr[4]/td[5]/button";
	public static final String SELECT_RELEASE_RESOURCE_EDATE= "//input[@id='P66_END_DATE']";
	public static final String SELECT_ADD_EMPLYOEE= "//input[@id='P66_NEW_PERSON_ID']";
	public static final String ADD_EMPLYOEE_NAME_SEARCH= "//input[@id='P66_NEW_PERSON_ID']";
	public static final String ADD_EMPLYOEE_SELECT= "//*[@id='PopupLov_66_P66_NEW_PERSON_ID_dlg']//span[contains(text(),'., Akanksha')]";
    public static final String REALLOCATE_RESOURCE_SUBMIT_BTN = "/html/body/form/div/div[3]/div/div/div[3]/div/button[2]";
	public static final String REALLOCATE_ROLE_TYPE = "//span[@id='ui-id-5']";
	public static final String VERIFY_REALLOCATION = "//*[@id='EMPLOYEE_NAME']";
	public static final String END_DATE_PICKER =  "//*[@class='ui-datepicker-calendar']/tbody/tr[1]/td[4]/button";
	
  	public static final String PAYMENT_MILESTONE_DETAILS_TAB = "//*[@id='pyment-mlstn_tab']//*[text()='Payment Milestone']";
  	
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
  	
    public static final String MILESTONE_MIDIFY_CLICK = "//*[@id='report_table_payrpt']/tbody/tr[1]/td[8]/button[1]";
  	public static final String MILESTONE_MIDIFY_SAVE = "//button[@id='SAVE']";
  	
  	//Ganesh2//
 
}

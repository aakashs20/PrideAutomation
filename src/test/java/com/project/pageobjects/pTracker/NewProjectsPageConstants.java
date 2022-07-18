package com.project.pageobjects.pTracker;


public class NewProjectsPageConstants {
	
	// Tab Links 
	public static final String NEWPROJECTS_LNK = "//*[@id='new_tab']/a/span[contains(text(),'New Projects')]";
	public static final String NEW_PROJECTS_TAB_FOCUSED = "//*[@id='new_tab']/a[@aria-selected='true']	";
	public static final String CLOSED_PROJECTS_TAB_LNK = "//*[@id='closed_tab']/a/span[contains(text(),'Closed Projects')]";
	public static final String CLOSED_PROJECTS_TAB_FOCUSED = "//*[@id='closed_tab']/a[@aria-selected='true']";
	public static final String ACTIVE_PROJECTS_TAB_LNK = "//*[@id='active_tab']/a/span[contains(text(),'Active Projects')]";
	public static final String ACTIVE_PROJECTS_TAB_FOCUSED = "//*[@id='active_tab']/a[@aria-selected='true']";
	
	public static final String CREATEPROJECTS_LNK = "//span[@class='t-Button-label' and contains(text(),'Create Project')]";
	public static final String DRAFTPROJECTS_LNK = "//*[@id='card_region_cards']//h3[contains(text(),'Draft')]";
	public static final String PENDINGWITH_DH_LNK = "//*[@id='card_region_cards']//h3[contains(text(),'Pending with DH')]";
	public static final String PENDINGWITH_PMO_LNK = "//*[@id='card_region_cards']//h3[contains(text(),'Pending with PMO')]";
	public static final String PENDINGWITH_FINANCE_LNK = "//*[@id='card_region_cards']//h3[contains(text(),'Pending with Finance')]";
	public static final String SEARCH_PROJECTS_TXT = "//*[@id='P30_NP_FC_SEARCH']";
	public static final String GO_BTN = "//*[@id='B604615927023884937']";
	public static final String FILTER_BTN = "//*[@id='B559085308147581244']/span[contains(text(),'Filter')]";
	public static final String DOWNLOAD_BTN = "//*[@id='B604618873030884966']/span[contains(text(),'Download')]";
	public static final String NEXTSET_BTN = "//*[@id='report_projects-active-report']//a[contains(text(),'Next Set')]";
	public static final String CREATE_FROM_EXISTING_PROJECT_LNK = "//*[@class='a-Menu-labelContainer']//a[contains(text(),'Select existing')]";
	public static final String CREATE_NEW_PROJECT_LNK = "//*[@class='a-Menu-labelContainer']//a[contains(text(),'New project')]";
	public static final String SELECT_FROM_EXITING_DLG = "//*[@id='ui-id-4' and contains(text(),'Select From Existing Project')]";
	
	public static final String EXITING_PROJECT_LBL = "//*[@id='P29_PROJECT_NUMBER_CONTAINER']" ;
	//public static final String EXITING_PROJECT_LBL = "//*[@id='P29_PROJECT_NUMBER_LABEL' and contains(text(),'Existing Project')]";
	public static final String SELECT_PROJECT_LST = "#P29_PROJECT_NUMBER.popup_lov.popup_lov.apex-item-text.apex-item-popup-lov"; 
	public static final String SELECT_PROJECT_BTN = "//*[@id='P29_PROJECT_NUMBER_lov_btn']"; 
	public static final String ADD_PROJECT_BTN = "//*[@id='B455823610092085497' and contains(text(),'Add')]";

	
	public static final String PROJECT_SEARCH_TXT = "//*[@id='t_PageBody']//input[contains(@aria-label,'Search') and contains(@class,'a-PopupLOV-search')]";
	public static final String SELECT_EXITING_PROJECT_LST = "//*[@id='PopupLov_29_P29_PROJECT_NUMBER_dlg']//span[contains(text(),'3IIN0007')]";

	public static final String NEWPROJECT_PAGE_CANCEL_BTN = "//*[@id='B421738502938553422' and contains(text(),'Cancel')]";
	public static final String NEWPROJECT_PAGE_SAVE_BTN = "//*[@id='CREATE_DRAFT' and contains(text(),'Save')]";
	public static final String NEWPROJECT_PAGE_SUBMIT_BTN = "//*[@id='CREATE_SUBMIT' and contains(text(),'Submit')]";
	public static final String NEWPROJECT_PAGE_ALERT_MSG = "//*[@id='t_Alert_Notification']//h2[contains(text(),'errors have occurred')]";
	public static final String NEWPROJECT_PAGE_ALERT_CLOSE_BTN = "//*[@id='t_Alert_Notification']//button[contains(@title,'Close Notification')]";
	public static final String SAVEPROJECT_ALERT_MSG = "//*[@class='t-Alert-title'and contains(text(),'Changes Saved')]";
	public static final String PROJECT_SAVE_ALERT_MSG = "//*[@id='t_Alert_Notification']//li[contains(text(),'Please attach supporting document.')]";
	public static final String CLOSE_ALERT_MSG = "//*[@id='t_Alert_Notification']//button[@title='Close Notification']";
	


}

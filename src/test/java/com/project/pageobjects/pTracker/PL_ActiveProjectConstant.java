package com.project.pageobjects.pTracker;

public class PL_ActiveProjectConstant {

	public static final String ACTIVE_FILTER_HEADERS ="//*[@id='fcap_fr']/div/h3";
	public static final String ACTIVE_FILTER_BUTTON="//*[@id='FCAP_BTN']/span[contains(text(),'Filter')]";
	public static final String ACTIVE_FILTER_CHECKBOXS="//div[@id='fcap_fr']//div[@class='a-FS-wrap']//div[contains(@class,'apex-item-option')]"; //div[@id='fcap_fr']//div[@class='a-FS-wrap']//div[@class='apex-item-option']
	public static final String APPLIED_FILTERS= "//*[@id='ap_top_region']/ul/li/button";
	public static final String APPLIED_FIXEDPRICE_ROW= "(//tbody/tr)[2]//td";
	public static final String ACTIVE_PROJECTS_TAB_LNK = "//span[normalize-space()='Active Projects']";
	public static final String ACTIVE_PROJECTTYPE_FILTERS= "//h4[@class='t-Card-subtitle']";
	public static final String APPLIED_PROJECTTYPE_FILTER_COL="(//table[@id='report_table_projects-active-report']//tbody//tr)//td[4]";
	public static final String APPLIED_CUSTOMERTYPE_FILTER_COL="//*[@id='report_table_projects-active-report']/tbody/tr/td[7]";
	public static final String ACTIVE_FILTERS_HEADERS_ROW="//*[@id='report_table_projects-active-report']/thead/tr/th";
	public static final String ACTIVE_FILTERS_DATA="//*[@id=\"report_table_projects-active-report\"]/tbody/tr/td";
	public static final String ACTIVE_NO_DATA="//*[@id='report_622190509015726503_catch']/span/span";
	public static final String ACTIVE_PROJ_PAGINATION="//*[@id='report_projects-active-report']/div/table[2]/tbody/tr/td/table/tbody/tr/td[3]/span/a";
	public static final String ACTIVE_PROJECT_TYPE_SELECTOR = "//ul[@id='R505691641167493060_cards']//div[@class='t-Card']";
	public static final String ACTIVE_PROJECT_TABLE_PROJECT_TYPE = "//table[@id='report_table_projects-classic-report']//td[@headers='PROJECT_TYPE']";
	//Ajay
	public static final String ACTIVE_PROJECT_NUMBER = "//a[normalize-space()='ProjectNumberToReplace']";
	public static final String NEWPROJECT_MENU_MOUSE_HOVER = "//div[@id='rgnSticky']//div[2]//a[1]//span[1]";
	public static final String INVOICE_BUTTON ="//span[normalize-space()='Invoices']";
	public static final String APEX_RDX_CONTAINER_UL ="//div[@class='apex-rds-container']/ul";
	public static final String RAISE_INVOICE ="//button[@id='ADD_INV']";
	public static final String PROJECT_NUMBER_XPATH_FIRST_PART ="//a[normalize-space()='";
	public static final String PROJECT_NUMBER_XPATH_LAST_PART ="]'";
	public static final String FRAME_01 ="//iframe[@title='Raise Invoice Request']";
	public static final String PAYMENT_MILESTONE ="//input[@id='P77_PAYMENT_MILESTONE']";
	public static final String SELECT_PAYMENT_MILESTONE ="//ul[@class='a-IconList']/li[1]";
	public static final String REQUEST_RAISE_INVOICE_BUTTON ="//button[@class='a-FS-clearAll blue-button t-Button--iconRight t-Button--padRight']";
	public static final String PAYMENT_MILESTONE_EROOR_TEXT ="//*[@id='P77_PAYMENT_MILESTONE_error']";
	public static final String CANCEL_BUTTON ="//*[@id='B582891941045352174']";
	public static final String PAYMENT_MILESTONE_DRP_VALUES="//div[@class='a-TMV-w-scroll']/ul";
	public static final String PTRACK_PAYMENT_MILESTONE ="//td[@headers='PAYMENT_MILESTONES']";
	public static final String PTRACK_INPUT_BOX ="#P30_AP_FC_SEACH";

	public static final String FIXED_PRICE_ACTIVE_FILTER_BTN = "(//h4[@class='t-Card-subtitle'])[1]";


	




}


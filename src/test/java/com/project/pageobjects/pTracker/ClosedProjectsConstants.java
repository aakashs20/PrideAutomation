package com.project.pageobjects.pTracker;

public class ClosedProjectsConstants {
	
	public static final String DOWNLOAD_BUTTON = "//*[@id='B566932007027501461']/span[contains(text(),'Download')]";
	public static final String SEARCH_TEXT_BOX = "[id='P30_CP_FC_SEARCH']";
	public static final String GO_BTN = "//div[@id='closed']//div[contains(@id,'NewProjects')]//button[contains(text(),'GO')]";
	public static final String PROJECT_TABLE_ROWS = "//*[@id='report_table_projects-classic-report']/tbody/tr";
	public static final String CLOSED_PROJECT_PAGINATION = "//div[@id='report_projects-classic-report']//*[@class='t-Report-paginationText']/a";
    
	public static final String CLOSED_PROJECT_FILTER_BTN = "#B559086133385581253";
	public static final String CLOSED_PROJECT_FILTER_BOX = "//div[@id='fccp_fr']";
	public static final String CLOSED_PROJECT_FILTER_HEADER = "//h3[@id='fccp_fr_0']//span[@class='a-FS-label']";
	public static final String CLOSED_PROJECT_FILTER_CLEAR_BUTTON = "//h3[@id='fccp_fr_0'] //button[text()='Clear']";
	public static final String CLOSED_PROJECT_FILTER_MENU_CHECKBOX = "//div[@id='fccp_fr']//div[@aria-labelledby='fccp_fr_0_lbl']/div";
	public static final String CLOSED_PROJECT_APPLIED_FILTER = "//div[@id='cp_top_region']/ul/li[@class='a-FS-currentItem']/button";
	public static final String CLOSED_PROJECT_TYPE_SELECTOR = "//ul[@id='R530072318487821345_cards']//div[@class='t-Card']";
	public static final String CLOSED_PROJECT_TABLE_PROJECT_TYPE = "//table[@id='report_table_projects-classic-report']//td[@headers='PROJECT_TYPE']";
	//public static final String CLOSED_PROJECT_PAGINATION = "//div[@id='report_projects-classic-report']//table[@class='t-Report-pagination t-Report-pagination--bottom']";
	
	public static final String CLOSE_PROJECT_BTN = "//span[contains(text(),'Close Project')]";
	public static final String CLOSURE_REMARK_TXTBOX = "(//textarea[@id='P22_REMARK'])[1]";
	public static final String CLOSUREQUEST_SUBMIT_BTN = "(//button[normalize-space()='Submit'])[1]";
	public static final String CLOSE_REQUEST_FRAME = "//iframe[contains(@title,'Closure Request')]";
	public static final String REVISED_CLOSURE_DATE = "//input[@id='P22_PROJECT_END_DATE']";
	public static final String CALENDAR_ENABLED_DATELIST="//td[contains(@data-handler,'selectDay')]";
	public static final String PROJECT_NUMBER="//td[@headers='PROJECT_NUMBER']";

	/**
	 * Created by Harshith Kaup
	 */
	public static final String FIXEDPRICE_PROJECTTYPE_UNDER_CLOSEDPROJECT = "//*[@id='R530072318487821345_cards']/li[1]/div/a/div[2]/h3[contains(text(), 'Fixed Price')]/../h4";
	public static final String SUPPORTPROJECT_PROJECTTYPE_UNDER_CLOSEDPROJECT = "//*[@id='R530072318487821345_cards']/li[2]/div/a/div[2]/h3[contains(text(), 'Support Project')]/../h4";
	public static final String STAFFING_PROJECTTYPE_UNDER_CLOSEDPROJECT = "//*[@id='R530072318487821345_cards']/li[3]/div/a/div[2]/h3[contains(text(), 'Staffing')]/../h4";
	public static final String TIMEANDMATERIAL_PROJECTTYPE_UNDER_CLOSEDPROJECT = "//*[@id='R530072318487821345_cards']/li[4]/div/a/div[2]/h3[contains(text(), 'Time and Material')]/../h4";
	public static final String ClEARBUTTON_PROJECTTYPEFILTER__UNDER_CLOSEDPROJECT = "//*[@id='fccp_fr_0']/button[contains(text(), 'Clear')]";
	public static final String ClEARBUTTON_SUBVERTICALFILTER_UNDER_CLOSEDPROJECT = "//*[@id='fccp_fr_1']/button[contains(text(), 'Clear')]";
	public static final String ClEARBUTTON__SUBVERTICALFILTERHEADFILTER__UNDER_CLOSEDPROJECT = "//*[@id='fccp_fr_2']/button[contains(text(), 'Clear')]";
	public static final String SUBVERTICALFILTER_SEARCHBOXENTRY = "//*[@id='fccp_fr_1_f']";
	public static final String SUBVERTICALFILTERHEAD_SEARCHBOXENTRY = "//*[@id='fccp_fr_2_f']";
	public static final String CLEARALLBUTTON_CLOSEDPROJECT_UI = "//*[@id='cp_top_region']/ul/li[2]/button[contains(text(), 'Clear All')]";
	public static final String SUBVERTICALFILTER_UNDERFILTERPANEL_MENU_CHECKBOX = "//div[@id='fccp_fr']//div[@aria-labelledby='fccp_fr_1_lbl']/div[@class='apex-item-option']/label/span[@class='label']";
	public static final String SUBVERTICALHEADFILTER_UNDERFILTERPANEL_MENU_CHECKBOX = "//div[@id='fccp_fr']//div[@aria-labelledby='fccp_fr_2_lbl']/div[@class='apex-item-option']/label/span[@class='label']";


}
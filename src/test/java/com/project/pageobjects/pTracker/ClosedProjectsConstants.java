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
}
